package com.itsz.sht.common.model.common.response;

import com.itsz.sht.common.model.common.AbstractResponseModel;
import com.itsz.sht.dao.hibernate.model.Product;



public class PurchasedProductResponseModel extends AbstractResponseModel{
	private boolean havePurchased;
	private Product product;
	
	public boolean isHavePurchased() {
		return havePurchased;
	}

	public void setHavePurchased(boolean havePurchased) {
		this.havePurchased = havePurchased;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
}
