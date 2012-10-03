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
import com.itsz.sht.common.beanutil.BeanCopyUtils;
import com.itsz.sht.common.model.common.AbstractRequestModel;
import com.itsz.sht.common.model.common.request.ListSelectServiceRequestModel;
import com.itsz.sht.common.model.common.response.ListSelectServiceResponseModel;
import com.itsz.sht.common.user.UserObject;
import com.itsz.sht.struts.form.ITSZForm;
import com.itsz.sht.struts.form.ListSelectRTQForm;
import com.itsz.sht.vp.ViewProvider;
import com.itsz.sht.vp.common.ProcessBean;

/**
 * $Id: SelectRTQStatusAction.java,v 1.2 2011/01/19 02:44:04 kyzou Exp $
 * @Project:NewWebTrading
 * @File:SelectRTQStatusAction.java
 * @Description:
 * @Author:kyzou
 * @Date:2010-12-31
 */
public class SelectRTQStatusAction extends ITSZAction {

	@Override
	public ActionForward executeAction(ViewProvider vp, ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		ListSelectServiceRequestModel listSelectRTQModel = new ListSelectServiceRequestModel();
		ListSelectRTQForm initForm = new ListSelectRTQForm();
		String CLV = (String)request.getSession().getAttribute("CLV");
		initForm.setCLV(StringUtils.isNotBlank(CLV)?CLV:"WT25");
		form2Model(request, initForm, listSelectRTQModel, response);
		UserObject user = (UserObject) request.getSession().getAttribute(Constants.USER);
		listSelectRTQModel.setClientId(user.getTradeInfoObject().getCustCode());
		ListSelectServiceResponseModel listSelectRTQResp = facade.listSelectService(listSelectRTQModel);
		ProcessBean processBean = new ProcessBean(listSelectRTQResp, null, mapping, request, response);
		return vp.processSelectRTQStatus(processBean);
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
