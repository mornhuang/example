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
	public static void main(String[] args) 
		throws Exception
	{
		//实例化Configuration，这行代码默认加载hibernate.cfg.xml文件
		Configuration conf = new Configuration().configure();
		//以Configuration创建SessionFactory
		SessionFactory sf = conf.buildSessionFactory();
		//打开上下文相关的Session
		Session sess = sf.getCurrentSession();
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
		//Session随事务的提交自动关闭，因此无需手动关闭
	}
}
