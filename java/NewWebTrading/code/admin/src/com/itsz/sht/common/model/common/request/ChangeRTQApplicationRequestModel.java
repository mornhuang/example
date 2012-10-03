package com.itsz.sht.common.model.common.request;

import java.util.Date;

import com.itsz.sht.common.model.common.AbstractRequestModel;



public class ChangeRTQApplicationRequestModel extends AbstractRequestModel{
    
	private String productId;
	
	private String rTQProvider;
	
	private String rTQUrl;
	
	private String rTQStatus;
	
	private String updBy;
	
	private Date updDate;

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getrTQProvider() {
		return rTQProvider;
	}

	public void setrTQProvider(String rTQProvider) {
		this.rTQProvider = rTQProvider;
	}

	public String getrTQUrl() {
		return rTQUrl;
	}

	public void setrTQUrl(String rTQUrl) {
		this.rTQUrl = rTQUrl;
	}

	public String getrTQStatus() {
		return rTQStatus;
	}

	public void setrTQStatus(String rTQStatus) {
		this.rTQStatus = rTQStatus;
	}

	public String getUpdBy() {
		return updBy;
	}

	public void setUpdBy(String updBy) {
		this.updBy = updBy;
	}

	public Date getUpdDate() {
		return updDate;
	}

	public void setUpdDate(Date updDate) {
		this.updDate = updDate;
	}	
	
}
