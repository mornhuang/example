package org.crazyit.jsf;

/**
 * Description:
 * <br/>网站: <a href="http://www.crazyit.org">疯狂Java联盟</a> 
 * <br/>Copyright (C), 2001-2010, Yeeku.H.Lee
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name:
 * <br/>Date:
 * @author  Yeeku.H.Lee kongyeeku@163.com
 * @version  1.0
 */
public class BookBean
{
	private int id;
	private String name;
	private String website;

	//无参数的构造器
	public BookBean()
	{
	}
	//初始化全部属性的构造器
	public BookBean(int id , String name , String website)
	{
		this.id = id;
		this.name = name;
		this.website = website;
	}

	//id属性的setter和getter方法
	public void setId(int id)
	{
		this.id = id;
	}
	public int getId()
	{
		return this.id;
	}

	//name属性的setter和getter方法
	public void setName(String name)
	{
		this.name = name;
	}
	public String getName()
	{
		return this.name;
	}

	//website属性的setter和getter方法
	public void setWebsite(String website)
	{
		this.website = website;
	}
	public String getWebsite()
	{
		return this.website;
	}

}