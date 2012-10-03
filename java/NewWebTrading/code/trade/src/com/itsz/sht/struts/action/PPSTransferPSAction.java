package com.itsz.sht.struts.action;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.Globals;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.itsz.sht.common.Constants;
import com.itsz.sht.common.Consts;
import com.itsz.sht.common.ErrorConfig;
import com.itsz.sht.common.ErrorReferenceGenerator;
import com.itsz.sht.common.WebConfig;
import com.itsz.sht.common.WebConfigBuilder;
import com.itsz.sht.common.user.RTQInfoObject;
import com.itsz.sht.common.user.UserObject;
import com.itsz.sht.exception.ITSZException;
import com.itsz.sht.exception.PPSTransferException;
import com.itsz.sht.exception.WebActionError;
import com.itsz.sht.exception.WebChannelException;
import com.itsz.sht.service.channels.ps.ServiceStatusEnquiryProcessor;
import com.itsz.sht.struts.form.PPSTransferForm;
import com.itsz.sht.vp.common.ServiceStatusEnquiryResponse;


/**
 * 
 * $Id: PPSTransferPSAction.java,v 1.1 2011/03/22 02:01:51 pbxie Exp $
 * @Project:portal
 * @File:PPSTransferAction.java
 * @Description:
 * @Author:
 * @Date:2007-11-13
 */
public class PPSTransferPSAction extends Action {
    private static Log log_debug = LogFactory.getLog(Constants.LOG_DEBUG_MCS);
    protected static final String SUCCESS_KEY = "success";
	protected static final String FAILURE_KEY = "failure";
	
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        log_debug.info("pps transfer action execute()");
        try {
            ITSZAction.sessionManagement(request);
        } catch (ITSZException e1) {
            log_debug.info("session time out");
            return mapping.findForward("epayment_session_overtime");
        }
        HttpSession session = request.getSession();
        synchronized (session.getId()) {
            String requestToken = request.getParameter(Consts.Boc.REQUEST_TOKEN_KEY);
//            String currentToken = (String) session.getAttribute(Constants.CURRENT_TOKEN_KEY);
            session.removeAttribute(Consts.Boc.FORM_KEY);
            session.removeAttribute(Consts.Global.Common.EXCEPTION_KEY);
            session.removeAttribute(Consts.Boc.FORWARD_KEY);
            session.removeAttribute(Consts.Boc.ERRORS_KEY);
            session.setAttribute(Consts.Boc.CURRENT_TOKEN_KEY, requestToken);
            ActionForward forward = null;
            try {
                forward = performWhenTokenValid(mapping, form,
                    request, response);                                                
            } 
            catch (WebChannelException webEx) {
                forward = processException(request, mapping, webEx);
            }
            // Keep the form resulting from the action.
            session.setAttribute(Consts.Boc.FORM_KEY, form);
            // Keep the forward resulting from the action.
            session.setAttribute(Consts.Boc.FORWARD_KEY, forward);

            // Keep the errors resulting from the action.
            session.setAttribute(Consts.Boc.ERRORS_KEY,request.getAttribute(Globals.ERROR_KEY));
            return forward;
        }
    }
  
	/* (non-Javadoc)
	 * @see com.taifook.mtss.web.common.ext.WebTokenCheckingAction#performWhenTokenValid(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, com.taifook.mtss.web.common.config.WebConfig)
	 */
	protected ActionForward performWhenTokenValid(
		ActionMapping mapping,
		ActionForm _form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws WebChannelException {

		saveToken(request);
		PPSTransferForm form = (PPSTransferForm) _form;
		ActionErrors errors = new ActionErrors();
		//check service status and operation period
		ServiceStatusEnquiryResponse serviceStatusEnquiryResponse =
			ServiceStatusEnquiryProcessor.getServiceStatus(
                    Consts.Epayment.FundTransfer.SERVICE_NAME_PPS);
		if (Consts.Epayment.FundTransfer
			.SERVICE_STATUS_SUSPEND
			.equals(serviceStatusEnquiryResponse.getStatus())) {
			form.setRemarks(getRemarks(request, serviceStatusEnquiryResponse));
			log_debug.info("PPS service is suspended");
			return mapping.findForward("suspend");
		//} else if (HolidayCheckHelper.isPublicHoliday(new Date())) {
		} else if (RTQInfoObject.isHoliday(new Date())) {
			form.setRemarks(getRemarks(request, serviceStatusEnquiryResponse));
			log_debug.info("PPS service is not available because of public holiday");
			return mapping.findForward("nonoperate");
		} else if (
                Consts.Epayment.FundTransfer.SERVICE_STATUS_ACTIVE.equals(
				serviceStatusEnquiryResponse.getStatus())
				&& !serviceStatusEnquiryResponse.IsWithinValidPeriod()) {
			form.setRemarks(getRemarks(request, serviceStatusEnquiryResponse));
			log_debug.info("PPS service is not available because of service not in valid period");
			return mapping.findForward("nonoperate");
		} else if (
                Consts.Epayment.FundTransfer.SERVICE_STATUS_INACTIVE.equals(
				serviceStatusEnquiryResponse.getStatus())) {
		    log_debug.info("PPS is INACTIVE");
			request.getSession().setAttribute(Consts.Epayment.FundTransfer.SERVICE_NAME_PPS, Consts.Epayment.FundTransfer.SERVICE_STATUS_INACTIVE);
			return mapping.findForward("inactive");
		}
		log_debug.info("PPS service is available");
		//check transfer limit
		BigDecimal amount = new BigDecimal(form.getTamount());
		Map paramMap =
			ServiceStatusEnquiryProcessor.getServiceParams(
                    Consts.Epayment.FundTransfer.SERVICE_NAME_PPS);
		BigDecimal minTransferValue =
			(BigDecimal) paramMap.get(
                    Consts.Epayment.FundTransfer.SERVICE_PARAM_MIN_TX_VALUE);
		BigDecimal maxTransferValue =
			(BigDecimal) paramMap.get(
                    Consts.Epayment.FundTransfer.SERVICE_PARAM_MAX_TX_VALUE);
		if ((1 == minTransferValue.compareTo(amount))
			|| (-1 == maxTransferValue.compareTo(amount))) {
			throw new PPSTransferException(
				"not within transfer limit range",
				"errors.ppsTransferForm.transferLimit",
				new Object[] { minTransferValue, maxTransferValue });
		}

		//logging
		if (errors.isEmpty()) {
			UserObject user =
				(UserObject) request.getSession().getAttribute(Constants.USER);

			form.setUser_id(user.getLoginName());
			form.setLang(getPPSLocale(request));
			form.setToken((String) request.getSession().getAttribute(Globals.TRANSACTION_TOKEN_KEY));
//			{
//				log_debug.info("submit to PPS with attributes:");
//				log_debug.info("lang=" + form.getLang());
//				log_debug.info("user_id=" + form.getUser_id());
//				log_debug.info("token=" + form.getToken());
//				log_debug.info("ac=" + form.getAc());
//				log_debug.info("tamount=" + form.getTamount());
//			}
			return mapping.findForward(SUCCESS_KEY);
		} else {
			saveErrors(request, errors);
			return mapping.findForward(FAILURE_KEY);
		}
	}

	protected String getPPSLocale(HttpServletRequest request) {
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

		return (isChinese ?Consts.Epayment.FundTransfer.PPS.LOCALE_CHI : Consts.Epayment.FundTransfer.PPS.LOCALE_ENG);
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

	protected ActionForward processException(HttpServletRequest request,
            ActionMapping mapping, WebChannelException webEx) {

        log_debug.error("*************************************************");
        log_debug.error(webEx.getClass());
        log_debug.error(webEx.getMessageKey());
        log_debug.error(webEx.getMessage());
        WebConfig webConfig = WebConfigBuilder.instance().buildWebConfig();
        ErrorConfig errorConfig = null;
        String errorReference = null;

        errorConfig = webConfig.getErrConfigByExceptionName(webEx
                .getClass().getName());
        try{
            ErrorReferenceGenerator errorReferenceGenerator = ErrorReferenceGenerator.instance();
            errorReferenceGenerator.init(webConfig);
            errorReference = errorReferenceGenerator.getErrorRef();
        }catch(Exception e){
//            e.printStackTrace();
            log_debug.error("exception exist:"+e.getMessage());
        }
        log_debug.warn(webEx.getMessage(), webEx);

        ActionErrors errors = new ActionErrors();
        WebActionError error = new WebActionError(webEx.getMessageKey(), webEx
                .getArgs());
        if (errorConfig != null) {
            error.setException(webEx);
            error.setErrorCode(errorConfig.getErrorCode());
            error.setReferenceNo(errorReference);
            error.setIsErrorCodeDisplayed(errorConfig.isIsErrorCodeDisplayed());
            error.setIsReferenceNoDisplayed(errorConfig.isIsErrorRefDisplayed());                                             
        }
        errors.add(ActionErrors.GLOBAL_ERROR, error);
        ActionForward forward = null;
        if (mapping.getInput() != null) {
            forward = new ActionForward(mapping.getInput());
        } else {
            forward = mapping.findForward(FAILURE_KEY);
        }
        saveErrors(request, errors);
        return forward;
    }

}
