
import java.util.Iterator;
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
public class Product extends Observable
{
	//定义两个属性
	private String name;
	private double price;
	//无参数的构造器
	public Product(){}
	public Product(String name , double price)
	{
		this.name = name;
		this.price = price;
	}
	public String getName()
	{
		return name;
	}
	//当程序调用name的setter方法来修改Product的name属性时
	//程序自然触发该对象上注册的所有观察者
	public void setName(String name)
	{
		this.name = name;
		notifyObservers(name);
	}
	public double getPrice()
	{
		return price;
	}
	//当程序调用price的setter方法来修改Product的price属性时
	//程序自然触发该对象上注册的所有观察者
	public void setPrice(double price)
	{
		this.price = price;
		notifyObservers(price);
	}
}