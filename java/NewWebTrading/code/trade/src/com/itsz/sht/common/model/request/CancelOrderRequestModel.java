package com.itsz.sht.common.model.request;


import com.itsz.sht.common.model.AbstractRequestModel;

/**
 * $Id: CancelOrderRequestModel.java,v 1.2 2010/11/24 07:38:05 kyzou Exp $
 * @Project:portal
 * @File:CancelOrderRequestModel.java
 * @Description:
 * @Author:kyzou
 * @Date:2008-11-13
 */
public class CancelOrderRequestModel  extends AbstractRequestModel{
	private String loginId;
	private String password;
	private long MCSOrderID;
	private long MTSSOrderID;
	private String custCode;
    private String accountId;
    private int listSize;
    private String transactionProtection;
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public long getMCSOrderID() {
		return MCSOrderID;
	}
	public void setMCSOrderID(long orderID) {
		MCSOrderID = orderID;
	}
	public long getMTSSOrderID() {
		return MTSSOrderID;
	}
	public void setMTSSOrderID(long orderID) {
		MTSSOrderID = orderID;
	}
	public String getCustCode() {
		return custCode;
	}
	public void setCustCode(String custCode) {
		this.custCode = custCode;
	}
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public int getListSize() {
		return listSize;
	}
	public void setListSize(int listSize) {
		this.listSize = listSize;
	}
	public String getTransactionProtection() {
		return transactionProtection;
	}
	public void setTransactionProtection(String transactionProtection) {
		this.transactionProtection = transactionProtection;
	}
}
