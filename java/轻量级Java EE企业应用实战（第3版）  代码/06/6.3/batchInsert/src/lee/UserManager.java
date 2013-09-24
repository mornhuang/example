package lee;

import org.hibernate.Transaction;
import org.hibernate.Session;

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
public class UserManager
{
	public static void main(String[] args)throws Exception
	{
		UserManager mgr = new UserManager();
		mgr.addUsers();
		HibernateUtil.sessionFactory.close();
	}
	private void addUsers()throws Exception
	{
		//打开Session
		Session session = HibernateUtil.currentSession();
		//开始事务
		Transaction tx = session.beginTransaction();
		//循环100000次，插入100000条记录
		for (int i = 0 ; i < 100000 ; i++ )
		{
			//创建User实例
			User u1 = new User();
			u1.setName("xxxxx" + i);
			u1.setAge(i);
			u1.setNationality("china");
			//在Session级别缓存User实例
			session.save(u1);
			//每当累加器是20的倍数时，将Session中数据刷入数据库，
			//并清空Session缓存。
			if (i % 20 == 0)
			{
				session.flush();
				session.clear();
			}
		}
		//提交事务
		tx.commit();
		//关闭事务
		HibernateUtil.closeSession();
	}
}