//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.1.0/xslt/JavaClass.xsl

package cn.hxex.action.struts.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.XMLOutputter;

/** 
 * MyEclipse Struts
 * Creation date: 06-20-2006
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class OutputXMLAction extends Action {

	// --------------------------------------------------------- Instance Variables

	// --------------------------------------------------------- Methods

	/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {

		response.setContentType("text/xml");

		Document doc = new Document();
		Element root = new Element("users");
		doc.setRootElement(root);

		Element e = new Element("user");
		e.setAttribute("name", "Mike");
		e.setAttribute("age", "30");
		root.addContent(e);

		e = new Element("user");
		e.setAttribute("name", "David");
		e.setAttribute("age", "45");
		root.addContent(e);

		XMLOutputter xout = new XMLOutputter();
		xout.output(doc, response.getWriter());
		response.getWriter().close();

		return null;
	}

}
