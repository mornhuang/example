package com.itsz.sht.common.model.response.listorder;

import java.math.BigDecimal;
import java.util.Collection;

import com.itsz.sht.common.model.AbstractResponseModel;
import com.taifook.mcs.core.beans.msg.OrderListResponse;
import com.taifook.mcs.core.beans.msg.TradeHistoryResponse;

/**
 * $Id: OrderDetailTradeResponseModel.java,v 1.4 2011/03/17 03:43:49 pbxie Exp $
 * @Project:portal.head
 * @File:OderDetailWithTradeHisModel.java
 * @Description:
 * @Author:Cimenon Saint
 * @Date:2007-5-26
 */
public class OrderDetailTradeResponseModel  extends AbstractResponseModel {
	private OrderListResponse orderListResponse;
	private TradeHistoryResponse tradeHistoryResponse;
	private String rejectReason;
	private String rejectMessage;
	private BigDecimal rejectedQty;
	private Collection traderHistories;
	private String hasHisories;//是否需要交易记录
	private String allowModify;
	private String transactionProtection;
	
	
	/**
	 * @return the tradeHistoryResponse
	 */
	public TradeHistoryResponse getTradeHistoryResponse() {
		return tradeHistoryResponse;
	}
	/**
	 * @param tradeHistoryResponse the tradeHistoryResponse to set
	 */
	public void setTradeHistoryResponse(TradeHistoryResponse tradeHistoryResponse) {
		this.tradeHistoryResponse = tradeHistoryResponse;
	}
	public String getRejectMessage() {
		return rejectMessage;
	}
	public void setRejectMessage(String rejectMessage) {
		this.rejectMessage = rejectMessage;
	}
	public OrderListResponse getOrderListResponse() {
		return orderListResponse;
	}
	public void setOrderListResponse(OrderListResponse orderListResponse) {
		this.orderListResponse = orderListResponse;
	}	 
	public String getAllowModify() {
		return allowModify;
	}
	public void setAllowModify(String allowModify) {
		this.allowModify = allowModify;
	}
	public String getHasHisories() {
		return hasHisories;
	}
	public void setHasHisories(String hasHisories) {
		this.hasHisories = hasHisories;
	}
	public BigDecimal getRejectedQty() {
		return rejectedQty;
	}
	public void setRejectedQty(BigDecimal rejectedQty) {
		this.rejectedQty = rejectedQty;
	}
	public String getRejectReason() {
		return rejectReason;
	}
	public void setRejectReason(String rejectReason) {
		this.rejectReason = rejectReason;
	}
	public Collection getTraderHistories() {
		return traderHistories;
	}
	public void setTraderHistories(Collection traderHistories) {
		this.traderHistories = traderHistories;
	}
	public String getTransactionProtection() {
		return transactionProtection;
	}
	public void setTransactionProtection(String transactionProtection) {
		this.transactionProtection = transactionProtection;
	}
}
