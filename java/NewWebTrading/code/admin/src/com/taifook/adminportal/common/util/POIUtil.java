package com.taifook.adminportal.common.util;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFHeader;

public class POIUtil {
	private static Log log = LogFactory
			.getLog("com.taifook.adminportal.common.util.POIUtil");

	public static boolean exportExcel(File excelFile, String sheetName,
			String[] title, Object[][] data) {
		FileOutputStream fos = null;
		try {
			int rowTitle = 0;
			int colTitle = 0;
			HSSFWorkbook book = new HSSFWorkbook();
			HSSFSheet sheet = book.createSheet();
			book.setSheetName(0, sheetName);
			if (title != null) {
				HSSFRow titleRow = sheet.createRow(0);
				HSSFCellStyle cellStyle = book.createCellStyle();
				HSSFFont fontStyle = book.createFont();
				fontStyle.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
				cellStyle.setFont(fontStyle);
				rowTitle++;
				for (int titleIndex = 0; titleIndex < title.length; titleIndex++) {
					HSSFCell titleCell = titleRow
							.createCell((short) titleIndex);
					titleCell.setCellValue(title[titleIndex]);
					titleCell.setCellStyle(cellStyle);
					//System.out.println("title: " + title[titleIndex]);
				}
			}
			for (int rowIndex = rowTitle; rowIndex < data.length + rowTitle; rowIndex++) {
				HSSFRow row = sheet.createRow(rowIndex);
				for (int colIndex = colTitle; colIndex < data[rowIndex
						- rowTitle].length
						+ colTitle; colIndex++) {
					HSSFCell cell = row.createCell((short) colIndex);
					Object cellData = data[rowIndex - (rowTitle)][colIndex
							- (colTitle)];
					if (cellData instanceof Boolean) {
						cell.setCellType(cell.CELL_TYPE_BOOLEAN);
						cell.setCellValue(((Boolean) cellData).booleanValue());
					} else if (cellData instanceof Date) {
						cell.setCellValue((Date) cellData);
					} else if (cellData instanceof Number) {
						cell.setCellValue(((Number) cellData).doubleValue());
					} else if (cellData instanceof String) {
						cell.setCellValue((String) cellData);
					} else if (cellData instanceof Calendar) {
						cell.setCellValue((Calendar) cellData);
					}
				}
			}
			fos = new FileOutputStream(excelFile);
			book.write(fos);
			fos.flush();
			fos.close();
			log.info((new SimpleDateFormat("yyyy-MM-dd hh:mm:ss  "))
					.format(new Date())
					+ " export excel success! ");
		} catch (Exception e) {
			e.printStackTrace();
			log.error((new SimpleDateFormat("yyyy-MM-dd hh:mm:ss  "))
					.format(new Date())
					+ " export excel error: " + e.getMessage());
			return false;
		} finally {
			try {
				if (fos != null) {
					fos.close();
				}
			} catch (Exception e) {

			}
		}
		return true;
	}

	public static void main(String[] args) {
		new POIUtil().exportExcel(new File("D:\\test.xls"), "test",
				new String[] { "title1", "title2" }, new Object[][] {
						{ "data11", "data12" }, { "data21", "data22" } });
	}

}
