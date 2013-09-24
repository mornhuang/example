package org.crazyit.app.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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
public class Order
{	
	//标识属性
	private Integer orderId;
	//订单日期
	private Date orderDate;
	//关联的的订单项
	private Set<OrderItem> items
		= new HashSet<OrderItem>();

	//无参数的构造器
	public Order()
	{
	}
	//初始化全部属性的构造器
	public Order(Date orderDate)
	{
		this.orderDate = orderDate;
	}

	//orderId属性的setter和getter方法
	public void setOrderId(Integer orderId)
	{
		this.orderId = orderId;
	}
	public Integer getOrderId()
	{
		return this.orderId;
	}

	//orderDate属性的setter和getter方法
	public void setOrderDate(Date orderDate)
	{
		this.orderDate = orderDate;
	}
	public Date getOrderDate()
	{
		return this.orderDate;
	}

	//items属性的setter和getter方法
	public void setItems(Set<OrderItem> items)
	{
		this.items = items;
	}
	public Set<OrderItem> getItems()
	{
		return this.items;
	}
}