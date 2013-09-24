package org.crazyit.app.domain;

import java.util.*;

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
public class MyEvent
{
	//定义标识属性
	private Integer id;
	//定义MyEvent对象的名称
	private String title;
	//定义MyEvent对象的发生时间
	private Date happenDate;
	//定义MyEvent对象和Person对象的关联
	private Set<Person> actors = new HashSet<Person>();
	
	//无参数的构造器
	public MyEvent()
	{
	}
	//初始化全部属性的构造器
	public MyEvent(Integer id , String title , Date happenDate)
	{
		this.id = id;
		this.title = title;
		this.happenDate = happenDate;
	}
	
	//id属性的setter和getter方法
	public void setId(Integer id)
	{
		this.id = id;
	}
	public Integer getId()
	{
		return this.id;
	}
	
	//title属性的setter和getter方法
	public void setTitle(String title)
	{
		this.title = title;
	}
	public String getTitle()
	{
		return this.title;
	}
	
	//happenDate属性的setter和getter方法
	public void setHappenDate(Date happenDate)
	{
		this.happenDate = happenDate;
	}
	public Date getHappenDate()
	{
		return this.happenDate;
	}
	
	//actors属性的setter和getter方法
	public void setActors(Set<Person> actors)
	{
		this.actors = actors;
	}
	public Set<Person> getActors()
	{
		return this.actors;
	}
}