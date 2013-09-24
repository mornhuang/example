package lee;

/**
 * Description:
 * <br/>网站: <a href="http://www.crazyit.org">疯狂Java联盟</a> 
 * <br/>Copyright (C), 2001-2012, Yeeku.H.Lee
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name:
 * <br/>Date:
 * @author  Yeeku.H.Lee kongyeeku@163.com
 * @version  1.0
 */
public aspect TxAspect 
{
	//指定执行Hello.sayHello()方法时执行下面代码块
	void around():call(void Hello.sayHello())
	{
		System.out.println("开始事务...");
		proceed();
		System.out.println("事务结束...");
	}
}