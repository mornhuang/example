package org.crazyit.controller;

import com.opensymphony.xwork2.ActionSupport;

import org.crazyit.service.*;
import org.crazyit.util.*;

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

//继承ActionSupport来实现Struts 2的Action类
public class AddNewsAction extends ActionSupport
{
	//下面是Action内用于封装用户请求参数的两个属性
	private String title;
	private String content;
	//title属性的setter和getter方法
	public void setTitle(String title)
	{
		this.title = title;
	}
	public String getTitle()
	{
		return this.title;
	}

	//content属性的setter和getter方法
	public void setContent(String content)
	{
		this.content = content;
	}
	public String getContent()
	{
		return this.content;
	}
	//处理用户请求的execute方法
	public String execute() throws Exception
	{
		//调用EJB工厂的静态方法来获取EJB的引用
		NewsService newsService = (NewsService)
			EJBFactory.lookup("newsService");
		//调用EJB的方法来处理用户请求
		newsService.addNews(title , content);
		return SUCCESS;
	}
}