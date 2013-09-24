package lee;

import org.hibernate.Transaction;
import org.hibernate.Session;

import java.util.Date;
import java.util.Set;
import java.util.HashSet;

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
		mgr.testPerson();
		HibernateUtil.sessionFactory.close();
	}
	//保存Person和Address对象
	private void testPerson()
	{
		Session session = HibernateUtil.currentSession();
		Transaction tx = session.beginTransaction();
		//创建一个Person对象
		Person p = new Person();
		//设置Person的Name为Yeeku字符串
		p.setName("Yeeku");
		p.setAge(29);
		session.save(p);
		//创建一个Address对象
		Address a = new Address("广州天河");
		//设置Person对象的Address属性
		p.setAddress(a);
		//创建2个School对象
		School s1 = new School("疯狂Java项目冲刺班");
		School s2 = new School("疯狂Java训练营");
		//保存2个School实体
		session.save(s1);
		session.save(s2);
		//设置Address对象和两个School的关联关系
		a.getSchools().add(s1);
		a.getSchools().add(s2);
		tx.commit();
		HibernateUtil.closeSession();
	}
}
