package org.crazyit.hrsystem.dao.impl;

import java.util.*; 

import org.crazyit.hrsystem.domain.*;
import org.crazyit.common.hibernate3.support.*;
import org.crazyit.hrsystem.dao.*;

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
public class EmployeeDaoHibernate
	extends YeekuHibernateDaoSupport
	implements EmployeeDao
{
	/**
	 * 根据标识属性来加载Employee实例
	 * @param id 需要加载的Employee实例的标识属性值
	 * @return 指定标识属性对应的Employee实例
	 */
	public Employee get(Integer id)
	{
		return getHibernateTemplate()
			.get(Employee.class , id);
	}

	/**
	 * 持久化指定的Employee实例
	 * @param employee 需要被持久化的Employee实例
	 * @return Employee实例被持久化后的标识属性值
	 */
	public Integer save(Employee employee)
	{
		return (Integer)getHibernateTemplate()
			.save(employee);
	}

	/**
	 * 修改指定的Employee实例
	 * @param employee 需要被修改的Employee实例
	 */
	public void update(Employee employee)
	{
		getHibernateTemplate()
			.update(employee);
	}

	/**
	 * 删除指定的Employee实例
	 * @param employee 需要被删除的Employee实例
	 */
	public void delete(Employee employee)
	{
		getHibernateTemplate()
			.delete(employee);
	}

	/**
	 * 根据标识属性删除Employee实例
	 * @param id 需要被删除的Employee实例的标识属性值
	 */
	public void delete(Integer id)
	{
		getHibernateTemplate()
			.delete(get(id));
	}

	/**
	 * 查询全部的Employee实例
	 * @return 数据库中全部的Employee实例
	 */
	public List<Employee> findAll()
	{
		return (List<Employee>)getHibernateTemplate()
			.find("from Employee");
	}

	/**
	 * 根据用户名和密码查询员工
	 * @param emp 包含指定用户名、密码的员工
	 * @return 符合指定用户名和密码的员工集合
	 */ 
	public List<Employee> findByNameAndPass(Employee emp)
	{
		return (List<Employee>)getHibernateTemplate()
			.find("from Employee p where p.name = ? and p.pass=?"
			, emp.getName() , emp.getPass()); 
	}

	/**
	 * 根据用户名查询员工
	 * @param name 员工的用户名
	 * @return 符合用户名的员工
	 */ 
	public Employee findByName(String name)
	{
		List<Employee> emps = (List<Employee>)getHibernateTemplate()
			.find("from Employee where name = ? " , name);
		if (emps!= null && emps.size() >= 1)
		{
			return emps.get(0);
		}
		return null;
	}

	/**
	 * 根据经理查询员工
	 * @param mgr 经理
	 * @return 该经理对应的所有员工
	 */ 
	public List<Employee> findByMgr(Manager mgr)
	{
		return (List<Employee>)getHibernateTemplate()
			.find("from Employee as e where "
			+ "e.manager = ?" , mgr);
	}
}
