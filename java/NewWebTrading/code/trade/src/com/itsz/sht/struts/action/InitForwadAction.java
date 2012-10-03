package com.itsz.sht.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.itsz.sht.vp.ViewProvider;

/**
 * $Id: InitForwadAction.java,v 1.1 2010/12/01 06:33:14 kyzou Exp $
 * @Project:portal
 * @File:InitAction.java
 * @Description:
 * @Author:kyzou
 * @Date:2008-3-26
 */
public class InitForwadAction extends ITSZAction {

	@Override
	public ActionForward executeAction(ViewProvider vp, ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
			return mapping.findForward(request.getParameter("forward"));
	}
}
