// Decompiled by Jad v1.5.7g. Copyright 2000 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/SiliconValley/Bridge/8617/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 
// Source File Name:   ECertConfig.java

package com.itsz.sht.common;

import java.io.Serializable;

public class ECertConfig
    implements Serializable
{

    private String hostname;
    private String port;
    private String appUrl;

    public ECertConfig()
    {
    }

    public String getHostname()
    {
        return hostname;
    }

    public void setHostname(String hostname)
    {
        this.hostname = hostname;
    }

    public String getPort()
    {
        return port;
    }

    public void setPort(String port)
    {
        this.port = port;
    }

    public String getAppUrl()
    {
        return appUrl;
    }

    public void setAppUrl(String appUrl)
    {
        this.appUrl = appUrl;
    }
}
