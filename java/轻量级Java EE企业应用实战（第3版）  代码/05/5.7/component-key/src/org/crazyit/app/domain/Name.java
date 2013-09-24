package org.crazyit.app.domain;

import java.util.*;
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
public class Name
	implements java.io.Serializable
{
	//定义first属性
	private String first;
	//定义last属性
	private String last;	//无参数的构造器
	public Name()
	{
	}
	//初始化全部属性的构造器
	public Name(String first , String last)
	{
		this.first = first;
		this.last = last;
	}
	
	//first属性的setter和getter方法
	public void setFirst(String first)
	{
		this.first = first;
	}
	public String getFirst()
	{
		return this.first;
	}
	
	//last属性的setter和getter方法
	public void setLast(String last)
	{
		this.last = last;
	}
	public String getLast()
	{
		return this.last;
	}
	
	//重写equals方法，根据first、last进行判断
	public boolean equals(Object obj)
	{
		if (this == obj)
		{
			return true;
		}
		if (obj != null 
			&& obj.getClass() == Name.class)
		{
			Name target = (Name)obj;
			if (target.getFirst().equals(getFirst())
				&& target.getLast().equals(getLast()))
			{
				return true;
			}
		}
		return false;
	}
	//重写hashCode方法，根据first、last计算hashCode值
	public int hashCode()
	{
		return getFirst().hashCode() * 7 
			+ getLast().hashCode();
	}
}