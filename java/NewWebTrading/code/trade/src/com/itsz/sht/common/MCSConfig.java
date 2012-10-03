package com.itsz.sht.common;

import java.io.Serializable;

/**
 * 
 * $Id: MCSConfig.java,v 1.1 2010/11/09 03:57:26 kyzou Exp $
 * @Project:portal
 * @File:MCSConfig.java
 * @Description:
 * @Author:
 * @Date:2008-4-15
 */
public class MCSConfig implements Serializable {

	private static final long serialVersionUID = 2900726898567317064L;
	private String hostname;
    private boolean isMCSCheckingEnabled;

    public MCSConfig()
    {
        isMCSCheckingEnabled = false;
    }

    public String getHostname()
    {
        return hostname;
    }

    public void setHostname(String hostname)
    {
        this.hostname = hostname;
    }

    public boolean IsMCSCheckingEnabled()
    {
        return isMCSCheckingEnabled;
    }

    public void setIsMCSCheckingEnabled(boolean isMCSCheckingEnabled)
    {
        this.isMCSCheckingEnabled = isMCSCheckingEnabled;
    }
}
