package com.itsz.sht.common.model.common.response;

import java.util.List;

import com.itsz.sht.common.model.common.AbstractResponseModel;



public class DeleteRTQAccountLastNResponseModel  extends AbstractResponseModel{
	   private List<String> rtqLoginIdList;

	public List<String> getRtqLoginIdList() {
		return rtqLoginIdList;
	}

	public void setRtqLoginIdList(List<String> rtqLoginIdList) {
		this.rtqLoginIdList = rtqLoginIdList;
	}
	   
	
}
