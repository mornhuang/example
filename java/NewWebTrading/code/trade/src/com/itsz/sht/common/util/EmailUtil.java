/**
 * Copyright (c) 2003 Tai Fook Securities Group Limited.
 * All rights reserved.
 *
 * This file contains the valuable properties of Tai Fook Securities
 * Group Limited, embodying substantial creative efforts and confidential
 * information, ideas and expressions. No part of this file may be
 * reproduced or distributed in any form or by any means, or stored
 * in a data base or a retrieval system, without the prior written
 * permission of Tai Fook Securities Group Limited.
 *
 */
package com.itsz.sht.common.util;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeUtility;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.HtmlEmail;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;

import com.itsz.sht.common.DateHelper;
import com.itsz.sht.common.PropertyConfig;
import com.itsz.sht.dao.hibernate.model.FundDeposit;


public class EmailUtil {

    private static Log log = LogFactory.getLog(EmailUtil.class);
	private Map<String, CellStyle> styles;
	private int rowIndex = 0;
    
    private String host;
    private String target;
    private String from;
    private String formName;
    
    public void setHost(String host) {
        this.host = host;
    }
    
    public void setTarget(String target) {
        this.target = target;
    }
    
    public void setFrom(String from) {
        this.from = from;
    }

    public void setFormName(String formName) {
		this.formName = formName;
	}

	public void sendEmail(String subject, String content) {        
        HtmlEmail email = new HtmlEmail();
        subject = subject + formatCurrentTimestamp(new Date());
        try{
            log.info("Send email to " + target);            
            if (log.isDebugEnabled()) {
                log.info("Email Subject [" + subject + "] + Content [" + content + "]");
            }
            email.setHostName(host);
            email.setFrom(from, formName);          
            Collection<Object> tos = getInternetAddress();            
            email.setTo(tos);

            email.setSubject(subject);
            email.setHtmlMsg(content);        
            email.setCharset("UTF-8");
    
            email.send();
        }catch(Exception ex){
            log.error("Email send failure.", ex);
        }
    }
    
    private String PATTERN = " (yyyy-MM-dd HH:mm:ss)";
    private String formatCurrentTimestamp(Date date) {        
        if (date != null) {            
            SimpleDateFormat sdf = new SimpleDateFormat(PATTERN);
            return sdf.format(date);
        } 
        
        return "";
    }
    private Collection<Object> getInternetAddress() {
    	target = PropertyConfig.getCommonProperty("fundDeposit_emailTarget");
		Collection<Object> tos = new ArrayList<Object>();

		if ("".equals(target) || target == null) {
			return tos;

		}
		try {
			if (target.indexOf(",") != -1) {
				StringTokenizer st1 = new StringTokenizer(target, ",");
				while (st1.hasMoreTokens()) {
					tos.add(new InternetAddress(st1.nextToken()));
				}
			} else if (target.indexOf(";") != -1) {
				StringTokenizer st1 = new StringTokenizer(target, ";");
				while (st1.hasMoreTokens()) {
					tos.add(new InternetAddress(st1.nextToken()));
				}
			} else {
				tos.add(new InternetAddress(target));
			}
		} catch (AddressException e) {
			log.error("parse target["+target+"] to InternetAddress failed!",e);
			tos = new ArrayList<Object>();
		}
		return tos;

	}    
	
	public void sendEmail(String subject, String content,String attFilePath,List<FundDeposit> fundDeposits) {
        HtmlEmail email = new HtmlEmail();
        subject = subject + formatCurrentTimestamp(new Date());
        try{
            log.info("Send email to " + target);            
            if (log.isDebugEnabled()) {
                log.info("Email Subject [" + subject + "] + Content [" + content + "]");
            }
            email.setHostName(host);
            email.setFrom(from, formName);          
            Collection<Object> tos = getInternetAddress();            
            email.setTo(tos);

            email.setSubject(subject);
            email.setHtmlMsg(content);
            //
            EmailAttachment attachment = new EmailAttachment(); 
            String fileName = generateFileName();
            attachment.setPath(generateAttFile(attFilePath,fileName,fundDeposits)); 
            attachment.setDisposition(EmailAttachment.ATTACHMENT); 
            attachment.setDescription(MimeUtility.encodeWord("Attachment","UTF-8",null)); 
            attachment.setName(MimeUtility.encodeWord(fileName,"UTF-8",null));
            email.attach(attachment);
            email.setCharset("UTF-8");    
            email.send();
            delFile(attachment.getPath());
        }catch(Exception ex){
            log.error("Email send failure.", ex);
        }
    }
	
	private void delFile(String  filePath) {
		try {
			File file = new File(filePath);
			if (!file.exists()) {
				log.info("File does not exist!");
				return;
			}
			boolean rs = file.delete();
			if (rs){
				log.info("delete file successful!");
			}			
			else{
				log.info("delete file failure!");
			}			
		} catch (Exception e) {
			log.error("Email send delFile failure.", e);
		}		
	}
    
    private String generateAttFile(String attFilePath,String attFileName,List<FundDeposit> fundDeposits){
    	File filepath = new File(attFilePath);
    	if(filepath.exists()==false){
    		filepath.mkdirs();
    	}
    	File file = new File(attFilePath+attFileName);
		FileOutputStream fileOut;
		try {
			fileOut = new FileOutputStream(file);
			Workbook wb = exportExcelWorkbook(fundDeposits);
			wb.write(fileOut);
			fileOut.flush();
			fileOut.close();

		} catch (Exception e) {
			log.error("Email send generateAttFile failure.", e);
		}
		return (attFilePath+attFileName);
    }
    
    private String generateFileName(){
    	return "FundDeposit-"+DateHelper.formatDate5(new Date())+".xls";
    }
    
	private Workbook exportExcelWorkbook(List<FundDeposit> fundDeposits) {
		Workbook wb = new HSSFWorkbook();
		createStyles(wb);
		Sheet s = wb.createSheet(DateHelper.formatDate4(new Date()));
		s.setColumnWidth(0, 6000);
		for (int i = 1; i < 12; i++) {
			s.autoSizeColumn(i);
		}
		generateTitle(s);
		generateHeader(s);
		generateContent(s, fundDeposits);		
		return wb;
	}

	private void generateTitle(Sheet s) {
		rowIndex=0;
		Row r = s.createRow(rowIndex++);
		Cell c = r.createCell(0);
		c.setCellValue("Fund Deposit Checking - "+DateHelper.formatDate5(new Date()));
		c.setCellStyle(styles.get("header"));
		
//		r = s.createRow(rowIndex++);
//		c = r.createCell(0);
//		c.setCellValue("Request No: FDC" + DateHelper.formatDate5(new Date()));
//		c.setCellStyle(styles.get("header"));
		//add blank row
		r = s.createRow(rowIndex++);
		c = r.createCell(0);
		c.setCellValue("");
		s.addMergedRegion(CellRangeAddress.valueOf("$A$1:$I$1"));
		s.addMergedRegion(CellRangeAddress.valueOf("$A$2:$I$2"));
	}

	private void generateHeader(Sheet s) {
		Row r = s.createRow(rowIndex++);
		CellStyle headerStyle = styles.get("header");

//		Cell c = r.createCell(0);
//		c.setCellValue("  ");
//		c.setCellStyle(headerStyle);
//		s.addMergedRegion(CellRangeAddress.valueOf("$A$4:$I$4"));
		
//		r = s.createRow(rowIndex++);
		Cell c = r.createCell(0);
		c.setCellValue("Request No");
		c.setCellStyle(headerStyle);
		
		c = r.createCell(1);
		c.setCellValue("Receive Date");
		c.setCellStyle(headerStyle);
		
		c = r.createCell(2);
		c.setCellValue("Receive Time");
		c.setCellStyle(headerStyle);
		
		c = r.createCell(3);
		c.setCellValue("Account Name");
		c.setCellStyle(headerStyle);
		
		c = r.createCell(4);
		c.setCellValue("Account No");
		c.setCellStyle(headerStyle);
		
		c = r.createCell(5);
		c.setCellValue("Currency");
		c.setCellStyle(headerStyle);
		
		c = r.createCell(6);
		c.setCellValue("Amount");
		c.setCellStyle(headerStyle);
		
		c = r.createCell(7);
		c.setCellValue("Deposit Date");
		c.setCellStyle(headerStyle);
		
		c = r.createCell(8);
		c.setCellValue("Deposit Time");
		c.setCellStyle(headerStyle);
		
		c = r.createCell(9);
		c.setCellValue("Bank");
		c.setCellStyle(headerStyle);
		
		c = r.createCell(10);
		c.setCellValue("Bank A/C");
		c.setCellStyle(headerStyle);
		
		c = r.createCell(11);
		c.setCellValue("Source");
		c.setCellStyle(headerStyle);
		
		c = r.createCell(12);
		c.setCellValue("Reference");
		c.setCellStyle(headerStyle);
	}

	@SuppressWarnings("unchecked")
	private void generateContent(Sheet s, List<FundDeposit> fundDeposits) {
//		CellStyle dateCellStyle = styles.get("dateCell");
		CellStyle numberCellStyle = styles.get("numberCell");
		if (fundDeposits != null) {
			for (Iterator iterator = fundDeposits.iterator(); iterator
					.hasNext();) {
				FundDeposit fundDeposit = (FundDeposit) iterator.next();
				
				Row r = s.createRow(rowIndex++);
	
				Cell c = r.createCell(0);
				c.setCellValue(fundDeposit.getRequestNo());
				
				c = r.createCell(1);
				c.setCellValue(DateHelper.formatDate3(fundDeposit.getReceiveDate()));
	
				c = r.createCell(2);
				c.setCellValue(DateHelper.formatTime(fundDeposit.getReceiveDate()));
	
				c = r.createCell(3);
				c.setCellValue(fundDeposit.getAccountName());
				
				c = r.createCell(4);
				c.setCellValue(fundDeposit.getAccountNo());
				
				c = r.createCell(5);
				c.setCellValue(fundDeposit.getCurrency());
				
				c = r.createCell(6);
				c.setCellValue(fundDeposit.getAmount().doubleValue());
				c.setCellStyle(numberCellStyle);
				
				c = r.createCell(7);
				c.setCellValue(DateHelper.formatDate3(fundDeposit.getDepositDate()));
	
				c = r.createCell(8);
				c.setCellValue(DateHelper.formatTime(fundDeposit.getDepositDate()));
	
				c = r.createCell(9);
				c.setCellValue(fundDeposit.getBank());

				c = r.createCell(10);
				c.setCellValue(fundDeposit.getBankAcc());
				
				c = r.createCell(11);
				c.setCellValue(fundDeposit.getSource());
				
				c = r.createCell(12);
				c.setCellValue(fundDeposit.getReference());
			}
		}
	}

	private Map<String, CellStyle> createStyles(Workbook wb) {
		styles = new HashMap<String, CellStyle>();
		DataFormat df = wb.createDataFormat();

		// --字体设定 --//

		//普通字体
		Font normalFont = wb.createFont();
		normalFont.setFontHeightInPoints((short) 10);

		//加粗字体
		Font boldFont = wb.createFont();
		boldFont.setFontHeightInPoints((short) 10);
		boldFont.setBoldweight(Font.BOLDWEIGHT_BOLD);

		//蓝色加粗字体
		Font blueBoldFont = wb.createFont();
		blueBoldFont.setFontHeightInPoints((short) 10);
		blueBoldFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
		blueBoldFont.setColor(IndexedColors.BLUE.getIndex());

		// --Cell Style设定-- //

		//标题格式
		CellStyle headerStyle = wb.createCellStyle();
		headerStyle.setFont(boldFont);
		styles.put("header", headerStyle);

		//日期格式
		CellStyle dateCellStyle = wb.createCellStyle();
		dateCellStyle.setFont(normalFont);
		dateCellStyle.setDataFormat(df.getFormat("yyyy"));
//		setBorder(dateCellStyle);
		styles.put("dateCell", dateCellStyle);

		//数字格式
		CellStyle numberCellStyle = wb.createCellStyle();
		numberCellStyle.setFont(normalFont);
		numberCellStyle.setDataFormat(df.getFormat("#,##0.00"));
//		setBorder(numberCellStyle);
		styles.put("numberCell", numberCellStyle);

		//合计列格式
		CellStyle totalStyle = wb.createCellStyle();
		totalStyle.setFont(blueBoldFont);
		totalStyle.setWrapText(true);
		totalStyle.setAlignment(CellStyle.ALIGN_RIGHT);
		setBorder(totalStyle);
		styles.put("total", totalStyle);

		return styles;
	}

	private void setBorder(CellStyle style) {
		//设置边框
		style.setBorderRight(CellStyle.BORDER_THIN);
		style.setRightBorderColor(IndexedColors.BLACK.getIndex());

		style.setBorderLeft(CellStyle.BORDER_THIN);
		style.setLeftBorderColor(IndexedColors.BLACK.getIndex());

		style.setBorderTop(CellStyle.BORDER_THIN);
		style.setTopBorderColor(IndexedColors.BLACK.getIndex());

		style.setBorderBottom(CellStyle.BORDER_THIN);
		style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
	}
	
//	private void setFileDownloadHeader(HttpServletResponse response, String fileName) {
//		try { 
//			String encodedfileName = new String(fileName.getBytes(), "ISO8859-1");
//			
//			response.setHeader("Content-Disposition", "attachment; filename=\"" + encodedfileName + "\"");
//		} catch (UnsupportedEncodingException e) {
//		}
//	}
}
