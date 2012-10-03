package com.itsz.sht.common.model.request;

import com.itsz.sht.common.model.AbstractRequestModel;

/**
 * $Id: StreamRtqProfileRequestModel.java,v 1.1 2010/11/09 03:57:27 kyzou Exp $
 * @Project:portal_b61
 * @File:StreamRtqProfileRequestModel.java
 * @Description:
 * @Author:kyzou
 * @Date:2009-5-5
 */
public class StreamRtqProfileRequestModel extends AbstractRequestModel {
	private String categoryCode;
	private String custCode;
	private String remoteAddr;
	
	public String getRemoteAddr() {
		return remoteAddr;
	}

	public void setRemoteAddr(String remoteAddr) {
		this.remoteAddr = remoteAddr;
	}

	public String getCustCode() {
		return custCode;
	}

	public void setCustCode(String custCode) {
		this.custCode = custCode;
	}

	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}
}
