package com.itsz.sht.common.model.request;

/**
 * $Id: QS2AppletRTQRequest.java,v 1.1 2010/11/09 03:57:27 kyzou Exp $
 * @Project:portal
 * @File:QS2AppletRTQRequest.java
 * @Description:
 * @Author:kyzou
 * @Date:2007-11-23
 */
public class QS2AppletRTQRequest {
	private String uid;
	private String lang;
	private String token;
	private String eKey;
	private String url;
	private String link;
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getLang() {
		return lang;
	}
	public void setLang(String lang) {
		this.lang = lang;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getEKey() {
		return eKey;
	}
	public void setEKey(String key) {
		eKey = key;
	}

}