package com.itsz.sht.struts.form;

/**
 * $Id: PurchaseRTQForm.java,v 1.3 2010/12/27 10:08:46 kyzou Exp $
 * @Project:NewWebTrading
 * @File:PurchaseRTQForm.java
 * @Description:
 * @Author:kyzou
 * @Date:2010-12-17
 */
public class PurchaseRTQForm extends ITSZForm {
	private static final long serialVersionUID = 1L;
	private String selectMonth;
	private String productId;
	private String allowRenewal; //"Y"\"N"
	private String password;

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSelectMonth() {
		return selectMonth;
	}
	public void setSelectMonth(String selectMonth) {
		this.selectMonth = selectMonth;
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
