
package com.itsz.sht.dao.hibernate.model;

import java.util.Date;

/**
 * AclPrmsn entity. @author MyEclipse Persistence Tools
 */

public class AclPermission implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 1L;
	private String prmisnCode;
	private String prmisnNme;
	private String initBy;
	private Date initDate;
	private String updBy;
	private Date updDate;

	// Constructors

	/** default constructor */
	public AclPermission() {
	}

	/** minimal constructor */
	public AclPermission(String prmisnCode) {
		this.prmisnCode = prmisnCode;
	}

	/** full constructor */
	public AclPermission(String prmisnCode, String prmisnNme, String initBy,
			Date initDate, String updBy, Date updDate) {
		this.prmisnCode = prmisnCode;
		this.prmisnNme = prmisnNme;
		this.initBy = initBy;
		this.initDate = initDate;
		this.updBy = updBy;
		this.updDate = updDate;
	}

	// Property accessors

	public String getPrmisnCode() {
		return this.prmisnCode;
	}

	public void setPrmisnCode(String prmisnCode) {
		this.prmisnCode = prmisnCode;
	}

	public String getPrmisnNme() {
		return this.prmisnNme;
	}

	public void setPrmisnNme(String prmisnNme) {
		this.prmisnNme = prmisnNme;
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