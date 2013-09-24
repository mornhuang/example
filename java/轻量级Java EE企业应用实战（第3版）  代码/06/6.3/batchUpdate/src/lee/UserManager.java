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
	//查询出User表中的所有记录
	ScrollableResults users = session.createQuery("from User")
		.setCacheMode(CacheMode.IGNORE)
		.scroll(ScrollMode.FORWARD_ONLY);
	int count=0;
	//遍历User表中的全部记录
	while ( users.next() )
	{
		User u = (User) users.get(0);
		u.setName("新用户名" + count);
		//当count为20的倍数时，
		//将更新的结果从Session中flush到数据库。
		if ( ++count % 20 == 0 ) 
		{
			session.flush();
			session.clear();
		}
	}
	tx.commit();
	HibernateUtil.closeSession();
}
}