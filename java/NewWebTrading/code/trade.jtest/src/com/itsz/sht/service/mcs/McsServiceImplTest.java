/*
 * McsServiceImplTest.java
 * Created by Jtest on 11-3-28 17:43:32.
 */

package com.itsz.sht.service.mcs;

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
import com.itsz.sht.common.model.response.placeorder.PrePlaceOrderResponseModel;
import com.itsz.sht.service.mcs.McsServiceImpl;
import com.itsz.sht.service.mcs.McsUtil;
import com.taifook.framework.foundation.configuration.ConfRepository;
import com.taifook.mcs.core.beans.msg.ChangePasswordRequest;
import com.taifook.mcs.core.beans.msg.Exception01;
import com.taifook.mcs.core.beans.msg.MCSMessage;
import com.taifook.mcs.core.beans.msg.MISAccountDetailResponse;
import com.taifook.mcs.core.beans.msg.MISAccountEnquiryResponse;
import com.taifook.mcs.core.beans.msg.MISAccountListResponse;
import com.taifook.mcs.core.beans.msg.MISAccountSummaryResponse;
import com.taifook.mcs.core.beans.msg.MTSSShareHoldingResponse;
import com.taifook.mcs.core.beans.msg.ModifyOrderRequest;
import com.taifook.mcs.core.beans.msg.OrderInfo;
import com.taifook.mcs.core.beans.msg.OrderListRequest;
import com.taifook.mcs.core.beans.msg.OrderListResponse;
import com.taifook.mcs.core.beans.msg.TradeHistoryResponse;
import com.taifook.mcs.core.beans.msg.TradeListRequest;
import com.taifook.mcs.core.beans.msg.TradeListResponse;
import com.taifook.mcs.core.beans.msg.TransactionProtectionResponse;
import com.taifook.mcs.msg.MessageSender;
import java.lang.reflect.Member;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import jtest.Stubs;
import org.apache.commons.beanutils.PropertyUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * McsServiceImplTest 是这个 McsServiceImpl 类的单元测试类
 * @see com.itsz.sht.service.mcs.McsServiceImpl
 * @author Parasoft Jtest 9.0
 */
public class McsServiceImplTest extends PackageTestCase {
	/**
	 * 构造一个由名称参数指定测试用例的测试.
	 * @param name 测试用例的名字
	 * @author Parasoft Jtest 9.0
	 */
	public McsServiceImplTest() {
		/*
		 * 此构造函数不应该被修改. 任何初始化代码
		 * 应该放在 setUp() 方法.
		 */
	}

	/**
	 * 针对方法的测试: bocTransfer(com.itsz.sht.common.model.request.BOCTransferRequestModel)
	 * @throws Throwable 测试可能抛出 Throwable
	 * @see McsServiceImpl#bocTransfer(com.itsz.sht.common.model.request.BOCTransferRequestModel)
	 * @author Parasoft Jtest 9.0
	 */
	@Test
	public void testBocTransfer6() throws Throwable {
		
		BOCTransferRequestModel requestModel = new BOCTransferRequestModel();
		BOCTransferResponseModel result = this.mcsService
				.bocTransfer(requestModel);
		// com.itsz.sht.exception.ITSZException thrown
		// at com.itsz.sht.service.mcs.McsServiceImpl.bocTransfer(McsServiceImpl.java:1331)
		// jtest_unverified
	}

	/**
	 * 针对方法的测试: callBulkCancelOrder(com.itsz.sht.common.model.request.BulkCancelOrderRequestModel)
	 * @throws Throwable 测试可能抛出 Throwable
	 * @see McsServiceImpl#callBulkCancelOrder(com.itsz.sht.common.model.request.BulkCancelOrderRequestModel)
	 * @author Parasoft Jtest 9.0
	 */
	@Test
	public void testCallBulkCancelOrder1() throws Throwable {
		
		BulkCancelOrderRequestModel reqModel = new BulkCancelOrderRequestModel();
		Collection mcsOrderID = new ArrayList();
		reqModel.setLoginId("loginId11");
		reqModel.setAccountId("accountId11");
		reqModel.setListSize(-10);
		reqModel.setPassword("password4");
		reqModel.setTransactionProtection("transactionProtection11");
		reqModel.setMcsOrderID(mcsOrderID);
		mcsOrderID.add("0");
		mcsOrderID.add(null);
		BulkCancelOrderResponseModel result = this.mcsService
				.callBulkCancelOrder(reqModel);
		// NumberFormatException thrown
		// at java.lang.Long.parseLong(Long.java:375)
		// at java.lang.Long.<init>(Long.java:678)
		// at com.itsz.sht.service.mcs.McsServiceImpl.getOrderIdColl(McsServiceImpl.java:930)
		// at com.itsz.sht.service.mcs.McsServiceImpl.assembleBulkCancelOrderInfo(McsServiceImpl.java:882)
		// at com.itsz.sht.service.mcs.McsServiceImpl.callBulkCancelOrder(McsServiceImpl.java:833)
		// jtest_unverified
	}

	/**
	 * 针对方法的测试: callBulkCancelOrder(com.itsz.sht.common.model.request.BulkCancelOrderRequestModel)
	 * @throws Throwable 测试可能抛出 Throwable
	 * @see McsServiceImpl#callBulkCancelOrder(com.itsz.sht.common.model.request.BulkCancelOrderRequestModel)
	 * @author Parasoft Jtest 9.0
	 */
	@Test
	public void testCallBulkCancelOrder4() throws Throwable {
		
		BulkCancelOrderRequestModel reqModel = new BulkCancelOrderRequestModel();
		Collection mcsOrderID = new ArrayList();
		
		reqModel.setLoginId("loginId8");
		reqModel.setAccountId("accountId8");
		reqModel.setListSize(256);
		reqModel.setPassword("-00");
		reqModel.setTransactionProtection("transactionProtection8");
		reqModel.setMcsOrderID(mcsOrderID);
		BulkCancelOrderResponseModel result = this.mcsService
				.callBulkCancelOrder(reqModel);
		// com.itsz.sht.exception.ITSZException thrown
		// at com.itsz.sht.service.mcs.McsServiceImpl.getBulkCancelOrder(McsServiceImpl.java:811)
		// at com.itsz.sht.service.mcs.McsServiceImpl.callBulkCancelOrder(McsServiceImpl.java:834)
		// jtest_unverified
	}

	/**
	 * 针对方法的测试: callBulkCancelOrder(com.itsz.sht.common.model.request.BulkCancelOrderRequestModel)
	 * @throws Throwable 测试可能抛出 Throwable
	 * @see McsServiceImpl#callBulkCancelOrder(com.itsz.sht.common.model.request.BulkCancelOrderRequestModel)
	 * @author Parasoft Jtest 9.0
	 */
	@Test
	public void testCallBulkCancelOrder7() throws Throwable {
		
		BulkCancelOrderRequestModel reqModel = new BulkCancelOrderRequestModel();
		Collection mcsOrderID = new ArrayList();
		reqModel.setLoginId("loginId5");
		reqModel.setAccountId("accountId5");
		reqModel.setListSize(2147483647);
		reqModel.setPassword("-00");
		reqModel.setTransactionProtection("transactionProtection5");
		reqModel.setMcsOrderID(mcsOrderID);
		mcsOrderID.add("0");
		BulkCancelOrderResponseModel result = this.mcsService
				.callBulkCancelOrder(reqModel);
		// com.itsz.sht.exception.ITSZException thrown
		// at com.itsz.sht.service.mcs.McsServiceImpl.getBulkCancelOrder(McsServiceImpl.java:811)
		// at com.itsz.sht.service.mcs.McsServiceImpl.callBulkCancelOrder(McsServiceImpl.java:834)
		// jtest_unverified
	}

	/**
	 * 针对方法的测试: callBulkCancelOrder(com.itsz.sht.common.model.request.BulkCancelOrderRequestModel)
	 * @throws Throwable 测试可能抛出 Throwable
	 * @see McsServiceImpl#callBulkCancelOrder(com.itsz.sht.common.model.request.BulkCancelOrderRequestModel)
	 * @author Parasoft Jtest 9.0
	 */
	@Test
	public void testCallBulkCancelOrder9() throws Throwable {
		
		BulkCancelOrderRequestModel reqModel = new BulkCancelOrderRequestModel();
		Collection mcsOrderID = new ArrayList();
		reqModel.setLoginId("loginId3");
		reqModel.setAccountId("accountId3");
		reqModel.setListSize(1000);
		reqModel.setPassword("password1");
		reqModel.setTransactionProtection("transactionProtection3");
		reqModel.setMcsOrderID(mcsOrderID);
		mcsOrderID.add("#$%");
		mcsOrderID.add(null);
		mcsOrderID.add(null);
		mcsOrderID.add(null);
		BulkCancelOrderResponseModel result = this.mcsService
				.callBulkCancelOrder(reqModel);
		// NumberFormatException thrown
		// at java.lang.NumberFormatException.forInputString(NumberFormatException.java:48)
		// at java.lang.Long.parseLong(Long.java:410)
		// at java.lang.Long.<init>(Long.java:678)
		// at com.itsz.sht.service.mcs.McsServiceImpl.getOrderIdColl(McsServiceImpl.java:930)
		// at com.itsz.sht.service.mcs.McsServiceImpl.assembleBulkCancelOrderInfo(McsServiceImpl.java:882)
		// at com.itsz.sht.service.mcs.McsServiceImpl.callBulkCancelOrder(McsServiceImpl.java:833)
		// jtest_unverified
	}

	/**
	 * 针对方法的测试: callBulkCancelOrder(com.itsz.sht.common.model.request.BulkCancelOrderRequestModel)
	 * @throws Throwable 测试可能抛出 Throwable
	 * @see McsServiceImpl#callBulkCancelOrder(com.itsz.sht.common.model.request.BulkCancelOrderRequestModel)
	 * @author Parasoft Jtest 9.0
	 */
	@Test
	public void testCallBulkCancelOrder11() throws Throwable {
		
		BulkCancelOrderRequestModel reqModel = new BulkCancelOrderRequestModel();
		Collection mcsOrderID = new ArrayList();
		Object e = new Object();
		reqModel.setLoginId("loginId1");
		reqModel.setAccountId("accountId1");
		reqModel.setListSize(-10);
		reqModel.setPassword("password0");
		reqModel.setTransactionProtection("transactionProtection1");
		reqModel.setMcsOrderID(mcsOrderID);
		mcsOrderID.add(e);
		BulkCancelOrderResponseModel result = this.mcsService
				.callBulkCancelOrder(reqModel);
		// ClassCastException: java.lang.Object cannot be cast to java.lang.String thrown
		// at com.itsz.sht.service.mcs.McsServiceImpl.getOrderIdColl(McsServiceImpl.java:929)
		// at com.itsz.sht.service.mcs.McsServiceImpl.assembleBulkCancelOrderInfo(McsServiceImpl.java:882)
		// at com.itsz.sht.service.mcs.McsServiceImpl.callBulkCancelOrder(McsServiceImpl.java:833)
		// jtest_unverified
	}

	/**
	 * 针对方法的测试: callBulkCancelOrder(com.itsz.sht.common.model.request.BulkCancelOrderRequestModel)
	 * @throws Throwable 测试可能抛出 Throwable
	 * @see McsServiceImpl#callBulkCancelOrder(com.itsz.sht.common.model.request.BulkCancelOrderRequestModel)
	 * @author Parasoft Jtest 9.0
	 */
	@Test
	public void testCallBulkCancelOrder13() throws Throwable {
		
		
		BulkCancelOrderResponseModel result = this.mcsService
				.callBulkCancelOrder((BulkCancelOrderRequestModel) null);
		// NullPointerException thrown, originator is arg 1 to <Method com.itsz.sht.service.mcs.McsServiceImpl.callBulkCancelOrder(Lcom/itsz/sht/common/model/request/BulkCancelOrderRequestModel;)Lcom/itsz/sht/common/model/response/BulkCancelOrderResponseModel;>
		// at com.itsz.sht.service.mcs.McsServiceImpl.callBulkCancelOrder(McsServiceImpl.java:834)
		// jtest_unverified
	}

	/**
	 * 针对方法的测试: callBulkCancelOrder(com.itsz.sht.common.model.request.BulkCancelOrderRequestModel)
	 * @throws Throwable 测试可能抛出 Throwable
	 * @see McsServiceImpl#callBulkCancelOrder(com.itsz.sht.common.model.request.BulkCancelOrderRequestModel)
	 * @author Parasoft Jtest 9.0
	 */
	@Test
	public void testCallBulkCancelOrder14() throws Throwable {
		
		BulkCancelOrderRequestModel reqModel = new BulkCancelOrderRequestModel();
		BulkCancelOrderResponseModel result = this.mcsService
				.callBulkCancelOrder(reqModel);
		// com.itsz.sht.exception.ITSZException thrown
		// at com.itsz.sht.service.mcs.McsServiceImpl.getBulkCancelOrder(McsServiceImpl.java:811)
		// at com.itsz.sht.service.mcs.McsServiceImpl.callBulkCancelOrder(McsServiceImpl.java:834)
		// jtest_unverified
	}

	/**
	 * 针对方法的测试: callCancelOrder(com.itsz.sht.common.model.request.CancelOrderRequestModel)
	 * @throws Throwable 测试可能抛出 Throwable
	 * @see McsServiceImpl#callCancelOrder(com.itsz.sht.common.model.request.CancelOrderRequestModel)
	 * @author Parasoft Jtest 9.0
	 */
	@Test
	public void testCallCancelOrder1() throws Throwable {
		
		CancelOrderRequestModel reqModel = new CancelOrderRequestModel();
		reqModel.setLoginId("loginId11");
		reqModel.setPassword("password2");
		reqModel.setMCSOrderID(100L);
		reqModel.setMTSSOrderID(1000L);
		reqModel.setCustCode("custCode11");
		reqModel.setAccountId("accountId11");
		reqModel.setListSize(-10);
		reqModel.setTransactionProtection("transactionProtection11");
		CancelOrderResponseModel result = this.mcsService
				.callCancelOrder(reqModel);
		// com.itsz.sht.exception.ITSZException thrown
		// at com.itsz.sht.service.mcs.McsServiceImpl.getCancelOrder(McsServiceImpl.java:826)
		// at com.itsz.sht.service.mcs.McsServiceImpl.callCancelOrder(McsServiceImpl.java:851)
		// jtest_unverified
	}

	/**
	 * 针对方法的测试: callCancelOrder(com.itsz.sht.common.model.request.CancelOrderRequestModel)
	 * @throws Throwable 测试可能抛出 Throwable
	 * @see McsServiceImpl#callCancelOrder(com.itsz.sht.common.model.request.CancelOrderRequestModel)
	 * @author Parasoft Jtest 9.0
	 */
	@Test
	public void testCallCancelOrder2() throws Throwable {
		
		CancelOrderRequestModel reqModel = new CancelOrderRequestModel();
		
		reqModel.setLoginId("loginId9");
		reqModel.setPassword("");
		reqModel.setMCSOrderID(256L);
		reqModel.setMTSSOrderID(920L);
		reqModel.setCustCode("custCode9");
		reqModel.setAccountId("accountId9");
		reqModel.setListSize(920);
		reqModel.setTransactionProtection("transactionProtection9");
		CancelOrderResponseModel result = this.mcsService
				.callCancelOrder(reqModel);
		// com.itsz.sht.exception.ITSZException thrown
		// at com.itsz.sht.service.mcs.McsServiceImpl.getCancelOrder(McsServiceImpl.java:826)
		// at com.itsz.sht.service.mcs.McsServiceImpl.callCancelOrder(McsServiceImpl.java:851)
		// jtest_unverified
	}

	/**
	 * 针对方法的测试: callCancelOrder(com.itsz.sht.common.model.request.CancelOrderRequestModel)
	 * @throws Throwable 测试可能抛出 Throwable
	 * @see McsServiceImpl#callCancelOrder(com.itsz.sht.common.model.request.CancelOrderRequestModel)
	 * @author Parasoft Jtest 9.0
	 */
	@Test
	public void testCallCancelOrder12() throws Throwable {
		
		
		CancelOrderResponseModel result = this.mcsService
				.callCancelOrder((CancelOrderRequestModel) null);
		// NullPointerException thrown, originator is arg 1 to <Method com.itsz.sht.service.mcs.McsServiceImpl.callCancelOrder(Lcom/itsz/sht/common/model/request/CancelOrderRequestModel;)Lcom/itsz/sht/common/model/response/CancelOrderResponseModel;>
		// at com.itsz.sht.service.mcs.McsServiceImpl.callCancelOrder(McsServiceImpl.java:851)
		// jtest_unverified
	}

	/**
	 * 针对方法的测试: callCashDetail(com.itsz.sht.common.model.request.CashDetailRequestModel)
	 * @throws Throwable 测试可能抛出 Throwable
	 * @see McsServiceImpl#callCashDetail(com.itsz.sht.common.model.request.CashDetailRequestModel)
	 * @author Parasoft Jtest 9.0
	 */
	@Test
	public void testCallCashDetail3() throws Throwable {
		
		CashDetailRequestModel cashDetailRequestModel = new CashDetailRequestModel();
		cashDetailRequestModel.setMarketCode("marketCode6");
		cashDetailRequestModel.setAccountId("accountId6");
		CashDetailResponseModel result = this.mcsService
				.callCashDetail(cashDetailRequestModel);
		// com.itsz.sht.exception.ITSZException thrown
		// at com.itsz.sht.service.mcs.McsServiceImpl.callCashDetail(McsServiceImpl.java:1283)
		// jtest_unverified
	}

	/**
	 * 指定当运行 testCallCashDetail3 要使用的桩.
	 * @param method 被调用的方法或构造方法
	 * @param _this 对应实例对象的方法或
	 *        <code>null</code> 静态方法
	 * @param args 传递到方法的参数
	 * @return 要使用的桩返回值或 <code>Stubs.NO_STUB_GENERATED</code>
	 *        指定不要打桩的方法调用.
	 * @throws Throwable Stubs may throw any Throwable
	 * @author Parasoft Jtest 9.0
	 */
	public Object stubsCallCashDetail3(Member method, Object _this,
			Object[] args) throws Throwable {
		Class[] argument_types;
		if (Stubs.matches(method, PropertyUtils.class)) {
			argument_types = new Class[] { Object.class, Object.class };
			if (Stubs.matches(method, "copyProperties", argument_types)) {
				return Stubs.VOID;
			}
		}
		return Stubs.NO_STUB_GENERATED;
	}

	/**
	 * 针对方法的测试: callChangePassword(com.itsz.sht.common.model.request.ChangePwdRequestModel)
	 * @throws Throwable 测试可能抛出 Throwable
	 * @see McsServiceImpl#callChangePassword(com.itsz.sht.common.model.request.ChangePwdRequestModel)
	 * @author Parasoft Jtest 9.0
	 */
	@Test
	public void testCallChangePassword1() throws Throwable {
		
		
		ChangePwdResponseModel result = this.mcsService
				.callChangePassword((ChangePwdRequestModel) null);
		// NullPointerException thrown, originator is arg 1 to <Method com.itsz.sht.service.mcs.McsServiceImpl.callChangePassword(Lcom/itsz/sht/common/model/request/ChangePwdRequestModel;)Lcom/itsz/sht/common/model/response/ChangePwdResponseModel;>
		// at com.itsz.sht.common.model.common.ModelUtil.assembleChangePassRequest(ModelUtil.java:1141)
		// at com.itsz.sht.service.mcs.McsServiceImpl.callChangePassword(McsServiceImpl.java:1093)
		// jtest_unverified
	}

	/**
	 * 针对方法的测试: callChangePassword(com.itsz.sht.common.model.request.ChangePwdRequestModel)
	 * @throws Throwable 测试可能抛出 Throwable
	 * @see McsServiceImpl#callChangePassword(com.itsz.sht.common.model.request.ChangePwdRequestModel)
	 * @author Parasoft Jtest 9.0
	 */
	@Test
	public void testCallChangePassword3() throws Throwable {
		
		MessageSender messageSender = new MessageSender();
		ChangePwdRequestModel requestModel = new ChangePwdRequestModel();
		_fChangePasswordRequest = new ChangePasswordRequest();
		
		ChangePwdResponseModel result = this.mcsService
				.callChangePassword(requestModel);
		// com.itsz.sht.exception.ITSZException thrown
		// at com.itsz.sht.service.mcs.McsServiceImpl.callChangePassword(McsServiceImpl.java:1100)
		// jtest_unverified
	}

	/**
	 * 指定当运行 testCallChangePassword3 要使用的桩.
	 * @param method 被调用的方法或构造方法
	 * @param _this 对应实例对象的方法或
	 *        <code>null</code> 静态方法
	 * @param args 传递到方法的参数
	 * @return 要使用的桩返回值或 <code>Stubs.NO_STUB_GENERATED</code>
	 *        指定不要打桩的方法调用.
	 * @throws Throwable Stubs may throw any Throwable
	 * @author Parasoft Jtest 9.0
	 */
	public Object stubsCallChangePassword3(Member method, Object _this,
			Object[] args) throws Throwable {
		Class[] argument_types;
		if (Stubs.matches(method, ConfRepository.class)) {
			argument_types = new Class[] { String.class };
			if (Stubs.matches(method, argument_types)) {
				return (ConfRepository) Stubs
						.makeStubObject(ConfRepository.class);
			}
			argument_types = new Class[] {};
			if (Stubs.matches(method, "getConfiguration", argument_types)) {
				return null;
			}
		}
		if ("com.taifook.mcs.msg.MessageSender".equals(method
				.getDeclaringClass().getName())) {
			argument_types = new Class[] {};
			if (Stubs.matches(method, "loadConf", argument_types)) {
				return Stubs.VOID;
			}
		}
		if (Stubs.matches(method, ModelUtil.class)) {
			argument_types = new Class[] { ChangePwdRequestModel.class };
			if (Stubs.matches(method, "assembleChangePassRequest",
					argument_types)) {
				return _fChangePasswordRequest;
			}
		}
		return Stubs.NO_STUB_GENERATED;
	}

	/**
	 * 针对方法的测试: callChangeTAndC(com.itsz.sht.common.model.request.ChangeTAndCRequestModel)
	 * @throws Throwable 测试可能抛出 Throwable
	 * @see McsServiceImpl#callChangeTAndC(com.itsz.sht.common.model.request.ChangeTAndCRequestModel)
	 * @author Parasoft Jtest 9.0
	 */
	@Test
	public void testCallChangeTAndC1() throws Throwable {
		
		ChangeTAndCRequestModel requestModel = new ChangeTAndCRequestModel();
		requestModel.setLoginId("loginId3");
		requestModel.setNewTermsAndConditions("newTermsAndConditions3");
		ChangeTAndCResponseModel result = this.mcsService
				.callChangeTAndC(requestModel);
		// com.itsz.sht.exception.ITSZException thrown
		// at com.itsz.sht.service.mcs.McsServiceImpl.callChangeTAndC(McsServiceImpl.java:1153)
		// jtest_unverified
	}

	/**
	 * 针对方法的测试: callChangeTAndC(com.itsz.sht.common.model.request.ChangeTAndCRequestModel)
	 * @throws Throwable 测试可能抛出 Throwable
	 * @see McsServiceImpl#callChangeTAndC(com.itsz.sht.common.model.request.ChangeTAndCRequestModel)
	 * @author Parasoft Jtest 9.0
	 */
	@Test
	public void testCallChangeTAndC2() throws Throwable {
		
		
		ChangeTAndCResponseModel result = this.mcsService
				.callChangeTAndC((ChangeTAndCRequestModel) null);
		// NullPointerException thrown, originator is arg 1 to <Method com.itsz.sht.service.mcs.McsServiceImpl.callChangeTAndC(Lcom/itsz/sht/common/model/request/ChangeTAndCRequestModel;)Lcom/itsz/sht/common/model/response/ChangeTAndCResponseModel;>
		// at com.itsz.sht.common.model.common.ModelUtil.assembleChangeTAndCRequest(ModelUtil.java:1155)
		// at com.itsz.sht.service.mcs.McsServiceImpl.callChangeTAndC(McsServiceImpl.java:1146)
		// jtest_unverified
	}

	/**
	 * 针对方法的测试: callEnquireAccount(com.itsz.sht.common.model.request.EnquireAccountRequestModel)
	 * @throws Throwable 测试可能抛出 Throwable
	 * @see McsServiceImpl#callEnquireAccount(com.itsz.sht.common.model.request.EnquireAccountRequestModel)
	 * @author Parasoft Jtest 9.0
	 */
	@Test
	public void testCallEnquireAccount1() throws Throwable {
		
		EnquireAccountRequestModel accountModel = new EnquireAccountRequestModel();
		accountModel.setAccountId("accountId5");
		accountModel.setAccountType("accountType5");
		accountModel.setOnline("online5");
		MISAccountSummaryResponse result = this.mcsService
				.callEnquireAccount(accountModel);
		// com.itsz.sht.exception.ITSZException thrown
		// at com.itsz.sht.service.mcs.McsServiceImpl.callEnquireAccount(McsServiceImpl.java:958)
		// jtest_unverified
	}

	/**
	 * 针对方法的测试: callEnquireAccountDetail(com.itsz.sht.common.model.request.EnquireAccountRequestModel)
	 * @throws Throwable 测试可能抛出 Throwable
	 * @see McsServiceImpl#callEnquireAccountDetail(com.itsz.sht.common.model.request.EnquireAccountRequestModel)
	 * @author Parasoft Jtest 9.0
	 */
	@Test
	public void testCallEnquireAccountDetail1() throws Throwable {
		
		EnquireAccountRequestModel accountModel = new EnquireAccountRequestModel();
		
		accountModel.setAccountId("accountId2");
		accountModel.setAccountType("accountType2");
		accountModel.setOnline("online2");
		MISAccountDetailResponse result = this.mcsService
				.callEnquireAccountDetail(accountModel);
		// com.itsz.sht.exception.ITSZException thrown
		// at com.itsz.sht.service.mcs.McsServiceImpl.callEnquireAccountDetail(McsServiceImpl.java:983)
		// jtest_unverified
	}

	/**
	 * 针对方法的测试: callEnquireAccountList(com.itsz.sht.common.model.request.EnquireAccountListRequestModel)
	 * @throws Throwable 测试可能抛出 Throwable
	 * @see McsServiceImpl#callEnquireAccountList(com.itsz.sht.common.model.request.EnquireAccountListRequestModel)
	 * @author Parasoft Jtest 9.0
	 */
	@Test
	public void testCallEnquireAccountList1() throws Throwable {
		
		EnquireAccountListRequestModel accountModel = new EnquireAccountListRequestModel();
		accountModel.setCustCode("custCode3");
		MISAccountListResponse result = this.mcsService
				.callEnquireAccountList(accountModel);
		// com.itsz.sht.exception.ITSZException thrown
		// at com.itsz.sht.service.mcs.McsServiceImpl.callEnquireAccountList(McsServiceImpl.java:1058)
		// jtest_unverified
	}

	/**
	 * 针对方法的测试: callEnquireAccountList(com.itsz.sht.common.model.request.EnquireAccountListRequestModel)
	 * @throws Throwable 测试可能抛出 Throwable
	 * @see McsServiceImpl#callEnquireAccountList(com.itsz.sht.common.model.request.EnquireAccountListRequestModel)
	 * @author Parasoft Jtest 9.0
	 */
	@Test
	public void testCallEnquireAccountList7() throws Throwable {
		
		MISAccountListResponse result = this.mcsService
				.callEnquireAccountList((EnquireAccountListRequestModel) null);
		// NullPointerException thrown, originator is arg 1 to <Method com.itsz.sht.service.mcs.McsServiceImpl.callEnquireAccountList(Lcom/itsz/sht/common/model/request/EnquireAccountListRequestModel;)Lcom/taifook/mcs/core/beans/msg/MISAccountListResponse;>
		// at com.itsz.sht.service.mcs.McsServiceImpl.callEnquireAccountList(McsServiceImpl.java:1048)
		// jtest_unverified
	}

	/**
	 * 针对方法的测试: callEnquireMarginRations(com.itsz.sht.common.model.request.MarginFinancingListRequestModel)
	 * @throws Throwable 测试可能抛出 Throwable
	 * @see McsServiceImpl#callEnquireMarginRations(com.itsz.sht.common.model.request.MarginFinancingListRequestModel)
	 * @author Parasoft Jtest 9.0
	 */
	@Test
	public void testCallEnquireMarginRations3() throws Throwable {
		
		MarginFinancingListRequestModel requestModel = new MarginFinancingListRequestModel();
		Integer fromIdx = new Integer(-2147483648);
		Integer toIdx = new Integer("0");
		requestModel.setLoginId("loginId6");
		requestModel.setMarketCode("marketCode6");
		requestModel.setInstrCode("instrCode6");
		requestModel.setFromIdx(fromIdx);
		requestModel.setToIdx(toIdx);
		requestModel.setLanguage("language6");
		MarginFinancingListResponseModel result = this.mcsService
				.callEnquireMarginRations(requestModel);
		// com.itsz.sht.exception.ITSZException thrown
		// at com.itsz.sht.service.mcs.McsServiceImpl.callEnquireMarginRations(McsServiceImpl.java:1375)
		// jtest_unverified
	}

	/**
	 * 指定当运行 testCallEnquireMarginRations3 要使用的桩.
	 * @param method 被调用的方法或构造方法
	 * @param _this 对应实例对象的方法或
	 *        <code>null</code> 静态方法
	 * @param args 传递到方法的参数
	 * @return 要使用的桩返回值或 <code>Stubs.NO_STUB_GENERATED</code>
	 *        指定不要打桩的方法调用.
	 * @throws Throwable Stubs may throw any Throwable
	 * @author Parasoft Jtest 9.0
	 */
	public Object stubsCallEnquireMarginRations3(Member method, Object _this,
			Object[] args) throws Throwable {
		Class[] argument_types;
		if (Stubs.matches(method, PropertyUtils.class)) {
			argument_types = new Class[] { Object.class, Object.class };
			if (Stubs.matches(method, "copyProperties", argument_types)) {
				return Stubs.VOID;
			}
		}
		return Stubs.NO_STUB_GENERATED;
	}

	/**
	 * 针对方法的测试: callEnquireMisAccount(com.itsz.sht.common.model.request.EnquireAccountListRequestModel)
	 * @throws Throwable 测试可能抛出 Throwable
	 * @see McsServiceImpl#callEnquireMisAccount(com.itsz.sht.common.model.request.EnquireAccountListRequestModel)
	 * @author Parasoft Jtest 9.0
	 */
	@Test
	public void testCallEnquireMisAccount1() throws Throwable {
		
		EnquireAccountListRequestModel accountModel = new EnquireAccountListRequestModel();
		accountModel.setCustCode("custCode6");
		MISAccountEnquiryResponse result = this.mcsService
				.callEnquireMisAccount(accountModel);
		// com.itsz.sht.exception.ITSZException thrown
		// at com.itsz.sht.service.mcs.McsServiceImpl.callEnquireMisAccount(McsServiceImpl.java:1033)
		// jtest_unverified
	}

	/**
	 * 针对方法的测试: callEnquireMisAccount(com.itsz.sht.common.model.request.EnquireAccountListRequestModel)
	 * @throws Throwable 测试可能抛出 Throwable
	 * @see McsServiceImpl#callEnquireMisAccount(com.itsz.sht.common.model.request.EnquireAccountListRequestModel)
	 * @author Parasoft Jtest 9.0
	 */
	@Test
	public void testCallEnquireMisAccount10() throws Throwable {
		
		MISAccountEnquiryResponse result = this.mcsService
				.callEnquireMisAccount((EnquireAccountListRequestModel) null);
		// NullPointerException thrown, originator is arg 1 to <Method com.itsz.sht.service.mcs.McsServiceImpl.callEnquireMisAccount(Lcom/itsz/sht/common/model/request/EnquireAccountListRequestModel;)Lcom/taifook/mcs/core/beans/msg/MISAccountEnquiryResponse;>
		// at com.itsz.sht.service.mcs.McsServiceImpl.callEnquireMisAccount(McsServiceImpl.java:1023)
		// jtest_unverified
	}

	/**
	 * 针对方法的测试: callEnquiryPosition(com.itsz.sht.common.model.request.EnquiryPositionRequestModel)
	 * @throws Throwable 测试可能抛出 Throwable
	 * @see McsServiceImpl#callEnquiryPosition(com.itsz.sht.common.model.request.EnquiryPositionRequestModel)
	 * @author Parasoft Jtest 9.0
	 */
	@Test
	public void testCallEnquiryPosition1() throws Throwable {
		
		EnquiryPositionRequestModel positionModel = new EnquiryPositionRequestModel();
		positionModel.setAccountId("accountId6");
		positionModel.setMarketCode("marketCode6");
		positionModel.setFromIdx(100);
		positionModel.setMaxResults(1000);
		positionModel.setDefaultAccountId("defaultAccountId6");
		positionModel.setAllowTradeStatusFlag("allowTradeStatusFlag6");
		MTSSShareHoldingResponse result = this.mcsService
				.callEnquiryPosition(positionModel);
		// com.itsz.sht.exception.ITSZException thrown
		// at com.itsz.sht.service.mcs.McsServiceImpl.callEnquiryPosition(McsServiceImpl.java:1008)
		// jtest_unverified
	}

	/**
	 * 针对方法的测试: callFilterOrder(com.itsz.sht.common.model.request.FilterOrderRequestModel)
	 * @throws Throwable 测试可能抛出 Throwable
	 * @see McsServiceImpl#callFilterOrder(com.itsz.sht.common.model.request.FilterOrderRequestModel)
	 * @author Parasoft Jtest 9.0
	 */
	@Test
	public void testCallFilterOrder1() throws Throwable {
		
		FilterOrderRequestModel listModel = new FilterOrderRequestModel();
		listModel.setSupportOverNightFlag("supportOverNightFlag13");
		listModel.setInstrCode("instrCode13");
		listModel.setPageSize("p");
		listModel.setPageNo("pageNo5");
		listModel.setFromIdx(-2147483648);
		listModel.setToIdx(-1000);
		listModel.setMarketCode("marketCode13");
		listModel.setAccountId("accountId13");
		listModel.setOrderStateType("California");
		FilterOrderResponseModel result = this.mcsService
				.callFilterOrder(listModel);
		// NumberFormatException thrown, originator is possible setup problem
		// at java.lang.NumberFormatException.forInputString(NumberFormatException.java:48)
		// at java.lang.Integer.parseInt(Integer.java:449)
		// at com.itsz.sht.common.model.common.ModelUtil.setFromToIndex(ModelUtil.java:708)
		// at com.itsz.sht.common.model.common.ModelUtil.assembleOrderFilteringRequest(ModelUtil.java:686)
		// at com.itsz.sht.service.mcs.McsServiceImpl.callFilterOrder(McsServiceImpl.java:412)
		// jtest_unverified
	}

	/**
	 * 针对方法的测试: callFilterOrder(com.itsz.sht.common.model.request.FilterOrderRequestModel)
	 * @throws Throwable 测试可能抛出 Throwable
	 * @see McsServiceImpl#callFilterOrder(com.itsz.sht.common.model.request.FilterOrderRequestModel)
	 * @author Parasoft Jtest 9.0
	 */
	@Test
	public void testCallFilterOrder2() throws Throwable {
		
		FilterOrderRequestModel listModel = new FilterOrderRequestModel();
		listModel.setSupportOverNightFlag("supportOverNightFlag10");
		listModel.setInstrCode("instrCode10");
		listModel.setPageSize("-");
		listModel.setPageNo("-");
		listModel.setFromIdx(5);
		listModel.setToIdx(-10);
		listModel.setMarketCode("marketCode10");
		listModel.setAccountId("accountId10");
		listModel.setOrderStateType("Arizona");
		FilterOrderResponseModel result = this.mcsService
				.callFilterOrder(listModel);
		// NumberFormatException thrown, originator is possible setup problem
		// at java.lang.NumberFormatException.forInputString(NumberFormatException.java:48)
		// at java.lang.Integer.parseInt(Integer.java:476)
		// at com.itsz.sht.common.model.common.ModelUtil.setFromToIndex(ModelUtil.java:708)
		// at com.itsz.sht.common.model.common.ModelUtil.assembleOrderFilteringRequest(ModelUtil.java:686)
		// at com.itsz.sht.service.mcs.McsServiceImpl.callFilterOrder(McsServiceImpl.java:412)
		// jtest_unverified
	}

	/**
	 * 针对方法的测试: callFilterOrder(com.itsz.sht.common.model.request.FilterOrderRequestModel)
	 * @throws Throwable 测试可能抛出 Throwable
	 * @see McsServiceImpl#callFilterOrder(com.itsz.sht.common.model.request.FilterOrderRequestModel)
	 * @author Parasoft Jtest 9.0
	 */
	@Test
	public void testCallFilterOrder3() throws Throwable {
		
		FilterOrderRequestModel listModel = new FilterOrderRequestModel();
		listModel.setSupportOverNightFlag("supportOverNightFlag7");
		listModel.setInstrCode("instrCode7");
		listModel.setPageSize("");
		listModel.setPageNo("-");
		listModel.setFromIdx(858);
		listModel.setToIdx(2147483647);
		listModel.setMarketCode("marketCode7");
		listModel.setAccountId("accountId7");
		listModel.setOrderStateType("-");
		FilterOrderResponseModel result = this.mcsService
				.callFilterOrder(listModel);
		// com.itsz.sht.exception.ITSZException thrown
		// at com.itsz.sht.service.mcs.McsServiceImpl.getFilterOrder(McsServiceImpl.java:546)
		// at com.itsz.sht.service.mcs.McsServiceImpl.callFilterOrder(McsServiceImpl.java:413)
		// jtest_unverified
	}

	/**
	 * 针对方法的测试: callFilterOrder(com.itsz.sht.common.model.request.FilterOrderRequestModel)
	 * @throws Throwable 测试可能抛出 Throwable
	 * @see McsServiceImpl#callFilterOrder(com.itsz.sht.common.model.request.FilterOrderRequestModel)
	 * @author Parasoft Jtest 9.0
	 */
	@Test
	public void testCallFilterOrder11() throws Throwable {
		
		
		FilterOrderResponseModel result = this.mcsService
				.callFilterOrder((FilterOrderRequestModel) null);
		// NullPointerException thrown, originator is arg 1 to <Method com.itsz.sht.service.mcs.McsServiceImpl.callFilterOrder(Lcom/itsz/sht/common/model/request/FilterOrderRequestModel;)Lcom/itsz/sht/common/model/response/FilterOrderResponseModel;>
		// at com.itsz.sht.service.mcs.McsServiceImpl.callFilterOrder(McsServiceImpl.java:413)
		// jtest_unverified
	}

	/**
	 * 针对方法的测试: callListOrder(com.itsz.sht.common.model.request.ListOrderRequestModel)
	 * @throws Throwable 测试可能抛出 Throwable
	 * @see McsServiceImpl#callListOrder(com.itsz.sht.common.model.request.ListOrderRequestModel)
	 * @author Parasoft Jtest 9.0
	 */
	@Test
	public void testCallListOrder1() throws Throwable {
		
		ListOrderRequestModel listModel = new ListOrderRequestModel();
		listModel.setMarketCode("marketCode11");
		listModel.setAccountId("accountId11");
		listModel
				.setOrderStateType("~!@#$%^&*()_+{}:\"?><`-=][';/.,|\\ \t\n\b");
		listModel.setFromIdx(100);
		listModel.setToIdx(1000);
		listModel.setPageSize("pageSize11");
		listModel.setPageNo("pageNo11");
		ListOrderResponseModel result = this.mcsService.callListOrder(listModel);
		// com.itsz.sht.exception.ITSZException thrown
		// at com.itsz.sht.service.mcs.McsServiceImpl.getFilterOrder(McsServiceImpl.java:546)
		// at com.itsz.sht.service.mcs.McsServiceImpl.callListOrder(McsServiceImpl.java:443)
		// jtest_unverified
	}

	/**
	 * 针对方法的测试: callListOrder(com.itsz.sht.common.model.request.ListOrderRequestModel)
	 * @throws Throwable 测试可能抛出 Throwable
	 * @see McsServiceImpl#callListOrder(com.itsz.sht.common.model.request.ListOrderRequestModel)
	 * @author Parasoft Jtest 9.0
	 */
	@Test
	public void testCallListOrder12() throws Throwable {
		
		
		ListOrderResponseModel result = this.mcsService
				.callListOrder((ListOrderRequestModel) null);
		// NullPointerException thrown, originator is arg 1 to <Method com.itsz.sht.service.mcs.McsServiceImpl.callListOrder(Lcom/itsz/sht/common/model/request/ListOrderRequestModel;)Lcom/itsz/sht/common/model/response/ListOrderResponseModel;>
		// at com.itsz.sht.service.mcs.McsServiceImpl.callListOrder(McsServiceImpl.java:443)
		// jtest_unverified
	}

	/**
	 * 针对方法的测试: callModifyOrder(com.itsz.sht.common.model.request.ModifyOrderRequestModel,com.taifook.mcs.core.beans.msg.OrderInfo)
	 * @throws Throwable 测试可能抛出 Throwable
	 * @see McsServiceImpl#callModifyOrder(com.itsz.sht.common.model.request.ModifyOrderRequestModel,com.taifook.mcs.core.beans.msg.OrderInfo)
	 * @author Parasoft Jtest 9.0
	 */
	@Test
	public void testCallModifyOrder1() throws Throwable {
		
		
		ModifyOrderResponseModel result = this.mcsService.callModifyOrder(
				(ModifyOrderRequestModel) null, (OrderInfo) null);
		// NullPointerException thrown, originator is arg 1 to <Method com.itsz.sht.service.mcs.McsServiceImpl.callModifyOrder(Lcom/itsz/sht/common/model/request/ModifyOrderRequestModel;Lcom/taifook/mcs/core/beans/msg/OrderInfo;)Lcom/itsz/sht/common/model/response/ModifyOrderResponseModel;>
		// at com.itsz.sht.service.mcs.McsServiceImpl.callModifyOrder(McsServiceImpl.java:764)
		// jtest_unverified
	}

	/**
	 * 针对方法的测试: callModifyOrder(com.itsz.sht.common.model.request.ModifyOrderRequestModel,com.taifook.mcs.core.beans.msg.OrderInfo)
	 * @throws Throwable 测试可能抛出 Throwable
	 * @see McsServiceImpl#callModifyOrder(com.itsz.sht.common.model.request.ModifyOrderRequestModel,com.taifook.mcs.core.beans.msg.OrderInfo)
	 * @author Parasoft Jtest 9.0
	 */
	@Test
	public void testCallModifyOrder6() throws Throwable {
		
		ModifyOrderRequestModel reqModel = new ModifyOrderRequestModel();
		OrderInfo orderInfo = new OrderInfo();
		_fInteger = new Integer(1000);
		
		ModifyOrderResponseModel result = this.mcsService.callModifyOrder(
				reqModel, orderInfo);
		// com.itsz.sht.exception.ITSZException thrown
		// at com.itsz.sht.service.mcs.McsServiceImpl.getModifyOrder(McsServiceImpl.java:748)
		// at com.itsz.sht.service.mcs.McsServiceImpl.callModifyOrder(McsServiceImpl.java:767)
		// jtest_unverified
	}

	/**
	 * 指定当运行 testCallModifyOrder6 要使用的桩.
	 * @param method 被调用的方法或构造方法
	 * @param _this 对应实例对象的方法或
	 *        <code>null</code> 静态方法
	 * @param args 传递到方法的参数
	 * @return 要使用的桩返回值或 <code>Stubs.NO_STUB_GENERATED</code>
	 *        指定不要打桩的方法调用.
	 * @throws Throwable Stubs may throw any Throwable
	 * @author Parasoft Jtest 9.0
	 */
	public Object stubsCallModifyOrder6(Member method, Object _this,
			Object[] args) throws Throwable {
		Class[] argument_types;
		if (Stubs.matches(method, McsUtil.class)) {
			argument_types = new Class[] { BigDecimal.class, BigDecimal.class,
					BigDecimal.class };
			if (Stubs.matches(method, "getDelta", argument_types)) {
				return _fInteger;
			}
		}
		if (Stubs.matches(method, ModifyOrderRequest.class)) {
			argument_types = new Class[] { Timestamp.class };
			if (Stubs.matches(method, "setExpiryDate", argument_types)) {
				return Stubs.VOID;
			}
		}
		if (Stubs.matches(method, Timestamp.class)) {
			argument_types = new Class[] { Long.TYPE };
			if (Stubs.matches(method, argument_types)) {
				return (Timestamp) Stubs.makeStubObject(Timestamp.class);
			}
		}
		return Stubs.NO_STUB_GENERATED;
	}

	/**
	 * 针对方法的测试: callModifyOrder(com.itsz.sht.common.model.request.ModifyOrderRequestModel,com.taifook.mcs.core.beans.msg.OrderInfo)
	 * @throws Throwable 测试可能抛出 Throwable
	 * @see McsServiceImpl#callModifyOrder(com.itsz.sht.common.model.request.ModifyOrderRequestModel,com.taifook.mcs.core.beans.msg.OrderInfo)
	 * @author Parasoft Jtest 9.0
	 */
	@Test
	public void testCallModifyOrder13() throws Throwable {
		
		ModifyOrderRequestModel reqModel = new ModifyOrderRequestModel();
		OrderInfo orderInfo = new OrderInfo();
		ModifyOrderResponseModel result = this.mcsService.callModifyOrder(
				reqModel, orderInfo);
		// NullPointerException thrown, originator is possible setup problem
		// at com.itsz.sht.service.mcs.McsUtil.getDelta(McsUtil.java:26)
		// at com.itsz.sht.common.model.common.ModelUtil.calDelta(ModelUtil.java:1039)
		// at com.itsz.sht.common.model.common.ModelUtil.assembleModifyOrderRequest(ModelUtil.java:1017)
		// at com.itsz.sht.service.mcs.McsServiceImpl.callModifyOrder(McsServiceImpl.java:763)
		// jtest_unverified
	}

	/**
	 * 指定当运行 testCallModifyOrder13 要使用的桩.
	 * @param method 被调用的方法或构造方法
	 * @param _this 对应实例对象的方法或
	 *        <code>null</code> 静态方法
	 * @param args 传递到方法的参数
	 * @return 要使用的桩返回值或 <code>Stubs.NO_STUB_GENERATED</code>
	 *        指定不要打桩的方法调用.
	 * @throws Throwable Stubs may throw any Throwable
	 * @author Parasoft Jtest 9.0
	 */
	public Object stubsCallModifyOrder13(Member method, Object _this,
			Object[] args) throws Throwable {
		Class[] argument_types;
		if (Stubs.matches(method, ModifyOrderRequest.class)) {
			argument_types = new Class[] { Timestamp.class };
			if (Stubs.matches(method, "setExpiryDate", argument_types)) {
				return Stubs.VOID;
			}
		}
		if (Stubs.matches(method, Timestamp.class)) {
			argument_types = new Class[] { Long.TYPE };
			if (Stubs.matches(method, argument_types)) {
				return (Timestamp) Stubs.makeStubObject(Timestamp.class);
			}
		}
		return Stubs.NO_STUB_GENERATED;
	}

	/**
	 * 针对方法的测试: callModifyOrder(com.itsz.sht.common.model.request.ModifyOrderRequestModel,com.taifook.mcs.core.beans.msg.OrderInfo)
	 * @throws Throwable 测试可能抛出 Throwable
	 * @see McsServiceImpl#callModifyOrder(com.itsz.sht.common.model.request.ModifyOrderRequestModel,com.taifook.mcs.core.beans.msg.OrderInfo)
	 * @author Parasoft Jtest 9.0
	 */
	@Test
	public void testCallModifyOrder14() throws Throwable {
		
		ModifyOrderRequestModel reqModel = new ModifyOrderRequestModel();
		ModifyOrderResponseModel result = this.mcsService.callModifyOrder(
				reqModel, (OrderInfo) null);
		// NullPointerException thrown, originator is arg 2 to <Method com.itsz.sht.service.mcs.McsServiceImpl.callModifyOrder(Lcom/itsz/sht/common/model/request/ModifyOrderRequestModel;Lcom/taifook/mcs/core/beans/msg/OrderInfo;)Lcom/itsz/sht/common/model/response/ModifyOrderResponseModel;>
		// at com.itsz.sht.common.model.common.ModelUtil.assembleModifyOrderRequest(ModelUtil.java:1007)
		// at com.itsz.sht.service.mcs.McsServiceImpl.callModifyOrder(McsServiceImpl.java:763)
		// jtest_unverified
	}

	/**
	 * 指定当运行 testCallModifyOrder14 要使用的桩.
	 * @param method 被调用的方法或构造方法
	 * @param _this 对应实例对象的方法或
	 *        <code>null</code> 静态方法
	 * @param args 传递到方法的参数
	 * @return 要使用的桩返回值或 <code>Stubs.NO_STUB_GENERATED</code>
	 *        指定不要打桩的方法调用.
	 * @throws Throwable Stubs may throw any Throwable
	 * @author Parasoft Jtest 9.0
	 */
	public Object stubsCallModifyOrder14(Member method, Object _this,
			Object[] args) throws Throwable {
		Class[] argument_types;
		if (Stubs.matches(method, ModifyOrderRequest.class)) {
			argument_types = new Class[] { Timestamp.class };
			if (Stubs.matches(method, "setExpiryDate", argument_types)) {
				return Stubs.VOID;
			}
		}
		if (Stubs.matches(method, Timestamp.class)) {
			argument_types = new Class[] { Long.TYPE };
			if (Stubs.matches(method, argument_types)) {
				return (Timestamp) Stubs.makeStubObject(Timestamp.class);
			}
		}
		return Stubs.NO_STUB_GENERATED;
	}

	/**
	 * 针对方法的测试: callMos(com.itsz.sht.common.model.request.MosRequestModel)
	 * @throws Throwable 测试可能抛出 Throwable
	 * @see McsServiceImpl#callMos(com.itsz.sht.common.model.request.MosRequestModel)
	 * @author Parasoft Jtest 9.0
	 */
	@Test
	public void testCallMos1() throws Throwable {
		
		MosRequestModel mosModel = new MosRequestModel();
		
		mosModel.setAccountId("accountId4");
		mosModel.setInstrCode("");
		mosModel.setMarketCode("marketCode4");
		mosModel.setMessageId("messageId4");
		mosModel.setMessageSEQNum("messageSEQNum4");
		MosResponseModel result = this.mcsService.callMos(mosModel);
		// com.itsz.sht.exception.ITSZException thrown
		// at com.itsz.sht.service.mcs.McsServiceImpl.getMos(McsServiceImpl.java:575)
		// at com.itsz.sht.service.mcs.McsServiceImpl.callMos(McsServiceImpl.java:554)
		// jtest_unverified
	}

	/**
	 * 针对方法的测试: callMos(com.itsz.sht.common.model.request.MosRequestModel)
	 * @throws Throwable 测试可能抛出 Throwable
	 * @see McsServiceImpl#callMos(com.itsz.sht.common.model.request.MosRequestModel)
	 * @author Parasoft Jtest 9.0
	 */
	@Test
	public void testCallMos7() throws Throwable {
		
		
		MosResponseModel result = this.mcsService.callMos((MosRequestModel) null);
		// NullPointerException thrown, originator is arg 1 to <Method com.itsz.sht.service.mcs.McsServiceImpl.callMos(Lcom/itsz/sht/common/model/request/MosRequestModel;)Lcom/itsz/sht/common/model/response/MosResponseModel;>
		// at com.itsz.sht.service.mcs.McsServiceImpl.callMos(McsServiceImpl.java:554)
		// jtest_unverified
	}

	/**
	 * 针对方法的测试: callOrderDetail(com.itsz.sht.common.model.request.OrderDetailRequestModel)
	 * @throws Throwable 测试可能抛出 Throwable
	 * @see McsServiceImpl#callOrderDetail(com.itsz.sht.common.model.request.OrderDetailRequestModel)
	 * @author Parasoft Jtest 9.0
	 */
	@Test
	public void testCallOrderDetail1() throws Throwable {
		
		OrderDetailRequestModel orderDetail = new OrderDetailRequestModel();
		Long mcsOrderId = new Long("0");
		Long orderId = new Long(2147483647L);
		orderDetail.setSupportOverNightFlag("supportOverNightFlag5");
		orderDetail.setAccountId("accountId5");
		orderDetail.setMcsOrderId(mcsOrderId);
		orderDetail.setOrderId(orderId);
		orderDetail.setHasHisories("hasHisories5");
		orderDetail.setTransactionProtection("transactionProtection5");
		OrderDetailTradeResponseModel result = this.mcsService
				.callOrderDetail(orderDetail);
		// com.itsz.sht.exception.ITSZException thrown
		// at com.itsz.sht.service.mcs.McsServiceImpl.getOrderDetail(McsServiceImpl.java:624)
		// at com.itsz.sht.service.mcs.McsServiceImpl.callOrderDetail(McsServiceImpl.java:585)
		// jtest_unverified
	}

	/**
	 * 针对方法的测试: callOrderDetail(com.itsz.sht.common.model.request.OrderDetailRequestModel)
	 * @throws Throwable 测试可能抛出 Throwable
	 * @see McsServiceImpl#callOrderDetail(com.itsz.sht.common.model.request.OrderDetailRequestModel)
	 * @author Parasoft Jtest 9.0
	 */
	@Test
	public void testCallOrderDetail7() throws Throwable {
		
		
		OrderDetailTradeResponseModel result = this.mcsService
				.callOrderDetail((OrderDetailRequestModel) null);
		// NullPointerException thrown, originator is arg 1 to <Method com.itsz.sht.service.mcs.McsServiceImpl.callOrderDetail(Lcom/itsz/sht/common/model/request/OrderDetailRequestModel;)Lcom/itsz/sht/common/model/response/listorder/OrderDetailTradeResponseModel;>
		// at com.itsz.sht.service.mcs.McsServiceImpl.callOrderDetail(McsServiceImpl.java:585)
		// jtest_unverified
	}

	/**
	 * 针对方法的测试: callOrderFee(com.itsz.sht.common.model.request.OrderFeeRequestModel,java.math.BigDecimal)
	 * @throws Throwable 测试可能抛出 Throwable
	 * @see McsServiceImpl#callOrderFee(com.itsz.sht.common.model.request.OrderFeeRequestModel,java.math.BigDecimal)
	 * @author Parasoft Jtest 9.0
	 */
	@Test
	public void testCallOrderFee1() throws Throwable {
		
		OrderFeeRequestModel orderFeeReq = new OrderFeeRequestModel();
		Integer lotsize = new Integer(5);
		BigDecimal orderQuantity = new BigDecimal("52000.0");
		BigDecimal orderPrice = new BigDecimal("1.0");
		BigDecimal triggerPrice = new BigDecimal("0.0");
		BigDecimal mos = new BigDecimal("0.0");
		orderFeeReq.setAccountId("accountId19");
		orderFeeReq.setAllOrNothing("allOrNothing19");
		orderFeeReq.setInstrCode("instrCode19");
		orderFeeReq.setLotsize(lotsize);
		orderFeeReq.setMarketCode("marketCode19");
		orderFeeReq.setOrderSide("orderSide0");
		orderFeeReq.setOrderType("orderType19");
		orderFeeReq.setOrderQuantity(orderQuantity);
		orderFeeReq.setOrderPrice(orderPrice);
		orderFeeReq.setConditionType("conditionType19");
		orderFeeReq.setIsConditionType("isConditionType19");
		orderFeeReq.setTriggerPrice(triggerPrice);
		orderFeeReq.setCustomerId("customerId19");
		orderFeeReq.setIsNeedCallMos("isNeedCallMos19");
		OrderFeeResponseModel result = this.mcsService.callOrderFee(orderFeeReq,
				mos);
		assertNotNull(result); // jtest_unverified
		assertEquals(null, result.getInsufficientAmount()); // jtest_unverified
		assertEquals(orderQuantity, result.getOrderQuantity()); // jtest_unverified
		assertEquals(orderPrice, result.getOrderPrice()); // jtest_unverified
		assertEquals("orderSide0", result.getOrderSide()); // jtest_unverified
		assertEquals("instrCode19", result.getInstrCode()); // jtest_unverified
		assertEquals("orderType19", result.getOrderType()); // jtest_unverified
		assertEquals(triggerPrice, result.getTriggerPrice()); // jtest_unverified
		assertEquals("conditionType19", result.getConditionType()); // jtest_unverified
		// No exception thrown
		// jtest_unverified
	}

	/**
	 * 针对方法的测试: callOrderFee(com.itsz.sht.common.model.request.OrderFeeRequestModel,java.math.BigDecimal)
	 * @throws Throwable 测试可能抛出 Throwable
	 * @see McsServiceImpl#callOrderFee(com.itsz.sht.common.model.request.OrderFeeRequestModel,java.math.BigDecimal)
	 * @author Parasoft Jtest 9.0
	 */
	@Test
	public void testCallOrderFee2() throws Throwable {
		
		OrderFeeRequestModel orderFeeReq = new OrderFeeRequestModel();
		Integer lotsize = new Integer(100);
		BigDecimal orderQuantity = new BigDecimal("1000.0");
		BigDecimal orderPrice = new BigDecimal("1.0");
		BigDecimal triggerPrice = new BigDecimal("0.0");
		orderFeeReq.setAccountId("accountId3");
		orderFeeReq.setAllOrNothing("allOrNothing3");
		orderFeeReq.setInstrCode("instrCode3");
		orderFeeReq.setLotsize(lotsize);
		orderFeeReq.setMarketCode("marketCode3");
		orderFeeReq.setOrderSide("B");
		orderFeeReq.setOrderType("orderType3");
		orderFeeReq.setOrderQuantity(orderQuantity);
		orderFeeReq.setOrderPrice(orderPrice);
		orderFeeReq.setConditionType("conditionType3");
		orderFeeReq.setIsConditionType("isConditionType3");
		orderFeeReq.setTriggerPrice(triggerPrice);
		orderFeeReq.setCustomerId("customerId3");
		orderFeeReq.setIsNeedCallMos("isNeedCallMos3");
		OrderFeeResponseModel result = this.mcsService.callOrderFee(orderFeeReq,
				(BigDecimal) null);
		assertNotNull(result); // jtest_unverified
		assertEquals(null, result.getInsufficientAmount()); // jtest_unverified
		assertEquals(orderQuantity, result.getOrderQuantity()); // jtest_unverified
		assertEquals(orderPrice, result.getOrderPrice()); // jtest_unverified
		assertEquals("B", result.getOrderSide()); // jtest_unverified
		assertEquals("instrCode3", result.getInstrCode()); // jtest_unverified
		assertEquals("orderType3", result.getOrderType()); // jtest_unverified
		assertEquals(triggerPrice, result.getTriggerPrice()); // jtest_unverified
		assertEquals("conditionType3", result.getConditionType()); // jtest_unverified
		// No exception thrown
		// jtest_unverified
	}

	/**
	 * 针对方法的测试: callOrderFee(com.itsz.sht.common.model.request.OrderFeeRequestModel,java.math.BigDecimal)
	 * @throws Throwable 测试可能抛出 Throwable
	 * @see McsServiceImpl#callOrderFee(com.itsz.sht.common.model.request.OrderFeeRequestModel,java.math.BigDecimal)
	 * @author Parasoft Jtest 9.0
	 */
	@Test
	public void testCallOrderFee4() throws Throwable {
		
		OrderFeeRequestModel orderFeeReq = new OrderFeeRequestModel();
		Integer lotsize = new Integer(-10);
		BigDecimal orderQuantity = new BigDecimal("5050000.0");
		BigDecimal orderPrice = new BigDecimal("1.0");
		BigDecimal triggerPrice = new BigDecimal("0.0");
		orderFeeReq.setAccountId("accountId1");
		orderFeeReq.setAllOrNothing("allOrNothing1");
		orderFeeReq.setInstrCode("instrCode1");
		orderFeeReq.setLotsize(lotsize);
		orderFeeReq.setMarketCode("marketCode1");
		orderFeeReq.setOrderSide("B");
		orderFeeReq.setOrderType("orderType1");
		orderFeeReq.setOrderQuantity(orderQuantity);
		orderFeeReq.setOrderPrice(orderPrice);
		orderFeeReq.setConditionType("conditionType1");
		orderFeeReq.setIsConditionType("isConditionType1");
		orderFeeReq.setTriggerPrice(triggerPrice);
		orderFeeReq.setCustomerId("customerId1");
		orderFeeReq.setIsNeedCallMos("isNeedCallMos1");
		OrderFeeResponseModel result = this.mcsService.callOrderFee(orderFeeReq,
				(BigDecimal) null);
		assertNotNull(result); // jtest_unverified
		assertEquals(null, result.getInsufficientAmount()); // jtest_unverified
		assertEquals(orderQuantity, result.getOrderQuantity()); // jtest_unverified
		assertEquals(orderPrice, result.getOrderPrice()); // jtest_unverified
		assertEquals("B", result.getOrderSide()); // jtest_unverified
		assertEquals("instrCode1", result.getInstrCode()); // jtest_unverified
		assertEquals("orderType1", result.getOrderType()); // jtest_unverified
		assertEquals(triggerPrice, result.getTriggerPrice()); // jtest_unverified
		assertEquals("conditionType1", result.getConditionType()); // jtest_unverified
		// No exception thrown
		// jtest_unverified
	}

	/**
	 * 针对方法的测试: callOrderFee(com.itsz.sht.common.model.request.OrderFeeRequestModel,java.math.BigDecimal)
	 * @throws Throwable 测试可能抛出 Throwable
	 * @see McsServiceImpl#callOrderFee(com.itsz.sht.common.model.request.OrderFeeRequestModel,java.math.BigDecimal)
	 * @author Parasoft Jtest 9.0
	 */
	@Test
	public void testCallOrderFee6() throws Throwable {
		
		OrderFeeRequestModel orderFeeReq = new OrderFeeRequestModel();
		
		OrderFeeResponseModel result = this.mcsService.callOrderFee(orderFeeReq,
				(BigDecimal) null);
		// com.itsz.sht.exception.ITSZException thrown
		// at com.itsz.sht.service.mcs.McsServiceImpl.callOrderFee(McsServiceImpl.java:250)
		// jtest_unverified
	}

	/**
	 * 针对方法的测试: callPlaceOrder(com.itsz.sht.common.model.request.PlaceOrderRequestModel)
	 * @throws Throwable 测试可能抛出 Throwable
	 * @see McsServiceImpl#callPlaceOrder(com.itsz.sht.common.model.request.PlaceOrderRequestModel)
	 * @author Parasoft Jtest 9.0
	 */
	@Test
	public void testCallPlaceOrder1() throws Throwable {
		
		PlaceOrderRequestModel placeReq = new PlaceOrderRequestModel();
		
		PlaceOrderResponseModel result = this.mcsService.callPlaceOrder(placeReq);
		// com.itsz.sht.exception.ITSZException thrown
		// at com.itsz.sht.service.mcs.McsServiceImpl.getPlaceOrder(McsServiceImpl.java:402)
		// at com.itsz.sht.service.mcs.McsServiceImpl.callPlaceOrder(McsServiceImpl.java:371)
		// jtest_unverified
	}

	/**
	 * 针对方法的测试: callPlaceOrder(com.itsz.sht.common.model.request.PlaceOrderRequestModel)
	 * @throws Throwable 测试可能抛出 Throwable
	 * @see McsServiceImpl#callPlaceOrder(com.itsz.sht.common.model.request.PlaceOrderRequestModel)
	 * @author Parasoft Jtest 9.0
	 */
	@Test
	public void testCallPlaceOrder9() throws Throwable {
		
		
		PlaceOrderResponseModel result = this.mcsService
				.callPlaceOrder((PlaceOrderRequestModel) null);
		// NullPointerException thrown, originator is arg 1 to <Method com.itsz.sht.service.mcs.McsServiceImpl.callPlaceOrder(Lcom/itsz/sht/common/model/request/PlaceOrderRequestModel;)Lcom/itsz/sht/common/model/response/PlaceOrderResponseModel;>
		// at com.itsz.sht.service.mcs.McsServiceImpl.callPlaceOrder(McsServiceImpl.java:371)
		// jtest_unverified
	}

	/**
	 * 针对方法的测试: callPrePlaceOrder(com.itsz.sht.common.model.request.PrePlaceOrderRequestModel)
	 * @throws Throwable 测试可能抛出 Throwable
	 * @see McsServiceImpl#callPrePlaceOrder(com.itsz.sht.common.model.request.PrePlaceOrderRequestModel)
	 * @author Parasoft Jtest 9.0
	 */
	@Test
	public void testCallPrePlaceOrder3() throws Throwable {
		
		PrePlaceOrderRequestModel prePlaceReq = new PrePlaceOrderRequestModel();
		
		PrePlaceOrderResponseModel result = this.mcsService
				.callPrePlaceOrder(prePlaceReq);
		// com.itsz.sht.exception.ITSZException thrown
		// at com.itsz.sht.service.mcs.McsServiceImpl.getPrePlaceOrder(McsServiceImpl.java:359)
		// at com.itsz.sht.service.mcs.McsServiceImpl.callPrePlaceOrder(McsServiceImpl.java:299)
		// jtest_unverified
	}

	/**
	 * 针对方法的测试: callPrePlaceOrder(com.itsz.sht.common.model.request.PrePlaceOrderRequestModel)
	 * @throws Throwable 测试可能抛出 Throwable
	 * @see McsServiceImpl#callPrePlaceOrder(com.itsz.sht.common.model.request.PrePlaceOrderRequestModel)
	 * @author Parasoft Jtest 9.0
	 */
	@Test
	public void testCallPrePlaceOrder7() throws Throwable {
		
		
		PrePlaceOrderResponseModel result = this.mcsService
				.callPrePlaceOrder((PrePlaceOrderRequestModel) null);
		// NullPointerException thrown, originator is arg 1 to <Method com.itsz.sht.service.mcs.McsServiceImpl.callPrePlaceOrder(Lcom/itsz/sht/common/model/request/PrePlaceOrderRequestModel;)Lcom/itsz/sht/common/model/response/placeorder/PrePlaceOrderResponseModel;>
		// at com.itsz.sht.service.mcs.McsServiceImpl.callPrePlaceOrder(McsServiceImpl.java:299)
		// jtest_unverified
	}

	/**
	 * 针对方法的测试: callProfitAndLossEnqiry(com.itsz.sht.common.model.request.ProfitAndLossEnqiryRequestModel)
	 * @throws Throwable 测试可能抛出 Throwable
	 * @see McsServiceImpl#callProfitAndLossEnqiry(com.itsz.sht.common.model.request.ProfitAndLossEnqiryRequestModel)
	 * @author Parasoft Jtest 9.0
	 */
	@Test
	public void testCallProfitAndLossEnqiry3() throws Throwable {
		
		ProfitAndLossEnqiryRequestModel requestModel = new ProfitAndLossEnqiryRequestModel();
		requestModel.setMarketCode("marketCode7");
		requestModel.setLoginID("loginID7");
		requestModel.setAccountID("accountID7");
		requestModel.setProfileID("profileID7");
		requestModel.setQuoteType("quoteType7");
		ProfitAndLossEnqiryResponseModel result = this.mcsService
				.callProfitAndLossEnqiry(requestModel);
		// com.itsz.sht.exception.ITSZException thrown
		// at com.itsz.sht.service.mcs.McsServiceImpl.callProfitAndLossEnqiry(McsServiceImpl.java:1239)
		// jtest_unverified
	}

	/**
	 * 指定当运行 testCallProfitAndLossEnqiry3 要使用的桩.
	 * @param method 被调用的方法或构造方法
	 * @param _this 对应实例对象的方法或
	 *        <code>null</code> 静态方法
	 * @param args 传递到方法的参数
	 * @return 要使用的桩返回值或 <code>Stubs.NO_STUB_GENERATED</code>
	 *        指定不要打桩的方法调用.
	 * @throws Throwable Stubs may throw any Throwable
	 * @author Parasoft Jtest 9.0
	 */
	public Object stubsCallProfitAndLossEnqiry3(Member method, Object _this,
			Object[] args) throws Throwable {
		Class[] argument_types;
		if (Stubs.matches(method, PropertyUtils.class)) {
			argument_types = new Class[] { Object.class, Object.class };
			if (Stubs.matches(method, "copyProperties", argument_types)) {
				return Stubs.VOID;
			}
		}
		return Stubs.NO_STUB_GENERATED;
	}

	/**
	 * 针对方法的测试: callProfitAndLossUpdate(com.itsz.sht.common.model.request.ProfitAndLossUpdateRequestModel)
	 * @throws Throwable 测试可能抛出 Throwable
	 * @see McsServiceImpl#callProfitAndLossUpdate(com.itsz.sht.common.model.request.ProfitAndLossUpdateRequestModel)
	 * @author Parasoft Jtest 9.0
	 */
	@Test
	public void testCallProfitAndLossUpdate1() throws Throwable {
		
		ProfitAndLossUpdateRequestModel requestModel = new ProfitAndLossUpdateRequestModel();
		requestModel.setProfileInfo("0");
		requestModel.setMarketCode("marketCode12");
		requestModel.setLoginID("loginID12");
		requestModel.setProfileID("profileID12");
		ProfitAndLossUpdateResponseModel result = this.mcsService
				.callProfitAndLossUpdate(requestModel);
		// com.itsz.sht.exception.ITSZException thrown
		// at com.itsz.sht.service.mcs.McsServiceImpl.callProfitAndLossUpdate(McsServiceImpl.java:1261)
		// jtest_unverified
	}

	/**
	 * 针对方法的测试: callPwdLogin(com.itsz.sht.common.model.request.LoginRequestModel)
	 * @throws Throwable 测试可能抛出 Throwable
	 * @see McsServiceImpl#callPwdLogin(com.itsz.sht.common.model.request.LoginRequestModel)
	 * @author Parasoft Jtest 9.0
	 */
	@Test
	public void testCallPwdLogin1() throws Throwable {
		
		LoginRequestModel requestModel = new LoginRequestModel();
		requestModel.setRemoteAddr("remoteAddr6");
		requestModel.setCheckVersion("checkVersion6");
		requestModel.setLoginId("loginId6");
		requestModel.setLoginType("loginType6");
		requestModel.setPassword("password6");
		requestModel.setLoginInfoFormat("loginInfoFormat6");
		requestModel.setMessageSEQNum("messageSEQNum6");
		requestModel.setRtqFlag("rtqFlag6");
		LoginResponseModel result = this.mcsService.callPwdLogin(requestModel);
		// com.itsz.sht.exception.ITSZException thrown
		// at com.itsz.sht.service.mcs.McsServiceImpl.getLogin(McsServiceImpl.java:197)
		// at com.itsz.sht.service.mcs.McsServiceImpl.callPwdLogin(McsServiceImpl.java:165)
		// jtest_unverified
	}

	/**
	 * 针对方法的测试: callPwdLogin(com.itsz.sht.common.model.request.LoginRequestModel)
	 * @throws Throwable 测试可能抛出 Throwable
	 * @see McsServiceImpl#callPwdLogin(com.itsz.sht.common.model.request.LoginRequestModel)
	 * @author Parasoft Jtest 9.0
	 */
	@Test
	public void testCallPwdLogin8() throws Throwable {
		
		
		LoginResponseModel result = this.mcsService
				.callPwdLogin((LoginRequestModel) null);
		// NullPointerException thrown, originator is arg 1 to <Method com.itsz.sht.service.mcs.McsServiceImpl.callPwdLogin(Lcom/itsz/sht/common/model/request/LoginRequestModel;)Lcom/itsz/sht/common/model/response/LoginResponseModel;>
		// at com.itsz.sht.service.mcs.McsServiceImpl.callPwdLogin(McsServiceImpl.java:165)
		// jtest_unverified
	}

	/**
	 * 针对方法的测试: callSetTransPwdPtd(com.itsz.sht.common.model.request.TransactionProtectionRequestModel)
	 * @throws Throwable 测试可能抛出 Throwable
	 * @see McsServiceImpl#callSetTransPwdPtd(com.itsz.sht.common.model.request.TransactionProtectionRequestModel)
	 * @author Parasoft Jtest 9.0
	 */
	@Test
	public void testCallSetTransPwdPtd3() throws Throwable {
		
		TransactionProtectionRequestModel requestModel = new TransactionProtectionRequestModel();
		requestModel.setLoginId("loginId6");
		requestModel.setPassword("password6");
		requestModel.setTransactionProtection("transactionProtection6");
		requestModel.setNeedVarifyPassword("needVarifyPassword6");
		TransactionProtectionResponse result = this.mcsService
				.callSetTransPwdPtd(requestModel);
		// com.itsz.sht.exception.ITSZException thrown
		// at com.itsz.sht.service.mcs.McsServiceImpl.callSetTransPwdPtd(McsServiceImpl.java:1079)
		// jtest_unverified
	}

	/**
	 * 指定当运行 testCallSetTransPwdPtd3 要使用的桩.
	 * @param method 被调用的方法或构造方法
	 * @param _this 对应实例对象的方法或
	 *        <code>null</code> 静态方法
	 * @param args 传递到方法的参数
	 * @return 要使用的桩返回值或 <code>Stubs.NO_STUB_GENERATED</code>
	 *        指定不要打桩的方法调用.
	 * @throws Throwable Stubs may throw any Throwable
	 * @author Parasoft Jtest 9.0
	 */
	public Object stubsCallSetTransPwdPtd3(Member method, Object _this,
			Object[] args) throws Throwable {
		Class[] argument_types;
		if (Stubs.matches(method, PropertyUtils.class)) {
			argument_types = new Class[] { Object.class, Object.class };
			if (Stubs.matches(method, "copyProperties", argument_types)) {
				return Stubs.VOID;
			}
		}
		return Stubs.NO_STUB_GENERATED;
	}

	/**
	 * 针对方法的测试: callTradeHistory(com.itsz.sht.common.model.request.OrderDetailRequestModel,com.itsz.sht.common.model.response.listorder.OrderDetailTradeResponseModel)
	 * @throws Throwable 测试可能抛出 Throwable
	 * @see McsServiceImpl#callTradeHistory(com.itsz.sht.common.model.request.OrderDetailRequestModel,com.itsz.sht.common.model.response.listorder.OrderDetailTradeResponseModel)
	 * @author Parasoft Jtest 9.0
	 */
	@Test
	public void testCallTradeHistory1() throws Throwable {
		
		OrderDetailRequestModel oDetailReq = new OrderDetailRequestModel();
		OrderDetailTradeResponseModel responseModel = new OrderDetailTradeResponseModel();
		TradeHistoryResponse tradeHistoryResponse = new TradeHistoryResponse();
		OrderListResponse orderListResponse = new OrderListResponse();
		BigDecimal rejectedQty = new BigDecimal("91016");
		Collection traderHistories = new ArrayList();
		responseModel.setTradeHistoryResponse(tradeHistoryResponse);
		responseModel.setRejectMessage("rejectMessage5");
		responseModel.setOrderListResponse(orderListResponse);
		responseModel.setAllowModify("allowModify5");
		responseModel.setHasHisories("hasHisories10");
		responseModel.setRejectedQty(rejectedQty);
		responseModel.setRejectReason("rejectReason5");
		responseModel.setTraderHistories(traderHistories);
		responseModel.setTransactionProtection("transactionProtection10");
		Collection result = this.mcsService.callTradeHistory(oDetailReq,
				responseModel);
		// com.itsz.sht.exception.ITSZException thrown
		// at com.itsz.sht.service.mcs.McsServiceImpl.getTradeHistory(McsServiceImpl.java:692)
		// at com.itsz.sht.service.mcs.McsServiceImpl.callTradeHistory(McsServiceImpl.java:640)
		// jtest_unverified
	}

	/**
	 * 针对方法的测试: callTradeHistory(com.itsz.sht.common.model.request.OrderDetailRequestModel,com.itsz.sht.common.model.response.listorder.OrderDetailTradeResponseModel)
	 * @throws Throwable 测试可能抛出 Throwable
	 * @see McsServiceImpl#callTradeHistory(com.itsz.sht.common.model.request.OrderDetailRequestModel,com.itsz.sht.common.model.response.listorder.OrderDetailTradeResponseModel)
	 * @author Parasoft Jtest 9.0
	 */
	@Test
	public void testCallTradeHistory14() throws Throwable {
		
		
		Collection result = this.mcsService.callTradeHistory(
				(OrderDetailRequestModel) null,
				(OrderDetailTradeResponseModel) null);
		// NullPointerException thrown, originator is arg 1 to <Method com.itsz.sht.service.mcs.McsServiceImpl.callTradeHistory(Lcom/itsz/sht/common/model/request/OrderDetailRequestModel;Lcom/itsz/sht/common/model/response/listorder/OrderDetailTradeResponseModel;)Ljava/util/Collection;>
		// at com.itsz.sht.service.mcs.McsServiceImpl.callTradeHistory(McsServiceImpl.java:640)
		// jtest_unverified
	}

	/**
	 * 针对方法的测试: callTradeList(com.itsz.sht.common.model.request.TradeListRequestModel)
	 * @throws Throwable 测试可能抛出 Throwable
	 * @see McsServiceImpl#callTradeList(com.itsz.sht.common.model.request.TradeListRequestModel)
	 * @author Parasoft Jtest 9.0
	 */
	@Test
	public void testCallTradeList1() throws Throwable {
		
		
		TradeListResponseModel result = this.mcsService
				.callTradeList((TradeListRequestModel) null);
		// com.itsz.sht.exception.ITSZException thrown
		// at com.itsz.sht.service.mcs.McsServiceImpl.callTradeList(McsServiceImpl.java:1176)
		// jtest_unverified
	}

	/**
	 * 针对方法的测试: callTradeList(com.itsz.sht.common.model.request.TradeListRequestModel)
	 * @throws Throwable 测试可能抛出 Throwable
	 * @see McsServiceImpl#callTradeList(com.itsz.sht.common.model.request.TradeListRequestModel)
	 * @author Parasoft Jtest 9.0
	 */
	@Test
	public void testCallTradeList3() throws Throwable {
		
		MessageSender messageSender = new MessageSender();
		TradeListRequestModel requestModel = new TradeListRequestModel();
		_fTradeListRequest = new TradeListRequest();
		
		TradeListResponseModel result = this.mcsService
				.callTradeList(requestModel);
		assertNotNull(result); // jtest_unverified
		assertEquals(0, result.getPageSize()); // jtest_unverified
		assertEquals(0, result.getListSize()); // jtest_unverified
		assertEquals(0, result.getPageAmount()); // jtest_unverified
		assertEquals(0, result.getPageNo()); // jtest_unverified
		assertEquals(0, result.getNextPage()); // jtest_unverified
		assertEquals(0, result.getPrevPage()); // jtest_unverified
		assertEquals(0, result.getCurrentPage()); // jtest_unverified
		assertEquals(0, result.getTotalPage()); // jtest_unverified
		// No exception thrown
		// jtest_unverified
	}

	/**
	 * 指定当运行 testCallTradeList3 要使用的桩.
	 * @param method 被调用的方法或构造方法
	 * @param _this 对应实例对象的方法或
	 *        <code>null</code> 静态方法
	 * @param args 传递到方法的参数
	 * @return 要使用的桩返回值或 <code>Stubs.NO_STUB_GENERATED</code>
	 *        指定不要打桩的方法调用.
	 * @throws Throwable Stubs may throw any Throwable
	 * @author Parasoft Jtest 9.0
	 */
	public Object stubsCallTradeList3(Member method, Object _this, Object[] args)
			throws Throwable {
		Class[] argument_types;
		if (Stubs.matches(method, ConfRepository.class)) {
			argument_types = new Class[] { String.class };
			if (Stubs.matches(method, argument_types)) {
				return (ConfRepository) Stubs
						.makeStubObject(ConfRepository.class);
			}
			argument_types = new Class[] {};
			if (Stubs.matches(method, "getConfiguration", argument_types)) {
				return null;
			}
		}
		if ("com.taifook.mcs.msg.MessageSender".equals(method
				.getDeclaringClass().getName())) {
			argument_types = new Class[] {};
			if (Stubs.matches(method, "loadConf", argument_types)) {
				return Stubs.VOID;
			}
			argument_types = new Class[] { MCSMessage.class, String.class };
			if (Stubs.matches(method, "sendRequest", argument_types)) {
				return (MCSMessage) Stubs.makeStubObject(MCSMessage.class);
			}
		}
		if (Stubs.matches(method, ModelUtil.class)) {
			argument_types = new Class[] { TradeListRequestModel.class };
			if (Stubs.matches(method, "assembleTradeListRequest",
					argument_types)) {
				return _fTradeListRequest;
			}
		}
		return Stubs.NO_STUB_GENERATED;
	}

	/**
	 * 针对方法的测试: callTradeList(com.itsz.sht.common.model.request.TradeListRequestModel)
	 * @throws Throwable 测试可能抛出 Throwable
	 * @see McsServiceImpl#callTradeList(com.itsz.sht.common.model.request.TradeListRequestModel)
	 * @author Parasoft Jtest 9.0
	 */
	@Test
	public void testCallTradeList9() throws Throwable {
		
		MessageSender messageSender = new MessageSender();
		TradeListRequestModel requestModel = new TradeListRequestModel();
		_fException01 = new Exception01();
		
		requestModel.setPageSize("0");
		requestModel.setPageNo("1");
		requestModel.setLoginId("loginId9");
		requestModel.setAccountId("0");
		requestModel.setInstrCode("instrCode9");
		requestModel.setOrderSide("orderSide9");
		requestModel.setChannel("channel9");
		requestModel.setFromDate("fromDate9");
		requestModel.setToDate("toDate9");
		requestModel.setFromIdx("fromIdx9");
		requestModel.setToIdx("toIdx9");
		requestModel.setLanguage("language9");
		TradeListResponseModel result = this.mcsService
				.callTradeList(requestModel);
		// com.itsz.sht.exception.ITSZException thrown
		// at com.itsz.sht.service.mcs.McsServiceImpl.callTradeList(McsServiceImpl.java:1202)
		// jtest_unverified
	}

	/**
	 * 指定当运行 testCallTradeList9 要使用的桩.
	 * @param method 被调用的方法或构造方法
	 * @param _this 对应实例对象的方法或
	 *        <code>null</code> 静态方法
	 * @param args 传递到方法的参数
	 * @return 要使用的桩返回值或 <code>Stubs.NO_STUB_GENERATED</code>
	 *        指定不要打桩的方法调用.
	 * @throws Throwable Stubs may throw any Throwable
	 * @author Parasoft Jtest 9.0
	 */
	public Object stubsCallTradeList9(Member method, Object _this, Object[] args)
			throws Throwable {
		Class[] argument_types;
		if (Stubs.matches(method, ConfRepository.class)) {
			argument_types = new Class[] { String.class };
			if (Stubs.matches(method, argument_types)) {
				return (ConfRepository) Stubs
						.makeStubObject(ConfRepository.class);
			}
			argument_types = new Class[] {};
			if (Stubs.matches(method, "getConfiguration", argument_types)) {
				return null;
			}
		}
		if (Stubs.matches(method, MCSMessage.class)) {
			argument_types = new Class[] { String.class };
			if (Stubs.matches(method, "setMessageId", argument_types)) {
				return Stubs.VOID;
			}
		}
		if ("com.taifook.mcs.msg.MessageSender".equals(method
				.getDeclaringClass().getName())) {
			argument_types = new Class[] {};
			if (Stubs.matches(method, "loadConf", argument_types)) {
				return Stubs.VOID;
			}
			argument_types = new Class[] { MCSMessage.class, String.class };
			if (Stubs.matches(method, "sendRequest", argument_types)) {
				return _fException01;
			}
		}
		if (Stubs.matches(method, PropertyUtils.class)) {
			argument_types = new Class[] { Object.class, Object.class };
			if (Stubs.matches(method, "copyProperties", argument_types)) {
				return Stubs.VOID;
			}
		}
		return Stubs.NO_STUB_GENERATED;
	}

	/**
	 * 针对方法的测试: callTradeList(com.itsz.sht.common.model.request.TradeListRequestModel)
	 * @throws Throwable 测试可能抛出 Throwable
	 * @see McsServiceImpl#callTradeList(com.itsz.sht.common.model.request.TradeListRequestModel)
	 * @author Parasoft Jtest 9.0
	 */
	@Test
	public void testCallTradeList11() throws Throwable {
		
		MessageSender messageSender = new MessageSender();
		TradeListRequestModel requestModel = new TradeListRequestModel();
		_fTradeListResponse = new TradeListResponse();
		
		TradeListResponseModel result = this.mcsService
				.callTradeList(requestModel);
		assertNotNull(result); // jtest_unverified
		assertEquals(0, result.getPageSize()); // jtest_unverified
		assertEquals(0, result.getListSize()); // jtest_unverified
		assertEquals(0, result.getPageAmount()); // jtest_unverified
		assertEquals(0, result.getPageNo()); // jtest_unverified
		assertEquals(0, result.getNextPage()); // jtest_unverified
		assertEquals(0, result.getPrevPage()); // jtest_unverified
		assertEquals(0, result.getCurrentPage()); // jtest_unverified
		assertEquals(0, result.getTotalPage()); // jtest_unverified
		// No exception thrown
		// jtest_unverified
	}

	/**
	 * 指定当运行 testCallTradeList11 要使用的桩.
	 * @param method 被调用的方法或构造方法
	 * @param _this 对应实例对象的方法或
	 *        <code>null</code> 静态方法
	 * @param args 传递到方法的参数
	 * @return 要使用的桩返回值或 <code>Stubs.NO_STUB_GENERATED</code>
	 *        指定不要打桩的方法调用.
	 * @throws Throwable Stubs may throw any Throwable
	 * @author Parasoft Jtest 9.0
	 */
	public Object stubsCallTradeList11(Member method, Object _this,
			Object[] args) throws Throwable {
		Class[] argument_types;
		if (Stubs.matches(method, ConfRepository.class)) {
			argument_types = new Class[] { String.class };
			if (Stubs.matches(method, argument_types)) {
				return (ConfRepository) Stubs
						.makeStubObject(ConfRepository.class);
			}
			argument_types = new Class[] {};
			if (Stubs.matches(method, "getConfiguration", argument_types)) {
				return null;
			}
		}
		if (Stubs.matches(method, MCSMessage.class)) {
			argument_types = new Class[] { String.class };
			if (Stubs.matches(method, "setMessageId", argument_types)) {
				return Stubs.VOID;
			}
		}
		if ("com.taifook.mcs.msg.MessageSender".equals(method
				.getDeclaringClass().getName())) {
			argument_types = new Class[] {};
			if (Stubs.matches(method, "loadConf", argument_types)) {
				return Stubs.VOID;
			}
			argument_types = new Class[] { MCSMessage.class, String.class };
			if (Stubs.matches(method, "sendRequest", argument_types)) {
				return _fTradeListResponse;
			}
		}
		if (Stubs.matches(method, PropertyUtils.class)) {
			argument_types = new Class[] { Object.class, Object.class };
			if (Stubs.matches(method, "copyProperties", argument_types)) {
				return Stubs.VOID;
			}
		}
		return Stubs.NO_STUB_GENERATED;
	}

	/**
	 * 针对方法的测试: callVerifyPassword(com.itsz.sht.common.model.request.VerifyPasswordRequestModel)
	 * @throws Throwable 测试可能抛出 Throwable
	 * @see McsServiceImpl#callVerifyPassword(com.itsz.sht.common.model.request.VerifyPasswordRequestModel)
	 * @author Parasoft Jtest 9.0
	 */
	@Test
	public void testCallVerifyPassword1() throws Throwable {
		
		VerifyPasswordRequestModel requestModel = new VerifyPasswordRequestModel();
		requestModel.setLoginId("loginId5");
		requestModel.setPassword("password5");
		VerifyPasswordResponseModel result = this.mcsService
				.callVerifyPassword(requestModel);
		// com.itsz.sht.exception.ITSZException thrown
		// at com.itsz.sht.service.mcs.McsServiceImpl.callVerifyPassword(McsServiceImpl.java:1127)
		// jtest_unverified
	}

	/**
	 * 针对方法的测试: fundTransfer(com.itsz.sht.common.model.response.FundTransferRequestModel)
	 * @throws Throwable 测试可能抛出 Throwable
	 * @see McsServiceImpl#fundTransfer(com.itsz.sht.common.model.response.FundTransferRequestModel)
	 * @author Parasoft Jtest 9.0
	 */
	@Test
	public void testFundTransfer1() throws Throwable {
		
		FundTransferRequestModel requestModel = new FundTransferRequestModel();
		BigDecimal amount = new BigDecimal("2.3e-4");
		
		requestModel.setBank(false);
		requestModel.setBankCode("bankCode4");
		requestModel.setLoginId("loginId4");
		requestModel.setPassword("passwd4");
		requestModel.setAmount(amount);
		requestModel.setFromAccountId("fromAccountId4");
		requestModel.setToCcy("toCcy4");
		requestModel.setFromCcy("fromCcy4");
		requestModel.setToAccountId("toAccountId4");
		FundTransferResponseModel result = this.mcsService
				.fundTransfer(requestModel);
		// com.itsz.sht.exception.ITSZException thrown
		// at com.itsz.sht.service.mcs.McsServiceImpl.fundTransfer(McsServiceImpl.java:1217)
		// jtest_unverified
	}

	/**
	 * 针对方法的测试: getInsufficientAmount(java.math.BigDecimal,java.math.BigDecimal)
	 * @throws Throwable 测试可能抛出 Throwable
	 * @see McsServiceImpl#getInsufficientAmount(java.math.BigDecimal,java.math.BigDecimal)
	 * @author Parasoft Jtest 9.0
	 */
	@Test
	public void testGetInsufficientAmount1() throws Throwable {
		
		BigDecimal netAmount = new BigDecimal("1.0");
		BigDecimal mos = new BigDecimal("1.0");
		BigDecimal result = this.mcsService.getInsufficientAmount(netAmount, mos);
		assertNotNull(result); // jtest_unverified
		assertEquals("-1", result.toString()); // jtest_unverified
		// No exception thrown
		// jtest_unverified
	}

	/**
	 * 针对方法的测试: getInsufficientAmount(java.math.BigDecimal,java.math.BigDecimal)
	 * @throws Throwable 测试可能抛出 Throwable
	 * @see McsServiceImpl#getInsufficientAmount(java.math.BigDecimal,java.math.BigDecimal)
	 * @author Parasoft Jtest 9.0
	 */
	@Test
	public void testGetInsufficientAmount3() throws Throwable {
		
		BigDecimal mos = new BigDecimal("1.0");
		BigDecimal netAmount = new BigDecimal("2.0");
		BigDecimal result = this.mcsService.getInsufficientAmount(netAmount, mos);
		assertNotNull(result); // jtest_unverified
		assertEquals("1", result.toString()); // jtest_unverified
		// No exception thrown
		// jtest_unverified
	}

	/**
	 * 针对方法的测试: getInsufficientAmount(java.math.BigDecimal,java.math.BigDecimal)
	 * @throws Throwable 测试可能抛出 Throwable
	 * @see McsServiceImpl#getInsufficientAmount(java.math.BigDecimal,java.math.BigDecimal)
	 * @author Parasoft Jtest 9.0
	 */
	@Test
	public void testGetInsufficientAmount4() throws Throwable {
		
		BigDecimal mos = new BigDecimal("-1.0");
		BigDecimal netAmount = new BigDecimal("0.0");
		BigDecimal result = this.mcsService.getInsufficientAmount(netAmount, mos);
		assertEquals(netAmount, result); // jtest_unverified
		// No exception thrown
		// jtest_unverified
	}

	/**
	 * 针对方法的测试: getModifyOrderDetail(com.taifook.mcs.core.beans.msg.OrderListRequest)
	 * @throws Throwable 测试可能抛出 Throwable
	 * @see McsServiceImpl#getModifyOrderDetail(com.taifook.mcs.core.beans.msg.OrderListRequest)
	 * @author Parasoft Jtest 9.0
	 */
	@Test
	public void testGetModifyOrderDetail1() throws Throwable {
		
		OrderListRequest requestModel = new OrderListRequest();
		
		OrderInfo result = this.mcsService.getModifyOrderDetail(requestModel);
		// com.itsz.sht.exception.ITSZException thrown
		// at com.itsz.sht.service.mcs.McsServiceImpl.getModifyOrderDetail(McsServiceImpl.java:726)
		// jtest_unverified
	}

	/**
	 * 针对方法的测试: getPPSRecords(com.itsz.sht.common.model.request.PPSEnquiryRequestModel)
	 * @throws Throwable 测试可能抛出 Throwable
	 * @see McsServiceImpl#getPPSRecords(com.itsz.sht.common.model.request.PPSEnquiryRequestModel)
	 * @author Parasoft Jtest 9.0
	 */
	@Test
	public void testGetPPSRecords1() throws Throwable {
		
		PPSEnquiryRequestModel requestModel = new PPSEnquiryRequestModel();
		
		PPSEnquiryResponseModel result = this.mcsService
				.getPPSRecords(requestModel);
		// com.itsz.sht.exception.ITSZException thrown
		// at com.itsz.sht.service.mcs.McsServiceImpl.getPPSRecords(McsServiceImpl.java:1353)
		// jtest_unverified
	}

	/**
	 * 针对方法的测试: McsServiceImpl()
	 * @throws Throwable 测试可能抛出 Throwable
	 * @see McsServiceImpl#McsServiceImpl()
	 * @author Parasoft Jtest 9.0
	 */
	@Test
	public void testMcsServiceImpl1() throws Throwable {
		
		
		// No exception thrown
		// jtest_unverified
	}

	/**
	 * 针对方法的测试: withDraw(com.itsz.sht.common.model.response.FundTransferRequestModel)
	 * @throws Throwable 测试可能抛出 Throwable
	 * @see McsServiceImpl#withDraw(com.itsz.sht.common.model.response.FundTransferRequestModel)
	 * @author Parasoft Jtest 9.0
	 */
	@Test
	public void testWithDraw3() throws Throwable {
		
		FundTransferRequestModel requestModel = new FundTransferRequestModel();
		FundTransferResponseModel result = this.mcsService.withDraw(requestModel);
		// com.itsz.sht.exception.ITSZException thrown
		// at com.itsz.sht.service.mcs.McsServiceImpl.withDraw(McsServiceImpl.java:1305)
		// jtest_unverified
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
		this.mcsService=(McsService)appContext.getBean("mcsService");
	}
	
	private ApplicationContext appContext;
    private McsService mcsService;

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
			this.appContext=null;
			this.mcsService=null;
		}
	}

	/**
	 * 实用程序的主要方法.  运行在测试类中所定义的测试用例.
	 * 
	 * 用法: java McsServiceImplTest
	 * @param args 不需要命令行参数
	 * @author Parasoft Jtest 9.0
	 */
	public static void main(String[] args) {
		// junit.textui.TestRunner 将打印测试结果到 stdout.

		org.junit.runner.JUnitCore
				.main("com.itsz.sht.service.mcs.McsServiceImplTest");
	}

	/**
	 * 用来获取要测试的类对象.
	 * @return 将被测试的类
	 * @author Parasoft Jtest 9.0
	 */
	public Class getTestedClass() {
		return McsServiceImpl.class;
	}

	/**
	 * 用来在测试方法和桩方法间传递信息.
	 * @author Parasoft Jtest 9.0
	 */
	private com.taifook.mcs.core.beans.msg.BOCTransferRequest _fBOCTransferRequest;
	/**
	 * 用来在测试方法和桩方法间传递信息.
	 * @author Parasoft Jtest 9.0
	 */
	private com.taifook.mcs.core.beans.msg.ChangePasswordRequest _fChangePasswordRequest;
	/**
	 * 用来在测试方法和桩方法间传递信息.
	 * @author Parasoft Jtest 9.0
	 */
	private com.taifook.mcs.core.beans.msg.Exception01 _fException01;
	/**
	 * 用来在测试方法和桩方法间传递信息.
	 * @author Parasoft Jtest 9.0
	 */
	private com.taifook.mcs.core.beans.msg.FundTransferRequest _fFundTransferRequest;
	/**
	 * 用来在测试方法和桩方法间传递信息.
	 * @author Parasoft Jtest 9.0
	 */
	private Integer _fInteger;
	/**
	 * 用来在测试方法和桩方法间传递信息.
	 * @author Parasoft Jtest 9.0
	 */
	private com.taifook.mcs.core.beans.msg.MISAccountCashHoldingRequest _fMISAccountCashHoldingRequest;
	/**
	 * 用来在测试方法和桩方法间传递信息.
	 * @author Parasoft Jtest 9.0
	 */
	private com.taifook.mcs.core.beans.msg.MarginFinancingListRequest _fMarginFinancingListRequest;
	/**
	 * 用来在测试方法和桩方法间传递信息.
	 * @author Parasoft Jtest 9.0
	 */
	private com.taifook.mcs.core.beans.msg.PPSTransferDetailRequest _fPPSTransferDetailRequest;
	/**
	 * 用来在测试方法和桩方法间传递信息.
	 * @author Parasoft Jtest 9.0
	 */
	private com.taifook.mcs.core.beans.msg.ProfitAndLossEnquiryRequest _fProfitAndLossEnquiryRequest;
	/**
	 * 用来在测试方法和桩方法间传递信息.
	 * @author Parasoft Jtest 9.0
	 */
	private com.taifook.mcs.core.beans.msg.ProfitAndLossUpdateRequest _fProfitAndLossUpdateRequest;
	/**
	 * 用来在测试方法和桩方法间传递信息.
	 * @author Parasoft Jtest 9.0
	 */
	private com.taifook.mcs.core.beans.msg.TermsAndConditionRequest _fTermsAndConditionRequest;
	/**
	 * 用来在测试方法和桩方法间传递信息.
	 * @author Parasoft Jtest 9.0
	 */
	private com.taifook.mcs.core.beans.msg.TradeListRequest _fTradeListRequest;
	/**
	 * 用来在测试方法和桩方法间传递信息.
	 * @author Parasoft Jtest 9.0
	 */
	private com.taifook.mcs.core.beans.msg.TradeListResponse _fTradeListResponse;
	/**
	 * 用来在测试方法和桩方法间传递信息.
	 * @author Parasoft Jtest 9.0
	 */
	private com.taifook.mcs.core.beans.msg.TransactionProtectionRequest _fTransactionProtectionRequest;
	/**
	 * 用来在测试方法和桩方法间传递信息.
	 * @author Parasoft Jtest 9.0
	 */
	private com.taifook.mcs.core.beans.msg.WithDrawRequest _fWithDrawRequest;
}
// JTEST_CURRENT_ID=1428798508.