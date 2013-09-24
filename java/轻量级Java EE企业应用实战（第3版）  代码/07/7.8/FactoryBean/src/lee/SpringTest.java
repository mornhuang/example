package lee;

import org.springframework.context.*;
import org.springframework.context.support.*;

import org.crazyit.app.service.*;
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
public class SpringTest 
{
	public static void main(String[] args)throws Exception
	{
		ApplicationContext ctx = new 
			ClassPathXmlApplicationContext("bean.xml");
		//直接请求FactoryBean时，系统将返回该FactoryBean的产品
		Person p1 = ctx.getBean("chinese" , Person.class);
		System.out.println(p1.sayHello("Mary"));
		System.out.println(p1.sayGoodBye("Mary"));
		//再次获取该FactoryBean的产品
		Person p2 = ctx.getBean("chinese" , Person.class);
		System.out.println(p1 == p2);
		//如需获取FactoryBean本身，则应该在Bean id前增加&
		System.out.println(ctx.getBean("&chinese"));
	}
}