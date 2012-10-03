package com.itsz.sht.common.model.response;

import com.itsz.sht.common.model.AbstractResponseModel;

/**
 * $Id: SnapshotRTQResponseModel.java,v 1.1 2010/11/09 03:57:29 kyzou Exp $
 * @Project:portal.head
 * @File:SnapshotRTQResponseModel.java
 * @Description:
 * @Author:Cimenon Saint
 * @Date:2007-5-21
 */
public class SnapshotRTQResponseModel extends AbstractResponseModel {
	
	private boolean isEsDown;
	private boolean isRtqAvailable;	
	public Integer accessUnits;
//	public String subSvcId;
	
	public Integer getAccessUnits() {
		return accessUnits;
	}
	public void setAccessUnits(Integer accessUnits) {
		this.accessUnits = accessUnits;
	}
	public boolean isEsDown() {
		return isEsDown;
	}
	public void setEsDown(boolean isEsDown) {
		this.isEsDown = isEsDown;
	}
	public boolean isRtqAvailable() {
		return isRtqAvailable;
	}
	public void setRtqAvailable(boolean isRtqAvailable) {
		this.isRtqAvailable = isRtqAvailable;
	}

}
