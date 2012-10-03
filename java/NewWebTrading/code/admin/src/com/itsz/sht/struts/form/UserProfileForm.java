package com.itsz.sht.struts.form;



import java.util.Date;

import org.apache.struts.validator.ValidatorForm;

public class UserProfileForm extends ValidatorForm{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String clntId;
	private String clntLoginId;
	private String defDebtAcc;
	private String cnDiscFlag;
	private String initBy;
	private Date initDate;
	private String updBy;
	private Date updDate;
	
	public String getClntId() {
		return clntId;
	}
	public void setClntId(String clntId) {
		this.clntId = clntId;
	}
	public String getClntLoginId() {
		return clntLoginId;
	}
	public void setClntLoginId(String clntLoginId) {
		this.clntLoginId = clntLoginId;
	}
	public String getDefDebtAcc() {
		return defDebtAcc;
	}
	public void setDefDebtAcc(String defDebtAcc) {
		this.defDebtAcc = defDebtAcc;
	}
	public String getCnDiscFlag() {
		return cnDiscFlag;
	}
	public void setCnDiscFlag(String cnDiscFlag) {
		this.cnDiscFlag = cnDiscFlag;
	}
	public String getInitBy() {
		return initBy;
	}
	public void setInitBy(String initBy) {
		this.initBy = initBy;
	}
	public Date getInitDate() {
		return initDate;
	}
	public void setInitDate(Date initDate) {
		this.initDate = initDate;
	}
	public String getUpdBy() {
		return updBy;
	}
	public void setUpdBy(String updBy) {
		this.updBy = updBy;
	}
	public Date getUpdDate() {
		return updDate;
	}
	public void setUpdDate(Date updDate) {
		this.updDate = updDate;
	}
	
	
}
