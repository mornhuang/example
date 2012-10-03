package com.taifook.adminportal.web.onlineuser.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.itsz.sht.admin.service.syndata.SynQueryOnLineUserCallBack;
import com.taifook.adminportal.common.Constants;
import com.taifook.adminportal.common.ServiceManager;
import com.taifook.adminportal.dao.OnLineUserDAO;
import com.taifook.adminportal.exceptions.SocketTransferException;
import com.taifook.adminportal.model.CsOnlineuser;
import com.taifook.adminportal.model.CsOnlineuserKey;
import com.taifook.adminportal.model.OnLineUserInfo;
import com.taifook.common.socket.ClientSocketService;
import com.taifook.common.socket.SocketMessage;

/**
 * $Id: QueryOnLineUserForKillAction.java,v 1.3 2011/01/18 04:51:58 kyzou Exp $
 * @Project:admin-portal
 * @File:QueryOnLineUserForKillAction.java
 * @Description:
 * @Author:kyzou
 * @Date:2008-6-24
 */
public class QueryOnLineUserForKillAction  extends Action{

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Log log = LogFactory.getLog(this.getClass()); 
		String forward=Constants.SUCCESS;
		OnLineUserInfo user = new OnLineUserInfo();
		user.setChannelCode(request.getParameter("channelcode"));
		user.setLoginId(request.getParameter("userid"));
		try{
			SocketMessage message = new ClientSocketService().startSend(new SocketMessage(Constants.NOTIFY_QUERYONLINEUSER, Constants.UPDATE_ACTION,user,new SynQueryOnLineUserCallBack()),true,user.getChannelCode());
			if(!Constants.SOCKET_TRANSFER_SUCCESS.equals(message.getNotifyObject())){
				log.error("QueryOnLineUserForKill-execute:Query Exception!");
				log.error(message.getContext());		
				request.setAttribute(Constants.GLOBAL_ERROR,message.getContext().toString());			
				forward=Constants.SUCCESS;
				return mapping.findForward(forward);
			}
//			List userinfos = (ArrayList)message.getContext();
			OnLineUserInfo userinfos = (OnLineUserInfo)message.getContext();
			CsOnlineuserKey key = new CsOnlineuserKey();
			key.setChannelcode(userinfos.getChannelCode());
			key.setUserid(userinfos.getLoginId());
			CsOnlineuser csuser = (CsOnlineuser)((OnLineUserDAO) ServiceManager.getInstance()
					.getService("com.taifook.adminportal.service.OnLineUserService")).findById(key);
			if(csuser!=null){
				userinfos.setLogintime(csuser.getLogintime());
				userinfos.setSessionId(csuser.getSessionid());
			}
			request.setAttribute("userinfo", userinfos);
		} catch (SocketTransferException e) {
			log.error("QueryOnLineUserForKill-execute:Query Exception!");
			log.error(e.getMessage());		
			request.setAttribute(Constants.GLOBAL_ERROR,"QueryOnLineUserForKill Exception:"+e.getMessage());			
			forward=Constants.FAILURE;
		} catch (Exception e) {
			log.error("QueryOnLineUserForKill-execute:Query Exception!");
			log.error(e.getMessage());			
			request.setAttribute(Constants.GLOBAL_ERROR,"QueryOnLineUserForKill fail! "+e.getMessage());			
			forward=Constants.FAILURE;
		}
		return mapping.findForward(forward);
	}

}
