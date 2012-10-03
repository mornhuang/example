package com.itsz.sht.common.model.request;

import com.itsz.sht.common.model.AbstractRequestModel;

/**
 * $Id: LoginRequestModel.java,v 1.1 2010/11/09 03:57:27 kyzou Exp $
 * @Project:portal.head
 * @File:LoginRequestModel.java
 * @Description:
 * @Author:Cimenon Saint
 * @Date:2007-5-21
 */
public class LoginRequestModel extends AbstractRequestModel {
	
	private String loginId;
	private String password;
	private String checkVersion;
	private String loginType;
	private String messageSEQNum;
	private String loginInfoFormat;
	private String rtqFlag;
	private String remoteAddr;
	
	public String getRemoteAddr() {
		return remoteAddr;
	}
	public void setRemoteAddr(String remoteAddr) {
		this.remoteAddr = remoteAddr;
	}
	public String getCheckVersion() {
		return checkVersion;
	}
	public void setCheckVersion(String checkVersion) {
		this.checkVersion = checkVersion;
	}
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getLoginType() {
		return loginType;
	}
	public void setLoginType(String loginType) {
		this.loginType = loginType;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getLoginInfoFormat() {
		return loginInfoFormat;
	}
	public void setLoginInfoFormat(String loginInfoFormat) {
		this.loginInfoFormat = loginInfoFormat;
	}
	public String getMessageSEQNum() {
		return messageSEQNum;
	}
	public void setMessageSEQNum(String messageSEQNum) {
		this.messageSEQNum = messageSEQNum;
	}
	public String getRtqFlag() {
		return rtqFlag;
	}
	public void setRtqFlag(String rtqFlag) {
		this.rtqFlag = rtqFlag;
	}
}
