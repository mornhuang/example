package com.itsz.sht.common.model.response;

import com.itsz.sht.common.model.AbstractResponseModel;
import com.taifook.mcs.core.beans.msg.SnapshotProfileResponse;

/**
 * $Id: SnapshotProfileResponseModel.java,v 1.1 2010/11/09 03:57:29 kyzou Exp $
 * @Project:portal_b61
 * @File:SnapshotProfileResponseModel.java
 * @Description:
 * @Author:kyzou
 * @Date:2009-4-20
 */
public class SnapshotProfileResponseModel extends AbstractResponseModel {
	private SnapshotProfileResponse snapshotProfileResponse;

	public SnapshotProfileResponse getSnapshotProfileResponse() {
		return snapshotProfileResponse;
	}

	public void setSnapshotProfileResponse(SnapshotProfileResponse snapshotProfileResponse) {
		this.snapshotProfileResponse = snapshotProfileResponse;
	}
}
