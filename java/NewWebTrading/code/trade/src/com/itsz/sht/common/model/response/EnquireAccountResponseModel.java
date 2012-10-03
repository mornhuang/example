
package com.itsz.sht.common.model.response;

import com.itsz.sht.common.model.AbstractResponseModel;
import com.taifook.mcs.core.beans.msg.MISAccountDetailResponse;
import com.taifook.mcs.core.beans.msg.MISAccountSummaryResponse;


public class EnquireAccountResponseModel extends AbstractResponseModel {
	private MISAccountSummaryResponse misAccountSummaryResponse;
	private MISAccountDetailResponse misAccountDetailResponse;
	
	/**
	 * @return the misAccountDetailResponse
	 */
	public MISAccountDetailResponse getMisAccountDetailResponse() {
		return misAccountDetailResponse;
	}
	/**
	 * @param misAccountDetailResponse the misAccountDetailResponse to set
	 */
	public void setMisAccountDetailResponse(
			MISAccountDetailResponse misAccountDetailResponse) {
		this.misAccountDetailResponse = misAccountDetailResponse;
	}
	public MISAccountSummaryResponse getMisAccountSummaryResponse() {
		return misAccountSummaryResponse;
	}
	public void setMisAccountSummaryResponse(
			MISAccountSummaryResponse misAccountSummaryResponse) {
		this.misAccountSummaryResponse = misAccountSummaryResponse;
	}
}
