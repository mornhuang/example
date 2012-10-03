package com.itsz.sht.struts.eipo.form;

import com.itsz.sht.struts.form.ITSZForm;

public class CancelEIPOSubscriptionSubmitForm extends ITSZForm {

	private static final long serialVersionUID = 1L;
	private String subscriptionId;
	private String password;
	
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
