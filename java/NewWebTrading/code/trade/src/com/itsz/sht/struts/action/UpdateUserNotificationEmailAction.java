package com.itsz.sht.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.itsz.sht.common.CLVSplitUtil;
import com.itsz.sht.common.Constants;
import com.itsz.sht.common.DataModelUtil;
import com.itsz.sht.common.SessionUtil;
import com.itsz.sht.common.beanutil.BeanCopyUtils;
import com.itsz.sht.common.model.common.AbstractRequestModel;
import com.itsz.sht.common.model.common.request.UserNotificationEmailRequestModel;
import com.itsz.sht.common.model.common.response.UserNotificationEmailResponseModel;
import com.itsz.sht.common.model.response.InsertIPOResponseModel;
import com.itsz.sht.common.user.UserObject;
import com.itsz.sht.struts.form.ITSZForm;
import com.itsz.sht.struts.form.UserNotificationEmailForm;
import com.itsz.sht.struts.form.ipo.IPORequestAddForm;
import com.itsz.sht.vp.ViewProvider;
import com.itsz.sht.vp.common.ProcessBean;

public class UpdateUserNotificationEmailAction extends ITSZAction {

	@Override
	public ActionForward executeAction(ViewProvider vp, ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		UserNotificationEmailRequestModel model = new UserNotificationEmailRequestModel();
		form2Model(request,(UserNotificationEmailForm) form, model, response);
		UserObject user = (UserObject) request.getSession().getAttribute(Constants.USER);
		if (user != null && user.getTradeInfoObject() != null){ 
		    model.setClientId(user.getTradeInfoObject().getCustCode());
		}
		facade.updateUserNotificationEmail(model);
		UserNotificationEmailResponseModel responseModel = facade.getUserNotificationEmail(model); 
		ProcessBean processBean = new ProcessBean(responseModel,null, mapping, request,response);
		return vp.processUpdateUserNotificationEmail(processBean);
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
