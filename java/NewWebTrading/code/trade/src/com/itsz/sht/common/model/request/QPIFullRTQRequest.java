package com.itsz.sht.common.model.request;

/**
 * $Id: QPIFullRTQRequest.java,v 1.1 2010/11/09 03:57:27 kyzou Exp $
 * @Project:portal
 * @File:QPIFullRTQRequest.java
 * @Description:
 * @Author:kyzou
 * @Date:2007-11-23
 */
public class QPIFullRTQRequest {
	private String uid;
	private String pwd;
	private String broker;
	private String lang;
	private String url;
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getBroker() {
		return broker;
	}
	public void setBroker(String broker) {
		this.broker = broker;
	}
	public String getLang() {
		return lang;
	}
	public void setLang(String lang) {
		this.lang = lang;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}

}