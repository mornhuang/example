package org.crazyit.app.advice;

import org.aspectj.lang.annotation.*;
import org.aspectj.lang.*;
import org.springframework.core.annotation.Order;
import java.util.Arrays;

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
@Order(1)
public class SecondAdviceTest
{
	//定义Before增强处理执行
	@Before("execution(* org.crazyit.app.service.impl.*.*(..))")
	public void zuthority(JoinPoint jp)
	{
		System.out.println("⑴号Before增强：模拟执行权限检查");
	}
}
