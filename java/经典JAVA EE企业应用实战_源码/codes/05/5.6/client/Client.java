
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
//客户端的远程服务接口同样要继承Remote
public interface Client extends Remote
{
	//定义一个允许远程调用的方法
	void showDialog(String msg)
		throws java.rmi.RemoteException;
}
