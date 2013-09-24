package org.crazyit.controller;

import javax.ejb.*;
import javax.naming.*;
import java.util.*;

import org.crazyit.service.*;
import org.crazyit.model.*;
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
public class ViewAllNews
{
	//通过依赖注入将EJB组件注入该托管Bean
	@EJB(beanName="newsService")
	private NewsService newsService;
	private List<News> newsList;
	//newsList属性的setter和getter方法
	public void setNewsList(List<News> newsList)
	{
		this.newsList = newsList;
	}
	public List<News> getNewsList()
	{
		//调用EJB的业务逻辑方法
		return newsService.getAllNews();
	}
}