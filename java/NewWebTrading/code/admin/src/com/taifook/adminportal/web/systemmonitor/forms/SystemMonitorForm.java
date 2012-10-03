package com.taifook.adminportal.web.systemmonitor.forms;

import org.apache.struts.action.ActionForm;

public class SystemMonitorForm extends ActionForm{
	
	private java.lang.String servicename;

	private java.lang.String status;

	private java.lang.String actionId;

	private java.lang.String ip;

	private java.lang.String accesstime;

	private java.lang.String expendtime;

	public java.lang.String getAccesstime() {
		return accesstime;
	}

	public void setAccesstime(java.lang.String accesstime) {
		this.accesstime = accesstime;
	}

	public java.lang.String getActionId() {
		return actionId;
	}

	public void setActionId(java.lang.String actionId) {
		this.actionId = actionId;
	}

	public java.lang.String getExpendtime() {
		return expendtime;
	}

	public void setExpendtime(java.lang.String expendtime) {
		this.expendtime = expendtime;
	}

	public java.lang.String getIp() {
		return ip;
	}

	public void setIp(java.lang.String ip) {
		this.ip = ip;
	}

	public java.lang.String getServicename() {
		return servicename;
	}

	public void setServicename(java.lang.String servicename) {
		this.servicename = servicename;
	}

	public java.lang.String getStatus() {
		return status;
	}

	public void setStatus(java.lang.String status) {
		this.status = status;
	}


}
