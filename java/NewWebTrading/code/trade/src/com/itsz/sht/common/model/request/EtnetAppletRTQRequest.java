package com.itsz.sht.common.model.request;

/**
 * $Id: EtnetAppletRTQRequest.java,v 1.1 2010/11/09 03:57:27 kyzou Exp $
 * @Project:portal
 * @File:EtnetAppletRTQRequest.java
 * @Description:
 * @Author:kyzou
 * @Date:2007-11-23
 */
public class EtnetAppletRTQRequest {
	private String url;
	private String uid;
	private String theme;
	private String lang;
	private String request;
	private String passport;
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getTheme() {
		return theme;
	}
	public void setTheme(String theme) {
		this.theme = theme;
	}
	public String getLang() {
		return lang;
	}
	public void setLang(String lang) {
		this.lang = lang;
	}
	public String getRequest() {
		return request;
	}
	public void setRequest(String request) {
		this.request = request;
	}
	public String getPassport() {
		return passport;
	}
	public void setPassport(String passport) {
		this.passport = passport;
	}

}