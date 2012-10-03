package com.itsz.sht.struts.form;

import java.util.Date;

import org.apache.struts.validator.ValidatorForm;

public class UserProductPaymentForm extends ValidatorForm{

	// Fields
	private static final long serialVersionUID = 1L;
	private String usrProdPayId;
	private String startDate;
	private String endDate;
	
	public String getUsrProdPayId() {
		return usrProdPayId;
	}
	public void setUsrProdPayId(String usrProdPayId) {
		this.usrProdPayId = usrProdPayId;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
}
