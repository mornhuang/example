package org.crazyit.app.advice;

import org.aspectj.lang.*;

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
public class FourAdviceTest
{
	public Object processTx(ProceedingJoinPoint jp)
		throws java.lang.Throwable
	{
		System.out.println("Around增强：执行目标方法之前，模拟开始事务...");
		//访问执行目标方法的参数
		Object[] args = jp.getArgs();
		//当执行目标方法的参数存在，
		//且第一个参数是字符串参数
		if (args != null && args.length > 0
			&& args[0].getClass() == String.class)
		{
			//改变第一个目标方法的第一个参数
			args[0] = "被改变的参数";
		}
		//执行目标方法，并保存目标方法执行后的返回值
		Object rvt = jp.proceed(args);
		System.out.println("Around增强：执行目标方法之后，模拟结束事务...");
		return rvt + " 新增的内容";
	}
	public void authority(JoinPoint jp)
	{
		System.out.println("②Before增强：模拟执行权限检查");
		//返回被织入增强处理的目标方法
		System.out.println("②Before增强：被织入增强处理的目标方法为："
			+ jp.getSignature().getName());
		//访问执行目标方法的参数
		System.out.println("②Before增强：目标方法的参数为："
			+ Arrays.toString(jp.getArgs()));
		//访问被增强处理的目标对象
		System.out.println("②Before增强：被织入增强处理的目标对象为："
			+ jp.getTarget());
	}
	public void log(JoinPoint jp , Object rvt)
	{
		System.out.println("AfterReturning增强：获取目标方法返回值:" 
			+ rvt);
		System.out.println("AfterReturning增强：模拟记录日志功能...");
		//返回被织入增强处理的目标方法
		System.out.println("AfterReturning增强：被织入增强处理的目标方法为："
			+ jp.getSignature().getName());
		//访问执行目标方法的参数
		System.out.println("AfterReturning增强：目标方法的参数为："
			+ Arrays.toString(jp.getArgs()));
		//访问被增强处理的目标对象
		System.out.println("AfterReturning增强：被织入增强处理的目标对象为："
			+ jp.getTarget());
	}
	public void release(JoinPoint jp)
	{
		System.out.println("After增强：模拟方法结束后的释放资源...");
		//返回被织入增强处理的目标方法
		System.out.println("After增强：被织入增强处理的目标方法为："
			+ jp.getSignature().getName());
		//访问执行目标方法的参数
		System.out.println("After增强：目标方法的参数为："
			+ Arrays.toString(jp.getArgs()));
		//访问被增强处理的目标对象
		System.out.println("After增强：被织入增强处理的目标对象为："
			+ jp.getTarget());
	}
}
