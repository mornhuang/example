package org.crazyit.app.advice;

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
public class AfterThrowingAdviceTest
{
	//定义一个普通方法作为增强处理方法
	public void doRecoveryActions(Throwable ex)
	{
		//ex代表目标方法中抛出的异常
		System.out.println("目标方法中抛出的异常:" + ex);
		System.out.println("模拟抛出异常后的资源回收...");
	}
}
