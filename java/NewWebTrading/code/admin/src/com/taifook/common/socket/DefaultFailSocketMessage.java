package com.taifook.common.socket;

import com.taifook.adminportal.common.Constants;
public class DefaultFailSocketMessage extends SocketMessage{
	
	public DefaultFailSocketMessage(String failMsg){
		super(Constants.SOCKET_TRANSFER_FAIL,Constants.SOCKET_TRANSFER_FAIL,failMsg);
	}
		
	
}
