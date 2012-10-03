package com.itsz.sht.common.model.common.request;

import com.itsz.sht.common.model.common.AbstractRequestModel;

public class PurchaseServiceRequestModel extends AbstractRequestModel{
	
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
	
	
}
