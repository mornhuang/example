package com.itsz.sht.common.model.common;

/**
 * $Id: ECert.java,v 1.1 2010/11/09 03:57:26 kyzou Exp $
 * @Project:portal
 * @File:ECert.java
 * @Description:
 * @Author:
 * @Date:2007-6-20
 */
import java.io.Serializable;


public class ECert implements Serializable {

    private static final long serialVersionUID = 7091659878267796175L;
    private String issuer;
	private String serial;
	private String cn;
	private boolean isExpiredOrRevoked;
	
	public String getIssuer() {
		return this.issuer;
	}
	
	public void setIssuer(String issuer) {
		this.issuer = issuer;
	}
	
	public String getSerial() {
		return this.serial;
	}
	
	public void setSerial(String serial) {
		this.serial = serial;
	}
	
	public String getCn() {
		return this.cn;
	}
	
	public void setCn(String cn) {
		this.cn = cn;
	}
	
	public boolean IsExpiredOrRevoked() {
		return this.isExpiredOrRevoked;
	}
	
	public void setIsExpiredOrRevoked(boolean isExpiredOrRevoked) {
		this.isExpiredOrRevoked = isExpiredOrRevoked;
	}
}
