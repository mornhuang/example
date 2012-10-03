package com.taifook.adminportal.web.parameter.actions;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.taifook.adminportal.common.BaseAction;
import com.taifook.adminportal.web.parameter.forms.ParameterForm;
import java.net.URLEncoder;

public abstract class BaseParameterAction extends BaseAction {

	public abstract ActionForward executeAction(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception;

	protected Object[] getFiltersForDB(ParameterForm actionForm) {
		List filterList = new ArrayList();

		if (actionForm.getKey() != null
				&& !actionForm.getKey().trim().equals("")) {
			filterList.add("UPPER(key) "+getConstraintForDB(actionForm.getKey(),true));
		}

		if (actionForm.getValue() != null
				&& !actionForm.getValue().trim().equals("")) {
			filterList.add("UPPER(value)  "+getConstraintForDB(actionForm.getValue(),true));
		}

		if (actionForm.getClassid() != null
				&& !actionForm.getClassid().trim().equals("")) {
			filterList.add("UPPER(classid)  "+getConstraintForDB(actionForm.getClassid(),true));
		}

		if (actionForm.getUpdatetime() != null
				&& !actionForm.getUpdatetime().trim().equals("")) {
			filterList.add("to_char(update_time,\'YYYY-MM-DD HH24:MI:SS\')  "+getConstraintForDB(actionForm.getUpdatetime(),true));
		}

		return filterList.toArray();
	}

	public String getFiltersForWeb(ParameterForm actionForm) {
		StringBuffer sb = new StringBuffer();

		if (actionForm.getKey() != null
				&& !actionForm.getKey().trim().equals("")) {
			sb.append("key=" + URLEncoder.encode(actionForm.getKey()) + "&");
		}

		if (actionForm.getValue() != null
				&& !actionForm.getValue().trim().equals("")) {
			sb.append("value=" + URLEncoder.encode(actionForm.getValue()) + "&");
		}

		if (actionForm.getClassid() != null
				&& !actionForm.getClassid().trim().equals("")) {
			sb.append("classid=" + URLEncoder.encode(actionForm.getClassid()) + "&");
		}

		if (actionForm.getUpdatetime() != null
				&& !actionForm.getUpdatetime().trim().equals("")) {
			sb.append("updatetime=" + URLEncoder.encode(actionForm.getUpdatetime()) + "&");
		}

		return sb.toString();
	}
}
