package com.itsz.sht.common.model.common.response;

import java.util.List;

import com.itsz.sht.common.model.common.AbstractResponseModel;
import com.itsz.sht.dao.hibernate.model.UserProduct;

public class UserProductResponseModel extends AbstractResponseModel{

	List<UserProduct> userProductList;

	public List<UserProduct> getUserProductList() {
		return userProductList;
	}

	public void setUserProductList(List<UserProduct> userProductList) {
		this.userProductList = userProductList;
	}
	
}
