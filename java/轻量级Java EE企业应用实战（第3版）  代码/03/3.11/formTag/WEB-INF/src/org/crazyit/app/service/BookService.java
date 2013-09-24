package org.crazyit.app.service;

import org.crazyit.app.dto.*;
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
	public Book[] getBooks()
	{
		return new Book[]
		{
			new Book("疯狂Java讲义","李刚"),
			new Book("轻量级Java EE企业应用实战","李刚"),
			new Book("经典Java EE企业应用实战","李刚"),
			new Book("疯狂Ajax讲义","李刚")
		};
	}
}
