package org.crazyit.app.domain;

import java.util.*;
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
public class Category
{
	private Integer id;
	private String name;
	private Date effectiveStartDate;
	private Date effectiveEndDate;
	private Set<Product> products =
		new HashSet<Product>();	//无参数的构造器
	public Category()
	{
	}
	//初始化全部属性的构造器
	public Category(String name , 
		Date effectiveStartDate , Date effectiveEndDate)
	{
		this.name = name;
		this.effectiveStartDate = effectiveStartDate;
		this.effectiveEndDate = effectiveEndDate;
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
	
	//effectiveStartDate属性的setter和getter方法
	public void setEffectiveStartDate(Date effectiveStartDate)
	{
		this.effectiveStartDate = effectiveStartDate;
	}
	public Date getEffectiveStartDate()
	{
		return this.effectiveStartDate;
	}
	
	//effectiveEndDate属性的setter和getter方法
	public void setEffectiveEndDate(Date effectiveEndDate)
	{
		this.effectiveEndDate = effectiveEndDate;
	}
	public Date getEffectiveEndDate()
	{
		return this.effectiveEndDate;
	}
	
	//products属性的setter和getter方法
	public void setProducts(Set<Product> products)
	{
		this.products = products;
	}
	public Set<Product> getProducts()
	{
		return this.products;
	}
	
	//判断两个物品是否相等
	public boolean equals(Object c)
	{		
		if ( this == c )
		{
			return true;
		}
		if(c.getClass() == Category.class 
			&& c.hashCode() == hashCode())
		{
			return true;
		}
		return false;
	}
	
	//生成hashCode的方式以名字的hashCode加上
	//生效开始日期hashCode 再加生效结束日期的hashCode
	public int hashCode()
	{
		int result;
		result = name.hashCode();
		result = 29 * result + (effectiveStartDate != null ?
			effectiveStartDate.hashCode() : 0);
		result = 29 * result + (effectiveEndDate != null ? 
			effectiveEndDate.hashCode() : 0);
		return result;
	}
}