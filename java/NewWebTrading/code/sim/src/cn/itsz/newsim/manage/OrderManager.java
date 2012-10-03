package cn.itsz.newsim.manage;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.itsz.newsim.common.ActionUtil;
import cn.itsz.newsim.common.Constants;
import cn.itsz.newsim.dao.ParameterDao;
import cn.itsz.newsim.dao.hibernate.model.ParameterModel;
import cn.itsz.newsim.dto.request.CancelOrderRequest;
import cn.itsz.newsim.dto.request.EnquireRTQRequest;
import cn.itsz.newsim.dto.request.EnquiryPositionRequest;
import cn.itsz.newsim.dto.request.InputOrderRequest;
import cn.itsz.newsim.dto.request.ListOrderRequest;
import cn.itsz.newsim.dto.request.ModfiyOrderRequest;
import cn.itsz.newsim.dto.request.OrderDetailRequest;
import cn.itsz.newsim.dto.request.OrderFeeRequest;
import cn.itsz.newsim.dto.request.TradeListRequest;
import cn.itsz.newsim.dto.response.CancelOrderResponse;
import cn.itsz.newsim.dto.response.EnquiryPositionResponse;
import cn.itsz.newsim.dto.response.InputOrderResponse;
import cn.itsz.newsim.dto.response.ListOrderResponse;
import cn.itsz.newsim.dto.response.ModfiyOrderResponse;
import cn.itsz.newsim.dto.response.OrderDetailResponse;
import cn.itsz.newsim.dto.response.OrderFeeResponse;
import cn.itsz.newsim.dto.response.TradeListResponse;
import cn.itsz.newsim.dto.response.entity.CalOrderFeeResponseEntity;
import cn.itsz.newsim.dto.response.entity.EnquireRTQResponseEntity;
import cn.itsz.newsim.dto.response.entity.EnquireShortRTQResponseEntity;
import cn.itsz.newsim.dto.response.entity.LoginResponseEntity;
import cn.itsz.newsim.dto.response.entity.OrderDTO;
import cn.itsz.newsim.dto.response.entity.ShareHoldingInfo;
import cn.itsz.newsim.dto.response.entity.TradeInfo;
import cn.itsz.newsim.exception.ITSZException;

@Component
public class OrderManager {

	private static Log logger = LogFactory.getLog(OrderManager.class);
	private Integer ORDERIDCOUNT = 0;
	private Date todayStart;
	private Date todayEnd;
	@Autowired
	private QsManager qsManager;
	@Autowired
	private ParameterDao parameterDao;
	//loginId map orderList
	private Map<String, LinkedList<OrderDTO>> accOrders = new HashMap<String, LinkedList<OrderDTO>>();
	
	protected void init() {
		Calendar calStart = Calendar.getInstance();
		calStart.set(Calendar.HOUR_OF_DAY, getTime(Constants.Parameter.START_TIME));
		calStart.set(Calendar.MINUTE, 0);
		calStart.set(Calendar.SECOND, 0);
		todayStart = calStart.getTime();
		logger.info("TodayStart: " + todayStart);
		Calendar calEnd = Calendar.getInstance();
		calEnd.set(Calendar.HOUR_OF_DAY, getTime(Constants.Parameter.END_TIME));
		calEnd.set(Calendar.MINUTE, 0);
		calEnd.set(Calendar.SECOND, 0);
		todayEnd = calEnd.getTime();
		logger.info("TodayEnd: " + todayEnd);
	}
	
	private int getTime(String name) {
		ParameterModel param = parameterDao.findParameter(name);
		ITSZException exception = new ITSZException();
		if (null != param) {
			String v = param.getPvalue();
			if (null != v) {
				try {
					int time = Integer.parseInt(v);
					return time;
				} catch (NumberFormatException e) {
					exception.setErrorMessage("Parameter: \""+ name + "\" value is not number");
					throw exception;
				}
			} else {
				exception.setErrorMessage("Parameter: \""+ name + "\" value is null");
				throw exception;
			}
		} else {
			exception.setErrorMessage("Parameter: \""+ name + "\" not found");
			throw exception;
		}
	}
	
	public void initAccount(String loginId) {
		accOrders.remove(loginId);
	}
	
	public OrderFeeResponse calOrderFee(OrderFeeRequest orderFeeRequest) {
		OrderFeeResponse orderFeeRes = new OrderFeeResponse();
		CalOrderFeeResponseEntity orderFeeResponse = new CalOrderFeeResponseEntity();
		//netAmount 总金额
		//transactionAmount 交易金額 qty*price
		//commonCharges 佣金>=50 transactionAmount*0.05%
		//stampCharge 厘印費<=1  transactionAmount×0.1%
		//levyCharge 交易徵費 transactionAmount×0.005%
		//tradingFee 交易費 transactionAmount×0.003%
		//結算費 ccassCharge 中央結算系統股份交收費 <= 100 >=2 transactionAmount×0.002%
		double transactionAmount = orderFeeRequest.getOrderQuantity().longValue()
				* orderFeeRequest.getOrderPrice().doubleValue();
		double commonCharges = transactionAmount * 0.05 / 100;
		if (commonCharges < 25) {
			commonCharges = 25;
		}
		double stampCharge = transactionAmount * 0.1 / 100;
		if (stampCharge < 1) {
			stampCharge = 1;
		}
		double levyCharge = transactionAmount * 0.005 / 100;
		double tradingFee = transactionAmount * 0.003 / 100;
		double ccassCharge = transactionAmount * 0.002 / 100;
		if (ccassCharge > 100) {
			ccassCharge = 100;
		}
		if (ccassCharge < 2) {
			ccassCharge = 2;
		}
		double netAmount = 0;
		if (Constants.OrderSide.BUY.equals(orderFeeRequest.getOrderSide())) {
			netAmount = transactionAmount + commonCharges + stampCharge + levyCharge + tradingFee + ccassCharge;
		} else {
			netAmount = transactionAmount - commonCharges - stampCharge - levyCharge - tradingFee - ccassCharge;
		}
		orderFeeResponse.setNetAmount(new BigDecimal(netAmount));
		orderFeeResponse.setTransactionAmount(new BigDecimal(transactionAmount));
		orderFeeResponse.setCommonCharges(new BigDecimal(commonCharges));
		orderFeeResponse.setStampCharge(new BigDecimal(stampCharge));
		orderFeeResponse.setLevyCharge(new BigDecimal(levyCharge));
		orderFeeResponse.setTradingFee(new BigDecimal(tradingFee));
		orderFeeResponse.setCcassCharge(new BigDecimal(ccassCharge));
		orderFeeRes.setCalOrderFeeResponse(orderFeeResponse);
		
		orderFeeRes.setOrderPrice(orderFeeRequest.getOrderPrice());
		orderFeeRes.setOrderQuantity(orderFeeRequest.getOrderQuantity());
		orderFeeRes.setOrderSide(orderFeeRequest.getOrderSide());
		orderFeeRes.setOrderType(orderFeeRequest.getOrderType());
		orderFeeRes.setInstrCode(orderFeeRequest.getInstrCode());
		orderFeeRes.setConditionType(orderFeeRequest.getConditionType());
		orderFeeRes.setTriggerPrice(orderFeeRequest.getTriggerPrice());
		
		Collection<String> instrCodes = new HashSet<String>();
		instrCodes.add(orderFeeRequest.getInstrCode());
		List<EnquireRTQResponseEntity> rtqRes = enquirySimpleRTQInfo(instrCodes, orderFeeRequest.getLanguage());
		if (rtqRes != null && rtqRes.size() > 0) {
			orderFeeRes.setCurrency(rtqRes.get(0).getCurrency());
		}
		return orderFeeRes;
	}
	
	public InputOrderResponse addOrder(InputOrderRequest inputOrderRequest) {
		InputOrderResponse res = new InputOrderResponse();
		if (StringUtils.isBlank(inputOrderRequest.getInstrCode())) {
			logger.warn("InstrCode is Blank");
			ActionUtil.packagingAndThrowException(Constants.ErrorKey.NSIM_00005);
		} else if (inputOrderRequest.getOrderPrice() == null) {
			logger.warn("OrderPrice is Blank");
			ActionUtil.packagingAndThrowException(Constants.ErrorKey.NSIM_00006);
		} else if (inputOrderRequest.getOrderQuantity() == null) {
			logger.warn("OrderQuantity is Blank");
			ActionUtil.packagingAndThrowException(Constants.ErrorKey.NSIM_00007);
		} else {
			Collection<String> instrCodes = new HashSet<String>();
			instrCodes.add(inputOrderRequest.getInstrCode());
			List<EnquireRTQResponseEntity> rtqRes = enquirySimpleRTQInfo(instrCodes,inputOrderRequest.getLanguage());
			OrderDTO orderDTO = packingOrderDTO(inputOrderRequest);
			if (rtqRes != null && rtqRes.size() > 0) {
				EnquireRTQResponseEntity rtqEntity = rtqRes.get(0);
				checkStockCode(rtqEntity);
				checkOrderSide(inputOrderRequest, orderDTO);
				checkPrice(orderDTO, rtqEntity);
				orderDTO.setInstrName(rtqEntity.getName());
				orderDTO.setOrderId(generateOrderId());
				orderDTO.setOrderRemark(Constants.OrderRemark.NORMAL);
				orderDTO.setChannelType(Constants.Channel.NWEB);
				orderDTO.setCurrency(rtqEntity.getCurrency());
				LoginResponseEntity entity = WebActiveUserManager.getInstance().getUserEntity(inputOrderRequest.getLoginId());
				if (isTradeTime()) {
					if (checkMos(orderDTO, entity)) {
						boolean flag=matchingPrice(rtqEntity, orderDTO);
						if(!flag&&Constants.OrderSide.BUY.equals(orderDTO.getOrderSide())){
							orderDTO.setTradeAvgPrice(orderDTO.getOrderPrice());
							orderDTO.setFilledQty(orderDTO.getOutstandingQuantity());
							trunPurchasingPower(orderDTO);
							orderDTO.setFilledQty(new BigDecimal(0));
							
						}
					} else {
						orderDTO.setRejectMessage(Constants.ErrorKey.NSIM_00020);
						orderDTO.setOrderState(Constants.OrderStatus.REJECTED);
					}
				} else {
					orderDTO.setRejectMessage(Constants.ErrorKey.NSIM_00012);
					orderDTO.setOrderState(Constants.OrderStatus.REJECTED);
				}
			} else {
				orderDTO.setOrderId(generateOrderId());
			}
			res.setOrderDTO(insertAccOrder(orderDTO));
		}
		
		return res;
	}
	
	private void checkOrderSide(InputOrderRequest inputOrderRequest, OrderDTO orderDTO) {
		if (Constants.OrderSide.SELL.equals(orderDTO.getOrderSide())) {
			EnquiryPositionRequest request = new EnquiryPositionRequest();
			request.setLoginId(inputOrderRequest.getLoginId());
			request.setLanguage(inputOrderRequest.getLanguage());
			EnquiryPositionResponse res = this.listPosition(request);
			if (null != res) {
				Collection<ShareHoldingInfo> shareHoldingInfoCol = res.getShareHoldingInfoCol();
				if (null != shareHoldingInfoCol) {
					for (ShareHoldingInfo info : shareHoldingInfoCol) {
						if (orderDTO.getInstrCode().equals(info.getInstrCode()) &&
							info.getMaxSellableQuantity().compareTo(orderDTO.getOutstandingQuantity()) >= 0) {
							return;
						}
					}
				}
			}
			logger.warn("Sell space");
			ActionUtil.packagingAndThrowException(Constants.ErrorKey.NSIM_00014);
		}
	}
	
	private boolean checkMos(OrderDTO orderDTO, LoginResponseEntity entity) {
		if (Constants.OrderSide.BUY.equals(orderDTO.getOrderSide())) {
			OrderFeeRequest feeRequest = new OrderFeeRequest();
			feeRequest.setOrderPrice(orderDTO.getOrderPrice());
			feeRequest.setOrderQuantity(orderDTO.getOutstandingQuantity());
			feeRequest.setOrderSide(orderDTO.getOrderSide());
			//不用计算各种费用，就按各自价格*股数
			//OrderFeeResponse orderFeeResponse = this.calOrderFee(feeRequest);
			BigDecimal amount=feeRequest.getOrderPrice().multiply(feeRequest.getOrderQuantity());
			/*
			if (entity.getTotalPurchasingPower().subtract(entity.getOnHoldBalance()).compareTo(orderFeeResponse.getCalOrderFeeResponse().getNetAmount()) >= 0) {
				
				//entity.setOnHoldBalance(entity.getOnHoldBalance().add(orderFeeResponse.getCalOrderFeeResponse().getNetAmount()));
				//应该是那股价数量*股价
				entity.setOnHoldBalance(feeRequest.getOrderPrice().multiply(feeRequest.getOrderQuantity()));
				
				orderDTO.setHoldAmount(orderFeeResponse.getCalOrderFeeResponse().getNetAmount());
			} else {
				return false;
			}
			*/
			
			//冻结金额等于股票数量*股票价格
			//上次的购买上限减去当次冻结金额加下各种费用
			//MOS-onholdblance-fee
			if(entity.getTotalPurchasingPower().compareTo(amount)>=0){
				//应该是那股价数量*股价
				entity.setOnHoldBalance(entity.getOnHoldBalance().add(amount));
				orderDTO.setHoldAmount(amount);
			}else{
				return false;
			}
		}
		return true;
	}
	
	private boolean isTradeTime() {
		init();
		Date now = new Date();
		if (now.compareTo(todayStart) < 0 || now.compareTo(todayEnd) > 0) {
			logger.info("Trading time has passed: " + now.getTime());
			return false;
		} else {
			return true;
		}
	}
	
	private void checkStockCode(EnquireRTQResponseEntity rtqEntity) {
		if (rtqEntity.getStatus() == 1) {
			logger.warn(rtqEntity.getCode() + " no exist.");
			ActionUtil.packagingAndThrowException(Constants.ErrorKey.NSIM_00009);
		} else if (rtqEntity.getStatus() == 2) {
			logger.warn(rtqEntity.getCode() + " suspend.");
			ActionUtil.packagingAndThrowException(Constants.ErrorKey.NSIM_00011);
		}
	}
	
	private void checkPrice(OrderDTO orderDTO, EnquireShortRTQResponseEntity rtqEntity) {
		BigDecimal orderPrice = orderDTO.getOrderPrice();
		BigDecimal spread = ActionUtil.calSpread(orderPrice);
		BigDecimal tradePrice = new BigDecimal(rtqEntity.getPrice());
		this.dealCheckPriceLogic(orderDTO, tradePrice, orderPrice, spread);
	}
	
	private void checkPrice(BigDecimal neworderPrice,OrderDTO orderDTO, EnquireShortRTQResponseEntity rtqEntity) {
		BigDecimal orderPrice = neworderPrice;
		BigDecimal spread = ActionUtil.calSpread(orderPrice);
		BigDecimal tradePrice = new BigDecimal(rtqEntity.getPrice());
		this.dealCheckPriceLogic(orderDTO, tradePrice, orderPrice, spread);
	}
	
	private void dealCheckPriceLogic(OrderDTO orderDTO, BigDecimal tradePrice, BigDecimal orderPrice, BigDecimal spread) {
		BigDecimal EIGHT = new BigDecimal("8");
		if (Constants.OrderSide.BUY.equals(orderDTO.getOrderSide())) {
			if (tradePrice.compareTo(BigDecimal.ZERO) <= 0
					|| orderPrice.compareTo(tradePrice.add(spread)) > 0
					|| orderPrice.compareTo(tradePrice.subtract(
							spread.multiply(EIGHT))) < 0) {
				logger.warn("OrderPrice is error");
				ActionUtil.packagingAndThrowException(Constants.ErrorKey.NSIM_00006);
			}
		} else if (tradePrice.compareTo(BigDecimal.ZERO) <= 0
					|| orderPrice.compareTo(tradePrice.subtract(spread)) < 0
					|| orderPrice.compareTo(tradePrice.add(
							spread.multiply(EIGHT))) > 0) {
			logger.warn("OrderPrice is error");
			ActionUtil.packagingAndThrowException(Constants.ErrorKey.NSIM_00006);
		}
	}
	
	
	public void removeAllOrder() {
		//init();
		accOrders.clear();
	}
	
	public ModfiyOrderResponse modfiyOrder(ModfiyOrderRequest modfiyOrderRequest) {
		ModfiyOrderResponse res = new ModfiyOrderResponse();
		if (modfiyOrderRequest.getNewOrderPrice() == null) {
			logger.warn("NewOrderPrice is Blank.");
			ActionUtil.packagingAndThrowException(Constants.ErrorKey.NSIM_00006);
		} else if (modfiyOrderRequest.getNewOrderQty() == null) {
			logger.warn("NewOrderQty is Blank.");
			ActionUtil.packagingAndThrowException(Constants.ErrorKey.NSIM_00007);
		} else if (StringUtils.isBlank(modfiyOrderRequest.getOrderId())) {
			logger.warn("OrderId is Blank.");
			ActionUtil.packagingAndThrowException(Constants.ErrorKey.NSIM_00008);
		} else {
			if (accOrders.containsKey(modfiyOrderRequest.getLoginId())) {
				List<OrderDTO> orderList = accOrders.get(modfiyOrderRequest.getLoginId());
				int index = orderList.indexOf(new OrderDTO(modfiyOrderRequest.getOrderId()));
				if (index != -1) {
					OrderDTO orderDTO = orderList.get(index);
					Collection<String> instrCodes = new HashSet<String>();
					instrCodes.add(orderDTO.getInstrCode());
					List<EnquireShortRTQResponseEntity> rtqRes = enquirySnapShot(instrCodes);
					if (rtqRes != null && rtqRes.size() > 0) {
						double temp=0;
						if (Constants.OrderSide.BUY.equals(orderDTO.getOrderSide())) {
							temp=orderDTO.getHoldAmount().doubleValue();
						}
						double oldprice=orderDTO.getOrderPrice().doubleValue();
						double outstandingQuantity=orderDTO.getOutstandingQuantity().doubleValue();
						checkPrice(modfiyOrderRequest.getNewOrderPrice(),orderDTO, rtqRes.get(0));
						releaseHoldAmount(orderDTO);
						//恢复购买上限的金额
						releasePurchasingPower(orderDTO);
						orderDTO.setOrderPrice(modfiyOrderRequest.getNewOrderPrice());
						orderDTO.setChangedQty(orderDTO.getInitialQuantity().subtract(modfiyOrderRequest.getNewOrderQty()).negate());
						orderDTO.setOutstandingQuantity(modfiyOrderRequest.getNewOrderQty());
						orderDTO.setOrderRemark(Constants.OrderRemark.SUCCESS);
						LoginResponseEntity entity = WebActiveUserManager.getInstance().getUserEntity(modfiyOrderRequest.getLoginId());
						if(!checkMos(orderDTO, entity)){
							if (Constants.OrderSide.BUY.equals(orderDTO.getOrderSide())) {
								entity.setOnHoldBalance(entity.getOnHoldBalance().add(new BigDecimal(temp)));
								entity.setTotalPurchasingPower(entity.getTotalPurchasingPower().subtract(new BigDecimal(temp)));
								orderDTO.setOrderPrice(new BigDecimal(oldprice));
								orderDTO.setChangedQty(new BigDecimal(0));
								orderDTO.setOutstandingQuantity(new BigDecimal(outstandingQuantity));
								logger.warn("MOS no enough.");
								ActionUtil.packagingAndThrowException(Constants.ErrorKey.NSIM_00020);
							}
						}
						boolean flag=matchingPrice(rtqRes.get(0), orderDTO);
						if(!flag&&Constants.OrderSide.BUY.equals(orderDTO.getOrderSide())){
							orderDTO.setTradeAvgPrice(orderDTO.getOrderPrice());
							orderDTO.setFilledQty(orderDTO.getOutstandingQuantity());
							trunPurchasingPower(orderDTO);
							orderDTO.setFilledQty(new BigDecimal(0));
							
						}
					}
				} else {
					logger.warn("Order no found.");
					ActionUtil.packagingAndThrowException(Constants.ErrorKey.NSIM_00008);
				}
			} else {
				logger.warn("Order no found.");
				ActionUtil.packagingAndThrowException(Constants.ErrorKey.NSIM_00008);
			}
		}
		return res;
	}
	
	public CancelOrderResponse cancelOrder(CancelOrderRequest cancelOrderRequest) {
		CancelOrderResponse res = new CancelOrderResponse();
		if (StringUtils.isBlank(cancelOrderRequest.getOrderId())) {
			logger.warn("OrderId is Blank.");
			ActionUtil.packagingAndThrowException(Constants.ErrorKey.NSIM_00008);
		} else if (accOrders.containsKey(cancelOrderRequest.getLoginId())) {
			int index = accOrders.get(cancelOrderRequest.getLoginId()).indexOf(new OrderDTO(cancelOrderRequest.getOrderId()));
			if (index != -1) {
				OrderDTO orderDTO = accOrders.get(cancelOrderRequest.getLoginId()).get(index);
				if (Constants.OrderStatus.QUEUING.equals(orderDTO.getOrderState())) {
					orderDTO.setOrderState(Constants.OrderStatus.CANCELLED);
					orderDTO.setChangedQty(orderDTO.getInitialQuantity().negate());
					orderDTO.setOrderRemark(Constants.OrderRemark.SUCCESS);
					if (Constants.OrderSide.BUY.equals(orderDTO.getOrderSide())) {
						releaseHoldAmount(orderDTO);
						releasePurchasingPower(orderDTO);
					}
				} else {
					logger.warn("Order Status error");
					ActionUtil.packagingAndThrowException(Constants.ErrorKey.NSIM_00013);
				}
			} else {
				logger.warn("Order no found.");
				ActionUtil.packagingAndThrowException(Constants.ErrorKey.NSIM_00008);
			}
		} else {
			logger.warn("Order no found.");
			ActionUtil.packagingAndThrowException(Constants.ErrorKey.NSIM_00008);
		}
		return res;
	}
	
	public void removeQueuingOrder() {
		if (!isTradeTime()) {
			Set<String> allActiveUsers = WebActiveUserManager.getInstance().getAllActiveUsers();
			List<OrderDTO> unFillorderDTOs = new ArrayList<OrderDTO>();
			for (String user : allActiveUsers) {
				List<OrderDTO> list = accOrders.get(user);
				if (list != null) {
					for (OrderDTO orderDTO : list) {
						if (Constants.OrderStatus.QUEUING.equals(orderDTO.getOrderState())) {
							unFillorderDTOs.add(orderDTO);
						}
					}
				}
			}
			logger.info("RemoveQueuingOrder: " + unFillorderDTOs);
			if (unFillorderDTOs.size() > 0) {
				for (OrderDTO orderDTO : unFillorderDTOs) {
					orderDTO.setOrderState(Constants.OrderStatus.CANCELLED);
					orderDTO.setChangedQty(orderDTO.getInitialQuantity().negate());
					orderDTO.setOrderRemark(Constants.OrderRemark.SUCCESS);
					if (Constants.OrderSide.BUY.equals(orderDTO.getOrderSide())) {
						releaseHoldAmount(orderDTO);
					}
				}
			}
		}
	}
	
	private void releaseHoldAmount(OrderDTO orderDTO) {
		LoginResponseEntity entity = WebActiveUserManager.getInstance().getUserEntity(orderDTO.getLoginId());
		if (Constants.OrderSide.BUY.equals(orderDTO.getOrderSide())) {
			entity.setOnHoldBalance(entity.getOnHoldBalance().subtract(orderDTO.getHoldAmount()));
		}
	}
	
	private void trunPurchasingPower(OrderDTO orderDTO) {
		LoginResponseEntity entity = WebActiveUserManager.getInstance().getUserEntity(orderDTO.getLoginId());
		OrderFeeRequest feeRequest = new OrderFeeRequest();
		feeRequest.setOrderPrice(orderDTO.getTradeAvgPrice());
		feeRequest.setOrderQuantity(orderDTO.getFilledQty());
		feeRequest.setOrderSide(orderDTO.getOrderSide());
		//现在不考虑各种费用的情况
		//OrderFeeResponse orderFeeResponse = this.calOrderFee(feeRequest);
		
		BigDecimal amount = feeRequest.getOrderPrice().multiply(feeRequest.getOrderQuantity());
		if (Constants.OrderSide.BUY.equals(orderDTO.getOrderSide())) {
			//amount = orderFeeResponse.getCalOrderFeeResponse().getNetAmount().negate();
			amount=amount.negate();
		} else {
			//amount = orderFeeResponse.getCalOrderFeeResponse().getNetAmount();
		}
		entity.setTotalPurchasingPower(entity.getTotalPurchasingPower().add(amount));
	}
	
	public OrderDetailResponse enquiryOrderDetail(OrderDetailRequest orderDetailRequest) {
		OrderDetailResponse res = new OrderDetailResponse();
		if (StringUtils.isBlank(orderDetailRequest.getOrderId())) {
			logger.warn("OrderId is Blank.");
			ActionUtil.packagingAndThrowException(Constants.ErrorKey.NSIM_00008);
		} else {
			if (accOrders.containsKey(orderDetailRequest.getLoginId())) {
				int index = accOrders.get(orderDetailRequest.getLoginId()).indexOf(new OrderDTO(orderDetailRequest.getOrderId()));
				if (index != -1)  {
					res.setOrderDTO(accOrders.get(orderDetailRequest.getLoginId()).get(index));
				} else {
					logger.warn("OrderId is no found.");
					ActionUtil.packagingAndThrowException(Constants.ErrorKey.NSIM_00008);
				}
			} else {
				logger.warn("OrderId is no found.");
				ActionUtil.packagingAndThrowException(Constants.ErrorKey.NSIM_00008);
			}
		}
		return res;
	}
	
	public TradeListResponse listTrade(TradeListRequest tradeListRequest) {
		TradeListResponse res = new TradeListResponse();
		if (accOrders.containsKey(tradeListRequest.getLoginId())) {
			List<OrderDTO> orderList = accOrders.get(tradeListRequest.getLoginId());
			List<TradeInfo> tradeList = new ArrayList<TradeInfo>();
			for (OrderDTO orderDTO : orderList) {
				if (Constants.OrderStatus.COMPLETELY_FILLED.equals(orderDTO.getOrderState())) {
					TradeInfo info = new TradeInfo();
					info.setInstrCode(orderDTO.getInstrCode());
					info.setInstrName(orderDTO.getInstrName());
					info.setCurrency(orderDTO.getCurrency());
					info.setTradeSide(orderDTO.getOrderSide());
					info.setChannelType(Constants.LoginChannel.NSIM);
					info.setExecutedPrice(orderDTO.getTradeAvgPrice());
					info.setExecutedQty(orderDTO.getFilledQty());
					tradeList.add(info);
				}
			}
			res.setTradeListInfos(tradeList);
		}
		return res;
	}
	
	public EnquiryPositionResponse listPosition(EnquiryPositionRequest enquiryPositionRequest) {
		EnquiryPositionResponse res = new EnquiryPositionResponse();
		if (accOrders.containsKey(enquiryPositionRequest.getLoginId())) {
			List<OrderDTO> orderList = accOrders.get(enquiryPositionRequest.getLoginId());
			Map<String, ShareHoldingInfo> posMap = new HashMap<String, ShareHoldingInfo>();
			for (OrderDTO orderDTO : orderList) {
				boolean complete = Constants.OrderStatus.COMPLETELY_FILLED.equals(orderDTO.getOrderState());
				boolean queuing = Constants.OrderStatus.QUEUING.equals(orderDTO.getOrderState());
				if (complete || queuing) {
					ShareHoldingInfo info = null;
					if (posMap.containsKey(orderDTO.getInstrCode())) {
						info = posMap.get(orderDTO.getInstrCode());
						//正确显示股票结存数量
						if(Constants.OrderSide.BUY.equals(orderDTO.getOrderSide()) && complete){
							info.setMaxSellableQuantity(info.getMaxSellableQuantity().add(orderDTO.getFilledQty()));
						}else if (Constants.OrderSide.SELL.equals(orderDTO.getOrderSide()) && (complete || queuing)){
							if (orderDTO.getFilledQty().compareTo(new BigDecimal(0)) <= 0) {
								info.setMaxSellableQuantity(info.getMaxSellableQuantity().add(orderDTO.getInitialQuantity().negate()));
							} else {
								info.setMaxSellableQuantity(info.getMaxSellableQuantity().add(orderDTO.getFilledQty().negate()));
							}
						}
						
						if (info.getPreviousClosingPrice().compareTo(new BigDecimal(0)) > 0) {
							info.setPreviousClosingValue(info.getPreviousClosingPrice().multiply(info.getMaxSellableQuantity()));
						}
					} else {
						info = new ShareHoldingInfo();
						info.setInstrCode(orderDTO.getInstrCode());
						info.setInstrName(orderDTO.getInstrName());
						info.setCurrency(orderDTO.getCurrency());
						if(Constants.OrderSide.BUY.equals(orderDTO.getOrderSide()) && complete){
							info.setMaxSellableQuantity(orderDTO.getFilledQty());
						}else if (Constants.OrderSide.SELL.equals(orderDTO.getOrderSide()) && (complete || queuing)){
							if (orderDTO.getFilledQty().compareTo(new BigDecimal(0)) <= 0) {
								info.setMaxSellableQuantity(orderDTO.getInitialQuantity().negate().subtract(orderDTO.getChangedQty()));
							} else {
								info.setMaxSellableQuantity(orderDTO.getFilledQty().negate());
							}
						}
						
						if (Constants.OrderSide.SELL.equals(orderDTO.getOrderSide()) || complete) {
							Vector<String> instrCodes = new Vector<String>();
							instrCodes.add(orderDTO.getInstrCode());
							List<EnquireRTQResponseEntity> rtq = enquirySimpleRTQInfo(instrCodes,enquiryPositionRequest.getLanguage());
							if (rtq != null && rtq.get(0) != null && rtq.get(0).getStatus() == 0) {
								EnquireRTQResponseEntity rtqInfo = rtq.get(0);
								info.setPreviousClosingPrice(new BigDecimal(rtqInfo.getPClose()));
								info.setPreviousClosingValue(info.getPreviousClosingPrice().multiply(orderDTO.getFilledQty()));
							}
							posMap.put(orderDTO.getInstrCode(), info);
						}
					}
				}
			}
			res.setShareHoldingInfoCol(posMap.values());
		}
		return res;
	}
	
	public ListOrderResponse listOrder(ListOrderRequest listOrderRequest) {
		ListOrderResponse res = new ListOrderResponse();
		if (accOrders.containsKey(listOrderRequest.getLoginId())) {
			List<OrderDTO> orderList = accOrders.get(listOrderRequest.getLoginId());
			List<OrderDTO> filterOrders = new ArrayList<OrderDTO>();
			int pageNo = listOrderRequest.getPageNo();
			int pageSize = listOrderRequest.getPageSize();
			if (pageNo > 0 && pageSize > 0 && orderList.size() > 0) {
				int toIndex = pageNo * pageSize > orderList.size() ? orderList.size() : pageNo * pageSize;
				filterOrders = orderList.subList((pageNo - 1) * pageSize, toIndex);
			}
			res.setOrders(filterOrders);
			int totalPage = 0;
			if (orderList.size() % pageSize > 0) {
				totalPage = ((int) Math.floor(orderList.size() / pageSize)) + 1;
			} else {
				totalPage = (int)Math.floor(orderList.size() / pageSize);
			}
			int currentPage = pageNo;
			int prevPage = currentPage <= 1 ? 1 : currentPage - 1;
			int nextPage = totalPage > currentPage ? currentPage + 1 : currentPage;
			int pageAmount = totalPage;
			res.setTotalPage(totalPage);
			res.setCurrentPage(currentPage);
			res.setPrevPage(prevPage);
			res.setNextPage(nextPage);
			res.setPageAmount(pageAmount);
		} else {
			res.setOrders(new ArrayList<OrderDTO>());
		}
		return res;
	}
	
	public void matching() {
		Set<String> allActiveUsers = WebActiveUserManager.getInstance().getAllActiveUsers();
		Set<String> instrCodes = new HashSet<String>();
		List<OrderDTO> unFillorderDTOs = new ArrayList<OrderDTO>();
		for (String user : allActiveUsers) {
			List<OrderDTO> list = accOrders.get(user);
			if (list != null) {
				for (OrderDTO orderDTO : list) {
					if (Constants.OrderStatus.QUEUING.equals(orderDTO.getOrderState())) {
						instrCodes.add(orderDTO.getInstrCode());
						unFillorderDTOs.add(orderDTO);
					}
				}
			}
		}
		logger.info("unFillorder: " + unFillorderDTOs);
		if (instrCodes.size() > 0) {
			List<EnquireShortRTQResponseEntity> res = enquirySnapShot(instrCodes);
			if (res != null && res.size() > 0) {
				Map<String, EnquireShortRTQResponseEntity> stockQuote = new HashMap<String, EnquireShortRTQResponseEntity>();
				for (EnquireShortRTQResponseEntity entity : res) {
					if (entity != null & entity.getCode() != null) {
						stockQuote.put(entity.getCode(), entity);
					}
				}
				for (OrderDTO orderDTO : unFillorderDTOs) {
					try {
						matchingPrice(stockQuote.get(orderDTO.getInstrCode()), orderDTO);
					} catch (Exception e) {
						logger.error("matching price error: {}", e);
					}
				}
			} else {
				logger.error("QS error.");
			}
		}
	}
	
	private boolean matchingPrice(EnquireShortRTQResponseEntity entity, OrderDTO orderDTO) {
		if (entity != null) {
			if (Constants.OrderSide.BUY.equals(orderDTO.getOrderSide())) {
				//BigDecimal bestAsk = new BigDecimal(entity.getBestAsk());
				BigDecimal bestAsk = new BigDecimal(entity.getPrice());//修改成实时的价格 xwquan 2011-04-15
				if (orderDTO.getOrderPrice().compareTo(bestAsk) >= 0) {
					orderDTO.setTradeAvgPrice(orderDTO.getOrderPrice());
					orderDTO.setOrderState(Constants.OrderStatus.COMPLETELY_FILLED);
					orderDTO.setFilledQty(orderDTO.getOutstandingQuantity());
					orderDTO.setOutstandingQuantity(new BigDecimal(0));
					releaseHoldAmount(orderDTO);
					trunPurchasingPower(orderDTO);
					return true;
				}
				
			} else {
				//BigDecimal bestBid = new BigDecimal(entity.getBestBid());
				BigDecimal bestBid = new BigDecimal(entity.getPrice());//修改成实时的价格  xwquan 2011-04-15
				if (orderDTO.getOrderPrice().compareTo(bestBid) <= 0) {
					orderDTO.setTradeAvgPrice(orderDTO.getOrderPrice());
					orderDTO.setOrderState(Constants.OrderStatus.COMPLETELY_FILLED);
					orderDTO.setFilledQty(orderDTO.getOutstandingQuantity());
					orderDTO.setOutstandingQuantity(new BigDecimal(0));
					trunPurchasingPower(orderDTO);
					return true;
				}
			}
			
		}
		return false;
	}
	
	/**
	 * 恢复购买上限,只对buy的起作用，对于
	 * @param orderDTO
	 */
	private void releasePurchasingPower(OrderDTO orderDTO) {
		LoginResponseEntity entity = WebActiveUserManager.getInstance().getUserEntity(orderDTO.getLoginId());
		OrderFeeRequest feeRequest = new OrderFeeRequest();
		feeRequest.setOrderPrice(orderDTO.getOrderPrice());
		feeRequest.setOrderQuantity(orderDTO.getOutstandingQuantity());
		feeRequest.setOrderSide(orderDTO.getOrderSide());
		//不考虑各种费用，不用计算各种费用
		//OrderFeeResponse orderFeeResponse = this.calOrderFee(feeRequest);
		BigDecimal amount =null; 
		if (Constants.OrderSide.BUY.equals(orderDTO.getOrderSide())) {
			//amount = orderFeeResponse.getCalOrderFeeResponse().getNetAmount();
			amount=feeRequest.getOrderQuantity().multiply(feeRequest.getOrderPrice());
		} else {
			amount = new BigDecimal(0);
		}
		entity.setTotalPurchasingPower(entity.getTotalPurchasingPower().add(amount));
	}
	
	
	private List<EnquireShortRTQResponseEntity> enquirySnapShot(Collection<String> instrCodes) {
		EnquireRTQRequest requestModel = new EnquireRTQRequest();
		Vector<String> instrs = new Vector<String>(instrCodes);
		requestModel.setInstrCode(instrs);
		requestModel.setChannelType(Constants.LoginChannel.NSIM);
		requestModel.setLanguage(Constants.Lang.ENG);
		return qsManager.callShortRTQInfo(requestModel);
	}
	
	private List<EnquireRTQResponseEntity> enquirySimpleRTQInfo(Collection<String> instrCodes,String lang) {
		EnquireRTQRequest requestModel = new EnquireRTQRequest();
		Vector<String> instrs = new Vector<String>(instrCodes);
		requestModel.setInstrCode(instrs);
		requestModel.setChannelType(Constants.LoginChannel.NSIM);
		requestModel.setLanguage(lang);
		return qsManager.callSimpleRTQInfo(requestModel);
	}
	
	private synchronized String generateOrderId() {
		ORDERIDCOUNT++;
		return Constants.Prefix.ORDERID_PREFIX + ActionUtil.fmtCode(ORDERIDCOUNT.toString(), 9);
	}
	
	private OrderDTO packingOrderDTO(InputOrderRequest inputOrderRequest) {
		OrderDTO orderDTO = new OrderDTO();
		orderDTO.setAccountId(inputOrderRequest.getAccountId());
		orderDTO.setChannelId(inputOrderRequest.getChannelId());
		orderDTO.setChannelType(inputOrderRequest.getChannelType());
		orderDTO.setFilledQty(new BigDecimal(0));
		orderDTO.setInitialQuantity(inputOrderRequest.getOrderQuantity());
		orderDTO.setInstrCode(inputOrderRequest.getInstrCode());
		orderDTO.setLoginId(inputOrderRequest.getLoginId());
		orderDTO.setMarketCode(inputOrderRequest.getMarketCode());
		orderDTO.setOrderPrice(inputOrderRequest.getOrderPrice());
		orderDTO.setOrderSide(inputOrderRequest.getOrderSide());
		orderDTO.setOrderState(Constants.OrderStatus.QUEUING);
		orderDTO.setOrderType(inputOrderRequest.getOrderType());
		orderDTO.setOutstandingQuantity(inputOrderRequest.getOrderQuantity());
		orderDTO.setTradeAvgPrice(new BigDecimal(0));
		orderDTO.setChangedQty(new BigDecimal(0));
		
		return orderDTO;
	}
	
	private OrderDTO insertAccOrder(OrderDTO orderDTO) {
//		orderDTO.setOrderId(generateOrderId());
		if (accOrders.containsKey(orderDTO.getLoginId())) {
			accOrders.get(orderDTO.getLoginId()).addFirst(orderDTO);
		} else {
			LinkedList<OrderDTO> orders = new LinkedList<OrderDTO>();
			orders.addFirst(orderDTO);
			accOrders.put(orderDTO.getLoginId(), orders);
		}
		return orderDTO;
	}
}
