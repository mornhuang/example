package org.crazyit.app.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import java.util.*;

import org.crazyit.app.action.*;
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
public class SimpleInterceptor
	extends AbstractInterceptor
{
	//简单拦截器的名字
	private String name;
	//为该简单拦截器设置名字的setter方法
	public void setName(String name)
	{
		this.name = name;
	}
	public String intercept(ActionInvocation invocation)
		throws Exception
	{
		//取得被拦截的Action实例
		LoginAction action = (LoginAction)invocation.getAction();
		//打印执行开始的实现
		System.out.println(name + " 拦截器的动作---------" + 
			"开始执行登录Action的时间为：" + new Date());
		//取得开始执行Action的时间
		long start = System.currentTimeMillis();
		//执行该拦截器的后一个拦截器
		//如果该拦截器后没有其他拦截器，则直接执行Action的execute方法
		String result = invocation.invoke();
		//打印执行结束的时间
		System.out.println(name + " 拦截器的动作---------" + 
			"执行完登录Action的时间为：" + new Date());
		long end = System.currentTimeMillis();
		System.out.println(name + " 拦截器的动作---------" + 
			"执行完该Action的事件为" + (end - start) + "毫秒");
		return result;
	}
}
