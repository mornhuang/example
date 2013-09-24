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
@Stateless(mappedName="CallHello")
public class CallHelloBean 
	implements CallHello
{
	@EJB(beanName="Hello")
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
