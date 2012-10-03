package com.itsz.sht.admin.service.syndata;

import com.itsz.sht.admin.service.NotifyMobileAgentAction;
import com.taifook.common.socket.SocketExecute;
import com.taifook.common.socket.SocketMessage;

/**
 * $Id: SynMobileAgentCallBack.java,v 1.1 2011/01/18 04:56:58 kyzou Exp $
 * @Project:portal
 * @File:SynMobileAgentCallBack.java
 * @Description:
 * @Author:kyzou
 * @Date:2008-8-27
 */
public class SynMobileAgentCallBack implements SocketExecute {

	public SocketMessage execute(SocketMessage msg) {
		return NotifyMobileAgentAction.execute(msg);
	}

}
