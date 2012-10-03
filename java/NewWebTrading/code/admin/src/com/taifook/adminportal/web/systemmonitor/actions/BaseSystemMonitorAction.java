package com.taifook.adminportal.web.systemmonitor.actions;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.taifook.adminportal.common.BaseAction;
import com.taifook.adminportal.web.systemmonitor.forms.SystemMonitorForm;

public abstract class BaseSystemMonitorAction extends BaseAction {

	public abstract ActionForward executeAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception ;
	
	protected Object[] getFiltersForDB(SystemMonitorForm actionForm) {		
		List filterList=new ArrayList();
		
		if (actionForm.getServicename() != null
				&& !actionForm.getServicename().trim().equals("")) {
			filterList.add("UPPER(servicename) "+this.getConstraintForDB(actionForm.getServicename(),true));
		}

		if (actionForm.getStatus() != null
				&& !actionForm.getStatus().trim().equals("")) {
			filterList.add("UPPER(status) "+getConstraintForDB(actionForm.getStatus(),true));
		}

		if (actionForm.getIp() != null
				&& !actionForm.getIp().trim().equals("")) {
			filterList.add("UPPER(ip) "+getConstraintForDB(actionForm.getIp(),true));
		}
		
		if (actionForm.getActionId() != null
				&& !actionForm.getActionId().trim().equals("")) {
			filterList.add("UPPER(actionId) "+getConstraintForDB(actionForm.getActionId(),true));
		}
		
		if (actionForm.getAccesstime() != null
				&& !actionForm.getAccesstime().trim().equals("")) {
			filterList.add("to_char(accesstime,\'YYYY-MM-DD HH24:MI:SS\') "+ getConstraintForDB(actionForm.getAccesstime(),true));
		}
		
		if (actionForm.getExpendtime() != null
				&& !actionForm.getExpendtime().trim().equals("")) {
			String expendTime=actionForm.getExpendtime().trim();			
			filterList.add("expendtime "+getConstraintForDB(actionForm.getExpendtime(),false));
		}		
		
		return filterList.toArray();
	}

	protected String getFiltersForWeb(SystemMonitorForm actionForm) {		
		StringBuffer sb=new StringBuffer();
		
		if (actionForm.getServicename() != null
				&& !actionForm.getServicename().trim().equals("")) {
			sb.append("servicename="+URLEncoder.encode(actionForm.getServicename())+"&");
		}

		if (actionForm.getStatus() != null
				&& !actionForm.getStatus().trim().equals("")) {
			sb.append("status="+URLEncoder.encode(actionForm.getStatus())+"&");
		}

		if (actionForm.getIp() != null
				&& !actionForm.getIp().trim().equals("")) {
			sb.append("ip="+URLEncoder.encode(actionForm.getIp())+"&");
		}
		
		if (actionForm.getActionId() != null
				&& !actionForm.getActionId().trim().equals("")) {
			sb.append("actionId="+URLEncoder.encode(actionForm.getActionId())+"&");
		}
		
		if (actionForm.getAccesstime() != null
				&& !actionForm.getAccesstime().trim().equals("")) {
			sb.append("accesstime="+URLEncoder.encode(actionForm.getAccesstime())+"&");
		}
		
		if (actionForm.getExpendtime() != null
				&& !actionForm.getExpendtime().trim().equals("")) {
			sb.append("expendtime="+URLEncoder.encode(actionForm.getExpendtime())+"&");
		}		
		
		return sb.toString();
	}
	

}
