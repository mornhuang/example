

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
//实现DiscountStrategy接口，实现对VIP打折的算法
public class VipDiscount 
	implements DiscountStrategy
{
	//重写getDiscount()方法，提供VIP打折算法
	public double getDiscount(double originPrice)
	{
		System.out.println("使用VIP折扣...");
		return originPrice * 0.5;
	}
}
