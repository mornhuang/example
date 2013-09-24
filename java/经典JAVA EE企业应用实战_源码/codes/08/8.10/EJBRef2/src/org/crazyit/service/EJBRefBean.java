package org.crazyit.service;

import java.util.*;
import javax.ejb.*;


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
@Stateless(mappedName="EJBRef")
/* 通过@EJB配置一个EJB引用 */
@EJB(name="ejb3/Hello" , beanInterface=Hello.class)
public class EJBRefBean
	implements EJBRef
{
	//通过依赖注入来注入指定EJB
	@EJB(name="ejb3/Hello")
	private Hello hello;
	public String callHello(String name)
	{
		final String prefix = "调用Hello EJB成功，返回值：";
		String result = hello.hello("yeeku");
		System.out.println(prefix 
			+ result);
		return prefix + result;
	}
}
