package org.crazyit.app.service.impl;

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
public abstract class Chinese
	implements Person
{
	public Chinese()
	{
		System.out.println("Spring实例化主调bean：Chinese实例...");
	}

	//定义一个抽象方法，该方法将由Spring负责实现
	public abstract Axe getAxe();
	public void useAxe()
	{
		System.out.println("正在使用 " + getAxe()
			+ "砍柴！");
		System.out.println(getAxe().chop());
	}
}