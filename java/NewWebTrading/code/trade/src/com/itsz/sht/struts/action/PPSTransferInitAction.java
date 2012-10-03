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
import com.itsz.sht.struts.form.PPSTransferForm;
import com.itsz.sht.vp.common.ServiceStatusEnquiryResponse;

/**
 * 
 * $Id: PPSTransferInitAction.java,v 1.1 2011/03/02 10:30:36 pbxie Exp $
 * @Project:portal
 * @File:PPSTransferInitAction.java
 * @Description:
 * @Author:Cimenon Saint
 * @Date:2007-7-2
 */
public class PPSTransferInitAction extends Action {
    private static Log log_debug = LogFactory.getLog(Constants.LOG_DEBUG_MCS);

    public ActionForward execute(ActionMapping mapping, ActionForm _form,
            HttpServletRequest request, HttpServletResponse response) {
        log_debug.info("pps transfer init action execute()");
        String clv = request.getParameter("CLV");
        try {
            ITSZAction.sessionManagement(request);
        } catch (ITSZException e) {
            log_debug.info("sesssion time out");
            request.setAttribute("CLV",clv);
            return mapping.findForward("epayment_session_overtime");
        }

        //����û��������趨��ʾ������?
        String language = CLVSplitUtil.getLanguage(clv);
        if("".equals(language)){
        	language = "BIG5";
        }
        if(!(clv==null)){
            LocaleUtil.setLocale(request,language);
        }

        PPSTransferForm form = (PPSTransferForm) _form;

        //check service status and operation period
        ServiceStatusEnquiryResponse serviceStatusEnquiryResponse = null;
        try {
            serviceStatusEnquiryResponse = ServiceStatusEnquiryProcessor
                    .getServiceStatus(Consts.Epayment.FundTransfer.SERVICE_NAME_PPS);
          //if(true) throw new ServiceStatusEnquiryException(); //for test
        } catch (ServiceStatusEnquiryException e) {
            log_debug.info("Enquiry PPS Service Status failure");
            request.getSession().setAttribute(
                    Consts.Epayment.FundTransfer.SERVICE_NAME_PPS,
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

            log_debug.info("PPS is INACTIVE");
            request.getSession().setAttribute(
                    Consts.Epayment.FundTransfer.SERVICE_NAME_PPS,
                    Consts.Epayment.FundTransfer.SERVICE_STATUS_INACTIVE);

            return mapping.findForward("inactive");
        }

        UserObject user = (UserObject) request.getSession().getAttribute(Constants.USER);
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
        log_debug.debug("forward to active page");
        String forward="ppsForward";
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
