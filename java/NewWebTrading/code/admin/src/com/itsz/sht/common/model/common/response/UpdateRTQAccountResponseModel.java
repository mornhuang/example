package com.itsz.sht.common.model.common.response;

import java.util.List;

import com.itsz.sht.common.model.common.AbstractResponseModel;



public class UpdateRTQAccountResponseModel  extends AbstractResponseModel{

	private boolean updateRTQAccountFlag;
	private List<String> rtqLognIdList;

	public boolean getUpdateRTQAccountFlag() {
		return updateRTQAccountFlag;
	}

	public void setUpdateRTQAccountFlag(boolean updateRTQAccountFlag) {
		this.updateRTQAccountFlag = updateRTQAccountFlag;
	}

	public List<String> getRtqLognIdList() {
		return rtqLognIdList;
	}

	public void setRtqLognIdList(List<String> rtqLognIdList) {
		this.rtqLognIdList = rtqLognIdList;
	}
	
}
