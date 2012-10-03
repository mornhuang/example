package com.itsz.sht.struts.action.ipo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.itsz.sht.common.DataModelUtil;
import com.itsz.sht.common.model.request.IPOQtyRequestModel;
import com.itsz.sht.common.model.response.FilterIPOAmtRcrdResponseModel;
import com.itsz.sht.common.model.response.FilterIPOQtyAmtRcrdResponseModel;
import com.itsz.sht.struts.action.ITSZAction;
import com.itsz.sht.struts.form.ipo.IPOQtyAmtForm;
import com.itsz.sht.vp.ViewProvider;
import com.itsz.sht.vp.common.ProcessBean;

/**
 *
 * @struts.action
 *    path="/IPOQueryAmtAction"
 *    name="IPOQtyAmtForm"
 *
 * @struts.action-forward
 *    name="success"
 *    path="/jsp/ipo/rider6.jsp"
 *    redirect="false"
 *
 * @struts.action-forward
 *    name="failure"
 *    path="/jsp/blank.jsp"
 *    redirect="false"
 **/

public class IPOQueryAmtAction extends ITSZAction {

    public IPOQueryAmtAction() {
    }

	@Override
	public ActionForward executeAction(ViewProvider vp, ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		IPOQtyRequestModel model = new IPOQtyRequestModel();
		DataModelUtil.form2Model(request,(IPOQtyAmtForm) form, model, response);
		FilterIPOAmtRcrdResponseModel responseModel = facade.getIPOAmtRcrd(model);
		ProcessBean processBean = new ProcessBean(responseModel,null, mapping, request,response);
		return vp.processIPOQueryAmt(processBean);
	}
}
