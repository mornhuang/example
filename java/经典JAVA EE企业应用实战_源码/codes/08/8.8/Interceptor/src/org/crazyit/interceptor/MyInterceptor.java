package org.crazyit.interceptor;

import javax.interceptor.*;
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
public class MyInterceptor
{
	@AroundInvoke
	public Object log(InvocationContext ctx)
		throws Exception
	{
		System.out.println("------拦截器开始运行------");
		//让目标方法执行
		Object rvt = ctx.proceed();
		if (rvt != null)
		{
			rvt = "拦截器改变了返回值：" + rvt;
		}
		System.out.println("------拦截器执行结束------");
		return rvt;
	}
}
