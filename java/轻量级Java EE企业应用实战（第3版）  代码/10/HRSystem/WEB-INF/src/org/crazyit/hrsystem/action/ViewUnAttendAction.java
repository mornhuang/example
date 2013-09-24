package org.crazyit.hrsystem.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.opensymphony.xwork2.*;
import org.apache.struts2.interceptor.*;

import org.crazyit.hrsystem.service.EmpManager;
import org.crazyit.hrsystem.exception.HrException;
import org.crazyit.hrsystem.action.base.EmpBaseAction;
import org.crazyit.hrsystem.vo.*;

import java.util.*;
import java.text.SimpleDateFormat;

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
public class ViewUnAttendAction extends EmpBaseAction
{
	private List<AttendBean> unAttend;
	//unAttend属性的setter和getter方法
	public void setUnAttend(List<AttendBean> unAttend)
	{
		this.unAttend = unAttend;
	}
	public List<AttendBean> getUnAttend()
	{
		return this.unAttend;
	}
	public String execute()
		throws Exception
	{
		//创建ActionContext实例
		ActionContext ctx = ActionContext.getContext();
		//获取HttpSession中的user属性
		String user = (String)ctx.getSession()
			.get(WebConstant.USER);
		List<AttendBean> result = mgr.unAttend(user);
		setUnAttend(result);
		return SUCCESS;
	}
}