package com.taifook.adminportal.web.systemmonitor.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.taifook.adminportal.common.Constants;
import com.taifook.adminportal.common.ServiceManager;
import com.taifook.adminportal.dao.SystemMonitorDAO;
import com.taifook.adminportal.web.systemmonitor.forms.SystemMonitorForm;

public class DeleteSystemMonitorAction extends BaseSystemMonitorAction {

	public ActionForward executeAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Log log = LogFactory.getLog(this.getClass());
		String forward = Constants.SUCCESS;
		boolean isSuccess = false;
		SystemMonitorForm actionForm=(SystemMonitorForm)form ;
		String[] params = request.getParameterValues("key");
		String deleteAll = request.getParameter("deleteAll");

		if (deleteAll != null && Boolean.valueOf(deleteAll).booleanValue()) {
			isSuccess = ((SystemMonitorDAO) (ServiceManager.getInstance()
					.getService("com.taifook.adminportal.service.SystemMonitorService")))
					.deleteAll();
		}
		if (params != null && params.length > 0) {
			isSuccess = ((SystemMonitorDAO) (ServiceManager.getInstance()
					.getService("com.taifook.adminportal.service.SystemMonitorService")))
					.deleteByKey(params);
			
		}		
		if (!isSuccess) {
			request.setAttribute(Constants.GLOBAL_ERROR,"delete system monitor detail fail! ");
			forward = Constants.FAILURE;
		} else {
			forward = Constants.SUCCESS;
		}
		
		request.setAttribute(Constants.RETURN_BACK_URL,"../systemmonitor.do?"+this.getFiltersForWeb(actionForm));

		return mapping.findForward(forward);
	}
}
