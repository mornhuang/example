package org.crazyit.auction.eao.impl;

import java.util.*;
import javax.persistence.*;
import javax.annotation.*;

import org.crazyit.auction.eao.Eao;
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
public abstract class CrazyItEao
	implements Eao 
{
	@PersistenceContext 
	private EntityManager em;
	/**
	 * 查找实体
	 * @param <T> 动态传入实体类
	 * @param entityClass 实体类
	 * @param pk 主键
	 * @return 根据指定主键返回的实体
	 */
	public <T> T get(Class <T> entityClass, Object primaryKey)
	{
		T obj = em.find(entityClass, primaryKey);
		return obj;
	}
	/**
	 * 保存实体
	 * @param entity 需要保存的实体
	 */
	public void save(Object entity)
	{
		em.persist(entity);
	}
	/**
	 * 更新实体
	 * @param entity 需要保存的实体
	 */
	public void update(Object entity)
	{
		em.merge(entity);
	}
	/**
	 * 删除实体
	 * @param entityClass 需要删除实体类
	 * @param pk 需要删除的实体主键
	 */
	public void delete(Class entityClass, Object primaryKey)
	{
		em.remove(em.getReference(entityClass, primaryKey));
	}
	
	public <T> List<T> getResultList(Class<T> entityClass 
		, String whereJpql 
		, LinkedHashMap<String, String> orderBy
		, Object... args)
	{
		//获取实体类的实体名。默认情况下，实体名和类名相同
		String entityName = entityClass.getSimpleName();
		//创建查询
		Query query = em.createQuery("select o from "+ entityName
			+ " as o " + whereJpql + buildOrderby(orderBy));
		//为查询字符串中参数设置值
		for (int i = 0 ; i < args.length ; i++)
		{
			query.setParameter(i + 1 , args[i]);
		}
		//返回结果集
		return (List<T>)query.getResultList();
	}
	public <T> List<T> getResultList(Class<T> entityClass 
		, String whereJpql 
		, int firstResult 
		, int maxResult 
		, LinkedHashMap<String, String> orderBy
		, Object... args)
	{
		//获取实体类的实体名。默认情况下，实体名和类名相同
		String entityName = entityClass.getSimpleName();
		//创建查询
		Query query = em.createQuery("select o from "+ entityName
			+ " as o " + whereJpql + buildOrderby(orderBy));
		//为查询字符串中参数设置值
		for (int i = 0 ; i < args.length ; i++)
		{
			query.setParameter(i + 1 , args[i]);
		}
		//对查询结果集进行分页
		query.setMaxResults(maxResult).setFirstResult(firstResult);
		//返回结果集
		return (List<T>)query.getResultList();
	}
	
	/**
	 * 构建排序子句
	 * @param orderby LinkedHashMap对象，每个key-value对指定一个排序条件
	 */ 
	private static String buildOrderby(LinkedHashMap<String , String>
		 orderby)
	{
		StringBuffer out = new StringBuffer("");
		if(orderby != null && orderby.size() > 0)
		{
			//添加order by 子句
			out.append(" order by ");
			//遍历LinkedHashMap中的每个key-value对，
			//每个key-value对生成一个排序条件
			for(String key : orderby.keySet())
			{
				out.append("o." + key + " " + orderby.get(key));
				out.append(",");
			}
			out.deleteCharAt(out.length()-1);
		}
		return out.toString();
	}
}
