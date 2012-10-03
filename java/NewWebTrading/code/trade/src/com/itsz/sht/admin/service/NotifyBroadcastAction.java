package com.itsz.sht.admin.service;

import java.text.SimpleDateFormat;

import com.itsz.sht.admin.broadcast.util.BroadcastInfo;
import com.itsz.sht.admin.broadcast.util.BroadcastManagement;
import com.taifook.adminportal.common.Constants;
import com.taifook.adminportal.model.CsBroadcast;
import com.taifook.common.socket.SocketMessage;

public class NotifyBroadcastAction{

	public static SocketMessage execute(SocketMessage msg) {
		SimpleDateFormat format = new SimpleDateFormat(com.itsz.sht.common.Constants.BroadTimeFormat);
		if (msg.getAction().endsWith(Constants.ADD_ACTION)
				|| msg.getAction().endsWith(Constants.UPDATE_ACTION)) {
			CsBroadcast broad = (CsBroadcast) msg.getContext();
			if (broad != null) {
				BroadcastManagement.removeBroadcast(String.valueOf(broad.getSeqno()));
				BroadcastInfo broadInfo = new BroadcastInfo();
				broadInfo.setId(broad.getSeqno().toString());
				broadInfo.setChannels(broad.getChannels());
				broadInfo.setContent_en_US(broad.getContentEnUs());
				broadInfo.setContent_zh_CN(broad.getContentZhCn());
				broadInfo.setContent_zh_TW(broad.getContentZhTw());
				broadInfo.setContenttype(broad.getContentType());
				broadInfo.setStime(format.format(broad.getStarttime()));
				broadInfo.setEtime(format.format(broad.getEndtime()));
				broadInfo.setLevel(broad.getBroadcastLevel());
				broadInfo.setLastmodifytime(format.format(broad.getLastupdatetime()));
				BroadcastManagement.addBroadcast(broadInfo);
			}
		} else {
			String[] delKeys = (String[]) msg.getContext();
			for (int index = 0; index < delKeys.length; index++) {
				String key = delKeys[index];
				BroadcastManagement.removeBroadcast(key);
			}
		}
		
		return new SocketMessage(com.taifook.adminportal.common.Constants.SOCKET_TRANSFER_SUCCESS,
				com.taifook.adminportal.common.Constants.SOCKET_TRANSFER_SUCCESS,
				com.taifook.adminportal.common.Constants.SOCKET_TRANSFER_SUCCESS);
	}

}
