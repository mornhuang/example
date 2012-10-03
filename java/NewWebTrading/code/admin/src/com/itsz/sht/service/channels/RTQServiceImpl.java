package com.itsz.sht.service.channels;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.mail.MessagingException;

import org.apache.commons.logging.Log;
import org.springframework.dao.DataAccessException;

import com.itsz.sht.common.Consts;
import com.itsz.sht.common.MemoDebitRemarkUtil;
import com.itsz.sht.common.model.common.LoggerFactory;
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
import com.itsz.sht.dao.hibernate.model.NotificationMedia;
import com.itsz.sht.dao.hibernate.model.Product;
import com.itsz.sht.dao.hibernate.model.UserProduct;
import com.itsz.sht.dao.hibernate.model.UserProfile;
import com.itsz.sht.service.ccis.CcisService;
import com.itsz.sht.service.datas.RtqAdminService;
import com.itsz.sht.service.memodebit.MemoDebitResult;
import com.itsz.sht.service.memodebit.MemoDebitService;

public class RTQServiceImpl implements RTQService{

	Log log = LoggerFactory.getInstance().getCommonInfo();
	public final static String ESTMT_EMAIL = "ESTMT_EMAIL";
	public final static String RTQ = "RTQ";
	public final static String SHK = "SHK";
	
	private CcisService ccisService;
	private RtqAdminService rtqAdminService;
	private MemoDebitService memoDebitService;
	
	public void setCcisService(CcisService ccisService) {
		this.ccisService = ccisService;
	}
	public void setRtqAdminService(RtqAdminService rtqAdminService) {
		this.rtqAdminService = rtqAdminService;
	}

	public void setMemoDebitService(MemoDebitService memoDebitService) {
		this.memoDebitService = memoDebitService;
	}
	
	@Override
	public AccessRTQResponseModel accessRTQ(
			AccessRTQRequestModel accessRTQRequestModel) {
		AccessRTQResponseModel res =new AccessRTQResponseModel();
		try {
			res = rtqAdminService.accessRTQ(accessRTQRequestModel);
		}catch (DataAccessException e) {
			log.error("accessRTQ() DAO error: ",e);
			res.setReturnCode(Consts.Error.Code.ADMINPROTAL_DAOERROR);	
		}catch (MessagingException e){
			log.error("accessRTQ() mail send error: ",e);
			res.setReturnCode(Consts.Error.Code.ADMINPROTAL_RTQACCESS_ERROR_NOACCOUNT);	
		} catch (Exception e) {	
			log.error("accessRTQ() error: aceess RTQ error: ",e);
		    res.setReturnCode(Consts.Error.Code.ADMINPROTAL_RTQACCESS_ERROR_SYSTEMERROR);	
		}	
		return res;
	}

	@Override
	public AccessSHKResponseModel accessSHK(
			AccessSHKRequestModel accessSHKRequestModel) {
		AccessSHKResponseModel res =new AccessSHKResponseModel();
		try {
			res = rtqAdminService.accessSHK(accessSHKRequestModel);
		} catch (DataAccessException e) {
			log.error("accessSHK() DAO error: ",e);
			res.setReturnCode(Consts.Error.Code.ADMINPROTAL_DAOERROR);		
		} catch (Exception e) {
			log.error("accessSHK() error: ",e);
			res.setReturnCode(Consts.Error.Code.ADMINPROTAL_SHKACCESS_ERROR_SYSTEMERROR);	
		}	
		return res;
	}

//	client 
    public UserProfileResponseModel findClientUserProfile(UserProfileRequestModel userProfileRequestModel){
    	UserProfileResponseModel res=new UserProfileResponseModel();
    	boolean flag=false;
		try {
			String cnDiscFlag = "OTHERS";
			try {
				cnDiscFlag = ccisService.getCNDiscountFlag(userProfileRequestModel.getClientId());
			}catch (Exception e) {
				log.error("ccisService.getCNDiscountFlag() error: ",e);
				flag=true;
			}
			UserProfile userProfile = rtqAdminService.getUserProfile(userProfileRequestModel.getClientId());
			if(userProfile == null){
				userProfile=new UserProfile();
				userProfile.setClntId(userProfileRequestModel.getClientId());
				userProfile.setClntLoginId(userProfileRequestModel.getClntLoginId());
				userProfile.setCnDiscFlag(cnDiscFlag);
				userProfile.setDefDebtAcc(userProfileRequestModel.getDefaultDebitAccount());
				userProfile.setInitBy(userProfileRequestModel.getClientId());
				userProfile.setInitDate(new Date());
				userProfile.setUpdBy(userProfileRequestModel.getClientId());
				userProfile.setUpdDate(new Date());
				rtqAdminService.saveUserProfile(userProfile);
			}else{
				userProfile.setClntLoginId(userProfileRequestModel.getClntLoginId());
				if(!flag){
					userProfile.setCnDiscFlag(cnDiscFlag);
				}
				userProfile.setDefDebtAcc(userProfileRequestModel.getDefaultDebitAccount());
				userProfile.setUpdBy(userProfileRequestModel.getClientId());
				userProfile.setUpdDate(new Date());
				rtqAdminService.updateUserProfile(userProfile);
			}
		} catch (Exception e) {
			log.error("findClientUserProfile",e);
			res.setReturnCode(Consts.AdminPortal.returnResulst.FAILED);				
		}
		return 	res;    	
   }  
	 
	@Override
	public AccessOrderNotificationResponseModel accessOrderNotification(
			AccessOrderNotificationRequestModel accessOrderNotificationRequestModel) {
		AccessOrderNotificationResponseModel res = new AccessOrderNotificationResponseModel();
		try {
			res = rtqAdminService.accessOrderNotification(accessOrderNotificationRequestModel);
		}catch (DataAccessException e) {
			log.error("accessOrderNotification() DAO error: ", e);
			res.setReturnCode(Consts.Error.Code.ADMINPROTAL_DAOERROR);	
			} 
		catch (Exception e) {
			log.error("accessOrderNotification() error: " , e);
			res.setReturnCode(Consts.Error.Code.ADMINPROTAL_ORDERNOTIFACATIONACCESS_ERROR_SYSTEMERROR);
		}
		return res;
	}
	
	
	@Override
	public PurchasedProductResponseModel havePurchasedProduct(
			PurchasedProductRequestModel purchasedProductRequestModel) {
		PurchasedProductResponseModel purchasedProductResponseModel = new PurchasedProductResponseModel();
		try{
			UserProduct userProduct = rtqAdminService.findAvailUserProductByKey(purchasedProductRequestModel.getProductId(), purchasedProductRequestModel.getClientId());
			if(userProduct == null){
				purchasedProductResponseModel.setProduct(rtqAdminService.findProuctByProductId(purchasedProductRequestModel.getProductId()));
				purchasedProductResponseModel.setHavePurchased(false);
			}else{
				purchasedProductResponseModel.setHavePurchased(true);
			}
		}catch(Exception e){
			log.error("RTQServiceImpl.havePurchasedProduct():  ",e);
			purchasedProductResponseModel.setReturnCode(Consts.Error.Code.HAVE_PURCHASED_PRODUCT_FAILED);
		}
		return purchasedProductResponseModel;
	}
		
	@Override
	public RTQProductResponseModel getRTQProductList(
			RTQProductRequestModel rTQProductRequestModel) {
		RTQProductResponseModel rTQProductResponseModel = new RTQProductResponseModel();
		try{
			UserProfile userProfile = rtqAdminService.getUserProfile(rTQProductRequestModel.getClientId());
			if(userProfile == null){
				rTQProductResponseModel.setReturnCode(Consts.Error.Code.USERPROFILE_IS_NULL);
			}else{
				List<Product> productList = rtqAdminService.getRTQProductList(userProfile.getCnDiscFlag());
				rTQProductResponseModel.setProductList(productList);
			}
		}catch(Exception e){
			log.error("RTQServiceImpl.getRTQProductList():  ",e);
			rTQProductResponseModel.setReturnCode(Consts.Error.Code.GET_RTQ_PRODUCT_LIST_FAILED);
		}
		return rTQProductResponseModel;
	}
	
	@Override
	public PurchaseServiceResponseModel purchaseService(PurchaseServiceRequestModel purchaseServiceRequestModel) {
		PurchaseServiceResponseModel purchaseServiceResponseModel = new PurchaseServiceResponseModel();
		try {
			if(rtqAdminService.getMisDayEndProcessingFlag().equals(Consts.AdminPortal.misDayEndProcessingFlag.Y)){ //MIS正在日结
				log.info("RTQServiceImpl.purchaseService():  " + memoDebitService.getMemoDebitSystem() + " in day end processing!");
				purchaseServiceResponseModel.setReturnCode(Consts.Error.Code.PURCHASE_SERVICE_MIS_IS_DAYEND_PROCESSING);
			}else{
				if(purchaseServiceRequestModel.getDefDebtAcc()==null || purchaseServiceRequestModel.getDefDebtAcc().equals("")){//无有效的默认账户
					log.info("RTQServiceImpl.purchaseService():  No default debit account!");
					purchaseServiceResponseModel.setReturnCode(Consts.Error.Code.PURCHASE_SERVICE_NO_DEFAULT_DEBIT_ACCOUNT);
				}else{
					Product product  = rtqAdminService.findProuctByProductId(purchaseServiceRequestModel.getProductId());
					if(product!=null){
						if(product.getProdStatus().equals(Consts.AdminPortal.productStatus.UNAVAIL)){//产品不可用
							log.info("RTQServiceImpl.purchaseService():  Proudct(" + purchaseServiceRequestModel.getProductId() + ") is UNAVAIL!");
							purchaseServiceResponseModel.setReturnCode(Consts.Error.Code.PRODUCT_UNAVAIL); 
						}else if(new Date().after(product.getExprDate())){//产品过期
							log.info("RTQServiceImpl.purchaseService():  Proudct(" + purchaseServiceRequestModel.getProductId() + ") was expired!");
							purchaseServiceResponseModel.setReturnCode(Consts.Error.Code.PRODUCT_EXPIRED);
						}else{
							List<UserProduct> userProductList = rtqAdminService.getAvailUserProductList(purchaseServiceRequestModel.getClientId());
							boolean flag = false; //是否已经购买产品标志，默认为没有购买产品。
							for(UserProduct up: userProductList){ //检查是否购买了RTQ 或者 SHK，可以同时购买SHK和一种RTQ，不能同时购买两种及以上RTQ
								if(purchaseServiceRequestModel.getProductId().equals(SHK) && up.getId().getProdId().equals(SHK)){
									flag = true;
									break;
								}
								if(!purchaseServiceRequestModel.getProductId().equals(SHK) && !up.getId().getProdId().equals(SHK)){
									flag = true;
									break;
								}
							}
							if (flag) {
								log.info("RTQServiceImpl.purchaseService():  User product exsited and not expried! <<<  " + userProductList + "  >>>");
								purchaseServiceResponseModel.setReturnCode(Consts.Error.Code.PURCHASE_SERVICE_USERPRODUCT_EXSITED);
							} else {
								String debtRemarks = MemoDebitRemarkUtil.generateMemoRemarks(purchaseServiceRequestModel.getProductId().equals(SHK)?MemoDebitRemarkUtil.SHK:MemoDebitRemarkUtil.RTQ_SEC_STR_HK, new Date());
								//还有可用余额
								if(memoDebitService.balanceCheck(purchaseServiceRequestModel.getDefDebtAcc(), product.getPriceInHkd()) != -1){
									Date reqTime = new Date(System.currentTimeMillis());   //request memo debit time
									MemoDebitResult result = memoDebitService.memoDebit(purchaseServiceRequestModel.getDefDebtAcc(), BigDecimal.valueOf(product.getPriceInHkd()), purchaseServiceRequestModel.getProductId().equals(SHK)?SHK:RTQ, debtRemarks, purchaseServiceRequestModel.getLanguage());
									Date resTime = new Date(System.currentTimeMillis());   //response memo debit time
									if(result.isCompleted()){//memo debit success
										purchaseServiceResponseModel = rtqAdminService.purchaseService(purchaseServiceRequestModel, product.getPriceInHkd(), debtRemarks, result.getResultCode(), reqTime, resTime);
									}else{
										log.info("RTQServiceImpl.purchaseService():  memo debit failed! \n" + result.toString());
										purchaseServiceResponseModel.setReturnCode(Consts.Error.Code.PURCHASE_SERVICE_MEMO_DEBIT_FAILED);
									}
								}else{
									log.info("RTQServiceImpl.purchaseService():  memo debit...insufficient balance!");
									purchaseServiceResponseModel.setReturnCode(Consts.Error.Code.PURCHASE_SERVICE_MEMO_DEBIT_INSUFFICIENT_BALANCE);
								}
							}
						}
					}else{
						log.info("RTQServiceImpl.purchaseService():  Proudct(" + purchaseServiceRequestModel.getProductId() + ") is null!");
						purchaseServiceResponseModel.setReturnCode(Consts.Error.Code.PRODUCT_IS_NULL);
					}	
				}
			}
		}catch(Exception e){
			log.error("RTQServiceImpl.purchaseService():  ",e);
			purchaseServiceResponseModel.setReturnCode(Consts.Error.Code.PURCHASE_SERVICE_FAILED);	
		}
		return purchaseServiceResponseModel;
	}

	@Override
	public ReserveServiceResponseModel reserveService(ReserveServiceRequestModel reserveServiceRequestModel) {// reserve RTQ product
		ReserveServiceResponseModel reserveServiceResponseModel = new ReserveServiceResponseModel();
		try {
			if(reserveServiceRequestModel.getDefDebtAcc()==null || reserveServiceRequestModel.getDefDebtAcc().equals("")){//无有效的默认账户
				log.info("RTQServiceImpl.reserveService():  No default debit account!");
				reserveServiceResponseModel.setReturnCode(Consts.Error.Code.PURCHASE_SERVICE_NO_DEFAULT_DEBIT_ACCOUNT);
			}else{
				if(reserveServiceRequestModel.getProductId().equals(SHK)){
					log.info("RTQServiceImpl.reserveService():  Can not reserve SHK!");
					reserveServiceResponseModel.setReturnCode(Consts.Error.Code.RESERVE_SERVICE_CANNOT_RESERVE_SHK);
				}else{
					Product product  = rtqAdminService.findProuctByProductId(reserveServiceRequestModel.getProductId());
					if(product!=null){
						if(product.getProdStatus().equals(Consts.AdminPortal.productStatus.UNAVAIL)){
							log.info("RTQServiceImpl.reserveService():  Proudct(" + reserveServiceRequestModel.getProductId() + ") is UNAVAIL!");
							reserveServiceResponseModel.setReturnCode(Consts.Error.Code.PRODUCT_UNAVAIL); 
						}else if(new Date().after(product.getExprDate())){
							log.info("RTQServiceImpl.reserveService():  Proudct(" + reserveServiceRequestModel.getProductId() + ") was expired!");
							reserveServiceResponseModel.setReturnCode(Consts.Error.Code.PRODUCT_EXPIRED);
						}else{
							reserveServiceResponseModel = rtqAdminService.reserveService(reserveServiceRequestModel, product.getPriceInHkd());
						}
					}else{
						log.info("RTQServiceImpl.reserveService():  Proudct(" + reserveServiceRequestModel.getProductId() + ") is null!");
						reserveServiceResponseModel.setReturnCode(Consts.Error.Code.PRODUCT_IS_NULL);
					}
				}
			}
		}catch(Exception e){
			log.error("RTQServiceImpl.reserveService():  ",e);
			reserveServiceResponseModel.setReturnCode(Consts.Error.Code.RESERVE_SERVICE_FAILED);	
		}		
		return reserveServiceResponseModel;
	}
		
	@Override
	public UserProductResponseModel cancelReservedUserProduct(
			UpdateUserProductRequestModel updateUserProductRequestModel) {
		UserProductResponseModel userProductResponseModel = new UserProductResponseModel();
		try{
			userProductResponseModel = rtqAdminService.cancelReservedUserProduct(updateUserProductRequestModel);
		}catch(Exception e){
			log.error("RTQServiceImpl.cancelReservedUserProduct():  ",e);
			userProductResponseModel.setReturnCode(Consts.Error.Code.CANCEL_RESERVED_FAILED);
		}
		return userProductResponseModel;
	}
	
	@Override
	public UserProductResponseModel updateUserProductStatus(
			UpdateUserProductRequestModel updateUserProductRequestModel) {
		UserProductResponseModel userProductResponseModel = new UserProductResponseModel();
		try{
			userProductResponseModel = rtqAdminService.updateUserProductAllRenl(updateUserProductRequestModel);
		}catch(Exception e){
			log.error("RTQServiceImpl.updateUserProductStatus():  ",e);
			userProductResponseModel.setReturnCode(Consts.Error.Code.UPDATE_STATUS_FAILED);
		}
		return userProductResponseModel;
	}
	
	@Override
	public UserNotificationEmailResponseModel getUserNotificationEmail(
			UserNotificationEmailRequestModel userNotificationEmailRequestModel) {
		UserNotificationEmailResponseModel userNotificationEmailResponseModel = new UserNotificationEmailResponseModel();
		try{
			HashMap<String, String> map = ccisService.getStmtNotfOptnByClntStmtID(userNotificationEmailRequestModel.getClientId());
			userNotificationEmailResponseModel.setUserNotificationEmail(map.get(ESTMT_EMAIL));
			userNotificationEmailResponseModel.setClientId(userNotificationEmailRequestModel.getClientId());
			boolean notiFlag = false;
			NotificationMedia notificationMedia = rtqAdminService.getNotificationMedia(userNotificationEmailRequestModel.getClientId());
			if(notificationMedia != null){
				notiFlag = true;
				if(map.get(ESTMT_EMAIL) == null){
					userNotificationEmailResponseModel.setUserNotificationEmail(notificationMedia.getEmailAddr());
				}
			}
			userNotificationEmailResponseModel.setNotiFlag(notiFlag);
		}catch(Exception e){
			log.error("RTQServiceImpl.getUserNotificationEmail():  ",e);
			userNotificationEmailResponseModel.setReturnCode(Consts.Error.Code.GET_USERNOTIFICATION_EMAIL_FAILED);	
		}
		return userNotificationEmailResponseModel;
	}
	
	@Override
	public UserNotificationEmailResponseModel updateUserNotificationEmail(
			UserNotificationEmailRequestModel userNotificationEmailRequestModel) {
		UserNotificationEmailResponseModel userNotificationEmailResponseModel = new UserNotificationEmailResponseModel();
		try{
			ccisService.updateStmtNotfOptn(userNotificationEmailRequestModel.getClientId(), userNotificationEmailRequestModel.getUserNotificationEmail(), null, null);
			userNotificationEmailResponseModel = rtqAdminService.updateNotificationMedia(userNotificationEmailRequestModel);			
		}catch(Exception e){
			log.error("RTQServiceImpl.updateUserNotificationEmail():  ",e);
			userNotificationEmailResponseModel.setReturnCode(Consts.Error.Code.UPDATE_USERNOTIFICATION_EMAIL_FAILED);	
		}
		return userNotificationEmailResponseModel;
	}		
	
	@Override
	public ListSelectServiceResponseModel listSelectService(
			ListSelectServiceRequestModel listSelectServiceRequestModel) {
		ListSelectServiceResponseModel listSelectServiceResponseModel = new ListSelectServiceResponseModel();
		try{
			listSelectServiceResponseModel = rtqAdminService.listSelectRTQService(listSelectServiceRequestModel);
		}catch(Exception e){
			log.error("RTQServiceImpl.listSelectService():  ",e);
			listSelectServiceResponseModel.setReturnCode(Consts.AdminPortal.returnResulst.FAILED);
		}
		return listSelectServiceResponseModel;
	}
	
	public  List<UserProfile> getAllUserProfile(){
		return rtqAdminService.getAllUserProfile();
	}
}
	
