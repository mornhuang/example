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
import com.itsz.sht.common.model.request.PlaceOrderRequestModel;
import com.itsz.sht.common.model.response.PlaceOrderResponseModel;
import com.itsz.sht.common.user.UserObject;
import com.itsz.sht.exception.ITSZException;
import com.itsz.sht.struts.form.PlaceOrderForm;
import com.itsz.sht.vp.ViewProvider;
import com.itsz.sht.vp.common.ProcessBean;

/**
 * $Id: PlaceOrderPSAction.java,v 1.5 2011/04/06 10:11:27 pbxie Exp $
 * 
 * @Project:portal.head
 * @File:PlaceOrderAction.java
 * @Description:
 * @Author:Cimenon Saint
 * @Date:2007-5-21
 */
public class PlaceOrderPSAction extends ITSZAction {

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
		
		PlaceOrderRequestModel model = new PlaceOrderRequestModel();
		DataModelUtil.form2Model(request, (PlaceOrderForm) form, model, response);
		if (!isOwnAccountID(request, response, model.getAccountId())) {
			ITSZException ex = new ITSZException();
			ex.setErrorCode(Constants.ERRORCODE_CONNECT_MCS);
			log_debug.info(model.getAccountId() + ": the account id is not belong to you");
			return processException(vp,mapping,ex, request, response);
		}

		UserObject user = (UserObject) request.getSession().getAttribute(Constants.USER);
		LoginUserInfo loginUserInfo = (LoginUserInfo) request.getSession().getAttribute(WEB_Constants.LOGIN_USER_INFO);
		if (user != null) {
			model.setLoginId(user.getLoginName());
			model.setCustCode(user.getTradeInfoObject().getCustCode());
			model.setAccountId(user.getTradeInfoObject().getTradingAccount());
			model.setMarketCode(user.getTradeInfoObject().getMarketCode());
		}
		if(loginUserInfo!=null){
			model.setTransactionProtection(loginUserInfo.getTransactionProtection());
		}
		model.setSupportOverNightFlag(Consts.Global.Flag.POSITIVE);
		if("TODAY".equalsIgnoreCase(model.getValidityType())){
			model.setSupportOverNightFlag(Consts.Global.Flag.NEGATIVE);
		}
		model.setValidityType("");
		PlaceOrderResponseModel responseModel = facade.placeOrder(model);
		
		ProcessBean processBean = new ProcessBean(responseModel, null, mapping,
												request, response);
		return vp.processPlaceOrder(processBean);
	}

}
