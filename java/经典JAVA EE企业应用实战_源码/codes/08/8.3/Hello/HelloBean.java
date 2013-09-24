package org.crazyit.service;

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
@Stateless(mappedName="Hello")
public class HelloBean 
	implements Hello
{
	private int count;
	public String hello(String name)
	{
		return name + "，您好，现在时间是"
			+ new java.util.Date();
	}
}
