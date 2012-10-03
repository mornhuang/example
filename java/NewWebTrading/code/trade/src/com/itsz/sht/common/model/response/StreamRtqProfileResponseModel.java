package com.itsz.sht.common.model.response;

import com.itsz.sht.common.model.AbstractResponseModel;
import com.taifook.mcs.core.beans.msg.StreamRtqProfileResponse;

/**
 * $Id: StreamRtqProfileResponseModel.java,v 1.1 2010/11/09 03:57:29 kyzou Exp $
 * @Project:portal_b61
 * @File:StreamRtqProfileResponseModel.java
 * @Description:
 * @Author:kyzou
 * @Date:2009-5-5
 */
public class StreamRtqProfileResponseModel extends AbstractResponseModel {
	private StreamRtqProfileResponse streamRtqProfileResponse;

	public StreamRtqProfileResponse getStreamRtqProfileResponse() {
		return streamRtqProfileResponse;
	}

	public void setStreamRtqProfileResponse(
			StreamRtqProfileResponse streamRtqProfileResponse) {
		this.streamRtqProfileResponse = streamRtqProfileResponse;
	}
}
