

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
public class BigImageTest
{
	public static void main(String[] args) 
	{
		long start = System.currentTimeMillis();
		//程序返回一个Image对象，该对象只是BigImage的代理对象
		Image image = new ImageProxy(null);
		System.out.println("系统得到Image对象的时间开销:" + 
			 (System.currentTimeMillis() - start));
		//只有当实际调用image代理的show()方法时，程序才会真正创建被代理对象。
		image.show();	
	}
}
