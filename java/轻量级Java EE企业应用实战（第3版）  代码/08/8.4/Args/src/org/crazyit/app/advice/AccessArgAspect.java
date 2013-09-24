package org.crazyit.app.advice;

import org.aspectj.lang.annotation.*;
import org.aspectj.lang.*;

import java.util.*;
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
public class AccessArgAspect
{	//下面的args(msg,time)保证该切入点只匹配
	//具有第一个参数是字符串，第二个参数是Date的方法
	@AfterReturning(pointcut="execution(* org.crazyit.app.service.impl"
		+ ".*.*(..)) && args(food , time , ..)"
		, returning="retVal")
	public void access(Date time , String food ,
			Object retVal)
	{
		System.out.println("目标方法中String参数为：" + food);
		System.out.println("目标方法中Date参数为：" + time);
		System.out.println("模拟记录日志....");
	}
}
