package com.itsz.sht.common.model.request;

import java.math.BigDecimal;

import com.itsz.sht.common.model.AbstractRequestModel;

/**
 * $Id: PlaceOrderRequestModel.java,v 1.2 2011/03/23 05:47:09 pbxie Exp $
 * @Project:portal.head
 * @File:PlaceOrderRequestModel.java
 * @Description:
 * @Author:Cimenon Saint
 * @Date:2007-5-21
 */
public class PlaceOrderRequestModel extends AbstractRequestModel{
	
	private String accountId;
	private String custCode;
	private String marketCode;
	private String instrCode;
	private String orderSide;
	private BigDecimal orderQuantity;
	private BigDecimal orderPrice;
	private String orderType;
	private String validityType;
//	private Timestamp expiryDate;
	private String loginId;
	private String password;
	private String allOrNothingFlag;
	private BigDecimal triggerPrice;
	private String conditionType;
	private String transactionProtection;
	private String transactionAmount;
	private String taskNo;
    private String supportOverNightFlag;
	
	
	/**
	 * @return the supportOverNightFlag
	 */
	public String getSupportOverNightFlag() {
		return supportOverNightFlag;
	}
	/**
	 * @param supportOverNightFlag the supportOverNightFlag to set
	 */
	public void setSupportOverNightFlag(String supportOverNightFlag) {
		this.supportOverNightFlag = supportOverNightFlag;
	}
	/**
	 * @return the validityType
	 */
	public String getValidityType() {
		return validityType;
	}
	/**
	 * @param validityType the validityType to set
	 */
	public void setValidityType(String validityType) {
		this.validityType = validityType;
	}
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
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
	public String getCustCode() {
		return custCode;
	}
	public void setCustCode(String custCode) {
		this.custCode = custCode;
	}
	public String getInstrCode() {
		return instrCode;
	}
	public void setInstrCode(String instrCode) {
		this.instrCode = instrCode;
	}
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getMarketCode() {
		return marketCode;
	}
	public void setMarketCode(String marketCode) {
		this.marketCode = marketCode;
	}
	public BigDecimal getOrderPrice() {
		return orderPrice;
	}
	public void setOrderPrice(BigDecimal orderPrice) {
		this.orderPrice = orderPrice;
	}
	public BigDecimal getOrderQuantity() {
		return orderQuantity;
	}
	public void setOrderQuantity(BigDecimal orderQuantity) {
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public BigDecimal getTriggerPrice() {
		return triggerPrice;
	}
	public void setTriggerPrice(BigDecimal triggerPrice) {
		this.triggerPrice = triggerPrice;
	}
	public String getTransactionProtection() {
		return transactionProtection;
	}
	public void setTransactionProtection(String transactionProtection) {
		this.transactionProtection = transactionProtection;
	}
	public String getTransactionAmount() {
		return transactionAmount;
	}
	public void setTransactionAmount(String transactionAmount) {
		this.transactionAmount = transactionAmount;
	}
	public String getTaskNo() {
		return taskNo;
	}
	public void setTaskNo(String taskNo) {
		this.taskNo = taskNo;
	}
}
