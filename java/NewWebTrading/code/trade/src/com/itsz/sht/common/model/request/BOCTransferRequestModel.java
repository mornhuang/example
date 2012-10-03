package com.itsz.sht.common.model.request;

import java.math.BigDecimal;

import com.itsz.sht.common.model.AbstractRequestModel;

/**
 * $Id: BOCTransferRequestModel.java,v 1.2 2010/12/08 02:57:15 kyzou Exp $
 * @Project:NewWebTrading
 * @File:BOCTransferRequestModel.java
 * @Description:
 * @Author:kyzou
 * @Date:2010-12-6
 */
public class BOCTransferRequestModel extends AbstractRequestModel {
    private String accountId;
    private BigDecimal payAmount;
    private String transactionDateTime;
    private String locale;
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public BigDecimal getPayAmount() {
		return payAmount;
	}
	public void setPayAmount(BigDecimal payAmount) {
		this.payAmount = payAmount;
	}
	public String getTransactionDateTime() {
		return transactionDateTime;
	}
	public void setTransactionDateTime(String transactionDateTime) {
		this.transactionDateTime = transactionDateTime;
	}
	public String getLocale() {
		return locale;
	}
	public void setLocale(String locale) {
		this.locale = locale;
	}
}
