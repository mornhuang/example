package com.itsz.sht.common.model.common;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Vector;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;

import com.itsz.rtq.provider.quot.DetailQuot;
import com.itsz.rtq.provider.quot.QuotHeader;
import com.itsz.rtq.provider.quot.QuotRecord;
import com.itsz.sht.common.Constants;
import com.itsz.sht.common.Consts;
import com.itsz.sht.common.FormatUtil;
import com.itsz.sht.common.LoggerFactory;
import com.itsz.sht.common.PortalUtil;
import com.itsz.sht.common.model.request.BOCTransferRequestModel;
import com.itsz.sht.common.model.request.CashDetailRequestModel;
import com.itsz.sht.common.model.request.ChangePwdRequestModel;
import com.itsz.sht.common.model.request.ChangeTAndCRequestModel;
import com.itsz.sht.common.model.request.ECertLoginRequestModel;
import com.itsz.sht.common.model.request.EnquireRTQRequestModel;
import com.itsz.sht.common.model.request.EnquiryPositionRequestModel;
import com.itsz.sht.common.model.request.FilterOrderRequestModel;
import com.itsz.sht.common.model.request.ListOrderRequestModel;
import com.itsz.sht.common.model.request.LoginRequestModel;
import com.itsz.sht.common.model.request.LotSizeRequestModel;
import com.itsz.sht.common.model.request.MarginFinancingListRequestModel;
import com.itsz.sht.common.model.request.ModifyOrderRequestModel;
import com.itsz.sht.common.model.request.MosRequestModel;
import com.itsz.sht.common.model.request.OrderDetailRequestModel;
import com.itsz.sht.common.model.request.OrderFeeRequestModel;
import com.itsz.sht.common.model.request.PPSEnquiryRequestModel;
import com.itsz.sht.common.model.request.PlaceOrderRequestModel;
import com.itsz.sht.common.model.request.ProfitAndLossEnqiryRequestModel;
import com.itsz.sht.common.model.request.ProfitAndLossUpdateRequestModel;
import com.itsz.sht.common.model.request.SnapshotRTQRequestModel;
import com.itsz.sht.common.model.request.StreamRTQRequestModel;
import com.itsz.sht.common.model.request.TradeListRequestModel;
import com.itsz.sht.common.model.request.TransactionProtectionRequestModel;
import com.itsz.sht.common.model.request.VerifyPlaceOrderRequestModel;
import com.itsz.sht.common.model.request.login.RtqRequestModel;
import com.itsz.sht.common.model.response.BulkCancelOrderResponseModel;
import com.itsz.sht.common.model.response.CancelOrderResponseModel;
import com.itsz.sht.common.model.response.ChangePwdResponseModel;
import com.itsz.sht.common.model.response.ChangeTAndCResponseModel;
import com.itsz.sht.common.model.response.EnquireDeptRTQResponse;
import com.itsz.sht.common.model.response.EnquireRTQResponse;
import com.itsz.sht.common.model.response.EnquireShortRTQResponse;
import com.itsz.sht.common.model.response.EnquiryPositionResponseModel;
import com.itsz.sht.common.model.response.FundTransferRequestModel;
import com.itsz.sht.common.model.response.FundTransferResponseModel;
import com.itsz.sht.common.model.response.LotSizeResponseModel;
import com.itsz.sht.common.model.response.MISAccountListResponseModel;
import com.itsz.sht.common.model.response.MosResponseModel;
import com.itsz.sht.common.model.response.OrderFeeResponseModel;
import com.itsz.sht.common.model.response.TradeListResponseModel;
import com.itsz.sht.common.model.response.TransactionProtectionResponseModel;
import com.itsz.sht.common.model.response.login.LoginModel;
import com.itsz.sht.common.model.response.login.LoginResultModel;
import com.itsz.sht.common.util.LangUtil;
import com.itsz.sht.service.mcs.McsUtil;
import com.taifook.mcs.core.beans.msg.Account;
import com.taifook.mcs.core.beans.msg.AccountListDetail;
import com.taifook.mcs.core.beans.msg.BOCTransferRequest;
import com.taifook.mcs.core.beans.msg.BulkCancelOrderResponse;
import com.taifook.mcs.core.beans.msg.CalMOSRequest;
import com.taifook.mcs.core.beans.msg.CalMOSResponse;
import com.taifook.mcs.core.beans.msg.CalOrderFeeRequest;
import com.taifook.mcs.core.beans.msg.CalOrderFeeResponse;
import com.taifook.mcs.core.beans.msg.CancelOrderResponse;
import com.taifook.mcs.core.beans.msg.ChangePasswordRequest;
import com.taifook.mcs.core.beans.msg.ChangePasswordResponse;
import com.taifook.mcs.core.beans.msg.FundTransferRequest;
import com.taifook.mcs.core.beans.msg.FundTransferResponse;
import com.taifook.mcs.core.beans.msg.InputOrderRequest;
import com.taifook.mcs.core.beans.msg.LoginRequest;
import com.taifook.mcs.core.beans.msg.LoginResponse;
import com.taifook.mcs.core.beans.msg.MISAccountCashHoldingRequest;
import com.taifook.mcs.core.beans.msg.MISAccountListResponse;
import com.taifook.mcs.core.beans.msg.MTSSShareHoldingResponse;
import com.taifook.mcs.core.beans.msg.MarginFinancingListRequest;
import com.taifook.mcs.core.beans.msg.ModifyOrderRequest;
import com.taifook.mcs.core.beans.msg.OrderFilteringRequest;
import com.taifook.mcs.core.beans.msg.OrderInfo;
import com.taifook.mcs.core.beans.msg.OrderListRequest;
import com.taifook.mcs.core.beans.msg.OrderListResponse;
import com.taifook.mcs.core.beans.msg.PPSTransferDetailRequest;
import com.taifook.mcs.core.beans.msg.ProfileInfo;
import com.taifook.mcs.core.beans.msg.ProfitAndLossEnquiryRequest;
import com.taifook.mcs.core.beans.msg.ProfitAndLossUpdateRequest;
import com.taifook.mcs.core.beans.msg.TermsAndConditionRequest;
import com.taifook.mcs.core.beans.msg.TermsAndConditionResponse;
import com.taifook.mcs.core.beans.msg.TradeHistoryRequest;
import com.taifook.mcs.core.beans.msg.TradeListRequest;
import com.taifook.mcs.core.beans.msg.TradeListResponse;
import com.taifook.mcs.core.beans.msg.TransactionProtectionRequest;
import com.taifook.mcs.core.beans.msg.TransactionProtectionResponse;
import com.taifook.mcs.core.beans.msg.WithDrawRequest;
import com.taifook.mcs.core.beans.msg.WithDrawResponse;
import com.taifook.mcs.core.util.EncryptPwd;

/**
 * $Id: ModelUtil.java,v 1.28 2011/04/19 07:52:49 xwquan Exp $
 * @Project:portal.head
 * @File:ModelUtil.java
 * @Description:
 * @Author:Cimenon Saint
 * @Date:2007-5-28
 */
public class ModelUtil {

	static Log commonInfo = LoggerFactory.getInstance().getCommonInfo();
	
	
	/**
	 * set order fee response data from request object[page]
	 * @Author:Cimenon Saint
	 * @Time:2007-6-4 14:02:46
	 * @param responseModel
	 * @param requestModel
	 */
	public static void echoplexOrderFee(
		OrderFeeResponseModel responseModel ,
		OrderFeeRequestModel requestModel){
			responseModel.setVersionId(requestModel.getVersionId());
			responseModel.setChannelId(requestModel.getChannelId());
			responseModel.setChannelType(requestModel.getChannelType());
			responseModel.setAllOrNothing(requestModel.getAllOrNothing());
			responseModel.setLanguage(requestModel.getLanguage());
			responseModel.setOrderSide(requestModel.getOrderSide());
			responseModel.setOrderType(requestModel.getOrderType());
			responseModel.setOrderPrice(requestModel.getOrderPrice());
			responseModel.setIsConditionType(requestModel.getIsConditionType());
			responseModel.setConditionType(requestModel.getConditionType());
			responseModel.setTriggerPrice(requestModel.getTriggerPrice());
			if(Consts.Order.Type.AT_AUCTION.equals(requestModel.getOrderType())){
				responseModel.setAllOrNothing(Consts.Global.Flag.NEGATIVE);
				responseModel.setOrderPrice(null);
            }
	}
	/**
	 * 
	 * @Author:Cimenon Saint
	 * @Time:2007-6-2 20:38:47
	 * @param detailReq
	 * @return
	 */
	public static TradeHistoryRequest assembleTradeHistoryRequest(OrderDetailRequestModel detailReq){
		TradeHistoryRequest historyReq = null;
		if(detailReq != null){
			historyReq = new TradeHistoryRequest();
			try {
				PropertyUtils.copyProperties(historyReq , detailReq);
				historyReq.setVersionId(Consts.Mcs.VersionId.VER100);
				historyReq.setMessageId(Consts.Mcs.MsgId.TradeHistory);
				historyReq.setMessageSEQNum(Long.decode(Consts.Global.Number.SONE));
			} catch (Exception e) {
				commonInfo.error(e);
			} 
		}
		return historyReq;
	}
	
	/**
	 * 
	 * @Author:Cimenon Saint
	 * @Time:2007-6-2 17:58:49
	 * @param orderFeeRequest
	 * @return
	 */
    public static LotSizeRequestModel assembleLotSizeRequest(OrderFeeRequestModel orderFeeRequest){
		LotSizeRequestModel lotSizeRequest = null;
		if(orderFeeRequest != null){
			lotSizeRequest = new LotSizeRequestModel();			
			lotSizeRequest.setChannelType(orderFeeRequest.getChannelType());
			lotSizeRequest.setSeqn(Consts.Global.Number.STHREE);
			lotSizeRequest.setLanguage(orderFeeRequest.getLanguage());
			lotSizeRequest.setSubType(Consts.Qs.S_TYPE_DELAY);
			lotSizeRequest.setCustomerId(orderFeeRequest.getCustomerId());
			lotSizeRequest.setServiceType(Consts.Global.Number.SZERO);
			String instrCode = FormatUtil.formatInstrCode(orderFeeRequest.getInstrCode() , 4);
			Vector vector = new Vector();
			vector.add(instrCode);
			lotSizeRequest.setCode(vector);
			lotSizeRequest.setDelayTime(String.valueOf(Consts.Qs.DELAY_TIME));
			lotSizeRequest.setType(Consts.Qs.QUOT_TYPE);
		}
		return lotSizeRequest;
	}
    
    public static LotSizeRequestModel assembleLotSizeRequest(ModifyOrderRequestModel orderFeeRequest){
		LotSizeRequestModel lotSizeRequest = null;
		if(orderFeeRequest != null){
			lotSizeRequest = new LotSizeRequestModel();			
			lotSizeRequest.setChannelType(orderFeeRequest.getChannelType());
			lotSizeRequest.setSeqn(Consts.Global.Number.STHREE);
			lotSizeRequest.setLanguage(orderFeeRequest.getLanguage());
			lotSizeRequest.setSubType(Consts.Qs.S_TYPE_DELAY);
			lotSizeRequest.setCustomerId(orderFeeRequest.getCustCode());
			lotSizeRequest.setServiceType(Consts.Global.Number.SZERO);
			String instrCode = FormatUtil.formatInstrCode(orderFeeRequest.getInstrCode() , 4);
			Vector vector = new Vector();
			vector.add(instrCode);
			lotSizeRequest.setCode(vector);
			lotSizeRequest.setDelayTime(String.valueOf(Consts.Qs.DELAY_TIME));
			lotSizeRequest.setType(Consts.Qs.QUOT_TYPE);
		}
		return lotSizeRequest;
	}
	
	/**
	 * 
	 * @Author:Cimenon Saint
	 * @Time:2007-6-1 16:09:27
	 * @param calMos
	 * @return
	 */
	public static MosResponseModel assembleMosResponse(CalMOSResponse calMos){
		MosResponseModel mosResponse = null;
		if(calMos != null){
			mosResponse = new MosResponseModel();
			try {
				PropertyUtils.copyProperties(mosResponse , calMos);
			} catch (Exception e) {
				commonInfo.error(e);
			} 

		}
		return mosResponse;
	}
	
	public static InputOrderRequest assembleInputOrderRequest(PlaceOrderRequestModel placeReq){
		InputOrderRequest inputOrderReq = null;
		if(placeReq != null){
			inputOrderReq = new InputOrderRequest();
			try {
				PropertyUtils.copyProperties(inputOrderReq , placeReq);				
				inputOrderReq.setVersionId(Consts.Mcs.VersionId.VER100);
				inputOrderReq.setMarketCode(Consts.Mcs.MarketCode.AMS3);
				inputOrderReq.setMessageId(Consts.Mcs.MsgId.InputOrder);
				inputOrderReq.setMessageSEQNum(Long.decode(Consts.Global.Number.SONE));
				//Encrypt password
				String pwd = placeReq.getPassword();
				if(StringUtils.isNotBlank(pwd)){
					inputOrderReq.setPasswd(EncryptPwd.Encrypt(pwd, ""));
				}
			} catch (Exception e) {
				commonInfo.error(e);
			} 
		}
		return inputOrderReq;
	}
	/**
	 * 
	 * @Author:Cimenon Saint
	 * @Time:2007-5-30 17:29:01
	 * @param detailQuot
	 * @return
	 */
	public static LotSizeResponseModel assembleLotSizeResponse(DetailQuot detailQuot){
		LotSizeResponseModel lotSizeResponse = null;
		if(detailQuot != null){
			lotSizeResponse = new LotSizeResponseModel();
			lotSizeResponse.setCode(detailQuot.Quot.Code);
			lotSizeResponse.setName(detailQuot.Quot.Name);
			lotSizeResponse.setTime(detailQuot.Quot.Time);
			lotSizeResponse.setPClose(detailQuot.Quot.PClose);
			lotSizeResponse.setPrice(detailQuot.Quot.Price);
			lotSizeResponse.setOpen(detailQuot.Quot.Open);
			lotSizeResponse.setHigh(detailQuot.Quot.High);
			lotSizeResponse.setLow(detailQuot.Quot.Low);
			lotSizeResponse.setClose(detailQuot.Quot.Close);
			lotSizeResponse.setTurnover(detailQuot.Quot.Turnover);
			lotSizeResponse.setChange(detailQuot.Quot.Change);
			lotSizeResponse.setPctChange(detailQuot.Quot.PctChange);
			lotSizeResponse.setVolume(detailQuot.Quot.Volume);
			lotSizeResponse.setYearHigh(detailQuot.Quot.YearHigh);
			lotSizeResponse.setYearLow(detailQuot.Quot.YearLow);
			lotSizeResponse.setPe(detailQuot.Quot.Pe);
			lotSizeResponse.setMarketCap(detailQuot.Quot.MarketCap);
			lotSizeResponse.setLowSpread(detailQuot.Quot.LowSpread);
			lotSizeResponse.setHighSpread(detailQuot.Quot.HighSpread);
			lotSizeResponse.setLotSize(detailQuot.Quot.LotSize);
			lotSizeResponse.setGearing(detailQuot.Quot.Gearing);
			lotSizeResponse.setExpDate(detailQuot.Quot.ExpDate);
			lotSizeResponse.setExePrice(detailQuot.Quot.ExePrice);
			lotSizeResponse.setConvRatio(detailQuot.Quot.ConvRatio);
			lotSizeResponse.setPreminum(detailQuot.Quot.Preminum);
			lotSizeResponse.setNote(detailQuot.Quot.Note);
			lotSizeResponse.setYield(detailQuot.Quot.Yield);
			lotSizeResponse.setCurrency(detailQuot.Quot.Currency);
			lotSizeResponse.setBestBid(detailQuot.Quot.bestBid);
			lotSizeResponse.setBestAsk(detailQuot.Quot.bestAsk);
			lotSizeResponse.setLatest_traded_time(detailQuot.Quot.latest_traded_time);
			lotSizeResponse.setLatest_traded_vol(detailQuot.Quot.latest_traded_vol);
			lotSizeResponse.setLatest_traded_price(detailQuot.Quot.latest_traded_price);
			lotSizeResponse.setLast_update_time(detailQuot.Quot.last_update_time);
			lotSizeResponse.setBody_type(detailQuot.Quot.body_type);
			lotSizeResponse.setLogTime(detailQuot.Quot.LogTime);
			lotSizeResponse.setStatus(detailQuot.Quot.Status);
		}
		return lotSizeResponse;
	}
	
	/**
	 * 
	 * @Author:kyzou
	 * @Time:2008-3-19 11:45:01
	 * @param detailQuot
	 * @return
	 */
	public static List<EnquireRTQResponse> assembleSimpleRtqResponse(DetailQuot[] detailQuots){
		List<EnquireRTQResponse> enquireRtqResponses = new ArrayList<EnquireRTQResponse>();
		for (int i = 0; i < detailQuots.length; i++) {
			DetailQuot detailQuot = detailQuots[i];
			if(detailQuot != null){
				EnquireRTQResponse enquireRtqResponse = new EnquireRTQResponse();
				if(detailQuot.Quot.Status == 0 ||detailQuot.Quot.Status == 2){
				enquireRtqResponse.setCode(detailQuot.Quot.Code);
				enquireRtqResponse.setName(detailQuot.Quot.Name);
				enquireRtqResponse.setTime(detailQuot.Quot.Time);
				enquireRtqResponse.setPClose(transValue(detailQuot.Quot.PClose));
				enquireRtqResponse.setPrice(transValue(detailQuot.Quot.Price));
				enquireRtqResponse.setOpen(transValue(detailQuot.Quot.Open));
				enquireRtqResponse.setHigh(transValue(detailQuot.Quot.High));
				enquireRtqResponse.setLow(transValue(detailQuot.Quot.Low));
				enquireRtqResponse.setClose(transValue(detailQuot.Quot.Close));
				enquireRtqResponse.setTurnover(transValue(detailQuot.Quot.Turnover));
				enquireRtqResponse.setChange(transValue(detailQuot.Quot.Change));
				enquireRtqResponse.setPctChange(transValue(detailQuot.Quot.PctChange));
				enquireRtqResponse.setVolume(transValue(detailQuot.Quot.Volume));
				enquireRtqResponse.setYearHigh(transValue(detailQuot.Quot.YearHigh));
				enquireRtqResponse.setYearLow(transValue(detailQuot.Quot.YearLow));
				enquireRtqResponse.setPe(transValue(detailQuot.Quot.Pe));
				enquireRtqResponse.setMarketCap(transValue(detailQuot.Quot.MarketCap));
				enquireRtqResponse.setLowSpread(transValue(detailQuot.Quot.LowSpread));
				enquireRtqResponse.setHighSpread(transValue(detailQuot.Quot.HighSpread));
				enquireRtqResponse.setLotSize(transValue(detailQuot.Quot.LotSize));
				enquireRtqResponse.setGearing(transValue(detailQuot.Quot.Gearing));
				enquireRtqResponse.setExpDate(transValue(detailQuot.Quot.ExpDate));
				enquireRtqResponse.setExePrice(transValue(detailQuot.Quot.ExePrice));
				enquireRtqResponse.setConvRatio(transValue(detailQuot.Quot.ConvRatio));
				enquireRtqResponse.setPreminum(transValue(detailQuot.Quot.Preminum));
				enquireRtqResponse.setNote(transValue(detailQuot.Quot.Note));
				enquireRtqResponse.setYield(transValue(detailQuot.Quot.Yield));
				enquireRtqResponse.setCurrency(transValue(detailQuot.Quot.Currency));
				enquireRtqResponse.setBestBid(transValue(detailQuot.Quot.bestBid));
				enquireRtqResponse.setBestAsk(transValue(detailQuot.Quot.bestAsk));
				enquireRtqResponse.setCanBidBPrice(getFlag(detailQuot.Quot.bestBid));
				enquireRtqResponse.setCanAskBPrice(getFlag(detailQuot.Quot.bestAsk));
				enquireRtqResponse.setLatest_traded_time(detailQuot.Quot.latest_traded_time);
				enquireRtqResponse.setLatest_traded_vol(transValue(detailQuot.Quot.latest_traded_vol));
				enquireRtqResponse.setLatest_traded_price(transValue(detailQuot.Quot.latest_traded_price));
				enquireRtqResponse.setLogTime(detailQuot.Quot.LogTime);
				enquireRtqResponse.setLast_update_time(formatTime(detailQuot.Quot.Time));
				}
				enquireRtqResponse.setBody_type(transValue(detailQuot.Quot.body_type));
				enquireRtqResponse.setStatus(detailQuot.Quot.Status);
				enquireRtqResponses.add(enquireRtqResponse);
			}
		}
		return enquireRtqResponses;
	}
	
	public static List<EnquireShortRTQResponse> assembleShortRtqResponse(DetailQuot[] detailQuots){
		List<EnquireShortRTQResponse> enquireRtqResponses = new ArrayList<EnquireShortRTQResponse>();
		for (int i = 0; i < detailQuots.length; i++) {
			DetailQuot detailQuot = detailQuots[i];
			if(detailQuot != null){
				EnquireShortRTQResponse enquireRtqResponse = new EnquireShortRTQResponse();
				if(detailQuot.Quot.Status == 0 ||detailQuot.Quot.Status == 2){
					enquireRtqResponse.setCode(detailQuot.Quot.Code);
					enquireRtqResponse.setName(detailQuot.Quot.Name);
					enquireRtqResponse.setPrice(transValue(detailQuot.Quot.Price));
					enquireRtqResponse.setLotSize(transValue(detailQuot.Quot.LotSize));
					enquireRtqResponse.setBestBid(transValue(detailQuot.Quot.bestBid));
					enquireRtqResponse.setBestAsk(transValue(detailQuot.Quot.bestAsk));
					enquireRtqResponses.add(enquireRtqResponse);
				}
			}
		}
		return enquireRtqResponses;
	}
	
	public static List<EnquireDeptRTQResponse> assembleDeptRtqResponse(DetailQuot[] detailQuots){
		List<EnquireDeptRTQResponse> enquireRtqResponses = new ArrayList<EnquireDeptRTQResponse>();
		for (int i = 0; i < detailQuots.length; i++) {
			DetailQuot detailQuot = detailQuots[i];
			if(detailQuot != null){
				EnquireDeptRTQResponse enquireRtqResponse = new EnquireDeptRTQResponse();
				if(detailQuot.Quot.Status == 0 ||detailQuot.Quot.Status == 2){
					enquireRtqResponse.setCode(detailQuot.Quot.Code);
					enquireRtqResponse.setName(detailQuot.Quot.Name);
					enquireRtqResponse.setPrice(transValue(detailQuot.Quot.Price));
					enquireRtqResponse.setLotSize(transValue(detailQuot.Quot.LotSize));
					enquireRtqResponse.setBestBid0(transValue(detailQuot.Depth.BPRC[0]));
					enquireRtqResponse.setBestAsk0(transValue(detailQuot.Depth.APRC[0]));
					enquireRtqResponse.setBestBid1(transValue(detailQuot.Depth.BPRC[1]));
					enquireRtqResponse.setBestAsk1(transValue(detailQuot.Depth.APRC[1]));
					enquireRtqResponse.setBestBid2(transValue(detailQuot.Depth.BPRC[2]));
					enquireRtqResponse.setBestAsk2(transValue(detailQuot.Depth.APRC[2]));
					enquireRtqResponse.setBestBid3(transValue(detailQuot.Depth.BPRC[3]));
					enquireRtqResponse.setBestAsk3(transValue(detailQuot.Depth.APRC[3]));
					enquireRtqResponse.setBestBid4(transValue(detailQuot.Depth.BPRC[4]));
					enquireRtqResponse.setBestAsk4(transValue(detailQuot.Depth.APRC[4]));
					enquireRtqResponses.add(enquireRtqResponse);
				}
			}
		}
		return enquireRtqResponses;
	}
	
	public static List<EnquireRTQResponse> assembleEnquireRtqResponse(QuotRecord[] quotRecords){
		List<EnquireRTQResponse> enquireRtqResponses = new ArrayList<EnquireRTQResponse>();
		for (int i = 0; i < quotRecords.length; i++) {
			QuotRecord quotRecord = quotRecords[i];
			if(quotRecord != null){
				EnquireRTQResponse enquireRtqResponse = new EnquireRTQResponse();
				if(quotRecord.Status == 0 ||quotRecord.Status == 2){
				enquireRtqResponse.setCode(quotRecord.Code);
				enquireRtqResponse.setName(quotRecord.Name);
				enquireRtqResponse.setTime(formatTime2(quotRecord.Time));
				enquireRtqResponse.setPClose(transValue(quotRecord.PClose));
				enquireRtqResponse.setPrice(transValue(quotRecord.Price));
				enquireRtqResponse.setOpen(transValue(quotRecord.Open));
				enquireRtqResponse.setHigh(transValue(quotRecord.High));
				enquireRtqResponse.setLow(transValue(quotRecord.Low));
				enquireRtqResponse.setClose(transValue(quotRecord.Close));
				enquireRtqResponse.setTurnover(transValue(quotRecord.Turnover));
				enquireRtqResponse.setChange(transValue(quotRecord.Change));
				enquireRtqResponse.setPctChange(transValue(quotRecord.PctChange));
				enquireRtqResponse.setVolume(transValue(quotRecord.Volume));
				enquireRtqResponse.setYearHigh(transValue(quotRecord.YearHigh));
				enquireRtqResponse.setYearLow(transValue(quotRecord.YearLow));
				enquireRtqResponse.setPe(transValue(quotRecord.Pe));
				enquireRtqResponse.setMarketCap(transValue(quotRecord.MarketCap));
				enquireRtqResponse.setLowSpread(transValue(quotRecord.LowSpread));
				enquireRtqResponse.setHighSpread(transValue(quotRecord.HighSpread));
				enquireRtqResponse.setLotSize(transValue(quotRecord.LotSize));
				enquireRtqResponse.setGearing(transValue(quotRecord.Gearing));
				enquireRtqResponse.setExpDate(transValue(quotRecord.ExpDate));
				enquireRtqResponse.setExePrice(transValue(quotRecord.ExePrice));
				enquireRtqResponse.setConvRatio(transValue(quotRecord.ConvRatio));
				enquireRtqResponse.setPreminum(transValue(quotRecord.Preminum));
				enquireRtqResponse.setNote(transValue(quotRecord.Note));
				enquireRtqResponse.setYield(transValue(quotRecord.Yield));
				enquireRtqResponse.setCurrency(transValue(quotRecord.Currency));
				enquireRtqResponse.setBestBid(transValue(quotRecord.bestBid));
				enquireRtqResponse.setBestAsk(transValue(quotRecord.bestAsk));
				enquireRtqResponse.setCanBidBPrice(getFlag(quotRecord.bestBid));
				enquireRtqResponse.setCanAskBPrice(getFlag(quotRecord.bestAsk));
				enquireRtqResponse.setLatest_traded_time(quotRecord.latest_traded_time);
				enquireRtqResponse.setLatest_traded_vol(transValue(quotRecord.latest_traded_vol));
				enquireRtqResponse.setLatest_traded_price(transValue(quotRecord.latest_traded_price));
				enquireRtqResponse.setLogTime(quotRecord.LogTime);
				enquireRtqResponse.setLast_update_time(formatTime(quotRecord.Time));
				}
				enquireRtqResponse.setBody_type(transValue(quotRecord.body_type));
				enquireRtqResponse.setStatus(quotRecord.Status);
				enquireRtqResponses.add(enquireRtqResponse);
			}
		}
		return enquireRtqResponses;
	}
	
	private static String transValue(String price){
		try {
			if(StringUtils.isBlank(price)){
				return Consts.Global.Flag.RTQ_NULL_PRICE;
			}
		} catch (Exception e) {
			return Consts.Global.Flag.RTQ_NULL_PRICE;
		}
		return price;
	}
	
	private static String getFlag(String price){
		try {
			if(Float.parseFloat(price)>0){
				return Consts.Global.Flag.POSITIVE;
			}
		} catch (Exception e) {
			return Consts.Global.Flag.NEGATIVE;
		}
		return Consts.Global.Flag.NEGATIVE;
	}
	
	private static String formatTime(String time){
		long timestamp = Long.parseLong(time);
	    if(timestamp == 0){
		   return "0";
	    }
        Date updatetime = new Date(timestamp);
        String pattern = "yyyy-MM-dd HH:mm:ss";
		SimpleDateFormat dateFormat=new SimpleDateFormat(pattern);
		String timeString = dateFormat.format(updatetime);
		return timeString;
	}
	
	private static String formatTime2(String time){
		long timestamp = Long.parseLong(time);
	    if(timestamp == 0){
		   return "0";
	    }
        Date updatetime = new Date(timestamp);
        String pattern = "yyyy-MM-dd HH:mm";
		SimpleDateFormat dateFormat=new SimpleDateFormat(pattern);
		String timeString = dateFormat.format(updatetime);
		return timeString;
	}
	
	/**
	 * 
	 * @Author:Cimenon Saint
	 * @Time:2007-5-30 17:29:07
	 * @param lotSizeRequestModel
	 * @return
	 */
	public static QuotHeader assembleHeader(LotSizeRequestModel lotSizeRequest){
		QuotHeader header = null;
		if(lotSizeRequest != null){
			header = new QuotHeader();
			header.Host = lotSizeRequest.getChannelType();
			header.SEQN = lotSizeRequest.getSeqn();
			header.Lang = lotSizeRequest.getLanguage();
			header.Type = lotSizeRequest.getType();
			header.SubType = lotSizeRequest.getSubType();
			header.CustomerID = lotSizeRequest.getCustomerId();
			header.Code = lotSizeRequest.getCode();
			header.DelayTime = lotSizeRequest.getDelayTime();
		}
		return header;
	}
	
	/**
	 * 
	 * @Author:Cimenon Saint
	 * @Time:2007-5-30 13:58:35
	 * @param calOrderFee
	 * @return
	 */
	public static OrderFeeResponseModel assembleOrderFeeResponse(CalOrderFeeResponse calOrderFee){
		OrderFeeResponseModel orderFeeResponse = null;
		if(calOrderFee != null){
			orderFeeResponse = new OrderFeeResponseModel();
			try {
				PropertyUtils.copyProperties(orderFeeResponse , calOrderFee);
			} catch (Exception e) {
				commonInfo.error(e);
			} 
		}
		return orderFeeResponse;
	}
	
	/**
	 * 
	 *  calOrderFeeResquest = new CalOrderFeeRequest();
        calOrderFeeResquest.setVersionId(versionID);
        calOrderFeeResquest.setMessageId(messageID);
        calOrderFeeResquest.setMessageSEQNum(Long.decode(messageSEQNum));
        calOrderFeeResquest.setChannelType(channelType);
        calOrderFeeResquest.setChannelId(channelID);
        calOrderFeeResquest.setAccountId(AccountID);
        calOrderFeeResquest.setMarketCode(MarketCode);
        calOrderFeeResquest.setInstrCode(InstrCode);
        calOrderFeeResquest.setOrderQuantity(OrderQuantity);
        calOrderFeeResquest.setOrderPrice(OrderPrice);
        calOrderFeeResquest.setOrderSide(OrderSide);
        return ms.sendRequest(calOrderFeeResquest, lang);
	 * @Author:Cimenon Saint
	 * @Time:2007-5-30 12:15:24
	 * @param orderFeeReq
	 * @return
	 */
	public static CalOrderFeeRequest assembleCalOrderFeeRequest(OrderFeeRequestModel orderFeeReq){
		CalOrderFeeRequest calOrderFeeReq = null;
		if(orderFeeReq != null){
			calOrderFeeReq = new CalOrderFeeRequest();
			calOrderFeeReq.setVersionId(Consts.Mcs.VersionId.VER100);
			//calOrderFeeReq.setVersionId("ver1_00");
			calOrderFeeReq.setMessageId(Consts.Mcs.MsgId.CalOrderFee);
			calOrderFeeReq.setMessageSEQNum(Long.decode(Consts.Global.Number.SONE));
	        calOrderFeeReq.setChannelType(orderFeeReq.getChannelType());
	        calOrderFeeReq.setChannelId(orderFeeReq.getChannelId());
	        calOrderFeeReq.setAccountId(orderFeeReq.getAccountId());
	        calOrderFeeReq.setMarketCode(Consts.Mcs.MarketCode.AMS3);
	        calOrderFeeReq.setInstrCode(orderFeeReq.getInstrCode());
	        //calOrderFeeReq.setInstrCode("0041");
	        calOrderFeeReq.setOrderQuantity(orderFeeReq.getOrderQuantity());
	        calOrderFeeReq.setOrderPrice(orderFeeReq.getOrderPrice());
	        calOrderFeeReq.setOrderSide(orderFeeReq.getOrderSide());
		}
		return calOrderFeeReq;
	}
	
	public static OrderFeeRequestModel assembleOrderFeeRequest(VerifyPlaceOrderRequestModel orderModel){
		OrderFeeRequestModel orderFeeRequest = new OrderFeeRequestModel();
		try {
			PropertyUtils.copyProperties(orderFeeRequest , orderModel);
		} catch (Exception e) {
//			e.printStackTrace();
			commonInfo.error("exception exist:"+e.getMessage());
		}
		orderFeeRequest.setVersionId(Consts.Mcs.VersionId.VER100);
		orderFeeRequest.setMarketCode(Consts.Mcs.MarketCode.AMS3);
		orderFeeRequest.setCustomerId(orderModel.getCustCode());
		if(Consts.Channel.WMT.equals(orderModel.getChannelType())){
			orderFeeRequest.setIsNeedCallMos(Consts.Global.Flag.NEGATIVE);
		}else orderFeeRequest.setIsNeedCallMos(Consts.Global.Flag.POSITIVE);
		return orderFeeRequest;
	}

	public static OrderListResponse assembleOrderInfo(OrderInfo orderInfo){
		OrderListResponse infoModel = null;
		if(orderInfo != null){
			infoModel = new OrderListResponse();
			try {
				PropertyUtils.copyProperties(infoModel , orderInfo);
//				infoModel.setChannelType(orderInfo.getChannelType());
//				infoModel.setOrderRemark(OrderStateConvertUtil.getAbbrRemark(orderInfo.getOrderRemark()));
//				long ultimateQuantity = getUltimateQuantity(infoModel.getInitialQuantity(),infoModel.getChangedQty());
//				infoModel.setUltimateQuantity(new BigDecimal(ultimateQuantity));
//				infoModel.setCondition(getCondition(infoModel.getConditionType(),infoModel.getTriggerPrice()));
			} catch (Exception e) {
				commonInfo.error(e);
			} 

		}
		return infoModel;
	}
	
	public static String getCondition(String conditionType,BigDecimal triggerPrice){
		if(Consts.Order.ConditionType.GTE.equals(conditionType)){
			if(triggerPrice!=null && triggerPrice.floatValue()>0){
				return "&gt="+PortalUtil.hold3Decimal(triggerPrice);
			}else{
				return Consts.Global.Flag.NOTAPPLICABLE;
			}
		}
		if(Consts.Order.ConditionType.LTE.equals(conditionType)){
			if(triggerPrice!=null && triggerPrice.floatValue()>0){
				return "&lt="+PortalUtil.hold2Decimal(triggerPrice);
			}else{
				return Consts.Global.Flag.NOTAPPLICABLE;
			}
		}
		if(StringUtils.isBlank(conditionType)){
			return Consts.Global.Flag.NOTAPPLICABLE;
		}
		return Consts.Global.Flag.NOTAPPLICABLE;
	}
	
	public static long getUltimateQuantity(BigDecimal initialQuantity,BigDecimal changedQty) {
		long ultimateQuantity=0;
		if(initialQuantity!=null){
			ultimateQuantity+=initialQuantity.longValue();
		}
		if(changedQty!=null){
			ultimateQuantity+=changedQty.longValue();
		}
		return ultimateQuantity;
	}
	
	public static OrderListRequest assembleOrderListRequest(OrderDetailRequestModel orderDetail){
		OrderListRequest orderListRequest = null;
		if(orderDetail != null){
			orderListRequest = new OrderListRequest();
			orderListRequest.setVersionId(Consts.Mcs.VersionId.VER100);
			orderListRequest.setMessageId(Consts.Mcs.MsgId.OrderList);
			orderListRequest.setMessageSEQNum(Long.decode(Consts.Global.Number.SONE));
			orderListRequest.setChannelType(orderDetail.getChannelType());
			orderListRequest.setChannelId(orderDetail.getChannelId());
			orderListRequest.setAccountId(orderDetail.getAccountId());
			orderListRequest.setMcsOrderId(orderDetail.getMcsOrderId());
			orderListRequest.setOrderId(orderDetail.getOrderId());
			orderListRequest.setLanguage(orderDetail.getLanguage());
			orderListRequest.setSupportOverNightFlag(orderDetail.getSupportOverNightFlag());
			orderListRequest.setFromIdx(null);
			orderListRequest.setToIdx(null);
		}
		return orderListRequest;
	}
	
	public static OrderFilteringRequest assembleOrderFilteringRequest(FilterOrderRequestModel filterOrderModel){
		OrderFilteringRequest orderFiltering = new OrderFilteringRequest();
		if(filterOrderModel != null){
			setFromToIndex(filterOrderModel);
			try {
				PropertyUtils.copyProperties(orderFiltering, filterOrderModel);
			} catch (Exception e) {			
			}
			orderFiltering.setVersionId(Consts.Mcs.VersionId.VER100);
			orderFiltering.setMessageId(Consts.Mcs.MsgId.OrderFilter);
			orderFiltering.setMessageSEQNum(Long.decode(Consts.Global.Number.SONE));
			orderFiltering.setChannelType(filterOrderModel.getChannelType());
			orderFiltering.setChannelId(filterOrderModel.getChannelId());
			orderFiltering.setLanguage(filterOrderModel.getLanguage());
			orderFiltering.setAccountId(filterOrderModel.getAccountId());
			orderFiltering.setInstrCode(filterOrderModel.getInstrCode());
			if (StringUtils.isNotBlank(filterOrderModel.getOrderStateType())){
				orderFiltering.setOrderStateGroup(filterOrderModel.getOrderStateType());
			}
		}
		return orderFiltering;
	}
	
	public static void setFromToIndex(FilterOrderRequestModel filterOrderModel){
		if(StringUtils.isNotBlank(filterOrderModel.getPageNo())&&StringUtils.isNotBlank(filterOrderModel.getPageSize())){
			int pageSize = Integer.parseInt(filterOrderModel.getPageSize());
			int pageNo = Integer.parseInt(filterOrderModel.getPageNo());
			int fromIdx = (pageNo-1)*pageSize;
			if(fromIdx<=0){
				fromIdx=0;
			}
			int toIdx = pageNo*pageSize;
			filterOrderModel.setFromIdx(fromIdx);
			filterOrderModel.setToIdx(toIdx);
		}
	}
	
	public static OrderFilteringRequest assembleOrderListingRequest(ListOrderRequestModel listOrderModel){
		OrderFilteringRequest orderFiltering = null;
		if(listOrderModel != null){
			orderFiltering = new OrderFilteringRequest();
			orderFiltering.setVersionId(Consts.Mcs.VersionId.VER100);
			orderFiltering.setMessageId(Consts.Mcs.MsgId.OrderFilter);
			orderFiltering.setMessageSEQNum(Long.decode(Consts.Global.Number.SONE));
			orderFiltering.setChannelType(listOrderModel.getChannelType());
			orderFiltering.setChannelId(listOrderModel.getChannelId());
			orderFiltering.setLanguage(listOrderModel.getLanguage());
			orderFiltering.setAccountId(listOrderModel.getAccountId());
			//
			int pageNo = Integer.parseInt(listOrderModel.getPageNo());
			if(pageNo<=0) pageNo=1;
			int pageSize = Integer.parseInt(listOrderModel.getPageSize());
			orderFiltering.setFromIdx((pageNo-1)*pageSize);
			orderFiltering.setToIdx((pageNo-1)*pageSize + pageSize);
			if (StringUtils.isNotBlank(listOrderModel.getOrderStateType())){
				orderFiltering.setOrderStateGroup(listOrderModel.getOrderStateType());
			}
		}
		return orderFiltering;
	}
	
	public static OrderFilteringRequest assembleOrderListingRequest1(ListOrderRequestModel listOrderModel){
		OrderFilteringRequest orderFiltering = null;
		if(listOrderModel != null){
			orderFiltering = new OrderFilteringRequest();
			orderFiltering.setVersionId(Consts.Mcs.VersionId.VER100);
			orderFiltering.setMessageId(Consts.Mcs.MsgId.OrderFilter);
			orderFiltering.setMessageSEQNum(Long.decode(Consts.Global.Number.SONE));
			orderFiltering.setChannelType(listOrderModel.getChannelType());
			orderFiltering.setChannelId(listOrderModel.getChannelId());
			orderFiltering.setLanguage(listOrderModel.getLanguage());
			orderFiltering.setAccountId(listOrderModel.getAccountId());
			if (StringUtils.isNotBlank(listOrderModel.getOrderStateType())){
				orderFiltering.setOrderStateGroup(listOrderModel.getOrderStateType());
			}
		}
		return orderFiltering;
	}
	
	/**
	 * 
	 * @Author:Cimenon Saint
	 * @Time:13:52:20
	 * @param mosRequestModel
	 * @return
	 */
	public static CalMOSRequest assembleCalMOSRequest(MosRequestModel mosRequestModel){
		CalMOSRequest calMOSRequest = null;
		if(mosRequestModel != null){
			calMOSRequest = new CalMOSRequest();
			calMOSRequest.setChannelId(mosRequestModel.getChannelId());
			calMOSRequest.setChannelType(mosRequestModel.getChannelType());
			calMOSRequest.setVersionId(Consts.Mcs.VersionId.VER100);
	        calMOSRequest.setMessageId(Consts.Mcs.MsgId.CalMos);
	        calMOSRequest.setMessageSEQNum(Long.decode(Consts.Global.Number.SONE));
	        calMOSRequest.setAccountId(mosRequestModel.getAccountId());
	        calMOSRequest.setMarketCode(Consts.Mcs.MarketCode.AMS3);
			if(StringUtils.isNotBlank(mosRequestModel.getInstrCode())){
				calMOSRequest.setInstrCode(mosRequestModel.getInstrCode());
			}
		}
		return calMOSRequest;
	}
	
	/**
	 * 
	 * @Author:Cimenon Saint
	 * @Time:18:34:38
	 * @param filterOrderModel
	 * @return
	 */
	public static MosRequestModel assembleMosRequestModel(FilterOrderRequestModel filterOrderModel){
		MosRequestModel mosModel = null;
		//:::需要满足MosRequestModel中字段内容
		if(filterOrderModel != null){
			mosModel = new MosRequestModel();
			try {
				PropertyUtils.copyProperties(mosModel , filterOrderModel);
			} catch (Exception e) {
				commonInfo.error(e);
			} 
		}
		return mosModel;
	}
	
	/*
	 * 
	 */
	public static MosRequestModel assembleMosRequestModel(ListOrderRequestModel filterOrderModel){
		MosRequestModel mosModel = null;
		//:::需要满足MosRequestModel中字段内容
		if(filterOrderModel != null){
			mosModel = new MosRequestModel();
			try {
				PropertyUtils.copyProperties(mosModel , filterOrderModel);
			} catch (Exception e) {
				commonInfo.error(e);
			} 
		}
		return mosModel;
	}
	
	/**
	 * 
	 * @Author:Cimenon Saint
	 * @Time:2007-6-1 16:44:26
	 * @param orderFeeRequest
	 * @return
	 */
	public static MosRequestModel assembleMosRequestModel(OrderFeeRequestModel orderFeeRequest){
		MosRequestModel mosModel = null;
		//:::需要满足MosRequestModel中字段内容
		if(orderFeeRequest != null){
			mosModel = new MosRequestModel();
			try {
				PropertyUtils.copyProperties(mosModel , orderFeeRequest);
			} catch (Exception e) {
				commonInfo.error(e);
			} 
			
		}
		return mosModel;
	}
	
	/**
	 * 
	 * @Author:Cimenon Saint
	 * @Time:18:33:10
	 * @param loginResponse
	 * @return
	 */
	public static LoginResultModel assembleResultModel(LoginResponse loginResponse){
		LoginResultModel resultModel = null;
		if(loginResponse != null){
			resultModel = new LoginResultModel();
			try {
				PropertyUtils.copyProperties(resultModel , loginResponse);
			} catch (Exception e) {
				commonInfo.error(e);
			}
		}
		return resultModel;
	}

	public static Locale getLocale(String language) {
		String str = LangUtil.mcs2admin(language);
        Locale locale = Locale.TRADITIONAL_CHINESE;
        try {
			locale = LangUtil.lang2locale(str);
		} catch (Exception e1) {
		}
		return locale;
	}
	
	public static RtqRequestModel assembleRtqModel(String custCode , LoginRequestModel requestModel){
		RtqRequestModel rtqRequestModel = new RtqRequestModel();
		Locale locale = ModelUtil.getLocale(requestModel.getLanguage());
		//set streamrtq request object
		StreamRTQRequestModel stream = new StreamRTQRequestModel();
		stream.setCustCode(custCode);
		stream.setRemoteAddr(requestModel.getRemoteAddr());
		stream.setLocale(locale);
		//set snapshot request object
		SnapshotRTQRequestModel snapshot = new SnapshotRTQRequestModel();
		snapshot.setCustCode(custCode);
		snapshot.setLocale(locale);
		rtqRequestModel.setStreamRTQRequestModel(stream);
		rtqRequestModel.setSnapshotRTQRequestModel(snapshot);
		return rtqRequestModel;
	}

	public static RtqRequestModel assembleRtqModel(String custCode , ECertLoginRequestModel requestModel){
		RtqRequestModel rtqRequestModel = new RtqRequestModel();
		Locale locale = ModelUtil.getLocale(requestModel.getLanguage());
		//set streamrtq request object
		StreamRTQRequestModel stream = new StreamRTQRequestModel();
		stream.setCustCode(custCode);
		stream.setRemoteAddr(requestModel.getRemoteAddr());
		stream.setLocale(locale);
		//set snapshot request object
		SnapshotRTQRequestModel snapshot = new SnapshotRTQRequestModel();
		snapshot.setCustCode(custCode);
		snapshot.setLocale(locale);
		rtqRequestModel.setStreamRTQRequestModel(stream);
		rtqRequestModel.setSnapshotRTQRequestModel(snapshot);
		return rtqRequestModel;
	}
	
	/**
	 * 
	 * @Author:Cimenon Saint
	 * @Time:18:29:54
	 * @param loginResponse
	 * @return
	 */
	public static LoginModel assembleLoginModel(LoginResponse loginResponse){
		LoginModel model = null;
		if(loginResponse != null){
			model = new LoginModel();
			try {
				PropertyUtils.copyProperties(model , loginResponse);
				model.setLoginStatus(loginResponse.getNewSubCode());
			} catch (Exception e) {
				commonInfo.error(e);
			}
		}
		return model;
	}
	/**
	 * 
	 *   loginRequstObj.setVersionId(versionID);
     *   loginRequstObj.setMessageId(messageID);
     *   loginRequstObj.setMessageSEQNum(Long.decode(messageSEQNum));
     *   loginRequstObj.setChannelType(channelType);
     *   loginRequstObj.setChannelId(channelID);
     *   loginRequstObj.setLoginId(loginID);
     *   loginRequstObj.setPassword(password);
     *   loginRequstObj.setLoginInfoFormat(loginInfoFormat);
     *   loginRequstObj.setRtqFlag(rtqFlag);
	 * 
	 * copy data from loginRequestModel to loginRequest(mcs)
	 * @Author:Cimenon Saint
	 * @Time:10:53:23
	 * @param requestModel
	 * @return
	 */
	public static LoginRequest assembleLoginRequest(LoginRequestModel requestModel){
		LoginRequest loginRequest = null;
		if(requestModel != null){
			loginRequest = new LoginRequest();
			try {
				PropertyUtils.copyProperties(loginRequest , requestModel);
			} catch (Exception e) {
				commonInfo.error(e);
			} 
			loginRequest.setVersionId(Consts.Mcs.VersionId.VER100);
			loginRequest.setChannelId(getChannelID(requestModel.getChannelType()));
			loginRequest.setRtqFlag(Consts.Global.Flag.NEGATIVE);
			loginRequest.setLoginInfoFormat(Consts.Mcs.LoginInfoFormat.FULL);
			loginRequest.setIpAddress(requestModel.getRemoteAddr());
			loginRequest.setMessageId(Consts.Mcs.MsgId.Login);
			loginRequest.setMessageSEQNum(Long.decode(Consts.Global.Number.SONE));
			//Encrypt password
			String pwd = loginRequest.getPassword();
			loginRequest.setPassword(EncryptPwd.Encrypt(pwd, ""));
		}
		return loginRequest;
	}
	
	private static String getChannelID(String channelType){
		if(Consts.Channel.WMT.equals(channelType)){
			return Consts.Channel.Id.WMT;
		}
		else if(Consts.Channel.WEB.equals(channelType)){
			return Consts.Channel.Id.WEB;
		}
		else if(Consts.Channel.H3G.equals(channelType)){
			return Consts.Channel.Id.H3G;
		}
		else if(Consts.Channel.STT.equals(channelType)){
			return Consts.Channel.Id.STT;
		}
		return Consts.Channel.Id.NWEB;
	}
	
	/**
	 * 
	 * 
	 * @param requestModel
	 * @return
	 */
	
	public static ModifyOrderRequest assembleModifyOrderRequest(ModifyOrderRequestModel requestModel,OrderInfo orderInfo){
		ModifyOrderRequest modifyOrderRequest = null;
		if(requestModel != null){
			modifyOrderRequest = new ModifyOrderRequest();
			try {
				PropertyUtils.copyProperties(modifyOrderRequest , requestModel);
			} catch (Exception e) {
				commonInfo.error(e);
			}
			modifyOrderRequest.setExpiryDate(new Timestamp(System.currentTimeMillis()));
			modifyOrderRequest.setMessageId(Consts.Mcs.MsgId.ModifyOrder);
			modifyOrderRequest.setMessageSEQNum(Long.decode(Consts.Global.Number.SONE));					
			modifyOrderRequest.setNewOrderType(orderInfo.getOrderType());	
			modifyOrderRequest.setMarketCode(Consts.Mcs.MarketCode.AMS3);
			modifyOrderRequest.setInstrCode(orderInfo.getInstrCode());
			modifyOrderRequest.setExpiryDate(orderInfo.getExpiryDate());
			//Encrypt password
			String pwd = requestModel.getPassword();
			if(StringUtils.isNotBlank(pwd)){
				modifyOrderRequest.setPasswd(EncryptPwd.Encrypt(pwd, ""));
			}
			modifyOrderRequest.setMtssOrderID(orderInfo.getOrderId());
			Integer delta = calDelta(modifyOrderRequest.getNewOrderQty() , orderInfo);
			modifyOrderRequest.setNewOrderQuantityDelta(new BigDecimal(delta.doubleValue()));
		}
		return modifyOrderRequest;
	}
	
	
	/**
	 * 
	 * @Author:Cimenon Saint
	 * @Time:2007-6-6 18:57:16
	 * @param newOrderQty
	 * @param orderInfo
	 * @return
	 */
	private static Integer calDelta(BigDecimal newOrderQty , OrderInfo orderInfo){
		Integer delta = null;
		BigDecimal initialQuantity = orderInfo.getInitialQuantity();
		BigDecimal changedQty = orderInfo.getChangedQty();
		if (changedQty == null) {
			changedQty = new BigDecimal(0);
		}
		delta = McsUtil.getDelta(initialQuantity , newOrderQty , changedQty);
		return delta;
	}

	public static BulkCancelOrderResponseModel assembleBulkCancelOrderInfo(BulkCancelOrderResponse bcOrderRes){
		BulkCancelOrderResponseModel cancelOrderResp = new BulkCancelOrderResponseModel();
		try{
			cancelOrderResp.setOrderIdInfos(bcOrderRes.getBulkCancelOrderInfo());
			cancelOrderResp.setListSize(String.valueOf(bcOrderRes.getListSize()));
//			PropertyUtils.copyProperties(cancelOrderResp, bcOrderRes);	
		}catch(Exception ex){
			commonInfo.error(ex);
		}
		return cancelOrderResp;
	}
	
	public static CancelOrderResponseModel assembleCancelOrderInfo(CancelOrderResponse bcOrderRes){
		CancelOrderResponseModel cancelOrderResp = new CancelOrderResponseModel();
		try{
			PropertyUtils.copyProperties(cancelOrderResp, bcOrderRes);
			cancelOrderResp.setOrderStatus(bcOrderRes.getOrderStatus());
		}catch(Exception ex){
			commonInfo.error(ex);
		}
		return cancelOrderResp;
	}

	
	public static RtqRequestModel assembleRtqModel(String custCode,	String Language, String RemoteAddr) {
		RtqRequestModel rtqRequestModel = new RtqRequestModel();
		Locale locale = ModelUtil.getLocale(Language);
		// set streamrtq request object
		StreamRTQRequestModel stream = new StreamRTQRequestModel();
		stream.setCustCode(custCode);
		stream.setRemoteAddr(RemoteAddr);
		stream.setLocale(locale);
		// set snapshot request object
		SnapshotRTQRequestModel snapshot = new SnapshotRTQRequestModel();
		snapshot.setCustCode(custCode);
		snapshot.setLocale(locale);
		rtqRequestModel.setStreamRTQRequestModel(stream);
		rtqRequestModel.setSnapshotRTQRequestModel(snapshot);
		return rtqRequestModel;
	}
	/** *******IDP and SSO***************************** */	
	

	public static Vector assembleStockCode(EnquireRTQRequestModel rtqRequest) {
		Vector vector = rtqRequest.getCode();
		Vector newCode = new Vector();
		Iterator iterator = vector.iterator();
		while(iterator.hasNext()) {
			String code = (String) iterator.next();
			if (StringUtils.isNumeric(code)) {
				newCode.add(FormatUtil.formatInstrCode(code));
			} else {
				newCode.add(code);
			}
		}
		return newCode;
	}
	
	public static Vector assembleStockCode(Vector instrcode) {
		Vector vector = instrcode;
		Vector newCode = new Vector();
		Iterator iterator = vector.iterator();
		while(iterator.hasNext()) {
			String code = (String) iterator.next();
			if (StringUtils.isNumeric(code)) {
				newCode.add(FormatUtil.formatInstrCode(code));
			} else {
				newCode.add(code);
			}
		}
		return newCode;
	}
	
	public static EnquireRTQRequestModel fillRTQRequestValue(EnquireRTQRequestModel rtqRequest,String quoteType){
		if(Constants.REALTIME_QUOTATION.equalsIgnoreCase(quoteType)){
			rtqRequest.setSeqn(Consts.Qs.SEQN);
			rtqRequest.setServiceType(Consts.Qs.ST_STOCK_MONTH);
			rtqRequest.setSubType(Consts.Qs.S_TYPE_DETAIL_QUOT);
			rtqRequest.setType(Consts.Qs.QUOT_TYPE);
		}else{
			rtqRequest.setDelayTime(String.valueOf(Consts.Qs.DELAY_TIME));
			rtqRequest.setSeqn(Consts.Qs.SEQN);
			rtqRequest.setServiceType(Consts.Qs.ST_STOCK_MONTH);
			//rtqRequest.setSubType(Consts.Qs.FREE_QUOT_REAL_TIME);
			//rtqRequest.setType(Consts.Qs.ENQ_TYPE);	
			rtqRequest.setSubType(Consts.Qs.S_TYPE_DETAIL_QUOT_DELAY);
			rtqRequest.setType(Consts.Qs.QUOT_TYPE);
		}
		return rtqRequest;
	}
	
	public static ChangePasswordRequest assembleChangePassRequest(ChangePwdRequestModel requestModel){
		ChangePasswordRequest request = new ChangePasswordRequest();
		try {
			PropertyUtils.copyProperties(request, requestModel);
			request.setMessageId(Consts.Mcs.MsgId.ModifyPassword);
			request.setMessageSEQNum(Long.decode(Consts.Global.Number.SONE));	
		} catch (Exception e) {
			commonInfo.error(e);
		} 
		request.setPassword(requestModel.getOldPassword());
		request.setNewPassword(requestModel.getNewPassword());
		return request;
	}
	
	public static TermsAndConditionRequest assembleChangeTAndCRequest(ChangeTAndCRequestModel requestModel){
		TermsAndConditionRequest request = new TermsAndConditionRequest();
		try {
			PropertyUtils.copyProperties(request, requestModel);
			request.setMessageId(Consts.Mcs.MsgId.ChangeTAndC);
			request.setMessageSEQNum(Long.decode(Consts.Global.Number.SONE));	
		} catch (Exception e) {
			commonInfo.error(e);
		} 
		request.setLoginId(requestModel.getLoginId());
		request.setNewTermsAndConditions(requestModel.getNewTermsAndConditions());
		return request;
	}
	
	public static ChangePwdResponseModel assembleChangePassResponse(ChangePasswordResponse response){
		ChangePwdResponseModel responseModel = new ChangePwdResponseModel();
		try {
			PropertyUtils.copyProperties(responseModel, response);
		} catch (Exception e) {
			commonInfo.error(e);
		} 
		responseModel.setChangePasswordStatus(response.getChangePasswordStatus());
		responseModel.setChangePasswordResponse(response);
		return responseModel;
	}
	
	public static ChangeTAndCResponseModel assembleChangeTAndCResponse(TermsAndConditionResponse response){
		ChangeTAndCResponseModel responseModel = new ChangeTAndCResponseModel();
		try {
			PropertyUtils.copyProperties(responseModel, response);
		} catch (Exception e) {
			commonInfo.error(e);
		} 
		responseModel.setTAndCUpdate(response.getChangeAndCStatus());
		return responseModel;
	}
	
	public static TransactionProtectionRequest assembleSetTransPwdPtdRequest(TransactionProtectionRequestModel requestModel){
		TransactionProtectionRequest request = new TransactionProtectionRequest();
		try {
			PropertyUtils.copyProperties(request, requestModel);
			request.setMessageId(Consts.Mcs.MsgId.TransactionProtection);
			request.setMessageSEQNum(Long.decode(Consts.Global.Number.SONE));	
		} catch (Exception e) {
			commonInfo.error(e);
		}
		return request;
	}
	
	public static TransactionProtectionResponseModel assembleSetTransPwdPtdResponse(TransactionProtectionResponse response,TransactionProtectionRequestModel ptnRequest){
		TransactionProtectionResponseModel responseModel = new TransactionProtectionResponseModel();
		try {
			PropertyUtils.copyProperties(responseModel, response);
			PropertyUtils.copyProperties(responseModel, ptnRequest);
		} catch (Exception e) {
			commonInfo.error(e);
		} 
		return responseModel;
	}
	
	private static boolean isMarginOnline(String accountType,String online) {
		return (Consts.Mcs.AccountType.MARGIN_OPTION.indexOf(accountType))>=0 && 
				(Consts.Mcs.AccountType.MARGIN_ONLINE.indexOf(online))>=0;
	}
	
	public static EnquiryPositionResponseModel assembleEnquiryStockPositionResponse(MTSSShareHoldingResponse response,EnquiryPositionRequestModel request){
		EnquiryPositionResponseModel responseModel = new EnquiryPositionResponseModel();
		try {
			PropertyUtils.copyProperties(responseModel, request);
			responseModel.setAllowModify(getAllowModifyFlag(request,response.getAccountId()));
			responseModel.setMtsSShareHoldingResponse(response);			
		} catch (Exception e) {
			commonInfo.error(e);
		}
		return responseModel;
	}
	
	private static String getAllowModifyFlag(EnquiryPositionRequestModel request,String stockAccountId){
	    if(stockAccountId.equals(request.getDefaultAccountId())){
	    	if(Consts.Global.Flag.POSITIVE.equals(request.getAllowTradeStatusFlag()))
	    	    return Consts.Global.Flag.POSITIVE;
	    }
		return 	Consts.Global.Flag.NEGATIVE;
	}
	
	public static MISAccountListResponseModel assambleEnquireMisAccountList(MISAccountListResponse response){
		MISAccountListResponseModel responseModel = new MISAccountListResponseModel();
		MISAccountListResponse misAccountListResponse = new MISAccountListResponse();
		Collection<AccountListDetail> newAccountListDetails = new ArrayList<AccountListDetail>();
		Collection<AccountListDetail> accountListDetails = response.getAccountListDetail();
        for (Iterator iterator = accountListDetails.iterator(); iterator.hasNext();) {
        	AccountListDetail detail = (AccountListDetail) iterator.next();        	
        	if(detail.getAccountType()!=null && Consts.Mcs.AccountType.SECURITIES_OPTION.indexOf(detail.getAccountType())>=0){
        		AccountListDetail newDetail = new AccountListDetail();
        		newDetail.setAccountId(detail.getAccountId());
        		newDetail.setAccountStatus(detail.getAccountStatus());
        		newDetail.setAccountType(detail.getAccountType());
        		newDetail.setOnline(detail.getOnline());
        		newAccountListDetails.add(newDetail);
        	}
		}
        misAccountListResponse.setAccountListDetail(newAccountListDetails);
        responseModel.setMisAccountListResponse(misAccountListResponse);
		return responseModel;
	}
	
    public static OrderListRequest assembleOrderInfo(ModifyOrderRequestModel modRequest){
        OrderListRequest requestModel = new OrderListRequest();
        try{
            BeanUtils.copyProperties(requestModel, modRequest);
            requestModel.setVersionId(Consts.Mcs.VersionId.VER100);
            requestModel.setMessageId(Consts.Mcs.MsgId.OrderList);
            requestModel.setMessageSEQNum(Long.decode(Consts.Global.Number.SONE));
            if (modRequest.getMcsOrderId() != null) {
                requestModel.setMcsOrderId(modRequest.getMcsOrderId());
            }   
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return requestModel;
    }
	
	public static TradeListRequest assembleTradeListRequest(TradeListRequestModel requestModel){
		TradeListRequest request = new TradeListRequest();
		try {
			PropertyUtils.copyProperties(request, requestModel);
			List accountList = new ArrayList();
			String accountIds[] = StringUtils.split(requestModel.getAccountId(), Consts.Global.Common.FORM_PARAM_SEPARATOR_CHAR);
			if(accountIds.length>0){
				for (int i = 0; i < accountIds.length; i++) {
					accountList.add(new Account(accountIds[i]));
				}
			}
			request.setAccountList(accountList);
			request.setMessageId(Consts.Mcs.MsgId.TradeList);
			request.setMessageSEQNum(Long.decode(Consts.Global.Number.SONE));
			request.setVersionId(Consts.Mcs.VersionId.VER100);
			if(requestModel.getPageNo()==null){
				return request;
			}
			int pageNo = Integer.parseInt(requestModel.getPageNo());
			if(pageNo<=0) pageNo=1;
			int pageSize = Integer.parseInt(requestModel.getPageSize());
			request.setFromIdx(String.valueOf((pageNo-1)*pageSize));
			request.setToIdx(String.valueOf((pageNo-1)*pageSize + pageSize));
		} catch (Exception e) {
			commonInfo.error(e);
		} 
		return request;
	}
	public static TradeListResponseModel assembleTradeListResponse(TradeListResponse requestModel){
		TradeListResponseModel response = new TradeListResponseModel();
		try {
			response.setListSize(requestModel.getListSize()!=null?Integer.parseInt(requestModel.getListSize()):0);
			response.setResponse(requestModel);
		} catch (Exception e) {
			commonInfo.error(e);
		} 
		return response;
	}
	
	public static FundTransferRequest assembleFundTransferRequest(FundTransferRequestModel requestModel){
		FundTransferRequest request = new FundTransferRequest();
		try {
			PropertyUtils.copyProperties(request, requestModel);
			request.setMessageId(Consts.Mcs.MsgId.fundTransfer);
			request.setMessageSEQNum(Long.decode(Consts.Global.Number.SONE));
			request.setVersionId(Consts.Mcs.VersionId.VER100);
		} catch (Exception e) {
			commonInfo.error(e);
		} 
		return request;
	}
	
	public static WithDrawRequest assembleWithDrawRequest(FundTransferRequestModel requestModel){
		WithDrawRequest request = new WithDrawRequest();
		try {
			PropertyUtils.copyProperties(request, requestModel);
			//request.setMessageId(Consts.Mcs.MsgId.fundTransfer);
			request.setFromAccountId(requestModel.getFromAccountId());
			request.setFromCcy(requestModel.getFromCcy());
			request.setToCcy(requestModel.getToCcy());
			request.setAmount(requestModel.getAmount());
			request.setBankCode(requestModel.getBankCode());
			request.setBankAccountCode(requestModel.getToAccountId());
			request.setLoginId(requestModel.getLoginId());
			request.setPasswd(requestModel.getPasswd());
			request.setMessageSEQNum(Long.decode(Consts.Global.Number.SONE));
			request.setVersionId(Consts.Mcs.VersionId.VER100);
		} catch (Exception e) {
			commonInfo.error(e);
		} 
		return request;
	}
	
	public static FundTransferResponseModel assembleFundTransferResponse(FundTransferResponse requestModel){
		FundTransferResponseModel response = new FundTransferResponseModel();
		try {
			PropertyUtils.copyProperties(response, requestModel);
			response.setFundTransferResponse(requestModel);
		} catch (Exception e) {
			commonInfo.error(e);
		} 
		return response;
	}
	
	public static FundTransferResponseModel assembleWithDrawResponse(WithDrawResponse withDrawResponse){
		FundTransferResponseModel response = new FundTransferResponseModel();
		try {
			PropertyUtils.copyProperties(response, withDrawResponse);
			response.setWithDrawResponse(withDrawResponse);
		} catch (Exception e) {
			commonInfo.error(e);
		} 
		return response;
	}
	
	
	public static MarginFinancingListRequest assembleEnquireMarginRationsRequest(MarginFinancingListRequestModel requestModel){
		MarginFinancingListRequest request = new MarginFinancingListRequest();
		try {
			PropertyUtils.copyProperties(request, requestModel);
			request.setMessageId(Consts.Mcs.MsgId.Margin_Financing_List);
			request.setMessageSEQNum(Long.decode(Consts.Global.Number.SONE));
			request.setVersionId(Consts.Mcs.VersionId.VER100);
		} catch (Exception e) {
			commonInfo.error(e);
		} 
		return request;
	}
	
	public static PPSTransferDetailRequest assemblePPSEnquiryRequest(PPSEnquiryRequestModel requestModel){
		PPSTransferDetailRequest request = new PPSTransferDetailRequest();
		try {
			PropertyUtils.copyProperties(request, requestModel);
			request.setMessageId(Consts.Mcs.MsgId.PPS_Enquiry);
			request.setMessageSEQNum(Long.decode(Consts.Global.Number.SONE));
			request.setVersionId(Consts.Mcs.VersionId.VER100);
		} catch (Exception e) {
			commonInfo.error(e);
		} 
		return request;
	}
	
	public static  ProfitAndLossUpdateRequest assembleProfitAndLossUpdateRequest(ProfitAndLossUpdateRequestModel requestModel){
		ProfitAndLossUpdateRequest profitAndLossUpdateRequest = new ProfitAndLossUpdateRequest();
		try {
			profitAndLossUpdateRequest.setChannelId(requestModel.getChannelId());
			profitAndLossUpdateRequest.setChannelType(requestModel.getChannelType());
			profitAndLossUpdateRequest.setLoginID(requestModel.getLoginID());
			profitAndLossUpdateRequest.setMarketCode(requestModel.getMarketCode());
			profitAndLossUpdateRequest.setProfileID(requestModel.getProfileID());
			String[] profileInfoArray=requestModel.getProfileInfo().split(",");
			List<ProfileInfo> profileInfoList=new ArrayList<ProfileInfo>();
			for(int i=0;i<profileInfoArray.length;){
				if(i%2==0){
					ProfileInfo profileInfo = new ProfileInfo();
					profileInfo.setInstrumentCode((String)profileInfoArray[i]);
		            profileInfo.setAverageCost(new BigDecimal(profileInfoArray[i+1]));
		            profileInfoList.add(profileInfo);
		            i+=2;
				}
			}
			profitAndLossUpdateRequest.setProfileInfo(profileInfoList);
			profitAndLossUpdateRequest.setMessageId(Consts.Mcs.MsgId.ProfitAndLossUpdate);
			profitAndLossUpdateRequest.setMessageSEQNum(Long.decode(Consts.Global.Number.SONE));
			profitAndLossUpdateRequest.setVersionId(Consts.Mcs.VersionId.VER100);
		} catch (Exception e) {
			commonInfo.error(e);
		} 
		return profitAndLossUpdateRequest;
	}
	
	public static  ProfitAndLossEnquiryRequest assembleProfitAndLossEnquiryRequest(ProfitAndLossEnqiryRequestModel requestModel){
		ProfitAndLossEnquiryRequest profitAndLossEnquiryRequest = new ProfitAndLossEnquiryRequest();
		try {
			PropertyUtils.copyProperties(profitAndLossEnquiryRequest, requestModel);
			profitAndLossEnquiryRequest.setMessageId(Consts.Mcs.MsgId.ProfitAndLossEnquiry);
			profitAndLossEnquiryRequest.setMessageSEQNum(Long.decode(Consts.Global.Number.SONE));
			profitAndLossEnquiryRequest.setVersionId(Consts.Mcs.VersionId.VER100);
		} catch (Exception e) {
			commonInfo.error(e);
		} 
		return profitAndLossEnquiryRequest;
	}
	
	public static MISAccountCashHoldingRequest assembleCashDetailRequest(CashDetailRequestModel cashDetailRequestModel){
		MISAccountCashHoldingRequest accountCashHoldingRequest = new MISAccountCashHoldingRequest();
		try {
			PropertyUtils.copyProperties(accountCashHoldingRequest, cashDetailRequestModel);
			accountCashHoldingRequest.setMessageId(Constants.MsgID_AcDetailWithCashHoldings);
			accountCashHoldingRequest.setMessageSEQNum(Long.decode(Consts.Global.Number.SONE));
			accountCashHoldingRequest.setVersionId(Consts.Mcs.VersionId.VER100);
		} catch (Exception e) {
			commonInfo.error(e);
		} 
		return accountCashHoldingRequest;
	}
	
	public static BOCTransferRequest assembleBOCTransferRequest(BOCTransferRequestModel requestModel){
		BOCTransferRequest request = new BOCTransferRequest();
		try {
			PropertyUtils.copyProperties(request, requestModel);
			request.setMessageId(Consts.Mcs.MsgId.BOC_Transfer);
			request.setMessageSEQNum(Long.decode(Consts.Global.Number.SONE));
			request.setVersionId(Consts.Mcs.VersionId.VER100);
		} catch (Exception e) {
			commonInfo.error(e);
		} 
		return request;
	}
}