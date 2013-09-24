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
	//下面是系列集合属性
	private List<String> schools;
	private Map scores;
	private Map<String , Axe> phaseAxes;
	private Properties health;
	private Set axes;
	private String[] books;
	
	public Chinese()
	{
		System.out.println("Spring实例化主调bean：Chinese实例...");
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
	//phaseAxes属性依赖注入必须的setter方法
	public void setPhaseAxes(Map<String , Axe> phaseAxes)
	{
		this.phaseAxes = phaseAxes;
	}
	//health属性依赖注入必须的setter方法
	public void setHealth(Properties health)
	{
		this.health = health;
	}
	//axes属性依赖注入必须的setter方法
	public void setAxes(Set axes)
	{
		this.axes = axes;
	}
	//books属性依赖注入必须的setter方法
	public void setBooks(String[] books)
	{
		this.books = books;
	}
	
	//访问上面全部的集合属性
	public void test()
	{
		System.out.println(schools);
		System.out.println(scores);
		System.out.println(phaseAxes);
		System.out.println(health);
		System.out.println(axes);
		System.out.println(java.util.Arrays.toString(books));
	}
}