package com.itsz.sht.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.itsz.sht.common.CLVSplitUtil;
import com.itsz.sht.common.SessionUtil;
import com.itsz.sht.common.beanutil.BeanCopyUtils;
import com.itsz.sht.common.model.common.AbstractRequestModel;
import com.itsz.sht.common.model.common.request.ReserveServiceRequestModel;
import com.itsz.sht.common.model.common.response.ReserveServiceResponseModel;
import com.itsz.sht.struts.form.ITSZForm;
import com.itsz.sht.struts.form.ReserveRTQForm;
import com.itsz.sht.vp.ViewProvider;
import com.itsz.sht.vp.common.ProcessBean;

/**
 * $Id: ReserveRTQAction.java,v 1.4 2011/01/19 02:44:04 kyzou Exp $
 * @Project:NewWebTrading
 * @File:ReserveRTQAction.java
 * @Description:
 * @Author:kyzou
 * @Date:2010-12-16
 */
public class ReserveRTQAction extends ITSZAction {

	@Override
	public ActionForward executeAction(ViewProvider vp, ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		ReserveServiceRequestModel reserveRTQModel = new ReserveServiceRequestModel();
		form2Model(request, (ReserveRTQForm) form, reserveRTQModel, response);
		ReserveServiceResponseModel purchaseRTQResp = facade.reserveService(reserveRTQModel);
		ProcessBean processBean = new ProcessBean(purchaseRTQResp, null, mapping, request, response);
		return vp.processReserveRTQ(processBean);
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
