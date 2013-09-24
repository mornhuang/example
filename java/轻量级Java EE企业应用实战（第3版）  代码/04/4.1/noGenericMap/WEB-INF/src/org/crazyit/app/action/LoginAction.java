package org.crazyit.app.action;

import com.opensymphony.xwork2.Action;
import java.util.Map;

import org.crazyit.app.domain.*;

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
	//Action类里包含一个Map类型的参数
	private Map users;
	private String tip;
	
	//users属性的setter和getter方法
	public void setUsers(Map users)
	{
		this.users = users;
	}
	public Map getUsers()
	{
		return this.users;
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
		//在控制台输出Struts 2封装产生的Map对象
		System.out.println(getUsers());
		User firstUser = (User)getUsers().get("one");
		//根据Map对象中key为one的User实例来决定控制逻辑
		if (firstUser.getName().equals("crazyit.org")
			&& firstUser.getPass().equals("leegang") )
		{
			setTip("登录成功！");
			return SUCCESS;
		}
		else
		{
			setTip("登录失败！！");
			return ERROR;
		}
	}
}