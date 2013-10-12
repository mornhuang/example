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
public class ModelDaoHibernate 
	extends HibernateDaoSupport implements ModelDao
{
	/**
	 * 根据id查找型号
	 * @param id 需要查找的型号id
	 */
	public Model get(Integer id)
	{
		return (Model)getHibernateTemplate()
			.get(Model.class , id);
	}
	/**
	 * 增加型号
	 * @param model 需要增加的型号
	 */
	public void save(Model model)
	{
		getHibernateTemplate().save(model);
	}

	/**
	 * 修改型号
	 * @param model 需要修改的型号
	 */
	public void update(Model model)
	{
		getHibernateTemplate().saveOrUpdate(model);
	}

	/**
	 * 删除型号
	 * @param id 需要删除的型号id
	 */
	public void delete(Integer id)
	{
		getHibernateTemplate().delete(get(id));
	}

	/**
	 * 删除型号
	 * @param model 需要删除的型号
	 */
	public void delete(Model model)
	{
		getHibernateTemplate().delete(model);
	}

	/**
	 * 查询全部型号
	 * @return 返回全部型号
	 */
	public List<Model> findAll()
	{
		return (List<Model>)getHibernateTemplate()
			.find("from Model");
	}

	/**
	 * 根据品牌查询型号
	 * @param brand 需要查询的品牌
	 * @return 该品牌对应的全部的型号
	 */
	public List<Model> findByBrand(String brand)
	{
		return (List<Model>)getHibernateTemplate()
			.find("from Model as m where m.brand.name=?" , brand);
	}

	/**
	 * 根据型号名查询型号
	 * @param model 需要查询的型号名
	 * @return 该型号名对应的型号
	 */
	public Model findByModel(String model)
	{
		List<Model> ml = (List<Model>)getHibernateTemplate()
			.find("from Model as m where m.name=?" , model);
		if (ml != null && ml.size() >= 0)
		{
			return ml.get(0);
		}
		return null;
	}
}
