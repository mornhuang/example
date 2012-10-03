package com.itsz.sht.common.model.request;

import java.util.Locale;

/**
 * $Id: SnapshotRTQRequestModel.java,v 1.1 2010/11/09 03:57:27 kyzou Exp $
 * @Project:portal.head
 * @File:SnapshotRTQRequestModel.java
 * @Description:
 * @Author:Cimenon Saint
 * @Date:2007-5-21
 */
public class SnapshotRTQRequestModel {

	private String custCode;
	private Locale locale;
	
	public String getCustCode() {
		return custCode;
	}
	public void setCustCode(String custCode) {
		this.custCode = custCode;
	}
	public Locale getLocale() {
		return locale;
	}
	public void setLocale(Locale locale) {
		this.locale = locale;
	}

}
