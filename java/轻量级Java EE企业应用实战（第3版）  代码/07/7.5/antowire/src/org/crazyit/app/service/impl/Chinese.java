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
	private Dog dog;
	//dog属性的setter方法
	public void setDog(Dog dog)
	{
		this.dog = dog;
	}
	public Dog getDog()
	{
		return dog;
	}
	public void test()
	{
		System.out.println("我是一个普通人,养了一条猎狗：" 
			+ getDog().run());
	}
}
