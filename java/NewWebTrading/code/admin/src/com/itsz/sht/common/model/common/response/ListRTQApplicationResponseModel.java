package com.itsz.sht.common.model.common.response;

import java.util.List;

import com.itsz.sht.common.model.common.AbstractResponseModel;
import com.itsz.sht.dao.hibernate.model.RtqApplication;

public class ListRTQApplicationResponseModel extends AbstractResponseModel{

	private List<RtqApplication> rtqApplication;

	public List<RtqApplication> getRtqApplication() {
		return rtqApplication;
	}

	public void setRtqApplication(List<RtqApplication> rtqApplication) {
		this.rtqApplication = rtqApplication;
	}
	
}
