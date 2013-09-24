
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
//远程接口必须集成java.rmi.Remote接口
public interface Server extends Remote 
{
	//所有在Remote接口里声明的方法都应该抛出RemoteException异常
	String helloWorld(String name)
		throws RemoteException;
	Person getPerson(String name , int age)
		throws RemoteException;
}

