package org.crazyit.eao.impl;

import java.util.*;
import javax.ejb.*;
import javax.annotation.*;
import javax.persistence.*;

import org.crazyit.eao.*;
import org.crazyit.model.*;

/**
 * Description:
 * <br/>网站: <a href="http://www.crazyit.org">疯狂Java联盟</a> 
 * <br/>Copyright (C), 2001-2010, Yeeku.H.Lee
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name:
 * <br/>Date:
 * @author  Yeeku.H.Lee kongyeeku@163.com
 * @version  1.0
 */
@Stateless(name="newsEao")
public class NewsEaoBean
	implements NewsEao
{
	//依赖注入EntityManager组件
	@PersistenceContext(unitName="newsUnit")
	private EntityManager em;
	/**
	 * 根据id查找News实体
	 * @param id 需要查找的News的id
	 */ 
	public News get(Integer id)
	{
		return em.find(News.class , id);
	}
	/**
	 * 增加News实体
	 * @param news 需要增加的News实体
	 */	  
	public void save(News news)
	{
		em.persist(news);
	}
	/**
	 * 修改News实体
	 * @param news 需要修改的News实体
	 */
	public void update(News news)
	{
		em.merge(news);
	}
	/**
	 * 删除News实体
	 * @param id 需要删除的News的id
	 */ 
	public void delete(Integer id)
	{
		em.remove(get(id));
	}
	/**
	 * 删除News实体
	 * @param news 需要删除的News实体
	 */
	public void delete(News news)
	{
		em.remove(news);
	}
	/**
	 * 查询全部的News实体
	 * @return 获得全部的News实体
	 */ 
	public List<News> findAll()
	{
		return (List<News>)em.createQuery("select news from News as news")
			.getResultList();
	}
}
