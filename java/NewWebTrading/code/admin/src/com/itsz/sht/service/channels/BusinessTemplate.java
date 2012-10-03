package com.itsz.sht.service.channels;

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
import com.itsz.sht.dao.hibernate.model.AclRole;
import com.itsz.sht.dao.hibernate.model.AclUserProfile;
import com.itsz.sht.dao.hibernate.model.Product;
import com.itsz.sht.dao.hibernate.model.UserProductAllotment;


public interface BusinessTemplate {
    
    //3.3.1.1 Search User Profile 需修改
    public UserProfileResponseModel findAdminUserProfile(UserProfileRequestModel userProfileRequestModel);
 
	//3.3.1.2.3	查询已选购服务
	public ListSelectServiceResponseModel listSelectService(ListSelectServiceRequestModel listSelectServiceRequestModel);
    
	//3.3.1.3.1	Change User Services Product Status(查询)
	public UserProductResponseModel findUserProductStautsByClientId(UserProductRequestModel userProductRequestModel);
	//3.3.1.3.1	Change User Services Product Status(修改)
	public UserProductResponseModel updateUserProductStatus(UpdateUserProductRequestModel updateUserProductRequestModel);
	//3.3.1.3.2	Cancel User Service Product(查询)
	public UserProductResponseModel findCancelUserProductByClientId(UserProductRequestModel userProductRequestModel);
	//3.3.1.3.2	Cancel User Service Product(修改)
	public UserProductResponseModel updateCancelUserProduct(UpdateUserProductRequestModel updateUserProductRequestModel);
	//3.3.1.3.3	Change Renewal Option(查询)
	public UserProductResponseModel findRenewalOptionByClientId(UserProductRequestModel userProductRequestModel);
	//3.3.1.3.3	Change Renewal Option(修改)	
	public UserProductResponseModel updateRenewalOption(UpdateUserProductRequestModel updateUserProductRequestModel);
	
	//3.3.2.1	Create Services Product 用sql脚本创建  
	public AddProductResponseModel addProduct(AddProductRequestModel addProductRequestModel);
	//3.3.2.2 Edit Services Product(查询) 
	public ListServiceProductResponseModel listServiceProduct();
    public ServiceProductResponseModel findServiceProductByProductId(ServiceProductRequestModel serviceProductRequestModel);
	//3.3.2.2 Edit Services Product(修改) 	Edit NO_EMAIL service product
	public ChangeProductResponseModel updateNoEmailProduct(ChangeNoEmailProductRequestModel changeNoEmailProductRequestModel);
     //Edit RTQ/SHK service product
	public ChangeProductResponseModel updateRTQ_SHKProduct(ChangeRTQ_SHKProductRequestModel changeRTQ_SHKProductRequestModel);
	
	//3.3.3.1--3.3.3.2	User Product Renewal
	public ExecuteAutoEnrollmentResponseModel executeAutoEnrollment(String actionBy);
	//3.3.3.3	Memo-debit
	public ExecuteMemoDebitResponseModel  exeMemoDebit(ExecuteMemoDebitRequestModel executeMemoDebitRequestModel);
	//查询到需要做MenoDebit的数据
	public List<UserProductAllotment> findMemoDebitUserProductAllotment();
	
	//3.3.4.1	Monthly Purchase Detail Enquiry 
	public MonthlyPurchaseEnquiryResponseModel monthlyPurchaseEnquiry(MonthlyPurchaseEnquiryRequestModel monthlyPurchaseEnquiryRequestModel);	
	//3.3.4.2	Reserve and Renewal Details Enquiry 
//	public ReserveAndRenewalEnquiryResponseModel reserveAndRenewalEnquiry(ReserveAndRenewalEnquiryRequestModel reserveAndRenewalEnquiryRequestModel);
	
	public ReserveAndRenewalEnquiryResponseModel findReserveAndRenewalEnquiry(ReserveAndRenewalEnquiryRequestModel reserveAndRenewalEnquiryRequestModel);
	
	//3.3.5	RTQ Maintenance
	//3.3.5.1.1	Initial RTQ App 用sql脚本创建  待实现	
	
	//3.3.5.1.2 Update RTQ App(查询)
	public ListRTQApplicationResponseModel listRTQApplication();
	public RTQApplicationResponseModel findRTQApplicationByProductId(RTQApplicationResquestModel rtqApplicationResquestModel);
	//3.3.5.1.2 Update RTQ App(修改)
	public ChangeRTQApplicationResponseModel updateRTQApplication(ChangeRTQApplicationRequestModel changeRTQApplicationRequestModel);

	//3.3.5.2	RTQ Account Maintenance   
	//3.3.5.2.1	RTQ Account Usage Enquiry 
	public RTQAccountViewResponseModel listRTQAccountView();
	//3.3.5.2.2	Add RTQ Account (batch)
	public SaveRTQAccountResponseModel saveRTQAccount(SaveRTQAccountRequestModel saveRTQAccountRequestModel);
	//3.3.5.2.3	Update RTQ Account (batch)
	public UpdateRTQAccountResponseModel updateRTQAccount(UpdateRTQAccountRequestModel updateRTQAccountRequestModel);
	//3.3.5.2.4	Delete RTQ Account (batch)
	public DeleteRTQAccountResponseModel deleteRTQAccount(DeleteRTQAccountRequestModel deleteRTQAccountRequestModel);
	//3.3.5.2.5	Delete Last n RTQ Account (batch)
	public DeleteRTQAccountLastNResponseModel deleteRTQAccountLastN(DeleteRTQAccountLastNRequestModel deleteRTQAccountLastNRequestModel);
	//3.3.5.2.6	RTQ Account Assignment Enquiry 
	public RTQAccountAssignmentResponseModel findRTQAccountAssignmentByProductId(RTQAccountAssignmentRequestModel rtqAccountAssignmentRequestModel);
	
	public RTQAccountResponseModel  listRtqAccountByProIdOrLoginId(RTQAccountRequestModel rtqAccountRequestModel);
	//3.3.6	Service Access
	//3.3.6.1.4	Release RTQ Account Assignment
	public ReleaseRTQAccountAssignmentResponseModel releaseRTQAccountAssignment(ReleaseRTQAccountAssignmentRequestModel releaseRTQAccountAssignmentRequestModel);

   	public String addAclUser(AclUserProfile aclUser);
    
    public String updateAclUser(AclUserProfile aclUser);
    
    public AclUserResponseModel getAclUser(String loginId);
    
    public AclUserResponseModelList listAclUser(StringMap parmsMap,Integer pageNumber,Integer pageSize);
    
    public String deleteAclUser(String loginId);
    
    public String addAclRole(AclRole aclRole);
    
    public String deleteRole(String roleId);
    
    public String updateAclRole(AclRole aclRole);
    
    public AclRoleResponseModel  getAclRole(String aclRoleId);
    
    public AclRoleResponseModelList listAclRole(StringMap parmsMap,Integer pageNumber,Integer pageSize);    
    
    public AclUserResponseModel loginAclUserProfile(String lognId) ;
    
    public AclRoleResponseModelList listAclRole();
    
    
  //3.2.2.5	Export User Notification Data
    public NotificationMediaListResponseModel exportNotificationMedia(NotificationMediaListRequestModel notificationMediaListRequestModel);
   
    public ListRtqAccountAssResponseModel listRtqAccountAssByProductId(ListRtqAccountAssRequestModel listRtqAccountAssRequestModel);
    
    
    
    public ServicesAccessLogByRtqAccAsgnIdResponseModel getServicesAccessLogByRtqAccAsgnId(ServicesAccessLogByRtqAccAsgnIdRequestModel servicesAccessLogByRtqAccAsgnIdRequestModel);

    public ProductResponseModel getProduct(ProductRequestModel productRequestModel);
    
    public boolean updateMisDayEndProcessingFlag(String flag);
    
    public String getMisDayEndProcessingFlag();
    
    public ReserveUserProductResponseModel findreserveAndRenewalEnquiryById(ReserveUserProductRequestModel reserveUserProductRequestModel);
    
    public List<Product> getProductList(String cnDiscFlag);
    
    public UserProductResponseModel cancelReservedUserProduct(UpdateUserProductRequestModel updateUserProductRequestModel) ;
    
    public ReserveServiceResponseModel reserveService(ReserveServiceRequestModel reserveServiceRequestModel);
    
    public AutoPurchaseResponseModel listAutoPurchase(AutoPurchaseRequestModel autoPurchaseRequestModel);

	public ListUserPaymentByManualResponseModel getUserProductPaymentListByManual(ListUserPaymentByManualRequestModel listUserPaymentByManualRequestModel);
	
	public UpdateUserPaymentByManualResponseModel updateUserProductPaymentByManual(UpdateUserPaymentByManualRequestModel updateUserPaymentByManualRequestModel);
	
	public String getMemoDebitSytem();

}
