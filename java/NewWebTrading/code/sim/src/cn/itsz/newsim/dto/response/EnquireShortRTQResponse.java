package cn.itsz.newsim.dto.response;

import java.util.List;

import cn.itsz.newsim.dto.ResultMessage;
import cn.itsz.newsim.dto.response.entity.EnquireShortRTQResponseEntity;

public class EnquireShortRTQResponse extends ResultMessage {
	private List<EnquireShortRTQResponseEntity> enquireShortRTQResponse;

	public List<EnquireShortRTQResponseEntity> getEnquireShortRTQResponse() {
		return enquireShortRTQResponse;
	}

	public void setEnquireShortRTQResponse(List<EnquireShortRTQResponseEntity> enquireShortRTQResponse) {
		this.enquireShortRTQResponse = enquireShortRTQResponse;
	}
}
