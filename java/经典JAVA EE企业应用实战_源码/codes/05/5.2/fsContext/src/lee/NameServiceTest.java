package lee;

import javax.naming.*;
import java.util.*;
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
public class NameServiceTest
{
	public static void main(String[] args) 
		throws NamingException
	{
		final String fileName = "00.目录.docx";
		final String dirName = "codes";
		//创建一个Hashtable对象，用于为Context设置初始化参数
		Hashtable env = new Hashtable();
		env.put(Context.INITIAL_CONTEXT_FACTORY, 
			"com.sun.jndi.fscontext.RefFSContextFactory");
		env.put(Context.PROVIDER_URL, "file:/G:/publish");
		Context ctx = new InitialContext(env);
		//根据名字查找对象
		Object file = ctx.lookup(fileName);
		System.out.println(fileName+ " 名称被绑定到: "
			+ file);
		System.out.println("file的类型是：" + file.getClass());
		//根据名字查找对象
		Object dir = ctx.lookup(dirName);
		System.out.println(dirName + " 名称被绑定到: "
			+ dir);
		System.out.println("dir的类型是：" + dir.getClass());
		//关闭该Context
		ctx.close();
	}
}
