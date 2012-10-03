package com.itsz.sht.dao.hibernate.model;

/**
 * UsrProdId entity. @author MyEclipse Persistence Tools
 */

public class UsrProdId implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 1L;
	private String clntId;
	private String prodId;

	// Constructors

	/** default constructor */
	public UsrProdId() {
	}

	/** full constructor */
	public UsrProdId(String clntId, String prodId) {
		this.clntId = clntId;
		this.prodId = prodId;
	}

	// Property accessors

	public String getClntId() {
		return this.clntId;
	}

	public void setClntId(String clntId) {
		this.clntId = clntId;
	}

	public String getProdId() {
		return this.prodId;
	}

	public void setProdId(String prodId) {
		this.prodId = prodId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof UsrProdId))
			return false;
		UsrProdId castOther = (UsrProdId) other;

		return ((this.getClntId() == castOther.getClntId()) || (this
				.getClntId() != null
				&& castOther.getClntId() != null && this.getClntId().equals(
				castOther.getClntId())))
				&& ((this.getProdId() == castOther.getProdId()) || (this
						.getProdId() != null
						&& castOther.getProdId() != null && this.getProdId()
						.equals(castOther.getProdId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getClntId() == null ? 0 : this.getClntId().hashCode());
		result = 37 * result
				+ (getProdId() == null ? 0 : this.getProdId().hashCode());
		return result;
	}

}