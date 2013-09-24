package org.crazyit.controller;

import com.opensymphony.xwork2.ActionSupport;

import java.util.*;

import org.crazyit.service.*;
import org.crazyit.model.*;
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
public class ViewAllNewsAction
	extends ActionSupport
{
	private List<News> newsList;
	//newsList属性的setter和getter方法
	public void setNewsList(List<News> newsList)
	{
		this.newsList = newsList;
	}
	public List<News> getNewsList()
	{
		//调用EJB工厂的静态方法来获取EJB的引用
		NewsService newsService = (NewsService)
			EJBFactory.lookup("newsService");
		//调用EJB的业务逻辑方法
		return newsService.getAllNews();
	}
}