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
	//定义一个int型的属性
	private int integerProperty;
	//定义一个double型的属性
	private double doubleProperty;

	//integerProperty属性的setter和getter方法
	public void setIntegerProperty(int integerProperty)
	{
		this.integerProperty = integerProperty;
	}
	public int getIntegerProperty()
	{
		return this.integerProperty;
	}

	//doubleProperty属性的setter和getter方法
	public void setDoubleProperty(double doubleProperty)
	{
		this.doubleProperty = doubleProperty;
	}
	public double getDoubleProperty()
	{
		return this.doubleProperty;
	}
}