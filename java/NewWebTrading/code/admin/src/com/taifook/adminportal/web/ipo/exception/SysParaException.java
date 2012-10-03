package com.taifook.adminportal.web.ipo.exception;

import com.taifook.framework.foundation.exception.TFAppException;


public class SysParaException extends TFAppException {

    protected String messageKey = null;
    protected Object[] args = null;

    public SysParaException() {
        super();
    }

    public SysParaException(String message) {
        super(message);
    }

    public SysParaException(String message, Throwable enclosedException) {
        super(message, enclosedException);
    }

    public SysParaException(Throwable enclosedException) {
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
