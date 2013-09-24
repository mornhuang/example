package org.crazyit.app.action;

import com.opensymphony.xwork2.*;
import org.apache.struts2.interceptor.*;

import javax.servlet.http.*;

import java.util.Map;

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
	implements Action,ServletResponseAware
{
	private String username;
	private String password;
	private HttpServletResponse response;

	//重写实现ServletResponseAware接口必须实现的方法
	public void setServletResponse(HttpServletResponse response) 
	{
		this.response = response;
	}

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
		//通过ActionContext访问application范围的属性值
		Integer counter = (Integer)ctx.getApplication()
			.get("counter");
		if (counter == null)
		{
			counter = 1;
		}
		else
		{
			counter = counter + 1;
		}
		//通过ActionContext设置application范围的属性
		ctx.getApplication().put("counter" , counter);
		//通过ActionContext设置session范围的属性
		ctx.getSession().put("user" , getUsername());
		if (getUsername().equals("crazyit.org")
			&& getPassword().equals("leegang") )
		{
			//通过response添加Cookie
			Cookie c = new Cookie("user" , getUsername());
			c.setMaxAge(60 * 60);
			response.addCookie(c);
			//通过ActionContext设置request范围的属性
			ctx.put("tip" , "服务器提示：您已经成功的登录");
			return SUCCESS;
		}
		else
		{
			//通过ActionContext设置request范围的属性
			ctx.put("tip" , "服务器提示：登录失败");
			return ERROR;
		}
	}
}