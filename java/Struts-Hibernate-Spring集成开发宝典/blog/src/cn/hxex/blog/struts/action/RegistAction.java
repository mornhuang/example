//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.1.0/xslt/JavaClass.xsl

package cn.hxex.blog.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cn.hxex.blog.auth.util.AuthorityUtil;
import cn.hxex.blog.dao.DaoFactory;
import cn.hxex.blog.dao.IUserDAO;
import cn.hxex.blog.model.User;
import cn.hxex.blog.struts.ext.BaseAction;
import cn.hxex.blog.struts.form.RegistForm;

/** 
 * MyEclipse Struts
 * Creation date: 07-22-2006
 * 
 * XDoclet definition:
 * @struts.action path="/regist" name="registForm" input="/regist.jsp" scope="request" validate="true"
 */
public class RegistAction extends BaseAction {

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
		HttpServletResponse response) throws Exception {
		RegistForm registForm = (RegistForm) form;

		User user = new User();
		user.setName( registForm.getUsername() );
		user.setPassword( registForm.getPassword() );
		
		IUserDAO dao = DaoFactory.getUserDAO();
		dao.saveUser( user );
		
		AuthorityUtil.saveUser( request, user );
		
		return mapping.findForward( "homepage" );
	}

}

