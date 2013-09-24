package org.crazyit.app.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ActionContext;

import java.util.Date;

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
 
public class LoginAction extends ActionSupport
{
	//封装用户请求参数的username和password属性
	private String username;
	private String password;

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
	
	public String execute() throws Exception
	{
		ActionContext ctx = ActionContext.getContext();
		if (getUsername().equals("crazyit.org")
			&& getPassword().equals("leegang") )
		{
			ctx.getSession().put("user" , getUsername());
			//根据key取出国际化消息，并为占位符指定值。
			ctx.put("tip" , getText("succTip" , new String[]
				{getUsername()}));
			return SUCCESS;
		}
		else
		{
			//根据key取出国际化消息，并为占位符指定值。
			ctx.put("tip" , getText("failTip", new String[]
				{getUsername()}));
			return ERROR;
		}
	}
}