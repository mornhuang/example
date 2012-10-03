package com.itsz.sht.struts.action;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.jboss.remoting.transport.coyote.ResponseMap;

import com.itsz.sht.common.Constants;
import com.itsz.sht.common.Consts;
import com.itsz.sht.common.DataModelUtil;
import com.itsz.sht.common.WEB_Constants;
import com.itsz.sht.common.model.common.LoginUserInfo;
import com.itsz.sht.common.model.request.EMCRequestModel;
import com.itsz.sht.common.model.response.EMCResponseModel;
import com.itsz.sht.common.user.UserObject;
import com.itsz.sht.common.util.EmcUtil;
import com.itsz.sht.vp.ViewProvider;
/**
 * $Id: EMCAction.java,v 1.3 2011/04/19 07:55:40 xwquan Exp $
 * 
 * @Project:portal.head
 * @File:LoginAction.java
 * @Description:
 * @Author:Cimenon Saint
 * @Date:2007-5-21
 */
public class EMCAction extends ITSZAction {

	
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
		EMCResponseModel responseEMCModel = facade.getEMCMsgURL(emcRequestModel);
		String emcURL = responseEMCModel.getEmcURL();
		if("".equals(emcURL)){
			return mapping.findForward("fail");
		}else{
			request.setAttribute("emcURL", emcURL);
	        return mapping.findForward("success");
		}
	}
}
