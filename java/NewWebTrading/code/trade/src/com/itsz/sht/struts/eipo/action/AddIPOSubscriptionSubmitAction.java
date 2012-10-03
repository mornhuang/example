package com.itsz.sht.struts.eipo.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.itsz.eipo.webservice.EIPOResponse;
import com.itsz.sht.common.Constants;
import com.itsz.sht.common.DataModelUtil;
import com.itsz.sht.common.model.request.EIPOSubscriptionSubmitRequestModel;
import com.itsz.sht.common.user.UserObject;
import com.itsz.sht.struts.action.ITSZAction;
import com.itsz.sht.struts.eipo.dao.EIPOMasterEntry;
import com.itsz.sht.struts.eipo.form.AddIPOSubscriptionSubmitForm;
import com.itsz.sht.struts.eipo.util.EIPOConstants;
import com.itsz.sht.vp.ViewProvider;
import com.itsz.sht.vp.common.ProcessBean;


/**
 * @author yzhang
 *
 */

/**
 * 
 * @struts.action path="/addIpoSubscriptionSubmit"
 *                name="addIpoSubscriptionSubmitForm" scope="request"
 *                validate="false"
 * 
 * @struts.action-forward name="success" path="/jsp/eipo/eipo_add_success.jsp"
 *                        redirect="false"
 * 
 * @struts.action-forward name="pwdfailure" path="/jsp/eipo/eipo_add_detail.jsp"
 *                        redirect="false"
 * 
 * @struts.action-forward name="failure" path="/ipoMasterEnquiry.do"
 *                        redirect="false"
 */

public class AddIPOSubscriptionSubmitAction extends ITSZAction {

	@Override
	public ActionForward executeAction(ViewProvider vp, ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		EIPOMasterEntry ipoSubscription = (EIPOMasterEntry) request.getSession().getAttribute(EIPOConstants.SESSION_IPO_SUBSCRIPTION);
		UserObject user = (UserObject) request.getSession().getAttribute(
				Constants.USER);
		EIPOSubscriptionSubmitRequestModel model = new EIPOSubscriptionSubmitRequestModel();
		AddIPOSubscriptionSubmitForm asForm = (AddIPOSubscriptionSubmitForm)form;
		model.setUser(user);
		model.setIpoSubscription(ipoSubscription);
		DataModelUtil.form2Model(request,(AddIPOSubscriptionSubmitForm) form, model, response);
		model.setLoginId(user.getLoginName());
		model.setEmail(asForm.isEmail());
		model.setPhoto(asForm.isPhone());
//		request.setAttribute("subId", model.getSubscriptionId());
		EIPOResponse responseModel = facade.getEIPOSubscriptionSubmit(model);
		ProcessBean processBean = new ProcessBean(responseModel,null, mapping, request,response);	
		return vp.processEIPOSubscriptionSubmit(processBean);
	}

}
