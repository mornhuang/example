//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.1.0/xslt/JavaClass.xsl

package cn.hxex.blog.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cn.hxex.blog.dao.DaoFactory;
import cn.hxex.blog.dao.IMessageDAO;
import cn.hxex.blog.model.Message;
import cn.hxex.blog.struts.ext.BaseAction;
import cn.hxex.blog.struts.form.MessageForm;

/** 
 * MyEclipse Struts
 * Creation date: 07-25-2006
 * 
 * XDoclet definition:
 * @struts.action path="/messageSaveUpdate" name="messageForm" input="/message_update.jsp" scope="request" validate="true"
 */
public class MessageSaveUpdateAction extends BaseAction {

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
		HttpServletResponse response) throws Exception{
		MessageForm messageForm = (MessageForm) form;

		IMessageDAO dao = DaoFactory.getMessageDAO();
		Message message = dao.getMessage( messageForm.getId() );
		if( message!=null ) {
			message.setTitle( messageForm.getTitle() );
			message.setContent( messageForm.getContent() );
			return mapping.findForward( "homepage" );
		}
		
		addMessage( request, "error.message.not.exist" );
		return mapping.findForward( "homepage" );
	}

}

