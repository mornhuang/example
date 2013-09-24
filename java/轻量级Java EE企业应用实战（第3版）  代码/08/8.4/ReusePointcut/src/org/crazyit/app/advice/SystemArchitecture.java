package org.crazyit.app.advice;

import org.aspectj.lang.annotation.*;
import org.aspectj.lang.*;

/**
 * Description:
 * <br/>利嫋: <a href="http://www.crazyit.org">決髄Java選男</a> 
 * <br/>Copyright (C), 2001-2012, Yeeku.H.Lee
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name:
 * <br/>Date:
 * @author  Yeeku.H.Lee kongyeeku@163.com
 * @version  1.0
 */
@Aspect
public class SystemArchitecture 
{
	@Pointcut("execution(* org.crazyit.app.service"
		+ ".impl.Chin*.say*(..))")
	public void myPointcut()
	{
	}
}
