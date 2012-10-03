package com.itsz.sht.common.model.response.login;

import com.itsz.sht.common.model.response.SnapshotRTQResponseModel;
import com.itsz.sht.common.model.response.StreamRTQResponseModel;

/**
 * $Id: RtqResponseModel.java,v 1.1 2010/11/09 03:57:29 kyzou Exp $
 * @Project:portal.head
 * @File:RtqResponseModel.java
 * @Description:
 * @Author:Cimenon Saint
 * @Date:2007-5-22
 */
public class RtqResponseModel {

	private String useDelay;
	private StreamRTQResponseModel streamRTQResponseModel;
	private SnapshotRTQResponseModel snapshotRTQResponseModel;
	
	public SnapshotRTQResponseModel getSnapshotRTQResponseModel() {
		return snapshotRTQResponseModel;
	}
	public void setSnapshotRTQResponseModel(
			SnapshotRTQResponseModel snapshotRTQResponseModel) {
		this.snapshotRTQResponseModel = snapshotRTQResponseModel;
	}
	public StreamRTQResponseModel getStreamRTQResponseModel() {
		return streamRTQResponseModel;
	}
	public void setStreamRTQResponseModel(
			StreamRTQResponseModel streamRTQResponseModel) {
		this.streamRTQResponseModel = streamRTQResponseModel;
	}
	public String getUseDelay() {
		return useDelay;
	}
	public void setUseDelay(String useDelay) {
		this.useDelay = useDelay;
	}


}
