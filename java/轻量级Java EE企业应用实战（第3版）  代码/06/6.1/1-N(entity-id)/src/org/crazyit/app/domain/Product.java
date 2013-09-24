package org.crazyit.app.domain;

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
	//标识属性
	private Integer productId;
	//产品名
	private String name;
	
	//无参数的构造器
	public Product()
	{
	}
	//初始化全部属性的构造器
	public Product(String name)
	{
		this.name = name;
	}

	//productId属性的setter和getter方法
	public void setProductId(Integer productId)
	{
		this.productId = productId;
	}
	public Integer getProductId()
	{
		return this.productId;
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
}