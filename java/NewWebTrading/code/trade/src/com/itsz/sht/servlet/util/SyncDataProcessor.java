/**
 * <p>Title:Channels Server</p>
 * <p>Description:Taifook Securities Trading Terminal</p>
 * @author Hu Xin
 * @version 1.0
 */
package com.itsz.sht.servlet.util;

import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.itsz.sht.common.Constants;
import com.itsz.sht.common.user.UserManagement;
import com.taifook.common.socket.SocketMessage;

public class SyncDataProcessor {
	private static Log log_debug = LogFactory.getLog(Constants.LOG_DEBUG_COMMON);
	public static SocketMessage execute(SocketMessage msg) {
		//if (Constants.GET_DATA_ACTION.equals(msg.getAction())) {
			HashMap channelMap = UserManagement.getChannelMap();
			log_debug.info("sync data with other node......");
		//} 
		return new SocketMessage(Constants.SYNC_DATA,
								Constants.GET_DATA_SUCCESS,
								channelMap);
	}
}
