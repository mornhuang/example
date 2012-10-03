package com.itsz.sht.common.model.response;

import com.itsz.sht.common.model.AbstractResponseModel;
import com.taifook.mcs.core.beans.msg.PPSTransferDetailResponse;

/**
 * $Id: IPPSRecordsResponseModel.java,v 1.2 2010/12/08 02:57:15 kyzou Exp $
 * @Project:NewWebTrading
 * @File:IPPSRecordsResponseModel.java
 * @Description:
 * @Author:kyzou
 * @Date:2010-11-16
 */
public class IPPSRecordsResponseModel extends AbstractResponseModel {
	private PPSTransferDetailResponse ppsTransferDetailResponse;

	public PPSTransferDetailResponse getPpsTransferDetailResponse() {
		return ppsTransferDetailResponse;
	}

	public void setPpsTransferDetailResponse(
			PPSTransferDetailResponse ppsTransferDetailResponse) {
		this.ppsTransferDetailResponse = ppsTransferDetailResponse;
	}
}
