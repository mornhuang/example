package com.itsz.sht.admin.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.itsz.sht.common.user.UserManagement;
import com.taifook.adminportal.model.OnLineUserInfo;
import com.taifook.common.socket.SocketMessage;

/**
 * $Id: NotifyInvalidUserAction.java,v 1.1 2010/11/09 03:57:15 kyzou Exp $
 * @Project:portal
 * @File:NotifyInvalidUserAction.java
 * @Description:
 * @Author:kyzou
 * @Date:2008-6-24
 */
public class NotifyInvalidUserAction {
	Log log = LogFactory.getLog(NotifyInvalidUserAction.class);
	
	public static SocketMessage execute(SocketMessage msg) {
		OnLineUserInfo user = (OnLineUserInfo)msg.getContext();
		StringBuffer str = new StringBuffer();
		str.append("User:");
		str.append(user.getLoginId());
		str.append(" does not exist in ");
		str.append(user.getChannelCode());
		str.append(" system");
		int result = UserManagement.RemoveUser(user.getChannelCode(), user.getLoginId());
		if(result==1){
			return new SocketMessage(com.taifook.adminportal.common.Constants.SOCKET_TRANSFER_SUCCESS,
					com.taifook.adminportal.common.Constants.SOCKET_TRANSFER_SUCCESS,
					com.taifook.adminportal.common.Constants.SOCKET_TRANSFER_SUCCESS);
		}else if(result==0){
			return new SocketMessage(com.taifook.adminportal.common.Constants.SOCKET_TRANSFER_FAIL,
					com.taifook.adminportal.common.Constants.SOCKET_TRANSFER_FAIL,
					str.toString());
		}else{
			return new SocketMessage(com.taifook.adminportal.common.Constants.SOCKET_TRANSFER_FAIL,
					com.taifook.adminportal.common.Constants.SOCKET_TRANSFER_FAIL,
					str.toString());
		}
	}
}
