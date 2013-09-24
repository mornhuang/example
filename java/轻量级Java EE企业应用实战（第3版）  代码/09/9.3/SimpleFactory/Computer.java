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
public class Computer
{
	private Output out;

	public Computer(Output out)
	{
		this.out = out;
	}
	//定义一个模拟获取字符串输入的方法
	public void keyIn(String msg)
	{
		out.getData(msg);
	}
	//定义一个模拟打印的方法
	public void print()
	{
		out.out();
	}
	public static void main(String[] args) 
	{
		//创建OutputFactory
		OutputFactory of = new OutputFactory();
		//将Output对象传入，创建Computer对象
		Computer c = new Computer(of.getOutput());
		c.keyIn("轻量级Java EE企业应用实战");
		c.keyIn("疯狂Java讲义");
		c.print();
	}
}