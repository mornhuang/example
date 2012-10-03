package com.itsz.sht.vp;

import org.apache.struts.action.ActionForward;

import com.itsz.sht.vp.common.ProcessBean;

/**
 * $Id: ViewProvider.java,v 1.50 2011/05/09 09:52:38 lpchen Exp $
 * @Project:portal.head
 * @File:ViewProvider.java
 * @Description:
 * @Author:Cimenon Saint
 * @Date:2007-5-21
 */
public interface ViewProvider {

	/**
	 * 
	 * @Author:Cimenon Saint
	 * @Time:15:09:59
	 * @param processBean
	 * @return
	 */
	public ActionForward processLogin(ProcessBean processBean);

	/**
	 * 
	 * @Author:Clyao
	 * @Time:15:09:59
	 * @param processBean
	 * @return
	 */
	public ActionForward processRisk(ProcessBean processBean);
	
	public ActionForward processChangeTAndC(ProcessBean processBean);

	public ActionForward processShowTPSetting(ProcessBean processBean);
	public ActionForward processBroadcast(ProcessBean processBean);
	public ActionForward processBulkCancelOrder(ProcessBean processBean);
	public ActionForward processProfitAndLossEnquiry(ProcessBean processBean);
	public ActionForward processProfitAndLossUpdate(ProcessBean processBean);
	/**
	 * 
	 * @Author:Cimenon Saint
	 * @Time:15:10:27
	 * @param processBean
	 * @return
	 */
	public ActionForward processECertLogin(ProcessBean processBean);
	public ActionForward processCashDetail(ProcessBean processBean);
	
	/**
	 * 
	 * @Author:Cimenon Saint
	 * @Time:15:10:34
	 * @param processBean
	 * @return
	 */
	public ActionForward processException(ProcessBean processBean);
	
	/**
	 * 
	 * @Author:Cimenon Saint
	 * @Time:15:10:41
	 * @param processBean
	 * @return
	 */
	public ActionForward processLogout(ProcessBean processBean);
	
	/**
	 * 
	 * @Author:Cimenon Saint
	 * @Time:15:10:46
	 * @param processBean
	 * @return
	 */
	public ActionForward processModifyOrder(ProcessBean processBean);
	
	/**
	 * 
	 * @Author:Cimenon Saint
	 * @Time:15:10:51
	 * @param processBean
	 * @return
	 */
	public ActionForward processQueryOrder(ProcessBean processBean);
	
	/**
	 * 
	 * @Author:Cimenon Saint
	 * @Time:15:10:55
	 * @param processBean
	 * @return
	 */
	public ActionForward processQuashOrder(ProcessBean processBean);
	
	/**
	 * 
	 * @Author:Cimenon Saint
	 * @Time:15:11:00
	 * @param processBean
	 * @return
	 */
	public ActionForward processPlaceOrder(ProcessBean processBean);
	
	public ActionForward processTaskPlaceOrder(ProcessBean processBean);
	
	/**
	 * 
	 * @Author:Cimenon Saint
	 * @Time:15:11:00
	 * @param processBean
	 * @return
	 */
	public ActionForward processPrePlaceOrder(ProcessBean processBean);
	
	/**
	 * 
	 * @Author:Cimenon Saint
	 * @Time:15:11:06
	 * @param processBean
	 * @return
	 */
	public ActionForward processOrderFee(ProcessBean processBean);
	
	/**
	 * 
	 * @Author:Cimenon Saint
	 * @Time:15:11:06
	 * @param processBean
	 * @return
	 */
	public ActionForward processOrderDetail(ProcessBean processBean);
	
	/**
	 * 
	 * @Author:Cimenon Saint
	 * @Time:15:11:06
	 * @param processBean
	 * @return
	 */
	public ActionForward processModifyDetail(ProcessBean processBean);
	
	/**
	 * 
	 * @Author:Cimenon Saint
	 * @Time:15:11:06
	 * @param processBean
	 * @return
	 */
	public ActionForward processOrderList(ProcessBean processBean);
	
	/**
	 * 
	 * @Author:Cimenon Saint
	 * @Time:2007-11-8 下午05:48:56
	 * @param processBean
	 * @return
	 */
	public ActionForward processPaginationOrder(ProcessBean processBean);
	
	/**
	 * 
	 * @Author:Cimenon Saint
	 * @Time:15:11:06
	 * @param processBean
	 * @return
	 */
	public ActionForward processCancelOrder(ProcessBean processBean);	
	
	/**
	 * 
	 * @Author:jhu
	 * @Time:2007-7-4 14:31:16
	 * @param processBean
	 * @return
	 */
	public ActionForward processAas(ProcessBean processBean);
	
	public ActionForward processVerifyPassword(ProcessBean processBean);
	
	public ActionForward processNewsUrl(ProcessBean processBean);

	public ActionForward processServerTime(ProcessBean processBean);

	public ActionForward processTradeHistory(ProcessBean processBean);
	
	/*
	 * kyzou
	 * 20030821
	 */
	public ActionForward processChangeLang(ProcessBean processBean);
	
	public ActionForward processVerifyTrading(ProcessBean processBean);
	
	public ActionForward processVerifyPlaceOrder(ProcessBean processBean);
	
	public ActionForward processInitModifyOrder(ProcessBean processBean);
	
	public ActionForward processInitCancelOrder(ProcessBean processBean);
	
	public ActionForward processEnquireAccountList(ProcessBean processBean);
	
	public ActionForward processEnquireStockPosition(ProcessBean processBean);
	
	public ActionForward processEnquireAccout(ProcessBean processBean);
	public ActionForward processEnquireAccoutDetail(ProcessBean processBean);
	public ActionForward processEnquireMisAccount(ProcessBean processBean);
	
	public ActionForward processEnquireRTQ(ProcessBean processBean);
	
	public ActionForward processShortEnquireRTQ(ProcessBean processBean);
	
	public ActionForward processEnquireIndexRTQ(ProcessBean processBean);
	
	public ActionForward processChangeTransactionProtection(ProcessBean processBean);
	
	public ActionForward processForceChangePassword(ProcessBean processBean);
	
	public ActionForward processChangePassword(ProcessBean processBean);
	
	public ActionForward processAnnounce(ProcessBean processBean);
	
	public ActionForward processShkIsm(ProcessBean processBean);
	
	
	//for ipo
	public ActionForward processIPOQueryList(ProcessBean processBean);
	public ActionForward processGetIPOQueryCode(ProcessBean processBean);
	public ActionForward processIPOQueryQty(ProcessBean processBean);
	public ActionForward processIPOQueryAmt(ProcessBean processBean);
	public ActionForward processInsertIPO(ProcessBean processBean);
	public ActionForward processQueryCode(ProcessBean processBean);
	public ActionForward processRemoveIPO(ProcessBean processBean);
	
	public ActionForward processCalBasketFee(ProcessBean processBean);
	public ActionForward processBasketSaveAndSend(ProcessBean processBean);
	public ActionForward processBasketNameUpdate(ProcessBean processBean);
	public ActionForward processEnquireBasketDetail(ProcessBean processBean);
	public ActionForward processListBasket(ProcessBean processBean);
	public ActionForward processQueryConfig(ProcessBean processBean);
	public ActionForward processTradeList(ProcessBean processBean);
	public ActionForward processOrderTradeList(ProcessBean processBean);
	public ActionForward processOrderTransactionHistory(ProcessBean processBean);
	public ActionForward processExportTradeList(ProcessBean processBean);
	public ActionForward processCalSnapshotProfile(ProcessBean processBean);
	public ActionForward processCalStreamRtqProfile(ProcessBean processBean);
	public ActionForward processFundTranser(ProcessBean processBean);
	public ActionForward processEnquireMarginRations(ProcessBean processBean);
	public ActionForward processPPSEnquiry(ProcessBean processBean);
	public ActionForward processPPSTransfer(ProcessBean processBean);
	public ActionForward processEPaymentEntrance(ProcessBean processBean);
	public ActionForward processBOCTransfer(ProcessBean processBean);
	public ActionForward processCallMos(ProcessBean processBean);
	public ActionForward processMarginRatios(ProcessBean processBean);
	public ActionForward processAccessRTQ(ProcessBean processBean);
	public ActionForward processPurchaseRTQ(ProcessBean processBean);
	public ActionForward processReserveRTQ(ProcessBean processBean);
	public ActionForward processListSelectRTQ(ProcessBean processBean);
	public ActionForward processSelectRTQStatus(ProcessBean processBean);
	public ActionForward processRTQProductList(ProcessBean processBean);
	public ActionForward processUpdateRTQStatus(ProcessBean processBean);
	public ActionForward processCancelReserveRTQ(ProcessBean processBean);
	public ActionForward processAccessSHK(ProcessBean processBean);
	public ActionForward processMain(ProcessBean processBean);
	public ActionForward processPurchaseAccessSHK(ProcessBean processBean);
	public ActionForward processCancelSHK(ProcessBean processBean);
	public ActionForward processAccessEnterSHK(ProcessBean processBean);
	public ActionForward processGetUserNotificationEmail(ProcessBean processBean);
	public ActionForward processUpdateUserNotificationEmail(ProcessBean processBean);
	public ActionForward processAccessSHK2(ProcessBean processBean);
	public ActionForward processCheckFundDeposit(ProcessBean processBean);
	public ActionForward processEMCCount(ProcessBean processBean);
	public ActionForward processAccountEnquiryInit(ProcessBean processBean);
	public ActionForward processAccountPosition(ProcessBean processBean);
	public ActionForward processPopupRTQ(ProcessBean processBean);
	public ActionForward processStockRTQ(ProcessBean processBean);
	public ActionForward processAccountSummary(ProcessBean processBean);

	//add by Arthur Chen on 20110421 for eipo
	public ActionForward processEIPOQueryList(ProcessBean processBean);
	public ActionForward processEIPOSub(ProcessBean processBean);
	public ActionForward processEIPOSubDetail(ProcessBean processBean);
	public ActionForward processEIPOSubscriptionSubmit(ProcessBean processBean);

	//add by Arthur xli on 20110421 for eipo
	public ActionForward processEIPOSubscriptionDetail(ProcessBean processBean);
	public ActionForward processEIPOSubscriptionEnquiry(ProcessBean processBean);
	public ActionForward processCancelEIPOSubscription(ProcessBean processBean);
	public ActionForward processCancelEIPOSubscriptionSubmit(ProcessBean processBean);
	public ActionForward processEIPOMasterDetail(ProcessBean processBean);
}
