package com.itsz.sht.service.channels;

import com.itsz.sht.common.model.common.request.AccessRTQRequestModel;
import com.itsz.sht.common.model.common.request.AccessSHKRequestModel;
import com.itsz.sht.common.model.common.response.AccessRTQResponseModel;
import com.itsz.sht.common.model.common.response.AccessSHKResponseModel;
import com.itsz.sht.service.datas.ProductAdminService;

public class ProductServiceImpl implements ProductService{

	private ProductAdminService productAdminService;

	public ProductAdminService getProductAdminService() {
		return productAdminService;
	}

	public void setProductAdminService(ProductAdminService productAdminService) {
		this.productAdminService = productAdminService;
	}
	
	@Override
	public AccessRTQResponseModel accessRTQ(
			AccessRTQRequestModel accessRTQRequestModel) {
		return productAdminService.accessRTQ(accessRTQRequestModel);
	}

	@Override
	public AccessSHKResponseModel accessSHK(
			AccessSHKRequestModel accessSHKRequestModel) {
		return productAdminService.accessSHK(accessSHKRequestModel);
	}
}
	
