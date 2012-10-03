// Decompiled by Jad v1.5.7g. Copyright 2000 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/SiliconValley/Bridge/8617/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 
// Source File Name:   ErrorConfig.java

package com.itsz.sht.common;

import java.io.Serializable;

public class ErrorConfig
    implements Serializable
{

    protected String exception;
    protected String errorCode;
    protected boolean isErrorCodeDisplayed;
    protected boolean isErrorRefDisplayed;

    public ErrorConfig()
    {
        exception = null;
        errorCode = null;
        isErrorCodeDisplayed = false;
        isErrorRefDisplayed = false;
    }

    public ErrorConfig(String errorCode, String exception, boolean isErrorCodeDisplayed, boolean isErrorRefDisplayed)
    {
        this.exception = null;
        this.errorCode = null;
        this.isErrorCodeDisplayed = false;
        this.isErrorRefDisplayed = false;
        this.errorCode = errorCode;
        this.exception = exception;
        this.isErrorCodeDisplayed = isErrorCodeDisplayed;
        this.isErrorRefDisplayed = isErrorRefDisplayed;
    }

    public String getException()
    {
        return exception;
    }

    public void setException(String exception)
    {
        this.exception = exception;
    }

    public String getErrorCode()
    {
        return errorCode;
    }

    public void setErrorCode(String errorCode)
    {
        this.errorCode = errorCode;
    }

    public boolean isIsErrorCodeDisplayed()
    {
        return isErrorCodeDisplayed;
    }

    public void setIsErrorCodeDisplayed(boolean isErrorCodeDisplayed)
    {
        this.isErrorCodeDisplayed = isErrorCodeDisplayed;
    }

    public boolean isIsErrorRefDisplayed()
    {
        return isErrorRefDisplayed;
    }

    public void setIsErrorRefDisplayed(boolean isErrorRefDisplayed)
    {
        this.isErrorRefDisplayed = isErrorRefDisplayed;
    }
}
