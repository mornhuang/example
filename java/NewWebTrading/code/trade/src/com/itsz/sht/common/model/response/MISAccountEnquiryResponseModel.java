package com.itsz.sht.common.model.response;

import com.itsz.sht.common.model.AbstractResponseModel;
import com.taifook.mcs.core.beans.msg.MISAccountEnquiryResponse;

/**
 * $Id: MISAccountEnquiryResponseModel.java,v 1.1 2010/11/24 09:22:53 kyzou Exp $
 * @Project:NewWebTrading
 * @File:MISAccountEnquiryResponseModel.java
 * @Description:
 * @Author:kyzou
 * @Date:2010-11-24
 */
public class MISAccountEnquiryResponseModel extends AbstractResponseModel {
	private MISAccountEnquiryResponse misAccountEnquiryResponse;

	public MISAccountEnquiryResponse getMisAccountEnquiryResponse() {
		return misAccountEnquiryResponse;
	}

	public void setMisAccountEnquiryResponse(
			MISAccountEnquiryResponse misAccountEnquiryResponse) {
		this.misAccountEnquiryResponse = misAccountEnquiryResponse;
	}
}
