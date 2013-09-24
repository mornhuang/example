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
public class Person
	implements java.io.Serializable
{
	//定义first属性，作为标识属性的成员
	private String first;
	//定义last属性，作为标识属性的成员
	private String last;
	//普通属性age
	private int age;
	//记录关联实体
	private Set<Address> addresses
		= new HashSet<Address>();
	
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
	
	//age属性的setter和getter方法
	public void setAge(int age)
	{
		this.age = age;
	}
	public int getAge()
	{
		return this.age;
	}

	//addresses属性的setter和getter方法
	public void setAddresses(Set<Address> addresses)
	{
		this.addresses = addresses;
	}
	public Set<Address> getAddresses()
	{
		return this.addresses;
	}

	
	//重写equals方法，根据first、last进行判断
	public boolean equals(Object obj)
	{
		if (this == obj)
		{
			return true;
		}
		if (obj != null 
			&& obj.getClass() == Person.class)
		{
			Person target = (Person)obj;
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
		return getFirst().hashCode() * 13
			+ getLast().hashCode();
	}
}