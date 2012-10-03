package com.itsz.sht.common.model.common.response;

import com.itsz.sht.common.model.common.AbstractResponseModel;
import com.taifook.adminportal.common.util.page.Page;

public class ListUserPaymentByManualResponseModel extends AbstractResponseModel {
	private Page page;

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}
	
	
}
