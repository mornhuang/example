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
public class Person
{
	/* 指定使用复合主键类是Name */
	@EmbeddedId
	@AttributeOverrides({
		@AttributeOverride(name="first"
			, column=@Column(name="person_first")),
		@AttributeOverride(name="last"
			, column=@Column(name="person_last" , length=20))
	})
	private Name name;
	/* @Column指定该Field映射的列信息，此处指定了列名、长度 */
	@Column(name="person_email")
	private String email;

	//无参数的构造器
	public Person()
	{
	}
	//初始化全部属性的构造器
	public Person(Name name , String email)
	{
		this.name = name;
		this.email = email;
	}

	//name属性的setter和getter方法
	public void setName(Name name)
	{
		this.name = name;
	}
	public Name getName()
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
}