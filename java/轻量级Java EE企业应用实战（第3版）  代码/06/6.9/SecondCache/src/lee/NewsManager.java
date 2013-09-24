package lee;

import org.hibernate.*;
import org.hibernate.cfg.*;

import java.util.*;

import org.crazyit.app.domain.*;

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
public class NewsManager
{
	static Configuration conf = new Configuration()
		.configure();
	//以Configuration创建SessionFactory
	static SessionFactory sf = conf.buildSessionFactory();
	public static void main(String[] args)
	{
		NewsManager mgr = new NewsManager();
		mgr.secondCacheTest();
		mgr.stat();
	}
	//测试二级缓存
	private void secondCacheTest()
	{
		Session session = sf.getCurrentSession();
		session.beginTransaction();
		List names  = session.createQuery("from News news")
			.list();
		session.getTransaction().commit();
		System.out.println("----------------------");
		//打开第二个Session
		Session sess2 = sf.getCurrentSession();
		sess2.beginTransaction();
		//根据主键加载实体，系统将直接从二级缓存读取
		//因此不会发出查询的SQL语句
		News news = (News)sess2.load(News.class , 1);
		System.out.println(news.getTitle());
		sess2.getTransaction().commit();
	}

	private void stat()
	{
		//----------统计二级缓存----------
		Map cacheEntries = sf.getStatistics()
			//二级缓存的名字默认与持久化类的类名相同
			.getSecondLevelCacheStatistics("org.crazyit.app.domain.News")
			.getEntries();
		System.out.println(cacheEntries);
	}
}
