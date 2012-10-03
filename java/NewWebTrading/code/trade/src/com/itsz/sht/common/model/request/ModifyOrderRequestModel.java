package com.itsz.sht.common.model.request;

import java.math.BigDecimal;

import com.itsz.sht.common.model.AbstractRequestModel;

/**
 * $Id: ModifyOrderRequestModel.java,v 1.1 2010/11/09 03:57:27 kyzou Exp $
 * @Project:portal.head
 * @File:ModifyOrderRequestModel.java
 * @Description:
 * @Author:Cimenon Saint
 * @Date:2007-5-21
 */
public class ModifyOrderRequestModel extends AbstractRequestModel {

	private String accountId;
	private String custCode;
	private Long mcsOrderId;
	private Long orderId;
	private BigDecimal newOrderPrice;
	private BigDecimal newOrderQty;
	private String loginId;
	private String password;
	private BigDecimal triggerPrice;
    private String lotSize;
    private String instrCode;
    private String transactionProtection;
    private String orderType;
	
	public String getInstrCode() {
        return instrCode;
    }
    public void setInstrCode(String instrCode) {
        this.instrCode = instrCode;
    }
    public String getLotSize() {
        return lotSize;
    }
    public void setLotSize(String lotSize) {
        this.lotSize = lotSize;
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
	public Long getMcsOrderId() {
		return mcsOrderId;
	}
	public void setMcsOrderId(Long mcsOrderId) {
		this.mcsOrderId = mcsOrderId;
	}
	public BigDecimal getNewOrderPrice() {
		return newOrderPrice;
	}
	public void setNewOrderPrice(BigDecimal newOrderPrice) {
		this.newOrderPrice = newOrderPrice;
	}
	public BigDecimal getNewOrderQty() {
		return newOrderQty;
	}
	public void setNewOrderQty(BigDecimal newOrderQty) {
		this.newOrderQty = newOrderQty;
	}
	public BigDecimal getTriggerPrice() {
		return triggerPrice;
	}
	public void setTriggerPrice(BigDecimal triggerPrice) {
		this.triggerPrice = triggerPrice;
	}
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getTransactionProtection() {
		return transactionProtection;
	}
	public void setTransactionProtection(String transactionProtection) {
		this.transactionProtection = transactionProtection;
	}
	public String getOrderType() {
		return orderType;
	}
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
}
