package org.crazyit.business;

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
public class Person
{
	private Integer id;
	private String name;

	//无参数的构造器
	public Person()
	{
	}
	//初始化全部属性的构造器
	public Person(Integer id , String name)
	{
		this.id = id;
		this.name = name;
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
	public boolean equals(Object target)
	{
		if (this == target)
		{
			return true;
		}
		if (target.getClass() == Person.class)
		{
			Person p = (Person)target;
			if (p.getId() == this.getId())
			{
				return true;
			}
		}
		return false;
	}
	public int hashCode()
	{
		return this.getId();
	}
}