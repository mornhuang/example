package com.itsz.sht.exception;


/**
 * 
 * $Id: PPSTransferException.java,v 1.1 2010/11/09 03:57:30 kyzou Exp $
 * @Project:portal
 * @File:PPSTransferException.java
 * @Description:
 * @Author:Cimenon Saint
 * @Date:2007-7-2
 */
public class PPSTransferException extends WebChannelException {

    private static final long serialVersionUID = -5997173121213515958L;

    public PPSTransferException() {
        super();
    }

    public PPSTransferException(String message) {
        super(message);
    }

    public PPSTransferException(String message, Throwable enclosedException) {
        super(message, enclosedException);
    }

    public PPSTransferException(Throwable enclosedException) {
        super(enclosedException);
    }

    public PPSTransferException(
        String message,
        String messageKey,
        Object[] args) {
        super(message);
        this.setMessageKey(messageKey);
        this.setArgs(args);
    }

}
