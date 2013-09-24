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
//指定该EJB的内部标识名为hello。
@Stateless(name="hello")
public class HelloBean 
	implements Hello
{
	public String hello(String name)
	{
		return name + "，您好。"
			+ "现在时间是：" + new java.util.Date();
	}
}
