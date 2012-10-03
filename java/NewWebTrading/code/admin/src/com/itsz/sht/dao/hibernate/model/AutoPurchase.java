/**
 * 
 */
package com.itsz.sht.dao.hibernate.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author cyzeng
 *
 */
public class AutoPurchase implements Serializable {

	// Fields
	private static final long serialVersionUID = 1L;
	private String autoPurcId;
	private String clntId;
	private String prodId;
	private String status;
	private Long priceInHkd;
	private String debtRemarks;
	private Date updDate;
	private String updBy;
	
	// Constructors
	public AutoPurchase() {
	}
	
	public AutoPurchase(String autoPurcId) {
		super();
		this.autoPurcId = autoPurcId;
	}

	public AutoPurchase(String autoPurcId, String clntId, String prodId,
			String status, Long priceInHkd, String debtRemarks, Date updDate, String updBy) {
		super();
		this.autoPurcId = autoPurcId;
		this.clntId = clntId;
		this.prodId = prodId;
		this.status = status;
		this.priceInHkd = priceInHkd;
		this.debtRemarks = debtRemarks;
		this.updDate = updDate;
		this.updBy = updBy;
	}

	// Property accessors
	public String getAutoPurcId() {
		return autoPurcId;
	}

	public void setAutoPurcId(String autoPurcId) {
		this.autoPurcId = autoPurcId;
	}

	public String getClntId() {
		return clntId;
	}

	public void setClntId(String clntId) {
		this.clntId = clntId;
	}

	public String getProdId() {
		return prodId;
	}

	public void setProdId(String prodId) {
		this.prodId = prodId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getPriceInHkd() {
		return priceInHkd;
	}

	public void setPriceInHkd(Long priceInHkd) {
		this.priceInHkd = priceInHkd;
	}

	public String getDebtRemarks() {
		return debtRemarks;
	}

	public void setDebtRemarks(String debtRemarks) {
		this.debtRemarks = debtRemarks;
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
