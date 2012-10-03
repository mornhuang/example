package cn.itsz.newsim.view.action;

import java.util.Locale;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.stereotype.Controller;

import cn.itsz.newsim.common.Constants;
import cn.itsz.newsim.common.LanguageUtil;

@Controller("/index")
public class InitLoginAction extends BaseAction {
	@Override
	protected boolean isRequiredLogon() {
		return false;
	}
	
	@Override
	protected ActionForward executeAction(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		String CLV = request.getParameter("CLV");
		if (StringUtils.isBlank(CLV)) {
			CLV = LanguageUtil.cookie2CLV(request);
		}
		HttpSession session = request.getSession();
		if(CLV.equalsIgnoreCase("WE25S")){
			session.setAttribute(Constants.Common.defaultLocaleAttributeName, Locale.US);
			
		}else if(CLV.equalsIgnoreCase("WG25S")){
			session.setAttribute(Constants.Common.defaultLocaleAttributeName, Locale.SIMPLIFIED_CHINESE);
		
		}else {
			session.setAttribute(Constants.Common.defaultLocaleAttributeName, Locale.TRADITIONAL_CHINESE);
			CLV = "WT25S";
		}
		Cookie cookie = new Cookie(Constants.Common.COOKIE_LANGUAGE, CLV);
		cookie.setMaxAge(Constants.Common.COOKIE_MAX_AGE);
		response.addCookie(cookie);
		session.setAttribute(Constants.Common.CLV_NAME, CLV);
		return mapping.findForward(Constants.Status.SUCCESS);
	}
}
