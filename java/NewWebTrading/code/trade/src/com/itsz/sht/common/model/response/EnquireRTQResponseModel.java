package com.itsz.sht.common.model.response;

import java.util.List;

import com.itsz.sht.common.model.AbstractResponseModel;

/**
 * $Id: EnquireRTQResponseModel.java,v 1.1 2010/11/09 03:57:28 kyzou Exp $
 * @Project:portal
 * @File:EnquireRTQResponseModel.java
 * @Description:
 * @Author:kyzou
 * @Date:2008-3-6
 */
public class EnquireRTQResponseModel extends AbstractResponseModel {
    private List<EnquireRTQResponse> enquireRTQResponse;

	public List<EnquireRTQResponse> getEnquireRTQResponse() {
		return enquireRTQResponse;
	}

	public void setEnquireRTQResponse(List<EnquireRTQResponse> enquireRTQResponse) {
		this.enquireRTQResponse = enquireRTQResponse;
	}
   
}
