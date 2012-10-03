package com.itsz.sht.common.model.response;

import com.itsz.sht.common.model.AbstractResponseModel;
import com.taifook.mcs.core.beans.msg.MarginFinancingListResponse;

/**
 * $Id: MarginFinancingListResponseModel.java,v 1.1 2010/12/07 08:31:02 kyzou Exp $
 * @Project:NewWebTrading
 * @File:MarginFinancingListResponseModel.java
 * @Description:
 * @Author:kyzou
 * @Date:2010-12-7
 */
public class MarginFinancingListResponseModel extends AbstractResponseModel {
	private MarginFinancingListResponse marginFinancingListResponse;

	public MarginFinancingListResponse getMarginFinancingListResponse() {
		return marginFinancingListResponse;
	}

	public void setMarginFinancingListResponse(
			MarginFinancingListResponse marginFinancingListResponse) {
		this.marginFinancingListResponse = marginFinancingListResponse;
	}
}
