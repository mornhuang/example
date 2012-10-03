package com.itsz.sht.common.model.response;

import com.itsz.sht.common.model.AbstractResponseModel;

/**
 * $Id: FundDepositResponseModel.java,v 1.2 2011/01/21 02:08:04 kyzou Exp $
 * @Project:NewWebTrading
 * @File:FundDepositResponseModel.java
 * @Description:
 * @Author:kyzou
 * @Date:2011-1-15
 */
public class FundDepositResponseModel extends AbstractResponseModel {
	private String requestNo;

	public String getRequestNo() {
		return requestNo;
	}

	public void setRequestNo(String requestNo) {
		this.requestNo = requestNo;
	}
	
}
