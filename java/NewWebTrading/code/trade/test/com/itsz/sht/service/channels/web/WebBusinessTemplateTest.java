package com.itsz.sht.service.channels.web;

import java.math.BigDecimal;

import junit.framework.TestCase;

import com.itsz.sht.common.Consts;
import com.itsz.sht.common.JSONUtil;
import com.itsz.sht.common.ServiceLocator;
import com.itsz.sht.common.model.request.OrderFeeRequestModel;
import com.itsz.sht.common.model.response.OrderFeeResponseModel;
import com.itsz.sht.service.channels.BusinessTemplate;
import com.itsz.sht.service.mcs.McsService;
import com.itsz.sht.service.qs.QsService;

public class WebBusinessTemplateTest extends TestCase {

	private BusinessTemplate bt;
	private McsService mcsService;
//	private ESService esService;
	private QsService qsService;
	
	protected void setUp() throws Exception {
		super.setUp();
		this.bt = (BusinessTemplate)ServiceLocator.getInstance().getService("WEBTemplate");
		this.mcsService = (McsService)ServiceLocator.getInstance().getService("mcsService");
//		this.esService = (ESService)ServiceLocator.getInstance().getService("esService");
		this.qsService = (QsService)ServiceLocator.getInstance().getService("qsService");
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		this.bt = null;
		this.mcsService = null;
//		this.esService = null;
		this.qsService = null;
	}
	
	/*
	 * Test method for 'com.itsz.channelsserver.service.channels.web.WebBusinessTemplate.eCertLogin(McsService, ESService, ECertLoginRequestModel)'
	 */
	public void testECertLogin() {

	}

	/*
	 * Test method for 'com.itsz.channelsserver.service.channels.web.WebBusinessTemplate.placeOrder(McsService, QsService, PlaceOrderRequestModel)'
	 */
	public void testPlaceOrder() {

	}

	/*
	 * Test method for 'com.itsz.channelsserver.service.channels.web.WebBusinessTemplate.calOrderFee(McsService, QsService, OrderFeeRequestModel)'
	 */
	public void testCalOrderFee() {
		OrderFeeRequestModel orderFeeRequstModel = new OrderFeeRequestModel();
		orderFeeRequstModel.setAccountId("02-0000024-30");
		orderFeeRequstModel.setMarketCode("AMS3");
		orderFeeRequstModel.setInstrCode("0023");
		orderFeeRequstModel.setOrderQuantity(new BigDecimal(600200));
		orderFeeRequstModel.setOrderPrice(new BigDecimal(0.01));
		orderFeeRequstModel.setOrderSide(Consts.Order.Side.B);
		orderFeeRequstModel.setOrderType(Consts.Order.Type.ENHANCED_LIMIT);
		orderFeeRequstModel.setAllOrNothing("N");
		orderFeeRequstModel.setChannelType(Consts.Channel.WEB);
		OrderFeeResponseModel responseModel = bt.calOrderFee(orderFeeRequstModel);
		System.out.println("------------------------------------------------");
		System.out.println(JSONUtil.bean2JSON(responseModel));
	}

	/*
	 * Test method for 'com.itsz.channelsserver.service.channels.web.WebBusinessTemplate.prePlaceOrder(McsService, QsService, PrePlaceOrderRequestModel)'
	 */
	public void testPrePlaceOrder() {

	}

	/*
	 * Test method for 'com.itsz.channelsserver.service.channels.web.WebBusinessTemplate.login(McsService, ESService, LoginRequestModel)'
	 */
	public void testLogin() {

	}

	/*
	 * Test method for 'com.itsz.channelsserver.service.channels.web.WebBusinessTemplate.filterOrder(McsService, FilterOrderRequestModel)'
	 */
	public void testFilterOrder() {

	}

	/*
	 * Test method for 'com.itsz.channelsserver.service.channels.web.WebBusinessTemplate.modifyOrder(McsService, ModifyOrderRequestModel)'
	 */
	public void testModifyOrder() {

	}

	/*
	 * Test method for 'com.itsz.channelsserver.service.channels.web.WebBusinessTemplate.cancelOrder(McsService, CancelOrderRequestModel)'
	 */
	public void testCancelOrder() {

	}

	/*
	 * Test method for 'com.itsz.channelsserver.service.channels.web.WebBusinessTemplate.orderDetail(McsService, OrderDetailRequestModel)'
	 */
	public void testOrderDetail() {

	}

}
