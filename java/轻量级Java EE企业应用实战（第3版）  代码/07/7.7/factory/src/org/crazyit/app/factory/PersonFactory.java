package org.crazyit.app.factory;

import org.crazyit.app.service.*;
import org.crazyit.app.service.impl.*;
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
public class PersonFactory
{
	/**
	 * 获得Person实例的实例工厂方法
	 * @param ethnic 决定返回哪个Person实例的参数
	 * @return 返回Person实例
	 */
	public Person getPerson(String ethnic)
	{
		if (ethnic.equalsIgnoreCase("chin"))
		{
			return new Chinese();
		}
		else
		{
			return new American();
		}
	}
}
