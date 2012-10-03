package com.itsz.sht.common.model.common.request;

import com.itsz.sht.common.model.common.AbstractRequestModel;



public class ChangeRTQ_SHKProductRequestModel extends AbstractRequestModel{
	
	private String productId;
	private String productStatus;
	private Long quota;
	private Long priceInHKD;
	private String remarks;
	private String actionBy;
	
	public String getActionBy() {
		return actionBy;
	}
	public void setActionBy(String actionBy) {
		this.actionBy = actionBy;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getProductStatus() {
		return productStatus;
	}
	public void setProductStatus(String productStatus) {
		this.productStatus = productStatus;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public Long getQuota() {
		return quota;
	}
	public void setQuota(Long quota) {
		this.quota = quota;
	}
	public Long getPriceInHKD() {
		return priceInHKD;
	}
	public void setPriceInHKD(Long priceInHKD) {
		this.priceInHKD = priceInHKD;
	}
	
}
