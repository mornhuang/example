package cn.itsz.newsim.view.action;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.stereotype.Controller;

import cn.itsz.newsim.common.ActionUtil;
import cn.itsz.newsim.common.Constants;
import cn.itsz.newsim.dto.ResultMessage;
import cn.itsz.newsim.dto.request.TradeListRequest;
import cn.itsz.newsim.dto.response.entity.LoginResponseEntity;

/**
 * $Id: TradeListAction.java,v 1.3 2011/03/10 08:42:13 bwu Exp $
 * @Project:portal
 * @File:TradeListAction.java
 * @Description:
 * @Author:zxfan
 * @Date:2009-6-26
 */
@Controller("/webTradeList")
public class TradeListAction extends BaseAction {

	@Override
	public ActionForward executeAction(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		TradeListRequest model = new TradeListRequest();
		LoginResponseEntity user = (LoginResponseEntity) request.getSession().getAttribute(Constants.AttributeKey.Session.USER);
		model.setLoginId(user.getLoginId());
		String lang =ActionUtil.locale2QSlang((Locale)request.getSession().getAttribute(Constants.Common.defaultLocaleAttributeName));
		model.setLanguage(lang);
		ResultMessage result = facade.enquireTradeList(model);
		return viewProcess.process(result, response);
	}
}
