package cn.itsz.newsim.view.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.stereotype.Controller;

import cn.itsz.newsim.common.Constants;
import cn.itsz.newsim.dto.response.MosResponse;
import cn.itsz.newsim.dto.response.entity.CalMOSResponseEntity;
import cn.itsz.newsim.dto.response.entity.LoginResponseEntity;

/**
 * 
 * $Id: CallMosAction.java,v 1.2 2011/03/07 10:20:02 zxfan Exp $
 * 
 * @Project:portal
 * @File:CallMosAction.java
 * @Description:
 * @Author: zxfan
 * @Date:2008-3-19
 */
@Controller("/webCallMos")
public class CallMosAction extends BaseAction {

	public ActionForward executeAction(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		MosResponse mosResModel = new MosResponse();
		LoginResponseEntity user = (LoginResponseEntity) request.getSession().getAttribute(Constants.AttributeKey.Session.USER);
		CalMOSResponseEntity entity = new CalMOSResponseEntity();
		entity.setTotalPurchasingPower(user.getTotalPurchasingPower());
		mosResModel.setCalMOSResponse(entity);
		return viewProcess.process(mosResModel, response);
	}
}
