package com.itsz.sht.dao.hibernate.model;

/**
 * RtqAccAsgnId entity. @author MyEclipse Persistence Tools
 */

public class RtqAccAsgnId implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 1L;
	private String prodId;
	private String rtqLognId;
	private String clntId;

	// Constructors

	/** default constructor */
	public RtqAccAsgnId() {
	}

	/** full constructor */
	public RtqAccAsgnId(String prodId, String rtqLognId, String clntId) {
		this.prodId = prodId;
		this.rtqLognId = rtqLognId;
		this.clntId = clntId;
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

	public String getClntId() {
		return this.clntId;
	}

	public void setClntId(String clntId) {
		this.clntId = clntId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof RtqAccAsgnId))
			return false;
		RtqAccAsgnId castOther = (RtqAccAsgnId) other;

		return ((this.getProdId() == castOther.getProdId()) || (this
				.getProdId() != null
				&& castOther.getProdId() != null && this.getProdId().equals(
				castOther.getProdId())))
				&& ((this.getRtqLognId() == castOther.getRtqLognId()) || (this
						.getRtqLognId() != null
						&& castOther.getRtqLognId() != null && this
						.getRtqLognId().equals(castOther.getRtqLognId())))
				&& ((this.getClntId() == castOther.getClntId()) || (this
						.getClntId() != null
						&& castOther.getClntId() != null && this.getClntId()
						.equals(castOther.getClntId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getProdId() == null ? 0 : this.getProdId().hashCode());
		result = 37 * result
				+ (getRtqLognId() == null ? 0 : this.getRtqLognId().hashCode());
		result = 37 * result
				+ (getClntId() == null ? 0 : this.getClntId().hashCode());
		return result;
	}

}