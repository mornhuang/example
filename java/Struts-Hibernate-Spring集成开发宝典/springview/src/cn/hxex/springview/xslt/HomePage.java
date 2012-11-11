package cn.hxex.springview.xslt;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMSource;

import org.jdom.Element;
import org.jdom.output.DOMOutputter;
import org.springframework.web.servlet.view.xslt.AbstractXsltView;

public class HomePage extends AbstractXsltView {

	protected Source createXsltSource(Map model, String rootName,
			HttpServletRequest req, HttpServletResponse res) throws Exception {

		org.jdom.Document doc = new org.jdom.Document();
		Element root = new Element(rootName);
		System.out.println( rootName );
		doc.setRootElement(root);

		List words = (List) model.get("wordList");
		for (Iterator it = words.iterator(); it.hasNext();) {
			String nextWord = (String) it.next();
			Element e = new Element("word");
			e.setText(nextWord);
			root.addContent(e);
		}

		return new DOMSource( new DOMOutputter().output(doc) );
	}

}
