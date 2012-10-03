/* Generated by Together */

package com.itsz.sht.exception;

import javax.ejb.*;

/** This exception is thrown when  occurred */

public class ESSysException
    extends EJBException
    implements ESErrorCode {

	private static final long serialVersionUID = 1L;

	protected int errCode;

    public ESSysException(String message) {
        super(message);
    }

    public ESSysException() {
        super();
    }

    public ESSysException(Exception e) {
        super(e);

        if (e instanceof ESErrorCode){
            setErrCode(((ESErrorCode)e).getErrCode());
        }
    }

    public ESSysException(String message, Exception enclosedException) {
        super(message, enclosedException);

        if (enclosedException instanceof ESErrorCode){
            setErrCode(((ESErrorCode)enclosedException).getErrCode());
        }
    }

    public int getErrCode() {
        return errCode;
    }

    public ESSysException setErrCode(int errCode) {
        this.errCode = errCode;
        return this;
    }

	public String getMessage() {
		return "Error " + errCode + ": " + super.getMessage();
	}
}