package org.crazyit.app.action;

import java.io.InputStream;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.Action;
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
public class LoginAction implements Action 
{
	private String user;
	private String pass;
	
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
	
	public String execute()
	{
		ActionContext.getContext().getSession()
			.put("user" , getUser());
		return SUCCESS;
	}
}