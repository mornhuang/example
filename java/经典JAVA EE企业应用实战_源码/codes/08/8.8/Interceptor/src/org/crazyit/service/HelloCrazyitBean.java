package org.crazyit.service;

import java.util.*;
import javax.ejb.*;
import javax.annotation.*;

import javax.interceptor.*;

import org.crazyit.interceptor.*;

/**
 * Description:
 * <br/>网站: <a href="http://www.crazyit.org">疯狂Java联盟</a> 
 * <br/>Copyright (C), 2001-2010, Yeeku.H.Lee
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name:
 * <br/>Date:
 * @author  Yeeku.H.Lee kongyeeku@163.com
 * @version  1.0
 */
@Stateless(mappedName="HelloCrazyit")
@Interceptors(MyInterceptor.class)
public class HelloCrazyitBean 
	implements HelloCrazyit
{
	public String hello(String name)
	{
		System.out.println(name 
			+ ", 您好！现在时间是："
			+ new java.util.Date());
		return "crazyit.org";
	}
	public void crazyit()
	{
		System.out.println("疯狂Java联盟!");
		System.out.println("最受欢迎的Java论坛!");
	}
	@ExcludeClassInterceptors
	public void exclude()
	{
		System.out.println("被排除在拦截器机制之外的方法!");
	}
}