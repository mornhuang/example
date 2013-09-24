package lee;

import org.hibernate.*;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import java.util.*;

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
public class CriteriaTest
{
	public static void main(String[] args)
	{
		CriteriaTest criteriaTest = new CriteriaTest();
		criteriaTest.query();
		criteriaTest.queryWithJoin();
		criteriaTest.queryWithFecth();
		HibernateUtil.sessionFactory.close();
	}
	private void query()
	{
		//打开Session和事务
		Session session = HibernateUtil.currentSession();
		Transaction tx = session.beginTransaction();
		//使用createCriteria开始条件查询
		List l = session.createCriteria(Student.class)
			//根据Student的属性进行过滤数据
			.add( Restrictions.gt("name" , "a"))
			.list();
		Iterator it = l.iterator();
		System.out.println("=====简单条件查询获取所有学生记录=====");
		while (it.hasNext())
		{
			Student s = (Student)it.next();
			System.out.println(s.getName());
			Set enrolments = s.getEnrolments();
			Iterator iter = enrolments.iterator();
			System.out.println("=====获取-" + s.getName()
				+ "-的选课记录=====");
			while(iter.hasNext())
			{
				Enrolment e = (Enrolment)iter.next();
				System.out.println(e.getCourse().getName());
			}
		}
		tx.commit();
		HibernateUtil.closeSession();
	}
	//示范根据关联实体的属性过滤数据
	private void queryWithJoin()
	{
		//打开Session和事务
		Session session = HibernateUtil.currentSession();
		Transaction tx = session.beginTransaction();
		//使用createCriteria开始条件查询
		List l = session.createCriteria(Student.class)
			//此处增加限制条件必须是Student已经存在的属性
			.add( Restrictions.gt("studentNumber" , 20050231L))
			//如果要增加对Student的关联类的属性的限制
			//则必须重新createCriteria()
			//如果此关联属性是集合，则只要集合里任意一个对象的属性满足下面条件即可
//			.createCriteria("enrolments")
//			.add( Restrictions.gt("semester" , 2))
			.createAlias("enrolments", "en")
			.add( Restrictions.gt("en.semester" , 2))
			.list();
		Iterator it = l.iterator();
		System.out.println("=====关联条件查询获取所有学生记录=====");
		while (it.hasNext())
		{
			Student s = (Student)it.next();
			System.out.println(s.getName());
			Set enrolments = s.getEnrolments();
			Iterator iter = enrolments.iterator();
			System.out.println("=====获取-" + s.getName()
				+ "-的选课记录=====");
			while(iter.hasNext())
			{
				Enrolment e = (Enrolment)iter.next();
				System.out.println(e.getCourse().getName());
			}
		}
		tx.commit();
		HibernateUtil.closeSession();
	}
	//示范根据关联实体的属性过滤数据
	private void queryWithFecth()
	{
		//打开Session和事务
		Session session = HibernateUtil.currentSession();
		Transaction tx = session.beginTransaction();
		//使用createCriteria开始条件查询
		List l = session.createCriteria(Student.class)
			//此处增加限制条件必须是Student已经存在的属性
			.add( Restrictions.gt("studentNumber" , 20050231L))
			.setFetchMode("enrolments", FetchMode.JOIN)
			.list();
		tx.commit();
		HibernateUtil.closeSession();
		Iterator it = l.iterator();
		System.out.println("=====启用预初始化的条件查询获取所有学生记录=====");
		while (it.hasNext())
		{
			Student s = (Student)it.next();
			System.out.println(s.getName());
			//Session关闭后访问Student的关联实体
			Set enrolments = s.getEnrolments();
			System.out.println(enrolments);
		}
	}
}
