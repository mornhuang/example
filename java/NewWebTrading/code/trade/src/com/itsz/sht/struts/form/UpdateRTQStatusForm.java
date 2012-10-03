package com.itsz.sht.struts.form;


/**
 * $Id: UpdateRTQStatusForm.java,v 1.3 2011/01/05 05:16:17 kyzou Exp $
 * @Project:NewWebTrading
 * @File:UpdateRTQStatus.java
 * @Description:
 * @Author:kyzou
 * @Date:2010-12-17
 */
public class UpdateRTQStatusForm extends ITSZForm {
	private static final long serialVersionUID = 1L;
	private String productId;
	private String allwRenl;
	private String status;
	private String updBy;
	private String password;

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getAllwRenl() {
		return allwRenl;
	}
	public void setAllwRenl(String allwRenl) {
		this.allwRenl = allwRenl;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getUpdBy() {
		return updBy;
	}
	public void setUpdBy(String updBy) {
		this.updBy = updBy;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
