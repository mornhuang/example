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
public interface CheckBackDao
{
	/**
	 * 根据标识属性来加载CheckBack实例
	 * @param id 需要加载的CheckBack实例的标识属性值
	 * @return 指定标识属性对应的CheckBack实例
	 */
	CheckBack get(Integer id);

	/**
	 * 持久化指定的CheckBack实例
	 * @param checkBack 需要被持久化的CheckBack实例
	 * @return CheckBack实例被持久化后的标识属性值
	 */
	Integer save(CheckBack checkBack);

	/**
	 * 修改指定的CheckBack实例
	 * @param checkBack 需要被修改的CheckBack实例
	 */
	void update(CheckBack checkBack);

	/**
	 * 删除指定的CheckBack实例
	 * @param checkBack 需要被删除的CheckBack实例
	 */
	void delete(CheckBack checkBack);

	/**
	 * 根据标识属性删除CheckBack实例
	 * @param id 需要被删除的CheckBack实例的标识属性值
	 */
	void delete(Integer id);

	/**
	 * 查询全部的CheckBack实例
	 * @return 数据库中全部的CheckBack实例
	 */
	List<CheckBack> findAll();
}
