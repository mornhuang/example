package org.crazyit.app.service;
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
public class ExampleBean
{
	//定义一个Person型的属性
	private Person person
		= new Person();	
	//person属性的getter方法
	public Person getPerson()
	{
		return this.person;
	}
}