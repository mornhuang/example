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
		mgr.deleteUsers();
		HibernateUtil.sessionFactory.close();
	}
private void deleteUsers()throws Exception
{
	//打开Session
	Session session = HibernateUtil.currentSession();
	//开始事务
	Transaction tx = session.beginTransaction();
	//定义批量删除的HQL语句
	String hqlDelete = "delete User";
	//执行删除
	int deletedEntities = session.createQuery( hqlDelete )
		.executeUpdate();
	//提交事务
	tx.commit();
	HibernateUtil.closeSession();
}
}