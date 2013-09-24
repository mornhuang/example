package lee;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import org.crazyit.app.service.*;
import org.crazyit.app.util.*;
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
	public static void main(String[] args)throws Exception
	{
		//CLASSPATH路径下的bean.xml文件创建Resource对象
		ClassPathResource isr = new ClassPathResource("bean.xml");
		//以Resource对象作为参数，创建BeanFactory的实例
		XmlBeanFactory factory = new XmlBeanFactory(isr);
		//获取Bean后处理器实例
		MyBeanPostProcessor prr = factory.getBean("beanPostProcessor"
			, MyBeanPostProcessor.class);
		//注册BeanPostProcessor实例
		factory.addBeanPostProcessor(prr);
		System.out.println("程序已经实例化BeanFactory...");
		Person p = factory.getBean("chinese" , Person.class);
		System.out.println("程序中已经完成了chinese bean的实例化...");
		p.useAxe();
//		ApplicationContext ctx = new ClassPathXmlApplicationContext("bean.xml");
//		Person p = (Person)ctx.getBean("chinese");
//		p.useAxe();
	}
}