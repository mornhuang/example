
import java.rmi.*;
import java.rmi.server.*;
import java.io.*;

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
//因为该客户端无需暴露成远程服务，因此无需继承UnicastRemoteObject
public class RMIClient implements Client
{
	public static void main(String[] args)
		throws Exception 
	{
		Client client = new RMIClient();
		UnicastRemoteObject.exportObject(client);
		Server stub = (Server)Naming
			.lookup("rmi://127.0.0.1:1099/crazyit");
		//定义键盘输入流，用于读取用户键盘输入
		BufferedReader br = new BufferedReader(
			new InputStreamReader(System.in));
		String line = null;
		while((line = br.readLine()) != null)
		{
			//调用远程方法时，将自身作为参数传入
			stub.hello(client , line);
		}
	}
	//实现允许远程调用的方法
	public void showDialog(String msg)
		throws java.rmi.RemoteException
	{
		//使用JOptionPane显示对话框
		System.out.println(msg);
	}
}