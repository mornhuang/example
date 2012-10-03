package com.itsz.sht.struts.form;


/**
 * $Id: LoginForm.java,v 1.2 2011/03/03 10:36:26 pbxie Exp $
 * @Project:portal.head
 * @File:LoginForm.java
 * @Description:
 * @Author:Cimenon Saint
 * @Date:2007-5-21
 */
public class LoginForm extends ITSZForm {

	private static final long serialVersionUID = -4833705636966191932L;
	private String loginId;
	private String password;
	private String checkVersion;
	private String rtqFlag;
	private String loginInfoFormat;
	

	public String getRtqFlag() {
		return rtqFlag;
	}
	public void setRtqFlag(String rtqFlag) {
		this.rtqFlag = rtqFlag;
	}
	public String getLoginInfoFormat() {
		return loginInfoFormat;
	}
	public void setLoginInfoFormat(String loginInfoFormat) {
		this.loginInfoFormat = loginInfoFormat;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}