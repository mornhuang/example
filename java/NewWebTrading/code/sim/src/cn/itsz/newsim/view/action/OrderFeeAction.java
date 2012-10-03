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
import cn.itsz.newsim.common.MD5;
import cn.itsz.newsim.dto.ResultMessage;
import cn.itsz.newsim.dto.request.OrderFeeRequest;
import cn.itsz.newsim.dto.response.entity.LoginResponseEntity;
import cn.itsz.newsim.view.form.OrderFeeForm;

/**
 * $Id: OrderFeeAction.java,v 1.3 2011/04/20 08:00:10 kdu Exp $
 * @Project:portal.head
 * @File:OrderFeeAction.java
 * @Description:
 * @Author:Cimenon Saint
 * @Date:2007-5-21
 */
@Controller("/webOrderFee")
public class OrderFeeAction extends BaseAction {
	
	public ActionForward executeAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		OrderFeeForm orderFeeForm = (OrderFeeForm) form;
		LoginResponseEntity user = (LoginResponseEntity) request.getSession().getAttribute(Constants.AttributeKey.Session.USER);
		
		ResultMessage result = new ResultMessage();
		if (Constants.Flag.POSITIVE.equals(user.getTransactionProtection()) &&
			!user.getPassword().equals(MD5.Md5(orderFeeForm.getPassword()))) {
			result.setReturnCode(Constants.Status.WARN);
			result.setErrorCode(Constants.ErrorKey.NSIM_00010);
		} else {
			OrderFeeRequest orderFeeRequest = new OrderFeeRequest();
			orderFeeRequest.setOrderPrice(new BigDecimal(orderFeeForm.getOrderPrice()));
			orderFeeRequest.setOrderQuantity(new BigDecimal(orderFeeForm.getOrderQuantity()));
			orderFeeRequest.setOrderSide(orderFeeForm.getOrderSide());
			orderFeeRequest.setOrderType(orderFeeForm.getOrderType());
			orderFeeRequest.setInstrCode(orderFeeForm.getInstrCode());
			orderFeeRequest.setLanguage(ActionUtil.locale2QSlang((Locale)request.getSession().getAttribute(Constants.Common.defaultLocaleAttributeName)));
//			orderFeeRequest.setConditionType(orderFeeForm.getConditionType());
//			orderFeeRequest.setTriggerPrice(new BigDecimal(orderFeeForm.getTriggerPrice()));
			result = facade.calOrderFee(orderFeeRequest);
		}
		return viewProcess.process(result, response);
	}
}
