package com.itsz.sht.exception;


/**
 * 
 * $Id: ServiceStatusEnquiryException.java,v 1.1 2010/11/09 03:57:30 kyzou Exp $
 * @Project:portal
 * @File:ServiceStatusEnquiryException.java
 * @Description:
 * @Author:Cimenon Saint
 * @Date:2007-7-2
 */
public class ServiceStatusEnquiryException extends WebChannelException {

    private static final long serialVersionUID = 2695531127920615802L;

    public ServiceStatusEnquiryException() {
        super();
    }

    public ServiceStatusEnquiryException(String message) {
        super(message);
    }

    public ServiceStatusEnquiryException(String message, Throwable enclosedException) {
        super(message, enclosedException);
    }

    public ServiceStatusEnquiryException(Throwable enclosedException) {
        super(enclosedException);
    }

    public ServiceStatusEnquiryException(
        String message,
        String messageKey,
        Object[] args) {
        super(message);
        this.setMessageKey(messageKey);
        this.setArgs(args);
    }
}