package com.itsz.sht.common.model.request;

import java.io.Serializable;

/**
 * $Id: SapRequestModel.java,v 1.1 2010/11/09 03:57:27 kyzou Exp $
 * @Project:portal
 * @File:SaPRequestModel.java
 * @Description:
 * @Author:kyzou
 * @Date:2007-11-6
 */
public class SapRequestModel implements Serializable {

	private static final long serialVersionUID = 4280267832218489115L;
	private String system;
	private String loginID;
	private String custCode;
	private String lang;
	
	public String getLoginID() {
		return loginID;
	}
	public String getCustCode() {
		return custCode;
	}
	public void setCustCode(String custCode) {
		this.custCode = custCode;
	}
	public String getLang() {
		return lang;
	}
	public void setLang(String lang) {
		this.lang = lang;
	}
	public String getSystem() {
		return system;
	}
	public void setSystem(String system) {
		this.system = system;
	}
	public void setLoginID(String loginID) {
		this.loginID = loginID;
	}

}
