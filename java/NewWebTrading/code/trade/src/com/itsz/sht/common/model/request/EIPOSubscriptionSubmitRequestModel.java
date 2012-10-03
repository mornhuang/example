package com.itsz.sht.common.model.request;

import com.itsz.sht.common.model.AbstractRequestModel;
import com.itsz.sht.common.user.UserObject;
import com.itsz.sht.struts.eipo.dao.EIPOMasterEntry;

public class EIPOSubscriptionSubmitRequestModel extends AbstractRequestModel {

	private String loginId;
	private String subscriptionId;
	private String password;
	private boolean isEmail;
	private boolean isPhoto;
	private UserObject user;
	public UserObject getUser() {
		return user;
	}
	public void setUser(UserObject user) {
		this.user = user;
	}
	private EIPOMasterEntry ipoSubscription ;
	
	
	public EIPOMasterEntry getIpoSubscription() {
		return ipoSubscription;
	}
	public void setIpoSubscription(EIPOMasterEntry ipoSubscription) {
		this.ipoSubscription = ipoSubscription;
	}
	public boolean isEmail() {
		return isEmail;
	}
	public void setEmail(boolean isEmail) {
		this.isEmail = isEmail;
	}
	public boolean isPhoto() {
		return isPhoto;
	}
	public void setPhoto(boolean isPhoto) {
		this.isPhoto = isPhoto;
	}
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getSubscriptionId() {
		return subscriptionId;
	}
	public void setSubscriptionId(String subscriptionId) {
		this.subscriptionId = subscriptionId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
