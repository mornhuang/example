package com.itsz.sht.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.itsz.sht.common.Consts;
import com.itsz.sht.common.DataModelUtil;
import com.itsz.sht.common.model.request.EnquiryPositionRequestModel;
import com.itsz.sht.common.model.response.EnquiryPositionResponseModel;
import com.itsz.sht.common.user.UserObject;
import com.itsz.sht.struts.form.EnquiryPositionForm;
import com.itsz.sht.vp.ViewProvider;
import com.itsz.sht.vp.common.ProcessBean;

/**
 * 
 * $Id: EnquireStockPositionAction.java,v 1.2 2010/12/13 09:09:14 zxfan Exp $
 * 
 * @Project:portal
 * @File:EnquireStockPositionAction.java
 * @Description:
 * @Author:kyzou
 * @Date:2008-3-6
 */
public class EnquireStockPositionAction extends ITSZAction {
	public ActionForward executeAction(ViewProvider vp, ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		EnquiryPositionRequestModel positionModel = new EnquiryPositionRequestModel();
		DataModelUtil.form2Model(request, (EnquiryPositionForm) form, positionModel, response);
		positionModel = this.fillValue(request, positionModel);
		EnquiryPositionResponseModel positionResp = facade.enquiryPosition(positionModel);
		ProcessBean processBean = new ProcessBean(positionResp, null, mapping, request, response);
		return vp.processEnquireStockPosition(processBean);
	}

	private EnquiryPositionRequestModel fillValue(HttpServletRequest request,
			EnquiryPositionRequestModel positionModel) {
		UserObject user = (UserObject) request.getSession().getAttribute(Consts.Profile.USER);
		positionModel.setAllowTradeStatusFlag(user.getTradeInfoObject().getAllowTradeStatusFlag());
		positionModel.setAccountId(user.getTradeInfoObject().getTradingAccount());
		return positionModel;
	}
}
