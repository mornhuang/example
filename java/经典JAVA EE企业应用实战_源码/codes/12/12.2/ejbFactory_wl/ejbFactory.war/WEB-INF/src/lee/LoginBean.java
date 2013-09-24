package lee;

import javax.ejb.*;

import org.crazyit.service.*;

import org.crazyit.util.*;
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

public class LoginBean
{
	//下面的三个属性都会直接与JSF标签绑定
	private String name;
	private String pass;
	private String err;	
	//name属性的setter和getter方法
	public void setName(String name)
	{
		this.name = name;
	}
	public String getName()
	{
		return this.name;
	}
	//pass属性的setter和getter方法
	public void setPass(String pass)
	{
		this.pass = pass;
	}
	public String getPass()
	{
		return this.pass;
	}

	//err属性的setter和getter方法
	public void setErr(String err)
	{
		this.err = err;
	}
	public String getErr()
	{
		return this.err;
	}
	//该方法被绑定到UI组件（按钮）的action属性
	public String valid()
	{
		//通过EJB工厂来获取EJB、并调用EJB的方法
		UserService us = (UserService)EJBFactory.lookup("userService");
		//调用Session Bean的方法来处理用户请求
		if (us.loginPro(name , pass))
		{
			return "success";
		}
		setErr("您的用户名和密码不符合");
		return "failure";
	}
}