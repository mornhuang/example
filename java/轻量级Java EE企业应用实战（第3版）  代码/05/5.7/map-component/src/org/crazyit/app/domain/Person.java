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
	//标识属性
	private Integer id;
	//普通属性age
	private int age;
	//组件属性name
	private Map<String , Name> nicks
		= new HashMap<String , Name>();	
	
	//id属性的setter和getter方法
	public void setId(Integer id)
	{
		this.id = id;
	}
	public Integer getId()
	{
		return this.id;
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
	
	//nicks属性的setter和getter方法
	public void setNicks(Map<String , Name> nicks)
	{
		this.nicks = nicks;
	}
	public Map<String , Name> getNicks()
	{
		return this.nicks;
	}
}