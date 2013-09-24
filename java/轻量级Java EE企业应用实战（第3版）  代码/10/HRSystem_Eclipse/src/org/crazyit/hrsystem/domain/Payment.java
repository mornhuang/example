package org.crazyit.hrsystem.domain;

import java.io.Serializable;
import java.sql.Date;

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
public class Payment
	implements Serializable
{
	private static final long serialVersionUID = 48L;
	//标识属性
	private Integer id;
	private String payMonth;
	//发薪的数量
	private double amount;
	//领薪的员工
	private Employee employee;
	
	//无参数的构造器
	public Payment()
	{
	}
	//初始化全部属性的构造器
	public Payment(Integer id , String payMonth , 
		double amount , Employee employee)
	{
		this.id = id;
		this.payMonth = payMonth;
		this.amount = amount;
		this.employee = employee;
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

	//payMonth属性的setter和getter方法
	public void setPayMonth(String payMonth)
	{
		this.payMonth = payMonth;
	}
	public String getPayMonth()
	{
		return this.payMonth;
	}

	//amount属性的setter和getter方法
	public void setAmount(double amount)
	{
		this.amount = amount;
	}
	public double getAmount()
	{
		return this.amount;
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