//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.1.0/xslt/JavaClass.xsl

package cn.hxex.actionform.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cn.hxex.actionform.struts.form.MapForm;

/** 
 * MyEclipse Struts
 * Creation date: 06-04-2006
 * 
 * XDoclet definition:
 * @struts.action path="/mapForm" name="mapForm" input="/mapFormInput.jsp" scope="request" validate="true"
 * @struts.action-forward name="display" path="/mapForm.jsp"
 */
public class MapFormAction extends Action {

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
	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response) {
		MapForm mapForm = (MapForm) form;

		return mapping.findForward("display");
	}

}

