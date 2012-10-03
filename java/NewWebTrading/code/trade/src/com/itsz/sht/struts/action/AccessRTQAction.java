package com.itsz.sht.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.itsz.sht.common.CLVSplitUtil;
import com.itsz.sht.common.Constants;
import com.itsz.sht.common.beanutil.BeanCopyUtils;
import com.itsz.sht.common.model.common.AbstractRequestModel;
import com.itsz.sht.common.model.common.request.AccessRTQRequestModel;
import com.itsz.sht.common.model.common.response.AccessRTQResponseModel;
import com.itsz.sht.common.user.UserObject;
import com.itsz.sht.struts.form.ITSZForm;
import com.itsz.sht.vp.ViewProvider;
import com.itsz.sht.vp.common.ProcessBean;

/**
 * $Id: AccessRTQAction.java,v 1.7 2011/04/06 08:35:08 pbxie Exp $
 * @Project:NewWebTrading
 * @File:AccessRTQAction.java
 * @Description:
 * @Author:kyzou
 * @Date:2010-12-16
 */
public class AccessRTQAction extends ITSZAction {

	@Override
	public ActionForward executeAction(ViewProvider vp, ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		AccessRTQRequestModel accessRTQModel = new AccessRTQRequestModel();
		form2Model(request, (ITSZForm) form, accessRTQModel, response);
		request.getSession().setAttribute("CLV", ((ITSZForm) form).getCLV());
		UserObject user = (UserObject) request.getSession().getAttribute(Constants.USER);
		accessRTQModel.setClientId(user.getTradeInfoObject().getCustCode());
		accessRTQModel.setClientIp(request.getRemoteAddr());
		AccessRTQResponseModel accessRTQResp = facade.accessRTQ(accessRTQModel);
//		String rtqProvider = accessRTQResp.getRtqAccess().getRtqProvider();
		ProcessBean processBean = new ProcessBean(accessRTQResp, null, mapping, request, response);
		return vp.processAccessRTQ(processBean);
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
