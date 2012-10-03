
package com.itsz.sht.common.model.response;


import com.itsz.sht.common.model.AbstractResponseModel;
import com.taifook.mcs.core.beans.msg.MTSSShareHoldingResponse;


public class EnquiryPositionResponseModel extends AbstractResponseModel {
	private MTSSShareHoldingResponse mtsSShareHoldingResponse;
	public MTSSShareHoldingResponse getMtsSShareHoldingResponse() {
		return mtsSShareHoldingResponse;
	}
	public void setMtsSShareHoldingResponse(
			MTSSShareHoldingResponse mtsSShareHoldingResponse) {
		this.mtsSShareHoldingResponse = mtsSShareHoldingResponse;
	}
	private String accountId;
	private String allowModify;
	
	public String getAllowModify() {
		return allowModify;
	}
	public void setAllowModify(String allowModify) {
		this.allowModify = allowModify;
	}
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
}
