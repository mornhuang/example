package com.itsz.sht.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.itsz.sht.vp.ViewProvider;

/**
 * 
 * $Id: CommonLinkAction.java,v 1.3 2011/01/10 09:53:15 xlliu Exp $
 * @Project:portal
 * @File:CommonLinkAction.java
 * @Description:
 * @Author:clyao
 * @Date:2008-4-02
 */
public class CommonLinkAction extends ITSZAction {
	
	public ActionForward executeAction(ViewProvider vp, ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		String forward = request.getParameter("forward");
		log_info.info("commonLink forward:" + forward);
	    return mapping.findForward(forward);
	}

	@Override
	public boolean isLoginActionExecuted() {
		// TODO Auto-generated method stub
		return true;
	}
	
}
