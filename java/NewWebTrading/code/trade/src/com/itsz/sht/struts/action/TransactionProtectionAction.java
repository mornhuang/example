package com.itsz.sht.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.itsz.sht.common.Constants;
import com.itsz.sht.common.DataModelUtil;
import com.itsz.sht.common.model.request.TransactionProtectionRequestModel;
import com.itsz.sht.common.model.response.TransactionProtectionResponseModel;
import com.itsz.sht.common.user.UserObject;
import com.itsz.sht.struts.form.TransactionProtectionForm;
import com.itsz.sht.vp.ViewProvider;
import com.itsz.sht.vp.common.ProcessBean;

/**
 * 
 * $Id: TransactionProtectionAction.java,v 1.4 2011/03/15 07:11:52 xli Exp $
 * 
 * @Project:portal
 * @File:TransactionProtectionAction.java
 * @Description:
 * @Author: kyzou
 * @Date:2008-3-6
 */
public class TransactionProtectionAction extends ITSZAction {
	public ActionForward executeAction(ViewProvider vp, ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		TransactionProtectionRequestModel requestModel = new TransactionProtectionRequestModel();
		DataModelUtil.form2Model(request, (TransactionProtectionForm) form, requestModel, response);
		UserObject user = (UserObject) request.getSession().getAttribute(Constants.USER);
		requestModel.setLoginId(user.getLoginName());

		TransactionProtectionResponseModel responseModel = facade.changeTransactionProtection(requestModel);
		ProcessBean processBean = new ProcessBean(responseModel, null, mapping, request, response);
		return vp.processChangeTransactionProtection(processBean);
	}
}
