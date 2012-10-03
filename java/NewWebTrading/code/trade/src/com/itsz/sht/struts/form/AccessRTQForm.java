package com.itsz.sht.struts.form;


public class AccessRTQForm extends ITSZForm {
	private static final long serialVersionUID = 1L;
	private String productId;
	private String clientId;
	private String clientIp;
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getClientIp() {
		return clientIp;
	}
	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}
}
