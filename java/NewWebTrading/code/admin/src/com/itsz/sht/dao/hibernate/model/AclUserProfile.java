package com.itsz.sht.dao.hibernate.model;

import java.util.Date;

/**
 * AclUsrProf entity. @author MyEclipse Persistence Tools
 */

public class AclUserProfile implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 1L;
	private String lognId;
	private String roleId;
	private AclRole aclRole;
	private String deptNme;
	private String usrNme;
	private String pwd;
	private String emailAddr;
	private String status;
	private String initBy;
	private Date initDate;
	private String updBy;
	private Date updDate;

	// Constructors

	/** default constructor */
	public AclUserProfile() {
	}

	/** minimal constructor */
	public AclUserProfile(String lognId) {
		this.lognId = lognId;
	}

	/** full constructor */
	public AclUserProfile(String lognId, String roleId, String deptNme,
			String usrNme, String pwd, String emailAddr, String status,
			String initBy, Date initDate, String updBy, Date updDate) {
		this.lognId = lognId;
		this.roleId = roleId;
		this.deptNme = deptNme;
		this.usrNme = usrNme;
		this.pwd = pwd;
		this.emailAddr = emailAddr;
		this.status = status;
		this.initBy = initBy;
		this.initDate = initDate;
		this.updBy = updBy;
		this.updDate = updDate;
	}

	// Property accessors

	public String getLognId() {
		return this.lognId;
	}

	public void setLognId(String lognId) {
		this.lognId = lognId;
	}

	public String getRoleId() {
		return this.roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	
	public AclRole getAclRole() {
		return aclRole;
	}

	public void setAclRole(AclRole aclRole) {
		this.aclRole = aclRole;
	}

	public String getDeptNme() {
		return this.deptNme;
	}

	public void setDeptNme(String deptNme) {
		this.deptNme = deptNme;
	}

	public String getUsrNme() {
		return this.usrNme;
	}

	public void setUsrNme(String usrNme) {
		this.usrNme = usrNme;
	}

	public String getPwd() {
		return this.pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getEmailAddr() {
		return this.emailAddr;
	}

	public void setEmailAddr(String emailAddr) {
		this.emailAddr = emailAddr;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
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