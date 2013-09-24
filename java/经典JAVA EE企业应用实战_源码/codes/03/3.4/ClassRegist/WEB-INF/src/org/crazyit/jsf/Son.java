package org.crazyit.jsf;

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
public class Son
{
	private String name;
	private double height;
	private int age;

	//无参数的构造器
	public Son()
	{
	}
	//初始化全部属性的构造器
	public Son(String name , double height , int age)
	{
		this.name = name;
		this.height = height;
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

	//height属性的setter和getter方法
	public void setHeight(double height)
	{
		this.height = height;
	}
	public double getHeight()
	{
		return this.height;
	}

	//age属性的setter和getter方法
	public void setAge(int age)
	{
		this.age = age;
	}
	public int getAge()
	{
		return this.age;
	}
}