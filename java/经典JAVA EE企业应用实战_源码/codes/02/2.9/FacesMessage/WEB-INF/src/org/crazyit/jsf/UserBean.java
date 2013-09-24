package org.crazyit.jsf;

import javax.faces.context.*;
import javax.faces.application.*;
import javax.faces.validator.*;
import javax.faces.convert.*;

import javax.faces.*;
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
public class UserBean 
{
	private String name;
	private int age;



	//无参数的构造器
	public UserBean()
	{
	}
	//初始化全部属性的构造器
	public UserBean(String name , int age)
	{
		this.name = name;
		this.age = age;
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

	//age属性的setter和getter方法
	public void setAge(int age)
	{
		if (age > 100 || age < 0)
		{
			FacesContext.getCurrentInstance()
				.addMessage("age"
				, new FacesMessage(FacesMessage.SEVERITY_FATAL
				, "年龄不对" , "年龄必须小于100，且大于0！")); 
		}
		this.age = age;
	}
	public int getAge()
	{
		return this.age;
	}
	//简单的处理逻辑，直接返回“success”字符串
	public String regist()
	{
		return "success";
	}
}