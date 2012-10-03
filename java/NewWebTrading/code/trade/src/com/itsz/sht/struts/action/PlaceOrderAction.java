package com.itsz.sht.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.itsz.sht.common.Constants;
import com.itsz.sht.common.Consts;
import com.itsz.sht.common.DataModelUtil;
import com.itsz.sht.common.ForwardMappingUtil;
import com.itsz.sht.common.PortalUtil;
import com.itsz.sht.common.model.request.PlaceOrderRequestModel;
import com.itsz.sht.common.model.response.PlaceOrderResponseModel;
import com.itsz.sht.common.user.UserObject;
import com.itsz.sht.struts.form.PlaceOrderForm;
import com.itsz.sht.vp.ViewProvider;
import com.itsz.sht.vp.common.ProcessBean;

/**
 * $Id: PlaceOrderAction.java,v 1.2 2010/12/08 10:33:12 zxfan Exp $
 * 
 * @Project:portal.head
 * @File:PlaceOrderAction.java
 * @Description:
 * @Author:Cimenon Saint
 * @Date:2007-5-21
 */
public class PlaceOrderAction extends ITSZAction {

	public boolean isTokenRequired() {
		return true;
	}

	/**
	 * 
	 * @Author:Cimenon Saint
	 * @Time:15:38:17
	 * @param vp
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 */
	public ActionForward executeAction(ViewProvider vp, ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		if (!PortalUtil.checkReferer(request.getHeader("referer"))) {
			return doWebTimeout(vp, mapping, form, request, response);
		}
		String oldToken = getSessionToken(request);
		if (isTokenRequired() && !checkAndSetToken(request)) {
			return doInvalidToken(vp, mapping, form, request, response);
		}

		PlaceOrderRequestModel model = new PlaceOrderRequestModel();
		DataModelUtil.form2Model(request, (PlaceOrderForm) form, model, response);
		UserObject user = (UserObject) request.getSession().getAttribute(Constants.USER);
		if (user != null) {
			model.setLoginId(user.getLoginName());
			model.setCustCode(user.getTradeInfoObject().getCustCode());
			model.setAccountId(user.getTradeInfoObject().getTradingAccount());
		}
		PlaceOrderResponseModel responseModel = facade.placeOrder(model);
		if (isExceptionOccured(responseModel)) {
			setOldTokenToSession(request, oldToken);
		}
		ProcessBean processBean = new ProcessBean(responseModel, null, mapping,
												request, response);
		return vp.processPlaceOrder(processBean);
	}

	private boolean isExceptionOccured(PlaceOrderResponseModel responseModel) {
		return !Consts.Global.Status.NORMAL.equals(responseModel.getReturnCode());
	}

	private ActionForward doWebTimeout(ViewProvider vp, ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		PlaceOrderResponseModel responseModel = new PlaceOrderResponseModel();
		responseModel.setReturnCode(Consts.Error.Code.WEBSITE_TIMEOUT);
		responseModel.setResultForward(ForwardMappingUtil.getForward(Consts.Error.Code.SHOWMSG));
		ProcessBean processBean = new ProcessBean(responseModel, null, mapping,
												form, request, response);
		return vp.processPlaceOrder(processBean);
	}

	private ActionForward doInvalidToken(ViewProvider vp,
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		PlaceOrderResponseModel responseModel = new PlaceOrderResponseModel();
		responseModel.setReturnCode(Consts.Error.Code.ERRORCODE_TOKEN_INVALID);
		responseModel.setResultForward(ForwardMappingUtil.getForward(Consts.Error.Code.SHOWMSG));
		ProcessBean processBean = new ProcessBean(responseModel, null, mapping,
												form, request, response);
		return vp.processPlaceOrder(processBean);
	}

}
