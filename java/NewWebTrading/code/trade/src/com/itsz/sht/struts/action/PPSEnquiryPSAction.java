package com.itsz.sht.struts.action;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
import com.itsz.sht.common.WebConfig;
import com.itsz.sht.common.WebConfigBuilder;
import com.itsz.sht.common.model.common.PPSRecord;
import com.itsz.sht.common.model.request.PPSEnquiryRequestModel;
import com.itsz.sht.common.model.response.PPSEnquiryResponseModel;
import com.itsz.sht.common.user.UserObject;
import com.itsz.sht.exception.ITSZException;
import com.itsz.sht.exception.PPSTransferException;
import com.itsz.sht.exception.WebActionError;
import com.itsz.sht.exception.WebChannelException;
import com.itsz.sht.struts.form.PPSEnquiryForm;
import com.itsz.sht.vp.ViewProvider;
import com.taifook.mcs.core.beans.msg.PPSTransferDetail;
import com.taifook.mcs.core.beans.msg.PPSTransferDetailResponse;

//import com.taifook.mtss.web.common.config.ErrorConfig;
//import com.taifook.mtss.web.common.config.WebConfig;
//import com.taifook.mtss.web.common.config.WebConfigBuilder;
//import com.taifook.mtss.web.common.exception.WebChannelException;
//import com.taifook.mtss.web.common.util.ErrorReferenceGenerator;

public class PPSEnquiryPSAction extends ITSZAction {
    private static Log log_debug = LogFactory.getLog(Constants.LOG_DEBUG_MCS);
    protected static final String SUCCESS_KEY = "success";
	protected static final String FAILURE_KEY = "failure"; 
	/* (non-Javadoc)
	 * @see com.taifook.mtss.web.common.ext.WebAction#executeAction(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, com.taifook.mtss.web.common.config.WebConfig)
	 */
	public ActionForward executeAction(
			ViewProvider vp,
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) {
	    log_debug.info("PPSEnquiryAction execute()");
	    try {
            ITSZAction.sessionManagement(request);
        } catch (ITSZException e1) {
            log_debug.info("session time out");
            return mapping.findForward("epayment_session_overtime");
        }
		PPSEnquiryForm actionForm = (PPSEnquiryForm) form;
		//TODO check service availability
		//if no, forward to service stop notice page

		UserObject user =(UserObject) request.getSession().getAttribute(Constants.USER);
				
        actionForm.setLang(getLang(request));
        actionForm.setLocale(getLocaleStr(request));
        actionForm.setOpCode("03");
		
		try {
			PPSEnquiryRequestModel model = new PPSEnquiryRequestModel();
			DataModelUtil.form2Model(request,actionForm, model, response);
			if (user != null && user.getTradeInfoObject() != null){
			    model.setAccountId(user.getLoginName());
			    model.setLoginId(user.getLoginName());
			}
			PPSEnquiryResponseModel responseModel = facade.ppsEnquiry(model);
			if(responseModel.getReturnCode().equals(Consts.Login.Status.LONGIN_STATUS_NORMAL)){
				PPSTransferDetailResponse res = responseModel.getPpsTransferDetailResponse();
				List<PPSRecord> ppsRecords = new ArrayList<PPSRecord>();
				if (res.getPPSRequestInfoCol() != null) {
					log_debug.info("PPSRequsetInfoCol.size()=" + res.getPPSRequestInfoCol().size());
					
					PPSRecord ppsRecord = null;
					Iterator it = res.getPPSRequestInfoCol().iterator();
					while (it.hasNext()) {
						PPSTransferDetail tmpPPSTransferDetail = (PPSTransferDetail) it.next();
						
						ppsRecord = new PPSRecord();
						ppsRecord.setTxRef(tmpPPSTransferDetail.getTxRef());
						ppsRecord.setTxDate(tmpPPSTransferDetail.getTxDate());
						ppsRecord.setTxAmt(new BigDecimal(tmpPPSTransferDetail.getTxAmt()));
						
						ppsRecords.add(ppsRecord);
					}
				}
				actionForm.setPpsRecords(ppsRecords);
			}else{
				throw new PPSTransferException(
		 				"mcs enquiry pps record failed",
		 				"errors.ppsEnquiry.unavailable",
		 				new Object[]{}
		 				);
			}
			
		} catch (WebChannelException webEx) {
            log_debug.info(webEx.getMessage(),webEx);
            log_debug.info("delegate.getPPSRecords() throw exception");
			return this.processException(request, mapping, webEx);
		}
        String forward="ppsEnquiryForward";
        return mapping.findForward(forward);
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
        forward = mapping.findForward(FAILURE_KEY);
        saveErrors(request, errors);
        return forward;
    }
}
