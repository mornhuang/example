package com.itsz.sht.service.datas;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;

import com.itsz.common.util.StringMap;
import com.itsz.sht.common.AdminConfigUtil;
import com.itsz.sht.common.Consts;
import com.itsz.sht.common.DateUtil;
import com.itsz.sht.common.Mail;
import com.itsz.sht.common.MailSender;
import com.itsz.sht.common.MemoDebitRemarkUtil;
import com.itsz.sht.common.NotificationBuilder;
import com.itsz.sht.common.NumberUtils;
import com.itsz.sht.common.model.common.LoggerFactory;
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
import com.itsz.sht.common.model.common.request.SaveRTQAccountRequestModel;
import com.itsz.sht.common.model.common.request.ServiceProductRequestModel;
import com.itsz.sht.common.model.common.request.ServicesAccessLogByRtqAccAsgnIdRequestModel;
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
import com.itsz.sht.common.model.common.response.SaveRTQAccountResponseModel;
import com.itsz.sht.common.model.common.response.ServiceProductResponseModel;
import com.itsz.sht.common.model.common.response.ServicesAccessLogByRtqAccAsgnIdResponseModel;
import com.itsz.sht.common.model.common.response.UpdateRTQAccountResponseModel;
import com.itsz.sht.common.model.common.response.UserProductResponseModel;
import com.itsz.sht.common.model.common.response.UserProfileResponseModel;
import com.itsz.sht.dao.AclRoleDao;
import com.itsz.sht.dao.AclRoleFnctnPrmisnDao;
import com.itsz.sht.dao.AclUserProfileDao;
import com.itsz.sht.dao.AutoPurchaseDao;
import com.itsz.sht.dao.CsParameterDao;
import com.itsz.sht.dao.NotificationMediaDao;
import com.itsz.sht.dao.ProductChangeLogDao;
import com.itsz.sht.dao.ProductDao;
import com.itsz.sht.dao.RtqAccountAssignmentDao;
import com.itsz.sht.dao.RtqAccountAssignmentLogDao;
import com.itsz.sht.dao.RtqAccountDao;
import com.itsz.sht.dao.RtqApplicationDao;
import com.itsz.sht.dao.ServicesAccessLogDao;
import com.itsz.sht.dao.UserProductAllotmentDao;
import com.itsz.sht.dao.UserProductAllotmentLogDao;
import com.itsz.sht.dao.UserProductDao;
import com.itsz.sht.dao.UserProductLogDao;
import com.itsz.sht.dao.UserProductPaymentDao;
import com.itsz.sht.dao.UserProfileDao;
import com.itsz.sht.dao.hibernate.model.AclRole;
import com.itsz.sht.dao.hibernate.model.AclRoleFnctnPrmisn;
import com.itsz.sht.dao.hibernate.model.AclUserProfile;
import com.itsz.sht.dao.hibernate.model.AutoPurchase;
import com.itsz.sht.dao.hibernate.model.CsParameter;
import com.itsz.sht.dao.hibernate.model.NotificationMedia;
import com.itsz.sht.dao.hibernate.model.Product;
import com.itsz.sht.dao.hibernate.model.ProductChangeLog;
import com.itsz.sht.dao.hibernate.model.RtqAccAsgnId;
import com.itsz.sht.dao.hibernate.model.RtqAccId;
import com.itsz.sht.dao.hibernate.model.RtqAccount;
import com.itsz.sht.dao.hibernate.model.RtqAccountAssignment;
import com.itsz.sht.dao.hibernate.model.RtqAccountAssignmentLog;
import com.itsz.sht.dao.hibernate.model.RtqApplication;
import com.itsz.sht.dao.hibernate.model.UserProduct;
import com.itsz.sht.dao.hibernate.model.UserProductAllotment;
import com.itsz.sht.dao.hibernate.model.UserProductAllotmentLog;
import com.itsz.sht.dao.hibernate.model.UserProductLog;
import com.itsz.sht.dao.hibernate.model.UserProductPayment;
import com.itsz.sht.dao.hibernate.model.UserProfile;
import com.itsz.sht.dao.hibernate.model.UsrProdId;
import com.itsz.sht.model.RtqAccountAssDto;
import com.itsz.sht.model.RtqAccountView;
import com.itsz.sht.model.UserProductRequest;
import com.taifook.adminportal.common.util.page.Page;

public class AdminServiceImpl implements AdminService {
	
	public final static String MIS_DAY_END_PROCESSING_FLAG = "MisDayEndProcessingFlag";
	
	private AclRoleDao aclRoleDao;
	private AclRoleFnctnPrmisnDao aclRoleFnctnPrmisnDao;
	private AclUserProfileDao aclUserProfileDao;
	private NotificationMediaDao notificationMediaDao;
	private ProductChangeLogDao productChangeLogDao;
	private ProductDao productDao;
	private RtqAccountAssignmentDao rtqAccountAssignmentDao;
	private RtqAccountAssignmentLogDao rtqAccountAssignmentLogDao;
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

	public void setAclRoleDao(AclRoleDao aclRoleDao) {
		this.aclRoleDao = aclRoleDao;
	}

	public void setAclRoleFnctnPrmisnDao(AclRoleFnctnPrmisnDao aclRoleFnctnPrmisnDao) {
		this.aclRoleFnctnPrmisnDao = aclRoleFnctnPrmisnDao;
	}

	public void setAclUserProfileDao(AclUserProfileDao aclUserProfileDao) {
		this.aclUserProfileDao = aclUserProfileDao;
	}

	public void setNotificationMediaDao(NotificationMediaDao notificationMediaDao) {
		this.notificationMediaDao = notificationMediaDao;
	}

	public void setProductChangeLogDao(ProductChangeLogDao productChangeLogDao) {
		this.productChangeLogDao = productChangeLogDao;
	}

	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

	public void setRtqAccountAssignmentDao(
			RtqAccountAssignmentDao rtqAccountAssignmentDao) {
		this.rtqAccountAssignmentDao = rtqAccountAssignmentDao;
	}

	public void setRtqAccountAssignmentLogDao(
			RtqAccountAssignmentLogDao rtqAccountAssignmentLogDao) {
		this.rtqAccountAssignmentLogDao = rtqAccountAssignmentLogDao;
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

	@Override
	public ChangeProductResponseModel updateNoEmailProduct(ChangeNoEmailProductRequestModel changeNoEmailProductRequestModel){
		ChangeProductResponseModel res = new ChangeProductResponseModel();
		Product product=productDao.getProduct(changeNoEmailProductRequestModel.getProductId());
		product.setRemarks(changeNoEmailProductRequestModel.getRemarks());
		product.setUpdBy(changeNoEmailProductRequestModel.getActionBy());
		product.setUpdDate(new Date());
		productDao.upateProduct(product);
		productChangeLogDao.addProductChangeLog(getProductLog(product,Consts.AdminPortal.actionType.CHANGE));
	    return res;
	}
	
	@Override
	public ChangeProductResponseModel updateRTQ_SHKProduct(ChangeRTQ_SHKProductRequestModel changeRTQ_SHKProductRequestModel){
		ChangeProductResponseModel res = new ChangeProductResponseModel();
		Product product=productDao.getProduct(changeRTQ_SHKProductRequestModel.getProductId());
		if(product != null){
			List<UserProduct> userProductList = userProductDao.findAvailUserProduct(product.getProdId());
			if(userProductList.size() > 0 && (product.getPriceInHkd()-changeRTQ_SHKProductRequestModel.getPriceInHKD()!=0)){
				res.setReturnCode(Consts.Error.Code.ADMINPROTAL_PRODUCT_CANNOT_CHANGE_PRICE);
			} else {
				product.setProdStatus((changeRTQ_SHKProductRequestModel.getProductStatus()));
				product.setQuota(changeRTQ_SHKProductRequestModel.getQuota());
				product.setPriceInHkd(changeRTQ_SHKProductRequestModel.getPriceInHKD());
				product.setRemarks(changeRTQ_SHKProductRequestModel.getRemarks());
				product.setUpdBy(changeRTQ_SHKProductRequestModel.getActionBy());
				product.setUpdDate(new Date());
				productDao.upateProduct(product);
				productChangeLogDao.addProductChangeLog(getProductLog(product,Consts.AdminPortal.actionType.CHANGE));
				res.setReturnCode(Consts.AdminPortal.returnResulst.SUCCESS);
			}
		} else {
			res.setReturnCode(Consts.AdminPortal.returnResulst.FAILED);
		}
	    return res;
	}
	
	@Override
	public ChangeRTQApplicationResponseModel updateRTQApplication(ChangeRTQApplicationRequestModel changeRTQApplicationRequestModel){
	    ChangeRTQApplicationResponseModel res=new ChangeRTQApplicationResponseModel();		
		RtqApplication rtqApplication=rtqApplicationDao.getRTQApplication(changeRTQApplicationRequestModel.getProductId());
		rtqApplication.setRtqPrdr(changeRTQApplicationRequestModel.getrTQProvider());
		rtqApplication.setRtqStatus(changeRTQApplicationRequestModel.getrTQStatus());
		rtqApplication.setRtqUrl(changeRTQApplicationRequestModel.getrTQUrl());
		rtqApplication.setUpdBy(changeRTQApplicationRequestModel.getUpdBy());
		rtqApplication.setUpdDate(changeRTQApplicationRequestModel.getUpdDate());
		rtqApplicationDao.updateRtqApplication(rtqApplication);
		return res;
	}
	
	@Override
	public ListRTQApplicationResponseModel listRTQApplication(){
		ListRTQApplicationResponseModel res=new ListRTQApplicationResponseModel();
	    res.setRtqApplication(rtqApplicationDao.listRTQApplication());
		return res;
	}
	
	@Override
	public RTQAccountViewResponseModel listRTQAccountView(){
		 RTQAccountViewResponseModel res =new RTQAccountViewResponseModel();
		 List<RtqAccountView> rtqAccountViewList=new ArrayList<RtqAccountView>();
		 List<Product> productList= productDao.getProductList();
		 for (int i = 0; i < productList.size(); i++) {
			 Product product =(Product)productList.get(i);
			 if(product.getProdId().equals("NO_EMAIL")){
				 continue;
			 }
			 RtqAccountView rtqAccountView=new RtqAccountView();
			  //获取RTQ Account 总数 
			  List<RtqAccount> rtqAccountList= rtqAccountDao.getRtqAccountByProductId(product.getProdId());
			  //获取RTQ Account 在用数
			  List<RtqAccountAssignment> rtqAccountAssignmentList = rtqAccountAssignmentDao.getRtqAccountAssignmentByProductId(product.getProdId());
			  //获取续期数
			  List<UserProduct> renewalList =userProductDao.getRenewalNumByProductId(product.getProdId());
			  //获取预定数
			  List<UserProductAllotment> autoPurchaseList =userProductAllotmentDao.getAutoPurchaseNumByProductId(product.getProdId());
			  //获取last account id
			  RtqAccount rtqAccount= rtqAccountList.size()>0 ? rtqAccountList.get(0) : null;
			  //获取本月订购此RTQ 客人的总数
			  List<UserProduct> accountRequiredUserProductList= userProductDao.findAccountRequiredThisMonth(product.getProdId());
			  
			  rtqAccountView.setProductId(product.getProdId());
			  rtqAccountView.setAccountTotal(rtqAccountList.size());
			  rtqAccountView.setAccountInUseThisMonth(rtqAccountAssignmentList.size());
			  rtqAccountView.setAccountRequiredNextMonth(renewalList.size()+autoPurchaseList.size());
			  if(rtqAccount==null){
				  rtqAccountView.setLastAccountId("no account");
			  }else{
				  rtqAccountView.setLastAccountId(rtqAccount.getId().getRtqLognId());  
			  }
			  
			  rtqAccountView.setAccountRequiredThisMonth(accountRequiredUserProductList.size());
			  rtqAccountViewList.add(rtqAccountView);			 
		 }
		 res.setRtqAccountViewList(rtqAccountViewList);
		 return res;
	}
	
	@Override
	public SaveRTQAccountResponseModel saveRTQAccount(SaveRTQAccountRequestModel saveRTQAccountRequestModel){
		SaveRTQAccountResponseModel res =new SaveRTQAccountResponseModel();
		List<RtqAccount> rtqAccountList=new ArrayList<RtqAccount>();
		if(saveRTQAccountRequestModel.getrTQLoginIdTo()==null||saveRTQAccountRequestModel.getrTQLoginIdTo().equals("")){
			saveRTQAccountRequestModel.setrTQLoginIdTo(saveRTQAccountRequestModel.getrTQLoginIdFrom());
		}
		for (int i = NumberUtils.getNumber(saveRTQAccountRequestModel.getrTQLoginIdFrom()); i < NumberUtils.getNumber(saveRTQAccountRequestModel.getrTQLoginIdTo())+1; i++) {
			RtqAccount rtqAccount=new RtqAccount();
			RtqAccId rtqAccId=new RtqAccId();
			rtqAccId.setProdId(saveRTQAccountRequestModel.getProductId());
			rtqAccId.setRtqLognId(NumberUtils.getString(NumberUtils.getPreString(saveRTQAccountRequestModel.getrTQLoginIdFrom()), i, saveRTQAccountRequestModel.getrTQLoginIdFrom().length()));
			rtqAccount.setId(rtqAccId);
			rtqAccount.setInitBy(saveRTQAccountRequestModel.getUserName());
			rtqAccount.setInitDate(new Date());
			rtqAccount.setUpdBy(saveRTQAccountRequestModel.getUserName());
			rtqAccount.setUpdDate(new Date());
			rtqAccount.setRtqLognPwd(saveRTQAccountRequestModel.getrTQLoginPassword());		
			rtqAccountList.add(rtqAccount);
		}
		rtqAccountDao.addRtqAccountList(rtqAccountList);
		return res;
	}
	
	@Override
	public UpdateRTQAccountResponseModel updateRTQAccount(UpdateRTQAccountRequestModel updateRTQAccountRequestModel){
		UpdateRTQAccountResponseModel res =new UpdateRTQAccountResponseModel();
		List<RtqAccount> rtqAccountList=new ArrayList<RtqAccount>();
		if(updateRTQAccountRequestModel.getrTQLoginIdTo().equals("")||updateRTQAccountRequestModel.getrTQLoginIdTo()==null){
			updateRTQAccountRequestModel.setrTQLoginIdTo(updateRTQAccountRequestModel.getrTQLoginIdFrom());
		}
		String rtqLognIdStr = "";
		List<String> rtqLognIdList = new ArrayList<String>();
		List<String> rtqLognIdUpdateList = new ArrayList<String>();
		for (int i = NumberUtils.getNumber(updateRTQAccountRequestModel.getrTQLoginIdFrom()); i < NumberUtils.getNumber(updateRTQAccountRequestModel.getrTQLoginIdTo())+1; i++) {
			rtqLognIdStr = NumberUtils.getString(NumberUtils.getPreString(updateRTQAccountRequestModel.getrTQLoginIdFrom()), i, updateRTQAccountRequestModel.getrTQLoginIdFrom().length());
			RtqAccount rtqAccount=	rtqAccountDao.getRtqAccount(updateRTQAccountRequestModel.getProductId(), rtqLognIdStr);
			if(rtqAccount == null){
				rtqLognIdList.add(rtqLognIdStr);
			} else {
				rtqLognIdUpdateList.add(rtqLognIdStr);
				rtqAccount.setRtqLognPwd(updateRTQAccountRequestModel.getrTQLoginPassword());
				rtqAccountList.add(rtqAccount);
			}
		}
		if(rtqLognIdList.size() > 0){
			res.setRtqLognIdList(rtqLognIdList);
		}
		if(rtqLognIdUpdateList.size() > 0){
			rtqAccountDao.updateRtqAccountPassword(updateRTQAccountRequestModel.getProductId(), rtqLognIdUpdateList, updateRTQAccountRequestModel.getrTQLoginPassword());
			rtqAccountAssignmentDao.updateRtqAccountAssignmentPassword(updateRTQAccountRequestModel.getProductId(), rtqLognIdUpdateList, updateRTQAccountRequestModel.getrTQLoginPassword());
		}
		return res;
	}
	
	@Override
	public DeleteRTQAccountResponseModel deleteRTQAccount(DeleteRTQAccountRequestModel deleteRTQAccountRequestModel){
		DeleteRTQAccountResponseModel res=new DeleteRTQAccountResponseModel();
		List<RtqAccount> rtqAccountList=new ArrayList<RtqAccount>();
//		List<RtqAccountAssignment> rtqAccountAssignmentList=new ArrayList<RtqAccountAssignment>();
		if(deleteRTQAccountRequestModel.getrTQLoginIdTo().equals("")||deleteRTQAccountRequestModel.getrTQLoginIdTo()==null){
			deleteRTQAccountRequestModel.setrTQLoginIdTo(deleteRTQAccountRequestModel.getrTQLoginIdFrom());
		}
		List<String> rtqLoginIdList=new ArrayList<String>();
		for (int i = NumberUtils.getNumber(deleteRTQAccountRequestModel.getrTQLoginIdFrom()); i < NumberUtils.getNumber(deleteRTQAccountRequestModel.getrTQLoginIdTo())+1; i++)  {
			String rtqLoginId=NumberUtils.getString(NumberUtils.getPreString(deleteRTQAccountRequestModel.getrTQLoginIdFrom()), i, deleteRTQAccountRequestModel.getrTQLoginIdFrom().length());
			String productId=deleteRTQAccountRequestModel.getProductId();
			RtqAccount rtqAccount=new RtqAccount();
			RtqAccId rtqAccId=new RtqAccId();
			rtqAccId.setProdId(productId);
			rtqAccId.setRtqLognId(rtqLoginId);
			rtqAccount.setId(rtqAccId);
			RtqAccountAssignment rtqAccAss=	rtqAccountAssignmentDao.getRtqAccountAssignmentByProdIdAndRtqLoginId(productId, rtqLoginId);
			if(rtqAccAss==null){
				rtqAccountList.add(rtqAccount);
			}else{
				rtqLoginIdList.add(rtqLoginId);
				res.setRtqLoginIdList(rtqLoginIdList);
			}
		}
//			for (int i = NumberUtils.getNumber(deleteRTQAccountRequestModel.getrTQLoginIdFrom()); i < NumberUtils.getNumber(deleteRTQAccountRequestModel.getrTQLoginIdTo())+1; i++)  {
//				RtqAccountAssignment rtqAccountAssignment=new RtqAccountAssignment();
//				RtqAccAsgnId rtqAccAsgnId=new RtqAccAsgnId();
//				rtqAccAsgnId.setProdId(deleteRTQAccountRequestModel.getProductId());
//				rtqAccAsgnId.setRtqLognId(NumberUtils.getString(NumberUtils.getPreString(deleteRTQAccountRequestModel.getrTQLoginIdFrom()), i, deleteRTQAccountRequestModel.getrTQLoginIdFrom().length()));
//				rtqAccountAssignment.setId(rtqAccAsgnId);
//				rtqAccountAssignmentList.add(rtqAccountAssignment);
//			}
		rtqAccountDao.deleteRtqAccountList(rtqAccountList);			
//			rtqAccountAssignmentDao.deleteRtqAccountAssignmentList(rtqAccountAssignmentList);
	//	rtqAccountAssignmentLogDao.addRtqAccountAssignmentLog(getRtqAccountAssignmentLog(rtqAccountAssignmentList,Consts.AdminPortal.actionType.CREATE));
		return res;
	}
	
	@Override
	public DeleteRTQAccountLastNResponseModel deleteRTQAccountLastN(DeleteRTQAccountLastNRequestModel deleteRTQAccountLastNRequestModel){
		DeleteRTQAccountLastNResponseModel res =new DeleteRTQAccountLastNResponseModel();
		res.setRtqLoginIdList(rtqAccountDao.deleteLastNRtqAccountByProductId(deleteRTQAccountLastNRequestModel.getProductId(), deleteRTQAccountLastNRequestModel.getN()));
		return res;
	}
	
	@Override
	public ReleaseRTQAccountAssignmentResponseModel releaseRTQAccountAssignment(
			ReleaseRTQAccountAssignmentRequestModel releaseRTQAccountAssignmentRequestModel) {
		ReleaseRTQAccountAssignmentResponseModel res = new ReleaseRTQAccountAssignmentResponseModel();
		List<Product> productList =productDao.getProductList();
		for(Product product : productList){
			if(product.getProdStatus().equals("AVAIL") && product.getProdId().startsWith("SSTR")){
				List<String> tempRtqAccountAssignmentList = new ArrayList<String>();
				List<RtqAccountAssignment> 	rtqAccountAssignmentList = rtqAccountAssignmentDao.getRtqAccountAssignmentByProductId(product.getProdId());
				List<UserProduct> userProductList = userProductDao.findAvailUserProduct(product.getProdId());
				for(UserProduct userProduct : userProductList){
					for(RtqAccountAssignment raa : rtqAccountAssignmentList){
						if(userProduct.getId().getClntId().equals(raa.getId().getClntId())){
							rtqAccountAssignmentList.remove(raa);
							break;
						}
					}
				}
				for(RtqAccountAssignment raa : rtqAccountAssignmentList){
					tempRtqAccountAssignmentList.add(raa.getId().getClntId());
				}
				rtqAccountAssignmentDao.deleteRtqAccountAssignment(tempRtqAccountAssignmentList, product.getProdId());
				rtqAccountAssignmentLogDao.addRtqAccountAssignmentLog(getRtqAccountAssignmentLog(rtqAccountAssignmentList,Consts.AdminPortal.actionType.DELETE));	
			}
		}
		res.setReturnCode(Consts.Error.Code.ADMINPROTAL_RELEASERTQACCOUNT_SUCCESS);
		return res;
	}
	
	@Override
	//释放完无用账号后，再分配账号
	public void allotRTQAccountAssignment() throws MessagingException{
		List<Product> productList =productDao.getProductList();
		Map<String, ArrayList<String>> noAccountMap = new HashMap<String, ArrayList<String>>();  //账号不够分配
		for(Product product : productList){
			if(product.getProdStatus().equals("AVAIL") && product.getProdId().startsWith("SSTR")){
				//查询空闲的RTQ账号								
				List<RtqAccount> notTakenUprtqAccountList= rtqAccountDao.findNotTakenUpAccount(product.getProdId());
				//查询未分配账号的clientId
				List<String> clientIdList = userProductDao.findNoAccountUserProductForClientId(product.getProdId());
				int index = 0;
				for(; index<clientIdList.size()&&index<notTakenUprtqAccountList.size(); index++){
					RtqAccount notTakenUprtqAccount=(RtqAccount)notTakenUprtqAccountList.get(0);
					RtqAccountAssignment rtqAccountAssignment = new RtqAccountAssignment();
					RtqAccAsgnId rtqAccAsgnId=new RtqAccAsgnId();
					rtqAccAsgnId.setClntId(clientIdList.get(index));
					rtqAccAsgnId.setProdId(notTakenUprtqAccount.getId().getProdId());
					rtqAccAsgnId.setRtqLognId(notTakenUprtqAccount.getId().getRtqLognId());
					rtqAccountAssignment.setId(rtqAccAsgnId);
					rtqAccountAssignment.setRtqLognPwd(notTakenUprtqAccount.getRtqLognPwd());
					rtqAccountAssignment.setInitBy(clientIdList.get(index));
					rtqAccountAssignment.setInitDate(new Date());
					rtqAccountAssignment.setUpdBy(clientIdList.get(index));
					rtqAccountAssignment.setUpdDate(new Date());
					rtqAccountAssignmentDao.addRtqAccountAssignment(rtqAccountAssignment);
				}
				if(clientIdList.size() > notTakenUprtqAccountList.size()){
					ArrayList<String> noAccountList = new ArrayList<String>();
					for(; index<clientIdList.size(); index++){
						noAccountList.add(clientIdList.get(index));
					}
					noAccountMap.put(product.getProdId(), noAccountList);
				}
			}
		}
		if(noAccountMap.size() != 0){
			Mail mail = NotificationBuilder.getInstance(AdminConfigUtil.getInstance().getEmailTarget(), noAccountMap).buildAll();
			MailSender.getContainerSender().send(mail);
		}
	}
	
//	@Override
//	public UserProfileResponseModel updateUserProfile(UserProfileRequestModel updateUserProfileRequestModel){
//		UserProfileResponseModel res =new UserProfileResponseModel();
//		long bt = System.currentTimeMillis();
//		try {
//			UserProfile userProfile=userProfileDao.getUserProfile(updateUserProfileRequestModel.getClientId());
//			userProfile.setCnDiscFlag(updateUserProfileRequestModel.getChinaDiscountFlag());
//			userProfile.setDefDebtAcc(updateUserProfileRequestModel.getDefaultDebitAccount());
//			userProfile.setUpdBy(updateUserProfileRequestModel.getUserName());
//			userProfile.setUpdDate(new Date());
//			userProfileDao.updateUserProfile(userProfile);
//			//写deftDebtAccChngLog日志
//			DeftDebtAccChngLog deftDebtAccChngLog=new DeftDebtAccChngLog();
//			deftDebtAccChngLog.setDefDebtAccChgLogId(String.valueOf(System.currentTimeMillis()));
//			deftDebtAccChngLog.setActnBy(updateUserProfileRequestModel.getUserName());
//			deftDebtAccChngLog.setActnDate(new Date());
//			deftDebtAccChngLog.setActnType(Consts.AdminPortal.actionType.CHANGE);
//			deftDebtAccChngLog.setClntId(userProfile.getClntId());
//			deftDebtAccChngLog.setDefDebtAcc(updateUserProfileRequestModel.getDefaultDebitAccount());
//			deftDebtAccChngLog.setInitBy(userProfile.getInitBy());
//			deftDebtAccChngLog.setInitDate(userProfile.getInitDate());
//			deftDebtAccChngLog.setUpdBy(userProfile.getUpdBy());
//			deftDebtAccChngLog.setUpdDate(userProfile.getUpdDate());			
//			deftDebtAccChngLogDao.addDefDebtAccChgLog(deftDebtAccChngLog);
//			res.setReturnCode(Consts.AdminPortal.returnResulst.SUCCESS);
//		} catch (Exception e) {
//			LoggerFactory.getInstance().info(bt , "updateUserProfile() error:  "+ updateUserProfileRequestModel.getClientId()+" " +e.getMessage());
//			res.setReturnCode(Consts.AdminPortal.returnResulst.FAILED);
//		}
//		return res;
//	}
	
	@Override
	public UserProfileResponseModel findUserProfile(UserProfileRequestModel userProfileRequestModel){
		 UserProfileResponseModel res=new UserProfileResponseModel();
		 UserProfile userProfile= userProfileDao.getUserProfile(userProfileRequestModel.getClientId(), userProfileRequestModel.getClntLoginId());
		 res.setClientId(userProfile.getClntId());
		 res.setClientLoginId(userProfile.getClntLoginId());
		 res.setChinaDiscountFlag(userProfile.getCnDiscFlag());
		 res.setDefaultDebitAccount(userProfile.getDefDebtAcc());
		 return res;
	}

	@Override
	public ListSelectServiceResponseModel listSelectService(ListSelectServiceRequestModel listSelectServiceRequestModel) {
		ListSelectServiceResponseModel res=new ListSelectServiceResponseModel();  
		List<RtqAccountAssignment> rtqAccountAssignmentList=	rtqAccountAssignmentDao.getRtqAccountByClientId(listSelectServiceRequestModel.getClientId());
		List<UserProduct> existingServiceProductList=	userProductDao.getExistingUserProductByClientId(listSelectServiceRequestModel.getClientId(), false);
		List<UserProductAllotment> subscriberServiceProductList= userProductAllotmentDao.getSubscriberUserProductAllotmentByClientId(listSelectServiceRequestModel.getClientId(), Consts.AdminPortal.allotStatus.RESERVE);
		if(existingServiceProductList.size()>0){
			existingServiceProductList.get(0).setUpdDate(userProductAllotmentDao.getEffect(listSelectServiceRequestModel.getClientId(),existingServiceProductList.get(0).getId().getProdId()));
		}
		res.setRtqAccountAssignmentList(rtqAccountAssignmentList);
		res.setExistingServiceProductList(existingServiceProductList);
		res.setSubscriberServiceProductList(subscriberServiceProductList);
		return res;
	}

	@Override
	public boolean deleteUserProfile(String clientId) {
	   boolean falg;
		 long bt = System.currentTimeMillis();
		try {
			userProfileDao.deleteUserProfile(clientId);
			falg=true;
		} catch (Exception e) {
			LoggerFactory.getInstance().info(bt , clientId+" " +e.getMessage());
			falg=false;
		}
		return falg;
	}
	@Override
	public boolean saveUserProfile(UserProfile userProfile) {
	   boolean falg;
	   long bt = System.currentTimeMillis();
		try {
			userProfileDao.addUserProfile(userProfile);
			falg=true;
		} catch (Exception e) {
			LoggerFactory.getInstance().info(bt , userProfile.getClntId()+" " +e.getMessage());
			falg=false;
		}
		return falg;
	}

	//3.3.1.3	User Product Maintenance
	
	@Override
	public UserProductResponseModel findUserProductStautsByClientId(UserProductRequestModel userProductRequestModel) {
		UserProductResponseModel res =new UserProductResponseModel();
		res.setUserProductList(userProductDao.findUserProductByClientId(userProductRequestModel.getClientId(),new Date()));
		return res;
	}
	
	@Override
	public UserProductResponseModel updateUserProductStatus(UpdateUserProductRequestModel updateUserProductRequestModel){
		UserProductResponseModel res =new UserProductResponseModel();
		List<UserProduct> userProductList=new ArrayList<UserProduct>();
		List<UserProductRequest> userProductRequestList= updateUserProductRequestModel.getUserProductRequestList();
		for (int i = 0; i < userProductRequestList.size(); i++) {
			UserProductRequest userProductRequest =(UserProductRequest)userProductRequestList.get(i);
			UserProduct userProduct=userProductDao.findUserProductByKey(userProductRequest.getId().getProdId(), userProductRequest.getId().getClntId());
			userProduct.setAllwRenl(userProductRequest.getAllwRenl());
			if(userProductRequest.getStatus().equals(Consts.AdminPortal.productStatus.UNAVAIL)){
				userProduct.setAllwRenl(Consts.AdminPortal.allowRenewal.NO);
			}else if(userProductRequest.getId().getProdId().equals("SHK") && userProductRequest.getStatus().equals(Consts.AdminPortal.productStatus.AVAIL)){
				userProduct.setAllwRenl(Consts.AdminPortal.allowRenewal.YES);
			}else if(!userProductRequest.getId().getProdId().equals("SHK") && userProductRequest.getStatus().equals(Consts.AdminPortal.productStatus.AVAIL)){
				userProductAllotmentDao.deleteUserProductAllotmentByClnId(userProductRequest.getId().getClntId());
				userProductPaymentDao.deleteUserProductPaymentByClnId(userProductRequest.getId().getClntId());
			}
			userProduct.setStatus(userProductRequest.getStatus());
			userProductList.add(userProduct);
		}
		userProductDao.updateUserProductList(userProductList);
		return res;
	}

	@Override
	public UserProductResponseModel findCancelUserProductByClientId(
			UserProductRequestModel userProductRequestModel) {
		UserProductResponseModel res =new UserProductResponseModel();
		res.setUserProductList(userProductDao.findUserProductByClientIdForCancle(userProductRequestModel.getClientId(),new Date()));
		return res;
	}
	
	@Override
	public UserProductResponseModel updateCancelUserProduct(UpdateUserProductRequestModel updateUserProductRequestModel) {
		UserProductResponseModel res =new UserProductResponseModel();
		List<UserProduct> userProductList=new ArrayList<UserProduct>();
		List<RtqAccountAssignment> raaList = new ArrayList<RtqAccountAssignment>();
		List<UserProductRequest> userProductRequestList= updateUserProductRequestModel.getUserProductRequestList();
		for (int i = 0; i < userProductRequestList.size(); i++) {
			UserProductRequest userProductRequest =(UserProductRequest)userProductRequestList.get(i);
			UserProduct userProduct=userProductDao.findUserProductByKey(userProductRequest.getId().getProdId(), userProductRequest.getId().getClntId());
			userProduct.setExprDate(new Date());
			userProduct.setAllwRenl(Consts.AdminPortal.allowRenewal.NO);
			userProduct.setStatus(Consts.AdminPortal.productStatus.UNAVAIL);
			userProduct.setUpdDate(new Date());
			userProductList.add(userProduct);
			if(userProductRequest.getId().getProdId().startsWith("SSTR")){
				RtqAccountAssignment raa = rtqAccountAssignmentDao.getRtqAccountAssignmentByClntIdAndProdId(userProductRequest.getId().getClntId(), userProductRequest.getId().getProdId());
				if(raa != null){
					raaList.add(raa);
				}
			}
		}			
		userProductDao.updateUserProductList(userProductList);
		rtqAccountAssignmentDao.deleteRtqAccountAssignmentList(raaList);
		return res;
	}

	@Override
	public UserProductResponseModel findRenewalOptionByClientId(UserProductRequestModel userProductRequestModel) {
		UserProductResponseModel res =new UserProductResponseModel();
		res.setUserProductList(userProductDao.findUserProductByClientId(userProductRequestModel.getClientId(),new Date(),Consts.AdminPortal.productStatus.AVAIL));
		return res;
	}


	@Override
	public UserProductResponseModel updateRenewalOption(
			UpdateUserProductRequestModel updateUserProductRequestModel) {
		UserProductResponseModel res =new UserProductResponseModel();
		List<UserProduct> userProductList=new ArrayList<UserProduct>();
		List<UserProductRequest> userProductRequestList= updateUserProductRequestModel.getUserProductRequestList();
		for (int i = 0; i < userProductRequestList.size(); i++) {
			UserProductRequest userProductRequest =(UserProductRequest)userProductRequestList.get(i);
			UserProduct userProduct=userProductDao.findUserProductByKey(userProductRequest.getId().getProdId(), userProductRequest.getId().getClntId());
			if(userProductRequest.getStatus().equals(Consts.AdminPortal.productStatus.UNAVAIL)){
				userProduct.setAllwRenl(Consts.AdminPortal.allowRenewal.NO);
				}
				userProduct.setStatus(userProductRequest.getStatus());
			userProductList.add(userProduct);
		}			
		userProductDao.updateUserProductList(userProductList);
		if(userProductRequestList.size()>0){
			UserProductRequest userProductRequest =(UserProductRequest)userProductRequestList.get(0);
		   userProductAllotmentDao.deleteUserProductAllotment(userProductRequest.getId().getProdId(),userProductRequest.getId().getClntId());
		}
		return res;
	}


	@Override
	public List<UserProduct> findExeRenewalUserProduct() {
		return userProductDao.findRenewalList();
	}

	@Override
	public UserProfile getUserProfile(String clientId) {
		return userProfileDao.getUserProfile(clientId);
	}

	@Override
	public Product getProuctByProductId(String productId) {
		return productDao.getProduct(productId);
	}

	@Override
	public void exeRenewalByUserProduct(UserProduct userProduct, Long priceInHkd, String defDebtAcc, String userName) {
		//update User Priduct
		userProduct.setAllwRenl(Consts.AdminPortal.allowRenewal.YES);
		userProduct.setExprDate(DateUtil.getThisMonthLastDateofMonth(new Date()));
		userProduct.setStatus(Consts.AdminPortal.productStatus.AVAIL);
		userProduct.setUpdDate(new Date());
		userProduct.setUpdBy(userName);
		userProductDao.updateUserProduct(userProduct);
		userProductLogDao.addUserProductLog(getUserProductLog(userProduct, Consts.AdminPortal.actionType.CHANGE));
					
		//create user product allotment
		UserProductAllotment userProductAllotment=new UserProductAllotment();
		userProductAllotment.setUsrProdAlltId(String.valueOf(System.currentTimeMillis()));
		userProductAllotment.setClntId(userProduct.getId().getClntId());
		userProductAllotment.setProdId(userProduct.getId().getProdId());
		userProductAllotment.setInitBy(userName);
		userProductAllotment.setInitDate(new Date());
		userProductAllotment.setUpdBy(userName);
		userProductAllotment.setUpdDate(new Date());
		userProductAllotment.setEffDate(DateUtil.getThisMonthFirstDateofMonth(new Date()));
		userProductAllotment.setExpDate(DateUtil.getThisMonthLastDateofMonth(new Date()));
		userProductAllotment.setAlltTime(new Date());
		userProductAllotment.setAlltStatus(Consts.AdminPortal.allotStatus.SUCC);		
		userProductAllotment.setPayReqId(String.valueOf(System.currentTimeMillis()));
		userProductAllotmentDao.addUserProductAllotment(userProductAllotment);
		userProductAllotmentLogDao.addUserProductAllotmentLog(getUserProductAllotmentLog(userProductAllotment, Consts.AdminPortal.actionType.CREATE));
        			
		//create User Product Payment
		UserProductPayment userProductPayment=new UserProductPayment();
		userProductPayment.setUsrProdPayId(userProductAllotment.getPayReqId());
		userProductPayment.setClntId(userProduct.getId().getClntId());
		userProductPayment.setDebtRemarks("");
		userProductPayment.setDefDebtAcc(defDebtAcc);
		userProductPayment.setInitBy(userName);
		userProductPayment.setInitDate(new Date());
		userProductPayment.setUpdBy(userName);
		userProductPayment.setUpdDate(new Date());
		userProductPayment.setPaySatus(Consts.AdminPortal.payStatus.MEMO);
		
		if(userProduct.getId().getProdId().substring(0, 3).equals("SHK")){
			userProductPayment.setMemoCode(Consts.AdminPortal.memeoCode.MDAC);
			userProductPayment.setDebtRemarks(MemoDebitRemarkUtil.generateMemoRemarks(MemoDebitRemarkUtil.SHK, new Date()));	
		}else{
			userProductPayment.setMemoCode(Consts.AdminPortal.memeoCode.MDAE);
			userProductPayment.setDebtRemarks(MemoDebitRemarkUtil.generateMemoRemarks(MemoDebitRemarkUtil.RTQ_SEC_STR_HK, new Date()));
		}
		userProductPayment.setPriceHkd(priceInHkd.toString());
		userProductPayment.setProdId(userProduct.getId().getProdId());
		userProductPayment.setReqSys(Consts.AdminPortal.requestSYS.MIS);
		userProductPayment.setReqTime(new Date());
		userProductPayment.setResTime(new Date());
		userProductPaymentDao.addUserProductPayment(userProductPayment);
		
		//为客户分配RTQ服务所需的访问账号
//			List<RtqAccount> notTakenUprtqAccountList= rtqAccountDao.findNotTakenUpAccount(userProduct.getId().getProdId());
//			if(notTakenUprtqAccountList.size()>0){
//				RtqAccount notTakenUprtqAccount=(RtqAccount)notTakenUprtqAccountList.get(0);
//				RtqAccountAssignment rtqAccountAssignment =new RtqAccountAssignment();
//				RtqAccAsgnId rtqAccAsgnId=new RtqAccAsgnId();
//				rtqAccAsgnId.setClntId(userProduct.getId().getClntId());
//				rtqAccAsgnId.setProdId(notTakenUprtqAccount.getId().getProdId());
//				rtqAccAsgnId.setRtqLognId(notTakenUprtqAccount.getId().getRtqLognId());
//				rtqAccountAssignment.setId(rtqAccAsgnId);
//				rtqAccountAssignment.setRtqLognPwd(notTakenUprtqAccount.getRtqLognPwd());
//				rtqAccountAssignment.setInitDate(new Date());
//				rtqAccountAssignment.setInitBy(userName);
//				rtqAccountAssignmentDao.addRtqAccountAssignment(rtqAccountAssignment);				
//			}			
	}

	@Override
	public List<UserProductAllotment> findAutoPurchaseUserProduct() {
		return userProductAllotmentDao.findReserveList();
	}

	@Override
	public void exeAutoPurchaseByUserProduct(UserProductAllotment userProductAllotment, String username) {
		//判断 user product 的有效性
		UserProduct userProduct= userProductDao.findUserProductByKey(userProductAllotment.getProdId(),userProductAllotment.getClntId());
	    if(userProduct==null){  //如果User Product = null
	    	//create User Product
	    	UsrProdId usrProdId =new UsrProdId();
	    	usrProdId.setClntId(userProductAllotment.getClntId());
	    	usrProdId.setProdId(userProductAllotment.getProdId());
	    	UserProduct newUserProduct= new UserProduct();		    	
	    	newUserProduct.setId(usrProdId);
	    	if(	userProductAllotment.getAlltStatus().equals(Consts.AdminPortal.allotStatus.RESERVEANDAUTO)){
	    		newUserProduct.setAllwRenl(Consts.AdminPortal.allowRenewal.YES);
	    	}else{
	    		newUserProduct.setAllwRenl(Consts.AdminPortal.allowRenewal.NO);
	    	}	
	    	newUserProduct.setExprDate(DateUtil.getThisMonthLastDateofMonth(new Date()));
	    	newUserProduct.setInitBy(username);
	    	newUserProduct.setUpdBy(username);
	    	newUserProduct.setUpdDate(new Date());
	    	newUserProduct.setInitDate(new Date());
	    	newUserProduct.setStatus(Consts.AdminPortal.productStatus.AVAIL);
	    	newUserProduct.setRemarks("");
            userProductDao.addUserProduct(newUserProduct);
            userProductLogDao.addUserProductLog(getUserProductLog(newUserProduct, Consts.AdminPortal.actionType.CREATE));
            
            //修改 User Product Allotment
            userProductAllotment.setEffDate(DateUtil.getThisMonthFirstDateofMonth(new Date()));
            userProductAllotment.setExpDate(DateUtil.getThisMonthLastDateofMonth(new Date()));
            userProductAllotment.setAlltTime(new Date());
            userProductAllotment.setAlltStatus(Consts.AdminPortal.allotStatus.SUCC);
            userProductAllotment.setUpdBy(username);
            userProductAllotment.setUpdDate(new Date());
            userProductAllotmentDao.updateUserProductAllotment(userProductAllotment);
            userProductAllotmentLogDao.addUserProductAllotmentLog(getUserProductAllotmentLog(userProductAllotment, Consts.AdminPortal.actionType.CHANGE));
            
            //修改 User Product payment
            UserProductPayment userProductPayment=  userProductPaymentDao.getUserProductPayment(userProductAllotment.getPayReqId());
            userProductPayment.setPaySatus(Consts.AdminPortal.payStatus.MEMO);
			if(userProductPayment.getProdId().substring(0, 3).equals("SHK")){
				userProductPayment.setMemoCode(Consts.AdminPortal.memeoCode.MDAC);
				userProductPayment.setDebtRemarks(MemoDebitRemarkUtil.generateMemoRemarks(MemoDebitRemarkUtil.SHK, new Date()));	
			}else{
				userProductPayment.setMemoCode(Consts.AdminPortal.memeoCode.MDAE);
				userProductPayment.setDebtRemarks(MemoDebitRemarkUtil.generateMemoRemarks(MemoDebitRemarkUtil.RTQ_SEC_STR_HK, new Date()));
			}
			userProductPayment.setUpdBy(username);
			userProductPayment.setUpdDate(new Date());
			userProductPayment.setReqSys(Consts.AdminPortal.requestSYS.MIS);
			userProductPaymentDao.updateUserProductPayment(userProductPayment);
			
			//为客户分配RTQ服务所需的访问账号
//    			List<RtqAccount> notTakenUprtqAccountList= rtqAccountDao.findNotTakenUpAccount(userProductAllotment.getProdId());
//    			if(notTakenUprtqAccountList.size()>0){
//    				RtqAccount notTakenUprtqAccount=(RtqAccount)notTakenUprtqAccountList.get(0);
//    				RtqAccountAssignment rtqAccountAssignment =new RtqAccountAssignment();
//    				RtqAccAsgnId rtqAccAsgnId=new RtqAccAsgnId();
//    				rtqAccAsgnId.setClntId(userProductAllotment.getClntId());
//    				rtqAccAsgnId.setProdId(notTakenUprtqAccount.getId().getProdId());
//    				rtqAccAsgnId.setRtqLognId(notTakenUprtqAccount.getId().getRtqLognId());    				
//    				rtqAccountAssignment.setId(rtqAccAsgnId);
//    				rtqAccountAssignment.setRtqLognPwd(notTakenUprtqAccount.getRtqLognPwd());
//    				rtqAccountAssignment.setUpdBy(username);
//    				rtqAccountAssignment.setUpdDate(new Date());
//    				rtqAccountAssignmentDao.addRtqAccountAssignment(rtqAccountAssignment);
//    				
//    			}
	    }else{ //	如果User Product <> null
	    	if(userProduct.getExprDate().before(new Date())){		    		
		    	if(	userProductAllotment.getAlltStatus().equals(Consts.AdminPortal.allotStatus.RESERVEANDAUTO)){
		    		userProduct.setAllwRenl(Consts.AdminPortal.allowRenewal.YES);
		    	}else{
		    		userProduct.setAllwRenl(Consts.AdminPortal.allowRenewal.NO);
		    	}		
		    	userProduct.setStatus(Consts.AdminPortal.productStatus.AVAIL);
		    	userProduct.setExprDate(DateUtil.getThisMonthLastDateofMonth(new Date()));
		    	userProduct.setUpdBy(username);
		    	userProduct.setUpdDate(new Date());
		    	userProductDao.updateUserProduct(userProduct);
		    	userProductLogDao.addUserProductLog(getUserProductLog(userProduct, Consts.AdminPortal.actionType.CHANGE));
		    	
                //修改 User Product Allotment
                userProductAllotment.setEffDate(DateUtil.getThisMonthFirstDateofMonth(new Date()));
                userProductAllotment.setExpDate(DateUtil.getThisMonthLastDateofMonth(new Date()));
                userProductAllotment.setAlltTime(new Date());
                userProductAllotment.setAlltStatus(Consts.AdminPortal.allotStatus.SUCC);
                userProductAllotment.setUpdBy(username);
                userProductAllotment.setUpdDate(new Date());
                userProductAllotmentDao.updateUserProductAllotment(userProductAllotment);
                userProductAllotmentLogDao.addUserProductAllotmentLog(getUserProductAllotmentLog(userProductAllotment, Consts.AdminPortal.actionType.CHANGE));
                
                //修改 User Product Payment
                UserProductPayment userProductPayment=  userProductPaymentDao.getUserProductPayment(userProductAllotment.getPayReqId());
                userProductPayment.setPaySatus(Consts.AdminPortal.payStatus.MEMO);
                if(userProductPayment.getProdId().substring(0, 3).equals("SHK")){
    				userProductPayment.setMemoCode(Consts.AdminPortal.memeoCode.MDAC);
    				userProductPayment.setDebtRemarks(MemoDebitRemarkUtil.generateMemoRemarks(MemoDebitRemarkUtil.SHK, new Date()));
    			}else{
    				userProductPayment.setMemoCode(Consts.AdminPortal.memeoCode.MDAE);
    				userProductPayment.setDebtRemarks(MemoDebitRemarkUtil.generateMemoRemarks(MemoDebitRemarkUtil.RTQ_SEC_STR_HK, new Date()));
    			}
    			userProductPayment.setUpdBy(username);
    			userProductPayment.setUpdDate(new Date());
    			userProductPayment.setReqSys(Consts.AdminPortal.requestSYS.MIS);
    			userProductPaymentDao.updateUserProductPayment(userProductPayment);
    			
    			//为客户分配RTQ服务所需的访问账号
//	    			List<RtqAccount> notTakenUprtqAccountList= rtqAccountDao.findNotTakenUpAccount(userProduct.getId().getProdId());
//	    			if(notTakenUprtqAccountList.size()>0){
//	    				RtqAccount notTakenUprtqAccount=(RtqAccount)notTakenUprtqAccountList.get(0);
//	    				RtqAccountAssignment rtqAccountAssignment =new RtqAccountAssignment();
//	    				RtqAccAsgnId rtqAccAsgnId=new RtqAccAsgnId();
//	    				rtqAccAsgnId.setClntId(userProduct.getId().getClntId());
//	    				rtqAccAsgnId.setProdId(notTakenUprtqAccount.getId().getProdId());
//	    				rtqAccAsgnId.setRtqLognId(notTakenUprtqAccount.getId().getRtqLognId());
//	    				rtqAccountAssignment.setId(rtqAccAsgnId);
//	    				rtqAccountAssignment.setRtqLognPwd(notTakenUprtqAccount.getRtqLognPwd());
//	    				rtqAccountAssignment.setUpdBy(username);
//	    				rtqAccountAssignment.setUpdDate(new Date());
//	    				rtqAccountAssignmentDao.addRtqAccountAssignment(rtqAccountAssignment);	    				
//	    			}	    			
	    	}
	    }		    
	}

	@Override
	public List<UserProductAllotment> findMemoDebitUserProductAllotment() {
		return userProductAllotmentDao.findMemoDebitList();
	}

	@Override
	public boolean updateUserProductPayment(String userProductPaymentId,String reference, String debtRemarks, Date reqTime, Date resTime, String actionBy, boolean succFlag) {
		UserProductPayment userProductPayment=userProductPaymentDao.getUserProductPayment(userProductPaymentId);
		if(succFlag){
			userProductPayment.setPaySatus(Consts.AdminPortal.payStatus.FULLYPAID);
		}
		userProductPayment.setResMessage(reference);
		userProductPayment.setDebtRemarks(debtRemarks);
		userProductPayment.setReqTime(reqTime);
		userProductPayment.setResTime(resTime);
		userProductPayment.setUpdBy(actionBy);
		userProductPayment.setUpdDate(new Date());
		userProductPaymentDao.updateUserProductPayment(userProductPayment);
		return true;
	}

	@Override
	public AddProductResponseModel addProduct(
			AddProductRequestModel addProductRequestModel) {
		AddProductResponseModel res =new AddProductResponseModel();
		Product product = productDao.getProduct(addProductRequestModel.getProduct().getProdId());
		if(product==null){
			productDao.addProduct(addProductRequestModel.getProduct());
			res.setReturnCode(Consts.AdminPortal.returnResulst.SUCCESS);
		}else{
			res.setReturnCode(Consts.Error.Code.ADMINPROTAL_PRODUCT_EXISTED);
		}
		return res;
	}

	@Override
	public ListServiceProductResponseModel listServiceProduct() {
		ListServiceProductResponseModel res =new ListServiceProductResponseModel();
		res.setProductList(productDao.getProductList());
		return res;
	}


	@Override
	public MonthlyPurchaseEnquiryResponseModel monthlyPurchaseEnquiry(MonthlyPurchaseEnquiryRequestModel model) {
		MonthlyPurchaseEnquiryResponseModel res =new MonthlyPurchaseEnquiryResponseModel();
		Page page= userProductAllotmentDao.findMonthlyPurchaseEnquiry(model.getProductId(),model.getYearMonth(),model.getPageNumber(),model.getPageSize());
		res.setPage(page);
		return res;
	}

	@Override
	public ReserveAndRenewalEnquiryResponseModel reserveAndRenewalEnquiry(
			ReserveAndRenewalEnquiryRequestModel reserveAndRenewalEnquiryRequestModel) {
		ReserveAndRenewalEnquiryResponseModel res =new ReserveAndRenewalEnquiryResponseModel();
//		res.setUserProductAllotmentList(userProductAllotmentDao.findReserveList());
//		res.setUserProductList(userProductDao.findRenewalList());
	//	res.setUserProductList(userProductDao.findreserveAndRenewalEnquiry());
		return res;
	}	
	
	@Override
	public Page findReserveAndRenewalEnquiry(Integer pageNumber,
			Integer pageSize,String productId) {
		return userProductDao.findReserveAndRenewalEnquiry(pageNumber, pageSize,productId);
	}

	@Override
	public RTQAccountAssignmentResponseModel findRTQAccountAssignmentByProductId(
			RTQAccountAssignmentRequestModel rtqAccountAssignmentRequestModel) {
		RTQAccountAssignmentResponseModel res =new RTQAccountAssignmentResponseModel();
		Page page=rtqAccountAssignmentDao.getRtqAccountAssignmentByProductId(rtqAccountAssignmentRequestModel.getProductId(),rtqAccountAssignmentRequestModel.getPageNumber(),rtqAccountAssignmentRequestModel.getPageSize());
		res.setPage(page);
		return res;
	}

	@Override
	public RTQAccountResponseModel listRtqAccountByProIdOrLoginId(RTQAccountRequestModel rtqAccountRequestModel) {
		RTQAccountResponseModel res=new RTQAccountResponseModel();
		Page page=rtqAccountDao.listRtqAccountByProIdOrLoginId(rtqAccountRequestModel.getProductId(), rtqAccountRequestModel.getRtqLoginId(), rtqAccountRequestModel.getPageNumber(), rtqAccountRequestModel.getPageSize());
		res.setPage(page);
		return res;
	}
	
	
	@Override
	public RTQApplicationResponseModel findRTQApplicationByProductId(
			RTQApplicationResquestModel rtqApplicationResquestModel) {
		RTQApplicationResponseModel res =new RTQApplicationResponseModel();
		res.setRtqApplication(rtqApplicationDao.getRTQApplication(rtqApplicationResquestModel.getProductId()));
		return res;
	}

	@Override
	public ServiceProductResponseModel findServiceProductByProductId(
			ServiceProductRequestModel serviceProductRequestModel) {
		ServiceProductResponseModel res =new ServiceProductResponseModel();
	    res.setProduct(productDao.getProduct(serviceProductRequestModel.getProductId()));
		return res;
	}
	
	@Override
	public String addAclUser(AclUserProfile aclUser) {
		aclUserProfileDao.addAclUserProfile(aclUser);
		return Consts.AdminPortal.returnResulst.SUCCESS;
	}

	@Override
	public String deleteAclUser(String loginId) {
		aclUserProfileDao.deleteAclUserProfile(loginId);
		return Consts.AdminPortal.returnResulst.SUCCESS;
	}

	@Override
	public AclUserResponseModel getAclUser(String loginId) {
		AclUserResponseModel model=new AclUserResponseModel();
		model.setAclUserProfile(aclUserProfileDao.getAclUserProfile(loginId));
		return model;
	}

	@Override
	public AclUserResponseModelList listAclUser(StringMap paramsMap,Integer pageNumber,Integer pageSize) {
		AclUserResponseModelList modelList=new AclUserResponseModelList();
	    Page page=aclUserProfileDao.listAclUserProfile(paramsMap, pageNumber, pageSize);
	    modelList.setPage(page);
		return modelList;
	}

	@Override
	public String updateAclUser(AclUserProfile aclUser) {
		aclUserProfileDao.updateAclUserProfile(aclUser);
		return Consts.AdminPortal.returnResulst.SUCCESS;
	}

	@Override
	public String addAclRole(AclRole aclRole) {
		String[] selFunId=aclRole.getSelectFunctionsId();
		if(selFunId!=null&&selFunId.length>0){
			AclRoleFnctnPrmisn arfp=null;
			for (int i = 0; i < selFunId.length; i++) {
				String funId=selFunId[i];
				arfp=new AclRoleFnctnPrmisn();
				arfp.setAclRoleFnctnPrmisnId(String.valueOf(System.currentTimeMillis())+String.valueOf(i));
				arfp.setFnctnId(funId);
				arfp.setRoleId(aclRole.getRoleId());
				arfp.setInitBy(aclRole.getInitBy());
				arfp.setInitDate(aclRole.getInitDate());
				arfp.setUpdBy(aclRole.getUpdBy());
				arfp.setUpdDate(aclRole.getUpdDate());
				arfp.setPrmisnCde("A");
				aclRoleFnctnPrmisnDao.addAclRoleFuctnPrmisn(arfp);
			}
			 aclRoleDao.addAclRole(aclRole);
		}
		return Consts.AdminPortal.returnResulst.SUCCESS;
	}

	@Override
	public String deleteRole(String roleId) {
		String returnCode="";
		int count=aclUserProfileDao.findUserCountByRoleId(roleId);
		if(count>0){
			returnCode="This Role be used!";
		} else  {
		    aclRoleFnctnPrmisnDao.deleteAclRoleFuctnByRoleId(roleId);
		    aclRoleDao.deleteAclRole(roleId);
		    returnCode=Consts.AdminPortal.returnResulst.SUCCESS;
		}
		return returnCode;
	}

	@Override
	public AclRoleResponseModel getAclRole(String aclRoleId) {
		AclRoleResponseModel resModel=new AclRoleResponseModel();
		resModel.setAclRole(aclRoleDao.getAclRole(aclRoleId));
		resModel.setReturnCode(Consts.AdminPortal.returnResulst.SUCCESS);
		return resModel;
	}

	@Override
	public AclRoleResponseModelList listAclRole(StringMap parmsMap,
			Integer pageNumber, Integer pageSize) {
		AclRoleResponseModelList modelList=new AclRoleResponseModelList();
		modelList.setPage(aclRoleDao.listAclRolePage(parmsMap, pageNumber, pageSize));
		return modelList;
	}

	@Override
	public String updateAclRole(AclRole aclRole) {
		aclRoleFnctnPrmisnDao.deleteAclRoleFuctnByRoleId(aclRole.getRoleId());
		String[] selFunId=aclRole.getSelectFunctionsId();
		if(selFunId!=null&&selFunId.length>0){
			AclRoleFnctnPrmisn arfp=null;
			for (int i = 0; i < selFunId.length; i++) {
				String funId=selFunId[i];
				 arfp=new AclRoleFnctnPrmisn();
				arfp.setAclRoleFnctnPrmisnId(String.valueOf(System.currentTimeMillis())+String.valueOf(i));
				arfp.setFnctnId(funId);
				arfp.setRoleId(aclRole.getRoleId());
				arfp.setInitBy(aclRole.getInitBy());
				arfp.setInitDate(aclRole.getInitDate());
				arfp.setUpdBy(aclRole.getUpdBy());
				arfp.setUpdDate(aclRole.getUpdDate());
				arfp.setPrmisnCde("A");
				aclRoleFnctnPrmisnDao.addAclRoleFuctnPrmisn(arfp);
			}
		}
		aclRoleDao.updateAclRole(aclRole);
		return Consts.AdminPortal.returnResulst.SUCCESS;
	}

	@Override
	public AclUserResponseModel loginAclUserProfile(String lognId) {
		AclUserResponseModel model=new AclUserResponseModel();
		model.setAclUserProfile(aclUserProfileDao.loginAclUserProfile(lognId));
		return model;
	}

	@Override
	public AclRoleResponseModelList listAclRole() {
		AclRoleResponseModelList modelList=new AclRoleResponseModelList();
		modelList.setAclRoleList(aclRoleDao.listAclRole());
		return modelList;
	}


	@Override
	public List<NotificationMedia> getNotificationMediaByType(String notfType) {
		return notificationMediaDao.getNotificationMediaByType(notfType);
	}

	@Override
	public ListRtqAccountAssResponseModel listRtqAccountAssByProductId(ListRtqAccountAssRequestModel listRtqAccountAssRequestModel) {
		ListRtqAccountAssResponseModel res =new ListRtqAccountAssResponseModel();
		List<RtqAccountAssDto>  rtqAccountAssDtoList =rtqAccountAssignmentDao.listRtqAccountAssByProductId(listRtqAccountAssRequestModel.getProductId());
		res.setRtqAccountAssDtoList(rtqAccountAssDtoList);
		return res;
	}

	private ProductChangeLog getProductLog(Product product, String actionType){
		ProductChangeLog productChangeLog=new ProductChangeLog();
		productChangeLog.setProdChgLogId(String.valueOf(System.currentTimeMillis()));
		productChangeLog.setAcesUnit(product.getAcesUnit());
		productChangeLog.setActnBy(actionType);
		productChangeLog.setActnDate(new Date());
		productChangeLog.setActnType(Consts.AdminPortal.actionType.CHANGE);
		productChangeLog.setAlltQuota(product.getAlltQuota());
		productChangeLog.setBillType(product.getBillType());
		productChangeLog.setDiscType(product.getDiscType());
		productChangeLog.setEffDate(product.getEffDate());
		productChangeLog.setExprDate(product.getExprDate());
		productChangeLog.setInitBy(product.getInitBy());
		productChangeLog.setInitDate(product.getInitDate());
		productChangeLog.setPriceInHkd(product.getPriceInHkd());
		productChangeLog.setProdId(product.getProdId());
		productChangeLog.setProdStatus(product.getProdStatus());
		productChangeLog.setQuota(product.getQuota());
		productChangeLog.setRemarks(product.getRemarks());
		productChangeLog.setSvcMode(product.getSvcMode());
		productChangeLog.setUpdBy(product.getUpdBy());
		productChangeLog.setUpdDate(product.getUpdDate());
		productChangeLog.setValtOfSvc(product.getValtOfSvc());
		return productChangeLog;
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
	
	
	private List<RtqAccountAssignmentLog> getRtqAccountAssignmentLog(List<RtqAccountAssignment> rtqAccountAssignmentList, String actionType){
		List<RtqAccountAssignmentLog> list =new ArrayList<RtqAccountAssignmentLog>(); 
		for (int i = 0; i < rtqAccountAssignmentList.size(); i++) {
			RtqAccountAssignment rtqAccountAssignment=(RtqAccountAssignment)rtqAccountAssignmentList.get(i);
			RtqAccountAssignmentLog rtqAccountAssignmentLog = new RtqAccountAssignmentLog();
			rtqAccountAssignmentLog.setActnBy(rtqAccountAssignment.getId().getClntId());
			rtqAccountAssignmentLog.setActnDate(new Date());
			rtqAccountAssignmentLog.setActnType(actionType);
			rtqAccountAssignmentLog.setClntId(rtqAccountAssignment.getId().getClntId());
			rtqAccountAssignmentLog.setInitBy(rtqAccountAssignment.getInitBy());
			rtqAccountAssignmentLog.setInitDate(rtqAccountAssignment.getInitDate());
			rtqAccountAssignmentLog.setProdId(rtqAccountAssignment.getId().getProdId());
			rtqAccountAssignmentLog.setRtqAccAsgnLogId(String.valueOf(System.currentTimeMillis())+String.valueOf(i));
			rtqAccountAssignmentLog.setRtqLognId(rtqAccountAssignment.getId().getRtqLognId());
			rtqAccountAssignmentLog.setRtqLognPwd(rtqAccountAssignment.getRtqLognPwd());
			rtqAccountAssignmentLog.setUpdBy(rtqAccountAssignment.getUpdBy());
			rtqAccountAssignmentLog.setUpdDate(rtqAccountAssignment.getUpdDate());	
			list.add(rtqAccountAssignmentLog);
		}		
		return list;
	}

	@Override
	public ServicesAccessLogByRtqAccAsgnIdResponseModel getServicesAccessLogByRtqAccAsgnId(ServicesAccessLogByRtqAccAsgnIdRequestModel servicesAccessLogByRtqAccAsgnIdRequestModel){
		ServicesAccessLogByRtqAccAsgnIdResponseModel res =new ServicesAccessLogByRtqAccAsgnIdResponseModel();
		res.setServicesAccessLog(servicesAccessLogDao.getServicesAccessLogByRtqAccAsgnId(servicesAccessLogByRtqAccAsgnIdRequestModel.getId()));
		return res;
	}

	@Override
	public ProductResponseModel getProduct(ProductRequestModel productRequestModel) {
		ProductResponseModel res = new ProductResponseModel();
		res.setProduct(productDao.getProduct(productRequestModel.getProductId()));
		return res;
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

	@Override
	public void updateMisDayEndProcessingFlag(String flag) {
		csParameterDao.updateMisDayEndProcessingFlag(flag);
	}
	
	public ReserveUserProductResponseModel findreserveAndRenewalEnquiryById(ReserveUserProductRequestModel reserveUserProductRequestModel){
		ReserveUserProductResponseModel reserveUserProductResponseModel = new ReserveUserProductResponseModel();
		reserveUserProductResponseModel.setReservedUserProductList(userProductDao.findreserveAndRenewalEnquiryById(reserveUserProductRequestModel.getClientId()));
		return reserveUserProductResponseModel;
	}
	
	@Override
	public ReserveServiceResponseModel reserveService(ReserveServiceRequestModel reserveServiceRequestModel, Long priceInHkd) {// reserve RTQ product
		ReserveServiceResponseModel reserveServiceResponseModel = new ReserveServiceResponseModel();
		boolean createFlag = true;				
		//get the using user product(RTQ)
		UserProduct userProduct = userProductDao.findAvailUserProductByClientIdAndRTQ(reserveServiceRequestModel.getClientId());
		if(userProduct!=null){ 
			userProduct.setAllwRenl(Consts.AdminPortal.allowRenewal.NO);
			userProduct.setUpdBy(reserveServiceRequestModel.getUpdBy());
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
					userProductAllotment.setUpdBy(reserveServiceRequestModel.getUpdBy());
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
			userProductAllotment.setInitBy(reserveServiceRequestModel.getUpdBy());
			userProductAllotment.setInitDate(new Date());
			userProductAllotment.setUpdBy(reserveServiceRequestModel.getUpdBy());
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
			userProductPayment.setInitBy(reserveServiceRequestModel.getUpdBy());
			userProductPayment.setInitDate(new Date());
			userProductPayment.setUpdBy(reserveServiceRequestModel.getUpdBy());
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
			UserProduct userProduct = userProductDao.findUserProductById(userProductRequest.getId().getProdId(), userProductRequest.getId().getClntId());
			if(userProduct!=null && userProduct.getStatus().equals(Consts.AdminPortal.productStatus.AVAIL) && userProduct.getAllwRenl().equals(Consts.AdminPortal.allowRenewal.YES)){
				userProduct.setAllwRenl(Consts.AdminPortal.allowRenewal.NO);
				userProduct.setUpdBy(userProductRequest.getUpdBy());
				userProduct.setUpdDate(new Date());
				userProductDao.updateUserProduct(userProduct);
				userProductLogDao.addUserProductLog(getUserProductLog(userProduct, Consts.AdminPortal.actionType.CHANGE));
			} else {
				if(!userProductRequest.getId().getProdId().equals("SHK")){
					UserProductAllotment userProductAllotment = userProductAllotmentDao.findReserveUserProductAllotmentByClnId(userProductRequest.getId().getClntId());	
					if(userProductAllotment != null){
						//delete the existed User Product Allotment(RESERVE|RESRV_AUTO) and User Product Payment(PENDING)
						userProductAllotmentDao.deleteUserProductAllotmentByClnId(userProductRequest.getId().getClntId());						
						userProductPaymentDao.deleteUserProductPaymentByClnId(userProductRequest.getId().getClntId());
						userProductAllotmentLogDao.addUserProductAllotmentLog(getUserProductAllotmentLog(userProductAllotment, Consts.AdminPortal.actionType.DELETE));
					}	
				}
			}
		}else{
			userProductResponseModel.setReturnCode(Consts.Error.Code.CANCEL_RESERVED_USERPRODUCT_IS_NULL);
		}
		
		return userProductResponseModel;
	}
	
	@Override
	public List<Product> getProductList(String cnDiscFlag) {
		return productDao.findProductListByClientCnDiscFlag(cnDiscFlag);
	}
	
	@Override
	public Product findProuctByProductId(String productId) {
		return productDao.getProduct(productId);
	}
	
	@Override
	public List<AutoPurchase> getAutoPurchaseList(String status, String startTime, String endTime){
		return autoPurchaseDao.getAutoPurchaseList(status, startTime, endTime);
	}
	
	@Override
	public void addAutoPurchase(AutoPurchase autoPurchase){
		autoPurchaseDao.addAutoPurchase(autoPurchase);
	}

	@Override
	public ListUserPaymentByManualResponseModel getUserProductPaymentListByManual(Date startTime, Date endTime, int pageNumber, int pageSize) {
		ListUserPaymentByManualResponseModel model = new ListUserPaymentByManualResponseModel();
		model.setPage(userProductPaymentDao.getUserProductPaymentListByManual(startTime, endTime, pageNumber, pageSize));
		return model;
	}
	@Override
	public void updateUserProductPaymentByManual(String[] usrProdPayIds) {
		userProductPaymentDao.updateUserProductPaymentByManual(usrProdPayIds);
	}

}


