package org.crazyit.app.action;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;

import org.crazyit.app.service.*;
/**
 * Description:
 * <br/>利嫋: <a href="http://www.crazyit.org">決髄Java選男</a> 
 * <br/>Copyright (C), 2001-2012, Yeeku.H.Lee
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name:
 * <br/>Date:
 * @author  Yeeku.H.Lee kongyeeku@163.com
 * @version  1.0
 */
 
public class GetBooksAction
	implements Action
{
	private String[] books;	
	
	public void setBooks(String[] books)
	{
		this.books = books;
	}
	public String[] getBooks()
	{
		return books;
	}
	
	public String execute() throws Exception
	{
		String user = (String)ActionContext.getContext()
			.getSession().get("user");
		if (user != null && user.equals("scott"))
		{
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
