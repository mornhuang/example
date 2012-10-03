package com.itsz.sht.common.model.response;

import com.itsz.sht.common.model.AbstractResponseModel;
import com.taifook.mcs.core.beans.msg.MISAccountListResponse;

/**
 * $Id: MISAccountListResponseModel.java,v 1.2 2010/11/24 09:23:31 kyzou Exp $
 * @Project:portal
 * @File:MISAccountListResponseModel.java
 * @Description:
 * @Author:kyzou
 * @Date:2008-3-19
 */
public class MISAccountListResponseModel extends AbstractResponseModel{
	private MISAccountListResponse misAccountListResponse;

	public MISAccountListResponse getMisAccountListResponse() {
		return misAccountListResponse;
	}

	public void setMisAccountListResponse(
			MISAccountListResponse misAccountListResponse) {
		this.misAccountListResponse = misAccountListResponse;
	}
	
}
