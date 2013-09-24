package lee;

import org.hibernate.Transaction;
import org.hibernate.Session;

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
public class PersonManager
{
	public static void main(String[] args)
	{
		PersonManager mgr = new PersonManager();
		mgr.createAndStorePerson();
		HibernateUtil.sessionFactory.close();
	}
	private void createAndStorePerson()
	{
		Session session = HibernateUtil.currentSession();
		Transaction tx = session.beginTransaction();
		//创建Person对象
		Person yeeku = new Person();
		//为Person对象设置属性
		yeeku.setAge(29);
		yeeku.setFirst("crazyit.org");
		yeeku.setLast("疯狂Java联盟");
		Address a1 = new Address("广州天河");
		a1.setPerson(yeeku);
		Address a2 = new Address("上海虹口");
		a2.setPerson(yeeku);
		session.save(yeeku);
		session.save(a1);
		session.save(a2);		
		tx.commit();
		HibernateUtil.closeSession();
	}
}