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
//		//创建Person对象
//		Person wawa = new Person();
//		//为Person对象设置属性
//		wawa.setAge(21);
//		wawa.setName("crazyit.org");
//		//创建Set集合
//		SortedSet<String> s = new TreeSet<String>();
//		s.add("Wild Java Camp");
//		s.add("Sun SCJP");
//		//设置Set集合属性
//		wawa.setTrainings(s);
//		session.save(wawa);
		
		Person p = (Person)session.get(Person.class , 1);
		p.getTrainings().add("CCNP");
		
		tx.commit();
		HibernateUtil.closeSession();
	}
}