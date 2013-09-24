package lee;

import org.hibernate.*;
import org.hibernate.transform.*;
import org.hibernate.type.*;

import java.util.*;

import org.crazyit.app.domain.*;
import org.crazyit.app.vo.*;

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
public class NativeSQLTest
{
	public static void main(String[] args)
	{
		NativeSQLTest test = new NativeSQLTest();
//		test.scalarQuery();
//		test.entityQuery();
//		test.multiEntityQuery();
//		test.beanQuery();
		test.joinQuery();
		HibernateUtil.sessionFactory.close();
	}
	//执行标量查询
	public void scalarQuery()
	{
		//打开Session和事务
		Session session = HibernateUtil.currentSession();
		Transaction tx = session.beginTransaction();
		String sqlString = "select stu.* from student_inf as stu";
		List l = session.createSQLQuery(sqlString)
			//指定查询name和student_id两个数据列
			.addScalar("name" , StandardBasicTypes.STRING)
			.addScalar("student_id" , StandardBasicTypes.INTEGER)
			//返回标量值列表
			.list();
		for (Iterator it = l.iterator(); it.hasNext() ; )
		{
			//每个集合元素都是一个数组，数组元素是name、student_id两列值
			Object[] row = (Object[])it.next();
			System.out.println(row[0] + "\t" + row[1]);
		}
		tx.commit();
		HibernateUtil.closeSession();
	}
	
	//执行实体SQL查询
	public void entityQuery()
	{
		//打开Session和事务
		Session session = HibernateUtil.currentSession();
		Transaction tx = session.beginTransaction();
		String sqlString = "select * from enrolment_inf where year=:year";
		List l = session.createSQLQuery(sqlString)
			//指定将查询的记录行转换成Student实体
			.addEntity(Enrolment.class)
			//为SQL字符串的参数设置值
			.setInteger("year" , 2005)
			.list();
		for (Iterator it = l.iterator(); it.hasNext() ; )
		{
			//每个集合元素都是一个Enrolment对象
			Enrolment e = (Enrolment)it.next();
			System.out.println(e.getStudent().getName() + "\t" + e.getCourse().getName());
		}
		tx.commit();
		HibernateUtil.closeSession();
	}
	
	//执行返回多个实体的SQL查询
	public void multiEntityQuery()
	{
		//打开Session和事务
		Session session = HibernateUtil.currentSession();
		Transaction tx = session.beginTransaction();
		String sqlString = "select s.*,e.*,c.* "
			+ "from student_inf s,enrolment_inf e,course_inf c "
			+ "where s.student_id = e.student_id "
			+ "and e.course_code = c.course_code";
		List l = session.createSQLQuery(sqlString)
			//指定将查询的记录行转换成Student实体
			.addEntity("s", Student.class)
			.addEntity("e", Enrolment.class)
			.addEntity("c", Course.class)
			.list();
		//提交事务，关闭Session
		tx.commit();
		HibernateUtil.closeSession();
		//因为数据已经全部被选出，故程序可以遍历列表中的数据
		for (Iterator it = l.iterator(); it.hasNext() ; )
		{
			//每个集合元素都是Student、Enrolment
			//和Course所组成的数组
			Object[] objs = (Object[])it.next();
			Student s = (Student)objs[0];
			Enrolment e = (Enrolment)objs[1];
			Course c = (Course)objs[2];
			System.out.println(s.getName() + "\t" 
				+ e.getYear() + "\t" + e.getSemester()
				+ "\t" + c.getName());
		}
	}
	
	//执行返回普通JavaBean的SQL查询
	public void beanQuery()
	{
		//打开Session和事务
		Session session = HibernateUtil.currentSession();
		Transaction tx = session.beginTransaction();
		String sqlString = "select s.name stuName, c.name courseName "
			+ "from student_inf s,enrolment_inf e,course_inf c "
			+ "where s.student_id = e.student_id "
			+ "and e.course_code = c.course_code ";
		List l = session.createSQLQuery(sqlString)
			//指定将查询的记录行转换成StudentCourse对象
			.setResultTransformer(Transformers
				.aliasToBean(StudentCourse.class))
			.list();
		//提交事务，关闭Session
		tx.commit();
		HibernateUtil.closeSession();
		//因为数据已经全部被选出，故程序可以遍历列表中的数据
		for (Iterator it = l.iterator(); it.hasNext() ; )
		{
			//每个集合元素都是StudentCourse对象
			StudentCourse sc = (StudentCourse)it.next();
			System.out.println(sc.getStuName() + "\t" 
				+ sc.getCourseName());
		}
	}
	
	//使用关联的原生SQL查询
	public void joinQuery()
	{
		//打开Session和事务
		Session session = HibernateUtil.currentSession();
		Transaction tx = session.beginTransaction();
		String sqlString = "select s.* , e.* from student_inf s , "
			+ "enrolment_inf e where s.student_id=e.student_id";
		List l = session.createSQLQuery(sqlString)
			.addEntity("s", Student.class)
			.addJoin("e" , "s.enrolments")
			.list();
		//提交事务，关闭Session
		tx.commit();
		HibernateUtil.closeSession();
		//因为数据已经全部被选出，故程序可以遍历列表中的数据
		for (Iterator it = l.iterator(); it.hasNext() ; )
		{
			//每个集合元素都是Student、Enrolment组成的数组
			Object[] objs = (Object[])it.next();
			Student s = (Student)objs[0];
			Enrolment e = (Enrolment)objs[1];
			System.out.println(s.getName() + "\t" + e.getYear());
		}
	}
}
