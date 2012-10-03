package com.itsz.sht.common.model.request;

/**
 * $Id: TaifookAppletRTQRequest.java,v 1.1 2010/11/09 03:57:28 kyzou Exp $
 * @Project:portal
 * @File:TaifookAppletRTQRequest.java
 * @Description:
 * @Author:kyzou
 * @Date:2007-11-23
 */
public class TaifookAppletRTQRequest {
	private String tfpath;
	private String lang;
	private String session;
	private String link;
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getTfpath() {
		return tfpath;
	}
	public void setTfpath(String tfpath) {
		this.tfpath = tfpath;
	}
	public String getLang() {
		return lang;
	}
	public void setLang(String lang) {
		this.lang = lang;
	}
	public String getSession() {
		return session;
	}
	public void setSession(String session) {
		this.session = session;
	}

}