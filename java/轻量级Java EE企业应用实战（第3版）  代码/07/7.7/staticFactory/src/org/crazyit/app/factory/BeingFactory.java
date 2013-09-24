package org.crazyit.app.factory;

import org.crazyit.app.service.impl.*;
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
public class BeingFactory
{
	/**
	 * 获取Being实例的静态工厂方法
	 * @param arg 决定返回哪个Being实例的参数
	 */
	public static Being getBeing(String arg) 
	{
		//调用此静态方法的参数为dog，则返回Dog实例
		if (arg.equalsIgnoreCase("dog"))
		{
			return new Dog();
		}
		//否则返回Cat实例
		else
		{
			return new Cat();
		}
	}
}
