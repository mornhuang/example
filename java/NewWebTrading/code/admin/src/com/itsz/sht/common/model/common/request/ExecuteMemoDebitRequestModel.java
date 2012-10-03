package com.itsz.sht.common.model.common.request;

import java.util.List;

import com.itsz.sht.common.model.common.AbstractRequestModel;
import com.itsz.sht.dao.hibernate.model.UserProductAllotment;

public class ExecuteMemoDebitRequestModel extends AbstractRequestModel{
	 public List<UserProductAllotment> userProductAllotmentList;
	public String  actionBy;

	public List<UserProductAllotment> getUserProductAllotmentList() {
		return userProductAllotmentList;
	}

	public void setUserProductAllotmentList(
			List<UserProductAllotment> userProductAllotmentList) {
		this.userProductAllotmentList = userProductAllotmentList;
	}

	public String getActionBy() {
		return actionBy;
	}

	public void setActionBy(String actionBy) {
		this.actionBy = actionBy;
	}
	

}
