package com.taifook.adminportal.web.security.forms;

import org.apache.struts.action.ActionForm;

public class ChangePwdForm extends ActionForm{
	private String userId;
	private String oldPwd;
	private String newPwd;
	
	public void setUserId(String userId){
		this.userId=userId;
	}
	
	public String getUserId(){
		return this.userId;
	}
	
	public void setOldPwd(String oldPwd){
		this.oldPwd=oldPwd;
	}

	public String getOldPwd(){
		return this.oldPwd;
	}
	
	public void setNewPwd(String newPwd){
		this.newPwd=newPwd;
	}
	
	public String getNewPwd(){
		return this.newPwd;
	}
}
