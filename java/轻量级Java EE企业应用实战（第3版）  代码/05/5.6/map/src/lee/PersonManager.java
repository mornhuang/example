package lee;import org.hibernate.Transaction;
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
		//打开线程安全的session对象
		Session session = HibernateUtil.currentSession();
		//打开事务
		Transaction tx = session.beginTransaction();
		//创建Person对象
		Person yeeku = new Person();
		//为Person对象设置属性
		yeeku.setAge(29);
		yeeku.setName("crazyit.org");
		//创建Map集合
		Map<String ,Float> m = new HashMap<String ,Float>();
		m.put("语文" , 67f);
		m.put("英文" , 45f);
		//设置Map集合属性
		yeeku.setScores(m);
		session.save(yeeku);
		tx.commit();
		HibernateUtil.closeSession();
	}
}