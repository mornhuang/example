package com.itsz.sht.vp.ps;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


import com.itsz.rtq.provider.quot.DetailQuot;
import com.itsz.sht.admin.broadcast.util.BroadcastInfo;
import com.itsz.sht.admin.service.AdminServiceDelegate;
import com.itsz.sht.common.Constants;
import com.itsz.sht.common.Consts;
import com.itsz.sht.common.LocaleUtil;
import com.itsz.sht.common.PortalUtil;
import com.itsz.sht.common.STTUtil;
import com.itsz.sht.common.WEB_Constants;
import com.itsz.sht.common.model.AbstractResponseModel;
import com.itsz.sht.common.model.common.AFXNewsInfo;
import com.itsz.sht.common.model.common.LoginUserInfo;
import com.itsz.sht.common.model.common.response.AccessRTQResponseModel;
import com.itsz.sht.common.model.response.BulkCancelOrderResponseModel;
import com.itsz.sht.common.model.response.CashDetailResponseModel;
import com.itsz.sht.common.model.response.ChangePwdResponseModel;
import com.itsz.sht.common.model.response.EnquireAccountResponseModel;
import com.itsz.sht.common.model.response.EnquireRTQResponse;
import com.itsz.sht.common.model.response.EnquireRTQResponseModel;
import com.itsz.sht.common.model.response.EnquireShortRTQResponseModel;
import com.itsz.sht.common.model.response.EnquiryPositionResponseModel;
import com.itsz.sht.common.model.response.FilterOrderResponseModel;
import com.itsz.sht.common.model.response.FundTransferResponseModel;
import com.itsz.sht.common.model.response.LoginResponseModel;
import com.itsz.sht.common.model.response.MISAccountEnquiryResponseModel;
import com.itsz.sht.common.model.response.ModifyOrderResponseModel;
import com.itsz.sht.common.model.response.MosResponseModel;
import com.itsz.sht.common.model.response.OrderFeeResponseModel;
import com.itsz.sht.common.model.response.PlaceOrderResponseModel;
import com.itsz.sht.common.model.response.ProfitAndLossEnqiryResponseModel;
import com.itsz.sht.common.model.response.ProfitAndLossUpdateResponseModel;
import com.itsz.sht.common.model.response.QuoteResponse;
import com.itsz.sht.common.model.response.SnapshotProfileResponseModel;
import com.itsz.sht.common.model.response.StreamRtqProfileResponseModel;
import com.itsz.sht.common.model.response.TradeListResponseModel;
import com.itsz.sht.common.model.response.TransactionProtectionResponseModel;
import com.itsz.sht.common.model.response.VerifyPasswordResponseModel;
import com.itsz.sht.common.model.response.listorder.OrderDetailTradeResponseModel;
import com.itsz.sht.common.model.response.placeorder.PrePlaceOrderResponseModel;
import com.itsz.sht.common.user.AcEnquiryInfo;
import com.itsz.sht.common.user.TradeInfoObject;
import com.itsz.sht.common.user.UserObject;
import com.itsz.sht.common.util.AccountFilterHelp;
import com.itsz.sht.common.util.FormatConversion;
import com.itsz.sht.common.util.OnlineAccountSorter;
import com.itsz.sht.common.util.StreamRtqForward;
import com.itsz.sht.exception.ITSZException;
import com.itsz.sht.vp.ViewProviderImpl;
import com.itsz.sht.vp.common.LoginState;
import com.itsz.sht.vp.common.ProcessBean;
import com.taifook.mcs.core.beans.msg.AccountDetail;
import com.taifook.mcs.core.beans.msg.Exception01;
import com.taifook.mcs.core.beans.msg.LoginResponse;
import com.taifook.mcs.core.beans.msg.MISAccountEnquiryResponse;
import com.taifook.mcs.core.beans.msg.TransactionProtectionResponse;
import com.taifook.mcs.core.beans.msg.VerifyPasswordResponse;

/**
 * $Id: PsViewProvider.java,v 1.28 2011/04/18 10:08:10 pbxie Exp $
 * @Project:portal.head
 * @File:PsViewProvider.java
 * @Description:
 * @Author:Cimenon Saint
 * @Date:2007-5-21
 */
public class PsViewProvider extends ViewProviderImpl {
	
	
	private ActionForward process(ProcessBean processBean){
		HttpServletRequest request = processBean.getRequest();
		ActionMapping mapping = processBean.getMapping();
		Object result = getEx01Result(processBean.getObject());
		String csv="";
		if(result instanceof ChangePwdResponseModel){
			ChangePwdResponseModel cprm=(ChangePwdResponseModel)result;
			csv=STTUtil.bean2csv(cprm.getChangePasswordResponse());
		}else if(result instanceof OrderDetailTradeResponseModel){
			OrderDetailTradeResponseModel odtrm=(OrderDetailTradeResponseModel)result;
			csv=STTUtil.bean2csv(odtrm.getOrderListResponse());
		}else if(result instanceof EnquiryPositionResponseModel){
			csv=STTUtil.bean2csv(((EnquiryPositionResponseModel) result).getMtsSShareHoldingResponse());
		}else if(result instanceof EnquireAccountResponseModel){
			EnquireAccountResponseModel arm=(EnquireAccountResponseModel)result;
			if(null!=arm.getMisAccountSummaryResponse())
			    csv=STTUtil.bean2csv(arm.getMisAccountSummaryResponse());
			else
				csv=STTUtil.bean2csv(arm.getMisAccountDetailResponse());
		}else if(result instanceof TradeListResponseModel){
			TradeListResponseModel responseModel = (TradeListResponseModel)processBean.getObject();
	        csv = STTUtil.bean2csv(responseModel.getResponse());
		}else if(result instanceof PlaceOrderResponseModel){
			PlaceOrderResponseModel porm=(PlaceOrderResponseModel)result;
			csv=STTUtil.bean2csv(porm.getInputOrderResponse());
		}else if(result instanceof OrderFeeResponseModel){
			OrderFeeResponseModel ofrm=(OrderFeeResponseModel)result;
			csv=STTUtil.bean2csv(ofrm.getCalOrderFeeResponse());
		}else if(result instanceof MosResponseModel){
			MosResponseModel mrm=(MosResponseModel)result;
			csv=STTUtil.bean2csv(mrm.getCalMOSResponse());
		}else if(result instanceof PrePlaceOrderResponseModel){
			PrePlaceOrderResponseModel pporm=(PrePlaceOrderResponseModel)result;
			csv=STTUtil.bean2csv(pporm.getPreOrderPlacingResponse());
		}else if(result instanceof FilterOrderResponseModel){
			FilterOrderResponseModel form=(FilterOrderResponseModel)result;
			csv=STTUtil.bean2csv(form.getOrderFilteringResponse());
		}else if(result instanceof FundTransferResponseModel){
			FundTransferResponseModel ftrm=(FundTransferResponseModel)result;
			if(null!=ftrm.getFundTransferResponse()){
				csv=STTUtil.bean2csv(ftrm.getFundTransferResponse());
			}else{
				csv=STTUtil.bean2csv(ftrm.getWithDrawResponse());
			}
		}else if(result instanceof CashDetailResponseModel){
			CashDetailResponseModel cdrm=(CashDetailResponseModel)result;
			csv=STTUtil.bean2csv(cdrm.getMisAccountCashHoldingResponse());
		}else if(result instanceof BulkCancelOrderResponseModel){
			BulkCancelOrderResponseModel bcorm=(BulkCancelOrderResponseModel)result;
			csv=STTUtil.bean2csv(bcorm.getBulkCancelOrderResponse());
		}else if(result instanceof ProfitAndLossEnqiryResponseModel){
			ProfitAndLossEnqiryResponseModel palerm=(ProfitAndLossEnqiryResponseModel)result;
			csv=STTUtil.bean2csv(palerm.getProfitAndLossEnquiryResponse());
		}else if(result instanceof ProfitAndLossUpdateResponseModel){
			ProfitAndLossUpdateResponseModel palurm=(ProfitAndLossUpdateResponseModel)result;
			csv=STTUtil.bean2csv(palurm.getProfitAndLossUpdateResponse());
		}else{
			Exception01 ex01 = (Exception01)result;
			if(Consts.Error.Code.CONNECT_MCS.equals(ex01.getErrorSubCode())||
					Consts.Error.Code.PARAMETER_MCS.equals(ex01.getErrorSubCode())){
				processBean.setObject(new ITSZException(ex01.getErrorSubCode()));
				return processException(processBean);
			}
	        csv = STTUtil.bean2csv(ex01);			
		}
		request.setAttribute(STT_Constants.MESSAGE,csv);
        return mapping.findForward(STT_Constants.SUCCESS);
	}
	
	public ActionForward processProfitAndLossUpdate(ProcessBean processBean){
		commonDebug.info("PSViewProvieder Processor: processProfitAndLossUpdate");
		return this.process(processBean);
	}
	
	public ActionForward processBulkCancelOrder(ProcessBean processBean){
		commonDebug.info("PSViewProvieder Processor: processBulkCancelOrder");			
		return this.process(processBean);
	}
	
	public ActionForward processCashDetail(ProcessBean processBean){
		commonDebug.info("PSViewProvieder Processor: processCashDetail");
		return this.process(processBean);
	}
	
	public ActionForward processFundTranser(ProcessBean processBean){
		commonDebug.info("PSViewProvieder Processor: processTransferFund");
		return this.process(processBean);
	}
	
	public ActionForward processPrePlaceOrder(ProcessBean processBean){
		return this.process(processBean);
	}
	
	public ActionForward processLogin(ProcessBean processBean) {
		HttpServletRequest request = processBean.getRequest();
		ActionMapping mapping = processBean.getMapping();
		Object result = getEx01Result(processBean.getObject());
		String csv="";
		if(result instanceof LoginResponseModel){
			String sessionId = "%NULL%";
			LoginResponseModel responseModel=(LoginResponseModel)result;
			LoginResponse res = responseModel.getLoginResponse();
			if(LoginState.checkAllowLoginStatus(res.getNewSubCode())){
			    String channelID=responseModel.getChannelId();
				String channelType=responseModel.getChannelType();
				String loginID=responseModel.getLoginId();
				String language=responseModel.getLanguage();
				HttpSession session=request.getSession();
				String sessionID=session.getId();
				UserObject user=new UserObject();
				user.setChannelID(channelID);
				user.setChannelType(channelType);
				user.setLoginName(loginID);
				user.setLang(language);
				user.setSessionID(sessionID);				
				TradeInfoObject trade = new TradeInfoObject();
				trade.setCustCode(res.getCustCode());
				trade.setTradingAccount(res.getTradingAccount());
				trade.setAllowTradeStatusFlag(responseModel.getLoginResponse().getAllowTradeStatusFlag());
				trade.setAcEnquiryList(responseModel.getLoginResponse().getTradingAccountCol());
				user.setTradeInfoObject(trade);
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
				processBean.getRequest().getSession().setAttribute(WEB_Constants.LOGIN_USER_INFO, loginUserInfo);
				sessionId = session.getId();
				session.setAttribute(Constants.USER,user);
				LocaleUtil.setLocale(request, language);
				AdminServiceDelegate.getInstance().processUserActionLog(loginID,channelID,channelType,request.getSession().getId(),request.getRemoteAddr(),com.taifook.adminportal.common.Constants.PW_LOGIN_ACTION);				
			}
			csv=STTUtil.bean2csv(res);
			csv = PortalUtil.addFieldToCSV(csv, "JSID", sessionId);
		}else{
			Exception01 ex01 = (Exception01)result;
			if(Consts.Error.Code.CONNECT_MCS.equals(ex01.getErrorSubCode())){
				processBean.setObject(new ITSZException(ex01.getErrorSubCode()));
				return processException(processBean);
			}
	        csv = STTUtil.bean2csv(ex01);			
		}
		request.setAttribute(STT_Constants.MESSAGE,csv);
        return mapping.findForward(STT_Constants.SUCCESS);
	}
	
	public ActionForward processProfitAndLossEnquiry(ProcessBean processBean){
		commonDebug.info("PSViewProvieder Processor: processProfitAndLossEnquiry");
		return this.process(processBean);
	}
	
	public ActionForward processChangePassword(ProcessBean processBean){
		commonDebug.info("PSViewProvieder Processor: processChangePassword");
		return this.process(processBean);
	}
	
	public ActionForward processOrderDetail(ProcessBean processBean){
        commonDebug.info("PSViewProvieder Processor: processOrderDetail");
		return this.process(processBean);
	}
	
	public ActionForward processCallMos(ProcessBean processBean){
		commonDebug.info("PSViewProvieder Processor: processCalMos");		
		return this.process(processBean);
	}
	
	public ActionForward processOrderList(ProcessBean processBean){
		commonDebug.info("PSViewProvieder Processor: processOrderList");
		return this.process(processBean);
	}
	
	public ActionForward processVerifyPassword(ProcessBean processBean){
		commonDebug.info("PSViewProvieder Processor: processVerifyPassword");
		HttpServletRequest request = processBean.getRequest();
		ActionMapping mapping = processBean.getMapping();
		Object result = getEx01Result(processBean.getObject());
		String csv="";
		if(result instanceof VerifyPasswordResponseModel){
			VerifyPasswordResponseModel responseModel = (VerifyPasswordResponseModel)result;
			VerifyPasswordResponse vpr = responseModel.getVerifyPwdResponse();
			csv=STTUtil.bean2csv(vpr);
		}else{
			Exception01 ex01 = (Exception01)result;
			if(Consts.Error.Code.CONNECT_MCS.equals(ex01.getErrorSubCode()) ||
					Consts.Error.Code.ERRORCODE_CONNECT_ESERVICE.equals(ex01.getErrorSubCode())){
				processBean.setObject(new ITSZException(ex01.getErrorSubCode()));
				return processException(processBean);
			}
	        csv = STTUtil.bean2csv(ex01);		
		}
        request.setAttribute(STT_Constants.MESSAGE, csv);	
		return mapping.findForward(STT_Constants.SUCCESS);
	}
	
	public ActionForward processNewsUrl(ProcessBean processBean){
		commonDebug.info("PSViewProvieder Processor: processNewUrl");
		HttpServletRequest request = processBean.getRequest();
		ActionMapping mapping = processBean.getMapping();
		Object result = getEx01Result(processBean.getObject());	
		CSVProcessor processor;
		AFXNewsInfo news = (AFXNewsInfo)result;
		String csv="";
        try {
            processor = new CSVProcessor();
            csv = processor.BeanToCSV(news);
        } catch (Exception e) {
        	commonDebug.info(e.getMessage(),e);
        }
		request.setAttribute(STT_Constants.MESSAGE,csv);
		return mapping.findForward(STT_Constants.SUCCESS);
	}
	
	public ActionForward processServerTime(ProcessBean processBean){
		HttpServletRequest request = processBean.getRequest();
		ActionMapping mapping = processBean.getMapping();
		Object result = getEx01Result(processBean.getObject());	
		commonDebug.info("PSViewProvieder Processor: processServerTime");	
		String csv = (String)result;
		request.setAttribute(STT_Constants.MESSAGE,csv);
		return mapping.findForward(STT_Constants.SUCCESS);
	}
	
	public ActionForward processChangeTransactionProtection(ProcessBean processBean){
		commonDebug.info("PSViewProvieder Processor: processTransactionProtection");
		HttpServletRequest request = processBean.getRequest();
		ActionMapping mapping = processBean.getMapping();
		Object result = getEx01Result(processBean.getObject());
		String csv = "";
		if(result instanceof TransactionProtectionResponseModel){
			TransactionProtectionResponseModel tprm = (TransactionProtectionResponseModel)result;
			if (Consts.Global.Status.NORMAL.equals(tprm.getReturnCode())) {
				LoginUserInfo loginUserInfo = (LoginUserInfo) processBean.getRequest().getSession().getAttribute(WEB_Constants.LOGIN_USER_INFO);
				loginUserInfo.setTransactionProtection(tprm.getTransactionProtectionStatus());
			}
		    csv=STTUtil.bean2csv(tprm.getTransactionProtectionRes());
		}else{
			Exception01 ex01 = (Exception01)result;
			if(Consts.Error.Code.CONNECT_MCS.equals(ex01.getErrorSubCode())){
				processBean.setObject(new ITSZException(ex01.getErrorSubCode()));
				return processException(processBean);
			}
			if(Consts.Error.Code.TRSPTD_INVALID_PASS.equals(ex01.getErrorSubCode()) ||
					Consts.Error.Code.TRSPTD_ERROR_PASS.equals(ex01.getErrorSubCode())){
			    csv = "RXPR=(MSID='RXPR', TPOS='INPW')";            		  
			}else{
				csv = STTUtil.bean2csv(ex01);
			}
		}
	    request.setAttribute(STT_Constants.MESSAGE,csv);
		return mapping.findForward(STT_Constants.SUCCESS);
	}
	
	@SuppressWarnings("rawtypes")
	public ActionForward processEnquireMisAccount(ProcessBean processBean){
		HttpServletRequest request = processBean.getRequest();
		ActionMapping mapping = processBean.getMapping();
		Object result = getEx01Result(processBean.getObject());
		if(result instanceof MISAccountEnquiryResponseModel){
			MISAccountEnquiryResponseModel responseModel = (MISAccountEnquiryResponseModel)result;
			HttpSession session = request.getSession();
			ArrayList acDetailCol = new ArrayList();
			MISAccountEnquiryResponse res = responseModel.getMisAccountEnquiryResponse();
			Collection acEnquiryCol = res.getAccountDetailCol();
			UserObject user=(UserObject)session.getAttribute(Constants.USER);
		    TradeInfoObject tradeInfo = user.getTradeInfoObject();
		    String tradingAccount=tradeInfo.getTradingAccount();
			Iterator it = acEnquiryCol.iterator();
			AccountDetail acDetail;
			boolean isContainsTradingAccount=false;
			//ArrayList acDetailCol = new ArrayList();
			while (it.hasNext()) {
				acDetail = (AccountDetail) it.next();
				if (acDetail != null && "1".equals(acDetail.getAccountStatus())) {
						//TODO add other filtration		
				    AcEnquiryInfo acEnquiryInfo = new AcEnquiryInfo();
					String ac=new String();
					try{
						ac=acDetail.getAccountId().trim();
						//add by tiger 
		                //1 filter sec account ( sub code>33&&sub code!=32&&sub code=31
		                //2 only keep fut account with sub code =01
					    if (AccountFilterHelp.filteraccount(acDetail)){
			                continue;
			            }
		                //end add by tiger 
					    if(ac.equals(tradingAccount)){
						    isContainsTradingAccount=true;
						}
					    BeanUtils.copyProperties(acEnquiryInfo,acDetail);
						if(acDetail.getAccountId().trim().equals(tradingAccount)){//����ǽ����˻����������ǰ��
							acDetailCol.add(0,acEnquiryInfo);
						}else {
							acDetailCol.add(acEnquiryInfo);
						}
					}catch(Exception e){
						commonInfo.info(e.getMessage(),e);
						ITSZException exception = new ITSZException();
				        exception.setErrorCode(Constants.ERRORCODE_PARAMETER_MCS);
				        request.setAttribute(STT_Constants.ITSZ_EXCEPTION,exception);
				        return mapping.findForward(STT_Constants.FAIL); 
					}   		
			    }
			}
			if(!isContainsTradingAccount){
				tradeInfo.setTradingAccount(null);
				commonDebug.warn("=======account enquiry not contains the login trading account=======");
			}	
			Collections.sort(acDetailCol, new OnlineAccountSorter());
			tradeInfo.setAcEnquiryList(acDetailCol);       
			user.setTradeInfoObject(tradeInfo);
			session.setAttribute(Constants.USER,user);
			res.setAccountDetailCol(acDetailCol);
			String csv=STTUtil.bean2csv(res);
			request.setAttribute(STT_Constants.MESSAGE,csv);
			return mapping.findForward(STT_Constants.SUCCESS);
		}else{
			Exception01 ex01 = (Exception01)result;
			if(Consts.Error.Code.CONNECT_MCS.equals(ex01.getErrorSubCode())){
				processBean.setObject(new ITSZException(ex01.getErrorSubCode()));
				return processException(processBean);
			}
	        String csv = STTUtil.bean2csv(ex01);
	        request.setAttribute(STT_Constants.MESSAGE, csv);			
		}
		return mapping.findForward(STT_Constants.SUCCESS);
    
	}
	
	public ActionForward processEnquireStockPosition(ProcessBean processBean){
        commonDebug.info("PSViewProvieder Processor: processStockHoldings");
	    return this.process(processBean);
	}
	
	public ActionForward processEnquireAccout(ProcessBean processBean){
		commonDebug.info("PSViewProvieder Processor: processAcSummary");
	    return this.process(processBean);
	}
	
	public ActionForward processEnquireAccoutDetail(ProcessBean processBean){
		commonDebug.info("PSViewProvieder Processor: processAcDetail");
	    return this.process(processBean);
	}

	public ActionForward processECertLogin(ProcessBean processBean) {
		return null;
	}

	/**
	 * 
	 */
	public ActionForward processException(ProcessBean processBean) {
		ITSZException ex=(ITSZException)processBean.getObject();
		HttpServletRequest request = processBean.getRequest();
		ActionMapping mapping = processBean.getMapping();
        request.setAttribute(STT_Constants.ITSZ_EXCEPTION,ex);
        return mapping.findForward(STT_Constants.FAIL);
	}
	
	public ActionForward processPlaceOrder(ProcessBean processBean){
		commonDebug.info("PSViewProvieder Processor: processInputOrder");
		return this.process(processBean);
	}

	public ActionForward processLogout(ProcessBean processBean) {
		commonDebug.info("PSViewProvieder Processor: processLogout");
		HttpServletRequest request = processBean.getRequest();
		ActionMapping mapping = processBean.getMapping();
		ITSZException exception = new ITSZException();
		exception.setErrorCode(STT_Constants.LOGOUT_STATUS);
		request.setAttribute(STT_Constants.ITSZ_EXCEPTION,exception);
		return mapping.findForward(STT_Constants.FAIL);
	}

	public ActionForward processModifyOrder(ProcessBean processBean) {
		commonDebug.info("PSViewProvieder Processor: processModifyOrder");
		HttpServletRequest request = processBean.getRequest();
		ActionMapping mapping = processBean.getMapping();
		Object result = getEx01Result(processBean.getObject());
		String csv = "";
		if(result instanceof ModifyOrderResponseModel){
			ModifyOrderResponseModel responseModel = (ModifyOrderResponseModel)result;
			csv=STTUtil.bean2csv(responseModel.getModifyOrderRes());
		}else{
			Exception01 ex01 = (Exception01)result;
			if(Consts.Error.Code.CONNECT_MCS.equals(ex01.getErrorSubCode()) ||
					Consts.Error.Code.LOTSIZENOTMATCH.equals(ex01.getErrorSubCode()) ||
					Consts.Error.Code.TRAD_MODIFY_NOTMODIFY.equals(ex01.getErrorSubCode()) ||
					Consts.Error.Code.TRAD_TOOMUCH_MODIFYQTY.equals(ex01.getErrorSubCode()) ||
					Consts.Error.Code.TRAD_TOOLESS_MODIFYQTY.equals(ex01.getErrorSubCode()) ||
					Consts.Error.Code.TRAD_PASSWORD_NOTMACHRULE.equals(ex01.getErrorSubCode())){
				processBean.setObject(new ITSZException(ex01.getErrorSubCode()));
				return processException(processBean);
			}
	        csv = STTUtil.bean2csv(ex01);
		}
		request.setAttribute(STT_Constants.MESSAGE,csv);
		return mapping.findForward(STT_Constants.SUCCESS);
	}
	
	public ActionForward processTradeHistory(ProcessBean processBean){
		commonDebug.info("PSViewProvieder Processor: processTradeHistory");
		HttpServletRequest request = processBean.getRequest();
		ActionMapping mapping = processBean.getMapping();
		Object result = getEx01Result(processBean.getObject());
		String csv = "";
		if(result instanceof OrderDetailTradeResponseModel){
			OrderDetailTradeResponseModel responseModel = (OrderDetailTradeResponseModel)result;
			csv = STTUtil.bean2csv(responseModel.getTradeHistoryResponse());
		}else{
			Exception01 ex01 = (Exception01)result;
			if(Consts.Error.Code.CONNECT_MCS.equals(ex01.getErrorSubCode())){
				processBean.setObject(new ITSZException(ex01.getErrorSubCode()));
				return processException(processBean);
			}
	        csv = STTUtil.bean2csv(ex01);
		}
		request.setAttribute(STT_Constants.MESSAGE,csv);
		return mapping.findForward(STT_Constants.SUCCESS);
	}

	public ActionForward processQueryOrder(ProcessBean processBean) {
		return null;
	}

	public ActionForward processQuashOrder(ProcessBean processBean) {
		return null;
	}
	
	public ActionForward processOrderFee(ProcessBean processBean) {
		commonDebug.info("PSViewProvieder Processor: processCalOrderFee");		
		return this.process(processBean);
	}
	
	public ActionForward processAccessRTQ(ProcessBean processBean){
		AccessRTQResponseModel accessRTQResp = (AccessRTQResponseModel)processBean.getObject();
		String status = accessRTQResp.getReturnCode();
		processBean.getRequest().setAttribute("result", accessRTQResp);
		if("ADMINPROTAL070013".equalsIgnoreCase(status) || "ADMINPROTAL070014".equalsIgnoreCase(status)){
			processBean.getRequest().setAttribute("error", status);
			return processBean.getMapping().findForward("no_purchased");
		}
		if(!Consts.Global.Status.NORMAL.equalsIgnoreCase(status) 
		         && !Consts.Global.Status.SUCCESS.equalsIgnoreCase(status)){
			processBean.getRequest().setAttribute("error", status);
			return processBean.getMapping().findForward("es_exception");
		}
		String forward = "no_purchased";
		StreamRtqForward sf = new StreamRtqForward();
		if(accessRTQResp.getRtqAccess()!=null){
			if (Constants.PROVIDER_ETNET.equals(accessRTQResp.getRtqAccess().getProductId())) {
	        	forward = sf.etnetAppletRTQ(processBean.getRequest(),accessRTQResp.getRtqAccess());
	        }else if (Constants.PROVIDER_AASTOCK.equals(accessRTQResp.getRtqAccess().getProductId())) {
	            forward = sf.aaStockRTQ(processBean.getRequest(),accessRTQResp.getRtqAccess());
	        }
		}
		return processBean.getMapping().findForward(forward);
	}
	
	public ActionForward processEnquireRTQ(ProcessBean processBean){
		commonDebug.info("PSViewProvieder Processor: processSimple rtq");
		HttpServletRequest request = processBean.getRequest();
		ActionMapping mapping = processBean.getMapping();
		Object result = getEx01Result(processBean.getObject());
		if(result instanceof EnquireRTQResponseModel){
			EnquireRTQResponseModel enquireRTQResponseModel=(EnquireRTQResponseModel)result;
			List<EnquireRTQResponse> list=enquireRTQResponseModel.getEnquireRTQResponse();
			String csv="";
			if(null!=list&&list.size()>0){
				EnquireRTQResponse enquireRTQResponse=(EnquireRTQResponse)list.get(0);
				QuoteResponse quote = new QuoteResponse();
				quote.setInstrCode(enquireRTQResponse.getCode());
				quote.setInstrName(enquireRTQResponse.getName());
				quote.setLotSize(enquireRTQResponse.getLotSize());
				quote.setNominalPrice(enquireRTQResponse.getPrice());
				quote.setHighPrice(enquireRTQResponse.getHigh());
				quote.setLowPrice(enquireRTQResponse.getLow());
				quote.setOpenPrice(enquireRTQResponse.getOpen());
				quote.setPrevClosingPrice(enquireRTQResponse.getPClose());
				quote.setPriceChange(enquireRTQResponse.getChange());
				quote.setPriceChangePerc(enquireRTQResponse.getPctChange());
				quote.setPe(enquireRTQResponse.getPe());
				quote.setCurrency(enquireRTQResponse.getCurrency());
				String formatedLowSpread;
				String formatedHighSpread;
				try{
				    formatedLowSpread = FormatConversion.hold3Decimal(new BigDecimal(enquireRTQResponse.getLowSpread()));
				    formatedHighSpread = FormatConversion.hold3Decimal(new BigDecimal(enquireRTQResponse.getHighSpread()));
				}catch(Exception e){
				    formatedLowSpread="0.000";
				    formatedHighSpread="0.000";
				}   
				String spread = formatedLowSpread+"/"+formatedHighSpread;
				quote.setSpread(spread);
				quote.setTotalTradeVolume(enquireRTQResponse.getVolume());
				quote.setTradeAmount(enquireRTQResponse.getTurnover());
				quote.setBestAskPrice(enquireRTQResponse.getBestAsk());
				quote.setBestBidPrice(enquireRTQResponse.getBestBid());
				String timeString = null;
				try{
				    long time = Long.parseLong(enquireRTQResponse.getTime());		    
				    timeString = STTUtil.getTimeString(time);
				}catch(Exception e){
				    commonDebug.info(e.getMessage(),e);
				}
				quote.setPostTime(timeString);				
				quote.setStockStatus(enquireRTQResponse.getStatus());
				CSVProcessor processor;					
		        try {
		            processor = new CSVProcessor();
		            csv = processor.BeanToCSV(quote);
		        } catch (Exception e) {
		            commonDebug.info(e.getMessage(),e);
		        }
			}
	        request.setAttribute(STT_Constants.MESSAGE, csv);
		}else{
			Exception01 ex01 = (Exception01)result;
			if(Consts.Error.Code.ERRORCODE_CONNECT_RTQ.equals(ex01.getErrorSubCode())){
				processBean.setObject(new ITSZException(ex01.getErrorSubCode()));
				return processException(processBean);
			}
	        String csv = STTUtil.bean2csv(ex01);
	        request.setAttribute(STT_Constants.MESSAGE, csv);
		}
		return mapping.findForward(STT_Constants.SUCCESS);
	}

	public ActionForward processShortEnquireRTQ(ProcessBean processBean){
		HttpServletRequest request = processBean.getRequest();
		ActionMapping mapping = processBean.getMapping();
		Object result = getEx01Result(processBean.getObject());
		if(result instanceof EnquireShortRTQResponseModel){
			EnquireShortRTQResponseModel responseModel = (EnquireShortRTQResponseModel)processBean.getObject();
			CSVProcessor processor;
			String csv="";
	        try {
	            processor = new CSVProcessor();
	            csv = processor.BeanToCSV(responseModel.getShortRTQResponse());
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        request.setAttribute(STT_Constants.MESSAGE, csv);
		}else{
			Exception01 ex01 = (Exception01)result;
			if(Consts.Error.Code.ERRORCODE_CONNECT_RTQ.equals(ex01.getErrorSubCode())){
				processBean.setObject(new ITSZException(ex01.getErrorSubCode()));
				return processException(processBean);
			}
	        String csv = STTUtil.bean2csv(ex01);
	        request.setAttribute(STT_Constants.MESSAGE, csv);
		}
        return mapping.findForward(STT_Constants.SUCCESS);
	}
	
	public ActionForward processBroadcast(ProcessBean processBean){
		HttpServletRequest request = processBean.getRequest();
		ActionMapping mapping = processBean.getMapping();
		BroadcastInfo broadcastInfo = new BroadcastInfo();
		broadcastInfo.setBroadcastCol((List)processBean.getObject());
		String csv="";
        try {
        	CSVProcessor processor = new CSVProcessor();
            csv = processor.BeanToCSV(broadcastInfo);
        } catch (Exception e) {
            commonDebug.info(e.getMessage(),e);
        }
		request.setAttribute(STT_Constants.MESSAGE,csv);
		return mapping.findForward(STT_Constants.SUCCESS);
	}
	

	public ActionForward processQueryConfig(ProcessBean processBean){
		HttpServletRequest request = processBean.getRequest();
		ActionMapping mapping = processBean.getMapping();
		Collection configResults = (Collection)processBean.getObject();
		String csv = STTUtil.config2csv(configResults);
		request.setAttribute(STT_Constants.MESSAGE, csv);
        return mapping.findForward(STT_Constants.SUCCESS);
	}
	
	private Object getEx01Result(Object result){
		if(result instanceof AbstractResponseModel) {
			AbstractResponseModel ex=(AbstractResponseModel)result;
			if(!Consts.Error.Code.NORMAL.equals(ex.getReturnCode())&&
					!Consts.Error.Code.INVIDPASS.equals(ex.getReturnCode())&&
					!Consts.Error.Code.TRSPTD_ERROR_INV_PASS.equals(ex.getReturnCode())&&
					!Consts.Error.Code.CHANGEPASS_INVALID_OLDPASS_N_V.equals(ex.getReturnCode())){
				Exception01 ex01 = new Exception01();
				ex01.setErrorSubCode(ex.getReturnCode());
				return ex01;
			}
		}
        return result;
	}
	
	public ActionForward processTradeList(ProcessBean processBean){
        return this.process(processBean);
	}
	
	public ActionForward processExportTradeList(ProcessBean processBean){
		HttpServletRequest request = processBean.getRequest();
		ActionMapping mapping = processBean.getMapping();
		Object result = getEx01Result(processBean.getObject());
		String csv = "";
		if(result instanceof TradeListResponseModel){
			TradeListResponseModel trm=(TradeListResponseModel)result;
			csv=STTUtil.bean2csv(trm.getResponse());
		}else{
			Exception01 ex01 = (Exception01)result;
			if(Consts.Error.Code.CONNECT_MCS.equals(ex01.getErrorSubCode())){
				processBean.setObject(new ITSZException(ex01.getErrorSubCode()));
				return processException(processBean);
			}
	        csv = STTUtil.bean2csv(ex01);	
		}
        request.setAttribute(STT_Constants.MESSAGE, csv);		
        return mapping.findForward("success");
	}
	
	public ActionForward processCalSnapshotProfile(ProcessBean processBean){
		HttpServletRequest request = processBean.getRequest();
		ActionMapping mapping = processBean.getMapping();
		SnapshotProfileResponseModel resModel = (SnapshotProfileResponseModel)processBean.getObject();		
		String csv = STTUtil.bean2csv(resModel.getSnapshotProfileResponse());
		if(StringUtils.isBlank(csv)){
			csv = "RSSP=(MSID='RSSP', PROF-L=())";
		}
		request.setAttribute(STT_Constants.MESSAGE, csv);
        return mapping.findForward(STT_Constants.SUCCESS);
	}
	
	public ActionForward processCalStreamRtqProfile(ProcessBean processBean){
		HttpServletRequest request = processBean.getRequest();
		ActionMapping mapping = processBean.getMapping();
		StreamRtqProfileResponseModel resModel = (StreamRtqProfileResponseModel)processBean.getObject();		
		String csv = STTUtil.bean2csv(resModel.getStreamRtqProfileResponse());
		if(StringUtils.isBlank(csv)){
			csv = "RSTP=(MSID='RSTP', APID=, QTPD=, VQPD=, VQUL=, VLID=, VPSD=)";
		}
		request.setAttribute(STT_Constants.MESSAGE, csv);
        return mapping.findForward(STT_Constants.SUCCESS);
	}
}
