package com.itsz.sht.common.model.common.request;

import com.itsz.sht.common.model.common.AbstractRequestModel;

public class RTQAccountRequestModel extends AbstractRequestModel {
	private String productId;

	private String rtqLoginId;
	
	public String getRtqLoginId() {
		return rtqLoginId;
	}

	public void setRtqLoginId(String rtqLoginId) {
		this.rtqLoginId = rtqLoginId;
	}

	private Integer pageNumber;

	private Integer pageSize;

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public Integer getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
}
