package com.taifook.adminportal.web.channels.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.taifook.adminportal.common.BaseAction;
import com.taifook.adminportal.common.Constants;

public class ManagerChannelServerAction extends BaseAction {

	public ActionForward executeAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		String forward = Constants.SUCCESS;

		return mapping.findForward(forward);

	}
}
