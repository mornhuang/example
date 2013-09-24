package org.crazyit.hrsystem.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.opensymphony.xwork2.*;
import org.apache.struts2.interceptor.*;

import org.crazyit.hrsystem.service.MgrManager;
import org.crazyit.hrsystem.exception.HrException;
import org.crazyit.hrsystem.action.base.MgrBaseAction;

import java.util.List;
/**
 * Description:
 * <br/>网站: <a href="http://www.crazyit.org">疯狂Java联盟</a> 
 * <br/>Copyright (C), 2001-2012, Yeeku.H.Lee
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name:
 * <br/>Date:
 * @author  Yeeku.H.Lee kongyeeku@163.com
 * @version  1.0
 */

public class ViewAppAction extends MgrBaseAction
{
	private List apps;
	//apps属性的setter和getter方法
	public void setApps(List apps)
	{
		this.apps = apps;
	}
	public List getApps()
	{
		return this.apps;
	}
	public String execute()
		throws Exception
	{
		//创建ActionContext实例
		ActionContext ctx = ActionContext.getContext();
		//获取HttpSession中的user属性
		String mgrName = (String)ctx.getSession()
			.get(WebConstant.USER);
		//获取需要被当前经理处理的全部申请
		setApps(mgr.getAppsByMgr(mgrName));
		return SUCCESS;
	}
}