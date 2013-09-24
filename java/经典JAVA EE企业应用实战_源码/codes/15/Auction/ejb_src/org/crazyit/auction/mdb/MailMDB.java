package org.crazyit.auction.mdb;

import javax.jms.*;
import javax.ejb.*;

import org.crazyit.auction.util.*;

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
	, propertyValue="AuctionQueue")
}
/* 让MDB的Bean实现类间接地实现MessageListener接口 */
,messageListenerInterface=javax.jms.MessageListener.class 
/* 指定MDB所监听的消息目的的JNDI绑定名 */
,mappedName="AuctionQueue"
)
public class MailMDB 
{
	//实现onMessage方法——当JMS消息被送达消息目的时，
	//该方法被触发
	public void onMessage(Message rawMsg)
	{
		try
		{
			if (rawMsg instanceof MapMessage)
			{
				//将消息强制转换为MapMessage
				MapMessage msg = (MapMessage)rawMsg;
				String mailTo = msg.getString("mailTo");
				String bidUser = msg.getString("bidUser");
				String itemName = msg.getString("itemName");
				//准备发送邮件
				SimpleMailSender sender =
					new SimpleMailSender();
				sender.setFrom("spring_test@sina.com");
				sender.setTo(mailTo);
				sender.setSubject("竞拍通知");
				sender.setContent("Dear "
					+ bidUser
					+ ", 谢谢你参与竞价，你的竞价的物品的是: "
					+ itemName);
				sender.send();
			}
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}
}

