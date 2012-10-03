package com.itsz.web.rtq.action;

import java.util.Locale;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class InitLoginAction extends Action {

	protected static Log log_info = LogFactory.getLog("commonInfo");
	protected static Log log_debug = LogFactory.getLog("commonInfo");
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String Language = request.getParameter("CLV");
		if(Language == null) {
			Language = "C";
			Cookie[] c = request.getCookies();
			if(c!= null){
				for(int i=0;i<c.length;i++){
					Cookie co = c[i];
					if("Language".equals(co.getName())){
						Language = co.getValue();
						break;
					}
				}
			}
		}
		HttpSession session = request.getSession();
		//String aas_url = PropertyConfig.getCommonProperty("AAS_URL");
		if(Language.equalsIgnoreCase("E")){
			session.setAttribute("org.apache.struts.action.LOCALE", Locale.US);
		}else if(Language.equalsIgnoreCase("GB")){
			session.setAttribute("org.apache.struts.action.LOCALE", Locale.SIMPLIFIED_CHINESE);
		}else {
			session.setAttribute("org.apache.struts.action.LOCALE", Locale.TRADITIONAL_CHINESE);
			Language = "C";
		}
		Cookie cookie = new Cookie("Language", Language);
		cookie.setMaxAge(60*60*24*30);
		response.addCookie(cookie);
		request.setAttribute("language", Language);
		return mapping.findForward("success");
	}

}
