package com.itsz.sht.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.itsz.sht.common.Constants;
import com.itsz.sht.common.DataModelUtil;
import com.itsz.sht.common.model.request.EMCRequestModel;
import com.itsz.sht.common.model.response.EMCResponseModel;
import com.itsz.sht.common.user.UserObject;
import com.itsz.sht.vp.ViewProvider;
import com.itsz.sht.vp.common.ProcessBean;
/**
 * $Id: EMCCountAction.java,v 1.2 2011/02/17 02:44:41 kyzou Exp $
 * 
 * @Project:portal.head
 * @File:LoginAction.java
 * @Description:
 * @Author:Cimenon Saint
 * @Date:2007-5-21
 */
public class EMCCountAction extends ITSZAction {

	
	public ActionForward executeAction(ViewProvider vp,
									   ActionMapping mapping,
									   ActionForm form,
									   HttpServletRequest request,
									   HttpServletResponse response) {
		
		EMCRequestModel emcRequestModel = new EMCRequestModel();
		DataModelUtil.request2Model(request, emcRequestModel);
		UserObject user = (UserObject) request.getSession().getAttribute(Constants.USER);
		if (user != null && user.getTradeInfoObject() != null) {
			emcRequestModel.setCustCode(user.getTradeInfoObject().getCustCode());
			emcRequestModel.setClientId(user.getTradeInfoObject().getCustCode());
		}
		EMCResponseModel responseEMCModel = facade.getEMCMsgCounts(emcRequestModel);
		ProcessBean processBean = new ProcessBean(responseEMCModel, null, mapping, form, request, response);
		return vp.processEMCCount(processBean);
	}
}
