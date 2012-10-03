package com.itsz.sht.service;



import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import junit.framework.TestCase;

import com.itsz.sht.common.Consts;
import com.itsz.sht.common.RtqServiceLocator;
import com.itsz.sht.common.model.common.request.AccessOrderNotificationRequestModel;
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
import com.itsz.sht.common.model.common.response.AccessOrderNotificationResponseModel;
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
import com.itsz.sht.dao.hibernate.model.Product;
import com.itsz.sht.dao.hibernate.model.RtqAccountAssignment;
import com.itsz.sht.dao.hibernate.model.UserProduct;
import com.itsz.sht.dao.hibernate.model.UserProductAllotment;
import com.itsz.sht.dao.hibernate.model.UserProfile;
import com.itsz.sht.dao.hibernate.model.UsrProdId;
import com.itsz.sht.model.UserProductRequest;
import com.itsz.sht.service.channels.RTQService;
import com.taifook.misgateway.TransactionMessage;

public class RtqServiceTest extends TestCase {

	private RTQService rtqService;

	protected void setUp() throws Exception {
		super.setUp();
		this.rtqService = (RTQService) RtqServiceLocator.getInstance()
				.getService("rtqService");
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		this.rtqService = null;
	}

	public void testGetRTQProductList(){
		RTQProductRequestModel rTQProductRequestModel = new RTQProductRequestModel();
		rTQProductRequestModel.setClientId("901605");
		RTQProductResponseModel rTQProductResponseModel = rtqService.getRTQProductList(rTQProductRequestModel);
		System.out.println(rTQProductResponseModel.getReturnCode());	
		if(rTQProductResponseModel.getProductList() != null){
			for(int i=0; i<rTQProductResponseModel.getProductList().size(); i++){
				System.out.println(i+" === " + ((Product)rTQProductResponseModel.getProductList().get(i)).getProdId());
			}
		}
	}
	
	public void testHavePurchasedProduct(){
		PurchasedProductRequestModel purchasedProductRequestModel = new PurchasedProductRequestModel();
		purchasedProductRequestModel.setClientId("0901605");
		purchasedProductRequestModel.setProductId("SSTR_AAST");
		PurchasedProductResponseModel purchasedProductResponseModel = rtqService.havePurchasedProduct(purchasedProductRequestModel);
		System.out.println(purchasedProductResponseModel.isHavePurchased());
	}
	
	public void testPurchaseService(){
		PurchaseServiceRequestModel model = new PurchaseServiceRequestModel();
		model.setClientId("0100567");
		model.setProductId("SHK");
		model.setChannelId("WMT01");
		model.setAllowRenewal(Consts.AdminPortal.allowRenewal.YES);
		model.setDefDebtAcc("02-0100567-30");
		model.setChannelType("WMT");
		model.setLanguage("ENG");
		PurchaseServiceResponseModel resModel = new PurchaseServiceResponseModel();
		//case1:用户产品不存在
		resModel = rtqService.purchaseService(model);
		
		//case2:用户产品已存在
//		model.setAllowRenewal(Consts.AdminPortal.allowRenewal.YES);
//		resModel = rtqService.purchaseService(model);
		System.out.println(resModel.getReturnCode());
	}
	
	public void testPurchaseServiceList(){
		List<PurchaseServiceRequestModel> modelList = new ArrayList<PurchaseServiceRequestModel>();
		PurchaseServiceRequestModel model = new PurchaseServiceRequestModel();
		model.setClientId("0000043");
		model.setProductId("SSTR_AAST_CN");
		model.setAllowRenewal(Consts.AdminPortal.allowRenewal.YES);
		model.setDefDebtAcc("02-0000043-33");
		modelList.add(model);
		
		PurchaseServiceRequestModel model2 = new PurchaseServiceRequestModel();
		model2.setClientId("0000043");
		model2.setProductId("SHK");
		model2.setAllowRenewal(Consts.AdminPortal.allowRenewal.YES);
		model2.setDefDebtAcc("02-0000043-33");
		modelList.add(model2);
		
		PurchaseServiceRequestModel model3 = new PurchaseServiceRequestModel();
		model3.setClientId("0000060");
		model3.setProductId("SSTR_AAST");
		model3.setAllowRenewal(Consts.AdminPortal.allowRenewal.YES);
		model3.setDefDebtAcc("02-0000060-22");
		modelList.add(model3);
		
		PurchaseServiceRequestModel model4 = new PurchaseServiceRequestModel();
		model4.setClientId("0000063");
		model4.setProductId("SSTR_IQS");
		model4.setAllowRenewal(Consts.AdminPortal.allowRenewal.YES);
		model4.setDefDebtAcc("02-0000063-00");
		modelList.add(model4);
		
		for(PurchaseServiceRequestModel md: modelList){
			PurchaseServiceResponseModel resModel = new PurchaseServiceResponseModel();
			resModel = rtqService.purchaseService(md);
			System.out.println(md.getClientId()+ "," + md.getProductId() + ": " + resModel.getReturnCode());
		}
	}
	
	public void testUpdateUserProductStatus(){
		UpdateUserProductRequestModel updateUserProductRequestModel = new UpdateUserProductRequestModel();
		UserProductRequest userProductRequest = new UserProductRequest();
		userProductRequest.setId(new UsrProdId("0901605","SSTR_AAST"));
		userProductRequest.setAllwRenl(Consts.AdminPortal.allowRenewal.NO);
		userProductRequest.setUpdBy("0901605");
		List<UserProductRequest> list = new ArrayList<UserProductRequest>();
		list.add(userProductRequest);
		updateUserProductRequestModel.setUserProductRequestList(list);
		updateUserProductRequestModel.setChannelId("WMT01");
		updateUserProductRequestModel.setChannelType("WMT");
		updateUserProductRequestModel.setLanguage("ENG");
		UserProductResponseModel resModel = new UserProductResponseModel();
		resModel = rtqService.updateUserProductStatus(updateUserProductRequestModel);
		System.out.println(resModel.getReturnCode());
	}
	
	public void testReserveService(){
		ReserveServiceRequestModel model = new ReserveServiceRequestModel();
		model.setClientId("0131388");
		model.setProductId("SSTR_IQS");
		model.setChannelId("WMT01");
		model.setAllowRenewal(Consts.AdminPortal.allowRenewal.NO);
		model.setDefDebtAcc("02-0131388-30");
		model.setChannelType("WMT");
		model.setLanguage("ENG");
		ReserveServiceResponseModel resModel = new ReserveServiceResponseModel();
		resModel = rtqService.reserveService(model);
		System.out.println(resModel.getReturnCode());
	}
	
	public void testReserveServiceList(){
		List<ReserveServiceRequestModel> modelList = new ArrayList<ReserveServiceRequestModel>();
		ReserveServiceRequestModel model = new ReserveServiceRequestModel();
		model.setClientId("0000043");
		model.setProductId("SSTR_IQS");
		model.setAllowRenewal(Consts.AdminPortal.allowRenewal.YES);
		model.setDefDebtAcc("02-0000043-33");
		modelList.add(model);
		
		ReserveServiceRequestModel model1 = new ReserveServiceRequestModel();
		model1.setClientId("0000116");
		model1.setProductId("SSTR_AAST");
		model1.setAllowRenewal(Consts.AdminPortal.allowRenewal.YES);
		model1.setDefDebtAcc("02-0000116-22");
		modelList.add(model1);
		
		ReserveServiceRequestModel model2 = new ReserveServiceRequestModel();
		model2.setClientId("0000060");
		model2.setProductId("SSTR_AAST_CN");
		model2.setAllowRenewal(Consts.AdminPortal.allowRenewal.NO);
		model2.setDefDebtAcc("02-0000060-22");
		modelList.add(model2);
				
		for(ReserveServiceRequestModel rm : modelList){
			ReserveServiceResponseModel resModel = new ReserveServiceResponseModel();
			resModel = rtqService.reserveService(rm);
			System.out.println(rm.getClientId() + "," + rm.getProductId() + ": " +resModel.getReturnCode());
		}
	}
	
	public void testCancelReservedUserProduct(){
		UpdateUserProductRequestModel updateUserProductRequestModel = new UpdateUserProductRequestModel();
		UserProductRequest userProductRequest = new UserProductRequest();
		userProductRequest.setId(new UsrProdId("0901605","SSTR_AAST"));
		userProductRequest.setUpdBy("0901605");
		List<UserProductRequest> list = new ArrayList<UserProductRequest>();
		list.add(userProductRequest);
		updateUserProductRequestModel.setUserProductRequestList(list);
		updateUserProductRequestModel.setChannelId("WMT01");
		updateUserProductRequestModel.setChannelType("WMT");
		updateUserProductRequestModel.setLanguage("ENG");
		UserProductResponseModel resModel = new UserProductResponseModel();
		resModel = rtqService.cancelReservedUserProduct(updateUserProductRequestModel);
		System.out.println(resModel.getReturnCode());
	}
	
	public void testGetUserNotificationEmail(){
		UserNotificationEmailRequestModel userNotificationEmailRequestModel = new UserNotificationEmailRequestModel();
		userNotificationEmailRequestModel.setClientId("0901605");
		UserNotificationEmailResponseModel userNotificationEmailResponseModel = rtqService.getUserNotificationEmail(userNotificationEmailRequestModel);
		System.out.println(userNotificationEmailResponseModel.getUserNotificationEmail());
		System.out.println(userNotificationEmailResponseModel.isNotiFlag());
	}
	
	public void testUpdateUserNotificationEmail(){
		UserNotificationEmailRequestModel userNotificationEmailRequestModel = new UserNotificationEmailRequestModel();
		userNotificationEmailRequestModel.setClientId("0901605");
		userNotificationEmailRequestModel.setUserNotificationEmail("0901605-23@itsz.com");//clnt_0077444attaifookdotcom
		userNotificationEmailRequestModel.setLanguage("BIG5");
		userNotificationEmailRequestModel.setNotiFlag(true);
		UserNotificationEmailResponseModel userNotificationEmailResponseModel = rtqService.updateUserNotificationEmail(userNotificationEmailRequestModel);
		System.out.println(userNotificationEmailResponseModel.getReturnCode());
	}
		
	public void testfindClientUserProfile() {
		UserProfileRequestModel model = new UserProfileRequestModel();
		model.setClientId("0087001");
		model.setChannelId("WMT01");
		model.setChannelType("WMT");
		model.setLanguage("ENG");
		UserProfileResponseModel responseModel = rtqService
				.findClientUserProfile(model);
		System.out.println("responseModel.getChinaDiscountFlag()"
				+ responseModel.getChinaDiscountFlag());
		System.out.println(responseModel.getDefaultDebitAccount());
		//assertEquals(responseModel.getDefaultDebitAccount(), "a");
	}



	public void testAccessRTQ() {
		AccessRTQRequestModel requestModel = new AccessRTQRequestModel();
		requestModel.setClientId("0000054");
		requestModel.setClientIp("127.0.0.1");
		AccessRTQResponseModel responseModel = rtqService.accessRTQ(requestModel);
		System.out.println(responseModel.getRtqAccess()!=null?responseModel.getRtqAccess().getRtqUrl():"");
	}
	
	public void testAccessSHK() {
		AccessSHKRequestModel requestModel = new AccessSHKRequestModel();
		requestModel.setClientId("0901605");
		requestModel.setProductId("SHK");
		AccessSHKResponseModel responseModel = rtqService.accessSHK(requestModel);
		System.out.println(responseModel.getRtqStatus());
	}
	
	public void testAccessOrderNotification() {
		AccessOrderNotificationRequestModel requestModel = new AccessOrderNotificationRequestModel();
		requestModel.setClientId("0901605");
		requestModel.setProductId("NO_EMAIL");
		AccessOrderNotificationResponseModel responseModel = rtqService.accessOrderNotification(requestModel);
		System.out.println(responseModel.getReturnCode());
	}

	 public void testlistSelectService() {
		 ListSelectServiceRequestModel model=new ListSelectServiceRequestModel();
		 model.setClientId("0901605");
		 model.setChannelId("WMT01");
		 model.setChannelType("WMT");
		 model.setLanguage("ENG");
		 ListSelectServiceResponseModel responseModel =	 rtqService.listSelectService(model);
		 List<UserProduct> userProductList =responseModel.getExistingServiceProductList();
		 System.out.println("userProductList=== "+userProductList.size());
		 for (int i = 0; i < userProductList.size(); i++) {
			 UserProduct userProduct=(UserProduct)userProductList.get(i);
			 System.out.println( userProduct.getId().getClntId());
			 System.out.println( userProduct.getId().getProdId());
		}
		 
		  List<RtqAccountAssignment> rtqAccountAssignmentList=  responseModel.getRtqAccountAssignmentList();
		  System.out.println("rtqAccountAssignmentList=== "+rtqAccountAssignmentList.size());
		  for (int i = 0; i < rtqAccountAssignmentList.size(); i++) {
			  RtqAccountAssignment rtqAccountAssignment= (RtqAccountAssignment)rtqAccountAssignmentList.get(i);
			  System.out.println(rtqAccountAssignment.getId().getClntId());
			  System.out.println(rtqAccountAssignment.getId().getProdId());
			  System.out.println(rtqAccountAssignment.getId().getRtqLognId());
		}
		  
		  List<UserProductAllotment> userProductAllotmentList= responseModel.getSubscriberServiceProductList();
	//	  System.out.println("userProductAllotmentList=== "+userProductAllotmentList.size());
		  for (int i = 0; i < userProductAllotmentList.size(); i++) {
			  UserProductAllotment userProductAllotment =(UserProductAllotment)userProductAllotmentList.get(i);
			  System.out.println(userProductAllotment.getClntId());
			  System.out.println(userProductAllotment.getProdId());
			  System.out.println(userProductAllotment.getAlltStatus());
		  }
		  
		 }
	 
	 public void testPurchaseAndReserveAllUser(){  //为测试批量自动续期与购买，模拟相关数据
		 List<UserProfile> upList = rtqService.getAllUserProfile();
//		 upList = upList.subList(0, 10);
		 int i = 0;
		 for(UserProfile up : upList){
			i++;
			PurchaseServiceRequestModel model = new PurchaseServiceRequestModel();
			model.setClientId(up.getClntId());
			model.setProductId("SHK");
			model.setAllowRenewal(Consts.AdminPortal.allowRenewal.YES);
			model.setDefDebtAcc(up.getDefDebtAcc());
			PurchaseServiceResponseModel resModel = rtqService.purchaseService(model);
			System.out.println("purchase SHK " + up.getClntId() + ":  " + resModel.getReturnCode());
			
			
			if(random("").equals(Consts.AdminPortal.allowRenewal.YES)){
				model.setClientId(up.getClntId());
				model.setProductId(random("product"));
				model.setAllowRenewal(Consts.AdminPortal.allowRenewal.YES);
				model.setDefDebtAcc(up.getDefDebtAcc());
				resModel = rtqService.purchaseService(model);
				System.out.println("purchase " + up.getClntId() + ":  " + resModel.getReturnCode());
			}
			
			if(random("").equals(Consts.AdminPortal.allowRenewal.YES)){
				ReserveServiceRequestModel model2 = new ReserveServiceRequestModel();
				model2.setClientId(up.getClntId());
				model2.setProductId(random("product"));
				model2.setAllowRenewal(random(""));
				model2.setDefDebtAcc(up.getDefDebtAcc());
				ReserveServiceResponseModel resModel2 = new ReserveServiceResponseModel();
				resModel2 = rtqService.reserveService(model2);
				System.out.println("reserve " + up.getClntId() + ":  " + resModel2.getReturnCode());
			}
		 }
		 System.out.println(i);
	 }
	
	 private String random(String flag){
		 Random random = new Random();
		 if(flag.equals("product")){
			 switch(random.nextInt(4)){
			 case 0: return "SSTR_AAST";
			 case 1: return "SSTR_IQS";
			 case 2: return "SSTR_AAST_CN";
			 case 3: return "SHK";
			 }
		 }else{
			 switch(random.nextInt(2)){
			 case 0: return Consts.AdminPortal.allowRenewal.YES;
			 case 1: return Consts.AdminPortal.allowRenewal.NO;
			 }
		 }
		 return null;
	 }
	 
}
