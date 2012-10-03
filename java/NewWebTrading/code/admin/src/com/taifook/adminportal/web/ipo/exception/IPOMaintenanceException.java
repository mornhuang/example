package com.taifook.adminportal.web.ipo.exception;

import com.taifook.framework.foundation.exception.TFAppException;


public class IPOMaintenanceException extends TFAppException {

    protected String messageKey = null;
    protected Object[] args = null;

    public IPOMaintenanceException() {
        super();
    }

    public IPOMaintenanceException(String message) {
        super(message);
    }

    public IPOMaintenanceException(String message, Throwable enclosedException) {
        super(message, enclosedException);
    }

    public IPOMaintenanceException(Throwable enclosedException) {
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
