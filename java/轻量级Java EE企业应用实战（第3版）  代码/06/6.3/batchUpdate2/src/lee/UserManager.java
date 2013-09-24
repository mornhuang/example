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
		mgr.updateUsers();
		HibernateUtil.sessionFactory.close();
	}
	private void updateUsers()throws Exception
	{
		//打开Session
		Session session = HibernateUtil.currentSession();
		//开始事务
		Transaction tx = session.beginTransaction();
		//定义批量更新的HQL语句
		String hqlUpdate = "update User set name = :newName";
		//执行更新
		int updatedEntities = session.createQuery( hqlUpdate )
			.setString( "newName", "新名字" )
			.executeUpdate();
		//提交事务
		tx.commit();
		HibernateUtil.closeSession();
	}
}