package org.crazyit.util;

import javax.naming.InitialContext;
import javax.naming.NamingException;

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
public class EJBFactory 
{
	public static Object lookup(String ejbRefName)
	{
		try 
		{
			//获取JNDI的Context
			InitialContext ctx = new InitialContext();
			//返回查找得到的EJB组件的引用
			return ctx.lookup("java:comp/env/"+ ejbRefName);
		} 
		catch (NamingException e)
		{
			e.printStackTrace();
		}
		return null;
	}
}
