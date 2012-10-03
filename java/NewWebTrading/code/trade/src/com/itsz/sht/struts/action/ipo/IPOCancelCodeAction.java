package com.itsz.sht.struts.action.ipo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.itsz.sht.common.Constants;
import com.itsz.sht.common.DataModelUtil;
import com.itsz.sht.common.model.request.RemoveIPORequestModel;
import com.itsz.sht.common.model.response.RemoveIPOResponseModel;
import com.itsz.sht.common.user.UserObject;
import com.itsz.sht.struts.action.ITSZAction;
import com.itsz.sht.struts.form.ipo.IPOCancelForm;
import com.itsz.sht.vp.ViewProvider;
import com.itsz.sht.vp.common.ProcessBean;

/**
 *
 * @struts.action
 *    path="/IPOCancelCodeAction"
 *    name="IPOCancelForm"
 *    attribute="IPOCancelForm"
 *
 * @struts.action-forward
 *    name="success"
 *    path="/jsp/ipo/optsc.jsp"
 *    redirect="false"
 *
 * @struts.action-forward
 *    name="linefailure"
 *    path="/jsp/ipo/lineerr.jsp"
 *    redirect="false"
 *
 * @struts.action-forward
 *    name="pwdfailure"
 *    path="/jsp/ipo/status_cancel.jsp"
 *    redirect="false"
 *
 * @struts.action-forward
 *    name="failure"
 *    path="/jsp/blank.jsp"
 *    redirect="false"
 **/

public class IPOCancelCodeAction extends ITSZAction {

    public IPOCancelCodeAction() {
    }

	@Override
	public ActionForward executeAction(ViewProvider vp, ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		RemoveIPORequestModel model = new RemoveIPORequestModel();
		DataModelUtil.form2Model(request,(IPOCancelForm) form, model, response);
		UserObject user = (UserObject) request.getSession().getAttribute(Constants.USER);
		if (user != null && user.getTradeInfoObject() != null) 
		    model.setAccountId(user.getTradeInfoObject().getTradingAccount());
			model.setLoginId(user.getLoginName());
		RemoveIPOResponseModel responseModel = facade.removeIPORequest(model);
		ProcessBean processBean = new ProcessBean(responseModel,null, mapping,(IPOCancelForm) form, request,response);
		return vp.processRemoveIPO(processBean);
	}
}
