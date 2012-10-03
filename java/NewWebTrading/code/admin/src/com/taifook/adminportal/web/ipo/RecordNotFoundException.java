
package com.taifook.adminportal.web.ipo;


public class RecordNotFoundException extends DAOException {

    protected String messageKey = null;
    protected Object[] args = null;

    public RecordNotFoundException() {
        super();
    }

    public RecordNotFoundException(String message) {
        super(message);
    }

    public RecordNotFoundException(String message, Throwable enclosedException) {
        super(message, enclosedException);
    }

    public RecordNotFoundException(Throwable enclosedException) {
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
