package org.crazyit.hrsystem.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.opensymphony.xwork2.*;
import org.apache.struts2.interceptor.*;

import org.crazyit.hrsystem.service.EmpManager;
import org.crazyit.hrsystem.domain.*;
import org.crazyit.hrsystem.exception.HrException;
import org.crazyit.hrsystem.action.base.EmpBaseAction;
import static org.crazyit.hrsystem.service.EmpManager.*;

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
public class LoginAction
	extends EmpBaseAction
{
	//定义一个常量作为员工登录成功的Result名
	private final String EMP_RESULT = "emp";
	//定义一个常量作为经理登录成功的Result名
	private final String MGR_RESULT = "mgr";
	//封装请求参数
	private Manager manager;
	//登录的验证码
	private String vercode;
	//处理登录后的提示信息
	private String tip;
	//manager属性的setter和getter方法
	public void setManager(Manager manager)
	{
		this.manager = manager;
	}
	public Manager getManager()
	{
		return this.manager;
	}

	//vercode属性的setter和getter方法
	public void setVercode(String vercode)
	{
		this.vercode = vercode;
	}
	public String getVercode()
	{
		return this.vercode;
	}

	//tip属性的setter和getter方法
	public void setTip(String tip)
	{
		this.tip = tip;
	}
	public String getTip()
	{
		return this.tip;
	}
	//处理用户请求
	public String execute()
		throws Exception
	{
		//创建ActionContext实例
		ActionContext ctx = ActionContext.getContext();
		//获取HttpSession中的rand属性
		String ver2 = (String)ctx.getSession().get("rand");
		if (vercode.equalsIgnoreCase(ver2))
		{
			//调用业务逻辑方法来处理登录请求
			int result = mgr.validLogin(getManager());
			//登录结果为普通员工
			if (result == LOGIN_EMP)
			{
				ctx.getSession().put(WebConstant.USER 
					, manager.getName());
				ctx.getSession().put(WebConstant.LEVEL
					, WebConstant.EMP_LEVEL);
				setTip("您已经成功登录系统");
				return EMP_RESULT;
			}
			//登录结果为经理
			else if (result == LOGIN_MGR)
			{
				ctx.getSession().put(WebConstant.USER 
					, manager.getName());
				ctx.getSession().put(WebConstant.LEVEL
					, WebConstant.MGR_LEVEL);
				setTip("您已经成功登录系统");
				return MGR_RESULT;
			}
			//用户名和密码不匹配
			else
			{
				setTip("用户名/密码不匹配");
				return ERROR;
			}
		}
		//验证码不匹配
		else
		{
			setTip("验证码不匹配,请重新输入");
			return ERROR;
		}
	}
}