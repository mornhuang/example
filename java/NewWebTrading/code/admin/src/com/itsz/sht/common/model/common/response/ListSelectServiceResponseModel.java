package com.itsz.sht.common.model.common.response;

import java.util.List;

import com.itsz.sht.common.model.common.AbstractResponseModel;
import com.itsz.sht.dao.hibernate.model.RtqAccountAssignment;
import com.itsz.sht.dao.hibernate.model.UserProduct;
import com.itsz.sht.dao.hibernate.model.UserProductAllotment;

public class ListSelectServiceResponseModel extends AbstractResponseModel {
	private List<RtqAccountAssignment> rtqAccountAssignmentList;
	
	private List<UserProduct> existingServiceProductList;

	private List<UserProductAllotment> subscriberServiceProductList;

	public List<RtqAccountAssignment> getRtqAccountAssignmentList() {
		return rtqAccountAssignmentList;
	}

	public void setRtqAccountAssignmentList(
			List<RtqAccountAssignment> rtqAccountAssignmentList) {
		this.rtqAccountAssignmentList = rtqAccountAssignmentList;
	}

	public List<UserProduct> getExistingServiceProductList() {
		return existingServiceProductList;
	}

	public void setExistingServiceProductList(
			List<UserProduct> existingServiceProductList) {
		this.existingServiceProductList = existingServiceProductList;
	}

	public List<UserProductAllotment> getSubscriberServiceProductList() {
		return subscriberServiceProductList;
	}

	public void setSubscriberServiceProductList(
			List<UserProductAllotment> subscriberServiceProductList) {
		this.subscriberServiceProductList = subscriberServiceProductList;
	}






}
