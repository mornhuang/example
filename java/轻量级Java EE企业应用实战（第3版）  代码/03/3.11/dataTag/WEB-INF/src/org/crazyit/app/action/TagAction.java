package org.crazyit.app.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ActionContext;

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
public class TagAction extends ActionSupport
{
	//封装用户请求参数的author属性
	private String author;

	//author属性的setter和getter方法
	public void setAuthor(String author)
	{
		this.author = author;
	}
	public String getAuthor()
	{
		return this.author;
	}
	//定义第一个处理逻辑
	public String execute() throws Exception
	{
		return "done";
	}
	//定义第二个处理逻辑
	public String login() throws Exception
	{
		ActionContext.getContext().
			put("author", getAuthor());
		return "done";
	}
}