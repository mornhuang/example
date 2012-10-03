package cn.itsz.newsim.dto.response;

import java.util.List;

import cn.itsz.newsim.dto.ResultMessage;
import cn.itsz.newsim.dto.response.entity.EnquireRTQResponseEntity;

public class EnquireRTQResponse extends ResultMessage {
	private List<EnquireRTQResponseEntity> enquireRTQResponse;

	public List<EnquireRTQResponseEntity> getEnquireRTQResponse() {
		return enquireRTQResponse;
	}

	public void setEnquireRTQResponse(
			List<EnquireRTQResponseEntity> enquireRTQResponse) {
		this.enquireRTQResponse = enquireRTQResponse;
	}

	
	
}
