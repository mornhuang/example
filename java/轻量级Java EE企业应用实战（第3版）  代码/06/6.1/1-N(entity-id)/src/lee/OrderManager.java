package lee;

import org.hibernate.Transaction;
import org.hibernate.Session;

import java.util.*;
import org.crazyit.app.domain.*;
/**
 * Description:
 * <br/>ÍøÕ¾: <a href="http://www.crazyit.org">·è¿ñJavaÁªÃË</a> 
 * <br/>Copyright (C), 2001-2012, Yeeku.H.Lee
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name:
 * <br/>Date:
 * @author  Yeeku.H.Lee kongyeeku@163.com
 * @version  1.0
 */
public class OrderManager
{
	public static void main(String[] args)
	{
		OrderManager mgr = new OrderManager();
		mgr.createAndStoreOrder();
		HibernateUtil.sessionFactory.close();
	}
	private void createAndStoreOrder()
	{
		Session sess = HibernateUtil.currentSession();
		Transaction tx = sess.beginTransaction();
		
		Order order = new Order(new Date());
		Product p1 = new Product("¼üÅÌ");
		Product p2 = new Product("ÏÔÊ¾Æ÷");
		OrderItem item1 = new OrderItem(order , p1 , 50);
		OrderItem item2 = new OrderItem(order , p2 , 18);
			
		sess.save(order);
		sess.save(p1);
		sess.save(p2);
		sess.save(item1);
		sess.save(item2);

		tx.commit();
		HibernateUtil.closeSession();
	}
}