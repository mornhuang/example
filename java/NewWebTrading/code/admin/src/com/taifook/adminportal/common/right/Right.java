package com.taifook.adminportal.common.right;

public class Right {
	private String name;
	private String action;
	private String roles;
	
	public Right(){
		
	}
	
	public Right(String name, String action, String roles){
		this.name=name;
		this.action=action;
		this.roles=roles;
	}
	
	public void setName(String name){
		this.name=name;
	}
	
	public String getName(){
		return this.name;
	}
	
	public void setAction(String action){
		this.action=action;
	}
	
	public String getAction(){
		return this.action;
	}
	
	public void setRoles(String roles){
		this.roles=roles;
	}
	public String getRoles(){
		return this.roles;
	}
	
	public String toString(){
		return ("name: "+name+" >>>action: "+action+" >>>roles: "+roles);
	}

}
