package lee;

import javax.rmi.*;
import javax.naming.*;
import java.util.Properties;

import org.crazyit.service.*;
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
public class EjbClient
{	 
	public void test()
		throws Exception
	{
		//获取WebLogic中JNDI服务的Context
		Context ctx = getInitialContext();
		Bmt bmt = (Bmt)ctx.lookup("Bmt#org.crazyit.service.Bmt");
		bmt.insert();
	}
	//工具方法，用来获取WebLogic中JNDI服务的Context
	private Context getInitialContext()
	{
		final String INIT_FACTORY = 
			"weblogic.jndi.WLInitialContextFactory";
		final String SERVER_URL = "t3://localhost:7001";
		Context ctx = null;
		try
		{
			Properties props = new Properties();
			props.put(Context.INITIAL_CONTEXT_FACTORY 
				, INIT_FACTORY);
			props.put(Context.PROVIDER_URL , SERVER_URL);
			ctx = new InitialContext(props);
		}
		catch(NamingException ne)
		{
			System.err.println("不能连接WebLogic Server在:"
				+ SERVER_URL);
			ne.printStackTrace();
		}
		return ctx;
	}
	public static void main(String[] args)
		throws Exception
	{
		EjbClient client = new EjbClient();
		client.test();
	}
}