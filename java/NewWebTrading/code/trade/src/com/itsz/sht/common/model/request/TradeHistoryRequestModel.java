package com.itsz.sht.common.model.request;

import com.itsz.sht.common.model.AbstractRequestModel;

/**
 * $Id: TradeHistoryRequestModel.java,v 1.1 2010/11/09 03:57:28 kyzou Exp $
 * @Project:portal.head
 * @File:TradeHistoryRequestModel.java
 * @Description:
 * @Author:Cimenon Saint
 * @Date:2007-6-2
 */
public class TradeHistoryRequestModel extends AbstractRequestModel {

	private String accountId;
	private Long mcsOrderId;
	private Long orderId;
	
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public Long getMcsOrderId() {
		return mcsOrderId;
	}
	public void setMcsOrderId(Long mcsOrderId) {
		this.mcsOrderId = mcsOrderId;
	}
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

}
