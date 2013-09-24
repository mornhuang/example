

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

//使用该BigImage模拟一个很大图片
public class BigImage implements Image
{
	public BigImage()
	{
		try
		{
			//程序暂停3s模式模拟系统开销
			Thread.sleep(3000);
			System.out.println("图片装载成功...");
		}
		catch (InterruptedException ex)
		{
			ex.printStackTrace();
		}
	}
	//实现Image里的show()方法
	public void show()
	{
		System.out.println("绘制实际的大图片");
	}
}