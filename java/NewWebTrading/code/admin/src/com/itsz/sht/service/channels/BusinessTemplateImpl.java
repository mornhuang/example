package com.itsz.sht.service.channels;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;

import com.itsz.common.util.StringMap;
import com.itsz.sht.common.Consts;
import com.itsz.sht.common.DateUtil;
import com.itsz.sht.common.MemoDebitRemarkUtil;
import com.itsz.sht.common.NotificationConstants;
import com.itsz.sht.common.auto.AutoPurchaseConstants;
import com.itsz.sht.common.auto.Counters;
import com.itsz.sht.common.auto.DistributeUtils;
import com.itsz.sht.common.auto.ProcessStatus;
import com.itsz.sht.common.model.common.LoggerFactory;
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
import com.itsz.sht.common.model.common.request.SaveRTQAccountRequestModel;
import com.itsz.sht.common.model.common.request.ServiceProductRequestModel;
import com.itsz.sht.common.model.common.request.ServicesAccessLogByRtqAccAsgnIdRequestModel;
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
import com.itsz.sht.common.model.common.response.SaveRTQAccountResponseModel;
import com.itsz.sht.common.model.common.response.ServiceProductResponseModel;
import com.itsz.sht.common.model.common.response.ServicesAccessLogByRtqAccAsgnIdResponseModel;
import com.itsz.sht.common.model.common.response.UpdateRTQAccountResponseModel;
import com.itsz.sht.common.model.common.response.UpdateUserPaymentByManualResponseModel;
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
import com.itsz.sht.exception.ESSysException;
import com.itsz.sht.service.ccis.CCISAccountPredicate;		
import com.itsz.sht.service.ccis.CcisService;
import com.itsz.sht.service.datas.AdminService;
import com.itsz.sht.service.memodebit.MemoDebitResult;
import com.itsz.sht.service.memodebit.MemoDebitService;
import com.itsz.sht.service.mis.MisService;
import com.taifook.framework.platform.persist.DBException;
	
public class BusinessTemplateImpl implements BusinessTemplate {

	Log log = LoggerFactory.getInstance().getCommonInfo();
	
	private AdminService adminService ;
	
	private CcisService ccisService;
	
	private MisService misService;
	
	private MemoDebitService memoDebitService;
	
	public void setMisService(MisService misService) {
		this.misService = misService;
	}

	public void setCcisService(CcisService ccisService) {
		this.ccisService = ccisService;
	}

	public void setAdminService(AdminService adminService) {
		this.adminService = adminService;
	}
	
	public void setMemoDebitService(MemoDebitService memoDebitService) {
		this.memoDebitService = memoDebitService;
	}

//	admin 查询是否允许购买内地定价的RTQ
	@Override
	public UserProfileResponseModel findAdminUserProfile(UserProfileRequestModel userProfileRequestModel){
		UserProfileResponseModel res=new UserProfileResponseModel();
		try {
			if(!((userProfileRequestModel.getClientId()==null||userProfileRequestModel.getClientId().equals(""))&&(userProfileRequestModel.getClntLoginId()==null||userProfileRequestModel.getClntLoginId().equals("")))){
				res = adminService.findUserProfile(userProfileRequestModel);				
			}
			res.setReturnCode(Consts.AdminPortal.returnResulst.SUCCESS);
		} catch (Exception e) {
	    	log.info("findUserProfile() error:  "+ userProfileRequestModel.getClientId()+" " +e);
			res.setReturnCode(Consts.AdminPortal.returnResulst.FAILED);
		}
		return res;
	}

	@Override
	 public ExecuteAutoEnrollmentResponseModel executeAutoEnrollment(final String actionBy){
		 ExecuteAutoEnrollmentResponseModel res =new ExecuteAutoEnrollmentResponseModel();
	   try {
		    //如果MIS Day End Processing 没有执行
			if(adminService.getMisDayEndProcessingFlag().equals(Consts.AdminPortal.misDayEndProcessingFlag.N)){
				//把MIS Day End Processing 改为执行状态
				adminService.updateMisDayEndProcessingFlag(Consts.AdminPortal.misDayEndProcessingFlag.Y);	
	   			  final ProcessStatus pStatus = ProcessStatus.getStatus("AutoPurchase",DistributeUtils.getDistributedHashtable("AutoPurchase"));
	   		      pStatus.start();
	   		      final List<UserProduct> userProductList;
	   		      final  List<UserProductAllotment> userProductAllotmentList;
	   		        long time = System.currentTimeMillis();
	   		        try {
	   		        	//获取  Renewal 的业务数据
	   		        	userProductList = adminService.findExeRenewalUserProduct();
		   		         long time1 = System.currentTimeMillis();
		   		         log.info(this.getClass().getName() + ",findRenewalData---"  + (System.currentTimeMillis()-time1) + ",Performance testing:Auto-enrollment.");
		   		        	//获取 auto Purchase 的业务数据
		   		         long time2 = System.currentTimeMillis();
		   		            userProductAllotmentList= adminService.findAutoPurchaseUserProduct();
		   		         log.info(this.getClass().getName() + ",findAutoPurchaseData--" + (System.currentTimeMillis()-time2) + ",Performance testing:Auto-enrollment.");
		   		        } catch (RuntimeException e) {
		   		            pStatus.end();
		   		            throw e;
		   		        }
		   		        log.info(this.getClass().getName() + ",findNeedAutoEnrollmentDate--" + (System.currentTimeMillis()-time) + ",Performance testing:Auto-enrollment.");
		   		        pStatus.setTotalRecord(userProductList.size()+userProductAllotmentList.size());
		   		        Runnable runnable = new Runnable() {
			            @SuppressWarnings("unchecked")
						public void run() {
			                Counters counters = pStatus.getCounters();
			                Map errMap = pStatus.getErrors4Append();
			            	Map sysErrorMap = new HashMap();
			                int processedNumber = 0;   
			                boolean misStatus = true;
			                String debtRemarks = null;
			                Long priceInHkd = null;
			                String status = null;
			                //处理  Renewal 的业务
			                long tim3 = System.currentTimeMillis();
			                for (Iterator<UserProduct> iter = userProductList.iterator(); iter.hasNext(); ) {
			                	processedNumber++;
			                	UserProduct userProduct =(UserProduct)iter.next();
			                	debtRemarks = userProduct.getId().getProdId().equals("SHK")?MemoDebitRemarkUtil.SHK:MemoDebitRemarkUtil.RTQ_SEC_STR_HK;
			                	status = AutoPurchaseConstants.PurchaseStatus.ST_SYS_ERROR;
			                	try {
			                		//待扣款产品
				                	Product product = adminService.getProuctByProductId(userProduct.getId().getProdId());
				                	if(product != null){
				                		priceInHkd = product.getPriceInHkd();
					                	if(product.getProdStatus().equals(Consts.AdminPortal.productStatus.AVAIL)){
						                	if(product.getExprDate().after(new Date())){
						                    	//检查User Profile中保存的Default Debit Account不为空
												UserProfile userProfile= adminService.getUserProfile(userProduct.getId().getClntId());	
												if(userProfile!=null){
													if(!(userProfile.getDefDebtAcc()==null || userProfile.getDefDebtAcc().equals(""))){
														//调用CCIS的api检查设定的Default Debit Account是有效的account
											    	     List<String> accCodeList=ccisService.getAcCodeListByClntStmtID(userProduct.getId().getClntId(),CCISAccountPredicate.FLT_AC_ACTIVE);
											    	     if(accCodeList.contains(userProfile.getDefDebtAcc()))
											    	     {
											    	    	BigDecimal accBalance = null;
											    	    	int flag = -1;
											    	    	if(Consts.AdminPortal.memoDebitSystem.MANUAL.equals(memoDebitService.getMemoDebitSystem())){
											    	    		flag = 0;
											    	    	} else {
												    	    	try{
												    	    		accBalance=misService.getAccBalance(userProfile.getDefDebtAcc()); 
												    	    		flag = accBalance.longValue() >= product.getPriceInHkd() ? 1 : -1;
												    	    	}catch(Exception e){
												    	    		processedNumber--;
												    	    		misStatus = false;
																	log.error("AutoEnrollment[Renewal, UserID:" + userProduct.getId().getClntId() + ", ProductID:"+userProduct.getId().getProdId() 
												                            + "]->Caught " + memoDebitService.getMemoDebitSystem() +  " error, break process. error: " + e);
																	sysErrorMap.put(memoDebitService.getMemoDebitSystem() +  " Error[UserID:" + userProduct.getId().getClntId() + "<br>ProductID:"+userProduct.getId().getProdId() + "]", "Caught " + memoDebitService.getMemoDebitSystem() +  " error, break process. error: " + e);
										                        	errMap.put("SYSERROR", sysErrorMap);
										                        	counters.increment(AutoPurchaseConstants.PurchaseStatus.ST_SYS_ERROR);
										                        	adminService.addAutoPurchase(getAutoPurchase(userProduct.getId().getClntId(), userProduct.getId().getProdId(), status, priceInHkd, debtRemarks, actionBy));
												    	    		break;
												    	    	}
											    	    	}
											    	    	//检查可用账户结余
//											    	    	if(accBalance.intValue() >= product.getPriceInHkd()){
											    	    	if(flag != -1){
											    	    		try {
											    	    			adminService.exeRenewalByUserProduct(userProduct, product.getPriceInHkd(), userProfile.getDefDebtAcc(), actionBy);
											    	    			status = AutoPurchaseConstants.PurchaseStatus.ST_SUCCESS;
											    	    			counters.increment(AutoPurchaseConstants.PurchaseStatus.ST_SUCCESS);
											    	    			adminService.addAutoPurchase(getAutoPurchase(userProduct.getId().getClntId(), userProduct.getId().getProdId(), status, priceInHkd, debtRemarks, actionBy));
											    	    		}catch(Exception e){
											    	    			processedNumber--;
									                            	log.info("AutoEnrollment[Renewal, UserID:" + userProduct.getId().getClntId() + ", ProductID:"+product.getProdId() 
									                            			+ "]->Access database failed, error: " + e);
										                        	sysErrorMap.put("UserID:" + userProduct.getId().getClntId() + "<br>ProductID:"+product.getProdId() , "Access database failed, error: " + e);
										                        	errMap.put("SYSERROR", sysErrorMap);
										                        	counters.increment(AutoPurchaseConstants.PurchaseStatus.ST_SYS_ERROR);
										                        	adminService.addAutoPurchase(getAutoPurchase(userProduct.getId().getClntId(), userProduct.getId().getProdId(), status, priceInHkd, debtRemarks, actionBy));
									                            	continue;
											    	    		}
											    	    	}else{
								                            	processedNumber--;
								                            	status = AutoPurchaseConstants.PurchaseStatus.ST_NOT_ENOUGH_MONEY;
								                            	log.info("AutoEnrollment[Renewal, UserID:" + userProduct.getId().getClntId() + ", ProductID:"+product.getProdId() 
								                            			+ "]->this record need debit cash."
								                            			+ " Not enough money: require:"+product.getPriceInHkd()+" available:"+accBalance.intValue());
									                        	sysErrorMap.put("UserID:" + userProduct.getId().getClntId() + "<br>ProductID:"+product.getProdId() , "Not enough money, require:"+product.getPriceInHkd()+" available:"+accBalance.intValue());
									                        	errMap.put("SYSERROR", sysErrorMap);
									                        	counters.increment(AutoPurchaseConstants.PurchaseStatus.ST_NOT_ENOUGH_MONEY);
									                        	adminService.addAutoPurchase(getAutoPurchase(userProduct.getId().getClntId(), userProduct.getId().getProdId(), status, priceInHkd, debtRemarks, actionBy));
								                            	continue;
									                        }
											    	     }else{
										    	    		processedNumber--;
															log.info("AutoEnrollment[Renewal, UserID:" + userProduct.getId().getClntId() + ", ProductID:"+userProduct.getId().getProdId()
												    				+ "]->In CCIS have no Default Debit Account:"+userProfile.getDefDebtAcc() + ".");
								                        	sysErrorMap.put("UserID:" + userProduct.getId().getClntId() + "<br>ProductID:"+userProduct.getId().getProdId() , "In CCIS have no Default Debit Account:"+userProfile.getDefDebtAcc());
								                        	errMap.put("SYSERROR", sysErrorMap);
								                        	counters.increment(AutoPurchaseConstants.PurchaseStatus.ST_SYS_ERROR);
								                        	adminService.addAutoPurchase(getAutoPurchase(userProduct.getId().getClntId(), userProduct.getId().getProdId(), status, priceInHkd, debtRemarks, actionBy));
								                        	continue;
											    	     }
													}else{
														processedNumber--;
														log.info("AutoEnrollment[Renewal, UserID:" + userProduct.getId().getClntId() + ", ProductID:"+product.getProdId() 
								                            	+ "]->No Default Debit Account:"+userProduct.getId().getClntId() + ".");
							                        	sysErrorMap.put("UserID:" + userProduct.getId().getClntId() + "<br>ProductID:"+product.getProdId() , "No Default Debit Account:"+userProduct.getId().getClntId());
							                        	errMap.put("SYSERROR", sysErrorMap);
							                        	counters.increment(AutoPurchaseConstants.PurchaseStatus.ST_SYS_ERROR);
							                        	adminService.addAutoPurchase(getAutoPurchase(userProduct.getId().getClntId(), userProduct.getId().getProdId(), status, priceInHkd, debtRemarks, actionBy));
							                        	continue;
													}
												}else{
													processedNumber--;
													log.info("AutoEnrollment[Renewal, UserID:" + userProduct.getId().getClntId() + ", ProductID:"+product.getProdId() 
								                            	+ "]->Table user profile have no clientId:"+userProduct.getId().getClntId());
						                        	sysErrorMap.put("UserID:" + userProduct.getId().getClntId() + "<br>ProductID:"+product.getProdId() , "Table user profile have no clientId:"+userProduct.getId().getClntId());
						                        	errMap.put("SYSERROR", sysErrorMap);
						                        	counters.increment(AutoPurchaseConstants.PurchaseStatus.ST_SYS_ERROR);
						                        	adminService.addAutoPurchase(getAutoPurchase(userProduct.getId().getClntId(), userProduct.getId().getProdId(), status, priceInHkd, debtRemarks, actionBy));
						                        	continue;
												}
						                	}else {//product expired
						                		processedNumber--;
						                		
												log.info("AutoEnrollment[Renewal, UserID:" + userProduct.getId().getClntId() + ", ProductID:"+product.getProdId() 
							                            	+ "]->Product["+product.getProdId()+ "] expired, expired date: " + DateUtil.getStringforDate(product.getExprDate()));
					                        	sysErrorMap.put("UserID:" + userProduct.getId().getClntId() + "<br>ProductID:"+product.getProdId() , "Product["+product.getProdId()+ "] expired, expired date: " + DateUtil.getStringforDate(product.getExprDate()));
					                        	errMap.put("SYSERROR", sysErrorMap);
					                        	counters.increment(AutoPurchaseConstants.PurchaseStatus.ST_SYS_ERROR);
					                        	adminService.addAutoPurchase(getAutoPurchase(userProduct.getId().getClntId(), userProduct.getId().getProdId(), status, priceInHkd, debtRemarks, actionBy));
					                        	continue;
						                	}
					                	}else {//product unavail
					                		processedNumber--;
											log.info("AutoEnrollment[Renewal, UserID:" + userProduct.getId().getClntId() + ", ProductID:"+product.getProdId() 
						                            	+ "]->Product["+product.getProdId() + "] unavail.");
				                        	sysErrorMap.put("UserID:" + userProduct.getId().getClntId() + "<br>ProductID:"+product.getProdId() , "Product["+product.getProdId() + "] unavail.");
				                        	errMap.put("SYSERROR", sysErrorMap);
				                        	counters.increment(AutoPurchaseConstants.PurchaseStatus.ST_SYS_ERROR);
				                        	adminService.addAutoPurchase(getAutoPurchase(userProduct.getId().getClntId(), userProduct.getId().getProdId(), status, priceInHkd, debtRemarks, actionBy));
				                        	continue;
					                	}
				                	}else{//no product 
				                		processedNumber--;
										log.info("AutoEnrollment[Renewal, UserID:" + userProduct.getId().getClntId() + ", ProductID:"+userProduct.getId().getProdId() 
					                            	+ "]->Table product have no productId:"+userProduct.getId().getProdId());
			                        	sysErrorMap.put("UserID:" + userProduct.getId().getClntId() + "<br>ProductID:"+userProduct.getId().getProdId() , "Table product have no productId:"+userProduct.getId().getProdId());
			                        	errMap.put("SYSERROR", sysErrorMap);
			                        	counters.increment(AutoPurchaseConstants.PurchaseStatus.ST_SYS_ERROR);
			                        	adminService.addAutoPurchase(getAutoPurchase(userProduct.getId().getClntId(), userProduct.getId().getProdId(), status, priceInHkd, debtRemarks, actionBy));
			                        	continue;
				                	}
								} catch (Exception e) {
									processedNumber--;
									try{
										log.error("AutoEnrollment[Renewal, UserID:" + userProduct.getId().getClntId() + ", ProductID:"+userProduct.getId().getProdId() 
				                            + "]->Caught system error, error: " + e);
										sysErrorMap.put("UserID:" + userProduct.getId().getClntId() + "<br>ProductID:"+userProduct.getId().getProdId(), "Caught system error, error: " + e);
			                        	errMap.put("SYSERROR", sysErrorMap);
			                        	counters.increment(AutoPurchaseConstants.PurchaseStatus.ST_SYS_ERROR);
		                        		adminService.addAutoPurchase(getAutoPurchase(userProduct.getId().getClntId(), userProduct.getId().getProdId(), status, priceInHkd, debtRemarks, actionBy));
		                        	}catch (Exception ex) {
//		                        		log.error("add AutoPurchase error: ", ex);
									}
//			                        break;
								}
			                }
			                log.info("AutoEnrollment->Renewal[TimeCust:"+(System.currentTimeMillis()-tim3)+"]");
			                
			                  //处理 auto Purchase 的业务
			                if(misStatus){
				            	long tim4 = System.currentTimeMillis();
					                for (Iterator<UserProductAllotment> iter = userProductAllotmentList.iterator(); iter.hasNext(); ) {
					                	UserProductAllotment upa=(UserProductAllotment) iter.next();
					                	debtRemarks = upa.getProdId().equals("SHK")?MemoDebitRemarkUtil.SHK:MemoDebitRemarkUtil.RTQ_SEC_STR_HK;
					                	status = AutoPurchaseConstants.PurchaseStatus.ST_SYS_ERROR;
					                	try {
					                		//待扣款产品
						                	Product product = adminService.getProuctByProductId(upa.getProdId());
						                	if(product != null){
						                		priceInHkd = product.getPriceInHkd();
							                	if(product.getProdStatus().equals(Consts.AdminPortal.productStatus.AVAIL)){
								                	if(product.getExprDate().after(new Date())){
														UserProfile userProfile= adminService.getUserProfile(upa.getClntId());
														if(userProfile!=null)
														{
															if(!(userProfile.getDefDebtAcc()==null || userProfile.getDefDebtAcc().equals("")))
															{
																//调用CCIS的api检查设定的Default Debit Account是有效的account
													    	     List<String> accCodeList=ccisService.getAcCodeListByClntStmtID(upa.getClntId(),CCISAccountPredicate.FLT_AC_ACTIVE);
													    	     if(accCodeList.contains(userProfile.getDefDebtAcc()))
													    	     {
													    	    	BigDecimal accBalance = null;
													    	    	int flag = -1;
													    	    	if(Consts.AdminPortal.memoDebitSystem.MANUAL.equals(memoDebitService.getMemoDebitSystem())){
													    	    		flag = 0;
													    	    	} else {
														    	    	try{
														    	    		accBalance=misService.getAccBalance(userProfile.getDefDebtAcc()); 
														    	    		flag = accBalance.longValue() >= product.getPriceInHkd() ? 1 : -1;
														    	    	 }catch(Exception e){
														    	    		processedNumber--;
																			log.error("AutoEnrollment[Auto-Purchase, UserID:" + upa.getClntId() + ", ProductID:"+upa.getProdId() 
														                            + "]->Caught " + memoDebitService.getMemoDebitSystem() + " error, break process. error: " + e);
																			sysErrorMap.put(memoDebitService.getMemoDebitSystem() + " Error[UserID:" + upa.getClntId() + "<br>ProductID:"+upa.getProdId() + "]", "Caught " + memoDebitService.getMemoDebitSystem() + " error, break process. error: " + e);
												                        	errMap.put("SYSERROR", sysErrorMap);
												                        	counters.increment(AutoPurchaseConstants.PurchaseStatus.ST_SYS_ERROR);
												                        	adminService.addAutoPurchase(getAutoPurchase(upa.getClntId(), upa.getProdId(), status, priceInHkd, debtRemarks, actionBy));
														    	    		break;
														    	    	}
														    	    }
													    	    	//检查可用账户结余
//													    	    	if(accBalance.longValue()>=product.getPriceInHkd()){
														    	    if (flag != -1) {
													    	    		try {
													    	    			adminService.exeAutoPurchaseByUserProduct(upa,actionBy);
													    	    			status = AutoPurchaseConstants.PurchaseStatus.ST_SUCCESS;
													    			    	counters.increment(AutoPurchaseConstants.PurchaseStatus.ST_SUCCESS);
													    			    	adminService.addAutoPurchase(getAutoPurchase(upa.getClntId(), upa.getProdId(), status, priceInHkd, debtRemarks, actionBy));
													    	    		}catch(Exception e){
													    	    			processedNumber--;
											                            	log.info("AutoEnrollment[Auto-Purchase, UserID:" + upa.getClntId() + ", ProductID:"+product.getProdId() 
											                            			+ "]->Access database failed, error: " + e);
												                        	sysErrorMap.put("UserID:" + upa.getClntId() + "<br>ProductID:"+product.getProdId() , "Access database failed, error: " + e);
												                        	errMap.put("SYSERROR", sysErrorMap);
												                        	counters.increment(AutoPurchaseConstants.PurchaseStatus.ST_SYS_ERROR);
												                        	adminService.addAutoPurchase(getAutoPurchase(upa.getClntId(), upa.getProdId(), status, priceInHkd, debtRemarks, actionBy));
											                            	continue;
													    	    		}
													    			}else{
													    				processedNumber--;
													    				status = AutoPurchaseConstants.PurchaseStatus.ST_NOT_ENOUGH_MONEY;
													    				log.info("AutoEnrollment[Auto-Purchase, UserID:" + upa.getClntId() + ", ProductID:"+product.getProdId()
													    						+ "]->Not enough money, require:"+product.getPriceInHkd()+" available:"+accBalance.intValue());
											                        	sysErrorMap.put("UserID:" + upa.getClntId() + "<br>ProductID:"+product.getProdId() , "Not enough money, require:"+product.getPriceInHkd()+" available:"+accBalance.intValue());
											                        	errMap.put("SYSERROR", sysErrorMap);
											                        	counters.increment(AutoPurchaseConstants.PurchaseStatus.ST_NOT_ENOUGH_MONEY);
											                        	adminService.addAutoPurchase(getAutoPurchase(upa.getClntId(), upa.getProdId(), status, priceInHkd, debtRemarks, actionBy));
											                        	continue;
													    			}
													    	     }else{
												    	    		processedNumber--;
																	log.info("AutoEnrollment[Auto-Purchase, UserID:" + upa.getClntId() + ", ProductID:"+upa.getProdId()
														    				+ "]->In CCIS have no Default Debit Account:"+userProfile.getDefDebtAcc());
										                        	sysErrorMap.put("UserID:" + upa.getClntId() + "<br>ProductID:"+upa.getProdId() , "In CCIS have no Default Debit Account:"+userProfile.getDefDebtAcc());
										                        	errMap.put("SYSERROR", sysErrorMap);
										                        	counters.increment(AutoPurchaseConstants.PurchaseStatus.ST_SYS_ERROR);
										                        	adminService.addAutoPurchase(getAutoPurchase(upa.getClntId(), upa.getProdId(), status, priceInHkd, debtRemarks, actionBy));
										                        	continue;
													    	     }
															}else{
																processedNumber--;
																log.info("AutoEnrollment[Auto-Purchase, UserID:" + upa.getClntId() + ", ProductID:"+upa.getProdId()
														    				+ "]->No Default Debit Account:" + upa.getClntId());
									                        	sysErrorMap.put("UserID:" + upa.getClntId() + "<br>ProductID:"+upa.getProdId() , "No Default Debit Account:"+userProfile.getDefDebtAcc());
									                        	errMap.put("SYSERROR", sysErrorMap);
									                        	counters.increment(AutoPurchaseConstants.PurchaseStatus.ST_SYS_ERROR);
									                        	adminService.addAutoPurchase(getAutoPurchase(upa.getClntId(), upa.getProdId(), status, priceInHkd, debtRemarks, actionBy));
									                        	continue;
															}	
														}else{
															processedNumber--;
															log.info("AutoEnrollment[Auto-Purchase, UserID:" + upa.getClntId() + ", ProductID:"+upa.getProdId()
														    				+ "]->Table user profile have no clientId:" + upa.getClntId());
								                        	sysErrorMap.put("UserID:" + upa.getClntId() + "<br>ProductID:"+upa.getProdId() , "Table user profile have no clientId:"+upa.getClntId());
								                        	errMap.put("SYSERROR", sysErrorMap);
								                        	counters.increment(AutoPurchaseConstants.PurchaseStatus.ST_SYS_ERROR);
								                        	adminService.addAutoPurchase(getAutoPurchase(upa.getClntId(), upa.getProdId(), status, priceInHkd, debtRemarks, actionBy));
								                        	continue;
														}
								                	}else {//product expired
								                		processedNumber--;
														log.info("AutoEnrollment[Auto-Purchase, UserID:" + upa.getClntId() + ", ProductID:"+product.getProdId() 
									                            	+ "]->Product["+product.getProdId()+ "] expired, expired date: " + DateUtil.getStringforDate(product.getExprDate()));
							                        	sysErrorMap.put("UserID:" + upa.getClntId() + "<br>ProductID:"+product.getProdId() , "Product["+product.getProdId()+ "] expired, expired date: " + DateUtil.getStringforDate(product.getExprDate()));
							                        	errMap.put("SYSERROR", sysErrorMap);
							                        	counters.increment(AutoPurchaseConstants.PurchaseStatus.ST_SYS_ERROR);
							                        	adminService.addAutoPurchase(getAutoPurchase(upa.getClntId(), upa.getProdId(), status, priceInHkd, debtRemarks, actionBy));
							                        	continue;
								                	}
							                	}else {//product unavail
							                		processedNumber--;
													log.info("AutoEnrollment[Auto-Purchase, UserID:" + upa.getClntId() + ", ProductID:"+product.getProdId() 
								                            	+ "]->Product["+product.getProdId() + "] unavail");
						                        	sysErrorMap.put("UserID:" + upa.getClntId() + "<br>ProductID:"+product.getProdId() , "Product["+product.getProdId() + "] unavail");
						                        	errMap.put("SYSERROR", sysErrorMap);
						                        	counters.increment(AutoPurchaseConstants.PurchaseStatus.ST_SYS_ERROR);
						                        	adminService.addAutoPurchase(getAutoPurchase(upa.getClntId(), upa.getProdId(), status, priceInHkd, debtRemarks, actionBy));
						                        	continue;
							                	}
						                	}else{//no product 
						                		processedNumber--;
												log.info("AutoEnrollment[Auto-Purchase, UserID:" + upa.getClntId() + ", ProductID:"+upa.getProdId()
							                            	+ "]->Table product have no productId:"+upa.getProdId());
					                        	sysErrorMap.put("UserID:" + upa.getClntId() + "<br>ProductID:"+upa.getProdId() , "Table product have no productId:"+upa.getProdId());
					                        	errMap.put("SYSERROR", sysErrorMap);
					                        	counters.increment(AutoPurchaseConstants.PurchaseStatus.ST_SYS_ERROR);
					                        	adminService.addAutoPurchase(getAutoPurchase(upa.getClntId(), upa.getProdId(), status, priceInHkd, debtRemarks, actionBy));
					                        	continue;
						                	}
					                	}catch(Exception e){
					                		processedNumber--;
					                		try{
					                			log.error("AutoEnrollment[Auto-Purchase, UserID:" + upa.getClntId() + ", ProductID:"+upa.getProdId()
											    	+ "]->Caught system error, error: " + e);
												sysErrorMap.put("UserID:" + upa.getClntId() + "<br>ProductID:"+upa.getProdId() , "Caught system error, error: " + e);
					                        	errMap.put("SYSERROR", sysErrorMap);
					                        	counters.increment(AutoPurchaseConstants.PurchaseStatus.ST_SYS_ERROR);
				                        		adminService.addAutoPurchase(getAutoPurchase(upa.getClntId(), upa.getProdId(), status, priceInHkd, debtRemarks, actionBy));
				                        	}catch (Exception ex) {
	//			                        		log.error("add AutoPurchase error: ", ex);
											}
	//				                        break;
					                	}
					             }
					             log.info("AutoEnrollment->Auto-Purchase[TimeCust:"+(System.currentTimeMillis()-tim4)+"]");
			                } else {
			                	log.info(memoDebitService.getMemoDebitSystem() + " error, AutoEnrollment->Inexecution Auto-Purchase.");
			                }   
			                
			                long time6 = System.currentTimeMillis();
			                log.info("AutoEnrollment->End process AutoEnrollment. Result " + counters);
			                log.info(this.getClass().getName() + ",AutoEnrollmentTotal," + processedNumber + "," + (time6-tim3) + ",Performance testing:Auto-enrollment.");
			                log.info("AutoEnrollment->Total[TimeCust:"+(System.currentTimeMillis()-tim3)+"]");
			                pStatus.end();
			            }
			       };
			       Thread backendThread = new Thread(runnable);
			        backendThread.start();
			      //把MIS Day End Processing 改为非执行状态
			        adminService.updateMisDayEndProcessingFlag(Consts.AdminPortal.misDayEndProcessingFlag.N);		
					res.setReturnCode(Consts.Error.Code.ADMINPROTAL_EXECUTEAUTOENROLLMENT_SUCCESS);	
	   		   }else{
	   				res.setReturnCode(Consts.Error.Code.ADMINPROTAL_EXECUTEAUTOENROLLMENT_ERROR_FLAG); 
	   		   }   
		} catch (ESSysException e) {
			log.error("exeRenewal() error:  ccis connect is error :",e);
			res.setReturnCode(Consts.Error.Code.ADMINPROTAL_EXEMEMODEBIT_ERROR_CCISDBEXCEPTION);
			adminService.updateMisDayEndProcessingFlag(Consts.AdminPortal.misDayEndProcessingFlag.N);	
		} catch (DBException e) {
			log.error("exeRenewal() error:  mis connect is error :",e);
			res.setReturnCode(Consts.Error.Code.ADMINPROTAL_EXEMEMODEBIT_ERROR_MISDBEXCEPTION);
			adminService.updateMisDayEndProcessingFlag(Consts.AdminPortal.misDayEndProcessingFlag.N);	
		} catch (Exception e) {
			log.error("exeRenewal() error: ",e);
			res.setReturnCode(Consts.Error.Code.ADMINPROTAL_EXECUTEAUTOENROLLMENT_ERROR_SYSTEMERROR);
			adminService.updateMisDayEndProcessingFlag(Consts.AdminPortal.misDayEndProcessingFlag.N);	
		}	
		return res;
	}
	
	private AutoPurchase getAutoPurchase(String clientId, String productId, String status, Long priceInHkd, String debtRemarks, String updBy){
		AutoPurchase autoPurchase = new AutoPurchase();
		autoPurchase.setAutoPurcId(String.valueOf(System.currentTimeMillis()));
		autoPurchase.setClntId(clientId);
		autoPurchase.setProdId(productId);
		autoPurchase.setStatus(status);
		autoPurchase.setPriceInHkd(priceInHkd);
		autoPurchase.setDebtRemarks(debtRemarks);
		autoPurchase.setUpdBy(updBy);
		autoPurchase.setUpdDate(new Date());
		return autoPurchase;
	}
 
  
  @Override
  public ExecuteMemoDebitResponseModel  exeMemoDebit(ExecuteMemoDebitRequestModel executeMemoDebitRequestModel){
	  ExecuteMemoDebitResponseModel res =new ExecuteMemoDebitResponseModel();
	  final String actionBy = executeMemoDebitRequestModel.getActionBy();
		try {
			//如果MIS Day End Processing 没有执行
			if(adminService.getMisDayEndProcessingFlag().equals(Consts.AdminPortal.misDayEndProcessingFlag.N)){
				//把MIS Day End Processing 改为执行状态
				adminService.updateMisDayEndProcessingFlag(Consts.AdminPortal.misDayEndProcessingFlag.Y);
			    final ProcessStatus pStatus = ProcessStatus.getStatus("memoDebit",DistributeUtils.getDistributedHashtable("memoDebit"));
   		        pStatus.start();
				//获取所有的可以进行 Memo Debit处理的数据
			    final List<UserProductAllotment> userProductAllotmentList;
			    long tim=System.currentTimeMillis();
			    userProductAllotmentList=adminService.findMemoDebitUserProductAllotment();
			    log.info(this.getClass().getName() + ",findMemoDebitData--" + (System.currentTimeMillis()-tim) + ",Performance testing:MemoDebit.");
  		        pStatus.setTotalRecord(userProductAllotmentList.size());
				if(userProductAllotmentList!=null&&userProductAllotmentList.size()>0){		
					 Runnable runnable = new Runnable() {
			            @SuppressWarnings({ "unchecked"})
						public void run() {
			                Counters counters = pStatus.getCounters();
			                Map errMap = pStatus.getErrors4Append();
			            	Map sysErrorMap = new HashMap();
			                int processedNumber = 0;     
			                @SuppressWarnings("unused")
							boolean isBreakOut = false;
			                //处理  Renewal 的业务
			                long tim1 = System.currentTimeMillis();         
			                //对每一个 Memo Debit的数据进行扣费以及修改状态
			            	for (int i = 0; i < userProductAllotmentList.size(); i++) {
								UserProductAllotment userProductAllotment=(UserProductAllotment)userProductAllotmentList.get(i);
								try {
									//检查User Profile中保存的Default Debit Account不为空
									UserProfile userProfile= adminService.getUserProfile(userProductAllotment.getClntId());	    	
							    	if(userProfile!=null){
							    		if(!(userProfile.getDefDebtAcc()==null || userProfile.getDefDebtAcc().equals(""))){
											Product product=adminService.getProuctByProductId(userProductAllotment.getProdId());
											String debtRemarks = MemoDebitRemarkUtil.generateMemoRemarks(userProductAllotment.getProdId().equals("SHK")?MemoDebitRemarkUtil.SHK:MemoDebitRemarkUtil.RTQ_SEC_STR_HK, new Date());
											Date reqTime = new Date(System.currentTimeMillis());   //request memo debit time
											MemoDebitResult result = new MemoDebitResult();
											try{
												result = memoDebitService.memoDebit(userProfile.getDefDebtAcc(), BigDecimal.valueOf(product.getPriceInHkd()), userProductAllotment.getProdId().equals("SHK")?"SHK":"RTQ", debtRemarks, "EN");
							    	    	 }catch(Exception e){
							    	    		processedNumber--;
												log.error("MemoDebit[UserID:" + userProductAllotment.getClntId() + ", ProductID:"+userProductAllotment.getProdId() 
							                            + "]->Caught " + memoDebitService.getMemoDebitSystem() + " error, break process. error: " + e);
												sysErrorMap.put(memoDebitService.getMemoDebitSystem() + " Error[UserID:" + userProductAllotment.getClntId() + "<br>ProductID:"+userProductAllotment.getProdId() + "]", "Caught " + memoDebitService.getMemoDebitSystem() + " error, break process. error: " + e);
					                        	errMap.put("SYSERROR", sysErrorMap);
					                        	counters.increment(AutoPurchaseConstants.PurchaseStatus.ST_SYS_ERROR);
					                        	isBreakOut = true;
							    	    		break;
							    	    	}
											Date resTime = new Date(System.currentTimeMillis());   //response memo debit time
											try{
												adminService.updateUserProductPayment(userProductAllotment.getPayReqId(), result.getResultCode(), debtRemarks, reqTime, resTime, actionBy, result.isCompleted());
											}catch(Exception e){
												processedNumber--;
				                            	log.info("MemoDebit[UserID:" + userProductAllotment.getClntId() + ", ProductID:"+product.getProdId() 
				                            			+ "]->Access database failed, error: " + e);
					                        	sysErrorMap.put("UserID:" + userProductAllotment.getClntId() + "<br>ProductID:"+product.getProdId() , "Access database failed, error: " + e);
					                        	errMap.put("SYSERROR", sysErrorMap);
					                        	counters.increment(AutoPurchaseConstants.PurchaseStatus.ST_SYS_ERROR);
												continue;
											}
											if(result.isCompleted()){//memo debit success
												counters.increment(AutoPurchaseConstants.PurchaseStatus.ST_SUCCESS);
											}else{ //memo debit fail
												processedNumber--;
												log.info("MemoDebit[UserID:" + userProductAllotment.getClntId() + ", ProductID:"+product.getProdId() 
														+ "]->ClientId: "+userProductAllotment.getClntId()+" Pay Failure ");
												sysErrorMap.put("UserID:" + userProductAllotment.getClntId() + "<br>ProductID:"+product.getProdId() , " Pay Failure:"+product.getProdId());
					                        	errMap.put("SYSERROR", sysErrorMap);
					                        	counters.increment(AutoPurchaseConstants.PurchaseStatus.ST_SYS_ERROR);
					                        	continue;
											}
										}else{
											processedNumber--;
											log.info("MemoDebit[UserID:" + userProductAllotment.getClntId() + ", ProductID:" + userProductAllotment.getProdId() 
														+ "]->No Default Debit Account:" + userProductAllotment.getClntId());
				                        	sysErrorMap.put("UserID:" + userProductAllotment.getClntId() + "<br>ProductID:"+userProductAllotment.getProdId() , "No Default Debit Account:"+userProductAllotment.getClntId());
				                        	errMap.put("SYSERROR", sysErrorMap);
				                        	counters.increment(AutoPurchaseConstants.PurchaseStatus.ST_SYS_ERROR);
				                        	continue;
							    		}
							    	}else {
							    		processedNumber--;
										log.info("MemoDebit[UserID:" + userProductAllotment.getClntId() + ", ProductID:" + userProductAllotment.getProdId() 
														+ "]->Table user Profile have no clientId"+userProductAllotment.getClntId());
			                        	sysErrorMap.put("UserID:" + userProductAllotment.getClntId() + "<br>ProductID:"+userProductAllotment.getProdId() , "Table user Profile have no clientId"+userProductAllotment.getClntId());
			                        	errMap.put("SYSERROR", sysErrorMap);
			                        	counters.increment(AutoPurchaseConstants.PurchaseStatus.ST_SYS_ERROR);
			                        	continue;
							    	}
								}catch(Exception e){
									processedNumber--;
									log.info("MemoDebit[UserID:" + userProductAllotment.getClntId() + ", ProductID:" + userProductAllotment.getProdId() 
											+ "]->Caught system error, error: " + e);
		                        	sysErrorMap.put("UserID:" + userProductAllotment.getClntId() + "<br>ProductID:"+userProductAllotment.getProdId() , "Caught system error, error:" + e);
		                        	errMap.put("SYSERROR", sysErrorMap);
		                        	counters.increment(AutoPurchaseConstants.PurchaseStatus.ST_SYS_ERROR);
//		                        	isBreakOut = true;
//		                        	break;
								}
							}	
		            	    long time2 = System.currentTimeMillis();
			                pStatus.end();
			                log.info("MemoDebit->End process Memo Debit. Result " + counters);
			                log.info(this.getClass().getName() + ",ProcessMemoDebitTotal," + processedNumber + "," + (time2-tim1) + ",Performance testing:Memo Debit.");
						}
			          };
			          Thread backendThread = new Thread(runnable);
				      backendThread.start();
				      //把MIS Day End Processing 改为非执行状态
				      adminService.updateMisDayEndProcessingFlag(Consts.AdminPortal.misDayEndProcessingFlag.N);
				      res.setReturnCode(Consts.Error.Code.ADMINPROTAL_MEMO_DEBIT_SUCCESS);
				    }
	        }else{ //如果MIS Day End Processing 在执行
				log.info(memoDebitService.getMemoDebitSystem() + " in day end processing!");
				res.setReturnCode(Consts.Error.Code.ADMINPROTAL_EXECUTEAUTOENROLLMENT_ERROR_FLAG);
	        }
	  } catch (DBException e) {
			log.error("exeMemoDebit() error: DBException: ",e);
			res.setReturnCode(Consts.Error.Code.ADMINPROTAL_EXEMEMODEBIT_ERROR_MISDBEXCEPTION);	
			adminService.updateMisDayEndProcessingFlag(Consts.AdminPortal.misDayEndProcessingFlag.N);
	  } catch (Exception e) {
		    log.error("exeMemoDebit() error: ",e);
		    res.setReturnCode(Consts.AdminPortal.returnResulst.FAILED);
		    adminService.updateMisDayEndProcessingFlag(Consts.AdminPortal.misDayEndProcessingFlag.N);
	  }
	  return res;
  }

  @Override
  public List<UserProductAllotment> findMemoDebitUserProductAllotment(){
	  List<UserProductAllotment> userProductAllotmentList =adminService.findMemoDebitUserProductAllotment();
	  return userProductAllotmentList;
  }
  
  
	@Override
	public DeleteRTQAccountResponseModel deleteRTQAccount(
			DeleteRTQAccountRequestModel deleteRTQAccountRequestModel) {
		DeleteRTQAccountResponseModel res=new DeleteRTQAccountResponseModel();
		try {
			res = adminService.deleteRTQAccount(deleteRTQAccountRequestModel);
			res.setReturnCode(Consts.AdminPortal.returnResulst.SUCCESS);
		}catch (Exception e) {
			log.info("deleteRTQAccount() error:  "+deleteRTQAccountRequestModel+" " +e);
			res.setReturnCode(Consts.AdminPortal.returnResulst.FAILED);
		}
		return res;
	}
	
	@Override
	public DeleteRTQAccountLastNResponseModel deleteRTQAccountLastN(
			DeleteRTQAccountLastNRequestModel deleteRTQAccountLastNRequeatModel) {
		DeleteRTQAccountLastNResponseModel res =new DeleteRTQAccountLastNResponseModel();
		try {
			res = adminService.deleteRTQAccountLastN(deleteRTQAccountLastNRequeatModel);
			res.setReturnCode(Consts.AdminPortal.returnResulst.SUCCESS);
		} catch (Exception e) {
			log.info("deleteRTQAccountLastN() error:  "+deleteRTQAccountLastNRequeatModel+" " +e);
			res.setReturnCode(Consts.AdminPortal.returnResulst.FAILED);			
		}
		return res;
	}
	
	@Override
	public UserProductResponseModel findCancelUserProductByClientId(UserProductRequestModel userProductRequestModel) {
		UserProductResponseModel res =new UserProductResponseModel();
		try {
			res = adminService.findCancelUserProductByClientId(userProductRequestModel);
			res.setReturnCode(Consts.AdminPortal.returnResulst.SUCCESS);
		} catch (Exception e) {
			log.info( "findCancelUserProductByClientId() error:  "+userProductRequestModel.getClientId()+" " +e);
			res.setReturnCode(Consts.AdminPortal.returnResulst.FAILED);
		}
		return res;
	}
	
	@Override
	public UserProductResponseModel findRenewalOptionByClientId(UserProductRequestModel userProductRequestModel) {
		UserProductResponseModel res =new UserProductResponseModel();
		try {
			res = adminService.findRenewalOptionByClientId(userProductRequestModel);
			res.setReturnCode(Consts.AdminPortal.returnResulst.SUCCESS);
		} catch (Exception e) {
			log.info("findRenewalOptionByClientId() error:  "+userProductRequestModel.getClientId()+" " +e);
			res.setReturnCode(Consts.AdminPortal.returnResulst.FAILED);			
		}
		return res;
	}
	
	@Override
	public UserProductResponseModel findUserProductStautsByClientId(UserProductRequestModel userProductRequestModel) {
		UserProductResponseModel res =new UserProductResponseModel();
		try {
			res = adminService.findUserProductStautsByClientId(userProductRequestModel);
			res.setReturnCode(Consts.AdminPortal.returnResulst.SUCCESS);
		} catch (Exception e) {
			log.info("findUserProductStautsByClientId() error:  "+ userProductRequestModel.getClientId()+" " +e);
			res.setReturnCode(Consts.AdminPortal.returnResulst.FAILED);
		}
		return res;
	}
	
	
	@Override
	public RTQAccountViewResponseModel listRTQAccountView() {
		RTQAccountViewResponseModel res =new RTQAccountViewResponseModel();
		try {
			res = adminService.listRTQAccountView();
			res.setReturnCode(Consts.AdminPortal.returnResulst.SUCCESS);
		} catch (Exception e) {
			log.info("listRTQAccountView() error：  "+e);
			res.setReturnCode(Consts.AdminPortal.returnResulst.FAILED);
		}	
		return res;
	}
	
	@Override
	public ListRTQApplicationResponseModel listRTQApplication() {
		ListRTQApplicationResponseModel res=new ListRTQApplicationResponseModel();
		try {
			res = adminService.listRTQApplication();
			res.setReturnCode(Consts.AdminPortal.returnResulst.SUCCESS);
		} catch (Exception e) {
			log.info("listRTQApplication() error：  "+e);
			res.setReturnCode(Consts.AdminPortal.returnResulst.FAILED);
		}
		return res;
	}
	
	@Override
	public ListSelectServiceResponseModel listSelectService(
			ListSelectServiceRequestModel listSelectServiceRequestModel) {
		ListSelectServiceResponseModel res=new ListSelectServiceResponseModel();  
		try {
			res = adminService.listSelectService(listSelectServiceRequestModel);
			res.setReturnCode(Consts.AdminPortal.returnResulst.SUCCESS);
		} catch (Exception e) {
			log.info("listSelectService() error:  "+ listSelectServiceRequestModel.getClientId()+" " +e);
			res.setReturnCode(Consts.AdminPortal.returnResulst.FAILED);
	    }	
		return res;
	}
	
	
	@Override
	public AddProductResponseModel addProduct(
			AddProductRequestModel addProductRequestModel) {
		AddProductResponseModel res =new AddProductResponseModel();
		try {
			res = adminService.addProduct(addProductRequestModel);
		} catch (Exception e) {
			log.info("addProduct() error: " +e);
			res.setReturnCode(Consts.AdminPortal.returnResulst.FAILED);
		}
		return res;
	}
	
	@Override
	public ListServiceProductResponseModel listServiceProduct() {
		ListServiceProductResponseModel res =new ListServiceProductResponseModel();
		try {
			res = adminService.listServiceProduct();
			res.setReturnCode(Consts.AdminPortal.returnResulst.SUCCESS);
		} catch (Exception e) {
			log.info("listServiceProduct()  error: "+" " +e);
			res.setReturnCode(Consts.AdminPortal.returnResulst.FAILED);
		}
		return res;
	}
	
	
	
	@Override
	public ReleaseRTQAccountAssignmentResponseModel releaseRTQAccountAssignment(
			ReleaseRTQAccountAssignmentRequestModel releaseRTQAccountAssignmentRequestModel) {
		ReleaseRTQAccountAssignmentResponseModel res = new ReleaseRTQAccountAssignmentResponseModel();
		try {
			res = adminService.releaseRTQAccountAssignment(releaseRTQAccountAssignmentRequestModel);
			adminService.allotRTQAccountAssignment();
		} catch (Exception e) {
			log.info("releaseRTQAccountAssignment() error:  "+ releaseRTQAccountAssignmentRequestModel+" " +e);
			res.setReturnCode(Consts.Error.Code.ADMINPROTAL_RELEASERTQACCOUNT_FAILD);
		}
		return res;
	}
	
	
	
	@Override
	public SaveRTQAccountResponseModel saveRTQAccount(
			SaveRTQAccountRequestModel saveRTQAccountRequestModel) {
		SaveRTQAccountResponseModel res =new SaveRTQAccountResponseModel();
		try {
			res = adminService.saveRTQAccount(saveRTQAccountRequestModel);
			res.setReturnCode(Consts.AdminPortal.returnResulst.SUCCESS);
		} catch (Exception e) {
			log.info("saveRTQAccount() error:  "+ saveRTQAccountRequestModel+"  " +e);
			res.setReturnCode(Consts.AdminPortal.returnResulst.FAILED);
		}	
		return res;
	}
	
	@Override
	public UserProductResponseModel updateCancelUserProduct(
			UpdateUserProductRequestModel updateUserProductRequestModel) {
		UserProductResponseModel res =new UserProductResponseModel();
		try {
			res = adminService.updateCancelUserProduct(updateUserProductRequestModel);
			res.setReturnCode(Consts.AdminPortal.returnResulst.SUCCESS);
		} catch (Exception e) {
			log.info("updateCancelUserProduct() error:  "+updateUserProductRequestModel.getUserProductRequestList()+" " +e);
			res.setReturnCode(Consts.AdminPortal.returnResulst.FAILED);
		}
		return res;
	}
	
	@Override
	public ChangeProductResponseModel updateNoEmailProduct(
			ChangeNoEmailProductRequestModel changeNoEmailProductRequestModel) {
		ChangeProductResponseModel res = new ChangeProductResponseModel();
		try {
			res = adminService.updateNoEmailProduct(changeNoEmailProductRequestModel);
			res.setReturnCode(Consts.AdminPortal.returnResulst.SUCCESS);
		} catch (Exception e) {
			log.info(" updateNoEmailProduct() error: "+changeNoEmailProductRequestModel+" " +e);
			res.setReturnCode(Consts.AdminPortal.returnResulst.FAILED);
		}
		return res;
	}
	
	@Override
	public UpdateRTQAccountResponseModel updateRTQAccount(
			UpdateRTQAccountRequestModel updateRTQAccountRequestModel) {
		UpdateRTQAccountResponseModel res =new UpdateRTQAccountResponseModel();
		try {
			res = adminService.updateRTQAccount(updateRTQAccountRequestModel);
			res.setReturnCode(Consts.AdminPortal.returnResulst.SUCCESS);
		} catch (Exception e) {
			log.info("updateRTQAccount() error:  "+updateRTQAccountRequestModel+" " +e);
			res.setReturnCode(Consts.AdminPortal.returnResulst.FAILED);
		}	
		return res;
	}
	
	@Override
	public ChangeRTQApplicationResponseModel updateRTQApplication(
			ChangeRTQApplicationRequestModel changeRTQApplicationRequestModel) {
		ChangeRTQApplicationResponseModel res=new ChangeRTQApplicationResponseModel();
		try {
			res = adminService.updateRTQApplication(changeRTQApplicationRequestModel);
			res.setReturnCode(Consts.AdminPortal.returnResulst.SUCCESS);
		} catch (Exception e) {
			log.info(" updateRTQApplication() error: " +e);
			res.setReturnCode(Consts.AdminPortal.returnResulst.FAILED);
		}	
		return res;
	}
	
	@Override
	public ChangeProductResponseModel updateRTQ_SHKProduct(
			ChangeRTQ_SHKProductRequestModel changeRTQSHKProductRequestModel) {
		ChangeProductResponseModel res = new ChangeProductResponseModel();
		try {
			res = adminService.updateRTQ_SHKProduct(changeRTQSHKProductRequestModel);
		} catch (Exception e) {
			 log.info(" updateRTQ_SHKProduct() error:  " +e);
			 res.setReturnCode(Consts.AdminPortal.returnResulst.FAILED);
		}
		return res;
	}
	
	@Override
	public UserProductResponseModel updateRenewalOption(
			UpdateUserProductRequestModel updateUserProductRequestModel) {
		UserProductResponseModel res =new UserProductResponseModel();
		try {
			res = adminService.updateRenewalOption(updateUserProductRequestModel);
			res.setReturnCode(Consts.AdminPortal.returnResulst.SUCCESS);
		} catch (Exception e) {
			log.info("updateRenewalOption() error:  "+updateUserProductRequestModel.getUserProductRequestList()+" " +e);
			res.setReturnCode(Consts.AdminPortal.returnResulst.FAILED);
		}
		return res;
	}
	
	@Override
	public UserProductResponseModel updateUserProductStatus(
			UpdateUserProductRequestModel updateUserProductRequestModel) {
		UserProductResponseModel res =new UserProductResponseModel();
		try {
			res = adminService.updateUserProductStatus(updateUserProductRequestModel);
			res.setReturnCode(Consts.AdminPortal.returnResulst.SUCCESS);
		} catch (Exception e) {
			log.info("updateUserProductStatus() error:  "+ updateUserProductRequestModel.getUserProductRequestList()+" " +e);
			res.setReturnCode(Consts.AdminPortal.returnResulst.FAILED);
		}
		return res;
	}
	
	@Override
	public MonthlyPurchaseEnquiryResponseModel monthlyPurchaseEnquiry(
			MonthlyPurchaseEnquiryRequestModel monthlyPurchaseEnquiryRequestModel) {
		MonthlyPurchaseEnquiryResponseModel res =new MonthlyPurchaseEnquiryResponseModel();
		try {
			res = adminService.monthlyPurchaseEnquiry(monthlyPurchaseEnquiryRequestModel);
			res.setReturnCode(Consts.AdminPortal.returnResulst.SUCCESS);
		} catch (Exception e) {
			log.info("monthlyPurchaseEnquiry()  error: "+monthlyPurchaseEnquiryRequestModel+" " +e);
			res.setReturnCode(Consts.AdminPortal.returnResulst.FAILED);
		}
		return res;
	}
	
	
	
	@Override
	public ReserveAndRenewalEnquiryResponseModel findReserveAndRenewalEnquiry(ReserveAndRenewalEnquiryRequestModel rsRModel){
		ReserveAndRenewalEnquiryResponseModel res =new ReserveAndRenewalEnquiryResponseModel();
	try {
		res.setPage(adminService.findReserveAndRenewalEnquiry(rsRModel.getPageNumber(), rsRModel.getPageSize(), rsRModel.getProductId()));
		res.setReturnCode(Consts.AdminPortal.returnResulst.SUCCESS);
	} catch (Exception e) { 
		log.info("reserveAndRenewalEnquiryEnquiry()  error: "+rsRModel+" " +e);
		res.setReturnCode(Consts.AdminPortal.returnResulst.FAILED);
	}
		return res;
	}
	
	@Override
	public RTQAccountAssignmentResponseModel findRTQAccountAssignmentByProductId(
			RTQAccountAssignmentRequestModel rtqAccountAssignmentRequestModel) {
		RTQAccountAssignmentResponseModel res =new RTQAccountAssignmentResponseModel();
		try {
			res = adminService.findRTQAccountAssignmentByProductId(rtqAccountAssignmentRequestModel);
			res.setReturnCode(Consts.AdminPortal.returnResulst.SUCCESS);
		} catch (Exception e) {
			log.info("findRTQAccountAssignmentByProductId()  error: "+rtqAccountAssignmentRequestModel +" " +e);
			res.setReturnCode(Consts.AdminPortal.returnResulst.FAILED);
		}
		return res;
	}
	
	@Override
	public RTQAccountResponseModel listRtqAccountByProIdOrLoginId(
			RTQAccountRequestModel rtqAccountRequestModel) {
		RTQAccountResponseModel res=new RTQAccountResponseModel();
		try {
			res = adminService.listRtqAccountByProIdOrLoginId(rtqAccountRequestModel);
			res.setReturnCode(Consts.AdminPortal.returnResulst.SUCCESS);
		} catch (Exception e) {
			log.info("listRtqAccountByProIdOrLoginId()  error: "+rtqAccountRequestModel +" " +e);
			res.setReturnCode(Consts.AdminPortal.returnResulst.FAILED);
		}
		return res;
	}	
	
	@Override
	public RTQApplicationResponseModel findRTQApplicationByProductId(
			RTQApplicationResquestModel rtqApplicationResquestModel) {
		RTQApplicationResponseModel res =new RTQApplicationResponseModel();
		try {
			res = adminService.findRTQApplicationByProductId(rtqApplicationResquestModel);
			res.setReturnCode(Consts.AdminPortal.returnResulst.SUCCESS);
		} catch (Exception e) {
			log.info("findRTQApplicationByProductId()  error: "+rtqApplicationResquestModel+" " +e);
			res.setReturnCode(Consts.AdminPortal.returnResulst.FAILED);
		}
		return res;
	}
	
	@Override
	public ServiceProductResponseModel findServiceProductByProductId(
			ServiceProductRequestModel serviceProductRequestModel) {
		ServiceProductResponseModel res =new ServiceProductResponseModel();
		try {
			res = adminService.findServiceProductByProductId(serviceProductRequestModel);
			res.setReturnCode(Consts.AdminPortal.returnResulst.SUCCESS);
		} catch (Exception e) {
			log.info("findServiceProductByProductId()  error: "+serviceProductRequestModel+" " +e);
			res.setReturnCode(Consts.AdminPortal.returnResulst.FAILED);
		}
		return res;
	}
	
	@Override
	public String addAclRole(AclRole aclRole) {
		String returnCode="";
		try {
			returnCode = adminService.addAclRole(aclRole);
		} catch (Exception e) {
			log.info("addAclRole() error:  " +e);
			returnCode=Consts.AdminPortal.returnResulst.FAILED;
		}
		return returnCode;
	}
	
	@Override
	public String addAclUser(AclUserProfile aclUser) {
		String returnCode="";
		try {
			returnCode = adminService.addAclUser(aclUser);
		} catch (Exception e) {
			log.info("addAclUser() error:  " +e);
			returnCode=Consts.AdminPortal.returnResulst.FAILED;
		}
		return returnCode;
	}
	
	@Override
	public String deleteAclUser(String loginId) {
		String returnCode = "";
		try {
			returnCode = adminService.deleteAclUser(loginId);
		} catch (Exception e) {
			log.info("deleteAclUser() error:  " +e);
			returnCode=Consts.AdminPortal.returnResulst.FAILED;
		}
		return returnCode;
	}
	
	@Override
	public String deleteRole(String roleId) {
		String returnCode="";
		try {
			returnCode = adminService.deleteRole(roleId);
		} catch (Exception e) {
			log.info("deleteRole() error:  " +e);
			returnCode=Consts.AdminPortal.returnResulst.FAILED;
		}
		return returnCode;
	}
	
	@Override
	public AclRoleResponseModel getAclRole(String aclRoleId) {
		AclRoleResponseModel res=new AclRoleResponseModel();
		try {
			res = adminService.getAclRole(aclRoleId);
		} catch (Exception e) {
			log.info("getAclRole() error:  " +e);
			res.setReturnCode(Consts.AdminPortal.returnResulst.FAILED);
		}
		return res;
	}
	
	@Override
	public AclUserResponseModel getAclUser(String loginId) {
		AclUserResponseModel res=new AclUserResponseModel();
		try {
			res = adminService.getAclUser(loginId);
			res.setReturnCode(Consts.AdminPortal.returnResulst.SUCCESS);
		} catch (Exception e) {
			log.info("getAclUser() error:  " +e);
			res.setReturnCode(Consts.AdminPortal.returnResulst.FAILED);
		}
		return res;
	}
	
	@Override
	public AclRoleResponseModelList listAclRole(StringMap parmsMap, Integer pageNumber, Integer pageSize) {
		AclRoleResponseModelList res=new AclRoleResponseModelList();
		try {
			res = adminService.listAclRole(parmsMap, pageNumber, pageSize);
			res.setReturnCode(Consts.AdminPortal.returnResulst.SUCCESS);
		} catch (Exception e) {
			log.info("listAclRole() error:  " +e);
			res.setReturnCode(Consts.AdminPortal.returnResulst.FAILED);
		}
		return res;
	}
	
	@Override
	public AclUserResponseModelList listAclUser(StringMap parmsMap, Integer pageNumber, Integer pageSize) {
		AclUserResponseModelList res = new AclUserResponseModelList();
		try {
			res = adminService.listAclUser(parmsMap, pageNumber, pageSize);
			res.setReturnCode(Consts.AdminPortal.returnResulst.SUCCESS);
	    } catch (Exception e) {
	    	log.info("listAclUser() error:  " +e);
			res.setReturnCode(Consts.AdminPortal.returnResulst.FAILED);
	    }
	    return res;
	}
	
	@Override
	public String updateAclUser(AclUserProfile aclUser) {
		String returnCode = "";
		try {
			returnCode = adminService.updateAclUser(aclUser);
		} catch (Exception e) {
			log.info("updateAclUser() error:  " +e);
			returnCode=Consts.AdminPortal.returnResulst.FAILED;
		}
		return returnCode;
	}
	
	@Override
	public String updateAclRole(AclRole aclRole) {
		String returnCode = "";
		try {
			returnCode = adminService.updateAclRole(aclRole);
		} catch (Exception e) {
			log.info("updateAclUser() error:  " +e);
			returnCode=Consts.AdminPortal.returnResulst.FAILED;
		}
		return returnCode;
	}
	
	@Override
	public AclUserResponseModel loginAclUserProfile(String lognId) {
		AclUserResponseModel model=new AclUserResponseModel();
		try {
			model = adminService.loginAclUserProfile(lognId);
			model.setReturnCode(Consts.AdminPortal.returnResulst.SUCCESS);
		} catch (Exception e) {
			log.info("loginAclUserProfile() error:  " +e);
			model.setReturnCode(Consts.AdminPortal.returnResulst.FAILED);
		}
		return model;
	}
	
	@Override
	public AclRoleResponseModelList listAclRole() {
		AclRoleResponseModelList res=new AclRoleResponseModelList();
		try {
			res = adminService.listAclRole();
			res.setReturnCode(Consts.AdminPortal.returnResulst.SUCCESS);
		} catch (Exception e) {
			res.setReturnCode(Consts.AdminPortal.returnResulst.FAILED);
		}
		return res;
	}
  
	@Override
	public NotificationMediaListResponseModel exportNotificationMedia(
			NotificationMediaListRequestModel notificationMediaListRequestModel) {
		NotificationMediaListResponseModel notificationMediaListResponseModel = new NotificationMediaListResponseModel();
		ByteArrayOutputStream baos = null;
		PrintWriter pw = null;
		try{
			List<NotificationMedia> resultList = (ArrayList<NotificationMedia>)adminService.getNotificationMediaByType(notificationMediaListRequestModel.getNotfType());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
			baos = new ByteArrayOutputStream();
	        pw= new PrintWriter(baos);
            pw.print("0");
            pw.print(sdf.format(new Date())); //17 chars
            pw.print(StringUtils.leftPad("CP_SUB_OVR", 10)); //10 chars
            pw.println(StringUtils.leftPad("NewWebTrading",50)); // 50 chars
			for(int i=0; i<resultList.size(); i++){
            	NotificationMedia otificationMedia = (NotificationMedia)resultList.get(i);
                pw.print(StringUtils.trimToEmpty("1"));
                pw.print(StringUtils.leftPad(otificationMedia.getClntId(), 20));
                pw.print(StringUtils.leftPad(otificationMedia.getNotfType(), 10));
                pw.print(StringUtils.leftPad(otificationMedia.getNotfChnnl(), 10));
                pw.print(StringUtils.leftPad(otificationMedia.getPrefLang(), 2));
                pw.print(StringUtils.leftPad(NotificationConstants.DB.NOTIFICATION_CNNL_EMAIL.equals(otificationMedia.getNotfChnnl())?otificationMedia.getEmailAddr():"", 50));//email
                pw.print(StringUtils.leftPad("", 30));//SMS Phone Number
                pw.print(StringUtils.leftPad("", 30));//SMS Provider
                pw.print(StringUtils.leftPad("", 5));//SMS Region Code
                pw.print(StringUtils.leftPad("", 20));//SMS Country Code
                pw.print(StringUtils.leftPad("", 30));//fax number
                pw.print(StringUtils.leftPad("", 5));//fax country code
                pw.print(StringUtils.leftPad("", 20));//fax country code
                if(otificationMedia.getOrdType()!=null && !otificationMedia.getOrdType().equals("")){
//	                List orderList = Arrays.asList(StringUtil2.partitionString(otificationMedia.getOrdType(), ","));
//	                pw.print(StringUtils.leftPad(orderList.contains(NotificationConstants.DB.NOTIFICATION_ORDER_TYPE_INPUT_RECEIVE)?"Y":"N", 1));
//	                pw.print(StringUtils.leftPad(orderList.contains(NotificationConstants.DB.NOTIFICATION_ORDER_TYPE_INPUT_REJECT)?"Y":"N", 1));
//	                pw.print(StringUtils.leftPad(orderList.contains(NotificationConstants.DB.NOTIFICATION_ORDER_TYPE_AMEND_RECEIVE)?"Y":"N", 1));
//	                pw.print(StringUtils.leftPad(orderList.contains(NotificationConstants.DB.NOTIFICATION_ORDER_TYPE_AMEND_REJECT)?"Y":"N", 1));
//	                pw.print(StringUtils.leftPad(orderList.contains(NotificationConstants.DB.NOTIFICATION_ORDER_TYPE_CANCEL_RECEIVE)?"Y":"N", 1));
//	                pw.print(StringUtils.leftPad(orderList.contains(NotificationConstants.DB.NOTIFICATION_ORDER_TYPE_CANCEL_REJECT)?"Y":"N", 1));
//	                pw.print(StringUtils.leftPad(orderList.contains(NotificationConstants.DB.NOTIFICATION_ORDER_TYPE_CANCEL_FULL)?"Y":"N", 1));
//	                pw.print(StringUtils.leftPad(orderList.contains(NotificationConstants.DB.NOTIFICATION_ORDER_TYPE_COMPLETED)?"Y":"N", 1));
                	pw.print(StringUtils.leftPad(otificationMedia.getOrdType(), 8));
                }else{
                	pw.print(StringUtils.leftPad(" ", 8));
                }
                pw.println(otificationMedia.getUpdDate() == null ? sdf.format(otificationMedia.getInitDate()) : sdf.format(otificationMedia.getUpdDate()));
            }
			pw.print("9");
            pw.println(StringUtils.leftPad("NewWebTrading", 50));
            pw.flush();
            notificationMediaListResponseModel.setBa(baos.toByteArray());            
            notificationMediaListResponseModel.setReturnCode(Consts.AdminPortal.returnResulst.SUCCESS);
		}catch(Exception e){
			log.error("RTQServiceImpl.exportNotificationMedia():  ",e);
			notificationMediaListResponseModel.setReturnCode(Consts.Error.Code.EXPORT_NOTIFICATIONMEDIA_FAILED);
		}finally{
			if (baos != null){
        		try {
					baos.close();
				} catch (IOException e) {
					log.error("RTQServiceImpl.exportNotificationMedia():IOException while close ByteArrayOutputStream.");
				}
        	}
        	if (pw != null){
        		pw.close();
        	}
		}
		return notificationMediaListResponseModel;
	}

	@Override
	public ListRtqAccountAssResponseModel listRtqAccountAssByProductId(
			ListRtqAccountAssRequestModel ListRtqAccountAssRequestModel) {
		ListRtqAccountAssResponseModel res =new ListRtqAccountAssResponseModel();
		try {
			res = adminService.listRtqAccountAssByProductId(ListRtqAccountAssRequestModel);
			res.setReturnCode(Consts.AdminPortal.returnResulst.SUCCESS);
		} catch (Exception e) {
			log.info("listRtqAccountAssByProductId() error:  productId: "+ListRtqAccountAssRequestModel.getProductId()+" " +e);
			res.setReturnCode(Consts.AdminPortal.returnResulst.FAILED);
		}
		return res;
	}

	@Override
	public ServicesAccessLogByRtqAccAsgnIdResponseModel getServicesAccessLogByRtqAccAsgnId(ServicesAccessLogByRtqAccAsgnIdRequestModel servicesAccessLogByRtqAccAsgnIdRequestModel){
		ServicesAccessLogByRtqAccAsgnIdResponseModel res =new ServicesAccessLogByRtqAccAsgnIdResponseModel();
		try {
			res =  adminService.getServicesAccessLogByRtqAccAsgnId(servicesAccessLogByRtqAccAsgnIdRequestModel);
			res.setReturnCode(Consts.AdminPortal.returnResulst.SUCCESS);
		} catch (Exception e) {
			log.info("getServicesAccessLogByRtqAccAsgnId() error:  " +e);
			res.setReturnCode(Consts.AdminPortal.returnResulst.FAILED);
		}
		return res;
	}

	@Override
	public ProductResponseModel getProduct(ProductRequestModel productRequestModel) {
		ProductResponseModel res = new ProductResponseModel();
		try {
			res = adminService.getProduct(productRequestModel);
			res.setReturnCode(Consts.AdminPortal.returnResulst.SUCCESS);
		} catch (Exception e) {
			log.info("getProduct() error:  " +e);
			res.setReturnCode(Consts.AdminPortal.returnResulst.FAILED);
		}
		return res;
	}

	@Override
	public boolean updateMisDayEndProcessingFlag(String flag) {
		try {
			adminService.updateMisDayEndProcessingFlag(flag);
			return true;
		}catch(Exception e){
			log.error("updateMisDayEndProcessingFlag() ", e);
		}
		return false;
	}

	@Override
	public String getMisDayEndProcessingFlag() {
		try {
			return adminService.getMisDayEndProcessingFlag();
		}catch(Exception e){
			log.error("getMisDayEndProcessingFlag() ", e);
			return Consts.AdminPortal.misDayEndProcessingFlag.Y;
		}
	}

	@Override
	public ReserveUserProductResponseModel findreserveAndRenewalEnquiryById(ReserveUserProductRequestModel reserveUserProductRequestModel){
		ReserveUserProductResponseModel res =new ReserveUserProductResponseModel();
		try {
			res = adminService.findreserveAndRenewalEnquiryById(reserveUserProductRequestModel);
			res.setReturnCode(Consts.AdminPortal.returnResulst.SUCCESS);
		} catch (Exception e) {
			log.info("listRtqAccountAssByProductId() error:  productId: "+reserveUserProductRequestModel.getClientId()+" " +e);
			res.setReturnCode(Consts.AdminPortal.returnResulst.FAILED);
		}
		return res;
	}
	
	@Override
	public List<Product> getProductList(String cnDiscFlag){
		List<Product> productList = new ArrayList<Product>();
		try{
			productList = adminService.getProductList(cnDiscFlag);
		}catch(Exception e){
			log.error("getProductList():  ",e);
		}
		return productList;
	}
	
	@Override
	public UserProductResponseModel cancelReservedUserProduct(UpdateUserProductRequestModel updateUserProductRequestModel){
		UserProductResponseModel res =new UserProductResponseModel();
		try {
			res = adminService.cancelReservedUserProduct(updateUserProductRequestModel);
			res.setReturnCode(Consts.AdminPortal.returnResulst.SUCCESS);
		} catch (Exception e) {
			log.info("cancelReservedUserProduct() error: "+updateUserProductRequestModel.getUserProductRequestList().toString()+" " +e);
			res.setReturnCode(Consts.AdminPortal.returnResulst.FAILED);
		}
		return res;
	}
	
	public ReserveServiceResponseModel reserveService(ReserveServiceRequestModel reserveServiceRequestModel) {
		ReserveServiceResponseModel reserveServiceResponseModel = new ReserveServiceResponseModel();
		try {
			UserProfile userProfile = adminService.getUserProfile(reserveServiceRequestModel.getClientId());
			if(userProfile.getDefDebtAcc()==null || userProfile.getDefDebtAcc().equals("")){//无有效的默认账户
				log.info("reserveService():  No default debit account!");
				reserveServiceResponseModel.setReturnCode(Consts.Error.Code.PURCHASE_SERVICE_NO_DEFAULT_DEBIT_ACCOUNT);
			}else{
				reserveServiceRequestModel.setDefDebtAcc(userProfile.getDefDebtAcc());
				if(reserveServiceRequestModel.getProductId().equals("SHK")){
					log.info("reserveService():  Can not reserve SHK!");
					reserveServiceResponseModel.setReturnCode(Consts.Error.Code.RESERVE_SERVICE_CANNOT_RESERVE_SHK);
				}else{
					Product product  = adminService.findProuctByProductId(reserveServiceRequestModel.getProductId());
					if(product!=null){
						if(product.getProdStatus().equals(Consts.AdminPortal.productStatus.UNAVAIL)){
							log.info("reserveService():  Proudct(" + reserveServiceRequestModel.getProductId() + ") is UNAVAIL!");
							reserveServiceResponseModel.setReturnCode(Consts.Error.Code.PRODUCT_UNAVAIL); 
						}else if(new Date().after(product.getExprDate())){
							log.info("reserveService():  Proudct(" + reserveServiceRequestModel.getProductId() + ") was expired!");
							reserveServiceResponseModel.setReturnCode(Consts.Error.Code.PRODUCT_EXPIRED);
						}else{
							reserveServiceResponseModel = adminService.reserveService(reserveServiceRequestModel, product.getPriceInHkd());
						}
					}else{
						log.info("reserveService():  Proudct(" + reserveServiceRequestModel.getProductId() + ") is null!");
						reserveServiceResponseModel.setReturnCode(Consts.Error.Code.PRODUCT_IS_NULL);
					}
				}
			}
		}catch(Exception e){
			log.error("reserveService():  ",e);
			reserveServiceResponseModel.setReturnCode(Consts.Error.Code.RESERVE_SERVICE_FAILED);	
		}		
		return reserveServiceResponseModel;
	}
	
	@Override
	public AutoPurchaseResponseModel listAutoPurchase(AutoPurchaseRequestModel autoPurchaseRequestModel){
		AutoPurchaseResponseModel res =new AutoPurchaseResponseModel();
		try {
			res.setAutoPurchaseList(adminService.getAutoPurchaseList(autoPurchaseRequestModel.getStatus(), autoPurchaseRequestModel.getStartTime(), autoPurchaseRequestModel.getEndTime()));
			res.setReturnCode(Consts.AdminPortal.returnResulst.SUCCESS);
		} catch (Exception e) {
			log.info("listAutoPurchase() error: "+autoPurchaseRequestModel+" " +e);
			res.setReturnCode(Consts.AdminPortal.returnResulst.FAILED);
		}
		return res;
	}

	@Override
	public ListUserPaymentByManualResponseModel getUserProductPaymentListByManual(
			ListUserPaymentByManualRequestModel listUserPaymentByManualRequestModel) {
		ListUserPaymentByManualResponseModel res = new ListUserPaymentByManualResponseModel();
		try {
			res = adminService.getUserProductPaymentListByManual(listUserPaymentByManualRequestModel.getStartTime(), listUserPaymentByManualRequestModel.getEndTime(), listUserPaymentByManualRequestModel.getPageNumber(), listUserPaymentByManualRequestModel.getPageSize());
			res.setReturnCode(Consts.AdminPortal.returnResulst.SUCCESS);
		} catch (Exception e){
			log.info("getUserProductPaymentListByManual() error: "+listUserPaymentByManualRequestModel+" " +e);
			res.setReturnCode(Consts.AdminPortal.returnResulst.FAILED);
		}
		return res;
	}

	@Override
	public UpdateUserPaymentByManualResponseModel updateUserProductPaymentByManual(
			UpdateUserPaymentByManualRequestModel updateUserPaymentByManualRequestModel) {
		UpdateUserPaymentByManualResponseModel res = new UpdateUserPaymentByManualResponseModel();
		try {
			adminService.updateUserProductPaymentByManual(updateUserPaymentByManualRequestModel.getUsrProdPayIds());
			res.setReturnCode(Consts.AdminPortal.returnResulst.SUCCESS);
		} catch (Exception e){
			log.info("updateUserProductPaymentByManual() error: "+updateUserPaymentByManualRequestModel+" " +e);
			res.setReturnCode(Consts.AdminPortal.returnResulst.FAILED);
		}
		return res;
	}
	
	@Override
	public String getMemoDebitSytem() {
		return memoDebitService.getMemoDebitSystem();
	}
}
