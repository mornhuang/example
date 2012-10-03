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

import java.io.Serializable;


/**
 * 
 * $Id: ParamPK.java,v 1.1 2010/11/12 04:26:51 kyzou Exp $
 * @Project:portal-branch-41
 * @File:ParamPK.java
 * @Description:
 * @Author:Cimenon Saint
 * @Date:2007-10-22
 */
public class ParamPK implements Serializable {
	
    private static final long serialVersionUID = 2934596591651823454L;
    private String paramName;
	private String serviceID;

	public String getParamName() {
		return this.paramName;
	}
	
	public void setParamName(String paramName) {
		this.paramName = paramName;
	}

	public String getServiceID() {
		return this.serviceID;
	}
	
	public void setServiceID(String serviceID) {
		this.serviceID = serviceID;
	}

	public boolean equals(Object arg0) { 
		if ( !(arg0 instanceof ParamPK) ) return false; 
	      return (( (ParamPK) arg0 ).getParamName().equals(paramName) && ( (ParamPK) arg0 ).getServiceID().equals(serviceID)); 
	} 
	
	public int hashCode() { 
		return super.hashCode(); 
	} 
}
