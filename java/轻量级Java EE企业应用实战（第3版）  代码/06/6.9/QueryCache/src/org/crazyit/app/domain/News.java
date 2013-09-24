package org.crazyit.app.domain;

/**
 * Description:
 * <br/>Copyright (C), 2008-2010, Yeeku.H.Lee
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name:
 * <br/>Date:
 * @author  Yeeku.H.Lee kongyeeku@163.com
 * @version  1.0
 */
public class News
{
	//消息类的标识属性
	private Integer id;
	//消息标题
	private String title;
	//消息内容
	private String content;
	//构造器
	public News()
	{
	}
	//标识属性的setter和getter方法
	public void setId(Integer id) 
	{
		this.id = id; 
	}
	public Integer getId()
	{
		return (this.id); 
	}
	//消息标题的setter方法和getter方法
	public void setTitle(String title) 
	{
		this.title = title; 
	}
	public String getTitle() 
	{
		return (this.title); 
	}

	//消息内容的setter方法和getter方法
	public void setContent(String content)
	{
		this.content = content; 
	}
	public String getContent()
	{
		return (this.content); 
	}
}
