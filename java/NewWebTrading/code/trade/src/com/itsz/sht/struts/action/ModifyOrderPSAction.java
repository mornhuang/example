package com.itsz.sht.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.itsz.sht.common.Constants;
import com.itsz.sht.common.DataModelUtil;
import com.itsz.sht.common.WEB_Constants;
import com.itsz.sht.common.model.common.LoginUserInfo;
import com.itsz.sht.common.model.request.ModifyOrderRequestModel;
import com.itsz.sht.common.model.response.ModifyOrderResponseModel;
import com.itsz.sht.common.user.UserObject;
import com.itsz.sht.struts.form.ModifyOrderForm;
import com.itsz.sht.vp.ViewProvider;
import com.itsz.sht.vp.common.ProcessBean;

public class ModifyOrderPSAction extends ITSZAction {

	public boolean isTokenRequired() {
		return true;
	}

	public ActionForward executeAction(ViewProvider vp, ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		ModifyOrderRequestModel model = new ModifyOrderRequestModel();
		ModifyOrderForm moForm = (ModifyOrderForm)form;
		DataModelUtil.form2Model(request, moForm, model, response);
	    if(StringUtils.isNotBlank(moForm.getMtssOrderId()))
			model.setOrderId(Long.parseLong(moForm.getMtssOrderId()));
		LoginUserInfo loginUserInfo = (LoginUserInfo) request.getSession().getAttribute(WEB_Constants.LOGIN_USER_INFO);
		model.setTransactionProtection(loginUserInfo.getTransactionProtection());
		UserObject user = (UserObject) request.getSession().getAttribute(Constants.USER);
		if (user != null) {
			model.setLoginId(user.getLoginName());
			model.setCustCode(user.getTradeInfoObject().getCustCode());
			model.setAccountId(user.getTradeInfoObject().getTradingAccount());
		}
		ModifyOrderResponseModel responseModel = facade.modifyOrder(model);
		ProcessBean processBean = new ProcessBean(responseModel, null, mapping, form, request, response);
		return vp.processModifyOrder(processBean);
	}
}
