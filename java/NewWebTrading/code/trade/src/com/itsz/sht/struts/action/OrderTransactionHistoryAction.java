package com.itsz.sht.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.itsz.sht.common.Consts;
import com.itsz.sht.common.DataModelUtil;
import com.itsz.sht.common.model.request.EnquireAccountListRequestModel;
import com.itsz.sht.common.model.response.MISAccountEnquiryResponseModel;
import com.itsz.sht.common.user.UserObject;
import com.itsz.sht.struts.form.OrderTransactionHistoryForm;
import com.itsz.sht.vp.ViewProvider;
import com.itsz.sht.vp.common.ProcessBean;

public class OrderTransactionHistoryAction extends ITSZAction {

	public ActionForward executeAction(ViewProvider vp, ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		OrderTransactionHistoryForm historyForm = (OrderTransactionHistoryForm)form;
		EnquireAccountListRequestModel accountModel = new EnquireAccountListRequestModel();
		DataModelUtil.request2Model(request, accountModel);
		UserObject user=(UserObject)request.getSession().getAttribute(Consts.Profile.USER);
		if (user != null && user.getTradeInfoObject() != null) 
			accountModel.setCustCode(user.getTradeInfoObject().getCustCode());
		MISAccountEnquiryResponseModel responseModel = facade.enquireMisAccount(accountModel);
		ProcessBean processBean = new ProcessBean(responseModel, null, mapping, historyForm, request, response);
		return vp.processOrderTransactionHistory(processBean);
	}

}
