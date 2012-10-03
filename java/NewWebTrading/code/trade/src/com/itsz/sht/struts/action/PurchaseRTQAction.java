package com.itsz.sht.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.itsz.sht.common.CLVSplitUtil;
import com.itsz.sht.common.Constants;
import com.itsz.sht.common.Consts;
import com.itsz.sht.common.DataModelUtil;
import com.itsz.sht.common.WEB_Constants;
import com.itsz.sht.common.beanutil.BeanCopyUtils;
import com.itsz.sht.common.model.common.AbstractRequestModel;
import com.itsz.sht.common.model.common.LoginUserInfo;
import com.itsz.sht.common.model.common.request.PurchaseServiceRequestModel;
import com.itsz.sht.common.model.common.request.ReserveServiceRequestModel;
import com.itsz.sht.common.model.common.response.PurchaseServiceResponseModel;
import com.itsz.sht.common.model.common.response.ReserveServiceResponseModel;
import com.itsz.sht.common.model.request.VerifyPasswordRequestModel;
import com.itsz.sht.common.model.response.VerifyPasswordResponseModel;
import com.itsz.sht.common.user.UserObject;
import com.itsz.sht.struts.form.ITSZForm;
import com.itsz.sht.struts.form.PurchaseRTQForm;
import com.itsz.sht.vp.ViewProvider;
import com.itsz.sht.vp.common.ProcessBean;

/**
 * $Id: PurchaseRTQAction.java,v 1.11 2011/02/17 09:07:49 kyzou Exp $
 * @Project:NewWebTrading
 * @File:PurchaseRTQAction.java
 * @Description:
 * @Author:kyzou
 * @Date:2010-12-16
 */
public class PurchaseRTQAction extends ITSZAction {

	@Override
	public ActionForward executeAction(ViewProvider vp, ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		PurchaseServiceRequestModel purchaseRTQModel = new PurchaseServiceRequestModel();
		PurchaseServiceResponseModel purchaseRTQResp = new PurchaseServiceResponseModel();
		ReserveServiceRequestModel reserveRTQModel = new ReserveServiceRequestModel();
		ReserveServiceResponseModel reserveRTQResp = new ReserveServiceResponseModel();
		UserObject user = (UserObject) request.getSession().getAttribute(Constants.USER);
		PurchaseRTQForm initForm = (PurchaseRTQForm) form;
		
        VerifyPasswordRequestModel verifyPassReq = new VerifyPasswordRequestModel();
        DataModelUtil.form2Model(request, initForm, verifyPassReq, response);
        verifyPassReq.setLoginId(user.getLoginName());
        verifyPassReq.setPassword(initForm.getPassword());
        boolean canApply = true;
		VerifyPasswordResponseModel verifyPassResp = facade.verifyPassword(verifyPassReq);
		if(Consts.Global.Status.NORMAL.equalsIgnoreCase(verifyPassResp.getReturnCode()) || 
				   Consts.Global.Status.SUCCESS.equalsIgnoreCase(verifyPassResp.getReturnCode())){
			canApply = verifyPassResp.getPasswordMatch().equals(Consts.Global.Flag.POSITIVE)?true:false;;
		}else{
			canApply = false;
		}
		if("This".equalsIgnoreCase(initForm.getSelectMonth())){
			purchaseRTQModel.setAllowRenewal("on".equalsIgnoreCase(initForm.getAllowRenewal())?Consts.Global.Flag.POSITIVE:Consts.Global.Flag.NEGATIVE);
			request.setAttribute("productId", initForm.getProductId());
			if(!canApply){
				purchaseRTQResp.setReturnCode(Consts.Error.Code.TRAD_ERROR_WRONGPASS);
				ProcessBean processBean = new ProcessBean(purchaseRTQResp, null, mapping, request, response);
				return vp.processPurchaseRTQ(processBean);
			}
			form2Model(request, initForm, purchaseRTQModel, response);
			if (user != null) {
				purchaseRTQModel.setClientId(user.getTradeInfoObject().getCustCode());
				purchaseRTQModel.setDefDebtAcc(user.getTradeInfoObject().getTradingAccount());
			}
			if("On".equalsIgnoreCase(purchaseRTQModel.getAllowRenewal())){
				purchaseRTQModel.setAllowRenewal(Consts.Global.Flag.POSITIVE);
			}else{
				purchaseRTQModel.setAllowRenewal(Consts.Global.Flag.NEGATIVE);
			}
			purchaseRTQResp = facade.purchaseService(purchaseRTQModel);
			ProcessBean processBean = new ProcessBean(purchaseRTQResp, null, mapping, request, response);
			return vp.processPurchaseRTQ(processBean);
		}else{
			reserveRTQModel.setAllowRenewal("on".equalsIgnoreCase(initForm.getAllowRenewal())?Consts.Global.Flag.POSITIVE:Consts.Global.Flag.NEGATIVE);
			request.setAttribute("productId", initForm.getProductId());
			if(!canApply){
				reserveRTQResp.setReturnCode(Consts.Error.Code.TRAD_ERROR_WRONGPASS);
				ProcessBean processBean = new ProcessBean(reserveRTQResp, null, mapping, request, response);
				return vp.processReserveRTQ(processBean);
			}
			form2Model(request, initForm, reserveRTQModel, response);
			if (user != null) {
				reserveRTQModel.setClientId(user.getTradeInfoObject().getCustCode());
				reserveRTQModel.setDefDebtAcc(user.getTradeInfoObject().getTradingAccount());
			}
			if("On".equalsIgnoreCase(reserveRTQModel.getAllowRenewal())){
				reserveRTQModel.setAllowRenewal(Consts.Global.Flag.POSITIVE);
			}else{
				reserveRTQModel.setAllowRenewal(Consts.Global.Flag.NEGATIVE);
			}
			reserveRTQResp = facade.reserveService(reserveRTQModel);
			ProcessBean processBean = new ProcessBean(reserveRTQResp, null, mapping, request, response);
			return vp.processReserveRTQ(processBean);
		}
	}
	
	public static void form2Model(HttpServletRequest request,
			ITSZForm origForm, AbstractRequestModel destModel, HttpServletResponse response) {
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

}
