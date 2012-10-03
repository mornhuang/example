package com.itsz.sht.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.itsz.sht.common.Constants;
import com.itsz.sht.common.Consts;
import com.itsz.sht.common.DataModelUtil;
import com.itsz.sht.common.model.request.FilterOrderRequestModel;
import com.itsz.sht.common.model.response.FilterOrderResponseModel;
import com.itsz.sht.common.user.UserObject;
import com.itsz.sht.struts.form.ListOrderForm;
import com.itsz.sht.vp.ViewProvider;
import com.itsz.sht.vp.common.ProcessBean;

/**
 * $Id: ListOrderAction.java,v 1.5 2011/03/09 04:32:27 xlliu Exp $
 * 
 * @Project:portal.head
 * @File:ListOrderAction.java
 * @Description:
 * @Author:Cimenon Saint
 * @Date:2007-5-21
 */
public class ListOrderAction extends ITSZAction {

	/**
	 * 
	 * @Author:Cimenon Saint
	 * @Time:16:26:36
	 * @param vp
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward executeAction(ViewProvider vp, ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		FilterOrderRequestModel model = new FilterOrderRequestModel();
		ListOrderForm orderForm = (ListOrderForm) form;
		if(StringUtils.isBlank(orderForm.getInstrCode())){
			orderForm.setInstrCode(null);
		}
		//System.out.println(orderForm.getInstrCode().length());
//		orderForm.setPageSize(PropertyConfig.getProviderName(Consts.Order.ListBook.PageSize.NAME));
		if (Integer.valueOf(orderForm.getPageSize()) <= 0) {
			orderForm.setPageSize(new Integer(Consts.Order.ListBook.PageSize.DEFAULTSIZE).toString());
		}
		DataModelUtil.form2Model(request, orderForm, model, response);
		FilterOrderResponseModel responseModel = new FilterOrderResponseModel();
		UserObject userObject = (UserObject) request.getSession().getAttribute(Constants.USER);
		if (userObject != null && userObject.getTradeInfoObject() != null) {
			model.setAccountId(userObject.getTradeInfoObject().getTradingAccount());
			responseModel = facade.filterOrder(model);
		}
		ProcessBean processBean = new ProcessBean(responseModel, null, mapping, request, response);
		return vp.processOrderList(processBean);
	}
}
