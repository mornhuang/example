package cn.itsz.newsim.view.action;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.springframework.beans.factory.annotation.Autowired;

import cn.itsz.newsim.common.Constants;
import cn.itsz.newsim.common.LanguageUtil;
import cn.itsz.newsim.dto.ResultMessage;
import cn.itsz.newsim.dto.response.entity.LoginResponseEntity;
import cn.itsz.newsim.manage.WebActiveUserManager;
import cn.itsz.newsim.service.Facade;
import cn.itsz.newsim.view.form.BaseForm;
import cn.itsz.newsim.view.process.ViewProcess;


public abstract class BaseAction extends DispatchAction {
	
	protected static Log log = LogFactory.getLog(BaseAction.class);
	@Autowired
	protected Facade facade;
	@Autowired
	protected ViewProcess viewProcess;
	
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
			log.info(">>>ACTION REQUEST: " + form);
			
			HttpSession session = request.getSession();
			String clv = null;
			if (null != form ) clv = ((BaseForm)form).getCLV();
			if (StringUtils.isBlank(clv)) {
				clv = LanguageUtil.cookie2CLV(request);
			}
			String language = LanguageUtil.converCLV(clv);
			if(Constants.Lang.GB.equals(language)){
	            session.setAttribute(Constants.Common.defaultLocaleAttributeName,
	            		Locale.SIMPLIFIED_CHINESE);
	        }else if(Constants.Lang.ENG.equals(language)){
	            session.setAttribute(Constants.Common.defaultLocaleAttributeName,
	            		Locale.US);
	        }else if(Constants.Lang.BIG5.equals(language)){
	        	session.setAttribute(Constants.Common.defaultLocaleAttributeName,
	        			Locale.TRADITIONAL_CHINESE);
	        }
			LanguageUtil.saveCookie(request, response, clv);
			 
			return this.processAction(mapping, form, request, response);
	}

	protected ActionForward processAction(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		if (isRequiredLogon()) {
			if (checkSession(request.getSession())) {
				LoginResponseEntity user = (LoginResponseEntity) request.getSession().getAttribute(Constants.AttributeKey.Session.USER);
				if (!WebActiveUserManager.getInstance().validateUser(user.getLoginId(), request.getSession().getId())) {
					ResultMessage result = new ResultMessage();
					result.setReturnCode(Constants.Status.FAILED);
					result.setErrorCode(Constants.ErrorKey.NSIM_00001);
					return viewProcess.processException(result, request, response, mapping, ((BaseForm)form).getCLV());
				}
			} else {
				ResultMessage result = new ResultMessage();
				result.setReturnCode(Constants.Status.FAILED);
				result.setErrorCode(Constants.ErrorKey.NSIM_00002);
				return viewProcess.processException(result, request, response, mapping, ((BaseForm)form).getCLV());
			}
		}
		if (this.isRequiredToken()) {
			String token = (String) request.getSession().getAttribute(Constants.AttributeKey.Session.TOKEN);
			request.getSession().removeAttribute(Constants.AttributeKey.Session.TOKEN);
			BaseForm baseForm = (BaseForm) form;
			if (StringUtils.isEmpty(token) ||
				baseForm == null ||
				StringUtils.isEmpty(baseForm.getToken()) ||
				!token.equals(baseForm.getToken())) {
				ResultMessage result = new ResultMessage();
				result.setReturnCode(Constants.Status.WARN);
				result.setErrorCode("TOKENERROR");
				return viewProcess.processException(result, request, response, mapping, baseForm.getCLV());
			}
		}
		String actionMethod = mapping.getParameter();
		if (StringUtils.isNotBlank(actionMethod) && StringUtils.isNotBlank(request.getParameter(actionMethod))) {
			try {
				return super.execute(mapping, form, request, response);
			} catch (Exception e) {
				return mapping.getInputForward();
			}
		} else {
			return this.executeAction(mapping, form, request, response);
		}
		
	}
	
    protected abstract ActionForward executeAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response);

	protected boolean isRequiredToken() {
		return false;
	}
	
	protected boolean isRequiredLogon() {
		return true;
	}
	
	protected void setOldTokenToSession(BaseForm baseForm, HttpServletRequest request) {
		
	}

	protected boolean checkSession(HttpSession session) {
		LoginResponseEntity user = (LoginResponseEntity) session.getAttribute(Constants.AttributeKey.Session.USER);
		boolean result = false;
		if (user != null) {
			result = true;
		}
		return result;
	}
}
