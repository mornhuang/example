package com.itsz.sht.service;


import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import com.itsz.sht.common.ServiceLocator;
import com.itsz.sht.common.model.common.request.ChangeNoEmailProductRequestModel;
import com.itsz.sht.common.model.common.request.ChangeRTQApplicationRequestModel;
import com.itsz.sht.common.model.common.request.ChangeRTQ_SHKProductRequestModel;
import com.itsz.sht.common.model.common.request.DeleteRTQAccountLastNRequestModel;
import com.itsz.sht.common.model.common.request.DeleteRTQAccountRequestModel;
import com.itsz.sht.common.model.common.request.ExecuteAutoEnrollmentRequestModel;
import com.itsz.sht.common.model.common.request.ExecuteMemoDebitRequestModel;
import com.itsz.sht.common.model.common.request.ListSelectServiceRequestModel;
import com.itsz.sht.common.model.common.request.MonthlyPurchaseEnquiryRequestModel;
import com.itsz.sht.common.model.common.request.RTQAccountAssignmentRequestModel;
import com.itsz.sht.common.model.common.request.RTQAccountRequestModel;
import com.itsz.sht.common.model.common.request.ReleaseRTQAccountAssignmentRequestModel;
import com.itsz.sht.common.model.common.request.ReserveAndRenewalEnquiryRequestModel;
import com.itsz.sht.common.model.common.request.SaveRTQAccountRequestModel;
import com.itsz.sht.common.model.common.request.ServiceProductRequestModel;
import com.itsz.sht.common.model.common.request.UpdateRTQAccountRequestModel;
import com.itsz.sht.common.model.common.request.UpdateUserProductRequestModel;
import com.itsz.sht.common.model.common.request.UserProductRequestModel;
import com.itsz.sht.common.model.common.request.UserProfileRequestModel;
import com.itsz.sht.common.model.common.response.DeleteRTQAccountLastNResponseModel;
import com.itsz.sht.common.model.common.response.DeleteRTQAccountResponseModel;
import com.itsz.sht.common.model.common.response.ExecuteAutoEnrollmentResponseModel;
import com.itsz.sht.common.model.common.response.ExecuteMemoDebitResponseModel;
import com.itsz.sht.common.model.common.response.ListRTQApplicationResponseModel;
import com.itsz.sht.common.model.common.response.ListSelectServiceResponseModel;
import com.itsz.sht.common.model.common.response.ListServiceProductResponseModel;
import com.itsz.sht.common.model.common.response.MonthlyPurchaseEnquiryResponseModel;
import com.itsz.sht.common.model.common.response.RTQAccountAssignmentResponseModel;
import com.itsz.sht.common.model.common.response.RTQAccountResponseModel;
import com.itsz.sht.common.model.common.response.RTQAccountViewResponseModel;
import com.itsz.sht.common.model.common.response.ReleaseRTQAccountAssignmentResponseModel;
import com.itsz.sht.common.model.common.response.ReserveAndRenewalEnquiryResponseModel;
import com.itsz.sht.common.model.common.response.SaveRTQAccountResponseModel;
import com.itsz.sht.common.model.common.response.ServiceProductResponseModel;
import com.itsz.sht.common.model.common.response.UpdateRTQAccountResponseModel;
import com.itsz.sht.common.model.common.response.UserProductResponseModel;
import com.itsz.sht.common.model.common.response.UserProfileResponseModel;
import com.itsz.sht.dao.hibernate.model.RtqAccountAssignment;
import com.itsz.sht.dao.hibernate.model.RtqApplication;
import com.itsz.sht.dao.hibernate.model.UserProduct;
import com.itsz.sht.dao.hibernate.model.UserProductAllotment;
import com.itsz.sht.model.MonthlyPurchaseEnquiry;
import com.itsz.sht.model.UserProductRequest;

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

	public void testfindAdminUserProfile() {
		UserProfileRequestModel model = new UserProfileRequestModel();
		model.setClientId("0077444");
		model.setChannelId("WMT01");
		model.setChannelType("WMT");
		model.setLanguage("ENG");
		UserProfileResponseModel responseModel = facade
				.findAdminUserProfile(model);
		System.out.println("responseModel======"+responseModel.getDefaultDebitAccount());
	}


	 public void testlistSelectService() {
		 ListSelectServiceRequestModel model=new ListSelectServiceRequestModel();
		 model.setClientId("0901605");
		 model.setChannelId("WMT01");
		 model.setChannelType("WMT");
		 model.setLanguage("ENG");
		 ListSelectServiceResponseModel responseModel =	 facade.listSelectService(model);
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
	
	public void  testfindUserProductStautsByClientId(){
		UserProductRequestModel model =new UserProductRequestModel();
		model.setClientId("0901605");
		UserProductResponseModel responseModel=facade.findUserProductStautsByClientId(model);
		System.out.println("responseModel======"+responseModel.getUserProductList().size());
	}	
	
	public void testupdateUserProductStatus(){		
		UserProductRequestModel model =new UserProductRequestModel();
		model.setClientId("0901605");
		UserProductResponseModel responseModel=facade.findUserProductStautsByClientId(model);
		List<UserProduct> userProductList=responseModel.getUserProductList();
		UpdateUserProductRequestModel  updateModel =new UpdateUserProductRequestModel();
		List<UserProductRequest> userProductRequestList =new ArrayList<UserProductRequest>();
		for (int i = 0; i < userProductList.size(); i++) {
			UserProductRequest userProductRequest=new UserProductRequest();
			UserProduct userProduct=(UserProduct)userProductList.get(i);
			userProductRequest.setId(userProduct.getId());
			userProductRequest.setAllwRenl(userProduct.getAllwRenl());
			userProductRequest.setStatus(userProduct.getStatus());
			userProductRequest.setUpdBy(userProduct.getUpdBy());
			userProductRequestList.add(userProductRequest);
		}		
		updateModel.setUserProductRequestList(userProductRequestList);
	 facade.updateUserProductStatus(updateModel);
	}
	
	public void testfindCancelUserProductByClientId(){
		UserProductRequestModel requestModel=new UserProductRequestModel();
		requestModel.setClientId("0901605");
		UserProductResponseModel responseModel=facade.findCancelUserProductByClientId(requestModel);
		System.out.println(responseModel.getUserProductList().size());
	}
	
	public void testupdateCancelUserProduct(){
		UserProductRequestModel requestModel=new UserProductRequestModel();
		requestModel.setClientId("0901605");
		UserProductResponseModel responseModel=facade.findCancelUserProductByClientId(requestModel);
		List<UserProduct> userProductList=responseModel.getUserProductList();		
		UpdateUserProductRequestModel  updateModel =new UpdateUserProductRequestModel();
		List<UserProductRequest> userProductRequestList =new ArrayList<UserProductRequest>();
		for (int i = 0; i < userProductList.size(); i++) {
			UserProductRequest userProductRequest=new UserProductRequest();
			UserProduct userProduct=(UserProduct)userProductList.get(i);
			userProductRequest.setId(userProduct.getId());
			userProductRequest.setAllwRenl(userProduct.getAllwRenl());
			userProductRequest.setStatus(userProduct.getStatus());
			userProductRequest.setUpdBy(userProduct.getUpdBy());
			userProductRequestList.add(userProductRequest);
		}		
		updateModel.setUserProductRequestList(userProductRequestList);
		 facade.updateCancelUserProduct(updateModel);		 
	}
	
	public void testfindRenewalOptionByClientId(){
	    UserProductRequestModel requestModel=new UserProductRequestModel();
		requestModel.setClientId("0077444");
		UserProductResponseModel responseModel=facade.findRenewalOptionByClientId(requestModel);
		System.out.println(responseModel.getUserProductList());	
	}
	
	public void  testupdateRenewalOption(){
	    UserProductRequestModel requestModel=new UserProductRequestModel();
		requestModel.setClientId("0077444");
		UserProductResponseModel responseModel=facade.findRenewalOptionByClientId(requestModel);
		List<UserProduct> userProductList=responseModel.getUserProductList();	
		UpdateUserProductRequestModel updateModel =new UpdateUserProductRequestModel();
		List<UserProductRequest> userProductRequestList =new ArrayList<UserProductRequest>();
		for (int i = 0; i < userProductList.size(); i++) {
			UserProductRequest userProductRequest=new UserProductRequest();
			UserProduct userProduct=(UserProduct)userProductList.get(i);
			userProductRequest.setId(userProduct.getId());
			userProductRequest.setAllwRenl(userProduct.getAllwRenl());
			userProductRequest.setStatus(userProduct.getStatus());
			userProductRequest.setUpdBy(userProduct.getUpdBy());
			userProductRequestList.add(userProductRequest);
		}	
		updateModel.setUserProductRequestList(userProductRequestList);
		facade.updateRenewalOption(updateModel);
	}
	

	public void  testlistServiceProduct(){
		ListServiceProductResponseModel res =facade.listServiceProduct();
		System.out.println(res.getProductList().size());
	}
 
	public void  testfindServiceProductByProductId(){
		ServiceProductRequestModel requestModel=new ServiceProductRequestModel();
		requestModel.setProductId("SSTR_AAST");
		ServiceProductResponseModel responseModel =facade.findServiceProductByProductId(requestModel);
		System.out.println(responseModel.getProduct().getProdId());	
	}
	
	public void testupdateNoEmailProduct(){
		ChangeNoEmailProductRequestModel updateModel =new ChangeNoEmailProductRequestModel();
		updateModel.setProductId("NO_EMAIL");
		updateModel.setActionBy("operator");
		updateModel.setRemarks("ffafafafa");
	 	facade.updateNoEmailProduct(updateModel);
	}
	
	public void  testupdateRTQ_SHKProduct(){
		ChangeRTQ_SHKProductRequestModel requestModel=new ChangeRTQ_SHKProductRequestModel();
		requestModel.setProductId("SHK");
		requestModel.setPriceInHKD(Long.valueOf(180));
		requestModel.setProductStatus("AVAIL");
		requestModel.setQuota(Long.valueOf(12));
		requestModel.setRemarks("sasasaa");
		facade.updateRTQ_SHKProduct(requestModel);
		
	}
	
	public void   testexeAutoEnrollment(){
		ExecuteAutoEnrollmentResponseModel responseModel= facade.exeAutoEnrollment("test-admins");
		System.out.println("responseModel======"+responseModel.getReturnCode());
	}
	
	
	public void   testexeMemoDebit(){
		ExecuteMemoDebitRequestModel requestModel= new ExecuteMemoDebitRequestModel();
		ExecuteMemoDebitResponseModel responseModel= facade.exeMemoDebit(requestModel);
		System.out.println("responseModel======"+responseModel.getReturnCode());
	}
	
//	public void testmonthlyPurchaseEnquiry(){
//		MonthlyPurchaseEnquiryRequestModel requestModel=new MonthlyPurchaseEnquiryRequestModel();
//		requestModel.setProductId("SSTR_AAST");
//		
//		MonthlyPurchaseEnquiryResponseModel responseModel=facade.monthlyPurchaseEnquiry(requestModel);
//		System.out.println("responseModel======"+responseModel.getReturnCode());
//		System.out.println("responseModel.getMonthlyPurchaseEnquiryList().size()=="+responseModel.getMonthlyPurchaseEnquiryList().size());
//		List<MonthlyPurchaseEnquiry> monthlyPurchaseEnquiryList =responseModel.getMonthlyPurchaseEnquiryList();
//		 for (int i = 0; i < monthlyPurchaseEnquiryList.size(); i++) {
//				 MonthlyPurchaseEnquiry monthlyPurchaseEnquiry=(MonthlyPurchaseEnquiry)(monthlyPurchaseEnquiryList.get(i));
//				 System.out.println(monthlyPurchaseEnquiry.getClientId());
//				 System.out.println(monthlyPurchaseEnquiry.getAllotmentTime());
//		}	
//	}
	 
//	public void testreserveAndRenewalEnquiry(){
//		ReserveAndRenewalEnquiryRequestModel requestModel=new ReserveAndRenewalEnquiryRequestModel();	
//		ReserveAndRenewalEnquiryResponseModel responseModel=facade.reserveAndRenewalEnquiry(requestModel);
//		System.out.println("responseModel======"+responseModel.getReturnCode());
//		System.out.println("responseModel.getUserProductList().size()=="+responseModel.getUserProductList().size());
//		List<UserProduct>  userProductList =responseModel.getUserProductList();
//		 for (int i = 0; i < userProductList.size(); i++) {			 
//			 UserProduct userProduct=(UserProduct)userProductList.get(i);
//			 System.out.println(userProduct.getId().getClntId());
//			 System.out.println(userProduct.getId().getProdId());			 
//		}	
//	}
	
	
	public void   testlistRTQApplication(){
		ListRTQApplicationResponseModel res=facade.listRTQApplication();
		System.out.println(res.getRtqApplication().size());
	}
	
	public void testupdateRTQApplication(){
		ListRTQApplicationResponseModel res=facade.listRTQApplication();
		List<RtqApplication> rtqApplicationList= res.getRtqApplication();
		if(rtqApplicationList.size()>0){
			RtqApplication rtqApplication =(RtqApplication)rtqApplicationList.get(0);
			ChangeRTQApplicationRequestModel requestModel=new ChangeRTQApplicationRequestModel();			
			requestModel.setProductId(rtqApplication.getProdId());
			requestModel.setrTQProvider(rtqApplication.getRtqPrdr());
			requestModel.setrTQStatus(rtqApplication.getRtqStatus());
			requestModel.setrTQUrl(rtqApplication.getRtqUrl());
		    facade.updateRTQApplication(requestModel);				
		}
	}
	
	public void testlistRTQAccountView(){
		RTQAccountViewResponseModel responseModel=facade.listRTQAccountView();
		System.out.println("responseModel=="+responseModel.getRtqAccountViewList());
	}
	
	public void testsaveRTQAccount(){
		SaveRTQAccountRequestModel requestModel=new SaveRTQAccountRequestModel();
		requestModel.setProductId("SSTR_AAST");
		requestModel.setrTQLoginIdFrom("APP001");
		requestModel.setrTQLoginIdTo("APP010");
		requestModel.setrTQLoginPassword("abc123");
		SaveRTQAccountResponseModel responseModel=facade.saveRTQAccount(requestModel);
		System.out.println("responseModel======"+responseModel.getReturnCode());
	}
	
	
	public void testupdateRTQAccount(){
		UpdateRTQAccountRequestModel requestModel=new UpdateRTQAccountRequestModel();
		requestModel.setProductId("SSTR_AAST");
		requestModel.setrTQLoginIdFrom("APP002");
		requestModel.setrTQLoginIdTo("APP008");
		requestModel.setrTQLoginPassword("abc123456");
		UpdateRTQAccountResponseModel responseModel=facade.updateRTQAccount(requestModel);
		System.out.println("responseModel======"+responseModel.getReturnCode());
	}
	
	public void testdeleteRTQAccount(){
		DeleteRTQAccountRequestModel requestModel=new DeleteRTQAccountRequestModel();
		requestModel.setProductId("SSTR_AAST");
		requestModel.setrTQLoginIdFrom("APP002");
		requestModel.setrTQLoginIdTo("APP003");
		DeleteRTQAccountResponseModel responseModel=facade.deleteRTQAccount(requestModel);
		System.out.println("responseModel======"+responseModel.getReturnCode());
	}
	
	public void testdeleteRTQAccountLastN(){
		DeleteRTQAccountLastNRequestModel requestModel=new DeleteRTQAccountLastNRequestModel();
		requestModel.setProductId("SSTR_AAST");
		requestModel.setN(new Long(2));
		DeleteRTQAccountLastNResponseModel responseModel=facade.deleteRTQAccountLastN(requestModel);
		System.out.println("responseModel======"+responseModel.getReturnCode());
	}
	
	public void testfindRTQAccountAssignmentByProductId(){
		RTQAccountAssignmentRequestModel  requestModel=new RTQAccountAssignmentRequestModel();
		requestModel.setPageNumber(Integer.valueOf(1));
		requestModel.setPageSize(Integer.valueOf(1));
		requestModel.setProductId("SSTR_AAST");
		RTQAccountAssignmentResponseModel responseModel	=facade.findRTQAccountAssignmentByProductId(requestModel);
		System.out.println("responseModel======"+responseModel.getReturnCode());
	}
	
	public void testlistRtqAccountByProIdOrLoginId(){
		RTQAccountRequestModel requestModel=new RTQAccountRequestModel();
		requestModel.setPageNumber(Integer.valueOf(1));
		requestModel.setPageSize(Integer.valueOf(1));
		requestModel.setProductId("SSTR_AAST");
		RTQAccountResponseModel responseModel=facade.listRtqAccountByProIdOrLoginId(requestModel);
		System.out.println("responseModel======"+responseModel.getReturnCode());
	}
	
	public void testreleaseRTQAccountAssignment(){
		ReleaseRTQAccountAssignmentRequestModel requestModel=new ReleaseRTQAccountAssignmentRequestModel();
		ReleaseRTQAccountAssignmentResponseModel responseModel=facade.releaseRTQAccountAssignment(requestModel);
		System.out.println("responseModel======"+responseModel.getReturnCode());
	}

	public void testgetMisDayEndProcessingFlag(){
		System.out.println(facade.getMisDayEndProcessingFlag());
	}
	
	public void testupdateMisDayEndProcessingFlag(){
		System.out.println(facade.updateMisDayEndProcessingFlag("N"));
	}
	
}
