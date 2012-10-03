package com.itsz.sht.struts.form;

/**
 * $Id: ReserveRTQForm.java,v 1.1 2010/12/17 08:51:25 kyzou Exp $
 * @Project:NewWebTrading
 * @File:ReserveRTQForm.java
 * @Description:
 * @Author:kyzou
 * @Date:2010-12-17
 */
public class ReserveRTQForm extends ITSZForm {
	private static final long serialVersionUID = 1L;
	private String clientId;
    private String defDebtAcc; //Default Debit Account
    private String productId;
    private String allowRenewal; //"Y"\"N"
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getDefDebtAcc() {
		return defDebtAcc;
	}
	public void setDefDebtAcc(String defDebtAcc) {
		this.defDebtAcc = defDebtAcc;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getAllowRenewal() {
		return allowRenewal;
	}
	public void setAllowRenewal(String allowRenewal) {
		this.allowRenewal = allowRenewal;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
