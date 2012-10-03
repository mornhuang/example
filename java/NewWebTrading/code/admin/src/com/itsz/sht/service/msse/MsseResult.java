package com.itsz.sht.service.msse;

import com.taifook.mtss.mss.webservice.ErrorInfo;

public class MsseResult {
	
	private boolean isCompleted;
	private String resultCode;
	private ErrorInfo[] errorList;
	
	public MsseResult() {
	}
	
	public MsseResult(boolean isCompleted, String resultCode,
			ErrorInfo[] errorList) {
		this.isCompleted = isCompleted;
		this.resultCode = resultCode;
		this.errorList = errorList;
	}
	
	public boolean isCompleted() {
		return isCompleted;
	}
	public void setCompleted(boolean isCompleted) {
		this.isCompleted = isCompleted;
	}
	public String getResultCode() {
		return resultCode;
	}
	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}
	public ErrorInfo[] getErrorList() {
		return errorList;
	}
	public void setErrorList(ErrorInfo[] errorList) {
		this.errorList = errorList;
	}
	
}
