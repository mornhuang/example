package com.itsz.sht.vp.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.Globals;
import org.apache.struts.action.ActionForward;
import org.apache.struts.util.MessageResources;

import sun.misc.resources.Messages;

import com.itsz.eipo.webservice.EIPOResponse;
import com.itsz.eipo.webservice.IpoSubscriptionDto;
import com.itsz.eipo.webservice.SubscriptionRes;
import com.itsz.sht.common.CLVSplitUtil;
import com.itsz.sht.common.Constants;
import com.itsz.sht.common.Consts;
import com.itsz.sht.common.DateHelper;
import com.itsz.sht.common.DateUtil;
import com.itsz.sht.common.JSONUtil;
import com.itsz.sht.common.PropertyConfig;
import com.itsz.sht.common.WEB_Constants;
import com.itsz.sht.common.model.common.LoginUserInfo;
import com.itsz.sht.common.model.common.response.AccessRTQResponseModel;
import com.itsz.sht.common.model.common.response.ListSelectServiceResponseModel;
import com.itsz.sht.common.model.common.response.PurchaseServiceResponseModel;
import com.itsz.sht.common.model.common.response.RTQProductResponseModel;
import com.itsz.sht.common.model.common.response.ReserveServiceResponseModel;
import com.itsz.sht.common.model.common.response.UserNotificationEmailResponseModel;
import com.itsz.sht.common.model.common.response.UserProductResponseModel;
import com.itsz.sht.common.model.response.BOCTransferResponseModel;
import com.itsz.sht.common.model.response.CancelEIPOSubscriptionResponseModel;
import com.itsz.sht.common.model.response.EIPOMasterDetailResponseModel;
import com.itsz.sht.common.model.response.EIPOSubResponseDetailModel;
import com.itsz.sht.common.model.response.EIPOSubResponseModel;
import com.itsz.sht.common.model.response.EnquireAccountResponseModel;
import com.itsz.sht.common.model.response.FilterEIPOListResponseModel;
import com.itsz.sht.common.model.response.FilterIPOAmtRcrdResponseModel;
import com.itsz.sht.common.model.response.FilterIPOListResponseModel;
import com.itsz.sht.common.model.response.FilterIPOQtyAmtRcrdResponseModel;
import com.itsz.sht.common.model.response.FundTransferResponseModel;
import com.itsz.sht.common.model.response.InsertIPOResponseModel;
import com.itsz.sht.common.model.response.LoginResponseModel;
import com.itsz.sht.common.model.response.MISAccountEnquiryResponseModel;
import com.itsz.sht.common.model.response.MarginFinancingListResponseModel;
import com.itsz.sht.common.model.response.PPSEnquiryResponseModel;
import com.itsz.sht.common.model.response.QueryCodeResponseModel;
import com.itsz.sht.common.model.response.RemoveIPOResponseModel;
import com.itsz.sht.common.model.response.TradeListResponseModel;
import com.itsz.sht.common.model.response.TransactionProtectionResponseModel;
import com.itsz.sht.common.user.TradeInfoObject;
import com.itsz.sht.common.user.UserObject;
import com.itsz.sht.common.util.AccountSorter;
import com.itsz.sht.common.util.LogUtil;
import com.itsz.sht.common.util.OfflineAccountSorter;
import com.itsz.sht.common.util.OnlineAccountSorter;
import com.itsz.sht.common.util.StreamRtqForward;
import com.itsz.sht.dao.hibernate.model.Product;
import com.itsz.sht.dao.hibernate.model.UserProduct;
import com.itsz.sht.dao.hibernate.model.UserProductAllotment;
import com.itsz.sht.dto.eipo.EIPORecord;
import com.itsz.sht.dto.ipo.IPORecord;
import com.itsz.sht.dto.ipo.IPOResult;
import com.itsz.sht.struts.eipo.dao.EIPOMasterEntry;
import com.itsz.sht.struts.eipo.dao.EIPOSubscriptionDto;
import com.itsz.sht.struts.eipo.dao.EIPOSubscriptionEntry;
import com.itsz.sht.struts.eipo.dao.EIPOSubscriptionEntryComparator;
import com.itsz.sht.struts.eipo.form.EIPOSubscriptionEnquiryForm;
import com.itsz.sht.struts.eipo.form.IPOMasterEnquiryForm;
import com.itsz.sht.struts.eipo.util.EIPOConstants;
import com.itsz.sht.struts.form.AccountPositionForm;
import com.itsz.sht.struts.form.OrderTransactionHistoryForm;
import com.itsz.sht.struts.form.PPSTransferForm;
import com.itsz.sht.struts.form.TransferFundActionForm;
import com.itsz.sht.struts.form.ipo.IPOCancelForm;
import com.itsz.sht.struts.form.ipo.IPORequestAddForm;
import com.itsz.sht.vp.ViewProviderImpl;
import com.itsz.sht.vp.common.ProcessBean;
import com.taifook.adminportal.common.util.DataFormatUtil;
import com.taifook.mcs.core.beans.msg.AccountDetail;
import com.taifook.mcs.core.beans.msg.AccountListDetail;
import com.taifook.mcs.core.beans.msg.MISCashHoldingSummary;
import com.taifook.mcs.core.beans.msg.PPSTransferDetail;
import com.taifook.mcs.core.beans.msg.StockInfo;
import com.taifook.mcs.core.beans.msg.TradeListInfo;
import com.taifook.mcs.core.beans.msg.TradingAccountInfo;

/**
 * $Id: WebViewProvider.java,v 1.130 2011/05/20 06:41:10 xli Exp $
 * @Project:portal.head
 * @File:WebViewProvider.java
 * @Description:
 * @Author:Cimenon Saint
 * @Date:2007-5-21
 */
public class WebViewProvider extends ViewProviderImpl {
	
	private ActionForward process(Object result, HttpServletResponse response) {
		try {
			response.setContentType("text/html;charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			PrintWriter writer = response.getWriter();
			String resultStr = JSONUtil.bean2JSON(result);
			writer.print(resultStr);
			writer.flush();
			LogUtil.logDTO(resultStr);
		} catch (IOException e) {
			LogUtil.logDTO(e);
		}
		return null;
	}

	public ActionForward processEMCCount(ProcessBean processBean){
		return process(processBean.getObject(), processBean.getResponse());
	}
	
	public ActionForward processChangeLang(ProcessBean processBean){		
		return processBean.getMapping().findForward((String)processBean.getObject());
	}
	
	public ActionForward processLogin(ProcessBean processBean) {
		LoginResponseModel responseModel = (LoginResponseModel) processBean.getObject();
		if(responseModel.getReturnCode().equals(Consts.Login.Status.LONGIN_STATUS_NORMAL)){
			String subCode = responseModel.getLoginResponse().getNewSubCode();
			if(isAllowLoginStatus(subCode)){
				if(isLogin(subCode) && responseModel.getLoginResponse().getAllowTradeStatusFlag().equals("N")){
					subCode = "Not_Trade";
					responseModel.setReturnCode(subCode);
				}
				if(isLogin(subCode) && responseModel.getLoginResponse().getTradingAccount()==null){
					subCode = "No_TradingAccount";
					responseModel.setReturnCode(subCode);
				}
				if(isLogin(subCode)){
					responseModel.setReturnCode(subCode);
					UserObject userObject = new UserObject();
					userObject.setLoginName(responseModel.getLoginId());
					userObject.setChannelType(Consts.Channel.NWEB);
					userObject.setChannelID(Consts.Channel.Id.NWEB);
					userObject.setSessionID(processBean.getRequest().getSession().getId());
					userObject.setDefaultAccountExist(StringUtils.isNotEmpty(responseModel.getLoginResponse().getTradingAccount()));
					TradeInfoObject tradeInfoObject = new TradeInfoObject();
					tradeInfoObject.setCustCode(responseModel.getLoginResponse().getCustCode());
					tradeInfoObject.setTradingAccount(responseModel.getLoginResponse().getTradingAccount());
					tradeInfoObject.setAllowTradeStatusFlag(responseModel.getLoginResponse().getAllowTradeStatusFlag());
					tradeInfoObject.setAcEnquiryList(responseModel.getLoginResponse().getTradingAccountCol());
					userObject.setTradeInfoObject(tradeInfoObject);
					LoginUserInfo loginUserInfo = new LoginUserInfo();
					if(responseModel.getLoginResponse().getFirstLoginDate()==null){
						loginUserInfo.setFirstLogin_channel(Consts.Global.Flag.POSITIVE);
					}else{
						loginUserInfo.setFirstLogin_channel(Consts.Global.Flag.NEGATIVE);
					}
					loginUserInfo.setCustName(responseModel.getLoginResponse().getCustName());
					loginUserInfo.setTermsAndConditions(responseModel.getLoginResponse().getTermsAndConditions());
					loginUserInfo.setLoginStatus(responseModel.getReturnCode());
					loginUserInfo.setTransactionProtection(responseModel.getLoginResponse().getTransactionProtection());
					processBean.getRequest().getSession().setAttribute(Constants.USER, userObject);
					processBean.getRequest().getSession().setAttribute(WEB_Constants.LOGIN_USER_INFO, loginUserInfo);
				}else{
					responseModel.setReturnCode(subCode);
				}
			}
		}else{
			processBean.getRequest().setAttribute("returnCode", responseModel.getReturnCode());
			processBean.getRequest().setAttribute("resultStatus", responseModel.getReturnCode());
			return processBean.getMapping().getInputForward();
		}
		processBean.getRequest().setAttribute("resultStatus", responseModel.getReturnCode());
		return processBean.getMapping().findForward(responseModel.getReturnCode());
	}
	
	private boolean isAllowLoginStatus(String newSubCode) {
		return newSubCode != null && (Consts.Login.Status.LONGIN_STATUS_NORMAL.equals(newSubCode)
				|| Consts.Login.Status.LONGIN_STATUS_CHGPASS.equals(newSubCode)
				|| Consts.Login.Status.LONGIN_STATUS_GRACELOGIN.equals(newSubCode)
				|| Consts.Login.Status.LONGIN_STATUS_ISSUED.equals(newSubCode)
				|| Consts.Login.MessageKey.LOCKED.equals(newSubCode)
				|| Consts.Login.Status.LOGIN_STATUS_INVIDPASS.equals(newSubCode))
				|| Consts.Login.Status.LOGIN_STATUS_INVIDLOGID.equals(newSubCode)
				|| Consts.Login.MessageKey.SUSPENDED.equals(newSubCode)
				|| Consts.Login.MessageKey.RETRYCNT.equals(newSubCode)
				|| Consts.Login.Status.LONGIN_STATUS_GRACECNT.equals(newSubCode)
				|| Consts.Login.MessageKey.CHAADNORMAL.equals(newSubCode)
				|| Consts.Login.MessageKey.CHASUSP.equals(newSubCode);
				
	}
	
	private boolean isLogin(String subCode){
		return subCode != null && (Consts.Login.Status.LONGIN_STATUS_NORMAL.equals(subCode)
				|| Consts.Login.Status.LONGIN_STATUS_CHGPASS.equals(subCode)
				|| Consts.Login.Status.LONGIN_STATUS_GRACELOGIN.equals(subCode)
				|| Consts.Login.Status.LONGIN_STATUS_ISSUED.equals(subCode)
				|| Consts.Login.Status.LONGIN_STATUS_GRACECNT.equals(subCode)
				|| "success".equals(subCode));
	}
	
	public ActionForward processECertLogin(ProcessBean processBean) {
		return null;
	}
	
	public ActionForward processException(ProcessBean processBean) {
		String clv = processBean.getRequest().getParameter("CLV");
		if (Consts.Global.Sync.SYNC.equals(CLVSplitUtil.getSync(clv))) {
			processBean.getRequest().setAttribute("result", processBean.getObject());
			return processBean.getMapping().findForward(Consts.Global.Forward.EXCEPTION);
		} else {
			return process(processBean.getObject(), processBean.getResponse());
		}
	}
	
	public ActionForward processLogout(ProcessBean processBean) {
		return processBean.getMapping().findForward(Consts.Global.Forward.SUCCESS);
	}
	
	public ActionForward processModifyOrder(ProcessBean processBean) {
		return process(processBean.getObject(), processBean.getResponse());
	}
	
	public ActionForward processQueryOrder(ProcessBean processBean) {
		return null;
	}
	
	public ActionForward processQuashOrder(ProcessBean processBean) {
		return null;
	}
	
	public ActionForward processPlaceOrder(ProcessBean processBean) {
		return process(processBean.getObject(), processBean.getResponse());
	}
	
	public ActionForward processPrePlaceOrder(ProcessBean processBean){
		return null;
	}
	
	public ActionForward processOrderFee(ProcessBean processBean) {
		return process(processBean.getObject(), processBean.getResponse());
	}
	
	public ActionForward processOrderDetail(ProcessBean processBean) {
		return process(processBean.getObject(), processBean.getResponse());	
	}
	
	public ActionForward processModifyDetail(ProcessBean processBean) {
		return process(processBean.getObject(), processBean.getResponse());
	}
	
	public ActionForward processOrderList(ProcessBean processBean) {		
		return process(processBean.getObject(), processBean.getResponse());
	}
	
	public ActionForward processCancelOrder(ProcessBean processBean) {
		return process(processBean.getObject(), processBean.getResponse());
	}
	
	public ActionForward processShortEnquireRTQ(ProcessBean processBean) {
		return process(processBean.getObject(), processBean.getResponse());
	}
	
	public ActionForward processChangeTransactionProtection(ProcessBean processBean) {
		TransactionProtectionResponseModel responseModel = (TransactionProtectionResponseModel) processBean.getObject();
		if (Consts.Global.Status.NORMAL.equals(responseModel.getReturnCode())) {
			LoginUserInfo loginUserInfo = (LoginUserInfo) processBean.getRequest().getSession().getAttribute(WEB_Constants.LOGIN_USER_INFO);
			loginUserInfo.setTransactionProtection(responseModel.getTransactionProtectionStatus());
		}
		return process(processBean.getObject(), processBean.getResponse());
	}
	
	public ActionForward processTradeList(ProcessBean processBean) {
		return process(processBean.getObject(), processBean.getResponse());
	}
	
	public ActionForward processVerifyPlaceOrder(ProcessBean processBean) {
		return process(processBean.getObject(), processBean.getResponse());
	}
	
	public ActionForward processEnquireAccout(ProcessBean processBean) {
		return process(processBean.getObject(), processBean.getResponse());
	}
	
	public ActionForward processEnquireRTQ(ProcessBean processBean) {
		return process(processBean.getObject(), processBean.getResponse());
	}
	
	public ActionForward processEnquireStockPosition(ProcessBean processBean){
		return process(processBean.getObject(), processBean.getResponse());
	}
	
	public ActionForward processMain(ProcessBean processBean) {
		return processBean.getMapping().findForward(Consts.Global.Forward.SUCCESS);
	}
	
	public ActionForward processPopupRTQ(ProcessBean processBean) {
		ListSelectServiceResponseModel responseModel = (ListSelectServiceResponseModel)processBean.getObject();
		String forward = "success";
		if(responseModel.getExistingServiceProductList().size() > 0){
			processBean.getRequest().setAttribute("result", responseModel.getExistingServiceProductList().get(0));
			forward = "success";
		}else{
			forward = "successStock";
		}
		return processBean.getMapping().findForward(forward);
	}
	
	public ActionForward processStockRTQ(ProcessBean processBean){
		AccessRTQResponseModel responseModel = (AccessRTQResponseModel)processBean.getObject();
		processBean.getRequest().setAttribute("error", responseModel.getReturnCode());
		return processBean.getMapping().findForward("RTQNoAccount");
	}
	
	public ActionForward processAas(ProcessBean processBean) {
		return null;
	}
	
	public ActionForward processIPOQueryList(ProcessBean processBean) {
		FilterIPOListResponseModel IPOQueryList = (FilterIPOListResponseModel)processBean.getObject();
		List<IPORecord> IPOList = IPOQueryList.getResult();
		processBean.getRequest().setAttribute("result", IPOList);
		return processBean.getMapping().findForward(IPOQueryList.getResultForward());
	}

	//add by Arthur Chen on 20110421 for eipo
	public ActionForward processEIPOQueryList(ProcessBean processBean) {	
		FilterEIPOListResponseModel IPOQueryList = (FilterEIPOListResponseModel)processBean.getObject();
		if (Consts.Error.Code.ERRORCODE_SYS.equals(IPOQueryList.getReturnCode())) {
			processBean.getRequest().setAttribute("error", Consts.Error.Code.ERRORCODE_SYS);
			return processBean.getMapping().findForward(IPOQueryList.getResultForward());
		}else if("label.eipo.error.onlineerror".equals(IPOQueryList.getReturnCode())){
			processBean.getRequest().setAttribute("error", "label.eipo.error.onlineerror");
		}
		List<EIPORecord> IPOList = IPOQueryList.getResult();
		IPOMasterEnquiryForm iPOMasterEnquiryForm = (IPOMasterEnquiryForm)processBean.getForm();
		iPOMasterEnquiryForm.setMasterList(IPOList);
		iPOMasterEnquiryForm.setMasterSize(IPOList.size());
		processBean.getRequest().setAttribute("result", iPOMasterEnquiryForm);
		return processBean.getMapping().findForward(IPOQueryList.getResultForward());
	}
	
	public ActionForward processEIPOSub(ProcessBean processBean) {
		EIPOSubResponseModel IPOSub = (EIPOSubResponseModel)processBean.getObject();
		EIPOMasterEntry masterEntry = IPOSub.getMasterEntry();
		processBean.getRequest().setAttribute("result", masterEntry);
		return processBean.getMapping().findForward(IPOSub.getResultForward());
	}
	public ActionForward processEIPOSubDetail(ProcessBean processBean) {
		EIPOSubResponseDetailModel IPOSubDetail = (EIPOSubResponseDetailModel)processBean.getObject();
		EIPOMasterEntry masterEntry = IPOSubDetail.getMasterEntry();
		processBean.getRequest().setAttribute("result", masterEntry);
		return processBean.getMapping().findForward(IPOSubDetail.getResultForward());
	}
	
	public ActionForward processGetIPOQueryCode(ProcessBean processBean) {
		QueryCodeResponseModel responseModel = (QueryCodeResponseModel)processBean.getObject();
		String forward = "success";
		String deadLine = responseModel.getIpoRecord().getDeadLine_dsply();
		String isClose = responseModel.getIpoRecord().getStatus();
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String nowTime = s.format(calendar.getTime());
		boolean canApply = false;
		if(!"CLOSE".equals(isClose)&&deadLine.compareTo(nowTime)>=0){
			canApply = true;
		}
		if(responseModel.getIpoRequest() != null && 
				responseModel.getIpoRequest().getStatus() != null && 
				responseModel.getIpoRequest().getStatus().equals("received")){
			processBean.getRequest().setAttribute("result", responseModel);
			forward = "successed";
			//return processBean.getMapping().findForward(responseModel.getResultForward()+"ed");
            IPOResult ipoResult = responseModel.getIpoResult();
            if (ipoResult != null && ipoResult.getApplyId() != null) {
                String prgStatus=getPrgStatus(ipoResult);
                String[] str = ipoResult.getRejectCode().split(",");
                List rejectList = new ArrayList();
                for(int i=0;i<str.length;i++){
                	String rejectStr = "message.ipo.reject.code"+str[i];
                	rejectList.add(rejectStr);
                	processBean.getRequest().setAttribute("resultPrgStatus", prgStatus);
                	processBean.getRequest().setAttribute("resultList", rejectList);
                }
                forward = "successedResult";
            }
		}
		processBean.getRequest().setAttribute("result", responseModel);
		processBean.getRequest().getSession().setAttribute("ipoMasterId", responseModel.getIpoRecord().getIpoMasterId());
		processBean.getRequest().setAttribute("canApply", canApply);
		return processBean.getMapping().findForward(forward);
	}
	
	public String getPrgStatus(IPOResult ipoResult){
		String prgStatus="message.ipo.common.status";
		if(ipoResult.getPrgStatus()!=null)
        {
            if("0".equals(ipoResult.getPrgStatus()))
      	  {
      		  prgStatus="message.ipo.common.status0";
      	  }
      	  if("1".equals(ipoResult.getPrgStatus()))
      	  {
                prgStatus="message.ipo.common.status1";
      	  }
      	  if("2".equals(ipoResult.getPrgStatus()))
      	  {
                prgStatus="message.ipo.common.status2";
      	  }
      	  if("3".equals(ipoResult.getPrgStatus()))
      	  {
                prgStatus="message.ipo.common.status3";
      	  }
        }
		return prgStatus;
	}

	public ActionForward processIPOQueryQty(ProcessBean processBean) {
		FilterIPOQtyAmtRcrdResponseModel responseModel = (FilterIPOQtyAmtRcrdResponseModel)processBean.getObject();
		processBean.getRequest().setAttribute("result",responseModel);
		return processBean.getMapping().findForward(responseModel.getResultForward());
	}
	
	public ActionForward processIPOQueryAmt(ProcessBean processBean){
		FilterIPOAmtRcrdResponseModel responseModel = (FilterIPOAmtRcrdResponseModel)processBean.getObject();
		responseModel.getIpoRequest().setApplyQuantity_dsply(DataFormatUtil.formatQty(responseModel.getIpoRequest().getApplyQuantity()));
		responseModel.getIpoRequest().setDsptAmount_dsply(DataFormatUtil.formatAmt(responseModel.getIpoRequest().getDsptAmount()));
		processBean.getRequest().setAttribute("result", responseModel);
		return processBean.getMapping().findForward(responseModel.getResultForward());
	}

	public ActionForward processInsertIPO(ProcessBean processBean) {
		InsertIPOResponseModel responseModel = (InsertIPOResponseModel)processBean.getObject();
		IPORequestAddForm ipoRequestAddForm = (IPORequestAddForm)processBean.getForm();
		String statu = responseModel.getReturnCode();
		if(statu.equals(Consts.Error.Code.TRAD_ERROR_WRONGPASS)){
			processBean.getRequest().setAttribute("pwdfailure", "errors.validator.password"); 
			return new ActionForward("/webIPOQueryAmt.do?ipoMasterId="+ipoRequestAddForm.getIpoMasterId()+"&shareQty="+ipoRequestAddForm.getApplyQuantity());
		}
		if(processBean.getRequest().getSession().getAttribute("ipoMasterId")!=null){
			processBean.getRequest().getSession().removeAttribute("ipoMasterId");
		}
		processBean.getRequest().setAttribute("result", responseModel);
		return processBean.getMapping().findForward(responseModel.getResultForward());
	}
	
	public ActionForward processQueryCode(ProcessBean processBean) {
		QueryCodeResponseModel responseModel = (QueryCodeResponseModel)processBean.getObject();
		processBean.getRequest().setAttribute("result", responseModel);
		return processBean.getMapping().findForward(responseModel.getResultForward());
	}

	public ActionForward processRemoveIPO(ProcessBean processBean) {
		RemoveIPOResponseModel responseModel =(RemoveIPOResponseModel)processBean.getObject();
		String statu = responseModel.getReturnCode();
		IPOCancelForm ipoCancelForm = (IPOCancelForm)processBean.getForm();
		if(statu.equals(Consts.Error.Code.TRAD_ERROR_WRONGPASS)){
			processBean.getRequest().setAttribute("failure", "errors.validator.password");
			return new ActionForward("/webIPOQueryCancel.do?ipoMasterId="+ipoCancelForm.getIpoMasterId());
		}else if(statu.equals(Consts.Error.Code.IPO_TIMEOVER)){
			processBean.getRequest().setAttribute("failure", "label.ipo.canceled.timeover");
			return new ActionForward("/webIPOQueryCancel.do?ipoMasterId="+ipoCancelForm.getIpoMasterId());
		}
		return processBean.getMapping().findForward(responseModel.getResultForward());
	}
	
	public ActionForward processPaginationOrder(ProcessBean processBean) {		
		return null;
	}
	
	public ActionForward processFundTranser(ProcessBean processBean) {
		TransferFundActionForm form = (TransferFundActionForm)processBean.getForm();
		FundTransferResponseModel responseModel = (FundTransferResponseModel)processBean.getObject();
		String forward = "success";
		if(!responseModel.getReturnCode().equals(Consts.Global.Status.NORMAL)){
			processBean.getRequest().setAttribute("pwdfailure", responseModel.getReturnCode());
			forward = "pwdfailure";
		}
		processBean.getRequest().setAttribute("result", form);
		return processBean.getMapping().findForward(forward);
	}
	public ActionForward processEnquireMarginRations(ProcessBean processBean) {
		return null;
	}
	
	public ActionForward processPPSEnquiry(ProcessBean processBean) {
		PPSEnquiryResponseModel responseModel = (PPSEnquiryResponseModel)processBean.getObject();
		String forward = "success";
		if(responseModel.getReturnCode().equals(Consts.Login.Status.LONGIN_STATUS_NORMAL)){
			String type = processBean.getRequest().getParameter("type");
			UserObject user = (UserObject)processBean.getRequest().getSession().getAttribute(Constants.USER);
			//List<AccountListDetail> accountList = new ArrayList<AccountListDetail>(); 
			Collection<AccountListDetail> accountList = null;
			if(user.getTradeInfoObject() != null){
				accountList = user.getTradeInfoObject().getMisAccountList().getAccountListDetail();
			}
			//Collections.sort(accountList, new OnlineAccountSorter());
			if(type != null){
				if(type.equals("pps")){
					Collection col= responseModel.getPpsTransferDetailResponse().getPPSRequestInfoCol();
					List<PPSTransferDetail> ppsTransferList = new ArrayList<PPSTransferDetail>();
					for(Iterator i = col.iterator(); i.hasNext();){
						PPSTransferDetail o = (PPSTransferDetail)i.next();
						String tempString = "0000000000000000"+o.getTxRef();
						PPSTransferDetail pps = new PPSTransferDetail();
						pps.setTxRef(tempString.substring(tempString.length()-16, tempString.length()));
						pps.setTxDate(o.getTxDate().substring(6, 8)+"-"+o.getTxDate().substring(4, 6)+"-"+o.getTxDate().substring(0, 4));
						pps.setTxAmt(o.getTxAmt());
						ppsTransferList.add(pps);
					}
					forward = "successPPS";
					processBean.getRequest().setAttribute("result", ppsTransferList);
					processBean.getRequest().setAttribute("resultModel", responseModel);
				}else if(type.equals("boc")){
					forward = "successBOC";
				}
			}
			processBean.getRequest().setAttribute("resultReturnCode", responseModel.getReturnCode());
			processBean.getRequest().setAttribute("resultList", accountList);
		}else{
			forward = "failed";
			processBean.getRequest().setAttribute("resultReturnCode", responseModel.getReturnCode());
		}
		return processBean.getMapping().findForward(forward);
	}
	
	public boolean isAccountOutOfCompany(String accountId) {
        boolean result = true;
        if (accountId != null && accountId.length() > 2) {
            if ("02".equals(accountId.substring(0, 2))) {
                result = false;
            }
        }
        return result;
    }
	
	public ActionForward processBOCTransfer(ProcessBean processBean){
		BOCTransferResponseModel responseModel = (BOCTransferResponseModel)processBean.getObject();
		processBean.getRequest().setAttribute("result", responseModel);
		return processBean.getMapping().findForward("success");
	}
	
	public ActionForward processPPSTransfer(ProcessBean processBean){
		PPSTransferForm ppsTransferForm = (PPSTransferForm)processBean.getForm();
		ppsTransferForm.setIpgClientURL(PropertyConfig.getCommonProperty(Constants.IpgClientURL));
		processBean.getRequest().setAttribute("result", ppsTransferForm);
		return processBean.getMapping().findForward("success");
	}
	
	public ActionForward processOrderTransactionHistory(ProcessBean processBean) {
		MISAccountEnquiryResponseModel responseModel = (MISAccountEnquiryResponseModel)processBean.getObject();
		if(responseModel.getReturnCode().equals(Consts.Global.Status.NORMAL)){
			OrderTransactionHistoryForm historyForm = (OrderTransactionHistoryForm)processBean.getForm();
			List list = (List)responseModel.getMisAccountEnquiryResponse().getAccountDetailCol();
			Collections.sort(list, new OnlineAccountSorter());
			historyForm.setAccountDetailCol(list);
			processBean.getRequest().setAttribute("result", historyForm);
			return processBean.getMapping().findForward("success");
		}else{
			processBean.getRequest().setAttribute("error", responseModel.getReturnCode());
			return processBean.getMapping().findForward("es_exception");
		}
	}
	
	public ActionForward processAccountEnquiryInit(ProcessBean processBean){
		UserObject user = (UserObject)processBean.getRequest().getSession().getAttribute(Constants.USER);
		List<AccountListDetail> accountList = new ArrayList<AccountListDetail>();
		if(user != null && user.getTradeInfoObject() != null){
			Collection<AccountListDetail> col = user.getTradeInfoObject().getMisAccountList().getAccountListDetail();
			for(Iterator<AccountListDetail> i = col.iterator(); i.hasNext();){
				AccountListDetail account = i.next();
				accountList.add(account);
			}
			Collections.sort(accountList, new OnlineAccountSorter());
			user.getTradeInfoObject().getMisAccountList().setAccountListDetail(accountList);
		}
		processBean.getRequest().setAttribute("result", accountList);
		return processBean.getMapping().findForward("success");
	}
	
	public ActionForward processAccountPosition(ProcessBean processBean){
		AccountPositionForm accountPositionForm = (AccountPositionForm)processBean.getForm();
		if(accountPositionForm.getAccResp().getReturnCode().equals(Consts.Global.Status.NORMAL)){
			MISCashHoldingSummary misCashHoldingSummary = (MISCashHoldingSummary)accountPositionForm.getAccResp().getMisAccountSummaryResponse().getCashHoldingCol().iterator().next();
			accountPositionForm.setMisCashHoldingSummary(misCashHoldingSummary);
			if(accountPositionForm.getPositionResp().getMtsSShareHoldingResponse().getShareHoldingInfoCol().size() != 0){
				accountPositionForm.setHavePosition(true);
			}
			processBean.getRequest().setAttribute("result", accountPositionForm);
			return processBean.getMapping().findForward("success");
		}else{
			processBean.getRequest().setAttribute("error", accountPositionForm.getAccResp().getReturnCode());
			return processBean.getMapping().findForward("es_exception");
		}
	}
	
	public ActionForward processOrderTradeList(ProcessBean processBean) {
		TradeListResponseModel responseModel = (TradeListResponseModel)processBean.getObject();
		if(responseModel.getReturnCode().equals(Consts.Global.Status.NORMAL)){
			processBean.getRequest().setAttribute("resultSize", responseModel.getResponse().getTradeListInfos().size());
			Collection<TradeListInfo> list = responseModel.getResponse().getTradeListInfos();
			for(Iterator<TradeListInfo> i = list.iterator();i.hasNext();){
				TradeListInfo tradeListInfo = i.next();
				tradeListInfo.setBusinessDate(tradeListInfo.getBusinessDate().substring(0, 10));
			}
			if(responseModel.getResponse().getTradeListInfos().size()>0){
				processBean.getRequest().setAttribute("result", responseModel);
			}
			return processBean.getMapping().findForward("success");
		}else{
			processBean.getRequest().setAttribute("returnCode", responseModel.getReturnCode());
			return processBean.getMapping().findForward("failed");
		}
	}
	
	public ActionForward processEPaymentEntrance(ProcessBean processBean){
		TradeInfoObject responseModel = (TradeInfoObject)processBean.getObject();
		boolean tradingFlag = false;
		TradingAccountInfo tmp = null;
		Collection tradingAccountList = responseModel.getAcEnquiryList(processBean.getRequest());
		if(tradingAccountList!=null || tradingAccountList.size()>0){
			for(Iterator i=tradingAccountList.iterator();i.hasNext();){
				tmp = (TradingAccountInfo)i.next();
				if(haveIcbcProfile(tmp)){
					tradingFlag = true;
					processBean.getRequest().setAttribute("result", tmp);
					break;
				}
			}
		}
		processBean.getRequest().setAttribute("ICBCFlag", tradingFlag);
		return processBean.getMapping().findForward("success");
	}
	
	public boolean haveIcbcProfile(TradingAccountInfo tradingInfo){
		if(tradingInfo.getIcbcAccountName() != null && tradingInfo.getIcbcAccountNo() != null && 
				!tradingInfo.getIcbcAccountName().trim().equals("") && !tradingInfo.getIcbcAccountNo().trim().equals("")){
			return true;
		}
		return false;
	}
	
	@SuppressWarnings("unchecked")
	public ActionForward processEnquireMisAccount(ProcessBean processBean){
		MISAccountEnquiryResponseModel responseModel = (MISAccountEnquiryResponseModel)processBean.getObject();
		EnquireAccountResponseModel accountRes = (EnquireAccountResponseModel)processBean.getRequest().getAttribute("resultResponseModel");
		if(responseModel.getReturnCode().equals(Consts.Global.Status.NORMAL)){
			if(responseModel.getMisAccountEnquiryResponse().getAccountDetailCol().size() > 0){
				Collection<AccountDetail> accountList = responseModel.getMisAccountEnquiryResponse().getAccountDetailCol();
				List<AccountDetail> fromAccounts = this.filterFromAccounts(accountList);
				Collections.sort(fromAccounts, new AccountSorter());
				if(fromAccounts.size() > 0){
					List<AccountDetail> toAccounts = this.filterToAccounts(fromAccounts.get(0), accountList);
					Collections.sort(toAccounts, new OfflineAccountSorter());
					processBean.getRequest().setAttribute("resultFromAccounts", fromAccounts);
					processBean.getRequest().setAttribute("resultToAccounts", toAccounts);
				}
//				if(accountRes != null){
//					if(accountRes.getReturnCode().equals("NORMAL")){
//						MISCashHoldingSummary misCashHoldingSummary = (MISCashHoldingSummary)accountRes.getMisAccountSummaryResponse().getCashHoldingCol().iterator().next();
//						processBean.getRequest().setAttribute("resultCashHoldingSummary", misCashHoldingSummary);
//					}else{
//						processBean.getRequest().setAttribute("error", accountRes.getReturnCode());
//						return processBean.getMapping().findForward("es_exception");
//					}
//				}
			}
		}else{
			processBean.getRequest().setAttribute("error", responseModel.getReturnCode());
			return processBean.getMapping().findForward("es_exception");
		}
		return processBean.getMapping().findForward("success");
	}
	
	@SuppressWarnings("unchecked")
	protected List filterFromAccounts(Collection accounts) {
		List fromAccounts = new ArrayList();
		AccountDetail account = null;
		String accountType = null;
		for (Iterator i = accounts.iterator(); i.hasNext();) {
			account = (AccountDetail) i.next();
			accountType = account.getAccountType();
			if (accountType == null) {
				continue;
			}
			if (isOnlineCashAccount(accountType)) { //online cash a/c
				fromAccounts.add(account);
			} else if (
					isOnlineMarginAccount(accountType)) { //online margin a/c
				fromAccounts.add(account);
			}
		}
		return fromAccounts;
	}
	
	@SuppressWarnings("unchecked")
	protected List filterToAccounts(AccountDetail fromAccount, Collection accounts) {
		String fromAccountId = fromAccount.getAccountId();
		List toAccounts = new ArrayList();
		AccountDetail account = null;
		String accountType = null;
		//if from_account associate with a bank account, add it to to_accounts
		//if (fromAccount.getBankAccount() != null && !"".equals(fromAccount.getBankAccount())) {
		if (fromAccount.getBankAccountCode()!=null && !fromAccount.getBankAccountCode().equals("")) {
			account = new AccountDetail();
			account.setAccountId(fromAccount.getBankAccountCode());
			account.setAccountType(Consts.Epayment.FundTransfer.ACTYPE_BANK);
			account.setBankShortName(fromAccount.getBankShortName());
			account.setBankCode(fromAccount.getBankCode());
			toAccounts.add(account);
		}
		for (Iterator i = accounts.iterator(); i.hasNext();) {
			account = (AccountDetail) i.next();
			if (fromAccountId.equals(account.getAccountId())) {
				continue;
			}
			accountType = account.getAccountType();
			if (accountType == null) {
				continue;
			}
			if (isOfflineSecuritiesAccount(accountType)) { //offline cash a/c
				toAccounts.add(account);
			} else if (isFuturesAccount(accountType)) { //futures a/c
				toAccounts.add(account);
			}
		}
		return toAccounts;
	}
	
	protected boolean isOfflineSecuritiesAccount(String accountType) {
		return accountType.endsWith(Consts.Epayment.FundTransfer.ACTYPE_OFFLINE)
		&& (accountType.startsWith(Consts.Epayment.FundTransfer.ACTYPE_CASH)
				|| accountType.startsWith(Consts.Epayment.FundTransfer.ACTYPE_MARGIN));
	}

	protected boolean isFuturesAccount(String accountType) {
		return accountType.startsWith(Consts.Epayment.FundTransfer.ACTYPE_FUTURES);
	}
	
	protected boolean isOnlineCashAccount(String accountType) {
		return accountType.startsWith(Consts.Epayment.FundTransfer.ACTYPE_CASH)
		&& accountType.endsWith(Consts.Epayment.FundTransfer.ACTYPE_ONLINE);
	}

	protected boolean isOnlineMarginAccount(String accountType) {
		return accountType.startsWith(Consts.Epayment.FundTransfer.ACTYPE_MARGIN)
		&& accountType.endsWith(Consts.Epayment.FundTransfer.ACTYPE_ONLINE);
	}
	
	public ActionForward processGetUserNotificationEmail(ProcessBean processBean){
		UserNotificationEmailResponseModel responseModel = (UserNotificationEmailResponseModel)processBean.getObject();
		processBean.getRequest().setAttribute("result", responseModel);
		return processBean.getMapping().findForward("success");
	}
	
	public ActionForward processUpdateUserNotificationEmail(ProcessBean processBean){
		UserNotificationEmailResponseModel responseModel = (UserNotificationEmailResponseModel)processBean.getObject();
		processBean.getRequest().setAttribute("result", responseModel);
		return processBean.getMapping().findForward("success");
	}
	
	public ActionForward processCallMos(ProcessBean processBean) {
		return process(processBean.getObject(), processBean.getResponse());
	}
	public ActionForward processMarginRatios(ProcessBean processBean){
		MarginFinancingListResponseModel responseModel=(MarginFinancingListResponseModel)processBean.getObject();
		Collection<StockInfo> ModelList=responseModel.getMarginFinancingListResponse().getStockInfos();	
		
		for(java.util.Iterator<StockInfo> iter=ModelList.iterator();iter.hasNext();){
			StockInfo si=iter.next();
			double DmarginRatio=si.getMarginRatio().doubleValue();
			int ImarginRatio=(int) (DmarginRatio*100);
			si.setMarginRatio(new BigDecimal(ImarginRatio));
		}
		HttpServletRequest request=processBean.getRequest();
		request.setAttribute("totalPage", processBean.getRequest().getAttribute("totalPage"));
		request.setAttribute("size", processBean.getRequest().getAttribute("size"));
		request.setAttribute("ipage", processBean.getRequest().getAttribute("ipage"));
		request.setAttribute("previous_page", processBean.getRequest().getAttribute("previous_page"));
		request.setAttribute("next_page", processBean.getRequest().getAttribute("next_page"));
		request.setAttribute("ModelList", ModelList);
		return processBean.getMapping().findForward("margin");
	}
	
	public ActionForward processAccessRTQ(ProcessBean processBean){
		AccessRTQResponseModel accessRTQResp = (AccessRTQResponseModel)processBean.getObject();
		String status = accessRTQResp.getReturnCode();
		processBean.getRequest().setAttribute("result", accessRTQResp);
		if(!Consts.Global.Status.NORMAL.equalsIgnoreCase(status) 
		         && !Consts.Global.Status.SUCCESS.equalsIgnoreCase(status)){
			processBean.getRequest().setAttribute("productId", processBean.getRequest().getParameter("productId"));
			processBean.getRequest().setAttribute("error", status);
			return processBean.getMapping().findForward(processBean.getRequest().getParameter("productId").startsWith(Constants.PROVIDER_AASTOCK)?"aastock_exception":"etnet_exception");
		}
		String forward = "etnet_exception";
		StreamRtqForward sf = new StreamRtqForward();
		if(accessRTQResp.getRtqAccess()!=null){
			if (accessRTQResp.getRtqAccess().getProductId().startsWith(Constants.PROVIDER_ETNET)) {
	        	forward = sf.newEtnetRTQ(processBean.getRequest(),accessRTQResp.getRtqAccess());
//	        	forward = sf.etnetAppletRTQ(processBean.getRequest(),accessRTQResp.getRtqAccess());
	        }else if (accessRTQResp.getRtqAccess().getProductId().startsWith(Constants.PROVIDER_AASTOCK)) {
	            forward = sf.aaStockRTQ(processBean.getRequest(),accessRTQResp.getRtqAccess());
	        }
		}
		return processBean.getMapping().findForward(forward);
	}
	
	public ActionForward processPurchaseRTQ(ProcessBean processBean){
		PurchaseServiceResponseModel responseModel =(PurchaseServiceResponseModel)processBean.getObject();
		String status = responseModel.getReturnCode();
		String productId = (String)processBean.getRequest().getAttribute("productId");
		if(Consts.Error.Code.TRAD_ERROR_WRONGPASS.equalsIgnoreCase(status)){
			processBean.getRequest().setAttribute("error", responseModel.getReturnCode());
			return processBean.getMapping().findForward("es_"+productId);
		}
		if(!Consts.Error.Code.NORMAL.equalsIgnoreCase(status)&& !Consts.Global.Status.SUCCESS.equalsIgnoreCase(status)){
			processBean.getRequest().setAttribute("error", responseModel.getReturnCode());
			return processBean.getMapping().findForward("es_exception");
		}
		processBean.getRequest().setAttribute("productId", productId);
		processBean.getRequest().setAttribute("result", responseModel);
		return processBean.getMapping().findForward("success");
	}
	
	public ActionForward processReserveRTQ(ProcessBean processBean){
		ReserveServiceResponseModel responseModel =(ReserveServiceResponseModel)processBean.getObject();
		String status = responseModel.getReturnCode();
		String productId = (String)processBean.getRequest().getAttribute("productId");
		if(!status.equals(Consts.Error.Code.NORMAL)){
			processBean.getRequest().setAttribute("error", responseModel.getReturnCode());			
			return processBean.getMapping().findForward("es_"+productId);
		}
		processBean.getRequest().setAttribute("productId", productId);
		processBean.getRequest().setAttribute("result", responseModel);
		return processBean.getMapping().findForward("success");
	}
	
	public ActionForward processListSelectRTQ(ProcessBean processBean){
		String productId = null;//processBean.getRequest().getParameter("productId");
		String existProductId = null;//processBean.getRequest().getParameter("productId");
		UserProductAllotment productAllotment = null;//processBean.getRequest().getParameter("productId");
		UserProduct existProduct = null;//processBean.getRequest().getParameter("productId");
		Locale locale = (Locale) processBean.getRequest().getSession().getAttribute(Consts.Global.Common.defaultLocaleAttributeName);
		SimpleDateFormat effFormat=new SimpleDateFormat(Messages.getBundle("com.itsz.sht.properties.ApplicationResourcesWEB", locale).getString("label.quote.effFormat"), locale);
		SimpleDateFormat expFormat=new SimpleDateFormat(Messages.getBundle("com.itsz.sht.properties.ApplicationResourcesWEB", locale).getString("label.quote.expFormat"), locale);
		ListSelectServiceResponseModel listSelectResp = (ListSelectServiceResponseModel)processBean.getObject();
		if(listSelectResp.getSubscriberServiceProductList()!=null){
			for (Iterator iterator = listSelectResp.getSubscriberServiceProductList().iterator(); iterator.hasNext();) {
				UserProductAllotment product = (UserProductAllotment) iterator.next();
				product.setRemarks(effFormat.format(product.getEffDate())+
						(locale.equals(Locale.US)?" ":"")+Messages.getBundle("com.itsz.sht.properties.ApplicationResourcesWEB", locale).getString("label.quote.to")+
						expFormat.format(product.getExpDate()));
				productId = product.getProdId();
				existProductId = productId;
				productAllotment = product;
			}
		}
		processBean.getRequest().setAttribute("result", listSelectResp);
		
		if(StringUtils.isNotBlank(productId)){
			processBean.getRequest().setAttribute("productId", productId);
			processBean.getRequest().setAttribute("productAllotment", productAllotment);
		}else{
			if(listSelectResp.getExistingServiceProductList()!=null){
				for (Iterator iterator = listSelectResp.getExistingServiceProductList().iterator(); iterator.hasNext();) {
					UserProduct product = (UserProduct) iterator.next();
					existProductId = product.getId().getProdId();
					processBean.getRequest().setAttribute("existProductId", existProductId);
					existProduct = product;
					processBean.getRequest().setAttribute("existProduct", existProduct);
				}
			}
		}
		String method = processBean.getRequest().getParameter("method");
		if(!Consts.Global.Status.NORMAL.equalsIgnoreCase(listSelectResp.getReturnCode())
		   && !Consts.Global.Status.SUCCESS.equalsIgnoreCase(listSelectResp.getReturnCode())){
			processBean.getRequest().setAttribute("error", listSelectResp.getReturnCode());
			return processBean.getMapping().findForward("es_exception");
		}
		if(StringUtils.isNotBlank(method)){
			return processBean.getMapping().findForward(method);
		}
		return processBean.getMapping().findForward("success");
	}
	
	public ActionForward processRTQProductList(ProcessBean processBean){
		String thisMonth = "";
		String nextMonth = "";
		Locale locale = (Locale) processBean.getRequest().getSession().getAttribute(Consts.Global.Common.defaultLocaleAttributeName);
		SimpleDateFormat expFormat=new SimpleDateFormat(Messages.getBundle("com.itsz.sht.properties.ApplicationResourcesWEB", locale).getString("label.quote.expFormat"), locale);
		SimpleDateFormat nowyFormat=new SimpleDateFormat(Messages.getBundle("com.itsz.sht.properties.ApplicationResourcesWEB", locale).getString("label.quote.nowyFormat"), locale);
		RTQProductResponseModel rtqProductResp = (RTQProductResponseModel)processBean.getObject();
		if(rtqProductResp.getProductList()!=null){
			for (Iterator iterator = rtqProductResp.getProductList().iterator(); iterator.hasNext();) {
				Product product = (Product) iterator.next();
			}
		}
		thisMonth = nowyFormat.format(new Date())+"("
        	+Messages.getBundle("com.itsz.sht.properties.ApplicationResourcesWEB", locale).getString("label.quote.nowmFormat")
        	+expFormat.format(DateHelper.lastDayOfMonth(new Date()))+")";
		nextMonth = nowyFormat.format(DateHelper.firstDayOfNextMonth(new Date()))
        	+"("+Messages.getBundle("com.itsz.sht.properties.ApplicationResourcesWEB", locale).getString("label.quote.nowsFormat")
        	+expFormat.format(DateHelper.firstDayOfNextMonth(new Date()))
        	+(locale.equals(Locale.US)?" ":"")+Messages.getBundle("com.itsz.sht.properties.ApplicationResourcesWEB", locale).getString("label.quote.to")
        	+expFormat.format(DateHelper.lastdayOfNextMonth(new Date()))+")";
		processBean.getRequest().setAttribute("result", rtqProductResp);
		processBean.getRequest().setAttribute("thisMonth", thisMonth);
		processBean.getRequest().setAttribute("nextMonth", nextMonth);
		
		//半交易日(特指12�?1�?12点以后、每月最后一个交易日18点以后都不能购买RTQ
		Boolean isDisablePurchase = false;
		Calendar calendar = Calendar.getInstance();
		Calendar lastTdCa = Calendar.getInstance();
		int month = calendar.get(Calendar.MONTH) + 1;
		String value = PropertyConfig.getCommonProperty("TradeDay" + (month >= 10 ? month : "0" + month));
		if(value != null){
			SimpleDateFormat sdf = new SimpleDateFormat("dd");
			int days = Integer.parseInt(sdf.format(new Date()));
			int maxDayNum = DateUtil.getMaximumDayOfMonth(new Date());
			int lastTdDay = maxDayNum;
			if(maxDayNum == value.length()){
				for(int i=value.length()-1; i>=0; i--){
					if(value.charAt(i) != '1'){ 
						lastTdDay = i + 1;
						break;
					}
				}
			}
			lastTdCa.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), lastTdDay, 18, 0, 0);
			if(calendar.after(lastTdCa)){
				isDisablePurchase = true;
			}
			if(value.charAt(days-1)=='2' && month==12){
				lastTdCa.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), days, 12, 0, 0);
				if(calendar.after(lastTdCa)){
					isDisablePurchase = true;
				}
			}
		}
		processBean.getRequest().setAttribute("isDisablePurchase", isDisablePurchase);
		
		if(!Consts.Global.Status.NORMAL.equalsIgnoreCase(rtqProductResp.getReturnCode())){
			processBean.getRequest().setAttribute("error", rtqProductResp.getReturnCode());
			return processBean.getMapping().findForward("es_exception");
		}
		String method = processBean.getRequest().getParameter("method");
		if(StringUtils.isNotBlank(method) && "purchase".equalsIgnoreCase(method)){
			return processBean.getMapping().findForward("purchase");
		}
		if(processBean.getRequest().getAttribute("existProduct")!=null || processBean.getRequest().getAttribute("productAllotment")!=null){
			return processBean.getMapping().findForward("status");
		}
		
		return processBean.getMapping().findForward("success");
	}
	
	public ActionForward processUpdateRTQStatus(ProcessBean processBean){
		UserProductResponseModel responseModel =(UserProductResponseModel)processBean.getObject();
		String status = responseModel.getReturnCode();
		String productId = (String)processBean.getRequest().getAttribute("productId");
		if(!status.equals(Consts.Error.Code.NORMAL)&& 
		   !Consts.Global.Status.SUCCESS.equalsIgnoreCase(responseModel.getReturnCode())){
			   processBean.getRequest().setAttribute("error", responseModel.getReturnCode());			
			   return processBean.getMapping().findForward("es_exception");
		}else{
			processBean.getRequest().setAttribute("error", "message.quote.update_success");
		}
		processBean.getRequest().setAttribute("productId", productId);
		processBean.getRequest().setAttribute("result", responseModel);
		return processBean.getMapping().findForward("success");
	}
	
	public ActionForward processCancelReserveRTQ(ProcessBean processBean){
		UserProductResponseModel responseModel =(UserProductResponseModel)processBean.getObject();
		String status = responseModel.getReturnCode();
		String productId = (String)processBean.getRequest().getAttribute("productId");
		if(!status.equals(Consts.Error.Code.NORMAL)&& 
		   !Consts.Global.Status.SUCCESS.equalsIgnoreCase(responseModel.getReturnCode())){
			   processBean.getRequest().setAttribute("error", responseModel.getReturnCode());			
			   return processBean.getMapping().findForward("es_exception");
		}else{
			processBean.getRequest().setAttribute("error", "message.quote.cancel_success");
		}
		processBean.getRequest().setAttribute("productId", productId);
		processBean.getRequest().setAttribute("result", responseModel);
		return processBean.getMapping().findForward("success");
	}
	
	public ActionForward processSelectRTQStatus(ProcessBean processBean){
		String existProductId = null;
		String productId = null;
		String usedProductId = null;
		processBean.getRequest().setAttribute("existProductId", null);
		processBean.getRequest().setAttribute("productId", null);
		ListSelectServiceResponseModel listSelectResp = (ListSelectServiceResponseModel)processBean.getObject();
		if(listSelectResp.getSubscriberServiceProductList()!=null){
			for (Iterator iterator = listSelectResp.getSubscriberServiceProductList().iterator(); iterator.hasNext();) {
				UserProductAllotment productAllotment = (UserProductAllotment) iterator.next();
				productId = productAllotment.getProdId();
				existProductId = productAllotment.getProdId();
				processBean.getRequest().setAttribute("existProductId", existProductId);
				processBean.getRequest().setAttribute("productId", productId);
			}
		}
		
		if(listSelectResp.getExistingServiceProductList()!=null){
			for (Iterator iterator = listSelectResp.getExistingServiceProductList().iterator(); iterator.hasNext();) {
				UserProduct product = (UserProduct) iterator.next();
				usedProductId = product.getId().getProdId();
				processBean.getRequest().setAttribute("usedProductId", usedProductId);
				if(StringUtils.isBlank(productId) && product.getStatus().equals(Consts.RTQ.productStatus.AVAIL)){
					existProductId = product.getId().getProdId();
					processBean.getRequest().setAttribute("existProductId", existProductId);
				}
			}
		}
		return processBean.getMapping().findForward("success");
	}
	
	public ActionForward processAccountSummary(ProcessBean processBean){
		EnquireAccountResponseModel responseModel =(EnquireAccountResponseModel)processBean.getObject();
		String status = responseModel.getReturnCode();
		if (status.equals(Consts.Error.Code.NORMAL)) {
			return process(processBean.getObject(), processBean.getResponse());
		} else {
			processBean.getRequest().setAttribute("error", status);
			return processBean.getMapping().findForward("es_exception");
		}
	}

	//add by Arthur xli on 20110423 for eipo
	public ActionForward processEIPOSubscriptionDetail(ProcessBean processBean) {
		EIPOResponse response =(EIPOResponse)processBean.getObject();
		Locale locale = processBean.getRequest().getSession().getAttribute(Globals.LOCALE_KEY) != null ? (Locale) processBean.getRequest().getSession().getAttribute(Globals.LOCALE_KEY) : Locale.US;
		MessageResources mr = processBean.getRequest().getAttribute(Globals.MESSAGES_KEY) != null ? (MessageResources) processBean.getRequest().getAttribute(Globals.MESSAGES_KEY) : null;
		EIPOSubscriptionEntry subscriptionResult = null;
		if (Consts.Global.Forward.SUCCESS.equals(response.getReturnCode())) {
			SubscriptionRes allSubscriptionIpoRes = (SubscriptionRes)response;
			List<IpoSubscriptionDto> ipoList = allSubscriptionIpoRes.getSubscriptionList();
			if (ipoList != null && ipoList.size() > 0) {
				subscriptionResult = new EIPOSubscriptionEntry(ipoList.get(0),locale,mr);
				processBean.getRequest().setAttribute("subscriptionResult", subscriptionResult);
			}
		} else if (Consts.Error.Code.ERRORCODE_SYS.equals(response.getReturnCode())) {
			processBean.getRequest().setAttribute("error", Consts.Error.Code.ERRORCODE_SYS);
			return processBean.getMapping().findForward(Consts.Global.Forward.FAILURE);
		} else {
			processBean.getRequest().setAttribute("error", "label.eipo.error." + response.getReturnCode());
			return processBean.getMapping().findForward(Consts.Global.Forward.FAILURE);
		}
		return processBean.getMapping().findForward(Consts.Global.Forward.SUCCESS);
	}
	public ActionForward processEIPOSubscriptionSubmit(ProcessBean processBean) {
		EIPOResponse response =(EIPOResponse)processBean.getObject();
		Locale locale = processBean.getRequest().getSession().getAttribute(Globals.LOCALE_KEY) != null ? (Locale) processBean.getRequest().getSession().getAttribute(Globals.LOCALE_KEY) : Locale.US;
		MessageResources mr = processBean.getRequest().getAttribute(Globals.MESSAGES_KEY) != null ? (MessageResources) processBean.getRequest().getAttribute(Globals.MESSAGES_KEY) : null;
		if (Consts.Global.Forward.SUCCESS.equals(response.getReturnCode())) {
			EIPOSubscriptionEntry subscriptionEntry = null;
			SubscriptionRes addIpoRes = (SubscriptionRes)response;			
			List<IpoSubscriptionDto> ipoList = addIpoRes.getSubscriptionList();
			if (ipoList != null && ipoList.size() > 0) {
				subscriptionEntry = new EIPOSubscriptionEntry(ipoList.get(0),locale,mr);
			}
			processBean.getRequest().setAttribute("subscriptionResult", subscriptionEntry);
			return processBean.getMapping().findForward(Consts.Global.Forward.SUCCESS);
		} else if (Consts.Error.Code.ERRORCODE_SYS.equals(response.getReturnCode())) {
			processBean.getRequest().setAttribute("error", Consts.Error.Code.ERRORCODE_SYS);
			return processBean.getMapping().findForward(Consts.Global.Forward.SUCCESS);
		} else if (Consts.Error.Code.TRAD_ERROR_WRONGPASS.equals(response.getReturnCode())) {
			processBean.getRequest().setAttribute("error", Consts.Error.Code.TRAD_ERROR_WRONGPASS);
			return processBean.getMapping().findForward("pwdfailure");
		} else {
			processBean.getRequest().setAttribute("error", "label.eipo.error." + response.getReturnCode());
			return processBean.getMapping().findForward(Consts.Global.Forward.FAILURE);
		}

	}

	public ActionForward processEIPOSubscriptionEnquiry(ProcessBean processBean) {
		EIPOResponse response =(EIPOResponse)processBean.getObject();
		Locale locale = processBean.getRequest().getSession().getAttribute(Globals.LOCALE_KEY) != null ? (Locale) processBean.getRequest().getSession().getAttribute(Globals.LOCALE_KEY) : Locale.US;
		MessageResources mr = processBean.getRequest().getAttribute(Globals.MESSAGES_KEY) != null ? (MessageResources) processBean.getRequest().getAttribute(Globals.MESSAGES_KEY) : null;
		List subscriptionList = new ArrayList();
		if (Consts.Global.Forward.SUCCESS.equals(response.getReturnCode())) {
			SubscriptionRes allSubscriptionIpoRes = (SubscriptionRes)response;
			List<IpoSubscriptionDto> ipoList = allSubscriptionIpoRes.getSubscriptionList();
			for (IpoSubscriptionDto subs : ipoList) {
				subscriptionList.add(new EIPOSubscriptionEntry(subs,locale,mr));
			}
			Collections.sort(subscriptionList, new EIPOSubscriptionEntryComparator());
		} else if (Consts.Error.Code.ERRORCODE_SYS.equals(response.getReturnCode())) {
			processBean.getRequest().setAttribute("error", Consts.Error.Code.ERRORCODE_SYS);
			return processBean.getMapping().getInputForward();
		} else if ("MCS00600".equals(response.getReturnCode())) {
			processBean.getRequest().setAttribute("error", "MCS00600");
			return processBean.getMapping().getInputForward();
		} else {
			processBean.getRequest().setAttribute("error", "label.eipo.error." + response.getReturnCode());
			return processBean.getMapping().getInputForward();
		}
		EIPOSubscriptionEnquiryForm subsEnquiryForm = new EIPOSubscriptionEnquiryForm();
		subsEnquiryForm.setIpoList(subscriptionList);
		subsEnquiryForm.setIpoSize(subscriptionList.size());
		processBean.getRequest().setAttribute("result", subsEnquiryForm);
		return processBean.getMapping().getInputForward();
	}

	public ActionForward processCancelEIPOSubscription(ProcessBean processBean) {
		CancelEIPOSubscriptionResponseModel response =(CancelEIPOSubscriptionResponseModel)processBean.getObject();	
		Locale locale = response.getLocale();
		MessageResources mr = response.getMr();
		EIPOMasterEntry masterEntry = null;
		EIPOSubscriptionDto eipoSubDto = null;
		IpoSubscriptionDto ipoSubscriptionDto = new IpoSubscriptionDto();
		if (Consts.Global.Forward.SUCCESS.equals(response.getReturnCode())) {
			masterEntry = response.getMasterEntry();
			eipoSubDto = response.getEipoSubscriptionDto();
			try {
				PropertyUtils.copyProperties(ipoSubscriptionDto, eipoSubDto);
			} catch (Exception e) {
				processBean.getRequest().setAttribute("error", Consts.Error.Code.ERRORCODE_SYS);
				return processBean.getMapping().findForward(Consts.Global.Forward.FAILURE);
			}
			
		} else if (Consts.Error.Code.ERRORCODE_SYS.equals(response.getReturnCode())) {
			processBean.getRequest().setAttribute("error", Consts.Error.Code.ERRORCODE_SYS);
			return processBean.getMapping().findForward(Consts.Global.Forward.FAILURE);
		} else {
			processBean.getRequest().setAttribute("error", "label.eipo.error." + response.getReturnCode());
			return processBean.getMapping().findForward(Consts.Global.Forward.FAILURE);
		}
		masterEntry.setIpoSubscription(ipoSubscriptionDto);
		if (ipoSubscriptionDto == null || 
				!EIPOConstants.IPO_MASTER_SUBSCRIPTION_STATE_CANCEL.equals(masterEntry.getIpoSubscriptionState())) {
			processBean.getRequest().setAttribute("error", "label.eipo.error.can_not_cancel");
			return processBean.getMapping().findForward(Consts.Global.Forward.FAILURE);
		}
		if (masterEntry != null) {
			double tradeAmount = new Double(masterEntry.getHandlingCharge().doubleValue())
				+ masterEntry.getIpoSubscription().getAppliedAmount().doubleValue()
				+ masterEntry.getIpoSubscription().getAppliedQuantity() * masterEntry.getOfferPrice().doubleValue() * masterEntry.getMiscFee().doubleValue();
			
			masterEntry.setTradeAmount(new BigDecimal(tradeAmount));
			masterEntry.setAppliedShare(new BigDecimal(masterEntry.getIpoSubscription().getAppliedQuantity()));
			masterEntry.setAmountPayable(masterEntry.getIpoSubscription().getAppliedAmount());
			
			EIPOSubscriptionEntry subEntry = new EIPOSubscriptionEntry(masterEntry.getIpoSubscription(),locale,mr);
			subEntry.setIpoSubscriptionNotificationDtoList(eipoSubDto.getIpoSubscriptionNotificationDtoList());
			processBean.getRequest().getSession().setAttribute("cancelMaster", masterEntry);//set session.
			processBean.getRequest().getSession().setAttribute("cancelSubscription",subEntry);//set session.
			processBean.getRequest().setAttribute("subId", masterEntry.getIpoSubscription().getId());
		} else {
			processBean.getRequest().setAttribute("error", "MCS00600");
			return processBean.getMapping().findForward(Consts.Global.Forward.FAILURE);
		}
		return processBean.getMapping().findForward(Consts.Global.Forward.SUCCESS);
	}

	public ActionForward processCancelEIPOSubscriptionSubmit(ProcessBean processBean) {
		EIPOResponse response =(EIPOResponse)processBean.getObject();
		if (Consts.Global.Forward.SUCCESS.equals(response.getReturnCode())) {
			processBean.getRequest().getSession().removeAttribute("cancelMaster");//remove session.
		} else if (Consts.Error.Code.ERRORCODE_SYS.equals(response.getReturnCode())) {
			processBean.getRequest().setAttribute("error", Consts.Error.Code.ERRORCODE_SYS);
			return processBean.getMapping().findForward(Consts.Global.Forward.FAILURE);
		} else if (Consts.Error.Code.TRAD_ERROR_WRONGPASS.equals(response.getReturnCode())) {
			processBean.getRequest().setAttribute("error", Consts.Error.Code.TRAD_ERROR_WRONGPASS);
			return processBean.getMapping().findForward("pwdfailure");
		} else {
			processBean.getRequest().setAttribute("error", "label.eipo.error." + response.getReturnCode());
			return processBean.getMapping().findForward(Consts.Global.Forward.FAILURE);
		}
		return processBean.getMapping().findForward(Consts.Global.Forward.SUCCESS);
	}
	
	public ActionForward processEIPOMasterDetail(ProcessBean processBean) {
		EIPOMasterDetailResponseModel response =(EIPOMasterDetailResponseModel)processBean.getObject();	
		Locale locale = response.getLocale();
		MessageResources mr = response.getMr();
		EIPOMasterEntry masterEntry = null;
		if (Consts.Global.Forward.SUCCESS.equals(response.getReturnCode())) {
			masterEntry = response.getMasterEntry();
		} else if (Consts.Error.Code.ERRORCODE_SYS.equals(response.getReturnCode())) {
			processBean.getRequest().setAttribute("error", Consts.Error.Code.ERRORCODE_SYS);
			return processBean.getMapping().findForward(Consts.Global.Forward.FAILURE);
		} else if ("MCS00600".equals(response.getReturnCode())) {
			processBean.getRequest().setAttribute("error", "MCS00600");
			return processBean.getMapping().findForward(Consts.Global.Forward.FAILURE);
		} else {
			processBean.getRequest().setAttribute("error", "label.eipo.error." + response.getReturnCode());
			return processBean.getMapping().findForward(Consts.Global.Forward.FAILURE);
		}
		processBean.getRequest().setAttribute("masterResult", masterEntry);
		return processBean.getMapping().findForward(Consts.Global.Forward.SUCCESS);
	}
}
