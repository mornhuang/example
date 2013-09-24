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
//定义一个切面
@Aspect
public class AroundAdviceTest
{
	//匹配org.crazyit.app.service.impl包下所有类的、
	//所有方法的执行作为切入点
	@Around("execution(* org.crazyit.app.service.impl.*.*(..))")
	public Object processTx(ProceedingJoinPoint jp)
		throws java.lang.Throwable
	{
		System.out.println("执行目标方法之前，模拟开始事务...");
		//执行目标方法，并保存目标方法执行后的返回值
		Object rvt = jp.proceed(new String[]{"被改变的参数"});
		System.out.println("执行目标方法之后，模拟结束事务...");
		return rvt + " 新增的内容";
	}
}
