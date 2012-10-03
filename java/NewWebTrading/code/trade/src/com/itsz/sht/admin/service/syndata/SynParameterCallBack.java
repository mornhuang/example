package com.itsz.sht.admin.service.syndata;

import com.itsz.sht.admin.service.NotifyParameterAction;
import com.taifook.common.socket.SocketExecute;
import com.taifook.common.socket.SocketMessage;

public class SynParameterCallBack implements SocketExecute{
	
	public SocketMessage execute(SocketMessage msg) {
		return NotifyParameterAction.execute(msg);
	}

}
