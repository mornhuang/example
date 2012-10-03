package com.itsz.sht.struts.action.ipo;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.itsz.sht.common.Constants;
import com.itsz.sht.common.DataModelUtil;
import com.itsz.sht.common.model.request.IPOQtyRequestModel;
import com.itsz.sht.common.model.request.IPORequestModel;
import com.itsz.sht.common.model.response.FilterIPOQtyAmtRcrdResponseModel;
import com.itsz.sht.common.user.UserObject;
import com.itsz.sht.struts.action.ITSZAction;
import com.itsz.sht.struts.form.ipo.IPOQtyAmtForm;
import com.itsz.sht.vp.ViewProvider;
import com.itsz.sht.vp.common.ProcessBean;

/**
 * @struts.action
 *    path="/IPOQueryQtyAction"
 *    name="IPORequestForm"
 *
 * @struts.action-forward
 *    name="success"
 *    path="/jsp/ipo/rider5.jsp"
 *    redirect="false"
 *
 * @struts.action-forward
 *    name="failure"
 *    path="/jsp/blank.jsp"
 *    redirect="false"
 **/

public class IPOQueryQtyAction extends ITSZAction {

    public IPOQueryQtyAction() {
    }

	@Override
	public ActionForward executeAction(ViewProvider vp, ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		IPOQtyRequestModel model = new IPOQtyRequestModel();
		DataModelUtil.form2Model(request,(IPOQtyAmtForm) form, model, response);
//		UserObject user = (UserObject) request.getSession().getAttribute(Constants.USER);
//		if (user != null && user.getTradeInfoObject() != null) 
//		    model.setAccountId(user.getTradeInfoObject().getTradingAccount());		
		FilterIPOQtyAmtRcrdResponseModel responseModel = facade.getIPOQtyAmtRcrd(model);
		ProcessBean processBean = new ProcessBean(responseModel,null, mapping, request,response);
		return vp.processIPOQueryQty(processBean);
	}
}
