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


import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.itsz.sht.common.Constants;
import com.itsz.sht.common.Consts;
import com.itsz.sht.common.DataModelUtil;
import com.itsz.sht.common.model.request.PPSEnquiryRequestModel;
import com.itsz.sht.common.model.response.PPSEnquiryResponseModel;
import com.itsz.sht.common.user.UserObject;
import com.itsz.sht.struts.form.PPSEnquiryForm;
import com.itsz.sht.vp.ViewProvider;
import com.itsz.sht.vp.common.ProcessBean;

/**
 * @struts.action
 *    name="ppsEnquiryForm"
 *    path="/ppsEnquiry"
 *    scope="request"
 *    validate="false"
 *    input="/jsp/transfer/mkenq.jsp"
 * 
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: Taifook Securities Groups</p>
 * @author Henry Kwong
 * @version $Id: PPSEnquiryAction.java,v 1.5 2011/01/04 08:31:49 xlliu Exp $
 */
public class PPSEnquiryAction extends ITSZAction {
	
	public ActionForward executeAction(
		ViewProvider vp,
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response) {
		PPSEnquiryRequestModel model = new PPSEnquiryRequestModel();
		DataModelUtil.form2Model(request,(PPSEnquiryForm) form, model, response);
		UserObject user = (UserObject) request.getSession().getAttribute(Constants.USER);
		if (user != null && user.getTradeInfoObject() != null){
		    model.setAccountId(user.getLoginName());
		    model.setLoginId(user.getLoginName());
		}
		PPSEnquiryResponseModel responseModel = facade.ppsEnquiry(model);
		responseModel.setLang(getLang(request));
		responseModel.setLocale(getLocaleStr(request));
		responseModel.setOpCode("03");
		ProcessBean processBean = new ProcessBean(responseModel,null, mapping, request,response);
		return vp.processPPSEnquiry(processBean);
	}
	
	protected String getLang(HttpServletRequest request) {
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
		return (isChinese ? Consts.Epayment.FundTransfer.PPS.LOCALE_CHI : Consts.Epayment.FundTransfer.PPS.LOCALE_ENG);
	}

	protected String getLocaleStr(HttpServletRequest request) {
		Locale locale = getLocale(request);
		if (locale != null) {
			if (Locale
				.TRADITIONAL_CHINESE
				.getLanguage()
				.equals(locale.getLanguage())) {
				return "Chinese";
			}

			if (Locale
				.SIMPLIFIED_CHINESE
				.getLanguage()
				.equals(locale.getLanguage())) {
				return "Chinese";
			}
		}

		return "English";
	}
}
