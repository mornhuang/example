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
@Table(name="address_table")
public class Address
{
	//标识属性
	@EmbeddedId
	private AddressPk pk;
	//地址详细信息
	private String detail;
	@ManyToOne(fetch=FetchType.EAGER
		,targetEntity=Person.class , cascade=CascadeType.ALL)
	/* 使用@JoinColumn来配置外键列的信息 */
	@JoinColumn(name="person_id", nullable=true)
	private Person person;

	//无参数的构造器
	public Address()
	{
	}
	//初始化全部属性的构造器
	public Address(AddressPk pk , String detail)
	{
		this.pk = pk;
		this.detail = detail;
	}

	//pk属性的setter和getter方法
	public void setPk(AddressPk pk)
	{
		this.pk = pk;
	}
	public AddressPk getPk()
	{
		return this.pk;
	}

	//detail属性的setter和getter方法
	public void setDetail(String detail)
	{
		this.detail = detail;
	}
	public String getDetail()
	{
		return this.detail;
	}

	//person属性的setter和getter方法
	public void setPerson(Person person)
	{
		this.person = person;
	}
	public Person getPerson()
	{
		return this.person;
	}

}