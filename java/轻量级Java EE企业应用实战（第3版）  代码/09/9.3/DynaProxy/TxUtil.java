

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
public class TxUtil
{
	//第一个拦截器方法:模拟事务开始
	public void beginTx()
	{
		System.out.println("=====模拟开始事务=====");
	}
	//第二个拦截器方法:模拟事务结束
	public void endTx()
	{
		System.out.println("=====模拟结束事务=====");
	}
}
