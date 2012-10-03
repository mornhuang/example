package com.itsz.sht.struts.form;

import java.util.List;

/**
 * $Id: PPSTransferForm.java,v 1.3 2011/03/08 01:46:40 xli Exp $
 * @Project:NewWebTrading
 * @File:PPSTransferForm.java
 * @Description:
 * @Author:kyzou
 * @Date:2010-12-7
 */
public class PPSTransferForm extends ITSZForm {

	private String accountId;
	private String tamount;
	private String lang;
	private String user_id;
	private String token;
	private String ipgClientURL;
	private String remarks;
	private List fromAccounts;
    private boolean disableFromAccount;
	
	
	public List getFromAccounts() {
		return fromAccounts;
	}
	public void setFromAccounts(List fromAccounts) {
		this.fromAccounts = fromAccounts;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public String getIpgClientURL() {
		return ipgClientURL;
	}
	public void setIpgClientURL(String ipgClientURL) {
		this.ipgClientURL = ipgClientURL;
	}
	public String getTamount() {
		return tamount;
	}
	public void setTamount(String tamount) {
		this.tamount = tamount;
	}
	public String getLang() {
		return lang;
	}
	public void setLang(String lang) {
		this.lang = lang;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String userId) {
		user_id = userId;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public boolean isDisableFromAccount() {
		return disableFromAccount;
	}
	public void setDisableFromAccount(boolean disableFromAccount) {
		this.disableFromAccount = disableFromAccount;
	}
}
