package cn.itsz.newsim.view.action.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.stereotype.Controller;

import cn.itsz.newsim.common.Constants;
import cn.itsz.newsim.dto.request.ParameterRequest;
import cn.itsz.newsim.dto.response.ParameterResponse;
import cn.itsz.newsim.view.action.BaseAction;
import cn.itsz.newsim.view.form.ParameterForm;

@Controller("/nadmin/paramList")
public class ParameterListAction extends BaseAction {
	
	@Override
	protected boolean isRequiredLogon() {
		return false;
	}

	@Override
	protected ActionForward executeAction(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		ParameterRequest model = new ParameterRequest();
		form2Model(request, form, model, response);
		ParameterResponse paramResponse = facade.enquireParameterList(model);
		
		request.setAttribute("paramResponse", paramResponse);
		
		return mapping.findForward(Constants.Status.SUCCESS);
	}
	
	public static void form2Model(HttpServletRequest request,
			ActionForm form, ParameterRequest destModel, HttpServletResponse response) {
		ParameterForm model = (ParameterForm)form;
		destModel.setPageNo(model.getPageNo());
	}
}
