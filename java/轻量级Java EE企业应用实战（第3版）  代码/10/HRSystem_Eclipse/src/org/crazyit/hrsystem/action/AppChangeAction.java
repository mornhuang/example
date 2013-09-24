package org.crazyit.hrsystem.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.opensymphony.xwork2.*;
import org.apache.struts2.interceptor.*;

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
public class AppChangeAction
	extends EmpBaseAction
{
	//封装所有异动的列表
	private List types;
	//types属性的setter和getter方法
	public void setTypes(List types)
	{
		this.types = types;
	}
	public List getTypes()
	{
		return this.types;
	}
	//处理用户请求
	public String execute()
		throws Exception
	{
		setTypes(mgr.getAllType());
		return SUCCESS;
	}
}