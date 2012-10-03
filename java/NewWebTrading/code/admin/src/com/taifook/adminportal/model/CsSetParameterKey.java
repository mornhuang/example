package com.taifook.adminportal.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * $Id: CsSetParameterKey.java,v 1.2 2010/11/09 04:31:52 kyzou Exp $
 * @Project:admin-portal
 * @File:CsSetParameterKey.java
 * @Description:
 * @Author:kyzou
 * @Date:2008-8-26
 */
public class CsSetParameterKey implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private volatile int hashValue = 0;

	private java.lang.String paramName;
	
	private java.lang.String paramValue;

	public CsSetParameterKey() {
	}
	
	public CsSetParameterKey(String paramName,String paramValue) {
		this.paramName=paramName;
		this.paramValue = paramValue;
	}

	public java.lang.String getParamName() {
		return paramName;
	}

	public void setParamName(java.lang.String paramName) {
		hashValue = 0;
		this.paramName = paramName;
	}

	public java.lang.String getParamValue() {
		return paramValue;
	}

	public void setParamValue(java.lang.String paramValue) {
		hashValue = 0;
		this.paramValue = paramValue;
	}
	
	public boolean equals(Object rhs) {
		if (rhs == this) {
			return true;
		}

		if (!(rhs instanceof CsSetParameterKey)) {
			return false;
		}

		CsSetParameterKey pk = (CsSetParameterKey) rhs;

		return new EqualsBuilder()
					.appendSuper(super.equals(rhs))
					.append(this.paramName, pk.getParamName())
					.append(this.paramValue, pk.getParamValue())
					.isEquals();
	}
	
	public int hashCode() {
		this.hashValue = new HashCodeBuilder(17, 37)
			.appendSuper(super.hashCode())
			.append(this.paramName)
			.append(this.paramValue)
			.toHashCode();
		
		return this.hashValue;
	}
}
