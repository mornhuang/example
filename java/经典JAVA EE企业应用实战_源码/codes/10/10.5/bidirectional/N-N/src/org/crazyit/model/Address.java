package org.crazyit.model;

import javax.persistence.*;
import java.util.*;
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
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int addressid;
	//地址详细信息
	private String detail;
	//N－N关联关系，使用Set来保存关联实体
	/* 设置了mappedBy属性表明Address实体不控制关联关系，
	因此不能增加@JoinTable和@JoinColumn修饰*/
	@ManyToMany(cascade=CascadeType.ALL, mappedBy="addresses"
		, fetch=FetchType.LAZY , targetEntity=Person.class)
	private Set<Person> persons
		= new HashSet<Person>();
	//无参数的构造器
	public Address()
	{
	}
	//初始化detail属性的构造器
	public Address(String detail)
	{
		this.detail = detail;
	}
	//addressid属性的setter和getter方法
	public void setAddressid(int addressid)
	{
		this.addressid = addressid;
	}
	public int getAddressid()
	{
		return this.addressid;
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

	//persons属性的setter和getter方法
	public void setPersons(Set<Person> persons)
	{
		this.persons = persons;
	}
	public Set<Person> getPersons()
	{
		return this.persons;
	}
}