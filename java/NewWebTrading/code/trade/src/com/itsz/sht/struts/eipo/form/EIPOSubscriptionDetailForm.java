package com.itsz.sht.struts.eipo.form;

import com.itsz.sht.struts.form.ITSZForm;

public class EIPOSubscriptionDetailForm extends ITSZForm {

	private static final long serialVersionUID = 1L;
	
	private String subscriptionId;

	public String getSubscriptionId() {
		return subscriptionId;
	}

	public void setSubscriptionId(String subscriptionId) {
		this.subscriptionId = subscriptionId;
	}
}
