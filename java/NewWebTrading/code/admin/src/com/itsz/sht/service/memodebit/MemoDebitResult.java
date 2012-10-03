package com.itsz.sht.service.memodebit;

import com.taifook.misgateway.TransactionMessage;
import com.taifook.mtss.mss.webservice.ErrorInfo;

public class MemoDebitResult {
	
	private boolean isCompleted;
	private String resultCode;
	private ErrorInfo[] errorList;                 //MSSE
	private TransactionMessage[] errorMessage;     //MIS
	
	public MemoDebitResult() {
		this.isCompleted = false;
	}

	public MemoDebitResult(boolean isCompleted, String resultCode,
			ErrorInfo[] errorList, TransactionMessage[] errorMessage) {
		super();
		this.isCompleted = isCompleted;
		this.resultCode = resultCode;
		this.errorList = errorList;
		this.errorMessage = errorMessage;
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

	public TransactionMessage[] getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(TransactionMessage[] errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	public String toString(){
		StringBuffer sb = new StringBuffer();
		sb.append("isCompleted: " + isCompleted + "\nresultCode:  " + resultCode + "\n");
		if(errorList!=null&&errorList.length>0){
			sb.append("MSSE error:\n");
			for(ErrorInfo info : errorList){
				sb.append("        " + info.getErrorCode() + ": " + info.getErrorParams() + "\n");
			}
		}
		if(errorMessage!=null&&errorMessage.length>0){
			sb.append("MIS error:\n");
			for(TransactionMessage info : errorMessage){
				sb.append("         " + info.getMsgCode() + ": " + info.getMsgContent() + "\n");
			}
		}
		return sb.toString();
	}
	
}
