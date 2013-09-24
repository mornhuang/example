package lee;

import org.hibernate.*;
import org.hibernate.cfg.*;
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
public class NewsManager
{
	public static void main(String[] args) throws Exception
	{
		//实例化Configuration，不加载任何配置文件
		Configuration conf = new Configuration()
			//通过addClass方法添加持久化类
			.addClass(org.crazyit.app.domain.News.class)
			//通过setProperty设置Hibernate的连接属性。
			.setProperty("hibernate.connection.driver_class" , "com.mysql.jdbc.Driver")
			.setProperty("hibernate.connection.url" , "jdbc:mysql://localhost/hibernate")
			.setProperty("hibernate.connection.username" , "root")
			.setProperty("hibernate.connection.password" , "32147")
			.setProperty("hibernate.c3p0.max_size" , "20")
			.setProperty("hibernate.c3p0.min_size" , "1")
			.setProperty("hibernate.c3p0.timeout" , "5000")
			.setProperty("hibernate.c3p0.max_statements" , "100")
			.setProperty("hibernate.c3p0.idle_test_period" , "3000")
			.setProperty("hibernate.c3p0.acquire_increment" , "2")
			.setProperty("hibernate.c3p0.validate" , "true")
			.setProperty("hibernate.dialect" , "org.hibernate.dialect.MySQLInnoDBDialect")
			.setProperty("hibernate.hbm2ddl.auto" , "create");
		//以Configuration创建SessionFactory
		SessionFactory sf = conf.buildSessionFactory();
		//实例化Session
		Session sess = sf.openSession();
		//开始事务
		Transaction tx = sess.beginTransaction();
		//创建消息实例
		News n = new News();
		//设置消息标题和消息内容
		n.setTitle("疯狂Java联盟成立了");
		n.setContent("疯狂Java联盟成立了，"
			+ "网站地址http://www.crazyit.org");
		//保存消息
		sess.save(n);
		//提交事务
		tx.commit();
		//关闭Session
		sess.close();
	}
}
