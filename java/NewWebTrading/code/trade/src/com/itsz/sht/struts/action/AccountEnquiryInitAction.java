package com.itsz.sht.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.itsz.sht.vp.ViewProvider;
import com.itsz.sht.vp.common.ProcessBean;

public class AccountEnquiryInitAction extends ITSZAction {

	public ActionForward executeAction(ViewProvider vp, ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		ProcessBean processBean = new ProcessBean(null, null, mapping, request, response);
		return vp.processAccountEnquiryInit(processBean);
	}
}
