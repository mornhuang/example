package com.itsz.sht.service.channels;


import com.itsz.eipo.webservice.EIPOResponse;
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
import com.taifook.mtss.web.eipo.exception.EIPOServiceProviderException;

/**
 * $Id: BusinessTemplate.java,v 1.36 2011/05/10 01:44:49 lpchen Exp $
 * @Project:portal.head
 * @File:BusinessTemplate.java
 * @Description:
 * @Author:Cimenon Saint
 * @Date:2007-5-25
 */
public interface BusinessTemplate {
	public LoginResponseModel login(
			LoginRequestModel loginModel );
	
	public OrderFeeResponseModel calOrderFee(
			OrderFeeRequestModel orderFeeRequest);
	
	public PrePlaceOrderResponseModel prePlaceOrder(
			PrePlaceOrderRequestModel prePlaceRequest);
	
	public PlaceOrderResponseModel placeOrder(
			PlaceOrderRequestModel requestModel);
	
	public FilterOrderResponseModel filterOrder(
			FilterOrderRequestModel filterModel );
	
	public ListOrderResponseModel listOrder(
			ListOrderRequestModel listModel );
	
	public ProfitAndLossEnqiryResponseModel profitAndLossEnqiry(ProfitAndLossEnqiryRequestModel requestModel);
	public ProfitAndLossUpdateResponseModel profitAndLossUpdate(ProfitAndLossUpdateRequestModel requestModel);
	
	public ModifyOrderResponseModel modifyOrder(
			ModifyOrderRequestModel requestModel );
	
	public BulkCancelOrderResponseModel bulkCancelOrder(
			BulkCancelOrderRequestModel requestModel );
	
	public CancelOrderResponseModel cancelOrder(
			CancelOrderRequestModel requestModel);
	
	public OrderDetailTradeResponseModel orderDetail(
			OrderDetailRequestModel orderDetailRequestModel );

	public CashDetailResponseModel enquireCashDetail(CashDetailRequestModel cashDetailRequestModel);
	public EnquireRTQResponseModel enquireRTQInfo(EnquireRTQRequestModel rtqRequest);
	public EnquireShortRTQResponseModel enquireShortRTQInfo(EnquireShortRTQRequestModel rtqRequest);
	public VerifyPlaceOrderResponseModel verifyPlaceOrder(VerifyPlaceOrderRequestModel orderModel);
	public TransactionProtectionResponseModel changeTransactionProtection(TransactionProtectionRequestModel ptnRequest);
	public EnquireAccountResponseModel enquireAccountDetail(EnquireAccountRequestModel accountModel);
	public EnquireAccountResponseModel enquireAccountDetailForPs(EnquireAccountRequestModel accountModel);
	public MISAccountListResponseModel enquireAccountList(EnquireAccountListRequestModel accountModel);
	public MISAccountEnquiryResponseModel enquireMisAccount(EnquireAccountListRequestModel accountModel);
	public EnquiryPositionResponseModel enquiryStockPosition(EnquiryPositionRequestModel positionModel);
	public ChangePwdResponseModel changePassword(ChangePwdRequestModel changePwdModel);
	public AnnounceResponseModel enquireBroadcast(AnnounceRequestModel requestModel);
	public ChangeTAndCResponseModel changeTAndC(ChangeTAndCRequestModel requestModel);
	public MosResponseModel getMos(MosRequestModel mosModel);
	public TradeListResponseModel enquireTradeList(TradeListRequestModel requestModel);
	public BOCTransferResponseModel bocTransfer(BOCTransferRequestModel requestModel);
	public FundTransferResponseModel fundTransfer(FundTransferRequestModel requestModel);
	public MarginFinancingListResponseModel enquireMarginRations(MarginFinancingListRequestModel requestModel);
	public PPSEnquiryResponseModel ppsEnquiry(PPSEnquiryRequestModel requestModel);
	
	public FilterIPOListResponseModel getAllIPORecord(IPORequestModel requestModel);
	public QueryCodeResponseModel getIPOQueryCode(IPORequestModel requestModel);
	public InsertIPOResponseModel insertIPORequest (IPOAddRequestModel requestModel);
	public FilterIPOQtyAmtRcrdResponseModel getIPOQtyAmtRcrd(IPOQtyRequestModel requestModel);
	public FilterIPOAmtRcrdResponseModel getIPOAmtRcrd(IPOQtyRequestModel requestModel);
	public FilterIPOResponseModel getIPORecord(IPORequestModel requestModel);
	public RemoveIPOResponseModel removeIPORequest(RemoveIPORequestModel requestModel);
	public VerifyPasswordResponseModel verifyPassword(VerifyPasswordRequestModel requestModel);
	
	//3.3.1.2.3	查询已选购服务
	public ListSelectServiceResponseModel listSelectService(ListSelectServiceRequestModel listSelectServiceRequestModel);
	public RTQProductResponseModel getRTQProductList(RTQProductRequestModel rTQProductRequestModel);
	//rtq reserveService
    public ReserveServiceResponseModel reserveService(ReserveServiceRequestModel reserveServiceRequestModel);    
    //更改自动续期
    public UserProductResponseModel updateUserProductStatus(UpdateUserProductRequestModel updateUserProductRequestModel);    
    //取消预订
	public UserProductResponseModel cancelReservedUserProduct(UpdateUserProductRequestModel updateUserProductRequestModel);
	//rtq purchaseService
    public PurchaseServiceResponseModel purchaseService(PurchaseServiceRequestModel purchaseServiceRequestModel);
	public AccessRTQResponseModel accessRTQ(AccessRTQRequestModel accessRTQRequestModel);	
	public AccessSHKResponseModel accessSHK(AccessSHKRequestModel accessSHKRequestModel);
	public PurchasedProductResponseModel havePurchasedProduct(PurchasedProductRequestModel purchasedProductRequestModel);
	public UserNotificationEmailResponseModel getUserNotificationEmail(UserNotificationEmailRequestModel userNotificationEmailRequestModel);
	public UserNotificationEmailResponseModel updateUserNotificationEmail(UserNotificationEmailRequestModel userNotificationEmailRequestModel);
	public UserProfileResponseModel findClientUserProfile(UserProfileRequestModel userProfileRequestModel);
	public FundDepositResponseModel fundDeposit(FundDepositRequestModel fundDepositRequestModel);
	public FundDepositResponseModel transFundDeposit(FundDepositRequestModel fundDepositRequestModel);
	public EMCResponseModel getEMCMsgCounts(EMCRequestModel emcRequestModel);
	public EMCResponseModel getEMCMsgURL(EMCRequestModel emcRequestModel);
	//add by Arthur Chen on 20110421 for eipo
	public FilterEIPOListResponseModel getAllEIPORecord(EIPORequestModel requestModel);
	public EIPOSubResponseModel getEIPOSubRecord(EIPORequestModel requestModel) throws EIPOServiceProviderException;
	public EIPOSubResponseDetailModel getEIPODetailSubRecord(EIPORequestModel requestModel) throws EIPOServiceProviderException;
	public EIPOResponse getEIPOSubscriptionSubmit(EIPOSubscriptionSubmitRequestModel eipoRequestModel);

	//add by Arthur xli on 20110421 for eipo
	public EIPOResponse getEIPOSubscriptionDetail(EIPOSubscriptionDetailRequestModel eipoRequestModel);
	public EIPOResponse getEIPOSubscriptionEnquiry(EIPOSubscriptionEnquiryRequestModel eipoRequestModel);
	public CancelEIPOSubscriptionResponseModel getCancelEIPOSubscription(CancelEIPOSubscriptionRequestModel eipoRequestModel);
	public EIPOResponse getCancelEIPOSubscriptionSubmit(CancelEIPOSubscriptionSubmitRequestModel eipoRequestModel);
	public EIPOMasterDetailResponseModel getEIPOMasterDetail(EIPOMasterDetailRequestModel eipoRequestModel);
}

