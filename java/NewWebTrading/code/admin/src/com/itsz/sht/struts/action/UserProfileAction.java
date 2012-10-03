package com.itsz.sht.struts.action;



import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


import com.itsz.sht.common.Consts;
import com.itsz.sht.common.model.common.request.ListSelectServiceRequestModel;
import com.itsz.sht.common.model.common.request.ProductRequestModel;
import com.itsz.sht.common.model.common.request.ServicesAccessLogByRtqAccAsgnIdRequestModel;
import com.itsz.sht.common.model.common.request.UserProfileRequestModel;
import com.itsz.sht.common.model.common.response.ListSelectServiceResponseModel;
import com.itsz.sht.common.model.common.response.ProductResponseModel;
import com.itsz.sht.common.model.common.response.ServicesAccessLogByRtqAccAsgnIdResponseModel;
import com.itsz.sht.common.model.common.response.UserProfileResponseModel;
import com.itsz.sht.dao.hibernate.model.AclUserProfile;
import com.itsz.sht.dao.hibernate.model.RtqAccAsgnId;
import com.itsz.sht.dao.hibernate.model.RtqAccountAssignment;
import com.itsz.sht.dao.hibernate.model.UserProduct;
import com.itsz.sht.dao.hibernate.model.UserProductAllotment;
import com.itsz.sht.model.RtqAccountAssDto;
import com.itsz.sht.model.UserProductAllotmentDto;
import com.itsz.sht.struts.form.UserProfileForm;
import com.taifook.adminportal.common.Constants;

public class UserProfileAction extends ITSZAction{

	

	public ActionForward addUserProfile(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//	UserProfileForm upForm = (UserProfileForm) form;
	//	UserProfileRequestModel reqModel = this.form2Model(upForm);
		  String returnCode="";
			if (returnCode.equals(Consts.AdminPortal.returnResulst.SUCCESS)) {
				return mapping.findForward("success");
			} else {
				return mapping.findForward("failure");
			}
	}

	public ActionForward showUserProfile(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String clientId=request.getParameter("clientId");
		UserProfileRequestModel urm=new UserProfileRequestModel();
		urm.setClientId(clientId);
		UserProfileResponseModel resModel = facade.findAdminUserProfile(urm);
		String retrunCode=resModel.getReturnCode();
		if (retrunCode!=null&&retrunCode.equals("SUCCESS")) {
			UserProfileForm upForm = new UserProfileForm();
			upForm = this.model2Form(resModel);
			request.setAttribute("userProfileForm", upForm);
			return mapping.findForward("success");
		}else{
			return mapping.findForward("failure");
		}
		
	}

	public ActionForward goUpdateUserProfile(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String clientId=request.getParameter("clientId");
		UserProfileRequestModel urm=new UserProfileRequestModel();
		urm.setClientId(clientId);
		UserProfileResponseModel resModel = facade.findAdminUserProfile(urm);
		String retrunCode=resModel.getReturnCode();
		if (retrunCode!=null&&retrunCode.equals("SUCCESS")) {
			UserProfileForm upForm = new UserProfileForm();
			upForm = this.model2Form(resModel);
			request.setAttribute("userProfileForm", upForm);
			return mapping.findForward("success");
		}else{
			return mapping.findForward("failure");
		}
		
	}

	public ActionForward updateUserProfile(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		//	UserProfileForm upForm = (UserProfileForm) form;
	//	UserProfileRequestModel reqModel = this.form2Model(upForm);
		String retrunCode="";
		if (retrunCode!=null&&retrunCode.equals("SUCCESS")) {
			return mapping.findForward("success");
		}else{
			return mapping.findForward("failure");
		}
	}

	public ActionForward deleteUserProfile(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		//	UserProfileForm upForm = (UserProfileForm) form;

		String retrunCode="";
		if (retrunCode!=null&&retrunCode.equals("SUCCESS")) {
			return mapping.findForward("success");
		}else{
			return mapping.findForward("failure");
		}
	}

	public ActionForward listUserProfile(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		UserProfileForm upForm = (UserProfileForm) form;
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
		request.setAttribute("userProfileForm", upForm);
		
		UserProfileRequestModel rModel=new UserProfileRequestModel();
		rModel.setClientId(clientIdStr);
		rModel.setClntLoginId(clientLoginIdStr);
		UserProfileResponseModel userProfile = facade.findAdminUserProfile(rModel);
		clientIdStr = userProfile.getClientId();
		clientLoginIdStr = userProfile.getClientLoginId();
		
		if(clientIdStr!=null && !clientIdStr.equals("")){
			ListSelectServiceRequestModel reqModel=new ListSelectServiceRequestModel();
			reqModel.setClientId(clientIdStr);
			ListSelectServiceResponseModel respModel=facade.listSelectService(reqModel);
			List<UserProduct> existUserProductList=respModel.getExistingServiceProductList();
			List<RtqAccountAssignment> rtqAccAssList=respModel.getRtqAccountAssignmentList();
			List<UserProductAllotment> userProductAllotList=respModel.getSubscriberServiceProductList();
			request.setAttribute("clientId", clientIdStr);
			request.setAttribute("clientLoginId", clientLoginIdStr);
			String returnCode1="";
			String returnCode2="";
			returnCode1=respModel.getReturnCode();
			if (returnCode1.equals(Consts.AdminPortal.returnResulst.SUCCESS)) {
				request.setAttribute("existUserProductList", existUserProductList);
				
				List<UserProductAllotmentDto> userProductAllotmentDtoList = new ArrayList<UserProductAllotmentDto>();
				ProductRequestModel productRequestModel = new ProductRequestModel();
				ProductResponseModel productResponseModel = new ProductResponseModel();
				for(UserProductAllotment upa : userProductAllotList){
					UserProductAllotmentDto upaDto = new UserProductAllotmentDto();
					upaDto.setClntId(upa.getClntId());
					upaDto.setAlltStatus(upa.getAlltStatus());
					upaDto.setAlltTime(upa.getAlltTime());
					upaDto.setEffDate(upa.getEffDate());
					upaDto.setExpDate(upa.getExpDate());
					upaDto.setProdId(upa.getProdId());
					upaDto.setUsrProdAlltId(upa.getUsrProdAlltId());
					productRequestModel.setProductId(upa.getProdId());
					productResponseModel = facade.getProduct(productRequestModel);
					if(productResponseModel.getProduct() != null){
						upaDto.setPriceInHkd(productResponseModel.getProduct().getPriceInHkd());
					}
					userProductAllotmentDtoList.add(upaDto);
				}
				request.setAttribute("userProductAllotList", userProductAllotmentDtoList);
				
				List<RtqAccountAssDto> rtqAccAssDtoList = new ArrayList<RtqAccountAssDto>();
				ServicesAccessLogByRtqAccAsgnIdRequestModel reModel = new ServicesAccessLogByRtqAccAsgnIdRequestModel();
				RtqAccAsgnId id = new RtqAccAsgnId();
				id.setClntId(clientIdStr);
				ServicesAccessLogByRtqAccAsgnIdResponseModel model = new ServicesAccessLogByRtqAccAsgnIdResponseModel();
				for(RtqAccountAssignment rtqAccAss : rtqAccAssList){
					RtqAccountAssDto rtqAccountAssDto = new RtqAccountAssDto();
					rtqAccountAssDto.setClientId(rtqAccAss.getId().getClntId());
					rtqAccountAssDto.setProductId(rtqAccAss.getId().getProdId());
					rtqAccountAssDto.setRtqLoginId(rtqAccAss.getId().getRtqLognId());
					rtqAccountAssDto.setRtqLoginPwd(rtqAccAss.getRtqLognPwd());
					id.setProdId(rtqAccAss.getId().getProdId());
					id.setRtqLognId(rtqAccAss.getId().getRtqLognId());
					reModel.setId(id);
					model = facade.getServicesAccessLogByRtqAccAsgnId(reModel);
					if(model.getServicesAccessLog() != null){
						rtqAccountAssDto.setLastAccessTime(model.getServicesAccessLog().getActnDate());
					}
					rtqAccAssDtoList.add(rtqAccountAssDto);
				}
				request.setAttribute("rtqAccAssList",rtqAccAssDtoList);
			}
			returnCode2=userProfile.getReturnCode();
			if (returnCode2.equals(Consts.AdminPortal.returnResulst.SUCCESS)) {
				request.setAttribute("userProfile", userProfile);
				return mapping.findForward("success");
			}else{
				return mapping.findForward("failure");
			}
		}
		return mapping.findForward("failure");
	}	
	
	
	
	// 页面上的数据formbean转换为requestModel
//	private UserProfileRequestModel form2Model(UserProfileForm upForm) {
//		UserProfileRequestModel reqModel = new UserProfileRequestModel();
//		reqModel.setChinaDiscountFlag(upForm.getCnDiscFlag());
//		reqModel.setClientId(upForm.getClntId());
//		reqModel.setDefaultDebitAccount(upForm.getDefDebtAcc());
//		return reqModel;
//	}

	// 返回的responseModel对象转换为formbean页面上显示
	private UserProfileForm model2Form(
			UserProfileResponseModel userProfileResModel) {
		UserProfileForm upForm = new UserProfileForm();
		upForm.setClntId(userProfileResModel.getClientId());
		upForm.setClntLoginId(userProfileResModel.getClientLoginId());
		upForm.setCnDiscFlag(userProfileResModel.getChinaDiscountFlag());
		upForm.setDefDebtAcc(userProfileResModel.getDefaultDebitAccount());
		return upForm;
	}

	@Override
	public ActionForward executeAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		return null;
	}
}
