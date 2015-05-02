package org.crazyjava.inputtip.dao.impl;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import org.crazyjava.inputtip.model.*;
import org.crazyjava.inputtip.dao.*;
/**
 * Description:
 * <br/>网站: <a href="http://www.crazyjava.org">疯狂Java联盟</a> 
 * <br/>Copyright (C), 2001-2010, Yeeku.H.Lee
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name:
 * <br/>Date:
 * @author  Yeeku.H.Lee kongyeeku@163.com
 * @version  1.0
 */
public class BrandDaoHibernate 
	extends HibernateDaoSupport implements BrandDao
{
	/**
	 * 根据id查找品牌
	 * @param id 需要查找的品牌id
	 */
	public Brand get(Integer id)
	{
		//借助于HibernateTemplate的get方法返回特定主键对应记录
		return (Brand)getHibernateTemplate()
			.get(Brand.class , id);
	}
	/**
	 * 增加品牌
	 * @param brand 需要增加的品牌
	 */
	public void save(Brand brand)
	{
		//借助于HibernateTemplate的save方法增加记录
		getHibernateTemplate().save(brand);
	}

	/**
	 * 修改品牌
	 * @param brand 需要修改的品牌
	 */
	public void update(Brand brand)
	{
		//借助于HibernateTemplate的saveOrUpdate更新记录
		getHibernateTemplate().saveOrUpdate(brand);
	}

	/**
	 * 删除品牌
	 * @param id 需要删除的品牌id
	 */
	public void delete(Integer id)
	{
		//借助于HibernateTemplate的delete方法删除特定主键对应记录
		getHibernateTemplate().delete(get(id));
	}

	/**
	 * 删除品牌
	 * @param brand 需要删除的品牌
	 */
	public void delete(Brand brand)
	{
		//借助于HibernateTemplate的delete方法删除指定实体
		getHibernateTemplate().delete(brand);
	}

	/**
	 * 根据前缀查询品牌
	 * @param prefix 前缀
	 * @return 该前缀对应的品牌
	 */
	public List<Brand> findAll()
	{
		//借助于HibernateTemplate的find()方法查询记录
		return (List<Brand>)getHibernateTemplate()
			.find("from Brand");
	}
}
