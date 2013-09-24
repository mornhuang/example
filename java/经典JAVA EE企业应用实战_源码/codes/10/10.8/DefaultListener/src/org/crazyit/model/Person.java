package org.crazyit.model;

import javax.persistence.*;
import org.crazyit.listener.*;
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
public class Person
{
	//实体类的标识属性
	@Id /* 用于修饰标识属性 */
	/* 指定该主键列的主键生成策略 */
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	/* @Column指定该Field映射的列信息，此处指定了列名、长度 */
	@Column(name="person_name" , length=50)
	private String name;
	private String email;
	private String phone;
	//无参数的构造器
	public Person()
	{
	}
	//初始化全部属性的构造器
	public Person(int id , String name 
		, String email , String phone)
	{
		this.id = id;
		this.name = name;
		this.email = email;
		this.phone = phone;
	}

	//id属性的setter和getter方法
	public void setId(int id)
	{
		this.id = id;
	}
	public int getId()
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

	//email属性的setter和getter方法
	public void setEmail(String email)
	{
		this.email = email;
	}
	public String getEmail()
	{
		return this.email;
	}

	//phone属性的setter和getter方法
	public void setPhone(String phone)
	{
		this.phone = phone;
	}
	public String getPhone()
	{
		return this.phone;
	}
}