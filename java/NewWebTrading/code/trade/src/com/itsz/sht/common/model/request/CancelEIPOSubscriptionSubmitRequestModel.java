package com.itsz.sht.common.model.request;

import com.itsz.sht.common.model.AbstractRequestModel;

public class CancelEIPOSubscriptionSubmitRequestModel extends AbstractRequestModel {

	private String loginId;
	private String subscriptionId;
	private String password;
	
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
