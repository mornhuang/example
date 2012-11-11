//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.1.0/xslt/JavaClass.xsl

package cn.hxex.blog.struts.action;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cn.hxex.blog.dao.DaoFactory;
import cn.hxex.blog.dao.IMessageDAO;
import cn.hxex.blog.model.Message;
import cn.hxex.blog.model.ReplyMessage;
import cn.hxex.blog.struts.ext.BaseAction;
import cn.hxex.blog.struts.form.ReplyForm;

/** 
 * MyEclipse Struts
 * Creation date: 07-24-2006
 * 
 * XDoclet definition:
 * @struts.action path="/replySave" name="replyForm" input="/reply.jsp" scope="request" validate="true"
 */
public class ReplySaveAction extends BaseAction {

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
		ReplyForm replyForm = (ReplyForm) form;
		
		IMessageDAO dao = DaoFactory.getMessageDAO();
		Message message = dao.getMessage( replyForm.getMessageId() );
		if( message!=null ) {
			ReplyMessage reply = new ReplyMessage();
			reply.setUsername( replyForm.getUsername() );
			reply.setTitle( replyForm.getTitle() );
			reply.setContent( replyForm.getContent() );
			reply.setPubdate( new Timestamp( System.currentTimeMillis() ) );
			
			reply.setMessage( message );
			message.getReplies().add( reply );

			ActionForward action = new ActionForward( "/blog.do?username=" + message.getUser().getName() );
			return action;
		}
		
		addMessage( request, "error.message.not.exist" );
		return mapping.getInputForward();
	}

}

