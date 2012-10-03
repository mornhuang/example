package com.itsz.sht.struts.action.ipo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.itsz.sht.common.Constants;
import com.itsz.sht.common.DataModelUtil;
import com.itsz.sht.common.model.response.IPOAddRequestModel;
import com.itsz.sht.common.model.response.InsertIPOResponseModel;
import com.itsz.sht.common.user.UserObject;
import com.itsz.sht.struts.action.ITSZAction;
import com.itsz.sht.struts.form.ipo.IPORequestAddForm;
import com.itsz.sht.vp.ViewProvider;
import com.itsz.sht.vp.common.ProcessBean;

/**
 * @struts.action
 *    path="/IPORequestAddAction"
 *    name="IPORequestAddForm"
 *    attribute="IPORequestAddForm"
 *
 * @struts.action-forward
 *    name="success"
 *    path="/jsp/ipo/rider7.jsp"
 *    redirect="false"
 *
 * @struts.action-forward
 *    name="pwdfailure"
 *    path="/jsp/ipo/rider6.jsp"
 *    redirect="false"
 *
 * @struts.action-forward
 *    name="dblfailure"
 *    path="/jsp/ipo/dblerr.jsp"
 *    redirect="false"
 *
 * @struts.action-forward
 *    name="linefailure"
 *    path="/jsp/ipo/lineerr.jsp"
 *    redirect="false"
 *
 * @struts.action-forward
 *    name="failure"
 *    path="/jsp/ipo/delerr.jsp"
 *    redirect="false"
 **/

public class IPORequestAddAction extends ITSZAction {

    public IPORequestAddAction() {
    }

	@Override
	public ActionForward executeAction(ViewProvider vp, ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		IPOAddRequestModel model = new IPOAddRequestModel();
		DataModelUtil.form2Model(request,(IPORequestAddForm) form, model, response);
		UserObject user = (UserObject) request.getSession().getAttribute(Constants.USER);
		if (user != null && user.getTradeInfoObject() != null){ 
		    model.setAccountId(user.getTradeInfoObject().getTradingAccount());
		    model.setLoginId(user.getLoginName());
		}
		InsertIPOResponseModel responseModel = facade.insertIPORequest(model);
		ProcessBean processBean = new ProcessBean(responseModel,null, mapping,(IPORequestAddForm) form, request,response);
		return vp.processInsertIPO(processBean);
	}
}
