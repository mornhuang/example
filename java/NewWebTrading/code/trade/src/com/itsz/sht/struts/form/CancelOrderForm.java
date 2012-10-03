package com.itsz.sht.struts.form;


/**
 * $Id: CancelOrderForm.java,v 1.1 2010/12/01 06:33:15 kyzou Exp $
 * 
 * @Project:portal.head
 * @File:CancelOrderForm.java
 * @Description:
 * @Author:Cimenon Saint
 * @Date:2007-5-21
 */
public class CancelOrderForm extends ITSZForm {

	private static final long serialVersionUID = 1157990541755411503L;
	private String password;
	private String MCSOrderID;
	private String MTSSOrderID;
	private String accountId;
	private String token;
	private String transactionProtection;

	public String getTransactionProtection() {
		return transactionProtection;
	}
	public void setTransactionProtection(String transactionProtection) {
		this.transactionProtection = transactionProtection;
	}
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getMCSOrderID() {
		return MCSOrderID;
	}
	public void setMCSOrderID(String orderID) {
		MCSOrderID = orderID;
	}
	public String getMTSSOrderID() {
		return MTSSOrderID;
	}
	public void setMTSSOrderID(String orderID) {
		MTSSOrderID = orderID;
	}
}
