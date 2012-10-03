package com.taifook.adminportal.model;

import java.io.Serializable;
import java.util.Date;

/**
 * $Id: OnLineUserInfo.java,v 1.2 2010/11/09 04:31:52 kyzou Exp $
 * @Project:admin-portal
 * @File:OnLineUserInfo.java
 * @Description:
 * @Author:kyzou
 * @Date:2008-6-24
 */
public class OnLineUserInfo implements Serializable {
	private String loginId;
	private String channelCode;
	private String ip;
	private String sessionId;
	private Date logintime;
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getChannelCode() {
		return channelCode;
	}
	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public Date getLogintime() {
		return logintime;
	}
	public void setLogintime(Date logintime) {
		this.logintime = logintime;
	}

}
