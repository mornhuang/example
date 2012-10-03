package cn.itsz.newsim.view.action.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.stereotype.Controller;

import cn.itsz.newsim.common.Constants;
import cn.itsz.newsim.dto.request.AdminChangePwdRequest;
import cn.itsz.newsim.view.action.BaseAction;
import cn.itsz.newsim.view.form.AdminChangePwdForm;

@Controller("/nadmin/adminChangePwd")
public class AdminChangePwdAction extends BaseAction {
	
	@Override
	protected boolean isRequiredLogon() {
		return false;
	}

	@Override
	protected ActionForward executeAction(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		
		AdminChangePwdRequest model = new AdminChangePwdRequest();
		form2Model(request, form, model, response);
		boolean b = facade.adminChangePwd(model);
		String status = b? Constants.Status.SUCCESS:Constants.Status.FAILED;
		request.setAttribute("status", status);
		return mapping.findForward(status);
	}
	
	public static void form2Model(HttpServletRequest request,
			ActionForm form, AdminChangePwdRequest destModel, HttpServletResponse response) {
		AdminChangePwdForm model = (AdminChangePwdForm)form;
		destModel.setUname(model.getUname());
		destModel.setUpass(model.getUpass());
		destModel.setNewUpass(model.getNewUpass());
	}
}
