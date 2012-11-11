//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.1.0/xslt/JavaClass.xsl

package cn.hxex.actionform.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;

/** 
 * MyEclipse Struts
 * Creation date: 06-04-2006
 * 
 * XDoclet definition:
 * @struts.action path="/dynaForm" name="dynaForm" input="/dynaFormInput.jsp" scope="request"
 * @struts.action-forward name="display" path="/dynaForm.jsp"
 */
public class DynaFormAction extends Action {

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
		DynaActionForm dynaForm = (DynaActionForm) form;
		
		String firstName = dynaForm.getString("firstName");
		String lastName = dynaForm.getString("lastName");
		String friend[] = (String[])dynaForm.get("friend");
		System.out.println( friend[0] );
		
		return mapping.findForward("display");
	}

}

