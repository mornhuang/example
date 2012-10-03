package com.taifook.adminportal.web.systemmonitor.actions;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.taifook.adminportal.common.BaseAction;
import com.taifook.adminportal.common.Constants;
import com.taifook.adminportal.common.ServiceManager;
import com.taifook.adminportal.common.util.page.Page;
import com.taifook.adminportal.dao.SystemMonitorDAO;
import com.taifook.adminportal.web.systemmonitor.forms.SystemMonitorForm;

public class QuerySystemMonitorAction extends BaseSystemMonitorAction {
	
	public ActionForward executeAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try{
			SystemMonitorForm actionForm=(SystemMonitorForm)form;
			 Object[] objects=this.getFiltersForDB(actionForm);
		     int pageNumber=getPageNumber(request);   //get current page number
	         int pageSize=getPageSize();  
	         
	         Page page = ((SystemMonitorDAO) ServiceManager.getInstance().getService(
					"com.taifook.adminportal.service.SystemMonitorService"))
					.findByPage(objects,pageNumber,pageSize);
	         HttpSession session=request.getSession();
	         session.removeAttribute(Constants.RESULT);
	         session.setAttribute(Constants.RESULT,page);
	         request.setAttribute(Constants.FILTER,actionForm);
	         request.setAttribute(Constants.URL_PARAMETER,this.getFiltersForWeb(actionForm));
	         
	         return mapping.findForward(Constants.SUCCESS);
		}catch(Exception e){
			e.printStackTrace();
			request.setAttribute(Constants.GLOBAL_ERROR,"query system monitor detail fail! "+e.getMessage());
			return mapping.findForward(Constants.FAILURE);
		}
	}
	

	
}	