

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
public class Facade
{
	//定义被Facade封装的三个部门
	Payment pay;
	Cook cook;
	Waiter waiter;
	//构造器
	public Facade()
	{
		this.pay = new PaymentImpl();
		this.cook = new CookImpl();
		this.waiter = new WaiterImpl();
	}
	public void serveFood()
	{
		//依次调用三个部门的方法，封装成一个serveFood()方法
		String food = pay.pay();
		food = cook.cook(food);
		waiter.serve(food);
	}
}
