package com.itsz.sht.struts.form;

/**
 * $Id: TransferFundActionForm.java,v 1.3 2011/03/11 10:24:38 pbxie Exp $
 * @Project:NewWebTrading
 * @File:TransferFundActionForm.java
 * @Description:
 * @Author:kyzou
 * @Date:2010-12-6
 */
public class TransferFundActionForm extends ITSZForm {
	private static final long serialVersionUID = -5242732188481084682L;

	private String passwd;
	private String amount;
	private String fromAccountId;
	private String toCcy;
	private String fromCcy;
	private String toAccountId;
	private String bankCode;
	

	/**
	 * @return the bankCode
	 */
	public String getBankCode() {
		return bankCode;
	}

	/**
	 * @param bankCode the bankCode to set
	 */
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getPassword() {
		return passwd;
	}

	public void setPassword(String passwd) {
		this.passwd = passwd;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
