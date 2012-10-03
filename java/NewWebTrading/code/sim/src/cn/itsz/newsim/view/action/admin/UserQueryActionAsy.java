package cn.itsz.newsim.view.action.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.stereotype.Controller;

import cn.itsz.newsim.dto.ResultMessage;
import cn.itsz.newsim.dto.request.UserProfileRequest;
import cn.itsz.newsim.view.action.BaseAction;
import cn.itsz.newsim.view.form.RegForm;

@Controller("/nadmin/userQueryAsy")
public class UserQueryActionAsy extends BaseAction {
	
	@Override
	protected boolean isRequiredLogon() {
		return false;
	}

	public ActionForward executeAction(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		UserProfileRequest model = new UserProfileRequest();
		form2Model(request, form, model, response);
		ResultMessage result = facade.enquireUserProfileAsy(model.getLoginId());
		return viewProcess.process(result, response);
	}
	
	public static void form2Model(HttpServletRequest request,
			ActionForm form, UserProfileRequest destModel, HttpServletResponse response) {
		RegForm model = (RegForm)form;
		destModel.setLoginId(model.getLoginId());
	}
}
