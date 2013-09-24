package org.crazyit.app.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.context.ApplicationEvent;

import org.crazyit.app.event.*;
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
public class EmailNotifier
	implements ApplicationListener
{
	//该方法会在容器发生事件时自动触发
	public void onApplicationEvent(ApplicationEvent evt)
	{
		if (evt instanceof EmailEvent)
		{
			//只处理EmailEvent，发送email通知...
			EmailEvent emailEvent = (EmailEvent)evt;
			System.out.println("需要发送邮件的接收地址  "
				+ emailEvent.getAddress());
			System.out.println("需要发送邮件的邮件正文  "
				+ emailEvent.getText());
		}
		else
		{
			//容器内置事件不作任何处理
			System.out.println("容器本身的事件:   " + evt);
		}
	}
}