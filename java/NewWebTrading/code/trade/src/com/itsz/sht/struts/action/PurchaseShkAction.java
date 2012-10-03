package com.itsz.sht.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.itsz.sht.common.CLVSplitUtil;
import com.itsz.sht.common.SessionUtil;
import com.itsz.sht.common.beanutil.BeanCopyUtils;
import com.itsz.sht.common.model.common.request.PurchaseServiceRequestModel;
import com.itsz.sht.common.model.common.response.PurchaseServiceResponseModel;
import com.itsz.sht.common.model.request.VerifyPasswordRequestModel;
import com.itsz.sht.common.model.response.VerifyPasswordResponseModel;
import com.itsz.sht.common.user.UserObject;


import com.itsz.sht.struts.form.AccessSHKFrom;
import com.itsz.sht.struts.form.ITSZForm;
import com.itsz.sht.vp.ViewProvider;
import com.itsz.sht.vp.common.ProcessBean;

/**
 * $Id: PurchaseShkAction.java,v 1.7 2011/03/16 04:28:16 bwu Exp $
 * @Project:NewWebTrading
 * @File:PurchaseShkAction.java
 * @Description:
 * @Author:kyzou
 * @Date:2010-12-16
 */
public class PurchaseShkAction extends ITSZAction {

	@Override
	public ActionForward executeAction(ViewProvider vp, ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		HttpSession session=request.getSession();
		AccessSHKFrom SHKFrom=(AccessSHKFrom)form;
		VerifyPasswordRequestModel pwdRequest=new VerifyPasswordRequestModel();
		form2Model(request, SHKFrom, pwdRequest, response);
		pwdRequest.setLoginId(((UserObject)request.getSession().getAttribute("User")).getLoginName());
		VerifyPasswordResponseModel pwdResponse =facade.verifyPassword(pwdRequest);
		
		String str=pwdResponse.getPasswordMatch();
		if(str=="Y"||"Y".equals(str)){
			PurchaseServiceRequestModel purchaseServiceRequestModel=new PurchaseServiceRequestModel();
			SHKFrom.setProductId("SHK");
			purchaseServiceRequestModel.setDefDebtAcc(((UserObject)session.getAttribute("User")).getTradeInfoObject().getTradingAccount());
			form2Model2(request, SHKFrom, purchaseServiceRequestModel, response);
			PurchaseServiceResponseModel purchaseServiceResponseModel =facade.purchaseService(purchaseServiceRequestModel);
			ProcessBean processBean = new ProcessBean(purchaseServiceResponseModel, null, mapping, request, response);
			return vp.processPurchaseAccessSHK(processBean);
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
			destModel.setChannelId(SessionUtil.getChannelID(request));
		}
	}
	
	public static void form2Model2(HttpServletRequest request,
			ITSZForm origForm, PurchaseServiceRequestModel destModel, HttpServletResponse response) {
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
			destModel.setClientId(((AccessSHKFrom)origForm).getLoginId());
			destModel.setAllowRenewal("Y");
		}
	}

}
