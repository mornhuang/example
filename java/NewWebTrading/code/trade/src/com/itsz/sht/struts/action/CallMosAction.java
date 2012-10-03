package com.itsz.sht.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.itsz.sht.common.Constants;
import com.itsz.sht.common.Consts;
import com.itsz.sht.common.DataModelUtil;
import com.itsz.sht.common.model.request.MosRequestModel;
import com.itsz.sht.common.model.response.MosResponseModel;
import com.itsz.sht.common.user.UserObject;
import com.itsz.sht.struts.form.ITSZForm;
import com.itsz.sht.vp.ViewProvider;
import com.itsz.sht.vp.common.ProcessBean;

/**
 * 
 * $Id: CallMosAction.java,v 1.1 2010/12/09 07:40:52 zxfan Exp $
 * 
 * @Project:portal
 * @File:CallMosAction.java
 * @Description:
 * @Author: swliu
 * @Date:2008-3-19
 */
public class CallMosAction extends ITSZAction {

	public ActionForward executeAction(ViewProvider vp, ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		MosResponseModel mosResModel = new MosResponseModel();
		UserObject user = (UserObject) request.getSession().getAttribute(Constants.USER);
		if (user != null &&
			user.getTradeInfoObject() != null &&
			StringUtils.isNotBlank(user.getTradeInfoObject().getTradingAccount()) &&
			Consts.Global.Flag.POSITIVE.equals(user.getTradeInfoObject().getAllowTradeStatusFlag())) {
			mosResModel = facade.getMos(fillRequestValue(request, response, form, user));
		} else {
			if (user != null && user.getTradeInfoObject() != null) {
				if (StringUtils.isBlank(user.getTradeInfoObject().getTradingAccount())) {
					mosResModel.setReturnCode(Consts.Error.Code.TRAD_NO_ACCTRADE);
				} else if (Consts.Global.Flag.NEGATIVE.equals(user.getTradeInfoObject().getAllowTradeStatusFlag())) {
					mosResModel.setReturnCode(Consts.Error.Code.TRAD_NO_ALLOWTRADE);
				}
			}
		}
		ProcessBean processBean = new ProcessBean(mosResModel, null, mapping, request, response);
		return vp.processCallMos(processBean);
	}

	private MosRequestModel fillRequestValue(HttpServletRequest request,
			HttpServletResponse response, ActionForm form, UserObject user) {
		MosRequestModel model = new MosRequestModel();
		DataModelUtil.form2Model(request, (ITSZForm) form, model, response);
		model.setAccountId(user.getTradeInfoObject().getTradingAccount());
		return model;
	}
}
