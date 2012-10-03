//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.1.0/xslt/JavaClass.xsl

package cn.hxex.actionform.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.LazyValidatorForm;

/** 
 * MyEclipse Struts
 * Creation date: 06-04-2006
 * 
 * XDoclet definition:
 * @struts.action path="/lazyDynaForm" name="lazyDynaForm" input="/lazyDynaFormInput.jsp" scope="request"
 * @struts.action-forward name="display" path="/lazyDynaForm.jsp"
 */
public class LazyDynaFormAction extends Action {

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
		LazyValidatorForm lazyDynaForm = (LazyValidatorForm) form;
		
		String firstName = (String)lazyDynaForm.get("firstName");
		String lastName = (String)lazyDynaForm.get("lastName");
		String friend1 = (String)lazyDynaForm.get("friend[0].name");
		
		return mapping.findForward("display");
	}

}

