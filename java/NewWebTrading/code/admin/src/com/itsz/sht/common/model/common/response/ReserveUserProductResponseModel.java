package com.itsz.sht.common.model.common.response;

import java.util.List;

import com.itsz.sht.common.model.common.AbstractResponseModel;
import com.itsz.sht.model.ReserveAndRenewalEnquiry;

public class ReserveUserProductResponseModel extends AbstractResponseModel{

	List<ReserveAndRenewalEnquiry> reservedUserProductList;

	public List<ReserveAndRenewalEnquiry> getReservedUserProductList() {
		return reservedUserProductList;
	}

	public void setReservedUserProductList(
			List<ReserveAndRenewalEnquiry> reservedUserProductList) {
		this.reservedUserProductList = reservedUserProductList;
	}
	
	
}
