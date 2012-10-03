package com.itsz.sht.common.model.common.response;

import com.itsz.sht.common.model.common.AbstractResponseModel;
import com.itsz.sht.dao.hibernate.model.ServicesAccessLog;

public class ServicesAccessLogByRtqAccAsgnIdResponseModel extends AbstractResponseModel{
	private ServicesAccessLog servicesAccessLog;

	public ServicesAccessLog getServicesAccessLog() {
		return servicesAccessLog;
	}

	public void setServicesAccessLog(ServicesAccessLog servicesAccessLog) {
		this.servicesAccessLog = servicesAccessLog;
	}
}
