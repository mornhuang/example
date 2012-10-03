package com.itsz.sht.struts.eipo.action;

import java.util.ArrayList;
import java.util.List;
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
import com.itsz.sht.common.Consts;
import com.itsz.sht.common.DataModelUtil;
import com.itsz.sht.common.model.request.EIPORequestModel;
import com.itsz.sht.common.model.response.EIPOSubResponseModel;
import com.itsz.sht.common.user.UserObject;
import com.itsz.sht.exception.WebActionError;
import com.itsz.sht.struts.action.ITSZAction;
import com.itsz.sht.struts.eipo.dao.EIPOMasterEntry;
import com.itsz.sht.struts.eipo.util.EIPOConstants;
import com.itsz.sht.struts.form.ITSZForm;
import com.itsz.sht.vp.ViewProvider;
import com.itsz.sht.vp.common.ProcessBean;
import com.taifook.mcs.core.beans.msg.TradingAccountInfo;
import com.taifook.mtss.web.eipo.exception.EIPOServiceProviderException;


/**
 * @author Arthur Chen 20110416 for eipo
 *
 */

/**
 * 
 * @struts.action path="/ipoSubscription" name="emptyForm" scope="request"
 *                validate="false"
 * 
 * @struts.action-forward name="success" path="/jsp/eipo_subscription.jsp"
 *                        redirect="false"
 * 
 * @struts.action-forward name="failure" path="/ipoMasterEnquiry.do"
 *                        redirect="false"
 */

public class IPOSubscriptionAction extends ITSZAction {

	@Override
	public ActionForward executeAction(ViewProvider vp, ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		ActionErrors errors = new ActionErrors();
		Locale locale = request.getSession().getAttribute(Globals.LOCALE_KEY) != null ? (Locale) request
				.getSession().getAttribute(Globals.LOCALE_KEY)
				: Locale.US;
		MessageResources mr = request.getAttribute(Globals.MESSAGES_KEY) != null ? (MessageResources) request
				.getAttribute(Globals.MESSAGES_KEY)
				: null;
		EIPORequestModel model = new EIPORequestModel();
		model.setMr(mr);
		model.setLocale(locale);
		DataModelUtil.form2Model(request, (ITSZForm) form, model,
				response);
		UserObject user = (UserObject) request.getSession().getAttribute(
				Constants.USER);
		String ipoMasterId = request.getParameter("id");
		model.setIpoMasterId(new Long(ipoMasterId));
		model.setClientClassCode(this.getClientClassCode(request, user));
		if (user != null && user.getTradeInfoObject() != null)
			model.setAccountId(user.getTradeInfoObject().getTradingAccount());
		EIPOSubResponseModel responseModel = null;
		try {
			responseModel = facade.getEIPOSubRecord(model);
		} catch (EIPOServiceProviderException e) { 
			errors.add(ActionErrors.GLOBAL_ERROR, new WebActionError(e
					.getMessageKey(), e.getArgs()));
		} catch (Exception e) {
			errors.add(ActionErrors.GLOBAL_ERROR,
					new WebActionError("MCS00600"));
		}
		ProcessBean processBean = new ProcessBean(responseModel, null, mapping,
				request, response);
		EIPOMasterEntry masterEntry = responseModel.getMasterEntry();
		request.getSession().setAttribute(EIPOConstants.SESSION_IPO_SUBSCRIPTION,masterEntry);
		return vp.processEIPOSub(processBean);
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
