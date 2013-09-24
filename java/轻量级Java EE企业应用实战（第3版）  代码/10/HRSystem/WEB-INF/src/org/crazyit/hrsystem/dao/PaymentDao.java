package org.crazyit.hrsystem.dao;

import java.util.*; 

import org.crazyit.hrsystem.domain.*;

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
public interface PaymentDao
{
	/**
	 * 根据标识属性来加载Payment实例
	 * @param id 需要加载的Payment实例的标识属性值
	 * @return 指定标识属性对应的Payment实例
	 */
	Payment get(Integer id);

	/**
	 * 持久化指定的Payment实例
	 * @param payment 需要被持久化的Payment实例
	 * @return Payment实例被持久化后的标识属性值
	 */
	Integer save(Payment payment);

	/**
	 * 修改指定的Payment实例
	 * @param payment 需要被修改的Payment实例
	 */
	void update(Payment payment);

	/**
	 * 删除指定的Payment实例
	 * @param payment 需要被删除的Payment实例
	 */
	void delete(Payment payment);

	/**
	 * 根据标识属性删除Payment实例
	 * @param id 需要被删除的Payment实例的标识属性值
	 */
	void delete(Integer id);

	/**
	 * 查询全部的Payment实例
	 * @return 数据库中全部的Payment实例
	 */
	List<Payment> findAll();

	/**
	 * 根据员工查询月结薪水
	 * @return 该员工对应的月结薪水集合
	 */ 
	List<Payment> findByEmp(Employee emp);

	/**
	 * 根据员工和发薪月份来查询月结薪水
	 * @param payMonth 发薪月份
	 * @param emp 领薪的员工
	 * @return 指定员工、指定月份的月结薪水
	 */ 
	Payment findByMonthAndEmp(String payMonth , Employee emp);
}
