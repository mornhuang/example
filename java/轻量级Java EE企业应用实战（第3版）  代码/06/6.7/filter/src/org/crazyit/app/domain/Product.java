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

public class Product

{

	//定义标识属性
	private Integer id;

	//定义产品名
	
	private String name;

	//定义股票号属性，该属性可标识该产品
	
	private int stockNumber;

	//定义生效的时间
	
	private Date effectiveStartDate;
	
	//定义失效时间
	
	private Date effectiveEndDate;

	//定义该产品所属的种类
	
	private Set<Category> categories =
		new HashSet<Category>();

	//无参数的构造器
	public Product()
	{
	}
	//初始化全部属性的构造器
	public Product(String name , int stockNumber ,
		Date effectiveStartDate, Date effectiveEndDate)
	{
		this.name = name;
		this.stockNumber = stockNumber;
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
	
	//stockNumber属性的setter和getter方法
	public void setStockNumber(int stockNumber)
	{
		this.stockNumber = stockNumber;
	}
	public int getStockNumber()
	{
		return this.stockNumber;
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
	
	//categories属性的setter和getter方法
	public void setCategories(Set<Category> categories)
	{
		this.categories = categories;
	}
	public Set<Category> getCategories()
	{
		return this.categories;
	}	public boolean equals(Object obj)
	{
		if (this == obj)
		{
			return true;
		}
		if (obj != null 
			&& obj.getClass() == Product.class)
		{
			Product target = (Product)obj;
			if (target.getStockNumber() == stockNumber)
			{
				return true;
			}
		}
		return false;
	}
	
	//根据stockNumber来生成hashCode方法
	public int hashCode()
	{
		return stockNumber;
	}
	
	//为产品添加一个所属类型
	public void addCategory(Category category)
	{
		if ( category == null )
		{
			return;
		}
		categories.add(category);
		category.getProducts().add(this);
	}
}