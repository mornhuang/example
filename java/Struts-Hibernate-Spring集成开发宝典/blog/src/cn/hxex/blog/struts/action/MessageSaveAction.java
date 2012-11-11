//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.1.0/xslt/JavaClass.xsl

package cn.hxex.blog.struts.action;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cn.hxex.blog.auth.bean.UserInfo;
import cn.hxex.blog.auth.util.AuthorityUtil;
import cn.hxex.blog.dao.DaoFactory;
import cn.hxex.blog.dao.IMessageDAO;
import cn.hxex.blog.dao.IUserDAO;
import cn.hxex.blog.model.Message;
import cn.hxex.blog.model.User;
import cn.hxex.blog.struts.ext.BaseAction;
import cn.hxex.blog.struts.form.MessageForm;

/** 
 * MyEclipse Struts
 * Creation date: 07-23-2006
 * 
 * XDoclet definition:
 * @struts.action path="/messageSave" name="messageForm" scope="request" validate="true"
 */
public class MessageSaveAction extends BaseAction {

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
		MessageForm messageForm = (MessageForm) form;
		
		Message message = new Message();
		message.setTitle( messageForm.getTitle() );
		message.setContent( messageForm.getContent() );
		message.setPubdate( new Timestamp( System.currentTimeMillis() ) );
		
		UserInfo ui = AuthorityUtil.getUser( request );
		IUserDAO userDao = DaoFactory.getUserDAO();
		User user = userDao.getUserById( ui.getUserId() );
		message.setUser( user );
		
		IMessageDAO dao = DaoFactory.getMessageDAO();
		dao.saveMessage( message );
		
		user.getMessages().add( message );
		
		return mapping.findForward( "homepage" );
	}

}

