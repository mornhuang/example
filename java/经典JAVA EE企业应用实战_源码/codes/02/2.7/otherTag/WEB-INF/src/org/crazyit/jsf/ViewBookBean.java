package org.crazyit.jsf;

import java.util.*;
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
public class ViewBookBean
{
	public List<BookBean> getBooks()
	{
		//添加5本图书
		List<BookBean> books = 
			new ArrayList<BookBean>();
		books.add(new BookBean(1 , "疯狂Java讲义"
			, "crazyit.org"));
		books.add(new BookBean(2 , "轻量级Java EE企业应用实战"
			, "crazyit.org"));
		books.add(new BookBean(3 , "疯狂Ajax讲义"
			, "crazyit.org"));
		books.add(new BookBean(4 , "疯狂XML讲义"
			, "crazyit.org"));
		books.add(new BookBean(5 , "经典Java EE企业应用实战"
			, "crazyit.org"));
		return books;
	}
}
