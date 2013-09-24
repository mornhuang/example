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
public class ManagerDaoHibernate
	extends YeekuHibernateDaoSupport
	implements ManagerDao
{
	/**
	 * 根据标识属性来加载Manager实例
	 * @param id 需要加载的Manager实例的标识属性值
	 * @return 指定标识属性对应的Manager实例
	 */
	public Manager get(Integer id)
	{
		return getHibernateTemplate()
			.get(Manager.class , id);
	}

	/**
	 * 持久化指定的Manager实例
	 * @param manager 需要被持久化的Manager实例
	 * @return Manager实例被持久化后的标识属性值
	 */
	public String save(Manager manager)
	{
		return (String)getHibernateTemplate()
			.save(manager);
	}

	/**
	 * 修改指定的Manager实例
	 * @param manager 需要被修改的Manager实例
	 */
	public void update(Manager manager)
	{
		getHibernateTemplate()
			.update(manager);
	}

	/**
	 * 删除指定的Manager实例
	 * @param manager 需要被删除的Manager实例
	 */
	public void delete(Manager manager)
	{
		getHibernateTemplate()
			.delete(manager);
	}

	/**
	 * 根据标识属性删除Manager实例
	 * @param id 需要被删除的Manager实例的标识属性值
	 */
	public void delete(Integer id)
	{
		getHibernateTemplate()
			.delete(get(id));
	}

	/**
	 * 查询全部的Manager实例
	 * @return 数据库中全部的Manager实例
	 */
	public List<Manager> findAll()
	{
		return (List<Manager>)getHibernateTemplate()
			.find("from Manager");
	}

	/**
	 * 根据用户名和密码查询经理
	 * @param emp 包含指定用户名、密码的经理
	 * @return 符合指定用户名和密码的经理
	 */ 
	public List<Manager> findByNameAndPass(Manager mgr)
	{
		return (List<Manager>)getHibernateTemplate()
			.find("from Manager m where m.name = ? and m.pass=?"
			, mgr.getName() , mgr.getPass());
	}

	/**
	 * 根据用户名查找经理
	 * @param name 经理的名字
	 * @return 名字对应的经理
	 */
	public Manager findByName(String name)
	{
		List<Manager> ml = (List<Manager>)getHibernateTemplate()
			.find("from Manager m where m.name=?" , name);
		if (ml != null && ml.size() > 0)
		{
			return ml.get(0);
		}
		return null;
	}
}
