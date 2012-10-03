package com.itsz.sht.common.model.common.response;

import com.itsz.sht.common.model.common.AbstractResponseModel;

public class UserNotificationEmailResponseModel extends AbstractResponseModel{
	
	private String clientId;
	private String userNotificationEmail;
	private boolean notiFlag;
	
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getUserNotificationEmail() {
		return userNotificationEmail;
	}
	public void setUserNotificationEmail(String userNotificationEmail) {
		this.userNotificationEmail = userNotificationEmail;
	}
	public boolean isNotiFlag() {
		return notiFlag;
	}
	public void setNotiFlag(boolean notiFlag) {
		this.notiFlag = notiFlag;
	}
	
}
