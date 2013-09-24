package org.crazyit.model;

import javax.persistence.*;
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
@Entity
@Table(name="person_table")
/* 指定使用复合主键类是Name */
@IdClass(Name.class)
public class Person
{
	//两个@Id定义了联合主键
	@Id 
	private String first;
	@Id 
	private String last;
	/* @Column指定该Field映射的列信息，此处指定了列名、长度 */
	@Column(name="person_email")
	private String email;

	//无参数的构造器
	public Person()
	{
	}
	//初始化全部属性的构造器
	public Person(String first , String last , String email)
	{
		this.first = first;
		this.last = last;
		this.email = email;
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

	//email属性的setter和getter方法
	public void setEmail(String email)
	{
		this.email = email;
	}
	public String getEmail()
	{
		return this.email;
	}
}