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
public class Dog
	implements Being
{
	private String msg;
	//依赖注入时候必须的setter方法
	public void setMsg(String msg)
	{
		this.msg = msg;
	}
	//实现接口必须实现的testBeing方法
	public void testBeing()
	{
		System.out.println(msg + 
			"	狗爱啃骨头");
	}
}
