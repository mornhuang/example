package cn.itsz.newsim.view.action;

import java.math.BigDecimal;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.stereotype.Controller;

import cn.itsz.newsim.common.ActionUtil;
import cn.itsz.newsim.common.Constants;
import cn.itsz.newsim.dto.ResultMessage;
import cn.itsz.newsim.dto.request.InputOrderRequest;
import cn.itsz.newsim.dto.response.entity.LoginResponseEntity;
import cn.itsz.newsim.view.form.PlaceOrderForm;

/**
 * $Id: PlaceOrderAction.java,v 1.4 2011/03/10 08:42:13 bwu Exp $
 * 
 * @Project:portal.head
 * @File:PlaceOrderAction.java
 * @Description:
 * @Author:zxfan
 * @Date:2007-5-21
 */
@Controller("/webPlaceOrder")
public class PlaceOrderAction extends BaseAction {

	public ActionForward executeAction(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		PlaceOrderForm orderForm = (PlaceOrderForm) form;
		InputOrderRequest model = new InputOrderRequest();
		LoginResponseEntity user = (LoginResponseEntity) request.getSession().getAttribute(Constants.AttributeKey.Session.USER);
		model.setLoginId(user.getLoginId());
		model.setAccountId(user.getTradeAccount());
		model.setInstrCode(orderForm.getInstrCode());
		model.setOrderPrice(new BigDecimal(orderForm.getOrderPrice()));
		model.setOrderQuantity(new BigDecimal(orderForm.getOrderQuantity()));
		model.setOrderSide(orderForm.getOrderSide());
		model.setOrderType(orderForm.getOrderType());
		String lang =ActionUtil.locale2QSlang((Locale)request.getSession().getAttribute(Constants.Common.defaultLocaleAttributeName));
		model.setLanguage(lang);
		ResultMessage result = facade.placeOrder(model);
		return viewProcess.process(result, response);
	}
}
