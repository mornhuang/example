package org.crazyit.app.service.impl;

import org.crazyit.app.service.*;
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
public class American 
	implements Person
{
	public String sayHello(String name)
	{
		return name + ",Hello";
	}
	public String sayGoodBye(String name)
	{
		return name + ",Good Bye";
	}
}