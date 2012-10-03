package com.taifook.adminportal.web.ipo.exception;

import com.taifook.framework.foundation.exception.TFAppException;


public class IPOQtyAmtException extends TFAppException {

    protected String messageKey = null;
    protected Object[] args = null;

    public IPOQtyAmtException() {
        super();
    }

    public IPOQtyAmtException(String message) {
        super(message);
    }

    public IPOQtyAmtException(String message, Throwable enclosedException) {
        super(message, enclosedException);
    }

    public IPOQtyAmtException(Throwable enclosedException) {
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
