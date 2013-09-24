package org.crazyit.app.domain;

import java.util.List;
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
	//普通属性name
	private String name;
	//普通属性age
	private int age;
	//集合属性，保留该对象关联的学校
	private Collection<String> schools =
		new ArrayList<String>();
	
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
	
	//schools属性的setter和getter方法
	public void setSchools(Collection<String> schools)
	{
		this.schools = schools;
	}
	public Collection<String> getSchools()
	{
		return this.schools;
	}
}