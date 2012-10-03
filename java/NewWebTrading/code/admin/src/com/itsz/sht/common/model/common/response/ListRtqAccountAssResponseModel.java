package com.itsz.sht.common.model.common.response;

import java.util.List;

import com.itsz.sht.common.model.common.AbstractResponseModel;
import com.itsz.sht.model.RtqAccountAssDto;

public class ListRtqAccountAssResponseModel extends AbstractResponseModel{

	private List<RtqAccountAssDto> rtqAccountAssDtoList;

	public List<RtqAccountAssDto> getRtqAccountAssDtoList() {
		return rtqAccountAssDtoList;
	}

	public void setRtqAccountAssDtoList(
			List<RtqAccountAssDto> rtqAccountAssDtoList) {
		this.rtqAccountAssDtoList = rtqAccountAssDtoList;
	}
	
	
}
