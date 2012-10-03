package com.itsz.sht.common.model.common.request;

import java.util.List;

import com.itsz.sht.common.model.common.AbstractRequestModel;
import com.itsz.sht.model.UserProductRequest;

public class UpdateUserProductRequestModel extends AbstractRequestModel{

	private List<UserProductRequest> userProductRequestList;

	public List<UserProductRequest> getUserProductRequestList() {
		return userProductRequestList;
	}

	public void setUserProductRequestList(
			List<UserProductRequest> userProductRequestList) {
		this.userProductRequestList = userProductRequestList;
	}

}
