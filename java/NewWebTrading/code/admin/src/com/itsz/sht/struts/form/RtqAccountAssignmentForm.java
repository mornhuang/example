package com.itsz.sht.struts.form;



import java.util.Date;

import org.apache.struts.validator.ValidatorForm;

public class RtqAccountAssignmentForm extends ValidatorForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String prodId;
	private String rtqLognId;
	private String clntId;
	private String rtqLognPwd;
	private Date initDate;
	private String initBy;
	private Date updDate;
	private String updBy;
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
	public String getClntId() {
		return clntId;
	}
	public void setClntId(String clntId) {
		this.clntId = clntId;
	}
	public String getRtqLognPwd() {
		return rtqLognPwd;
	}
	public void setRtqLognPwd(String rtqLognPwd) {
		this.rtqLognPwd = rtqLognPwd;
	}
	public Date getInitDate() {
		return initDate;
	}
	public void setInitDate(Date initDate) {
		this.initDate = initDate;
	}
	public String getInitBy() {
		return initBy;
	}
	public void setInitBy(String initBy) {
		this.initBy = initBy;
	}
	public Date getUpdDate() {
		return updDate;
	}
	public void setUpdDate(Date updDate) {
		this.updDate = updDate;
	}
	public String getUpdBy() {
		return updBy;
	}
	public void setUpdBy(String updBy) {
		this.updBy = updBy;
	}

}
