package org.crazyit.jsf;

import javax.faces.component.html.HtmlInputText;
import javax.faces.event.ActionEvent;
import javax.faces.context.FacesContext; 

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
	private String name;
	private String pass;

	//无参数的构造器
	public LoginBean()
	{
	}
	//初始化全部属性的构造器
	public LoginBean(String name , String pass)
	{
		this.name = name;
		this.pass = pass;
	}

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
	
	public String process()
	{
		//本来应该调用业务组件通过数据库来验证用户名、密码是否正确
		//此处只是直接判断
		if (getName().equals("crazyit.org")
			&& getPass().equals("leegang"))
		{
			//将登录用户名设置成session范围的属性
			FacesContext.getCurrentInstance().getExternalContext()
				.getSessionMap().put("user" , getName());
			return "success";
		}  
		return "failure";
	}
}