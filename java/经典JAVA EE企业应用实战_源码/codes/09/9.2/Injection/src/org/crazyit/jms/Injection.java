package org.crazyit.jms;

import javax.ejb.*;
import javax.jms.*;

import org.crazyit.service.*;
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
@MessageDriven(activationConfig=
/* 指定MDB所监听消息目的的类型 */
{@ActivationConfigProperty(propertyName="destinationType"
	, propertyValue="javax.jms.Queue"), 
@ActivationConfigProperty(propertyName="acknowledgeMode"
	, propertyValue="Auto-acknowledge"),
/* 指定MDB所监听的消息目的的JNDI绑定名 */
@ActivationConfigProperty(propertyName="destination" 
	, propertyValue="MessageQueue")}
/* 指定MDB所监听的消息目的的JNDI绑定名 */
,mappedName="MessageQueue"
)
public class Injection 
	implements MessageListener
{
	@EJB(name="StudentBean")
	private Student student;
	//实现onMessage方法——当JMS消息被送达消息目的时，
	//该方法被触发
	public void onMessage(Message msg)
	{
		try
		{
			if (msg instanceof MapMessage)
			{
				MapMessage map = (MapMessage)msg;
				String name = map.getString("name");
				String gender = map.getString("gender");
				int age = map.getInt("age");
				//调用Session Bean的方法添加学生。
				student.add(name , gender , age);
			}
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}
}
