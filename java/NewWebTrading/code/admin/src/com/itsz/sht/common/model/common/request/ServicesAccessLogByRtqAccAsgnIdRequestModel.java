package com.itsz.sht.common.model.common.request;

import com.itsz.sht.common.model.common.AbstractRequestModel;
import com.itsz.sht.dao.hibernate.model.RtqAccAsgnId;

public class ServicesAccessLogByRtqAccAsgnIdRequestModel extends AbstractRequestModel{

	private RtqAccAsgnId id;

	public RtqAccAsgnId getId() {
		return id;
	}

	public void setId(RtqAccAsgnId id) {
		this.id = id;
	}
	
}
