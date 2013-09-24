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
public class OutputFactoryFactory
{
	//仅定义一个方法用于返回输出设备。
	public static OutputFactory getOutputFactory(
		String type)
	{
		if (type.equalsIgnoreCase("better"))
		{
			return new BetterPrinterFactory();
		}
		else
		{
			return new PrinterFactory();
		}
	}
}
