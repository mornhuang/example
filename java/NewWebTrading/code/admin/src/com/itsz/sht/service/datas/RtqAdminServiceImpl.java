package com.itsz.sht.service.datas;

import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;

import com.itsz.sht.common.AdminConfigUtil;
import com.itsz.sht.common.Consts;
import com.itsz.sht.common.DateUtil;
import com.itsz.sht.common.Mail;
import com.itsz.sht.common.MailSender;
import com.itsz.sht.common.NotificationBuilder;
import com.itsz.sht.common.model.common.LoggerFactory;
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
import com.itsz.sht.dao.AutoPurchaseDao;
import com.itsz.sht.dao.CsParameterDao;
import com.itsz.sht.dao.NotificationMediaDao;
import com.itsz.sht.dao.ProductDao;
import com.itsz.sht.dao.RtqAccountAssignmentDao;
import com.itsz.sht.dao.RtqAccountDao;
import com.itsz.sht.dao.RtqApplicationDao;
import com.itsz.sht.dao.ServicesAccessLogDao;
import com.itsz.sht.dao.UserProductAllotmentDao;
import com.itsz.sht.dao.UserProductAllotmentLogDao;
import com.itsz.sht.dao.UserProductDao;
import com.itsz.sht.dao.UserProductLogDao;
import com.itsz.sht.dao.UserProductPaymentDao;
import com.itsz.sht.dao.UserProfileDao;
import com.itsz.sht.dao.hibernate.model.CsParameter;
import com.itsz.sht.dao.hibernate.model.NotificationMedia;
import com.itsz.sht.dao.hibernate.model.Product;
import com.itsz.sht.dao.hibernate.model.RtqAccAsgnId;
import com.itsz.sht.dao.hibernate.model.RtqAccount;
import com.itsz.sht.dao.hibernate.model.RtqAccountAssignment;
import com.itsz.sht.dao.hibernate.model.RtqApplication;
import com.itsz.sht.dao.hibernate.model.ServicesAccessLog;
import com.itsz.sht.dao.hibernate.model.UserProduct;
import com.itsz.sht.dao.hibernate.model.UserProductAllotment;
import com.itsz.sht.dao.hibernate.model.UserProductAllotmentLog;
import com.itsz.sht.dao.hibernate.model.UserProductLog;
import com.itsz.sht.dao.hibernate.model.UserProductPayment;
import com.itsz.sht.dao.hibernate.model.UserProfile;
import com.itsz.sht.dao.hibernate.model.UsrProdId;
import com.itsz.sht.model.RTQAccess;
import com.itsz.sht.model.UserProductRequest;

public class RtqAdminServiceImpl 
	implements RtqAdminService {
		
		private final LoggerFactory log = LoggerFactory.getInstance();
		public final static String MIS_DAY_END_PROCESSING_FLAG = "MisDayEndProcessingFlag";
		
		private NotificationMediaDao notificationMediaDao;
		private ProductDao productDao; 
		private RtqAccountAssignmentDao rtqAccountAssignmentDao;
		private RtqAccountDao rtqAccountDao;
		private RtqApplicationDao rtqApplicationDao;
		private ServicesAccessLogDao servicesAccessLogDao;
		private UserProductAllotmentDao userProductAllotmentDao;
		private UserProductAllotmentLogDao userProductAllotmentLogDao;
		private UserProductDao userProductDao;
		private UserProductLogDao userProductLogDao;
		private UserProductPaymentDao userProductPaymentDao;
		private UserProfileDao userProfileDao;
		private CsParameterDao csParameterDao;
		private AutoPurchaseDao autoPurchaseDao;

	public void setNotificationMediaDao(NotificationMediaDao notificationMediaDao) {
		this.notificationMediaDao = notificationMediaDao;
	}

	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

	public void setRtqAccountAssignmentDao(
			RtqAccountAssignmentDao rtqAccountAssignmentDao) {
		this.rtqAccountAssignmentDao = rtqAccountAssignmentDao;
	}

	public void setRtqAccountDao(RtqAccountDao rtqAccountDao) {
		this.rtqAccountDao = rtqAccountDao;
	}

	public void setRtqApplicationDao(RtqApplicationDao rtqApplicationDao) {
		this.rtqApplicationDao = rtqApplicationDao;
	}

	public void setServicesAccessLogDao(ServicesAccessLogDao servicesAccessLogDao) {
		this.servicesAccessLogDao = servicesAccessLogDao;
	}

	public void setUserProductAllotmentDao(
			UserProductAllotmentDao userProductAllotmentDao) {
		this.userProductAllotmentDao = userProductAllotmentDao;
	}

	public void setUserProductAllotmentLogDao(
			UserProductAllotmentLogDao userProductAllotmentLogDao) {
		this.userProductAllotmentLogDao = userProductAllotmentLogDao;
	}

	public void setUserProductDao(UserProductDao userProductDao) {
		this.userProductDao = userProductDao;
	}

	public void setUserProductLogDao(UserProductLogDao userProductLogDao) {
		this.userProductLogDao = userProductLogDao;
	}

	public void setUserProductPaymentDao(UserProductPaymentDao userProductPaymentDao) {
		this.userProductPaymentDao = userProductPaymentDao;
	}

	public void setUserProfileDao(UserProfileDao userProfileDao) {
		this.userProfileDao = userProfileDao;
	}
		
	public void setCsParameterDao(CsParameterDao csParameterDao) {
		this.csParameterDao = csParameterDao;
	}
	
	public void setAutoPurchaseDao(AutoPurchaseDao autoPurchaseDao) {
		this.autoPurchaseDao = autoPurchaseDao;
	}
				
	public AccessRTQResponseModel accessRTQ(AccessRTQRequestModel accessRTQRequestModel) throws MessagingException{
		AccessRTQResponseModel res =new AccessRTQResponseModel();
		long bt = System.currentTimeMillis();
		Product product = productDao.findRTQProduct(accessRTQRequestModel.getClientId());
		if(product!=null && product.getProdStatus().equals(Consts.AdminPortal.productStatus.AVAIL)){
			UserProduct userProduct=userProductDao.findUserProductByKey(product.getProdId(), accessRTQRequestModel.getClientId());
			if(userProduct!=null && userProduct.getStatus().equals(Consts.AdminPortal.userProductStatus.AVAIL) && userProduct.getExprDate().after(new Date())){
				RtqApplication rtqApplication=rtqApplicationDao.getRTQApplication(product.getProdId());
				if(rtqApplication!=null){
					if(rtqApplication.getRtqStatus().equals(Consts.AdminPortal.rtqStatus.AVAIL)){
						List<RtqAccountAssignment> rtqAccountAssignmentList = rtqAccountAssignmentDao.getRtqAccountAssignment(product.getProdId(), accessRTQRequestModel.getClientId());
						if(rtqAccountAssignmentList.size() == 0){ //if no RTQ Account Assignment
							//find the rest account of RTQ Account by Product ID 									
							List<RtqAccount> notTakenUprtqAccountList= rtqAccountDao.findNotTakenUpAccount(product.getProdId());
                          	if(notTakenUprtqAccountList.size() > 0){ //get a account to customer if have the rest account
								RtqAccount notTakenUprtqAccount=(RtqAccount)notTakenUprtqAccountList.get(0);
								RtqAccountAssignment rtqAccountAssignment = new RtqAccountAssignment();
								RtqAccAsgnId rtqAccAsgnId=new RtqAccAsgnId();
								rtqAccAsgnId.setClntId(userProduct.getId().getClntId());
								rtqAccAsgnId.setProdId(notTakenUprtqAccount.getId().getProdId());
								rtqAccAsgnId.setRtqLognId(notTakenUprtqAccount.getId().getRtqLognId());
								rtqAccountAssignment.setId(rtqAccAsgnId);
								rtqAccountAssignment.setRtqLognPwd(notTakenUprtqAccount.getRtqLognPwd());
								rtqAccountAssignment.setInitBy(userProduct.getId().getClntId());
								rtqAccountAssignment.setInitDate(new Date());
								rtqAccountAssignment.setUpdBy(userProduct.getId().getClntId());
								rtqAccountAssignment.setUpdDate(new Date());
								rtqAccountAssignmentDao.addRtqAccountAssignment(rtqAccountAssignment);
								
								RTQAccess rtqAccess =new RTQAccess();
								rtqAccess.setClientId(accessRTQRequestModel.getClientId());
								rtqAccess.setProductId(product.getProdId());
								rtqAccess.setRtqLoginId(notTakenUprtqAccount.getId().getRtqLognId());
								rtqAccess.setRtqLoginPassword(notTakenUprtqAccount.getRtqLognPwd());
								rtqAccess.setRtqProvider(rtqApplication.getRtqPrdr());
								rtqAccess.setRtqUrl(rtqApplication.getRtqUrl());
								rtqAccess.setStatus(rtqApplication.getRtqStatus());
								res.setRtqAccess(rtqAccess);
							
								 // write  Service Access Log 
								ServicesAccessLog servicesAccessLog=new ServicesAccessLog();	
								servicesAccessLog.setSvcAcesLogId(String.valueOf(System.currentTimeMillis()));
								servicesAccessLog.setAcesChnnl(accessRTQRequestModel.getChannelId());
								servicesAccessLog.setAcesTime(new Date());
								servicesAccessLog.setAcesUrl("");
								servicesAccessLog.setActnBy(accessRTQRequestModel.getClientId());
								servicesAccessLog.setActnDate(new Date());
								servicesAccessLog.setActnType(Consts.AdminPortal.actionType.CREATE);
								servicesAccessLog.setClntId(accessRTQRequestModel.getClientId());
								servicesAccessLog.setProdId(product.getProdId());
								servicesAccessLog.setRemarkss("");
								servicesAccessLog.setRemoteClntIp(accessRTQRequestModel.getClientIp());
								servicesAccessLog.setRtqLognId(notTakenUprtqAccount.getId().getRtqLognId());
								servicesAccessLog.setRtqLognPwd(notTakenUprtqAccount.getRtqLognPwd());
								servicesAccessLog.setRtqPrdr(rtqApplication.getRtqPrdr());
								servicesAccessLog.setRtqStatus(rtqApplication.getRtqStatus());
								servicesAccessLog.setRtqUr(rtqApplication.getRtqUrl());
								servicesAccessLogDao.addServiceAccessLog(servicesAccessLog);
							}else{ // throw a exception if have no rest account
						    	Mail mail = NotificationBuilder.getInstance(AdminConfigUtil.getInstance().getEmailTarget(), product.getProdId(), accessRTQRequestModel.getClientId()).build();
								MailSender.getContainerSender().send(mail);
								res.setReturnCode(Consts.Error.Code.ADMINPROTAL_RTQACCESS_ERROR_NOACCOUNT);
							}						
						}else{ //if have RTQ Account Assignment
							RTQAccess rtqAccess =new RTQAccess();
							rtqAccess.setClientId(accessRTQRequestModel.getClientId());
							rtqAccess.setProductId(product.getProdId());
							rtqAccess.setRtqLoginId(((RtqAccountAssignment)rtqAccountAssignmentList.get(0)).getId().getRtqLognId());
							rtqAccess.setRtqLoginPassword(((RtqAccountAssignment)rtqAccountAssignmentList.get(0)).getRtqLognPwd());
							rtqAccess.setRtqProvider(rtqApplication.getRtqPrdr());
							rtqAccess.setRtqUrl(rtqApplication.getRtqUrl());
							rtqAccess.setStatus(rtqApplication.getRtqStatus());
							res.setRtqAccess(rtqAccess);
							
							 // write  Service Access Log 
							ServicesAccessLog servicesAccessLog=new ServicesAccessLog();	
							servicesAccessLog.setSvcAcesLogId(String.valueOf(System.currentTimeMillis()));
							servicesAccessLog.setAcesChnnl(accessRTQRequestModel.getChannelId());
							servicesAccessLog.setAcesTime(new Date());
							servicesAccessLog.setAcesUrl("");
							servicesAccessLog.setActnBy(accessRTQRequestModel.getClientId());
							servicesAccessLog.setActnDate(new Date());
							servicesAccessLog.setActnType(Consts.AdminPortal.actionType.CREATE);
							servicesAccessLog.setClntId(accessRTQRequestModel.getClientId());
							servicesAccessLog.setProdId(product.getProdId());
							servicesAccessLog.setRemarkss("");
							servicesAccessLog.setRemoteClntIp(accessRTQRequestModel.getClientIp());
							servicesAccessLog.setRtqLognId(((RtqAccountAssignment)rtqAccountAssignmentList.get(0)).getId().getRtqLognId());
							servicesAccessLog.setRtqLognPwd(((RtqAccountAssignment)rtqAccountAssignmentList.get(0)).getRtqLognPwd());
							servicesAccessLog.setRtqPrdr(rtqApplication.getRtqPrdr());
							servicesAccessLog.setRtqStatus(rtqApplication.getRtqStatus());
							servicesAccessLog.setRtqUr(rtqApplication.getRtqUrl());
							servicesAccessLogDao.addServiceAccessLog(servicesAccessLog);
						}					
					}else{
						log.info(bt , "accessRTQ() error:  RtqApplication is UNAVAIL the record of productId:"+product.getProdId());
						res.setReturnCode(Consts.Error.Code.ADMINPROTAL_RTQACCESS_ERROR_RTQACC_UNAVAIL);
					}
				}else {
					 log.info(bt , "accessRTQ() error:  table RtqApplication isn't existing the record of productId:"+product.getProdId());
					 res.setReturnCode(Consts.Error.Code.ADMINPROTAL_SHKACCESS_ERROR_NORTQAPPLICATION);	 
				}
			}else {
				log.info(bt , "accessRTQ() error:  table UserProduct isn't existing the record of productId:"+product.getProdId()+" and  clientId:"+accessRTQRequestModel.getClientId());
				res.setReturnCode(Consts.Error.Code.ADMINPROTAL_SHKACCESS_ERROR_NOUSERPRODUCT);	  
			}					
	    }else {
		    log.info(bt , "accessRTQ() error: clientId: "+accessRTQRequestModel.getClientId() +" have no RTQ product to purchase or reserve  ");
			res.setReturnCode(Consts.Error.Code.ADMINPROTAL_SHKACCESS_ERROR_NOPRODUCT);	
		}		 
		return res;
	}
	
	public AccessSHKResponseModel accessSHK(AccessSHKRequestModel accessSHKRequestModel){
		AccessSHKResponseModel res =new AccessSHKResponseModel();
		long bt = System.currentTimeMillis();	
		Product product = productDao.getProduct(accessSHKRequestModel.getProductId());
		if(product!=null && product.getProdStatus().equals(Consts.AdminPortal.productStatus.AVAIL)){
			UserProduct userProduct=userProductDao.findUserProductByKey(accessSHKRequestModel.getProductId(), accessSHKRequestModel.getClientId());
			if(userProduct!=null && userProduct.getStatus().equals(Consts.AdminPortal.userProductStatus.AVAIL) && userProduct.getExprDate().after(new Date())){
				RtqApplication rtqApplication=rtqApplicationDao.getRTQApplication(accessSHKRequestModel.getProductId());
				if(rtqApplication!=null){
					if(rtqApplication.getRtqStatus().equals(Consts.AdminPortal.rtqStatus.AVAIL)){
						res.setProductId(rtqApplication.getProdId());
						res.setRtqProvider(rtqApplication.getRtqPrdr());
						res.setRtqUrl(rtqApplication.getRtqUrl());
						res.setRtqStatus(rtqApplication.getRtqStatus());
						
						// write  Service Access Log 
						ServicesAccessLog servicesAccessLog=new ServicesAccessLog();	
						servicesAccessLog.setSvcAcesLogId(String.valueOf(System.currentTimeMillis()));
						servicesAccessLog.setAcesChnnl(accessSHKRequestModel.getChannelId());
						servicesAccessLog.setAcesTime(new Date());
						servicesAccessLog.setAcesUrl("");
						servicesAccessLog.setActnBy(accessSHKRequestModel.getClientId());
						servicesAccessLog.setActnDate(new Date());
						servicesAccessLog.setActnType(Consts.AdminPortal.actionType.CREATE);
						servicesAccessLog.setClntId(accessSHKRequestModel.getClientId());
						servicesAccessLog.setProdId(product.getProdId());
						servicesAccessLog.setRemarkss("");
						servicesAccessLog.setRemoteClntIp(accessSHKRequestModel.getClientIp());
						servicesAccessLog.setRtqPrdr(rtqApplication.getRtqPrdr());
						servicesAccessLog.setRtqStatus(rtqApplication.getRtqStatus());
						servicesAccessLog.setRtqUr(rtqApplication.getRtqUrl());
						servicesAccessLogDao.addServiceAccessLog(servicesAccessLog);
					}else{
						log.info(bt , "accessSHK() error: RtqApplication is UNAVAIL the record of productId:"+product.getProdId());
						res.setReturnCode(Consts.Error.Code.ADMINPROTAL_SHKACCESS_ERROR_RTQACC_UNAVAIL);
					}
				}else {
					log.info(bt , "accessSHK() error: it isn't existing the record of productId:"+accessSHKRequestModel.getProductId());
					res.setReturnCode(Consts.Error.Code.ADMINPROTAL_SHKACCESS_ERROR_NORTQAPPLICATION);	  
				}  					   
		   }else{
				log.info(bt , "accessSHK() error:  table UserProduct isn't existing the record of productId:"+accessSHKRequestModel.getProductId()+" and  clientId:"+accessSHKRequestModel.getClientId());
				res.setReturnCode(Consts.Error.Code.ADMINPROTAL_SHKACCESS_ERROR_NOUSERPRODUCT);	  
		   }					
		}else{
			log.info(bt , "accessSHK() error: "+accessSHKRequestModel.getProductId() +" isn't existed ");
			res.setReturnCode(Consts.Error.Code.ADMINPROTAL_SHKACCESS_ERROR_NOPRODUCT);	
		}	
		return res;
	}

	public AccessOrderNotificationResponseModel accessOrderNotification(AccessOrderNotificationRequestModel accessOrderNotificationRequestModel){
		AccessOrderNotificationResponseModel res=new AccessOrderNotificationResponseModel();
		long bt = System.currentTimeMillis();
		Product product = productDao.getProduct(accessOrderNotificationRequestModel.getProductId());
		if(product!=null){
			UserProduct userProduct=userProductDao.findUserProductByKey(accessOrderNotificationRequestModel.getProductId(), accessOrderNotificationRequestModel.getClientId());
			if(userProduct!=null){
				if(product.getProdStatus().equals(Consts.AdminPortal.productStatus.AVAIL)&&
					    userProduct.getStatus().equals(Consts.AdminPortal.userProductStatus.AVAIL)&&
				    	(userProduct.getExprDate().after(new Date()))){
				     res.setReturnCode(Consts.Error.Code.ADMINPROTAL_ORDERNOTIFACATIONACCESS_SUCCESS);	
			     }else{
				    UserProduct newUserProduct=new UserProduct();
					UsrProdId usrProdId=new UsrProdId();
					usrProdId.setProdId(accessOrderNotificationRequestModel.getProductId());
					usrProdId.setClntId(accessOrderNotificationRequestModel.getClientId());
					newUserProduct.setId(usrProdId);
					newUserProduct.setAllwRenl(Consts.AdminPortal.allowRenewal.YES);
					newUserProduct.setExprDate(java.sql.Timestamp.valueOf(Consts.AdminPortal.orderNotification.expireDate));
					newUserProduct.setStatus(Consts.AdminPortal.productStatus.AVAIL);				
					userProductDao.addUserProduct(newUserProduct);
					res.setReturnCode(Consts.Error.Code.ADMINPROTAL_ORDERNOTIFACATIONACCESS_SUCCESS);	
			   }						
		    }else {
			    log.info(bt , "accessOrderNotification() error:  clientId:"+accessOrderNotificationRequestModel.getClientId() +" hava no right to visit");
			    res.setReturnCode(Consts.Error.Code.ADMINPROTAL_ORDERNOTIFACATIONACCESS_ERROR_NORIGHT);	
		    }					
		}else {
		    log.info(bt , "accessOrderNotification() error: "+accessOrderNotificationRequestModel.getProductId() +" isn't existed");
		    res.setReturnCode(Consts.Error.Code.ADMINPROTAL_SHKACCESS_ERROR_NOPRODUCT);	
		}				
				
		return res;
	}

	@Override
	public ListSelectServiceResponseModel listSelectRTQService(ListSelectServiceRequestModel listSelectServiceRequestModel) {
		ListSelectServiceResponseModel res=new ListSelectServiceResponseModel();  
		res.setRtqAccountAssignmentList(rtqAccountAssignmentDao.getRtqAccountByClientId(listSelectServiceRequestModel.getClientId()));
		res.setExistingServiceProductList(userProductDao.getExistingUserProductByClientIdAndRTQ(listSelectServiceRequestModel.getClientId()));
		res.setSubscriberServiceProductList(userProductAllotmentDao.getSubscriberUserProductAllotmentByClientId(listSelectServiceRequestModel.getClientId(), Consts.AdminPortal.allotStatus.RESERVE));
		return res;
	}

	public boolean deleteUserProfile(String clientId) {
		userProfileDao.deleteUserProfile(clientId);
		return true;
	}

	public boolean saveUserProfile(UserProfile userProfile) {
		userProfileDao.addUserProfile(userProfile);
		return true;
	}
	
	@Override
	public UserProductResponseModel updateUserProductAllRenl(UpdateUserProductRequestModel updateUserProductRequestModel) {
		UserProductResponseModel userProductResponseModel = new UserProductResponseModel();	
		final String SHK = "SHK";
		if(updateUserProductRequestModel.getUserProductRequestList().size() >0){
			UserProductRequest userProductRequest = updateUserProductRequestModel.getUserProductRequestList().get(0);	
			if(userProductRequest.getId().getProdId().equals(SHK)){
				UserProduct userProduct = userProductDao.findUserProductById(userProductRequest.getId().getProdId(), userProductRequest.getId().getClntId());
				if(userProduct!=null && !userProduct.getAllwRenl().equals(userProductRequest.getAllwRenl())){
					userProduct.setAllwRenl(userProductRequest.getAllwRenl());	
					userProduct.setUpdBy((userProductRequest.getUpdBy()==null || userProductRequest.getUpdBy().equals(""))?userProductRequest.getId().getClntId():userProductRequest.getUpdBy());
					userProduct.setUpdDate(new Date());
					userProductDao.updateUserProduct(userProduct);
					userProductLogDao.addUserProductLog(getUserProductLog(userProduct, Consts.AdminPortal.actionType.CHANGE));
				}
			}else{
				UserProductAllotment userProductAllotment = userProductAllotmentDao.findReserveUserProductAllotmentByClnId(userProductRequest.getId().getClntId());	
				if(userProductAllotment != null){
					if(!userProductAllotment.getAlltStatus().equals(userProductRequest.getAllwRenl().equals(Consts.AdminPortal.allowRenewal.YES)?Consts.AdminPortal.allotStatus.RESERVEANDAUTO:Consts.AdminPortal.allotStatus.RESERVE)){
						userProductAllotment.setAlltStatus(userProductRequest.getAllwRenl().equals(Consts.AdminPortal.allowRenewal.YES)?Consts.AdminPortal.allotStatus.RESERVEANDAUTO:Consts.AdminPortal.allotStatus.RESERVE);
						userProductAllotment.setUpdBy(userProductRequest.getUpdBy()==null || userProductRequest.getUpdBy().equals("")?userProductRequest.getId().getClntId():userProductRequest.getUpdBy());
						userProductAllotment.setUpdDate(new Date());
						userProductAllotmentDao.updateUserProductAllotment(userProductAllotment);
						userProductAllotmentLogDao.addUserProductAllotmentLog(getUserProductAllotmentLog(userProductAllotment, Consts.AdminPortal.actionType.CHANGE));
					}				
				}else{	
					UserProduct userProduct = userProductDao.findUserProductById(userProductRequest.getId().getProdId(), userProductRequest.getId().getClntId());
					if(userProduct!=null && userProduct.getExprDate().after(new Date()) && !userProduct.getAllwRenl().equals(userProductRequest.getAllwRenl())){
						userProduct.setAllwRenl(userProductRequest.getAllwRenl());	
						userProduct.setUpdBy((userProductRequest.getUpdBy()==null || userProductRequest.getUpdBy().equals(""))?userProductRequest.getId().getClntId():userProductRequest.getUpdBy());
						userProduct.setUpdDate(new Date());
						userProductDao.updateUserProduct(userProduct);
						userProductLogDao.addUserProductLog(getUserProductLog(userProduct, Consts.AdminPortal.actionType.CHANGE));
					}
				}		
			}
		}else{
			log.info(System.currentTimeMillis(), "RtqAdminServiceImpl.updateUserProductAllRenl(): User product is null!");
			userProductResponseModel.setReturnCode(Consts.Error.Code.UPDATE_STATUS_USERPRODUCT_IS_NULL);
		}		
		
		return userProductResponseModel;
	}
		
	@Override
	public PurchaseServiceResponseModel purchaseService(
			PurchaseServiceRequestModel purchaseServiceRequestModel, Long priceInHkd, String debtRemarks, String reference, Date reqTime, Date resTime) {
		PurchaseServiceResponseModel purchaseServiceResponseModel = new PurchaseServiceResponseModel();
		final String SHK = "SHK";
		//user product existed
		UserProduct userProduct = userProductDao.findUserProductById(purchaseServiceRequestModel.getProductId(), purchaseServiceRequestModel.getClientId());
		if(userProduct == null){
			userProduct = new UserProduct();
			userProduct.setId(new UsrProdId(purchaseServiceRequestModel.getClientId(), purchaseServiceRequestModel.getProductId()));
			userProduct.setAllwRenl(purchaseServiceRequestModel.getAllowRenewal());
			userProduct.setExprDate(DateUtil.getThisMonthLastDateofMonth(new Date()));
			userProduct.setStatus(Consts.AdminPortal.userProductStatus.AVAIL);
			userProduct.setInitBy(purchaseServiceRequestModel.getClientId());
			userProduct.setInitDate(new Date());
			userProduct.setUpdBy(purchaseServiceRequestModel.getClientId());
			userProduct.setUpdDate(new Date());
			userProductDao.addUserProduct(userProduct);
			userProductLogDao.addUserProductLog(getUserProductLog(userProduct, Consts.AdminPortal.actionType.CREATE));
		}else{
			userProduct.setAllwRenl(purchaseServiceRequestModel.getAllowRenewal());
			userProduct.setExprDate(DateUtil.getThisMonthLastDateofMonth(new Date()));
			userProduct.setStatus(Consts.AdminPortal.userProductStatus.AVAIL);
			userProduct.setUpdBy(purchaseServiceRequestModel.getClientId());
			userProduct.setUpdDate(new Date());
			userProductDao.updateUserProduct(userProduct);
			userProductLogDao.addUserProductLog(getUserProductLog(userProduct, Consts.AdminPortal.actionType.CHANGE));
		}

		
		UserProductAllotment userProductAllotment;
		if(!purchaseServiceRequestModel.getProductId().equals(SHK)){ //buy RTQ product
			userProductAllotment = userProductAllotmentDao.findReserveUserProductAllotmentByClnId(purchaseServiceRequestModel.getClientId());
			if(userProductAllotment != null){
				//delete the existed User Product Allotment(RESERVE|RESRV_AUTO) and User Product Payment(PENDING)
				userProductAllotmentDao.deleteUserProductAllotmentByClnId(purchaseServiceRequestModel.getClientId());						
				userProductPaymentDao.deleteUserProductPaymentByClnId(purchaseServiceRequestModel.getClientId());
				userProductAllotmentLogDao.addUserProductAllotmentLog(getUserProductAllotmentLog(userProductAllotment, Consts.AdminPortal.actionType.DELETE));
			}
		}
		
		//create User Product Allotment
		userProductAllotment = new UserProductAllotment();
		userProductAllotment.setUsrProdAlltId(String.valueOf(System.currentTimeMillis()));
		userProductAllotment.setClntId(purchaseServiceRequestModel.getClientId());
		userProductAllotment.setProdId(purchaseServiceRequestModel.getProductId());
		userProductAllotment.setEffDate(new Date());
		userProductAllotment.setExpDate(DateUtil.getThisMonthLastDateofMonth(new Date()));
		userProductAllotment.setAlltTime(new Date());
		userProductAllotment.setAlltStatus(Consts.AdminPortal.allotStatus.SUCC);
		String payReqId = String.valueOf(System.currentTimeMillis());
		userProductAllotment.setPayReqId(payReqId);
		userProductAllotment.setInitBy(purchaseServiceRequestModel.getClientId());
		userProductAllotment.setInitDate(new Date());
		userProductAllotment.setUpdBy(purchaseServiceRequestModel.getClientId());
		userProductAllotment.setUpdDate(new Date());
		userProductAllotmentDao.addUserProductAllotment(userProductAllotment);
		userProductAllotmentLogDao.addUserProductAllotmentLog(getUserProductAllotmentLog(userProductAllotment, Consts.AdminPortal.actionType.CREATE));
		
		//create User Product Payment
		UserProductPayment userProductPayment = new UserProductPayment();
		userProductPayment.setUsrProdPayId(payReqId);
		userProductPayment.setClntId(purchaseServiceRequestModel.getClientId());
		userProductPayment.setProdId(purchaseServiceRequestModel.getProductId());
		userProductPayment.setPriceHkd(priceInHkd.toString());
		userProductPayment.setDefDebtAcc(purchaseServiceRequestModel.getDefDebtAcc());
		userProductPayment.setReqSys(Consts.AdminPortal.requestSYS.MIS);
		userProductPayment.setReqTime(reqTime);
		userProductPayment.setPaySatus(Consts.AdminPortal.payStatus.FULLYPAID);
		userProductPayment.setMemoCode(purchaseServiceRequestModel.getProductId().equals(SHK)?Consts.AdminPortal.memeoCode.MDAC:Consts.AdminPortal.memeoCode.MDAE);
		userProductPayment.setDebtRemarks(debtRemarks);
		userProductPayment.setResMessage(reference);
		userProductPayment.setResTime(resTime);
		userProductPayment.setInitBy(purchaseServiceRequestModel.getClientId());
		userProductPayment.setInitDate(new Date());
		userProductPayment.setUpdBy(purchaseServiceRequestModel.getClientId());
		userProductPayment.setUpdDate(new Date());
		userProductPaymentDao.addUserProductPayment(userProductPayment);
		
		return purchaseServiceResponseModel;
	}

	@Override
	public ReserveServiceResponseModel reserveService(ReserveServiceRequestModel reserveServiceRequestModel, Long priceInHkd) {// reserve RTQ product
		ReserveServiceResponseModel reserveServiceResponseModel = new ReserveServiceResponseModel();
		boolean createFlag = true;				
		//get the using user product(RTQ)
		UserProduct userProduct = userProductDao.findAvailUserProductByClientIdAndRTQ(reserveServiceRequestModel.getClientId());
		if(userProduct!=null){ 
			userProduct.setAllwRenl(Consts.AdminPortal.allowRenewal.NO);
			userProduct.setUpdBy(reserveServiceRequestModel.getClientId());
			userProduct.setUpdDate(new Date());
			userProductDao.updateUserProduct(userProduct);
			userProductLogDao.addUserProductLog(getUserProductLog(userProduct, Consts.AdminPortal.actionType.CHANGE));	
		}
		
		//get reserved user product allotment
		UserProductAllotment userProductAllotment = userProductAllotmentDao.findReserveUserProductAllotmentByClnId(reserveServiceRequestModel.getClientId());					
		if(userProductAllotment != null){
			if(reserveServiceRequestModel.getProductId().equals(userProductAllotment.getProdId())){
				createFlag = false;
				if(!userProductAllotment.getAlltStatus().equals(reserveServiceRequestModel.getAllowRenewal().equals(Consts.AdminPortal.allowRenewal.YES)?Consts.AdminPortal.allotStatus.RESERVEANDAUTO:Consts.AdminPortal.allotStatus.RESERVE)){
					userProductAllotment.setAlltStatus(reserveServiceRequestModel.getAllowRenewal().equals(Consts.AdminPortal.allowRenewal.YES)?Consts.AdminPortal.allotStatus.RESERVEANDAUTO:Consts.AdminPortal.allotStatus.RESERVE);
					userProductAllotment.setUpdBy(reserveServiceRequestModel.getClientId());
					userProductAllotment.setUpdDate(new Date());
					userProductAllotmentDao.updateUserProductAllotment(userProductAllotment);
					userProductAllotmentLogDao.addUserProductAllotmentLog(getUserProductAllotmentLog(userProductAllotment, Consts.AdminPortal.actionType.CHANGE));
				}
			}else{
				createFlag = true;
				//delete the already existing User Product Allotment(RESERVE|RESRV_AUTO) and User Product Payment(PENDIG)
				userProductAllotmentDao.deleteUserProductAllotmentByClnId(reserveServiceRequestModel.getClientId());
				userProductAllotmentLogDao.addUserProductAllotmentLog(getUserProductAllotmentLog(userProductAllotment, Consts.AdminPortal.actionType.DELETE));
				userProductPaymentDao.deleteUserProductPaymentByClnId(reserveServiceRequestModel.getClientId());
			}
		}
	
		if(createFlag){
			//create User Product Allotment
			userProductAllotment = new UserProductAllotment();
			userProductAllotment.setUsrProdAlltId(String.valueOf(System.currentTimeMillis()));
			userProductAllotment.setClntId(reserveServiceRequestModel.getClientId());
			userProductAllotment.setProdId(reserveServiceRequestModel.getProductId());
			userProductAllotment.setEffDate(DateUtil.getNextMonthFirstDateofMonth(new Date()));
			userProductAllotment.setExpDate(DateUtil.getNextMonthLastDateofMonth(new Date()));
			userProductAllotment.setAlltTime(new Date());
			userProductAllotment.setAlltStatus(reserveServiceRequestModel.getAllowRenewal().equals(Consts.AdminPortal.allowRenewal.YES)?Consts.AdminPortal.allotStatus.RESERVEANDAUTO:Consts.AdminPortal.allotStatus.RESERVE);
			String payReqId = String.valueOf(System.currentTimeMillis());
			userProductAllotment.setPayReqId(payReqId);
			userProductAllotment.setInitBy(reserveServiceRequestModel.getClientId());
			userProductAllotment.setInitDate(new Date());
			userProductAllotment.setUpdBy(reserveServiceRequestModel.getClientId());
			userProductAllotment.setUpdDate(new Date());
			userProductAllotmentDao.addUserProductAllotment(userProductAllotment);
			userProductAllotmentLogDao.addUserProductAllotmentLog(getUserProductAllotmentLog(userProductAllotment, Consts.AdminPortal.actionType.CREATE));
			
			//create User Product Payment
			UserProductPayment userProductPayment = new UserProductPayment();
			userProductPayment.setUsrProdPayId(payReqId);
			userProductPayment.setClntId(reserveServiceRequestModel.getClientId());
			userProductPayment.setProdId(reserveServiceRequestModel.getProductId());
			userProductPayment.setPriceHkd(priceInHkd.toString());
			userProductPayment.setDefDebtAcc(reserveServiceRequestModel.getDefDebtAcc());
			userProductPayment.setPaySatus(Consts.AdminPortal.payStatus.PENDING);
			userProductPayment.setMemoCode(Consts.AdminPortal.memeoCode.MDAE);
			userProductPayment.setInitBy(reserveServiceRequestModel.getClientId());
			userProductPayment.setInitDate(new Date());
			userProductPayment.setUpdBy(reserveServiceRequestModel.getClientId());
			userProductPayment.setUpdDate(new Date());
			userProductPaymentDao.addUserProductPayment(userProductPayment);
		}
		
		return reserveServiceResponseModel;
	}
			
	@Override
	public UserProductResponseModel cancelReservedUserProduct(UpdateUserProductRequestModel updateUserProductRequestModel) {
		UserProductResponseModel userProductResponseModel = new UserProductResponseModel();	
		if(updateUserProductRequestModel.getUserProductRequestList().size() >0){
			UserProductRequest userProductRequest = updateUserProductRequestModel.getUserProductRequestList().get(0);
			UserProduct userProduct = userProductDao.findAvailUserProductByClientIdAndRTQ(userProductRequest.getId().getClntId());
			if(userProduct!=null && userProduct.getAllwRenl().equals(Consts.AdminPortal.allowRenewal.YES)){
				userProduct.setAllwRenl(Consts.AdminPortal.allowRenewal.NO);
				userProduct.setUpdBy(userProductRequest.getId().getClntId());
				userProduct.setUpdDate(new Date());
				userProductDao.updateUserProduct(userProduct);
				userProductLogDao.addUserProductLog(getUserProductLog(userProduct, Consts.AdminPortal.actionType.CHANGE));
			} else {
				UserProductAllotment userProductAllotment = userProductAllotmentDao.findReserveUserProductAllotmentByClnId(userProductRequest.getId().getClntId());	
				if(userProductAllotment != null){
					//delete the existed User Product Allotment(RESERVE|RESRV_AUTO) and User Product Payment(PENDING)
					userProductAllotmentDao.deleteUserProductAllotmentByClnId(userProductRequest.getId().getClntId());						
					userProductPaymentDao.deleteUserProductPaymentByClnId(userProductRequest.getId().getClntId());
					userProductAllotmentLogDao.addUserProductAllotmentLog(getUserProductAllotmentLog(userProductAllotment, Consts.AdminPortal.actionType.DELETE));
				}	
			}
		}else{
			log.info(System.currentTimeMillis(), "RtqAdminServiceImpl.cancelReservedUserProduct(): User product is null!");
			userProductResponseModel.setReturnCode(Consts.Error.Code.CANCEL_RESERVED_USERPRODUCT_IS_NULL);
		}
		
		return userProductResponseModel;
	}
	
	@Override
	public List<UserProduct> getAvailUserProductList(String clientId) {
		return userProductDao.findUserProductByClientId(clientId, new Date());
	}

	@Override
	public List<Product> getRTQProductList(String cnDiscFlag) {
		return productDao.findRTQProductListByClientCnDiscFlag(cnDiscFlag);
	}		
	
	@Override
	public UserProfile getUserProfile(String clientId) {
		return userProfileDao.getUserProfile(clientId);
	}

	@Override
	public UserProduct findAvailUserProductByKey(String productId,
			String clientId) {
		UserProduct userProduct = null;
		userProduct = userProductDao.findUserProductById(productId, clientId);
		if(userProduct!=null && userProduct.getExprDate().after(new Date())){
			return userProduct;
		}else{
			return null;
		}
	}

	@Override
	public Product findProuctByProductId(String productId) {
		return productDao.getProduct(productId);
	}
		
	public UserNotificationEmailResponseModel updateNotificationMedia(
			UserNotificationEmailRequestModel userNotificationEmailRequestModel) {
		UserNotificationEmailResponseModel userNotificationEmailResponseModel = new UserNotificationEmailResponseModel();
		NotificationMedia notificationMedia = notificationMediaDao.getNotificationMediaByClntId(userNotificationEmailRequestModel.getClientId());
		if(userNotificationEmailRequestModel.isNotiFlag()){
			String prefLange = "TC";
			if(userNotificationEmailRequestModel.getLanguage() != null){
				if(userNotificationEmailRequestModel.getLanguage().equals("ENG")){
					prefLange = "EN";
				}else if(userNotificationEmailRequestModel.getLanguage().equals("GS")){
					prefLange = "SC";
				}else if(userNotificationEmailRequestModel.getLanguage().equals("BIG5")){
					prefLange = "TC";
				}
			}
			if(notificationMedia == null){
				notificationMedia = new NotificationMedia();
				notificationMedia.setNotfMdiaId(String.valueOf(System.currentTimeMillis()));
				notificationMedia.setClntId(userNotificationEmailRequestModel.getClientId());
				notificationMedia.setEmailAddr(userNotificationEmailRequestModel.getUserNotificationEmail());
				notificationMedia.setNotfChnnl("EMAIL");
				notificationMedia.setNotfType("OTCON");
				notificationMedia.setOrdType("YYYYYYYY");					
				notificationMedia.setPrefLang(prefLange);
				notificationMedia.setInitBy(userNotificationEmailRequestModel.getClientId());
				notificationMedia.setInitDate(new Date());
				notificationMedia.setUpdBy(userNotificationEmailRequestModel.getClientId());
				notificationMedia.setUpdDate(new Date());
				notificationMediaDao.addNotificationMedia(notificationMedia);
			}else{
				notificationMedia.setEmailAddr(userNotificationEmailRequestModel.getUserNotificationEmail());
				notificationMedia.setPrefLang(prefLange);
				notificationMedia.setUpdBy(userNotificationEmailRequestModel.getClientId());
				notificationMedia.setUpdDate(new Date());
				notificationMediaDao.updateNotificationMedia(notificationMedia);
			}				
		}else{
			notificationMediaDao.deleteNotificationMedia(notificationMedia.getNotfMdiaId());
		}
			
		return userNotificationEmailResponseModel;
	}
	
	@Override
	public NotificationMedia getNotificationMedia(String clientId) {
		return notificationMediaDao.getNotificationMediaByClntId(clientId);
	}
	
	@Override
	public List<UserProfile> getAllUserProfile(){
		return userProfileDao.listAll();
	}
	
	@Override
	public String getMisDayEndProcessingFlag() {
		CsParameter csParameter = csParameterDao.getCsParameter(MIS_DAY_END_PROCESSING_FLAG);
		if(csParameter!=null && csParameter.getValue()!=null && csParameter.getValue().equals(Consts.AdminPortal.misDayEndProcessingFlag.N)){
			return Consts.AdminPortal.misDayEndProcessingFlag.N;
		}else{
			return Consts.AdminPortal.misDayEndProcessingFlag.Y;
		}
	}
		
	private UserProductLog getUserProductLog(UserProduct userProduct, String actionType){
		UserProductLog userProductLog = new UserProductLog();
		userProductLog.setUsrProdLogId(String.valueOf(System.currentTimeMillis()));
		userProductLog.setAllwRenl(userProduct.getAllwRenl());
		userProductLog.setClntId(userProduct.getId().getClntId());
		userProductLog.setProdId(userProduct.getId().getProdId());
		userProductLog.setExprDate(userProduct.getExprDate());
		userProductLog.setInitBy(userProduct.getInitBy());
		userProductLog.setInitDate(userProduct.getInitDate());
		userProductLog.setStatus(userProduct.getStatus());
		userProductLog.setUpdBy(userProduct.getUpdBy());
		userProductLog.setUpdDate(userProduct.getUpdDate());
		userProductLog.setActnBy(userProduct.getId().getClntId());
		userProductLog.setActnType(actionType);
		userProductLog.setActnDate(new Date());
		return userProductLog;
	}
	
	private UserProductAllotmentLog getUserProductAllotmentLog(UserProductAllotment userProductAllotment, String actionType){
		UserProductAllotmentLog userProductAllotmentLog = new UserProductAllotmentLog();
		userProductAllotmentLog.setUsrProdAlltLogId(String.valueOf(System.currentTimeMillis()));
		userProductAllotmentLog.setAlltStatus(userProductAllotment.getAlltStatus());
		userProductAllotmentLog.setAlltTime(userProductAllotment.getAlltTime());
		userProductAllotmentLog.setClntId(userProductAllotment.getClntId());
		userProductAllotmentLog.setProdId(userProductAllotment.getProdId());
		userProductAllotmentLog.setEffDate(userProductAllotment.getEffDate());
		userProductAllotmentLog.setExpDate(userProductAllotment.getExpDate());
		userProductAllotmentLog.setInitBy(userProductAllotment.getInitBy());
		userProductAllotmentLog.setInitDate(userProductAllotment.getInitDate());
		userProductAllotmentLog.setPayReqId(userProductAllotment.getPayReqId());
		userProductAllotmentLog.setRemarks(userProductAllotment.getRemarks());
		userProductAllotmentLog.setUpdBy(userProductAllotment.getUpdBy());
		userProductAllotmentLog.setUpdDate(userProductAllotment.getUpdDate());
		userProductAllotmentLog.setUsrProdAlltId(userProductAllotment.getUsrProdAlltId());
		userProductAllotmentLog.setActnBy(userProductAllotment.getClntId());
		userProductAllotmentLog.setActnType(actionType);
		userProductAllotmentLog.setActnDate(new Date());
		return userProductAllotmentLog;
	}

	@Override
	public void updateUserProfile(UserProfile userProfile) {
		userProfileDao.updateUserProfile(userProfile);
	}
}
