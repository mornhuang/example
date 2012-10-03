package com.itsz.sht.common.model.request;

import java.io.Serializable;

import com.itsz.sht.common.model.AbstractRequestModel;

/**
 * $Id: TradeListRequestModel.java,v 1.3 2011/01/18 07:26:46 xlliu Exp $
 * @Project:portal
 * @File:TradeListRequestModel.java
 * @Description:
 * @Author:kyzou
 * @Date:2009-6-26
 */
public class TradeListRequestModel extends AbstractRequestModel implements Serializable {
	private String loginId;
	private String accountId;
	private String instrCode;
	private String orderSide;
	private String channel;
	private String fromDate;
	private String toDate;
	private String fromIdx;
	private String toIdx;
	private String pageSize;
	private String pageNo;
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
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
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
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	public String getFromIdx() {
		return fromIdx;
	}
	public void setFromIdx(String fromIdx) {
		this.fromIdx = fromIdx;
	}
	public String getToIdx() {
		return toIdx;
	}
	public void setToIdx(String toIdx) {
		this.toIdx = toIdx;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
}
