package org.crazyit.app.action.book;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;

import org.apache.struts2.dispatcher.*;

import org.apache.struts2.config.*;

import org.crazyit.app.service.*;

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

public class GetBooksAction implements Action
{
	//封装数据的books属性
	private String[] books;
	//省略books属性的setter和getter方法
	
	//books属性的setter和getter方法
	public void setBooks(String[] books)
	{
		this.books = books;
	}
	public String[] getBooks()
	{
		return this.books;
	}

	//处理用户请求的execute方法
	public String execute() throws Exception
	{
		String user = (String)ActionContext.getContext()
			.getSession().get("user");
		if (user != null && user.equals("crazyit.org"))
		{
			//创建业务逻辑组件，并调用业务逻辑组件的方法
			BookService bs = new BookService();
			setBooks(bs.getLeeBooks());
			return SUCCESS;
		}
		else
		{
			return LOGIN;
		}
	}
}