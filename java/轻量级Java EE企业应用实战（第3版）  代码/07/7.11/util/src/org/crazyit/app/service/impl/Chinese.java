package org.crazyit.app.service.impl;

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
public class Chinese
	implements Person
{
	private Axe axe;
	private int age;
	private List schools;
	private Map scores;
	private Set axes;
	
	public Chinese()
	{
	}
	//设值注入axe属性所需的setter方法
	public void setAxe(Axe axe)
	{
		this.axe = axe;
	}
	//设值注入age属性所需的setter方法
	public void setAge(int age)
	{
		this.age = age;
	}
	public int getAge()
	{
		return this.age;
	}
	//schools属性依赖注入必须的setter方法
	public void setSchools(List schools)
	{
		this.schools = schools;
	}
	//scores属性依赖注入必须的setter方法
	public void setScores(Map scores)
	{
		this.scores = scores;
	}
	//axes属性依赖注入必须的setter方法
	public void setAxes(Set axes)
	{
		this.axes = axes;
	}
	//实现Person接口的useAxe方法
	public void useAxe()
	{
		//调用axe的chop()方法，
		//表明Person对象依赖于axe对象
		System.out.println(axe.chop());
		System.out.println("age属性值：" + age);
		System.out.println(schools);
		System.out.println(scores);
		System.out.println(axes);
	}
}