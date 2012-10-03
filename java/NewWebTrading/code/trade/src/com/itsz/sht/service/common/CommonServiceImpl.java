package com.itsz.sht.service.common;

import org.apache.commons.lang.StringUtils;
import com.itsz.sht.common.PropertyConfig;
import com.itsz.sht.common.Consts;
import com.itsz.sht.common.PortalUtil;
import com.itsz.sht.common.model.request.SapRequestModel;

/**
 * 
 * $Id: CommonServiceImpl.java,v 1.1 2010/11/09 03:57:35 kyzou Exp $
 * @Project:portal
 * @File:CommonServiceImpl.java
 * @Description:
 * @Author:kyzou
 * @Date:2007-11-5
 */
public class CommonServiceImpl implements CommonService {
	public String callSapResponse(SapRequestModel reqModel){
		StringBuffer reqBuffer = new StringBuffer();
		String private_key = PortalUtil.md5(reqModel.getCustCode() + reqModel.getLoginID());
		String url = PropertyConfig.getProviderName(Consts.Channel.Sap.URL);
		if(StringUtils.isBlank(url)){
			url = Consts.Channel.Sap.DEFURALURL;
		}
		reqBuffer.append(url);
		reqBuffer.append("?system=");
		reqBuffer.append(reqModel.getSystem());
		reqBuffer.append("&cust_code=");
		reqBuffer.append(reqModel.getCustCode());
		reqBuffer.append("&loginID=");
		reqBuffer.append(reqModel.getLoginID());
		reqBuffer.append("&private_key=");
		reqBuffer.append(private_key);
		reqBuffer.append("&lang=");
		reqBuffer.append(reqModel.getLang());
		return reqBuffer.toString();
	}

}
