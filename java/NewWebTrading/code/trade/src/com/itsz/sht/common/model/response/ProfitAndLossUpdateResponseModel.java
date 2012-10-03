package com.itsz.sht.common.model.response;

import com.itsz.sht.common.model.AbstractResponseModel;
import com.taifook.mcs.core.beans.msg.ProfitAndLossUpdateResponse;

public class ProfitAndLossUpdateResponseModel extends AbstractResponseModel {
	private ProfitAndLossUpdateResponse profitAndLossUpdateResponse;

	/**
	 * @return the profitAndLossUpdateResponse
	 */
	public ProfitAndLossUpdateResponse getProfitAndLossUpdateResponse() {
		return profitAndLossUpdateResponse;
	}

	/**
	 * @param profitAndLossUpdateResponse the profitAndLossUpdateResponse to set
	 */
	public void setProfitAndLossUpdateResponse(
			ProfitAndLossUpdateResponse profitAndLossUpdateResponse) {
		this.profitAndLossUpdateResponse = profitAndLossUpdateResponse;
	}
	

}
