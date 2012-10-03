package com.taifook.adminportal.web.useraction.actions;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.taifook.adminportal.common.BaseAction;
import com.taifook.adminportal.web.useraction.forms.UserActionForm;

public abstract class BaseUserActionAction extends BaseAction {

	public abstract ActionForward executeAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception ;
	
	protected Object[] getFiltersForDB(UserActionForm actionForm) {
		List filterList = new ArrayList();

		if (actionForm.getUserid() != null
				&& !actionForm.getUserid().trim().equals("")) {
			filterList.add("UPPER(userid) "+getConstraintForDB(actionForm.getUserid(),true));
		}

		if (actionForm.getActionid() != null
				&& !actionForm.getActionid().trim().equals("")) {
			filterList.add("UPPER(actionid) "+getConstraintForDB(actionForm.getActionid(),true));
		}
		
		if (actionForm.getChannelType() != null && !actionForm.getChannelType().trim().equals("")) {
			filterList.add("UPPER(channelType) "+getConstraintForDB(actionForm.getChannelType(),true));
		}		

		if (actionForm.getIp() != null && !actionForm.getIp().trim().equals("")) {
			filterList.add("UPPER(ip) "+getConstraintForDB(actionForm.getIp(),true));
		}

		if (actionForm.getOperationtime() != null
				&& !actionForm.getOperationtime().trim().equals("")) {
			filterList.add("to_char(operationtime,\'YYYY-MM-DD HH24:MI:SS\') "+ getConstraintForDB(actionForm.getOperationtime(),true));
		}

		return filterList.toArray();
	}

	protected String getFiltersForWeb(UserActionForm actionForm) {
		StringBuffer sb = new StringBuffer();

		if (actionForm.getUserid() != null
				&& !actionForm.getUserid().trim().equals("")) {
			sb.append("userid="+URLEncoder.encode(actionForm.getUserid())+"&");
		}

		if (actionForm.getActionid() != null
				&& !actionForm.getActionid().trim().equals("")) {
			sb.append("actionid="+URLEncoder.encode(actionForm.getActionid())+"&");
		}

		if (actionForm.getChannelType() != null && !actionForm.getChannelType().trim().equals("")) {
			sb.append("channelType="+URLEncoder.encode(actionForm.getChannelType())+"&");
		}
		
		if (actionForm.getIp() != null && !actionForm.getIp().trim().equals("")) {
			sb.append("ip="+URLEncoder.encode(actionForm.getIp())+"&");
		}

		if (actionForm.getOperationtime() != null
				&& !actionForm.getOperationtime().trim().equals("")) {
			sb.append("operationtime="+URLEncoder.encode(actionForm.getOperationtime())+"&");
		}

		return sb.toString();
	}
}
