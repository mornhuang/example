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
public class RegistBean 
{
	private String name;
	private int duration;
	private double cost;

	//无参数的构造器
	public RegistBean()
	{
	}
	//初始化全部属性的构造器
	public RegistBean(String name , int duration , double cost)
	{
		this.name = name;
		this.duration = duration;
		this.cost = cost;
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

	//duration属性的setter和getter方法
	public void setDuration(int duration)
	{
		this.duration = duration;
	}
	public int getDuration()
	{
		return this.duration;
	}

	//cost属性的setter和getter方法
	public void setCost(double cost)
	{
		this.cost = cost;
	}
	public double getCost()
	{
		return this.cost;
	}

	//编写处理导航的方法
	public String add()
	{
		return "success";
	}
}