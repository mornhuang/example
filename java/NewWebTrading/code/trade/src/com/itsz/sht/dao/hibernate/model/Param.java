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
package com.itsz.sht.dao.hibernate.model;

import java.util.Date;

public class Param {
	
	private ParamPK id;
	private String paramValue;
	private String dataType;
	private Date lastModifiedDate;
	private long updateSeqNum;
	private String updateToken;
	
	
	public ParamPK getId() {
		return this.id;
	}
	
	public void setId(ParamPK id) {
		this.id = id;
	}
	
	public String getParamValue() {
		return this.paramValue;
	}
	
	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
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
	
	public long getUpdateSeqNum() {
		return this.updateSeqNum;
	}
	
	public void setUpdateSeqNum(long updateSeqNum) {
		this.updateSeqNum = updateSeqNum;
	}
	
	public String getUpdateToken() {
		return this.updateToken;
	}
	
	public void setUpdateToken(String updateToken) {
		this.updateToken = updateToken;
	}
}
