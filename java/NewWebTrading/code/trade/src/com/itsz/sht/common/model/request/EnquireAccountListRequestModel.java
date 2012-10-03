package com.itsz.sht.common.model.request;

import com.itsz.sht.common.model.AbstractRequestModel;

/**
 * $Id: EnquireAccountListRequestModel.java,v 1.1 2010/11/09 03:57:27 kyzou Exp $
 * @Project:portal
 * @File:EnquireAccountListRequestModel.java
 * @Description:
 * @Author:kyzou
 * @Date:2008-3-18
 */
public class EnquireAccountListRequestModel extends AbstractRequestModel {
    private String custCode;

	public String getCustCode() {
		return custCode;
	}

	public void setCustCode(String custCode) {
		this.custCode = custCode;
	}
}
