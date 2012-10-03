package com.itsz.sht.common.model.request;

/**
 * $Id: EFinetAppletRTQRequest.java,v 1.1 2010/11/09 03:57:27 kyzou Exp $
 * @Project:portal
 * @File:EFinetAppletRTQRequest.java
 * @Description:
 * @Author:kyzou
 * @Date:2007-11-23
 */
public class EFinetAppletRTQRequest {
	private String url;
	private String company;
	private String username;
	private String password;
	private String UD2;
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUD2() {
		return UD2;
	}
	public void setUD2(String ud2) {
		UD2 = ud2;
	}

}