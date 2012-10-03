package com.itsz.sht.service;

import java.util.List;

import com.itsz.common.util.StringMap;
import com.itsz.sht.common.model.common.request.AddProductRequestModel;
import com.itsz.sht.common.model.common.request.AutoPurchaseRequestModel;
import com.itsz.sht.common.model.common.request.ChangeNoEmailProductRequestModel;
import com.itsz.sht.common.model.common.request.ChangeRTQApplicationRequestModel;
import com.itsz.sht.common.model.common.request.ChangeRTQ_SHKProductRequestModel;
import com.itsz.sht.common.model.common.request.DeleteRTQAccountLastNRequestModel;
import com.itsz.sht.common.model.common.request.DeleteRTQAccountRequestModel;
import com.itsz.sht.common.model.common.request.ExecuteMemoDebitRequestModel;
import com.itsz.sht.common.model.common.request.ListRtqAccountAssRequestModel;
import com.itsz.sht.common.model.common.request.ListSelectServiceRequestModel;
import com.itsz.sht.common.model.common.request.ListUserPaymentByManualRequestModel;
import com.itsz.sht.common.model.common.request.MonthlyPurchaseEnquiryRequestModel;
import com.itsz.sht.common.model.common.request.NotificationMediaListRequestModel;
import com.itsz.sht.common.model.common.request.ProductRequestModel;
import com.itsz.sht.common.model.common.request.RTQAccountAssignmentRequestModel;
import com.itsz.sht.common.model.common.request.RTQAccountRequestModel;
import com.itsz.sht.common.model.common.request.RTQApplicationResquestModel;
import com.itsz.sht.common.model.common.request.ReleaseRTQAccountAssignmentRequestModel;
import com.itsz.sht.common.model.common.request.ReserveAndRenewalEnquiryRequestModel;
import com.itsz.sht.common.model.common.request.ReserveServiceRequestModel;
import com.itsz.sht.common.model.common.request.ReserveUserProductRequestModel;
import com.itsz.sht.common.model.common.request.ServicesAccessLogByRtqAccAsgnIdRequestModel;
import com.itsz.sht.common.model.common.request.SaveRTQAccountRequestModel;
import com.itsz.sht.common.model.common.request.ServiceProductRequestModel;
import com.itsz.sht.common.model.common.request.UpdateRTQAccountRequestModel;
import com.itsz.sht.common.model.common.request.UpdateUserPaymentByManualRequestModel;
import com.itsz.sht.common.model.common.request.UpdateUserProductRequestModel;
import com.itsz.sht.common.model.common.request.UserProductRequestModel;
import com.itsz.sht.common.model.common.request.UserProfileRequestModel;
import com.itsz.sht.common.model.common.response.AclRoleResponseModel;
import com.itsz.sht.common.model.common.response.AclRoleResponseModelList;
import com.itsz.sht.common.model.common.response.AclUserResponseModel;
import com.itsz.sht.common.model.common.response.AclUserResponseModelList;
import com.itsz.sht.common.model.common.response.AddProductResponseModel;
import com.itsz.sht.common.model.common.response.AutoPurchaseResponseModel;
import com.itsz.sht.common.model.common.response.ChangeProductResponseModel;
import com.itsz.sht.common.model.common.response.ChangeRTQApplicationResponseModel;
import com.itsz.sht.common.model.common.response.DeleteRTQAccountLastNResponseModel;
import com.itsz.sht.common.model.common.response.DeleteRTQAccountResponseModel;
import com.itsz.sht.common.model.common.response.ExecuteAutoEnrollmentResponseModel;
import com.itsz.sht.common.model.common.response.ExecuteMemoDebitResponseModel;
import com.itsz.sht.common.model.common.response.ListRTQApplicationResponseModel;
import com.itsz.sht.common.model.common.response.ListRtqAccountAssResponseModel;
import com.itsz.sht.common.model.common.response.ListSelectServiceResponseModel;
import com.itsz.sht.common.model.common.response.ListServiceProductResponseModel;
import com.itsz.sht.common.model.common.response.ListUserPaymentByManualResponseModel;
import com.itsz.sht.common.model.common.response.MonthlyPurchaseEnquiryResponseModel;
import com.itsz.sht.common.model.common.response.NotificationMediaListResponseModel;
import com.itsz.sht.common.model.common.response.ProductResponseModel;
import com.itsz.sht.common.model.common.response.RTQAccountAssignmentResponseModel;
import com.itsz.sht.common.model.common.response.RTQAccountResponseModel;
import com.itsz.sht.common.model.common.response.RTQAccountViewResponseModel;
import com.itsz.sht.common.model.common.response.RTQApplicationResponseModel;
import com.itsz.sht.common.model.common.response.ReleaseRTQAccountAssignmentResponseModel;
import com.itsz.sht.common.model.common.response.ReserveAndRenewalEnquiryResponseModel;
import com.itsz.sht.common.model.common.response.ReserveServiceResponseModel;
import com.itsz.sht.common.model.common.response.ReserveUserProductResponseModel;
import com.itsz.sht.common.model.common.response.ServicesAccessLogByRtqAccAsgnIdResponseModel;
import com.itsz.sht.common.model.common.response.SaveRTQAccountResponseModel;
import com.itsz.sht.common.model.common.response.ServiceProductResponseModel;
import com.itsz.sht.common.model.common.response.UpdateRTQAccountResponseModel;
import com.itsz.sht.common.model.common.response.UpdateUserPaymentByManualResponseModel;
import com.itsz.sht.common.model.common.response.UserProductResponseModel;
import com.itsz.sht.common.model.common.response.UserProfileResponseModel;
import com.itsz.sht.common.model.common.util.LogUtil;
import com.itsz.sht.dao.hibernate.model.AclRole;
import com.itsz.sht.dao.hibernate.model.AclUserProfile;
import com.itsz.sht.dao.hibernate.model.Product;
import com.itsz.sht.dao.hibernate.model.UserProductAllotment;
import com.itsz.sht.service.channels.BusinessTemplate;

public class FacadeImpl implements Facade{
	private BusinessTemplate businessTemplate;	
	
	public void setBusinessTemplate(BusinessTemplate businessTemplate) {
		this.businessTemplate = businessTemplate;
	} 
	
	@Override
	public DeleteRTQAccountResponseModel deleteRTQAccount(
			DeleteRTQAccountRequestModel deleteRTQAccountRequestModel) {
		LogUtil.logDTO("Delete RTQ Account Request: ", deleteRTQAccountRequestModel);
		DeleteRTQAccountResponseModel responseModel=businessTemplate.deleteRTQAccount(deleteRTQAccountRequestModel);
		LogUtil.logDTO("Delete RTQ Account Response: ", responseModel); 
		return responseModel; 
	}

	@Override
	public DeleteRTQAccountLastNResponseModel deleteRTQAccountLastN(
			DeleteRTQAccountLastNRequestModel deleteRTQAccountLastNRequestModel) {
		LogUtil.logDTO("Delete RTQ Account LastN Request: ", deleteRTQAccountLastNRequestModel);
		DeleteRTQAccountLastNResponseModel responseModel= businessTemplate.deleteRTQAccountLastN(deleteRTQAccountLastNRequestModel);
		LogUtil.logDTO("Delete RTQ Account LastN Response: ", responseModel); 
		return responseModel;
	}

	@Override
	public ExecuteMemoDebitResponseModel exeMemoDebit(
			ExecuteMemoDebitRequestModel executeMemoDebitRequestModel) {
		LogUtil.logDTO("Execute MemoDebit Request: ", executeMemoDebitRequestModel);
		ExecuteMemoDebitResponseModel responseModel= businessTemplate.exeMemoDebit(executeMemoDebitRequestModel);
		LogUtil.logDTO("Execute MemoDebit Response: ", responseModel);  
		return responseModel;
	}

	public List<UserProductAllotment> findMemoDebitUserProductAllotment(){
		return businessTemplate.findMemoDebitUserProductAllotment();
	}
	
	@Override
	public ExecuteAutoEnrollmentResponseModel exeAutoEnrollment(
			String  actionBy) {
	//	LogUtil.logDTO(" Execute AutoEnrollment Request: ", executeAutoEnrollmentRequestModel);
		ExecuteAutoEnrollmentResponseModel responseModel= businessTemplate.executeAutoEnrollment(actionBy);
		LogUtil.logDTO("Execute AutoEnrollment Response: ", responseModel); 
		return responseModel;
	}

	@Override
	public UserProductResponseModel findCancelUserProductByClientId(
			UserProductRequestModel userProductRequestModel) {
		LogUtil.logDTO("find Cancel User Product By ClientId Request: ", userProductRequestModel);
		UserProductResponseModel responseModel= businessTemplate.findCancelUserProductByClientId(userProductRequestModel);
		LogUtil.logDTO("find Cancel User Product By ClientId Response: ", responseModel);
		return responseModel;
	}

	@Override
	public UserProductResponseModel findRenewalOptionByClientId(UserProductRequestModel userProductRequestModel) {
		LogUtil.logDTO("find Renewal Option By ClientId Request: ", userProductRequestModel);
		UserProductResponseModel responseModel= businessTemplate.findRenewalOptionByClientId(userProductRequestModel);
		LogUtil.logDTO("find Renewal Option By ClientId Response: ", responseModel);
		return responseModel;
	}

	@Override
	public UserProductResponseModel findUserProductStautsByClientId(
			UserProductRequestModel userProductRequestModel) {
		LogUtil.logDTO("find User Product Stauts By ClientId Request: ", userProductRequestModel);
		UserProductResponseModel responseModel= businessTemplate.findUserProductStautsByClientId(userProductRequestModel);
		LogUtil.logDTO("find User Product Stauts By ClientId Response: ", responseModel);
		return responseModel;
	}





	@Override
	public RTQAccountViewResponseModel listRTQAccountView() {
		LogUtil.logDTO("list RTQ Account View Request: ", "listRTQAccountView");
		RTQAccountViewResponseModel responseModel= businessTemplate.listRTQAccountView();
		LogUtil.logDTO("list RTQ Account View Response: ", responseModel);
		return responseModel;
		
		
	}

	@Override
	public ListRTQApplicationResponseModel listRTQApplication() {		
		LogUtil.logDTO("List RTQ Application Request: ", "listRTQApplication");
		ListRTQApplicationResponseModel responseModel= businessTemplate.listRTQApplication();
		LogUtil.logDTO("List RTQ Application Response: ", responseModel);
		return responseModel;
	}

	@Override
	public ListSelectServiceResponseModel listSelectService(
			ListSelectServiceRequestModel listSelectServiceRequestModel) {
		LogUtil.logDTO("List Select Service Request: ", listSelectServiceRequestModel);
		ListSelectServiceResponseModel responseModel= businessTemplate.listSelectService(listSelectServiceRequestModel);
		LogUtil.logDTO("List Select Service Response: ", responseModel);
		return responseModel;
	}

	@Override
	public AddProductResponseModel addProduct(
			AddProductRequestModel addProductRequestModel) {
		LogUtil.logDTO("Add Product Request: ", "addProduct");
		AddProductResponseModel responseModel= businessTemplate.addProduct(addProductRequestModel);
		LogUtil.logDTO("Add Product Response: ", responseModel);
		return responseModel;
	}

	@Override
	public ListServiceProductResponseModel listServiceProduct() {
		LogUtil.logDTO("List Service Product Request: ", "listSelectServiceRequestModel");
		ListServiceProductResponseModel responseModel= businessTemplate.listServiceProduct();
		LogUtil.logDTO("List Service Produc Response: ", responseModel);
		return responseModel;
	}


	@Override
	public ReleaseRTQAccountAssignmentResponseModel releaseRTQAccountAssignment(
			ReleaseRTQAccountAssignmentRequestModel releaseRTQAccountAssignmentRequestModel) {		
		LogUtil.logDTO("Release RTQ Account Assignment Request: ", releaseRTQAccountAssignmentRequestModel);
		ReleaseRTQAccountAssignmentResponseModel responseModel=businessTemplate.releaseRTQAccountAssignment(releaseRTQAccountAssignmentRequestModel);
		LogUtil.logDTO("Release RTQ Account Assignment Response: ", responseModel);
		return responseModel;
	}
	

	@Override
	public SaveRTQAccountResponseModel saveRTQAccount(
			SaveRTQAccountRequestModel saveRTQAccountRequestModel) {
		LogUtil.logDTO("Save RTQ Account Request: ", saveRTQAccountRequestModel);
		SaveRTQAccountResponseModel responseModel=businessTemplate.saveRTQAccount(saveRTQAccountRequestModel);
		LogUtil.logDTO("Save RTQ Account Response: ", responseModel);
		return responseModel;
		
	}

	@Override
	public UserProductResponseModel updateCancelUserProduct(
			UpdateUserProductRequestModel updateUserProductRequestModel) {
		LogUtil.logDTO("Update Cancel User Product Request: ", updateUserProductRequestModel);
		UserProductResponseModel responseModel= businessTemplate.updateCancelUserProduct(updateUserProductRequestModel);
		LogUtil.logDTO("Update Cancel User Product Response: ", responseModel);
		return responseModel;
		
	}

	@Override
	public ChangeProductResponseModel updateNoEmailProduct(
			ChangeNoEmailProductRequestModel changeNoEmailProductRequestModel) {
		LogUtil.logDTO("Update Cancel User Product Request: ", changeNoEmailProductRequestModel);
		ChangeProductResponseModel responseModel= businessTemplate.updateNoEmailProduct(changeNoEmailProductRequestModel);
		LogUtil.logDTO("Update Cancel User Product Response: ", responseModel);
		return responseModel;
	}

	@Override
	public UpdateRTQAccountResponseModel updateRTQAccount(
			UpdateRTQAccountRequestModel updateRTQAccountRequestModel) {
		LogUtil.logDTO("Update RTQ Account Request: ", updateRTQAccountRequestModel);
		UpdateRTQAccountResponseModel responseModel= businessTemplate.updateRTQAccount(updateRTQAccountRequestModel);
		LogUtil.logDTO("Update RTQ Account Response: ", responseModel);
		return responseModel;
	}

	@Override
	public ChangeRTQApplicationResponseModel updateRTQApplication(
			ChangeRTQApplicationRequestModel changeRTQApplicationRequestModel) {
		LogUtil.logDTO("Change RTQ Application Request: ", changeRTQApplicationRequestModel);
		ChangeRTQApplicationResponseModel responseModel= businessTemplate.updateRTQApplication(changeRTQApplicationRequestModel);
		LogUtil.logDTO("Change RTQ Application Response: ", responseModel);
		return responseModel;
	}

	@Override
	public ChangeProductResponseModel updateRTQ_SHKProduct(
			ChangeRTQ_SHKProductRequestModel changeRTQSHKProductRequestModel) {
		LogUtil.logDTO("update RTQ_SHK Product Request: ", changeRTQSHKProductRequestModel);
		ChangeProductResponseModel responseModel= businessTemplate.updateRTQ_SHKProduct(changeRTQSHKProductRequestModel);
		LogUtil.logDTO("update RTQ_SHK Product Response: ", responseModel);
		return responseModel;
	}

	@Override
	public UserProductResponseModel updateRenewalOption(
			UpdateUserProductRequestModel updateUserProductRequestModel) {
		LogUtil.logDTO("Update Renewal Option Request: ", updateUserProductRequestModel);
		UserProductResponseModel responseModel= businessTemplate.updateRenewalOption(updateUserProductRequestModel);
		LogUtil.logDTO("Update Renewal Option Response: ", responseModel);
		return responseModel;
	}

	@Override
	public UserProductResponseModel updateUserProductStatus(
			UpdateUserProductRequestModel updateUserProductRequestModel) {
		LogUtil.logDTO("Update User Product Status Request: ", updateUserProductRequestModel);
		UserProductResponseModel responseModel= businessTemplate.updateUserProductStatus(updateUserProductRequestModel);
		LogUtil.logDTO("Update User Product Status Response: ", responseModel);
		return responseModel;
	}

	@Override
	public UserProfileResponseModel findAdminUserProfile(
			UserProfileRequestModel userProfileRequestModel) {
		LogUtil.logDTO("find Admin User Profile Request: ", userProfileRequestModel);
		UserProfileResponseModel responseModel= businessTemplate.findAdminUserProfile(userProfileRequestModel);;
		LogUtil.logDTO("find Admin User Profile Response: ", responseModel);
		return responseModel;
	}



	@Override
	public MonthlyPurchaseEnquiryResponseModel monthlyPurchaseEnquiry(
			MonthlyPurchaseEnquiryRequestModel monthlyPurchaseEnquiryRequestModel) {
		LogUtil.logDTO("Monthly Purchase Enquiry Request: ", monthlyPurchaseEnquiryRequestModel);
		MonthlyPurchaseEnquiryResponseModel responseModel= businessTemplate.monthlyPurchaseEnquiry(monthlyPurchaseEnquiryRequestModel);
		LogUtil.logDTO("Monthly Purchase Enquiry Response: ", responseModel);
		return responseModel;
	}

//	@Override
//	public ReserveAndRenewalEnquiryResponseModel reserveAndRenewalEnquiry(
//			ReserveAndRenewalEnquiryRequestModel reserveAndRenewalEnquiryRequestModel) {
//		LogUtil.logDTO("Reserve And Renewal Enquiry Request: ", reserveAndRenewalEnquiryRequestModel);
//		ReserveAndRenewalEnquiryResponseModel responseModel= businessTemplate.reserveAndRenewalEnquiry(reserveAndRenewalEnquiryRequestModel);
//		LogUtil.logDTO("Reserve And Renewal Enquiry Response: ", responseModel);
//		return responseModel;
//	}
   
	@Override
	public ReserveAndRenewalEnquiryResponseModel findReserveAndRenewalEnquiry(ReserveAndRenewalEnquiryRequestModel reserveAndRenewalEnquiryRequestModel){
		LogUtil.logDTO("Reserve And Renewal Enquiry Request: ", reserveAndRenewalEnquiryRequestModel);
		ReserveAndRenewalEnquiryResponseModel responseModel= businessTemplate.findReserveAndRenewalEnquiry(reserveAndRenewalEnquiryRequestModel);
		LogUtil.logDTO("Reserve And Renewal Enquiry Response: ", responseModel);
		return responseModel;
		
	}
	@Override
	public RTQAccountAssignmentResponseModel findRTQAccountAssignmentByProductId(
			RTQAccountAssignmentRequestModel rtqAccountAssignmentRequestModel) {
		LogUtil.logDTO("find RTQ Account Assignment By ProductId Request: ", rtqAccountAssignmentRequestModel);
		RTQAccountAssignmentResponseModel responseModel=  businessTemplate.findRTQAccountAssignmentByProductId(rtqAccountAssignmentRequestModel);
		LogUtil.logDTO("find RTQ Account Assignment By ProductId Response: ", responseModel);
		return responseModel;
	}
	
	@Override
	public RTQAccountResponseModel listRtqAccountByProIdOrLoginId(
			RTQAccountRequestModel rtqAccountRequestModel) {
		LogUtil.logDTO("list RTQ Account  Request: ", rtqAccountRequestModel);
		RTQAccountResponseModel responseModel=businessTemplate.listRtqAccountByProIdOrLoginId(rtqAccountRequestModel);
		LogUtil.logDTO("list RTQ Account  Response: ", responseModel);
		return responseModel;
	}
	
	

	@Override
	public RTQApplicationResponseModel findRTQApplicationByProductId(
			RTQApplicationResquestModel rtqApplicationResquestModel) {
		LogUtil.logDTO("find RTQ Application By ProductId Request: ", rtqApplicationResquestModel);
		RTQApplicationResponseModel responseModel=  businessTemplate.findRTQApplicationByProductId(rtqApplicationResquestModel);
		LogUtil.logDTO("find RTQ Application By ProductId Response: ", responseModel);
		return responseModel;
	}

	@Override
	public ServiceProductResponseModel findServiceProductByProductId(
			ServiceProductRequestModel serviceProductRequestModel) {
		LogUtil.logDTO("find Service Product By ProductId Request: ", serviceProductRequestModel);
		ServiceProductResponseModel responseModel=  businessTemplate.findServiceProductByProductId(serviceProductRequestModel);
		LogUtil.logDTO("find Service Product By ProductId Response: ", responseModel);
		return responseModel;
	}

	@Override
	public String addAclRole(AclRole aclRole) {
	
		return businessTemplate.addAclRole(aclRole);
	}

	@Override
	public String addAclUser(AclUserProfile aclUser) {
	
		return businessTemplate.addAclUser(aclUser);
	}



	@Override
	public String deleteAclUser(String loginId) {
	
		return businessTemplate.deleteAclUser(loginId);
	}



	@Override
	public String deleteRole(String roleId) {
		
		return businessTemplate.deleteRole(roleId);
	}

	@Override
	public AclRoleResponseModel getAclRole(String aclRoleId) {
		
		return businessTemplate.getAclRole(aclRoleId);
	}

	@Override
	public AclUserResponseModel getAclUser(String loginId) {
		
		return businessTemplate.getAclUser(loginId);
	}

	@Override
	public AclRoleResponseModelList listAclRole(StringMap parmsMap,
			Integer pageNumber, Integer pageSize) {
		
		return businessTemplate.listAclRole(parmsMap, pageNumber, pageSize);
	}

	@Override
	public AclUserResponseModelList listAclUser(StringMap parmsMap,
			Integer pageNumber, Integer pageSize) {

		return businessTemplate.listAclUser(parmsMap, pageNumber, pageSize);
	}

	@Override
	public String updateAclUser(AclUserProfile aclUser) {
	
		return businessTemplate.updateAclUser(aclUser);
	}

	@Override
	public String updateAclRole(AclRole aclRole) {
		
		return businessTemplate.updateAclRole(aclRole);
	}

	@Override
	public AclUserResponseModel loginAclUserProfile(String lognId) {
		return businessTemplate.getAclUser(lognId);
	}

	@Override
	public AclRoleResponseModelList listAclRole() {
		return businessTemplate.listAclRole();
	}
	
	@Override
	public NotificationMediaListResponseModel exportNotificationMedia(
			NotificationMediaListRequestModel notificationMediaListRequestModel) {
		return businessTemplate.exportNotificationMedia(notificationMediaListRequestModel);
	}

	@Override
	public ListRtqAccountAssResponseModel listRtqAccountAssByProductId(
			ListRtqAccountAssRequestModel listRtqAccountAssRequestModel) {
		LogUtil.logDTO("list RtqAccountAss Response Request: ", listRtqAccountAssRequestModel);
		ListRtqAccountAssResponseModel responseModel=  businessTemplate.listRtqAccountAssByProductId(listRtqAccountAssRequestModel);
		LogUtil.logDTO("list RtqAccountAss Response Response: ", responseModel);
		return responseModel;
	}

	@Override
	public ServicesAccessLogByRtqAccAsgnIdResponseModel getServicesAccessLogByRtqAccAsgnId(ServicesAccessLogByRtqAccAsgnIdRequestModel servicesAccessLogByRtqAccAsgnIdRequestModel){
		return businessTemplate.getServicesAccessLogByRtqAccAsgnId(servicesAccessLogByRtqAccAsgnIdRequestModel);
	}

	@Override
	public ProductResponseModel getProduct(
			ProductRequestModel productRequestModel) {
		return businessTemplate.getProduct(productRequestModel);
	}

	@Override
	public boolean updateMisDayEndProcessingFlag(String flag) {
		return businessTemplate.updateMisDayEndProcessingFlag(flag);
	}

	@Override
	public String getMisDayEndProcessingFlag() {
		return businessTemplate.getMisDayEndProcessingFlag();
	}
	
	@Override
	public ReserveUserProductResponseModel findreserveAndRenewalEnquiryById(ReserveUserProductRequestModel reserveUserProductRequestModel){
		return businessTemplate.findreserveAndRenewalEnquiryById(reserveUserProductRequestModel);
	}
	
	@Override
	public List<Product> getProductList(String cnDiscFlag){
		return businessTemplate.getProductList(cnDiscFlag);
	}
	
	@Override
	public UserProductResponseModel cancelReservedUserProduct(UpdateUserProductRequestModel updateUserProductRequestModel) {
		return businessTemplate.cancelReservedUserProduct(updateUserProductRequestModel);
	}
	
	@Override
	public ReserveServiceResponseModel reserveService(ReserveServiceRequestModel reserveServiceRequestModel){
		return businessTemplate.reserveService(reserveServiceRequestModel);
	}
	
	@Override
	public AutoPurchaseResponseModel listAutoPurchase(AutoPurchaseRequestModel autoPurchaseRequestModel){
		return businessTemplate.listAutoPurchase(autoPurchaseRequestModel);
	}

	@Override
	public ListUserPaymentByManualResponseModel getUserProductPaymentListByManual(
			ListUserPaymentByManualRequestModel listUserPaymentByManualRequestModel) {
		LogUtil.logDTO("UserProductPaymentListByManual Request: ", listUserPaymentByManualRequestModel);
		ListUserPaymentByManualResponseModel responseModel=businessTemplate.getUserProductPaymentListByManual(listUserPaymentByManualRequestModel);
		LogUtil.logDTO("UserProductPaymentListByManual Response: ", responseModel); 
		return responseModel; 
	}

	@Override
	public UpdateUserPaymentByManualResponseModel updateUserProductPaymentByManual(
			UpdateUserPaymentByManualRequestModel updateUserPaymentByManualRequestModel) {
		LogUtil.logDTO("UpdateUserPaymentByManual Request: ", updateUserPaymentByManualRequestModel);
		UpdateUserPaymentByManualResponseModel responseModel=businessTemplate.updateUserProductPaymentByManual(updateUserPaymentByManualRequestModel);
		LogUtil.logDTO("UpdateUserPaymentByManual Response: ", responseModel); 
		return responseModel; 
	}
	
	@Override
	public String getMemoDebitSytem() {
		return businessTemplate.getMemoDebitSytem();
	}
}
	
