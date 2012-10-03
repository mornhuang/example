package com.itsz.sht.common.model.response;

import java.util.List;

import com.itsz.sht.admin.broadcast.util.Broadcast;
import com.itsz.sht.common.model.AbstractResponseModel;

public class AnnounceResponseModel extends AbstractResponseModel {
	private List<Broadcast> broadcast;

	public List<Broadcast> getBroadcast() {
		return broadcast;
	}

	public void setBroadcast(List<Broadcast> broadcast) {
		this.broadcast = broadcast;
	}
}
