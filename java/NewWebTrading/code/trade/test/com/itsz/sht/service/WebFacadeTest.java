package com.itsz.sht.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Vector;

import junit.framework.TestCase;

import com.itsz.sht.common.Consts;
import com.itsz.sht.common.ServiceLocator;
import com.itsz.sht.common.model.common.request.AccessRTQRequestModel;
import com.itsz.sht.common.model.common.request.ListSelectServiceRequestModel;
import com.itsz.sht.common.model.common.request.PurchaseServiceRequestModel;
import com.itsz.sht.common.model.common.request.RTQProductRequestModel;
import com.itsz.sht.common.model.common.response.AccessRTQResponseModel;
import com.itsz.sht.common.model.common.response.ListSelectServiceResponseModel;
import com.itsz.sht.common.model.common.response.PurchaseServiceResponseModel;
import com.itsz.sht.common.model.common.response.RTQProductResponseModel;
import com.itsz.sht.common.model.request.BulkCancelOrderRequestModel;
import com.itsz.sht.common.model.request.ChangePwdRequestModel;
import com.itsz.sht.common.model.request.EnquireAccountListRequestModel;
import com.itsz.sht.common.model.request.EnquireAccountRequestModel;
import com.itsz.sht.common.model.request.EnquireRTQRequestModel;
import com.itsz.sht.common.model.request.EnquiryPositionRequestModel;
import com.itsz.sht.common.model.request.FilterOrderRequestModel;
import com.itsz.sht.common.model.request.FundDepositRequestModel;
import com.itsz.sht.common.model.request.IPOQtyRequestModel;
import com.itsz.sht.common.model.request.IPORequestModel;
import com.itsz.sht.common.model.request.LoginRequestModel;
import com.itsz.sht.common.model.request.LotSizeRequestModel;
import com.itsz.sht.common.model.request.MarginFinancingListRequestModel;
import com.itsz.sht.common.model.request.ModifyOrderRequestModel;
import com.itsz.sht.common.model.request.OrderDetailRequestModel;
import com.itsz.sht.common.model.request.OrderFeeRequestModel;
import com.itsz.sht.common.model.request.PPSEnquiryRequestModel;
import com.itsz.sht.common.model.request.PlaceOrderRequestModel;
import com.itsz.sht.common.model.request.RemoveIPORequestModel;
import com.itsz.sht.common.model.request.TradeListRequestModel;
import com.itsz.sht.common.model.request.TransactionProtectionRequestModel;
import com.itsz.sht.common.model.request.VerifyPasswordRequestModel;
import com.itsz.sht.common.model.request.VerifyPlaceOrderRequestModel;
import com.itsz.sht.common.model.response.BulkCancelOrderResponseModel;
import com.itsz.sht.common.model.response.ChangePwdResponseModel;
import com.itsz.sht.common.model.response.EnquireAccountResponseModel;
import com.itsz.sht.common.model.response.EnquireRTQResponseModel;
import com.itsz.sht.common.model.response.EnquiryPositionResponseModel;
import com.itsz.sht.common.model.response.FilterIPOAmtRcrdResponseModel;
import com.itsz.sht.common.model.response.FilterIPOListResponseModel;
import com.itsz.sht.common.model.response.FilterOrderResponseModel;
import com.itsz.sht.common.model.response.FundDepositResponseModel;
import com.itsz.sht.common.model.response.IPOAddRequestModel;
import com.itsz.sht.common.model.response.InsertIPOResponseModel;
import com.itsz.sht.common.model.response.LoginResponseModel;
import com.itsz.sht.common.model.response.MISAccountListResponseModel;
import com.itsz.sht.common.model.response.MarginFinancingListResponseModel;
import com.itsz.sht.common.model.response.ModifyOrderResponseModel;
import com.itsz.sht.common.model.response.OrderFeeResponseModel;
import com.itsz.sht.common.model.response.PPSEnquiryResponseModel;
import com.itsz.sht.common.model.response.PlaceOrderResponseModel;
import com.itsz.sht.common.model.response.QueryCodeResponseModel;
import com.itsz.sht.common.model.response.RemoveIPOResponseModel;
import com.itsz.sht.common.model.response.TradeListResponseModel;
import com.itsz.sht.common.model.response.TransactionProtectionResponseModel;
import com.itsz.sht.common.model.response.VerifyPasswordResponseModel;
import com.itsz.sht.common.model.response.VerifyPlaceOrderResponseModel;
import com.itsz.sht.common.model.response.listorder.OrderDetailTradeResponseModel;
import com.itsz.sht.service.qs.QsService;
import com.taifook.mcs.core.beans.msg.OrderIdInfo;

public class WebFacadeTest extends TestCase {

	private Facade facade;

	protected void setUp() throws Exception {
		super.setUp();
		this.facade = (Facade) ServiceLocator.getInstance()
				.getService("facade");
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		this.facade = null;
	}

	public void testLogin() {
		LoginRequestModel model = new LoginRequestModel();
		model.setLoginId("0901605");// 02-0124779-33
		model.setPassword("000000");
		model.setChannelId("WEB01");
		model.setChannelType("WEB");
		model.setLanguage("ENG");

		LoginResponseModel responseModel = facade.login(model);
		// assertNotNull();
		assertEquals(responseModel.getReturnCode(), 0);
	}
	
	public void testFundDeposit() {
		FundDepositRequestModel model = new FundDepositRequestModel();
		model.setAccountName("kyzou");
		model.setAccountNo("02-0124779-30");
		model.setAmount(new BigDecimal(100));
		model.setBank("HSBC");
		model.setBankAcc("02-0124779-33");
		model.setCurrency("HKD");
		model.setDepositDate(new Date());
		model.setReceiveDate(new Date());
		model.setReference("xxxxxx");
		model.setSource("sxxxxx");
		model.setChannelId("NWEB");
		model.setChannelType("WEB");
		model.setLanguage("ENG");

		FundDepositResponseModel responseModel = facade.fundDeposit(model);
		assertEquals(responseModel.getReturnCode(), 0);
	}
	
	public void testEnquireRtq() {
		EnquireRTQRequestModel rtqRequest = new EnquireRTQRequestModel();
		Vector v = new Vector();
//		v.add(".HSI");
//		v.add("HSIc1");
		v.add("9999");	
		rtqRequest.setChannelId("WEB01");
		rtqRequest.setChannelType("WEB");
		rtqRequest.setLanguage("ENG");
		rtqRequest.setCode(v);
		rtqRequest.setDelayTime(String.valueOf(Consts.Qs.REAL_TIME));
		rtqRequest.setCustomerId("23");
		rtqRequest.setSeqn(Consts.Qs.SEQN);
		rtqRequest.setServiceType(Consts.Qs.ST_INDEX_FREE);
		rtqRequest.setSubType(Consts.Qs.S_TYPE_REAL);
		rtqRequest.setType(Consts.Qs.QUOT_TYPE);
		EnquireRTQResponseModel responseModel = facade.enquireRTQInfo(rtqRequest);
		assertEquals(responseModel.getReturnCode(), 0);
	}
	
	public void testLotSize() {
		QsService qsService = (QsService) ServiceLocator.getInstance().getService("qsService");
		LotSizeRequestModel rtqRequest = new LotSizeRequestModel();
		Vector v = new Vector();
//		v.add(".HSI");
//		v.add("HSIc1");
		v.add("0001");	
		rtqRequest.setChannelId("WEB01");
		rtqRequest.setChannelType("WEB");
		rtqRequest.setLanguage("ENG");
		rtqRequest.setCode(v);
		rtqRequest.setDelayTime(String.valueOf(Consts.Qs.DELAY_TIME));
		rtqRequest.setCustomerId("23");
		rtqRequest.setSeqn(Consts.Qs.SEQN);
		rtqRequest.setServiceType(Consts.Global.Number.SZERO);
		rtqRequest.setSubType(Consts.Qs.S_TYPE_DELAY);
		rtqRequest.setType(Consts.Qs.QUOT_TYPE);

		String lotsize = qsService.lotSize(rtqRequest);
		assertEquals(lotsize, 0);
	}

	public void testStockPosition() {
		EnquiryPositionResponseModel responseModel = new EnquiryPositionResponseModel();
		EnquiryPositionRequestModel request = new EnquiryPositionRequestModel();
		request.setAccountId("02-0124836-30");
		request.setChannelId("WEB01");
		request.setChannelType("WEB");
		request.setLanguage("ENG");
		responseModel = facade.enquiryPosition(request);
		assertEquals(responseModel.getReturnCode(), 0);
	}

	public void testAccountPosition() {
		EnquireAccountResponseModel responseModel = new EnquireAccountResponseModel();
		EnquireAccountRequestModel request = new EnquireAccountRequestModel();
		request.setAccountId("02-0124779-33");
		request.setChannelId("WEB01");
		request.setChannelType("WEB");
		request.setLanguage("ENG");
		responseModel = facade.enquireAccountDetail(request);
		assertEquals(responseModel.getReturnCode(), 0);
	}

	public void testChangePass() {
		ChangePwdResponseModel responseModel = new ChangePwdResponseModel();
		ChangePwdRequestModel request = new ChangePwdRequestModel();
		request.setLoginId("0122915");// 909610,0122864
		request.setOldPassword("111111");
		request.setNewPassword("000000");
		request.setChannelId("WEB01");
		request.setChannelType("WEB");
		request.setLanguage("ENG");

		responseModel = facade.changePassword(request);
		// assertNotNull();
		assertEquals(responseModel.getReturnCode(), 0);
	}

	public void testChangePwdPtm() {
		TransactionProtectionResponseModel responseModel = new TransactionProtectionResponseModel();
		TransactionProtectionRequestModel request = new TransactionProtectionRequestModel();
		request.setLoginId("0122915");// 909610,0122864
		request.setPassword("111111");
		request.setTransactionProtection("N");
		request.setChannelId("WEB01");
		request.setChannelType("WEB");
		request.setLanguage("ENG");

		responseModel = facade.changeTransactionProtection(request);
		// assertNotNull();
		assertEquals(responseModel.getReturnCode(), 0);
	}

	public void testMISAccountList() throws Exception {
		EnquireAccountListRequestModel request = new EnquireAccountListRequestModel();
		MISAccountListResponseModel responseModel = new MISAccountListResponseModel();
		request.setVersionId("Ver_00-1");
		request.setChannelId("WEB01");
		request.setChannelType("WEB");
		request.setCustCode("0122915");
		request.setLanguage("ENG");
		// request.setQuery(Consts.);
		responseModel = facade.enquireAccountList(request);
		assertEquals(responseModel.getReturnCode(), 0);
	}
	
	public void testTradeList() throws Exception {
		TradeListRequestModel request = new TradeListRequestModel();
		TradeListResponseModel responseModel = new TradeListResponseModel();
		request.setVersionId("Ver_00-1");
		request.setChannelId("WEB01");
		request.setChannelType("WEB");
		request.setAccountId("02-0901605-30");
		request.setLoginId("0901605");
		request.setFromDate("2010-12-23 00:00:00");
		request.setToDate("2010-12-23 23:59:59");
		request.setPageSize("10");
		//request.setPageNo("1");
		request.setLanguage("ENG");
		// request.setQuery(Consts.);
		responseModel = facade.enquireTradeList(request);
		assertEquals(responseModel.getReturnCode(), 0);
	}
	
	public void testCalOrderFee() {
		OrderFeeRequestModel orderFeeRequstModel = new OrderFeeRequestModel();
		orderFeeRequstModel.setLanguage(Consts.Global.Language.GB);
		orderFeeRequstModel.setVersionId("Ver_00-1");
		orderFeeRequstModel.setChannelId("WEB01");
		orderFeeRequstModel.setChannelType("WEB");
		orderFeeRequstModel.setAccountId("02-0901605-30");
		orderFeeRequstModel.setMarketCode("AMS3");
		orderFeeRequstModel.setInstrCode("7");
		orderFeeRequstModel.setOrderQuantity(new BigDecimal(4000));
		orderFeeRequstModel.setOrderPrice(new BigDecimal(100));
		orderFeeRequstModel.setOrderSide(Consts.Order.Side.S);
		orderFeeRequstModel.setOrderType(Consts.Order.Type.ENHANCED_LIMIT);
		orderFeeRequstModel.setAllOrNothing("N");
		
		OrderFeeResponseModel responseModel = facade.calOrderFee(orderFeeRequstModel);
		System.out.println(responseModel);
	}
	
	public void testVerifyPlaceOrder() {
		VerifyPlaceOrderRequestModel orderFeeRequstModel = new VerifyPlaceOrderRequestModel();
		orderFeeRequstModel.setLanguage(Consts.Global.Language.GB);
		orderFeeRequstModel.setVersionId("Ver_00-1");
		orderFeeRequstModel.setChannelId("WEB01");
		orderFeeRequstModel.setChannelType("WEB");
		orderFeeRequstModel.setAccountId("02-0124779-33");
		orderFeeRequstModel.setCustCode("0122915");
		orderFeeRequstModel.setMarketCode("AMS3");
		orderFeeRequstModel.setInstrCode("0001");
		orderFeeRequstModel.setOrderQuantity(new BigDecimal(4000));
		orderFeeRequstModel.setOrderPrice(new BigDecimal(100));
		orderFeeRequstModel.setOrderSide(Consts.Order.Side.B);
		orderFeeRequstModel.setOrderType(Consts.Order.Type.ENHANCED_LIMIT);
		orderFeeRequstModel.setAllOrNothing("N");
		
		VerifyPlaceOrderResponseModel responseModel = facade.verifyPlaceOrder(orderFeeRequstModel);
		System.out.println(responseModel);
	}

	public void testPlaceOrder() {
		PlaceOrderRequestModel requestModel = new PlaceOrderRequestModel();
		requestModel.setLanguage(Consts.Global.Language.GB);
		requestModel.setVersionId("Ver_00-1");
		requestModel.setChannelId("WEB01");
		requestModel.setChannelType("WEB");
		requestModel.setAccountId("02-0124836-30");
		requestModel.setCustCode("0124836");
		requestModel.setMarketCode("AMS3");
		requestModel.setInstrCode("0001");
		requestModel.setOrderQuantity(new BigDecimal(4000));
		requestModel.setOrderPrice(new BigDecimal(100));
		requestModel.setOrderSide(Consts.Order.Side.B);
		requestModel.setOrderType(Consts.Order.Type.ENHANCED_LIMIT);
		requestModel.setAllOrNothingFlag("N");
		requestModel.setLoginId("0124836");// 02-0124779-33
		requestModel.setPassword("111111");
		//requestModel.setTriggerPrice(triggerPrice);
		requestModel.setConditionType("LTE");		
		PlaceOrderResponseModel responseModel = facade.placeOrder(requestModel);
		
		System.out.println(responseModel);
	}
	
	public void testVerifyPassword() {
		VerifyPasswordRequestModel requestModel = new VerifyPasswordRequestModel();
		requestModel.setLanguage(Consts.Global.Language.GB);
		requestModel.setVersionId("Ver_00-1");
		requestModel.setChannelId("WEB01");
		requestModel.setChannelType("WEB");
		requestModel.setLoginId("0901605");// 02-0124779-33
		requestModel.setPassword("000000");		
		VerifyPasswordResponseModel responseModel = facade.verifyPassword(requestModel);		
		System.out.println(responseModel);
	}	
	
	 public void testOrderDetail() {
		 OrderDetailRequestModel orderListRequest = new OrderDetailRequestModel();
		 orderListRequest.setVersionId("Ver_00-1");
		 orderListRequest.setChannelId("WEB01");
		 orderListRequest.setChannelType("WEB");
		 orderListRequest.setAccountId("02-0901605-30");
		 orderListRequest.setMcsOrderId(Long.decode("80222"));
		 orderListRequest.setOrderId(Long.decode("12977283"));
		 orderListRequest.setLanguage(Consts.Global.Language.GB);
		 orderListRequest.setHasHisories("Y");
		 OrderDetailTradeResponseModel hisModel =facade.orderDetail(orderListRequest);
		 System.out.println(hisModel);
	 }

	 public void testCancelOrder(){
		 BulkCancelOrderRequestModel requestModel = new BulkCancelOrderRequestModel();
		 requestModel.setAccountId("02-0124836-30");
		 requestModel.setVersionId("Ver_00-1");
		 requestModel.setChannelId("WEB01");
		 requestModel.setChannelType("WEB");
		 requestModel.setLanguage("ENG");
		 requestModel.setListSize(1);
		 requestModel.setLoginId("0124839");
		 requestModel.setPassword("111111");
		 OrderIdInfo orderIdList = new OrderIdInfo();
		 orderIdList.setOrderId(Long.decode("80086"));
		 Collection col = new ArrayList();
		 col.add(orderIdList);
//		 requestModel.setMCSOrderID(col);
		 // requestModel.setVersionId("");
		 try {
			 BulkCancelOrderResponseModel cResponseModel =facade.bulkCancelOrder(requestModel);
			 System.out.println(cResponseModel.getReturnCode());
		 } catch (Exception e) {
			 e.printStackTrace();
	     }
	 }

	 public void testModifyOrder(){
		 ModifyOrderRequestModel requestModel = new ModifyOrderRequestModel();
		 requestModel.setAccountId("02-0124779-33");
		 requestModel.setCustCode("0124779");
		 requestModel.setVersionId("Ver_00-1");
		 requestModel.setChannelId("WEB01");
		 requestModel.setChannelType("WEB");
		 requestModel.setLanguage("ENG");
		 requestModel.setLoginId("0124779");
		 requestModel.setMcsOrderId(Long.decode("80230"));
		 // requestModel.setMtssOrderId(Long.decode("80216"));
		 requestModel.setNewOrderPrice(BigDecimal.valueOf((long)1.24));
		 requestModel.setNewOrderQty(BigDecimal.valueOf((long)10));
		 requestModel.setPassword("000000");
		 requestModel.setTriggerPrice(BigDecimal.valueOf((long)1.24));
		 try {
			 ModifyOrderResponseModel mResponseModel =
			 facade.modifyOrder(requestModel);
			 System.out.println(mResponseModel.getReturnCode());
		 } catch (Exception e) {
			 e.printStackTrace();
		 }
	 }

	 public void testListOrder(){
		 FilterOrderRequestModel requstModel = new FilterOrderRequestModel();
		 requstModel.setAccountId("02-0901605-30");
		 requstModel.setChannelId("WEB01");
		 requstModel.setChannelType("WEB");
		 requstModel.setLanguage("GB");
		 requstModel.setPageNo("3");
		 requstModel.setPageSize("7");
		 //requstModel.setFromIdx(0);
		 //requstModel.setToIdx(8);
		 //requstModel.setOrderStateType("REJECTED");
		 FilterOrderResponseModel responseModel = facade.filterOrder(requstModel);
		 System.out.println(responseModel.getTotalPage());
	 }
	
	 public void testEnquireAccountList() {
		 EnquireAccountListRequestModel requestModel = new
		 EnquireAccountListRequestModel();
		 requestModel.setVersionId("Ver_00-1");
		 requestModel.setChannelId("WEB01");
		 requestModel.setChannelType("WEB");
		 requestModel.setCustCode("0124779");
		 requestModel.setLanguage("ENG");
		 MISAccountListResponseModel responseModel = facade.enquireAccountList(requestModel);
		 System.out.println(responseModel);
	 }
	 
	 public void testGetAllIPORecord() {
		 IPORequestModel requestModel = new IPORequestModel();
		 requestModel.setVersionId("Ver_00-1");
		 requestModel.setChannelId("WEB01");
		 requestModel.setChannelType("WEB");
		 requestModel.setLanguage("BIG5");
		 requestModel.setAccountId("02-0901605-30");
		 FilterIPOListResponseModel responseModel = facade.getAllIPORecord(requestModel);
		 System.out.println(responseModel);
	 }
	 
	 public void testIPOQueryCode() {
		 IPORequestModel requestModel = new IPORequestModel();
		 requestModel.setVersionId("Ver_00-1");
		 requestModel.setChannelId("WEB01");
		 requestModel.setChannelType("WEB");
		 requestModel.setLanguage("BIG5");
		 requestModel.setAccountId("02-0901605-30");
		 requestModel.setIpoMasterId(504);
		 QueryCodeResponseModel responseModel = facade.getIPOQueryCode(requestModel);
		 System.out.println(responseModel.toString());
	 }
	 
	 public void testEnquiryIPOQtyAmtRcrd() {
		 IPOQtyRequestModel requestModel = new IPOQtyRequestModel();
		 requestModel.setVersionId("Ver_00-1");
		 requestModel.setChannelId("WEB01");
		 requestModel.setChannelType("WEB");
		 requestModel.setLanguage("BIG5");
		 requestModel.setIpoMasterId(541);
		 requestModel.setShareQty(1000);
		 FilterIPOAmtRcrdResponseModel responseModel = facade.getIPOAmtRcrd(requestModel);
		 System.out.println(responseModel.toString());
	 }
	 
	 public void testIPOCancelCode() {
		 RemoveIPORequestModel requestModel = new RemoveIPORequestModel();
		 requestModel.setVersionId("Ver_00-1");
		 requestModel.setChannelId("WEB01");
		 requestModel.setChannelType("WEB");
		 requestModel.setLanguage("BIG5");
		 requestModel.setIpoMasterId(541);
		 requestModel.setAccountId("02-0901605-30");
		 requestModel.setLoginId("0901605");
		 requestModel.setPassword("000000");
		 RemoveIPOResponseModel responseModel = facade.removeIPORequest(requestModel);
		 System.out.println(responseModel.toString());
	 }
	 
	 public void testInsertIPORequest() {
		 IPOAddRequestModel requestModel = new IPOAddRequestModel();
		 requestModel.setVersionId("Ver_00-1");
		 requestModel.setChannelId("WEB01");
		 requestModel.setChannelType("WEB");
		 requestModel.setLanguage("BIG5");
		 requestModel.setIpoMasterId(541);
		 requestModel.setAccountId("02-0901605-30");
		 requestModel.setLoginId("0901605");
		 requestModel.setPassword("000000");
		 requestModel.setEmail("kyzou@itsz.cn");
		 InsertIPOResponseModel responseModel = facade.insertIPORequest(requestModel);
		 System.out.println(responseModel.toString());
	 }
	 
	 public void testEnquireMarginRations() {
		 MarginFinancingListRequestModel requestModel = new MarginFinancingListRequestModel();
		 requestModel.setVersionId("Ver_00-1");
		 requestModel.setChannelId("WEB01");
		 requestModel.setChannelType("WEB");
		 requestModel.setLanguage("BIG5");
		 requestModel.setLoginId("0901605");
		 requestModel.setInstrCode("1");
		 MarginFinancingListResponseModel responseModel = facade.enquireMarginRations(requestModel);
		 System.out.println(responseModel.toString());
	 }
	 
	 public void testPPSEnquiry() {
		 PPSEnquiryRequestModel requestModel = new PPSEnquiryRequestModel();
		 requestModel.setVersionId("Ver_00-1");
		 requestModel.setChannelId("WEB01");
		 requestModel.setChannelType("WEB");
		 requestModel.setLanguage("BIG5");
		 requestModel.setAccountId("0023547");
//		 requestModel.setAccountId("02-0023547-30");
		 PPSEnquiryResponseModel responseModel = facade.ppsEnquiry(requestModel);
		 System.out.println(responseModel.toString());
	 }
	 
	public void testPurchaseService(){
		PurchaseServiceRequestModel model = new PurchaseServiceRequestModel();
		model.setClientId("clientId50");
		model.setProductId("SSTR_AAST");
		model.setChannelId("WEB01");
		model.setAllowRenewal("N");
		model.setDefDebtAcc("02-1234567-02");
		model.setChannelType("WEB");
		model.setLanguage("ENG");
		//case1:用户产品不存在
//		resModel = rtqService.purchaseService(model);
		
		//case2:用户产品已存在
		model.setAllowRenewal("Y");
		PurchaseServiceResponseModel resModel = facade.purchaseService(model);
		System.out.println(resModel.getReturnCode());
	}
		
	public void testAccessRTQ() {
		AccessRTQRequestModel requestModel = new AccessRTQRequestModel();
		requestModel.setChannelType("WEB");
		requestModel.setLanguage("ENG");
		requestModel.setVersionId("Ver_00-1");
		requestModel.setChannelId("WEB01");
		requestModel.setChannelType("WEB");
		requestModel.setClientId("0901605");
		requestModel.setClientIp("127.0.0.1");
//		requestModel.setProductId("SSTR_AAST");
		AccessRTQResponseModel responseModel = facade.accessRTQ(requestModel);
		System.out.println(responseModel.getRtqAccess().getRtqUrl());
	}
	
	public void testListSelectRTQ() {
		ListSelectServiceRequestModel requestModel = new ListSelectServiceRequestModel();
		requestModel.setChannelType("WEB");
		requestModel.setLanguage("ENG");
		requestModel.setVersionId("Ver_00-1");
		requestModel.setChannelId("WEB01");
		requestModel.setChannelType("WEB");
		requestModel.setClientId("0077444");
		ListSelectServiceResponseModel responseModel = facade.listSelectService(requestModel);
		System.out.println(responseModel.toString());
	}
	
	public void testGetRTQProductList() {
		RTQProductRequestModel requestModel = new RTQProductRequestModel();
		requestModel.setChannelType("WEB");
		requestModel.setLanguage("ENG");
		requestModel.setVersionId("Ver_00-1");
		requestModel.setChannelId("WEB01");
		requestModel.setChannelType("WEB");
		requestModel.setClientId("0901605");
		RTQProductResponseModel responseModel = facade.getRTQProductList(requestModel);
		System.out.println(responseModel.toString());
	}
}
