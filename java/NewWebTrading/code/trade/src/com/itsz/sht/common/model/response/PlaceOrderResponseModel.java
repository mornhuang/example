package com.itsz.sht.common.model.response;

import java.math.BigDecimal;

import com.itsz.sht.common.Consts;
import com.itsz.sht.common.model.AbstractResponseModel;
import com.taifook.mcs.core.beans.msg.InputOrderResponse;

/**
 * $Id: PlaceOrderResponseModel.java,v 1.3 2010/12/08 10:33:12 zxfan Exp $
 * @Project:portal.head
 * @File:PlaceOrderResponseModel.java
 * @Description:
 * @Author:Cimenon Saint
 * @Date:2007-5-21
 */
public class PlaceOrderResponseModel extends AbstractResponseModel {

	private InputOrderResponse inputOrderResponse;

	private String instrCode;
	private BigDecimal orderQuantity;
	private String orderSide;
	private String orderType;
	private String allOrNothing=Consts.Global.Flag.NEGATIVE;
	private BigDecimal orderPrice;
	private BigDecimal triggerPrice;
	private String conditionType;
	
	private String accountId;
	private String custCode;
	private String marketCode;
	private String loginId;
	private String password;
	
	private OrderFeeResponseModel orderFeeResp;
	
	public InputOrderResponse getInputOrderResponse() {
		return inputOrderResponse;
	}
	public void setInputOrderResponse(InputOrderResponse inputOrderResponse) {
		this.inputOrderResponse = inputOrderResponse;
	}
	public String getAllOrNothing() {
		return allOrNothing;
	}
	public void setAllOrNothing(String allOrNothing) {
		this.allOrNothing = allOrNothing;
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
	public BigDecimal getTriggerPrice() {
		return triggerPrice;
	}
	public void setTriggerPrice(BigDecimal triggerPrice) {
		this.triggerPrice = triggerPrice;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public OrderFeeResponseModel getOrderFeeResp() {
		return orderFeeResp;
	}
	public void setOrderFeeResp(OrderFeeResponseModel orderFeeResp) {
		this.orderFeeResp = orderFeeResp;
	}
}