package com.itsz.sht.service.datas;

import java.util.Date;
import java.util.List;

import org.springframework.dao.DataAccessException;

import com.itsz.sht.common.Consts;
import com.itsz.sht.common.model.common.LoggerFactory;
import com.itsz.sht.common.model.common.request.AccessRTQRequestModel;
import com.itsz.sht.common.model.common.request.AccessSHKRequestModel;
import com.itsz.sht.common.model.common.response.AccessRTQResponseModel;
import com.itsz.sht.common.model.common.response.AccessSHKResponseModel;
import com.itsz.sht.dao.ProductDao;
import com.itsz.sht.dao.RtqAccountAssignmentDao;
import com.itsz.sht.dao.RtqAccountDao;
import com.itsz.sht.dao.RtqApplicationDao;
import com.itsz.sht.dao.ServicesAccessLogDao;
import com.itsz.sht.dao.UserProductDao;
import com.itsz.sht.dao.hibernate.model.Product;
import com.itsz.sht.dao.hibernate.model.RtqAccAsgnId;
import com.itsz.sht.dao.hibernate.model.RtqAccount;
import com.itsz.sht.dao.hibernate.model.RtqAccountAssignment;
import com.itsz.sht.dao.hibernate.model.RtqApplication;
import com.itsz.sht.dao.hibernate.model.ServicesAccessLog;
import com.itsz.sht.dao.hibernate.model.UserProduct;
import com.itsz.sht.model.RTQAccess;

public class ProductAdminServiceImpl implements ProductAdminService {
	
	private final LoggerFactory log = LoggerFactory.getInstance();
	
	private UserProductDao userProductDao;
	private ProductDao productDao;
	private RtqAccountDao rtqAccountDao;
	private RtqApplicationDao rtqApplicationDao;
	private RtqAccountAssignmentDao rtqAccountAssignmentDao;
	private ServicesAccessLogDao servicesAccessLogDao;
	
	public UserProductDao getUserProductDao() {
		return userProductDao;
	}

	public void setUserProductDao(UserProductDao userProductDao) {
		this.userProductDao = userProductDao;
	}

	public ProductDao getProductDao() {
		return productDao;
	}

	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

	public RtqAccountDao getRtqAccountDao() {
		return rtqAccountDao;
	}

	public void setRtqAccountDao(RtqAccountDao rtqAccountDao) {
		this.rtqAccountDao = rtqAccountDao;
	}

	public RtqApplicationDao getRtqApplicationDao() {
		return rtqApplicationDao;
	}

	public void setRtqApplicationDao(RtqApplicationDao rtqApplicationDao) {
		this.rtqApplicationDao = rtqApplicationDao;
	}

	public RtqAccountAssignmentDao getRtqAccountAssignmentDao() {
		return rtqAccountAssignmentDao;
	}

	public void setRtqAccountAssignmentDao(
			RtqAccountAssignmentDao rtqAccountAssignmentDao) {
		this.rtqAccountAssignmentDao = rtqAccountAssignmentDao;
	}

	public ServicesAccessLogDao getServicesAccessLogDao() {
		return servicesAccessLogDao;
	}

	public void setServicesAccessLogDao(ServicesAccessLogDao servicesAccessLogDao) {
		this.servicesAccessLogDao = servicesAccessLogDao;
	}
		
	public AccessRTQResponseModel accessRTQ(AccessRTQRequestModel accessRTQRequestModel){
		AccessRTQResponseModel res =new AccessRTQResponseModel();
		long bt = System.currentTimeMillis();
		RTQAccess rtqAccess =new RTQAccess();
		rtqAccess.setClientId(accessRTQRequestModel.getClientId());
		try {
			Product product = productDao.findRTQProduct(accessRTQRequestModel.getClientId());
			if(product!=null){
				UserProduct userProduct = userProductDao.findUserProductByKey(product.getProdId(), accessRTQRequestModel.getClientId());
				if(userProduct!=null){
					rtqAccess.setProductId(userProduct.getId().getProdId());
					RtqApplication rtqApplication=rtqApplicationDao.getRTQApplication(product.getProdId());
					if(rtqApplication!=null){
						if(rtqApplication.getRtqStatus().equals(Consts.AdminPortal.rtqStatus.AVAIL)){
							if(product.getProdStatus().equals(Consts.AdminPortal.productStatus.AVAIL) &&  userProduct.getStatus().equals(Consts.AdminPortal.userProductStatus.AVAIL) && userProduct.getExprDate().after(new Date())){ 
								List<RtqAccountAssignment> rtqAccountAssignmentList = rtqAccountAssignmentDao.getRtqAccountAssignment(product.getProdId(), accessRTQRequestModel.getClientId());
								if(rtqAccountAssignmentList.size() == 0){ //if no RTQ Account Assignment
									//find the rest account of RTQ Account by Product ID 									
									List<RtqAccount> notTakenUprtqAccountList= rtqAccountDao.findNotTakenUpAccount(product.getProdId());
//									System.out.println("notTakenUprtqAccountList.size()==="+notTakenUprtqAccountList.size());
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
										rtqAccountAssignmentDao.addRtqAccountAssignment(rtqAccountAssignment);
										
										rtqAccess.setClientId(accessRTQRequestModel.getClientId());
										rtqAccess.setProductId(product.getProdId());
										rtqAccess.setRtqLoginId(notTakenUprtqAccount.getId().getRtqLognId());
										rtqAccess.setRtqLoginPassword(notTakenUprtqAccount.getRtqLognPwd());
										rtqAccess.setRtqProvider(rtqApplication.getRtqPrdr());
										rtqAccess.setRtqUrl(rtqApplication.getRtqUrl());
										rtqAccess.setStatus("0");//正常返回
									
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
										res.setReturnCode(Consts.AdminPortal.returnResulst.SUCCESS);
									}else{ // throw a exception if have no rest account
//								    	 Mail mail = NotificationBuilder.getInstance(AdminConfigUtil.getInstance().getEmailTarget(), product.getProdId(), accessRTQRequestModel.getClientId()).build();
//								    	 MailSender.getContainerSender().send(mail); 
										 rtqAccess.setStatus("3");//账号不足
									}						
								}else{ //if have RTQ Account Assignment
									rtqAccess.setClientId(accessRTQRequestModel.getClientId());
									rtqAccess.setProductId(product.getProdId());
									rtqAccess.setRtqLoginId(((RtqAccountAssignment)rtqAccountAssignmentList.get(0)).getId().getRtqLognId());
									rtqAccess.setRtqLoginPassword(((RtqAccountAssignment)rtqAccountAssignmentList.get(0)).getRtqLognPwd());
									rtqAccess.setRtqProvider(rtqApplication.getRtqPrdr());
									rtqAccess.setRtqUrl(rtqApplication.getRtqUrl());
									rtqAccess.setStatus("0");//正常返回
									
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
									res.setReturnCode(Consts.AdminPortal.returnResulst.SUCCESS);
								}					
							 }else{			
								 log.info(bt , "accessRTQ() error:  clientId:"+accessRTQRequestModel.getClientId() +" hava no right to visit");
								 rtqAccess.setStatus("1");//产品不可用，或者不存在		
							 }
						}else{
							log.info(bt , "accessRTQ() error:  RtqApplication is UNAVAIL the record of productId:"+product.getProdId());
							rtqAccess.setStatus("1");//产品不可用，或者不存在	
						}
					}else {
						 log.info(bt , "accessRTQ() error:  table RtqApplication isn't existing the record of productId:"+product.getProdId());
						 rtqAccess.setStatus("1");//产品不可用，或者不存在	 
					}
				}else {
					log.info(bt , "accessRTQ() error:  table UserProduct isn't existing the record of productId:"+product.getProdId()+" and  clientId:"+accessRTQRequestModel.getClientId());
					rtqAccess.setStatus("2");//客户未购买产品	 
				}					
		    }else {
			    log.info(bt , "accessRTQ() error: clientId: "+accessRTQRequestModel.getClientId() +" have no RTQ product to purchase or reserve  ");
			    rtqAccess.setStatus("2");//客户未购买产品
			}		 
		} catch (DataAccessException e) {
			log.info(bt , "accessRTQ() DAO error: " +e.getMessage());
			rtqAccess.setStatus("1");//产品不可用，或者不存在		
		} catch (Exception e) {	
	    	log.info(bt , "accessRTQ() error: aceess RTQ error " +e.getMessage());
	    	rtqAccess.setStatus("1");//产品不可用，或者不存在		
		}	
		res.setRtqAccess(rtqAccess);
		return res;
	}
	
	public AccessSHKResponseModel accessSHK(AccessSHKRequestModel accessSHKRequestModel){
		AccessSHKResponseModel res =new AccessSHKResponseModel();
		 long bt = System.currentTimeMillis();	
		try {
			Product product = productDao.getProduct(accessSHKRequestModel.getProductId());
			if(product!=null){
				UserProduct userProduct=userProductDao.findUserProductByKey(accessSHKRequestModel.getProductId(), accessSHKRequestModel.getClientId());
				if(userProduct!=null){
					res.setProductId(userProduct.getId().getProdId());
					RtqApplication rtqApplication=rtqApplicationDao.getRTQApplication(accessSHKRequestModel.getProductId());
					if(rtqApplication!=null){
						if(rtqApplication.getRtqStatus().equals(Consts.AdminPortal.rtqStatus.AVAIL)){
							if(product.getProdStatus().equals(Consts.AdminPortal.productStatus.AVAIL) && userProduct.getStatus().equals(Consts.AdminPortal.userProductStatus.AVAIL) && userProduct.getExprDate().after(new Date())){
								res.setProductId(rtqApplication.getProdId());
								res.setRtqProvider(rtqApplication.getRtqPrdr());
								res.setRtqUrl(rtqApplication.getRtqUrl());
								res.setRtqStatus("0");//正常返回
								
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
								res.setReturnCode(Consts.AdminPortal.returnResulst.SUCCESS);
						    }else{
							    log.info(bt , "accessSHK() error:  clientId:"+accessSHKRequestModel.getClientId() +" hava no right to visit");
							    res.setRtqStatus("1");//产品不可用，或者不存在
						    } 	
						}else{
							log.info(bt , "accessSHK() error: RtqApplication is UNAVAIL the record of productId:"+product.getProdId());
							res.setRtqStatus("1");//产品不可用，或者不存在
						}
					}else {
						log.info(bt , "accessSHK() error: it isn't existing the record of productId:"+accessSHKRequestModel.getProductId());
						res.setRtqStatus("1");//产品不可用，或者不存在	  
					}  					   
			   }else{
					log.info(bt , "accessSHK() error:  table UserProduct isn't existing the record of productId:"+accessSHKRequestModel.getProductId()+" and  clientId:"+accessSHKRequestModel.getClientId());
					res.setRtqStatus("2");//客户未购买产品	  
			   }					
			}else{
				log.info(bt , "accessSHK() error: "+accessSHKRequestModel.getProductId() +" isn't existed ");
				res.setRtqStatus("2");//客户未购买产品
			}	
		} catch (DataAccessException e) {
			log.info(bt , "accessSHK() DAO error: " +e.getMessage());
			res.setRtqStatus("1");//产品不可用，或者不存在		
		} catch (Exception e) {
			log.info(bt , "accessSHK() error: " +e.getMessage());
			res.setRtqStatus("1");//产品不可用，或者不存在		
		}		
		return res;
	}		
}
