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

public class StatusRemarks {

	private StatusRemarksPK id;
	private String remarks;
	private long updateSeqNum;
	private Date lastModifiedDate;
	private String updateToken;
	
	public StatusRemarksPK getId() {
		return this.id;
	}
	
	public void setId(StatusRemarksPK id) {
		this.id = id;
	}
		
	public String getRemarks() {
		return this.remarks;
	}
	
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	public long getUpdateSeqNum() {
		return this.updateSeqNum;
	}
	
	public void setUpdateSeqNum(long updateSeqNum) {
		this.updateSeqNum = updateSeqNum;
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
