//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.1.0/xslt/JavaClass.xsl

package cn.hxex.blog.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cn.hxex.blog.dao.DaoFactory;
import cn.hxex.blog.dao.IUserDAO;
import cn.hxex.blog.model.User;
import cn.hxex.blog.struts.ext.BaseAction;
import cn.hxex.blog.struts.form.BlogForm;

/** 
 * MyEclipse Struts
 * Creation date: 07-23-2006
 * 
 * XDoclet definition:
 * @struts.action path="/blog" name="blogForm" input="/blog.jsp" scope="request" validate="true"
 */
public class BlogAction extends BaseAction {

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
		BlogForm blogForm = (BlogForm) form;
		
		IUserDAO dao = DaoFactory.getUserDAO();
		User user = dao.getUser( blogForm.getUsername() );
		if( user==null ) {
			addMessage( request, "error.user.not.exist" );
		} else {
			request.setAttribute( "user", user );
		}
		
		return mapping.findForward( "blog" );
	}

}

