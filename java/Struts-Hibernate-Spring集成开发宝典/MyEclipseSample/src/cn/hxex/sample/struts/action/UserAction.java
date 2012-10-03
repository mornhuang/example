//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.1.1/xslt/JavaClass.xsl

package cn.hxex.sample.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.Globals;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import cn.hxex.sample.struts.form.UserForm;

/** 
 * MyEclipse Struts
 * Creation date: 04-29-2006
 * 
 * XDoclet definition:
 * @struts.action path="/user" name="userForm" input="/form/user.jsp" scope="request" validate="true"
 */
public class UserAction extends Action {

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
		UserForm userForm = (UserForm) form;
		// TODO Auto-generated method stub
		
		if( "admin".equals( userForm.getName() ) 
				&& "12345".equals( userForm.getPassword() ) )
			return mapping.findForward( "success" );
		
		ActionMessages messages = this.getMessages( request );
		messages.add( Globals.ERROR_KEY, new ActionMessage( "login.failed" ) );
		this.addErrors( request, messages );
		
		return mapping.getInputForward();
	}

}

