package org.crazyit.util;

import javax.persistence.*;
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
public class EntityManagerUtil
{
	//保存系统中的EntityManagerFactory
	private static final EntityManagerFactory emf; 
	//使用ThreadLocal来保证EntityManager的线程安全
	private static final ThreadLocal<EntityManager> threadLocal;
	/**初始化*/
	static 
	{
		//初始化EntityManagerFactory对象
		emf = Persistence.createEntityManagerFactory("newsUnit");
		threadLocal = new ThreadLocal<EntityManager>();
	}
	//通过ThreadLocal获取EntityManager对象
	public static EntityManager getEntityManager() 
	{
		//获取当前线程关联的EntityManager对象
		EntityManager em = threadLocal.get();
		//如果当前线程关联的EntityManager为null，或没有打开
		if (em == null || !em.isOpen())
		{
			//创建新的EntityManager
			em = emf.createEntityManager();
			threadLocal.set(em);
		}
		return em;
	}
	//关闭EntityManager对象
	public static void closeEntityManager()
	{
		EntityManager em = threadLocal.get();
		threadLocal.set(null);
		if (em != null)
		{
			em.close();
		}
	}
	//开始事务
	public static void beginTransaction() 
	{
		getEntityManager().getTransaction().begin();
	}
	//提交事务
	public static void commit() 
	{
		getEntityManager().getTransaction().commit();
	}
	//创建查询
	public static Query createQuery(String jpql)
	{
		return getEntityManager().createQuery(jpql);
	}
}