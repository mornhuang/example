package com.itsz.sht.common.model.common.response;

import java.util.List;

import com.itsz.sht.common.model.common.AbstractResponseModel;



public class DeleteRTQAccountResponseModel  extends AbstractResponseModel{

    private  boolean deleteRTQAccountFlag;
    
    private List<String> rtqLoginIdList;

	public boolean getDeleteRTQAccountFlag() {
		return deleteRTQAccountFlag;
	}

	public void setDeleteRTQAccountFlag(boolean deleteRTQAccountFlag) {
		this.deleteRTQAccountFlag = deleteRTQAccountFlag;
	}

	public List<String> getRtqLoginIdList() {
		return rtqLoginIdList;
	}

	public void setRtqLoginIdList(List<String> rtqLoginIdList) {
		this.rtqLoginIdList = rtqLoginIdList;
	}
	 
	
}
