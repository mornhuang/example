package com.itsz.sht.common.model.response;

import java.math.BigDecimal;

import com.itsz.sht.common.Consts;
import com.itsz.sht.common.model.AbstractResponseModel;
import com.taifook.mcs.core.beans.msg.CalOrderFeeResponse;

/**
 * $Id: OrderFeeResponseModel.java,v 1.4 2010/12/16 10:14:50 kyzou Exp $
 * @Project:portal.head
 * @File:OrderFeeResponseModel.java
 * @Description:
 * @Author:Cimenon Saint
 * @Date:2007-5-21
 */
public class OrderFeeResponseModel extends AbstractResponseModel {
	
	private CalOrderFeeResponse calOrderFeeResponse = new CalOrderFeeResponse();
    private BigDecimal insufficientAmount;//买单帐户缺少金额
	private String splitOrderFlag = Consts.Global.Flag.NEGATIVE;//whether need split the big order-Y/N
	private String orderSide;
	private String isConditionType;
	private String conditionType;
	private String orderType;
	private String allOrNothing;
	private BigDecimal orderPrice;
	private BigDecimal orderQuantity;
	private BigDecimal triggerPrice;
	private String isExceedMaxLot = Consts.Global.Flag.NEGATIVE;//whether over max lot size or not
	private String instrCode;
	private String disassemblyFlag;//提示拆单
	
	public BigDecimal getOrderQuantity() {
		return orderQuantity;
	}
	public void setOrderQuantity(BigDecimal orderQuantity) {
		this.orderQuantity = orderQuantity;
	}
	public BigDecimal getInsufficientAmount() {
		return insufficientAmount;
	}
	public void setInsufficientAmount(BigDecimal insufficientAmount) {
		this.insufficientAmount = insufficientAmount;
	}
	public String getIsExceedMaxLot() {
		return isExceedMaxLot;
	}
	public void setIsExceedMaxLot(String isExceedMaxLot) {
		this.isExceedMaxLot = isExceedMaxLot;
	}
	public String getAllOrNothing() {
		return allOrNothing;
	}
	public void setAllOrNothing(String allOrNothing) {
		this.allOrNothing = allOrNothing;
	}
    public CalOrderFeeResponse getCalOrderFeeResponse() {
		return calOrderFeeResponse;
	}
	public void setCalOrderFeeResponse(CalOrderFeeResponse calOrderFeeResponse) {
		this.calOrderFeeResponse = calOrderFeeResponse;
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
	public BigDecimal getOrderPrice() {
		return orderPrice;
	}
	public void setOrderPrice(BigDecimal orderPrice) {
		this.orderPrice = orderPrice;
	}
	public String getSplitOrderFlag() {
		return splitOrderFlag;
	}
	public void setSplitOrderFlag(String splitOrderFlag) {
		this.splitOrderFlag = splitOrderFlag;
	}
	public BigDecimal getTriggerPrice() {
		return triggerPrice;
	}
	public void setTriggerPrice(BigDecimal triggerPrice) {
		this.triggerPrice = triggerPrice;
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
	public String getInstrCode() {
		return instrCode;
	}
	public void setInstrCode(String instrCode) {
		this.instrCode = instrCode;
	}
	public String getDisassemblyFlag() {
		return disassemblyFlag;
	}
	public void setDisassemblyFlag(String disassemblyFlag) {
		this.disassemblyFlag = disassemblyFlag;
	}
}
