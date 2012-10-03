package com.itsz.sht.struts.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import oracle.jdbc.Const;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.itsz.sht.common.Consts;
import com.itsz.sht.common.DataModelUtil;
import com.itsz.sht.common.LanguageUtil;
import com.itsz.sht.common.model.request.LoginRequestModel;
import com.itsz.sht.common.model.response.LoginResponseModel;
import com.itsz.sht.exception.ITSZException;
import com.itsz.sht.struts.form.ITSZForm;
import com.itsz.sht.struts.form.LoginForm;
import com.itsz.sht.vp.ViewProvider;
import com.itsz.sht.vp.common.ProcessBean;
/**
 * $Id: LoginAction.java,v 1.5 2010/12/13 08:56:49 xlliu Exp $
 * 
 * @Project:portal.head
 * @File:LoginAction.java
 * @Description:
 * @Author:Cimenon Saint
 * @Date:2007-5-21
 */
public class LoginAction extends ITSZAction {

	/**
	 * 
	 * @Author:Cimenon Saint
	 * @Time:15:35:14
	 * @param vp
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 */
	
	public ActionForward executeAction(ViewProvider vp,
									   ActionMapping mapping,
									   ActionForm form,
									   HttpServletRequest request,
									   HttpServletResponse response) {
		LoginRequestModel model = new LoginRequestModel();
		DataModelUtil.form2Model(request,(LoginForm) form, model, response);
		model.setRemoteAddr(request.getRemoteAddr());
		if(StringUtils.isNotBlank(model.getLanguage())) {
			request.getSession().setAttribute(Consts.Global.Common.defaultLocaleAttributeName,LanguageUtil.lang2locale(model.getLanguage()));
		}
		LoginResponseModel responseModel = facade.login(model);
		//if find sessionID from channelMap and loginId is not the same in channelMap
		ProcessBean processBean = new ProcessBean(responseModel,null, mapping, request,response);
        return vp.processLogin(processBean);
	}
	
	public boolean isLoginActionExecuted() {
		return true;
	}
	
	
	
	public  ActionForward processException(
		ViewProvider vp,
		ActionMapping mapping,
		ITSZException exceptionBean,
		HttpServletRequest request,
		HttpServletResponse response){
		
			ProcessBean processBean = new ProcessBean(exceptionBean,null, mapping, request,response);		
			return vp.processException(processBean);
	}
}
