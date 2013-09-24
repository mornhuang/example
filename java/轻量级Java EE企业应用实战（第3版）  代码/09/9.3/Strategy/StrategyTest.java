

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
public class StrategyTest
{
	public static void main(String[] args) 
	{
		//客户端没有选择打折策略类
		DiscountContext dc = new DiscountContext(null);
		double price1 = 79;
		//使用默认的打折策略
		System.out.println("79元的书默认打折后的价格是：" 
			+ dc.getDiscountPrice(price1));
		//客户端选择合适的VIP打折策略
		dc.changeDiscount(new VipDiscount());
		double price2 = 89;
		//使用VIP打折得到打折价格
		System.out.println("89元的书对VIP用户的价格是：" 
			+ dc.getDiscountPrice(price2));
	}
}
