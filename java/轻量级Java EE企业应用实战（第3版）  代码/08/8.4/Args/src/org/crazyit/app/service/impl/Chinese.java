package org.crazyit.app.service.impl;

import org.springframework.stereotype.*;

import java.util.*;

import org.crazyit.app.service.*;
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
@Component
public class Chinese 
	implements Person
{
	//实现Person接口的sayHello()方法
	public String sayHello(String name)
	{
		//返回简单的字符串
		return name + " Hello , Spring AOP";
	}
	public void eat(String food 
		, Date time)
	{
		System.out.println("我正在吃" + food
			+ ",现在时间是:" + time);
	}
}