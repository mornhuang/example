package com.itsz.sht.common.model.response;

import com.itsz.sht.common.model.AbstractResponseModel;

/**
 * $Id: EnquireShortRTQResponseModel.java,v 1.1 2010/11/09 03:57:28 kyzou Exp $
 * @Project:portal
 * @File:EnquireShortRTQResponseModel.java
 * @Description:
 * @Author:kyzou
 * @Date:2009-4-13
 */
public class EnquireShortRTQResponseModel extends AbstractResponseModel {
	private ShortRTQResponse shortRTQResponse;

	public ShortRTQResponse getShortRTQResponse() {
		return shortRTQResponse;
	}

	public void setShortRTQResponse(ShortRTQResponse shortRTQResponse) {
		this.shortRTQResponse = shortRTQResponse;
	}
}
