package com.itsz.sht.admin.service;

import com.taifook.adminportal.common.Constants;
import com.taifook.common.socket.SocketMessage;

public class ProcessSynDataDelegate {
	 private static ProcessSynDataDelegate service=new ProcessSynDataDelegate();	 
	 
	 private ProcessSynDataDelegate(){
		 
	 }
	 
	 public SocketMessage processRequest(SocketMessage msg) {
		if (msg.getNotifyObject().equals(Constants.NOTIFY_PARAMETER)) {
			return NotifyParameterAction.execute(msg);
		} else if (msg.getNotifyObject().equals(Constants.NOTIFY_BROADCAST)) {
			return NotifyBroadcastAction.execute(msg);			
		} else if (msg.getNotifyObject().equals(Constants.NOTIFY_CHANNELSTATUS)) {
			return NotifyChannelAction.execute(msg);
		}
		return null;
	}
	 
	 public static ProcessSynDataDelegate getInstance(){
		 if(service==null){
			 service=new ProcessSynDataDelegate();
		 }
		 return service;
	 }
}
