package com.taifook.mtss.web.common.log;

import java.net.URL;

import org.apache.commons.logging.LogFactory;
import org.apache.log4j.xml.DOMConfigurator;

/**
 * 
 * $Id: WebLoggerImpl.java,v 1.1 2010/11/09 03:57:47 kyzou Exp $
 * @Project:portal
 * @File:WebLoggerImpl.java
 * @Description:
 * @Author:Cimenon Saint
 * @Date:2007-11-5
 */
public final class WebLoggerImpl implements WebLogger {

    private String cat;

    public WebLoggerImpl()
    {
        cat = null;
        URL url = getClass().getClassLoader().getResource("log4j.xml");
        if(url != null)
            DOMConfigurator.configureAndWatch(url.getPath());
    }

    public WebLoggerImpl(String category)
    {
        this();
        cat = category;
    }

    public WebLoggerImpl(Class className)
    {
        this();
        cat = className.getName();
    }

    public WebLoggerImpl(Object obj)
    {
        this();
        cat = obj.getClass().getName();
    }

    public void debug(Object message)
    {
        if(LogFactory.getLog(cat).isDebugEnabled())
            LogFactory.getLog(cat).debug(message);
    }

    public void debug(Object message, Throwable throwable)
    {
        if(LogFactory.getLog(cat).isDebugEnabled())
            LogFactory.getLog(cat).debug(message, throwable);
    }

    public void info(Object message)
    {
        if(LogFactory.getLog(cat).isInfoEnabled())
            LogFactory.getLog(cat).info(message);
    }

    public void info(Object message, Throwable throwable)
    {
        if(LogFactory.getLog(cat).isInfoEnabled())
            LogFactory.getLog(cat).info(message, throwable);
    }

    public void warn(Object message)
    {
        if(LogFactory.getLog(cat).isWarnEnabled())
            LogFactory.getLog(cat).warn(message);
    }

    public void warn(Object message, Throwable throwable)
    {
        if(LogFactory.getLog(cat).isWarnEnabled())
            LogFactory.getLog(cat).warn(message, throwable);
    }

    public void error(Object message)
    {
        if(LogFactory.getLog(cat).isErrorEnabled())
            LogFactory.getLog(cat).error(message);
    }

    public void error(Object message, Throwable throwable)
    {
        if(LogFactory.getLog(cat).isErrorEnabled())
            LogFactory.getLog(cat).error(message, throwable);
    }

    public void fatal(Object message)
    {
        if(LogFactory.getLog(cat).isFatalEnabled())
            LogFactory.getLog(cat).fatal(message);
    }

    public void fatal(Object message, Throwable throwable)
    {
        if(LogFactory.getLog(cat).isFatalEnabled())
            LogFactory.getLog(cat).fatal(message, throwable);
    }
}
