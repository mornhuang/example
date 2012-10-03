/*
 *  Copyright (c) 2004 Tai Fook Securities Group Limited.
 *  All rights reserved.
 *
 *  This file contains the valuable properties of Tai Fook Securities
 *  Group Limited, embodying substantial creative efforts and confidential
 *  information, ideas and expressions. No part of this file may be
 *  reproduced or distributed in any form or by any means, or stored
 *  in a data base or a retrieval system, without the prior written
 *  permission of Tai Fook Securities Group Limited.
 *
 */
package com.itsz.sht.dto;

import java.io.Serializable;
import java.util.Date;


public class ServiceParameter implements Serializable {
	
	private String paramName;
	private String paramValue;
	private String serviceID;
	private String dataType;
	private Date lastModifiedDate;
	private String updateToken;
	
	public String getParamName() {
		return this.paramName;
	}
	
	public void setParamName(String paramName) {
		this.paramName = paramName;
	}
	
	public String getParamValue() {
		return this.paramValue;
	}
	
	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}
	
	public String getServiceID() {
		return this.serviceID;
	}
	
	public void setServiceID(String serviceID) {
		this.serviceID = serviceID;
	}

	public String getDataType() {
		return this.dataType;
	}
	
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	
	public Date getLastModifiedDate() {
		return this.lastModifiedDate;
	}
	
	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}
	
	public String getUpdateToken() {
		return this.updateToken;
	}
	
	public void setUpdateToken(String updateToken) {
		this.updateToken = updateToken;
	}
	
}
