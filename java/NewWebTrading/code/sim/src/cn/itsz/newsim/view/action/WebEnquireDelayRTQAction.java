package cn.itsz.newsim.view.action;

import java.util.Locale;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.stereotype.Controller;

import cn.itsz.newsim.common.ActionUtil;
import cn.itsz.newsim.common.Constants;
import cn.itsz.newsim.dto.ResultMessage;
import cn.itsz.newsim.dto.request.EnquireRTQRequest;
import cn.itsz.newsim.dto.response.entity.LoginResponseEntity;
import cn.itsz.newsim.view.form.EnquireRTQForm;

@Controller("/webEnquireDelayRTQ")
public class WebEnquireDelayRTQAction extends BaseAction {

	@Override
	protected boolean isRequiredLogon() {
		return false;
	}
	
	@Override
	protected ActionForward executeAction(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		EnquireRTQForm rtqForm = (EnquireRTQForm) form;
		EnquireRTQRequest rtqRequest = this.fillValue(request, rtqForm, response);
		ResultMessage result = facade.enquireRTQInfo(rtqRequest);
		return viewProcess.process(result, response);
	}

	private EnquireRTQRequest fillValue(HttpServletRequest request,
			EnquireRTQForm form, HttpServletResponse response) {
		EnquireRTQRequest rtqRequest = new EnquireRTQRequest();
		Vector<String> v = new Vector<String>();
		v.add(form.getInstrCode());
		rtqRequest.setInstrCode(v);
		rtqRequest.setLoginId(((LoginResponseEntity)(request.getSession().getAttribute(Constants.AttributeKey.Session.USER))).getLoginId());
		Locale locale = (Locale) request.getSession().getAttribute(Constants.AttributeKey.Session.DEFAULT_LOCALE);
		rtqRequest.setLanguage(ActionUtil.locale2QSlang(locale));
		return rtqRequest;
	}
}
