/*
 *  Copyright (c) 2004 Tai Fook Securities Group Limited.
 *  All rights reserved.
 *
 *  This file contains the valuable properties of Tai Fook Securities
 *  Group Limited, embodying substantial creative efforts and confidential
 *  information, ideas and expressions. No part of this file may be
 *  reproduced or distributed in any form or by any means, or stored
 *  in a data base or a retrieval system, without the prior written
 *  permission of Tai Fook Securities Group Limited.
 *
 */
package com.itsz.sht.struts.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.itsz.sht.common.Constants;
import com.itsz.sht.common.Consts;
import com.itsz.sht.common.DataModelUtil;
import com.itsz.sht.common.PropertyConfig;
import com.itsz.sht.common.model.request.BOCTransferRequestModel;
import com.itsz.sht.common.model.response.BOCTransferResponseModel;
import com.itsz.sht.common.user.UserObject;
import com.itsz.sht.struts.form.BOCTransferForm;
import com.itsz.sht.vp.ViewProvider;
import com.itsz.sht.vp.common.ProcessBean;
import com.taifook.adminportal.common.util.DataFormatUtil;


/**
 * @struts.action
 *    name="bocTransferForm"
 *    path="/bocTransfer"
 *    scope="request"
 *    validate="true"
 *
 * @struts.action-forward
 *    name="success"
 *    path="/jsp/transfer/boc_forward.jsp"
 *    redirect="false"
 *
 * @struts.action-forward
 *    name="failure"
 *    path="/bocTransferInit.do"
 *    redirect="false"
 *
 * @struts.action-forward
 *    name="suspend"
 *    path="/jsp/transfer/boc_suspend.jsp"
 *    redirect="false"
 *
 * @struts.action-forward
 *    name="nonoperate"
 *    path="/jsp/transfer/boc_nonoperate.jsp"
 *    redirect="false"
 *
 * @struts.action-forward
 *    name="inactive"
 *    path="/redirect_main.do"
 *    redirect="false"
 *
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: Taifook Securities Groups</p>
 * @author Henry Kwong
 * @version $Id: BOCTransferAction.java,v 1.5 2011/01/04 02:00:11 xlliu Exp $
 */

public class BOCTransferAction extends ITSZAction {
	
	public ActionForward executeAction(
		ViewProvider vp,
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response) {
		BOCTransferRequestModel model = new BOCTransferRequestModel();
		DataModelUtil.form2Model(request,(BOCTransferForm) form, model, response);		
		UserObject user = (UserObject) request.getSession().getAttribute(Constants.USER);
		if (user != null && user.getTradeInfoObject() != null){
		    model.setAccountId(user.getTradeInfoObject().getTradingAccount());
			model.setLocale(getBOCLocale(request));
			model.setTransactionDateTime(getBOCTimestamp(new Date()));
		}
		BOCTransferResponseModel responseModel = facade.bocTransfer(model);
		responseModel.setMch_merchID(Consts.Boc.MERCH_ID);
		responseModel.setMch_locale(model.getLocale());
		responseModel.setMch_timeStamp(model.getTransactionDateTime());
		responseModel.setMch_returnURL(PropertyConfig.getCommonProperty(Constants.BOCReturnURL));
		responseModel.setItsHandlerURL(PropertyConfig.getCommonProperty(Constants.ItsHandlerURL));
		responseModel.setMch_payAmt(DataFormatUtil.formatAmt(model.getPayAmount()));
		responseModel.setMch_custID(model.getAccountId().trim().replaceAll("-", ""));
		ProcessBean processBean = new ProcessBean(responseModel,null, mapping, request,response);
		return vp.processBOCTransfer(processBean);
	}
	
	protected String getBOCLocale(HttpServletRequest request) {
		boolean isChinese = false;
		Locale locale = getLocale(request);
		if (locale != null) {
			if (Locale
				.TRADITIONAL_CHINESE
				.getLanguage()
				.equals(locale.getLanguage())) {
				isChinese = true;
			}

			if (Locale
				.SIMPLIFIED_CHINESE
				.getLanguage()
				.equals(locale.getLanguage())) {
				isChinese = true;
			}
		}

		return (isChinese ? Consts.Boc.LOCALE_CHI : Consts.Boc.LOCALE_ENG);
	}

	protected String getBOCTimestamp(Date today) {
		SimpleDateFormat dateFormat =
			new SimpleDateFormat(Consts.Boc.TIMESTAMP_FORMAT);

		return dateFormat.format(today);
	}

//	protected String getRemarks(
//		HttpServletRequest request,
//		ServiceStatusEnquiryResponse serviceStatusEnquiryResponse) {
//		Locale locale = getLocale(request);
//		if (Locale.TRADITIONAL_CHINESE.equals(locale)) {
//			return serviceStatusEnquiryResponse.getRemarks_zh_TW();
//		} else if (Locale.SIMPLIFIED_CHINESE.equals(locale)) {
//			return serviceStatusEnquiryResponse.getRemarks_zh_CN();
//		} else if (Locale.JAPANESE.equals(locale)) {
//			return serviceStatusEnquiryResponse.getRemarks_ja_JP();
//		}  else {
//			return serviceStatusEnquiryResponse.getRemarks_en_US();
//		}
//	}
}