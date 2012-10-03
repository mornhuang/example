package com.itsz.sht.struts.action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
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
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import sun.misc.resources.Messages;

import com.itsz.sht.common.Constants;
import com.itsz.sht.common.Consts;
import com.itsz.sht.common.DataModelUtil;
import com.itsz.sht.common.DateHelper;
import com.itsz.sht.common.model.request.TradeListRequestModel;
import com.itsz.sht.common.model.response.TradeListResponseModel;
import com.itsz.sht.common.user.UserObject;
import com.itsz.sht.struts.form.TradeListForm;
import com.itsz.sht.vp.ViewProvider;
import com.taifook.mcs.core.beans.msg.TradeListInfo;

public class ExcelExportAction extends ITSZAction {
	private Map<String, CellStyle> styles;
	private int rowIndex = 0;
	
	@Override
	public ActionForward executeAction(ViewProvider vp, ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		rowIndex = 0;
		TradeListRequestModel model = new TradeListRequestModel();
		Locale locale = (Locale) request.getSession().getAttribute(Consts.Global.Common.defaultLocaleAttributeName);
		DataModelUtil.form2Model(request,(TradeListForm)form, model, response);
		String type = request.getParameter("type");
		if(type!=null && request.getSession().getAttribute("resultModel")!=null){
			model = (TradeListRequestModel)request.getSession().getAttribute("resultModel");
			model.setPageNo(null);
		}
		UserObject user = (UserObject) request.getSession().getAttribute(Constants.USER);
		if (user != null) {
			model.setLoginId(user.getLoginName());
			if(StringUtils.isBlank(model.getAccountId())){
				model.setAccountId(user.getTradeInfoObject().getTradingAccount());
			}
		}
		if (model.getFromDate() == null) {
			model.setFromDate(DateHelper.formatDate1(new Date()));
		}
		if (model.getToDate() == null) {
			model.setToDate(DateHelper.formatDate1(new Date()));
		}
		TradeListResponseModel responseModel = facade.enquireTradeList(model);
		Workbook wb = exportExcelWorkbook(responseModel, model.getAccountId(), locale);
		
		response.setContentType(Constants.EXCEL_TYPE);
		setFileDownloadHeader(response, "tradeBook.xls");
		try {
			wb.write(response.getOutputStream());
			response.getOutputStream().flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private Workbook exportExcelWorkbook(TradeListResponseModel responseModel, String account, Locale locale) {
		Workbook wb = new HSSFWorkbook();

		createStyles(wb);

		Sheet s = wb.createSheet(DateHelper.formatDate3(new Date()));

//		s.createFreezePane(0, 2, 0, 2);
		s.setColumnWidth(0, 6000);
		for (int i = 1; i < 9; i++) {
			s.autoSizeColumn(i);
		}
		generateTitle(s, locale);
		generateHeader(s, account, locale);
		generateContent(s, responseModel, locale);

		return wb;
	}

	private void generateTitle(Sheet s, Locale locale) {
		Row r = s.createRow(rowIndex++);
		Cell c = r.createCell(0);
		c.setCellValue(Messages.getBundle("com.itsz.sht.properties.ApplicationResourcesWEB", locale).getString("message.history1.row1"));
		c.setCellStyle(styles.get("header"));
		
		r = s.createRow(rowIndex++);
		c = r.createCell(0);
		c.setCellValue(Messages.getBundle("com.itsz.sht.properties.ApplicationResourcesWEB", locale).getString("message.history1.row2") + DateHelper.formatDate1(new Date()));
		c.setCellStyle(styles.get("header"));
		//add blank row
		r = s.createRow(rowIndex++);
		c = r.createCell(0);
		c.setCellValue("");
		s.addMergedRegion(CellRangeAddress.valueOf("$A$1:$I$1"));
		s.addMergedRegion(CellRangeAddress.valueOf("$A$2:$I$2"));
	}

	private void generateHeader(Sheet s, String account, Locale locale) {
		Row r = s.createRow(rowIndex++);
		CellStyle headerStyle = styles.get("header");

		Cell c = r.createCell(0);
		c.setCellValue(Messages.getBundle("com.itsz.sht.properties.ApplicationResourcesWEB", locale).getString("message.history1.row3") + account + Messages.getBundle("com.itsz.sht.properties.ApplicationResourcesWEB", locale).getString("message.history1.row4"));
		c.setCellStyle(headerStyle);
		s.addMergedRegion(CellRangeAddress.valueOf("$A$4:$I$4"));
		
		r = s.createRow(rowIndex++);
		c = r.createCell(0);
		c.setCellValue(Messages.getBundle("com.itsz.sht.properties.ApplicationResourcesWEB", locale).getString("message.history1.row5"));
		c.setCellStyle(headerStyle);
		
		c = r.createCell(1);
		c.setCellValue(Messages.getBundle("com.itsz.sht.properties.ApplicationResourcesWEB", locale).getString("message.history1.row6"));
		c.setCellStyle(headerStyle);
		
		c = r.createCell(2);
		c.setCellValue(Messages.getBundle("com.itsz.sht.properties.ApplicationResourcesWEB", locale).getString("message.history1.row7"));
		c.setCellStyle(headerStyle);
		
		c = r.createCell(3);
		c.setCellValue(Messages.getBundle("com.itsz.sht.properties.ApplicationResourcesWEB", locale).getString("message.history1.row8"));
		c.setCellStyle(headerStyle);
		
		c = r.createCell(4);
		c.setCellValue(Messages.getBundle("com.itsz.sht.properties.ApplicationResourcesWEB", locale).getString("message.history1.row9"));
		c.setCellStyle(headerStyle);
		
		c = r.createCell(5);
		c.setCellValue(Messages.getBundle("com.itsz.sht.properties.ApplicationResourcesWEB", locale).getString("message.history1.row10"));
		c.setCellStyle(headerStyle);
		
		c = r.createCell(6);
		c.setCellValue(Messages.getBundle("com.itsz.sht.properties.ApplicationResourcesWEB", locale).getString("message.history1.row11"));
		c.setCellStyle(headerStyle);
		
		c = r.createCell(7);
		c.setCellValue(Messages.getBundle("com.itsz.sht.properties.ApplicationResourcesWEB", locale).getString("message.history1.row12"));
		c.setCellStyle(headerStyle);
		
		c = r.createCell(8);
		c.setCellValue(Messages.getBundle("com.itsz.sht.properties.ApplicationResourcesWEB", locale).getString("message.history1.row13"));
		c.setCellStyle(headerStyle);
	}

	@SuppressWarnings("unchecked")
	private void generateContent(Sheet s, TradeListResponseModel responseModel, Locale locale) {
//		CellStyle dateCellStyle = styles.get("dateCell");
		CellStyle numberCellStyle = styles.get("numberCell");
		if (responseModel.getResponse() != null) {
			Collection<TradeListInfo> tradeInfos = responseModel.getResponse().getTradeListInfos();
	
			for (TradeListInfo trade : tradeInfos) {
				Row r = s.createRow(rowIndex++);
	
				Cell c = r.createCell(0);
				c.setCellValue(trade.getBusinessDate());
//				c.setCellStyle(dateCellStyle);
	
				c = r.createCell(1);
				c.setCellValue(trade.getInstrCode());
	
				c = r.createCell(2);
				c.setCellValue(trade.getInstrName());
				
				c = r.createCell(3);
				c.setCellValue(trade.getTradeSide());
				
				c = r.createCell(4);
				c.setCellValue(trade.getExecutedQty().intValue());
				
				c = r.createCell(5);
				c.setCellValue(trade.getExecutedPrice().doubleValue());
				c.setCellStyle(numberCellStyle);
				
				c = r.createCell(6);
				if(trade.getAmount() != null){
					c.setCellValue(trade.getAmount().doubleValue());
				}else{
					c.setCellValue("");
				}
	
				c = r.createCell(7);
				c.setCellValue(Messages.getBundle("com.itsz.sht.properties.ApplicationResourcesWEB", locale).getString("channel.type."+trade.getChannelType()));
	
				c = r.createCell(8);
				c.setCellValue(trade.getMcsOrderId());
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
	
	private void setFileDownloadHeader(HttpServletResponse response, String fileName) {
		try {
			String encodedfileName = new String(fileName.getBytes("UTF-8"), "ISO8859-1");
			response.setContentType( "application/vnd.ms-excel;   charset=UTF-8 "); 
			response.setHeader("Content-Disposition", "attachment; filename=\"" + encodedfileName + "\"");
		} catch (UnsupportedEncodingException e) {
		}
	}
}
