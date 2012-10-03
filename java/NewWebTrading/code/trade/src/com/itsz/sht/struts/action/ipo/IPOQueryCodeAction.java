package com.itsz.sht.struts.action.ipo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.itsz.sht.common.Constants;
import com.itsz.sht.common.DataModelUtil;
import com.itsz.sht.common.model.request.IPORequestModel;
import com.itsz.sht.common.model.response.QueryCodeResponseModel;
import com.itsz.sht.common.user.UserObject;
import com.itsz.sht.struts.action.ITSZAction;
import com.itsz.sht.struts.form.OrderDetailForm;
import com.itsz.sht.struts.form.ipo.IPORequestForm;
import com.itsz.sht.vp.ViewProvider;
import com.itsz.sht.vp.common.ProcessBean;

/**
 * @struts.action
 *    path="/IPOQueryCodeAction"
 *    name="IPORequestForm"
 *
 * @struts.action-forward
 *    name="success"
 *    path="/jsp/ipo/rider1.jsp"
 *    redirect="false"
 *
 * @struts.action-forward
 *    name="successed"
 *    path="/jsp/ipo/status.jsp"
 *    redirect="false"
 *
 * @struts.action-forward
 *    name="successedResult"
 *    path="/jsp/ipo/status_result.jsp"
 *    redirect="false"
 *
 *  @struts.action-forward
 *    name="failureonline"
 *    path="/jsp/ipo/onlineerr.jsp"
 *    redirect="false"
 *
 * @struts.action-forward
 *    name="failure"
 *    path="/jsp/blank.jsp"
 *    redirect="false"
 **/

public class IPOQueryCodeAction extends ITSZAction {

    public IPOQueryCodeAction() {
    }

	@Override
	public ActionForward executeAction(ViewProvider vp, ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
        IPORequestModel model = new IPORequestModel();
		DataModelUtil.form2Model(request,(IPORequestForm) form, model, response);
		UserObject user = (UserObject) request.getSession().getAttribute(Constants.USER);
		if (user != null && user.getTradeInfoObject() != null) 
		    model.setAccountId(user.getTradeInfoObject().getTradingAccount());		
		QueryCodeResponseModel responseModel = facade.getIPOQueryCode(model);
		ProcessBean processBean = new ProcessBean(responseModel,null, mapping, request,response);
		return vp.processGetIPOQueryCode(processBean);
	}
}
