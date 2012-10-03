package com.itsz.sht.struts.eipo.dao;

import java.io.Serializable;
import java.math.BigDecimal;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.itsz.eipo.webservice.IpoDenominationDto;
import com.itsz.sht.common.FieldUtil;

public class EIPODenominationEntry implements Serializable{

	private static final long serialVersionUID = 1L;
	private BigDecimal appliedShare;   //申购股数
	private BigDecimal amountPayable;   //股数面额
	
	public EIPODenominationEntry(IpoDenominationDto dto) {
		this.appliedShare = dto.getAppliedShare();
		this.amountPayable = dto.getAmountPayable();
	}
	

	/**
	 * @return the appliedShare
	 */
	public String getFormatAppliedShare() {
		String s = null;
		if (appliedShare != null) {
			s = FieldUtil.formatDouble(new Double(appliedShare.doubleValue()),
			"#,###");
		}
		return s;
	}

	/**
	 * @return the appliedShare
	 */
	public BigDecimal getAppliedShare() {
		return appliedShare;
	}

	
	/**
	 * @param appliedShare the appliedShare to set
	 */
	public void setAppliedShare(BigDecimal appliedShare) {
		this.appliedShare = appliedShare;
	}


	/**
	 * @return the amountPayable
	 */
	public BigDecimal getAmountPayable() {
		return amountPayable;
	}
	
	/**
	 * @return the amountPayable
	 */
	public String getFormatAmountPayable() {
		String s = null;
		if (amountPayable != null) {
			s = FieldUtil.formatDouble(new Double(amountPayable.doubleValue()),
			"$#,##0.000");
		}
		return s;
	}


	/**
	 * @param amountPayable the amountPayable to set
	 */
	public void setAmountPayable(BigDecimal amountPayable) {
		this.amountPayable = amountPayable;
	}


	public void reset() {
		// TODO Auto-generated method stub

	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
