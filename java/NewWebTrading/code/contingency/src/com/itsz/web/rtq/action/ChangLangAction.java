package com.itsz.web.rtq.action;

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

import com.itsz.common.Constants;

public class ChangLangAction extends Action {

	protected static Log log_info = LogFactory.getLog("commonInfo");
	protected static Log log_debug = LogFactory.getLog("commonInfo");
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		String forward = "";
		if(session.getAttribute(Constants.LOGIN_ID) == null){
			log_debug
			.info("@@@@@@@@@@@  User not Exist  id=" + session.getId());
			request.setAttribute("result", "message.general.sessionKickedOff");
			forward = "failed";
		}else{
			String loginId = request.getParameter("loginID");
			String Language = request.getParameter("Language");
			String custCode = request.getParameter("custCode");
			String rtqChnlUsrID = request.getParameter("RTQChnlUsrID");
			String rtqChnlUsrPwd = request.getParameter("RTQChnlUsrPwd");
			String quoteUrl = request.getParameter("quoteUrl");
			String rtqChnlName = request.getParameter("RTQChnlName");
			String RTQPrimalURL=request.getParameter("RTQPrimalURL");
			String RTQStatus=request.getParameter("RTQStatus");
			String Status = request.getParameter("Status");
			String ProdStatus = request.getParameter("ProdStatus");
			String RTQProdName = request.getParameter("RTQProdName");
			request.setAttribute("loginID", loginId);
			request.setAttribute("Language", Language);
			request.setAttribute("custCode", custCode);
			request.setAttribute("RTQChnlUsrID", rtqChnlUsrID);
			request.setAttribute("RTQChnlUsrPwd", rtqChnlUsrPwd);
			request.setAttribute("quoteUrl", quoteUrl);
			request.setAttribute("RTQChnlName", rtqChnlName);
			request.setAttribute("RTQPrimalURL", RTQPrimalURL);
			request.setAttribute("RTQStatus", RTQStatus);
			request.setAttribute("Status", Status);
			request.setAttribute("ProdStatus", ProdStatus);
			request.setAttribute("RTQProdName", RTQProdName);
			if(Language.equals("C")){
				Cookie cookie = new Cookie("Language",Language);
                cookie.setMaxAge(60*60*24*30);
                response.addCookie(cookie);
			}else if(Language.equals("GB")){
				Cookie cookie = new Cookie("Language",Language);
                cookie.setMaxAge(60*60*24*30);
                response.addCookie(cookie);
			}else if(Language.equals("E")){
				Cookie cookie = new Cookie("Language",Language);
                cookie.setMaxAge(60*60*24*30);
                response.addCookie(cookie);
			}
			forward = "success";
		}
		return mapping.findForward(forward);
	}

}
