package com.itsz.sht.dao.hibernate.model;

import java.io.Serializable;

/**
 * 
 * $Id: StatusRemarksPK.java,v 1.1 2010/11/12 04:26:51 kyzou Exp $
 * @Project:portal
 * @File:StatusRemarksPK.java
 * @Description:
 * @Author:Cimenon Saint
 * @Date:2007-7-2
 */
public class StatusRemarksPK implements Serializable {
	
    private static final long serialVersionUID = 6476534768882317349L;
    private String serviceID;
	private String status;
	private String lang;

	public String getServiceID() {
		return this.serviceID;
	}
	
	public void setServiceID(String serviceID) {
		this.serviceID = serviceID;
	}
	
	public String getStatus() {
		return this.status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}

	public String getLang() {
		return this.lang;
	}
	
	public void setLang(String lang) {
		this.lang = lang;
	}
	

	public boolean equals(Object arg0) { 
		if ( !(arg0 instanceof StatusRemarksPK) ) return false; 
	      return (( (StatusRemarksPK) arg0 ).getServiceID().equals(serviceID) 
	      		&& ( (StatusRemarksPK) arg0 ).getStatus().equals(status)
				&& ( (StatusRemarksPK) arg0 ).getLang().equals(lang)); 
	} 
	
	public int hashCode() { 
		return super.hashCode(); 
	} 
}
