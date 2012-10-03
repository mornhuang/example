package cn.itsz.newsim.view.action.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.stereotype.Controller;

import cn.itsz.newsim.common.Constants;
import cn.itsz.newsim.dto.request.SearchRequest;
import cn.itsz.newsim.dto.response.UserProfileResponse;
import cn.itsz.newsim.view.action.BaseAction;
import cn.itsz.newsim.view.form.SearchForm;

@Controller("/nadmin/userSearch")
public class UserSearchAction extends BaseAction {
	
	@Override
	protected boolean isRequiredLogon() {
		return false;
	}

	@Override
	protected ActionForward executeAction(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		SearchRequest searchRequest = new SearchRequest();
		form2Model(request, form, searchRequest, response);
		UserProfileResponse upResponse = facade.searchUserProfileList(searchRequest);
		
		request.setAttribute("userResponse", upResponse);
		
		return mapping.findForward(Constants.Status.SUCCESS);
	}
	
	public static void form2Model(HttpServletRequest request,
			ActionForm form, SearchRequest destModel, HttpServletResponse response) {
		SearchForm model = (SearchForm)form;
		destModel.setKeyword(model.getKeyword());
		destModel.setPageNo(model.getPageNo());
	}
}
