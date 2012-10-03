package com.itsz.sht.service.channels;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.struts.util.MessageResources;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.itsz.eipo.webservice.EIPOResponse;
import com.itsz.eipo.webservice.IpoMaster;
import com.itsz.eipo.webservice.IpoMasterRes;
import com.itsz.eipo.webservice.IpoSubscriptionDto;
import com.itsz.eipo.webservice.IpoSubscrptnNotifyDto;
import com.itsz.eipo.webservice.SubscriptionRes;
import com.itsz.sht.common.Constants;
import com.itsz.sht.common.Consts;
import com.itsz.sht.common.DateHelper;
import com.itsz.sht.common.FormatUtil;
import com.itsz.sht.common.ForwardMappingUtil;
import com.itsz.sht.common.LanguageUtil;
import com.itsz.sht.common.LoggerFactory;
import com.itsz.sht.common.PortalUtil;
import com.itsz.sht.common.PropertyConfig;
import com.itsz.sht.common.model.common.ModelUtil;
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
import com.itsz.sht.common.model.request.LotSizeRequestModel;
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
import com.itsz.sht.common.model.response.EnquireRTQResponse;
import com.itsz.sht.common.model.response.EnquireRTQResponseModel;
import com.itsz.sht.common.model.response.EnquireShortRTQResponse;
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
import com.itsz.sht.common.model.response.ShortRTQResponse;
import com.itsz.sht.common.model.response.TradeListResponseModel;
import com.itsz.sht.common.model.response.TransactionProtectionResponseModel;
import com.itsz.sht.common.model.response.VerifyPasswordResponseModel;
import com.itsz.sht.common.model.response.VerifyPlaceOrderResponseModel;
import com.itsz.sht.common.model.response.listorder.OrderDetailTradeResponseModel;
import com.itsz.sht.common.model.response.listorder.TradeInfoModel;
import com.itsz.sht.common.model.response.placeorder.PrePlaceOrderResponseModel;
import com.itsz.sht.common.util.EmailUtil;
import com.itsz.sht.common.util.EmcUtil;
import com.itsz.sht.common.util.LangUtil;
import com.itsz.sht.dao.delegate.FundDepositDelegate;
import com.itsz.sht.dao.delegate.IPOMaintenanceDelegate;
import com.itsz.sht.dao.delegate.IPOQtyAmtDelegate;
import com.itsz.sht.dao.delegate.IPORequestDelegate;
import com.itsz.sht.dao.delegate.IPOResultDelegate;
import com.itsz.sht.dao.hibernate.model.FundDeposit;
import com.itsz.sht.dto.ipo.IPORecord;
import com.itsz.sht.dto.ipo.IPORequest;
import com.itsz.sht.dto.ipo.IPOResult;
import com.itsz.sht.exception.ITSZException;
import com.itsz.sht.service.mcs.McsService;
import com.itsz.sht.service.qs.QsService;
import com.itsz.sht.struts.eipo.dao.EIPOMasterEntry;
import com.itsz.sht.struts.eipo.dao.EIPOSubscriptionDto;
import com.itsz.sht.struts.eipo.util.EIPOClientUtil;
import com.taifook.mcs.core.beans.msg.MISAccountDetailResponse;
import com.taifook.mcs.core.beans.msg.MISAccountEnquiryResponse;
import com.taifook.mcs.core.beans.msg.MISAccountListResponse;
import com.taifook.mcs.core.beans.msg.MISAccountSummaryResponse;
import com.taifook.mcs.core.beans.msg.MTSSShareHoldingResponse;
import com.taifook.mcs.core.beans.msg.OrderInfo;
import com.taifook.mcs.core.beans.msg.OrderListRequest;
import com.taifook.mcs.core.beans.msg.TransactionProtectionResponse;
import com.taifook.mcs.core.util.EncryptPwd;
import com.taifook.mtss.web.eipo.exception.EIPOServiceProviderException;

/**
 * $Id: BaseBusinessTemplate.java,v 1.97 2011/05/19 06:32:23 xli Exp $
 * 
 * @Project:portal
 * @File:BaseBusinessTemplate.java
 * @Description:
 * @Author:kyzou
 * @Date:2008-3-4
 */
public class BaseBusinessTemplate implements BusinessTemplate {

	Log mcsInfo = LoggerFactory.getInstance().getMcsInfo();
	private McsService mcsService;
	private QsService qsService;
	private RTQService rtqService;
	private IPOMaintenanceDelegate ipoMaintenanceDelegate;
	private IPOQtyAmtDelegate ipoQtyAmtDelegate;
	private IPORequestDelegate ipoRequestDelegate;
	private IPOResultDelegate ipoResultDelegate;
	private FundDepositDelegate fundDepositDelegate;
	private EmailUtil email;
	private EmcUtil emcUtil;

	private ClassPathXmlApplicationContext context;

	public BaseBusinessTemplate() {
	}

	public void setEmcUtil(EmcUtil emcUtil) {
		this.emcUtil = emcUtil;
	}

	public void setBaseEmail(EmailUtil email) {
		this.email = email;
	}

	public void setRtqService(RTQService rtqService) {
		this.rtqService = rtqService;
	}

	public void setBaseIpoMaintenanceDelegate(
			IPOMaintenanceDelegate ipoMaintenanceDelegate) {
		this.ipoMaintenanceDelegate = ipoMaintenanceDelegate;
	}

	public void setBaseIpoQtyAmtDelegate(IPOQtyAmtDelegate ipoQtyAmtDelegate) {
		this.ipoQtyAmtDelegate = ipoQtyAmtDelegate;
	}

	public void setBaseIpoRequestDelegate(IPORequestDelegate ipoRequestDelegate) {
		this.ipoRequestDelegate = ipoRequestDelegate;
	}

	public void setBaseIpoResultDelegate(IPOResultDelegate ipoResultDelegate) {
		this.ipoResultDelegate = ipoResultDelegate;
	}

	public void setBaseMcsService(McsService mcsService) {
		this.mcsService = mcsService;
	}

	public void setBaseQsService(QsService qsService) {
		this.qsService = qsService;
	}

	public void setBaseRtqService(RTQService rtqService) {
		this.rtqService = rtqService;
	}

	public void setBaseFundDepositDelegate(
			FundDepositDelegate fundDepositDelegate) {
		this.fundDepositDelegate = fundDepositDelegate;
	}

	public PlaceOrderResponseModel placeOrder(
			PlaceOrderRequestModel requestModel) {
		PlaceOrderResponseModel placeRes = new PlaceOrderResponseModel();
		try {
			if (PortalUtil.isNotMachRulePassword(requestModel.getPassword(),
					requestModel.getTransactionProtection())) {
				placeRes
						.setReturnCode(Consts.Error.Code.TRAD_PASSWORD_NOTMACHRULE);
				placeRes.setResultForward(ForwardMappingUtil
						.getForward(Consts.Error.Code.FAILURE));
				fillPlaceOrderResponse(requestModel, placeRes);
				return placeRes;
			}
			placeRes = mcsService.callPlaceOrder(requestModel);
		} catch (ITSZException placeOrderEx) {
			fillPlaceOrderResponse(requestModel, placeRes);
			placeRes.setReturnCode(placeOrderEx.getErrorCode());
			placeRes.setResultForward(getException01Forward(placeOrderEx
					.getErrorCode()));
			return placeRes;
		}
		echoplexPlaceOrder(placeRes, requestModel);
		return placeRes;
	}

	private void fillPlaceOrderResponse(PlaceOrderRequestModel requestModel,
			PlaceOrderResponseModel placeRes) {
		try {
			PropertyUtils.copyProperties(placeRes, requestModel);
			placeRes.setAllOrNothing(requestModel.getAllOrNothingFlag());
			OrderFeeResponseModel orderFeeResp = new OrderFeeResponseModel();
			orderFeeResp.getCalOrderFeeResponse().setNetAmount(
					new BigDecimal(requestModel.getTransactionAmount()));
			placeRes.setOrderFeeResp(orderFeeResp);
		} catch (Exception e) {
			mcsInfo.info("thread :: " + Thread.currentThread().getName());
			mcsInfo.info("get lotsize exist exception :: message ::: "
					+ e.getMessage());
		}
	}

	protected void echoplexPlaceOrder(PlaceOrderResponseModel placeRes,
			PlaceOrderRequestModel requestModel) {
		if (requestModel != null) {
			placeRes.setAccountId(requestModel.getAccountId());
			placeRes.setCustCode(requestModel.getCustCode());
			placeRes.setMarketCode(requestModel.getMarketCode());
			placeRes.setInstrCode(requestModel.getInstrCode());
			placeRes.setOrderSide(requestModel.getOrderSide());
			placeRes.setOrderQuantity(requestModel.getOrderQuantity());
			placeRes.setOrderPrice(requestModel.getOrderPrice());
			placeRes.setOrderType(requestModel.getOrderType());
			placeRes.setLoginId(requestModel.getLoginId());
			placeRes.setPassword(requestModel.getPassword());
			placeRes.setAllOrNothing(requestModel.getAllOrNothingFlag());
			placeRes.setTriggerPrice(requestModel.getTriggerPrice());
			placeRes.setConditionType(requestModel.getConditionType());
		}
	}

	/**
	 * cal order fee need set those data come from pages
	 */
	public OrderFeeResponseModel calOrderFee(
			OrderFeeRequestModel orderFeeRequest) {
		OrderFeeResponseModel orderFeeRes = new OrderFeeResponseModel();
		String lotSize = null;
		int lsInt = 0;
		try {
			lotSize = qsService.lotSize(ModelUtil
					.assembleLotSizeRequest(orderFeeRequest));
			lsInt = lotSize != null ? Integer.parseInt(lotSize) : 0;
		} catch (Exception e) {
			mcsInfo.info("thread :: " + Thread.currentThread().getName());
			mcsInfo.info("get lotsize exist exception :: message ::: "
					+ e.getMessage());
		}
		int orderQty = orderFeeRequest.getOrderQuantity() != null ? orderFeeRequest
				.getOrderQuantity().intValue()
				: 0;
		verifyIllegalAccess(orderFeeRequest, orderFeeRes, lotSize, lsInt,
				orderQty);
		if (!Consts.Global.Status.NORMAL.equals(orderFeeRes.getReturnCode())) {
			return orderFeeRes;
		}
		MosResponseModel mosResponse = getMosResponse(orderFeeRequest);
		if (!Consts.Global.Status.NORMAL.equals(mosResponse.getReturnCode())) {
			orderFeeRes.setReturnCode(mosResponse.getReturnCode());
			return orderFeeRes;
		}
		BigDecimal mos = new BigDecimal(0.00);
		if (mosResponse != null
				&& mosResponse.getCalMOSResponse().getTotalPurchasingPower() != null) {
			mos = mosResponse.getCalMOSResponse().getTotalPurchasingPower();
		}
		try {
			if (isNotAOOrder(orderFeeRequest)) {
				orderFeeRes = mcsService.callOrderFee(orderFeeRequest, mos);
			} else {
				try {
					PropertyUtils.copyProperties(orderFeeRes, orderFeeRequest);
				} catch (Exception e) {
					//
				}
			}
			if (isDealDisassemblyOrder(lsInt, orderQty)) {
				orderFeeRes
						.setDisassemblyFlag(Consts.Error.Code.TRAD_WARNING_SEPORDER);// 提示拆单�?
			}
		} catch (ITSZException orderFeeEx) {
			orderFeeRes.setInstrCode(orderFeeRequest.getInstrCode());
			orderFeeRes.setOrderPrice(orderFeeRequest.getOrderQuantity());
			orderFeeRes.setReturnCode(orderFeeEx.getErrorCode());
			orderFeeRes.setResultForward(getException01Forward(orderFeeEx
					.getErrorCode()));
			return orderFeeRes;
		}
		String isExceedMaxLotFlag = Consts.Global.Flag.NEGATIVE;
		if (isDealCountExceedMAXPermit(lotSize, lsInt, orderQty))
			isExceedMaxLotFlag = Consts.Global.Flag.POSITIVE;
		orderFeeRes.setOrderQuantity(orderFeeRequest.getOrderQuantity());
		dealWithAOBuy(mcsService, orderFeeRequest, orderFeeRes, mosResponse,
				isExceedMaxLotFlag);
		return orderFeeRes;
	}

	protected OrderFeeResponseModel verifyIllegalAccess(
			OrderFeeRequestModel orderFeeRequest,
			OrderFeeResponseModel orderFeeRes, String lotSize, int lsInt,
			int orderQty) {
		if (StringUtils.isBlank(FormatUtil.formatInstrCode(orderFeeRequest
				.getInstrCode()))) {
			orderFeeRes.setReturnCode(Consts.Error.Code.TRAD_ERROR_INSTRCODE);// 股票代码不符合规范�?
			orderFeeRes.setResultForward(ForwardMappingUtil
					.getForward(Consts.Error.Code.TRAD_ERROR_INSTRCODE));
			return orderFeeRes;
		}
		if (isDealCountExceedMaxHand(lsInt, orderQty)
				&& Consts.Channel.Id.NWEB.equals(orderFeeRequest
						.getChannelType())) {
			orderFeeRes.setReturnCode(Consts.Error.Code.TRAD_ERROR_QTYMAXSIZE);// 交易数量不能超过30000手�?
			orderFeeRes.setResultForward(ForwardMappingUtil
					.getForward(Consts.Error.Code.FAILURE));
			return orderFeeRes;
		}
		if (isNotInLotSizeRule(lotSize, lsInt, orderQty)) {
			orderFeeRes.setReturnCode(Consts.Error.Code.LOTSIZENOTMATCH);// not
			// in
			// lot
			// size
			// rule
			// sys018
			orderFeeRes.setResultForward(ForwardMappingUtil
					.getForward(Consts.Error.Code.FAILURE));
			return orderFeeRes;
		}
		if (isDealCountExceedMAXForbid(qsService, orderFeeRequest, lotSize,
				lsInt, orderQty)
				&& Consts.Channel.Id.NWEB.equals(orderFeeRequest
						.getChannelType())) {
			orderFeeRes.setReturnCode(Consts.Error.Code.AONINVALIDQTY);// 全额或撤消的交易数量不能超过3000手�?
			orderFeeRes.setResultForward(ForwardMappingUtil
					.getForward(Consts.Error.Code.FAILURE));
			return orderFeeRes;
		}
		return orderFeeRes;
	}

	protected MosResponseModel getMosResponse(
			OrderFeeRequestModel orderFeeRequest) {
		MosResponseModel mosResponse = new MosResponseModel();
		if (isNeedCallMos(orderFeeRequest)) {
			try {
				mosResponse = getMOSResponse(orderFeeRequest);
			} catch (Exception e) {
				return mosResponse;
			}
		}
		return mosResponse;
	}

	protected boolean isNeedCallMos(OrderFeeRequestModel orderFeeRequest) {
		return (!Consts.Global.Flag.NEGATIVE.equals(orderFeeRequest
				.getIsNeedCallMos()));
	}

	protected boolean isDealCountExceedMaxHand(int lsInt, int orderQty) {
		if (lsInt == 0)
			return false;
		if ((orderQty / lsInt) > Consts.Qs.MAX_HAND
				|| isExceedCount(lsInt, orderQty, Consts.Qs.MAX_HAND)) {
			return true;
		}
		return false;
	}

	protected boolean isDealDisassemblyOrder(int lsInt, int orderQty) {
		if (lsInt == 0)
			return false;
		if (isExceedCount(lsInt, orderQty, Consts.Qs.MAX_LOT)
				|| ((orderQty / lsInt) > Consts.Qs.MAX_LOT)) {
			return true;
		}
		return false;
	}

	private boolean isExceedCount(int lsInt, int orderQty, int lot) {
		if ((orderQty / lsInt) == lot && (orderQty % lsInt) > 0) {
			return true;
		}
		return false;
	}

	/**
	 * not ao order
	 * 
	 * @Author:Cimenon Saint
	 * @Time:2007-6-4 16:45:27
	 * @param orderFeeRequest
	 * @return
	 */
	protected boolean isNotAOOrder(OrderFeeRequestModel orderFeeRequest) {
		return !Consts.Order.Type.AT_AUCTION.equals(orderFeeRequest
				.getOrderType());
	}

	/**
	 * get mos response
	 * 
	 * @Author:Cimenon Saint
	 * @Time:2007-6-4 16:45:48
	 * @param mcsService
	 * @param orderFeeRequest
	 * @return
	 */
	protected MosResponseModel getMOSResponse(
			OrderFeeRequestModel orderFeeRequest) {
		MosResponseModel model = new MosResponseModel();
		try {
			MosRequestModel mosRequest = ModelUtil
					.assembleMosRequestModel(orderFeeRequest);
			model = mcsService.callMos(mosRequest);
		} catch (ITSZException mosEx) {
			mcsInfo.info("thread :: " + Thread.currentThread().getName());
			mcsInfo.info("call mos exist exception :: message ::: "
					+ mosEx.getMessage());
			model.setReturnCode(mosEx.getErrorCode());
			model.setResultForward(getException01Forward(mosEx.getErrorCode()));
		}
		return model;
	}

	/**
	 * the lot size is in the lotsize rule and exceed the max size under the
	 * condition :: can get the lot size value
	 * 
	 * @Author:Cimenon Saint
	 * @Time:2007-6-7 12:25:05
	 * @param qsService
	 * @param lotSize
	 * @param lsInt
	 * @param orderQty
	 * @return
	 */
	protected boolean isDealCountExceedMAXPermit(String lotSize, int lsInt,
			int orderQty) {
		return StringUtils.isNotBlank(lotSize) && isInRule(lsInt, orderQty)
				&& qsService.isExceedMaxLot(orderQty, lsInt);
	}

	/**
	 * 
	 * @Author:Cimenon Saint
	 * @Time:2007-6-7 12:25:44
	 * @param qsService
	 * @param orderFeeRequest
	 * @param lotSize
	 * @param lsInt
	 * @param orderQty
	 * @return
	 */
	protected boolean isDealCountExceedMAXForbid(QsService qsService,
			OrderFeeRequestModel orderFeeRequest, String lotSize, int lsInt,
			int orderQty) {
		return isDealCountExceedMAXPermit(lotSize, lsInt, orderQty)
				&& isFullOrCancel(orderFeeRequest);
	}

	/**
	 * 
	 * @Author:Cimenon Saint
	 * @Time:2007-6-7 12:26:20
	 * @param qsService
	 * @param lotSize
	 * @param lsInt
	 * @param orderQty
	 * @return
	 */
	protected boolean isNotInLotSizeRule(String lotSize, int lsInt, int orderQty) {
		return StringUtils.isNotBlank(lotSize) && !isInRule(lsInt, orderQty);
	}

	/**
	 * ao order and buy
	 * 
	 * @Author:Cimenon Saint
	 * @Time:2007-6-4 15:54:09
	 * @param mcsService
	 * @param orderFeeRequest
	 * @param orderFeeResponse
	 * @param mosResponse
	 * @param isExceedMaxLotFlag
	 */
	protected void dealWithAOBuy(McsService mcsService,
			OrderFeeRequestModel orderFeeRequest,
			OrderFeeResponseModel orderFeeResponse,
			MosResponseModel mosResponse, String isExceedMaxLotFlag) {
		orderFeeResponse.setSplitOrderFlag(isExceedMaxLotFlag);
		orderFeeResponse.setIsExceedMaxLot(isExceedMaxLotFlag);
		ModelUtil.echoplexOrderFee(orderFeeResponse, orderFeeRequest);
		BigDecimal netAmount = new BigDecimal(0.00);
		if (!isAOBuy(orderFeeRequest)) {
			netAmount = orderFeeResponse.getCalOrderFeeResponse()
					.getNetAmount();
		} else {
			double zpop = 0.00;//
			if (mosResponse != null) {
				if (mosResponse.getCalMOSResponse().getZeroPriceOrderPrice() != null) {
					zpop = mosResponse.getCalMOSResponse()
							.getZeroPriceOrderPrice().doubleValue();
				}
			} else {
				return;
			}
			netAmount = new BigDecimal(orderFeeRequest.getOrderQuantity()
					.doubleValue()
					* zpop);
		}
		BigDecimal iAmount = new BigDecimal(Consts.Global.Number.NONE);
		if (Consts.Order.Side.B.equals(orderFeeRequest.getOrderSide())) {
			iAmount = mcsService.getInsufficientAmount(netAmount, mosResponse
					.getCalMOSResponse().getTotalPurchasingPower());
		}
		if (iAmount.doubleValue() != Consts.Global.Number.NONE) {
			orderFeeResponse.setInsufficientAmount(iAmount);
		}
	}

	/**
	 * whether ao order and order type is B
	 * 
	 * @Author:Cimenon Saint
	 * @Time:2007-6-4 15:54:30
	 * @param orderFeeRequest
	 * @return
	 */
	protected boolean isAOBuy(OrderFeeRequestModel orderFeeRequest) {
		return Consts.Order.Side.B.equals(orderFeeRequest.getOrderSide())
				&& Consts.Order.Type.AT_AUCTION.equals(orderFeeRequest
						.getOrderType());
	}

	/**
	 * whether the order quantity is in lot size rule
	 * 
	 * @Author:Cimenon Saint
	 * @Time:2007-6-4 15:55:15
	 * @param qsService
	 * @param lsInt
	 * @param orderQty
	 * @return
	 */
	protected boolean isInRule(int lsInt, int orderQty) {
		return qsService.inLotSizeRule(orderQty, lsInt);
	}

	/**
	 * the allorNothing value come from page is Y?
	 * 
	 * @Author:Cimenon Saint
	 * @Time:2007-6-4 15:55:57
	 * @param orderFeeRequest
	 * @return
	 */
	protected boolean isFullOrCancel(OrderFeeRequestModel orderFeeRequest) {
		return Consts.Global.Flag.POSITIVE.equalsIgnoreCase(orderFeeRequest
				.getAllOrNothing());
	}

	/**
	 * pre place order 1.lot size problem 2.3000 lot problem 4.ao 5.co
	 */
	public PrePlaceOrderResponseModel prePlaceOrder(
			PrePlaceOrderRequestModel prePlaceRequest) {
		PrePlaceOrderResponseModel prePlaceResponse = new PrePlaceOrderResponseModel();
		try {
			prePlaceResponse = mcsService.callPrePlaceOrder(prePlaceRequest);
		} catch (ITSZException e) {
			if (prePlaceResponse == null) {
				prePlaceResponse = new PrePlaceOrderResponseModel();
			}
			prePlaceResponse.setReturnCode(e.getErrorCode());
			// e.printStackTrace();
		}
		return prePlaceResponse;
	}

	public LoginResponseModel login(LoginRequestModel requestModel) {
		return mcsLogin(requestModel);
	}

	protected void delay(LoginResponseModel resModel) {
		if (esIsDown(resModel)) {
			if (resModel.getRtqResponseModel() != null) {
				if (Consts.Global.Flag.POSITIVE.equals(resModel
						.getRtqResponseModel().getUseDelay())) {
					resModel
							.setReturnCode(Consts.Error.Code.STREAM_INFO_UNAVAILABLE);
				}
			} else {
				resModel
						.setReturnCode(Consts.Error.Code.STREAM_INFO_UNAVAILABLE);
			}
		}
	}

	protected boolean esIsDown(LoginResponseModel resModel) {
		boolean result = false;
		if (resModel != null && resModel.getRtqResponseModel() != null) {
			boolean streamSayDown = false;
			if (resModel.getRtqResponseModel().getStreamRTQResponseModel() != null) {
				streamSayDown = resModel.getRtqResponseModel()
						.getStreamRTQResponseModel().isEsDown();
			}
			boolean snapshotSayDown = false;
			if (resModel.getRtqResponseModel().getSnapshotRTQResponseModel() != null) {
				snapshotSayDown = resModel.getRtqResponseModel()
						.getSnapshotRTQResponseModel().isEsDown();
			}
			if (streamSayDown || snapshotSayDown) {
				result = true;
			}
		}
		return result;
	}

	protected boolean isAccountAvailable(String newSubCode) {
		boolean result = false;
		if (newSubCode != null) {
			result = (newSubCode
					.equals(Consts.Login.Status.LONGIN_STATUS_NORMAL)
					|| newSubCode
							.equals(Consts.Login.Status.LONGIN_STATUS_CHGPASS)
					|| newSubCode
							.equals(Consts.Login.Status.LONGIN_STATUS_GRACELOGIN)
					|| newSubCode
							.equals(Consts.Login.Status.LONGIN_STATUS_GRACECNT) || newSubCode
					.equals(Consts.Login.Status.LONGIN_STATUS_ISSUED));
		}
		return result;
	}

	public FilterOrderResponseModel filterOrder(
			FilterOrderRequestModel requestModel) {
		FilterOrderResponseModel filterOrderResponseModel = new FilterOrderResponseModel();
		try {
			filterOrderResponseModel = mcsService.callFilterOrder(requestModel);
		} catch (ITSZException ex) {
			filterOrderResponseModel.setReturnCode(ex.getErrorCode());
		} catch (Exception e) {
			filterOrderResponseModel
					.setReturnCode(Consts.Error.Code.CONNECT_MCS);
		}
		BigDecimal filledQty = null;
		if (filterOrderResponseModel.getOrderFilteringResponse() != null) {
			for (Iterator iterator = filterOrderResponseModel
					.getOrderFilteringResponse().getOrderInfoCol().iterator(); iterator
					.hasNext();) {
				OrderInfo info = (OrderInfo) iterator.next();
				filledQty = info.getFilledQty();
				if (filledQty != null && filledQty.intValue() > 0) {
					info.setTradeAvgPrice(getTradeAvgPrice(requestModel, info));
				}
			}
		}
		echoplexFilterOrder(filterOrderResponseModel, requestModel);
		return filterOrderResponseModel;
	}

	private BigDecimal getTradeAvgPrice(FilterOrderRequestModel requestModel,
			OrderInfo info) {
		BigDecimal bdTradeAvgPrice = null;
		OrderDetailRequestModel orderDetailRequestModel = new OrderDetailRequestModel();
		try {
			PropertyUtils.copyProperties(orderDetailRequestModel, requestModel);
		} catch (Exception e1) {
			mcsInfo
					.error("call getTradeAvgPrice exception :: message ::: ",
							e1);
		}
		orderDetailRequestModel.setAccountId(info.getAccountId());
		orderDetailRequestModel.setMcsOrderId(info.getMcsOrderId());
		orderDetailRequestModel.setOrderId(info.getOrderId());
		try {
			List tHistories = (LinkedList) mcsService.callTradeHistory(
					orderDetailRequestModel, null);
			double tradeAvgPrice = 0;
			int tradeTotalQty = 0;
			for (Iterator iterator = tHistories.iterator(); iterator.hasNext();) {
				TradeInfoModel tradInfo = (TradeInfoModel) iterator.next();
				tradeTotalQty = tradeTotalQty
						+ tradInfo.getTradeQuantity().intValue();
				tradeAvgPrice = tradeAvgPrice
						+ tradInfo.getTradeQuantity().intValue()
						* tradInfo.getTradePrice().doubleValue();
			}
			if (tradeAvgPrice > 0 && tradeTotalQty > 0) {
				bdTradeAvgPrice = PortalUtil.hold3Decimal(tradeAvgPrice
						/ tradeTotalQty);
			}
		} catch (ITSZException e) {
			mcsInfo.error("call getTradeAvgPrice exception :: message ::: ", e);
		}
		return bdTradeAvgPrice;
	}

	public ListOrderResponseModel listOrder(ListOrderRequestModel requestModel) {
		ListOrderResponseModel listOrderResponseModel = new ListOrderResponseModel();
		// get order list group by order status
		try {
			if (StringUtils.isBlank(requestModel.getAccountId())) {
				listOrderResponseModel
						.setReturnCode(Consts.Error.Code.CONNECT_MCS);
				listOrderResponseModel.setResultForward(ForwardMappingUtil
						.getForward(Consts.Error.Code.CONNECT_MCS));
			}
			listOrderResponseModel = mcsService.callListOrder(requestModel);
		} catch (ITSZException ex) {
			listOrderResponseModel.setReturnCode(ex.getErrorCode());
			listOrderResponseModel.setResultForward(getException01Forward(ex
					.getErrorCode()));
		} catch (Exception e) {
			mcsInfo.error("call mos exist exception :: message ::: "
					+ e.getMessage());
			listOrderResponseModel.setReturnCode(Consts.Error.Code.CONNECT_MCS);
			listOrderResponseModel.setResultForward(ForwardMappingUtil
					.getForward(Consts.Error.Code.CONNECT_MCS));
		}
		setMos(requestModel, listOrderResponseModel);
		echoplexListOrder(listOrderResponseModel, requestModel);
		return listOrderResponseModel;
	}

	private void setMos(ListOrderRequestModel requestModel,
			ListOrderResponseModel listOrderResponseModel) {
		if (!Consts.Channel.WMT.equals(requestModel.getChannelType())) {
			BigDecimal mos = null;
			MosRequestModel mosRequest = ModelUtil
					.assembleMosRequestModel(requestModel);
			try {
				mos = mcsService.callMos(mosRequest).getCalMOSResponse()
						.getTotalPurchasingPower();
			} catch (ITSZException ex) {
				mcsInfo.info("thread :: " + Thread.currentThread().getName());
				mcsInfo.info("call mos exist exception :: code ::: "
						+ ex.getErrorCode() + " ::: message ::: "
						+ ex.getErrorMessage());
			} catch (Exception e) {
				mcsInfo.info("thread :: " + Thread.currentThread().getName());
				mcsInfo.info("call mos exist exception :: message ::: "
						+ e.getMessage());
			}
			if (mos != null) {
				listOrderResponseModel.setMos(mos);
			}
		}
	}

	/**
	 * 
	 * @Author:Cimenon Saint
	 * @Time:2007-6-7 12:28:26
	 * @param responseModel
	 * @param requestModel
	 */
	public static void echoplexFilterOrder(
			FilterOrderResponseModel responseModel,
			FilterOrderRequestModel requestModel) {
		responseModel.setVersionId(requestModel.getVersionId());
		responseModel.setChannelId(requestModel.getChannelId());
		responseModel.setChannelType(requestModel.getChannelType());
		responseModel.setLanguage(requestModel.getLanguage());
	}

	/*
	 * 
	 */
	public static void echoplexListOrder(ListOrderResponseModel responseModel,
			ListOrderRequestModel requestModel) {
		responseModel.setVersionId(requestModel.getVersionId());
		responseModel.setChannelId(requestModel.getChannelId());
		responseModel.setChannelType(requestModel.getChannelType());
		responseModel.setLanguage(requestModel.getLanguage());
	}

	/**
	 * Modify Order
	 */
	public ModifyOrderResponseModel modifyOrder(
			ModifyOrderRequestModel requestModel) {
		ModifyOrderResponseModel modifyOrderResponseModel = new ModifyOrderResponseModel();
		try {
			// 首先回查获取改单所要参�?
			OrderListRequest reqModel = ModelUtil
					.assembleOrderInfo(requestModel);
			OrderInfo orderInfo = mcsService.getModifyOrderDetail(reqModel);
			modifyOrderResponseModel = checkParamResult(requestModel,
					modifyOrderResponseModel, orderInfo);
			if (!Consts.Global.Status.NORMAL.equals(modifyOrderResponseModel
					.getReturnCode())) {
				return modifyOrderResponseModel;
			}
			// 判断lotsize
			String ls = requestModel.getLotSize();
			int lsInt = 0;
			LotSizeRequestModel model = ModelUtil
					.assembleLotSizeRequest(requestModel);
			try {
				ls = qsService.lotSize(model);
				lsInt = ls != null ? Integer.parseInt(ls) : 0;
				if (isNotInLotSizeRule(ls, lsInt, requestModel.getNewOrderQty()
						.intValue())) {
					modifyOrderResponseModel
							.setReturnCode(Consts.Error.Code.LOTSIZENOTMATCH);// not
					// in
					// lot
					// size
					// rule
					// sys018
					modifyOrderResponseModel
							.setResultForward(ForwardMappingUtil
									.getForward(Consts.Error.Code.LOTSIZENOTMATCH));
					return modifyOrderResponseModel;
				}
			} catch (Exception e) {
				mcsInfo.error("thread :: " + Thread.currentThread().getName());
				mcsInfo.error("get lotsize exist exception :: message ::: "
						+ e.getMessage());
			}
			modifyOrderResponseModel = mcsService.callModifyOrder(requestModel,
					orderInfo);
			if (modifyOrderResponseModel == null) {
				modifyOrderResponseModel = new ModifyOrderResponseModel();
				modifyOrderResponseModel
						.setReturnCode(Consts.Error.Code.CONNECT_MCS);
				modifyOrderResponseModel.setResultForward(ForwardMappingUtil
						.getForward(Consts.Error.Code.CONNECT_MCS));
			}
		} catch (ITSZException ex) {
			try {
				PropertyUtils.copyProperties(modifyOrderResponseModel,
						requestModel);
			} catch (Exception e) {
				// e.printStackTrace();
			}
			mcsInfo.info("thread :: " + Thread.currentThread().getName());
			mcsInfo.info("modify order exist exception :: code ::: "
					+ ex.getErrorCode() + " ::: message ::: "
					+ ex.getErrorMessage());
			modifyOrderResponseModel.setReturnCode(ex.getErrorCode());
			modifyOrderResponseModel.setResultForward(getException01Forward(ex
					.getErrorCode()));
		} catch (Exception e) {
			mcsInfo.error("thread :: " + Thread.currentThread().getName());
			mcsInfo.error("modify order exist exception :: message ::: "
					+ e.getMessage());
			modifyOrderResponseModel
					.setReturnCode(Consts.Error.Code.CONNECT_MCS);
			modifyOrderResponseModel.setResultForward(ForwardMappingUtil
					.getForward(Consts.Error.Code.CONNECT_MCS));
		}
		return modifyOrderResponseModel;
	}

	private ModifyOrderResponseModel checkParamResult(
			ModifyOrderRequestModel requestModel,
			ModifyOrderResponseModel modifyOrderResponseModel,
			OrderInfo orderInfo) throws ITSZException {
		// 是否修改
		if (isNotChangeModifyOrder(orderInfo, requestModel)) {
			modifyOrderResponseModel
					.setReturnCode(Consts.Error.Code.TRAD_MODIFY_NOTMODIFY);
			modifyOrderResponseModel.setResultForward(ForwardMappingUtil
					.getForward(Consts.Error.Code.FAILURE));
			return modifyOrderResponseModel;
		}
		// 校验新的改单数量
		if (isIncreQtyOrder(orderInfo.getInitialQuantity(), requestModel
				.getNewOrderQty(), orderInfo.getChangedQty())) {
			modifyOrderResponseModel
					.setReturnCode(Consts.Error.Code.TRAD_TOOMUCH_MODIFYQTY);
			modifyOrderResponseModel.setResultForward(ForwardMappingUtil
					.getForward(Consts.Error.Code.FAILURE));
			return modifyOrderResponseModel;
		}
		if (isLessQtyOrder(orderInfo.getInitialQuantity(), requestModel
				.getNewOrderQty(), orderInfo.getFilledQty())) {
			modifyOrderResponseModel
					.setReturnCode(Consts.Error.Code.TRAD_TOOLESS_MODIFYQTY);
			modifyOrderResponseModel.setResultForward(ForwardMappingUtil
					.getForward(Consts.Error.Code.FAILURE));
			return modifyOrderResponseModel;
		}
		// 校验密码
		if (PortalUtil.isNotMachRulePassword(requestModel.getPassword(),
				requestModel.getTransactionProtection())) {
			modifyOrderResponseModel
					.setReturnCode(Consts.Error.Code.TRAD_PASSWORD_NOTMACHRULE);
			modifyOrderResponseModel.setResultForward(ForwardMappingUtil
					.getForward(Consts.Error.Code.FAILURE));
			return modifyOrderResponseModel;
		}
		return modifyOrderResponseModel;
	}

	private boolean isNotChangeModifyOrder(OrderInfo orderInfo,
			ModifyOrderRequestModel reqModel) {
		// AO
		if (Consts.Order.Type.AT_AUCTION.equals(orderInfo.getOrderType())) {
			if (ModelUtil.getUltimateQuantity(orderInfo.getInitialQuantity(),
					orderInfo.getChangedQty()) == reqModel.getNewOrderQty()
					.longValue()) {
				return true;
			}
		} else { // Not AO
			if (orderInfo.getOrderPrice() == null
					|| reqModel.getNewOrderPrice() == null) {
				return false;
			}
			if ((orderInfo.getOrderPrice().floatValue() == reqModel
					.getNewOrderPrice().floatValue())
					&& (ModelUtil.getUltimateQuantity(orderInfo
							.getInitialQuantity(), orderInfo.getChangedQty()) == reqModel
							.getNewOrderQty().longValue())) {
				// pbxie:不加这句会有问题�?
				if (Consts.Order.Type.CONDITIONAL.equals(orderInfo
						.getOrderType())
						&& !orderInfo.getTriggerPrice().equals(
								reqModel.getTriggerPrice()))
					return false;

				return true;
			}
		}
		return false;
	}

	private boolean isIncreQtyOrder(BigDecimal initQty, BigDecimal newQty,
			BigDecimal changedQty) {
		if (newQty.floatValue() > (initQty.floatValue() + changedQty
				.floatValue())) {
			return true;
		}
		return false;
	}

	private boolean isLessQtyOrder(BigDecimal initQty, BigDecimal newQty,
			BigDecimal filledQty) {
		if (newQty.floatValue() < filledQty.floatValue()) {
			return true;
		}
		return false;
	}

	/**
	 * BulkCancelOrder
	 */
	public BulkCancelOrderResponseModel bulkCancelOrder(
			BulkCancelOrderRequestModel requestModel) {
		BulkCancelOrderResponseModel bulkCancelOrderResponseModel = new BulkCancelOrderResponseModel();
		try {
			if (PortalUtil.isNotMachRulePassword(requestModel.getPassword(),
					requestModel.getTransactionProtection())) {
				bulkCancelOrderResponseModel
						.setReturnCode(Consts.Error.Code.TRAD_PASSWORD_NOTMACHRULE);
				bulkCancelOrderResponseModel
						.setResultForward(ForwardMappingUtil
								.getForward(Consts.Error.Code.FAILURE));
				return bulkCancelOrderResponseModel;
			}
			bulkCancelOrderResponseModel = mcsService
					.callBulkCancelOrder(requestModel);
			if (bulkCancelOrderResponseModel == null) {
				bulkCancelOrderResponseModel = new BulkCancelOrderResponseModel();
				bulkCancelOrderResponseModel
						.setReturnCode(Consts.Error.Code.CONNECT_MCS);
				bulkCancelOrderResponseModel
						.setResultForward(ForwardMappingUtil
								.getForward(Consts.Error.Code.CONNECT_MCS));
			}
		} catch (ITSZException ex) {
			mcsInfo.error("thread :: " + Thread.currentThread().getName());
			mcsInfo.info("cancel order exist exception :: code ::: "
					+ ex.getErrorCode() + " ::: message ::: "
					+ ex.getErrorMessage());
			bulkCancelOrderResponseModel.setReturnCode(ex.getErrorCode());
			bulkCancelOrderResponseModel
					.setResultForward(getException01Forward(ex.getErrorCode()));
		} catch (Exception e) {
			mcsInfo.error("thread :: " + Thread.currentThread().getName());
			mcsInfo.error("cancel order exist exception :: message ::: "
					+ e.getMessage());
			bulkCancelOrderResponseModel
					.setReturnCode(Consts.Error.Code.CONNECT_MCS);
			bulkCancelOrderResponseModel.setResultForward(ForwardMappingUtil
					.getForward(Consts.Error.Code.CONNECT_MCS));
		}
		return bulkCancelOrderResponseModel;
	}

	public ProfitAndLossEnqiryResponseModel profitAndLossEnqiry(
			ProfitAndLossEnqiryRequestModel requestModel) {
		ProfitAndLossEnqiryResponseModel profitAndLossEnqiryResponseModel = new ProfitAndLossEnqiryResponseModel();
		try {
			profitAndLossEnqiryResponseModel = mcsService
					.callProfitAndLossEnqiry(requestModel);
			if (profitAndLossEnqiryResponseModel == null) {
				profitAndLossEnqiryResponseModel = new ProfitAndLossEnqiryResponseModel();
				profitAndLossEnqiryResponseModel
						.setReturnCode(Consts.Error.Code.CONNECT_MCS);
				profitAndLossEnqiryResponseModel
						.setResultForward(ForwardMappingUtil
								.getForward(Consts.Error.Code.CONNECT_MCS));
			}
		} catch (ITSZException ex) {
			mcsInfo.error("thread :: " + Thread.currentThread().getName());
			mcsInfo.info("cancel order exist exception :: code ::: "
					+ ex.getErrorCode() + " ::: message ::: "
					+ ex.getErrorMessage());
			profitAndLossEnqiryResponseModel.setReturnCode(ex.getErrorCode());
			profitAndLossEnqiryResponseModel
					.setResultForward(getException01Forward(ex.getErrorCode()));
		} catch (Exception e) {
			mcsInfo.error("thread :: " + Thread.currentThread().getName());
			mcsInfo.error("cancel order exist exception :: message ::: "
					+ e.getMessage());
			profitAndLossEnqiryResponseModel
					.setReturnCode(Consts.Error.Code.CONNECT_MCS);
			profitAndLossEnqiryResponseModel
					.setResultForward(ForwardMappingUtil
							.getForward(Consts.Error.Code.CONNECT_MCS));
		}
		return profitAndLossEnqiryResponseModel;
	}

	public ProfitAndLossUpdateResponseModel profitAndLossUpdate(
			ProfitAndLossUpdateRequestModel requestModel) {
		ProfitAndLossUpdateResponseModel profitAndLossUpdateResponseModel = new ProfitAndLossUpdateResponseModel();
		try {
			profitAndLossUpdateResponseModel = mcsService
					.callProfitAndLossUpdate(requestModel);
			if (profitAndLossUpdateResponseModel == null) {
				profitAndLossUpdateResponseModel = new ProfitAndLossUpdateResponseModel();
				profitAndLossUpdateResponseModel
						.setReturnCode(Consts.Error.Code.CONNECT_MCS);
				profitAndLossUpdateResponseModel
						.setResultForward(ForwardMappingUtil
								.getForward(Consts.Error.Code.CONNECT_MCS));
			}
		} catch (ITSZException ex) {
			mcsInfo.error("thread :: " + Thread.currentThread().getName());
			mcsInfo.info("cancel order exist exception :: code ::: "
					+ ex.getErrorCode() + " ::: message ::: "
					+ ex.getErrorMessage());
			profitAndLossUpdateResponseModel.setReturnCode(ex.getErrorCode());
			profitAndLossUpdateResponseModel
					.setResultForward(getException01Forward(ex.getErrorCode()));
		} catch (Exception e) {
			mcsInfo.error("thread :: " + Thread.currentThread().getName());
			mcsInfo.error("cancel order exist exception :: message ::: "
					+ e.getMessage());
			profitAndLossUpdateResponseModel
					.setReturnCode(Consts.Error.Code.CONNECT_MCS);
			profitAndLossUpdateResponseModel
					.setResultForward(ForwardMappingUtil
							.getForward(Consts.Error.Code.CONNECT_MCS));
		}
		return profitAndLossUpdateResponseModel;
	}

	/**
	 * CancelOrder
	 */
	public CancelOrderResponseModel cancelOrder(
			CancelOrderRequestModel requestModel) {
		CancelOrderResponseModel cancelOrderResponseModel = new CancelOrderResponseModel();
		try {
			if (PortalUtil.isNotMachRulePassword(requestModel.getPassword(),
					requestModel.getTransactionProtection())) {
				cancelOrderResponseModel
						.setReturnCode(Consts.Error.Code.TRAD_PASSWORD_NOTMACHRULE);
				cancelOrderResponseModel.setResultForward(ForwardMappingUtil
						.getForward(Consts.Error.Code.FAILURE));
				return cancelOrderResponseModel;
			}
			cancelOrderResponseModel = mcsService.callCancelOrder(requestModel);
			if (cancelOrderResponseModel == null) {
				cancelOrderResponseModel = new CancelOrderResponseModel();
				cancelOrderResponseModel
						.setReturnCode(Consts.Error.Code.CONNECT_MCS);
				cancelOrderResponseModel.setResultForward(ForwardMappingUtil
						.getForward(Consts.Error.Code.CONNECT_MCS));
			}
		} catch (ITSZException ex) {
			mcsInfo.error("thread :: " + Thread.currentThread().getName());
			mcsInfo.info("cancel order exist exception :: code ::: "
					+ ex.getErrorCode() + " ::: message ::: "
					+ ex.getErrorMessage());
			cancelOrderResponseModel.setReturnCode(ex.getErrorCode());
			cancelOrderResponseModel.setResultForward(getException01Forward(ex
					.getErrorCode()));
		} catch (Exception e) {
			mcsInfo.error("thread :: " + Thread.currentThread().getName());
			mcsInfo.error("cancel order exist exception :: message ::: "
					+ e.getMessage());
			cancelOrderResponseModel
					.setReturnCode(Consts.Error.Code.CONNECT_MCS);
			cancelOrderResponseModel.setResultForward(ForwardMappingUtil
					.getForward(Consts.Error.Code.CONNECT_MCS));
		}
		return cancelOrderResponseModel;
	}

	/**
	 * order detail and its histories
	 */
	@SuppressWarnings("unchecked")
	public OrderDetailTradeResponseModel orderDetail(
			OrderDetailRequestModel orderDetailRequestModel) {
		OrderDetailTradeResponseModel detailWithHis = new OrderDetailTradeResponseModel();
		try {
			detailWithHis = mcsService.callOrderDetail(orderDetailRequestModel);
			if (detailWithHis.getOrderListResponse() == null) {
				detailWithHis = new OrderDetailTradeResponseModel();
				detailWithHis.setReturnCode(Consts.Error.Code.CONNECT_MCS);
				detailWithHis.setResultForward(ForwardMappingUtil
						.getForward(Consts.Error.Code.SHOWMSG));
				return detailWithHis;
			}
			if (Consts.Global.Flag.POSITIVE.equals(orderDetailRequestModel
					.getHasHisories())) {
				BigDecimal filledQty = null;
				if (detailWithHis.getOrderListResponse() != null) {
					for (Iterator iterator = detailWithHis
							.getOrderListResponse().getOrderInfoCol()
							.iterator(); iterator.hasNext();) {
						OrderInfo info = (OrderInfo) iterator.next();
						filledQty = info.getFilledQty();
						break;
					}
				}
				if (filledQty != null && filledQty.intValue() > 0) {
					try {
						List tHistories = (LinkedList) mcsService
								.callTradeHistory(orderDetailRequestModel,
										detailWithHis);
						detailWithHis.setTraderHistories(tHistories);
					} catch (ITSZException e) {
						detailWithHis.setReturnCode(e.getErrorCode());
						detailWithHis.setResultForward(getException01Forward(e
								.getErrorCode()));
					}
					detailWithHis.setHasHisories(orderDetailRequestModel
							.getHasHisories());
				}
			}
		} catch (ITSZException e) {
			detailWithHis.setReturnCode(e.getErrorCode());
			detailWithHis.setResultForward(getException01Forward(e
					.getErrorCode()));
		} catch (Exception ex) {
			mcsInfo.error("thread :: " + Thread.currentThread().getName());
			mcsInfo.error("orderDetail exist exception :: message ::: "
					+ ex.getMessage());
			return detailWithHis;
		}
		return detailWithHis;
	}

	/**
	 * 
	 * @Author:kyzou
	 * @Time:2008-3-06
	 * @param
	 * @return
	 */
	protected LoginResponseModel mcsLogin(LoginRequestModel requestModel) {
		LoginResponseModel responseModel = new LoginResponseModel();
		requestModel.setLoginId(FormatUtil.formatLoginId(requestModel
				.getLoginId()));
		try {
			responseModel = mcsService.callPwdLogin(requestModel);
			responseModel.setLoginId(requestModel.getLoginId());
			responseModel.setChannelId(requestModel.getChannelId());
			responseModel.setChannelType(requestModel.getChannelType());
			responseModel.setLanguage(requestModel.getLanguage());
			responseModel.setVersionId(requestModel.getVersionId());
		} catch (ITSZException ex) {
			responseModel.setReturnCode(ex.getErrorCode());
			responseModel.setResultForward(ForwardMappingUtil
					.getForward(Consts.Error.Code.FAILURE));
			return responseModel;
		} catch (Exception e) {
			mcsInfo.error("thread :: " + Thread.currentThread().getName());
			mcsInfo.error("mcsLogin exist exception :: message ::: "
					+ e.getMessage());
			responseModel.setReturnCode(Consts.Error.Code.CONNECT_MCS);
			responseModel.setResultForward(ForwardMappingUtil
					.getForward(Consts.Error.Code.FAILURE));
			return responseModel;
		}
		return responseModel;
	}

	/**
	 * 
	 * @Author:kyzou
	 * @Time:2008-3-06
	 * @param
	 * @return
	 */
	public EnquireRTQResponseModel enquireRTQInfo(
			EnquireRTQRequestModel rtqRequest) {
		EnquireRTQResponseModel responseModel = new EnquireRTQResponseModel();
		List<EnquireRTQResponse> response = null;
		rtqRequest.setCode(ModelUtil.assembleStockCode(rtqRequest));
		try {
			if (Consts.Qs.FREE_QUOT_REAL_TIME.equals(rtqRequest.getSubType())) {
				response = qsService.callSimpleRTQInfo(rtqRequest);
			} else {
				response = qsService.callEnquireRTQInfo(rtqRequest);
			}
			PropertyUtils.copyProperties(responseModel, rtqRequest);
			responseModel.setEnquireRTQResponse(response);
		} catch (Exception e) {
			mcsInfo.error("thread :: " + Thread.currentThread().getName());
			mcsInfo.error("enquireRTQInfo qs exist exception :: message ::: "
					+ e.getMessage());
			responseModel
					.setReturnCode(Consts.Error.Code.ERRORCODE_CONNECT_RTQ);
			responseModel.setResultForward(ForwardMappingUtil
					.getForward(Consts.Error.Code.ERRORCODE_CONNECT_RTQ));
			return responseModel;
		}
		return responseModel;
	}

	public EnquireShortRTQResponseModel enquireShortRTQInfo(
			EnquireShortRTQRequestModel request) {
		EnquireShortRTQResponseModel responseModel = new EnquireShortRTQResponseModel();
		try {
			PropertyUtils.copyProperties(responseModel, request);
			EnquireRTQRequestModel rtqRequest = new EnquireRTQRequestModel();
			rtqRequest.setCode(ModelUtil.assembleStockCode(request
					.getInstrCode()));
			rtqRequest.setChannelId(request.getChannelId());
			rtqRequest.setCustomerId(request.getCustCode());
			rtqRequest.setLanguage(request.getLanguage());
			rtqRequest.setChannelType(request.getChannelType());
			rtqRequest = ModelUtil.fillRTQRequestValue(rtqRequest, request
					.getQuoteType());
			List<EnquireShortRTQResponse> rtqResponses = qsService
					.callShortRTQInfo(rtqRequest);
			PropertyUtils.copyProperties(responseModel, request);
			responseModel
					.setShortRTQResponse(new ShortRTQResponse(rtqResponses));
		} catch (Exception e) {
			mcsInfo.error("thread :: " + Thread.currentThread().getName());
			mcsInfo
					.error("enquireShortRTQInfo qs exist exception :: message ::: "
							+ e.getMessage());
			responseModel
					.setReturnCode(Consts.Error.Code.ERRORCODE_CONNECT_RTQ);
			responseModel.setResultForward(ForwardMappingUtil
					.getForward(Consts.Error.Code.ERRORCODE_CONNECT_RTQ));
			return responseModel;
		}
		return responseModel;
	}

	public VerifyPlaceOrderResponseModel verifyPlaceOrder(
			VerifyPlaceOrderRequestModel orderModel) {
		VerifyPlaceOrderResponseModel responseModel = new VerifyPlaceOrderResponseModel();
		try {
			OrderFeeRequestModel orderFeeRequest = ModelUtil
					.assembleOrderFeeRequest(orderModel);
			OrderFeeResponseModel orderFeeRes = calOrderFee(orderFeeRequest);
			assambleVerifyPlaceOrderResponse(orderModel, responseModel);
			responseModel.setOrderFeeResp(orderFeeRes);
			if (!Consts.Error.Code.NORMAL.equals(orderFeeRes.getReturnCode())) {
				responseModel.setReturnCode(orderFeeRes.getReturnCode());
				responseModel.setResultForward(orderFeeRes.getResultForward());
			}
		} catch (Exception e) {
			mcsInfo.error("thread :: " + Thread.currentThread().getName());
			mcsInfo.error("verifyPlaceOrder exist exception :: message ::: "
					+ e.getMessage());
			responseModel.setReturnCode(Consts.Error.Code.CONNECT_MCS);
			responseModel.setResultForward(ForwardMappingUtil
					.getForward(Consts.Error.Code.TRAD_ERROR_SYSTEM));
			return responseModel;
		}
		return responseModel;

	}

	protected void assambleVerifyPlaceOrderResponse(
			VerifyPlaceOrderRequestModel orderModel,
			VerifyPlaceOrderResponseModel responseModel)
			throws IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		PropertyUtils.copyProperties(responseModel, orderModel);
		if (Consts.Order.Type.AT_AUCTION.equals(orderModel.getOrderType())) {
			responseModel.setAllOrNothing(Consts.Global.Flag.NEGATIVE);
			responseModel.setOrderPrice(null);
		}
	}

	public TransactionProtectionResponseModel changeTransactionProtection(
			TransactionProtectionRequestModel ptnRequest) {
		TransactionProtectionResponse response = null;
		TransactionProtectionResponseModel responseModel = new TransactionProtectionResponseModel();
		try {
			if (needVerifyPassword(ptnRequest)) {
				responseModel = doVerifyPassword(ptnRequest, responseModel);
				if (!Consts.Error.Code.NORMAL.equals(responseModel
						.getReturnCode())) {
					return responseModel;
				}
			}
			response = mcsService.callSetTransPwdPtd(ptnRequest);
			responseModel = ModelUtil.assembleSetTransPwdPtdResponse(response,
					ptnRequest);
			responseModel.setTransactionProtectionStatus(response
					.getTransactionProtectionStatus());
			if (Consts.Global.Flag.NEGATIVE.equals(responseModel
					.getTransactionProtectionStatus())) {
				responseModel.setReturnCode(Consts.Error.Code.TRSPTD_FAILURE);
				responseModel.setResultForward(ForwardMappingUtil
						.getForward(Consts.Error.Code.TRSPTD_FAILURE));
				return responseModel;
			}
			if (Consts.Global.Flag.NEGATIVE.equals(ptnRequest
					.getNeedVarifyPassword())) {
				responseModel
						.setResultForward(Consts.Wmt.Forward.FIRST_CONFIRM_SETTING);
			}
			responseModel.setTransactionProtectionStatus(ptnRequest
					.getTransactionProtection());
			responseModel.setTransactionProtectionRes(response);
		} catch (ITSZException ex) {
			responseModel.setReturnCode(ex.getErrorCode());
			responseModel.setResultForward(getException01Forward(ex
					.getErrorCode()));
			responseModel.setNeedVarifyPassword(ptnRequest
					.getNeedVarifyPassword());
			return responseModel;
		} catch (Exception e) {
			mcsInfo.error("thread :: " + Thread.currentThread().getName());
			mcsInfo
					.error("changeTransactionProtection exist exception :: message ::: "
							+ e.getMessage());
			responseModel.setReturnCode(Consts.Error.Code.CONNECT_MCS);
			responseModel.setResultForward(ForwardMappingUtil
					.getForward(Consts.Error.Code.TRSPTDS_ERROR_SYS));
			responseModel.setNeedVarifyPassword(ptnRequest
					.getNeedVarifyPassword());
			return responseModel;
		}
		return responseModel;
	}

	private boolean needVerifyPassword(
			TransactionProtectionRequestModel ptnRequest) {
		// return
		// (Consts.Global.Flag.POSITIVE.equals(ptnRequest.getNeedVarifyPassword())
		// &&
		// Consts.Global.Flag.NEGATIVE.equals(ptnRequest.getTransactionProtection()));
		return Consts.Global.Flag.NEGATIVE.equals(ptnRequest
				.getTransactionProtection());
	}

	protected TransactionProtectionResponseModel doVerifyPassword(
			TransactionProtectionRequestModel ptnRequest,
			TransactionProtectionResponseModel responseModel) throws Exception,
			ITSZException {
		if (StringUtils.isBlank(ptnRequest.getPassword())) {
			responseModel.setReturnCode(Consts.Error.Code.TRSPTD_INVALID_PASS);
			responseModel.setResultForward(ForwardMappingUtil
					.getForward(Consts.Error.Code.TRSPTD_INVALID_PASS));
			return responseModel;
		}
		VerifyPasswordRequestModel requestModel = new VerifyPasswordRequestModel();
		PropertyUtils.copyProperties(requestModel, ptnRequest);
		requestModel.setLoginId(FormatUtil.formatLoginId(requestModel
				.getLoginId()));
		requestModel.setPassword(EncryptPwd.Encrypt(requestModel.getPassword(),
				""));
		VerifyPasswordResponseModel passResp = mcsService
				.callVerifyPassword(requestModel);
		if (Consts.Global.Flag.NEGATIVE.equals(passResp.getPasswordMatch())) {
			responseModel.setReturnCode(Consts.Error.Code.TRSPTD_ERROR_PASS);
			responseModel.setResultForward(ForwardMappingUtil
					.getForward(Consts.Error.Code.TRSPTD_ERROR_PASS));
			return responseModel;
		}
		return responseModel;
	}

	public EnquireAccountResponseModel enquireAccountDetail(
			EnquireAccountRequestModel accountModel) {
		MISAccountSummaryResponse misAccountResp = new MISAccountSummaryResponse();
		EnquireAccountResponseModel responseModel = new EnquireAccountResponseModel();
		try {
			misAccountResp = mcsService.callEnquireAccount(accountModel);
			PropertyUtils.copyProperties(responseModel, accountModel);
			responseModel.setMisAccountSummaryResponse(misAccountResp);
		} catch (ITSZException ex) {
			responseModel.setReturnCode(ex.getErrorCode());
			responseModel.setResultForward(getException01Forward(ex
					.getErrorCode()));
			return responseModel;
		} catch (Exception e) {
			mcsInfo.error("thread :: " + Thread.currentThread().getName());
			mcsInfo.error("enquireAccount exist exception :: message ::: "
					+ e.getMessage());
			responseModel.setReturnCode(Consts.Error.Code.CONNECT_MCS);
			responseModel.setResultForward(ForwardMappingUtil
					.getForward(Consts.Error.Code.SHOWMSG));
			return responseModel;
		}
		return responseModel;
	}

	public EnquireAccountResponseModel enquireAccountDetailForPs(
			EnquireAccountRequestModel accountModel) {
		EnquireAccountResponseModel responseModel = new EnquireAccountResponseModel();
		try {
			MISAccountDetailResponse misAccountResp = mcsService
					.callEnquireAccountDetail(accountModel);
			PropertyUtils.copyProperties(responseModel, accountModel);
			responseModel.setMisAccountDetailResponse(misAccountResp);
		} catch (ITSZException ex) {
			responseModel.setReturnCode(ex.getErrorCode());
			responseModel.setResultForward(getException01Forward(ex
					.getErrorCode()));
			return responseModel;
		} catch (Exception e) {
			mcsInfo.error("thread :: " + Thread.currentThread().getName());
			mcsInfo.error("enquireAccount exist exception :: message ::: "
					+ e.getMessage());
			responseModel.setReturnCode(Consts.Error.Code.CONNECT_MCS);
			responseModel.setResultForward(ForwardMappingUtil
					.getForward(Consts.Error.Code.SHOWMSG));
			return responseModel;
		}
		return responseModel;
	}

	public MISAccountEnquiryResponseModel enquireMisAccount(
			EnquireAccountListRequestModel accountModel) {
		MISAccountEnquiryResponseModel responseModel = new MISAccountEnquiryResponseModel();
		try {
			MISAccountEnquiryResponse response = mcsService
					.callEnquireMisAccount(accountModel);
			PropertyUtils.copyProperties(responseModel, accountModel);
			responseModel.setMisAccountEnquiryResponse(response);
		} catch (ITSZException ex) {
			responseModel.setReturnCode(ex.getErrorCode());
			responseModel.setResultForward(ForwardMappingUtil
					.getForward(Consts.Error.Code.SUCCESS));
		} catch (Exception e) {
			mcsInfo.error("thread :: " + Thread.currentThread().getName());
			mcsInfo.error("enquireAccountList exist exception :: message ::: "
					+ e.getMessage());
			responseModel.setReturnCode(Consts.Error.Code.CONNECT_MCS);
			responseModel.setResultForward(ForwardMappingUtil
					.getForward(Consts.Error.Code.SUCCESS));
		}
		return responseModel;
	}

	public MISAccountListResponseModel enquireAccountList(
			EnquireAccountListRequestModel accountModel) {
		MISAccountListResponseModel responseModel = new MISAccountListResponseModel();
		try {
			MISAccountListResponse response = mcsService
					.callEnquireAccountList(accountModel);
			PropertyUtils.copyProperties(responseModel, accountModel);
			responseModel = ModelUtil.assambleEnquireMisAccountList(response);
		} catch (ITSZException ex) {
			responseModel.setReturnCode(ex.getErrorCode());
			responseModel.setResultForward(ForwardMappingUtil
					.getForward(Consts.Error.Code.SUCCESS));
		} catch (Exception e) {
			mcsInfo.error("thread :: " + Thread.currentThread().getName());
			mcsInfo.error("enquireAccountList exist exception :: message ::: "
					+ e.getMessage());
			responseModel.setReturnCode(Consts.Error.Code.CONNECT_MCS);
			responseModel.setResultForward(ForwardMappingUtil
					.getForward(Consts.Error.Code.SUCCESS));
		}
		return responseModel;
	}

	public EnquiryPositionResponseModel enquiryStockPosition(
			EnquiryPositionRequestModel positionModel) {
		EnquiryPositionResponseModel responseModel = new EnquiryPositionResponseModel();
		try {
			MTSSShareHoldingResponse response = mcsService
					.callEnquiryPosition(positionModel);
			responseModel = ModelUtil.assembleEnquiryStockPositionResponse(
					response, positionModel);
		} catch (ITSZException ex) {
			responseModel.setReturnCode(ex.getErrorCode());
			responseModel.setResultForward(getException01Forward(ex
					.getErrorCode()));
			return responseModel;
		} catch (Exception e) {
			mcsInfo.error("thread :: " + Thread.currentThread().getName());
			mcsInfo
					.error("enquiryStockPosition exist exception :: message ::: "
							+ e.getMessage());
			responseModel.setReturnCode(Consts.Error.Code.CONNECT_MCS);
			responseModel.setResultForward(ForwardMappingUtil
					.getForward(Consts.Error.Code.SHOWMSG));
			return responseModel;
		}
		return responseModel;
	}

	public ChangePwdResponseModel changePassword(
			ChangePwdRequestModel changePwdModel) {
		ChangePwdResponseModel responseModel = new ChangePwdResponseModel();
		changePwdModel.setLoginId(FormatUtil.formatLoginId(changePwdModel
				.getLoginId()));
		changePwdModel.setOldPassword(EncryptPwd.Encrypt(changePwdModel
				.getOldPassword(), ""));
		changePwdModel.setNewPassword(EncryptPwd.Encrypt(changePwdModel
				.getNewPassword(), ""));
		try {
			responseModel = mcsService.callChangePassword(changePwdModel);
			if (!Consts.Error.Code.SUCCESS.equals(responseModel
					.getChangePasswordStatus())) {
				responseModel.setReturnCode(toDealExpErrCode(responseModel
						.getChangePasswordStatus()));
				responseModel.setResultForward(ForwardMappingUtil
						.getForward(Consts.Error.Code.FAILURE));
			}
			PropertyUtils.copyProperties(responseModel, changePwdModel);
		} catch (ITSZException ex) {
			responseModel.setReturnCode(toDealExpErrCode(ex.getErrorCode()));
			responseModel.setResultForward(getException01Forward(ex
					.getErrorCode()));
			return responseModel;
		} catch (Exception e) {
			mcsInfo.error("thread :: " + Thread.currentThread().getName());
			mcsInfo.error("changePassword exist exception :: message ::: "
					+ e.getMessage());
			responseModel.setReturnCode(Consts.Error.Code.CONNECT_MCS);
			responseModel.setResultForward(ForwardMappingUtil
					.getForward(Consts.Error.Code.CHANGEPASS_ERROR_SYS));
			return responseModel;
		}
		return responseModel;
	}

	protected String getException01Forward(String extCode) {
		if (StringUtils.isBlank(ForwardMappingUtil.getForward(extCode))) {
			return ForwardMappingUtil
					.getForward(Consts.Error.Code.ITSZ_EXCEPTION);
		}
		return ForwardMappingUtil.getForward(extCode);
	}

	private String toDealExpErrCode(String errCode) {
		if (Consts.Error.Code.CHANGEPASS_INVALID_OLDPASS_N.equals(errCode)) {
			return Consts.Error.Code.CHANGEPASS_INVALID_OLDPASS_N_V;
		}
		return errCode;
	}

	public AnnounceResponseModel enquireBroadcast(
			AnnounceRequestModel requestModel) {
		return null;
	}

	public MosResponseModel getMos(MosRequestModel mosModel) {
		MosResponseModel mosResModel = new MosResponseModel();
		try {
			mosResModel = mcsService.callMos(mosModel);
		} catch (ITSZException e) {
			mosResModel.setReturnCode(e.getErrorCode());
		} catch (Exception e) {
			mosResModel.setReturnCode(Consts.Error.Code.CONNECT_MCS);
		}
		return mosResModel;
	}

	public ChangeTAndCResponseModel changeTAndC(
			ChangeTAndCRequestModel requestModel) {
		ChangeTAndCResponseModel responseModel = new ChangeTAndCResponseModel();
		requestModel.setLoginId(FormatUtil.formatLoginId(requestModel
				.getLoginId()));
		try {
			responseModel = mcsService.callChangeTAndC(requestModel);
			if (!Consts.Global.Flag.POSITIVE.equals(responseModel
					.getTAndCUpdate())) {
				mcsInfo.error("thread :: " + Thread.currentThread().getName());
				mcsInfo.error("changeTAndC failure ::: ");
			}
			PropertyUtils.copyProperties(responseModel, requestModel);
		} catch (ITSZException ex) {
			mcsInfo.error("thread :: " + Thread.currentThread().getName());
			mcsInfo.error("changeTAndC exist exception :: message ::: "
					+ ex.getMessage());
			responseModel.setReturnCode(ex.getErrorCode());
			responseModel.setResultForward(getException01Forward(ex
					.getErrorCode()));
			return responseModel;
		} catch (Exception e) {
			mcsInfo.error("thread :: " + Thread.currentThread().getName());
			mcsInfo.error("changeTAndC exist exception :: message ::: "
					+ e.getMessage());
			responseModel.setReturnCode(Consts.Error.Code.CONNECT_MCS);
			responseModel.setResultForward(ForwardMappingUtil
					.getForward(Consts.Error.Code.SHOWMSG));
			return responseModel;
		}
		return responseModel;
	}

	public TradeListResponseModel enquireTradeList(
			TradeListRequestModel requestModel) {
		TradeListResponseModel responseModel = new TradeListResponseModel();
		requestModel.setLoginId(FormatUtil.formatLoginId(requestModel
				.getLoginId()));
		try {
			responseModel = mcsService.callTradeList(requestModel);
		} catch (ITSZException ex) {
			mcsInfo.error("thread :: " + Thread.currentThread().getName());
			mcsInfo.error("enquireTradeList exist exception :: message ::: "
					+ ex.getMessage());
			responseModel.setReturnCode(ex.getErrorCode());
			responseModel.setResultForward(getException01Forward(ex
					.getErrorCode()));
			return responseModel;
		} catch (Exception e) {
			mcsInfo.error("thread :: " + Thread.currentThread().getName());
			mcsInfo.error("enquireTradeList exist exception :: message ::: "
					+ e.getMessage());
			responseModel.setReturnCode(Consts.Error.Code.CONNECT_MCS);
			responseModel.setResultForward(ForwardMappingUtil
					.getForward(Consts.Error.Code.SHOWMSG));
			return responseModel;
		}
		return responseModel;
	}

	public BOCTransferResponseModel bocTransfer(
			BOCTransferRequestModel requestModel) {
		BOCTransferResponseModel responseModel = new BOCTransferResponseModel();
		try {
			responseModel = mcsService.bocTransfer(requestModel);
			PropertyUtils.copyProperties(responseModel, requestModel);
		} catch (ITSZException ex) {
			mcsInfo.error("thread :: " + Thread.currentThread().getName());
			mcsInfo.error("bocTransfer exist exception :: message ::: "
					+ ex.getMessage());
			responseModel.setReturnCode(ex.getErrorCode());
			responseModel.setResultForward(getException01Forward(ex
					.getErrorCode()));
			return responseModel;
		} catch (Exception e) {
			mcsInfo.error("thread :: " + Thread.currentThread().getName());
			mcsInfo.error("bocTransfer exist exception :: message ::: "
					+ e.getMessage());
			responseModel.setReturnCode(Consts.Error.Code.CONNECT_MCS);
			responseModel.setResultForward(ForwardMappingUtil
					.getForward(Consts.Error.Code.SHOWMSG));
			return responseModel;
		}
		return responseModel;
	}

	public CashDetailResponseModel enquireCashDetail(
			CashDetailRequestModel cashDetailRequestModel) {
		CashDetailResponseModel cashDetailResponseModel = new CashDetailResponseModel();
		try {
			cashDetailResponseModel = mcsService
					.callCashDetail(cashDetailRequestModel);
		} catch (ITSZException ex) {
			mcsInfo.error("thread :: " + Thread.currentThread().getName());
			mcsInfo.error("enquireCashDetail exist exception :: message ::: "
					+ ex.getMessage());
			cashDetailResponseModel.setReturnCode(ex.getErrorCode());
			cashDetailResponseModel.setResultForward(getException01Forward(ex
					.getErrorCode()));
			return cashDetailResponseModel;
		} catch (Exception e) {
			mcsInfo.error("thread :: " + Thread.currentThread().getName());
			mcsInfo.error("enquireCashDetail exist exception :: message ::: "
					+ e.getMessage());
			cashDetailResponseModel
					.setReturnCode(Consts.Error.Code.CONNECT_MCS);
			cashDetailResponseModel.setResultForward(ForwardMappingUtil
					.getForward(Consts.Error.Code.SHOWMSG));
			return cashDetailResponseModel;
		}
		return cashDetailResponseModel;
	}

	public FundTransferResponseModel fundTransfer(
			FundTransferRequestModel requestModel) {
		FundTransferResponseModel responseModel = new FundTransferResponseModel();
		requestModel.setLoginId(FormatUtil.formatLoginId(requestModel
				.getLoginId()));
		requestModel.setPassword(EncryptPwd.Encrypt(requestModel.getPasswd(),
				""));
		try {
			BigDecimal amount = requestModel.getAmount();
			BigDecimal minTransferValue = new BigDecimal(PropertyConfig
					.getCommonProperty(Constants.FUNDTRANSFER_MIN_LIMIT));
			BigDecimal maxTransferValue = new BigDecimal(PropertyConfig
					.getCommonProperty(Constants.FUNDTRANSFER_MAX_LIMIT));
			boolean minFlag = (1 == minTransferValue.compareTo(amount));
			boolean maxFlag = (-1 == maxTransferValue.compareTo(amount));
			if (minFlag || maxFlag) {
				ITSZException ex = new ITSZException();
				if (minFlag) {
					ex.setErrorCode(Constants.ERRORCODE_TRANSFER_MIN);
					ex.setErrorMessage("not within transfer limit range");
				} else if (maxFlag) {
					ex.setErrorCode(Constants.ERRORCODE_TRANSFER_MAX);
					ex.setErrorMessage("errors.fundTransferForm.transferLimit");
				}
				return responseModel;
			}
			if (requestModel.isBank()) {
				responseModel = mcsService.withDraw(requestModel);
			} else {
				responseModel = mcsService.fundTransfer(requestModel);
			}
			PropertyUtils.copyProperties(responseModel, requestModel);
		} catch (ITSZException ex) {
			mcsInfo.error("thread :: " + Thread.currentThread().getName());
			mcsInfo.error("fundTransfer exist exception :: message ::: "
					+ ex.getMessage());
			responseModel.setReturnCode(ex.getErrorCode());
			responseModel.setResultForward(getException01Forward(ex
					.getErrorCode()));
			return responseModel;
		} catch (Exception e) {
			mcsInfo.error("thread :: " + Thread.currentThread().getName());
			mcsInfo.error("fundTransfer exist exception :: message ::: "
					+ e.getMessage());
			responseModel.setReturnCode(Consts.Error.Code.CONNECT_MCS);
			responseModel.setResultForward(ForwardMappingUtil
					.getForward(Consts.Error.Code.SHOWMSG));
			return responseModel;
		}
		return responseModel;
	}

	public MarginFinancingListResponseModel enquireMarginRations(
			MarginFinancingListRequestModel requestModel) {
		MarginFinancingListResponseModel responseModel = new MarginFinancingListResponseModel();
		requestModel.setLoginId(FormatUtil.formatLoginId(requestModel
				.getLoginId()));
		try {
			responseModel = mcsService.callEnquireMarginRations(requestModel);
			PropertyUtils.copyProperties(responseModel, requestModel);
		} catch (ITSZException ex) {
			mcsInfo.error("thread :: " + Thread.currentThread().getName());
			mcsInfo
					.error("enquireMarginRations exist exception :: message ::: "
							+ ex.getMessage());
			responseModel.setReturnCode(ex.getErrorCode());
			responseModel.setResultForward(getException01Forward(ex
					.getErrorCode()));
			return responseModel;
		} catch (Exception e) {
			mcsInfo.error("thread :: " + Thread.currentThread().getName());
			mcsInfo
					.error("enquireMarginRations exist exception :: message ::: "
							+ e.getMessage());
			responseModel.setReturnCode(Consts.Error.Code.CONNECT_MCS);
			responseModel.setResultForward(ForwardMappingUtil
					.getForward(Consts.Error.Code.SHOWMSG));
			return responseModel;
		}
		return responseModel;
	}

	public PPSEnquiryResponseModel ppsEnquiry(
			PPSEnquiryRequestModel requestModel) {
		PPSEnquiryResponseModel responseModel = new PPSEnquiryResponseModel();
		try {
			responseModel = mcsService.getPPSRecords(requestModel);
			PropertyUtils.copyProperties(responseModel, requestModel);
		} catch (ITSZException ex) {
			mcsInfo.error("thread :: " + Thread.currentThread().getName());
			mcsInfo
					.error("enquireMarginRations exist exception :: message ::: "
							+ ex.getMessage());
			responseModel.setReturnCode(ex.getErrorCode());
			responseModel.setResultForward(getException01Forward(ex
					.getErrorCode()));
			return responseModel;
		} catch (Exception e) {
			mcsInfo.error("thread :: " + Thread.currentThread().getName());
			mcsInfo
					.error("enquireMarginRations exist exception :: message ::: "
							+ e.getMessage());
			responseModel.setReturnCode(Consts.Error.Code.CONNECT_MCS);
			responseModel.setResultForward(ForwardMappingUtil
					.getForward(Consts.Error.Code.SHOWMSG));
			return responseModel;
		}
		return responseModel;
	}

	public FilterIPOListResponseModel getAllIPORecord(
			IPORequestModel requestModel) {
		FilterIPOListResponseModel responseModel = new FilterIPOListResponseModel();
		try {
			List ipoRecordList = (List) ipoMaintenanceDelegate
					.getAllIPORecord();
			IPORecord ipoRecord = new IPORecord();
			if (ipoRecordList.size() > 0) {
				String currentLocale = requestModel.getLanguage();
				for (int i = 0; i < ipoRecordList.size(); i++) {
					ipoRecord = (IPORecord) ipoRecordList.get(i);
					if (Consts.Global.Language.BIG5.equals(currentLocale)) {
						ipoRecord.setIpoName_dsply(ipoRecord.getIpoName_big());
						ipoRecord.setProp_url_dsply(ipoRecord.getProp_url_tw());
					} else {
						if (Consts.Global.Language.GB.equals(currentLocale)) {
							ipoRecord.setIpoName_dsply(ipoRecord
									.getIpoName_gb());
							ipoRecord.setProp_url_dsply(ipoRecord
									.getProp_url_cn());
						} else {
							ipoRecord.setIpoName_dsply(ipoRecord.getIpoName());
							ipoRecord.setProp_url_dsply(ipoRecord
									.getProp_url_en());
						}
					}
				}
			}
			PropertyUtils.copyProperties(responseModel, requestModel);
			responseModel.setResult(ipoRecordList);
		} catch (Exception e) {
			mcsInfo.error("thread :: " + Thread.currentThread().getName());
			mcsInfo.error("getAllIPORecord exist exception :: message ::: "
					+ e.getMessage());
			responseModel.setReturnCode(Consts.Error.Code.CONNECT_IPO);
		}
		return responseModel;
	}

	public InsertIPOResponseModel insertIPORequest(
			IPOAddRequestModel requestModel) {
		InsertIPOResponseModel responseModel = new InsertIPOResponseModel();
		try {
			requestModel.setLoginId(FormatUtil.formatLoginId(requestModel
					.getLoginId()));
			IPORequest ipoRequest = new IPORequest();
			VerifyPasswordRequestModel verifyPassReq = new VerifyPasswordRequestModel();
			PropertyUtils.copyProperties(verifyPassReq, requestModel);
			verifyPassReq.setLoginId(requestModel.getLoginId());
			verifyPassReq.setPassword(requestModel.getPassword());
			VerifyPasswordResponseModel verifyPassResp = verifyPassword(verifyPassReq);
			boolean canApply = verifyPassResp.getPasswordMatch().equals(
					Consts.Global.Flag.POSITIVE) ? true : false;
			if (!canApply) {
				responseModel
						.setReturnCode(Consts.Error.Code.TRAD_ERROR_WRONGPASS);
				mcsInfo.error("add ipo error,password is error!");
				PropertyUtils.copyProperties(responseModel, requestModel);
				return responseModel;
			}
			Date applyDate = new Date();
			String nowTime = new Timestamp(applyDate.getTime()).toString();
			canApply = false;
			long ipoMasterId = requestModel.getIpoMasterId();
			IPORecord ipoRecord = ipoMaintenanceDelegate
					.getIPORecord(ipoMasterId);
			if (ipoRecord != null) {
				if (Consts.Global.Language.BIG5.equals(requestModel
						.getLanguage())) {
					ipoRecord.setIpoName_dsply(ipoRecord.getIpoName_big());
					ipoRecord.setProp_url_dsply(ipoRecord.getProp_url_tw());
				} else {
					if (Consts.Global.Language.GB.equals(requestModel
							.getLanguage())) {
						ipoRecord.setIpoName_dsply(ipoRecord.getIpoName_gb());
						ipoRecord.setProp_url_dsply(ipoRecord.getProp_url_cn());
					} else {
						ipoRecord.setIpoName_dsply(ipoRecord.getIpoName());
						ipoRecord.setProp_url_dsply(ipoRecord.getProp_url_en());
					}
				}
			}
			responseModel.setIpoRecord(ipoRecord);
			String deadLine = ipoRecord.getDeadLine().toString();
			String isClose = ipoRecord.getStatus();
			if (!"CLOSE".equals(isClose) && deadLine.compareTo(nowTime) >= 0) {
				canApply = true;
			}
			if (!canApply) {
				mcsInfo.error("add ipo error,time is over!");
				responseModel.setReturnCode(Consts.Error.Code.IPO_TIMEOVER);
				return responseModel;
			}
			IPORequest testRequest = ipoRequestDelegate.getIPORequest(
					requestModel.getAccountId(), ipoMasterId);
			if (testRequest != null && testRequest.getStatus() != null
					&& testRequest.getStatus().equals("received")) {
				mcsInfo.error("double ipo error!");
				responseModel.setReturnCode(Consts.Error.Code.TRAD_DOUBLE);
				return responseModel;
			}

			ipoRequest.setAccountId(requestModel.getAccountId());
			ipoRequest.setApplyQuantity(requestModel.getApplyQuantity());
			ipoRequest.setIpoMasterId(requestModel.getIpoMasterId());
			ipoRequest.setDsptAmount(requestModel.getDsptAmount());
			ipoRequest.setEmail(requestModel.getEmail());
			ipoRequest.setIsAccept(requestModel.getIsAccept());
			ipoRequest.setRemark("online");
			ipoRequest.setStockCode(requestModel.getStockCode());
			ipoRequest.setStatus("received");
			ipoRequest.setApplyDate(applyDate);
			ipoRequest.setTelephone(requestModel.getTelephone());
			ipoRequestDelegate.insertIPORequest(ipoRequest);
			IPORequest thisIpoRequest = ipoRequestDelegate.getIPORequest(
					requestModel.getAccountId(), ipoMasterId);
			responseModel.setIpoRequest(thisIpoRequest);
			PropertyUtils.copyProperties(responseModel, requestModel);
		} catch (Exception e) {
			mcsInfo.error("thread :: " + Thread.currentThread().getName());
			mcsInfo.error("insertIPORequest exist exception :: message ::: "
					+ e.getMessage());
			responseModel.setReturnCode(Consts.Error.Code.CONNECT_IPO);
		}
		return responseModel;
	}

	public FilterIPOQtyAmtRcrdResponseModel getIPOQtyAmtRcrd(
			IPOQtyRequestModel requestModel) {
		FilterIPOQtyAmtRcrdResponseModel responseModel = new FilterIPOQtyAmtRcrdResponseModel();
		try {
			long ipoMasterId = requestModel.getIpoMasterId();
			List ipoQtyAmt = (List) ipoQtyAmtDelegate
					.getIPOQtyAmtRcrd(ipoMasterId);
			IPORecord ipoRecord = ipoMaintenanceDelegate
					.getIPORecord(ipoMasterId);
			if (ipoRecord != null) {
				if (Consts.Global.Language.BIG5.equals(requestModel
						.getLanguage())) {
					ipoRecord.setIpoName_dsply(ipoRecord.getIpoName_big());
					ipoRecord.setProp_url_dsply(ipoRecord.getProp_url_tw());
				} else {
					if (Consts.Global.Language.GB.equals(requestModel
							.getLanguage())) {
						ipoRecord.setIpoName_dsply(ipoRecord.getIpoName_gb());
						ipoRecord.setProp_url_dsply(ipoRecord.getProp_url_cn());
					} else {
						ipoRecord.setIpoName_dsply(ipoRecord.getIpoName());
						ipoRecord.setProp_url_dsply(ipoRecord.getProp_url_en());
					}
				}
			}
			responseModel.setIpoRecord(ipoRecord);
			PropertyUtils.copyProperties(responseModel, requestModel);
			responseModel.setResult(ipoQtyAmt);
		} catch (Exception e) {
			mcsInfo.error("thread :: " + Thread.currentThread().getName());
			mcsInfo.error("getIPOQtyAmtRcrd exist exception :: message ::: "
					+ e.getMessage());
			responseModel.setReturnCode(Consts.Error.Code.CONNECT_IPO);
		}
		return responseModel;
	}

	public FilterIPOAmtRcrdResponseModel getIPOAmtRcrd(
			IPOQtyRequestModel requestModel) {
		FilterIPOAmtRcrdResponseModel responseModel = new FilterIPOAmtRcrdResponseModel();
		try {
			long ipoMasterId = requestModel.getIpoMasterId();
			int ipoQty = requestModel.getShareQty();
			try {
				double handFee = 100.000;
				try {
					IPORecord ipoRecord = ipoMaintenanceDelegate
							.getIPORecord(ipoMasterId);
					handFee = ipoRecord.getHandFee().doubleValue();
					if (ipoRecord != null) {
						if (Consts.Global.Language.BIG5.equals(requestModel
								.getLanguage())) {
							ipoRecord.setIpoName_dsply(ipoRecord
									.getIpoName_big());
							ipoRecord.setProp_url_dsply(ipoRecord
									.getProp_url_tw());
						} else {
							if (Consts.Global.Language.GB.equals(requestModel
									.getLanguage())) {
								ipoRecord.setIpoName_dsply(ipoRecord
										.getIpoName_gb());
								ipoRecord.setProp_url_dsply(ipoRecord
										.getProp_url_cn());
							} else {
								ipoRecord.setIpoName_dsply(ipoRecord
										.getIpoName());
								ipoRecord.setProp_url_dsply(ipoRecord
										.getProp_url_en());
							}
						}
					}
					responseModel.setIpoRecord(ipoRecord);
				} catch (Exception e) {
					mcsInfo.error("getIPOAmtRcrd :: ", e);
				}
				BigDecimal ipoAmt = (BigDecimal) ipoQtyAmtDelegate
						.getAmtByMasterIdQty(ipoMasterId, ipoQty);
				IPORequest ipoRequest = new IPORequest();
				ipoRequest.setApplyQuantity(ipoQty);
				ipoAmt = new BigDecimal(ipoAmt.doubleValue() + handFee);
				ipoRequest.setDsptAmount(ipoAmt);
				if (ipoAmt != null) {
					mcsInfo.info("ipoAmt=" + ipoAmt + " ,ipoMasterId="
							+ ipoMasterId + " ,ipoQty=" + ipoQty);
					responseModel.setIpoRequest(ipoRequest);
				}
			} catch (Exception bme) {
				mcsInfo.error("getIPOAmtRcrd :: ", bme);
			}
			PropertyUtils.copyProperties(responseModel, requestModel);
		} catch (Exception e) {
			mcsInfo.error("thread :: " + Thread.currentThread().getName());
			mcsInfo.error("getIPOAmtRcrd exist exception :: message ::: "
					+ e.getMessage());
			responseModel.setReturnCode(Consts.Error.Code.CONNECT_IPO);
		}
		return responseModel;
	}

	public FilterIPOResponseModel getIPORecord(IPORequestModel requestModel) {
		FilterIPOResponseModel responseModel = new FilterIPOResponseModel();
		try {
			IPORecord result = ipoMaintenanceDelegate.getIPORecord(requestModel
					.getIpoMasterId());
			PropertyUtils.copyProperties(responseModel, requestModel);
			responseModel.setResult(result);
		} catch (Exception e) {
			mcsInfo.error("thread :: " + Thread.currentThread().getName());
			mcsInfo.error("getIPORecord exist exception :: message ::: "
					+ e.getMessage());
			responseModel.setReturnCode(Consts.Error.Code.CONNECT_IPO);
		}
		return responseModel;
	}

	public QueryCodeResponseModel getIPOQueryCode(IPORequestModel requestModel) {
		QueryCodeResponseModel responseModel = new QueryCodeResponseModel();
		try {
			PropertyUtils.copyProperties(responseModel, requestModel);
			IPORecord ipoRecord = ipoMaintenanceDelegate
					.getIPORecord(requestModel.getIpoMasterId());
			if (ipoRecord != null) {
				String currentLocale = requestModel.getLanguage();
				mcsInfo.info("now lang=" + currentLocale);
				if (Consts.Global.Language.BIG5.equals(currentLocale)) {
					ipoRecord.setIpoName_dsply(ipoRecord.getIpoName_big());
					ipoRecord.setProp_url_dsply(ipoRecord.getProp_url_tw());
				} else {
					if (Consts.Global.Language.GB.equals(currentLocale)) {
						ipoRecord.setIpoName_dsply(ipoRecord.getIpoName_gb());
						ipoRecord.setProp_url_dsply(ipoRecord.getProp_url_cn());
					} else {
						ipoRecord.setIpoName_dsply(ipoRecord.getIpoName());
						ipoRecord.setProp_url_dsply(ipoRecord.getProp_url_en());
					}
				}
				responseModel.setIpoRecord(ipoRecord);
			} else {
				mcsInfo.info("can nor get IPO Record for id "
						+ requestModel.getIpoMasterId());
				return responseModel;
			}

			try {
				IPORequest ipoRequest = ipoRequestDelegate.getIPORequest(
						requestModel.getAccountId(), requestModel
								.getIpoMasterId());
				if (ipoRequest != null && ipoRequest.getStatus() != null
						&& ipoRequest.getStatus().equals("received")) {
					long applyId = ipoRequest.getApplyId().longValue();
					responseModel.setIpoRequest(ipoRequest);
					try {
						IPOResult ipoResult = ipoResultDelegate
								.getIPOResult(applyId);
						if (ipoResult != null) {
							responseModel.setIpoResult(ipoResult);
						}
					} catch (Exception e) {
						mcsInfo.error("", e);
					}
				} else {
					// session.setAttribute("allowOnce","YES");
				}
			} catch (Exception e) {
				mcsInfo.error("", e);
			}
		} catch (Exception e) {
			mcsInfo.error("thread :: " + Thread.currentThread().getName());
			mcsInfo.error("getIPORecord exist exception :: message ::: "
					+ e.getMessage());
			responseModel.setReturnCode(Consts.Error.Code.CONNECT_IPO);
		}
		return responseModel;
	}

	public RemoveIPOResponseModel removeIPORequest(
			RemoveIPORequestModel requestModel) {
		RemoveIPOResponseModel responseModel = new RemoveIPOResponseModel();
		try {
			requestModel.setLoginId(FormatUtil.formatLoginId(requestModel
					.getLoginId()));
			VerifyPasswordRequestModel verifyPassReq = new VerifyPasswordRequestModel();
			PropertyUtils.copyProperties(verifyPassReq, requestModel);
			verifyPassReq.setLoginId(requestModel.getLoginId());
			verifyPassReq.setPassword(requestModel.getPassword());
			VerifyPasswordResponseModel verifyPassResp = verifyPassword(verifyPassReq);
			boolean canApply = verifyPassResp.getPasswordMatch().equals(
					Consts.Global.Flag.POSITIVE) ? true : false;
			if (!canApply) {
				responseModel
						.setReturnCode(Consts.Error.Code.TRAD_ERROR_WRONGPASS);
				mcsInfo.error("cancel ipo error,password is error!");
				PropertyUtils.copyProperties(responseModel, requestModel);
				return responseModel;
			} else {
				IPORecord ipoRecord = ipoMaintenanceDelegate
						.getIPORecord(requestModel.getIpoMasterId());
				if (ipoRecord != null) {
					String currentLocale = requestModel.getLanguage();
					mcsInfo.info("now lang=" + currentLocale);
					if (Consts.Global.Language.BIG5.equals(currentLocale)) {
						ipoRecord.setIpoName_dsply(ipoRecord.getIpoName_big());
						ipoRecord.setProp_url_dsply(ipoRecord.getProp_url_tw());
					} else {
						if (Consts.Global.Language.GB.equals(currentLocale)) {
							ipoRecord.setIpoName_dsply(ipoRecord
									.getIpoName_gb());
							ipoRecord.setProp_url_dsply(ipoRecord
									.getProp_url_cn());
						} else {
							ipoRecord.setIpoName_dsply(ipoRecord.getIpoName());
							ipoRecord.setProp_url_dsply(ipoRecord
									.getProp_url_en());
						}
					}
					responseModel.setIpoRecord(ipoRecord);
				}
				IPORequest ipoRequest = ipoRequestDelegate.getIPORequest(
						requestModel.getAccountId(), requestModel
								.getIpoMasterId());
				if (ipoRequest != null && ipoRequest.getStatus() != null
						&& ipoRequest.getStatus().equals("received")) {
					responseModel.setIpoRequest(ipoRequest);
				}
				Date applyDate = new Date();
				String nowTime = new Timestamp(applyDate.getTime()).toString();
				canApply = false;
				String deadLine = ipoRecord.getDeadLine().toString();
				String isClose = ipoRecord.getStatus();

				if (!"CLOSE".equals(isClose)
						&& deadLine.compareTo(nowTime) >= 0) {
					canApply = true;
				}
				if (!canApply) {
					mcsInfo.error("cancel ipo error,time is over!");
					responseModel.setReturnCode(Consts.Error.Code.IPO_TIMEOVER);
					return responseModel;
				}
				ipoRequestDelegate.removeIPORequest(ipoRequest.getApplyId());
			}
			PropertyUtils.copyProperties(responseModel, requestModel);
		} catch (Exception e) {
			mcsInfo.error("thread :: " + Thread.currentThread().getName());
			mcsInfo.error("removeIPORequest exist exception :: message ::: "
					+ e.getMessage());
			responseModel.setReturnCode(Consts.Error.Code.CONNECT_IPO);
		}
		return responseModel;
	}

	public VerifyPasswordResponseModel verifyPassword(
			VerifyPasswordRequestModel requestModel) {
		VerifyPasswordResponseModel responseModel = new VerifyPasswordResponseModel();
		try {
			requestModel.setPassword(EncryptPwd.Encrypt(requestModel
					.getPassword(), ""));
			responseModel = mcsService.callVerifyPassword(requestModel);
		} catch (Exception e) {
			mcsInfo.error("thread :: " + Thread.currentThread().getName());
			mcsInfo.error("verifyPassword exist exception :: message ::: "
					+ e.getMessage());
			responseModel.setReturnCode(Consts.Error.Code.CONNECT_MCS);
			responseModel.setResultForward(ForwardMappingUtil
					.getForward(Consts.Error.Code.SHOWMSG));
			return responseModel;
		}
		return responseModel;
	}

	// 3.3.1.2.3 查询已选购服务
	public ListSelectServiceResponseModel listSelectService(
			ListSelectServiceRequestModel listSelectServiceRequestModel) {
		return rtqService.listSelectService(listSelectServiceRequestModel);
	}

	public RTQProductResponseModel getRTQProductList(
			RTQProductRequestModel rTQProductRequestModel) {
		return rtqService.getRTQProductList(rTQProductRequestModel);
	}

	// rtq reserveService
	public ReserveServiceResponseModel reserveService(
			ReserveServiceRequestModel reserveServiceRequestModel) {
		return rtqService.reserveService(reserveServiceRequestModel);
	}

	// 更改自动续期
	public UserProductResponseModel updateUserProductStatus(
			UpdateUserProductRequestModel updateUserProductRequestModel) {
		return rtqService
				.updateUserProductStatus(updateUserProductRequestModel);
	}

	// 取消预订
	public UserProductResponseModel cancelReservedUserProduct(
			UpdateUserProductRequestModel updateUserProductRequestModel) {
		return rtqService
				.cancelReservedUserProduct(updateUserProductRequestModel);
	}

	// rtq purchaseService
	public PurchaseServiceResponseModel purchaseService(
			PurchaseServiceRequestModel purchaseServiceRequestModel) {
		return rtqService.purchaseService(purchaseServiceRequestModel);
	}

	public AccessRTQResponseModel accessRTQ(
			AccessRTQRequestModel accessRTQRequestModel) {
		return rtqService.accessRTQ(accessRTQRequestModel);
	}

	public AccessSHKResponseModel accessSHK(
			AccessSHKRequestModel accessSHKRequestModel) {
		return rtqService.accessSHK(accessSHKRequestModel);
	}

	public PurchasedProductResponseModel havePurchasedProduct(
			PurchasedProductRequestModel purchasedProductRequestModel) {
		return rtqService.havePurchasedProduct(purchasedProductRequestModel);
	}

	public UserNotificationEmailResponseModel getUserNotificationEmail(
			UserNotificationEmailRequestModel userNotificationEmailRequestModel) {
		return rtqService
				.getUserNotificationEmail(userNotificationEmailRequestModel);
	}

	public UserNotificationEmailResponseModel updateUserNotificationEmail(
			UserNotificationEmailRequestModel userNotificationEmailRequestModel) {
		return rtqService
				.updateUserNotificationEmail(userNotificationEmailRequestModel);
	}

	public UserProfileResponseModel findClientUserProfile(
			UserProfileRequestModel userProfileRequestModel) {
		return rtqService.findClientUserProfile(userProfileRequestModel);
	}

	public FundDepositResponseModel fundDeposit(
			FundDepositRequestModel fundDepositRequestModel) {
		FundDepositResponseModel responseModel = new FundDepositResponseModel();
		FundDeposit newFundDeposit = new FundDeposit();
		try {
			PropertyUtils.copyProperties(newFundDeposit,
					fundDepositRequestModel);
			newFundDeposit.setIsSended(Consts.Global.Flag.NEGATIVE);
			String requestNo = "FDC" + DateHelper.formatDate5(new Date());
			newFundDeposit.setRequestNo(requestNo);
			fundDepositDelegate.insertFundDeposit(newFundDeposit);
			responseModel.setRequestNo(requestNo);
		} catch (Exception e) {
			mcsInfo.error("thread :: " + Thread.currentThread().getName());
			mcsInfo.error("fundDeposit exist exception :: message ::: ", e);
			responseModel.setReturnCode(Consts.Error.Code.CONNECT_IPO);
		}
		return responseModel;
	}

	public FundDepositResponseModel transFundDeposit(
			FundDepositRequestModel fundDepositRequestModel) {
		FundDepositResponseModel responseModel = new FundDepositResponseModel();
		try {
			List<FundDeposit> fundDeposits = fundDepositDelegate
					.findFundDeposit();
			mcsInfo
					.info("transFundDeposit fundDepositDelegate.findFundDeposit() fundDeposits.size="
							+ fundDeposits.size());
			if (fundDeposits != null && fundDeposits.size() > 0) {
				mcsInfo.info("transFundDeposit begin :: ");
				// sendmail
				String fileName = "/tmp/";
				String fund_deposit_filePath = PropertyConfig
						.getCommonProperty(Consts.Global.Common.FUND_DEPOSIT_FILEPATH);
				if (fund_deposit_filePath != null) {
					fileName = fund_deposit_filePath;
				}
				StringBuilder sb = new StringBuilder();
				sb.append("Fund Deposit Checking ");
				sb.append("<br/>");
				email.sendEmail("Fund Deposit Checking", sb.toString(),
						fileName, fundDeposits);
				// update status
				for (Iterator iterator = fundDeposits.iterator(); iterator
						.hasNext();) {
					FundDeposit fundDeposit = (FundDeposit) iterator.next();
					fundDeposit.setIsSended(Consts.Global.Flag.POSITIVE);
				}
				fundDepositDelegate.updateFundDeposit(fundDeposits);
				mcsInfo.info("transFundDeposit end :: ");
			}
		} catch (Exception e) {
			mcsInfo.error("thread :: " + Thread.currentThread().getName());
			mcsInfo
					.error("transFundDeposit exist exception :: message ::: ",
							e);
			responseModel.setReturnCode(Consts.Error.Code.CONNECT_IPO);
		}
		return responseModel;
	}

	public EMCResponseModel getEMCMsgCounts(EMCRequestModel emcRequestModel) {
		EMCResponseModel responseModel = new EMCResponseModel();
		try {
			int msgCounts = emcUtil.getMsgCountByReceive(emcRequestModel
					.getToken(), emcRequestModel.getClientId(), emcRequestModel
					.getCustCode());
			responseModel.setMsgCounts(msgCounts);
		} catch (Exception e) {
			mcsInfo.error("thread :: " + Thread.currentThread().getName());
			mcsInfo.error("getEMCMsgCounts exist exception :: message ::: ", e);
			responseModel.setReturnCode(Consts.Error.Code.CONNECT_IPO);
		}
		return responseModel;
	}

	public EMCResponseModel getEMCMsgURL(EMCRequestModel emcRequestModel) {
		EMCResponseModel responseModel = new EMCResponseModel();
		try {
			String emcURL = emcUtil.showInfoUrl(emcRequestModel.getCustCode(),
					emcRequestModel.getClientId(), LanguageUtil
							.lang2locale(emcRequestModel.getLanguage()));
			responseModel.setEmcURL(emcURL);
		} catch (Exception e) {
			mcsInfo.error("thread :: " + Thread.currentThread().getName());
			mcsInfo.error("getEMCMsgCounts exist exception :: message ::: ", e);
			responseModel.setReturnCode(Consts.Error.Code.CONNECT_IPO);
		}
		return responseModel;
	}

	// ADD by Arthur Chen on 20110421 for eipo
	public FilterEIPOListResponseModel getAllEIPORecord(
			EIPORequestModel requestModel) {
		FilterEIPOListResponseModel responseModel = new FilterEIPOListResponseModel();
		String lang = requestModel.getLanguage();
		try {
			EIPOResponse respnose = EIPOClientUtil.eipoClient
					.enquiryAllIposByLang(requestModel.getAccountId(),LangUtil.getLang(lang));
			List ipoMasterList = new ArrayList();
			if (Constants.EIPO_SUCCESS.equals(respnose.getReturnCode())) {
				mcsInfo.debug("get EIPO all master success.");
				IpoMasterRes allMasterIpoRes = (IpoMasterRes) respnose;
				List<IpoMaster> masterList = allMasterIpoRes.getIpoList();
				mcsInfo.debug("EIPO master Size = " + masterList.size());
				for (IpoMaster master : masterList) {
					ipoMasterList.add(new EIPOMasterEntry(master, requestModel
							.getLocale(), requestModel.getMr()));
					mcsInfo.debug("Master Detail: "
							+ EIPOClientUtil.getToString(master));
				}
//				Collections.sort(ipoMasterList, new EIPOMasterEntryComparator());
			} else {
				mcsInfo.error("EIPOExKey >>>" + respnose.getReturnCode());
				throw new EIPOServiceProviderException(
						"getIPOMasterList Exception.", "label.eipo.error."
								+ respnose.getReturnCode(), respnose
								.getErrorParams());
			}
			responseModel.setResult(ipoMasterList);
		} catch (Exception e) {
			mcsInfo.error("thread :: " + Thread.currentThread().getName());
			mcsInfo.error("getAllIPORecord exist exception :: message ::: "
					+ e.getMessage());
			responseModel.setReturnCode(Consts.Error.Code.ERRORCODE_SYS);
		}
		return responseModel;
	}
	// ADD by Arthur xli on 20110421 for eipo
	public EIPOResponse getEIPOSubscriptionEnquiry(EIPOSubscriptionEnquiryRequestModel eipoRequestModel) {
		EIPOResponse respnose = new EIPOResponse();
		String acId = eipoRequestModel.getAccountId();
		String lang = eipoRequestModel.getLanguage();
		try {
			respnose = EIPOClientUtil.eipoClient.enquirySuscription(acId,LangUtil.getLang(lang));
		} catch (Exception e) {
			mcsInfo.error("thread :: " + Thread.currentThread().getName());
			mcsInfo.error("getEIPOSubscriptionEnquiry exist exception :: message ::: ",e);
			respnose.setReturnCode(Consts.Error.Code.ERRORCODE_SYS);
		}
		return respnose;
	}
	
	public EIPOResponse getEIPOSubscriptionDetail(EIPOSubscriptionDetailRequestModel eipoRequestModel) {
		EIPOResponse response = new EIPOResponse();
		String subscriptionId = eipoRequestModel.getSubscriptionId();
		String lang = eipoRequestModel.getLanguage();
		try {
			response = EIPOClientUtil.eipoClient.enquiryIpoSubscriptionById(subscriptionId, LangUtil.getLang(lang));
		} catch (Exception e) {
			mcsInfo.error("thread :: " + Thread.currentThread().getName());
			mcsInfo.error("getEIPOSubscriptionDetail exist exception :: message ::: ",e);
			response.setReturnCode(Consts.Error.Code.ERRORCODE_SYS);
		}
		return response;
	}

	public CancelEIPOSubscriptionResponseModel getCancelEIPOSubscription(CancelEIPOSubscriptionRequestModel eipoRequestModel) {
		CancelEIPOSubscriptionResponseModel response = new CancelEIPOSubscriptionResponseModel();
		String acId = eipoRequestModel.getAccountId();
		String ipoId = eipoRequestModel.getIpoMasterId();
		String classCode = eipoRequestModel.getClientClassCode();
		String subscriptionId = eipoRequestModel.getSubscriptionId();
		String lang = eipoRequestModel.getLanguage();
		Locale locale = eipoRequestModel.getLocale();
		MessageResources mr = eipoRequestModel.getMr();
		try {
			EIPOMasterEntry masterEntry = null;
			IpoSubscriptionDto ipoSubscriptionDto = null;
			EIPOResponse eipoMasterResponse = EIPOClientUtil.eipoClient.enquiryIpoMasterDetail(acId,ipoId,classCode,LangUtil.getLang(lang));
			if (Consts.Global.Forward.SUCCESS.equals(eipoMasterResponse.getReturnCode())){
				IpoMasterRes ipoMasterRes = (IpoMasterRes)eipoMasterResponse;
				List<IpoMaster> masterList = ipoMasterRes.getIpoList();
				if (masterList != null && masterList.size() > 0) {
					masterEntry = new EIPOMasterEntry(masterList.get(0),locale,mr);
				}
			} else {
				response.setReturnCode("label.eipo.error."+eipoMasterResponse.getReturnCode());
				return response;
			}
			EIPOResponse eipoSubscriptionResponse = EIPOClientUtil.eipoClient.enquiryIpoSubscriptionById(subscriptionId,LangUtil.getLang(lang));
			if (Consts.Global.Forward.SUCCESS.equals(eipoSubscriptionResponse.getReturnCode())){
				SubscriptionRes subscriptionRes = (SubscriptionRes)eipoSubscriptionResponse;
				List<IpoSubscriptionDto> ipoList = subscriptionRes.getSubscriptionList();
				if (ipoList != null && ipoList.size() > 0) {
					ipoSubscriptionDto = ipoList.get(0);
				}
			} else {
				response.setReturnCode("label.eipo.error."+eipoSubscriptionResponse.getReturnCode());
				return response;
			}
			EIPOSubscriptionDto eipoSubDto = new EIPOSubscriptionDto();
			PropertyUtils.copyProperties(eipoSubDto, ipoSubscriptionDto);
			eipoSubDto.setIsFaultSubscription(ipoSubscriptionDto.isIsFaultSubscription());
			eipoSubDto.setIsSplit(ipoSubscriptionDto.isIsSplit());
			response.setMasterEntry(masterEntry);
			response.setEipoSubscriptionDto(eipoSubDto);
			response.setLocale(locale);
			response.setMr(mr);
			response.setReturnCode(Consts.Global.Forward.SUCCESS);
		} catch (Exception e) {
			mcsInfo.error("thread :: " + Thread.currentThread().getName());
			mcsInfo.error("getCancelEIPOSubscription exist exception :: message ::: ",e);
			response.setReturnCode(Consts.Error.Code.ERRORCODE_SYS);
		}
		return response;
	}
	public EIPOResponse getEIPOSubscriptionSubmit(EIPOSubscriptionSubmitRequestModel eipoRequestModel) {
		EIPOResponse response = new EIPOResponse();
		IpoSubscriptionDto ipoDto = new IpoSubscriptionDto();
		try {
			eipoRequestModel.setLoginId(FormatUtil.formatLoginId(eipoRequestModel.getLoginId()));
			VerifyPasswordRequestModel verifyPassReq = new VerifyPasswordRequestModel();
			PropertyUtils.copyProperties(verifyPassReq, eipoRequestModel);
			verifyPassReq.setLoginId(eipoRequestModel.getLoginId());
			verifyPassReq.setPassword(eipoRequestModel.getPassword());
			VerifyPasswordResponseModel verifyPassResp = verifyPassword(verifyPassReq);
			boolean canApply = verifyPassResp.getPasswordMatch().equals(Consts.Global.Flag.POSITIVE) ? true : false;
			if (!canApply) {
				response.setReturnCode(Consts.Error.Code.TRAD_ERROR_WRONGPASS);
				mcsInfo.error("cancel ipo error,password is error!");
				return response;
			}
			EIPOMasterEntry ipoSubscription = eipoRequestModel.getIpoSubscription();
			ipoDto.setIpoId(new Long(ipoSubscription.getIpoId()));
			ipoDto.setAccountId(eipoRequestModel.getUser().getTradeInfoObject().getTradingAccount());
			ipoDto.setSubscriptionType("FULLY_PAID");
			ipoDto.setAppliedQuantity(ipoSubscription.getAppliedShare().longValue());
			ipoDto.setAppliedAmount(ipoSubscription.getAmountPayable());
			ipoDto.setTradeChannelGroupCode("ONLINE");
			ipoDto.setDepositRate(new BigDecimal(1));
			//add miscFee
			double miscFee = 0;
			if (null != ipoSubscription.getMiscFee()) {
				miscFee = ipoSubscription.getMiscFee().doubleValue();
			}
			ipoDto.setMiscFeeAmount(new BigDecimal(miscFee));
			ipoDto.setHandlingChargeAmount(ipoSubscription.getHandlingCharge());
			
			ipoDto.setDepositAmount(
					new BigDecimal(ipoSubscription.getAmountPayable().doubleValue()
					+ ipoSubscription.getHandlingCharge().doubleValue()
					+ miscFee));
			ipoDto.setLastUpdatedBy("EIPO");
			IpoSubscrptnNotifyDto notifyDto = null;
			if (eipoRequestModel.isEmail()) {
				notifyDto = new IpoSubscrptnNotifyDto();
				notifyDto.setNotifyMethodType("EMAIL");
				ipoDto.getIpoSubscriptionNotificationDtoList().add(notifyDto);
			}
			if (eipoRequestModel.isPhoto()) {
				notifyDto = new IpoSubscrptnNotifyDto();
				notifyDto.setNotifyMethodType("SMS");
				ipoDto.getIpoSubscriptionNotificationDtoList().add(notifyDto);
			}			
			mcsInfo.debug("webTrading add subscription detail: " + EIPOClientUtil.getToString(ipoDto));			
			response = EIPOClientUtil.eipoClient.addIpoSubscription(ipoDto);
		} catch (Exception e) {
			mcsInfo.error("thread :: " + Thread.currentThread().getName());
			mcsInfo.error("getCancelEIPOSubscriptionSubmit exist exception :: message ::: ",e);
			response.setReturnCode(Consts.Error.Code.ERRORCODE_SYS);
		}
		return response;
	}

	public EIPOResponse getCancelEIPOSubscriptionSubmit(CancelEIPOSubscriptionSubmitRequestModel eipoRequestModel) {
		EIPOResponse response = new EIPOResponse();
		String subscriptionId = eipoRequestModel.getSubscriptionId();
		try {
			eipoRequestModel.setLoginId(FormatUtil.formatLoginId(eipoRequestModel.getLoginId()));
			VerifyPasswordRequestModel verifyPassReq = new VerifyPasswordRequestModel();
			PropertyUtils.copyProperties(verifyPassReq, eipoRequestModel);
			verifyPassReq.setLoginId(eipoRequestModel.getLoginId());
			verifyPassReq.setPassword(eipoRequestModel.getPassword());
			VerifyPasswordResponseModel verifyPassResp = verifyPassword(verifyPassReq);
			boolean canApply = verifyPassResp.getPasswordMatch().equals(Consts.Global.Flag.POSITIVE) ? true : false;
			if (!canApply) {
				response.setReturnCode(Consts.Error.Code.TRAD_ERROR_WRONGPASS);
				mcsInfo.error("cancel ipo error,password is error!");
				return response;
			}
			response = EIPOClientUtil.eipoClient.cancelIpoSubscription(subscriptionId);
		} catch (Exception e) {
			mcsInfo.error("thread :: " + Thread.currentThread().getName());
			mcsInfo.error("getCancelEIPOSubscriptionSubmit exist exception :: message ::: ",e);
			response.setReturnCode(Consts.Error.Code.ERRORCODE_SYS);
		}
		return response;
	}

	public EIPOMasterDetailResponseModel getEIPOMasterDetail(EIPOMasterDetailRequestModel eipoRequestModel) {
		EIPOMasterDetailResponseModel response = new EIPOMasterDetailResponseModel();
		String acId = eipoRequestModel.getAccountId();
		String ipoMasterId = eipoRequestModel.getIpoMasterId();
		String classCode = eipoRequestModel.getClientClassCode();
		String lang = eipoRequestModel.getLanguage();
		Locale locale = eipoRequestModel.getLocale();
		MessageResources mr = eipoRequestModel.getMr();
		try {
			EIPOMasterEntry masterEntry = null;
			EIPOResponse eipoMasterDetailResponse = EIPOClientUtil.eipoClient.enquiryIpoMasterDetail(acId,ipoMasterId,classCode,LangUtil.getLang(lang));
			if (Consts.Global.Forward.SUCCESS.equals(eipoMasterDetailResponse.getReturnCode())){
				IpoMasterRes allMasterIpoRes = (IpoMasterRes)eipoMasterDetailResponse;
				List<IpoMaster> masterList = allMasterIpoRes.getIpoList();
				if (masterList != null && masterList.size() > 0) {
					masterEntry = new EIPOMasterEntry(masterList.get(0),locale,mr);
				}
			} else {
				response.setReturnCode("label.eipo.error."+eipoMasterDetailResponse.getReturnCode());
				return response;
			}
			response.setMasterEntry(masterEntry);
			response.setLocale(locale);
			response.setMr(mr);
			response.setReturnCode(Consts.Global.Forward.SUCCESS);
		} catch (Exception e) {
			mcsInfo.error("thread :: " + Thread.currentThread().getName());
			mcsInfo.error("getEIPOMasterDetail exist exception :: message ::: ",e);
			response.setReturnCode(Consts.Error.Code.ERRORCODE_SYS);
		}
		return response;
	}
	public EIPOSubResponseModel getEIPOSubRecord(EIPORequestModel requestModel) throws EIPOServiceProviderException{
		EIPOSubResponseModel responseModel = new EIPOSubResponseModel();
		EIPOMasterEntry masterEntry = null;
		String classCode = requestModel.getClientClassCode();
		String lang = requestModel.getLanguage();
		EIPOResponse respnose = EIPOClientUtil.eipoClient.enquiryIpoMasterDetail(requestModel.getAccountId(),
				String.valueOf(requestModel.getIpoMasterId()) ,classCode , LangUtil.getLang(lang));
		if (Constants.EIPO_SUCCESS.equals(respnose.getReturnCode())) {
			mcsInfo.debug("get master detail success.");
			IpoMasterRes allMasterIpoRes = (IpoMasterRes)respnose;
			
			List<IpoMaster> masterList = allMasterIpoRes.getIpoList();
			mcsInfo.debug("master detail Size = " + masterList.size());
			if (masterList != null && masterList.size() > 0) {
				masterEntry = new EIPOMasterEntry(masterList.get(0),requestModel
						.getLocale(), requestModel.getMr());
				mcsInfo.debug("Master Detail: " + EIPOClientUtil.getToString(masterList.get(0)));
			}
			responseModel.setMasterEntry(masterEntry);
		} else {
			mcsInfo.error("EIPOExKey >>>" + respnose.getReturnCode());
			throw new EIPOServiceProviderException("getIPOMasterDetail Exception.",
					"label.eipo.error." + respnose.getReturnCode(),respnose.getErrorParams());
		}
		return responseModel;
	}
	public EIPOSubResponseDetailModel getEIPODetailSubRecord(EIPORequestModel requestModel) throws EIPOServiceProviderException{
		EIPOSubResponseDetailModel responseModel = new EIPOSubResponseDetailModel();
		EIPOMasterEntry masterEntry = null;
		String classCode = requestModel.getClientClassCode();
		String lang = requestModel.getLanguage();
		EIPOResponse respnose = EIPOClientUtil.eipoClient.enquiryIpoMasterDetail(requestModel.getAccountId(),
				String.valueOf(requestModel.getIpoMasterId()) ,classCode , LangUtil.getLang(lang));
		if (Constants.EIPO_SUCCESS.equals(respnose.getReturnCode())) {
			mcsInfo.debug("get master detail success.");
			IpoMasterRes allMasterIpoRes = (IpoMasterRes)respnose;
			
			List<IpoMaster> masterList = allMasterIpoRes.getIpoList();
			mcsInfo.debug("master detail Size = " + masterList.size());
			if (masterList != null && masterList.size() > 0) {
				masterEntry = new EIPOMasterEntry(masterList.get(0),requestModel
						.getLocale(), requestModel.getMr());
				mcsInfo.debug("Master Detail: " + EIPOClientUtil.getToString(masterList.get(0)));
			}
			responseModel.setMasterEntry(masterEntry);
		} else {
			mcsInfo.error("EIPOExKey >>>" + respnose.getReturnCode());
			throw new EIPOServiceProviderException("getIPOMasterDetail Exception.",
					"label.eipo.error." + respnose.getReturnCode(),respnose.getErrorParams());
		}
		return responseModel;
	}
}
