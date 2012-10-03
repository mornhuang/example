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
import com.itsz.sht.common.model.common.request.AccessSHKRequestModel;
import com.itsz.sht.common.model.common.request.PurchasedProductRequestModel;
import com.itsz.sht.common.model.common.response.AccessSHKResponseModel;
import com.itsz.sht.common.model.common.response.PurchasedProductResponseModel;
import com.itsz.sht.common.user.UserObject;
import com.itsz.sht.dao.hibernate.model.Product;
import com.itsz.sht.struts.form.AccessSHKFrom;
import com.itsz.sht.struts.form.ITSZForm;
import com.itsz.sht.vp.ViewProvider;
import com.itsz.sht.vp.common.ProcessBean;



public class AccessShk2Action extends ITSZAction{
	public ActionForward executeAction(ViewProvider vp,
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response)  {
		AccessSHKFrom SHKform=(AccessSHKFrom) form;
		HttpSession session=request.getSession();
		SHKform.setLoginId(((UserObject)session.getAttribute("User")).getTradeInfoObject().getCustCode());
		SHKform.setProductId("SHK");
		PurchasedProductRequestModel accessSHKModel = new PurchasedProductRequestModel();
		form2Model(request, SHKform, accessSHKModel, response,session);
		PurchasedProductResponseModel accessSHKResp = facade.havePurchasedProduct(accessSHKModel);
		if(accessSHKResp.isHavePurchased()){
			AccessSHKFrom SHKFrom=(AccessSHKFrom)form;
			AccessSHKRequestModel accessSHKModel1=new AccessSHKRequestModel();
			form2Model2(request, SHKFrom, accessSHKModel1, response,session);
			AccessSHKResponseModel accessSHKResponseModel=facade.accessSHK(accessSHKModel1);
			accessSHKResponseModel.setLanguage(accessSHKModel1.getLanguage());
			ProcessBean processBean = new ProcessBean(accessSHKResponseModel, null, mapping, request, response);
			return vp.processAccessSHK2(processBean);
		}else{
			Product p=(Product)accessSHKResp.getProduct();
			request.setAttribute("PriceInHkd", p.getPriceInHkd());
			return mapping.findForward("fail");
		}
	}
	
	public static void form2Model(HttpServletRequest request,
			ITSZForm origForm, PurchasedProductRequestModel destModel, HttpServletResponse response,HttpSession session) {
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
			destModel.setClientId(((UserObject)session.getAttribute("User")).getTradeInfoObject().getCustCode());
		}
	}
	
	public static void form2Model2(HttpServletRequest request,
			ITSZForm origForm, AccessSHKRequestModel destModel, HttpServletResponse response,HttpSession session) {
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
			destModel.setClientId(((UserObject)session.getAttribute("User")).getTradeInfoObject().getCustCode());
			destModel.setClientIp(request.getRemoteAddr());
		}
	}
}
