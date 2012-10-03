package com.itsz.sht.service.datas;

import com.itsz.sht.common.model.common.request.AccessRTQRequestModel;
import com.itsz.sht.common.model.common.request.AccessSHKRequestModel;
import com.itsz.sht.common.model.common.response.AccessRTQResponseModel;
import com.itsz.sht.common.model.common.response.AccessSHKResponseModel;

public interface ProductAdminService {
 
	public AccessRTQResponseModel accessRTQ(AccessRTQRequestModel accessRTQRequestModel);
	
	public AccessSHKResponseModel accessSHK(AccessSHKRequestModel accessSHKRequestModel);

}
