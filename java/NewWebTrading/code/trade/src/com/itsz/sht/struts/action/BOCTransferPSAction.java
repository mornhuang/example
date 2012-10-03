package com.itsz.sht.struts.action;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
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
import com.itsz.sht.common.DataModelUtil;
import com.itsz.sht.common.ErrorConfig;
import com.itsz.sht.common.ErrorReferenceGenerator;
import com.itsz.sht.common.PropertyConfig;
import com.itsz.sht.common.WebConfig;
import com.itsz.sht.common.WebConfigBuilder;
import com.itsz.sht.common.model.common.BOCTransfer;
import com.itsz.sht.common.model.request.BOCTransferRequestModel;
import com.itsz.sht.common.model.request.PPSEnquiryRequestModel;
import com.itsz.sht.common.model.response.BOCTransferResponseModel;
import com.itsz.sht.common.model.response.PPSEnquiryResponseModel;
import com.itsz.sht.common.user.RTQInfoObject;
import com.itsz.sht.common.user.UserObject;
import com.itsz.sht.exception.BOCTransferException;
import com.itsz.sht.exception.ITSZException;
import com.itsz.sht.exception.WebActionError;
import com.itsz.sht.exception.WebChannelException;
import com.itsz.sht.service.channels.ps.ServiceStatusEnquiryProcessor;
import com.itsz.sht.struts.form.BOCTransferPSForm;
import com.itsz.sht.vp.ViewProvider;
import com.itsz.sht.vp.common.ServiceStatusEnquiryResponse;
import com.taifook.mcs.core.beans.msg.BOCTransferRequest;
import com.taifook.mcs.core.beans.msg.BOCTransferResponse;
import com.taifook.mcs.core.beans.msg.Exception01;
import com.taifook.mcs.msg.MessageSender;

public class BOCTransferPSAction extends ITSZAction {

	 private static Log log_debug = LogFactory.getLog(Constants.LOG_DEBUG_MCS);
	 protected static final String SUCCESS_KEY = "success";
	 protected static final String FAILURE_KEY = "failure";
	 
	 public ActionForward executeAction(ViewProvider vp, ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
	 	log_debug.info("boc transfer action execute()");
	 	try {
            ITSZAction.sessionManagement(request);
        } catch (ITSZException e1) {
            log_debug.info("session time out");
            return mapping.findForward("epayment_session_overtime");
        }	 	
        HttpSession session = request.getSession();
        synchronized (session.getId()) {
            String requestToken = request.getParameter(Consts.Boc.REQUEST_TOKEN_KEY);
            String currentToken = (String) session.getAttribute(Consts.Boc.CURRENT_TOKEN_KEY);          
            session.removeAttribute(Consts.Boc.FORM_KEY);
            session.removeAttribute(Consts.Global.Common.EXCEPTION_KEY);
            session.removeAttribute(Consts.Boc.FORWARD_KEY);
            session.removeAttribute(Consts.Boc.ERRORS_KEY);

            session.setAttribute(Consts.Boc.CURRENT_TOKEN_KEY, requestToken);
            ActionForward forward;
            try {
                forward = performWhenTokenValid(mapping, form,
                    request, response);
            } catch (WebChannelException webEx) {
                log_debug.info("boc delegate throw exception");
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
	
	 protected ActionForward performWhenTokenValid(
 		ActionMapping mapping,
 		ActionForm _form,
 		HttpServletRequest request,
 		HttpServletResponse response)	 		
 		throws WebChannelException {

		 BOCTransferPSForm form = (BOCTransferPSForm) _form;
	 		ActionErrors errors = new ActionErrors();

	 		//check service status and operation period
	 		Date today = new Date();
	 		ServiceStatusEnquiryResponse serviceStatusEnquiryResponse =
	 			ServiceStatusEnquiryProcessor.getServiceStatus(
                       Consts.Epayment.FundTransfer.SERVICE_NAME_BOC);

	 		if (Consts.Epayment.FundTransfer.SERVICE_STATUS_SUSPEND
	 			.equals(serviceStatusEnquiryResponse.getStatus())) {
	 			form.setRemarks(getRemarks(request, serviceStatusEnquiryResponse));
	 			return mapping.findForward("suspend");
	 		} else if (RTQInfoObject.isHoliday(new Date())) {
	 			form.setRemarks(getRemarks(request, serviceStatusEnquiryResponse));
	 			return mapping.findForward("nonoperate");
	 		} else if (
                   Consts.Epayment.FundTransfer.SERVICE_STATUS_ACTIVE.equals(
	 				serviceStatusEnquiryResponse.getStatus())
	 				&& !serviceStatusEnquiryResponse.IsWithinValidPeriod()) {
	 			form.setRemarks(getRemarks(request, serviceStatusEnquiryResponse));
	 			return mapping.findForward("nonoperate");
	 		} else if (
                   Consts.Epayment.FundTransfer.SERVICE_STATUS_INACTIVE.equals(
	 				serviceStatusEnquiryResponse.getStatus())) {

	 			log_debug.info("BOC is INACTIVE");
	 			request.getSession().setAttribute(Consts.Epayment.FundTransfer.SERVICE_NAME_BOC, Consts.Epayment.FundTransfer.SERVICE_STATUS_INACTIVE);

	 			return mapping.findForward("inactive");
	 		}

	 		//check transfer limit
	 		BigDecimal amount = new BigDecimal(form.getMch_payAmt());
	 		Map paramMap =
	 			ServiceStatusEnquiryProcessor.getServiceParams(
                       Consts.Epayment.FundTransfer.SERVICE_NAME_BOC);
	 		BigDecimal minTransferValue =
	 			(BigDecimal) paramMap.get(
                       Consts.Epayment.FundTransfer.SERVICE_PARAM_MIN_TX_VALUE);
	 		BigDecimal maxTransferValue =
	 			(BigDecimal) paramMap.get(
                       Consts.Epayment.FundTransfer.SERVICE_PARAM_MAX_TX_VALUE);
	 		if ((1 == minTransferValue.compareTo(amount))
	 			|| (-1 == maxTransferValue.compareTo(amount))) {
	 			throw new BOCTransferException(
	 				"not within transfer limit range",
	 				"errors.bocTransferForm.transferLimit",
	 				new Object[] { minTransferValue, maxTransferValue });
	 		}
	 		
			UserObject user =(UserObject) request.getSession().getAttribute(Constants.USER);
			BOCTransferResponse res = new BOCTransferResponse();
	 		try {
	 			BOCTransferRequestModel model = new BOCTransferRequestModel();
				DataModelUtil.form2Model(request,form, model, response);
				if (user != null && user.getTradeInfoObject() != null){
				    model.setAccountId(form.getMch_custID());
				    model.setPayAmount(amount);
				    model.setTransactionDateTime(getBOCTimestamp(today));
				    model.setLocale(getBOCLocale(request));
				}
				BOCTransferResponseModel responseModel = facade.bocTransfer(model);

				if(responseModel.getReturnCode().equals(Consts.Login.Status.LONGIN_STATUS_NORMAL)){			
					res = (BOCTransferResponse) responseModel.getBocTransferResponse();
					log_debug.info("RefCode0" + res.getTransactionRefCode());
				}
			} catch (Exception ex) {
	 			throw new BOCTransferException(
	 				"enquiry mcs failed",
	 				"errors.bocTransfer.unavailable",
	 				new Object[]{});
			}
	 		
	 		String bocReturnUrl = PropertyConfig.getCommonProperty(Constants.BOCReturnURL);
	 		if (errors.isEmpty()) {
	 			form.setMch_merchID(Consts.Boc.MERCH_ID);
	 			form.setMch_merchRef(res.getTransactionRefCode());
//	 			form.setMch_merchRef(dto.getRefCode());
	 			form.setMch_locale(getBOCLocale(request));
	 			form.setMch_timeStamp(getBOCTimestamp(today));
	 			form.setMch_returnURL(bocReturnUrl);
	 			form.setMch_custID(form.getMch_custID().trim().replaceAll("-", ""));
	 			return mapping.findForward(SUCCESS_KEY);
	 		} else {
	 			saveErrors(request, errors);
	 			return mapping.findForward(FAILURE_KEY);
	 		}
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
		SimpleDateFormat dateFormat = new SimpleDateFormat(Consts.DateTime.Format.Pattern.yyyyMMddHHmmss);

		return dateFormat.format(today);
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
            System.out.println("error reference "+errorReference);
        }catch(Exception e){
//            e.printStackTrace();
            log_debug.error("exception exist:"+e.getMessage());
        }

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