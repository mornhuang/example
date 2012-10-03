package com.itsz.sht.common.model.response;

import com.itsz.sht.common.model.AbstractResponseModel;
import com.taifook.mcs.core.beans.msg.FundTransferResponse;
import com.taifook.mcs.core.beans.msg.WithDrawResponse;

/**
 * $Id: FundTransferResponseModel.java,v 1.2 2011/03/11 10:24:38 pbxie Exp $
 * @Project:NewWebTrading
 * @File:FundTransferResponseModel.java
 * @Description:
 * @Author:kyzou
 * @Date:2010-12-6
 */
public class FundTransferResponseModel extends AbstractResponseModel {
	private FundTransferResponse fundTransferResponse;
    private WithDrawResponse withDrawResponse;
    
	/**
	 * @return the withDrawResponse
	 */
	public WithDrawResponse getWithDrawResponse() {
		return withDrawResponse;
	}

	/**
	 * @param withDrawResponse the withDrawResponse to set
	 */
	public void setWithDrawResponse(WithDrawResponse withDrawResponse) {
		this.withDrawResponse = withDrawResponse;
	}

	public FundTransferResponse getFundTransferResponse() {
		return fundTransferResponse;
	}

	public void setFundTransferResponse(FundTransferResponse fundTransferResponse) {
		this.fundTransferResponse = fundTransferResponse;
	}
}
