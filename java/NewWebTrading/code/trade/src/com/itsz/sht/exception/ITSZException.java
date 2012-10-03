/*
 * Created on 2005-3-29
 *
 */
package com.itsz.sht.exception;

/**
 * @author wzzhan
 *
 */
public class ITSZException extends Exception{
	private static final long serialVersionUID = 1601116840599105904L;
	private String errorCode;
	private String errorMessage;
	
	public ITSZException() {
		
	}
	
	public ITSZException(String errorCode) {
		this.errorCode = errorCode;
	}
	
	public ITSZException(String errorCode, String errorMessage) {
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}
	
	/**
	 * @return Returns the errorCode.
	 */
	public String getErrorCode() {
		return errorCode;
	}
	/**
	 * @param errorCode The errorCode to set.
	 */
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	/**
	 * @return Returns the errorMessage.
	 */
	public String getErrorMessage() {
		return errorMessage;
	}
	/**
	 * @param errorMessage The errorMessage to set.
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}
