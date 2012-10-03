package com.itsz.web.rtq.action;

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

public class LoginAction extends Action {

	protected static Log log_info = LogFactory.getLog("commonInfo");
	protected static Log log_debug = LogFactory.getLog("commonInfo");
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
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
		HttpSession session = request.getSession();
		session.setAttribute(Constants.LOGIN_ID,loginId);
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
		return mapping.findForward("success");
	}

}
