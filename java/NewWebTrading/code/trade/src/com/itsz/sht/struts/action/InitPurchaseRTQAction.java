package com.itsz.sht.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.itsz.sht.common.Consts;
import com.itsz.sht.common.WEB_Constants;
import com.itsz.sht.common.model.common.LoginUserInfo;
import com.itsz.sht.struts.form.PurchaseRTQForm;
import com.itsz.sht.vp.ViewProvider;

/**
 * $Id: InitPurchaseRTQAction.java,v 1.3 2011/01/05 06:50:43 kyzou Exp $
 * @Project:NewWebTrading
 * @File:InitPurchaseRTQAction.java
 * @Description:
 * @Author:kyzou
 * @Date:2010-12-27
 */
public class InitPurchaseRTQAction extends ITSZAction {

	@Override
	public ActionForward executeAction(ViewProvider vp, ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		PurchaseRTQForm initForm = (PurchaseRTQForm) form;
		LoginUserInfo loginUserInfo = (LoginUserInfo) request.getSession().getAttribute(WEB_Constants.LOGIN_USER_INFO);
		String transactionProtection =  loginUserInfo.getTransactionProtection();
		request.setAttribute("transactionProtection", transactionProtection);
		request.setAttribute("PurchaseRTQForm", initForm);
		String productId = initForm.getProductId();
		String existProductId = request.getParameter("existProductId");
		if(StringUtils.isBlank(productId)){
			productId = existProductId;
			initForm.setProductId(productId);
			request.setAttribute("PurchaseRTQForm", initForm);
		}
		if(Consts.RTQ.Product_AAS.equalsIgnoreCase(productId) || Consts.RTQ.Product_AAS_CN.equalsIgnoreCase(productId)){
			return mapping.findForward("aastock");
		}
		return mapping.findForward("etnet");
	}
}
