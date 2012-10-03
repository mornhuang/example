package com.itsz.sht.common.auto;

public class AutoPurchaseError {
	
	public  String errorId;
	public String  errorValue;
	public String getErrorId() {
		return errorId;
	}
	public void setErrorId(String errorId) {
		this.errorId = errorId;
	}
	public String getErrorValue() {
		return errorValue;
	}
	public void setErrorValue(String errorValue) {
		this.errorValue = errorValue;
	}
	
    public AutoPurchaseError(String errorId,String errorValue){
    	this.errorId=errorId;
    	this.errorValue=errorValue;
    }
}
