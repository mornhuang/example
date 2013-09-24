package lee;

import java.util.*;
import javax.naming.*;
import javax.swing.*;
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
public class JNDIClient
{
	public static void main(String[] args)
		throws Exception
	{
		final String INIT_FACTORY = 
			"weblogic.jndi.WLInitialContextFactory";
		final String WL_URL = "t3://localhost:7001";
		Hashtable props = new Hashtable();
		props.put(Context.INITIAL_CONTEXT_FACTORY , INIT_FACTORY);
		props.put(Context.PROVIDER_URL , WL_URL);
		//如果需要安全控制，则需要如下两行代码
//		props.put(Context.SECURITY_PRINCIPAL, "weblogic");
//		props.put(Context.SECURITY_CREDENTIALS, "weblogic");
		//初始化Context，使用InitialContext初始化Context
		Context ctx = new InitialContext(props); 
		//通过JNDI查找对象，该对象是一个JFrame对象。
		Object obj = ctx.lookup("testName");
		System.out.println(obj);
	}
}
