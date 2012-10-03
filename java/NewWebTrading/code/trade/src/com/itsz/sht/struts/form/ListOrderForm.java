package com.itsz.sht.struts.form;


/**
 * $Id: ListOrderForm.java,v 1.3 2011/03/09 02:41:37 zxfan Exp $
 * 
 * @Project:portal.head
 * @File:ListOrderForm.java
 * @Description:
 * @Author:Cimenon Saint
 * @Date:2007-5-21
 */
public class ListOrderForm extends ITSZForm {

	private static final long serialVersionUID = 6753871702406495008L;
	private String accountId;
	private String marketCode;
	private String orderStateType;
	private String pageNo = "1";
	private String pageSize;
	private String instrCode;

	public String getInstrCode() {
		return instrCode;
	}
	public void setInstrCode(String instrCode) {
		this.instrCode = instrCode;
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
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public String getMarketCode() {
		return marketCode;
	}
	public void setMarketCode(String marketCode) {
		this.marketCode = marketCode;
	}
	public String getOrderStateType() {
		return orderStateType;
	}
	public void setOrderStateType(String orderStateType) {
		this.orderStateType = orderStateType;
	}
}