package cn.itsz.newsim.view.form;

import cn.itsz.newsim.common.Constants;


/**
 * $Id: PlaceOrderForm.java,v 1.1 2011/03/04 09:47:34 zxfan Exp $
 * @Project:portal.head
 * @File:PlaceOrderForm.java
 * @Description:
 * @Author:Cimenon Saint
 * @Date:2007-5-21
 */
public class PlaceOrderForm extends BaseForm {
	
	private static final long serialVersionUID = -4120475396767852336L;
	private String taskNo;
	private String loginId;
	private String accountId;
	private String custCode;
	private String instrCode;
	private String orderSide;
	private String orderQuantity;
	private String orderPrice;
	private String orderType;
	private String password;
	private String allOrNothingFlag = Constants.Flag.NEGATIVE;
	private String triggerPrice;
	private String conditionType;
	private String token;
	private String transactionAmount;
	private String transactionProtection;
	
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
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
	public String getAllOrNothingFlag() {
		return allOrNothingFlag;
	}
	public void setAllOrNothingFlag(String allOrNothingFlag) {
		this.allOrNothingFlag = allOrNothingFlag;
	}
	public String getConditionType() {
		return conditionType;
	}
	public void setConditionType(String conditionType) {
		this.conditionType = conditionType;
	}
	public String getInstrCode() {
		return instrCode;
	}
	public void setInstrCode(String instrCode) {
		this.instrCode = instrCode;
	}
	public String getOrderPrice() {
		return orderPrice;
	}
	public void setOrderPrice(String orderPrice) {
		this.orderPrice = orderPrice;
	}
	public String getOrderQuantity() {
		return orderQuantity;
	}
	public void setOrderQuantity(String orderQuantity) {
		this.orderQuantity = orderQuantity;
	}
	public String getOrderSide() {
		return orderSide;
	}
	public void setOrderSide(String orderSide) {
		this.orderSide = orderSide;
	}
	public String getOrderType() {
		return orderType;
	}
	public void setOrderType(String orderType) {
		this.orderType = orderType;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getTransactionAmount() {
		return transactionAmount;
	}
	public void setTransactionAmount(String transactionAmount) {
		this.transactionAmount = transactionAmount;
	}
	public String getTransactionProtection() {
		return transactionProtection;
	}
	public void setTransactionProtection(String transactionProtection) {
		this.transactionProtection = transactionProtection;
	}
	public String getTaskNo() {
		return taskNo;
	}
	public void setTaskNo(String taskNo) {
		this.taskNo = taskNo;
	}
	
}
