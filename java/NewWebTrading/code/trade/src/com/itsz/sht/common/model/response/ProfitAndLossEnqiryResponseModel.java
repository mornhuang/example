package com.itsz.sht.common.model.response;

import com.itsz.sht.common.model.AbstractResponseModel;
import com.taifook.mcs.core.beans.msg.ProfitAndLossEnquiryResponse;

public class ProfitAndLossEnqiryResponseModel extends AbstractResponseModel {
	private ProfitAndLossEnquiryResponse profitAndLossEnquiryResponse;

	/**
	 * @return the profitAndLossEnquiryResponse
	 */
	public ProfitAndLossEnquiryResponse getProfitAndLossEnquiryResponse() {
		return profitAndLossEnquiryResponse;
	}

	/**
	 * @param profitAndLossEnquiryResponse the profitAndLossEnquiryResponse to set
	 */
	public void setProfitAndLossEnquiryResponse(
			ProfitAndLossEnquiryResponse profitAndLossEnquiryResponse) {
		this.profitAndLossEnquiryResponse = profitAndLossEnquiryResponse;
	}
	

}
