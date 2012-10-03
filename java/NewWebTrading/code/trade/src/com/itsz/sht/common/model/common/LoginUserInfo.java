package com.itsz.sht.common.model.common;

import java.io.Serializable;

public class LoginUserInfo implements Serializable{
	private String custName;
	private String transactionProtection;
	private String afxNewsUrl;
	private String singleNewsUrl;
	private String jsonAcEnquiry;
	private String firstLogin; //base channelgroup
	private String firstLogin_channel; //base channel Y:first login N:not first login
	private String loginStatus; 
	private String termsAndConditions;
	public String getTermsAndConditions() {
		return termsAndConditions;
	}
	public void setTermsAndConditions(String termsAndConditions) {
		this.termsAndConditions = termsAndConditions;
	}
	public String getAfxNewsUrl() {
		return afxNewsUrl;
	}
	public void setAfxNewsUrl(String afxNewsUrl) {
		this.afxNewsUrl = afxNewsUrl;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getSingleNewsUrl() {
		return singleNewsUrl;
	}
	public void setSingleNewsUrl(String singleNewsUrl) {
		this.singleNewsUrl = singleNewsUrl;
	}
	public String getTransactionProtection() {
		return transactionProtection;
	}
	public void setTransactionProtection(String transactionProtection) {
		this.transactionProtection = transactionProtection;
	}
	public String getJsonAcEnquiry() {
		return jsonAcEnquiry;
	}
	public void setJsonAcEnquiry(String jsonAcEnquiry) {
		this.jsonAcEnquiry = jsonAcEnquiry;
	}
	public String getFirstLogin() {
		return firstLogin;
	}
	public void setFirstLogin(String firstLogin) {
		this.firstLogin = firstLogin;
	}
	public String getLoginStatus() {
		return loginStatus;
	}
	public void setLoginStatus(String loginStatus) {
		this.loginStatus = loginStatus;
	}
	public String getFirstLogin_channel() {
		return firstLogin_channel;
	}
	public void setFirstLogin_channel(String firstLogin_channel) {
		this.firstLogin_channel = firstLogin_channel;
	}
}
