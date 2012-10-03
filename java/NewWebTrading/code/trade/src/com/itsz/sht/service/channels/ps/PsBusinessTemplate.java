package com.itsz.sht.service.channels.ps;


import com.itsz.sht.common.model.request.LoginRequestModel;
import com.itsz.sht.common.model.response.LoginResponseModel;
import com.itsz.sht.service.channels.BaseBusinessTemplate;
import com.itsz.sht.service.mcs.McsService;
import com.itsz.sht.service.qs.QsService;

/**
 * $Id: PsBusinessTemplate.java,v 1.10 2011/03/03 10:36:26 pbxie Exp $
 * @Project:portal.head
 * @File:PsBusinessTemplate.java
 * @Description:
 * @Author:Cimenon Saint
 * @Date:2007-5-25
 */
public class PsBusinessTemplate extends BaseBusinessTemplate {
	private McsService mcsService;
	private QsService qsService;

	public void setMcsService(McsService mcsService) {
		this.mcsService = mcsService;
	}

	public void setQsService(QsService qsService) {
		this.qsService = qsService;
	}
	
	public void init(){
		setBaseMcsService(mcsService);
		setBaseQsService(qsService);
	}
	
	public LoginResponseModel login(LoginRequestModel loginModel) {
		return super.login(loginModel);
	}

}
