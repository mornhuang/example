package cn.hxex.springview.excel;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.servlet.view.document.AbstractExcelView;

public class HomePage extends AbstractExcelView {

	protected void buildExcelDocument(Map model, HSSFWorkbook wb,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HSSFSheet sheet;
		HSSFRow sheetRow;
		HSSFCell cell;

		sheet = wb.createSheet("Spring");
		sheet.setDefaultColumnWidth((short) 12);

		// write a text at A1
		cell = getCell(sheet, 0, 0);
		setText(cell, "Spring-Excel test");

		List words = (List) model.get("wordList");
		for (int i = 0; i < words.size(); i++) {
			cell = getCell(sheet, 2 + i, 0);
			setText(cell, (String) words.get(i));

		}

	}

}
