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
	}
	//构造注入所需的带参数的构造器
	public Chinese(Axe axe)
	{
		this.axe = axe;
	}
	//实现Person接口的useAxe方法
	public void useAxe()
	{
		//调用axe的chop()方法
		//表明Person对象依赖于axe对象
		System.out.println(axe.chop());
	}
}