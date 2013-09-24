
import javax.naming.*;
import java.rmi.*;
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
public class RMIClient
{
	public static void main(String[] args)throws Exception
	{
		//通过JNDI查找远程服务,并执行强制类型转换
		Server ser = (Server)Naming.lookup("rmi://:1099/crazyit");
		System.out.println(ser instanceof java.rmi.server.RemoteStub);
		//调用远程方法
		System.out.println(ser.helloWorld("yeeku"));
		//调用远程方法。
		System.out.println(ser.getPerson("yeeku",28));
	}
}
