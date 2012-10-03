package com.itsz.sht.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


import com.itsz.sht.common.Constants;
import com.itsz.sht.common.DataModelUtil;
import com.itsz.sht.common.model.request.VerifyPasswordRequestModel;
import com.itsz.sht.common.model.response.VerifyPasswordResponseModel;
import com.itsz.sht.common.user.UserObject;
import com.itsz.sht.struts.form.VerifyPasswordActionForm;
import com.itsz.sht.vp.ViewProvider;
import com.itsz.sht.vp.common.ProcessBean;


public class VerifyPasswordPSAction extends ITSZAction {
	public ActionForward executeAction(ViewProvider vp,
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response)  {
		VerifyPasswordActionForm passwordForm = (VerifyPasswordActionForm) form;
		VerifyPasswordRequestModel verifyPwdRequestModel = new VerifyPasswordRequestModel();
		DataModelUtil.form2Model(request, passwordForm, verifyPwdRequestModel,response);
		UserObject user = (UserObject) request.getSession().getAttribute(Constants.USER);
        if(user!=null){
        	verifyPwdRequestModel.setLoginId(user.getLoginName());
        }  
        VerifyPasswordResponseModel responseModel = facade.verifyPassword(verifyPwdRequestModel);
		ProcessBean processBean = new ProcessBean(responseModel, null, mapping, request, response);
		return vp.processVerifyPassword(processBean);
	}
}
