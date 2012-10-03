package com.itsz.sht.service;


import org.apache.commons.logging.Log;

import com.itsz.eipo.webservice.EIPOResponse;
import com.itsz.sht.common.LoggerFactory;
import com.itsz.sht.common.ServiceLocator;
import com.itsz.sht.common.model.common.request.AccessRTQRequestModel;
import com.itsz.sht.common.model.common.request.AccessSHKRequestModel;
import com.itsz.sht.common.model.common.request.ListSelectServiceRequestModel;
import com.itsz.sht.common.model.common.request.PurchaseServiceRequestModel;
import com.itsz.sht.common.model.common.request.PurchasedProductRequestModel;
import com.itsz.sht.common.model.common.request.RTQProductRequestModel;
import com.itsz.sht.common.model.common.request.ReserveServiceRequestModel;
import com.itsz.sht.common.model.common.request.UpdateUserProductRequestModel;
import com.itsz.sht.common.model.common.request.UserNotificationEmailRequestModel;
import com.itsz.sht.common.model.common.request.UserProfileRequestModel;
import com.itsz.sht.common.model.common.response.AccessRTQResponseModel;
import com.itsz.sht.common.model.common.response.AccessSHKResponseModel;
import com.itsz.sht.common.model.common.response.ListSelectServiceResponseModel;
import com.itsz.sht.common.model.common.response.PurchaseServiceResponseModel;
import com.itsz.sht.common.model.common.response.PurchasedProductResponseModel;
import com.itsz.sht.common.model.common.response.RTQProductResponseModel;
import com.itsz.sht.common.model.common.response.ReserveServiceResponseModel;
import com.itsz.sht.common.model.common.response.UserNotificationEmailResponseModel;
import com.itsz.sht.common.model.common.response.UserProductResponseModel;
import com.itsz.sht.common.model.common.response.UserProfileResponseModel;
import com.itsz.sht.common.model.request.AnnounceRequestModel;
import com.itsz.sht.common.model.request.BOCTransferRequestModel;
import com.itsz.sht.common.model.request.BulkCancelOrderRequestModel;
import com.itsz.sht.common.model.request.CancelEIPOSubscriptionRequestModel;
import com.itsz.sht.common.model.request.CancelEIPOSubscriptionSubmitRequestModel;
import com.itsz.sht.common.model.request.CancelOrderRequestModel;
import com.itsz.sht.common.model.request.CashDetailRequestModel;
import com.itsz.sht.common.model.request.ChangePwdRequestModel;
import com.itsz.sht.common.model.request.ChangeTAndCRequestModel;
import com.itsz.sht.common.model.request.EIPOMasterDetailRequestModel;
import com.itsz.sht.common.model.request.EIPORequestModel;
import com.itsz.sht.common.model.request.EIPOSubscriptionDetailRequestModel;
import com.itsz.sht.common.model.request.EIPOSubscriptionEnquiryRequestModel;
import com.itsz.sht.common.model.request.EIPOSubscriptionSubmitRequestModel;
import com.itsz.sht.common.model.request.EMCRequestModel;
import com.itsz.sht.common.model.request.EnquireAccountListRequestModel;
import com.itsz.sht.common.model.request.EnquireAccountRequestModel;
import com.itsz.sht.common.model.request.EnquireRTQRequestModel;
import com.itsz.sht.common.model.request.EnquireShortRTQRequestModel;
import com.itsz.sht.common.model.request.EnquiryPositionRequestModel;
import com.itsz.sht.common.model.request.FilterOrderRequestModel;
import com.itsz.sht.common.model.request.FundDepositRequestModel;
import com.itsz.sht.common.model.request.IPOQtyRequestModel;
import com.itsz.sht.common.model.request.IPORequestModel;
import com.itsz.sht.common.model.request.ListOrderRequestModel;
import com.itsz.sht.common.model.request.LoginRequestModel;
import com.itsz.sht.common.model.request.MarginFinancingListRequestModel;
import com.itsz.sht.common.model.request.ModifyOrderRequestModel;
import com.itsz.sht.common.model.request.MosRequestModel;
import com.itsz.sht.common.model.request.OrderDetailRequestModel;
import com.itsz.sht.common.model.request.OrderFeeRequestModel;
import com.itsz.sht.common.model.request.PPSEnquiryRequestModel;
import com.itsz.sht.common.model.request.PlaceOrderRequestModel;
import com.itsz.sht.common.model.request.PrePlaceOrderRequestModel;
import com.itsz.sht.common.model.request.ProfitAndLossEnqiryRequestModel;
import com.itsz.sht.common.model.request.ProfitAndLossUpdateRequestModel;
import com.itsz.sht.common.model.request.RemoveIPORequestModel;
import com.itsz.sht.common.model.request.SapRequestModel;
import com.itsz.sht.common.model.request.TradeListRequestModel;
import com.itsz.sht.common.model.request.TransactionProtectionRequestModel;
import com.itsz.sht.common.model.request.VerifyPasswordRequestModel;
import com.itsz.sht.common.model.request.VerifyPlaceOrderRequestModel;
import com.itsz.sht.common.model.response.AnnounceResponseModel;
import com.itsz.sht.common.model.response.BOCTransferResponseModel;
import com.itsz.sht.common.model.response.BulkCancelOrderResponseModel;
import com.itsz.sht.common.model.response.CancelEIPOSubscriptionResponseModel;
import com.itsz.sht.common.model.response.CancelOrderResponseModel;
import com.itsz.sht.common.model.response.CashDetailResponseModel;
import com.itsz.sht.common.model.response.ChangePwdResponseModel;
import com.itsz.sht.common.model.response.ChangeTAndCResponseModel;
import com.itsz.sht.common.model.response.EIPOMasterDetailResponseModel;
import com.itsz.sht.common.model.response.EIPOSubResponseDetailModel;
import com.itsz.sht.common.model.response.EIPOSubResponseModel;
import com.itsz.sht.common.model.response.EMCResponseModel;
import com.itsz.sht.common.model.response.EnquireAccountResponseModel;
import com.itsz.sht.common.model.response.EnquireRTQResponseModel;
import com.itsz.sht.common.model.response.EnquireShortRTQResponseModel;
import com.itsz.sht.common.model.response.EnquiryPositionResponseModel;
import com.itsz.sht.common.model.response.FilterEIPOListResponseModel;
import com.itsz.sht.common.model.response.FilterIPOAmtRcrdResponseModel;
import com.itsz.sht.common.model.response.FilterIPOListResponseModel;
import com.itsz.sht.common.model.response.FilterIPOQtyAmtRcrdResponseModel;
import com.itsz.sht.common.model.response.FilterIPOResponseModel;
import com.itsz.sht.common.model.response.FilterOrderResponseModel;
import com.itsz.sht.common.model.response.FundDepositResponseModel;
import com.itsz.sht.common.model.response.FundTransferRequestModel;
import com.itsz.sht.common.model.response.FundTransferResponseModel;
import com.itsz.sht.common.model.response.IPOAddRequestModel;
import com.itsz.sht.common.model.response.InsertIPOResponseModel;
import com.itsz.sht.common.model.response.ListOrderResponseModel;
import com.itsz.sht.common.model.response.LoginResponseModel;
import com.itsz.sht.common.model.response.MISAccountEnquiryResponseModel;
import com.itsz.sht.common.model.response.MISAccountListResponseModel;
import com.itsz.sht.common.model.response.MarginFinancingListResponseModel;
import com.itsz.sht.common.model.response.ModifyOrderResponseModel;
import com.itsz.sht.common.model.response.MosResponseModel;
import com.itsz.sht.common.model.response.OrderFeeResponseModel;
import com.itsz.sht.common.model.response.PPSEnquiryResponseModel;
import com.itsz.sht.common.model.response.PlaceOrderResponseModel;
import com.itsz.sht.common.model.response.ProfitAndLossEnqiryResponseModel;
import com.itsz.sht.common.model.response.ProfitAndLossUpdateResponseModel;
import com.itsz.sht.common.model.response.QueryCodeResponseModel;
import com.itsz.sht.common.model.response.RemoveIPOResponseModel;
import com.itsz.sht.common.model.response.TradeListResponseModel;
import com.itsz.sht.common.model.response.TransactionProtectionResponseModel;
import com.itsz.sht.common.model.response.VerifyPasswordResponseModel;
import com.itsz.sht.common.model.response.VerifyPlaceOrderResponseModel;
import com.itsz.sht.common.model.response.listorder.OrderDetailTradeResponseModel;
import com.itsz.sht.common.model.response.placeorder.PrePlaceOrderResponseModel;
import com.itsz.sht.common.util.LogUtil;
import com.itsz.sht.service.channels.BusinessTemplate;
import com.itsz.sht.service.common.CommonService;
import com.taifook.mtss.web.eipo.exception.EIPOServiceProviderException;

/**
 * $Id: FacadeImpl.java,v 1.50 2011/05/20 06:42:30 xli Exp $
 * @Project:portal.head
 * @File:FacadeImpl.java
 * @Description:
 * @Author:Cimenon Saint
 * @Date:2007-5-21
 */
public class FacadeImpl implements Facade {
	private CommonService commonService;
	Log info = LoggerFactory.getInstance().getMcsInfo();

	public void setCommonService(CommonService commonService) {
		this.commonService = commonService;
	}
	
	public LoginResponseModel login(LoginRequestModel requestModel) {
	    LogUtil.logDTO("login Request: ", requestModel);
		LoginResponseModel responseModel = null;
		String channelType = requestModel.getChannelType();
		BusinessTemplate bt = (BusinessTemplate)ServiceLocator.getInstance().getService(channelType + "Template");
		responseModel = bt.login(requestModel);
		LogUtil.logDTO("login Response: ", responseModel);
		return responseModel;
	}

	public OrderFeeResponseModel calOrderFee(OrderFeeRequestModel orderFeeModel) {
		LogUtil.logDTO("calOrderFee Request: ", orderFeeModel);
		OrderFeeResponseModel orderFeeResponse = null;
		String channelType = orderFeeModel.getChannelType();
		BusinessTemplate bt = (BusinessTemplate)ServiceLocator.getInstance().getService(channelType + "Template");
		orderFeeResponse = bt.calOrderFee(orderFeeModel);
		LogUtil.logDTO("calOrderFee Response: ", orderFeeResponse);
		return orderFeeResponse;
	}
	
	public PrePlaceOrderResponseModel prePlaceOrder(PrePlaceOrderRequestModel prePlaceRequest) {
		PrePlaceOrderResponseModel prePlaceResponse = null;
		String channelType = prePlaceRequest.getChannelType();
		BusinessTemplate bt = (BusinessTemplate)ServiceLocator.getInstance().getService(channelType + "Template");
		prePlaceResponse = bt.prePlaceOrder(prePlaceRequest);
		return prePlaceResponse;
	}
	
	public PlaceOrderResponseModel placeOrder(PlaceOrderRequestModel orderModel) {
		LogUtil.logDTO("placeOrder Request: ", orderModel);
		PlaceOrderResponseModel responseModel = null;
		String channelType = orderModel.getChannelType();
		BusinessTemplate bt = (BusinessTemplate)ServiceLocator.getInstance().getService(channelType + "Template");
		responseModel = bt.placeOrder(orderModel);
		LogUtil.logDTO("placeOrder Response: ", responseModel);
		return responseModel;
	}
	
	public FilterOrderResponseModel filterOrder(FilterOrderRequestModel listModel) {
		LogUtil.logDTO("filterOrder Request: ", listModel);
		FilterOrderResponseModel filterOrderResponseModel = null;
		String channelType = listModel.getChannelType();
		BusinessTemplate bt = (BusinessTemplate)ServiceLocator.getInstance().getService(channelType + "Template");
		filterOrderResponseModel = bt.filterOrder(listModel);
		LogUtil.logDTO("filterOrder Response: ", filterOrderResponseModel);
		return filterOrderResponseModel;
	}

	public OrderDetailTradeResponseModel orderDetail(OrderDetailRequestModel orderModel) {
		LogUtil.logDTO("orderDetail Request: ", orderModel);
		OrderDetailTradeResponseModel detailWithTradeHisModel = null;
		String channelType = orderModel.getChannelType();
		BusinessTemplate bt = (BusinessTemplate)ServiceLocator.getInstance().getService(channelType + "Template");
		detailWithTradeHisModel = bt.orderDetail(orderModel);
		LogUtil.logDTO("orderDetail Response: ", detailWithTradeHisModel);
		return detailWithTradeHisModel;
	}

	public ModifyOrderResponseModel modifyOrder(ModifyOrderRequestModel orderModel) {
		LogUtil.logDTO("modifyOrder Request: ", orderModel);
		ModifyOrderResponseModel modifyOrderResponseModel = null;
		String channelType = orderModel.getChannelType();
		BusinessTemplate bt = (BusinessTemplate)ServiceLocator.getInstance().getService(channelType + "Template");
		modifyOrderResponseModel = bt.modifyOrder(orderModel);
		LogUtil.logDTO("modifyOrder Response: ", modifyOrderResponseModel);
		return modifyOrderResponseModel;
	}

	public BulkCancelOrderResponseModel bulkCancelOrder(BulkCancelOrderRequestModel orderModel) {
		LogUtil.logDTO("bulkcancelOrder Request: ", orderModel);
		BulkCancelOrderResponseModel bulkCancelOrderResponseModel = null;
		String channelType = orderModel.getChannelType();
		BusinessTemplate bt = (BusinessTemplate)ServiceLocator.getInstance().getService(channelType + "Template");
		bulkCancelOrderResponseModel = bt.bulkCancelOrder(orderModel);
		LogUtil.logDTO("bulkcancelOrder Response: ", bulkCancelOrderResponseModel);
		return bulkCancelOrderResponseModel;
	}
	
	public ProfitAndLossEnqiryResponseModel profitAndLossEnqiry(ProfitAndLossEnqiryRequestModel requestModel){
		LogUtil.logDTO("profitAndLossEnqiry Request: ", requestModel);
		ProfitAndLossEnqiryResponseModel profitAndLossEnqiryResponseModel=null;
		String channelType = requestModel.getChannelType();
		BusinessTemplate bt = (BusinessTemplate)ServiceLocator.getInstance().getService(channelType + "Template");
		profitAndLossEnqiryResponseModel=bt.profitAndLossEnqiry(requestModel);
		LogUtil.logDTO("profitAndLossEnqiry Response: ", profitAndLossEnqiryResponseModel);
		return profitAndLossEnqiryResponseModel;
	}
	
	public ProfitAndLossUpdateResponseModel profitAndLossUpdate(ProfitAndLossUpdateRequestModel requestModel){
		LogUtil.logDTO("profitAndLossUpdate Request: ", requestModel);
		ProfitAndLossUpdateResponseModel profitAndLossUpdateResponseModel=null;
		String channelType = requestModel.getChannelType();
		BusinessTemplate bt = (BusinessTemplate)ServiceLocator.getInstance().getService(channelType + "Template");
		profitAndLossUpdateResponseModel=bt.profitAndLossUpdate(requestModel);
		LogUtil.logDTO("profitAndLossUpdate Response: ", profitAndLossUpdateResponseModel);
		return profitAndLossUpdateResponseModel;
	}

	public CancelOrderResponseModel cancelOrder(CancelOrderRequestModel orderModel) {
		LogUtil.logDTO("cancelOrder Request: ", orderModel);
		CancelOrderResponseModel cancelOrderResponseModel = null;
		String channelType = orderModel.getChannelType();
		BusinessTemplate bt = (BusinessTemplate)ServiceLocator.getInstance().getService(channelType + "Template");
		cancelOrderResponseModel = bt.cancelOrder(orderModel);
		LogUtil.logDTO("cancelOrder Response: ", cancelOrderResponseModel);
		return cancelOrderResponseModel;
	}
	
	public String callSapResponse(SapRequestModel reqModel) {
		return commonService.callSapResponse(reqModel);
	}

	public ListOrderResponseModel listOrders(ListOrderRequestModel listModel){
		LogUtil.logDTO("listOrders Request: ", listModel);
		ListOrderResponseModel listResModel = null;
		String channelType = listModel.getChannelType();
		BusinessTemplate bt = (BusinessTemplate)ServiceLocator.getInstance().getService(channelType + "Template");
		listResModel = bt.listOrder(listModel);
		LogUtil.logDTO("listOrders Response: ", listResModel);
		return listResModel;
	}
	 	
	public VerifyPlaceOrderResponseModel verifyPlaceOrder(VerifyPlaceOrderRequestModel orderModel){
		LogUtil.logDTO("verifyPlaceOrder Request: ", orderModel);
		VerifyPlaceOrderResponseModel placeOrderResModel=null;
		String channelType = orderModel.getChannelType();
		BusinessTemplate bt = (BusinessTemplate)ServiceLocator.getInstance().getService(channelType + "Template");
		placeOrderResModel = bt.verifyPlaceOrder(orderModel);
		LogUtil.logDTO("verifyPlaceOrder Response: ", placeOrderResModel);
		return placeOrderResModel;
	}	
	
	public EnquireAccountResponseModel enquireAccountDetail(EnquireAccountRequestModel accountModel){
		LogUtil.logDTO("enquireAccountDetail Request: ", accountModel);
		EnquireAccountResponseModel resModel=null;
		String channelType = accountModel.getChannelType();
		BusinessTemplate bt = (BusinessTemplate)ServiceLocator.getInstance().getService(channelType + "Template");
		resModel = bt.enquireAccountDetail(accountModel);
		LogUtil.logDTO("enquireAccountDetail Response: ", resModel);
		return resModel;
	}
	
	public EnquireAccountResponseModel enquireAccountDetailForPs(EnquireAccountRequestModel accountModel){
		LogUtil.logDTO("enquireAccountDetailForPs Request: ", accountModel);
		EnquireAccountResponseModel resModel=null;
		String channelType = accountModel.getChannelType();
		BusinessTemplate bt = (BusinessTemplate)ServiceLocator.getInstance().getService(channelType + "Template");
		resModel = bt.enquireAccountDetailForPs(accountModel);
		LogUtil.logDTO("enquireAccountDetail Response: ", resModel);
		return resModel;
	}
	
	public MISAccountListResponseModel enquireAccountList(EnquireAccountListRequestModel accountModel){
		LogUtil.logDTO("enquireAccountList Request: ", accountModel);
		MISAccountListResponseModel resModel=null;
		String channelType = accountModel.getChannelType();
		BusinessTemplate bt = (BusinessTemplate)ServiceLocator.getInstance().getService(channelType + "Template");
		resModel = bt.enquireAccountList(accountModel);
		LogUtil.logDTO("enquireAccountList Response: ", resModel);
		return resModel;		
	}
	
	public MISAccountEnquiryResponseModel enquireMisAccount(EnquireAccountListRequestModel accountModel){
		LogUtil.logDTO("enquireMisAccount Request: ", accountModel);
		MISAccountEnquiryResponseModel resModel=null;
		String channelType = accountModel.getChannelType();
		BusinessTemplate bt = (BusinessTemplate)ServiceLocator.getInstance().getService(channelType + "Template");
		resModel = bt.enquireMisAccount(accountModel);
		LogUtil.logDTO("enquireMisAccount Response: ", resModel);
		return resModel;
	}
	
	public EnquiryPositionResponseModel enquiryPosition(EnquiryPositionRequestModel positionModel){
		LogUtil.logDTO("enquiryPosition Request: ", positionModel);
		EnquiryPositionResponseModel resModel=null;
		String channelType = positionModel.getChannelType();
		BusinessTemplate bt = (BusinessTemplate)ServiceLocator.getInstance().getService(channelType + "Template");
		resModel = bt.enquiryStockPosition(positionModel);
		LogUtil.logDTO("enquiryPosition Response: ", resModel);
		return resModel;
	}
	
	public TransactionProtectionResponseModel changeTransactionProtection(TransactionProtectionRequestModel ptnRequest){
		LogUtil.logDTO("changeTransactionProtection Request: ", ptnRequest);
		TransactionProtectionResponseModel resModel=null;
		String channelType = ptnRequest.getChannelType();
		BusinessTemplate bt = (BusinessTemplate)ServiceLocator.getInstance().getService(channelType + "Template");
		resModel = bt.changeTransactionProtection(ptnRequest);
		LogUtil.logDTO("changeTransactionProtection Response: ", resModel);
		return resModel;
	}
	
	public ChangePwdResponseModel changePassword(ChangePwdRequestModel changePwdModel){
		LogUtil.logDTO("changePassword Request: ", changePwdModel);
		ChangePwdResponseModel resModel=null;
		String channelType = changePwdModel.getChannelType();
		BusinessTemplate bt = (BusinessTemplate)ServiceLocator.getInstance().getService(channelType + "Template");
		resModel = bt.changePassword(changePwdModel);
		LogUtil.logDTO("changePassword Response: ", resModel);
		return resModel;
    }
	
	public EnquireRTQResponseModel enquireRTQInfo(EnquireRTQRequestModel rtqRequest){
		LogUtil.logDTO("enquireRTQInfo Request: ", rtqRequest);
		EnquireRTQResponseModel resModel=null;
		String channelType = rtqRequest.getChannelType();
		BusinessTemplate bt = (BusinessTemplate)ServiceLocator.getInstance().getService(channelType + "Template");
		resModel = bt.enquireRTQInfo(rtqRequest);
		LogUtil.logDTO("enquireRTQInfo Response: ", resModel);
		return resModel;
	}
	
	public EnquireShortRTQResponseModel enquireShortRTQInfo(EnquireShortRTQRequestModel rtqRequest){
		LogUtil.logDTO("enquireShortRTQInfo Request: ", rtqRequest);
		EnquireShortRTQResponseModel resModel=null;
		String channelType = rtqRequest.getChannelType();
		BusinessTemplate bt = (BusinessTemplate)ServiceLocator.getInstance().getService(channelType + "Template");
		resModel = bt.enquireShortRTQInfo(rtqRequest);
		LogUtil.logDTO("enquireShortRTQInfo Response: ", resModel);
		return resModel;		
	}
	
	public AnnounceResponseModel enquireBroadcast(AnnounceRequestModel rtqRequest){
		LogUtil.logDTO("enquireBroadcast Request: ", rtqRequest);
		AnnounceResponseModel resModel=null;
		String channelType = rtqRequest.getChannelType();
		BusinessTemplate bt = (BusinessTemplate)ServiceLocator.getInstance().getService(channelType + "Template");
		resModel = bt.enquireBroadcast(rtqRequest);
		LogUtil.logDTO("enquireBroadcast Response: ", resModel);
		return resModel;
	}
	
	public ChangeTAndCResponseModel changeTAndC(ChangeTAndCRequestModel rtqRequest){
		LogUtil.logDTO("changeTAndC Request: ", rtqRequest);
		ChangeTAndCResponseModel resModel=null;
		String channelType = rtqRequest.getChannelType();
		BusinessTemplate bt = (BusinessTemplate)ServiceLocator.getInstance().getService(channelType + "Template");
		resModel = bt.changeTAndC(rtqRequest);
		LogUtil.logDTO("changeTAndC Response: ", resModel);
		return resModel;		
	}
	
	public MosResponseModel getMos(MosRequestModel rtqRequest){
		LogUtil.logDTO("getMos Request: ", rtqRequest);
		MosResponseModel resModel=null;
		String channelType = rtqRequest.getChannelType();
		BusinessTemplate bt = (BusinessTemplate)ServiceLocator.getInstance().getService(channelType + "Template");
		resModel = bt.getMos(rtqRequest);
		LogUtil.logDTO("getMos Response: ", resModel);
		return resModel;			
	}
	
	public TradeListResponseModel enquireTradeList(TradeListRequestModel requestModel){
		LogUtil.logDTO("enquireTradeList Request: ", requestModel);
		TradeListResponseModel resModel=null;
		String channelType = requestModel.getChannelType();
		BusinessTemplate bt = (BusinessTemplate)ServiceLocator.getInstance().getService(channelType + "Template");
		resModel = bt.enquireTradeList(requestModel);
		LogUtil.logDTO("enquireTradeList Response: ", resModel);
		return resModel;
	}
	
	public BOCTransferResponseModel bocTransfer(BOCTransferRequestModel requestModel){
		LogUtil.logDTO("fundTransfer Request: ", requestModel);
		BOCTransferResponseModel resModel=null;
		String channelType = requestModel.getChannelType();
		BusinessTemplate bt = (BusinessTemplate)ServiceLocator.getInstance().getService(channelType + "Template");
		resModel = bt.bocTransfer(requestModel);
		LogUtil.logDTO("fundTransfer Response: ", resModel);
		return resModel;
	}
	public FundTransferResponseModel fundTransfer(FundTransferRequestModel requestModel){
		LogUtil.logDTO("fundTransfer Request: ", requestModel);
		FundTransferResponseModel resModel=null;
		String channelType = requestModel.getChannelType();
		BusinessTemplate bt = (BusinessTemplate)ServiceLocator.getInstance().getService(channelType + "Template");
		resModel = bt.fundTransfer(requestModel);
		LogUtil.logDTO("fundTransfer Response: ", resModel);
		return resModel;
	}
	
	public CashDetailResponseModel enquireCashDetail(CashDetailRequestModel cashDetailRequestModel){
		LogUtil.logDTO("CashDetail Request: ", cashDetailRequestModel);
		String channelType = cashDetailRequestModel.getChannelType();
		BusinessTemplate bt = (BusinessTemplate)ServiceLocator.getInstance().getService(channelType + "Template");
		CashDetailResponseModel resModel=bt.enquireCashDetail(cashDetailRequestModel);
		LogUtil.logDTO("CashDetail Response: ", resModel);
		return resModel;
	}
	
	public MarginFinancingListResponseModel enquireMarginRations(MarginFinancingListRequestModel requestModel){
		LogUtil.logDTO("enquireMarginRations Request: ", requestModel);
		MarginFinancingListResponseModel resModel=null;
		String channelType = requestModel.getChannelType();
		BusinessTemplate bt = (BusinessTemplate)ServiceLocator.getInstance().getService(channelType + "Template");
		resModel = bt.enquireMarginRations(requestModel);
		LogUtil.logDTO("enquireMarginRations Response: ", resModel);
		return resModel;
	}
	
	public PPSEnquiryResponseModel ppsEnquiry(PPSEnquiryRequestModel requestModel){
		LogUtil.logDTO("ppsEnquiry Request: ", requestModel);
		PPSEnquiryResponseModel resModel=null;
		String channelType = requestModel.getChannelType();
		BusinessTemplate bt = (BusinessTemplate)ServiceLocator.getInstance().getService(channelType + "Template");
		resModel = bt.ppsEnquiry(requestModel);
		LogUtil.logDTO("ppsEnquiry Response: ", resModel);
		return resModel;
	}
	
	public FilterIPOListResponseModel getAllIPORecord(IPORequestModel requestModel){
		LogUtil.logDTO("getAllIPORecord Request: ", requestModel);
		FilterIPOListResponseModel resModel=null;
		String channelType = requestModel.getChannelType();
		BusinessTemplate bt = (BusinessTemplate)ServiceLocator.getInstance().getService(channelType + "Template");
		resModel = bt.getAllIPORecord(requestModel);
		LogUtil.logDTO("getAllIPORecord Response: ", resModel);
		return resModel;
	}
	
	public EIPOSubResponseModel getEIPOSubRecord(EIPORequestModel requestModel) throws EIPOServiceProviderException{
		LogUtil.logDTO("getEIPOSubRecord Request: ", requestModel);
		EIPOSubResponseModel resModel=null;
		String channelType = requestModel.getChannelType();
		BusinessTemplate bt = (BusinessTemplate)ServiceLocator.getInstance().getService(channelType + "Template");
		resModel = bt.getEIPOSubRecord(requestModel);
		LogUtil.logDTO("getEIPOSubRecord Response: ", resModel);
		return resModel;
	}
	public EIPOSubResponseDetailModel getEIPODetailSubRecord(EIPORequestModel requestModel) throws EIPOServiceProviderException{
		LogUtil.logDTO("getEIPOSubRecord Request: ", requestModel);
		EIPOSubResponseDetailModel resModel=null;
		String channelType = requestModel.getChannelType();
		BusinessTemplate bt = (BusinessTemplate)ServiceLocator.getInstance().getService(channelType + "Template");
		resModel = bt.getEIPODetailSubRecord(requestModel);
		LogUtil.logDTO("getEIPOSubRecord Response: ", resModel);
		return resModel;
	}
	
	public InsertIPOResponseModel insertIPORequest(IPOAddRequestModel requestModel){
		LogUtil.logDTO("insertIPORequest Request: ", requestModel);
		InsertIPOResponseModel resModel=null;
		String channelType = requestModel.getChannelType();
		BusinessTemplate bt = (BusinessTemplate)ServiceLocator.getInstance().getService(channelType + "Template");
		resModel = bt.insertIPORequest(requestModel);
		LogUtil.logDTO("insertIPORequest Response: ", resModel);
		return resModel;
	}
	
	public FilterIPOQtyAmtRcrdResponseModel getIPOQtyAmtRcrd(IPOQtyRequestModel requestModel){
		//LogUtil.logDTO("getIPOQtyAmtRcrd Request: ", requestModel);
		FilterIPOQtyAmtRcrdResponseModel resModel=null;
		String channelType = requestModel.getChannelType();
		BusinessTemplate bt = (BusinessTemplate)ServiceLocator.getInstance().getService(channelType + "Template");
		resModel = bt.getIPOQtyAmtRcrd(requestModel);
		//LogUtil.logDTO("getIPOQtyAmtRcrd Response: ", resModel);
		return resModel;
	}
	
	public FilterIPOAmtRcrdResponseModel getIPOAmtRcrd(IPOQtyRequestModel requestModel){
		//LogUtil.logDTO("getIPOAmtRcrd Request: ", requestModel);
		FilterIPOAmtRcrdResponseModel resModel=null;
		String channelType = requestModel.getChannelType();
		BusinessTemplate bt = (BusinessTemplate)ServiceLocator.getInstance().getService(channelType + "Template");
		resModel = bt.getIPOAmtRcrd(requestModel);
		//LogUtil.logDTO("getIPOAmtRcrd Response: ", resModel);
		return resModel;
	}
	
	public FilterIPOResponseModel getIPORecord(IPORequestModel requestModel){
		LogUtil.logDTO("getIPORecord Request: ", requestModel);
		FilterIPOResponseModel resModel=null;
		String channelType = requestModel.getChannelType();
		BusinessTemplate bt = (BusinessTemplate)ServiceLocator.getInstance().getService(channelType + "Template");
		resModel = bt.getIPORecord(requestModel);
		LogUtil.logDTO("getIPORecord Response: ", resModel);
		return resModel;
	}
	
	public QueryCodeResponseModel getIPOQueryCode(IPORequestModel requestModel){
		LogUtil.logDTO("ipoQueryCancel Request: ", requestModel);
		QueryCodeResponseModel resModel=null;
		String channelType = requestModel.getChannelType();
		BusinessTemplate bt = (BusinessTemplate)ServiceLocator.getInstance().getService(channelType + "Template");
		resModel = bt.getIPOQueryCode(requestModel);
		LogUtil.logDTO("ipoQueryCancel Response: ", resModel);
		return resModel;
	}
	
	public RemoveIPOResponseModel removeIPORequest(RemoveIPORequestModel requestModel){
		LogUtil.logDTO("removeIPORequest Request: ", requestModel);
		RemoveIPOResponseModel resModel=null;
		String channelType = requestModel.getChannelType();
		BusinessTemplate bt = (BusinessTemplate)ServiceLocator.getInstance().getService(channelType + "Template");
		resModel = bt.removeIPORequest(requestModel);
		LogUtil.logDTO("removeIPORequest Response: ", resModel);
		return resModel;
	}
	
	public VerifyPasswordResponseModel verifyPassword(VerifyPasswordRequestModel requestModel){
		LogUtil.logDTO("verifyPassword Request: ", requestModel);
		VerifyPasswordResponseModel resModel=null;
		String channelType = requestModel.getChannelType();
		BusinessTemplate bt = (BusinessTemplate)ServiceLocator.getInstance().getService(channelType + "Template");
		resModel = bt.verifyPassword(requestModel);
		LogUtil.logDTO("verifyPassword Response: ", resModel);
		return resModel;
	}
	
	//3.3.1.2.3	查询已选购服务
	public ListSelectServiceResponseModel listSelectService(ListSelectServiceRequestModel listSelectServiceRequestModel){
		LogUtil.logDTO("listSelectService Request: ", listSelectServiceRequestModel);
		ListSelectServiceResponseModel resModel=null;
		String channelType = listSelectServiceRequestModel.getChannelType();
		BusinessTemplate bt = (BusinessTemplate)ServiceLocator.getInstance().getService(channelType + "Template");
		resModel = bt.listSelectService(listSelectServiceRequestModel);
		LogUtil.logDTO("listSelectService Response: ", resModel);
		return resModel;
	}
	
	public RTQProductResponseModel getRTQProductList(RTQProductRequestModel rTQProductRequestModel){
		LogUtil.logDTO("getRTQProductList Request: ", rTQProductRequestModel);
		RTQProductResponseModel resModel=null;
		String channelType = rTQProductRequestModel.getChannelType();
		BusinessTemplate bt = (BusinessTemplate)ServiceLocator.getInstance().getService(channelType + "Template");
		resModel = bt.getRTQProductList(rTQProductRequestModel);
		LogUtil.logDTO("getRTQProductList Response: ", resModel);
		return resModel;
	}
	
	//rtq reserveService
    public ReserveServiceResponseModel reserveService(ReserveServiceRequestModel reserveServiceRequestModel){
//		LogUtil.logDTO("reserveService Request: ", reserveServiceRequestModel);
		ReserveServiceResponseModel resModel=null;
		String channelType = reserveServiceRequestModel.getChannelType();
		BusinessTemplate bt = (BusinessTemplate)ServiceLocator.getInstance().getService(channelType + "Template");
		resModel = bt.reserveService(reserveServiceRequestModel);
//		LogUtil.logDTO("reserveService Response: ", resModel);
		return resModel;
    }
    
    //更改自动续期
    public UserProductResponseModel updateUserProductStatus(UpdateUserProductRequestModel updateUserProductRequestModel){
		LogUtil.logDTO("reserveService Request: ", updateUserProductRequestModel);
		UserProductResponseModel resModel=null;
		String channelType = updateUserProductRequestModel.getChannelType();
		BusinessTemplate bt = (BusinessTemplate)ServiceLocator.getInstance().getService(channelType + "Template");
		resModel = bt.updateUserProductStatus(updateUserProductRequestModel);
		LogUtil.logDTO("reserveService Response: ", resModel);
		return resModel;
    }    
    
    //取消预订
	public UserProductResponseModel cancelReservedUserProduct(UpdateUserProductRequestModel updateUserProductRequestModel){
		LogUtil.logDTO("reserveService Request: ", updateUserProductRequestModel);
		UserProductResponseModel resModel=null;
		String channelType = updateUserProductRequestModel.getChannelType();
		BusinessTemplate bt = (BusinessTemplate)ServiceLocator.getInstance().getService(channelType + "Template");
		resModel = bt.cancelReservedUserProduct(updateUserProductRequestModel);
		LogUtil.logDTO("reserveService Response: ", resModel);
		return resModel;
	}
    
	//rtq purchaseService
    public PurchaseServiceResponseModel purchaseService(PurchaseServiceRequestModel purchaseServiceRequestModel){
		LogUtil.logDTO("purchaseService Request: ", purchaseServiceRequestModel);
		PurchaseServiceResponseModel resModel=null;
		String channelType = purchaseServiceRequestModel.getChannelType();
		BusinessTemplate bt = (BusinessTemplate)ServiceLocator.getInstance().getService(channelType + "Template");
		resModel = bt.purchaseService(purchaseServiceRequestModel);
		LogUtil.logDTO("purchaseService Response: ", resModel);
		return resModel;
    }

	public AccessRTQResponseModel accessRTQ(AccessRTQRequestModel accessRTQRequestModel){
		LogUtil.logDTO("accessRTQ Request: ", accessRTQRequestModel);
		AccessRTQResponseModel resModel=null;
		String channelType = accessRTQRequestModel.getChannelType();
		BusinessTemplate bt = (BusinessTemplate)ServiceLocator.getInstance().getService(channelType + "Template");
		resModel = bt.accessRTQ(accessRTQRequestModel);
		LogUtil.logDTO("accessRTQ Response: ", resModel);
		return resModel;
    }
	
	public AccessSHKResponseModel accessSHK(AccessSHKRequestModel accessSHKRequestModel){
		LogUtil.logDTO("accessSHK Request: ", accessSHKRequestModel);
		AccessSHKResponseModel resModel=null;
		String channelType = accessSHKRequestModel.getChannelType();
		BusinessTemplate bt = (BusinessTemplate)ServiceLocator.getInstance().getService(channelType + "Template");
		resModel = bt.accessSHK(accessSHKRequestModel);
		LogUtil.logDTO("accessSHK Response: ", resModel);
		return resModel;
	}
	public PurchasedProductResponseModel havePurchasedProduct(PurchasedProductRequestModel purchasedProductRequestModel){
		LogUtil.logDTO("havePurchasedProduct Request: ", purchasedProductRequestModel);
		PurchasedProductResponseModel resModel=null;
		String channelType = purchasedProductRequestModel.getChannelType();
		BusinessTemplate bt = (BusinessTemplate)ServiceLocator.getInstance().getService(channelType + "Template");
		resModel = bt.havePurchasedProduct(purchasedProductRequestModel);
		LogUtil.logDTO("havePurchasedProduct Response: ", resModel);
		return resModel;
	}
	
	public UserNotificationEmailResponseModel getUserNotificationEmail(UserNotificationEmailRequestModel userNotificationEmailRequestModel){
		LogUtil.logDTO("getUserNotificationEmail Request: ", userNotificationEmailRequestModel);
		UserNotificationEmailResponseModel resModel=null;
		String channelType = userNotificationEmailRequestModel.getChannelType();
		BusinessTemplate bt = (BusinessTemplate)ServiceLocator.getInstance().getService(channelType + "Template");
		resModel = bt.getUserNotificationEmail(userNotificationEmailRequestModel);
		LogUtil.logDTO("getUserNotificationEmail Response: ", resModel);
		return resModel;
	}
	
	public UserNotificationEmailResponseModel updateUserNotificationEmail(UserNotificationEmailRequestModel userNotificationEmailRequestModel){
		LogUtil.logDTO("updateUserNotificationEmail Request: ", userNotificationEmailRequestModel);
		UserNotificationEmailResponseModel resModel=null;
		String channelType = userNotificationEmailRequestModel.getChannelType();
		BusinessTemplate bt = (BusinessTemplate)ServiceLocator.getInstance().getService(channelType + "Template");
		resModel = bt.updateUserNotificationEmail(userNotificationEmailRequestModel);
		LogUtil.logDTO("updateUserNotificationEmail Response: ", resModel);
		return resModel;
	}

	public UserProfileResponseModel findClientUserProfile(UserProfileRequestModel userProfileRequestModel){
		LogUtil.logDTO("findClientUserProfile Request: ", userProfileRequestModel);
		UserProfileResponseModel resModel=null;
		String channelType = userProfileRequestModel.getChannelType();
		BusinessTemplate bt = (BusinessTemplate)ServiceLocator.getInstance().getService(channelType + "Template");
		resModel = bt.findClientUserProfile(userProfileRequestModel);
		LogUtil.logDTO("findClientUserProfile Response: ", resModel);
		return resModel;		
	}
	
	public FundDepositResponseModel fundDeposit(FundDepositRequestModel fundDepositRequestModel){
		LogUtil.logDTO("fundDeposit Request: ", fundDepositRequestModel);
		FundDepositResponseModel resModel=null;
		String channelType = fundDepositRequestModel.getChannelType();
		BusinessTemplate bt = (BusinessTemplate)ServiceLocator.getInstance().getService(channelType + "Template");
		resModel = bt.fundDeposit(fundDepositRequestModel);
		LogUtil.logDTO("fundDeposit Response: ", resModel);
		return resModel;	
	}
	
	public FundDepositResponseModel transFundDeposit(FundDepositRequestModel fundDepositRequestModel){
		LogUtil.logDTO("fundDeposit Request: ", fundDepositRequestModel);
		FundDepositResponseModel resModel=null;
		String channelType = fundDepositRequestModel.getChannelType();
		BusinessTemplate bt = (BusinessTemplate)ServiceLocator.getInstance().getService(channelType + "Template");
		resModel = bt.transFundDeposit(fundDepositRequestModel);
		LogUtil.logDTO("fundDeposit Response: ", resModel);
		return resModel;
	}
	
	public EMCResponseModel getEMCMsgCounts(EMCRequestModel emcRequestModel){
		LogUtil.logDTO("getEMCMsgCounts Request: ", emcRequestModel);
		EMCResponseModel resModel=null;
		String channelType = emcRequestModel.getChannelType();
		BusinessTemplate bt = (BusinessTemplate)ServiceLocator.getInstance().getService(channelType + "Template");
		resModel = bt.getEMCMsgCounts(emcRequestModel);
		LogUtil.logDTO("getEMCMsgCounts Response: ", resModel);
		return resModel;		
	}
	
	public EMCResponseModel getEMCMsgURL(EMCRequestModel emcRequestModel){
		LogUtil.logDTO("getEMCMsgCounts Request: ", emcRequestModel);
		EMCResponseModel resModel=null;
		String channelType = emcRequestModel.getChannelType();
		BusinessTemplate bt = (BusinessTemplate)ServiceLocator.getInstance().getService(channelType + "Template");
		resModel = bt.getEMCMsgURL(emcRequestModel);
		LogUtil.logDTO("getEMCMsgCounts Response: ", resModel);
		return resModel;		
	}
	
	//add by Arthur Chen on 20110421 for eipo
	public FilterEIPOListResponseModel getAllEIPORecord(EIPORequestModel requestModel){
		LogUtil.logDTO("getAllEIPORecord Request: ", requestModel);
		FilterEIPOListResponseModel resModel=null;
		String channelType = requestModel.getChannelType();
		BusinessTemplate bt = (BusinessTemplate)ServiceLocator.getInstance().getService(channelType + "Template");
		resModel = bt.getAllEIPORecord(requestModel);
		LogUtil.logDTO("getAllEIPORecord Response: ", resModel);
		return resModel;
	}
	
	public EIPOResponse getEIPOSubscriptionSubmit(EIPOSubscriptionSubmitRequestModel eipoRequestModel) {
		LogUtil.logDTO("getEIPOSubscriptionSubmit Request: ", eipoRequestModel);
		EIPOResponse resModel=null;
		String channelType = eipoRequestModel.getChannelType();
		BusinessTemplate bt = (BusinessTemplate)ServiceLocator.getInstance().getService(channelType + "Template");
		resModel = bt.getEIPOSubscriptionSubmit(eipoRequestModel);
//		LogUtil.logDTO("getEIPOSubscriptionSubmit Response: ", resModel);
		return resModel;
	}

	// ADD by Arthur xli on 20110421 for eipo
	public EIPOResponse getEIPOSubscriptionDetail(EIPOSubscriptionDetailRequestModel eipoRequestModel) {
		LogUtil.logDTO("getEIPOSubscriptionDetail Request: ", eipoRequestModel);
		EIPOResponse resModel=null;
		String channelType = eipoRequestModel.getChannelType();
		BusinessTemplate bt = (BusinessTemplate)ServiceLocator.getInstance().getService(channelType + "Template");
		resModel = bt.getEIPOSubscriptionDetail(eipoRequestModel);
		LogUtil.logDTO("getEIPOSubscriptionDetail Response: ", resModel);
		return resModel;
	}

	public EIPOResponse getEIPOSubscriptionEnquiry(EIPOSubscriptionEnquiryRequestModel eipoRequestModel) {
		LogUtil.logDTO("getEIPOSubscriptionEnquiry Request: ", eipoRequestModel);
		EIPOResponse resModel=null;
		String channelType = eipoRequestModel.getChannelType();
		BusinessTemplate bt = (BusinessTemplate)ServiceLocator.getInstance().getService(channelType + "Template");
		resModel = bt.getEIPOSubscriptionEnquiry(eipoRequestModel);
		LogUtil.logDTO("getEIPOSubscriptionEnquiry Response: ", resModel);
		return resModel;
	}

	public CancelEIPOSubscriptionResponseModel getCancelEIPOSubscription(CancelEIPOSubscriptionRequestModel eipoRequestModel) {
		LogUtil.logDTO("getCancelEIPOSubscription Request: ", eipoRequestModel);
		CancelEIPOSubscriptionResponseModel resModel=null;
		String channelType = eipoRequestModel.getChannelType();
		BusinessTemplate bt = (BusinessTemplate)ServiceLocator.getInstance().getService(channelType + "Template");
		resModel = bt.getCancelEIPOSubscription(eipoRequestModel);
		LogUtil.logDTO("getCancelEIPOSubscription Response: ", resModel);
		return resModel;
	}

	public EIPOResponse getCancelEIPOSubscriptionSubmit(CancelEIPOSubscriptionSubmitRequestModel eipoRequestModel) {
		LogUtil.logDTO("getCancelEIPOSubscriptionSubmit Request: ", eipoRequestModel);
		EIPOResponse resModel=null;
		String channelType = eipoRequestModel.getChannelType();
		BusinessTemplate bt = (BusinessTemplate)ServiceLocator.getInstance().getService(channelType + "Template");
		resModel = bt.getCancelEIPOSubscriptionSubmit(eipoRequestModel);
		LogUtil.logDTO("getCancelEIPOSubscriptionSubmit Response: ", resModel);
		return resModel;
	}

	public EIPOMasterDetailResponseModel getEIPOMasterDetail(EIPOMasterDetailRequestModel eipoRequestModel) {
		LogUtil.logDTO("getEIPOMasterDetail Request: ", eipoRequestModel);
		EIPOMasterDetailResponseModel resModel=null;
		String channelType = eipoRequestModel.getChannelType();
		BusinessTemplate bt = (BusinessTemplate)ServiceLocator.getInstance().getService(channelType + "Template");
		resModel = bt.getEIPOMasterDetail(eipoRequestModel);
		LogUtil.logDTO("getEIPOMasterDetail Response: ", resModel);
		return resModel;
	}

}
