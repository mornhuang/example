package com.itsz.sht.common.model.request;

import com.itsz.sht.common.model.AbstractRequestModel;

/**
 * $Id: ListOrderRequestModel.java,v 1.1 2010/11/09 03:57:27 kyzou Exp $
 * @Project:portal
 * @File:ListOrderRequestModel.java
 * @Description:
 * @Author:kyzou
 * @Date:2007-11-8
 */
public class ListOrderRequestModel extends AbstractRequestModel{
	private String accountId;
	private String orderStateType;//order state type,such as:
	private String marketCode;
	private String pageSize;
	private String pageNo;
	private int fromIdx;
	private int toIdx;
	
	public String getMarketCode() {
		return marketCode;
	}
	public void setMarketCode(String marketCode) {
		this.marketCode = marketCode;
	}
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public String getOrderStateType() {
		return orderStateType;
	}
	public void setOrderStateType(String orderStateType) {
		this.orderStateType = orderStateType;
	}
	public int getFromIdx() {
		return fromIdx;
	}
	public void setFromIdx(int fromIdx) {
		this.fromIdx = fromIdx;
	}
	public int getToIdx() {
		return toIdx;
	}
	public void setToIdx(int toIdx) {
		this.toIdx = toIdx;
	}
	public String getPageSize() {
		return pageSize;
	}
	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}
	public String getPageNo() {
		return pageNo;
	}
	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}
}
