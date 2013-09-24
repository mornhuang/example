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
public class BookService
{
	private String[] books = 
		new String[]{
		"疯狂Java讲义" ,
		"轻量级Java EE企业应用实战",
		"经典Java EE企业应用实战",
		"疯狂Ajax讲义"
	};
	
	public String[] getLeeBooks()
	{
		return books;
	}
}
