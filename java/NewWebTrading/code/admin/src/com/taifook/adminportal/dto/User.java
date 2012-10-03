package com.taifook.adminportal.dto;

import com.taifook.adminportal.common.right.RightsManager;

public class User {

	/**
	 * @param args
	 */

	private String userid;

	private String password;

	private String powerStr;

	private RightsManager rightsManager;

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUserid() {
		return this.userid;
	}

	private void setPaswsword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPowerStr(String powerStr) {
		this.powerStr = powerStr;
	}

	public String getPowerStr() {
		return this.powerStr;
	}

	public RightsManager getRightsManager() {
		return this.rightsManager;
	}

	public void setRightsManager(RightsManager rightsManager) {
		this.rightsManager = rightsManager;
	}
}
