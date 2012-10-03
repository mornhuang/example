package com.itsz.sht.struts.form;

import org.apache.struts.action.ActionForm;

public class NotificationMediaForm extends ActionForm {

	private static final long serialVersionUID = 1L;
	private String notfMdiaId;
	private String clntId;
	private String notfChnnl;
	private String notfType;
	private String prefLang;
	private String emailAddr;
	private String phoneNum;
	private String smsPrdr;
	private String smsRegnCode;
	private String smsCntryCode;
	private String ordType;
//	private String initBy;
//	private Date initDate;
//	private String updBy;
//	private Date updDate;
	
	public String getNotfMdiaId() {
		return this.notfMdiaId;
	}

	public void setNotfMdiaId(String notfMdiaId) {
		this.notfMdiaId = notfMdiaId;
	}

	public String getClntId() {
		return this.clntId;
	}

	public void setClntId(String clntId) {
		this.clntId = clntId;
	}

	public String getNotfChnnl() {
		return this.notfChnnl;
	}

	public void setNotfChnnl(String notfChnnl) {
		this.notfChnnl = notfChnnl;
	}

	public String getNotfType() {
		return this.notfType;
	}

	public void setNotfType(String notfType) {
		this.notfType = notfType;
	}

	public String getPrefLang() {
		return this.prefLang;
	}

	public void setPrefLang(String prefLang) {
		this.prefLang = prefLang;
	}

	public String getEmailAddr() {
		return this.emailAddr;
	}

	public void setEmailAddr(String emailAddr) {
		this.emailAddr = emailAddr;
	}

	public String getPhoneNum() {
		return this.phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getSmsPrdr() {
		return this.smsPrdr;
	}

	public void setSmsPrdr(String smsPrdr) {
		this.smsPrdr = smsPrdr;
	}

	public String getSmsRegnCode() {
		return this.smsRegnCode;
	}

	public void setSmsRegnCode(String smsRegnCode) {
		this.smsRegnCode = smsRegnCode;
	}

	public String getSmsCntryCode() {
		return this.smsCntryCode;
	}

	public void setSmsCntryCode(String smsCntryCode) {
		this.smsCntryCode = smsCntryCode;
	}

	public String getOrdType() {
		return this.ordType;
	}

	public void setOrdType(String ordType) {
		this.ordType = ordType;
	}



}

