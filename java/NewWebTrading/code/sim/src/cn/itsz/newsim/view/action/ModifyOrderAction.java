package cn.itsz.newsim.view.action;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.stereotype.Controller;

import cn.itsz.newsim.common.Constants;
import cn.itsz.newsim.common.MD5;
import cn.itsz.newsim.dto.ResultMessage;
import cn.itsz.newsim.dto.request.ModfiyOrderRequest;
import cn.itsz.newsim.dto.response.entity.LoginResponseEntity;
import cn.itsz.newsim.view.form.ModifyOrderForm;

/**
 * $Id: ModifyOrderAction.java,v 1.2 2011/03/05 14:12:32 zxfan Exp $
 * 
 * @Project:portal.head
 * @File:ModifyOrderAction.java
 * @Description:
 * @Author:Cimenon Saint
 * @Date:2007-5-21
 */
@Controller("/webModifyOrder")
public class ModifyOrderAction extends BaseAction {

	public ActionForward executeAction(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		ModifyOrderForm modifyOrderForm = (ModifyOrderForm) form;
		ModfiyOrderRequest model = new ModfiyOrderRequest();
		LoginResponseEntity user = (LoginResponseEntity) request.getSession().getAttribute(Constants.AttributeKey.Session.USER);
		ResultMessage result = new ResultMessage();
		if (Constants.Flag.POSITIVE.equals(user.getTransactionProtection()) &&
			!user.getPassword().equals(MD5.Md5(modifyOrderForm.getPassword()))) {
			result.setReturnCode(Constants.Status.WARN);
			result.setErrorCode(Constants.ErrorKey.NSIM_00010);
		} else {
			model.setLoginId(user.getLoginId());
			model.setOrderId(modifyOrderForm.getOrderId());
			model.setNewOrderPrice(new BigDecimal(modifyOrderForm.getNewOrderPrice()));
			model.setNewOrderQty(new BigDecimal(modifyOrderForm.getNewOrderQty()));
			result = facade.modifyOrder(model);
		}
		return viewProcess.process(result, response);
	}
}
