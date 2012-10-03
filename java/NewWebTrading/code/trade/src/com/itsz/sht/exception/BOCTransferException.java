package com.itsz.sht.exception;


/**
 * 
 * $Id: BOCTransferException.java,v 1.1 2010/11/09 03:57:30 kyzou Exp $
 * @Project:portal
 * @File:BOCTransferException.java
 * @Description:
 * @Author:Cimenon Saint
 * @Date:2007-7-2
 */
public class BOCTransferException extends WebChannelException {

    private static final long serialVersionUID = 1L;

    public BOCTransferException() {
        super();
    }
    public BOCTransferException(String message) {
        super(message);
    }
    public BOCTransferException(String message, Throwable enclosedException) {
        super(message, enclosedException);
    }
    public BOCTransferException(Throwable enclosedException) {
        super(enclosedException);
    }
    public BOCTransferException(
        String message,
        String messageKey,
        Object[] args) {
        super(message);
        this.setMessageKey(messageKey);
        this.setArgs(args);
    }
}
