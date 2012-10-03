package com.itsz.sht.struts.form;

import org.apache.struts.validator.ValidatorForm;

public class RtqAccountForm extends ValidatorForm {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String prodId;
	private String rtqLognId;
	private String rtqLognPwd;
    private String	idFrom;
	private String	idTo;
	private Long lastN;
	private String dayEndFlag;
	public String getProdId() {
		return prodId;
	}
	public void setProdId(String prodId) {
		this.prodId = prodId;
	}
	public String getRtqLognId() {
		return rtqLognId;
	}
	public void setRtqLognId(String rtqLognId) {
		this.rtqLognId = rtqLognId;
	}
	public String getRtqLognPwd() {
		return rtqLognPwd;
	}
	public void setRtqLognPwd(String rtqLognPwd) {
		this.rtqLognPwd = rtqLognPwd;
	}
	public String getIdFrom() {
		return idFrom;
	}
	public void setIdFrom(String idFrom) {
		this.idFrom = idFrom;
	}
	public String getIdTo() {
		return idTo;
	}
	public void setIdTo(String idTo) {
		this.idTo = idTo;
	}
	public Long getLastN() {
		return lastN;
	}
	public void setLastN(Long lastN) {
		this.lastN = lastN;
	}
	public String getDayEndFlag() {
		return dayEndFlag;
	}
	public void setDayEndFlag(String dayEndFlag) {
		this.dayEndFlag = dayEndFlag;
	}
	
}
