package org.crazyit.model;

import java.util.*;

import javax.persistence.*;

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

//员工类继承了Person类
@Entity
@AssociationOverride(name="address", 
	joinColumns=@JoinColumn(name="employee_address"))
public class Employee extends Person
{
	//定义该员工的职位属性
	private String title;
	//定义该员工的工资属性
	private double salary;
	//和顾客保持关联关系的属性
	@OneToMany(cascade=CascadeType.ALL 
		, mappedBy="employee" , targetEntity=Customer.class)
	private Set<Customer> customers = new HashSet<Customer>();
	//无参数的构造器
	public Employee()
	{
	}
	//初始化全部属性的构造器
	public Employee(String title , double salary)
	{
		this.title = title;
		this.salary = salary;
	}

	//title属性的setter和getter方法
	public void setTitle(String title)
	{
		this.title = title;
	}
	public String getTitle()
	{
		return this.title;
	}

	//salary属性的setter和getter方法
	public void setSalary(double salary)
	{
		this.salary = salary;
	}
	public double getSalary()
	{
		return this.salary;
	}

	//customers属性的setter和getter方法
	public void setCustomers(Set<Customer> customers)
	{
		this.customers = customers;
	}
	public Set<Customer> getCustomers()
	{
		return this.customers;
	}
}