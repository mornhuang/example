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
public class SecondAdviceTest
{
	//定义Before增强处理
	public void authority(String aa)
	{
		System.out.println("目标方法的参数为：" + aa);
		System.out.println("①号Before增强：模拟执行权限检查");
	}
}
