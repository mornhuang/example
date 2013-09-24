package lee;

import org.hibernate.*;
import org.hibernate.cfg.*;

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
public class HibernateUtil
{
	public static final SessionFactory sessionFactory;
	
	static
	{
		try
		{
			//采用默认的hibernate.cfg.xml来启动一个Configuration的实例
			Configuration configuration = new Configuration()
				.configure();
			//由Configuration的实例来创建一个SessionFactory实例
			sessionFactory = configuration.buildSessionFactory();
		}
		catch (Throwable ex)
		{
			// Make sure you log the exception, as it might be swallowed
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}
	
	//ThreadLocal可以隔离多个线程的数据共享，因此不再需要对线程同步	
	public static final ThreadLocal<Session> session
		= new ThreadLocal<Session>();
	
	public static Session currentSession() 
		throws HibernateException
	{
		Session s = session.get();
		//如果该线程还没有Session,则创建一个新的Session
		if (s == null)
		{
			s = sessionFactory.openSession();
			//将获得的Session变量存储在ThreadLocal变量session里
			session.set(s);
		}
		return s;
	}
	
	public static void closeSession() throws HibernateException 
	{
		Session s = session.get();
		if (s != null)
			s.close();
		session.set(null);
	}
}