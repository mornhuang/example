/*
 * FacadeImplTest.java
 * Created by Jtest on 11-3-30 12:12:41.
 */

package com.itsz.sht.service;

import com.itsz.sht.admin.parameter.util.ParaParser;
import com.itsz.sht.common.Consts;
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
import com.itsz.sht.common.model.request.CancelOrderRequestModel;
import com.itsz.sht.common.model.request.CashDetailRequestModel;
import com.itsz.sht.common.model.request.ChangePwdRequestModel;
import com.itsz.sht.common.model.request.ChangeTAndCRequestModel;
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
import com.itsz.sht.common.model.response.CancelOrderResponseModel;
import com.itsz.sht.common.model.response.CashDetailResponseModel;
import com.itsz.sht.common.model.response.ChangePwdResponseModel;
import com.itsz.sht.common.model.response.ChangeTAndCResponseModel;
import com.itsz.sht.common.model.response.EMCResponseModel;
import com.itsz.sht.common.model.response.EnquireAccountResponseModel;
import com.itsz.sht.common.model.response.EnquireRTQResponseModel;
import com.itsz.sht.common.model.response.EnquireShortRTQResponseModel;
import com.itsz.sht.common.model.response.EnquiryPositionResponseModel;
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
import com.itsz.sht.dao.hibernate.model.UsrProdId;
import com.itsz.sht.dto.ipo.IPORecord;
import com.itsz.sht.model.UserProductRequest;
import com.itsz.sht.service.FacadeImpl;
import com.itsz.sht.service.channels.BusinessTemplate;
import com.itsz.sht.service.common.CommonService;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Member;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import jtest.Stubs;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * FacadeImplTest 是这个 FacadeImpl 类的单元测试类
 * @see com.itsz.sht.service.FacadeImpl
 * @author Parasoft Jtest 9.0
 */
public class FacadeImplTest extends PackageTestCase {
	/**
	 * 构造一个由名称参数指定测试用例的测试.
	 * @param name 测试用例的名字
	 * @author Parasoft Jtest 9.0
	 */
	public FacadeImplTest() {
		/*
		 * 此构造函数不应该被修改. 任何初始化代码
		 * 应该放在 setUp() 方法.
		 */
	}

	/**
	 * 针对方法的测试: accessRTQ(com.itsz.sht.common.model.common.request.AccessRTQRequestModel)
	 * @throws Throwable 测试可能抛出 Throwable
	 * @see FacadeImpl#accessRTQ(com.itsz.sht.common.model.common.request.AccessRTQRequestModel)
	 * @author Parasoft Jtest 9.0
	 */
	@Test
	public void testAccessRTQ1() throws Throwable {

		AccessRTQRequestModel accessRTQRequestModel = new AccessRTQRequestModel();
		accessRTQRequestModel.setChannelId("IWIN01");
		accessRTQRequestModel.setChannelType("IWIN");
		accessRTQRequestModel.setLanguage("GB");
		accessRTQRequestModel.setClientId("0009502");
		accessRTQRequestModel.setClientIp("127.0.0.1");
		AccessRTQResponseModel result = this.facade.accessRTQ(accessRTQRequestModel);
		assertEquals("success", result.getResultForward());
		assertEquals("ADMINPROTAL070013",result.getReturnCode());
	}

	
	@Test
	public void testAccessRTQ3() throws Throwable {
		
		AccessRTQRequestModel accessRTQRequestModel = new AccessRTQRequestModel();
		accessRTQRequestModel.setChannelId("IWIN01");
		accessRTQRequestModel.setChannelType("IWIN");
		accessRTQRequestModel.setLanguage("GB");
		accessRTQRequestModel.setClientId("0002193");
		accessRTQRequestModel.setClientIp("127.0.0.1");
		AccessRTQResponseModel result = this.facade.accessRTQ(accessRTQRequestModel);
		assertEquals("success", result.getResultForward());
		assertEquals("ADMINPROTAL070024",result.getReturnCode());
	}
	
	@Test
	public void testAccessRTQ5() throws Throwable {
		
		AccessRTQRequestModel accessRTQRequestModel = new AccessRTQRequestModel();
		accessRTQRequestModel.setChannelId("IWIN01");
		accessRTQRequestModel.setChannelType("IWIN");
		accessRTQRequestModel.setLanguage("GB");
		accessRTQRequestModel.setClientId("0100567");
		accessRTQRequestModel.setClientIp("127.0.0.1");
		AccessRTQResponseModel result = this.facade.accessRTQ(accessRTQRequestModel);
		assertEquals("success", result.getResultForward());
		assertEquals("ADMINPROTAL070017",result.getReturnCode());
	}
	
	@Test
	public void testAccessRTQ4() throws Throwable {
		
		AccessRTQRequestModel accessRTQRequestModel = new AccessRTQRequestModel();
		accessRTQRequestModel.setChannelId("IWIN01");
		accessRTQRequestModel.setChannelType("IWIN");
		accessRTQRequestModel.setLanguage("GB");
		accessRTQRequestModel.setClientId("0002193");
		accessRTQRequestModel.setClientIp("127.0.0.1");
		AccessRTQResponseModel result = this.facade.accessRTQ(accessRTQRequestModel);
		assertEquals("success", result.getResultForward());
		assertEquals("NORMAL",result.getReturnCode());
	}

	/**
	 * 针对方法的测试: accessSHK(com.itsz.sht.common.model.common.request.AccessSHKRequestModel)
	 * @throws Throwable 测试可能抛出 Throwable
	 * @see FacadeImpl#accessSHK(com.itsz.sht.common.model.common.request.AccessSHKRequestModel)
	 * @author Parasoft Jtest 9.0
	 */
	@Test
	public void testAccessSHK1() throws Throwable {
		AccessSHKRequestModel accessSHKRequestModel=new AccessSHKRequestModel();
		accessSHKRequestModel.setChannelId("IWEB01");
		accessSHKRequestModel.setChannelType("IWEB");
		accessSHKRequestModel.setClientId("0100567");
		accessSHKRequestModel.setLanguage("BIG5");
		accessSHKRequestModel.setProductId("SHK");
		accessSHKRequestModel.setVersionId("25");
		AccessSHKResponseModel result = this.facade.accessSHK(accessSHKRequestModel);
		assertEquals("success", result.getResultForward());
		assertEquals("NORMAL",result.getReturnCode());
		assertEquals("AVAIL",result.getRtqStatus());
	}
	
	@Test
	public void testAccessSHK2() throws Throwable {
		AccessSHKRequestModel accessSHKRequestModel=new AccessSHKRequestModel();
		accessSHKRequestModel.setChannelId("IWEB01");
		accessSHKRequestModel.setChannelType("IWEB");
		accessSHKRequestModel.setClientId("0100534");
		accessSHKRequestModel.setLanguage("BIG5");
		accessSHKRequestModel.setProductId("SHK");
		accessSHKRequestModel.setVersionId("25");
		AccessSHKResponseModel result = this.facade.accessSHK(accessSHKRequestModel);
		assertEquals("success", result.getResultForward());
		assertEquals("ADMINPROTAL070014",result.getReturnCode());
	}

	/**
	 * 针对方法的测试: bocTransfer(com.itsz.sht.common.model.request.BOCTransferRequestModel)
	 * @throws Throwable 测试可能抛出 Throwable
	 * @see FacadeImpl#bocTransfer(com.itsz.sht.common.model.request.BOCTransferRequestModel)
	 * @author Parasoft Jtest 9.0
	 */
	@Test
	public void testBocTransfer1() throws Throwable {
		
		BOCTransferRequestModel requestModel = new BOCTransferRequestModel();
		requestModel.setChannelId("IWIN01");
		requestModel.setChannelType("IWIN");
		requestModel.setLanguage("GB");
		requestModel.setAccountId("02-0901605-30");
		requestModel.setLocale("CHI");
		requestModel.setPayAmount(BigDecimal.valueOf(Double.parseDouble("1")));
		requestModel.setTransactionDateTime("20110406122000");
		BOCTransferResponseModel result = this.facade.bocTransfer(requestModel);
		assertEquals("success", result.getResultForward());
		assertEquals("NORMAL",result.getReturnCode());
	}

	
	@Test
	public void testBulkCancelOrder1() throws Throwable {
		BulkCancelOrderRequestModel orderModel = new BulkCancelOrderRequestModel();
		orderModel.setChannelId("IWIN01");
		orderModel.setChannelType("IWIN");
		orderModel.setLanguage("GB");
		orderModel.setAccountId("02-0155116-33");
		orderModel.setListSize(0);
		orderModel.setLoginId("alanv111");
		Vector<String> vec = new Vector<String>();
		vec.add("80085");
		orderModel.setMcsOrderID(vec);
		orderModel.setPassword("555555");
		orderModel.setTransactionProtection("Y");
		BulkCancelOrderResponseModel result = this.facade.bulkCancelOrder(orderModel);
		assertEquals("failure", result.getResultForward());
		assertEquals("MCS00403", result.getReturnCode());
	}

	/**
	 * 针对方法的测试: bulkCancelOrder(com.itsz.sht.common.model.request.BulkCancelOrderRequestModel)
	 * @throws Throwable 测试可能抛出 Throwable
	 * @see FacadeImpl#bulkCancelOrder(com.itsz.sht.common.model.request.BulkCancelOrderRequestModel)
	 * @author Parasoft Jtest 9.0
	 */
	@Test
	public void testBulkCancelOrder2() throws Throwable {
		
		BulkCancelOrderRequestModel orderModel = new BulkCancelOrderRequestModel();
		orderModel.setChannelId("IWIN01");
		orderModel.setChannelType("IWIN");
		orderModel.setLanguage("GB");
		orderModel.setAccountId("02-0155116-33");
		orderModel.setListSize(0);
		orderModel.setLoginId("alanv111");
		Vector<String> vec = new Vector<String>();
		vec.add("80085");
		orderModel.setMcsOrderID(vec);
		orderModel.setPassword("000000");
		orderModel.setTransactionProtection("Y");
		BulkCancelOrderResponseModel result = this.facade.bulkCancelOrder(orderModel);
		assertEquals("success", result.getResultForward());
		assertEquals("NORMAL", result.getReturnCode());
	}

	/**
	 * 针对方法的测试: calOrderFee(com.itsz.sht.common.model.request.OrderFeeRequestModel)
	 * @throws Throwable 测试可能抛出 Throwable
	 * @see FacadeImpl#calOrderFee(com.itsz.sht.common.model.request.OrderFeeRequestModel)
	 * @author Parasoft Jtest 9.0
	 */
	@Test
	public void testCalOrderFee1() throws Throwable {
		
		OrderFeeRequestModel orderFeeModel = new OrderFeeRequestModel();
		orderFeeModel.setAccountId("02-0155116-33");
		orderFeeModel.setMarketCode("AMS3");
		orderFeeModel.setInstrCode("3");
		orderFeeModel.setOrderQuantity(BigDecimal.valueOf(Double.parseDouble("2000")));
		orderFeeModel.setOrderPrice(BigDecimal.valueOf(Double.parseDouble("18.840")));
		orderFeeModel.setOrderSide("B");
		orderFeeModel.setChannelId("IWIN01");
		orderFeeModel.setChannelType("IWIN");
		orderFeeModel.setLanguage("GB");
		orderFeeModel.setLotsize(null);
		orderFeeModel.setOrderType(null);
		orderFeeModel.setAllOrNothing("N");
		orderFeeModel.setIsConditionType(null);
		orderFeeModel.setConditionType(null);
		orderFeeModel.setTriggerPrice(null);
		orderFeeModel.setCustomerId(null);
		orderFeeModel.setIsNeedCallMos(null);
		OrderFeeResponseModel result = this.facade.calOrderFee(orderFeeModel);
		assertEquals("success", result.getResultForward());
		assertEquals("NORMAL",result.getReturnCode());
	}

	/**
	 * 针对方法的测试: cancelOrder(com.itsz.sht.common.model.request.CancelOrderRequestModel)
	 * @throws Throwable 测试可能抛出 Throwable
	 * @see FacadeImpl#cancelOrder(com.itsz.sht.common.model.request.CancelOrderRequestModel)
	 * @author Parasoft Jtest 9.0
	 */
	@Test
	public void testCancelOrder1() throws Throwable {
		CancelOrderRequestModel cancelOrderRequestModel=new CancelOrderRequestModel();
		cancelOrderRequestModel.setAccountId("02-0901605-30");
		cancelOrderRequestModel.setChannelId("IWEB01");
		cancelOrderRequestModel.setChannelType("IWEB");
		cancelOrderRequestModel.setCustCode("0901605");
		cancelOrderRequestModel.setLanguage("BIG5");
		cancelOrderRequestModel.setLoginId("0901605");
		cancelOrderRequestModel.setMCSOrderID(80282);
		cancelOrderRequestModel.setPassword("000000");
		cancelOrderRequestModel.setTransactionProtection("Y");
		cancelOrderRequestModel.setVersionId("25");
		CancelOrderResponseModel result = this.facade.cancelOrder(cancelOrderRequestModel);
		assertEquals("success", result.getResultForward());
		assertEquals("NORMAL",result.getReturnCode());
	}

	/**
	 * 针对方法的测试: cancelReservedUserProduct(com.itsz.sht.common.model.common.request.UpdateUserProductRequestModel)
	 * @throws Throwable 测试可能抛出 Throwable
	 * @see FacadeImpl#cancelReservedUserProduct(com.itsz.sht.common.model.common.request.UpdateUserProductRequestModel)
	 * @author Parasoft Jtest 9.0
	 */
	@Test
	public void testCancelReservedUserProduct1() throws Throwable {
		UpdateUserProductRequestModel updateUserProductRequestModel=new UpdateUserProductRequestModel();
		updateUserProductRequestModel.setChannelId("IWEB01");
		updateUserProductRequestModel.setChannelType("IWEB");
		updateUserProductRequestModel.setLanguage("BIG5");
		List<UserProductRequest> list=new ArrayList<UserProductRequest>();
		UserProductRequest upr=new UserProductRequest();
		upr.setAllwRenl("N");
		UsrProdId upi=new UsrProdId();
		upi.setClntId("0042038");
		upi.setProdId("SSTR_AAST");
		upr.setId(upi);
		list.add(upr);
		updateUserProductRequestModel.setVersionId("25");
		updateUserProductRequestModel.setUserProductRequestList(list);
		UserProductResponseModel result = this.facade.cancelReservedUserProduct(updateUserProductRequestModel);
		assertEquals("success", result.getResultForward());
		assertEquals("NORMAL",result.getReturnCode());

	}


	/**
	 * 针对方法的测试: changePassword(com.itsz.sht.common.model.request.ChangePwdRequestModel)
	 * @throws Throwable 测试可能抛出 Throwable
	 * @see FacadeImpl#changePassword(com.itsz.sht.common.model.request.ChangePwdRequestModel)
	 * @author Parasoft Jtest 9.0
	 */
	@Test
	public void testChangePassword1() throws Throwable {
		ChangePwdRequestModel changePwdModel = new ChangePwdRequestModel();
		changePwdModel.setChannelId("IWIN01");
		changePwdModel.setChannelType("IWIN");
		changePwdModel.setLanguage("GB");
		changePwdModel.setLoginId("alanv111");
		changePwdModel.setNewPassword("111111");
		changePwdModel.setOldPassword("555555");
		ChangePwdResponseModel result = this.facade.changePassword(changePwdModel);
		assertEquals("failure", result.getResultForward());
		assertEquals("INV_PASS", result.getReturnCode());
	}
	
	@Test
	public void testChangePassword2() throws Throwable {
		ChangePwdRequestModel changePwdModel = new ChangePwdRequestModel();
		changePwdModel.setChannelId("IWIN01");
		changePwdModel.setChannelType("IWIN");
		changePwdModel.setLanguage("GB");
		changePwdModel.setLoginId("alanv111");
		changePwdModel.setNewPassword("000000");
		changePwdModel.setOldPassword("000000");
		ChangePwdResponseModel result = this.facade.changePassword(changePwdModel);
		assertEquals("failure", result.getResultForward());
		assertEquals("INV_N_PASS_N", result.getReturnCode());
	}

	/**
	 * 针对方法的测试: changePassword(com.itsz.sht.common.model.request.ChangePwdRequestModel)
	 * @throws Throwable 测试可能抛出 Throwable
	 * @see FacadeImpl#changePassword(com.itsz.sht.common.model.request.ChangePwdRequestModel)
	 * @author Parasoft Jtest 9.0
	 */
	@Test
	public void testChangePassword3() throws Throwable {
		ChangePwdRequestModel changePwdModel = new ChangePwdRequestModel();
		changePwdModel.setChannelId("IWIN01");
		changePwdModel.setChannelType("IWIN");
		changePwdModel.setLanguage("GB");
		changePwdModel.setLoginId("002193");
		changePwdModel.setNewPassword("000000");
		changePwdModel.setOldPassword("111111");
		ChangePwdResponseModel result = this.facade.changePassword(changePwdModel);
		assertEquals("success", result.getResultForward());
		assertEquals("NORMAL", result.getReturnCode());
	}

	/**
	 * 针对方法的测试: changeTransactionProtection(com.itsz.sht.common.model.request.TransactionProtectionRequestModel)
	 * @throws Throwable 测试可能抛出 Throwable
	 * @see FacadeImpl#changeTransactionProtection(com.itsz.sht.common.model.request.TransactionProtectionRequestModel)
	 * @author Parasoft Jtest 9.0
	 */
	
	@Test
	public void testChangeTransactionProtection3() throws Throwable {
		TransactionProtectionRequestModel ptnRequest = new TransactionProtectionRequestModel();
		ptnRequest.setChannelId("IWIN01");
		ptnRequest.setChannelType("IWIN");
		ptnRequest.setLanguage("GB");
		ptnRequest.setLoginId("alanv111");
		ptnRequest.setNeedVarifyPassword(null);
		ptnRequest.setPassword("555555");
		ptnRequest.setTransactionProtection("N");
		TransactionProtectionResponseModel result = this.facade.changeTransactionProtection(ptnRequest);
		assertEquals("failure", result.getResultForward());
		assertEquals("WEB051013", result.getReturnCode());
	}
	@Test
	public void testChangeTransactionProtection1() throws Throwable {
		TransactionProtectionRequestModel ptnRequest = new TransactionProtectionRequestModel();
		ptnRequest.setChannelId("IWIN01");
		ptnRequest.setChannelType("IWIN");
		ptnRequest.setLanguage("GB");
		ptnRequest.setLoginId("100534");
		ptnRequest.setNeedVarifyPassword(null);
		ptnRequest.setPassword("000000");
		ptnRequest.setTransactionProtection("N");
		TransactionProtectionResponseModel result = this.facade.changeTransactionProtection(ptnRequest);
		assertEquals("success", result.getResultForward());
		assertEquals("NORMAL", result.getReturnCode());
	}

	/**
	 * 针对方法的测试: changeTransactionProtection(com.itsz.sht.common.model.request.TransactionProtectionRequestModel)
	 * @throws Throwable 测试可能抛出 Throwable
	 * @see FacadeImpl#changeTransactionProtection(com.itsz.sht.common.model.request.TransactionProtectionRequestModel)
	 * @author Parasoft Jtest 9.0
	 */
	@Test
	public void testChangeTransactionProtection2() throws Throwable {
		TransactionProtectionRequestModel ptnRequest = new TransactionProtectionRequestModel();
		ptnRequest.setChannelId("IWIN01");
		ptnRequest.setChannelType("IWIN");
		ptnRequest.setLanguage("GB");
		ptnRequest.setLoginId("alanv111");
		ptnRequest.setNeedVarifyPassword(null);
		ptnRequest.setPassword("");
		ptnRequest.setTransactionProtection("Y");
		TransactionProtectionResponseModel result = this.facade.changeTransactionProtection(ptnRequest);
		assertEquals("success", result.getResultForward());
		assertEquals("NORMAL", result.getReturnCode());
	}

	/**
	 * 针对方法的测试: enquireAccountDetail(com.itsz.sht.common.model.request.EnquireAccountRequestModel)
	 * @throws Throwable 测试可能抛出 Throwable
	 * @see FacadeImpl#enquireAccountDetail(com.itsz.sht.common.model.request.EnquireAccountRequestModel)
	 * @author Parasoft Jtest 9.0
	 */
	@Test
	public void testEnquireAccountDetail1() throws Throwable {
		
		EnquireAccountRequestModel accountModel = new EnquireAccountRequestModel();
		accountModel.setChannelId("IWIN01");
		accountModel.setChannelType("IWIN");
		accountModel.setLanguage("GB");
		accountModel.setAccountId("02-0901605-30");
		accountModel.setAccountType("C");
		accountModel.setOnline("ON");
		EnquireAccountResponseModel result = this.facade.enquireAccountDetail(accountModel);
		assertEquals("success", result.getResultForward());
		assertEquals("NORMAL",result.getReturnCode());
	}
	
	@Test
	public void testEnquireAccountDetail2() throws Throwable {
		
		EnquireAccountRequestModel accountModel = new EnquireAccountRequestModel();
		accountModel.setChannelId("IWIN01");
		accountModel.setChannelType("IWIN");
		accountModel.setLanguage("GB");
		accountModel.setAccountId("");
		accountModel.setAccountType("C");
		accountModel.setOnline("ON");
		EnquireAccountResponseModel result = this.facade.enquireAccountDetail(accountModel);
		assertEquals("showMsg", result.getResultForward());
		assertEquals("MCS00402",result.getReturnCode());
	}
	
	@Test
	public void testEnquireAccountDetail3() throws Throwable {
		
		EnquireAccountRequestModel accountModel = new EnquireAccountRequestModel();
		accountModel.setChannelId("IWIN01");
		accountModel.setChannelType("IWIN");
		accountModel.setLanguage("GB");
		accountModel.setAccountId("ddddd");
		accountModel.setAccountType("C");
		accountModel.setOnline("ON");
		EnquireAccountResponseModel result = this.facade.enquireAccountDetail(accountModel);
		assertEquals("showMsg", result.getResultForward());
		assertEquals("MCS00400",result.getReturnCode());
	}


	/**
	 * 针对方法的测试: enquireAccountDetailForPs(com.itsz.sht.common.model.request.EnquireAccountRequestModel)
	 * @throws Throwable 测试可能抛出 Throwable
	 * @see FacadeImpl#enquireAccountDetailForPs(com.itsz.sht.common.model.request.EnquireAccountRequestModel)
	 * @author Parasoft Jtest 9.0
	 */
	@Test
	public void testEnquireAccountDetailForPs1() throws Throwable {
		
		EnquireAccountRequestModel accountModel = new EnquireAccountRequestModel();
		accountModel.setChannelId("IWIN01");
		accountModel.setChannelType("IWIN");
		accountModel.setLanguage("GB");
		accountModel.setAccountId("02-0901605-30");
		accountModel.setAccountType("C");
		accountModel.setOnline("ON");
		EnquireAccountResponseModel result = this.facade.enquireAccountDetailForPs(accountModel);
		assertEquals("success", result.getResultForward());
		assertEquals("NORMAL",result.getReturnCode());
	}
	
	@Test
	public void testEnquireAccountDetailForPs2() throws Throwable {
		
		EnquireAccountRequestModel accountModel = new EnquireAccountRequestModel();
		accountModel.setChannelId("IWIN01");
		accountModel.setChannelType("IWIN");
		accountModel.setLanguage("GB");
		accountModel.setAccountId("02-0901605");
		accountModel.setAccountType("C");
		accountModel.setOnline("ON");
		EnquireAccountResponseModel result = this.facade.enquireAccountDetailForPs(accountModel);
		assertEquals("showMsg", result.getResultForward());
		assertEquals("MCS00400",result.getReturnCode());
	}
	
	@Test
	public void testEnquireAccountDetailForPs3() throws Throwable {
		
		EnquireAccountRequestModel accountModel = new EnquireAccountRequestModel();
		accountModel.setChannelId("IWIN01");
		accountModel.setChannelType("IWIN");
		accountModel.setLanguage("GB");
		accountModel.setAccountId("");
		accountModel.setAccountType("C");
		accountModel.setOnline("ON");
		EnquireAccountResponseModel result = this.facade.enquireAccountDetailForPs(accountModel);
		assertEquals("showMsg", result.getResultForward());
		assertEquals("MCS00402",result.getReturnCode());
	}

	/**
	 * 针对方法的测试: enquireAccountList(com.itsz.sht.common.model.request.EnquireAccountListRequestModel)
	 * @throws Throwable 测试可能抛出 Throwable
	 * @see FacadeImpl#enquireAccountList(com.itsz.sht.common.model.request.EnquireAccountListRequestModel)
	 * @author Parasoft Jtest 9.0
	 */
	@Test
	public void testEnquireAccountList1() throws Throwable {
		EnquireAccountListRequestModel enquireAccountListRequestModel=new EnquireAccountListRequestModel();
		enquireAccountListRequestModel.setChannelId("IWEB01");
		enquireAccountListRequestModel.setChannelType("IWIN");
		enquireAccountListRequestModel.setCustCode("0042038");
		enquireAccountListRequestModel.setLanguage("BIG5");
		enquireAccountListRequestModel.setVersionId("25");
		MISAccountListResponseModel result = this.facade.enquireAccountList(enquireAccountListRequestModel);
		assertEquals("success", result.getResultForward());
		assertEquals("NORMAL",result.getReturnCode());
		assertEquals(2,result.getMisAccountListResponse().getAccountListDetail().size());
	}

	/**
	 * 针对方法的测试: enquireCashDetail(com.itsz.sht.common.model.request.CashDetailRequestModel)
	 * @throws Throwable 测试可能抛出 Throwable
	 * @see FacadeImpl#enquireCashDetail(com.itsz.sht.common.model.request.CashDetailRequestModel)
	 * @author Parasoft Jtest 9.0
	 */
	@Test
	public void testEnquireCashDetail1() throws Throwable {
		
		CashDetailRequestModel cashDetailRequestModel = new CashDetailRequestModel();
		cashDetailRequestModel.setChannelId("IWIN01");
		cashDetailRequestModel.setChannelType("IWIN");
		cashDetailRequestModel.setLanguage("GB");
		cashDetailRequestModel.setAccountId("02-0901605-30");
		cashDetailRequestModel.setMarketCode("AMS3");
		CashDetailResponseModel result = this.facade.enquireCashDetail(cashDetailRequestModel);
		assertEquals("success", result.getResultForward());
		assertEquals("NORMAL",result.getReturnCode());
	}
	
	@Test
	public void testEnquireCashDetail2() throws Throwable {
		
		CashDetailRequestModel cashDetailRequestModel = new CashDetailRequestModel();
		cashDetailRequestModel.setChannelId("IWIN01");
		cashDetailRequestModel.setChannelType("IWIN");
		cashDetailRequestModel.setLanguage("GB");
		cashDetailRequestModel.setAccountId("");
		cashDetailRequestModel.setMarketCode("AMS3");
		CashDetailResponseModel result = this.facade.enquireCashDetail(cashDetailRequestModel);
		assertEquals("showMsg", result.getResultForward());
		assertEquals("MCS00402",result.getReturnCode());
	}
	
	@Test
    public void testEnquireCashDetail3() throws Throwable {
		
		CashDetailRequestModel cashDetailRequestModel = new CashDetailRequestModel();
		cashDetailRequestModel.setChannelId("IWIN01");
		cashDetailRequestModel.setChannelType("IWIN");
		cashDetailRequestModel.setLanguage("GB");
		cashDetailRequestModel.setAccountId("dddddd");
		cashDetailRequestModel.setMarketCode("AMS3");
		CashDetailResponseModel result = this.facade.enquireCashDetail(cashDetailRequestModel);
		assertEquals("showMsg", result.getResultForward());
		assertEquals("MCS00400",result.getReturnCode());
	}

	/**
	 * 针对方法的测试: enquireMarginRations(com.itsz.sht.common.model.request.MarginFinancingListRequestModel)
	 * @throws Throwable 测试可能抛出 Throwable
	 * @see FacadeImpl#enquireMarginRations(com.itsz.sht.common.model.request.MarginFinancingListRequestModel)
	 * @author Parasoft Jtest 9.0
	 */
	@Test
	public void testEnquireMarginRations1() throws Throwable {
		MarginFinancingListRequestModel marginFinancingListRequestModel=new MarginFinancingListRequestModel();
		marginFinancingListRequestModel.setChannelId("IWEB01");
		marginFinancingListRequestModel.setChannelType("IWEB");
		marginFinancingListRequestModel.setInstrCode("d");
		marginFinancingListRequestModel.setLanguage("BIG5");
		marginFinancingListRequestModel.setLoginId("0042038");
		marginFinancingListRequestModel.setVersionId("25");
		MarginFinancingListResponseModel result = this.facade.enquireMarginRations(marginFinancingListRequestModel);
		assertEquals("success", result.getResultForward());
		assertEquals("NORMAL",result.getReturnCode());
		assertEquals(0,result.getMarginFinancingListResponse().getStockInfos().size());
	}
	
	@Test
	public void testEnquireMarginRations2() throws Throwable {
		MarginFinancingListRequestModel marginFinancingListRequestModel=new MarginFinancingListRequestModel();
		marginFinancingListRequestModel.setChannelId("IWEB01");
		marginFinancingListRequestModel.setChannelType("IWEB");
		marginFinancingListRequestModel.setInstrCode("8");
		marginFinancingListRequestModel.setLanguage("BIG5");
		marginFinancingListRequestModel.setLoginId("0042038");
		marginFinancingListRequestModel.setVersionId("25");
		MarginFinancingListResponseModel result = this.facade.enquireMarginRations(marginFinancingListRequestModel);
		assertEquals("success", result.getResultForward());
		assertEquals("NORMAL",result.getReturnCode());
		assertEquals(1,result.getMarginFinancingListResponse().getStockInfos().size());
	}


	/**
	 * 针对方法的测试: enquireMisAccount(com.itsz.sht.common.model.request.EnquireAccountListRequestModel)
	 * @throws Throwable 测试可能抛出 Throwable
	 * @see FacadeImpl#enquireMisAccount(com.itsz.sht.common.model.request.EnquireAccountListRequestModel)
	 * @author Parasoft Jtest 9.0
	 */
	@Test
	public void testEnquireMisAccount1() throws Throwable {
		
		EnquireAccountListRequestModel accountModel = new EnquireAccountListRequestModel();
		accountModel.setChannelId("IWIN01");
		accountModel.setChannelType("IWIN");
		accountModel.setLanguage("GB");
		accountModel.setCustCode("0901605");
		MISAccountEnquiryResponseModel result = this.facade.enquireMisAccount(accountModel);
		assertEquals("success", result.getResultForward());
		assertEquals("NORMAL",result.getReturnCode());
	}
	
	@Test
	public void testEnquireMisAccount2() throws Throwable {
		
		EnquireAccountListRequestModel accountModel = new EnquireAccountListRequestModel();
		accountModel.setChannelId("IWIN01");
		accountModel.setChannelType("IWIN");
		accountModel.setLanguage("GB");
		accountModel.setCustCode("sdfasdf");
		MISAccountEnquiryResponseModel result = this.facade.enquireMisAccount(accountModel);
		assertEquals("success", result.getResultForward());
		assertEquals("MCS00400",result.getReturnCode());
	}
	
	@Test
	public void testEnquireMisAccount3() throws Throwable {
		
		EnquireAccountListRequestModel accountModel = new EnquireAccountListRequestModel();
		accountModel.setChannelId("IWIN01");
		accountModel.setChannelType("IWIN");
		accountModel.setLanguage("GB");
		accountModel.setCustCode("");
		MISAccountEnquiryResponseModel result = this.facade.enquireMisAccount(accountModel);
		assertEquals("success", result.getResultForward());
		assertEquals("MCS00402",result.getReturnCode());
	}


	/**
	 * 针对方法的测试: enquireRTQInfo(com.itsz.sht.common.model.request.EnquireRTQRequestModel)
	 * @throws Throwable 测试可能抛出 Throwable
	 * @see FacadeImpl#enquireRTQInfo(com.itsz.sht.common.model.request.EnquireRTQRequestModel)
	 * @author Parasoft Jtest 9.0
	 */
	@Test
	public void testEnquireRTQInfo1() throws Throwable {
		EnquireRTQRequestModel rtqRequest = new EnquireRTQRequestModel();
		rtqRequest.setChannelId("IWIN01");
		rtqRequest.setChannelType("IWIN");
		rtqRequest.setLanguage("GB");
		Vector<String> vec = new Vector<String>();
		vec.add("00001");
		rtqRequest.setCode(vec);
		rtqRequest.setCustomerId("MONITOR");
		rtqRequest.setDelayTime("15");
		rtqRequest.setSeqn("3");
		rtqRequest.setServiceType("2");
		rtqRequest.setSubType("SRFP");
		rtqRequest.setType("ENQ");
		EnquireRTQResponseModel result = this.facade.enquireRTQInfo(rtqRequest);
		assertEquals("success", result.getResultForward());
		assertEquals("NORMAL",result.getReturnCode());
	}
	@Test
	public void testEnquireRTQInfo2() throws Throwable {//quotation server 连接不上时的用例
		EnquireRTQRequestModel rtqRequest = new EnquireRTQRequestModel();
		rtqRequest.setChannelId("IWIN01");
		rtqRequest.setChannelType("IWIN");
		rtqRequest.setLanguage("GB");
		Vector<String> vec = new Vector<String>();
		vec.add("00002");
		rtqRequest.setCode(vec);
		rtqRequest.setCustomerId("MONITOR");
		rtqRequest.setDelayTime("15");
		rtqRequest.setSeqn("3");
		rtqRequest.setServiceType("2");
		rtqRequest.setSubType("SDDP");
		rtqRequest.setType("QUOT");
		EnquireRTQResponseModel result = this.facade.enquireRTQInfo(rtqRequest);
		assertEquals("failure", result.getResultForward());
		assertEquals("sys005", result.getReturnCode());
	}

	/**
	 * 针对方法的测试: enquireTradeList(com.itsz.sht.common.model.request.TradeListRequestModel)
	 * @throws Throwable 测试可能抛出 Throwable
	 * @see FacadeImpl#enquireTradeList(com.itsz.sht.common.model.request.TradeListRequestModel)
	 * @author Parasoft Jtest 9.0
	 */
	@Test
	public void testEnquireTradeList1() throws Throwable {
		
		TradeListRequestModel requestModel = new TradeListRequestModel();
		requestModel.setChannelId("IWIN01");
		requestModel.setChannelType("IWIN");
		requestModel.setLanguage("GB");
		requestModel.setAccountId("02-0901605-30");
		requestModel.setChannel(null);
		requestModel.setFromDate("2011-03-30 00:00:00");
		requestModel.setFromIdx(null);
		requestModel.setInstrCode(null);
		requestModel.setLoginId("0901605");
		requestModel.setOrderSide(null);
		requestModel.setPageNo(null);
		requestModel.setPageSize(null);
		requestModel.setToDate("2011-04-06 00:00:00");
		requestModel.setToIdx(null);
		TradeListResponseModel result = this.facade.enquireTradeList(requestModel);
		assertEquals("success", result.getResultForward());
		assertEquals("NORMAL",result.getReturnCode());
	}
	
	@Test
	public void testEnquireTradeList2() throws Throwable {
		
		TradeListRequestModel requestModel = new TradeListRequestModel();
		requestModel.setChannelId("IWIN01");
		requestModel.setChannelType("IWIN");
		requestModel.setLanguage("GB");
		requestModel.setAccountId("");
		requestModel.setChannel(null);
		requestModel.setFromDate("2011-03-30 00:00:00");
		requestModel.setFromIdx(null);
		requestModel.setInstrCode(null);
		requestModel.setLoginId("");
		requestModel.setOrderSide(null);
		requestModel.setPageNo(null);
		requestModel.setPageSize(null);
		requestModel.setToDate("2011-04-06 00:00:00");
		requestModel.setToIdx(null);
		TradeListResponseModel result = this.facade.enquireTradeList(requestModel);
		assertEquals("showMsg", result.getResultForward());
		assertEquals("MCS00606",result.getReturnCode());
	}

	/**
	 * 针对方法的测试: enquiryPosition(com.itsz.sht.common.model.request.EnquiryPositionRequestModel)
	 * @throws Throwable 测试可能抛出 Throwable
	 * @see FacadeImpl#enquiryPosition(com.itsz.sht.common.model.request.EnquiryPositionRequestModel)
	 * @author Parasoft Jtest 9.0
	 */
	@Test
	public void testEnquiryPosition1() throws Throwable {
		
		EnquiryPositionRequestModel positionModel = new EnquiryPositionRequestModel();
		positionModel.setChannelId("IWIN01");
		positionModel.setChannelType("IWIN");
		positionModel.setLanguage("GB");
		positionModel.setAccountId("02-0901605-30");
		positionModel.setAllowTradeStatusFlag("Y");
		positionModel.setDefaultAccountId(null);
		positionModel.setFromIdx(0);
		positionModel.setMarketCode(null);
		positionModel.setMaxResults(0);
		EnquiryPositionResponseModel result = this.facade.enquiryPosition(positionModel);
		assertEquals("success", result.getResultForward());
		assertEquals("NORMAL",result.getReturnCode());
	}

	/**
	 * 针对方法的测试: filterOrder(com.itsz.sht.common.model.request.FilterOrderRequestModel)
	 * @throws Throwable 测试可能抛出 Throwable
	 * @see FacadeImpl#filterOrder(com.itsz.sht.common.model.request.FilterOrderRequestModel)
	 * @author Parasoft Jtest 9.0
	 */
	@Test
	public void testFilterOrder1() throws Throwable {
		
		FilterOrderRequestModel listModel = new FilterOrderRequestModel();
		listModel.setChannelId("IWIN01");
		listModel.setChannelType("IWIN");
		listModel.setLanguage("GB");
		listModel.setAccountId("02-0901605-30");
		listModel.setFromIdx(0);
		listModel.setInstrCode(null);
		listModel.setMarketCode(null);
		listModel.setOrderStateType("");
		listModel.setPageNo("1");
		listModel.setPageSize(null);
		listModel.setSupportOverNightFlag("Y");
		listModel.setToIdx(0);
		FilterOrderResponseModel result = this.facade.filterOrder(listModel);
		assertEquals("success", result.getResultForward());
		assertEquals("NORMAL",result.getReturnCode());
	}

	/**
	 * 针对方法的测试: findClientUserProfile(com.itsz.sht.common.model.common.request.UserProfileRequestModel)
	 * @throws Throwable 测试可能抛出 Throwable
	 * @see FacadeImpl#findClientUserProfile(com.itsz.sht.common.model.common.request.UserProfileRequestModel)
	 * @author Parasoft Jtest 9.0
	 */
	@Test
	public void testFindClientUserProfile1() throws Throwable {
		UserProfileRequestModel userProfileRequestModel=new UserProfileRequestModel();
		userProfileRequestModel.setChannelId("IWEB01");
		userProfileRequestModel.setChannelType("IWEB");
		userProfileRequestModel.setClientId("0901605");
		userProfileRequestModel.setClntLoginId("0901605");
		userProfileRequestModel.setDefaultDebitAccount("02-0901605-30");
		userProfileRequestModel.setLanguage("BIG5");
		userProfileRequestModel.setVersionId("25");
		UserProfileResponseModel result = this.facade.findClientUserProfile(userProfileRequestModel);
		assertEquals("success", result.getResultForward());
		assertEquals("NORMAL",result.getReturnCode());
	}

	/**
	 * 针对方法的测试: fundDeposit(com.itsz.sht.common.model.request.FundDepositRequestModel)
	 * @throws Throwable 测试可能抛出 Throwable
	 * @see FacadeImpl#fundDeposit(com.itsz.sht.common.model.request.FundDepositRequestModel)
	 * @author Parasoft Jtest 9.0
	 */
	@Test
	public void testFundDeposit1() throws Throwable {
		FundDepositRequestModel fundDepositRequestModel=new FundDepositRequestModel();
		fundDepositRequestModel.setAccountName("Client 0901605");
		fundDepositRequestModel.setAccountNo("02-0901605-30");
		fundDepositRequestModel.setAmount(new BigDecimal(10));
		fundDepositRequestModel.setBank("HSBCNET");
		fundDepositRequestModel.setBankAcc("500-113089-001");
		fundDepositRequestModel.setChannelId("IWEB01");
		fundDepositRequestModel.setChannelType("IWEB");
		fundDepositRequestModel.setDepositDate(new Date());
		fundDepositRequestModel.setCurrency("HKD");
		fundDepositRequestModel.setDepositTime("01:04");
		fundDepositRequestModel.setLanguage("BIG5");
		fundDepositRequestModel.setReceiveDate(new Date());
		fundDepositRequestModel.setReference("12345");
		fundDepositRequestModel.setSource("ATM");
		fundDepositRequestModel.setVersionId("25");
		FundDepositResponseModel result = this.facade.fundDeposit(fundDepositRequestModel);
		assertEquals("success", result.getResultForward());
		assertEquals("NORMAL",result.getReturnCode());
	}


	/**
	 * 针对方法的测试: fundTransfer(com.itsz.sht.common.model.response.FundTransferRequestModel)
	 * @throws Throwable 测试可能抛出 Throwable
	 * @see FacadeImpl#fundTransfer(com.itsz.sht.common.model.response.FundTransferRequestModel)
	 * @author Parasoft Jtest 9.0
	 */
	@Test
	public void testFundTransfer1() throws Throwable {//MCS连接异常的case
//		FundTransferRequestModel requestModel = new FundTransferRequestModel();
//		requestModel.setChannelId("IWIN01");
//		requestModel.setChannelType("IWIN");
//		requestModel.setLanguage("GB");
//		requestModel.setAmount(BigDecimal.valueOf(Double.parseDouble("1")));
//		requestModel.setBank(false);
//		requestModel.setBankCode(null);
//		requestModel.setFromAccountId("02-0100567-30");
//		requestModel.setFromCcy("HKD");
//		requestModel.setLoginId("0100567");
//		requestModel.setPassword("555555");
//		requestModel.setToAccountId("02-0100567-22");
//		requestModel.setToCcy("HKD");
//		FundTransferResponseModel result = this.facade.fundTransfer(requestModel);
//		assertEquals("showMsg", result.getResultForward());
//		assertEquals("sys004",result.getReturnCode());
	}
	@Test
	public void testFundTransfer2() throws Throwable {
		FundTransferRequestModel requestModel = new FundTransferRequestModel();
		requestModel.setChannelId("IWIN01");
		requestModel.setChannelType("IWIN");
		requestModel.setLanguage("GB");
		requestModel.setAmount(BigDecimal.valueOf(Double.parseDouble("1")));
		requestModel.setBank(false);
		requestModel.setBankCode(null);
		requestModel.setFromAccountId("02-0100567-30");
		requestModel.setFromCcy("HKD");
		requestModel.setLoginId("0100567");
		requestModel.setPassword("111111");
		requestModel.setToAccountId("02-0100567-22");
		requestModel.setToCcy("HKD");
		FundTransferResponseModel result = this.facade.fundTransfer(requestModel);
		assertEquals("failure", result.getResultForward());
		assertEquals("MCS00403",result.getReturnCode());
	}

	/**
	 * 针对方法的测试: fundTransfer(com.itsz.sht.common.model.response.FundTransferRequestModel)
	 * @throws Throwable 测试可能抛出 Throwable
	 * @see FacadeImpl#fundTransfer(com.itsz.sht.common.model.response.FundTransferRequestModel)
	 * @author Parasoft Jtest 9.0
	 */
	@Test
	public void testFundTransfer3() throws Throwable {
		FundTransferRequestModel requestModel = new FundTransferRequestModel();
		requestModel.setChannelId("IWIN01");
		requestModel.setChannelType("IWIN");
		requestModel.setLanguage("GB");
		requestModel.setAmount(BigDecimal.valueOf(Double.parseDouble("1")));
		requestModel.setBank(false);
		requestModel.setBankCode(null);
		requestModel.setFromAccountId("02-0100567-30");
		requestModel.setFromCcy("HKD");
		requestModel.setLoginId("0100567");
		requestModel.setPassword("000000");
		requestModel.setToAccountId("02-0100567-22");
		requestModel.setToCcy("HKD");
		requestModel.setVersionId("25");
		FundTransferResponseModel result = this.facade.fundTransfer(requestModel);
		assertEquals("success", result.getResultForward());
		assertEquals("NORMAL",result.getReturnCode());
	}



	/**
	 * 针对方法的测试: getEMCMsgCounts(com.itsz.sht.common.model.request.EMCRequestModel)
	 * @throws Throwable 测试可能抛出 Throwable
	 * @see FacadeImpl#getEMCMsgCounts(com.itsz.sht.common.model.request.EMCRequestModel)
	 * @author Parasoft Jtest 9.0
	 */
	@Test
	public void testGetEMCMsgCounts1() throws Throwable {
	    EMCRequestModel emcRequestModel=new EMCRequestModel();
	    emcRequestModel.setChannelId("IWEB01");
	    emcRequestModel.setChannelType("IWEB");
	    emcRequestModel.setClientId("0901605");
	    emcRequestModel.setCustCode("0901605");
	    emcRequestModel.setLanguage("BIG5");
	    emcRequestModel.setVersionId("25");
        EMCResponseModel result = this.facade.getEMCMsgCounts(emcRequestModel);
        assertEquals("success", result.getResultForward());
		assertEquals("NORMAL",result.getReturnCode());
		assertEquals(0,result.getMsgCounts());
	}


	/**
	 * 针对方法的测试: getEMCMsgURL(com.itsz.sht.common.model.request.EMCRequestModel)
	 * @throws Throwable 测试可能抛出 Throwable
	 * @see FacadeImpl#getEMCMsgURL(com.itsz.sht.common.model.request.EMCRequestModel)
	 * @author Parasoft Jtest 9.0
	 */
	@Test
	public void testGetEMCMsgURL1() throws Throwable {
		EMCRequestModel emcRequestModel=new EMCRequestModel();
	    emcRequestModel.setChannelId("IWEB01");
	    emcRequestModel.setChannelType("IWEB");
	    emcRequestModel.setClientId("0901605");
	    emcRequestModel.setCustCode("0901605");
	    emcRequestModel.setLanguage("BIG5");
	    emcRequestModel.setVersionId("25");
		EMCResponseModel result = this.facade.getEMCMsgURL(emcRequestModel);
		assertEquals("success", result.getResultForward());
		assertEquals("NORMAL",result.getReturnCode());
		assertTrue(StringUtils.isNotBlank(result.getEmcURL()));
	}

	
	/**
	 * 针对方法的测试: getMos(com.itsz.sht.common.model.request.MosRequestModel)
	 * @throws Throwable 测试可能抛出 Throwable
	 * @see FacadeImpl#getMos(com.itsz.sht.common.model.request.MosRequestModel)
	 * @author Parasoft Jtest 9.0
	 */
	@Test
	public void testGetMos1() throws Throwable {
		
		MosRequestModel rtqRequest = new MosRequestModel();
		rtqRequest.setChannelId("IWIN01");
		rtqRequest.setChannelType("IWIN");
		rtqRequest.setLanguage("GB");
		rtqRequest.setAccountId("02-0901605-30");
		rtqRequest.setInstrCode("1");
		rtqRequest.setMarketCode("AMS3");
		rtqRequest.setMessageId(null);
		rtqRequest.setMessageSEQNum(null);
		MosResponseModel result = this.facade.getMos(rtqRequest);
		assertEquals("success", result.getResultForward());
		assertEquals("NORMAL",result.getReturnCode());
	}
	
	@Test
	public void testGetMos2() throws Throwable {
		
		MosRequestModel rtqRequest = new MosRequestModel();
		rtqRequest.setChannelId("IWIN01");
		rtqRequest.setChannelType("IWIN");
		rtqRequest.setLanguage("GB");
		rtqRequest.setAccountId("");
		rtqRequest.setInstrCode("1");
		rtqRequest.setMarketCode("AMS3");
		rtqRequest.setMessageId(null);
		rtqRequest.setMessageSEQNum(null);
		MosResponseModel result = this.facade.getMos(rtqRequest);
		assertEquals("success", result.getResultForward());
		assertEquals("MCS00402",result.getReturnCode());
	}


	/**
	 * 针对方法的测试: getRTQProductList(com.itsz.sht.common.model.common.request.RTQProductRequestModel)
	 * @throws Throwable 测试可能抛出 Throwable
	 * @see FacadeImpl#getRTQProductList(com.itsz.sht.common.model.common.request.RTQProductRequestModel)
	 * @author Parasoft Jtest 9.0
	 */
	@Test
	public void testGetRTQProductList1() throws Throwable {
		RTQProductRequestModel rtqProductRequestModel=new RTQProductRequestModel();
		rtqProductRequestModel.setChannelId("IWEB01");
		rtqProductRequestModel.setChannelType("IWEB");
		rtqProductRequestModel.setClientId("0901605");
		rtqProductRequestModel.setLanguage("BIG5");
		rtqProductRequestModel.setVersionId("25");
		RTQProductResponseModel result = this.facade.getRTQProductList(rtqProductRequestModel);
		assertEquals("success", result.getResultForward());
		assertEquals("NORMAL",result.getReturnCode());
        assertTrue(result.getProductList().size()>0);
	}


	/**
	 * 针对方法的测试: getUserNotificationEmail(com.itsz.sht.common.model.common.request.UserNotificationEmailRequestModel)
	 * @throws Throwable 测试可能抛出 Throwable
	 * @see FacadeImpl#getUserNotificationEmail(com.itsz.sht.common.model.common.request.UserNotificationEmailRequestModel)
	 * @author Parasoft Jtest 9.0
	 */
	@Test
	public void testGetUserNotificationEmail1() throws Throwable {
		UserNotificationEmailRequestModel userNotificationEmailRequestModel=new UserNotificationEmailRequestModel();
		userNotificationEmailRequestModel.setChannelId("IWEB01");
		userNotificationEmailRequestModel.setChannelType("IWEB");
		userNotificationEmailRequestModel.setClientId("0901605");
		userNotificationEmailRequestModel.setLanguage("BIG5");
		userNotificationEmailRequestModel.setNotiFlag(false);
		userNotificationEmailRequestModel.setVersionId("25");
		UserNotificationEmailResponseModel result = this.facade.getUserNotificationEmail(userNotificationEmailRequestModel);
		assertEquals("success", result.getResultForward());
		assertEquals("NORMAL",result.getReturnCode());
	    assertTrue(StringUtils.isNotBlank(result.getUserNotificationEmail()));
	}
	
	@Test
	public void testGetUserNotificationEmail2() throws Throwable {
		UserNotificationEmailRequestModel userNotificationEmailRequestModel=new UserNotificationEmailRequestModel();
		userNotificationEmailRequestModel.setChannelId("IWEB01");
		userNotificationEmailRequestModel.setChannelType("IWEB");
		userNotificationEmailRequestModel.setClientId("");
		userNotificationEmailRequestModel.setLanguage("BIG5");
		userNotificationEmailRequestModel.setNotiFlag(false);
		userNotificationEmailRequestModel.setVersionId("25");
		UserNotificationEmailResponseModel result = this.facade.getUserNotificationEmail(userNotificationEmailRequestModel);
		assertEquals("success", result.getResultForward());
		assertEquals("WEB060011",result.getReturnCode());
	    assertTrue(StringUtils.isBlank(result.getUserNotificationEmail()));
	}
	
	
	@Test
	public void testGetUserNotificationEmail3() throws Throwable {
		UserNotificationEmailRequestModel userNotificationEmailRequestModel=new UserNotificationEmailRequestModel();
		userNotificationEmailRequestModel.setChannelId("IWEB01");
		userNotificationEmailRequestModel.setChannelType("IWEB");
		userNotificationEmailRequestModel.setClientId("123456789");
		userNotificationEmailRequestModel.setLanguage("BIG5");
		userNotificationEmailRequestModel.setNotiFlag(false);
		userNotificationEmailRequestModel.setVersionId("25");
		UserNotificationEmailResponseModel result = this.facade.getUserNotificationEmail(userNotificationEmailRequestModel);
		assertEquals("success", result.getResultForward());
		assertEquals("NORMAL",result.getReturnCode());
	    assertTrue(StringUtils.isBlank(result.getUserNotificationEmail()));
	}


	/**
	 * 针对方法的测试: havePurchasedProduct(com.itsz.sht.common.model.common.request.PurchasedProductRequestModel)
	 * @throws Throwable 测试可能抛出 Throwable
	 * @see FacadeImpl#havePurchasedProduct(com.itsz.sht.common.model.common.request.PurchasedProductRequestModel)
	 * @author Parasoft Jtest 9.0
	 */
	@Test
	public void testHavePurchasedProduct1() throws Throwable {
		PurchasedProductRequestModel purchasedProductRequestModel=new PurchasedProductRequestModel();
		purchasedProductRequestModel.setChannelId("IWEB01");
		purchasedProductRequestModel.setChannelType("IWEB");
		purchasedProductRequestModel.setClientId("0901605");
		purchasedProductRequestModel.setLanguage("BIG5");
		purchasedProductRequestModel.setProductId("SHK");
		purchasedProductRequestModel.setVersionId("25");
	    PurchasedProductResponseModel result = this.facade.havePurchasedProduct(purchasedProductRequestModel);
	    assertEquals("success", result.getResultForward());
		assertEquals("NORMAL",result.getReturnCode());
		assertFalse(result.isHavePurchased());
	}
	
	@Test
	public void testHavePurchasedProduct2() throws Throwable {
		PurchasedProductRequestModel purchasedProductRequestModel=new PurchasedProductRequestModel();
		purchasedProductRequestModel.setChannelId("IWEB01");
		purchasedProductRequestModel.setChannelType("IWEB");
		purchasedProductRequestModel.setClientId("0100567");
		purchasedProductRequestModel.setLanguage("BIG5");
		purchasedProductRequestModel.setProductId("SHK");
		purchasedProductRequestModel.setVersionId("25");
	    PurchasedProductResponseModel result = this.facade.havePurchasedProduct(purchasedProductRequestModel);
	    assertEquals("success", result.getResultForward());
		assertEquals("NORMAL",result.getReturnCode());
		assertTrue(result.isHavePurchased());
	}


	/**
	 * 针对方法的测试: listSelectService(com.itsz.sht.common.model.common.request.ListSelectServiceRequestModel)
	 * @throws Throwable 测试可能抛出 Throwable
	 * @see FacadeImpl#listSelectService(com.itsz.sht.common.model.common.request.ListSelectServiceRequestModel)
	 * @author Parasoft Jtest 9.0
	 */
	@Test
	public void testListSelectService1() throws Throwable {
		ListSelectServiceRequestModel listSelectServiceRequestModel=new ListSelectServiceRequestModel();
		listSelectServiceRequestModel.setChannelId("IWEB01");
		listSelectServiceRequestModel.setChannelType("IWEB");
		listSelectServiceRequestModel.setClientId("0100567");
		listSelectServiceRequestModel.setLanguage("BIG5");
		listSelectServiceRequestModel.setVersionId("25");
		ListSelectServiceResponseModel result = this.facade.listSelectService(listSelectServiceRequestModel);
		assertEquals("success", result.getResultForward());
		assertEquals("NORMAL",result.getReturnCode());
		assertEquals(1,result.getExistingServiceProductList().size());
	}
	
	@Test
	public void testListSelectService2() throws Throwable {
		ListSelectServiceRequestModel listSelectServiceRequestModel=new ListSelectServiceRequestModel();
		listSelectServiceRequestModel.setChannelId("IWEB01");
		listSelectServiceRequestModel.setChannelType("IWEB");
		listSelectServiceRequestModel.setClientId("0901605");
		listSelectServiceRequestModel.setLanguage("BIG5");
		listSelectServiceRequestModel.setVersionId("25");
		ListSelectServiceResponseModel result = this.facade.listSelectService(listSelectServiceRequestModel);
		assertEquals("success", result.getResultForward());
		assertEquals("NORMAL",result.getReturnCode());
		assertEquals(0,result.getExistingServiceProductList().size());
	}


	/**
	 * 针对方法的测试: login(com.itsz.sht.common.model.request.LoginRequestModel)
	 * @throws Throwable 测试可能抛出 Throwable
	 * @see FacadeImpl#login(com.itsz.sht.common.model.request.LoginRequestModel)
	 * @author Parasoft Jtest 9.0
	 */
	@Test
	public void testLogin1() throws Throwable {
		LoginRequestModel requestModel = new LoginRequestModel();
		requestModel.setLoginId("901605");
		requestModel.setPassword("111111");
	    requestModel.setRtqFlag("N");
	    requestModel.setLoginInfoFormat("FULL");
	    requestModel.setCheckVersion("N");
	    requestModel.setChannelType("IWIN");
	    requestModel.setChannelId("IWIN01");
		LoginResponseModel result = this.facade.login(requestModel);
		assertEquals("FAILED", result.getLoginResponse().getNewStatus());
		assertEquals("INVIDPASS", result.getLoginResponse().getNewSubCode());
		
	}

	/**
	 * 针对方法的测试: login(com.itsz.sht.common.model.request.LoginRequestModel)
	 * @throws Throwable 测试可能抛出 Throwable
	 * @see FacadeImpl#login(com.itsz.sht.common.model.request.LoginRequestModel)
	 * @author Parasoft Jtest 9.0
	 */
	@Test
	public void testLogin2() throws Throwable {
		
		LoginRequestModel requestModel = new LoginRequestModel();
		requestModel.setLoginId("901605");
		requestModel.setPassword("000000");
	    requestModel.setRtqFlag("N");
	    requestModel.setLoginInfoFormat("FULL");
	    requestModel.setCheckVersion("N");
	    requestModel.setChannelType("IWIN");
	    requestModel.setChannelId("IWIN01");
		LoginResponseModel result = this.facade.login(requestModel);
		assertEquals("SUCCESS", result.getLoginResponse().getNewStatus());

	}

	/**
	 * 针对方法的测试: login(com.itsz.sht.common.model.request.LoginRequestModel)
	 * @throws Throwable 测试可能抛出 Throwable
	 * @see FacadeImpl#login(com.itsz.sht.common.model.request.LoginRequestModel)
	 * @author Parasoft Jtest 9.0
	 */
	@Test
	public void testLogin3() throws Throwable {
		
		LoginRequestModel requestModel = new LoginRequestModel();
		requestModel.setLoginId("abcdef");
		requestModel.setPassword("000000");
	    requestModel.setRtqFlag("N");
	    requestModel.setLoginInfoFormat("FULL");
	    requestModel.setCheckVersion("N");
	    requestModel.setChannelType("IWIN");
	    requestModel.setChannelId("IWIN01");
		LoginResponseModel result = this.facade.login(requestModel);
		assertEquals("FAILED", result.getLoginResponse().getNewStatus());
		assertEquals("INVIDLOGID", result.getLoginResponse().getNewSubCode());

	}

	@Test
	public void testModifyOrder1() throws Throwable {
		ModifyOrderRequestModel orderModel = new ModifyOrderRequestModel();
		orderModel.setChannelId("IWIN01");
		orderModel.setChannelType("IWIN");
		orderModel.setLanguage("GB");
		orderModel.setAccountId("02-0155116-33");
		orderModel.setCustCode("0155116");
		orderModel.setInstrCode(null);
		orderModel.setLoginId("alanv111");
		orderModel.setLotSize(null);
		orderModel.setMcsOrderId(Long.parseLong("80098"));
		orderModel.setNewOrderPrice(BigDecimal.valueOf(Double.parseDouble("65.800")));
		orderModel.setNewOrderQty(BigDecimal.valueOf(Double.parseDouble("1000")));
		orderModel.setOrderId(null);
		orderModel.setOrderType(null);
		orderModel.setPassword("555555");
		orderModel.setTransactionProtection("Y");
		orderModel.setTriggerPrice(null);
		ModifyOrderResponseModel result = this.facade.modifyOrder(orderModel);
		assertEquals("failure", result.getResultForward());
		assertEquals("MCS00403", result.getReturnCode());
	}

	/**
	 * 针对方法的测试: modifyOrder(com.itsz.sht.common.model.request.ModifyOrderRequestModel)
	 * @throws Throwable 测试可能抛出 Throwable
	 * @see FacadeImpl#modifyOrder(com.itsz.sht.common.model.request.ModifyOrderRequestModel)
	 * @author Parasoft Jtest 9.0
	 */
	@Test
	public void testModifyOrder2() throws Throwable {
		ModifyOrderRequestModel orderModel = new ModifyOrderRequestModel();
		orderModel.setChannelId("IWIN01");
		orderModel.setChannelType("IWIN");
		orderModel.setLanguage("GB");
		orderModel.setAccountId("02-0901605-30");
		orderModel.setCustCode("0901605");
		orderModel.setInstrCode(null);
		orderModel.setLoginId("0901605");
		orderModel.setLotSize(null);
		orderModel.setMcsOrderId(Long.parseLong("80243"));
		orderModel.setNewOrderPrice(BigDecimal.valueOf(Double.parseDouble("3.590")));
		orderModel.setNewOrderQty(BigDecimal.valueOf(Double.parseDouble("1000")));
		orderModel.setOrderId(Long.parseLong("14184302"));
		orderModel.setOrderType(null);
		orderModel.setPassword("000000");
		orderModel.setTransactionProtection("Y");
		orderModel.setTriggerPrice(null);
		ModifyOrderResponseModel result = this.facade.modifyOrder(orderModel);
		assertEquals("success", result.getResultForward());
		assertEquals("NORMAL", result.getReturnCode());
	}


	/**
	 * 针对方法的测试: orderDetail(com.itsz.sht.common.model.request.OrderDetailRequestModel)
	 * @throws Throwable 测试可能抛出 Throwable
	 * @see FacadeImpl#orderDetail(com.itsz.sht.common.model.request.OrderDetailRequestModel)
	 * @author Parasoft Jtest 9.0
	 */
	@Test
	public void testOrderDetail1() throws Throwable {
		
		OrderDetailRequestModel orderModel = new OrderDetailRequestModel();
		orderModel.setChannelId("IWIN01");
		orderModel.setChannelType("IWIN");
		orderModel.setLanguage("GB");
		orderModel.setAccountId("02-0901605-30");
		orderModel.setHasHisories(null);
		orderModel.setMcsOrderId(Long.parseLong("80243"));
		orderModel.setOrderId(null);
		orderModel.setSupportOverNightFlag("Y");
		orderModel.setTransactionProtection(null);
		OrderDetailTradeResponseModel result = this.facade.orderDetail(orderModel);
		assertEquals("success", result.getResultForward());
		assertEquals("NORMAL",result.getReturnCode());
	}

	/**
	 * 针对方法的测试: placeOrder(com.itsz.sht.common.model.request.PlaceOrderRequestModel)
	 * @throws Throwable 测试可能抛出 Throwable
	 * @see FacadeImpl#placeOrder(com.itsz.sht.common.model.request.PlaceOrderRequestModel)
	 * @author Parasoft Jtest 9.0
	 */
	@Test
	public void testPlaceOrder1() throws Throwable {
		PlaceOrderRequestModel orderModel = new PlaceOrderRequestModel();
		orderModel.setChannelId("IWIN01");
		orderModel.setChannelType("IWIN");
		orderModel.setLanguage("GB");
		orderModel.setAccountId("02-0155116-33");
		orderModel.setAllOrNothingFlag("N");
		orderModel.setConditionType("");
		orderModel.setCustCode("0155116");
		orderModel.setInstrCode("8");
		orderModel.setLoginId("alanv111");
		orderModel.setMarketCode("AMS3");
		orderModel.setOrderPrice(BigDecimal.valueOf(Double.parseDouble("11.560")));
		orderModel.setOrderQuantity(BigDecimal.valueOf(Double.parseDouble("8000")));
		orderModel.setOrderSide("B");
		orderModel.setOrderType("ENHANCED_LIMIT");
		orderModel.setPassword("000000");
		orderModel.setSupportOverNightFlag("N");
		orderModel.setTaskNo("");
		orderModel.setTransactionAmount(null);
		orderModel.setTransactionProtection("Y");
		orderModel.setTriggerPrice(null);
		orderModel.setValidityType("");
		PlaceOrderResponseModel result = this.facade.placeOrder(orderModel);
		assertEquals("success", result.getResultForward());
		assertEquals("NORMAL", result.getReturnCode());
	}
	@Test
	public void testPlaceOrder2() throws Throwable {
		PlaceOrderRequestModel orderModel = new PlaceOrderRequestModel();
		orderModel.setChannelId("IWIN01");
		orderModel.setChannelType("IWIN");
		orderModel.setLanguage("GB");
		orderModel.setAccountId("02-0155116-33");
		orderModel.setAllOrNothingFlag("N");
		orderModel.setConditionType("");
		orderModel.setCustCode("0155116");
		orderModel.setInstrCode("8");
		orderModel.setLoginId("alanv111");
		orderModel.setMarketCode("AMS3");
		orderModel.setOrderPrice(BigDecimal.valueOf(Double.parseDouble("11.560")));
		orderModel.setOrderQuantity(BigDecimal.valueOf(Double.parseDouble("8000")));
		orderModel.setOrderSide("B");
		orderModel.setOrderType("ENHANCED_LIMIT");
		orderModel.setPassword("555555");
		orderModel.setSupportOverNightFlag("N");
		orderModel.setTaskNo("");
		orderModel.setTransactionAmount(null);
		orderModel.setTransactionProtection("Y");
		orderModel.setTriggerPrice(null);
		orderModel.setValidityType("");
		PlaceOrderResponseModel result = this.facade.placeOrder(orderModel);
		assertEquals("failure", result.getResultForward());
		assertEquals("MCS00403", result.getReturnCode());
	}

	/**
	 * 针对方法的测试: ppsEnquiry(com.itsz.sht.common.model.request.PPSEnquiryRequestModel)
	 * @throws Throwable 测试可能抛出 Throwable
	 * @see FacadeImpl#ppsEnquiry(com.itsz.sht.common.model.request.PPSEnquiryRequestModel)
	 * @author Parasoft Jtest 9.0
	 */
	@Test
	public void testPpsEnquiry1() throws Throwable {
		
		PPSEnquiryRequestModel requestModel = new PPSEnquiryRequestModel();
		requestModel.setChannelId("IWIN01");
		requestModel.setChannelType("IWIN");
		requestModel.setLanguage("GB");
		requestModel.setAccountId("0901605");
		requestModel.setLoginId("0901605");
		PPSEnquiryResponseModel result = this.facade.ppsEnquiry(requestModel);
		assertEquals("success", result.getResultForward());
		assertEquals("NORMAL",result.getReturnCode());
	}


	/**
	 * 针对方法的测试: profitAndLossEnqiry(com.itsz.sht.common.model.request.ProfitAndLossEnqiryRequestModel)
	 * @throws Throwable 测试可能抛出 Throwable
	 * @see FacadeImpl#profitAndLossEnqiry(com.itsz.sht.common.model.request.ProfitAndLossEnqiryRequestModel)
	 * @author Parasoft Jtest 9.0
	 */
	@Test
	public void testProfitAndLossEnqiry1() throws Throwable {
		
		ProfitAndLossEnqiryRequestModel requestModel = new ProfitAndLossEnqiryRequestModel();
		requestModel.setChannelId("IWIN01");
		requestModel.setChannelType("IWIN");
		requestModel.setLanguage("GB");
		requestModel.setAccountID("02-0901605-30");
		requestModel.setLoginID("0901605");
		requestModel.setMarketCode("AMS3");
		requestModel.setProfileID("1");
		requestModel.setQuoteType("DELAYED");
		ProfitAndLossEnqiryResponseModel result = this.facade.profitAndLossEnqiry(requestModel);
		assertEquals("success", result.getResultForward());
		assertEquals("NORMAL",result.getReturnCode());
	}


	/**
	 * 针对方法的测试: profitAndLossUpdate(com.itsz.sht.common.model.request.ProfitAndLossUpdateRequestModel)
	 * @throws Throwable 测试可能抛出 Throwable
	 * @see FacadeImpl#profitAndLossUpdate(com.itsz.sht.common.model.request.ProfitAndLossUpdateRequestModel)
	 * @author Parasoft Jtest 9.0
	 */
	@Test
	public void testProfitAndLossUpdate1() throws Throwable {
		
		ProfitAndLossUpdateRequestModel requestModel = new ProfitAndLossUpdateRequestModel();
		requestModel.setChannelId("IWIN01");
		requestModel.setChannelType("IWIN");
		requestModel.setLanguage("GB");
		requestModel.setLoginID("0901605");
		requestModel.setMarketCode("AMS3");
		requestModel.setProfileID("1");
		requestModel.setProfileInfo("368,50,410,33");
		ProfitAndLossUpdateResponseModel result = this.facade.profitAndLossUpdate(requestModel);
		assertEquals("success", result.getResultForward());
		assertEquals("NORMAL",result.getReturnCode());
	}

	/**
	 * 针对方法的测试: purchaseService(com.itsz.sht.common.model.common.request.PurchaseServiceRequestModel)
	 * @throws Throwable 测试可能抛出 Throwable
	 * @see FacadeImpl#purchaseService(com.itsz.sht.common.model.common.request.PurchaseServiceRequestModel)
	 * @author Parasoft Jtest 9.0
	 */
	@Test
	public void testPurchaseService1() throws Throwable {
		PurchaseServiceRequestModel purchaseServiceRequestModel=new PurchaseServiceRequestModel();
		purchaseServiceRequestModel.setChannelId("IWEB01");
		purchaseServiceRequestModel.setChannelType("IWEB");
		purchaseServiceRequestModel.setAllowRenewal("Y");
		purchaseServiceRequestModel.setClientId("0901605");
		purchaseServiceRequestModel.setDefDebtAcc("02-0901605-30");
		purchaseServiceRequestModel.setLanguage("BIG5");
		purchaseServiceRequestModel.setProductId("SHK");
		purchaseServiceRequestModel.setVersionId("25");
		PurchaseServiceResponseModel result = this.facade.purchaseService(purchaseServiceRequestModel);
		assertEquals("success", result.getResultForward());
		assertEquals("WEB060018",result.getReturnCode());
	
	}
	
	@Test
	public void testPurchaseService2() throws Throwable {
		PurchaseServiceRequestModel purchaseServiceRequestModel=new PurchaseServiceRequestModel();
		purchaseServiceRequestModel.setChannelId("IWEB01");
		purchaseServiceRequestModel.setChannelType("IWEB");
		purchaseServiceRequestModel.setAllowRenewal("Y");
		purchaseServiceRequestModel.setClientId("0042038");
		purchaseServiceRequestModel.setDefDebtAcc("02-0042038-30");
		purchaseServiceRequestModel.setLanguage("BIG5");
		purchaseServiceRequestModel.setProductId("SSTR_AAST");
		purchaseServiceRequestModel.setVersionId("25");
		PurchaseServiceResponseModel result = this.facade.purchaseService(purchaseServiceRequestModel);
		assertEquals("success", result.getResultForward());
		assertEquals("WEB060027",result.getReturnCode());
	
	}


	/**
	 * 针对方法的测试: reserveService(com.itsz.sht.common.model.common.request.ReserveServiceRequestModel)
	 * @throws Throwable 测试可能抛出 Throwable
	 * @see FacadeImpl#reserveService(com.itsz.sht.common.model.common.request.ReserveServiceRequestModel)
	 * @author Parasoft Jtest 9.0
	 */
	@Test
	public void testReserveService1() throws Throwable {
		
		ReserveServiceRequestModel reserveServiceRequestModel = new ReserveServiceRequestModel();
		reserveServiceRequestModel.setChannelId("IWEB01");
		reserveServiceRequestModel.setChannelType("IWEB");
		reserveServiceRequestModel.setAllowRenewal("Y");
		reserveServiceRequestModel.setClientId("0042038");
		reserveServiceRequestModel.setDefDebtAcc("02-0042038-30");
		reserveServiceRequestModel.setLanguage("BIG5");
		reserveServiceRequestModel.setProductId("SSTR_AAST");
		reserveServiceRequestModel.setVersionId("25");
		ReserveServiceResponseModel result = this.facade.reserveService(reserveServiceRequestModel);
		assertEquals("success", result.getResultForward());
		assertEquals("NORMAL",result.getReturnCode());
	
	}

	/**
	 * 针对方法的测试: transFundDeposit(com.itsz.sht.common.model.request.FundDepositRequestModel)
	 * @throws Throwable 测试可能抛出 Throwable
	 * @see FacadeImpl#transFundDeposit(com.itsz.sht.common.model.request.FundDepositRequestModel)
	 * @author Parasoft Jtest 9.0
	 */
	@Test
	public void testTransFundDeposit1() throws Throwable {

		FundDepositRequestModel fundDepositRequestModel = new FundDepositRequestModel();
		fundDepositRequestModel.setChannelId(Consts.Channel.Id.NWEB);
		fundDepositRequestModel.setChannelType(Consts.Channel.NWEB);
	    FundDepositResponseModel result = this.facade.transFundDeposit(fundDepositRequestModel);
	    assertEquals("success", result.getResultForward());
		assertEquals("NORMAL",result.getReturnCode());
	
	}


	/**
	 * 针对方法的测试: updateUserNotificationEmail(com.itsz.sht.common.model.common.request.UserNotificationEmailRequestModel)
	 * @throws Throwable 测试可能抛出 Throwable
	 * @see FacadeImpl#updateUserNotificationEmail(com.itsz.sht.common.model.common.request.UserNotificationEmailRequestModel)
	 * @author Parasoft Jtest 9.0
	 */
	@Test
	public void testUpdateUserNotificationEmail1() throws Throwable {
		UserNotificationEmailRequestModel userNotificationEmailRequestModel=new UserNotificationEmailRequestModel();
		userNotificationEmailRequestModel.setChannelId("IWEB01");
		userNotificationEmailRequestModel.setChannelType("IWEB");
		userNotificationEmailRequestModel.setClientId("0901605");
		userNotificationEmailRequestModel.setLanguage("BIG5");
		userNotificationEmailRequestModel.setNotiFlag(true);
		userNotificationEmailRequestModel.setUserNotificationEmail("testingteam@itsz.cn");
		UserNotificationEmailResponseModel result = this.facade.updateUserNotificationEmail(userNotificationEmailRequestModel);
		assertEquals("success", result.getResultForward());
		assertEquals("NORMAL",result.getReturnCode());
	}


	/**
	 * 针对方法的测试: updateUserProductStatus(com.itsz.sht.common.model.common.request.UpdateUserProductRequestModel)
	 * @throws Throwable 测试可能抛出 Throwable
	 * @see FacadeImpl#updateUserProductStatus(com.itsz.sht.common.model.common.request.UpdateUserProductRequestModel)
	 * @author Parasoft Jtest 9.0
	 */
	@Test
	public void testUpdateUserProductStatus1() throws Throwable {
		UpdateUserProductRequestModel updateUserProductRequestModel=new UpdateUserProductRequestModel();
		updateUserProductRequestModel.setChannelId("IWEB01");
		updateUserProductRequestModel.setChannelType("IWEB");
		updateUserProductRequestModel.setLanguage("BIG5");
		List<UserProductRequest> list=new ArrayList<UserProductRequest>();
		UserProductRequest upr=new UserProductRequest();
		upr.setAllwRenl("N");
		UsrProdId upi=new UsrProdId();
		upi.setClntId("0042038");
		upi.setProdId("SSTR_IQS");
		upr.setId(upi);
		list.add(upr);
		updateUserProductRequestModel.setUserProductRequestList(list);
		UserProductResponseModel result = this.facade.updateUserProductStatus(updateUserProductRequestModel);
		assertEquals("success", result.getResultForward());
		assertEquals("NORMAL",result.getReturnCode());
	}


	/**
	 * 针对方法的测试: verifyPassword(com.itsz.sht.common.model.request.VerifyPasswordRequestModel)
	 * @throws Throwable 测试可能抛出 Throwable
	 * @see FacadeImpl#verifyPassword(com.itsz.sht.common.model.request.VerifyPasswordRequestModel)
	 * @author Parasoft Jtest 9.0
	 */
	@Test
	public void testVerifyPassword1() throws Throwable {
		VerifyPasswordRequestModel requestModel = new VerifyPasswordRequestModel();
		requestModel.setChannelId("IWIN01");
		requestModel.setChannelType("IWIN");
		requestModel.setLanguage("GB");
		requestModel.setLoginId("alanv111");
		requestModel.setPassword("555555");
		VerifyPasswordResponseModel result = this.facade.verifyPassword(requestModel);
		assertEquals("success", result.getResultForward());
		assertEquals("INVIDPASS",result.getReturnCode());
	}
	
	/**
	 * 针对方法的测试: verifyPassword(com.itsz.sht.common.model.request.VerifyPasswordRequestModel)
	 * @throws Throwable 测试可能抛出 Throwable
	 * @see FacadeImpl#verifyPassword(com.itsz.sht.common.model.request.VerifyPasswordRequestModel)
	 * @author Parasoft Jtest 9.0
	 */
	@Test
	public void testVerifyPassword2() throws Throwable {
		VerifyPasswordRequestModel requestModel = new VerifyPasswordRequestModel();
		requestModel.setChannelId("IWIN01");
		requestModel.setChannelType("IWIN");
		requestModel.setLanguage("GB");
		requestModel.setLoginId("alanv111");
		requestModel.setPassword("000000");
		VerifyPasswordResponseModel result = this.facade.verifyPassword(requestModel);
		assertEquals("success", result.getResultForward());
		assertEquals("NORMAL",result.getReturnCode());
	}


	/**
	 * 用于建立测试. 这个方法是 JUnit 在每个测试执行前
	 * 所调用的.
	 * @see junit.framework.TestCase#setUp()
	 * @author Parasoft Jtest 9.0
	 */
	@Before
	public void setUp() throws Exception {
		super.setUp();
		/*
		 * 在这里添加必要的初始化代码 (例, open a socket).
		 * 调用 Repository.putTemporary() 来提供测试时所需要的
		 * 对象初始化实例.
		 */
		// jtest.Repository.putTemporary("name", object);
		if (appContext == null)
			appContext = new ClassPathXmlApplicationContext(new String[] {"beans.xml"});
		this.facade=(Facade)appContext.getBean("facade");
		pp = new ParaParser();
		pp.initParaTable();
	}
	
	private ApplicationContext appContext;
	private Facade facade;
	private ParaParser pp;

	/**
	 * 用于在测试后清理. 这个方法是 JUnit 在每个测试完成后
	 * 所调用的.
	 * @see junit.framework.TestCase#tearDown()
	 * @author Parasoft Jtest 9.0
	 */
	@After
	public void tearDown() throws Exception {
		try {
			/*
			 * 在这里添加必要的清理代码 (例, close a socket).
			 */
			
		} finally {
			super.tearDown();
			appContext=null;
			facade=null;
			pp=null;
		}
	}

	/**
	 * 实用程序的主要方法.  运行在测试类中所定义的测试用例.
	 * 
	 * 用法: java FacadeImplTest
	 * @param args 不需要命令行参数
	 * @author Parasoft Jtest 9.0
	 */
	public static void main(String[] args){
		// junit.textui.TestRunner 将打印测试结果到 stdout.

		org.junit.runner.JUnitCore.main("com.itsz.sht.service.FacadeImplTest");
	}

	/**
	 * 用来获取要测试的类对象.
	 * @return 将被测试的类
	 * @author Parasoft Jtest 9.0
	 */
	public Class getTestedClass() {
		return FacadeImpl.class;
	}

}
// JTEST_CURRENT_ID=-1919066464.