package com.itsz.sht.service.channels.web;

import org.apache.commons.logging.Log;
import com.itsz.sht.common.LoggerFactory;
import com.itsz.sht.common.model.request.LoginRequestModel;
import com.itsz.sht.common.model.response.LoginResponseModel;
import com.itsz.sht.common.util.EmailUtil;
import com.itsz.sht.dao.delegate.FundDepositDelegate;
import com.itsz.sht.dao.delegate.IPOMaintenanceDelegate;
import com.itsz.sht.dao.delegate.IPOQtyAmtDelegate;
import com.itsz.sht.dao.delegate.IPORequestDelegate;
import com.itsz.sht.dao.delegate.IPOResultDelegate;
import com.itsz.sht.service.channels.BaseBusinessTemplate;
import com.itsz.sht.service.channels.RTQService;
import com.itsz.sht.service.mcs.McsService;
import com.itsz.sht.service.qs.QsService;

/**
 * $Id: WebBusinessTemplate.java,v 1.18 2011/01/19 02:46:20 kyzou Exp $
 * @Project:portal.head
 * @File:WebBusinessTemplate.java
 * @Description:
 * @Author:Cimenon Saint
 * @Date:2007-5-25
 */
public class WebBusinessTemplate extends BaseBusinessTemplate {
	
	Log mcsInfo = LoggerFactory.getInstance().getMcsInfo();	
	private McsService mcsService;
	private QsService qsService;
	private RTQService rtqService;
	private IPOMaintenanceDelegate ipoMaintenanceDelegate;
	private IPOQtyAmtDelegate ipoQtyAmtDelegate;
	private IPORequestDelegate ipoRequestDelegate;
	private IPOResultDelegate ipoResultDelegate;
	private FundDepositDelegate fundDepositDelegate;
	private EmailUtil email;
	
	public void setEmail(EmailUtil email) {
		this.email = email;
	}

	public void setFundDepositDelegate(FundDepositDelegate fundDepositDelegate) {
		this.fundDepositDelegate = fundDepositDelegate;
	}

	public void setRtqService(RTQService rtqService) {
		this.rtqService = rtqService;
	}

	public void setIpoMaintenanceDelegate(
			IPOMaintenanceDelegate ipoMaintenanceDelegate) {
		this.ipoMaintenanceDelegate = ipoMaintenanceDelegate;
	}

	public void setIpoQtyAmtDelegate(IPOQtyAmtDelegate ipoQtyAmtDelegate) {
		this.ipoQtyAmtDelegate = ipoQtyAmtDelegate;
	}

	public void setIpoRequestDelegate(IPORequestDelegate ipoRequestDelegate) {
		this.ipoRequestDelegate = ipoRequestDelegate;
	}

	public void setIpoResultDelegate(IPOResultDelegate ipoResultDelegate) {
		this.ipoResultDelegate = ipoResultDelegate;
	}

	public void setMcsService(McsService mcsService) {
		this.mcsService = mcsService;
	}

	public void setQsService(QsService qsService) {
		this.qsService = qsService;
	}
	
	public void init(){
		setBaseMcsService(mcsService);
		setBaseQsService(qsService);
		setBaseRtqService(rtqService);
		setBaseEmail(email);
		setBaseIpoMaintenanceDelegate(ipoMaintenanceDelegate);
		setBaseIpoQtyAmtDelegate(ipoQtyAmtDelegate);
		setBaseIpoRequestDelegate(ipoRequestDelegate);
		setBaseIpoResultDelegate(ipoResultDelegate);
		setBaseFundDepositDelegate(fundDepositDelegate);
	}
	
	public LoginResponseModel login(LoginRequestModel requestModel) {
		return mcsLogin(requestModel);
	}
}