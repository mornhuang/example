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
		ApplicationContext ctx = new
			ClassPathXmlApplicationContext("bean.xml");
		//获取son Bean实例
		Son son = ctx.getBean("son" , Son.class);
		//输出son的age值
		System.out.println("系统获取son的age属性值："
			+ son.getAge());
		System.out.println("系统获取theAge1的值："
			+ ctx.getBean("theAge1"));
		System.out.println("系统获取theAge2的值："
			+ ctx.getBean("theAge2"));
	}
}