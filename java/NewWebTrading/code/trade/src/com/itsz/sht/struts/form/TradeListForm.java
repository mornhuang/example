package com.itsz.sht.struts.form;

/**
 * $Id: TradeListForm.java,v 1.2 2010/12/31 09:25:28 zxfan Exp $
 * @Project:portal
 * @File:TradeListForm.java
 * @Description:
 * @Author:kyzou
 * @Date:2009-6-26
 */
public class TradeListForm extends ITSZForm {
	private static final long serialVersionUID = 1L;

	private String accountId;
	private String instrCode;
	private String orderSide;
	private String channel;
	private String fromDate;
	private String toDate;
	private String fromIdx;
	private String toIdx;
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
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
}
