package com.itsz.sht.admin.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.itsz.sht.admin.parameter.util.ParaManagement;
import com.itsz.sht.common.Constants;
import com.itsz.sht.common.user.UserManagement;
import com.taifook.adminportal.model.CsParameter;
import com.taifook.common.socket.SocketMessage;

//��adminportal��������ģ���key��Ϊ����֪ͨchannelserver������Ӧ�ĸ��
public class NotifyParameterAction {	
	
	Log log = LogFactory.getLog(NotifyParameterAction.class);
	
	public static SocketMessage execute(SocketMessage msg) {
				
		if (msg.getAction().equals(			
				com.taifook.adminportal.common.Constants.ADD_ACTION)
				|| msg.getAction().equals(
						com.taifook.adminportal.common.Constants.UPDATE_ACTION)) {
			CsParameter parameter = (CsParameter) msg.getContext();
			String key = parameter.getKey();
			String value = parameter.getValue();
			if (key != null && !key.equals("")) {
				ParaManagement.putParaTable(key, value);
				int k = key.indexOf(Constants.VIEW_PROVIDER);
				if (k > 0) {
					UserManagement.setChannelMap(key.substring(0, k));
				}
			}
		} else {
			String[] delKeys=(String[])msg.getContext();
			for(int index=0; index<delKeys.length;index++){
				String key=delKeys[index];
				ParaManagement.remove(key);
				int k = key.indexOf(Constants.VIEW_PROVIDER);
				if (k > 0) {
				UserManagement.removeChannelMap(key.substring(0, k));
			}
			}
		}
		return new SocketMessage(com.taifook.adminportal.common.Constants.SOCKET_TRANSFER_SUCCESS,
								com.taifook.adminportal.common.Constants.SOCKET_TRANSFER_SUCCESS,
								com.taifook.adminportal.common.Constants.SOCKET_TRANSFER_SUCCESS);
	}

}
