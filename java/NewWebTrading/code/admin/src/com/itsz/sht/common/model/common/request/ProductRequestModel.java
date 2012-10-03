package com.itsz.sht.common.model.common.request;

import com.itsz.sht.common.model.common.AbstractRequestModel;
import com.itsz.sht.dao.hibernate.model.Product;

public class ProductRequestModel extends AbstractRequestModel{

	private String productId;

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}
	
}
