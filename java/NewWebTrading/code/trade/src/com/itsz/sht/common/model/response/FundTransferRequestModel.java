package com.itsz.sht.common.model.response;

import java.math.BigDecimal;

import com.itsz.sht.common.model.AbstractRequestModel;

/**
 * $Id: FundTransferRequestModel.java,v 1.5 2011/03/11 10:24:38 pbxie Exp $
 * @Project:NewWebTrading
 * @File:FundTransferRequestModel.java
 * @Description:
 * @Author:kyzou
 * @Date:2010-12-6
 */
public class FundTransferRequestModel extends AbstractRequestModel {
	private String loginId;
	private String passwd;
	private BigDecimal amount;
	private String fromAccountId;
	private String toCcy;
	private String fromCcy;
	private String toAccountId;
	private String bankCode;
	private boolean isBank = false;
	
	public boolean isBank() {
		return isBank;
	}
	public void setBank(boolean isBank) {
		this.isBank = isBank;
	}
	public String getLoginId() {
		return loginId;
	}
	public String getBankCode() {
		return bankCode;
	}
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPassword(String passwd) {
		this.passwd = passwd;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public String getFromAccountId() {
		return fromAccountId;
	}
	public void setFromAccountId(String fromAccountId) {
		this.fromAccountId = fromAccountId;
	}
	public String getToCcy() {
		return toCcy;
	}
	public void setToCcy(String toCcy) {
		this.toCcy = toCcy;
	}
	public String getFromCcy() {
		return fromCcy;
	}
	public void setFromCcy(String fromCcy) {
		this.fromCcy = fromCcy;
	}
	public String getToAccountId() {
		return toAccountId;
	}
	public void setToAccountId(String toAccountId) {
		this.toAccountId = toAccountId;
	}
}
