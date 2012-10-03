package com.itsz.sht.common.model.common.response;

import com.itsz.sht.common.model.common.AbstractResponseModel;
import com.itsz.sht.model.RTQAccess;



public class AccessRTQResponseModel  extends AbstractResponseModel{

	private RTQAccess rtqAccess;

	public RTQAccess getRtqAccess() {
		return rtqAccess;
	}

	public void setRtqAccess(RTQAccess rtqAccess) {
		this.rtqAccess = rtqAccess;
	}	 
}
