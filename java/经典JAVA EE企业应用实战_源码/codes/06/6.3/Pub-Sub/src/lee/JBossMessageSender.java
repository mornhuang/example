package lee;

import javax.jms.*;
import javax.naming.*;
import java.util.Properties;

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
public class JBossMessageSender
{	 
	public void sendMessage() 
		throws NamingException, JMSException
	{
		//定义JBoss中默认连接工厂的JNDI
		final String CONNECTION_FACTORY_JNDI = "ConnectionFactory";
		//获取JNDI服务所需的Context
		Context ctx = getInitialContext();
		//通过JNDI查找获取连接工厂
		ConnectionFactory connFactory = (ConnectionFactory)
			ctx.lookup(CONNECTION_FACTORY_JNDI); 
		//通过JNDI查找获取消息目的
		Destination dest = (Destination)ctx.lookup("MessageTopic");
		//连接工厂创建连接
		Connection conn = connFactory.createConnection();
		//JMS连接创建JMS会话
		Session session = conn.createSession(false/*不是事务性会话*/
			, Session.AUTO_ACKNOWLEDGE);
		//JMS会话创建消息生产者
		MessageProducer sender = session.createProducer(dest);
		//设置消息生产者生产出来的消息的传递模式、有效时间。
		sender.setDeliveryMode(DeliveryMode.PERSISTENT);
		sender.setTimeToLive(20000);
		//通过JMS会话创建一个文本消息
		TextMessage msg = session.createTextMessage();
		//msg.setStringProperty("ConType","txt");
		//设置消息内容
		msg.setText("Hello");
		//发送消息
		sender.send(msg);
		msg.setText("Welcome to JMS");
		//再次发送消息
		sender.send(msg);
		//关闭资源
		session.close();
		conn.close();
	}
	//工具方法，用来获取命名服务的Context对象
	private Context getInitialContext()
	{
		final String INIT_FACTORY = 
			"org.jnp.interfaces.NamingContextFactory";
		final String SERVER_URL = "jnp://localhost:1099";
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
			System.err.println("不能连接JBoss Server在:" + SERVER_URL);
			ne.printStackTrace();
		}
		return ctx;
	}
	public static void main(String[] args)
		throws Exception
	{
		JBossMessageSender mp = new JBossMessageSender();
		mp.sendMessage();
	}
}

