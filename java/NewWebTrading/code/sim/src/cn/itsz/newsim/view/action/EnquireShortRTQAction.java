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
import cn.itsz.newsim.view.form.EnquireShortRTQForm;

/**
 * $Id: EnquireShortRTQAction.java,v 1.2 2011/03/05 14:12:32 zxfan Exp $
 * 
 * @Project:portal
 * @File:EnquireShortRTQAction.java
 * @Description:
 * @Author:kyzou
 * @Date:2009-4-13
 */
@Controller("/webEnquireShortRTQ")
public class EnquireShortRTQAction extends BaseAction {

	@Override
	public ActionForward executeAction(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		EnquireShortRTQForm rtqForm = (EnquireShortRTQForm) form;
		EnquireRTQRequest rtqRequest = new EnquireRTQRequest();
		LoginResponseEntity user = (LoginResponseEntity) request.getSession().getAttribute(Constants.AttributeKey.Session.USER);
		rtqRequest.setLoginId(user.getLoginId());
		Vector<String> instrCodes = new Vector<String>();
		instrCodes.add(rtqForm.getInstrCode());
		rtqRequest.setInstrCode(instrCodes);
		Locale locale = (Locale) request.getSession().getAttribute(Constants.AttributeKey.Session.DEFAULT_LOCALE);
		rtqRequest.setLanguage(ActionUtil.locale2QSlang(locale));
		ResultMessage result = facade.enquireShortRTQInfo(rtqRequest);
		return viewProcess.process(result, response);
	}
}
