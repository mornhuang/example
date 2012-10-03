package com.taifook.adminportal.web.onlineuser.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.itsz.sht.admin.service.syndata.SynInvalidUserCallBack;
import com.taifook.adminportal.common.Constants;
import com.taifook.adminportal.common.ServiceManager;
import com.taifook.adminportal.dao.OnLineUserDAO;
import com.taifook.adminportal.exceptions.SocketTransferException;
import com.taifook.adminportal.model.OnLineUserInfo;
import com.taifook.common.socket.ClientSocketService;
import com.taifook.common.socket.SocketMessage;

/**
 * $Id: InvalidLoginUserAction.java,v 1.3 2011/01/18 04:51:58 kyzou Exp $
 * @Project:admin-portal
 * @File:InvalidLoginUserAction.java
 * @Description:
 * @Author:kyzou
 * @Date:2008-6-24
 */
public class InvalidLoginUserAction  extends Action{

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Log log = LogFactory.getLog(this.getClass()); 
		String forward=Constants.SUCCESS;
		OnLineUserInfo user = new OnLineUserInfo();
		user.setChannelCode(request.getParameter("channelType"));
		user.setLoginId(request.getParameter("loginId"));
		try{
			SocketMessage message = new ClientSocketService().startSend(new SocketMessage(Constants.NOTIFY_INVALIDLOGINUSER, Constants.UPDATE_ACTION,user,new SynInvalidUserCallBack()),false,user.getChannelCode());
			if(!Constants.SOCKET_TRANSFER_SUCCESS.equals(message.getNotifyObject())){
				log.error("InvalidLoginUser-execute:InvalidLoginUser Exception!");
				log.error(message.getContext().toString());		
				request.setAttribute(Constants.GLOBAL_ERROR,message.getContext().toString());			
				forward=Constants.FAILURE;
				return mapping.findForward(forward);
			}
			((OnLineUserDAO)ServiceManager.getInstance().getService("com.taifook.adminportal.service.OnLineUserService"))
            .deletebyUser(user);
		} catch (SocketTransferException e) {
			log.error("InvalidLoginUser-execute:InvalidLoginUser Exception!");
			log.error(e.getMessage());		
			request.setAttribute(Constants.GLOBAL_ERROR,"InvalidLoginUser Exception:"+e.getMessage());			
			forward=Constants.FAILURE;
		} catch (Exception e) {
			log.error("InvalidLoginUser-execute:InvalidLoginUser Exception!");
			log.error(e.getMessage());			
			request.setAttribute(Constants.GLOBAL_ERROR,"InvalidLoginUser fail! "+e.getMessage());			
			forward=Constants.FAILURE;
		}
		return mapping.findForward(forward);
	}

}
