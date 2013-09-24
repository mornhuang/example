package org.crazyit.auction.jsf.listener;

import javax.faces.event.*;
import javax.faces.context.*;
import javax.faces.application.*;

/**
 * Description:
 * <br/>网站: <a href="http://www.crazyit.org">疯狂Java联盟</a> 
 * <br/>Copyright (C), 2001-2010, Yeeku.H.Lee
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name:
 * <br/>Date:
 * @author  Yeeku.H.Lee kongyeeku@163.com
 * @version  1.0
 */
public class LoggedInCheck implements PhaseListener
{ 
	public PhaseId getPhaseId()
	{ 
		return PhaseId.RESTORE_VIEW; 
	} 
	public void beforePhase(PhaseEvent event)
	{ 
	} 
	public void afterPhase(PhaseEvent event)
	{ 
		FacesContext fc = event.getFacesContext(); 
		//对login.jsp页面不做检查
		boolean loginPage = fc.getViewRoot()
			.getViewId().lastIndexOf("login.jsp") 
			> -1 ? true : false; 
		//对main.jsp页面不做检查
		boolean mainPage = fc.getViewRoot()
			.getViewId().lastIndexOf("main.jsp") 
			> -1 ? true : false; 
		//只对后缀是jsp的请求进行检查
		boolean jspSuffix = fc.getViewRoot()
			.getViewId().lastIndexOf(".jsp") 
			> -1 ? true : false; 
		if (!loginPage && !mainPage && !loggedIn(fc) && jspSuffix) 
		{ 
			NavigationHandler nh = fc.getApplication()
				.getNavigationHandler(); 
			nh.handleNavigation(fc, null, "logout"); 
		} 
	} 
	//验证是否已经登录
	private boolean loggedIn(FacesContext fc) 
	{
		//读取session中user属性
		Object user = fc.getExternalContext().getSessionMap()
			.get("userId");
		//如果session范围的user属性存在，即表明用户已经登录。
		if (user != null
			&& user.toString().length() > 0)
		{
			return true;
		}
		return false;
	} 
}
