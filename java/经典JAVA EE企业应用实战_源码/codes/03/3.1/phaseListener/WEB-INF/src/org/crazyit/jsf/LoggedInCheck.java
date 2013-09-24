package org.crazyit.jsf;

import javax.faces.application.NavigationHandler; 
import javax.faces.context.FacesContext; 
import javax.faces.event.PhaseEvent; 
import javax.faces.event.PhaseId; 
import javax.faces.event.PhaseListener; 

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
		if (!loginPage && !loggedIn(fc)) 
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
			.get("user");
		//如果session范围的user属性存在，即表明用户已经登录。
		if (user != null
			&& user.toString().length() > 0)
		{
			return true;
		}
		return false;
	} 
}