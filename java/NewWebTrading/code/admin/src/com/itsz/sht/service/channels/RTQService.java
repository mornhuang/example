package com.itsz.sht.service.channels;

import java.util.List;

import com.itsz.sht.common.model.common.request.AccessOrderNotificationRequestModel;
import com.itsz.sht.common.model.common.request.AccessRTQRequestModel;
import com.itsz.sht.common.model.common.request.AccessSHKRequestModel;
import com.itsz.sht.common.model.common.request.ListSelectServiceRequestModel;
import com.itsz.sht.common.model.common.request.PurchaseServiceRequestModel;
import com.itsz.sht.common.model.common.request.RTQProductRequestModel;
import com.itsz.sht.common.model.common.request.ReserveServiceRequestModel;
import com.itsz.sht.common.model.common.request.PurchasedProductRequestModel;
import com.itsz.sht.common.model.common.request.UpdateUserProductRequestModel;
import com.itsz.sht.common.model.common.request.UserNotificationEmailRequestModel;
import com.itsz.sht.common.model.common.request.UserProfileRequestModel;
import com.itsz.sht.common.model.common.response.AccessOrderNotificationResponseModel;
import com.itsz.sht.common.model.common.response.AccessRTQResponseModel;
import com.itsz.sht.common.model.common.response.AccessSHKResponseModel;
import com.itsz.sht.common.model.common.response.ListSelectServiceResponseModel;
import com.itsz.sht.common.model.common.response.PurchaseServiceResponseModel;
import com.itsz.sht.common.model.common.response.RTQProductResponseModel;
import com.itsz.sht.common.model.common.response.ReserveServiceResponseModel;
import com.itsz.sht.common.model.common.response.PurchasedProductResponseModel;
import com.itsz.sht.common.model.common.response.UserNotificationEmailResponseModel;
import com.itsz.sht.common.model.common.response.UserProductResponseModel;
import com.itsz.sht.common.model.common.response.UserProfileResponseModel;
import com.itsz.sht.dao.hibernate.model.UserProfile;

public interface RTQService {
	
	public PurchasedProductResponseModel havePurchasedProduct(PurchasedProductRequestModel purchasedProductRequestModel);

	public RTQProductResponseModel getRTQProductList(RTQProductRequestModel rTQProductRequestModel);
	 //3.2.1.1 Reserve Service 
    public ReserveServiceResponseModel reserveService(ReserveServiceRequestModel reserveServiceRequestModel); 
	//3.2.1.2 Purchase Service 
    public PurchaseServiceResponseModel purchaseService(PurchaseServiceRequestModel purchaseServiceRequestModel);
//3.2.2 Order Notification Service 
    //3.2.2.1	Get User Notification Email Setting
    public UserNotificationEmailResponseModel getUserNotificationEmail(UserNotificationEmailRequestModel userNotificationEmailRequestModel);
    //3.2.2.2	Update User Notification Email Setting
    public UserNotificationEmailResponseModel updateUserNotificationEmail(UserNotificationEmailRequestModel userNotificationEmailRequestModel);
  //3.3.1.3.1	Change User Services Product Status(update)
	public UserProductResponseModel updateUserProductStatus(UpdateUserProductRequestModel updateUserProductRequestModel);
    //3.3.1.3.2	Cancel User Service Product(update)
	public UserProductResponseModel cancelReservedUserProduct(UpdateUserProductRequestModel updateUserProductRequestModel);
	
	
	
    //3.3.1.1 Search User Profile 
    public UserProfileResponseModel findClientUserProfile(UserProfileRequestModel userProfileRequestModel);   
    
    public List<UserProfile> getAllUserProfile();
    
  //3.3.6	Service Access
	//3.3.6.1	Access RTQ
	public AccessRTQResponseModel accessRTQ(AccessRTQRequestModel accessRTQRequestModel);
	//3.3.6.2	Access SHK
	public AccessSHKResponseModel accessSHK(AccessSHKRequestModel accessSHKRequestModel);
	//3.3.6.2	Access Order Notification
	public AccessOrderNotificationResponseModel accessOrderNotification(AccessOrderNotificationRequestModel accessOrderNotificationRequestModel);
	
	//3.3.1.2.3	select service
	public ListSelectServiceResponseModel listSelectService(ListSelectServiceRequestModel listSelectServiceRequestModel);
	    
}
