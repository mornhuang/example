package lee;

import org.hibernate.*;
import org.hibernate.transform.*;

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
public class NamedSQLTest
{
	public static void main(String[] args)
	{
		NamedSQLTest test = new NamedSQLTest();
		test.simpleQuery();
		test.query();
		test.callProcedure();
		HibernateUtil.sessionFactory.close();
	}
	
	//执行简单的命名SQL查询
	private void simpleQuery()
	{
		//打开Session和事务
		Session session = HibernateUtil.currentSession();
		Transaction tx = session.beginTransaction();
		//调用命名查询，直接返回结果
		List l = session.getNamedQuery("simpleQuery")
			.list();
		tx.commit();
		HibernateUtil.closeSession();
		//遍历结果集
		for(Iterator it = l.iterator(); it.hasNext() ;)
		{
			//每个集合元素是Student对象
			Student s = (Student)it.next();
			System.out.println(s.getName() + "\t");
		}
	}
	
	//执行命名SQL查询
	private void query()
	{
		//打开Session和事务
		Session session = HibernateUtil.currentSession();
		Transaction tx = session.beginTransaction();
		//调用命名查询，直接返回结果
		List l = session.getNamedQuery("queryTest")
			.setInteger("targetYear" , 2005)
			.list();
		tx.commit();
		HibernateUtil.closeSession();
		//遍历结果集
		for(Iterator it = l.iterator(); it.hasNext() ;)
		{
			//每个集合元素是Student、Enrolment
			//和stuName三个元素的数组
			Object[] objs = (Object[])it.next();
			Student s = (Student)objs[0];
			Enrolment e = (Enrolment)objs[1];
			String stuName = (String)objs[2];
			System.out.println(s.getName() + "\t"
				+ e.getYear() + "\t" + e.getSemester()
				+ "\t=" + e.getCourse().getName() + "=\t" + stuName);
		}
	}
	
//调用存储过程
private void callProcedure()
{
	//打开Session和事务
	Session session = HibernateUtil.currentSession();
	Transaction tx = session.beginTransaction();
	//调用命名查询，直接返回结果
	List l = session.getNamedQuery("callProcedure")
		.list();
	tx.commit();
	HibernateUtil.closeSession();
	//遍历结果集
	for(Iterator it = l.iterator(); it.hasNext() ;)
	{
		//每个集合元素是Student对象
		Student s = (Student)it.next();
		System.out.println(s.getName());
	}
}
}
