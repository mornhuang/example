package cn.itsz.newsim.view.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.stereotype.Controller;

import cn.itsz.newsim.common.Constants;
import cn.itsz.newsim.dto.response.EnquiryAccountResponse;
import cn.itsz.newsim.dto.response.entity.LoginResponseEntity;

@Controller("/webEnquireAccount")
public class EnquiryAccountAction extends BaseAction {

	@Override
	protected ActionForward executeAction(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		LoginResponseEntity user = (LoginResponseEntity) request.getSession().getAttribute(Constants.AttributeKey.Session.USER);
		EnquiryAccountResponse result = new EnquiryAccountResponse();
		result.setAccountId(user.getTradeAccount());
		result.setTotalPurchasingPower(user.getTotalPurchasingPower());
		result.setOnHoldBalance(user.getOnHoldBalance());
		return viewProcess.process(result, response);
	}

}
