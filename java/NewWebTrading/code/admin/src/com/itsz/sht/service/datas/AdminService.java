package com.itsz.sht.service.datas;

import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;

import com.itsz.common.util.StringMap;
import com.itsz.sht.common.model.common.request.AddProductRequestModel;
import com.itsz.sht.common.model.common.request.ChangeNoEmailProductRequestModel;
import com.itsz.sht.common.model.common.request.ChangeRTQApplicationRequestModel;
import com.itsz.sht.common.model.common.request.ChangeRTQ_SHKProductRequestModel;
import com.itsz.sht.common.model.common.request.DeleteRTQAccountLastNRequestModel;
import com.itsz.sht.common.model.common.request.DeleteRTQAccountRequestModel;
import com.itsz.sht.common.model.common.request.ListRtqAccountAssRequestModel;
import com.itsz.sht.common.model.common.request.ListSelectServiceRequestModel;
import com.itsz.sht.common.model.common.request.MonthlyPurchaseEnquiryRequestModel;
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
import com.itsz.sht.common.model.common.request.UpdateUserProductRequestModel;
import com.itsz.sht.common.model.common.request.UserProductRequestModel;
import com.itsz.sht.common.model.common.request.UserProfileRequestModel;
import com.itsz.sht.common.model.common.response.AclRoleResponseModel;
import com.itsz.sht.common.model.common.response.AclRoleResponseModelList;
import com.itsz.sht.common.model.common.response.AclUserResponseModel;
import com.itsz.sht.common.model.common.response.AclUserResponseModelList;
import com.itsz.sht.common.model.common.response.AddProductResponseModel;
import com.itsz.sht.common.model.common.response.ChangeProductResponseModel;
import com.itsz.sht.common.model.common.response.ChangeRTQApplicationResponseModel;
import com.itsz.sht.common.model.common.response.DeleteRTQAccountLastNResponseModel;
import com.itsz.sht.common.model.common.response.DeleteRTQAccountResponseModel;
import com.itsz.sht.common.model.common.response.ListRTQApplicationResponseModel;
import com.itsz.sht.common.model.common.response.ListRtqAccountAssResponseModel;
import com.itsz.sht.common.model.common.response.ListSelectServiceResponseModel;
import com.itsz.sht.common.model.common.response.ListServiceProductResponseModel;
import com.itsz.sht.common.model.common.response.ListUserPaymentByManualResponseModel;
import com.itsz.sht.common.model.common.response.MonthlyPurchaseEnquiryResponseModel;
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
import com.itsz.sht.common.model.common.response.UserProductResponseModel;
import com.itsz.sht.common.model.common.response.UserProfileResponseModel;
import com.itsz.sht.dao.hibernate.model.AclRole;
import com.itsz.sht.dao.hibernate.model.AclUserProfile;
import com.itsz.sht.dao.hibernate.model.AutoPurchase;
import com.itsz.sht.dao.hibernate.model.NotificationMedia;
import com.itsz.sht.dao.hibernate.model.Product;
import com.itsz.sht.dao.hibernate.model.UserProduct;
import com.itsz.sht.dao.hibernate.model.UserProductAllotment;
import com.itsz.sht.dao.hibernate.model.UserProfile;
import com.taifook.adminportal.common.util.page.Page;

public interface AdminService {	
	
	//use  profile 
//	public UserProfileResponseModel updateUserProfile(UserProfileRequestModel userProfileRequestModel);
	
	public UserProfileResponseModel findUserProfile(UserProfileRequestModel userProfileRequestModel);
	
	public boolean deleteUserProfile(String clientId);
	
	public boolean saveUserProfile(UserProfile userProfile);	
		
	public ListSelectServiceResponseModel listSelectService(ListSelectServiceRequestModel listSelectServiceRequestModel);

	public ChangeProductResponseModel updateNoEmailProduct(ChangeNoEmailProductRequestModel changeNoEmailProductRequestModel);

	public ChangeProductResponseModel updateRTQ_SHKProduct(ChangeRTQ_SHKProductRequestModel changeRTQ_SHKProductRequestModel);
	
	public ChangeRTQApplicationResponseModel updateRTQApplication(ChangeRTQApplicationRequestModel changeRTQApplicationRequestModel);
	
	public ListRTQApplicationResponseModel listRTQApplication();
	
	public RTQAccountViewResponseModel listRTQAccountView();
	
	public SaveRTQAccountResponseModel saveRTQAccount(SaveRTQAccountRequestModel saveRTQAccountRequestModel);
	
	public UpdateRTQAccountResponseModel updateRTQAccount(UpdateRTQAccountRequestModel updateRTQAccountRequestModel);
	
	public DeleteRTQAccountResponseModel deleteRTQAccount(DeleteRTQAccountRequestModel deleteRTQAccountRequestModel);
	
	public DeleteRTQAccountLastNResponseModel deleteRTQAccountLastN(DeleteRTQAccountLastNRequestModel deleteRTQAccountLastNRequestModel);
	

	
	
	public ReleaseRTQAccountAssignmentResponseModel releaseRTQAccountAssignment(ReleaseRTQAccountAssignmentRequestModel releaseRTQAccountAssignmentRequestModel);
	
	public void allotRTQAccountAssignment() throws MessagingException;
	
	public AddProductResponseModel addProduct(AddProductRequestModel addProductRequestModel);
	
	public ListServiceProductResponseModel listServiceProduct();
	
	public UserProductResponseModel findUserProductStautsByClientId(UserProductRequestModel userProductRequestModel);
	
	public UserProductResponseModel updateUserProductStatus(UpdateUserProductRequestModel updateUserProductRequestModel);
	
	public UserProductResponseModel findCancelUserProductByClientId(UserProductRequestModel userProductRequestModel);
	
	public UserProductResponseModel updateCancelUserProduct(UpdateUserProductRequestModel updateUserProductRequestModel);
	
	public UserProductResponseModel findRenewalOptionByClientId(UserProductRequestModel userProductRequestModel);
	
	public UserProductResponseModel updateRenewalOption(UpdateUserProductRequestModel updateUserProductRequestModel);
	
	
	public List<UserProduct> findExeRenewalUserProduct();

	public UserProfile getUserProfile(String clientId);
	
	public Product getProuctByProductId(String productId);
	
	public void exeRenewalByUserProduct(UserProduct userProduct, Long priceInHkd, String defDebtAcc, String username);
	
	public List<UserProductAllotment>  findAutoPurchaseUserProduct();
	
	public void exeAutoPurchaseByUserProduct(UserProductAllotment userProductAllotment,String username);
	
	public List<UserProductAllotment> findMemoDebitUserProductAllotment() ;
	
	public boolean updateUserProductPayment(String userProductPaymentId, String reference, String debtRemarks, Date reqTime, Date resTime, String actionBy, boolean succFlag);
	
	public MonthlyPurchaseEnquiryResponseModel monthlyPurchaseEnquiry(MonthlyPurchaseEnquiryRequestModel monthlyPurchaseEnquiryRequestModel);	
	
	public ReserveAndRenewalEnquiryResponseModel reserveAndRenewalEnquiry(ReserveAndRenewalEnquiryRequestModel reserveAndRenewalEnquiryRequestModel);
	
	public Page findReserveAndRenewalEnquiry(Integer pageNumber, Integer pageSize,String productId);
	
	public RTQAccountAssignmentResponseModel findRTQAccountAssignmentByProductId(RTQAccountAssignmentRequestModel rtqAccountAssignmentRequestModel);

	public RTQAccountResponseModel  listRtqAccountByProIdOrLoginId(RTQAccountRequestModel rtqAccountRequestModel);
	
	public RTQApplicationResponseModel findRTQApplicationByProductId(RTQApplicationResquestModel rtqApplicationResquestModel);

    public ServiceProductResponseModel findServiceProductByProductId(ServiceProductRequestModel serviceProductRequestModel);
    
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

    public AclUserResponseModel loginAclUserProfile(String lognId);
    
    public AclRoleResponseModelList listAclRole();    
    
    public List<NotificationMedia> getNotificationMediaByType(String notfType);
    
    public ListRtqAccountAssResponseModel listRtqAccountAssByProductId(ListRtqAccountAssRequestModel listRtqAccountAssRequestModel);

    public ServicesAccessLogByRtqAccAsgnIdResponseModel getServicesAccessLogByRtqAccAsgnId(ServicesAccessLogByRtqAccAsgnIdRequestModel servicesAccessLogByRtqAccAsgnIdRequestModel);

    public ProductResponseModel getProduct(ProductRequestModel productRequestModel);
    
	public String getMisDayEndProcessingFlag();
	
	public void updateMisDayEndProcessingFlag(String flag);
	
	public List<Product> getProductList(String cnDiscFlag);
	
	public ReserveUserProductResponseModel findreserveAndRenewalEnquiryById(ReserveUserProductRequestModel reserveUserProductRequestModel);
	
	public ReserveServiceResponseModel reserveService(ReserveServiceRequestModel reserveServiceRequestModel, Long priceInHkd);
	
	public UserProductResponseModel cancelReservedUserProduct(UpdateUserProductRequestModel updateUserProductRequestModel) ;
	
	public Product findProuctByProductId(String productId);
	
	public List<AutoPurchase> getAutoPurchaseList(String status, String startTime, String endTime);
	
	public void addAutoPurchase(AutoPurchase autoPurchase);
	
	public ListUserPaymentByManualResponseModel getUserProductPaymentListByManual(Date startTime, Date endTime, int pageNumber, int pageSize);
	
	public void updateUserProductPaymentByManual(String[] usrProdPayIds);
}
