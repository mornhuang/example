package com.itsz.sht.common.model.request;

import java.math.BigDecimal;
import java.util.Date;

import com.itsz.sht.common.model.AbstractRequestModel;

/**
 * $Id: FundDepositRequestModel.java,v 1.1 2011/01/17 01:56:24 kyzou Exp $
 * @Project:NewWebTrading
 * @File:FundDepositRequestModel.java
 * @Description:
 * @Author:kyzou
 * @Date:2011-1-15
 */
public class FundDepositRequestModel extends AbstractRequestModel {
	private Date receiveDate;
	private Date depositDate;
	private String receiveTime;
	private String depositTime;
	private String accountName;
	private String accountNo;
	private String currency;
	private BigDecimal amount;
	private String bank;
	private String bankAcc;
	private String source;
	private String Reference;
	private String Remarks;
	public Date getReceiveDate() {
		return receiveDate;
	}
	public void setReceiveDate(Date receiveDate) {
		this.receiveDate = receiveDate;
	}
	public Date getDepositDate() {
		return depositDate;
	}
	public void setDepositDate(Date depositDate) {
		this.depositDate = depositDate;
	}
	public String getReceiveTime() {
		return receiveTime;
	}
	public void setReceiveTime(String receiveTime) {
		this.receiveTime = receiveTime;
	}
	public String getDepositTime() {
		return depositTime;
	}
	public void setDepositTime(String depositTime) {
		this.depositTime = depositTime;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
	public String getBankAcc() {
		return bankAcc;
	}
	public void setBankAcc(String bankAcc) {
		this.bankAcc = bankAcc;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getReference() {
		return Reference;
	}
	public void setReference(String reference) {
		Reference = reference;
	}
	public String getRemarks() {
		return Remarks;
	}
	public void setRemarks(String remarks) {
		Remarks = remarks;
	}
	
}
