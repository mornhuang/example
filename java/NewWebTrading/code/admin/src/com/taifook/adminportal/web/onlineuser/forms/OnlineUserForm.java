package com.taifook.adminportal.web.onlineuser.forms;

import org.apache.struts.action.ActionForm;

public class OnlineUserForm extends ActionForm {
	private String userid;

	private String channelcode;

	private String sessionid;

	private String logintime;

	public String getChannelcode() {
		return channelcode;
	}

	public void setChannelcode(String channelcode) {
		this.channelcode = channelcode;
	}

	public String getLogintime() {
		return logintime;
	}

	public void setLogintime(String logintime) {
		this.logintime = logintime;
	}

	public String getSessionid() {
		return sessionid;
	}

	public void setSessionid(String sessionid) {
		this.sessionid = sessionid;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

}
