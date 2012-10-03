package cn.itsz.newsim.view.action.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.stereotype.Controller;

import cn.itsz.newsim.common.Constants;
import cn.itsz.newsim.dao.hibernate.model.ParameterModel;
import cn.itsz.newsim.view.action.BaseAction;
import cn.itsz.newsim.view.form.ParameterForm;

@Controller("/nadmin/paramQuery")
public class ParameterQueryAction extends BaseAction {
	
	@Override
	protected boolean isRequiredLogon() {
		return false;
	}

	@Override
	protected ActionForward executeAction(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		
		ParameterModel model = new ParameterModel();
		form2Model(request, form, model, response);
		model = facade.enquireParameter(model.getPname());
		request.setAttribute("paramReponse", model);
		
		return mapping.findForward(Constants.Status.SUCCESS);
	}
	
	public static void form2Model(HttpServletRequest request,
			ActionForm form, ParameterModel destModel, HttpServletResponse response) {
		ParameterForm model = (ParameterForm)form;
		destModel.setPname(model.getPname());
	}
}
