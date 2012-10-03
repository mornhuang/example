package com.itsz.sht.struts.eipo.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.itsz.eipo.webservice.EIPOResponse;
import com.itsz.sht.common.Constants;
import com.itsz.sht.common.Consts;
import com.itsz.sht.common.DataModelUtil;
import com.itsz.sht.common.PasswordUtil;
import com.itsz.sht.common.model.request.CancelEIPOSubscriptionSubmitRequestModel;
import com.itsz.sht.common.model.request.EIPOSubscriptionEnquiryRequestModel;
import com.itsz.sht.common.user.UserObject;
import com.itsz.sht.struts.action.ITSZAction;
import com.itsz.sht.struts.eipo.form.CancelEIPOSubscriptionSubmitForm;
import com.itsz.sht.struts.eipo.form.EIPOSubscriptionEnquiryForm;
import com.itsz.sht.vp.ViewProvider;
import com.itsz.sht.vp.common.ProcessBean;

public class CancelEIPOSubscriptionSubmitAction extends ITSZAction{
	
	public ActionForward executeAction(ViewProvider vp, ActionMapping mapping,
            ActionForm form, HttpServletRequest request,
            HttpServletResponse response) {

		CancelEIPOSubscriptionSubmitRequestModel model = new CancelEIPOSubscriptionSubmitRequestModel();
		DataModelUtil.form2Model(request,(CancelEIPOSubscriptionSubmitForm) form, model, response);
		UserObject user = (UserObject) request.getSession().getAttribute(Constants.USER);
		model.setLoginId(user.getLoginName());
		request.setAttribute("subId", model.getSubscriptionId());
		EIPOResponse responseModel = facade.getCancelEIPOSubscriptionSubmit(model);
		ProcessBean processBean = new ProcessBean(responseModel,null, mapping, request,response);
		return vp.processCancelEIPOSubscriptionSubmit(processBean);
	}
}
