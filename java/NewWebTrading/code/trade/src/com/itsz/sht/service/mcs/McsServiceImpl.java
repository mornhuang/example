package com.itsz.sht.service.mcs;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Locale;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;

import sun.misc.resources.Messages;

import com.itsz.sht.common.Consts;
import com.itsz.sht.common.LanguageUtil;
import com.itsz.sht.common.LoggerFactory;
import com.itsz.sht.common.OrderStateConvertUtil;
import com.itsz.sht.common.PaginationUtil;
import com.itsz.sht.common.PortalUtil;
import com.itsz.sht.common.model.common.ModelUtil;
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
import com.itsz.sht.common.model.response.listorder.OrderInfoModel;
import com.itsz.sht.common.model.response.listorder.TradeInfoModel;
import com.itsz.sht.common.model.response.placeorder.PrePlaceOrderResponseModel;
import com.itsz.sht.exception.ITSZException;
import com.taifook.mcs.core.beans.msg.BOCTransferRequest;
import com.taifook.mcs.core.beans.msg.BOCTransferResponse;
import com.taifook.mcs.core.beans.msg.BulkCancelOrderRequest;
import com.taifook.mcs.core.beans.msg.BulkCancelOrderResponse;
import com.taifook.mcs.core.beans.msg.CalMOSRequest;
import com.taifook.mcs.core.beans.msg.CalMOSResponse;
import com.taifook.mcs.core.beans.msg.CalOrderFeeRequest;
import com.taifook.mcs.core.beans.msg.CalOrderFeeResponse;
import com.taifook.mcs.core.beans.msg.CancelOrderRequest;
import com.taifook.mcs.core.beans.msg.CancelOrderResponse;
import com.taifook.mcs.core.beans.msg.ChangePasswordRequest;
import com.taifook.mcs.core.beans.msg.ChangePasswordResponse;
import com.taifook.mcs.core.beans.msg.Exception01;
import com.taifook.mcs.core.beans.msg.FundTransferRequest;
import com.taifook.mcs.core.beans.msg.FundTransferResponse;
import com.taifook.mcs.core.beans.msg.InputOrderRequest;
import com.taifook.mcs.core.beans.msg.InputOrderResponse;
import com.taifook.mcs.core.beans.msg.LoginRequest;
import com.taifook.mcs.core.beans.msg.LoginResponse;
import com.taifook.mcs.core.beans.msg.MCSMessage;
import com.taifook.mcs.core.beans.msg.MISAccountCashHoldingRequest;
import com.taifook.mcs.core.beans.msg.MISAccountCashHoldingResponse;
import com.taifook.mcs.core.beans.msg.MISAccountDetailRequest;
import com.taifook.mcs.core.beans.msg.MISAccountDetailResponse;
import com.taifook.mcs.core.beans.msg.MISAccountEnquiryRequest;
import com.taifook.mcs.core.beans.msg.MISAccountEnquiryResponse;
import com.taifook.mcs.core.beans.msg.MISAccountListRequest;
import com.taifook.mcs.core.beans.msg.MISAccountListResponse;
import com.taifook.mcs.core.beans.msg.MISAccountSummaryRequest;
import com.taifook.mcs.core.beans.msg.MISAccountSummaryResponse;
import com.taifook.mcs.core.beans.msg.MTSSShareHoldingRequest;
import com.taifook.mcs.core.beans.msg.MTSSShareHoldingResponse;
import com.taifook.mcs.core.beans.msg.MarginFinancingListRequest;
import com.taifook.mcs.core.beans.msg.MarginFinancingListResponse;
import com.taifook.mcs.core.beans.msg.ModifyOrderRequest;
import com.taifook.mcs.core.beans.msg.ModifyOrderResponse;
import com.taifook.mcs.core.beans.msg.OrderFilteringRequest;
import com.taifook.mcs.core.beans.msg.OrderFilteringResponse;
import com.taifook.mcs.core.beans.msg.OrderIdInfo;
import com.taifook.mcs.core.beans.msg.OrderInfo;
import com.taifook.mcs.core.beans.msg.OrderListRequest;
import com.taifook.mcs.core.beans.msg.OrderListResponse;
import com.taifook.mcs.core.beans.msg.PPSTransferDetailRequest;
import com.taifook.mcs.core.beans.msg.PPSTransferDetailResponse;
import com.taifook.mcs.core.beans.msg.PreOrderPlacingRequest;
import com.taifook.mcs.core.beans.msg.PreOrderPlacingResponse;
import com.taifook.mcs.core.beans.msg.ProfitAndLossEnquiryRequest;
import com.taifook.mcs.core.beans.msg.ProfitAndLossEnquiryResponse;
import com.taifook.mcs.core.beans.msg.ProfitAndLossUpdateRequest;
import com.taifook.mcs.core.beans.msg.ProfitAndLossUpdateResponse;
import com.taifook.mcs.core.beans.msg.TermsAndConditionRequest;
import com.taifook.mcs.core.beans.msg.TermsAndConditionResponse;
import com.taifook.mcs.core.beans.msg.TradeHistoryRequest;
import com.taifook.mcs.core.beans.msg.TradeHistoryResponse;
import com.taifook.mcs.core.beans.msg.TradeInfo;
import com.taifook.mcs.core.beans.msg.TradeListRequest;
import com.taifook.mcs.core.beans.msg.TradeListResponse;
import com.taifook.mcs.core.beans.msg.TransactionProtectionRequest;
import com.taifook.mcs.core.beans.msg.TransactionProtectionResponse;
import com.taifook.mcs.core.beans.msg.VerifyPasswordRequest;
import com.taifook.mcs.core.beans.msg.VerifyPasswordResponse;
import com.taifook.mcs.core.beans.msg.WithDrawRequest;
import com.taifook.mcs.core.beans.msg.WithDrawResponse;
import com.taifook.mcs.core.util.EncryptPwd;
import com.taifook.mcs.msg.MessageSender;

/**
 * $Id: McsServiceImpl.java,v 1.43 2011/04/06 02:51:31 pbxie Exp $
 * 
 * @Project:portal.head
 * @File:McsServiceImpl.java
 * @Description:
 * @Author:Cimenon Saint
 * @Date:2007-5-21
 */
public class McsServiceImpl implements McsService {

	Log mcsInfo = LoggerFactory.getInstance().getMcsInfo();
    
    private MessageSender messageSender;

	public void setMessageSender(MessageSender messageSender) {
        this.messageSender = messageSender;
    }

	public LoginResponseModel callPwdLogin(LoginRequestModel requestModel)throws ITSZException {
		LoginResponseModel model = new LoginResponseModel();
		LoginRequest loginRequest = new LoginRequest();
		loginRequest = ModelUtil.assembleLoginRequest(requestModel);
		MCSMessage mes = getLogin(loginRequest, requestModel.getLanguage());
		model = processResponse(mes);
		return model;
	}

	private LoginResponseModel processResponse(MCSMessage mes) throws ITSZException {
		if (mes instanceof Exception01) {
			return processResponse((Exception01) mes);
		} else if (mes instanceof LoginResponse) {
			LoginResponseModel model = new LoginResponseModel();
			model.setLoginResponse((LoginResponse) mes);
			return model;
		}else {
            throw new ITSZException(Consts.Error.Code.CONNECT_MCS);
        }
	}

	private LoginResponseModel processResponse(Exception01 loginEx)
		throws ITSZException {
			throw new ITSZException(loginEx.getErrorSubCode(), loginEx.getErrorMessage());
	}

	private MCSMessage getLogin(
		LoginRequest loginRequest ,
		String language)throws ITSZException {
			MCSMessage mes = null;
            long bt = System.currentTimeMillis();
            try{
				mes = messageSender.sendRequest(loginRequest, language);
                LoggerFactory.getInstance().info(bt , loginRequest.getMessageId());
            }catch (Exception e) {
              LoggerFactory.getInstance().info(bt , loginRequest.getMessageId()+" " +e.getMessage());
              throw new ITSZException(Consts.Error.Code.CONNECT_MCS, e.getMessage());
            }
			return mes;
	}
    
	public OrderFeeResponseModel callOrderFee(
		OrderFeeRequestModel orderFeeReq ,
		BigDecimal mos) throws ITSZException {
			OrderFeeResponseModel orderFeeRes = new OrderFeeResponseModel();			
			CalOrderFeeResponse orderFeeResponse = new CalOrderFeeResponse();
			try {				
				//netAmount 总金额
				//transactionAmount 交易金額 qty*price
				//commonCharges 佣金>=50 transactionAmount*0.05%
				//stampCharge 厘印費<=1  transactionAmount×0.1%
				//levyCharge 交易徵費 transactionAmount×0.003%
				//tradingFee 交易費 transactionAmount×0.005%
				//結算費 ccassCharge 中央結算系統股份交收費 <= 100 >=2 transactionAmount×0.002%
				double transactionAmount = orderFeeReq.getOrderQuantity().longValue()*orderFeeReq.getOrderPrice().doubleValue();
				double commonCharges = transactionAmount * 0.05 / 100;
				if (commonCharges < 25) {
					commonCharges = 25;
				}
				double stampCharge = transactionAmount * 0.1 / 100;
				if (stampCharge < 1) {
					stampCharge = 1;
				}
				double levyCharge = transactionAmount * 0.003 / 100;
				double tradingFee = transactionAmount * 0.005 / 100;
				double ccassCharge = transactionAmount * 0.002 / 100;
				if (ccassCharge > 100) {
					ccassCharge = 100;
				}
				if (ccassCharge < 2) {
					ccassCharge = 2;
				}
				double netAmount = 0;
				if (Consts.Order.Side.B.equals(orderFeeReq.getOrderSide())) {
					netAmount = transactionAmount + commonCharges + stampCharge + levyCharge + tradingFee + ccassCharge;
				} else {
					netAmount = transactionAmount - commonCharges - stampCharge - levyCharge - tradingFee - ccassCharge;
				}
				orderFeeResponse.setNetAmount(PortalUtil.hold2Decimal(netAmount));
				orderFeeResponse.setTransactionAmount(PortalUtil.hold2Decimal(transactionAmount));
				orderFeeResponse.setCommonCharges(PortalUtil.hold2Decimal(commonCharges));
				orderFeeResponse.setStampCharge(PortalUtil.hold2Decimal(stampCharge));
				orderFeeResponse.setLevyCharge(PortalUtil.hold2Decimal(levyCharge));
				orderFeeResponse.setTradingFee(PortalUtil.hold2Decimal(tradingFee));
				orderFeeResponse.setCcassCharge(PortalUtil.hold2Decimal(ccassCharge));
				PropertyUtils.copyProperties(orderFeeRes,orderFeeReq);
				orderFeeRes.setCalOrderFeeResponse(orderFeeResponse);
			} catch (Exception odfEx) {
				mcsInfo.error("callOrderFee exception:: ",odfEx);
				throw new ITSZException(Consts.Error.Code.PARAMETER_MCS, "");
			}
			return orderFeeRes;
	}

	public BigDecimal getInsufficientAmount(BigDecimal netAmount, BigDecimal mos) {
		double amount = Consts.Global.Number.NONE;
		if (mos != null && netAmount != null) {
			if(mos.doubleValue()<=0) return netAmount;
			amount = netAmount.doubleValue() - mos.doubleValue();
			if (amount <= 0) {
				amount = Consts.Global.Number.NONE;
			}
		}
		return new BigDecimal(amount);
	}

	/**
	 * 
	 * @Author:Cimenon Saint
	 * @Time:2007-5-30 14:02:29
	 * @param orderFeeRequest
	 * @param language
	 * @return
	 * @throws ITSZException
	 */
	private MCSMessage getOrderFee(
		CalOrderFeeRequest orderFeeRequest ,
		String language) throws ITSZException {
			MCSMessage mes = null;
            long bt = System.currentTimeMillis();
			try {
				mes = messageSender.sendRequest(orderFeeRequest, language);
                LoggerFactory.getInstance().info(bt , orderFeeRequest.getMessageId());
			} catch (Exception e) {
                LoggerFactory.getInstance().info(bt , orderFeeRequest.getMessageId()+" " +e.getMessage());
				// :::fail mcs connect
				throw new ITSZException(Consts.Error.Code.CONNECT_MCS);
			}
			return mes;
	}

	/**
	 * call pre place order
	 */
	public PrePlaceOrderResponseModel callPrePlaceOrder(
		PrePlaceOrderRequestModel prePlaceReq) throws ITSZException {
			PrePlaceOrderResponseModel prePlaceRes = null;
			PreOrderPlacingRequest placingReq = assemblePrePlaceRequest(prePlaceReq);
			MCSMessage mes = getPrePlaceOrder(placingReq, prePlaceReq.getLanguage());
			if (mes instanceof PreOrderPlacingResponse) {
				PreOrderPlacingResponse prePlacingRes = (PreOrderPlacingResponse) mes;
				prePlaceRes = new PrePlaceOrderResponseModel();
				prePlaceRes.setAccountID(prePlacingRes.getAccountId());
				prePlaceRes.setInstrCode(prePlacingRes.getInstrCode());
				prePlaceRes.setTriggerRightAway(prePlacingRes.getTriggerRightAwayFlag());
				prePlaceRes.setPreOrderPlacingResponse(prePlacingRes);
			} else if (mes instanceof Exception01) {
				Exception01 prePlaceEx = (Exception01) mes;
				throw new ITSZException(prePlaceEx.getErrorSubCode());
			}
			return prePlaceRes;
	}
	
	/**
	 * 
	 * @Author:Cimenon Saint
	 * @Time:2007-5-30 9:28:24
	 * @param prePlaceReq
	 * @return
	 */
	private PreOrderPlacingRequest assemblePrePlaceRequest(PrePlaceOrderRequestModel prePlaceReq){
		PreOrderPlacingRequest preOrderReq = null;
		if(prePlaceReq != null){
			preOrderReq = new PreOrderPlacingRequest();
			try {
				PropertyUtils.copyProperties(preOrderReq , prePlaceReq);
				preOrderReq.setVersionId(Consts.Mcs.VersionId.VER100);
				preOrderReq.setMarketCode(Consts.Mcs.MarketCode.AMS3);
				preOrderReq.setMessageId(Consts.Mcs.MsgId.PreInputOrder);
				preOrderReq.setMessageSEQNum(Long.decode(Consts.Global.Number.SONE));
				String pwd = prePlaceReq.getPassword();
				preOrderReq.setPasswd(EncryptPwd.Encrypt(pwd, ""));
			} catch (Exception e) {
//				e.printStackTrace();
				mcsInfo.error("exception exist:"+e.getMessage());
			} 
		}
		return preOrderReq;
	}

	/**
	 * 
	 * @Author:Cimenon Saint
	 * @Time:2007-5-29 18:18:42
	 * @param prePlacingReq
	 * @return
	 */
	private MCSMessage getPrePlaceOrder(
		PreOrderPlacingRequest prePlacingReq ,
		String language)throws ITSZException {
			MCSMessage mes = null;
            long bt = System.currentTimeMillis();
			try {
				mes = messageSender.sendRequest(prePlacingReq, language);
                LoggerFactory.getInstance().info(bt , prePlacingReq.getMessageId());
			} catch (Exception e) {
                LoggerFactory.getInstance().info(bt , prePlacingReq.getMessageId()+" " +e.getMessage());
				// :::fail mcs connect
				throw new ITSZException(Consts.Error.Code.CONNECT_MCS, e.getMessage());
			}
			return mes;
	}

	/**
	 * 
	 */
	public PlaceOrderResponseModel callPlaceOrder(
		PlaceOrderRequestModel placeReq) throws ITSZException {
			PlaceOrderResponseModel placeOrderResponse =  new PlaceOrderResponseModel();
			InputOrderRequest inputReq = ModelUtil.assembleInputOrderRequest(placeReq);
			MCSMessage mes = getPlaceOrder(inputReq , placeReq.getLanguage());
			if (mes instanceof InputOrderResponse) {
				InputOrderResponse inputRes = (InputOrderResponse) mes;
				placeOrderResponse.setInputOrderResponse(inputRes);
			} else if (mes instanceof Exception01) {
				Exception01 placeEx = (Exception01) mes;
				throw new ITSZException(placeEx.getErrorSubCode(),placeEx.getErrorMessage());
			}
			return placeOrderResponse;
	}

	/**
	 * 
	 * @Author:Cimenon Saint
	 * @Time:2007-5-31 10:09:16
	 * @param inputOrderRequest
	 * @param language
	 * @return
	 * @throws ITSZException
	 */
	private MCSMessage getPlaceOrder(
		InputOrderRequest inputOrderRequest ,
		String language) throws ITSZException {
			MCSMessage mes = null;
            long bt = System.currentTimeMillis();
			try {
				mes = messageSender.sendRequest(inputOrderRequest, language);
                LoggerFactory.getInstance().info(bt , inputOrderRequest.getMessageId());
			} catch (Exception e) {
                LoggerFactory.getInstance().info(bt , inputOrderRequest.getMessageId()+" " +e.getMessage());
				// :::fail mcs connect
				throw new ITSZException(Consts.Error.Code.CONNECT_MCS, e.getMessage());
			}
			return mes;
	}

	/**
	 * call order filter group by order status
	 */
	public FilterOrderResponseModel callFilterOrder(FilterOrderRequestModel listModel) throws ITSZException {
		FilterOrderResponseModel filterRes = null;
		OrderFilteringRequest orderFiltering = ModelUtil.assembleOrderFilteringRequest(listModel);
		MCSMessage mes = getFilterOrder(orderFiltering, listModel.getLanguage());
		if (mes instanceof Exception01) {
			Exception01 filterEx = (Exception01) mes;
			throw new ITSZException(filterEx.getErrorSubCode(),filterEx.getErrorMessage());
		} else if (mes instanceof OrderFilteringResponse) {
			OrderFilteringResponse filteringRes = (OrderFilteringResponse) mes;
			filterRes = new FilterOrderResponseModel();
			if(StringUtils.isNotBlank(listModel.getPageSize())){
				int totalPage = PaginationUtil.getPageAmount(filteringRes.getListSize(),Integer.parseInt(listModel.getPageSize()));
				filterRes.setListSize(filteringRes.getListSize());
				filterRes.setTotalPage(totalPage);
				filterRes.setPageAmount(totalPage);
				filterRes.setPageNo(Integer.parseInt(listModel.getPageNo()));
				filterRes.setCurrentPage(filterRes.getPageNo());
				if(totalPage<=0){
					filterRes.setPageNo(totalPage);
				}
				filterRes.setPageSize(Integer.parseInt(listModel.getPageSize()));
				filterRes.setNextPage(getNextPage(totalPage,filterRes.getPageNo()));
				filterRes.setPrevPage(getPrevPage(filterRes.getPageNo()));
			}
			filterRes.setOrderFilteringResponse(filteringRes);
			filterRes.setUpdateTime(new Date(System.currentTimeMillis()));
		}
		return filterRes;
	}
	
	public ListOrderResponseModel callListOrder(ListOrderRequestModel listModel) throws ITSZException {
		ListOrderResponseModel filterRes = null;
		OrderFilteringRequest orderFiltering = ModelUtil.assembleOrderListingRequest1(listModel);
		MCSMessage mes = getFilterOrder(orderFiltering, listModel.getLanguage());
		if (mes instanceof Exception01) {
			Exception01 filterEx = (Exception01) mes;
			throw new ITSZException(filterEx.getErrorSubCode(),filterEx.getErrorMessage());
		} else if (mes instanceof OrderFilteringResponse) {
			OrderFilteringResponse filteringRes = (OrderFilteringResponse) mes;
			filterRes = new ListOrderResponseModel();
			filterRes.setOrderFilteringResponse(filteringRes);
			filterRes.setUpdateTime(new Date(System.currentTimeMillis()));
		}
		filterRes.setAccountId(listModel.getAccountId());
		return filterRes;
	}
	
	private int getPrevPage(int pageNo){
		if(pageNo<=1){
			return 1;
		}else{
		    return pageNo-1;
		}
	}
	
	private int getNextPage(int totalPage,int pageNo){
		if(totalPage<=1){
			return 1;
		}else{
		    return pageNo+1;
		}
	}
    
    /**
     * 
     * @Author:Cimenon Saint
     * @Time:2007-6-1 16:07:09
     * @param coll
     * @return
     */
    private OrderFilteringResponse assembleOrderInfoModelArray(Collection coll){
    	OrderFilteringResponse response = new OrderFilteringResponse();
        Collection infoModelColl = null;
        if(coll != null && coll.size() > 0){
            infoModelColl = new ArrayList();
            OrderInfo orderInfo=null;
            OrderInfoModel orderInfoModel=null;
            for(Iterator it=coll.iterator(); it.hasNext();){
                orderInfo=(OrderInfo)it.next();
                orderInfoModel=new OrderInfoModel();
                orderInfoModel.setAccountId(orderInfo.getAccountId());
                orderInfoModel.setMarketCode(Consts.Mcs.MarketCode.AMS3);
                orderInfoModel.setInstrCode(orderInfo.getInstrCode());
                orderInfoModel.setOrderSide(orderInfo.getOrderSide());
                orderInfoModel.setOrderQuantity(orderInfo.getOrderQuantity());
                orderInfoModel.setOrderPrice(orderInfo.getOrderPrice());
                orderInfoModel.setOrderType(orderInfo.getOrderType());
                orderInfoModel.setValidityType(orderInfo.getValidityType());
                orderInfoModel.setExpiryDate(orderInfo.getExpiryDate());
                orderInfoModel.setMcsOrderId(orderInfo.getMcsOrderId());
                orderInfoModel.setOrderId(orderInfo.getOrderId());
                orderInfoModel.setOrderState(orderInfo.getOrderState());
                orderInfoModel.setShortMtssOrderState(OrderStateConvertUtil.mappingOrderState(orderInfo.getMtssOrderState(), orderInfo.getOrderRemark()));
                orderInfoModel.setCcy(orderInfo.getCcy());
                orderInfoModel.setInitialQuantity(orderInfo.getInitialQuantity());
                orderInfoModel.setOutstandingQuantity(orderInfo.getOutstandingQuantity());
                orderInfoModel.setBusinessDate(orderInfo.getBusinessDate());
                orderInfoModel.setDateCreated(orderInfo.getDateCreated());
                orderInfoModel.setMtssOrderState(orderInfo.getMtssOrderState());
                orderInfoModel.setInstrName(orderInfo.getInstrName());
                orderInfoModel.setChangedQty(getChangedQty(orderInfo));
                orderInfoModel.setFilledQty(orderInfo.getFilledQty());
                orderInfoModel.setChannelType(orderInfo.getChannelType());
                orderInfoModel.setOrderRemark(orderInfo.getOrderRemark());
                orderInfoModel.setAllOrNothingFlag(orderInfo.getAllOrNothingFlag());
                orderInfoModel.setTriggerPrice(orderInfo.getTriggerPrice());
                orderInfoModel.setConditionType(orderInfo.getConditionType());
                orderInfoModel.setBasketRef(orderInfo.getBasketRef());
                infoModelColl.add(orderInfoModel);
            }
        }
        response.setOrderInfoCol(infoModelColl);
        return response;
    }

    private BigDecimal getChangedQty(OrderInfo order){
        BigDecimal cq = new BigDecimal(0.00);
        if(order != null){
            double filledQty = order.getFilledQty() != null ? order.getFilledQty().doubleValue():0.00;
            double osQty = order.getOutstandingQuantity() != null ? order.getOutstandingQuantity().doubleValue() : 0.00;
            double initQty = order.getInitialQuantity() != null ? order.getInitialQuantity().doubleValue() : 0.00;
            cq = new BigDecimal(filledQty + osQty - initQty);
        }
        return cq;
    }

	private MCSMessage getFilterOrder(
		OrderFilteringRequest filteringRequest ,
		String language) throws ITSZException {
			MCSMessage mes = null;
            long bt = System.currentTimeMillis();
			try {
				mes = messageSender.sendRequest(filteringRequest, language);
                LoggerFactory.getInstance().info(bt , filteringRequest.getMessageId());
			} catch (Exception e) {
                LoggerFactory.getInstance().info(bt , filteringRequest.getMessageId()+" "+e.getMessage());
				throw new ITSZException(Consts.Error.Code.CONNECT_MCS, e.getMessage());
			}
			return mes;
	}

	public MosResponseModel callMos(MosRequestModel mosModel) throws ITSZException {
		MosResponseModel mosResponse = new MosResponseModel();
		CalMOSRequest calMOSRequest = ModelUtil.assembleCalMOSRequest(mosModel);
		MCSMessage mes = getMos(calMOSRequest, mosModel.getLanguage());
		if (mes instanceof CalMOSResponse) {
			mosResponse.setCalMOSResponse((CalMOSResponse) mes);
		} else if (mes instanceof Exception01) {
			Exception01 mosEx = (Exception01) mes;
			throw new ITSZException(mosEx.getErrorSubCode(), mosEx.getErrorMessage());
		}
		return mosResponse;
	}

	private MCSMessage getMos(
		CalMOSRequest calMOSRequest ,
		String language)  throws ITSZException {
			MCSMessage mes = null;
            long bt = System.currentTimeMillis();
			try {
				mes = messageSender.sendRequest(calMOSRequest, language);
                LoggerFactory.getInstance().info(bt , calMOSRequest.getMessageId());
			} catch (Exception e) {
                LoggerFactory.getInstance().info(bt , calMOSRequest.getMessageId() + " " + e.getMessage());
				// :::call mos connect error
				throw new ITSZException(Consts.Error.Code.CONNECT_MCS, e.getMessage());
			}
			return mes;
	}

	public OrderDetailTradeResponseModel callOrderDetail(
		OrderDetailRequestModel orderDetail) throws ITSZException {
			OrderDetailTradeResponseModel detailWithHis = null;
			MCSMessage mes = null;
			OrderListRequest orderListRequest = ModelUtil.assembleOrderListRequest(orderDetail);
			mes = getOrderDetail(orderListRequest, orderDetail.getLanguage());
			if (mes instanceof OrderListResponse) {
				detailWithHis = new OrderDetailTradeResponseModel();
				OrderListResponse orderList = (OrderListResponse) mes;
				detailWithHis.setOrderListResponse(orderList);
				OrderInfo[] oInfos = (OrderInfo[]) orderList.getOrderInfoCol().toArray(new OrderInfo[0]);
				if (oInfos != null && oInfos.length > 0) {
					detailWithHis.setAllowModify(getAllowModifyFlag(oInfos[0].getMtssOrderState()));
					detailWithHis.setRejectedQty(oInfos[0].getRejectedQty());
					detailWithHis.setRejectReason(oInfos[0].getRejectReason());	
					if(StringUtils.isNotBlank(oInfos[0].getRejectReason())){
						detailWithHis.setRejectMessage(Messages.getBundle("com.itsz.sht.properties.ApplicationResourcesWEB",LanguageUtil.lang2locale(orderDetail.getLanguage())).getString(oInfos[0].getRejectReason())+"["+oInfos[0].getRejectReason()+"]");
					}					
				}
			} else if (mes instanceof Exception01) {
				Exception01 oDetailEx = (Exception01) mes;
				throw new ITSZException(oDetailEx.getErrorSubCode(),oDetailEx.getErrorMessage());
			}
			return detailWithHis;
	}
	
	private String getAllowModifyFlag(String s){
		if(Consts.Order.State.QUEUING.equals(s) || Consts.Order.State.RECEIVED.equals(s) || Consts.Order.State.PARTIALLY_FILLED.equals(s)){
			return Consts.Global.Flag.POSITIVE;
		}
		return Consts.Global.Flag.NEGATIVE;
	}

	private MCSMessage getOrderDetail(
		OrderListRequest listRequest ,
		String language) throws ITSZException {
			MCSMessage mes = null;
            long bt = System.currentTimeMillis();
			try {
				mes = messageSender.sendRequest(listRequest, language);
                LoggerFactory.getInstance().info(bt , listRequest.getMessageId());
			} catch (Exception e) {
                LoggerFactory.getInstance().info(bt , listRequest.getMessageId() + " " + e.getMessage());
				// :::fail mcs connect
				throw new ITSZException(Consts.Error.Code.CONNECT_MCS, e.getMessage());
			}
			return mes;
	}


    /**
	 * order histories
	 * 
	 * @see TradeHistoryProcessor
	 */
	public Collection callTradeHistory(
		OrderDetailRequestModel oDetailReq,OrderDetailTradeResponseModel responseModel)	throws ITSZException {
			Collection tHistories = null;
            Collection tempColl = null;
			TradeHistoryRequest listRequest = ModelUtil.assembleTradeHistoryRequest(oDetailReq);
			MCSMessage mes = getTradeHistory(listRequest, oDetailReq.getLanguage());
			if (mes instanceof TradeHistoryResponse) {
				tHistories = new LinkedList();
				TradeHistoryResponse tradeHistory = (TradeHistoryResponse) mes;
				if(null!=responseModel)
				    responseModel.setTradeHistoryResponse(tradeHistory);
                if(tradeHistory != null){
    				tHistories = tradeHistory.getTradeInfoCol();
                    if(tHistories != null && tHistories.size() > 0){
                        TradeInfoModel tradeInfoModel = null;
                        TradeInfo tradeInfo = null;
                        tempColl = new LinkedList();
                        for(Iterator it=tHistories.iterator(); it.hasNext();){
                            tradeInfo=(TradeInfo)it.next();
                            tradeInfoModel = new TradeInfoModel();
                            tradeInfoModel.setTradePrice(tradeInfo.getTradePrice());
                            tradeInfoModel.setTradeQuantity(tradeInfo.getTradeQuantity());
                            if(Consts.Order.Side.S.equals(tradeInfo.getTradeSide())){
                                tradeInfoModel.setBrokerNum(tradeInfo.getBuyerBrokerNum());
                            }else{
                                tradeInfoModel.setBrokerNum(tradeInfo.getSellerBrokerNum());
                            }
                            tempColl.add(tradeInfoModel);
                        }
                    }
                }
			} else if (mes instanceof Exception01) {
				Exception01 tradeHisEx = (Exception01) mes;
				throw new ITSZException(tradeHisEx.getErrorSubCode(), tradeHisEx.getErrorMessage());
			}
			return tempColl;
	}

	/**
	 * 
	 * @Author:Cimenon Saint
	 * @Time:2007-5-29 10:54:12
	 * @param historyRequest
	 * @param language
	 * @return
	 */
	private MCSMessage getTradeHistory(
		TradeHistoryRequest listRequest ,
		String language) throws ITSZException {
			MCSMessage mes = null;
            long bt = System.currentTimeMillis();
			try {
				mes = messageSender.sendRequest(listRequest, language);
                LoggerFactory.getInstance().info(bt , listRequest.getMessageId());
			} catch (Exception e) {
                LoggerFactory.getInstance().info(bt , listRequest.getMessageId() + " " +e.getMessage());
				// :::fail mcs connect
				throw new ITSZException(Consts.Error.Code.CONNECT_MCS, e.getMessage());
			}
			return mes;
	}

	/**
	 * get orderdetail for modify
	 * @Author:kyzou
	 * @Time:5-29 11:44
	 * @param OrderListRequest
	 * @return OrderInfo
	 */
	public OrderInfo getModifyOrderDetail(
		OrderListRequest requestModel) throws ITSZException {
			OrderInfo order = null;
			OrderListResponse orderInfo = null;
			MCSMessage mes = null;
			try {
				mes = messageSender.sendRequest(requestModel, requestModel.getLanguage());
				if (mes instanceof OrderListResponse) {
					orderInfo = (OrderListResponse) mes;
					if (orderInfo.getOrderInfoCol().size() > 0) {
						Iterator it = orderInfo.getOrderInfoCol().iterator();
						if (it.hasNext()) {
							order = (OrderInfo) it.next();
						}
					} else {
						return new OrderInfo();
					}
				} else if (mes instanceof Exception01) {
					Exception01 mDetailEx = (Exception01) mes;
					throw new ITSZException(mDetailEx.getErrorSubCode() , mDetailEx.getErrorMessage());
				}
			} catch (Exception ex) {
				throw new ITSZException(Consts.Error.Code.CONNECT_MCS, ex.getMessage());
			}
			return order;
	}

	/**
	 * get Modify Order response
	 * @Author:kyzou
	 * @Time:5-29 11:44
	 * @param ModifyOrderRequest
	 * @return MCSMessage
	 */
	private MCSMessage getModifyOrder(
		ModifyOrderRequest modifyOrderRequest ,
		String language) throws ITSZException {
			MCSMessage mes = null;
            long bt = System.currentTimeMillis();
			try {
				mes = messageSender.sendRequest(modifyOrderRequest, language);
                LoggerFactory.getInstance().info(bt , modifyOrderRequest.getMessageId());
			} catch (Exception ex) {
                LoggerFactory.getInstance().info(bt , modifyOrderRequest.getMessageId() + " "+ex.getMessage());
				throw new ITSZException(Consts.Error.Code.CONNECT_MCS, ex.getMessage());
			}
			return mes;
	}

	/**
	 * Modify Order
	 * @Author:kyzou
	 * @Time:5-29 11:44
	 * @param ModifyOrderRequestModel
	 * @return ModifyOrderResponseModel
	 */
	public ModifyOrderResponseModel callModifyOrder(
		ModifyOrderRequestModel reqModel,OrderInfo orderInfo) throws ITSZException {
			ModifyOrderResponseModel modifyOrderRes = null;
			ModifyOrderRequest modifyOrderRequest = ModelUtil.assembleModifyOrderRequest(reqModel, orderInfo);
            if(reqModel.getTriggerPrice() != null){
                modifyOrderRequest.setNewTriggerPrice(reqModel.getTriggerPrice());
            }
			MCSMessage mes = getModifyOrder(modifyOrderRequest,reqModel.getLanguage());
			ModifyOrderResponse mOrderRes = null;
			if (mes instanceof ModifyOrderResponse) {
				mOrderRes = (ModifyOrderResponse) mes;
				modifyOrderRes = assembleModifyOrderInfo(mOrderRes);
				modifyOrderRes.setModifyOrderRes(mOrderRes);
			} else if (mes instanceof Exception01) {
				Exception01 cDetailEx = (Exception01) mes;
				throw new ITSZException(cDetailEx.getErrorSubCode() , cDetailEx.getErrorMessage());
			}
			return modifyOrderRes;
	}
	
	private ModifyOrderResponseModel assembleModifyOrderInfo(ModifyOrderResponse modResponse){
		ModifyOrderResponseModel responseModel = null;
		if(modResponse != null){
			responseModel = new ModifyOrderResponseModel();
		    try {
				BeanUtils.copyProperties(responseModel, modResponse);
			} catch (Exception e) {
				mcsInfo.error("exception exist:"+e.getMessage());
			} 	
		}
		return responseModel;
	}

	/**
	 * get BulkCancel Order response
	 * 
	 * @Author:kyzou
	 * @Time:5-29 11:44
	 * @param BulkCancelOrderRequest
	 * @return MCSMessage
	 */
	private MCSMessage getBulkCancelOrder(
		BulkCancelOrderRequest bulkCancelReq, String language)
		throws ITSZException {
			MCSMessage mes = null;
            long bt = System.currentTimeMillis();
			try {
				mes = messageSender.sendRequest(bulkCancelReq, language);
                LoggerFactory.getInstance().info(bt , bulkCancelReq.getMessageId());
			} catch (Exception ex) {
                LoggerFactory.getInstance().info(bt , bulkCancelReq.getMessageId()+" "+ex.getMessage());
				throw new ITSZException(Consts.Error.Code.CONNECT_MCS, ex.getMessage());
			}
			return mes;
	}

	private MCSMessage getCancelOrder(
		CancelOrderRequest cancelReq, String language)
		throws ITSZException {
			MCSMessage mes = null;
            long bt = System.currentTimeMillis();
			try {
				mes = messageSender.sendRequest(cancelReq, language);
                LoggerFactory.getInstance().info(bt , cancelReq.getMessageId());
			} catch (Exception ex) {
                LoggerFactory.getInstance().info(bt , cancelReq.getMessageId()+" "+ex.getMessage());
				throw new ITSZException(Consts.Error.Code.CONNECT_MCS, ex.getMessage());
			}
			return mes;
	}

	public BulkCancelOrderResponseModel callBulkCancelOrder(BulkCancelOrderRequestModel reqModel) throws ITSZException {
		BulkCancelOrderResponseModel cancelRes = null;
		BulkCancelOrderRequest bulkCancelReq = assembleBulkCancelOrderInfo(reqModel);
		MCSMessage mes = getBulkCancelOrder(bulkCancelReq , reqModel.getLanguage());
		if (mes instanceof BulkCancelOrderResponse) {
			cancelRes = new BulkCancelOrderResponseModel();
			BulkCancelOrderResponse mor = (BulkCancelOrderResponse) mes;
			cancelRes = ModelUtil.assembleBulkCancelOrderInfo(mor);
			cancelRes.setOrderIdInfos(mor.getBulkCancelOrderInfo());
			cancelRes.setBulkCancelOrderResponse(mor);
		} else if (mes instanceof Exception01) {
			Exception01 cancelorderEx = (Exception01) mes;
			throw new ITSZException(cancelorderEx.getErrorSubCode());
		}
		return cancelRes;
	}

	public CancelOrderResponseModel callCancelOrder(CancelOrderRequestModel reqModel) throws ITSZException {
		CancelOrderResponseModel cancelRes = null;
		CancelOrderRequest cancelReq = assembleCancelOrderInfo(reqModel);
		MCSMessage mes = getCancelOrder(cancelReq , reqModel.getLanguage());
		if (mes instanceof CancelOrderResponse) {
			cancelRes = new CancelOrderResponseModel();
			CancelOrderResponse mor = (CancelOrderResponse) mes;			
			cancelRes = ModelUtil.assembleCancelOrderInfo(mor);
		} else if (mes instanceof Exception01) {
			Exception01 cancelorderEx = (Exception01) mes;
			throw new ITSZException(cancelorderEx.getErrorSubCode());
		}
		return cancelRes;
	}
     
 	/**
 	 * 
 	 * @Author:Cimenon Saint
 	 * @Time:2007-6-7 10:32:27
 	 * @param cancelReq
 	 * @return
 	 */
	private BulkCancelOrderRequest assembleBulkCancelOrderInfo(BulkCancelOrderRequestModel cancelReq){
		BulkCancelOrderRequest requestModel = null;
		if(cancelReq != null){
			requestModel = new BulkCancelOrderRequest();
	        requestModel.setVersionId(Consts.Mcs.VersionId.VER100);
	        requestModel.setMessageId(Consts.Mcs.MsgId.BulkCancelOrder);
	        requestModel.setMessageSEQNum(Long.decode(Consts.Global.Number.SONE));
	        requestModel.setChannelType(cancelReq.getChannelType());
	        requestModel.setChannelId(cancelReq.getChannelId());
	        requestModel.setLanguage(cancelReq.getLanguage());
	        requestModel.setAccountId(cancelReq.getAccountId());
	        requestModel.setLoginId(cancelReq.getLoginId());
	        Collection coll = getOrderIdColl(cancelReq.getMcsOrderID());
	        requestModel.setOrderIdList(coll);
	        String pwd = cancelReq.getPassword();
            if(StringUtils.isNotBlank(pwd)){
                requestModel.setPasswd(EncryptPwd.Encrypt(pwd, ""));
            }
		}
		return requestModel;
	}
    
	private CancelOrderRequest assembleCancelOrderInfo(CancelOrderRequestModel cancelReq){
		CancelOrderRequest requestModel = null;
		if(cancelReq != null){
			requestModel = new CancelOrderRequest();
	        requestModel.setVersionId(Consts.Mcs.VersionId.VER100);
	        requestModel.setMessageId(Consts.Mcs.MsgId.CancelOrder);
	        requestModel.setMessageSEQNum(Long.decode(Consts.Global.Number.SONE));
	        requestModel.setChannelType(cancelReq.getChannelType());
	        requestModel.setChannelId(cancelReq.getChannelId());
	        requestModel.setLanguage(cancelReq.getLanguage());
	        requestModel.setAccountId(cancelReq.getAccountId());
	        requestModel.setLoginId(cancelReq.getLoginId());
	        requestModel.setCustCode(cancelReq.getCustCode());
	        requestModel.setMtssOrderID(cancelReq.getMTSSOrderID());
	        requestModel.setMcsOrderId(cancelReq.getMCSOrderID());
	        String pwd = cancelReq.getPassword();
            if(StringUtils.isNotBlank(pwd)){
                requestModel.setPasswd(EncryptPwd.Encrypt(pwd, ""));
            }
		}
		return requestModel;
	}
	
	/**
	 * 
	 * @Author:Cimenon Saint
	 * @Time:2007-6-7 11:03:39
	 * @param mtssOrderIds
	 * @return
	 */
	private Collection getOrderIdColl(Collection mtssOrderIds){
		if(mtssOrderIds == null || mtssOrderIds.size() <= 0){
			return null;
		}
		Collection resColl = new ArrayList();
		for(Iterator it=mtssOrderIds.iterator(); it.hasNext();){
			OrderIdInfo idInfo = new OrderIdInfo();
			String orderId = (String)it.next();
			Long lOrderId = new Long(orderId);
			idInfo.setOrderId(lOrderId);
			resColl.add(idInfo); 
		}
		return resColl;
	}
	
	/**
	 * 
	 * @Author:kyzou
	 * @Time:2008-3-18
	 * @param 
	 * @return
	 */		
	public MISAccountSummaryResponse callEnquireAccount(EnquireAccountRequestModel accountModel) throws ITSZException{
		MCSMessage mes = null;
		MISAccountSummaryResponse misAccountRes = new MISAccountSummaryResponse();
		MISAccountSummaryRequest accountRequest = new MISAccountSummaryRequest();
        long bt = System.currentTimeMillis();
		try {
			PropertyUtils.copyProperties(accountRequest, accountModel);
			accountRequest.setMessageId(Consts.Mcs.MsgId.AccountSummary);
			accountRequest.setMessageSEQNum(Long.decode(Consts.Global.Number.SONE));
			accountRequest.setMarketCode(Consts.Mcs.MarketCode.AMS3);
			mes = messageSender.sendRequest(accountRequest, accountModel.getLanguage());
            LoggerFactory.getInstance().info(bt , accountRequest.getMessageId());
		} catch (Exception ex) {
            LoggerFactory.getInstance().info(bt , accountRequest.getMessageId()+" "+ex.getMessage());
			throw new ITSZException(Consts.Error.Code.CONNECT_MCS, ex.getMessage());
		}
		if (mes instanceof MISAccountSummaryResponse) {
			misAccountRes = (MISAccountSummaryResponse) mes;
		} else if (mes instanceof Exception01) {
			Exception01 cancelorderEx = (Exception01) mes;
			throw new ITSZException(cancelorderEx.getErrorSubCode());
		}
		return misAccountRes;
	}
	
	public MISAccountDetailResponse callEnquireAccountDetail(EnquireAccountRequestModel accountModel) throws ITSZException{
		MCSMessage mes = null;
		MISAccountDetailResponse accountDetailResponse=new MISAccountDetailResponse();
		MISAccountDetailRequest accountDetailRequest = new MISAccountDetailRequest();
        long bt = System.currentTimeMillis();
		try {
			PropertyUtils.copyProperties(accountDetailRequest, accountModel);
			accountDetailRequest.setMessageId(Consts.Mcs.MsgId.AccountDetail);
			accountDetailRequest.setMessageSEQNum(Long.decode(Consts.Global.Number.SONE));
			accountDetailRequest.setMarketCode(Consts.Mcs.MarketCode.AMS3);
			mes = messageSender.sendRequest(accountDetailRequest, accountModel.getLanguage());
            LoggerFactory.getInstance().info(bt , accountDetailRequest.getMessageId());
		} catch (Exception ex) {
            LoggerFactory.getInstance().info(bt , accountDetailRequest.getMessageId()+" "+ex.getMessage());
			throw new ITSZException(Consts.Error.Code.CONNECT_MCS, ex.getMessage());
		}
		if (mes instanceof MISAccountDetailResponse) {
			accountDetailResponse = (MISAccountDetailResponse) mes;
		} else if (mes instanceof Exception01) {
			Exception01 cancelorderEx = (Exception01) mes;
			throw new ITSZException(cancelorderEx.getErrorSubCode());
		}
		return accountDetailResponse;
	}
	
	public MTSSShareHoldingResponse callEnquiryPosition(EnquiryPositionRequestModel positionModel) throws ITSZException{
		MCSMessage mes = null;
		MTSSShareHoldingResponse positionRes = new MTSSShareHoldingResponse();
		MTSSShareHoldingRequest positionRequest = new MTSSShareHoldingRequest();
        long bt = System.currentTimeMillis();
		try {
			PropertyUtils.copyProperties(positionRequest,positionModel);
			positionRequest.setMessageId(Consts.Mcs.MsgId.StockHoldings);
			positionRequest.setMessageSEQNum(Long.decode(Consts.Global.Number.SONE));
			positionRequest.setMarketCode(Consts.Mcs.MarketCode.AMS3);
			mes = messageSender.sendRequest(positionRequest, positionModel.getLanguage());
            LoggerFactory.getInstance().info(bt , positionRequest.getMessageId());
		} catch (Exception ex) {
            LoggerFactory.getInstance().info(bt , positionRequest.getMessageId()+" "+ex.getMessage());
			throw new ITSZException(Consts.Error.Code.CONNECT_MCS, ex.getMessage());
		}
		if (mes instanceof MTSSShareHoldingResponse) {
			positionRes = (MTSSShareHoldingResponse) mes;
		} else if (mes instanceof Exception01) {
			Exception01 cancelorderEx = (Exception01) mes;
			throw new ITSZException(cancelorderEx.getErrorSubCode());
		}
		return positionRes;
	}
	
	public MISAccountEnquiryResponse callEnquireMisAccount(EnquireAccountListRequestModel accountModel) throws ITSZException{
		MCSMessage mes = null;
		MISAccountEnquiryResponse accountListRes = new MISAccountEnquiryResponse();
		MISAccountEnquiryRequest accountListRequest = new MISAccountEnquiryRequest();
		accountListRequest.setCustCode(accountModel.getCustCode());		
		long bt = System.currentTimeMillis();
		try {
			PropertyUtils.copyProperties(accountListRequest, accountModel);
			accountListRequest.setMessageId(Consts.Mcs.MsgId.AccountEnquery);
			accountListRequest.setMessageSEQNum(Long.decode(Consts.Global.Number.SONE));
			mes = messageSender.sendRequest(accountListRequest, accountModel.getLanguage());
	        LoggerFactory.getInstance().info(bt , accountListRequest.getMessageId());
		} catch (Exception ex) {
	        LoggerFactory.getInstance().info(bt , accountListRequest.getMessageId()+" "+ex.getMessage());
			throw new ITSZException(Consts.Error.Code.CONNECT_MCS, ex.getMessage());
		}
		if (mes instanceof MISAccountEnquiryResponse) {
			accountListRes = (MISAccountEnquiryResponse) mes;
		} else if (mes instanceof Exception01) {
			Exception01 accountlistEx = (Exception01) mes;
			throw new ITSZException(accountlistEx.getErrorSubCode());
		}
		return accountListRes;
	}
	
	public MISAccountListResponse callEnquireAccountList(EnquireAccountListRequestModel accountModel) throws ITSZException{
		MCSMessage mes = null;
		MISAccountListResponse accountListRes = new MISAccountListResponse();
		MISAccountListRequest accountListRequest = new MISAccountListRequest();
		accountListRequest.setCustCode(accountModel.getCustCode());		
		long bt = System.currentTimeMillis();
		try {
			PropertyUtils.copyProperties(accountListRequest, accountModel);
			accountListRequest.setMessageId(Consts.Mcs.MsgId.AccountList);
			accountListRequest.setMessageSEQNum(Long.decode(Consts.Global.Number.SONE));
			mes = messageSender.sendRequest(accountListRequest, accountModel.getLanguage());
	        LoggerFactory.getInstance().info(bt , accountListRequest.getMessageId());
		} catch (Exception ex) {
	        LoggerFactory.getInstance().info(bt , accountListRequest.getMessageId()+" "+ex.getMessage());
			throw new ITSZException(Consts.Error.Code.CONNECT_MCS, ex.getMessage());
		}
		if (mes instanceof MISAccountListResponse) {
			accountListRes = (MISAccountListResponse) mes;
		} else if (mes instanceof Exception01) {
			Exception01 accountlistEx = (Exception01) mes;
			throw new ITSZException(accountlistEx.getErrorSubCode());
		}
		return accountListRes;
	}
	
	public TransactionProtectionResponse callSetTransPwdPtd(TransactionProtectionRequestModel requestModel) throws ITSZException{
		MCSMessage mes = null;
		TransactionProtectionResponse transPtdRes = new TransactionProtectionResponse();
		TransactionProtectionRequest transPtdRequest = ModelUtil.assembleSetTransPwdPtdRequest(requestModel);
        long bt = System.currentTimeMillis();
		try {
			mes = messageSender.sendRequest(transPtdRequest, requestModel.getLanguage());
            LoggerFactory.getInstance().info(bt , transPtdRequest.getMessageId());
		} catch (Exception ex) {
            LoggerFactory.getInstance().info(bt , transPtdRequest.getMessageId()+" "+ex.getMessage());
			throw new ITSZException(Consts.Error.Code.CONNECT_MCS, ex.getMessage());
		}
		if (mes instanceof TransactionProtectionResponse) {
			transPtdRes = (TransactionProtectionResponse) mes;
		} else if (mes instanceof Exception01) {
			Exception01 cancelorderEx = (Exception01) mes;
			throw new ITSZException(cancelorderEx.getErrorSubCode());
		}
		return transPtdRes;
	}

	public ChangePwdResponseModel callChangePassword(ChangePwdRequestModel requestModel) throws ITSZException{
		MCSMessage mes = null;
		ChangePwdResponseModel changePwdResModel = new ChangePwdResponseModel();
		ChangePasswordRequest changePwdRequest = ModelUtil.assembleChangePassRequest(requestModel);
        long bt = System.currentTimeMillis();
		try {
			mes = messageSender.sendRequest(changePwdRequest, requestModel.getLanguage());
            LoggerFactory.getInstance().info(bt , changePwdRequest.getMessageId());
		} catch (Exception ex) {
            LoggerFactory.getInstance().info(bt , changePwdRequest.getMessageId()+" "+ex.getMessage());
			throw new ITSZException(Consts.Error.Code.CONNECT_MCS, ex.getMessage());
		}
		if (mes instanceof ChangePasswordResponse) {
			ChangePasswordResponse changePwdRes = (ChangePasswordResponse) mes;
			changePwdResModel = ModelUtil.assembleChangePassResponse(changePwdRes);
			changePwdResModel.setLoginId(requestModel.getLoginId());
		} else if (mes instanceof Exception01) {
			Exception01 cancelorderEx = (Exception01) mes;
			throw new ITSZException(cancelorderEx.getErrorSubCode());
		}
		return changePwdResModel;
	}
	
	public VerifyPasswordResponseModel callVerifyPassword(VerifyPasswordRequestModel requestModel) throws ITSZException{
		MCSMessage mes = null;
		VerifyPasswordResponseModel verifyPassResModel = new VerifyPasswordResponseModel();
		VerifyPasswordResponse verifyPassRes =null;
		VerifyPasswordRequest verifyPassRequest = new VerifyPasswordRequest();
        long bt = System.currentTimeMillis();
		try {
			PropertyUtils.copyProperties(verifyPassRequest,requestModel);
			verifyPassRequest.setMessageId(Consts.Mcs.MsgId.VerifyPassword);
			verifyPassRequest.setMessageSEQNum(Long.decode(Consts.Global.Number.SONE));
			mes = messageSender.sendRequest(verifyPassRequest, requestModel.getLanguage());
            LoggerFactory.getInstance().info(bt , verifyPassRequest.getMessageId());
		} catch (Exception ex) {
            LoggerFactory.getInstance().info(bt , verifyPassRequest.getMessageId()+" "+ex.getMessage());
			throw new ITSZException(Consts.Error.Code.CONNECT_MCS, ex.getMessage());
		}
		if (mes instanceof VerifyPasswordResponse) {
			verifyPassRes = (VerifyPasswordResponse) mes;
			verifyPassResModel.setPasswordMatch(verifyPassRes.getPasswordMatch());
			if(Consts.Global.Flag.NEGATIVE.equalsIgnoreCase(verifyPassRes.getPasswordMatch())){
				verifyPassResModel.setReturnCode(Consts.Error.Code.INVIDPASS);
			}
			verifyPassResModel.setVerifyPwdResponse(verifyPassRes);
		} else if (mes instanceof Exception01) {
			Exception01 cancelorderEx = (Exception01) mes;
			throw new ITSZException(cancelorderEx.getErrorSubCode());
		}
		return verifyPassResModel;
	}
	
	public ChangeTAndCResponseModel callChangeTAndC(ChangeTAndCRequestModel requestModel) throws ITSZException{
		MCSMessage mes = null;
		ChangeTAndCResponseModel changeTAndCResModel = new ChangeTAndCResponseModel();
		TermsAndConditionRequest changeTAndCRequest = ModelUtil.assembleChangeTAndCRequest(requestModel);
        long bt = System.currentTimeMillis();
		try {
			mes = messageSender.sendRequest(changeTAndCRequest, requestModel.getLanguage());
            LoggerFactory.getInstance().info(bt , changeTAndCRequest.getMessageId());
		} catch (Exception ex) {
            LoggerFactory.getInstance().info(bt , changeTAndCRequest.getMessageId()+" "+ex.getMessage());
			throw new ITSZException(Consts.Error.Code.CONNECT_MCS, ex.getMessage());
		}
		if (mes instanceof TermsAndConditionResponse) {
			TermsAndConditionResponse changePwdRes = (TermsAndConditionResponse) mes;
			changeTAndCResModel = ModelUtil.assembleChangeTAndCResponse(changePwdRes);
			changeTAndCResModel.setLoginId(requestModel.getLoginId());
		} else if (mes instanceof Exception01) {
			Exception01 cancelorderEx = (Exception01) mes;
			throw new ITSZException(cancelorderEx.getErrorSubCode());
		}
		return changeTAndCResModel;		
	}
	
	public TradeListResponseModel callTradeList(TradeListRequestModel requestModel) throws ITSZException{
		MCSMessage mes = null;
		TradeListResponseModel tradeListResModel = new TradeListResponseModel();
		TradeListRequest tradeListRequest = ModelUtil.assembleTradeListRequest(requestModel);
        long bt = System.currentTimeMillis();
		try {
			mes = messageSender.sendRequest(tradeListRequest, requestModel.getLanguage());
            LoggerFactory.getInstance().info(bt , tradeListRequest.getMessageId());
		} catch (Exception ex) {
            LoggerFactory.getInstance().info(bt , tradeListRequest.getMessageId()+" "+ex.getMessage());
			throw new ITSZException(Consts.Error.Code.CONNECT_MCS, ex.getMessage());
		}
		if (mes instanceof TradeListResponse) {
			TradeListResponse res = (TradeListResponse) mes;
			if(requestModel.getPageNo()==null){
				tradeListResModel.setResponse(res);
				return tradeListResModel;
			}
			tradeListResModel = ModelUtil.assembleTradeListResponse(res);
			int totalPage = PaginationUtil.getPageAmount(Integer.parseInt(res.getListSize()),Integer.parseInt(requestModel.getPageSize()));
			tradeListResModel.setListSize(Integer.parseInt(res.getListSize()));
			tradeListResModel.setTotalPage(totalPage);
			tradeListResModel.setPageAmount(totalPage);
			tradeListResModel.setPageNo(Integer.parseInt(requestModel.getPageNo()));
			tradeListResModel.setCurrentPage(tradeListResModel.getPageNo());
			if(totalPage<=0){
				tradeListResModel.setPageNo(totalPage);
			}
			tradeListResModel.setPageSize(Integer.parseInt(requestModel.getPageSize()));
			tradeListResModel.setNextPage(getNextPage(totalPage,tradeListResModel.getPageNo()));
			tradeListResModel.setPrevPage(getPrevPage(tradeListResModel.getPageNo()));
			tradeListResModel.setChannelId(requestModel.getChannelId());
			tradeListResModel.setChannelType(requestModel.getChannelType());
			tradeListResModel.setLanguage(requestModel.getLanguage());
		} else if (mes instanceof Exception01) {
			Exception01 ex = (Exception01) mes;
			throw new ITSZException(ex.getErrorSubCode());
		}
		return tradeListResModel;
	}
	
	public FundTransferResponseModel fundTransfer(FundTransferRequestModel requestModel) throws ITSZException{
		MCSMessage mes = null;
		FundTransferResponseModel fundTransferResModel = new FundTransferResponseModel();
		FundTransferRequest fundTransferRequest = ModelUtil.assembleFundTransferRequest(requestModel);
        long bt = System.currentTimeMillis();
		try {
			mes = messageSender.sendRequest(fundTransferRequest, requestModel.getLanguage());
            LoggerFactory.getInstance().info(bt , fundTransferRequest.getMessageId());
		} catch (Exception ex) {
            LoggerFactory.getInstance().info(bt , fundTransferRequest.getMessageId()+" "+ex.getMessage());
			throw new ITSZException(Consts.Error.Code.CONNECT_MCS, ex.getMessage());
		}
		if (mes instanceof FundTransferResponse) {
			FundTransferResponse res = (FundTransferResponse) mes;
			fundTransferResModel = ModelUtil.assembleFundTransferResponse(res);
		} else if (mes instanceof Exception01) {
			Exception01 ex = (Exception01) mes;
			throw new ITSZException(ex.getErrorSubCode());
		}
		return fundTransferResModel;
	}
	
	public ProfitAndLossEnqiryResponseModel callProfitAndLossEnqiry(ProfitAndLossEnqiryRequestModel requestModel)throws ITSZException{
		ProfitAndLossEnquiryRequest profitAndLossEnquiryRequest=ModelUtil.assembleProfitAndLossEnquiryRequest(requestModel);
		ProfitAndLossEnqiryResponseModel profitAndLossEnqiryResponseModel=new ProfitAndLossEnqiryResponseModel();
		long bt = System.currentTimeMillis();
		MCSMessage mes = null;
		try {
			mes = messageSender.sendRequest(profitAndLossEnquiryRequest, requestModel.getLanguage());
			LoggerFactory.getInstance().info(bt , profitAndLossEnquiryRequest.getMessageId());
		} catch (Exception ex) {
			LoggerFactory.getInstance().info(bt , profitAndLossEnquiryRequest.getMessageId()+" "+ex.getMessage());
			throw new ITSZException(Consts.Error.Code.CONNECT_MCS, ex.getMessage());
		}
		if (mes instanceof ProfitAndLossEnquiryResponse) {
			ProfitAndLossEnquiryResponse res = (ProfitAndLossEnquiryResponse) mes;
			profitAndLossEnqiryResponseModel.setProfitAndLossEnquiryResponse(res);
		} else if (mes instanceof Exception01) {
			Exception01 ex = (Exception01) mes;
			throw new ITSZException(ex.getErrorSubCode());
		}
		return profitAndLossEnqiryResponseModel;
	}
	
	public ProfitAndLossUpdateResponseModel callProfitAndLossUpdate(ProfitAndLossUpdateRequestModel requestModel)throws ITSZException{
		ProfitAndLossUpdateRequest profitAndLossUpdateRequest=ModelUtil.assembleProfitAndLossUpdateRequest(requestModel);
		ProfitAndLossUpdateResponseModel profitAndLossUpdateResponseModel=new ProfitAndLossUpdateResponseModel();
		long bt = System.currentTimeMillis();
		MCSMessage mes = null;
		try {
			mes = messageSender.sendRequest(profitAndLossUpdateRequest, requestModel.getLanguage());
			LoggerFactory.getInstance().info(bt , profitAndLossUpdateRequest.getMessageId());
		} catch (Exception ex) {
			LoggerFactory.getInstance().info(bt , profitAndLossUpdateRequest.getMessageId()+" "+ex.getMessage());
			throw new ITSZException(Consts.Error.Code.CONNECT_MCS, ex.getMessage());
		}
		if (mes instanceof ProfitAndLossUpdateResponse) {
			ProfitAndLossUpdateResponse res = (ProfitAndLossUpdateResponse) mes;
			profitAndLossUpdateResponseModel.setProfitAndLossUpdateResponse(res);
		} else if (mes instanceof Exception01) {
			Exception01 ex = (Exception01) mes;
			throw new ITSZException(ex.getErrorSubCode());
		}
		return profitAndLossUpdateResponseModel;
	}
	
	public CashDetailResponseModel callCashDetail(CashDetailRequestModel cashDetailRequestModel)throws ITSZException {
		MISAccountCashHoldingRequest accountCashHoldingRequest =ModelUtil.assembleCashDetailRequest(cashDetailRequestModel);
		CashDetailResponseModel cashDetailResponseModel=new CashDetailResponseModel();
		long bt = System.currentTimeMillis();
		MCSMessage mes = null;
		try {
			mes = messageSender.sendRequest(accountCashHoldingRequest, cashDetailRequestModel.getLanguage());
			LoggerFactory.getInstance().info(bt , accountCashHoldingRequest.getMessageId());
		} catch (Exception ex) {
			LoggerFactory.getInstance().info(bt , accountCashHoldingRequest.getMessageId()+" "+ex.getMessage());
			throw new ITSZException(Consts.Error.Code.CONNECT_MCS, ex.getMessage());
		}
		if (mes instanceof MISAccountCashHoldingResponse) {
			MISAccountCashHoldingResponse res = (MISAccountCashHoldingResponse) mes;
			cashDetailResponseModel.setMisAccountCashHoldingResponse(res);
		} else if (mes instanceof Exception01) {
			Exception01 ex = (Exception01) mes;
			throw new ITSZException(ex.getErrorSubCode());
		}
        return cashDetailResponseModel;
	}
	
	public FundTransferResponseModel withDraw(FundTransferRequestModel requestModel) throws ITSZException{
		MCSMessage mes = null;
		FundTransferResponseModel withDrawResModel = new FundTransferResponseModel();
		WithDrawRequest withDrawRequest = ModelUtil.assembleWithDrawRequest(requestModel);
		long bt = System.currentTimeMillis();
		try {
			mes = messageSender.sendRequest(withDrawRequest, requestModel.getLanguage());
			LoggerFactory.getInstance().info(bt , withDrawRequest.getMessageId());
		} catch (Exception ex) {
			LoggerFactory.getInstance().info(bt , withDrawRequest.getMessageId()+" "+ex.getMessage());
			throw new ITSZException(Consts.Error.Code.CONNECT_MCS, ex.getMessage());
		}
		if (mes instanceof FundTransferResponse) {
			//FundTransferResponse res = (FundTransferResponse) mes;
			FundTransferResponse res = (FundTransferResponse) mes;
			withDrawResModel = ModelUtil.assembleFundTransferResponse(res);
		} else if(mes instanceof WithDrawResponse){
			WithDrawResponse res=(WithDrawResponse)mes;
			withDrawResModel=ModelUtil.assembleWithDrawResponse(res);
		}else if (mes instanceof Exception01) {
			Exception01 ex = (Exception01) mes;
			throw new ITSZException(ex.getErrorSubCode());
		}
		return withDrawResModel;
	}
	
	public BOCTransferResponseModel bocTransfer(BOCTransferRequestModel requestModel) throws ITSZException{
		MCSMessage mes = null;
		BOCTransferResponseModel resModel = new BOCTransferResponseModel();
		BOCTransferRequest request = ModelUtil.assembleBOCTransferRequest(requestModel);
        long bt = System.currentTimeMillis();
		try {
			mes = messageSender.sendRequest(request, requestModel.getLanguage());
            LoggerFactory.getInstance().info(bt , request.getMessageId());
		} catch (Exception ex) {
            LoggerFactory.getInstance().info(bt , request.getMessageId()+" "+ex.getMessage());
			throw new ITSZException(Consts.Error.Code.CONNECT_MCS, ex.getMessage());
		}
		if (mes instanceof BOCTransferResponse) {
			BOCTransferResponse res = (BOCTransferResponse) mes;
			resModel.setBocTransferResponse(res);
		} else if (mes instanceof Exception01) {
			Exception01 ex = (Exception01) mes;
			throw new ITSZException(ex.getErrorSubCode());
		}
		return resModel;
	}
	
	public PPSEnquiryResponseModel getPPSRecords(PPSEnquiryRequestModel requestModel) throws ITSZException{
		MCSMessage mes = null;
		PPSEnquiryResponseModel resModel = new PPSEnquiryResponseModel();
		PPSTransferDetailRequest request = ModelUtil.assemblePPSEnquiryRequest(requestModel);
        long bt = System.currentTimeMillis();
		try {
			mes = messageSender.sendRequest(request, requestModel.getLanguage());
            LoggerFactory.getInstance().info(bt , request.getMessageId());
		} catch (Exception ex) {
            LoggerFactory.getInstance().info(bt , request.getMessageId()+" "+ex.getMessage());
			throw new ITSZException(Consts.Error.Code.CONNECT_MCS, ex.getMessage());
		}
		if (mes instanceof PPSTransferDetailResponse) {
			PPSTransferDetailResponse res = (PPSTransferDetailResponse) mes;
			resModel.setPpsTransferDetailResponse(res);
		} else if (mes instanceof Exception01) {
			Exception01 ex = (Exception01) mes;
			throw new ITSZException(ex.getErrorSubCode());
		}
		return resModel;
	}
	
	public MarginFinancingListResponseModel callEnquireMarginRations(MarginFinancingListRequestModel requestModel)throws ITSZException{
		MCSMessage mes = null;
		MarginFinancingListResponseModel resModel = new MarginFinancingListResponseModel();
		MarginFinancingListRequest request = ModelUtil.assembleEnquireMarginRationsRequest(requestModel);
        long bt = System.currentTimeMillis();
		try {
			mes = messageSender.sendRequest(request, requestModel.getLanguage());
            LoggerFactory.getInstance().info(bt , request.getMessageId());
		} catch (Exception ex) {
            LoggerFactory.getInstance().info(bt , request.getMessageId()+" "+ex.getMessage());
			throw new ITSZException(Consts.Error.Code.CONNECT_MCS, ex.getMessage());
		}
		if (mes instanceof MarginFinancingListResponse) {
			MarginFinancingListResponse res = (MarginFinancingListResponse) mes;
			resModel.setMarginFinancingListResponse(res);
		} else if (mes instanceof Exception01) {
			Exception01 ex = (Exception01) mes;
			throw new ITSZException(ex.getErrorSubCode());
		}
		return resModel;
	}
}