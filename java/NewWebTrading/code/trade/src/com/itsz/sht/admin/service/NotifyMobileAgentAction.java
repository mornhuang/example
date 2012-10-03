package com.itsz.sht.admin.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.itsz.sht.admin.parameter.util.MobileAgentManagement;
import com.taifook.adminportal.model.CsSetParameter;
import com.taifook.common.socket.SocketMessage;

/**
 * $Id: NotifyMobileAgentAction.java,v 1.1 2010/11/09 03:57:15 kyzou Exp $
 * @Project:portal
 * @File:NotifyMobileAgentAction.java
 * @Description:
 * @Author:kyzou
 * @Date:2008-8-27
 */
public class NotifyMobileAgentAction {
	Log log = LogFactory.getLog(NotifyMobileAgentAction.class);
	
	public static SocketMessage execute(SocketMessage msg) {
		CsSetParameter parameter = (CsSetParameter) msg.getContext();
		String name = parameter.getId().getParamName();
		String value = parameter.getId().getParamValue();		
		if (msg.getAction().equals(			
				com.taifook.adminportal.common.Constants.ADD_ACTION)
				|| msg.getAction().equals(
						com.taifook.adminportal.common.Constants.UPDATE_ACTION)) {
			if (name != null && !name.equals("")) {
				MobileAgentManagement.putAgentSet(name, value);
			}
		} else {
			MobileAgentManagement.remove(name, value);
		}
		return new SocketMessage(com.taifook.adminportal.common.Constants.SOCKET_TRANSFER_SUCCESS,
								com.taifook.adminportal.common.Constants.SOCKET_TRANSFER_SUCCESS,
								com.taifook.adminportal.common.Constants.SOCKET_TRANSFER_SUCCESS);
	}
}
