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
public aspect LogAspect 
{
	//定义一个PointCut，其名为logPointcut
	//该PointCut对应于指定Hello对象的sayHello方法
	pointcut logPointcut()
		:execution(void Hello.sayHello());
	//在logPointcut之后执行下面代码块
	after():logPointcut()
	{
		System.out.println("记录日志...");
	}
}
