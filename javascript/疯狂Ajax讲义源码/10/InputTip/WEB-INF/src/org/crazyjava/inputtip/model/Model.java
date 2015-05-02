package org.crazyjava.inputtip.model;

import java.io.Serializable;

/**
 * Description:
 * <br/>网站: <a href="http://www.crazyjava.org">疯狂Java联盟</a> 
 * <br/>Copyright (C), 2001-2010, Yeeku.H.Lee
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name:
 * <br/>Date:
 * @author  Yeeku.H.Lee kongyeeku@163.com
 * @version  1.0
 */
public class Model implements Serializable
{
	//Model的标识属性，该属性映射数据库表的主键
	private Integer id;
	//型号名
	private String name;
	//型号描述
	private String desc;
	//该型号所属的品牌
	private Brand brand;

	//无参数的构造器
	public Model()
	{
	}
	//初始化全部属性的构造器
	public Model(Integer id , String name , String desc)
	{
		this.id = id;
		this.name = name;
		this.desc = desc;
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

	//desc属性的setter和getter方法
	public void setDesc(String desc)
	{
		this.desc = desc;
	}
	public String getDesc()
	{
		return this.desc;
	}

	//brand属性的setter和getter方法
	public void setBrand(Brand brand)
	{
		this.brand = brand;
	}
	public Brand getBrand()
	{
		return this.brand;
	}
}