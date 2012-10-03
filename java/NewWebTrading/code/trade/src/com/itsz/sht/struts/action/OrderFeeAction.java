package com.itsz.sht.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.itsz.sht.common.Constants;
import com.itsz.sht.common.Consts;
import com.itsz.sht.common.DataModelUtil;
import com.itsz.sht.common.WEB_Constants;
import com.itsz.sht.common.model.AbstractResponseModel;
import com.itsz.sht.common.model.common.LoginUserInfo;
import com.itsz.sht.common.model.request.OrderFeeRequestModel;
import com.itsz.sht.common.model.request.VerifyPasswordRequestModel;
import com.itsz.sht.common.user.UserObject;
import com.itsz.sht.struts.form.OrderFeeForm;
import com.itsz.sht.vp.ViewProvider;
import com.itsz.sht.vp.common.ProcessBean;

/**
 * $Id: OrderFeeAction.java,v 1.2 2010/12/15 08:31:54 zxfan Exp $
 * @Project:portal.head
 * @File:OrderFeeAction.java
 * @Description:
 * @Author:Cimenon Saint
 * @Date:2007-5-21
 */
public class OrderFeeAction extends ITSZAction {
	
	/**
	 * 
	 * @Author:Cimenon Saint
	 * @Time:16:27:00
	 * @param vp
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward executeAction(
		ViewProvider vp,
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response) {
		AbstractResponseModel responseModel = null;
		
		UserObject user = (UserObject) request.getSession().getAttribute(Constants.USER);
		LoginUserInfo loginUserInfo = (LoginUserInfo) request.getSession().getAttribute(WEB_Constants.LOGIN_USER_INFO);
		if (user != null &&
			"Y".equals(loginUserInfo.getTransactionProtection())) {
			VerifyPasswordRequestModel verifyPasswordRequestModel = new VerifyPasswordRequestModel();
			DataModelUtil.form2Model(request, (OrderFeeForm) form, verifyPasswordRequestModel, response);
			verifyPasswordRequestModel.setLoginId(user.getLoginName());
			responseModel = facade.verifyPassword(verifyPasswordRequestModel);
		}
		if (responseModel == null || Consts.Global.Status.NORMAL.equals(responseModel.getReturnCode())) {
			OrderFeeRequestModel model = new OrderFeeRequestModel();
			DataModelUtil.form2Model(request, (OrderFeeForm) form, model, response);
			model.setAccountId(user.getTradeInfoObject().getTradingAccount());
			responseModel = facade.calOrderFee(model);
		}
		if (Consts.Global.Status.NORMAL.equals(responseModel.getReturnCode())) {
			setTokenToSession(request);
		}
		ProcessBean processBean = new ProcessBean(responseModel, null, mapping, request, response);
		return vp.processOrderFee(processBean);
	}
}
