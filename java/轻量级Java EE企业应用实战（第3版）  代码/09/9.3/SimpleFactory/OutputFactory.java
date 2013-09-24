
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
public class OutputFactory
{
	public Output getOutput()
	{
		//下面两行代码用于控制系统到底使用Output的哪个实现类。
		//return new Printer();
		return new BetterPrinter();
	}
}
