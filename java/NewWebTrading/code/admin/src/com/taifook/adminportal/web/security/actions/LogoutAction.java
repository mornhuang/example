package com.taifook.adminportal.web.security.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.taifook.adminportal.common.BaseAction;
import com.taifook.adminportal.common.Constants;
import com.taifook.adminportal.common.ServiceManager;
import com.taifook.adminportal.dao.SecurityDAO;
import com.taifook.adminportal.proxy.ProxyAdminPortalDAO;
import com.taifook.adminportal.proxy.impl.ProxyAdminportalImpl;

/**
 * <p> * Title: admin_portal *
 * </p>
 * <p> * Description: *
 * </p>
 * <p> * Copyright: Copyright (c) 2006 *
 * </p>
 * <p> * Company: TaiFook itsz *
 * </p>
 * 
 * @author hsli
 * @version 1.0
 */
public final class LogoutAction extends BaseAction {

	public ActionForward executeAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		boolean isSucess = false;
		isSucess = ((SecurityDAO) ServiceManager.getInstance().getService(
				"com.taifook.adminportal.service.SecurityService"))
				.userLogout(session);
		if (isSucess) {
			return mapping.findForward(Constants.SUCCESS);
		} else {
			return mapping.findForward(Constants.FAILURE);
		}
	}

}
