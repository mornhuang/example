package com.itsz.sht.exception;


/**
 * $Id: ECertAuthenticationException.java,v 1.1 2010/11/09 03:57:30 kyzou Exp $
 * @Project:portal
 * @File:ECertAuthenticationException.java
 * @Description:
 * @Author:
 * @Date:2007-6-20
 */
public class ECertAuthenticationException extends WebChannelException {

    private static final long serialVersionUID = -7565835567692233798L;

    public ECertAuthenticationException() {
        super();
    }

    public ECertAuthenticationException(String message) {
        super(message);
    }

    public ECertAuthenticationException(String message, Throwable enclosedException) {
        super(message, enclosedException);
    }

    public ECertAuthenticationException(Throwable enclosedException) {
        super(enclosedException);
    }

    public ECertAuthenticationException(String message, String messageKey, Object[] args ) {
        super(message);
        this.setMessageKey( messageKey );
        this.setArgs( args );
    }
}
