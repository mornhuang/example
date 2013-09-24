package org.crazyit.app.domain;

import java.util.Set;
import java.util.HashSet;
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

//经理类继承员工类
public class  Manager extends Employee
{
	//定义经理管辖部门的属性
	private String department;
	//和员工保持关联关系的属性
	private Set<Employee> employees = new HashSet<Employee>();

	//无参数的构造器
	public Manager()
	{
	}
	//初始化全部属性的构造器
	public Manager(String department)
	{
		this.department = department;
	}
	
	//department属性的setter和getter方法
	public void setDepartment(String department)
	{
		this.department = department;
	}
	public String getDepartment()
	{
		return this.department;
	}
	
	//employees属性的setter和getter方法
	public void setEmployees(Set<Employee> employees)
	{
		this.employees = employees;
	}
	public Set<Employee> getEmployees()
	{
		return this.employees;
	}
}