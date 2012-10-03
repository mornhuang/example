package cn.itsz.newsim.view.action.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.stereotype.Controller;

import cn.itsz.newsim.common.Constants;
import cn.itsz.newsim.dao.hibernate.model.AdminProfileModel;
import cn.itsz.newsim.dto.ResultMessage;
import cn.itsz.newsim.dto.response.AdminLoginResponse;
import cn.itsz.newsim.view.action.BaseAction;
import cn.itsz.newsim.view.form.AdminLoginForm;

@Controller("/nadmin/adminLogin")
public class AdminLoginAction extends BaseAction {

	@Override
	protected boolean isRequiredLogon() {
		return false;
	}

	@Override
	protected ActionForward executeAction(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		AdminProfileModel model = new AdminProfileModel();
		form2Model(request, form, model, response);
		ResultMessage loginResponse = facade.adminLogin(model);
		if (loginResponse.getReturnCode().equals(Constants.Status.NORMAL)) {
			request.getSession().setAttribute(
					Constants.AttributeKey.Session.ADMIN,
					((AdminLoginResponse) loginResponse)
							.getAdminLoginResponseEntity());
		} else if (loginResponse.getReturnCode()
				.equals(Constants.Status.FAILED)) {
		}
		request.setAttribute("status", loginResponse.getReturnCode());

		return mapping.findForward(loginResponse.getReturnCode());
	}

	public static void form2Model(HttpServletRequest request, ActionForm form,
			AdminProfileModel destModel, HttpServletResponse response) {
		AdminLoginForm model = (AdminLoginForm) form;
		destModel.setUname(model.getUname());
		destModel.setUpass(model.getUpass());
	}
}
