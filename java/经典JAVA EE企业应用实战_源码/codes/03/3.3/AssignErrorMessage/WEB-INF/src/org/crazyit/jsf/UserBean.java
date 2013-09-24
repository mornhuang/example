package org.crazyit.jsf;


import java.util.*;
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
	private Date birthday;

	//无参数的构造器
	public UserBean()
	{
	}
	//初始化全部属性的构造器
	public UserBean(String name , Date birthday)
	{
		this.name = name;
		this.birthday = birthday;
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

	//birthday属性的setter和getter方法
	public void setBirthday(Date birthday)
	{
		this.birthday = birthday;
	}
	public Date getBirthday()
	{
		return this.birthday;
	}

	//编写处理导航的方法
	public String add()
	{
		System.out.println(getBirthday());
		return "success";
	}

}