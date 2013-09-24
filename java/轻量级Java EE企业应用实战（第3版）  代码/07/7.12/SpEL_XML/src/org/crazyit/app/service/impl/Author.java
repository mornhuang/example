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
public class Author
	implements Person
{
	private Integer id;
	private String name;
	private List<String> books;
	private Axe axe;

	public void setAxe(Axe axe)
	{
		this.axe = axe;
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

	//name属性的setter和getter方法
	public void setName(String name)
	{
		this.name = name;
	}
	public String getName()
	{
		return this.name;
	}

	//books属性的setter和getter方法
	public void setBooks(List<String> books)
	{
		this.books = books;
	}
	public List<String> getBooks()
	{
		return this.books;
	}

	public void useAxe()
	{
		System.out.println("我是"
			+ name 
			+ "，正在砍柴\n"
			+ axe.chop());
	}
}