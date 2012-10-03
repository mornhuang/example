package com.itsz.sht.common.model.request;

import com.itsz.sht.common.model.AbstractRequestModel;

public class EIPOSubscriptionDetailRequestModel extends AbstractRequestModel {

	private String subscriptionId;

	public String getSubscriptionId() {
		return subscriptionId;
	}

	public void setSubscriptionId(String subscriptionId) {
		this.subscriptionId = subscriptionId;
	}
}
