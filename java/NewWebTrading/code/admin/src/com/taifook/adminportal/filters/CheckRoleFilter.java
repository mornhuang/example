package com.taifook.adminportal.filters;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.Globals;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;

import com.itsz.sht.common.Consts;
import com.itsz.sht.common.ServiceLocator;
import com.itsz.sht.dao.hibernate.model.AclUserProfile;
import com.itsz.sht.service.Facade;
import com.taifook.adminportal.common.Constants;

@SuppressWarnings("deprecation")
public class CheckRoleFilter extends AbstractFilter {

	public void init(FilterConfig filterConfig) throws ServletException {
		super.init(filterConfig);
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws IOException, ServletException {
		Log log = LogFactory.getLog(this.getClass());
		if (isCheck) {
			if (request instanceof HttpServletRequest) {
				HttpServletRequest httpRequest = (HttpServletRequest) request;
/*				log.info((new Date()).toLocaleString() + "in filter "
						+ httpRequest.getRequestURI());*/
				if (!isExcluded(httpRequest)) {
					AclUserProfile user = (AclUserProfile) httpRequest.getSession().getAttribute(Consts.AdminPortal.userContext.USERCONTEXT);
					if (user == null) {
						log.info(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "： user not login!");
						this.forwardNotLoginFailPage(httpRequest, (HttpServletResponse) response);
						return;
					} else {
						if (!hasAccessRight(user.getLognId())) {
							log.info(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "：user not right!");
							this.forwardNotRightFailPage(httpRequest, (HttpServletResponse) response);
							return;
						}
					}
				}
			}
		}

		filterChain.doFilter(request, response);
	}

	public void destroy() {
		super.destroy();
	}

	private boolean hasAccessRight(String loginId) {
		Facade facade = (Facade) ServiceLocator.getInstance().getService("facade");
		AclUserProfile user = facade.getAclUser(loginId).getAclUserProfile();
		if(user == null){
			return false;
		}
		return true;
	}

	//
	private void forwardNotRightFailPage(HttpServletRequest httpRequest,
			HttpServletResponse httpResponse) throws IOException,
			ServletException {
		ActionErrors errors = new ActionErrors();
		httpRequest.removeAttribute(Globals.ERROR_KEY);
		httpRequest.setAttribute(Constants.RETURN_BACK_URL, "LOGOUT");
		errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.user_not_right"));
		this.saveErrors(httpRequest, errors);
		RequestDispatcher rd = httpRequest.getRequestDispatcher(Constants.COMMON_FAIL_FORWARD_JSP);
		rd.forward(httpRequest, httpResponse);
		return;
	}
	
	//
	private void forwardNotLoginFailPage(HttpServletRequest httpRequest,
			HttpServletResponse httpResponse) throws IOException,
			ServletException {
		ActionErrors errors = new ActionErrors();
		if (this.basePath == null) {
			this.basePath = httpRequest.getScheme() + "://"
						+ httpRequest.getServerName() + ":"
						+ httpRequest.getServerPort()
						+ httpRequest.getContextPath() + "/logout.do";
		}
		httpRequest.removeAttribute(Globals.ERROR_KEY);
		httpRequest.setAttribute(Constants.RETURN_BACK_URL, "LOGOUT");
		errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.user_not_login", basePath));
		this.saveErrors(httpRequest, errors);
		RequestDispatcher rd = httpRequest.getRequestDispatcher(Constants.COMMON_FAIL_FORWARD_JSP);
		rd.forward(httpRequest, httpResponse);
		return;
	}

}
