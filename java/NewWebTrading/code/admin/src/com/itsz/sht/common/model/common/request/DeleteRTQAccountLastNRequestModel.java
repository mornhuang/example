package com.itsz.sht.common.model.common.request;

import com.itsz.sht.common.model.common.AbstractRequestModel;



public class DeleteRTQAccountLastNRequestModel extends AbstractRequestModel{

	private String productId;
	
	private Long n;

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public Long getN() {
		return n;
	}

	public void setN(Long n) {
		this.n = n;
	}


	
}
