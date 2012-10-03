package com.taifook.common.socket;

import java.io.Serializable;

public class SocketMessage implements java.io.Serializable {
	
	private String notifyObject;
	
	private String action;

	public Serializable context;
	
	public SocketExecute callBack;

	public SocketMessage() {

	}
	
	public SocketMessage(String notifyObject, String action, Serializable context){
		this.notifyObject=notifyObject;
		this.action = action;
		this.context = context;
	}

	public SocketMessage(String notifyObject, String action, Serializable context,SocketExecute callBack) {
		this.notifyObject=notifyObject;
		this.action = action;
		this.context = context;
		this.callBack=callBack;
	}
	
	public void setNotifyObject(String notifyObject){
		this.notifyObject=notifyObject;
	}
	
	public String getNotifyObject(){
		return this.notifyObject;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getAction() {
		return this.action;
	}

	public void setContext(Serializable context) {
		this.context = context;
	}

	public Serializable getContext() {
		return this.context;
	}

	public SocketExecute getCallBack() {
		return callBack;
	}

	public void setCallBack(SocketExecute callBack) {
		this.callBack = callBack;
	}
}
