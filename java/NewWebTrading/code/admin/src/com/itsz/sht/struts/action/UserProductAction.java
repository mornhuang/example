package com.itsz.sht.struts.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.LabelValueBean;

import com.itsz.sht.common.Consts;
import com.itsz.sht.common.model.common.request.ListSelectServiceRequestModel;
import com.itsz.sht.common.model.common.request.ReserveServiceRequestModel;
import com.itsz.sht.common.model.common.request.ReserveUserProductRequestModel;
import com.itsz.sht.common.model.common.request.UpdateUserProductRequestModel;
import com.itsz.sht.common.model.common.request.UserProductRequestModel;
import com.itsz.sht.common.model.common.request.UserProfileRequestModel;
import com.itsz.sht.common.model.common.response.ListSelectServiceResponseModel;
import com.itsz.sht.common.model.common.response.ReserveUserProductResponseModel;
import com.itsz.sht.common.model.common.response.UserProductResponseModel;
import com.itsz.sht.common.model.common.response.UserProfileResponseModel;
import com.itsz.sht.dao.hibernate.model.AclUserProfile;
import com.itsz.sht.dao.hibernate.model.Product;
import com.itsz.sht.dao.hibernate.model.RtqAccountAssignment;
import com.itsz.sht.dao.hibernate.model.UserProduct;
import com.itsz.sht.dao.hibernate.model.UserProductAllotment;
import com.itsz.sht.dao.hibernate.model.UsrProdId;
import com.itsz.sht.model.ReserveAndRenewalEnquiry;
import com.itsz.sht.model.UserProductRequest;
import com.itsz.sht.struts.form.UserProductForm;
import com.taifook.adminportal.common.Constants;


public class UserProductAction extends ITSZAction {

	@Override
	public ActionForward executeAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		return null;
	}

	public ActionForward listUserProductByClientId(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		UserProductForm upForm = (UserProductForm) form;
		String clientIdStr = request.getParameter("clientIdss");
		if(clientIdStr==null || clientIdStr.equals("")){
			clientIdStr=upForm.getClntId();
		}
		String clientLoginIdStr = request.getParameter("clientLoginIdss");
		if(clientLoginIdStr==null || clientLoginIdStr.equals("")){
			clientLoginIdStr = upForm.getClntLoginId();
		}
		upForm.setClntId(clientIdStr);
	    upForm.setClntLoginId(clientLoginIdStr);
		request.setAttribute("userProductForm", upForm);
		
		UserProfileRequestModel rModel=new UserProfileRequestModel();
		rModel.setClientId(clientIdStr);
		rModel.setClntLoginId(clientLoginIdStr);
		UserProfileResponseModel userProfile = facade.findAdminUserProfile(rModel);
		clientIdStr = userProfile.getClientId();
		clientLoginIdStr = userProfile.getClientLoginId();
		
		if(clientIdStr!=null && !clientIdStr.equals("")){
			request.setAttribute("clientId", clientIdStr);
			request.setAttribute("clientLoginId", clientLoginIdStr);
			
			List<LabelValueBean> statusList = new ArrayList<LabelValueBean>();
			statusList.add(new LabelValueBean("AVAIL", "AVAIL"));
			statusList.add(new LabelValueBean("UNAVAIL", "UNAVAIL"));
	
			List<LabelValueBean> allowRenList = new ArrayList<LabelValueBean>();
			allowRenList.add(new LabelValueBean("Y", "Y"));
			allowRenList.add(new LabelValueBean("N", "N"));
	
			UserProductRequestModel reqModel = new UserProductRequestModel();
			reqModel.setClientId(clientIdStr);
			UserProductResponseModel resModel = facade.findUserProductStautsByClientId(reqModel);
			String returnCode = resModel.getReturnCode();
			if (returnCode.equals(Consts.AdminPortal.returnResulst.SUCCESS)) {
				request.setAttribute("userProductList", resModel.getUserProductList());
				request.setAttribute("statusList", statusList);
				request.setAttribute("allowRenList", allowRenList);
				request.setAttribute("userProfile", userProfile);
				return mapping.findForward("success");
			} else {
				return mapping.findForward("failure");
			}
		}
		return mapping.findForward("failure");
	}

	public ActionForward updateUserProductStatusList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		AclUserProfile user = (AclUserProfile) request.getSession().getAttribute(Consts.AdminPortal.userContext.USERCONTEXT);
		UserProductForm upForm = (UserProductForm) form;
		String[] statusOld = upForm.getStatusOld();
		String[] allwRenOld = upForm.getAllwRenlOld();
		String[] allwRenNew = upForm.getAllwRenl();
		String[] statusNew = upForm.getStatus();
		String[] proId = upForm.getProdId();
		String clientIdStr = request.getParameter("clientIdss");
		if(clientIdStr==null || clientIdStr.equals("")){
			clientIdStr=upForm.getClntId();
		}
		String clientLoginIdStr = request.getParameter("clientLoginIdss");
		if(clientLoginIdStr==null || clientLoginIdStr.equals("")){
			clientLoginIdStr = upForm.getClntLoginId();
		}
	    upForm.setClntId(clientIdStr);
	    upForm.setClntLoginId(clientLoginIdStr);
	    request.setAttribute("userProductForm", upForm);
	    
	    UserProfileRequestModel rModel=new UserProfileRequestModel();
		rModel.setClientId(clientIdStr);
		rModel.setClntLoginId(clientLoginIdStr);
		UserProfileResponseModel userProfile = facade.findAdminUserProfile(rModel);
		clientIdStr = userProfile.getClientId();
		clientLoginIdStr = userProfile.getClientLoginId();
	    
		if (statusOld != null && statusOld.length > 0) {
			String proIdStr, statusOldStr, statusNewStr, allwRenNewStr, allwRewOldStr;
			UpdateUserProductRequestModel reModel = new UpdateUserProductRequestModel();
			List<UserProductRequest> list = new ArrayList<UserProductRequest>();
			for (int i = 0; i < statusOld.length; i++) {
				proIdStr = proId[i];
				statusOldStr = statusOld[i];
				statusNewStr = statusNew[i];
				allwRenNewStr = allwRenNew[i];
				allwRewOldStr = allwRenOld[i];
				if (statusOldStr.equals(statusNewStr)&& allwRewOldStr.equals(allwRenNewStr)) {
					continue;
				} else {
					UserProductRequest upr = new UserProductRequest();
					UsrProdId upid = new UsrProdId();
					upid.setClntId(clientIdStr);
					upid.setProdId(proIdStr);
					upr.setAllwRenl(allwRenNewStr);
					upr.setStatus(statusNewStr);
					upr.setId(upid);
					upr.setUpdBy(user.getLognId());
					list.add(upr);
				}
			}
			reModel.setUserProductRequestList(list);
			String returnCode = facade.updateUserProductStatus(reModel).getReturnCode();
			
			if (returnCode.equals(Consts.AdminPortal.returnResulst.SUCCESS)) {
				return mapping.findForward("success");
			} else {
				return mapping.findForward("failure");
			}
		}
		return mapping.findForward("failure");
	}
	
	public ActionForward listCancelUserProduct(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserProductForm upForm = (UserProductForm) form;
		String clientIdStr = request.getParameter("clientIdss");
		if(clientIdStr==null || clientIdStr.equals("")){
			clientIdStr=upForm.getClntId();
		}
		String clientLoginIdStr = request.getParameter("clientLoginIdss");
		if(clientLoginIdStr==null || clientLoginIdStr.equals("")){
			clientLoginIdStr = upForm.getClntLoginId();
		}
		upForm.setClntId(clientIdStr);
		upForm.setClntLoginId(clientLoginIdStr);
		request.setAttribute("userProductForm", upForm);
		
		UserProfileRequestModel rModel=new UserProfileRequestModel();
		rModel.setClientId(clientIdStr); 
		rModel.setClntLoginId(clientLoginIdStr);
		UserProfileResponseModel userProfile = facade.findAdminUserProfile(rModel);
		clientIdStr = userProfile.getClientId();
		clientLoginIdStr = userProfile.getClientLoginId();
		
		if(clientIdStr!=null && !clientIdStr.equals("")){
			request.setAttribute("clientId", clientIdStr);
			request.setAttribute("clientLoginId", clientLoginIdStr);
			request.setAttribute("userProfile", userProfile);
			
			UserProductRequestModel uprModel=new UserProductRequestModel();
			uprModel.setClientId(clientIdStr);
			UserProductResponseModel model=facade.findCancelUserProductByClientId(uprModel);
			List<UserProduct> userProductList=new ArrayList<UserProduct>();
		    userProductList=model.getUserProductList();
			if(userProductList!=null&&userProductList.size()>0){
				request.setAttribute("userProductList", userProductList);
			}
			if (model.getReturnCode().equals(Consts.AdminPortal.returnResulst.SUCCESS)) {
				return mapping.findForward("success");
			} else {
				return mapping.findForward("failure");
			}
		}
		return mapping.findForward("failure");
	}
	
	public ActionForward listReserveProduct(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserProductForm upForm = (UserProductForm) form;
		String clientIdStr = request.getParameter("clientIdss");
		if(clientIdStr==null || clientIdStr.equals("")){
			clientIdStr=upForm.getClntId();
		}
		String clientLoginIdStr = request.getParameter("clientLoginIdss");
		if(clientLoginIdStr==null || clientLoginIdStr.equals("")){
			clientLoginIdStr = upForm.getClntLoginId();
		}
		upForm.setClntId(clientIdStr);
		upForm.setClntLoginId(clientLoginIdStr);
		request.setAttribute("userProductForm", upForm);
		
		UserProfileRequestModel rModel=new UserProfileRequestModel();
		rModel.setClientId(clientIdStr); 
		rModel.setClntLoginId(clientLoginIdStr);
		UserProfileResponseModel userProfile = facade.findAdminUserProfile(rModel);
		clientIdStr = userProfile.getClientId();
		clientLoginIdStr = userProfile.getClientLoginId();
		
		List<LabelValueBean> allowRenList = new ArrayList<LabelValueBean>();
		allowRenList.add(new LabelValueBean("Y", "Y"));
		allowRenList.add(new LabelValueBean("N", "N"));
		
		List<Product> productVList=facade.getProductList(userProfile.getChinaDiscountFlag()==null || userProfile.getChinaDiscountFlag().equals("")?"OTHERS":userProfile.getChinaDiscountFlag());
		List<LabelValueBean> productList=new ArrayList<LabelValueBean>();
		for (Product product : productVList) {
			if(!product.getProdId().equals("SHK"))
			{
				productList.add(new LabelValueBean(product.getProdId(),product.getProdId()));
			}
		}
		
		if(clientIdStr!=null && !clientIdStr.equals("")){
			request.setAttribute("clientId", clientIdStr);
			request.setAttribute("clientLoginId", clientLoginIdStr);
			request.setAttribute("userProfile", userProfile);
			
			ReserveUserProductRequestModel reserveUserProductRequestModel =new ReserveUserProductRequestModel();
			reserveUserProductRequestModel.setClientId(clientIdStr);
			ReserveUserProductResponseModel model = facade.findreserveAndRenewalEnquiryById(reserveUserProductRequestModel);
			List<ReserveAndRenewalEnquiry> reservedUserProductList =new ArrayList<ReserveAndRenewalEnquiry>();
			reservedUserProductList=model.getReservedUserProductList();
			if(reservedUserProductList!=null&&reservedUserProductList.size()>0){
				request.setAttribute("reservedUserProductList", reservedUserProductList);
			}
			request.setAttribute("allowRenList", allowRenList);
			request.setAttribute("productList", productList);
			if (model.getReturnCode().equals(Consts.AdminPortal.returnResulst.SUCCESS)) {
				return mapping.findForward("success");
			} else {
				return mapping.findForward("failure");
			}
		}
		return mapping.findForward("failure");
	}
	
	public ActionForward cancelReservedUserProduct(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		AclUserProfile user = (AclUserProfile) request.getSession().getAttribute(Consts.AdminPortal.userContext.USERCONTEXT);
		UserProductForm upForm = (UserProductForm) form;
		String clientIdStr=request.getParameter("clientId");
		String productIdStr=request.getParameter("productId");
		String clientLoginIdStr = upForm.getClntLoginId();	
		UpdateUserProductRequestModel model=new UpdateUserProductRequestModel();
		List<UserProductRequest> userProductRequestList=new ArrayList<UserProductRequest>();
		UserProductRequest up=new UserProductRequest();
		UsrProdId id=new UsrProdId();
		id.setClntId(clientIdStr);
		id.setProdId(productIdStr);
		up.setId(id);
		up.setUpdBy(user.getLognId());
		userProductRequestList.add(up);
		model.setUserProductRequestList(userProductRequestList);
		String returnCode="";
		returnCode=facade.cancelReservedUserProduct(model).getReturnCode();
		request.setAttribute("clientId", clientIdStr);
		request.setAttribute("clientLoginId", clientLoginIdStr);
		upForm.setClntId(clientIdStr);
		request.setAttribute("userProductForm", upForm);
		if (returnCode.equals(Consts.AdminPortal.returnResulst.SUCCESS)) {
			return mapping.findForward("success");
		} else {
			return mapping.findForward("failure");
		}
	}
	
	public ActionForward reserveUserProduct(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		AclUserProfile user = (AclUserProfile) request.getSession().getAttribute(Consts.AdminPortal.userContext.USERCONTEXT);
		UserProductForm upForm = (UserProductForm) form;
		String clientIdStr=upForm.getClntId();
		String[] proIds = upForm.getProdId();
		String productIdStr = proIds.length>0?proIds[0]:request.getParameter("productId");
		String[] allwRens = upForm.getAllwRenl();
		String allRen = allwRens.length>0?allwRens[0]:"N";
		String clientLoginIdStr = upForm.getClntLoginId();	
		
		UserProfileRequestModel rModel=new UserProfileRequestModel();
		rModel.setClientId(clientIdStr); 
		rModel.setClntLoginId(clientLoginIdStr);
		UserProfileResponseModel userProfile = facade.findAdminUserProfile(rModel);
		clientIdStr = userProfile.getClientId();
		clientLoginIdStr = userProfile.getClientLoginId();
		
		ReserveServiceRequestModel reserveServiceRequestModel = new ReserveServiceRequestModel();
		reserveServiceRequestModel.setAllowRenewal(allRen);
		reserveServiceRequestModel.setClientId(clientIdStr.equals("") ? clientLoginIdStr : clientIdStr);
		reserveServiceRequestModel.setDefDebtAcc("");
		reserveServiceRequestModel.setProductId(productIdStr);
		reserveServiceRequestModel.setUpdBy(user.getLognId());
		String returnCode="";
		returnCode=facade.reserveService(reserveServiceRequestModel).getReturnCode();
		request.setAttribute("clientId", clientIdStr);
		request.setAttribute("clientLoginId", clientLoginIdStr);
		if (returnCode.equals(Consts.AdminPortal.returnResulst.SUCCESS)) {
			return mapping.findForward("success");
		} else {
			return mapping.findForward("failure");
		}
	}
   
	public ActionForward queryCancelUserProduct(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserProductForm upForm = (UserProductForm) form;
		String clientIdStr=upForm.getClntId();
	    String clientLoginIdStr = upForm.getClntLoginId();
		UserProfileRequestModel rModel=new UserProfileRequestModel();
		rModel.setClientId(clientIdStr);
		rModel.setClntLoginId(clientLoginIdStr);
		UserProfileResponseModel userProfile = facade.findAdminUserProfile(rModel);
		clientIdStr = userProfile.getClientId();
		clientLoginIdStr = userProfile.getClientLoginId();
		
		if(clientIdStr!=null && !clientIdStr.equals("")){
			request.setAttribute("clientId", clientIdStr);
			request.setAttribute("clientLoginId", clientLoginIdStr);
			request.setAttribute("userProfile", userProfile);
			UserProductRequestModel uprModel=new UserProductRequestModel();
			uprModel.setClientId(clientIdStr);
			UserProductResponseModel model = facade.findCancelUserProductByClientId(uprModel);
			List<UserProduct> userProductList = model.getUserProductList();
			if(userProductList!=null&&userProductList.size()>0){
				request.setAttribute("userProductList", userProductList);
			}
			if (model.getReturnCode().equals(Consts.AdminPortal.returnResulst.SUCCESS)) {
				return mapping.findForward("success");
			} else {
				return mapping.findForward("failure");
			}
		}
		return mapping.findForward("failure");
	}
	
	
	public ActionForward cancelUserProduct(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		AclUserProfile user = (AclUserProfile) request.getSession().getAttribute(Consts.AdminPortal.userContext.USERCONTEXT);
		UserProductForm upForm = (UserProductForm) form;
		String clientIdStr=request.getParameter("clientId");
		String productIdStr=request.getParameter("productId");
		String clientLoginIdStr = upForm.getClntLoginId();	
		upForm.setClntId(clientIdStr);
		
		UserProfileRequestModel rModel=new UserProfileRequestModel();
		rModel.setClientId(clientIdStr); 
		rModel.setClntLoginId(clientLoginIdStr);
		UserProfileResponseModel userProfile = facade.findAdminUserProfile(rModel);
		clientIdStr = userProfile.getClientId();
		clientLoginIdStr = userProfile.getClientLoginId();
		
		UpdateUserProductRequestModel model=new UpdateUserProductRequestModel();
		List<UserProductRequest> userProductRequestList=new ArrayList<UserProductRequest>();
		UserProductRequest up=new UserProductRequest();
		UsrProdId id=new UsrProdId();
		id.setClntId(clientIdStr);
		id.setProdId(productIdStr);
		up.setId(id);
		up.setUpdBy(user.getLognId());
		userProductRequestList.add(up);
		model.setUserProductRequestList(userProductRequestList);
		String returnCode="";
		returnCode=facade.updateCancelUserProduct(model).getReturnCode();
		request.setAttribute("clientId", clientIdStr);
		request.setAttribute("clientLoginId", clientLoginIdStr);
//		request.setAttribute("userProductForm", upForm);
		if (returnCode.equals(Consts.AdminPortal.returnResulst.SUCCESS)) {
			return mapping.findForward("success");
		} else {
			return mapping.findForward("failure");
		}
	}

	
	public ActionForward listRenewalOption(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String clinetIdStr=request.getParameter("clientId");
		UserProductRequestModel uprModel=new UserProductRequestModel();
		uprModel.setClientId(clinetIdStr);
		List<UserProduct> userProductList=facade.findRenewalOptionByClientId(uprModel).getUserProductList();
		if(userProductList!=null&&userProductList.size()>0){
			request.setAttribute("userProductList", userProductList);
		}
			return mapping.findForward("success");
		}
	
	
	
	
	public ActionForward updateRenewalOption(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		AclUserProfile user = (AclUserProfile) request.getSession().getAttribute(Consts.AdminPortal.userContext.USERCONTEXT);
		String clinetIdStr=request.getParameter("clientId");
		String productIdStr=request.getParameter("productId");
		UpdateUserProductRequestModel model=new UpdateUserProductRequestModel();
		List<UserProductRequest> userProductRequestList=new ArrayList<UserProductRequest>();
		UserProductRequest up=new UserProductRequest();
		UsrProdId id=new UsrProdId();
		id.setClntId(clinetIdStr);
		id.setProdId(productIdStr);
		up.setId(id);
		up.setUpdBy(user.getLognId());
		userProductRequestList.add(up);
		model.setUserProductRequestList(userProductRequestList);
		String returnCode="";
		returnCode=facade.updateRenewalOption(model).getReturnCode();
		if (returnCode.equals(Consts.AdminPortal.returnResulst.SUCCESS)) {
			return mapping.findForward("success");
		} else {
			return mapping.findForward("failure");
		}
	}
	
	public ActionForward listSelectService(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String clinetIdStr=request.getParameter("clientIdss");
		ListSelectServiceRequestModel reqModel=new ListSelectServiceRequestModel();
		reqModel.setClientId(clinetIdStr);
		ListSelectServiceResponseModel respModel=facade.listSelectService(reqModel);
		List<UserProduct> existUserProductList=respModel.getExistingServiceProductList();
		List<RtqAccountAssignment> rtqAccAssList=respModel.getRtqAccountAssignmentList();
		List<UserProductAllotment> userProductAllotList=respModel.getSubscriberServiceProductList();
		String returnCode="";
		returnCode=respModel.getReturnCode();
		if (returnCode.equals(Consts.AdminPortal.returnResulst.SUCCESS)) {
			request.setAttribute("existUserProductList", existUserProductList);
			request.setAttribute("rtqAccAssList",rtqAccAssList );
			request.setAttribute("userProductAllotList", userProductAllotList);
			request.setAttribute("clientId", clinetIdStr);
			return mapping.findForward("success");
		} else {
			return mapping.findForward("failure");
		}
	}

	
}
