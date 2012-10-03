package com.itsz.sht.exception;

import org.apache.struts.action.ActionError;

/**
 * 
 * $Id: WebActionError.java,v 1.1 2010/11/09 03:57:30 kyzou Exp $
 * @Project:portal
 * @File:WebActionError.java
 * @Description:
 * @Author:Cimenon Saint
 * @Date:2007-7-2
 */
public final class WebActionError  extends ActionError {

    private static final long serialVersionUID = -5006971086419370409L;

    /** Catched Application exception object reference */
    private Exception exception = null;

    /** Error code of associated exception */
    private String errorCode = null;

    /** Session based error reference no. */
    private String referenceNo = null;

    /** Instructing whetther the error code should be printed out. */
    private boolean isErrorCodeDisplayed = false;

    /** Instructing whetther the reference no. should be printed out. */
    private boolean isReferenceNoDisplayed = false;

    public WebActionError(String key) {
        super(key);
    }

    public WebActionError(String key, Object[] values) {
        super(key, values);
    }

    public void setException(Exception obj) {
        this.exception = obj;
    }

    public Exception getException() {
        return this.exception;
    }

    public String geErrorCode() {
        return errorCode;
    }

    public boolean isIsErrorCodeDisplayed() {
        return this.isErrorCodeDisplayed;
    }

    public void setIsErrorCodeDisplayed(boolean isErrorCodeDisplayed) {
        this.isErrorCodeDisplayed = isErrorCodeDisplayed;
    }

    public String getReferenceNo() {
        return this.referenceNo;
    }

    public void setReferenceNo(String referenceNo) {
        this.referenceNo = referenceNo;
    }

    public boolean isIsReferenceNoDisplayed() {
        return this.isReferenceNoDisplayed;
    }

    public void setIsReferenceNoDisplayed(boolean isReferenceNoDisplayed) {
        this.isReferenceNoDisplayed = isReferenceNoDisplayed;
    }

    public String getErrorCode() {
        return this.errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

}