package com.itsz.sht.service.mcs;

import java.math.BigDecimal;
import java.util.Collection;

import com.itsz.sht.common.model.request.BOCTransferRequestModel;
import com.itsz.sht.common.model.request.BulkCancelOrderRequestModel;
import com.itsz.sht.common.model.request.CancelOrderRequestModel;
import com.itsz.sht.common.model.request.CashDetailRequestModel;
import com.itsz.sht.common.model.request.ChangePwdRequestModel;
import com.itsz.sht.common.model.request.ChangeTAndCRequestModel;
import com.itsz.sht.common.model.request.EnquireAccountListRequestModel;
import com.itsz.sht.common.model.request.EnquireAccountRequestModel;
import com.itsz.sht.common.model.request.EnquiryPositionRequestModel;
import com.itsz.sht.common.model.request.FilterOrderRequestModel;
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
import com.itsz.sht.common.model.request.TradeListRequestModel;
import com.itsz.sht.common.model.request.TransactionProtectionRequestModel;
import com.itsz.sht.common.model.request.VerifyPasswordRequestModel;
import com.itsz.sht.common.model.response.BOCTransferResponseModel;
import com.itsz.sht.common.model.response.BulkCancelOrderResponseModel;
import com.itsz.sht.common.model.response.CancelOrderResponseModel;
import com.itsz.sht.common.model.response.CashDetailResponseModel;
import com.itsz.sht.common.model.response.ChangePwdResponseModel;
import com.itsz.sht.common.model.response.ChangeTAndCResponseModel;
import com.itsz.sht.common.model.response.FilterOrderResponseModel;
import com.itsz.sht.common.model.response.FundTransferRequestModel;
import com.itsz.sht.common.model.response.FundTransferResponseModel;
import com.itsz.sht.common.model.response.ListOrderResponseModel;
import com.itsz.sht.common.model.response.LoginResponseModel;
import com.itsz.sht.common.model.response.MarginFinancingListResponseModel;
import com.itsz.sht.common.model.response.ModifyOrderResponseModel;
import com.itsz.sht.common.model.response.MosResponseModel;
import com.itsz.sht.common.model.response.OrderFeeResponseModel;
import com.itsz.sht.common.model.response.PPSEnquiryResponseModel;
import com.itsz.sht.common.model.response.PlaceOrderResponseModel;
import com.itsz.sht.common.model.response.ProfitAndLossEnqiryResponseModel;
import com.itsz.sht.common.model.response.ProfitAndLossUpdateResponseModel;
import com.itsz.sht.common.model.response.TradeListResponseModel;
import com.itsz.sht.common.model.response.VerifyPasswordResponseModel;
import com.itsz.sht.common.model.response.listorder.OrderDetailTradeResponseModel;
import com.itsz.sht.common.model.response.placeorder.PrePlaceOrderResponseModel;
import com.itsz.sht.exception.ITSZException;
import com.taifook.mcs.core.beans.msg.MISAccountDetailResponse;
import com.taifook.mcs.core.beans.msg.MISAccountEnquiryResponse;
import com.taifook.mcs.core.beans.msg.MISAccountListResponse;
import com.taifook.mcs.core.beans.msg.MISAccountSummaryResponse;
import com.taifook.mcs.core.beans.msg.MTSSShareHoldingResponse;
import com.taifook.mcs.core.beans.msg.OrderInfo;
import com.taifook.mcs.core.beans.msg.OrderListRequest;
import com.taifook.mcs.core.beans.msg.TransactionProtectionResponse;


public interface McsService {
	
	public LoginResponseModel callPwdLogin(LoginRequestModel loginRequestModel)throws Exception;
	
	public OrderFeeResponseModel callOrderFee(OrderFeeRequestModel orderFeeRequstModel , BigDecimal mos) throws ITSZException;
	
	public BigDecimal getInsufficientAmount(BigDecimal netAmount , BigDecimal mos);
	
	public PrePlaceOrderResponseModel callPrePlaceOrder(PrePlaceOrderRequestModel prePlaceRequest) throws ITSZException;
	
	public PlaceOrderResponseModel callPlaceOrder(PlaceOrderRequestModel placeOrderRequstModel) throws ITSZException;

	public FilterOrderResponseModel callFilterOrder(FilterOrderRequestModel requestModel) throws ITSZException;
	
	public ListOrderResponseModel callListOrder(ListOrderRequestModel listModel) throws ITSZException;
	public ProfitAndLossEnqiryResponseModel callProfitAndLossEnqiry(ProfitAndLossEnqiryRequestModel requestModel)throws ITSZException;
	public ProfitAndLossUpdateResponseModel callProfitAndLossUpdate(ProfitAndLossUpdateRequestModel requestModel)throws ITSZException;
	public MosResponseModel callMos(MosRequestModel mosModel) throws ITSZException;

	public OrderDetailTradeResponseModel callOrderDetail(OrderDetailRequestModel orderDetail) throws ITSZException;

	public Collection callTradeHistory(OrderDetailRequestModel orderDetailRequstModel,OrderDetailTradeResponseModel orderDetailTradeResponseModel)throws ITSZException ;
	public CashDetailResponseModel callCashDetail(CashDetailRequestModel cashDetailRequestModel)throws ITSZException ;
	
	public ModifyOrderResponseModel callModifyOrder(ModifyOrderRequestModel modifyOrderRequestModel,OrderInfo orderInfo) throws ITSZException;
	public BulkCancelOrderResponseModel callBulkCancelOrder(BulkCancelOrderRequestModel bulkCancelOrderRequestModel) throws ITSZException;
	public CancelOrderResponseModel callCancelOrder(CancelOrderRequestModel reqModel) throws ITSZException;
	public OrderInfo getModifyOrderDetail(OrderListRequest requestModel) throws ITSZException;
		
	public MISAccountSummaryResponse callEnquireAccount(EnquireAccountRequestModel accountModel) throws ITSZException;
	public MISAccountDetailResponse callEnquireAccountDetail(EnquireAccountRequestModel accountModel) throws ITSZException;
	public MISAccountListResponse callEnquireAccountList(EnquireAccountListRequestModel accountModel) throws ITSZException;
	public MISAccountEnquiryResponse callEnquireMisAccount(EnquireAccountListRequestModel accountModel) throws ITSZException;;
    public MTSSShareHoldingResponse callEnquiryPosition(EnquiryPositionRequestModel positionModel) throws ITSZException;
	public TransactionProtectionResponse callSetTransPwdPtd(TransactionProtectionRequestModel requestModel) throws ITSZException;
	public ChangePwdResponseModel callChangePassword(ChangePwdRequestModel requestModel) throws ITSZException;
	public VerifyPasswordResponseModel callVerifyPassword(VerifyPasswordRequestModel requestModel) throws ITSZException;
	public ChangeTAndCResponseModel callChangeTAndC(ChangeTAndCRequestModel requestModel) throws ITSZException;
	public TradeListResponseModel callTradeList(TradeListRequestModel requestModel) throws ITSZException;
	public BOCTransferResponseModel bocTransfer(BOCTransferRequestModel requestModel) throws ITSZException;
	public PPSEnquiryResponseModel getPPSRecords(PPSEnquiryRequestModel requestModel) throws ITSZException;
	public FundTransferResponseModel fundTransfer(FundTransferRequestModel requestModel) throws ITSZException;
	public FundTransferResponseModel withDraw(FundTransferRequestModel requestModel) throws ITSZException;
	public MarginFinancingListResponseModel callEnquireMarginRations(MarginFinancingListRequestModel requestModel)throws ITSZException;
}
