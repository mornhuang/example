package org.crazyit.app.action;

import com.opensymphony.xwork2.Action;

import org.crazyit.app.domain.*;
import org.crazyit.app.service.*;

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
public class RegistAction
	implements Action
{
	//下面是用于封装用户请求参数的属性
	private Person person;
	//用于封装处理结果的属性
	private String tip;
	//系统所用的业务逻辑组件
	private LeeService leeService;
	//设置注入业务逻辑组件所必需的setter方法
	public void setLeeService(LeeService leeService)
	{
		this.leeService = leeService;
	}

	//person属性的setter和getter方法
	public void setPerson(Person person)
	{
		this.person = person;
	}
	public Person getPerson()
	{
		return this.person;
	}

	//tip属性的setter和getter方法
	public void setTip(String tip)
	{
		this.tip = tip;
	}
	public String getTip()
	{
		return this.tip;
	}

	//处理用户请求的execute方法
	public String execute()
		throws Exception
	{
		//调用业务逻辑组件的regist方法来处理请求
		if (leeService.regist(person))
		{
			setTip("哈哈，注册成功！");
			return SUCCESS;
		}
		else
		{
			return ERROR;
		}
	}
}