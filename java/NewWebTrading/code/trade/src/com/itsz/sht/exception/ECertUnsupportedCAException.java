package com.itsz.sht.exception;


/**
 * $Id: ECertUnsupportedCAException.java,v 1.1 2010/11/09 03:57:30 kyzou Exp $
 * @Project:portal
 * @File:ECertUnsupportedCAException.java
 * @Description:
 * @Author:
 * @Date:2007-6-20
 */
public class ECertUnsupportedCAException extends WebChannelException {

    private static final long serialVersionUID = 4087826377523938351L;

    public ECertUnsupportedCAException() {
        super();
    }

    public ECertUnsupportedCAException(String message) {
        super(message);
    }

    public ECertUnsupportedCAException(String message, Throwable enclosedException) {
        super(message, enclosedException);
    }

    public ECertUnsupportedCAException(Throwable enclosedException) {
        super(enclosedException);
    }

    public ECertUnsupportedCAException(String message, String messageKey, Object[] args ) {
        super(message);
        this.setMessageKey( messageKey );
        this.setArgs( args );
    }
}
