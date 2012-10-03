package com.itsz.sht.common.model.common.response;



import com.itsz.sht.common.model.common.AbstractResponseModel;
import com.taifook.adminportal.common.util.page.Page;

public class ReserveAndRenewalEnquiryResponseModel extends AbstractResponseModel{

	public Page page;

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}
	
}
