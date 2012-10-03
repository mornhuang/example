package com.itsz.sht.common.model.request;

import java.math.BigDecimal;
import java.sql.Timestamp;

import com.itsz.sht.common.model.AbstractRequestModel;

/**
 * $Id: PrePlaceOrderRequestModel.java,v 1.1 2010/11/09 03:57:27 kyzou Exp $
 * @Project:portal.head
 * @File:PrePlaceOrderRequestModel.java
 * @Description:
 * @Author:Cimenon Saint
 * @Date:2007-5-26
 */
public class PrePlaceOrderRequestModel extends AbstractRequestModel {

    private String accountId;
    private String marketCode;
    private String instrCode;
    private String orderSide;
    private BigDecimal orderQuantity;
    private BigDecimal orderPrice;
    private String orderType;
    private String validityType;
    private Timestamp expiryDate;
    private String custCode;
    private String password;
    private String allOrNothingFlag;
    private BigDecimal triggerPrice;
    private String conditionType;
    
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
	public Timestamp getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(Timestamp expiryDate) {
		this.expiryDate = expiryDate;
	}
	public String getInstrCode() {
		return instrCode;
	}
	public void setInstrCode(String instrCode) {
		this.instrCode = instrCode;
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
	public String getValidityType() {
		return validityType;
	}
	public void setValidityType(String validityType) {
		this.validityType = validityType;
	}
}
