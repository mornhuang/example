package com.itsz.sht.common.model.response.listorder;

import java.math.BigDecimal;
import java.sql.Timestamp;

import com.itsz.sht.common.Consts;
import com.itsz.sht.common.model.AbstractResponseModel;

/**
 * $Id: OrderInfoModel.java,v 1.1 2010/11/09 03:57:29 kyzou Exp $
 * @Project:portal.head
 * @File:OrderInfoModel.java
 * @Description:
 * @Author:Cimenon Saint
 * @Date:2007-5-26
 */
public class OrderInfoModel extends AbstractResponseModel {

    private String accountId;
    private String marketCode;
    private String instrCode;
    private String orderSide;
    private BigDecimal orderQuantity;
    private BigDecimal orderPrice;
    private String orderType;
    private String validityType;
    private Timestamp expiryDate;
    private Long mcsOrderId;
    private Long orderId;
    private String orderState;
    private String shortMtssOrderState;
    private String ccy;
    private BigDecimal initialQuantity;
    private BigDecimal outstandingQuantity;
    private BigDecimal ultimateQuantity;
    private Timestamp businessDate;
    private Timestamp dateCreated;
    private String mtssOrderState;
    private String instrName;
    private BigDecimal changedQty;
    private BigDecimal filledQty;
    private String orderRemark;
    private String allOrNothingFlag;
    private BigDecimal triggerPrice;
    private String conditionType;
    private String condition;
    private Long basketRef;
    
    /**
     * can or not be modify or cancel
     * Y:can be operated
     * N:can't be operated
     */
	public String canBeMC() {
		String result = Consts.Global.Flag.POSITIVE;
		if(!mtssOrderState.equals(Consts.Order.State.QUEUING)
			&& !mtssOrderState.equals(Consts.Order.State.RECEIVED)
			&& !mtssOrderState.equals(Consts.Order.State.PARTIALLY_FILLED)){
				result = Consts.Global.Flag.NEGATIVE;
		}
		return result;
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
	public Timestamp getBusinessDate() {
		return businessDate;
	}
	public void setBusinessDate(Timestamp businessDate) {
		this.businessDate = businessDate;
	}
	public String getCcy() {
		return ccy;
	}
	public void setCcy(String ccy) {
		this.ccy = ccy;
	}
	public BigDecimal getChangedQty() {
		return changedQty;
	}
	public void setChangedQty(BigDecimal changedQty) {
		this.changedQty = changedQty;
	}
	public String getConditionType() {
		return conditionType;
	}
	public void setConditionType(String conditionType) {
		this.conditionType = conditionType;
	}
	public Timestamp getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(Timestamp dateCreated) {
		this.dateCreated = dateCreated;
	}
	public Timestamp getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(Timestamp expiryDate) {
		this.expiryDate = expiryDate;
	}
	public BigDecimal getFilledQty() {
		return filledQty;
	}
	public void setFilledQty(BigDecimal filledQty) {
		this.filledQty = filledQty;
	}
	public BigDecimal getInitialQuantity() {
		return initialQuantity;
	}
	public void setInitialQuantity(BigDecimal initialQuantity) {
		this.initialQuantity = initialQuantity;
	}
	public String getInstrCode() {
		return instrCode;
	}
	public void setInstrCode(String instrCode) {
		this.instrCode = instrCode;
	}
	public String getInstrName() {
		return instrName;
	}
	public void setInstrName(String instrName) {
		this.instrName = instrName;
	}
	public String getMarketCode() {
		return marketCode;
	}
	public void setMarketCode(String marketCode) {
		this.marketCode = marketCode;
	}
	public Long getMcsOrderId() {
		return mcsOrderId;
	}
	public void setMcsOrderId(Long mcsOrderId) {
		this.mcsOrderId = mcsOrderId;
	}
	public String getMtssOrderState() {
		return mtssOrderState;
	}
	public void setMtssOrderState(String mtssOrderState) {
		this.mtssOrderState = mtssOrderState;
	}
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
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
	public String getOrderRemark() {
		return orderRemark;
	}
	public void setOrderRemark(String orderRemark) {
		this.orderRemark = orderRemark;
	}
	public String getOrderSide() {
		return orderSide;
	}
	public void setOrderSide(String orderSide) {
		this.orderSide = orderSide;
	}
	public String getOrderState() {
		return orderState;
	}
	public void setOrderState(String orderState) {
		this.orderState = orderState;
	}
	public String getOrderType() {
		return orderType;
	}
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	public BigDecimal getOutstandingQuantity() {
		return outstandingQuantity;
	}
	public void setOutstandingQuantity(BigDecimal outstandingQuantity) {
		this.outstandingQuantity = outstandingQuantity;
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
	public String getShortMtssOrderState() {
		return shortMtssOrderState;
	}
	public void setShortMtssOrderState(String shortMtssOrderState) {
		this.shortMtssOrderState = shortMtssOrderState;
	}
	public BigDecimal getUltimateQuantity() {
		return ultimateQuantity;
	}
	public void setUltimateQuantity(BigDecimal ultimateQuantity) {
		this.ultimateQuantity = ultimateQuantity;
	}
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
	public Long getBasketRef() {
		return basketRef;
	}
	public void setBasketRef(Long basketRef) {
		this.basketRef = basketRef;
	}
}
