package com.itsz.sht.common.model.response;

import com.itsz.sht.common.model.AbstractResponseModel;
import com.itsz.sht.common.model.response.login.EcertResponseModel;
import com.itsz.sht.common.model.response.login.RtqResponseModel;

/**
 * $Id: ECertLoginResponseModel.java,v 1.1 2010/11/09 03:57:28 kyzou Exp $
 * @Project:portal.head
 * @File:ECertLoginResponseModel.java
 * @Description:
 * @Author:Cimenon Saint
 * @Date:2007-5-21
 */
public class ECertLoginResponseModel extends AbstractResponseModel {

	private EcertResponseModel ecertResponseModel;
	private RtqResponseModel rtqResponseModel;

	public EcertResponseModel getEcertResponseModel() {
		return ecertResponseModel;
	}
	public void setEcertResponseModel(EcertResponseModel ecertResponseModel) {
		this.ecertResponseModel = ecertResponseModel;
	}
	public RtqResponseModel getRtqResponseModel() {
		return rtqResponseModel;
	}
	public void setRtqResponseModel(RtqResponseModel rtqResponseModel) {
		this.rtqResponseModel = rtqResponseModel;
	}
}
