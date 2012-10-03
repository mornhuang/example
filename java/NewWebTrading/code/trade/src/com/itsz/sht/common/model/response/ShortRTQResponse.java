package com.itsz.sht.common.model.response;

import java.util.List;

import com.itsz.sht.common.Consts;
import com.taifook.mcs.core.beans.msg.MCSMessage;

/**
 * $Id: ShortRTQResponse.java,v 1.1 2010/11/09 03:57:29 kyzou Exp $
 * @Project:portal
 * @File:ShortRTQResponse.java
 * @Description:
 * @Author:kyzou
 * @Date:2009-4-14
 */
public class ShortRTQResponse extends MCSMessage {
	private List<EnquireShortRTQResponse> enquireShortRTQResponse;

	public ShortRTQResponse(List<EnquireShortRTQResponse> enquireShortRTQResponse) {
		this.enquireShortRTQResponse = enquireShortRTQResponse;
		this.setMessageId(Consts.Mcs.MsgId.ShortRtq);
	}
	public ShortRTQResponse(){
		this.setMessageId(Consts.Mcs.MsgId.ShortRtq);
	}
	public List<EnquireShortRTQResponse> getEnquireShortRTQResponse() {
		return enquireShortRTQResponse;
	}

	public void setEnquireShortRTQResponse(List<EnquireShortRTQResponse> enquireShortRTQResponse) {
		this.enquireShortRTQResponse = enquireShortRTQResponse;
	}
}
