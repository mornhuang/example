package cn.hxex.springview.pdf;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

public class HomePage extends AbstractPdfView {

	protected void buildPdfDocument(Map model, Document doc, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		List words = (List) model.get("wordList");

		for (int i = 0; i < words.size(); i++)
			doc.add(new Paragraph((String) words.get(i)));

		response.setHeader("Content-disposition", "inline; filename=homepage.pdf" );
		response.setContentType( "application/pdf" );
	}
}
