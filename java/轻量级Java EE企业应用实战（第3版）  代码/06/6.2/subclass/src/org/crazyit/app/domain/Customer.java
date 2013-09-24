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
 
//顾客类继承了Person类
public class Customer extends Person
{
	//顾客的评论信息
	private String comments;
	//和员工保持关联关系的属性
	private Employee employee;
	//无参数的构造器
	public Customer()
	{
	}
	//初始化comments属性的构造器
	public Customer(String comments)
	{
		this.comments = comments;
	}
	
	//comments属性的setter和getter方法
	public void setComments(String comments)
	{
		this.comments = comments;
	}
	public String getComments()
	{
		return this.comments;
	}
	
	//employee属性的setter和getter方法
	public void setEmployee(Employee employee)
	{
		this.employee = employee;
	}
	public Employee getEmployee()
	{
		return this.employee;
	}
}