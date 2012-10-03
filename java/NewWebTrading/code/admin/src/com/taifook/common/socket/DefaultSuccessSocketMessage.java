package com.taifook.common.socket;

import com.taifook.adminportal.common.Constants;

public class DefaultSuccessSocketMessage extends SocketMessage{
	
	public DefaultSuccessSocketMessage(){
		super(Constants.SOCKET_TRANSFER_SUCCESS,Constants.SOCKET_TRANSFER_SUCCESS,Constants.SOCKET_TRANSFER_SUCCESS);
	}
}
