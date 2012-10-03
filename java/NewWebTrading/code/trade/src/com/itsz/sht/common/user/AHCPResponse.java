package com.itsz.sht.common.user;

import org.apache.commons.lang.StringUtils;

/**
 * $Id: AHCPResponse.java,v 1.1 2011/03/03 07:29:15 xli Exp $
 * @Project:portal
 * @File:AHCPResponse.java
 * @Description:
 * @Author:kyzou
 * @Date:2008-12-30
 */
public class AHCPResponse {
	private String url;
	private String loginId;
	private String pwd;
	private String language;
	
	public AHCPResponse(){
		
	}
	
	public AHCPResponse(String url,String loginId,String pwd,String language){
		this.url = url;
		this.loginId = loginId;
		this.pwd = pwd;
		this.language = language;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	
	public boolean invalidParam(){
		if(StringUtils.isBlank(url)||StringUtils.isBlank(loginId)||StringUtils.isBlank(pwd)){
			return true;
		}
		return false;
	}
	
	public String toString(){
		return ("A+H Request: url="+url
		+";loginId="+loginId
		+";pwd="+pwd
		+";language="+language);
	}
}
