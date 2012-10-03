package com.itsz.sht.struts.form;

/**
 * $Id: ListSelectRTQForm.java,v 1.2 2010/12/29 03:49:05 kyzou Exp $
 * @Project:NewWebTrading
 * @File:ListSelectRTQForm.java
 * @Description:
 * @Author:kyzou
 * @Date:2010-12-17
 */
public class ListSelectRTQForm extends ITSZForm {
	private static final long serialVersionUID = 1L;
	private String clientId;
	private String method;

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
}
