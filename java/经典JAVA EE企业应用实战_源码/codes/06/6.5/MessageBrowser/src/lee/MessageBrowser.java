package lee;

import javax.jms.*;
import javax.naming.*;
import java.util.*;
/**
 * Description:
 * <br/>网站: <a href="http://www.crazyit.org">疯狂Java联盟</a> 
 * <br/>Copyright (C), 2001-2010, Yeeku.H.Lee
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name:
 * <br/>Date:
 * @author  Yeeku.H.Lee kongyeeku@163.com
 * @version  1.0
 */
public class MessageBrowser
{	 
	public void browseMessage()
		throws JMSException , NamingException
	{
		//定义WebLogic默认连接工厂的JNDI
		final String CONNECTION_FACTORY_JNDI
			= "weblogic.jms.ConnectionFactory";
		//获取JNDI服务所需的Context
		Context ctx = getInitialContext();
		//通过JNDI查找获取连接工厂
		ConnectionFactory connFactory = (ConnectionFactory)
			ctx.lookup(CONNECTION_FACTORY_JNDI);
		//通过JNDI查找获取消息目的	
		Destination dest = (Destination)ctx.lookup("MessageQueue");
		//连接工厂创建连接
		Connection conn = connFactory.createConnection();
		//启动JMS连接，让它开始传输JMS消息
		conn.start();
		//JMS连接创建JMS会话
		Session session = conn.createSession(false/*不是事务性会话*/
			, Session.AUTO_ACKNOWLEDGE);
		//以JMS会话根据消息队列来创建队列浏览者
		QueueBrowser browser = session.createBrowser(
			(javax.jms.Queue)dest);
		//获取消息队列中所有消息
		Enumeration em = browser.getEnumeration();
		//列出消息队列中的所有消息
		while(em.hasMoreElements())
		{
			TextMessage msg = (TextMessage)em.nextElement();
			System.out.println(msg.getText());
		}
		session.close();
		conn.close();
	}
	//工具方法，用来获取命名服务的Context对象
	private Context getInitialContext()
	{
		final String INIT_FACTORY = 
			"weblogic.jndi.WLInitialContextFactory";
		final String SERVER_URL = "t3://localhost:7001";
		Context ctx = null;
		try
		{
			Properties props = new Properties();
			props.put(Context.INITIAL_CONTEXT_FACTORY 
				, INIT_FACTORY);
			props.put(Context.PROVIDER_URL , SERVER_URL);
			ctx = new InitialContext(props);
		}
		catch(NamingException ne)
		{
			System.err.println("不能连接WebLogic Server在:"
				+ SERVER_URL);
			ne.printStackTrace();
		}
		return ctx;
	}
	public static void main(String[] args)
		throws Exception
	{
		MessageBrowser mb = new MessageBrowser();
		mb.browseMessage();
	}
}