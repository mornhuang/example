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
import cn.itsz.newsim.dto.request.EnquiryPositionRequest;
import cn.itsz.newsim.dto.response.entity.LoginResponseEntity;

/**
 * 
 * $Id: EnquireStockPositionAction.java,v 1.4 2011/03/24 08:41:32 kdu Exp $
 * 
 * @Project:portal
 * @File:EnquireStockPositionAction.java
 * @Description:
 * @Author:kyzou
 * @Date:2008-3-6
 */
@Controller("/webEnquireStockPosition")
public class EnquireStockPositionAction extends BaseAction {
	public ActionForward executeAction(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		EnquiryPositionRequest positionModel = new EnquiryPositionRequest();
		LoginResponseEntity user = (LoginResponseEntity) request.getSession().getAttribute(Constants.AttributeKey.Session.USER);
		positionModel.setLoginId(user.getLoginId());
		String lang =ActionUtil.locale2QSlang((Locale)request.getSession().getAttribute(Constants.Common.defaultLocaleAttributeName));
		positionModel.setLanguage(lang);
		ResultMessage result = facade.enquiryPosition(positionModel);
		return viewProcess.process(result, response);
	}
}
