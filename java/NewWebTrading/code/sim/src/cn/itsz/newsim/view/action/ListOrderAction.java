package cn.itsz.newsim.view.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.stereotype.Controller;

import cn.itsz.newsim.common.Constants;
import cn.itsz.newsim.dto.ResultMessage;
import cn.itsz.newsim.dto.request.ListOrderRequest;
import cn.itsz.newsim.dto.response.entity.LoginResponseEntity;
import cn.itsz.newsim.view.form.ListOrderForm;

/**
 * $Id: ListOrderAction.java,v 1.2 2011/03/05 14:12:32 zxfan Exp $
 * 
 * @Project:portal.head
 * @File:ListOrderAction.java
 * @Description:
 * @Author:zxfan
 * @Date:2007-5-21
 */
@Controller("/webListOrder")
public class ListOrderAction extends BaseAction {

	public ActionForward executeAction(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		ListOrderRequest model = new ListOrderRequest();
		ListOrderForm orderForm = (ListOrderForm) form;
		if (Integer.valueOf(orderForm.getPageSize()) <= 0) {
			orderForm.setPageSize(new Integer(7).toString());
		}
		LoginResponseEntity user = (LoginResponseEntity) request.getSession().getAttribute(Constants.AttributeKey.Session.USER);
		model.setLoginId(user.getLoginId());
		model.setPageSize(new Integer(orderForm.getPageSize()));
		model.setPageNo(new Integer(orderForm.getPageNo()));
		ResultMessage result = facade.listOrder(model);
		return viewProcess.process(result, response);
	}
}
