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
public class BeanTest
{
	public static void main(String[] args)
	{
		ApplicationContext ctx = new 
			ClassPathXmlApplicationContext("bean.xml");
		Person p = ctx.getBean("chinese" , Person.class);
		//两次通过p对象使用Axe对象
		p.useAxe();
		p.useAxe();
	}
}