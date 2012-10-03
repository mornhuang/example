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
import com.itsz.sht.common.WEB_Constants;
import com.itsz.sht.common.model.common.LoginUserInfo;
import com.itsz.sht.common.model.request.ModifyOrderRequestModel;
import com.itsz.sht.common.model.response.ModifyOrderResponseModel;
import com.itsz.sht.common.user.UserObject;
import com.itsz.sht.struts.form.ModifyOrderForm;
import com.itsz.sht.vp.ViewProvider;
import com.itsz.sht.vp.common.ProcessBean;

/**
 * $Id: ModifyOrderAction.java,v 1.4 2010/12/21 11:33:43 zxfan Exp $
 * 
 * @Project:portal.head
 * @File:ModifyOrderAction.java
 * @Description:
 * @Author:Cimenon Saint
 * @Date:2007-5-21
 */
public class ModifyOrderAction extends ITSZAction {

	public boolean isTokenRequired() {
		return true;
	}

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
		ModifyOrderRequestModel model = new ModifyOrderRequestModel();
		DataModelUtil.form2Model(request, (ModifyOrderForm) form, model, response);
		LoginUserInfo loginUserInfo = (LoginUserInfo) request.getSession().getAttribute(WEB_Constants.LOGIN_USER_INFO);
		model.setTransactionProtection(loginUserInfo.getTransactionProtection());
		UserObject user = (UserObject) request.getSession().getAttribute(Constants.USER);
		if (user != null) {
			model.setLoginId(user.getLoginName());
			model.setCustCode(user.getTradeInfoObject().getCustCode());
			model.setAccountId(user.getTradeInfoObject().getTradingAccount());
		}
		ModifyOrderResponseModel responseModel = facade.modifyOrder(model);
		if (isExceptionOccured(responseModel)) {
			setOldTokenToSession(request, oldToken);
		}
		ProcessBean processBean = new ProcessBean(responseModel, null, mapping, form, request, response);
		return vp.processModifyOrder(processBean);
	}

	private boolean isExceptionOccured(ModifyOrderResponseModel responseModel) {
		return !Consts.Global.Status.NORMAL.equals(responseModel.getReturnCode());
	}

	private ActionForward doWebTimeout(ViewProvider vp, ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		ModifyOrderResponseModel responseModel = new ModifyOrderResponseModel();
		responseModel.setReturnCode(Consts.Error.Code.WEBSITE_TIMEOUT);
		responseModel.setResultForward(ForwardMappingUtil.getForward(Consts.Error.Code.SHOWMSG));
		ProcessBean processBean = new ProcessBean(responseModel, null, mapping, form, request, response);
		return vp.processModifyOrder(processBean);
	}

	private ActionForward doInvalidToken(ViewProvider vp,
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		ModifyOrderResponseModel responseModel = new ModifyOrderResponseModel();
		responseModel.setReturnCode(Consts.Error.Code.ERRORCODE_TOKEN_INVALID);
		responseModel.setResultForward(ForwardMappingUtil.getForward(Consts.Error.Code.SHOWMSG));
		ProcessBean processBean = new ProcessBean(responseModel, null, mapping, form, request, response);
		return vp.processModifyOrder(processBean);
	}

}
