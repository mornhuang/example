
import java.lang.reflect.*;
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
public class MyInvokationHandler
	implements InvocationHandler
{
	//需要被代理的对象
	private Object target;
	public void setTarget(Object target)
	{
		this.target = target;
	}
	//执行动态代理对象的所有方法时，都会被替换成执行如下的invoke方法
	public Object invoke(Object proxy, Method method, Object[] args)
		throws Exception
	{
		TxUtil tx = new TxUtil();
		//执行TxUtil对象中的beginTx。
		tx.beginTx();
		//以target作为主调来执行method方法
		Object result = method.invoke(target , args);
		//执行TxUtil对象中的endTx。
		tx.endTx();
		return result;
	}
}