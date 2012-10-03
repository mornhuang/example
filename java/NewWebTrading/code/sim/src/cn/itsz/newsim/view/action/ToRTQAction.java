package cn.itsz.newsim.view.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.stereotype.Controller;

import cn.itsz.newsim.common.Constants;

@Controller("/webRTQ")
public class ToRTQAction extends BaseAction {
	@Override
	protected boolean isRequiredLogon() {
		return false;
	}
	
	@Override
	protected ActionForward executeAction(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		return mapping.findForward(Constants.Status.SUCCESS);
	}
}
