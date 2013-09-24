

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
public class Customer
{
	public void haveDinner()
	{
		//依次创建三个部门实例
		Payment pay = new PaymentImpl();
		Cook cook = new CookImpl();
		Waiter waiter = new WaiterImpl();
		//依次调用三个部门实例的方法来实现用餐功能
		String food = pay.pay();
		food = cook.cook(food);
		waiter.serve(food);
		//直接依赖于Facade类来实现用餐方法
		Facade f = new Facade();
		f.serveFood();
	}
	public static void main(String[] args)
	{
		new Customer().haveDinner();
	}
}
