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
public class JBossClient
{
	public static void main(String[] args)
		throws Exception
	{
		final String INIT_FACTORY = 
			"org.jnp.interfaces.NamingContextFactory";
		final String SERVER_URL = "jnp://localhost:1099";
		Hashtable env = new Hashtable();
		env.put(Context.INITIAL_CONTEXT_FACTORY , INIT_FACTORY);
		env.put(Context.PROVIDER_URL , SERVER_URL);
		//初始化Context，使用InitialContext初始化Context
		Context ctx = new InitialContext(env); 
		System.out.println("ctx对象初始化成功:" + ctx);
		//通过JNDI查找对象，该对象是一个JFrame对象。
		JFrame mainWin = (JFrame)ctx.lookup("testName");
		mainWin.setBounds(20 ,30 , 400, 300);
		mainWin.setVisible(true);
	}
}
