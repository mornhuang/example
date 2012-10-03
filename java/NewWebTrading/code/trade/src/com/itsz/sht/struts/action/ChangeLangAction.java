package com.itsz.sht.struts.action;

import java.util.Locale;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.itsz.sht.common.CLVSplitUtil;
import com.itsz.sht.common.Consts;
import com.itsz.sht.common.user.UserObject;
import com.itsz.sht.vp.ViewProvider;
import com.itsz.sht.vp.common.ProcessBean;

public class ChangeLangAction extends ITSZAction {
	@Override
    public ActionForward executeAction(ViewProvider vp ,
        ActionMapping mapping ,
        ActionForm form ,
        HttpServletRequest request ,
        HttpServletResponse response) {
            String clv = request.getParameter(Consts.Global.Common.CLV_NAME);
            if(StringUtils.isBlank(clv)){
            	clv="WT25S";
            }
            String language = CLVSplitUtil.getLanguage(clv);
            HttpSession session = request.getSession();
            UserObject user =  (UserObject) session.getAttribute(Consts.Profile.USER);
            if(user != null){
            	user.setClv(clv);
            }
            if(Consts.Global.Language.GB.equals(language)){
                session.setAttribute(Consts.Global.Common.defaultLocaleAttributeName,Locale.SIMPLIFIED_CHINESE);
                if(user != null){
                    user.setLang(Locale.SIMPLIFIED_CHINESE.toString());
                }
                this.setCookie(request, response, clv);
            }else if(Consts.Global.Language.ENG.equals(language)){
                session.setAttribute(Consts.Global.Common.defaultLocaleAttributeName,Locale.US);
                if(user != null){
                    user.setLang(Locale.US.toString());
                }
                this.setCookie(request, response, clv);
            }else if(Consts.Global.Language.BIG5.equals(language)){
            	session.setAttribute(Consts.Global.Common.defaultLocaleAttributeName,Locale.TRADITIONAL_CHINESE);
            	if(user != null){
                    user.setLang(Locale.TRADITIONAL_CHINESE.toString());
                }
            	this.setCookie(request, response, clv);
            }
            String page = request.getParameter(Consts.Global.Common.CHANGELANG_PAGE_PARAM);
            if(StringUtils.isBlank(page)){
            	page = Consts.Global.Common.LOGIN_PAGE;
            }
            session.setAttribute(Consts.Global.Common.CLV_NAME, clv);
            ProcessBean processBean = new ProcessBean(page, null, mapping, request,response);
            return vp.processChangeLang(processBean);
    }
	
	public void setCookie(HttpServletRequest request,HttpServletResponse response, String clv){
		Cookie[] c = request.getCookies();
		if( c!= null&&c.length>0){
			for(int i=0;i<c.length;i++){
				if(Consts.Global.Common.COOKIE_LANGUAGE.equals(c[i].getName())){
					c[i].setValue(clv);  
					response.addCookie(c[i]);    
					break;
				}
			}
		}else{
			Cookie cookie = new Cookie(Consts.Global.Common.COOKIE_LANGUAGE,clv);
        	cookie.setMaxAge(Consts.Global.Common.COOKIE_MAX_AGE);
        	response.addCookie(cookie);
		}
	}
	
	public boolean isLoginActionExecuted() {
		return true;
	}
}
