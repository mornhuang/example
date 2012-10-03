package cn.itsz.newsim.view.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.stereotype.Controller;

import cn.itsz.newsim.common.Constants;
import cn.itsz.newsim.dto.response.entity.LoginResponseEntity;
import cn.itsz.newsim.manage.WebActiveUserManager;

@Controller("/logout")
public class LogoutAction extends BaseAction {

	@Override
	protected boolean isRequiredLogon() {
		return false;
	}

	@Override
	protected ActionForward executeAction(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		LoginResponseEntity entity = (LoginResponseEntity) request.getSession().getAttribute(Constants.AttributeKey.Session.USER);
		if (entity != null) {
			WebActiveUserManager.getInstance().removeUser(entity.getLoginId(),request.getSession().getId());
		}
		request.getSession().removeAttribute(Constants.AttributeKey.Session.USER);

		return mapping.findForward(Constants.Status.NORMAL);

	}

}
