package com.itsz.sht.struts.form;

import com.itsz.sht.common.model.response.EnquireAccountResponseModel;
import com.itsz.sht.common.model.response.EnquiryPositionResponseModel;
import com.taifook.mcs.core.beans.msg.MISCashHoldingSummary;


public class AccountPositionForm extends ITSZForm{

	private static final long serialVersionUID = 1L;
	private String indexId;
    private MISCashHoldingSummary misCashHoldingSummary;
    private EnquiryPositionResponseModel positionResp;
    private EnquireAccountResponseModel accResp;
    private boolean havePosition = false;
	
    public String getIndexId() {
		return indexId;
	}
	public void setIndexId(String indexId) {
		this.indexId = indexId;
	}
	public EnquiryPositionResponseModel getPositionResp() {
		return positionResp;
	}
	public void setPositionResp(EnquiryPositionResponseModel positionResp) {
		this.positionResp = positionResp;
	}
	public boolean isHavePosition() {
		return havePosition;
	}
	public void setHavePosition(boolean havePosition) {
		this.havePosition = havePosition;
	}
    public EnquireAccountResponseModel getAccResp() {
		return accResp;
	}
	public MISCashHoldingSummary getMisCashHoldingSummary() {
		return misCashHoldingSummary;
	}
	public void setMisCashHoldingSummary(MISCashHoldingSummary misCashHoldingSummary) {
		this.misCashHoldingSummary = misCashHoldingSummary;
	}
	public void setAccResp(EnquireAccountResponseModel accResp) {
		this.accResp = accResp;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
