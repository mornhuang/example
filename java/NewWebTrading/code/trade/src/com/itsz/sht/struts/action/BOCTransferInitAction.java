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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.itsz.sht.common.CLVSplitUtil;
import com.itsz.sht.common.Constants;
import com.itsz.sht.common.Consts;
import com.itsz.sht.common.LocaleUtil;
import com.itsz.sht.common.user.AcEnquiryInfo;
import com.itsz.sht.common.user.RTQInfoObject;
import com.itsz.sht.common.user.UserObject;
import com.itsz.sht.exception.ITSZException;
import com.itsz.sht.exception.ServiceStatusEnquiryException;
import com.itsz.sht.service.channels.ps.ServiceStatusEnquiryProcessor;
import com.itsz.sht.struts.form.BOCTransferForm;
import com.itsz.sht.struts.form.BOCTransferPSForm;
import com.itsz.sht.vp.common.ServiceStatusEnquiryResponse;

public class BOCTransferInitAction extends Action {

	private static Log log_debug = LogFactory.getLog(Constants.LOG_DEBUG_MCS);

	/*
     * (non-Javadoc)
     *
     * @see com.taifook.mtss.web.common.ext.WebAction#executeAction(org.apache.struts.action.ActionMapping,
     *      org.apache.struts.action.ActionForm,
     *      javax.servlet.http.HttpServletRequest,
     *      javax.servlet.http.HttpServletResponse,
     *      com.taifook.mtss.web.common.config.WebConfig)
     */
    public ActionForward execute(ActionMapping mapping, ActionForm _form,
            HttpServletRequest request, HttpServletResponse response) {
        log_debug.info("boc transfer init action execute()");
        String clv = request.getParameter("CLV");
        try {
            ITSZAction.sessionManagement(request);
        } catch (ITSZException e1) {
            log_debug.info("session time out");
            request.setAttribute("CLV",clv);
            return mapping.findForward("epayment_session_overtime");
        }

        //����û��������趨��ʾ������
        String language = CLVSplitUtil.getLanguage(clv);
        if("".equals(language)){
        	language = "BIG5";
        }
        if(!(clv==null)){
            LocaleUtil.setLocale(request,language);
        }
        saveToken(request);
        BOCTransferPSForm form = (BOCTransferPSForm) _form;

        //check service status and operation period
        ServiceStatusEnquiryResponse serviceStatusEnquiryResponse;
        try {
            serviceStatusEnquiryResponse = ServiceStatusEnquiryProcessor
                    .getServiceStatus(Consts.Epayment.FundTransfer.SERVICE_NAME_BOC);
        //if(true) throw new ServiceStatusEnquiryException();//for test
        } catch (ServiceStatusEnquiryException e) {
            log_debug.info("Enquiry BOC Service Status failure");
            request.getSession().setAttribute(
                    Consts.Epayment.FundTransfer.SERVICE_NAME_BOC,
                    Consts.Epayment.FundTransfer.SERVICE_STATUS_INACTIVE);
            return mapping.findForward("inactive");
        }
        //if(true){//for test
        if (Consts.Epayment.FundTransfer.SERVICE_STATUS_SUSPEND
                .equals(serviceStatusEnquiryResponse.getStatus())) {
            form.setRemarks(getRemarks(request, serviceStatusEnquiryResponse));
            return mapping.findForward("suspend");
        //} else if (HolidayCheckHelper.isPublicHoliday(new Date())) {
        } else if (RTQInfoObject.isHoliday(new Date())) {
            form.setRemarks(getRemarks(request, serviceStatusEnquiryResponse));
            return mapping.findForward("nonoperate");
        } else if (Consts.Epayment.FundTransfer.SERVICE_STATUS_ACTIVE
                .equals(serviceStatusEnquiryResponse.getStatus())
                && !serviceStatusEnquiryResponse.IsWithinValidPeriod()) {
            form.setRemarks(getRemarks(request, serviceStatusEnquiryResponse));
            return mapping.findForward("nonoperate");
        } else if (Consts.Epayment.FundTransfer.SERVICE_STATUS_INACTIVE
                .equals(serviceStatusEnquiryResponse.getStatus())) {
            log_debug.info("BOC is INACTIVE");
            request.getSession().setAttribute(
                    Consts.Epayment.FundTransfer.SERVICE_NAME_BOC,
                    Consts.Epayment.FundTransfer.SERVICE_STATUS_INACTIVE);
            return mapping.findForward("inactive");
        }

        UserObject user = (UserObject) request.getSession().getAttribute(
                Constants.USER);
        List accounts = null;
        try {
            accounts = (List) user.getTradeInfoObject().getAcEnquiryList(request);
        } catch (Exception e) {
            log_debug.info("user getAcEnquiry is null");
        }
        if (accounts == null) {
            accounts = new ArrayList();
            //brief account info should have retrieved at logon
        }
        List newList = new ArrayList();
        for (int i = 0; i < accounts.size(); i++) {
            AcEnquiryInfo accountInfo = (AcEnquiryInfo) accounts.get(i);
            if (!isAccountOutOfCompany(accountInfo.getAccountId())) {
                newList.add(accountInfo);
            }
        }
        form.setFromAccounts(newList);
        log_debug.debug("forward to input page");
        String forward="bocForward";
        //return mapping.getInputForward();
        return mapping.findForward(forward);

    }
    
    public boolean isAccountOutOfCompany(String accountId) {
        boolean result = true;
        if (accountId != null && accountId.length() > 2) {
            if (Consts.Global.Common.TAIFOOK_SECURITIES.equals(accountId.substring(0, 2))) {
                result = false;
            }
        }
        return result;
    }

	protected String getRemarks(
		HttpServletRequest request,
		ServiceStatusEnquiryResponse serviceStatusEnquiryResponse) {
		Locale locale = getLocale(request);
		if (Locale.TRADITIONAL_CHINESE.equals(locale)) {
			return serviceStatusEnquiryResponse.getRemarks_zh_TW();
		} else if (Locale.SIMPLIFIED_CHINESE.equals(locale)) {
			return serviceStatusEnquiryResponse.getRemarks_zh_CN();
		} else {
			return serviceStatusEnquiryResponse.getRemarks_en_US();
		}
	}
}
