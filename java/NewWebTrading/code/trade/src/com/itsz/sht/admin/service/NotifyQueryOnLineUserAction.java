package com.itsz.sht.admin.service;

import java.util.Iterator;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.itsz.sht.common.FormatUtil;
import com.itsz.sht.common.user.UserManagement;
import com.taifook.adminportal.common.Constants;
import com.taifook.adminportal.model.OnLineUserInfo;
import com.taifook.common.socket.SocketMessage;

/**
 * $Id: NotifyQueryOnLineUserAction.java,v 1.1 2010/11/09 03:57:15 kyzou Exp $
 * @Project:portal
 * @File:NotifyQueryOnLineUserAction.java
 * @Description:
 * @Author:kyzou
 * @Date:2008-6-24
 */
public class NotifyQueryOnLineUserAction {
	Log log = LogFactory.getLog(NotifyQueryOnLineUserAction.class);
	
	public static SocketMessage execute(SocketMessage msg) {
		SocketMessage message = new SocketMessage();
		message.setAction(msg.getAction());
		message.setCallBack(msg.getCallBack());
		OnLineUserInfo user = (OnLineUserInfo)msg.getContext();
		user.setLoginId(FormatUtil.formatLoginId(user.getLoginId()));
		List users = UserManagement.getOnLineUserInfo(user.getChannelCode(), user.getLoginId());
		if(users.size()>0){			
			message.setNotifyObject(Constants.SOCKET_TRANSFER_SUCCESS);			
			message.setContext(getResultContext(users));
		}else{
			message.setNotifyObject(Constants.SOCKET_TRANSFER_FAIL);
			StringBuffer str = new StringBuffer();
			str.append("Could not found User:");
			str.append(user.getLoginId());
			str.append(" in ");
			str.append(user.getChannelCode());
			str.append(" system");
			message.setContext(str.toString());
		}
		return message;
	}
	
	private static OnLineUserInfo getResultContext(List users){
		for (Iterator iterator = users.iterator(); iterator.hasNext();) {
			OnLineUserInfo context = (OnLineUserInfo)iterator.next();
			return context;
		}
		return (OnLineUserInfo)users.get(0);
	}
}
