package org.crazyit.hrsystem.domain;

import java.io.Serializable;
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
public class Employee
	implements Serializable
{
	private static final long serialVersionUID = 48L;
	//标识属性
	private Integer id;
	//员工姓名
	private String name;
	//员工密码
	private String pass;
	//员工工资
	private double salary;
	//员工对应的经理
	private Manager manager;
	//员工对应的出勤记录
	private Set<Attend> attends = new HashSet<Attend>();
	//员工对应的工资支付记录
	private Set<Payment> payments = new HashSet<Payment>();
	

	//无参数的构造器
	public Employee()
	{
	}
	//初始化全部属性的构造器
	public Employee(Integer id , String name , String pass , 
		double salary , Manager manager , 
		Set<Attend> attends , Set<Payment> payments)
	{
		this.id = id;
		this.name = name;
		this.pass = pass;
		this.salary = salary;
		this.manager = manager;
		this.attends = attends;
		this.payments = payments;
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

	//pass属性的setter和getter方法
	public void setPass(String pass)
	{
		this.pass = pass;
	}
	public String getPass()
	{
		return this.pass;
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

	//manager属性的setter和getter方法
	public void setManager(Manager manager)
	{
		this.manager = manager;
	}
	public Manager getManager()
	{
		return this.manager;
	}

	//attends属性的setter和getter方法
	public void setAttends(Set<Attend> attends)
	{
		this.attends = attends;
	}
	public Set<Attend> getAttends()
	{
		return this.attends;
	}

	//payments属性的setter和getter方法
	public void setPayments(Set<Payment> payments)
	{
		this.payments = payments;
	}
	public Set<Payment> getPayments()
	{
		return this.payments;
	}
 
	//重写equals()方法，只要name、pass相同的员工即认为相等。
	public boolean equals(Object obj)
	{
		if (this == obj)
		{
			return true;
		}
		if (obj != null &&
			obj.getClass() == Employee.class)
		{
			Employee employee = (Employee)obj;
			return this.getName().equals(employee.getName())
				&& this.getPass().equals(employee.getPass());
		}
		return false;
	}
	//根据员工的name、pass来计算hashCode值
	public int hashCode()
	{
		return name.hashCode()
			+ pass.hashCode() * 17;
	}
}