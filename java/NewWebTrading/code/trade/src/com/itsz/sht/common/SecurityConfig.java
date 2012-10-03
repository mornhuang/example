package com.itsz.sht.common;

import java.io.Serializable;

/**
 * 
 * $Id: SecurityConfig.java,v 1.1 2010/11/09 03:57:26 kyzou Exp $
 * @Project:portal
 * @File:SecurityConfig.java
 * @Description:
 * @Author:
 * @Date:2008-4-15
 */
public class SecurityConfig implements Serializable {

	private static final long serialVersionUID = 3366013303110231862L;
	private boolean isDoubleLogonOnEnabled;

    public SecurityConfig()
    {
        isDoubleLogonOnEnabled = false;
    }

    public boolean isIsDoubleLogonOnEnabled()
    {
        return isDoubleLogonOnEnabled;
    }

    public void setIsDoubleLogonOnEnabled(boolean isDoubleLogonOnEnabled)
    {
        this.isDoubleLogonOnEnabled = isDoubleLogonOnEnabled;
    }
}
