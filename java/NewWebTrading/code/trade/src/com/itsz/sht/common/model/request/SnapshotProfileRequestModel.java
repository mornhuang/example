package com.itsz.sht.common.model.request;

import com.itsz.sht.common.model.AbstractRequestModel;

/**
 * $Id: SnapshotProfileRequestModel.java,v 1.1 2010/11/09 03:57:27 kyzou Exp $
 * @Project:portal_b61
 * @File:SnapshotProfileRequestModel.java
 * @Description:
 * @Author:kyzou
 * @Date:2009-4-20
 */
public class SnapshotProfileRequestModel extends AbstractRequestModel{
	private String productId;
	private String custCode;

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getCustCode() {
		return custCode;
	}

	public void setCustCode(String custCode) {
		this.custCode = custCode;
	}
}
