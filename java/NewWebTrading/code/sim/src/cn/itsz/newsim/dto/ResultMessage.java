package cn.itsz.newsim.dto;

import cn.itsz.newsim.common.Constants;

public class ResultMessage extends BaseDTO {
	private String returnCode = Constants.Status.NORMAL;
	private String errorCode;

	public String getReturnCode() {
		return returnCode;
	}

	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
}
