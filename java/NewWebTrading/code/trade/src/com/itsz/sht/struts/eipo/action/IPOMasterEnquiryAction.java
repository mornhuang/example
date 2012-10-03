package com.itsz.sht.struts.eipo.action;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.Globals;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;

import com.itsz.sht.common.Constants;
import com.itsz.sht.common.DataModelUtil;
import com.itsz.sht.common.model.request.EIPORequestModel;
import com.itsz.sht.common.model.response.FilterEIPOListResponseModel;
import com.itsz.sht.common.user.UserObject;
import com.itsz.sht.exception.WebActionError;
import com.itsz.sht.struts.action.ITSZAction;
import com.itsz.sht.struts.eipo.form.IPOMasterEnquiryForm;
import com.itsz.sht.struts.form.ipo.IPORequestForm;
import com.itsz.sht.vp.ViewProvider;
import com.itsz.sht.vp.common.ProcessBean;


/**
 * @struts.action path="/IPOQueryListAction" name="IPORequestForm"
 * 
 * @struts.action-forward name="success" path="/jsp/ipo/index.jsp"
 *                        redirect="false"
 * 
 * @struts.action-forward name="failure" path="/jsp/blank.jsp" redirect="false"
 **/

public class IPOMasterEnquiryAction extends ITSZAction {

	public IPOMasterEnquiryAction() {
	}

	@Override
	public ActionForward executeAction(ViewProvider vp, ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		Locale locale = request.getSession().getAttribute(Globals.LOCALE_KEY) != null ? (Locale) request
				.getSession().getAttribute(Globals.LOCALE_KEY)
				: Locale.US;
		MessageResources mr = request.getAttribute(Globals.MESSAGES_KEY) != null ? (MessageResources) request
				.getAttribute(Globals.MESSAGES_KEY)
				: null;

		EIPORequestModel model = new EIPORequestModel();
		ActionErrors errors = new ActionErrors();
		model.setMr(mr);
		model.setLocale(locale);

		DataModelUtil.form2Model(request, (IPOMasterEnquiryForm) form, model,response);
		FilterEIPOListResponseModel responseModel = new FilterEIPOListResponseModel();
		UserObject user = (UserObject) request.getSession().getAttribute(Constants.USER);
		if (false == user.isDefaultAccountExist()){
			responseModel.setReturnCode("label.eipo.error.onlineerror");
		} else {
			if (user != null && user.getTradeInfoObject() != null)
				model.setAccountId(user.getTradeInfoObject().getTradingAccount());
			responseModel = facade.getAllEIPORecord(model);
		}
		ProcessBean processBean = new ProcessBean(responseModel, null, mapping,form,request, response);
		return vp.processEIPOQueryList(processBean);
	}
}
