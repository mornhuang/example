package com.itsz.sht.struts.form;

import java.util.Date;

import org.apache.struts.validator.ValidatorForm;

public class UserProductForm extends ValidatorForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String clntId;
	private String clntLoginId;
	private String[] prodId;
	
	private Date exprDate;
	
	private String acesUnitTotal;
	private String acesUnitUsed;
	private String acesUnitExpr;
	private String remarks;
	private String initBy;
	private Date initDate;
	private String updBy;
	private Date updDate;
	
	private String[] allwRenl;
	private String[] status;
	
	private String[] allwRenlOld;
	private String[] statusOld;
	
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
	public String[] getProdId() {
		return prodId;
	}
	public void setProdId(String[] prodId) {
		this.prodId = prodId;
	}
	public String[] getAllwRenl() {
		return allwRenl;
	}
	public void setAllwRenl(String[] allwRenl) {
		this.allwRenl = allwRenl;
	}
	public Date getExprDate() {
		return exprDate;
	}
	public void setExprDate(Date exprDate) {
		this.exprDate = exprDate;
	}
	public String[] getStatus() {
		return status;
	}
	public void setStatus(String[] status) {
		this.status = status;
	}
	public String getAcesUnitTotal() {
		return acesUnitTotal;
	}
	public void setAcesUnitTotal(String acesUnitTotal) {
		this.acesUnitTotal = acesUnitTotal;
	}
	public String getAcesUnitUsed() {
		return acesUnitUsed;
	}
	public void setAcesUnitUsed(String acesUnitUsed) {
		this.acesUnitUsed = acesUnitUsed;
	}
	public String getAcesUnitExpr() {
		return acesUnitExpr;
	}
	public void setAcesUnitExpr(String acesUnitExpr) {
		this.acesUnitExpr = acesUnitExpr;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
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
	public String[] getAllwRenlOld() {
		return allwRenlOld;
	}
	public void setAllwRenlOld(String[] allwRenlOld) {
		this.allwRenlOld = allwRenlOld;
	}
	public String[] getStatusOld() {
		return statusOld;
	}
	public void setStatusOld(String[] statusOld) {
		this.statusOld = statusOld;
	}
	

}
