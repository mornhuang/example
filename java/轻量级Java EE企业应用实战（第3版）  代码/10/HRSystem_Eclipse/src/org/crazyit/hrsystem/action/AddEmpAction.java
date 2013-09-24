package org.crazyit.hrsystem.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.opensymphony.xwork2.*;
import org.apache.struts2.interceptor.*;

import org.crazyit.hrsystem.exception.HrException;
import org.crazyit.hrsystem.domain.*;
import org.crazyit.hrsystem.action.base.MgrBaseAction;

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
public class AddEmpAction extends MgrBaseAction
{
	//新增的员工的
	private Employee emp;
	//封装提示信息的tip属性
	private String tip;
	//emp属性的setter和getter方法
	public void setEmp(Employee emp)
	{
		this.emp = emp;
	}
	public Employee getEmp()
	{
		return this.emp;
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

	public String execute()
		throws Exception
	{
		//创建ActionContext实例
		ActionContext ctx = ActionContext.getContext();
		//获取HttpSession中的user属性
		String mgrName = (String)ctx.getSession()
			.get(WebConstant.USER);
		//添加新用户
		mgr.addEmp(emp , mgrName);
		setTip("新增员工成功");
		return SUCCESS;
	}

}