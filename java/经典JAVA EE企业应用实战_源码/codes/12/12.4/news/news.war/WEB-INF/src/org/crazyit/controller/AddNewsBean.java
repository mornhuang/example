package org.crazyit.controller;

import javax.ejb.*;
import javax.naming.*;

import org.crazyit.service.*;
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
public class AddNewsBean
{
	//通过依赖注入将EJB组件注入该托管Bean
	@EJB(beanName="newsService")
	private NewsService newsService;
	//下面的三个属性都会直接与JSF标签绑定
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

	//该方法被绑定到UI组件（按钮）的action属性
	public String addNews()
	{
		//调用EJB的方法来处理用户请求
		newsService.addNews(title , content);
		return "success";
	}
}