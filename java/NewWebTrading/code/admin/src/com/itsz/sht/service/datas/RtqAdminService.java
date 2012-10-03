package com.itsz.sht.service.datas;

import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;

import com.itsz.sht.common.model.common.request.AccessOrderNotificationRequestModel;
import com.itsz.sht.common.model.common.request.AccessRTQRequestModel;
import com.itsz.sht.common.model.common.request.AccessSHKRequestModel;
import com.itsz.sht.common.model.common.request.ListSelectServiceRequestModel;
import com.itsz.sht.common.model.common.request.PurchaseServiceRequestModel;
import com.itsz.sht.common.model.common.request.ReserveServiceRequestModel;
import com.itsz.sht.common.model.common.request.UpdateUserProductRequestModel;
import com.itsz.sht.common.model.common.request.UserNotificationEmailRequestModel;
import com.itsz.sht.common.model.common.response.AccessOrderNotificationResponseModel;
import com.itsz.sht.common.model.common.response.AccessRTQResponseModel;
import com.itsz.sht.common.model.common.response.AccessSHKResponseModel;
import com.itsz.sht.common.model.common.response.ListSelectServiceResponseModel;
import com.itsz.sht.common.model.common.response.PurchaseServiceResponseModel;
import com.itsz.sht.common.model.common.response.ReserveServiceResponseModel;
import com.itsz.sht.common.model.common.response.UserNotificationEmailResponseModel;
import com.itsz.sht.common.model.common.response.UserProductResponseModel;
import com.itsz.sht.dao.hibernate.model.NotificationMedia;
import com.itsz.sht.dao.hibernate.model.Product;
import com.itsz.sht.dao.hibernate.model.UserProduct;
import com.itsz.sht.dao.hibernate.model.UserProfile;

public interface RtqAdminService {
	
	//use  profile 	
	public boolean deleteUserProfile(String clientId);
	
	public boolean saveUserProfile(UserProfile userProfile);
	
	public void updateUserProfile(UserProfile userProfile);
	
	public List<UserProfile> getAllUserProfile();
	
	public UserProfile getUserProfile(String clientId);
		
	public ListSelectServiceResponseModel listSelectRTQService(ListSelectServiceRequestModel listSelectServiceRequestModel);
						
	public AccessRTQResponseModel accessRTQ(AccessRTQRequestModel accessRTQRequestModel) throws MessagingException;
	
	public AccessSHKResponseModel accessSHK(AccessSHKRequestModel accessSHKRequestModel);
	
	public AccessOrderNotificationResponseModel accessOrderNotification(AccessOrderNotificationRequestModel accessOrderNotificationRequestModel);
		
	
	public ReserveServiceResponseModel reserveService(ReserveServiceRequestModel reserveServiceRequestModel, Long priceInHkd); 

    public PurchaseServiceResponseModel purchaseService(PurchaseServiceRequestModel purchaseServiceRequestModel, Long priceInHkd, String debtRemarks, String reference, Date reqTime, Date resTime);
    
    public UserProductResponseModel cancelReservedUserProduct(UpdateUserProductRequestModel updateUserProductRequestModel) ;
  
    public UserProductResponseModel updateUserProductAllRenl(UpdateUserProductRequestModel updateUserProductRequestModel);
    
    public List<UserProduct> getAvailUserProductList(String clientId);
    
    public List<Product> getRTQProductList(String cnDiscFlag);
    
    public UserProduct findAvailUserProductByKey(String productId, String clientId);
    	
	public Product findProuctByProductId(String productId);	
		
	public UserNotificationEmailResponseModel updateNotificationMedia(UserNotificationEmailRequestModel userNotificationEmailRequestModel);
	
	public NotificationMedia getNotificationMedia(String clientId);
	
	public String getMisDayEndProcessingFlag();
		
}
