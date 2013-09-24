package lee;

import org.springframework.context.*;
import org.springframework.context.support.*;

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
		//以类加载路径下的beans.xml文件创建Spring容器
		ApplicationContext ctx = new 
			ClassPathXmlApplicationContext("bean.xml");
		//判断两次请求singleton作用域的Bean实例是否相等
		System.out.println(ctx.getBean("p1")
			== ctx.getBean("p1"));
		//判断两次请求prototype作用域的Bean实例是否相等
		System.out.println(ctx.getBean("p2")
			== ctx.getBean("p2"));
	}
}
