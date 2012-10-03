package com.itsz.sht.struts.form;

/**
 * $Id: UserNotificationEmailForm.java,v 1.2 2011/01/13 09:18:58 xlliu Exp $
 * @Project:portal
 * @File:TradeListForm.java
 * @Description:
 * @Author:kyzou
 * @Date:2009-6-26
 */
public class UserNotificationEmailForm extends ITSZForm {
	private static final long serialVersionUID = 1L;

	private String clientId;
	private String userNotificationEmail;
	private boolean notiFlag;
	public boolean isNotiFlag() {
		return notiFlag;
	}
	public void setNotiFlag(boolean notiFlag) {
		this.notiFlag = notiFlag;
	}
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
}
