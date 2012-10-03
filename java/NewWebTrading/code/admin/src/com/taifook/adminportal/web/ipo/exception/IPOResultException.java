package com.taifook.adminportal.web.ipo.exception;

import com.taifook.framework.foundation.exception.TFAppException;


public class IPOResultException extends TFAppException {

    protected String messageKey = null;
    protected Object[] args = null;

    public IPOResultException() {
        super();
    }

    public IPOResultException(String message) {
        super(message);
    }

    public IPOResultException(String message, Throwable enclosedException) {
        super(message, enclosedException);
    }

    public IPOResultException(Throwable enclosedException) {
        super(enclosedException);
    }

    public void setMessageKey( String messageKey ) {
        this.messageKey = messageKey;
    }

    public String getMessageKey() {
        return this.messageKey;
    }

    public void setArgs( Object[] args ) {
        this.args = args;
    }

    public Object[] getArgs() {
        return this.args;
    }

}
