package com.itsz.sht.common.model.request;

import java.util.Vector;

import com.itsz.sht.common.model.AbstractRequestModel;

/**
 * $Id: LotSizeRequestModel.java,v 1.1 2010/11/09 03:57:27 kyzou Exp $
 * @Project:portal.head
 * @File:LotSizeRequestModel.java
 * @Description:
 * @Author:Cimenon Saint
 * @Date:2007-5-21
 */
public class LotSizeRequestModel extends AbstractRequestModel {

	private String seqn;
	private String type;
	private String subType;
	private String customerId;
	private String serviceType;
	private Vector code;
	private String delayTime;

	public Vector getCode() {
		return code;
	}
	public void setCode(Vector code) {
		this.code = code;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getDelayTime() {
		return delayTime;
	}
	public void setDelayTime(String delayTime) {
		this.delayTime = delayTime;
	}
	public String getSeqn() {
		return seqn;
	}
	public void setSeqn(String seqn) {
		this.seqn = seqn;
	}
	public String getServiceType() {
		return serviceType;
	}
	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}
	public String getSubType() {
		return subType;
	}
	public void setSubType(String subType) {
		this.subType = subType;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

}
