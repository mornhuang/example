package com.itsz.sht.common.model.common.request;

import com.itsz.sht.common.model.common.AbstractRequestModel;

public class UpdateUserPaymentByManualRequestModel extends AbstractRequestModel {
	private String[] usrProdPayIds;

	public String[] getUsrProdPayIds() {
		return usrProdPayIds;
	}

	public void setUsrProdPayIds(String[] usrProdPayIds) {
		this.usrProdPayIds = usrProdPayIds;
	}
	
}
