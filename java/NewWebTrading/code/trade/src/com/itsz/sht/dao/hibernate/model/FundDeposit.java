package com.itsz.sht.dao.hibernate.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * $Id: FundDeposit.java,v 1.2 2011/01/21 03:16:08 kyzou Exp $
 * @Project:NewWebTrading
 * @File:FundDeposit.java
 * @Description:
 * @Author:kyzou
 * @Date:2011-1-15
 */
public class FundDeposit implements Serializable {
	private static final long serialVersionUID = 1L;
	private long fundDepositId;
	private Date receiveDate;
	private Date depositDate;
	private String receiveDate_str;
	private String depositDate_str;
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
	private String isSended;
	private String requestNo;

	public String getRequestNo() {
		return requestNo;
	}

	public void setRequestNo(String requestNo) {
		this.requestNo = requestNo;
	}

	public String getIsSended() {
		return isSended;
	}

	public void setIsSended(String isSended) {
		this.isSended = isSended;
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

	public long getFundDepositId() {
		return fundDepositId;
	}

	public void setFundDepositId(long fundDepositId) {
		this.fundDepositId = fundDepositId;
	}

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

	public String getReceiveDate_str() {
		return receiveDate_str;
	}

	public void setReceiveDate_str(String receiveDateStr) {
		receiveDate_str = receiveDateStr;
	}

	public String getDepositDate_str() {
		return depositDate_str;
	}

	public void setDepositDate_str(String depositDateStr) {
		depositDate_str = depositDateStr;
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
	
}
