package lee;

import org.hibernate.Transaction;
import org.hibernate.Session;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
public class HqlQuery
{
	public static void main(String[] args)throws Exception
	{
		HqlQuery mgr = new HqlQuery();
		mgr.findByNamedQuery();
		HibernateUtil.sessionFactory.close();
	}
private void findByNamedQuery()
	throws Exception
{
	//打开Hibernate的Session和事务
	Session sess = HibernateUtil.currentSession();
	Transaction tx = sess.beginTransaction();
	System.out.println("===执行命名查询===");
	//执行命名查询
	List pl = sess.getNamedQuery("myNamedQuery")
		//根据HQL语句里参数索引为参数赋值
		.setInteger(0 , 20)
		.list();
	//迭代输出查询得到的每个Person对象
	for (Iterator pit = pl.iterator() ; pit.hasNext(); )
	{
		Person p = ( Person )pit.next();
		System.out.println(p.getName());
	}
	//提交事务、关闭Session
	tx.commit();
	HibernateUtil.closeSession();
}
}
