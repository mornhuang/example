package com.itsz.sht.dao.hibernate.model;

import java.util.Date;

/**
 * AclRoleFnctnPrmisn entity. @author MyEclipse Persistence Tools
 */

public class AclRoleFnctnPrmisn implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 1L;
	private String aclRoleFnctnPrmisnId;
	private String roleId;
	private String fnctnId;
	private AclFunction function;
	private String prmisnCde;
	private String initBy;
	private Date initDate;
	private String updBy;
	private Date updDate;

	// Constructors

	/** default constructor */
	public AclRoleFnctnPrmisn() {
	}

	/** minimal constructor */
	public AclRoleFnctnPrmisn(String aclRoleFnctnPrmisnId) {
		this.aclRoleFnctnPrmisnId = aclRoleFnctnPrmisnId;
	}

	/** full constructor */
	public AclRoleFnctnPrmisn(String aclRoleFnctnPrmisnId, String roleId,
			String fnctnId, String prmisnCde, String initBy, Date initDate,
			String updBy, Date updDate) {
		this.aclRoleFnctnPrmisnId = aclRoleFnctnPrmisnId;
		this.roleId = roleId;
		this.fnctnId = fnctnId;
		this.prmisnCde = prmisnCde;
		this.initBy = initBy;
		this.initDate = initDate;
		this.updBy = updBy;
		this.updDate = updDate;
	}

	// Property accessors

	public String getAclRoleFnctnPrmisnId() {
		return this.aclRoleFnctnPrmisnId;
	}

	public void setAclRoleFnctnPrmisnId(String aclRoleFnctnPrmisnId) {
		this.aclRoleFnctnPrmisnId = aclRoleFnctnPrmisnId;
	}

	public String getRoleId() {
		return this.roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getFnctnId() {
		return this.fnctnId;
	}

	public void setFnctnId(String fnctnId) {
		this.fnctnId = fnctnId;
	}

	public String getPrmisnCde() {
		return this.prmisnCde;
	}

	public void setPrmisnCde(String prmisnCde) {
		this.prmisnCde = prmisnCde;
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

	public AclFunction getFunction() {
		return function;
	}

	public void setFunction(AclFunction function) {
		this.function = function;
	}

}