package com.taifook.mtss.web.common.log;


/**
 * 
 * $Id: WebLoggerFactory.java,v 1.1 2010/11/09 03:57:47 kyzou Exp $
 * @Project:portal
 * @File:WebLoggerFactory.java
 * @Description:
 * @Author:Cimenon Saint
 * @Date:2007-11-5
 */
public class WebLoggerFactory {

    private static WebLoggerFactory instance = null;

    protected WebLoggerFactory() {
    }

    public static WebLoggerFactory instance() {
        if(instance == null) {
                if(instance == null)
                    instance = new WebLoggerFactory();
        }
        return instance;
    }

    public final WebLogger getLogger()
    {
        return new WebLoggerImpl();
    }

    public final WebLogger getLogger(String cat)
    {
        return new WebLoggerImpl(cat);
    }

    public final WebLogger getLogger(Object obj)
    {
        return new WebLoggerImpl(obj);
    }

    public final WebLogger getLogger(Class className)
    {
        return new WebLoggerImpl(className);
    }

}
