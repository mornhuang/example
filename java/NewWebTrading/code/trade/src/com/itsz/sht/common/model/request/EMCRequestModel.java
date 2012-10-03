package com.itsz.sht.common.model.request;

import com.itsz.sht.common.model.AbstractRequestModel;

/**
 * $Id: EMCRequestModel.java,v 1.1 2011/01/26 03:52:56 kyzou Exp $
 * @Project:NewWebTrading
 * @File:EMCRequestModel.java
 * @Description:
 * @Author:kyzou
 * @Date:2011-1-26
 */
public class EMCRequestModel extends AbstractRequestModel {
	private String token;
	private String clientId;
	private String custCode;
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getCustCode() {
		return custCode;
	}
	public void setCustCode(String custCode) {
		this.custCode = custCode;
	}
	
}
