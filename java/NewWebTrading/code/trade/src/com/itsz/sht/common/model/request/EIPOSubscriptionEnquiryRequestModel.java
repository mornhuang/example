package com.itsz.sht.common.model.request;

import com.itsz.sht.common.model.AbstractRequestModel;

public class EIPOSubscriptionEnquiryRequestModel extends AbstractRequestModel {

	private String accountId;

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	
}
