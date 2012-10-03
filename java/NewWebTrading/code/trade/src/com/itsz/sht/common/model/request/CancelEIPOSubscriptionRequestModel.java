package com.itsz.sht.common.model.request;

import java.util.Locale;

import org.apache.struts.util.MessageResources;

import com.itsz.sht.common.model.AbstractRequestModel;

public class CancelEIPOSubscriptionRequestModel extends AbstractRequestModel {

	private String accountId;
	private String subscriptionId;
	private String ipoMasterId;
	private String clientClassCode;
	private Locale locale;
	private MessageResources mr;

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getSubscriptionId() {
		return subscriptionId;
	}

	public void setSubscriptionId(String subscriptionId) {
		this.subscriptionId = subscriptionId;
	}

	public String getIpoMasterId() {
		return ipoMasterId;
	}

	public void setIpoMasterId(String ipoMasterId) {
		this.ipoMasterId = ipoMasterId;
	}

	public String getClientClassCode() {
		return clientClassCode;
	}

	public void setClientClassCode(String clientClassCode) {
		this.clientClassCode = clientClassCode;
	}

	public Locale getLocale() {
		return locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	public MessageResources getMr() {
		return mr;
	}

	public void setMr(MessageResources mr) {
		this.mr = mr;
	}
	
}
