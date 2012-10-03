package com.itsz.sht.common.model.common.request;

import com.itsz.sht.common.model.common.AbstractRequestModel;
import com.itsz.sht.dao.hibernate.model.NotificationMedia;

public class NotificationMediaListRequestModel extends AbstractRequestModel {

	String notfType;

	public String getNotfType() {
		return notfType;
	}

	public void setNotfType(String notfType) {
		this.notfType = notfType;
	}
	
}
