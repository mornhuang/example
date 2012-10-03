package com.itsz.sht.dao.hibernate.model;
import java.util.Date;;

public class RtqApplication  implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 1L;
	private String prodId;
	private String rtqPrdr;
	private String rtqUrl;
	private String rtqStatus;
	private Date initDate;
	private String initBy;
	private Date updDate;
	private String updBy;

	// Constructors

	/** default constructor */
	public RtqApplication() {
	}

	/** minimal constructor */
	public RtqApplication(String prodId) {
		this.prodId = prodId;
	}

	/** full constructor */
	public RtqApplication(String prodId, String rtqPrdr, String rtqUrl,
			String rtqStatus, Date initDate, String initBy, Date updDate,
			String updBy) {
		this.prodId = prodId;
		this.rtqPrdr = rtqPrdr;
		this.rtqUrl = rtqUrl;
		this.rtqStatus = rtqStatus;
		this.initDate = initDate;
		this.initBy = initBy;
		this.updDate = updDate;
		this.updBy = updBy;
	}

	// Property accessors

	public String getProdId() {
		return this.prodId;
	}

	public void setProdId(String prodId) {
		this.prodId = prodId;
	}

	public String getRtqPrdr() {
		return this.rtqPrdr;
	}

	public void setRtqPrdr(String rtqPrdr) {
		this.rtqPrdr = rtqPrdr;
	}

	public String getRtqUrl() {
		return this.rtqUrl;
	}

	public void setRtqUrl(String rtqUrl) {
		this.rtqUrl = rtqUrl;
	}

	public String getRtqStatus() {
		return this.rtqStatus;
	}

	public void setRtqStatus(String rtqStatus) {
		this.rtqStatus = rtqStatus;
	}

	public Date getInitDate() {
		return this.initDate;
	}

	public void setInitDate(Date initDate) {
		this.initDate = initDate;
	}

	public String getInitBy() {
		return this.initBy;
	}

	public void setInitBy(String initBy) {
		this.initBy = initBy;
	}

	public Date getUpdDate() {
		return this.updDate;
	}

	public void setUpdDate(Date updDate) {
		this.updDate = updDate;
	}

	public String getUpdBy() {
		return this.updBy;
	}

	public void setUpdBy(String updBy) {
		this.updBy = updBy;
	}

}
