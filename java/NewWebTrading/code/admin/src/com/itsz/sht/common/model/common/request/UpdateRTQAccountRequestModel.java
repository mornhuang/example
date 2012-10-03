package com.itsz.sht.common.model.common.request;

import com.itsz.sht.common.model.common.AbstractRequestModel;



public class UpdateRTQAccountRequestModel extends AbstractRequestModel{

 private String productId;
	 
	 private String	rTQLoginIdFrom;
	 
	 private String	rTQLoginIdTo;
	 
	 private String	rTQLoginPassword;
	 
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getrTQLoginIdFrom() {
		return rTQLoginIdFrom;
	}
	public void setrTQLoginIdFrom(String rTQLoginIdFrom) {
		this.rTQLoginIdFrom = rTQLoginIdFrom;
	}
	public String getrTQLoginIdTo() {
		return rTQLoginIdTo;
	}
	public void setrTQLoginIdTo(String rTQLoginIdTo) {
		this.rTQLoginIdTo = rTQLoginIdTo;
	}
	public String getrTQLoginPassword() {
		return rTQLoginPassword;
	}
	public void setrTQLoginPassword(String rTQLoginPassword) {
		this.rTQLoginPassword = rTQLoginPassword;
	}
	
}
