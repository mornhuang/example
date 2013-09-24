package org.crazyit.app.action;

import com.opensymphony.xwork2.Action;

import java.io.*;
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
	implements Action
{
	//封装请求参数的两个属性
	private String user;
	private String pass;
	//封装输出结果的二进制流
	private InputStream inputStream;

	//user属性的setter和getter方法
	public void setUser(String user)
	{
		this.user = user;
	}
	public String getUser()
	{
		return this.user;
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

	public InputStream getResult()
	{
		return inputStream;
	}

	public String execute() 
		throws Exception 
	{
		//判断用户名、密码，生成对应的响应
		inputStream = user.equals("crazyit.org") && pass.equals("leegang")
			? new ByteArrayInputStream("恭喜你，登录成功!"
				.getBytes("UTF-8"))
			: new ByteArrayInputStream("对不起，用户名、密码不匹配！"
				.getBytes("UTF-8"));
		return SUCCESS;
	}
}