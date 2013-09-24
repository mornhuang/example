package org.crazyit.app.service;

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
public class AfterAdviceTest
{
	//匹配org.crazyit.app.service包下所有类的、
	//所有方法的执行作为切入点
	@After("execution(* org.crazyit.app.service.*.*(..))")
	public void release()
	{
		System.out.println("模拟方法结束后的释放资源...");
	}
}
