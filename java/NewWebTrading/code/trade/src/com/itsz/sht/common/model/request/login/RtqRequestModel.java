package com.itsz.sht.common.model.request.login;

import com.itsz.sht.common.model.request.SnapshotRTQRequestModel;
import com.itsz.sht.common.model.request.StreamRTQRequestModel;
import com.itsz.sht.common.model.response.login.RtqModel;

public class RtqRequestModel extends RtqModel {

	private StreamRTQRequestModel streamRTQRequestModel;
	private SnapshotRTQRequestModel snapshotRTQRequestModel;
	
	public SnapshotRTQRequestModel getSnapshotRTQRequestModel() {
		return snapshotRTQRequestModel;
	}
	public void setSnapshotRTQRequestModel(
			SnapshotRTQRequestModel snapshotRTQRequestModel) {
		this.snapshotRTQRequestModel = snapshotRTQRequestModel;
	}
	public StreamRTQRequestModel getStreamRTQRequestModel() {
		return streamRTQRequestModel;
	}
	public void setStreamRTQRequestModel(StreamRTQRequestModel streamRTQRequestModel) {
		this.streamRTQRequestModel = streamRTQRequestModel;
	}

}
