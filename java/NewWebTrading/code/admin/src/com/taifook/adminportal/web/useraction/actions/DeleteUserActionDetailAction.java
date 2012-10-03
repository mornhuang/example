package com.taifook.adminportal.web.useraction.actions;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.taifook.adminportal.common.Constants;
import com.taifook.adminportal.common.ServiceManager;
import com.taifook.adminportal.dao.UserActionDAO;
import com.taifook.adminportal.web.useraction.forms.UserActionForm;

public class DeleteUserActionDetailAction extends BaseUserActionAction {

	public ActionForward executeAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
//		System.out.println("execute delete user actin log......");
		Log log = LogFactory.getLog(this.getClass());
		String forward = Constants.SUCCESS;
		boolean isSuccess = false;
		UserActionForm actionForm = (UserActionForm) form;
		String[] params = request.getParameterValues("seqno");
		String deleteAll = request.getParameter("deleteAll");

		if (deleteAll != null && Boolean.valueOf(deleteAll).booleanValue()) {
			isSuccess = ((UserActionDAO) (ServiceManager.getInstance()
					.getService("com.taifook.adminportal.service.UserActionService")))
					.deleteAll();
		} else {
			if (params != null && params.length > 0) {
				isSuccess = ((UserActionDAO) (ServiceManager.getInstance()
						.getService("com.taifook.adminportal.service.UserActionService")))
						.deleteByKey(params);
			}
		}

		if (!isSuccess) {
			request.setAttribute(Constants.GLOBAL_ERROR,
					"delete user action detail fail! ");
			forward = Constants.FAILURE;
		} else {
			request.setAttribute(Constants.RETURN_BACK_URL,
					"../useractiondetail.do");
			forward = Constants.SUCCESS;
		}

		request.setAttribute(Constants.RETURN_BACK_URL,
				"../useractiondetail.do?" + this.getFiltersForWeb(actionForm));

		return mapping.findForward(forward);
	}

}
