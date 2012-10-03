package com.taifook.mtss.web.common.log;

/**
 * 
 * $Id: WebLogger.java,v 1.1 2010/11/09 03:57:47 kyzou Exp $
 * @Project:portal
 * @File:WebLogger.java
 * @Description:
 * @Author:Cimenon Saint
 * @Date:2007-11-5
 */
public interface WebLogger {

    public abstract void debug(Object obj);

    public abstract void debug(Object obj, Throwable throwable);

    public abstract void info(Object obj);

    public abstract void info(Object obj, Throwable throwable);

    public abstract void warn(Object obj);

    public abstract void warn(Object obj, Throwable throwable);

    public abstract void error(Object obj);

    public abstract void error(Object obj, Throwable throwable);

    public abstract void fatal(Object obj);

    public abstract void fatal(Object obj, Throwable throwable);
}
