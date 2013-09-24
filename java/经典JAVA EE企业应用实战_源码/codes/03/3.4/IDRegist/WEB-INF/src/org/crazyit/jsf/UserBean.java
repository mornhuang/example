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
	private Son son;

	//无参数的构造器
	public UserBean()
	{
	}
	//初始化全部属性的构造器
	public UserBean(String name , Son son)
	{
		this.name = name;
		this.son = son;
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

	//son属性的setter和getter方法
	public void setSon(Son son)
	{
		this.son = son;
	}
	public Son getSon()
	{
		return this.son;
	}

	//编写处理导航的方法
	public String add()
	{
		return "success";
	}
}