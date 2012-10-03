/**
 * <p>Title:Channels Server</p>
 * <p>Description:Taifook Securities Trading Terminal</p>
 * @author Hu Xin
 * @version 1.0
 */
package com.itsz.sht.servlet.util;

import com.taifook.common.socket.SocketExecute;
import com.taifook.common.socket.SocketMessage;

public class SyncDataCallBack implements SocketExecute{
	private static final long serialVersionUID = 1L;

	public SocketMessage execute(SocketMessage msg) {
		return SyncDataProcessor.execute(msg);
	}
}
