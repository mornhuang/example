package cn.itsz.newsim.view.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.stereotype.Controller;

import cn.itsz.newsim.common.Constants;
import cn.itsz.newsim.dto.ResultMessage;
import cn.itsz.newsim.dto.request.OrderDetailRequest;
import cn.itsz.newsim.dto.response.entity.LoginResponseEntity;
import cn.itsz.newsim.view.form.OrderDetailForm;

@Controller("/webOrderDetail")
public class OrderDetailAction extends BaseAction {

	public ActionForward executeAction(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		OrderDetailRequest model = new OrderDetailRequest();
		LoginResponseEntity user = (LoginResponseEntity) request.getSession().getAttribute(Constants.AttributeKey.Session.USER);
		model.setLoginId(user.getLoginId());
		OrderDetailForm orderDetailForm = (OrderDetailForm) form;
		model.setOrderId(orderDetailForm.getOrderId());
		ResultMessage result = facade.orderDetail(model);
		return viewProcess.process(result, response);
	}
}
