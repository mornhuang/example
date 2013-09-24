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
{
	//定义标识属性
	private Integer id;
	//定义Person实例的name属性
	private String name;
	//定义Person实例的age属性
	private int age;
	//定义Person和MyEvent之间的关联关系
	private MyEvent myEvent;
	//定义一个集合属性
	private Set<String> emails = new HashSet<String>();
	
	//无参数的构造器
	public Person()
	{
	}
	//初始化全部属性的构造器
	public Person(Integer id , String name , int age)
	{
		this.id = id;
		this.name = name;
		this.age = age;
	}
	
	//id属性的setter和getter方法
	public void setId(Integer id)
	{
		this.id = id;
	}
	public Integer getId()
	{
		return this.id;
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
		this.age = age;
	}
	public int getAge()
	{
		return this.age;
	}
	
	//myEvent属性的setter和getter方法
	public void setMyEvent(MyEvent myEvent)
	{
		this.myEvent = myEvent;
	}
	public MyEvent getMyEvent()
	{
		return this.myEvent;
	}
	
	//emails属性的setter和getter方法
	public void setEmails(Set<String> emails)
	{
		this.emails = emails;
	}
	public Set<String> getEmails()
	{
		return this.emails;
	}
}