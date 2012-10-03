package com.itsz.sht.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.itsz.sht.common.Constants;
import com.itsz.sht.common.user.TradeInfoObject;
import com.itsz.sht.common.user.UserObject;
import com.itsz.sht.vp.ViewProvider;
import com.itsz.sht.vp.common.ProcessBean;

public class EPaymentEntranceAction extends ITSZAction  {

	@Override
	public ActionForward executeAction(ViewProvider vp, ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		TradeInfoObject model = new TradeInfoObject();
		UserObject user = (UserObject) request.getSession().getAttribute(Constants.USER);
		if(user != null && user.getTradeInfoObject() != null)
			model = user.getTradeInfoObject();
		ProcessBean processBean = new ProcessBean(model,null, mapping, request,response);
		return vp.processEPaymentEntrance(processBean);
	}

}
