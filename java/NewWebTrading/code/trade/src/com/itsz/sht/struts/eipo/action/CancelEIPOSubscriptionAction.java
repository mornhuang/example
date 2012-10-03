package com.itsz.sht.struts.eipo.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.Globals;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;

import com.itsz.sht.common.Constants;
import com.itsz.sht.common.Consts;
import com.itsz.sht.common.DataModelUtil;
import com.itsz.sht.common.model.request.CancelEIPOSubscriptionRequestModel;
import com.itsz.sht.common.model.response.CancelEIPOSubscriptionResponseModel;
import com.itsz.sht.common.user.UserObject;
import com.itsz.sht.struts.action.ITSZAction;
import com.itsz.sht.struts.eipo.form.CancelEIPOSubscriptionForm;
import com.itsz.sht.vp.ViewProvider;
import com.itsz.sht.vp.common.ProcessBean;
import com.taifook.mcs.core.beans.msg.TradingAccountInfo;

public class CancelEIPOSubscriptionAction extends ITSZAction{
	
	public ActionForward executeAction(ViewProvider vp, ActionMapping mapping,
            ActionForm form, HttpServletRequest request,
            HttpServletResponse response) {

		CancelEIPOSubscriptionRequestModel model = new CancelEIPOSubscriptionRequestModel();
		DataModelUtil.form2Model(request,(CancelEIPOSubscriptionForm) form, model, response);	
		UserObject user = (UserObject) request.getSession().getAttribute(Constants.USER);
		Locale locale = request.getSession().getAttribute(Globals.LOCALE_KEY) != null ? (Locale) request.getSession().getAttribute(Globals.LOCALE_KEY) : Locale.US;
		MessageResources mr = request.getAttribute(Globals.MESSAGES_KEY) != null ? (MessageResources) request.getAttribute(Globals.MESSAGES_KEY) : null;
		model.setAccountId(user.getTradeInfoObject().getTradingAccount());
		model.setClientClassCode(this.getClientClassCode(request, user));
		model.setLocale(locale);
		model.setMr(mr);
		CancelEIPOSubscriptionResponseModel responseModel = facade.getCancelEIPOSubscription(model);
		ProcessBean processBean = new ProcessBean(responseModel,null, mapping, request,response);
		return vp.processCancelEIPOSubscription(processBean);
	}
	
	public String getClientClassCode(HttpServletRequest request,UserObject user) {
		List accounts = new ArrayList();
		if (user.getTradeInfoObject().getAcEnquiryList(request) != null) {
			List tradingAccounts = (List) user.getTradeInfoObject().getAcEnquiryList(request);
			if (user.isDefaultAccountExist()) {
				 for (int i = 0; i < tradingAccounts.size(); i++) {
					 TradingAccountInfo tradingAccount = (TradingAccountInfo) tradingAccounts.get(i);
            		 if (user.getTradeInfoObject().getTradingAccount().equals(tradingAccount.getTradingAccount())) {
            			 if(null != tradingAccount.getClientClassCode()){
                			 return tradingAccount.getClientClassCode();
            			 }
            		 }
				 }
			}
		}
		return Consts.Global.Status.NORMAL;
	}

}
