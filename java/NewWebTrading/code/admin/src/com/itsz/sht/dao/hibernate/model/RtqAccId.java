package com.itsz.sht.dao.hibernate.model;

/**
 * RtqAccId entity. @author MyEclipse Persistence Tools
 */

public class RtqAccId implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 1L;
	private String prodId;
	private String rtqLognId;

	// Constructors

	/** default constructor */
	public RtqAccId() {
	}

	/** full constructor */
	public RtqAccId(String prodId, String rtqLognId) {
		this.prodId = prodId;
		this.rtqLognId = rtqLognId;
	}

	// Property accessors

	public String getProdId() {
		return this.prodId;
	}

	public void setProdId(String prodId) {
		this.prodId = prodId;
	}

	public String getRtqLognId() {
		return this.rtqLognId;
	}

	public void setRtqLognId(String rtqLognId) {
		this.rtqLognId = rtqLognId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof RtqAccId))
			return false;
		RtqAccId castOther = (RtqAccId) other;

		return ((this.getProdId() == castOther.getProdId()) || (this
				.getProdId() != null
				&& castOther.getProdId() != null && this.getProdId().equals(
				castOther.getProdId())))
				&& ((this.getRtqLognId() == castOther.getRtqLognId()) || (this
						.getRtqLognId() != null
						&& castOther.getRtqLognId() != null && this
						.getRtqLognId().equals(castOther.getRtqLognId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getProdId() == null ? 0 : this.getProdId().hashCode());
		result = 37 * result
				+ (getRtqLognId() == null ? 0 : this.getRtqLognId().hashCode());
		return result;
	}

}