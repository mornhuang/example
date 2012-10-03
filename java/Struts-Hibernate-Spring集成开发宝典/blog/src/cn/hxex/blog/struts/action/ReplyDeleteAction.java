//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.1.0/xslt/JavaClass.xsl

package cn.hxex.blog.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cn.hxex.blog.auth.bean.UserInfo;
import cn.hxex.blog.auth.util.AuthorityUtil;
import cn.hxex.blog.dao.DaoFactory;
import cn.hxex.blog.dao.IMessageDAO;
import cn.hxex.blog.struts.ext.BaseAction;
import cn.hxex.blog.struts.form.IdForm;

/** 
 * MyEclipse Struts
 * Creation date: 07-24-2006
 * 
 * XDoclet definition:
 * @struts.action path="/replyDelete" name="idForm" input="/homepage.jsp" scope="request" validate="true"
 */
public class ReplyDeleteAction extends BaseAction {

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
		IdForm idForm = (IdForm) form;
		
		UserInfo user = AuthorityUtil.getUser( request );
		
		IMessageDAO dao = DaoFactory.getMessageDAO();
		dao.deleteReplyMessage( idForm.getId(), user.getUserId() );
		
		return mapping.findForward( "homepage" );
	}

}

