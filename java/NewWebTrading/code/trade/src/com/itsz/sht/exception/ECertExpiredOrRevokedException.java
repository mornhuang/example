package com.itsz.sht.exception;


/**
 * $Id: ECertExpiredOrRevokedException.java,v 1.1 2010/11/09 03:57:30 kyzou Exp $
 * @Project:portal
 * @File:ECertExpiredOrRevokedException.java
 * @Description:
 * @Author:
 * @Date:2007-6-20
 */
public class ECertExpiredOrRevokedException extends WebChannelException {

    private static final long serialVersionUID = -9044839033866054005L;

    public ECertExpiredOrRevokedException() {
        super();
    }

    public ECertExpiredOrRevokedException(String message) {
        super(message);
    }

    public ECertExpiredOrRevokedException(String message, Throwable enclosedException) {
        super(message, enclosedException);
    }

    public ECertExpiredOrRevokedException(Throwable enclosedException) {
        super(enclosedException);
    }

    public ECertExpiredOrRevokedException(String message, String messageKey, Object[] args ) {
        super(message);
        this.setMessageKey( messageKey );
        this.setArgs( args );
    }
}
