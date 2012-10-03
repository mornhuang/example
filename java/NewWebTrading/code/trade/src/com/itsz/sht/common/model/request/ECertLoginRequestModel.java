package com.itsz.sht.common.model.request;

import com.itsz.sht.common.model.AbstractRequestModel;

/**
 * $Id: ECertLoginRequestModel.java,v 1.1 2010/11/09 03:57:27 kyzou Exp $
 * @Project:portal.head
 * @File:ECertLoginRequestModel.java
 * @Description:
 * @Author:Cimenon Saint
 * @Date:2007-5-21
 */
public class ECertLoginRequestModel extends AbstractRequestModel {
	
	private String ecertHeader;
	private String remoteAddr;
	
	public String getRemoteAddr() {
		return remoteAddr;
	}
	public void setRemoteAddr(String remoteAddr) {
		this.remoteAddr = remoteAddr;
	}
	public String getEcertHeader() {
		return ecertHeader;
	}
	public void setEcertHeader(String ecertHeader) {
		this.ecertHeader = ecertHeader;
	}
}
