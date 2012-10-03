package com.itsz.sht.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.itsz.sht.common.CLVSplitUtil;
import com.itsz.sht.common.Consts;
import com.itsz.sht.common.LoggerFactory;
import com.itsz.sht.common.PropertyConfig;
import com.itsz.sht.common.user.AHCPResponse;
import com.itsz.sht.exception.ITSZException;

/**
 * $Id: AHSubmitAction.java,v 1.2 2011/04/08 05:56:14 pbxie Exp $
 * @Project:portal
 * @File:AHSubmitAction.java
 * @Description:
 * @Author:kyzou
 * @Date:2008-12-30
 */
public class AHSubmitAction extends Action{
	Log mcsInfo = LoggerFactory.getInstance().getMcsInfo();
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {	
		String clv = request.getParameter("CLV");
		try {
	        ITSZAction.sessionManagement(request);
	    } catch (ITSZException e) {
	    	mcsInfo.info("sesssion time out");
	        request.setAttribute("CLV",clv);
	        return mapping.findForward("epayment_session_overtime");
	    }
		String language = translateLang(CLVSplitUtil.getLanguage(clv));
		String url = PropertyConfig.getProviderName(Consts.Ps.AHQuote.AHURL);
		String ahLoginId = PropertyConfig.getProviderName(Consts.Ps.AHQuote.AHLoginId);
		String ahPwd = PropertyConfig.getProviderName(Consts.Ps.AHQuote.AHPwd);
		AHCPResponse ahcpRes = new AHCPResponse(url,ahLoginId,ahPwd,language);
		mcsInfo.info("+++++++++++++++post to "+ahcpRes.toString());
		request.setAttribute("ahcpRes", ahcpRes);
		if(ahcpRes.invalidParam()){
			return mapping.findForward("failure");
		}
		return mapping.findForward("success");
	}
	
	private String translateLang(String lang){
		if(Consts.Global.Language.ENG.equals(lang)){
			return "Eng";
		}else if(Consts.Global.Language.GB.equals(lang)){
			return "GB";
		}else{
			return "Big5";
		}
	}
}
