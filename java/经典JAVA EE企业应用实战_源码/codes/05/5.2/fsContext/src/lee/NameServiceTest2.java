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
public class NameServiceTest2
{
	public static void main(String[] args) 
		throws NamingException
	{
		final String fileName = "youandme.mp3";
		final String newName = "油和米.mp3";
		final String dirName = "Beyond";
		final String newDir1 = "newDir1";
		final String newDir2 = "newDir2";
		//创建一个Hashtable对象，用于为Context设置初始化参数
		Hashtable env = new Hashtable();
		env.put(Context.INITIAL_CONTEXT_FACTORY, 
			"com.sun.jndi.fscontext.RefFSContextFactory");
		env.put(Context.PROVIDER_URL, "file:/F:/music");
		Context ctx = new InitialContext(env);
		//列出当前Context下的所有绑定关系
		NamingEnumeration<Binding> bindings 
			= ctx.listBindings("");
		while(bindings.hasMore())
		{
			Binding binding = bindings.next();
			System.out.println(binding.getName()
				+ " --> " + binding.getObject());
		}
		//列出当前Context的指定SubContext下的所有绑定关系
		bindings = ctx.listBindings(dirName);
		System.out.println("------下面是Beyond Context下的绑定------");
		while(bindings.hasMore())
		{
			Binding binding = bindings.next();
			System.out.println(binding.getName()
				+ " --> " + binding.getObject());
		}
		//创建2个SubContext（实际就是创建两个子目录）
		ctx.createSubcontext(newDir1);
		ctx.createSubcontext(newDir2);
		//删除1个SubContext
		ctx.destroySubcontext(newDir2);
		//将当前Context下指定的绑定名进行重命名
		ctx.rename(fileName , newName);
		ctx.close();
	}
}
