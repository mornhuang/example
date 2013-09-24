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
public class OrderItem 
	implements java.io.Serializable
{
	//下面3个属性将作为联合主键
	//定义关联的Order实体
	private Order order;
	//定义关联的Product实体
	private Product product;
	//该订单项订购的产品数量
	private int count;

	//无参数的构造器
	public OrderItem()
	{
	}
	//初始化全部属性的构造器
	public OrderItem(Order order , Product product , int count)
	{
		this.order = order;
		this.product = product;
		this.count = count;
	}

	//order属性的setter和getter方法
	public void setOrder(Order order)
	{
		this.order = order;
	}
	public Order getOrder()
	{
		return this.order;
	}

	//product属性的setter和getter方法
	public void setProduct(Product product)
	{
		this.product = product;
	}
	public Product getProduct()
	{
		return this.product;
	}

	//count属性的setter和getter方法
	public void setCount(int count)
	{
		this.count = count;
	}
	public int getCount()
	{
		return this.count;
	}

}