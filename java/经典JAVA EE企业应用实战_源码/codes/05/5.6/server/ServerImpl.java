import java.rmi.*;
import java.rmi.server.*;
import java.rmi.registry.*;
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
public class ServerImpl extends UnicastRemoteObject
	implements Server 
{
	//定义一个List保存所有连接进来的客户
	static List<Client> users 
		= new ArrayList<Client>();
	public ServerImpl()
		throws RemoteException
	{
	}
	//该方法中有一个形参是Client类型
	//通过这种方式让服务器获得远程客户端对象的引用
	public void hello(Client cm , String saying)
		throws Exception
	{
		if (!users.contains(cm))
		{
			users.add(cm);
		}
		try
		{
			java.util.Date now = new java.util.Date();
			String msg = now + saying;
			//依次调用连接到该服务器的每个客户端的showDialog方法
			for (Client c : users)
			{
				//回调远程客户端方法
				c.showDialog(msg);
			}
		}
		catch (RemoteException ex)
		{
			users.remove(cm);
			ex.printStackTrace();
		}
	}
	public static void main(String args[])
		throws Exception 
	{
		//注册RMI服务端口
		LocateRegistry.createRegistry(1099);
		Server remote = new ServerImpl();
		//将远程服务对象绑定到指定JNDI。
		Naming.rebind("rmi://127.0.0.1:1099/crazyit"
			, remote);
	}
}
