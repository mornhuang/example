package com.itsz.sht.common.model.common.request;

import com.itsz.sht.common.model.common.AbstractRequestModel;
import com.itsz.sht.dao.hibernate.model.Product;

public class AddProductRequestModel extends AbstractRequestModel{

	private Product product;

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
	
}
