package com.itsz.sht.common.model.response;

import java.util.List;

import com.itsz.sht.common.model.AbstractResponseModel;

/**
 * $Id: IPPSEnquiryResponseModel.java,v 1.2 2010/12/01 11:21:49 kyzou Exp $
 * @Project:NewWebTrading
 * @File:IPPSEnquiryResponseModel.java
 * @Description:
 * @Author:kyzou
 * @Date:2010-11-16
 */
public class IPPSEnquiryResponseModel extends AbstractResponseModel {
	private List result;

	public List getResult() {
		return result;
	}

	public void setResult(List result) {
		this.result = result;
	}
}
