package com.itsz.sht.service;





import junit.framework.TestCase;

import com.itsz.sht.common.ServiceLocator;
import com.itsz.sht.common.model.common.request.VerifyPasswordRequestModel;
import com.itsz.sht.common.model.common.response.VerifyPasswordResponseModel;
import com.itsz.sht.exception.ITSZException;
import com.itsz.sht.service.mcs.McsService;

public class McsServiceTest extends TestCase {

	private McsService mcsService;
	
	protected void setUp() throws Exception {
		super.setUp();
		mcsService = (McsService)ServiceLocator.getInstance().getService("mcsService");
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		mcsService = null;
	}

//	public void testCallOrderFee() throws ITSZException {
//		OrderFeeRequestModel orderFeeRequstModel = new OrderFeeRequestModel();
//		orderFeeRequstModel.setAccountId("02-0000024-30");
//		orderFeeRequstModel.setMarketCode("AMS3");
//		orderFeeRequstModel.setInstrCode("0023");
//		orderFeeRequstModel.setOrderQuantity(new BigDecimal(600200));
//		orderFeeRequstModel.setOrderPrice(new BigDecimal(0.01));
//		orderFeeRequstModel.setOrderSide(Consts.Order.Side.B);
//		orderFeeRequstModel.setOrderType(Consts.Order.Type.ENHANCED_LIMIT);
//		orderFeeRequstModel.setAllOrNothing("N");
//		OrderFeeResponseModel responseModel = mcsService.callOrderFee(orderFeeRequstModel , new BigDecimal("1000000"));
//		System.out.println(JSONUtil.bean2JSON(responseModel));
//	}

	public void testcallVerifyPassword() throws ITSZException {
		VerifyPasswordRequestModel requestModel = new VerifyPasswordRequestModel();
		requestModel.setLoginId("0077444");
		requestModel.setPasswordMatch("111111");
		requestModel.setLanguage("ENG");
		requestModel.setVersionId("Ver_00-1");
		requestModel.setChannelId("WEB01");
		requestModel.setChannelType("WEB");
		VerifyPasswordResponseModel responseModel=mcsService.callVerifyPassword(requestModel);

		System.out.println(responseModel.getReturnCode());

	}
	

	/*
	 * Test method for 'com.itsz.channelsserver.service.mcs.McsService.callOrderDetail(OrderDetailRequestModel)'
	 */
//	public void testCallOrderDetail() {
//		OrderDetailRequestModel model = new OrderDetailRequestModel();
//		
//		model.setVersionId("0001");
//		model.setChannelId("WE25");
//		model.setChannelType("WEB");
//		model.setAccountId("02-0000024-30");
//		model.setMcsOrderId(Long.decode("80216"));
//		model.setLanguage("ENG");
//
//		try {
//			OrderDetailWithTradeHisModel withTradeHis = mcsService.callOrderDetail(model);
//			System.out.println(JSONUitl.bean2JSON(withTradeHis));
//			assertNotNull(withTradeHis);
//			
//		} catch (ITSZException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (Exception ex){
//			ex.printStackTrace();
//		}
//	}
	
	/**
	 * 
	 * @Author:Cimenon Saint
	 * @Time:2007-6-2 14:57:05
	 */
//	public void testCallFilterOrder(){
//		
//		FilterOrderRequestModel model = new FilterOrderRequestModel();
//		model.setAccountId("02-0000024-30");
//		model.setMarketCode("AMS3");
//		model.setOrderStateType("");
//		model.setChannelId(Consts.Channel.Id.WEB);
//		model.setChannelType(Consts.Channel.WEB);
//		
//		try {
//			FilterOrderResponseModel responseModel = mcsService.callFilterOrder(model);
//			assertTrue(responseModel.getMos().intValue() >= 0);
//		} catch (ITSZException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
}
