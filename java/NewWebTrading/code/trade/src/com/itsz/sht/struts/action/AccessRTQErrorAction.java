package com.itsz.sht.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.itsz.sht.vp.ViewProvider;

/**
 * $Id: AccessRTQErrorAction.java,v 1.1 2011/04/12 07:32:27 cyzeng Exp $
 * @Project:NewWebTrading
 * @File:AccessRTQAction.java
 * @Description:
 * @Author:kyzou
 * @Date:2010-12-16
 */
public class AccessRTQErrorAction extends ITSZAction {

	@Override
	public ActionForward executeAction(ViewProvider vp, ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		String error = request.getParameter("error");
		String productId = request.getParameter("productId");
		request.setAttribute("error", error);
		request.setAttribute("productId", productId);
		return mapping.findForward("exception");
	}
	
}
