package com.itsz.sht.struts.action;

import java.util.Locale;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.itsz.sht.common.Consts;
import com.itsz.sht.common.PropertyConfig;

public class InitLoginAction extends Action {
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String CLV = request.getParameter("CLV");
		if(CLV == null) {
			CLV = "WT25S";
			Cookie[] c = request.getCookies();
			if(c!= null){
				for(int i=0;i<c.length;i++){
					Cookie co = c[i];
					if(Consts.Global.Common.COOKIE_LANGUAGE.equals(co.getName())){
						CLV = co.getValue();
						break;
					}
				}
			}
		}
		HttpSession session = request.getSession();
		//String aas_url = PropertyConfig.getCommonProperty("AAS_URL");
		String aas_url = PropertyConfig.getCommonProperty("BIG5_WEB_AAS_URL");
		String openAccountUrl = PropertyConfig.getCommonProperty("BIG5_WEB_OpenAccountUrl");
		String tradeServiceUrl = PropertyConfig.getCommonProperty("BIG5_WEB_TradeServiceUrl");
		String simTradeUrl = PropertyConfig.getCommonProperty("BIG5_WEB_SimTrade");
		if(CLV.equalsIgnoreCase("WE25S")){
			session.setAttribute(Consts.Global.Common.defaultLocaleAttributeName, Locale.US);
			aas_url = PropertyConfig.getCommonProperty("ENG_WEB_AAS_URL");
			openAccountUrl = PropertyConfig.getCommonProperty("ENG_WEB_OpenAccountUrl");
			tradeServiceUrl = PropertyConfig.getCommonProperty("ENG_WEB_TradeServiceUrl");
			simTradeUrl = PropertyConfig.getCommonProperty("ENG_WEB_SimTrade");
		}else if(CLV.equalsIgnoreCase("WG25S")){
			session.setAttribute(Consts.Global.Common.defaultLocaleAttributeName, Locale.SIMPLIFIED_CHINESE);
			aas_url = PropertyConfig.getCommonProperty("GB_WEB_AAS_URL");
			openAccountUrl = PropertyConfig.getCommonProperty("GB_WEB_OpenAccountUrl");
			tradeServiceUrl = PropertyConfig.getCommonProperty("GB_WEB_TradeServiceUrl");
			simTradeUrl = PropertyConfig.getCommonProperty("GB_WEB_SimTrade");
		}else {
			session.setAttribute(Consts.Global.Common.defaultLocaleAttributeName, Locale.TRADITIONAL_CHINESE);
			aas_url = PropertyConfig.getCommonProperty("BIG5_WEB_AAS_URL");
			openAccountUrl = PropertyConfig.getCommonProperty("BIG5_WEB_OpenAccountUrl");
			tradeServiceUrl = PropertyConfig.getCommonProperty("BIG5_WEB_TradeServiceUrl");
			simTradeUrl = PropertyConfig.getCommonProperty("BIG5_WEB_SimTrade");
			CLV = "WT25S";
		}
		request.setAttribute("AAS_url", aas_url);
		request.setAttribute("openAccountUrl", openAccountUrl);
		request.setAttribute("tradeServiceUrl", tradeServiceUrl);
		request.setAttribute("simTradeUrl", simTradeUrl);
		Cookie cookie = new Cookie(Consts.Global.Common.COOKIE_LANGUAGE, CLV);
		cookie.setMaxAge(Consts.Global.Common.COOKIE_MAX_AGE);
		response.addCookie(cookie);
		session.setAttribute(Consts.Global.Common.CLV_NAME, CLV);
		return mapping.findForward(Consts.Global.Forward.SUCCESS);
	}
}
