package com.itsz.sht.admin.service.syndata;

import com.itsz.sht.admin.service.NotifyChannelAction;
import com.taifook.common.socket.SocketExecute;
import com.taifook.common.socket.SocketMessage;

public class SynChannelCallBack implements SocketExecute{
	
	public SocketMessage execute(SocketMessage msg) {
		return NotifyChannelAction.execute(msg);
	}
}
