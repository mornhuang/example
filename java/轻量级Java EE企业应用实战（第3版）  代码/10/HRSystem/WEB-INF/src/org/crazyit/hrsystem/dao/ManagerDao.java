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
public interface ManagerDao
{
	/**
	 * 根据标识属性来加载Manager实例
	 * @param id 需要加载的Manager实例的标识属性值
	 * @return 指定标识属性对应的Manager实例
	 */
	Manager get(Integer id);

	/**
	 * 持久化指定的Manager实例
	 * @param manager 需要被持久化的Manager实例
	 * @return Manager实例被持久化后的标识属性值
	 */
	String save(Manager manager);

	/**
	 * 修改指定的Manager实例
	 * @param manager 需要被修改的Manager实例
	 */
	void update(Manager manager);

	/**
	 * 删除指定的Manager实例
	 * @param manager 需要被删除的Manager实例
	 */
	void delete(Manager manager);

	/**
	 * 根据标识属性删除Manager实例
	 * @param id 需要被删除的Manager实例的标识属性值
	 */
	void delete(Integer id);

	/**
	 * 查询全部的Manager实例
	 * @return 数据库中全部的Manager实例
	 */
	List<Manager> findAll();

	/**
	 * 根据用户名和密码查询经理
	 * @param emp 包含指定用户名、密码的经理
	 * @return 符合指定用户名和密码的经理
	 */ 
	List<Manager> findByNameAndPass(Manager mgr);

	/**
	 * 根据用户名查找经理
	 * @param name 经理的名字
	 * @return 名字对应的经理
	 */
	Manager findByName(String name);
}
