package cn.itsz.newsim.view.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.stereotype.Controller;

import cn.itsz.newsim.common.Constants;
import cn.itsz.newsim.common.LanguageUtil;
import cn.itsz.newsim.dto.ResultMessage;
import cn.itsz.newsim.dto.request.LoginRequest;
import cn.itsz.newsim.dto.response.LoginResponse;
import cn.itsz.newsim.dto.response.entity.LoginResponseEntity;
import cn.itsz.newsim.manage.WebActiveUserManager;
import cn.itsz.newsim.view.form.LoginForm;


@Controller("/webLogin")
public class LoginAction extends BaseAction {

	@Override
	protected boolean isRequiredLogon() {
		return false;
	}
	@Override
	protected ActionForward executeAction(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		LoginRequest loginRequest = new LoginRequest();
		form2Model(request,form, loginRequest, response);
		if(StringUtils.isNotBlank(loginRequest.getLanguage())) {
			request.getSession().setAttribute(Constants.Common.defaultLocaleAttributeName,LanguageUtil.lang2locale(loginRequest.getLanguage()));
		}
		ResultMessage loginResponse =facade.login(loginRequest);
		if(loginResponse.getReturnCode().equals(Constants.Status.NORMAL)){
			request.getSession().setAttribute(Constants.AttributeKey.Session.USER,((LoginResponse)loginResponse).getLoginResponseEntity());
			request.setAttribute("resultStatus", loginResponse.getReturnCode());
			LoginResponseEntity entity = ((LoginResponse)loginResponse).getLoginResponseEntity();
			WebActiveUserManager.getInstance().put(entity.getLoginId(), request.getSession().getId(),entity);
		}else if(loginResponse.getReturnCode().equals(Constants.Status.FAILED)){
			request.setAttribute("resultStatus", loginResponse.getReturnCode());
		}
		/*LoginResponse loginResponse = new LoginResponse();
		LoginResponseEntity entity = new LoginResponseEntity();
		entity.setChannelId("NSIM");
		entity.setLoginId("0042038");
		entity.setLoginStatus("Y");
		entity.setTradeAccount("02-0042038-30");
		entity.setAllowTradeStatusFlag("Y");
		entity.setTransactionProtection("Y");
		entity.setPassword("670b14728ad9902aecba32e22fa4f6bd");
		request.getSession().setAttribute(Constants.AttributeKey.Session.USER, entity);
		*/
		
		return mapping.findForward(loginResponse.getReturnCode());
	}
	
	public static void form2Model(HttpServletRequest request,
			ActionForm form, LoginRequest destModel, HttpServletResponse response) {
		destModel.setLoginId(((LoginForm)form).getLoginId());
		destModel.setPassword(((LoginForm)form).getPassWord());
	}
}
