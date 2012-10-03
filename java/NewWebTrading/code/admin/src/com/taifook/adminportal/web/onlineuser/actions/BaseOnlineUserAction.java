package com.taifook.adminportal.web.onlineuser.actions;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.taifook.adminportal.common.BaseAction;
import com.taifook.adminportal.web.onlineuser.forms.OnlineUserForm;

public abstract class BaseOnlineUserAction extends BaseAction {

	public abstract ActionForward executeAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception ;
	
	protected Object[] getFiltersForDB(OnlineUserForm actionForm) {		
		List filterList=new ArrayList();
		
		if (actionForm.getUserid() != null
				&& !actionForm.getUserid().trim().equals("")) {
			filterList.add("UPPER(id.userid) "+getConstraintForDB(actionForm.getUserid(),true));
		}

		if (actionForm.getChannelcode() != null
				&& !actionForm.getChannelcode().trim().equals("")) {
			filterList.add("UPPER(id.channelcode) "+getConstraintForDB(actionForm.getChannelcode(),true));
		}

		if (actionForm.getLogintime() != null
				&& !actionForm.getLogintime().trim().equals("")) {
			filterList.add("to_char(logintime,\'YYYY-MM-DD HH24:MI:SS\') "+getConstraintForDB(actionForm.getLogintime(),true));
		}
		
		return filterList.toArray();
	}
	
	protected String getFiltersForWeb(OnlineUserForm actionForm) {		
		StringBuffer sb=new StringBuffer();
		
		if (actionForm.getUserid() != null
				&& !actionForm.getUserid().trim().equals("")) {
			sb.append("userid="+URLEncoder.encode(actionForm.getUserid())+"&");
		}

		if (actionForm.getChannelcode() != null
				&& !actionForm.getChannelcode().trim().equals("")) {
			sb.append("channelcode="+URLEncoder.encode(actionForm.getChannelcode())+"&");
		}

		if (actionForm.getLogintime() != null
				&& !actionForm.getLogintime().trim().equals("")) {
			sb.append("logintime="+URLEncoder.encode(actionForm.getLogintime())+"&");
		}
		
		return sb.toString();
	}	
}
