package org.crazyit.app.domain;


	
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

public class User

{

	//定义标识属性

	private Integer id;

	//定义User实例的名称

	private String name;

	//定义User实例的年龄
	private int age;
	//定义User实例的国别
	private String nationality;
	

	//无参数的构造器
	public User()
	{
	}
	//初始化全部属性的构造器
	public User(String name , int age , String nationality)
	{
		this.name = name;
		this.age = age;
		this.nationality = nationality;
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
	
	//nationality属性的setter和getter方法
	public void setNationality(String nationality)
	{
		this.nationality = nationality;
	}
	public String getNationality()
	{
		return this.nationality;
	}
}