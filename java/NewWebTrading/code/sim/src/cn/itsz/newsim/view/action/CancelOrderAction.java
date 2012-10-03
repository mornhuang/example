package cn.itsz.newsim.view.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.stereotype.Controller;

import cn.itsz.newsim.common.Constants;
import cn.itsz.newsim.common.MD5;
import cn.itsz.newsim.dto.ResultMessage;
import cn.itsz.newsim.dto.request.CancelOrderRequest;
import cn.itsz.newsim.dto.response.entity.LoginResponseEntity;
import cn.itsz.newsim.view.form.CancelOrderForm;

/**
 * $Id: CancelOrderAction.java,v 1.3 2011/03/05 14:12:32 zxfan Exp $
 * @Project:portal.head
 * @File:CancelOrderAction.java
 * @Description:
 * @Author:zxfan
 * @Date:2007-5-21
 */
@Controller("/webCancelOrder")
public class CancelOrderAction extends BaseAction {

	public ActionForward executeAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		CancelOrderForm cancelOrderForm = (CancelOrderForm) form;
		LoginResponseEntity user = (LoginResponseEntity) request.getSession().getAttribute(Constants.AttributeKey.Session.USER);
		ResultMessage result = new ResultMessage();
		if (Constants.Flag.POSITIVE.equals(user.getTransactionProtection()) &&
			!user.getPassword().equals(MD5.Md5(cancelOrderForm.getPassword()))) {
			result.setReturnCode(Constants.Status.WARN);
			result.setErrorCode(Constants.ErrorKey.NSIM_00010);
		} else {
			CancelOrderRequest model = new CancelOrderRequest();
			model.setOrderId(cancelOrderForm.getOrderId());
			model.setLoginId(user.getLoginId());
			result = facade.cancelOrder(model);
		}
		return viewProcess.process(result, response);
	}
}
