package cn.itsz.newsim.view.action;

import java.util.Locale;

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
import cn.itsz.newsim.view.form.BaseForm;

@Controller("/changeLang")
public class ChangeLangAction extends BaseAction {

	
	@Override
	protected boolean isRequiredLogon() {
		return false;
	}

	@Override
	protected ActionForward executeAction(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		String clv = ((BaseForm)form).getCLV();
		String language = LanguageUtil.converCLV(clv);
        
        HttpSession session = request.getSession();

        if(Constants.Lang.GB.equals(language)){
            session.setAttribute(Constants.Common.defaultLocaleAttributeName,Locale.SIMPLIFIED_CHINESE);
        }else if(Constants.Lang.ENG.equals(language)){
            session.setAttribute(Constants.Common.defaultLocaleAttributeName,Locale.US);
        }else if(Constants.Lang.BIG5.equals(language)){
        	session.setAttribute(Constants.Common.defaultLocaleAttributeName,Locale.TRADITIONAL_CHINESE);
        }
        LanguageUtil.saveCookie(request, response, clv);
        String page = request.getParameter(Constants.Common.CHANGELANG_PAGE_PARAM);
        if(StringUtils.isBlank(page)){
        	page = Constants.Common.LOGIN_PAGE;
        }
        session.setAttribute(Constants.Common.CLV_NAME, clv);
        return mapping.findForward(page);
	}
	
}
