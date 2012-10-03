package com.itsz.sht.model;

import java.util.Date;

public class MonthlyPurchaseEnquiry implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String clientId;
	private String productId;
	private String productName;
	private Date effectiveDate;
	private Date expireDate;
	private String paymentRequestId;
	private Date chargeDate;
	private String chargeCash;
	private String chargeAccount;
	private String responseMessage;
	private String payStatus;
	private Date updateDate;
	private String updateBy;

	public MonthlyPurchaseEnquiry(String clientId, String productId,
			 Date effectiveDate, Date expireDate,
			String paymentRequestId, Date chargeDate, String chargeCash,
			String chargeAccount,String responseMessage,String payStatus, Date updateDate, String updateBy) {
       this.clientId=clientId;
       this.productId=productId;
       this.effectiveDate=effectiveDate;
       this.expireDate=expireDate;
       this.paymentRequestId=paymentRequestId;
       this.chargeDate=chargeDate;
       this.chargeCash=chargeCash;
       this.chargeAccount=chargeAccount;
       this.responseMessage=responseMessage;
       this.payStatus=payStatus;
       this.updateDate=updateDate;
       this.updateBy=updateBy;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Date getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public Date getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}

	public String getPaymentRequestId() {
		return paymentRequestId;
	}

	public void setPaymentRequestId(String paymentRequestId) {
		this.paymentRequestId = paymentRequestId;
	}

	public Date getChargeDate() {
		return chargeDate;
	}

	public void setChargeDate(Date chargeDate) {
		this.chargeDate = chargeDate;
	}

	public String getChargeCash() {
		return chargeCash;
	}

	public void setChargeCash(String chargeCash) {
		this.chargeCash = chargeCash;
	}

	public String getChargeAccount() {
		return chargeAccount;
	}

	public void setChargeAccount(String chargeAccount) {
		this.chargeAccount = chargeAccount;
	}

	public String getResponseMessage() {
		return responseMessage;
	}

	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

	public String getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

}
