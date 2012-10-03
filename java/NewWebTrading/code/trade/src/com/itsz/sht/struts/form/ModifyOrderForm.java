package com.itsz.sht.struts.form;

/**
 * $Id: ModifyOrderForm.java,v 1.2 2010/12/24 10:21:39 zxfan Exp $
 * @Project:portal.head
 * @File:ModifyOrderForm.java
 * @Description:
 * @Author:Cimenon Saint
 * @Date:2007-5-21
 */
public class ModifyOrderForm extends ITSZForm {

	private static final long serialVersionUID = 5791546366885637006L;
	private String accountId;
	private String custCode;
	private String mcsOrderId;
	private String mtssOrderId;
	private String newOrderPrice;
	private String newOrderQty;
	private String password;
	private String triggerPrice;
	private String token;

    public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public String getCustCode() {
		return custCode;
	}
	public void setCustCode(String custCode) {
		this.custCode = custCode;
	}
	public String getMcsOrderId() {
		return mcsOrderId;
	}
	public void setMcsOrderId(String mcsOrderId) {
		this.mcsOrderId = mcsOrderId;
	}
	public String getMtssOrderId() {
		return mtssOrderId;
	}
	public void setMtssOrderId(String mtssOrderId) {
		this.mtssOrderId = mtssOrderId;
	}
	public String getNewOrderPrice() {
		return newOrderPrice;
	}
	public void setNewOrderPrice(String newOrderPrice) {
		this.newOrderPrice = newOrderPrice;
	}
	public String getNewOrderQty() {
		return newOrderQty;
	}
	public void setNewOrderQty(String newOrderQty) {
		this.newOrderQty = newOrderQty;
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
	public String getTriggerPrice() {
		return triggerPrice;
	}
	public void setTriggerPrice(String triggerPrice) {
		this.triggerPrice = triggerPrice;
	}
}
