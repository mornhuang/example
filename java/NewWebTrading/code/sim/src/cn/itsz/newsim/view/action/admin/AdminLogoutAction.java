package cn.itsz.newsim.view.action.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.stereotype.Controller;

import cn.itsz.newsim.common.Constants;
import cn.itsz.newsim.view.action.BaseAction;

@Controller("/nadmin/adminLogout")
public class AdminLogoutAction extends BaseAction {

	@Override
	protected boolean isRequiredLogon() {
		return false;
	}

	@Override
	protected ActionForward executeAction(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		request.getSession().removeAttribute(Constants.AttributeKey.Session.ADMIN);

		return mapping.findForward(Constants.Status.NORMAL);

	}

}
