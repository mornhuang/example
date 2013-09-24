package org.crazyit.app.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ActionContext;
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
 
public class LoginRegistAction 
	extends ActionSupport
{
	//封装用户请求参数的两个属性
	private String username;
	private String password;
	//封装处理结果的tip属性
	private String tip;
	//username属性对应的setter和getter方法
	public String getUsername()
	{
		return username;
	}
	public void setUsername(String username)
	{
		this.username = username;
	}
	//password属性对应的getter和setter方法
	public String getPassword()
	{
		return password;
	}
	public void setPassword(String password)
	{
		this.password = password;
	}
	//tip属性对应的setter和getter方法
	public String getTip()
	{
		return tip;
	}
	public void setTip(String tip)
	{
		this.tip = tip;
	}
	//Action包含的注册控制逻辑
	public String regist() throws Exception
	{
		ActionContext.getContext().getSession()
			.put("user" , getUsername());
		setTip("恭喜您," + getUsername() + ",您已经注册成功！");
		return SUCCESS;
	}
	//Action默认包含的控制逻辑
	public String execute() throws Exception
	{
		if (getUsername().equals("crazyit.org")
			&& getPassword().equals("leegang") )
		{
			ActionContext.getContext().getSession()
				.put("user" , getUsername());
			setTip("欢迎," + getUsername() + ",您已经登录成功！");
			return SUCCESS;
		}
		else
		{
			return ERROR;
		}
	}
}