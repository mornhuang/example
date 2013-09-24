package lee;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.*;

import org.crazyit.app.service.*;
/**
 * Description:
 * <br/>利嫋: <a href="http://www.crazyit.org">決髄Java選男</a> 
 * <br/>Copyright (C), 2001-2012, Yeeku.H.Lee
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name:
 * <br/>Date:
 * @author  Yeeku.H.Lee kongyeeku@163.com
 * @version  1.0
 */
public class BeanTest
{
	public static void main(String[] args)
	{
		ApplicationContext ctx = new
			ClassPathXmlApplicationContext("bean.xml");
		Person p1 = ctx.getBean("chinese" , Person.class);
		Person p2 = ctx.getBean("chinese" , Person.class);
		p1.useAxe();
		System.out.println(p1 == p2);
	}
}