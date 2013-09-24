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
public class AfterThrowingAdviceTest
{
	//匹配org.crazyit.app.service.impl包下所有类的、
	//所有方法的执行作为切入点
	@AfterThrowing(throwing="ex"
		, pointcut="execution(* org.crazyit.app.service.impl.*.*(..))")
	public void doRecoveryActions(Throwable ex)
	{
		System.out.println("目标方法中抛出的异常:" + ex);
		System.out.println("模拟抛出异常后的增强处理...");
	}
}
