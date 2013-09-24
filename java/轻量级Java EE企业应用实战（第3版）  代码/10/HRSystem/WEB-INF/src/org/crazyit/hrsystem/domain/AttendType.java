package org.crazyit.hrsystem.domain;

import java.io.Serializable;

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
public class AttendType
	implements Serializable
{
	private static final long serialVersionUID = 48L;

	//标识属性
	private Integer id;
	//出勤类型的名称
	private String name;
	//此类出勤对应的罚款
	private double amerce;
	
	//无参数的构造器
	public AttendType()
	{
	}
	//初始化全部属性的构造器
	public AttendType(Integer id , String name , double amerce)
	{
		this.id = id;
		this.name = name;
		this.amerce = amerce;
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

	//amerce属性的setter和getter方法
	public void setAmerce(double amerce)
	{
		this.amerce = amerce;
	}
	public double getAmerce()
	{
		return this.amerce;
	}
}