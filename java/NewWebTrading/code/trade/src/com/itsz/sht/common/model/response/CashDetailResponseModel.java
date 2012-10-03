package com.itsz.sht.common.model.response;

import com.itsz.sht.common.model.AbstractResponseModel;
import com.taifook.mcs.core.beans.msg.MISAccountCashHoldingResponse;

/**
 * $Id: CashDetailResponseModel.java,v 1.1 2011/03/11 10:24:38 pbxie Exp $
 * @Project:portal.head
 * @File:ModifyOrderResponseModel.java
 * @Description:
 * @Author:Cimenon Saint
 * @Date:2007-5-21
 */
public class CashDetailResponseModel extends AbstractResponseModel {

	private MISAccountCashHoldingResponse misAccountCashHoldingResponse;

	/**
	 * @return the misAccountCashHoldingResponse
	 */
	public MISAccountCashHoldingResponse getMisAccountCashHoldingResponse() {
		return misAccountCashHoldingResponse;
	}

	/**
	 * @param misAccountCashHoldingResponse the misAccountCashHoldingResponse to set
	 */
	public void setMisAccountCashHoldingResponse(
			MISAccountCashHoldingResponse misAccountCashHoldingResponse) {
		this.misAccountCashHoldingResponse = misAccountCashHoldingResponse;
	}
	

}
