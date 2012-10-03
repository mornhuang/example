package com.itsz.sht.common.model.common.response;

import com.itsz.sht.common.model.common.AbstractResponseModel;
import com.itsz.sht.dao.hibernate.model.Product;

public class ProductResponseModel  extends AbstractResponseModel {
	private Product product;

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
}
