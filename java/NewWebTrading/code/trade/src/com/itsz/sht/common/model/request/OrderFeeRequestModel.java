package com.itsz.sht.common.model.request;

import java.math.BigDecimal;

import com.itsz.sht.common.model.AbstractRequestModel;

/**
 * $Id: OrderFeeRequestModel.java,v 1.1 2010/11/09 03:57:27 kyzou Exp $
 * @Project:portal.head
 * @File:OrderFeeRequestModel.java
 * @Description:
 * @Author:Cimenon Saint
 * @Date:2007-5-21
 */
public class OrderFeeRequestModel extends AbstractRequestModel {

	private String accountId;
	private String marketCode;
	private String instrCode;
	private BigDecimal orderQuantity;
	private BigDecimal orderPrice;
	private String orderSide;
	//----------
	private Integer lotsize;
	private String orderType;
	private String allOrNothing;
	
	
	private String isConditionType;
	private String conditionType;
	private BigDecimal triggerPrice;
	private String customerId;
	
	private String isNeedCallMos;
	
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public String getAllOrNothing() {
		return allOrNothing;
	}
	public void setAllOrNothing(String allOrNothing) {
		this.allOrNothing = allOrNothing;
	}
	public String getInstrCode() {
		return instrCode;
	}
	public void setInstrCode(String instrCode) {
		this.instrCode = instrCode;
	}
	public Integer getLotsize() {
		return lotsize;
	}
	public void setLotsize(Integer lotsize) {
		this.lotsize = lotsize;
	}
	public String getMarketCode() {
		return marketCode;
	}
	public void setMarketCode(String marketCode) {
		this.marketCode = marketCode;
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
	public BigDecimal getOrderQuantity() {
		return orderQuantity;
	}
	public void setOrderQuantity(BigDecimal orderQuantity) {
		this.orderQuantity = orderQuantity;
	}
	public BigDecimal getOrderPrice() {
		return orderPrice;
	}
	public void setOrderPrice(BigDecimal orderPrice) {
		this.orderPrice = orderPrice;
	}
	public String getConditionType() {
		return conditionType;
	}
	public void setConditionType(String conditionType) {
		this.conditionType = conditionType;
	}
	public String getIsConditionType() {
		return isConditionType;
	}
	public void setIsConditionType(String isConditionType) {
		this.isConditionType = isConditionType;
	}
	public BigDecimal getTriggerPrice() {
		return triggerPrice;
	}
	public void setTriggerPrice(BigDecimal triggerPrice) {
		this.triggerPrice = triggerPrice;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getIsNeedCallMos() {
		return isNeedCallMos;
	}
	public void setIsNeedCallMos(String isNeedCallMos) {
		this.isNeedCallMos = isNeedCallMos;
	}
}
