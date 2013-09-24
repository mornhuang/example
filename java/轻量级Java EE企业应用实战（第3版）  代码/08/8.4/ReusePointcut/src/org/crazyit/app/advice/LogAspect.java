package org.crazyit.app.advice;

import org.aspectj.lang.annotation.*;
import org.aspectj.lang.*;

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
@Aspect
public class LogAspect
{
	//直接使用SystemArchitecture切面类的myPointcut切入点
	//args(msg)保证该切入点只匹配只有一个字符串参数的方法
	@AfterReturning(pointcut="SystemArchitecture.myPointcut()"
		+ "&&args(msg)" , returning="retVal")
	public void writeLog(String msg, Object retVal)
	{
		System.out.println(msg);
		System.out.println(retVal);
		System.out.println("模拟记录日志....");
	}
}
