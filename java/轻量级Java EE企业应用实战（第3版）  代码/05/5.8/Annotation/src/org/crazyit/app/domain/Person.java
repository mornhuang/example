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
	//普通属性
	@Column(name="person_email")
	private String email;
	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name="name" 
			, column=@Column(name="cat_name" , length=35)),
		@AttributeOverride(name="color" 
			, column=@Column(name="cat_color"))
	})
	//组件属性，代表此人拥有的宠物
	private Cat pet;

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
	
	//pet属性的setter和getter方法
	public void setPet(Cat pet)
	{
		this.pet = pet;
	}
	public Cat getPet()
	{
		return this.pet;
	}
}