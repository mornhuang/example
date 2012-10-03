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

public class LogoutAction extends Action {

	protected static Log log_info = LogFactory.getLog("commonInfo");
	protected static Log log_debug = LogFactory.getLog("commonInfo");
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		String forward = "success";
		if(session.getAttribute(Constants.LOGIN_ID) == null){
			log_debug
			.info("@@@@@@@@@@@  User not Exist  id=" + session.getId());
			request.setAttribute("result", "message.general.sessionKickedOff");
			forward = "failed";
		}else{
			if(session.getAttribute(Constants.LOGIN_ID)!=null){
	            log_debug.info("=====session==========remove UserObject ");
	            session.removeAttribute(Constants.LOGIN_ID);
	            forward = "success";
	        }
		}
		return mapping.findForward(forward);
	}
}
