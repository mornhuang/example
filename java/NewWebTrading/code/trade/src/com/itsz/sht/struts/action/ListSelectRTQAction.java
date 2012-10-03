package com.itsz.sht.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.itsz.sht.common.CLVSplitUtil;
import com.itsz.sht.common.Constants;
import com.itsz.sht.common.SessionUtil;
import com.itsz.sht.common.WEB_Constants;
import com.itsz.sht.common.beanutil.BeanCopyUtils;
import com.itsz.sht.common.model.common.AbstractRequestModel;
import com.itsz.sht.common.model.common.LoginUserInfo;
import com.itsz.sht.common.model.common.request.ListSelectServiceRequestModel;
import com.itsz.sht.common.model.common.response.ListSelectServiceResponseModel;
import com.itsz.sht.common.user.UserObject;
import com.itsz.sht.struts.form.ITSZForm;
import com.itsz.sht.struts.form.ListSelectRTQForm;
import com.itsz.sht.vp.ViewProvider;
import com.itsz.sht.vp.common.ProcessBean;

/**
 * $Id: ListSelectRTQAction.java,v 1.4 2011/01/05 05:16:17 kyzou Exp $
 * @Project:NewWebTrading
 * @File:ListSelectRTQAction.java
 * @Description:
 * @Author:kyzou
 * @Date:2010-12-17
 */
public class ListSelectRTQAction extends ITSZAction {

	@Override
	public ActionForward executeAction(ViewProvider vp, ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		ListSelectServiceRequestModel listSelectRTQModel = new ListSelectServiceRequestModel();
		form2Model(request, (ListSelectRTQForm) form, listSelectRTQModel, response);
		UserObject user = (UserObject) request.getSession().getAttribute(Constants.USER);
		listSelectRTQModel.setClientId(user.getTradeInfoObject().getCustCode());
		LoginUserInfo loginUserInfo = (LoginUserInfo) request.getSession().getAttribute(WEB_Constants.LOGIN_USER_INFO);
		String transactionProtection =  loginUserInfo.getTransactionProtection();
		request.setAttribute("transactionProtection", transactionProtection);
		ListSelectServiceResponseModel listSelectRTQResp = facade.listSelectService(listSelectRTQModel);
		ProcessBean processBean = new ProcessBean(listSelectRTQResp, null, mapping, request, response);
		return vp.processListSelectRTQ(processBean);
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
			destModel.setChannelId(SessionUtil.getChannelID(request));
		}
	}
}
