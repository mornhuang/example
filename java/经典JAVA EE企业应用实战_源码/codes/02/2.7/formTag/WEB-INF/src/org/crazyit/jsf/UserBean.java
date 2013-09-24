package org.crazyit.jsf;


import javax.faces.context.*;
import javax.servlet.http.*;
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
	//绑定UI组件本身的属性

	private String pass;
	private int grade;

	//无参数的构造器
	public UserBean()
	{
	}
	//初始化全部属性的构造器
	public UserBean(String name , String pass , int grade)
	{
		this.name = name;
		this.pass = pass;
		this.grade = grade;
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

	//grade属性的setter和getter方法
	public void setGrade(int grade)
	{
		this.grade = grade;
	}
	public int getGrade()
	{
		return this.grade;
	}

}