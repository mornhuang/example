package com.itsz.sht.dao.hibernate.model;

import java.util.Date;

/**
 * DeftDebtAccChngLog entity. @author MyEclipse Persistence Tools
 */

public class DeftDebtAccChngLog implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 1L;
	private String defDebtAccChgLogId;
	private String clntId;
	private String defDebtAcc;
	private Date initDate;
	private String initBy;
	private Date updDate;
	private String updBy;
	private String actnType;
	private Date actnDate;
	private String actnBy;

	// Constructors

	/** default constructor */
	public DeftDebtAccChngLog() {
	}

	/** minimal constructor */
	public DeftDebtAccChngLog(String defDebtAccChgLogId) {
		this.defDebtAccChgLogId = defDebtAccChgLogId;
	}

	/** full constructor */
	public DeftDebtAccChngLog(String defDebtAccChgLogId, String clntId,
			String defDebtAcc, Date initDate, String initBy, Date updDate,
			String updBy, String actnType, Date actnDate, String actnBy) {
		this.defDebtAccChgLogId = defDebtAccChgLogId;
		this.clntId = clntId;
		this.defDebtAcc = defDebtAcc;
		this.initDate = initDate;
		this.initBy = initBy;
		this.updDate = updDate;
		this.updBy = updBy;
		this.actnType = actnType;
		this.actnDate = actnDate;
		this.actnBy = actnBy;
	}

	// Property accessors

	public String getDefDebtAccChgLogId() {
		return this.defDebtAccChgLogId;
	}

	public void setDefDebtAccChgLogId(String defDebtAccChgLogId) {
		this.defDebtAccChgLogId = defDebtAccChgLogId;
	}

	public String getClntId() {
		return this.clntId;
	}

	public void setClntId(String clntId) {
		this.clntId = clntId;
	}

	public String getDefDebtAcc() {
		return this.defDebtAcc;
	}

	public void setDefDebtAcc(String defDebtAcc) {
		this.defDebtAcc = defDebtAcc;
	}

	public Date getInitDate() {
		return this.initDate;
	}

	public void setInitDate(Date initDate) {
		this.initDate = initDate;
	}

	public String getInitBy() {
		return this.initBy;
	}

	public void setInitBy(String initBy) {
		this.initBy = initBy;
	}

	public Date getUpdDate() {
		return this.updDate;
	}

	public void setUpdDate(Date updDate) {
		this.updDate = updDate;
	}

	public String getUpdBy() {
		return this.updBy;
	}

	public void setUpdBy(String updBy) {
		this.updBy = updBy;
	}

	public String getActnType() {
		return this.actnType;
	}

	public void setActnType(String actnType) {
		this.actnType = actnType;
	}

	public Date getActnDate() {
		return this.actnDate;
	}

	public void setActnDate(Date actnDate) {
		this.actnDate = actnDate;
	}

	public String getActnBy() {
		return this.actnBy;
	}

	public void setActnBy(String actnBy) {
		this.actnBy = actnBy;
	}

}