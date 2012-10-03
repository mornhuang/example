package com.itsz.sht.admin.service.syndata;

import com.itsz.sht.admin.service.NotifyInvalidUserAction;
import com.taifook.common.socket.SocketExecute;
import com.taifook.common.socket.SocketMessage;

/**
 * $Id: SynInvalidUserCallBack.java,v 1.1 2011/01/18 04:56:58 kyzou Exp $
 * @Project:portal
 * @File:SynInvalidUserCallBack.java
 * @Description:
 * @Author:kyzou
 * @Date:2008-6-24
 */
public class SynInvalidUserCallBack implements SocketExecute {

	public SocketMessage execute(SocketMessage msg) {
		return NotifyInvalidUserAction.execute(msg);
	}

}
