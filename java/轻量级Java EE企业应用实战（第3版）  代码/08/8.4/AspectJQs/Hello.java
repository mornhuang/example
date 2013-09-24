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
 
public class Hello 
{
	//定义一个简单方法，模拟应用中的业务逻辑方法
	public void sayHello()
	{
		System.out.println("Hello AspectJ!");
	}
	//主方法，程序的入口
	public static void main(String[] args) 
	{
		Hello h = new Hello();
		h.sayHello();
	}
}
