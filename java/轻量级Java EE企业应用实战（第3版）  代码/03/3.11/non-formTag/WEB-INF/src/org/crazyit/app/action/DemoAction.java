package org.crazyit.app.action;

import com.opensymphony.xwork2.ActionSupport;

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
public class DemoAction extends ActionSupport
{
	public String execute()
	{
		//添加两条Error信息
		addActionError("第一条错误消息！"); 
		addActionError("第二条错误消息！"); 
		//添加两条普通信息
		addActionMessage("第一条普通消息！"); 
		addActionMessage("第二条普通消息！"); 
		return SUCCESS;
	}
}