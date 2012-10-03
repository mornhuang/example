package com.itsz.sht.admin.service.syndata;

import com.itsz.sht.admin.service.NotifyQueryOnLineUserAction;
import com.taifook.common.socket.SocketExecute;
import com.taifook.common.socket.SocketMessage;
/**
 * $Id: SynQueryOnLineUserCallBack.java,v 1.1 2011/01/18 04:56:58 kyzou Exp $
 * @Project:portal
 * @File:SynQueryOnLineUserCallBack.java
 * @Description:
 * @Author:kyzou
 * @Date:2008-6-24
 */
public class SynQueryOnLineUserCallBack implements SocketExecute {

	public SocketMessage execute(SocketMessage msg) {
		return NotifyQueryOnLineUserAction.execute(msg);
	}

}
