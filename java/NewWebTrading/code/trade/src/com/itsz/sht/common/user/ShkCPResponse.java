package com.itsz.sht.common.user;

/**
 * $Id: ShkCPResponse.java,v 1.1 2011/03/03 07:29:15 xli Exp $
 * @Project:portal_b41
 * @File:ShkCPResponse.java
 * @Description:
 * @Author:kyzou
 * @Date:2008-9-5
 */
public class ShkCPResponse {
	private String url;
	private String security;
	private String language;
	private String stockcode;
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getSecurity() {
		return security;
	}
	public void setSecurity(String security) {
		this.security = security;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getStockcode() {
		return stockcode;
	}
	public void setStockcode(String stockcode) {
		this.stockcode = stockcode;
	}
}
