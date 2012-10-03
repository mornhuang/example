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
import com.itsz.sht.common.model.common.response.AccessSHKResponseModel;
import com.itsz.sht.common.user.UserObject;
import com.itsz.sht.struts.form.AccessSHKFrom;
import com.itsz.sht.struts.form.ITSZForm;
import com.itsz.sht.vp.ViewProvider;
import com.itsz.sht.vp.common.ProcessBean;



public class EnterShkAction extends ITSZAction{
	public ActionForward executeAction(ViewProvider vp,
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response)  {
		HttpSession session=request.getSession();
		AccessSHKFrom SHKFrom=(AccessSHKFrom)form;
		AccessSHKRequestModel accessSHKModel=new AccessSHKRequestModel();
		form2Model(request, SHKFrom, accessSHKModel, response,session);
		AccessSHKResponseModel accessSHKResponseModel=facade.accessSHK(accessSHKModel);
		accessSHKResponseModel.setLanguage(accessSHKModel.getLanguage());
		ProcessBean processBean = new ProcessBean(accessSHKResponseModel, null, mapping, request, response);
		return vp.processAccessEnterSHK(processBean);
	}
	
	public static void form2Model(HttpServletRequest request,
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
