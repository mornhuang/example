package com.itsz.sht.dao.hibernate.model;
import java.io.Serializable;
import java.util.Date;


public class Product  implements Serializable{
	private static final long serialVersionUID = 1L;
	private String prodId;
	private String prodStatus;
	private Long quota;
	private Long alltQuota;
	private String svcMode;
	private String valtOfSvc;
	private Long acesUnit;
	private String billType;
	private String discType;
	private Long priceInHkd;
	private Date effDate;
	private Date exprDate;
	private String remarks;
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
	public String getProdStatus() {
		return prodStatus;
	}
	public void setProdStatus(String prodStatus) {
		this.prodStatus = prodStatus;
	}
	public Long getQuota() {
		return quota;
	}
	public void setQuota(Long quota) {
		this.quota = quota;
	}
	public Long getAlltQuota() {
		return alltQuota;
	}
	public void setAlltQuota(Long alltQuota) {
		this.alltQuota = alltQuota;
	}
	public String getSvcMode() {
		return svcMode;
	}
	public void setSvcMode(String svcMode) {
		this.svcMode = svcMode;
	}
	public String getValtOfSvc() {
		return valtOfSvc;
	}
	public void setValtOfSvc(String valtOfSvc) {
		this.valtOfSvc = valtOfSvc;
	}
	public Long getAcesUnit() {
		return acesUnit;
	}
	public void setAcesUnit(Long acesUnit) {
		this.acesUnit = acesUnit;
	}
	public String getBillType() {
		return billType;
	}
	public void setBillType(String billType) {
		this.billType = billType;
	}
	public String getDiscType() {
		return discType;
	}
	public void setDiscType(String discType) {
		this.discType = discType;
	}
	public Long getPriceInHkd() {
		return priceInHkd;
	}
	public void setPriceInHkd(Long priceInHkd) {
		this.priceInHkd = priceInHkd;
	}
	public Date getEffDate() {
		return effDate;
	}
	public void setEffDate(Date effDate) {
		this.effDate = effDate;
	}
	public Date getExprDate() {
		return exprDate;
	}
	public void setExprDate(Date exprDate) {
		this.exprDate = exprDate;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
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
