package com.itsz.sht.common.model.common.request;

import com.taifook.framework.application.dto.DataTransferObject;

public class EstatementOptionInfoModel implements DataTransferObject {

    /**
	 * 
	 */
	private static final long serialVersionUID = 5416060397411224054L;
	
	private String custCode = null;
    private String email = null;
    private String address1 = null;
    private String address2 = null;
    private String address3 = null;
    private String subscriptionStat = null;
    
    private String dailyMail = null;
    private String monthlyMail = null;
    private String dailyEmail = null;
    private String monthlyEmail = null;
    
    private boolean withHoldFlag;
    

	public EstatementOptionInfoModel(String custCode, String email,
			String address1, String address2, String address3,
			String subscriptionStat, String dailyMail, String monthlyMail,
			String dailyEmail, String monthlyEmail, boolean withHoldFlag) {
		super();
		this.custCode = custCode;
		this.email = email;
		this.address1 = address1;
		this.address2 = address2;
		this.address3 = address3;
		this.subscriptionStat = subscriptionStat;
		this.dailyMail = dailyMail;
		this.monthlyMail = monthlyMail;
		this.dailyEmail = dailyEmail;
		this.monthlyEmail = monthlyEmail;
		this.withHoldFlag = withHoldFlag;
	}

	public EstatementOptionInfoModel() {

	}
	
	public String getCustCode() {
		return custCode;
	}
	public void setCustCode(String custCode) {
		this.custCode = custCode;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getAddress3() {
		return address3;
	}
	public void setAddress3(String address3) {
		this.address3 = address3;
	}
	public String getSubscriptionStat() {
		return subscriptionStat;
	}
	public void setSubscriptionStat(String subscriptionStat) {
		this.subscriptionStat = subscriptionStat;
	}

	public String getDailyMail() {
		return dailyMail;
	}

	public void setDailyMail(String dailyMail) {
		this.dailyMail = dailyMail;
	}

	public String getMonthlyMail() {
		return monthlyMail;
	}

	public void setMonthlyMail(String monthlyMail) {
		this.monthlyMail = monthlyMail;
	}

	public String getDailyEmail() {
		return dailyEmail;
	}

	public void setDailyEmail(String dailyEmail) {
		this.dailyEmail = dailyEmail;
	}

	public String getMonthlyEmail() {
		return monthlyEmail;
	}

	public void setMonthlyEmail(String monthlyEmail) {
		this.monthlyEmail = monthlyEmail;
	}

	public boolean isWithHoldFlag() {
		return withHoldFlag;
	}

	public void setWithHoldFlag(boolean withHoldFlag) {
		this.withHoldFlag = withHoldFlag;
	}

}

