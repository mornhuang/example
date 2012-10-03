package com.itsz.sht.common.model.common;

import java.math.BigDecimal;

import com.itsz.sht.common.model.AbstractRequestModel;

/**
 * $Id: TradeHistoryModel.java,v 1.1 2010/11/09 03:57:26 kyzou Exp $
 * @Project:portal.head
 * @File:TradeHistoryModel.java
 * @Description:
 * @Author:Cimenon Saint
 * @Date:2007-5-21
 */
public class TradeHistoryModel extends AbstractRequestModel{
	
	public BigDecimal tradePrice;
	public Integer tradeQuantity;
	public String brokerId;
	
	public String getBrokerId() {
		return brokerId;
	}
	public void setBrokerId(String brokerId) {
		this.brokerId = brokerId;
	}
	public BigDecimal getTradePrice() {
		return tradePrice;
	}
	public void setTradePrice(BigDecimal tradePrice) {
		this.tradePrice = tradePrice;
	}
	public Integer getTradeQuantity() {
		return tradeQuantity;
	}
	public void setTradeQuantity(Integer tradeQuantity) {
		this.tradeQuantity = tradeQuantity;
	}
}
