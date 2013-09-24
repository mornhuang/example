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
public class Chinese
	implements Person
{
	private Axe axe;
	//默认的构造器
	public Chinese()
	{
		System.out.println("Spring实例化主调Bean：Chinese实例...");
	}
	//设值注入所需的setter方法
	public void setAxe(Axe axe)
	{
		System.out.println("Spring执行依赖关系注入...");
		this.axe = axe;
	}
	//实现Person接口的useAxe方法
	public void useAxe()
	{
		System.out.println(axe.chop());
	}
}