
package lee;

import org.crazyit.ioc.*;
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
public class IoCTest
{
	public static void main(String[] args) 
		throws Exception
	{
		//创建IoC容器
		ApplicationContext ctx = 
			new YeekuXmlApplicationContext("bean.xml");
		//从IoC容器中取出computer Bean
		Computer c = (Computer)ctx.getBean("computer");
		//测试Computer对象
		c.keyIn("轻量级Java EE企业应用实战");
		c.keyIn("疯狂Java讲义");
		c.print();
		System.out.println(ctx.getBean("now"));
	}
}
