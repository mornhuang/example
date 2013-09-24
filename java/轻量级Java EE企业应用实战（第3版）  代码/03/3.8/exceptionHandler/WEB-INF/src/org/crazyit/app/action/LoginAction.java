package org.crazyit.app.action;

import com.opensymphony.xwork2.Action;

import org.crazyit.app.exception.*;

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
 
public class LoginAction implements Action
{
	//封装请求参数的username和password属性
	private String username;
	private String password;
	//封装提示的tip属性
	private String tip;

	//username属性的setter和getter方法
	public void setUsername(String username)
	{
		this.username = username;
	}
	public String getUsername()
	{
		return this.username;
	}

	//password属性的setter和getter方法
	public void setPassword(String password)
	{
		this.password = password;
	}
	public String getPassword()
	{
		return this.password;
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

	public String execute() throws Exception
	{
		if (getUsername().equalsIgnoreCase("user"))
		{
			throw new MyException("自定义异常");
		}
		if (getUsername().equalsIgnoreCase("sql"))
		{
			throw new java.sql.SQLException("用户名不能为SQL");
		}
		if (getUsername().equals("crazyit.org")
			&& getPassword().equals("leegang") )
		{
			setTip("哈哈，服务器提示！");
			return SUCCESS;
		}
		else
		{
			return ERROR;
		}
	}
}