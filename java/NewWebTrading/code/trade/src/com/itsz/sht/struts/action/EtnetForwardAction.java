//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.0.1/xslt/JavaClass.xsl

package com.itsz.sht.struts.action;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.itsz.sht.common.Constants;
import com.itsz.sht.common.PropertyConfig;
import com.itsz.sht.common.model.common.IQLoginProfile;
import com.itsz.sht.common.model.request.EtnetAppletRTQRequest;
import com.itsz.sht.common.util.LangUtil;
import com.itsz.sht.common.util.StreamRtqUtil;
import com.itsz.sht.struts.form.EtnetForwardForm;

/** 
 * MyEclipse Struts
 * Creation date: 10-17-2006
 * 
 * XDoclet definition:
 * @struts.action path="/EtnetForward" name="EtnetForwardForm" scope="request"
 */
public class EtnetForwardAction extends Action {
	private static Log log_debug = LogFactory.getLog(Constants.LOG_DEBUG_RTQ);
	public ActionForward execute(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response) {
		log_debug.info("call EthnetForwardAction execute()");	
		String etnet_enabled = PropertyConfig.getCommonProperty("etnet_enabled");		
		if(StringUtils.isBlank(etnet_enabled)){
			etnet_enabled = "0";
		}
		request.setAttribute("etnet_enabled",etnet_enabled);
		if("1".equals(etnet_enabled)){
			return executeNewVersion(mapping,form,request,response);
		}
		EtnetForwardForm etnetForm = (EtnetForwardForm)form;
		HttpSession session  = request.getSession();
		String action = etnetForm.getRequest();
		String uid = etnetForm.getUid();
		String pwd = (String) session.getAttribute("pwd");
		String url = etnetForm.getUrl();		
		
		String etnet_login_url = PropertyConfig.getCommonProperty(Constants.ETNET_LOGIN_URL);
		StringBuffer elu = new StringBuffer(etnet_login_url);
		elu.append("?request=").append(action);
		elu.append("&uid=").append(uid);
		elu.append("&pwd=").append(pwd);		
		IQLoginProfile iq = StreamRtqUtil.IQObtainKey(elu.toString());		
		if(iq == null || iq.getResult()==null || !"0".equals(iq.getResult().trim())){
			request.setAttribute(Constants.IQ_RESULT,iq.getResult());
			return mapping.findForward("etnet_failure");
		}
		Locale lc = (Locale) request.getSession().getAttribute(Constants.defaultLocaleAttributeName);
		String lang = LangUtil.mcs2iq(LangUtil.locale2lang(lc));
		session.removeAttribute("pwd");
		EtnetAppletRTQRequest etnetAppletRTQRequest = new EtnetAppletRTQRequest();
		etnetAppletRTQRequest.setLang(lang);
		etnetAppletRTQRequest.setPassport(iq.getPassport());
		etnetAppletRTQRequest.setRequest("startIQ");
		etnetAppletRTQRequest.setTheme("IBA_CO");
		etnetAppletRTQRequest.setUid(uid);
		etnetAppletRTQRequest.setUrl(url);
		request.setAttribute("etnetAppletRTQRequest",etnetAppletRTQRequest);
		return mapping.findForward("etnet_post");		
	}
	
	private ActionForward executeNewVersion(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response) {
		EtnetForwardForm etnetForm = (EtnetForwardForm)form;
		HttpSession session  = request.getSession();
		String uid = etnetForm.getUid();
		String Digest = (String) session.getAttribute("Digest");
		String language = etnetForm.getLang();
		String etnet_login_url = PropertyConfig.getCommonProperty(Constants.ETNET_LOGIN_URL);
		StringBuffer elu = new StringBuffer(etnet_login_url);
		elu.append("?Company=taifook");
		elu.append("&Userid=").append(uid);
		elu.append("&Digest=").append(Digest);
		elu.append("&Lang=").append(language);
		elu.append("&StockCode=");
		log_debug.info("get etnet access request url:="+elu.toString());
		String result = StreamRtqUtil.getEtnetAcessResult(elu.toString());
		log_debug.info("get etnet access response:="+result);
		if(StringUtils.isBlank(result)){
			request.setAttribute(Constants.IQ_RESULT,"etnet_errorcode");
			return mapping.findForward("etnet_failure");
		}
		session.removeAttribute("Digest");
		request.setAttribute("result", result);
		return mapping.findForward("etnet_post");
	}
}

