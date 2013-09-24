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
public class BeforeResultInterceptor
	extends AbstractInterceptor
{
	public String intercept(ActionInvocation invocation)
		throws Exception
	{
		//将一个拦截结果的监听器注册给该拦截器
		invocation.addPreResultListener(new MyPreResultListener()); 
		System.out.println("execute方法执行之前的拦截...");
		//调用下一个拦截器，或者Action的执行方法
		String result = invocation.invoke();
		System.out.println("execute方法执行之后的拦截...");
		return result;
	}
}
