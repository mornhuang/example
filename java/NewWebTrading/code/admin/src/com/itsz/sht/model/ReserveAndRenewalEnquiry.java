package com.itsz.sht.model;


public class ReserveAndRenewalEnquiry {

	private String clientId;
	private String productId;
	private String productName;
	private String priceInHK;
	private String defAccount;
	private String updBy;
	private String updDate;
	private String type;
	
	public ReserveAndRenewalEnquiry(String clientId,String productId,String priceInHK,String defAccount,String updDate,String updBy,String type){
		this.clientId=clientId;
		this.productId=productId;
		this.priceInHK=priceInHK;
		this.defAccount=defAccount;
		this.updDate=updDate;
		this.updBy=updBy;
		this.type=type;
	}
	
	public ReserveAndRenewalEnquiry(){
		super();
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
	public String getPriceInHK() {
		return priceInHK;
	}
	public void setPriceInHK(String priceInHK) {
		this.priceInHK = priceInHK;
	}
	public String getDefAccount() {
		return defAccount;
	}
	public void setDefAccount(String defAccount) {
		this.defAccount = defAccount;
	}
	public String getUpdBy() {
		return updBy;
	}
	public void setUpdBy(String updBy) {
		this.updBy = updBy;
	}
	public String getUpdDate() {
		return updDate;
	}
	public void setUpdDate(String updDate) {
		this.updDate = updDate;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
}
