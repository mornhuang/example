package com.itsz.sht.admin.service.syndata;

import com.itsz.sht.admin.service.NotifyBroadcastAction;
import com.taifook.common.socket.SocketExecute;
import com.taifook.common.socket.SocketMessage;

public class SynBroadCastCallBack implements SocketExecute{
	
	public SocketMessage execute(SocketMessage msg) {
		return NotifyBroadcastAction.execute(msg);
	}
}
