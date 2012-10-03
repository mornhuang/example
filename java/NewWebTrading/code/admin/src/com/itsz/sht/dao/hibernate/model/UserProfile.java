package com.itsz.sht.dao.hibernate.model;

import java.util.Date;

public class UserProfile implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 1L;
	private String clntId;
	private String clntLoginId;
	private String defDebtAcc;
	private String cnDiscFlag;
	private String initBy;
	private Date initDate;
	private String updBy;
	private Date updDate;

	// Constructors

	/** default constructor */
	public UserProfile() {
	}

	/** minimal constructor */
	public UserProfile(String clntId) {
		this.clntId = clntId;
	}

	/** full constructor */
	public UserProfile(String clntId, String clntLoginId, String defDebtAcc, String cnDiscFlag,
			String initBy, Date initDate, String updBy, Date updDate) {
		this.clntId = clntId;
		this.clntLoginId = clntLoginId;
		this.defDebtAcc = defDebtAcc;
		this.cnDiscFlag = cnDiscFlag;
		this.initBy = initBy;
		this.initDate = initDate;
		this.updBy = updBy;
		this.updDate = updDate;
	}

	// Property accessors

	public String getClntId() {
		return this.clntId;
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
		return this.defDebtAcc;
	}

	public void setDefDebtAcc(String defDebtAcc) {
		this.defDebtAcc = defDebtAcc;
	}

	public String getCnDiscFlag() {
		return this.cnDiscFlag;
	}

	public void setCnDiscFlag(String cnDiscFlag) {
		this.cnDiscFlag = cnDiscFlag;
	}

	public String getInitBy() {
		return this.initBy;
	}

	public void setInitBy(String initBy) {
		this.initBy = initBy;
	}

	public Date getInitDate() {
		return this.initDate;
	}

	public void setInitDate(Date initDate) {
		this.initDate = initDate;
	}

	public String getUpdBy() {
		return this.updBy;
	}

	public void setUpdBy(String updBy) {
		this.updBy = updBy;
	}

	public Date getUpdDate() {
		return this.updDate;
	}

	public void setUpdDate(Date updDate) {
		this.updDate = updDate;
	}

}