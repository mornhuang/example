package com.itsz.sht.struts.action;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.itsz.sht.common.Constants;
import com.itsz.sht.common.DataModelUtil;
import com.itsz.sht.common.DateHelper;
import com.itsz.sht.common.model.request.TradeListRequestModel;
import com.itsz.sht.common.model.response.TradeListResponseModel;
import com.itsz.sht.common.user.UserObject;
import com.itsz.sht.struts.form.TradeListForm;
import com.itsz.sht.vp.ViewProvider;
import com.itsz.sht.vp.common.ProcessBean;

/**
 * $Id: TradeListAction.java,v 1.6 2011/04/01 03:08:16 zxfan Exp $
 * @Project:portal
 * @File:TradeListAction.java
 * @Description:
 * @Author:kyzou
 * @Date:2009-6-26
 */
public class TradeListAction extends ITSZAction {

	@Override
	public ActionForward executeAction(ViewProvider vp, ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		TradeListRequestModel model = new TradeListRequestModel();
		DataModelUtil.form2Model(request,(TradeListForm)form, model, response);
		UserObject user = (UserObject) request.getSession().getAttribute(Constants.USER);
		if (user != null) {
			model.setLoginId(user.getLoginName());
			if(StringUtils.isBlank(model.getAccountId())){
				model.setAccountId(user.getTradeInfoObject().getTradingAccount());
			}
		}
		model = validateRequest(model);
		TradeListResponseModel responseModel = facade.enquireTradeList(model);
		ProcessBean processBean = new ProcessBean(responseModel, null, mapping, request,response);
		return vp.processTradeList(processBean);
	}
	
	private TradeListRequestModel validateRequest(TradeListRequestModel model) {
		String instrCode = model.getInstrCode();
		if (StringUtils.isBlank(instrCode)) {
			model.setInstrCode(null);
		} else {
			instrCode = StringUtils.stripStart(instrCode, "0");
			model.setInstrCode(instrCode);
		}
		model.setOrderSide(StringUtils.isBlank(model.getOrderSide()) ? null : model.getOrderSide());
		model.setFromIdx(StringUtils.isBlank(model.getFromIdx()) ? null : model.getFromIdx());
		model.setToIdx(StringUtils.isBlank(model.getToIdx()) ? null : model.getToIdx());
		model.setChannel(StringUtils.isBlank(model.getChannel()) ? null : model.getChannel());
		if (StringUtils.isBlank(model.getFromDate())) {
			model.setFromDate(DateHelper.formatDate3(new Date()) + " 00:00:00");
		}
		if (StringUtils.isBlank(model.getToDate())) {
			model.setToDate(DateHelper.formatDate3(new Date()) + " 00:00:00");
		}
		return model;
	}
}
