package com.itsz.sht.struts.eipo.form;

import com.itsz.sht.struts.form.ITSZForm;

public class CancelEIPOSubscriptionForm extends ITSZForm {

	private static final long serialVersionUID = 1L;
	
	private String subscriptionId;
	private String ipoMasterId;
	
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
}
