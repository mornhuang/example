package com.itsz.sht.struts.eipo.form;

import com.itsz.sht.struts.form.ITSZForm;

/**
 * @struts.form name = "addIpoSubscriptionForm"
 * 
 * 
 */


public class AddIPOSubscriptionForm extends ITSZForm {

	private static final long serialVersionUID = 1L;
	private String appliedShare;   //申购股数
	/**
	 * @return the appliedShare
	 */
	public String getAppliedShare() {
		return appliedShare;
	}
	/**
	 * @param appliedShare the appliedShare to set
	 */
	public void setAppliedShare(String appliedShare) {
		this.appliedShare = appliedShare;
	}
	

}
