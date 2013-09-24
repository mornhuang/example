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
public class CheckBackDaoHibernate
	extends YeekuHibernateDaoSupport
	implements CheckBackDao
{
	/**
	 * 根据标识属性来加载CheckBack实例
	 * @param id 需要加载的CheckBack实例的标识属性值
	 * @return 指定标识属性对应的CheckBack实例
	 */
	public CheckBack get(Integer id)
	{
		return getHibernateTemplate()
			.get(CheckBack.class , id);
	}

	/**
	 * 持久化指定的CheckBack实例
	 * @param checkBack 需要被持久化的CheckBack实例
	 * @return CheckBack实例被持久化后的标识属性值
	 */
	public Integer save(CheckBack checkBack)
	{
		return (Integer)getHibernateTemplate()
			.save(checkBack);
	}

	/**
	 * 修改指定的CheckBack实例
	 * @param checkBack 需要被修改的CheckBack实例
	 */
	public void update(CheckBack checkBack)
	{
		getHibernateTemplate()
			.update(checkBack);
	}

	/**
	 * 删除指定的CheckBack实例
	 * @param checkBack 需要被删除的CheckBack实例
	 */
	public void delete(CheckBack checkBack)
	{
		getHibernateTemplate()
			.delete(checkBack);
	}

	/**
	 * 根据标识属性删除CheckBack实例
	 * @param id 需要被删除的CheckBack实例的标识属性值
	 */
	public void delete(Integer id)
	{
		getHibernateTemplate()
			.delete(get(id));
	}

	/**
	 * 查询全部的CheckBack实例
	 * @return 数据库中全部的CheckBack实例
	 */
	public List<CheckBack> findAll()
	{
		return (List<CheckBack>)getHibernateTemplate()
			.find("from CheckBack");
	}
}
