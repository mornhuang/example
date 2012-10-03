package com.itsz.sht.admin.service;

import com.itsz.sht.admin.channel.util.ChannelInfoManagement;
import com.itsz.sht.common.user.UserManagement;
import com.taifook.adminportal.common.Constants;
import com.taifook.adminportal.model.CsParameter;
import com.taifook.common.socket.SocketMessage;

public class NotifyChannelAction{
	
	public static SocketMessage execute(SocketMessage msg) {
		
		if (msg.getAction().endsWith(Constants.ADD_ACTION)
				|| msg.getAction().endsWith(Constants.UPDATE_ACTION)) {
			CsParameter channel = (CsParameter) msg.getContext();
			if (channel != null) {
				ChannelInfoManagement.setChannelState(channel.getKey(), channel.getValue());
				if(channel.getValue().equals("stoppedAndClear")){
					//clear session
					UserManagement.clearChannelMap(channel.getKey());
				}
			}
		}
		return new SocketMessage(com.taifook.adminportal.common.Constants.SOCKET_TRANSFER_SUCCESS,
				com.taifook.adminportal.common.Constants.SOCKET_TRANSFER_SUCCESS,
				com.taifook.adminportal.common.Constants.SOCKET_TRANSFER_SUCCESS);
	}
}
