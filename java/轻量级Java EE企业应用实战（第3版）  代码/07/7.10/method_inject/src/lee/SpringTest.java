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
	public static void main(String[] args)
	{
		ApplicationContext ctx =
				new ClassPathXmlApplicationContext("bean.xml");
		//获取son1 Bean实例
		Son son1 = ctx.getBean("son1" , Son.class);
		//输出son1的age值
		System.out.println("系统获取son1的age属性值："
			+ son1.getAge());
		//获取son2 Bean实例
		Son son2 = ctx.getBean("son2" , Son.class);
		//输出son2的age值
		System.out.println("系统获取son2的age属性值："
			+ son2.getAge());
		System.out.println("系统获取Java版本：" + ctx.getBean("javaVersion"));
	}
}