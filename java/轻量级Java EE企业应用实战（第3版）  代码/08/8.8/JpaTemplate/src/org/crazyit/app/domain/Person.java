package org.crazyit.app.domain;

import javax.persistence.*;
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
@Entity
@Table(name="person_inf")
public class Person
{
	@Id 
	/* 用于修饰标识属性 */
	/* 指定该主键列的主键生成策略 */
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="person_id")
	private Integer id;
	private String name;
	private int age;
	
	//无参数的构造器
	public Person()
	{
	}
	//初始化全部属性的构造器
	public Person(String name , int age)
	{
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
	}	//age属性的setter和getter方法
	public void setAge(int age)
	{
		this.age = age;
	}
	public int getAge()
	{
		return this.age;
	}
}