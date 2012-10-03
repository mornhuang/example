package com.taifook.adminportal.web.useraction.forms;

import org.apache.struts.action.ActionForm;

public class UserActionForm extends ActionForm {

	private java.lang.String userid;

	private java.lang.String actionid;

	 private java.lang.String channelType;
	
	private java.lang.String ip;

	private java.lang.String operationtime;
	

	public java.lang.String getActionid() {
		return actionid;
	}

	public void setActionid(java.lang.String actionid) {
		this.actionid = actionid;
	}

	public java.lang.String getIp() {
		return ip;
	}

	public void setIp(java.lang.String ip) {
		this.ip = ip;
	}

	public java.lang.String getOperationtime() {
		return operationtime;
	}

	public void setOperationtime(java.lang.String operationtime) {
		this.operationtime = operationtime;
	}

	public java.lang.String getUserid() {
		return userid;
	}

	public void setUserid(java.lang.String userid) {
		this.userid = userid;
	}

	public java.lang.String getChannelType() {
		return channelType;
	}

	public void setChannelType(java.lang.String channelType) {
		this.channelType = channelType;
	}


}
