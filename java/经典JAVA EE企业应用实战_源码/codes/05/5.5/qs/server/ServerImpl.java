
import java.rmi.*;
import java.rmi.server.*;
import java.rmi.registry.*;
import javax.naming.*;
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
//远程服务类，远程服务类必须继承UnicastRemoteObject，并实现Remote接口
public class ServerImpl extends UnicastRemoteObject
	implements Server
{
	//由于默认构造器必须声明抛出RemoteException
	//因此此处必须显式定义该构造器
	public ServerImpl()
		throws RemoteException
	{
	}
	//实现Remote接口必须实现的方法
	public String helloWorld(String name)
		throws RemoteException
	{
		return name + ", 您好!";
	}
	//实现Remote接口必须实现的方法
	public Person getPerson(String name , int age)
		throws RemoteException
	{
		return new Person(name , age);
	}
	//下面是服务类的本地方法，不会“暴露”为远程服务。
	public void info()
	{
		System.out.println("我是本地方法");
	}
	//下面提供程序入口，将远程类实例绑定为本机的服务。
	public static void main(String[] args)
		throws Exception
	{
		//创建远程服务类实例
		Server imp = new ServerImpl();
		//注册远程服务的端口
		LocateRegistry.createRegistry(1099);
		//将远程服务实例绑定为远程服务。
		Naming.rebind("rmi://:1099/crazyit", imp);
	}
}
