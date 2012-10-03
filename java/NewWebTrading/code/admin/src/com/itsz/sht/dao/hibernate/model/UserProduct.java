package com.itsz.sht.dao.hibernate.model;

import java.util.Date;

public class UserProduct implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 1L;
	private UsrProdId id;
	private String allwRenl;
	private Date exprDate;
	private String status;
	private String acesUnitTotal;
	private String acesUnitUsed;
	private String acesUnitExpr;
	private String remarks;
	private String initBy;
	private Date initDate;
	private String updBy;
	private Date updDate;

	// Constructors

	/** default constructor */
	public UserProduct() {
	}

	/** minimal constructor */
	public UserProduct(UsrProdId id) {
		this.id = id;
	}

	/** full constructor */
	public UserProduct(UsrProdId id, String allwRenl, Date exprDate, String status,
			String acesUnitTotal, String acesUnitUsed, String acesUnitExpr,
			String remarks, String initBy, Date initDate, String updBy,
			Date updDate) {
		this.id = id;
		this.allwRenl = allwRenl;
		this.exprDate = exprDate;
		this.status = status;
		this.acesUnitTotal = acesUnitTotal;
		this.acesUnitUsed = acesUnitUsed;
		this.acesUnitExpr = acesUnitExpr;
		this.remarks = remarks;
		this.initBy = initBy;
		this.initDate = initDate;
		this.updBy = updBy;
		this.updDate = updDate;
	}

	// Property accessors

	public UsrProdId getId() {
		return this.id;
	}

	public void setId(UsrProdId id) {
		this.id = id;
	}

	public String getAllwRenl() {
		return this.allwRenl;
	}

	public void setAllwRenl(String allwRenl) {
		this.allwRenl = allwRenl;
	}

	public Date getExprDate() {
		return this.exprDate;
	}

	public void setExprDate(Date exprDate) {
		this.exprDate = exprDate;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAcesUnitTotal() {
		return this.acesUnitTotal;
	}

	public void setAcesUnitTotal(String acesUnitTotal) {
		this.acesUnitTotal = acesUnitTotal;
	}

	public String getAcesUnitUsed() {
		return this.acesUnitUsed;
	}

	public void setAcesUnitUsed(String acesUnitUsed) {
		this.acesUnitUsed = acesUnitUsed;
	}

	public String getAcesUnitExpr() {
		return this.acesUnitExpr;
	}

	public void setAcesUnitExpr(String acesUnitExpr) {
		this.acesUnitExpr = acesUnitExpr;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
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