package com.itsz.sht.model;

public class RtqAccountView {

	private String productId;
	private int accountTotal;
	private int accountRequiredThisMonth;
	private int accountInUseThisMonth;
	private int accountRequiredNextMonth;
	private String lastAccountId;
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	
	public int getAccountTotal() {
		return accountTotal;
	}
	public void setAccountTotal(int accountTotal) {
		this.accountTotal = accountTotal;
	}
	public int getAccountRequiredThisMonth() {
		return accountRequiredThisMonth;
	}
	public void setAccountRequiredThisMonth(int accountRequiredThisMonth) {
		this.accountRequiredThisMonth = accountRequiredThisMonth;
	}
	public int getAccountInUseThisMonth() {
		return accountInUseThisMonth;
	}
	public void setAccountInUseThisMonth(int accountInUseThisMonth) {
		this.accountInUseThisMonth = accountInUseThisMonth;
	}
	public int getAccountRequiredNextMonth() {
		return accountRequiredNextMonth;
	}
	public void setAccountRequiredNextMonth(int accountRequiredNextMonth) {
		this.accountRequiredNextMonth = accountRequiredNextMonth;
	}
	public String getLastAccountId() {
		return lastAccountId;
	}
	public void setLastAccountId(String lastAccountId) {
		this.lastAccountId = lastAccountId;
	}
	 
}
