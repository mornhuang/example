
package com.taifook.adminportal.web.ipo;

import com.taifook.framework.foundation.exception.TFAppException;

public class DAOException extends TFAppException {

    protected String messageKey = null;
    protected Object[] args = null;

    public DAOException() {
        super();
    }

    public DAOException(String message) {
        super(message);
    }

    public DAOException(String message, Throwable enclosedException) {
        super(message, enclosedException);
    }

    public DAOException(Throwable enclosedException) {
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
