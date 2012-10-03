package com.itsz.sht.vp;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForward;

import com.itsz.sht.common.Consts;
import com.itsz.sht.common.model.common.LoginUserInfo;
import com.itsz.sht.common.model.common.response.AccessSHKResponseModel;
import com.itsz.sht.common.model.common.response.PurchaseServiceResponseModel;
import com.itsz.sht.common.model.common.response.PurchasedProductResponseModel;
import com.itsz.sht.common.model.common.response.UserProductResponseModel;
import com.itsz.sht.common.model.response.ChangePwdResponseModel;
import com.itsz.sht.common.model.response.FundDepositResponseModel;
import com.itsz.sht.dao.hibernate.model.Product;
import com.itsz.sht.vp.common.ProcessBean;

/**
 * $Id: ViewProviderImpl.java,v 1.64 2011/05/09 09:52:38 lpchen Exp $
 * @Project:portal.head
 * @File:ViewProviderImpl.java
 * @Description:
 * @Author:Cimenon Saint
 * @Date:2007-5-22
 */
public class ViewProviderImpl implements ViewProvider {
	//debug
	protected static Log commonDebug = LogFactory.getLog(Consts.Log.Debug.COMMON);
	protected static Log mcsDebug = LogFactory.getLog(Consts.Log.Debug.MCS);
	protected static Log rtqDebug = LogFactory.getLog(Consts.Log.Debug.QS);
	protected static Log esDebug = LogFactory.getLog(Consts.Log.Debug.ESERVICE);
	protected static Log commonInfo = LogFactory.getLog(Consts.Log.Info.COMMON);
	protected static Log mcsInfo = LogFactory.getLog(Consts.Log.Info.MCS);
	protected static Log rtqInfo = LogFactory.getLog(Consts.Log.Info.QS);
	protected static Log esInfo = LogFactory.getLog(Consts.Log.Info.ESERVICE);
	
	/**
	 * 
	 */
	public ActionForward processLogin(ProcessBean processBean) {
		return null;
	}
	
	public ActionForward processTaskPlaceOrder(ProcessBean processBean) {
		return null;
	}
	
	public ActionForward processCashDetail(ProcessBean processBean){
		return null;
	}
	public ActionForward processBulkCancelOrder(ProcessBean processBean){
		return null;
	}
	public ActionForward processProfitAndLossEnquiry(ProcessBean processBean){
		return null;
	}
	public ActionForward processProfitAndLossUpdate(ProcessBean processBean){
		return null;
	}
	
	/**
	 * 
	 */
	public ActionForward processRisk(ProcessBean processBean) {
		return null;
	}
	
	public ActionForward processBroadcast(ProcessBean processBean){
		return null;
	}
	
	public ActionForward processChangeTAndC(ProcessBean processBean) {	
		return null;
	}
	
	public ActionForward processShowTPSetting(ProcessBean processBean) {	
		return null;
	}
	
	/**
	 * 
	 */
	public ActionForward processECertLogin(ProcessBean processBean) {
		return null;
	}
	
	/**
	 * 
	 */
	public ActionForward processException(ProcessBean processBean) {
		return null;
	}
	
	/**
	 * 
	 */
	public ActionForward processLogout(ProcessBean processBean) {
		return null;
	}
	
	/**
	 * 
	 */
	public ActionForward processModifyOrder(ProcessBean processBean) {
		return null;
	}
	
	/**
	 * 
	 */
	public ActionForward processQueryOrder(ProcessBean processBean) {
		return null;
	}
	
	/**
	 * 
	 */
	public ActionForward processQuashOrder(ProcessBean processBean) {
		return null;
	}
	
	/**
	 * 
	 */
	public ActionForward processPlaceOrder(ProcessBean processBean) {
		return null;
	}
	
	public ActionForward processPrePlaceOrder(ProcessBean processBean){
		return null;
	}
	/**
	 * 
	 */
	public ActionForward processOrderFee(ProcessBean processBean) {
		return null;
	}
	
	/**
	 * 
	 * @Author:Cimenon Saint
	 * @Time:15:11:06
	 * @param processBean
	 * @return
	 */
	public ActionForward processOrderDetail(ProcessBean processBean){
		return null;
	}
	
	/**
	 * 
	 * @Author:Cimenon Saint
	 * @Time:15:11:06
	 * @param processBean
	 * @return
	 */
	public ActionForward processModifyDetail(ProcessBean processBean){
		return null;
	}	
	
	/**
	 * 
	 * @Author:Cimenon Saint
	 * @Time:15:11:06
	 * @param processBean
	 * @return
	 */
	public ActionForward processOrderList(ProcessBean processBean){
		return null;
	}
	
	/**
	 * 
	 * @Author:Cimenon Saint
	 * @Time:15:11:06
	 * @param processBean
	 * @return
	 */	
	public ActionForward processCancelOrder(ProcessBean processBean){
		return null;
	}
	
	/**
	 * @Author:jhu
	 * @Time:2007-7-4 14:31:16
	 * @param processBean
	 * @return
	 */
	public ActionForward processAas(ProcessBean processBean){
		return null;
	}

	public ActionForward processVerifyPassword(ProcessBean processBean){
		return null;
	}
	
	public ActionForward processNewsUrl(ProcessBean processBean){
		return null;
	}

	public ActionForward processServerTime(ProcessBean processBean){
		return null;
	}
	
	public ActionForward processTradeHistory(ProcessBean processBean){
		return null;
	}
	/**
	 * 
	 */
	public ActionForward processPaginationOrder(ProcessBean processBean) {
		return null;
	}
	
	/*
	 * kyzou
	 * 20030821
	 */
	public ActionForward processChangeLang(ProcessBean processBean){
		return null;
	}
	
	public ActionForward processVerifyTrading(ProcessBean processBean){
		return null;
	}
	
	public ActionForward processVerifyPlaceOrder(ProcessBean processBean){
		return null;
	}
	
	public ActionForward processInitModifyOrder(ProcessBean processBean){
		return null;
	}
	
	public ActionForward processInitCancelOrder(ProcessBean processBean){
		return null;
	}
	
	public ActionForward processEnquireAccountList(ProcessBean processBean){
		return null;
	}
	
	public ActionForward processBOCTransfer(ProcessBean processBean){
		return null;
	}
	
	public ActionForward processEnquireStockPosition(ProcessBean processBean){
		return null;
	}
	
	public ActionForward processEnquireAccout(ProcessBean processBean){
		return null;
	}
	
	public ActionForward processEnquireAccoutDetail(ProcessBean processBean){
		return null;
	}
	
	public ActionForward processEnquireMisAccount(ProcessBean processBean){
		return null;
	}
	
	public ActionForward processEnquireRTQ(ProcessBean processBean){
		return null;
	}
	
	public ActionForward processShortEnquireRTQ(ProcessBean processBean){
		return null;
	}
	
	public ActionForward processEnquireIndexRTQ(ProcessBean processBean) {
		return null;
	}
	
	public ActionForward processChangeTransactionProtection(ProcessBean processBean){
		return null;
	}
	
	public ActionForward processForceChangePassword(ProcessBean processBean){
		HttpServletRequest request=processBean.getRequest();
		LoginUserInfo userinfo=(LoginUserInfo)(processBean.getRequest().getSession().getAttribute("loginUserInfo"));
		request.setAttribute("loginStatus", userinfo.getLoginStatus());
		String str=((ChangePwdResponseModel)processBean.getObject()).getChangePasswordStatus();
		if(str=="SUCCESS"||"SUCCESS".equals(str)){
			request.setAttribute("changePasswordStatus", str);
			request.setAttribute("StatusKey", "message.setting.chgpwd.success");
			return processBean.getMapping().findForward("success");
		}else if(str=="INV_PASS"||"INV_PASS".equals(str)){
			request.setAttribute("changePasswordStatus", str);
			request.setAttribute("StatusKey", str);
			return processBean.getMapping().findForward("fail");
		}else if(StringUtils.indexOf(str, "PASS:")>0){
			str="INV_N_PASS_N";
			request.setAttribute("changePasswordStatus", str);
			request.setAttribute("StatusKey", str);
			return processBean.getMapping().findForward("fail");
		}else{
			request.setAttribute("changePasswordStatus", ((ChangePwdResponseModel)processBean.getObject()).getReturnCode());
			request.setAttribute("StatusKey", ((ChangePwdResponseModel)processBean.getObject()).getReturnCode());
			return processBean.getMapping().findForward("fail");
		}
		
	}
	public ActionForward processChangePassword(ProcessBean processBean){
		HttpServletRequest request=processBean.getRequest();
		String str=((ChangePwdResponseModel)processBean.getObject()).getChangePasswordStatus();
		if(str=="SUCCESS"||"SUCCESS".equals(str)){
			request.setAttribute("changePasswordStatus", str);
			request.setAttribute("StatusKey", "message.setting.chgpwd.success");
			return processBean.getMapping().findForward("success");
		}else if(str=="INV_PASS"||"INV_PASS".equals(str)){
			request.setAttribute("changePasswordStatus", str);
			request.setAttribute("StatusKey", str);
			return processBean.getMapping().findForward("fail");
		}else if(StringUtils.indexOf(str, "PASS:")>0){
			str="INV_N_PASS_N";
			request.setAttribute("changePasswordStatus", str);
			request.setAttribute("StatusKey", str);
			return processBean.getMapping().findForward("fail");
		}else{
			request.setAttribute("changePasswordStatus", ((ChangePwdResponseModel)processBean.getObject()).getReturnCode());
			request.setAttribute("StatusKey", ((ChangePwdResponseModel)processBean.getObject()).getReturnCode());
			return processBean.getMapping().findForward("fail");
		}
		
	}
	public ActionForward processAnnounce(ProcessBean processBean) {
		return null;
	}
	
	//for basket
	public ActionForward processCalBasketFee(ProcessBean processBean){
		return null;
	}
	public ActionForward processBasketSaveAndSend(ProcessBean processBean){
		return null;
	}
	public ActionForward processBasketNameUpdate(ProcessBean processBean){
		return null;
	}
	public ActionForward processEnquireBasketDetail(ProcessBean processBean){
		return null;
	}
	public ActionForward processListBasket(ProcessBean processBean){
		return null;
	}
	public ActionForward processQueryConfig(ProcessBean processBean){
		return null;
	}	
	public ActionForward processTradeList(ProcessBean processBean){
		return null;
	}
	public ActionForward processOrderTradeList(ProcessBean processBean){
		return null;
	}
	public ActionForward processOrderTransactionHistory(ProcessBean processBean){
		return null;
	}
	public ActionForward processExportTradeList(ProcessBean processBean){
		return null;
	}
	public ActionForward processCalSnapshotProfile(ProcessBean processBean){
		return null;
	}
	public ActionForward processCalStreamRtqProfile(ProcessBean processBean){
		return null;
	}

	public ActionForward processIPOQueryList(ProcessBean processBean) {
		return null;
	}

	public ActionForward processGetIPOQueryCode(ProcessBean processBean) {
		return null;
	}

	public ActionForward processIPOQueryQty(ProcessBean processBean) {
		return null;
	}
	
	public ActionForward processIPOQueryAmt(ProcessBean processBean){
		return null;
	}

	public ActionForward processInsertIPO(ProcessBean processBean) {
		return null;
	}

	public ActionForward processRemoveIPO(ProcessBean processBean) {
		return null;
	}

	public ActionForward processQueryCode(ProcessBean processBean) {
		return null;
	}
	public ActionForward processFundTranser(ProcessBean processBean){
		return null;
	}
	public ActionForward processEnquireMarginRations(ProcessBean processBean){
		return null;
	}
	public ActionForward processPPSEnquiry(ProcessBean processBean){
		return null;
	}
	public ActionForward processPPSTransfer(ProcessBean processBean){
		return null;
	}
	public ActionForward processEPaymentEntrance(ProcessBean processBean){
		return null;
	}
	
	public ActionForward processCallMos(ProcessBean processBean){
		return null;
	}
	public ActionForward processMarginRatios(ProcessBean processBean) {
		return null;
	}
	
	public ActionForward processShkIsm(ProcessBean processBean){
		return null;		
	}
	public ActionForward processAccessRTQ(ProcessBean processBean){
		return null;		
	}
	public ActionForward processPurchaseRTQ(ProcessBean processBean){
		return null;		
	}
	public ActionForward processReserveRTQ(ProcessBean processBean){
		return null;		
	}
	public ActionForward processListSelectRTQ(ProcessBean processBean){
		return null;		
	}
	public ActionForward processSelectRTQStatus(ProcessBean processBean){
		return null;
	}
	public ActionForward processRTQProductList(ProcessBean processBean){
		return null;
	}
	public ActionForward processUpdateRTQStatus(ProcessBean processBean){
		return null;		
	}
	public ActionForward processCancelReserveRTQ(ProcessBean processBean){
		return null;		
	}
	public ActionForward processAccessSHK(ProcessBean processBean){
		HttpServletRequest request=processBean.getRequest();
		PurchasedProductResponseModel  ResponseModel=(PurchasedProductResponseModel)processBean.getObject();
		if(ResponseModel.isHavePurchased()){
			return processBean.getMapping().findForward("success");
		}else{
			Product p=(Product)ResponseModel.getProduct();
			request.setAttribute("PriceInHkd", p.getPriceInHkd());
			return processBean.getMapping().findForward("fail");
		}
	}
	
	public ActionForward processAccessSHK2(ProcessBean processBean){
		AccessSHKResponseModel accessSHKResponseModel=(AccessSHKResponseModel)processBean.getObject();
		HttpServletRequest request=processBean.getRequest();
		String StatusSHK=accessSHKResponseModel.getRtqStatus();
		if(StatusSHK=="AVAIL"||"AVAIL".equals(StatusSHK)){
		String url=accessSHKResponseModel.getRtqUrl();
		String[] strs= StringUtils.split(url, "?");
		String[] sec = StringUtils.split(strs[1], "=");
		request.setAttribute("url", strs[0]);
		request.setAttribute("security", sec[1]);
		String lang=accessSHKResponseModel.getLanguage();
		if(lang=="BIG5"||"BIG5".equals(lang)){
			request.setAttribute("language", "C");
		}else if(lang=="ENG"||"ENG".equals(lang)){
			request.setAttribute("language", "E");
		}else{
			request.setAttribute("language", "S");
		}
			return processBean.getMapping().findForward("success");
		}else{
			request.setAttribute("EnterStatus", "fail");
			return processBean.getMapping().findForward("fail");
		}
	}
	
	public ActionForward processAccessEnterSHK(ProcessBean processBean){
		AccessSHKResponseModel accessSHKResponseModel=(AccessSHKResponseModel)processBean.getObject();
		HttpServletRequest request=processBean.getRequest();
		String StatusSHK=accessSHKResponseModel.getRtqStatus();
		if(StatusSHK=="AVAIL"||"AVAIL".equals(StatusSHK)){
		String url=accessSHKResponseModel.getRtqUrl();
		String[] strs= StringUtils.split(url, "?");
		String[] sec = StringUtils.split(strs[1], "=");
		request.setAttribute("url", strs[0]);
		request.setAttribute("security", sec[1]);
		String lang=accessSHKResponseModel.getLanguage();
		if(lang=="BIG5"||"BIG5".equals(lang)){
			request.setAttribute("language", "C");
		}else if(lang=="ENG"||"ENG".equals(lang)){
			request.setAttribute("language", "E");
		}else{
			request.setAttribute("language", "S");
		}
			return processBean.getMapping().findForward("success");
		}else{
			request.setAttribute("EnterStatus", "fail");
			return processBean.getMapping().findForward("fail");
		}
	}
	
	public ActionForward processPurchaseAccessSHK(ProcessBean processBean){
		HttpServletRequest request=processBean.getRequest();
		PurchaseServiceResponseModel purchaseServiceResponseModel=(PurchaseServiceResponseModel)processBean.getObject();
		String purchstart=purchaseServiceResponseModel.getReturnCode();
		if(purchstart=="NORMAL"||"NORMAL".equals(purchstart)){
			return processBean.getMapping().findForward("success");
		}else{
			request.setAttribute("error", purchstart);
			return processBean.getMapping().findForward("error");
		}
		
	}
	public ActionForward processMain(ProcessBean processBean) {
		return null;
	}
	public ActionForward processCancelSHK(ProcessBean processBean){
		HttpServletRequest request=processBean.getRequest();
		UserProductResponseModel UserProductResponseModel=(UserProductResponseModel)processBean.getObject();
		String CanecelSHKStart=UserProductResponseModel.getReturnCode();
		if(CanecelSHKStart=="NORMAL"||"NORMAL".equals(CanecelSHKStart)){
			request.setAttribute("CanecelSHKStart", "success");
			return processBean.getMapping().findForward("success");
		}else{
			request.setAttribute("CanecelSHKStart", "fail");
			return processBean.getMapping().findForward("fail");
		}
	}
	
	public ActionForward processCheckFundDeposit(ProcessBean processBean){
		HttpServletRequest request=processBean.getRequest();
		FundDepositResponseModel fundDepositResponseModel=(FundDepositResponseModel)processBean.getObject();
		String returnCode=fundDepositResponseModel.getReturnCode();
		if(returnCode=="NORMAL"||"NORMAL".equals(returnCode)){
			request.setAttribute("requestNo", fundDepositResponseModel.getRequestNo());
			return processBean.getMapping().findForward("success");
		}else{
			return processBean.getMapping().findForward("fail");
		}
	}

	public ActionForward processGetUserNotificationEmail(ProcessBean processBean){
		return null;
	}
	public ActionForward processUpdateUserNotificationEmail(ProcessBean processBean){
		return null;
	}
	public ActionForward processEMCCount(ProcessBean processBean){
		return null;
	}
	public ActionForward processAccountEnquiryInit(ProcessBean processBean){
		return null;
	}
	public ActionForward processAccountPosition(ProcessBean processBean){
		return null;
	}
	public ActionForward processPopupRTQ(ProcessBean processBean){
		return null;
	}
	public ActionForward processStockRTQ(ProcessBean processBean){
		return null;
	}
	public ActionForward processAccountSummary(ProcessBean processBean){
		return null;
	}
//add by Arthur Chen on 20110421 for eipo
	public ActionForward processEIPOQueryList(ProcessBean processBean) {
		// TODO Auto-generated method stub
		return null;
	}
	public ActionForward processEIPOSub(ProcessBean processBean) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public ActionForward processEIPOSubscriptionSubmit(ProcessBean processBean) {
		return null;
	}
	
	public ActionForward processEIPOSubDetail(ProcessBean processBean) {
		return null;
	}

	public ActionForward processEIPOSubscriptionDetail(ProcessBean processBean) {
		return null;
	}

	public ActionForward processEIPOSubscriptionEnquiry(ProcessBean processBean) {
		return null;
	}

	public ActionForward processCancelEIPOSubscription(ProcessBean processBean) {
		return null;
	}

	public ActionForward processCancelEIPOSubscriptionSubmit(ProcessBean processBean) {
		return null;
	}

	public ActionForward processEIPOMasterDetail(ProcessBean processBean) {
		return null;
	}
}
