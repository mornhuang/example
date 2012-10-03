package com.itsz.sht.common.model.common.response;

import com.itsz.sht.common.model.common.AbstractResponseModel;
import com.itsz.sht.dao.hibernate.model.RtqApplication;

public class RTQApplicationResponseModel  extends AbstractResponseModel{

 private 	RtqApplication  rtqApplication;

public RtqApplication getRtqApplication() {
	return rtqApplication;
}

public void setRtqApplication(RtqApplication rtqApplication) {
	this.rtqApplication = rtqApplication;
}


}
