package com.itsz.sht.common.model.request.login;

import com.itsz.sht.common.model.AbstractRequestModel;

/**
 * $Id: EcertModel.java,v 1.1 2010/11/09 03:57:28 kyzou Exp $
 * @Project:portal.head
 * @File:EcertModel.java
 * @Description:
 * @Author:Cimenon Saint
 * @Date:2007-6-6
 */
public class EcertModel extends AbstractRequestModel {

	private String ecertHost;
	private String ecertPort;
	private String ecertUrl;
	
	public String getEcertHost() {
		return ecertHost;
	}
	public void setEcertHost(String ecertHost) {
		this.ecertHost = ecertHost;
	}
	public String getEcertPort() {
		return ecertPort;
	}
	public void setEcertPort(String ecertPort) {
		this.ecertPort = ecertPort;
	}
	public String getEcertUrl() {
		return ecertUrl;
	}
	public void setEcertUrl(String ecertUrl) {
		this.ecertUrl = ecertUrl;
	}

}
