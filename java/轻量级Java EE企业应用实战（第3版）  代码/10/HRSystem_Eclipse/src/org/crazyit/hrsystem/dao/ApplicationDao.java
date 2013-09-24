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
public interface ApplicationDao
{
	/**
	 * 根据标识属性来加载Application实例
	 * @param id 需要加载的Application实例的标识属性值
	 * @return 指定标识属性对应的Application实例
	 */
	Application get(Integer id);

	/**
	 * 持久化指定的Application实例
	 * @param application 需要被持久化的Application实例
	 * @return Application实例被持久化后的标识属性值
	 */
	Integer save(Application application);

	/**
	 * 修改指定的Application实例
	 * @param application 需要被修改的Application实例
	 */
	void update(Application application);

	/**
	 * 删除指定的Application实例
	 * @param application 需要被删除的Application实例
	 */
	void delete(Application application);

	/**
	 * 根据标识属性删除Application实例
	 * @param id 需要被删除的Application实例的标识属性值
	 */
	void delete(Integer id);

	/**
	 * 查询全部的Application实例
	 * @return 数据库中全部的Application实例
	 */
	List<Application> findAll();

	/**
	 * 根据员工查询未处理的异动申请
	 * @param emp 需要查询的员工
	 * @return 该员工对应的未处理的异动申请
	 */ 
	List<Application> findByEmp(Employee emp);
}
