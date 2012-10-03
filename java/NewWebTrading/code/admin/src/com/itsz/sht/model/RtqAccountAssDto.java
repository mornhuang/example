package com.itsz.sht.model;

import java.util.Date;

public class RtqAccountAssDto {

	private String productId;

	private String rtqLoginId;

	private String rtqLoginPwd;

	private String clientId;
	
	private Date lastAccessTime;
  
	public RtqAccountAssDto(){}
	
	public RtqAccountAssDto(String productId, String rtqLoginId,
			String rtqLoginPwd, String clientId, Date lastAccessTime) {
		this.productId = productId;
		this.rtqLoginId = rtqLoginId;
		this.rtqLoginPwd = rtqLoginPwd;
		this.clientId = clientId;
		this.lastAccessTime = lastAccessTime;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getRtqLoginId() {
		return rtqLoginId;
	}

	public void setRtqLoginId(String rtqLoginId) {
		this.rtqLoginId = rtqLoginId;
	}

	public String getRtqLoginPwd() {
		return rtqLoginPwd;
	}

	public void setRtqLoginPwd(String rtqLoginPwd) {
		this.rtqLoginPwd = rtqLoginPwd;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public Date getLastAccessTime() {
		return lastAccessTime;
	}

	public void setLastAccessTime(Date lastAccessTime) {
		this.lastAccessTime = lastAccessTime;
	}
}
