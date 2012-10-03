package com.itsz.sht.struts.action;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.itsz.sht.common.Constants;
import com.itsz.sht.common.Consts;
import com.itsz.sht.common.DataModelUtil;
import com.itsz.sht.common.model.request.OrderDetailRequestModel;
import com.itsz.sht.common.model.response.listorder.OrderDetailTradeResponseModel;
import com.itsz.sht.common.user.UserObject;
import com.itsz.sht.struts.form.OrderDetailForm;
import com.itsz.sht.vp.ViewProvider;
import com.itsz.sht.vp.common.ProcessBean;

public class TradeHistoryAction extends ITSZAction {

	@Override
	public ActionForward executeAction(ViewProvider vp, ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		OrderDetailRequestModel model = new OrderDetailRequestModel();
		OrderDetailForm thaf = (OrderDetailForm)form;
		DataModelUtil.form2Model(request, thaf, model, response);
		model.setHasHisories(Consts.Global.Flag.POSITIVE);
		UserObject user = (UserObject) request.getSession().getAttribute(Constants.USER);
		if (user != null) {
			model.setAccountId(user.getTradeInfoObject().getTradingAccount());
		}
		
		OrderDetailTradeResponseModel responseModel = facade.orderDetail(model);
		ProcessBean processBean = new ProcessBean(responseModel, null, mapping, request,response);
		return vp.processTradeHistory(processBean);
	}
}
