package com.itsz.sht.service.common;

import com.itsz.sht.common.model.request.SapRequestModel;

/**
 * 
 * $Id: CommonService.java,v 1.2 2010/11/09 04:07:39 kyzou Exp $
 * @Project:portal
 * @File:CommonService.java
 * @Description:
 * @Author:kyzou
 * @Date:2007-11-5
 */
public interface CommonService {
	public String callSapResponse(SapRequestModel reqModel);

}
