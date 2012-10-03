package com.itsz.sht.struts.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


import com.itsz.sht.common.CLVSplitUtil;
import com.itsz.sht.common.SessionUtil;
import com.itsz.sht.common.beanutil.BeanCopyUtils;
import com.itsz.sht.common.model.common.request.UpdateUserProductRequestModel;
import com.itsz.sht.common.model.common.response.UserProductResponseModel;
import com.itsz.sht.common.model.request.VerifyPasswordRequestModel;
import com.itsz.sht.common.model.response.VerifyPasswordResponseModel;
import com.itsz.sht.dao.hibernate.model.UsrProdId;
import com.itsz.sht.model.UserProductRequest;
import com.itsz.sht.struts.form.CancelSHKFrom;
import com.itsz.sht.struts.form.ITSZForm;
import com.itsz.sht.vp.ViewProvider;
import com.itsz.sht.vp.common.ProcessBean;

public class CancelSHKAction extends ITSZAction {
	@Override
	public ActionForward executeAction(ViewProvider vp, ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		CancelSHKFrom CancelFrom=(CancelSHKFrom)form;
		VerifyPasswordRequestModel pwdRequest = new VerifyPasswordRequestModel();
		form2Model(request, CancelFrom, pwdRequest, response);
		VerifyPasswordResponseModel pwdResponse = facade.verifyPassword(pwdRequest);
		String str=pwdResponse.getPasswordMatch();
		if(str=="Y"||"Y".equals(str)){
			UpdateUserProductRequestModel updateUserProductRequestModel=new UpdateUserProductRequestModel();
			ArrayList<UserProductRequest> userProductRequestList=new ArrayList<UserProductRequest>();
			UserProductRequest userProductRequest=new UserProductRequest();
			UsrProdId usrProdId =new UsrProdId();
			usrProdId.setClntId(CancelFrom.getLoginId());
			usrProdId.setProdId("SHK");
			userProductRequest.setId(usrProdId);
			userProductRequest.setAllwRenl("N");
			userProductRequestList.add(userProductRequest);
			updateUserProductRequestModel.setUserProductRequestList(userProductRequestList);
			form2Model2(request, CancelFrom, updateUserProductRequestModel, response);
			UserProductResponseModel userProductResponseModel =facade.updateUserProductStatus(updateUserProductRequestModel);
			ProcessBean processBean = new ProcessBean(userProductResponseModel, null, mapping, request, response);
			return vp.processCancelSHK(processBean);
		}else{
			
			request.setAttribute("PasswordStatus", "fail");
			return mapping.findForward("fail");
		}
	}
	
	public static void form2Model(HttpServletRequest request,
			ITSZForm origForm, VerifyPasswordRequestModel destModel, HttpServletResponse response) {
		try {
			BeanCopyUtils.copyProperties(destModel, origForm);
			if(StringUtils.isBlank(origForm.getCLV())) origForm.setCLV(CLVSplitUtil.transNullCLV(request,response,origForm,""));
		} catch (Exception e) {
//			e.printStackTrace();
		}finally{
			destModel.setChannelType(CLVSplitUtil.getChannelType(origForm.getCLV()));
			destModel.setLanguage(CLVSplitUtil.getLanguage(origForm.getCLV()));
			destModel.setVersionId(String.valueOf(CLVSplitUtil.getVersionID(origForm.getCLV())));
			destModel.setChannelId(CLVSplitUtil.getChannelId(origForm.getCLV()));
		}
	}
	
	public static void form2Model2(HttpServletRequest request,
			ITSZForm origForm, UpdateUserProductRequestModel destModel, HttpServletResponse response) {
		try {
			BeanCopyUtils.copyProperties(destModel, origForm);
			if(StringUtils.isBlank(origForm.getCLV())) origForm.setCLV(CLVSplitUtil.transNullCLV(request,response,origForm,""));
		} catch (Exception e) {
//			e.printStackTrace();
		}finally{
			destModel.setChannelType(CLVSplitUtil.getChannelType(origForm.getCLV()));
			destModel.setLanguage(CLVSplitUtil.getLanguage(origForm.getCLV()));
			destModel.setVersionId(String.valueOf(CLVSplitUtil.getVersionID(origForm.getCLV())));
			destModel.setChannelId(SessionUtil.getChannelID(request));
		}
	}

}
