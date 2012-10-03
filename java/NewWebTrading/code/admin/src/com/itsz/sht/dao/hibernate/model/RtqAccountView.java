package com.itsz.sht.dao.hibernate.model;

public class RtqAccountView {
	
	private String productId;
	private String accountTotal;
	private String accountRequiredThisMonth;
	private String accountInUseThisMonth;
	private String accountRequiredNextMonth;
	private String lastAccountId;
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getAccountTotal() {
		return accountTotal;
	}
	public void setAccountTotal(String accountTotal) {
		this.accountTotal = accountTotal;
	}
	public String getAccountRequiredThisMonth() {
		return accountRequiredThisMonth;
	}
	public void setAccountRequiredThisMonth(String accountRequiredThisMonth) {
		this.accountRequiredThisMonth = accountRequiredThisMonth;
	}
	public String getAccountInUseThisMonth() {
		return accountInUseThisMonth;
	}
	public void setAccountInUseThisMonth(String accountInUseThisMonth) {
		this.accountInUseThisMonth = accountInUseThisMonth;
	}
	public String getAccountRequiredNextMonth() {
		return accountRequiredNextMonth;
	}
	public void setAccountRequiredNextMonth(String accountRequiredNextMonth) {
		this.accountRequiredNextMonth = accountRequiredNextMonth;
	}
	public String getLastAccountId() {
		return lastAccountId;
	}
	public void setLastAccountId(String lastAccountId) {
		this.lastAccountId = lastAccountId;
	}
	 
}
