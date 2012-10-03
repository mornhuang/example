package com.itsz.sht.exception;

import com.taifook.framework.foundation.exception.TFAppException;

/**
 * 
 * $Id: WebChannelException.java,v 1.1 2010/11/09 03:57:30 kyzou Exp $
 * @Project:portal
 * @File:WebChannelException.java
 * @Description:
 * @Author:Cimenon Saint
 * @Date:2007-11-5
 */
public class WebChannelException extends TFAppException {

	private static final long serialVersionUID = -8733679494528144522L;
	protected String messageKey;
	protected Object args[];

	public WebChannelException() {
		messageKey = null;
		args = null;
	}

	public WebChannelException(String message) {
		super(message);
		messageKey = null;
		args = null;
	}

	public WebChannelException(String message, Throwable enclosedException) {
		super(message, enclosedException);
		messageKey = null;
		args = null;
	}

	public WebChannelException(Throwable enclosedException) {
		super(enclosedException);
		messageKey = null;
		args = null;
	}

	public void setMessageKey(String messageKey) {
		this.messageKey = messageKey;
	}

	public String getMessageKey() {
		return messageKey;
	}

	public void setArgs(Object args[]) {
		this.args = args;
	}

	public Object[] getArgs() {
		return args;
	}
}
