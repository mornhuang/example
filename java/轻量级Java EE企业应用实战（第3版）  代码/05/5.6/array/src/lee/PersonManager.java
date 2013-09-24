package lee;

import org.hibernate.Transaction;
import org.hibernate.Session;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;

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
public class PersonManager
{
	public static void main(String[] args)
	{
		PersonManager mgr = new PersonManager();
		mgr.createAndStorePerson();
		HibernateUtil.sessionFactory.close();
	}
	//创建并保存Person对象
	private void createAndStorePerson()
	{
		//打开线程安全的session对象
		Session session = HibernateUtil.currentSession();
		//打开事务
		Transaction tx = session.beginTransaction();
		//创建Person对象
		Person yeeku = new Person();
		//为Person对象设置属性
		yeeku.setAge(29);
		yeeku.setName("crazyit.org");
		//创建数组对象
		String[] schools = new String[]
			{"小学" , "中学"};
		//设置数组属性
		yeeku.setSchools(schools);
		session.save(yeeku);
		tx.commit();
		HibernateUtil.closeSession();
	}
}