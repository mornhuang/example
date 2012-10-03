package com.itsz.sht.common.model.request;

import java.util.Collection;

import com.itsz.sht.common.model.AbstractRequestModel;

/**
 * $Id: BulkCancelOrderRequestModel.java,v 1.2 2011/03/16 07:58:20 pbxie Exp $
 * @Project:portal.head
 * @File:CancelOrderRequestModel.java
 * @Description:
 * @Author:Cimenon Saint
 * @Date:2007-5-21
 */
public class BulkCancelOrderRequestModel extends AbstractRequestModel{
	
	private String loginId;
	private String password;
	private Collection mcsOrderID; //其实是BulkCancelOrderInfo对象集
    private String accountId;
    private int listSize;//????
    private String transactionProtection;
	
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
	public int getListSize() {
		return listSize;
	}
	public void setListSize(int listSize) {
		this.listSize = listSize;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getTransactionProtection() {
		return transactionProtection;
	}
	public void setTransactionProtection(String transactionProtection) {
		this.transactionProtection = transactionProtection;
	}
	/**
	 * @return the mcsOrderID
	 */
	public Collection getMcsOrderID() {
		return mcsOrderID;
	}
	/**
	 * @param mcsOrderID the mcsOrderID to set
	 */
	public void setMcsOrderID(Collection mcsOrderID) {
		this.mcsOrderID = mcsOrderID;
	}
	
}
