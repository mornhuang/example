
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
public class PriceObserver implements Observer
{
	//实现观察者必须实现的update方法
	public void update(Observable o , Object arg)
	{	
		if(arg instanceof Double)
		{
			System.out.println("价格观察者:" +
				o + "物品价格已经改变为: " + arg);
		}
	}
}
