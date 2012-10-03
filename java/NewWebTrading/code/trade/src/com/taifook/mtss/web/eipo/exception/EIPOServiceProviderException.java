package com.taifook.mtss.web.eipo.exception;

import java.util.List;

import com.itsz.sht.exception.WebChannelException;


public class EIPOServiceProviderException extends WebChannelException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EIPOServiceProviderException() {
        super();
    }

    public EIPOServiceProviderException(String message,String messageKey, List args) {
        super(message);
        this.setMessageKey( messageKey );
        if (args!=null && !args.isEmpty()) {
        	this.setArgs(args.toArray());
        }
    }

}
