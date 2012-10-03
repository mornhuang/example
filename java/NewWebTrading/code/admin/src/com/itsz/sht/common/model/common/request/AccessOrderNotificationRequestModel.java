package com.itsz.sht.common.model.common.request;

import com.itsz.sht.common.model.common.AbstractRequestModel;



public class AccessOrderNotificationRequestModel extends AbstractRequestModel {

	private String productId;
	
	private String clientId;

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
	
	
}
